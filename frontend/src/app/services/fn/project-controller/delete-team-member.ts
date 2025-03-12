/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';


export interface DeleteTeamMember$Params {
  projectId: number;
  teamMemberId: number;
}

export function deleteTeamMember(http: HttpClient, rootUrl: string, params: DeleteTeamMember$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
  const rb = new RequestBuilder(rootUrl, deleteTeamMember.PATH, 'delete');
  if (params) {
    rb.path('projectId', params.projectId, {});
    rb.query('teamMemberId', params.teamMemberId, {});
  }

  return http.request(
    rb.build({ responseType: 'text', accept: '*/*', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return (r as HttpResponse<any>).clone({ body: undefined }) as StrictHttpResponse<void>;
    })
  );
}

deleteTeamMember.PATH = '/api/projects/{projectId}/team-members';
