/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { TaskResponseDto } from '../../models/task-response-dto';

export interface GetAll1$Params {
  projectId: number;
}

export function getAll1(http: HttpClient, rootUrl: string, params: GetAll1$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<TaskResponseDto>>> {
  const rb = new RequestBuilder(rootUrl, getAll1.PATH, 'get');
  if (params) {
    rb.path('projectId', params.projectId, {});
  }

  return http.request(
    rb.build({ responseType: 'blob', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<TaskResponseDto>>;
    })
  );
}

getAll1.PATH = '/api/tasks/project/{projectId}';
