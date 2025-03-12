/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { confirmEmail } from '../fn/authentication-controller/confirm-email';
import { ConfirmEmail$Params } from '../fn/authentication-controller/confirm-email';
import { login } from '../fn/authentication-controller/login';
import { Login$Params } from '../fn/authentication-controller/login';
import { oauth2LoginSuccess } from '../fn/authentication-controller/oauth-2-login-success';
import { Oauth2LoginSuccess$Params } from '../fn/authentication-controller/oauth-2-login-success';
import { register } from '../fn/authentication-controller/register';
import { Register$Params } from '../fn/authentication-controller/register';
import { UserLoginResponseDto } from '../models/user-login-response-dto';
import { UserResponseDto } from '../models/user-response-dto';

@Injectable({ providedIn: 'root' })
export class AuthenticationControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `register()` */
  static readonly RegisterPath = '/api/auth/registration';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `register()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  register$Response(params: Register$Params, context?: HttpContext): Observable<StrictHttpResponse<UserResponseDto>> {
    return register(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `register$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  register(params: Register$Params, context?: HttpContext): Observable<UserResponseDto> {
    return this.register$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserResponseDto>): UserResponseDto => r.body)
    );
  }

  /** Path part for operation `login()` */
  static readonly LoginPath = '/api/auth/login';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `login()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  login$Response(params: Login$Params, context?: HttpContext): Observable<StrictHttpResponse<UserLoginResponseDto>> {
    return login(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `login$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  login(params: Login$Params, context?: HttpContext): Observable<UserLoginResponseDto> {
    return this.login$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserLoginResponseDto>): UserLoginResponseDto => r.body)
    );
  }

  /** Path part for operation `oauth2LoginSuccess()` */
  static readonly Oauth2LoginSuccessPath = '/api/auth/oauth2-login';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `oauth2LoginSuccess()` instead.
   *
   * This method doesn't expect any request body.
   */
  oauth2LoginSuccess$Response(params?: Oauth2LoginSuccess$Params, context?: HttpContext): Observable<StrictHttpResponse<UserLoginResponseDto>> {
    return oauth2LoginSuccess(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `oauth2LoginSuccess$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  oauth2LoginSuccess(params?: Oauth2LoginSuccess$Params, context?: HttpContext): Observable<UserLoginResponseDto> {
    return this.oauth2LoginSuccess$Response(params, context).pipe(
      map((r: StrictHttpResponse<UserLoginResponseDto>): UserLoginResponseDto => r.body)
    );
  }

  /** Path part for operation `confirmEmail()` */
  static readonly ConfirmEmailPath = '/api/auth/confirm-email';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `confirmEmail()` instead.
   *
   * This method doesn't expect any request body.
   */
  confirmEmail$Response(params: ConfirmEmail$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return confirmEmail(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `confirmEmail$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  confirmEmail(params: ConfirmEmail$Params, context?: HttpContext): Observable<string> {
    return this.confirmEmail$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

}
