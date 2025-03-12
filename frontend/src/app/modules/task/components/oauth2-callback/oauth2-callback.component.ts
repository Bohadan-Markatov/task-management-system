import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../../../services/services/token/token.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-oauth2-callback',
  standalone: true,
  imports: [],
  template: '<p>Logging in...</p>',
  styleUrl: './oauth2-callback.component.scss'
})
export class OAuth2CallbackComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private tokenService: TokenService
  ) {}

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      const token = params['token'];
      if (token) {
        this.tokenService.token = token;
        this.router.navigate(['tasks']);
      } else {
        // handle error
      }
    });
  }
}
