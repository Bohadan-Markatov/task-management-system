import {
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpInterceptorFn,
  HttpRequest
} from '@angular/common/http';
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {TokenService} from "../services/token/token.service";

@Injectable()
export class HttpTokenInterceptor implements HttpInterceptor {

  constructor(
    private tokenService: TokenService
  ) {
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const token: string = this.tokenService.token;
    if (token) {
      const authReq = request.clone({
        headers: new HttpHeaders({
          Authorization: 'Bearer ' + token
        })
      });
      return next.handle(authReq)
    }
    return next.handle(request);
  }

}
export const httpTokenInterceptor: HttpInterceptorFn = (req, next) => {
  return next(req);
};
