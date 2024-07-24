/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { CommentResponseDto } from '../../models/comment-response-dto';

export interface Create2$Params {
  taskId: number;
  text: string;
}

export function create2(http: HttpClient, rootUrl: string, params: Create2$Params, context?: HttpContext): Observable<StrictHttpResponse<CommentResponseDto>> {
  const rb = new RequestBuilder(rootUrl, create2.PATH, 'post');
  if (params) {
    rb.path('taskId', params.taskId, {});
    rb.query('text', params.text, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<CommentResponseDto>;
    })
  );
}

create2.PATH = '/api/comments/task/{taskId}';
