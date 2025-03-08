import {Component, ElementRef, HostListener, OnInit} from '@angular/core';
import {jwtDecode} from 'jwt-decode';
import {RouterLink} from "@angular/router";
import SockJS from "sockjs-client";
import Stomp from 'stompjs'
import {Notification} from "./notification";
import {ToastrService} from "ngx-toastr";
import {NgForOf, NgIf} from "@angular/common";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  standalone: true,
  imports: [
    RouterLink,
    NgForOf,
    NgIf
  ],
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit {
  isDropdownOpen = false;
  userEmail: string = '';
  socketClient: any = null;
  private notificationSubscription: any;
  unreadNotificationCount: number = 0;
  notifications: Array<Notification> = [];

  constructor(private toasterService: ToastrService, private eRef: ElementRef) {
  }
  ngOnInit(): void {
    this.loadUserEmail();
    if (this.userEmail !== '') {
      let ws = new SockJS('http://localhost:8080/ws');
      this.socketClient = Stomp.over(ws);
      this.socketClient.connect({Authorization: 'Bearer ' + localStorage.getItem('token')}, () => {
        this.notificationSubscription
          = this.socketClient.subscribe(`/user/${this.userEmail}/notifications`,
          (message: any) => {
            const notification: Notification = JSON.parse(message.body);
            if (notification) {
              this.notifications.unshift(notification);
              switch (notification.status) {
                case 'INFO':
                  this.toasterService.info(notification.message);
                  break;
                  case 'DELETED':
                    this.toasterService.error(notification.message);
                    break;
                    case 'ADDED':
                      this.toasterService.success(notification.message);
                      break;
              }
              this.unreadNotificationCount++
            }
          });
      })
    }
  }

  loadUserEmail(): void {
    const token = localStorage.getItem('token');
    if (token) {
      try {
        const decodedToken: any = jwtDecode(token);
        this.userEmail = decodedToken.sub;
        localStorage.setItem("email", decodedToken.sub)
      } catch (error) {
        console.error('Error decoding token:', error);
      }
    }
  }
  logout() {
    localStorage.removeItem('token');
    window.location.reload();
  }

  toggleDropdown() {
    this.isDropdownOpen = !this.isDropdownOpen;
    if (this.isDropdownOpen) {
      this.unreadNotificationCount = 0;
    }
  }

  @HostListener('document:click', ['$event'])
  handleClickOutside(event: Event) {
    if (this.isDropdownOpen && !this.eRef.nativeElement.contains(event.target)) {
      this.isDropdownOpen = false;
    }
  }
}
