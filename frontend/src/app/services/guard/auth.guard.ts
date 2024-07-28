import {CanActivateFn, Router} from '@angular/router';
import {TokenService} from "../services/token/token.service";
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = (route, state) => {
  const tokenService = inject(TokenService);
  const router: Router = inject(Router);
  if (tokenService.isTokenNotValid()) {
    router.navigate(['login']);
    return false;
  }
  return true;
};
