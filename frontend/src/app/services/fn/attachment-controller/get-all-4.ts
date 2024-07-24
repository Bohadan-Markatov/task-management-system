/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { AttachmentResponseDto } from '../../models/attachment-response-dto';

export interface GetAll4$Params {
  taskId: number;
}

export function getAll4(http: HttpClient, rootUrl: string, params: GetAll4$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<AttachmentResponseDto>>> {
  const rb = new RequestBuilder(rootUrl, getAll4.PATH, 'get');
  if (params) {
    rb.path('taskId', params.taskId, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<AttachmentResponseDto>>;
    })
  );
}

getAll4.PATH = '/api/attachments/task/{taskId}';
