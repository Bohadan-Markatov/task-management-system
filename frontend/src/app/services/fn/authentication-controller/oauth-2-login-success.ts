/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { UserLoginResponseDto } from '../../models/user-login-response-dto';

export interface Oauth2LoginSuccess$Params {
}

export function oauth2LoginSuccess(http: HttpClient, rootUrl: string, params?: Oauth2LoginSuccess$Params, context?: HttpContext): Observable<StrictHttpResponse<UserLoginResponseDto>> {
  const rb = new RequestBuilder(rootUrl, oauth2LoginSuccess.PATH, 'get');
  if (params) {
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<UserLoginResponseDto>;
    })
  );
}

oauth2LoginSuccess.PATH = '/api/auth/oauth2-login';
