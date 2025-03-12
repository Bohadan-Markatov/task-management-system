/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { AttachmentResponseDto } from '../../models/attachment-response-dto';

export interface Create3$Params {
  taskId: number;
  filePath: string;
}

export function create3(http: HttpClient, rootUrl: string, params: Create3$Params, context?: HttpContext): Observable<StrictHttpResponse<AttachmentResponseDto>> {
  const rb = new RequestBuilder(rootUrl, create3.PATH, 'post');
  if (params) {
    rb.path('taskId', params.taskId, {});
    rb.query('filePath', params.filePath, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<AttachmentResponseDto>;
    })
  );
}

create3.PATH = '/api/attachments/task/{taskId}';
