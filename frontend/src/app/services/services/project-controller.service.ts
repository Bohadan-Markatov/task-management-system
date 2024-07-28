/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { addTeamMember } from '../fn/project-controller/add-team-member';
import { AddTeamMember$Params } from '../fn/project-controller/add-team-member';
import { create1 } from '../fn/project-controller/create-1';
import { Create1$Params } from '../fn/project-controller/create-1';
import { delete1 } from '../fn/project-controller/delete-1';
import { Delete1$Params } from '../fn/project-controller/delete-1';
import { deleteTeamMember } from '../fn/project-controller/delete-team-member';
import { DeleteTeamMember$Params } from '../fn/project-controller/delete-team-member';
import { getAll2 } from '../fn/project-controller/get-all-2';
import { GetAll2$Params } from '../fn/project-controller/get-all-2';
import { ProjectResponseDto } from '../models/project-response-dto';
import { quitProject } from '../fn/project-controller/quit-project';
import { QuitProject$Params } from '../fn/project-controller/quit-project';
import {TaskResponseDto} from "../models/task-response-dto";
import {UserResponseDto} from "../models/user-response-dto";
import {CreateProjectComponent} from "../../modules/task/components/create-project/create-project.component";
import {CreateProjectRequestDto} from "../models/create-project-request-dto";
import {CreateTaskRequestDto} from "../models/create-task-request-dto";

@Injectable({ providedIn: 'root' })
export class ProjectControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `addTeamMember()` */
  static readonly AddTeamMemberPath = '/api/projects/{projectId}/team-members';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `addTeamMember()` instead.
   *
   * This method doesn't expect any request body.
   */
  addTeamMember$Response(params: AddTeamMember$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return addTeamMember(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `addTeamMember$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  addTeamMember(params: AddTeamMember$Params, context?: HttpContext): Observable<void> {
    return this.addTeamMember$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `deleteTeamMember()` */
  static readonly DeleteTeamMemberPath = '/api/projects/{projectId}/team-members';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteTeamMember()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteTeamMember$Response(params: DeleteTeamMember$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteTeamMember(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteTeamMember$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteTeamMember(params: DeleteTeamMember$Params, context?: HttpContext): Observable<void> {
    return this.deleteTeamMember$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getAll2()` */
  static readonly GetAll2Path = '/api/projects';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAll2()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll2$Response(params?: GetAll2$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<ProjectResponseDto>>> {
    return getAll2(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAll2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll2(params?: GetAll2$Params, context?: HttpContext): Observable<Array<ProjectResponseDto>> {
    return this.getAll2$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<ProjectResponseDto>>): Array<ProjectResponseDto> => r.body)
    );
  }

  /** Path part for operation `create1()` */
  static readonly Create1Path = '/api/projects';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `create1()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  create1$Response(params: Create1$Params, context?: HttpContext): Observable<StrictHttpResponse<ProjectResponseDto>> {
    return create1(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `create1$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  create1(params: Create1$Params, context?: HttpContext): Observable<ProjectResponseDto> {
    return this.create1$Response(params, context).pipe(
      map((r: StrictHttpResponse<ProjectResponseDto>): ProjectResponseDto => r.body)
    );
  }

  /** Path part for operation `delete1()` */
  static readonly Delete1Path = '/api/projects/{projectId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delete1()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete1$Response(params: Delete1$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delete1(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delete1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete1(params: Delete1$Params, context?: HttpContext): Observable<void> {
    return this.delete1$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `quitProject()` */
  static readonly QuitProjectPath = '/api/projects/{projectId}/quit';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `quitProject()` instead.
   *
   * This method doesn't expect any request body.
   */
  quitProject$Response(params: QuitProject$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return quitProject(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `quitProject$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  quitProject(params: QuitProject$Params, context?: HttpContext): Observable<void> {
    return this.quitProject$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  getAll(): Observable<ProjectResponseDto[]> {
    return this.http.get<ProjectResponseDto[]>('http://localhost:8080/api/projects');
  }

  addMember(memberEmail: string, projectId: number): Observable<UserResponseDto> {
    const dto: AddTeamMember$Params = {
      email: memberEmail,
      projectId: 0,
      teamMemberId: 0
    }
    const url = `http://localhost:8080/api/projects/${projectId}/team-members`;
    return this.http.put<UserResponseDto>(url, dto);
  }

  createProject(params: CreateProjectRequestDto): Observable<ProjectResponseDto> {
    const url: string = 'http://localhost:8080/api/projects';
    return this.http.post<ProjectResponseDto>(url, params);
  }
}
