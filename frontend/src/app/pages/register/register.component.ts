import { Component } from '@angular/core';
import {UserRegistrationRequestDto} from "../../services/models/user-registration-request-dto";
import {Router} from "@angular/router";
import {AuthenticationControllerService} from "../../services/services/authentication-controller.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})

export class RegisterComponent {
  registerRequest: UserRegistrationRequestDto = {email: '', firstname: '',
  lastname: '', password: '', repeatPassword: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationControllerService
  ) {
  }

  register() {
    this.errorMsg = [];
    this.authService.register({
      body: this.registerRequest
    }).subscribe({
      next: () => {
        this.router.navigate(['login'])
      },
      error: (err) => {
        const reader = new FileReader();
        reader.onloadend = () => {
          const text = reader.result as string;
          const errorObj = JSON.parse(text);
          this.errorMsg = errorObj.errors;
        };
        reader.readAsText(err.error);
      }
    });
  }

  login() {
    this.router.navigate(['login'])
  }
}
