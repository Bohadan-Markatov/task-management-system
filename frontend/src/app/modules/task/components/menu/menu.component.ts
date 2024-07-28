import {Component, OnInit} from '@angular/core';
import {jwtDecode} from 'jwt-decode';
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  standalone: true,
  imports: [
    RouterLink
  ],
  styleUrl: './menu.component.scss'
})
export class MenuComponent implements OnInit {
  userEmail: string = '';

  ngOnInit(): void {
    this.loadUserEmail();
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
}
