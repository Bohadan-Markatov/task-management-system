import {Component, OnInit} from '@angular/core';
import {UserLoginRequestDto} from "../../services/models/user-login-request-dto";
import {Router} from "@angular/router";
import {AuthenticationControllerService} from "../../services/services/authentication-controller.service";
import {TokenService} from "../../services/services/token/token.service";
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  authRequest: UserLoginRequestDto = {email: '', password: ''};
  errorMsg: Array<string> = [];

  constructor(
    private router: Router,
    private authService: AuthenticationControllerService,
    private tokenService: TokenService,
  ) {}

  login() {
    this.errorMsg = [];
    this.authService.login({
      body: this.authRequest
    }).subscribe({
      next: (res: any) => {
        this.readBlobResponse(res)
        this.router.navigate(['tasks']);
      },
      error: (err) => {
        if (err.status === 401) {
          this.errorMsg.push('Email or/and password are incorrect');
        } else if (err.error instanceof Blob) {
          const reader = new FileReader();
          reader.onloadend = () => {
            const text = reader.result as string;
            const errorObj = JSON.parse(text);
            if (errorObj.errors) {
              this.errorMsg = errorObj.errors;
            } else {
              this.errorMsg.push('Unexpected error format');
            }
          };
          reader.readAsText(err.error);
        } else {
          this.errorMsg.push('An unexpected error occurred');
        }
      }
    });
  }

  register() {
    this.router.navigate(['register'])
  }

  private readBlobResponse(blob: Blob) {
    const reader = new FileReader();
    reader.onloadend = () => {
      const text = reader.result as string;
      const responseObject = JSON.parse(text);
      this.tokenService.token = responseObject.token;
    };
    reader.readAsText(blob);
  }

  googleLogin() {
    window.location.href = 'http://localhost:8080/api/auth/oauth2-login';
  }
}
