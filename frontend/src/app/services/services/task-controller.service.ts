/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { create } from '../fn/task-controller/create';
import { Create$Params } from '../fn/task-controller/create';
import { delete$ } from '../fn/task-controller/delete';
import { Delete$Params } from '../fn/task-controller/delete';
import { getAll } from '../fn/task-controller/get-all';
import { GetAll$Params } from '../fn/task-controller/get-all';
import { getAll1 } from '../fn/task-controller/get-all-1';
import { GetAll1$Params } from '../fn/task-controller/get-all-1';
import { TaskResponseDto } from '../models/task-response-dto';
import { updateStatus } from '../fn/task-controller/update-status';
import { UpdateStatus$Params } from '../fn/task-controller/update-status';
import {CreateTaskRequestDto} from "../models/create-task-request-dto";

@Injectable({ providedIn: 'root' })
export class TaskControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `updateStatus()` */
  static readonly UpdateStatusPath = '/api/tasks/{taskId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateStatus()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateStatus$Response(params: UpdateStatus$Params, context?: HttpContext): Observable<StrictHttpResponse<TaskResponseDto>> {
    return updateStatus(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateStatus$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateStatus(params: UpdateStatus$Params, context?: HttpContext): Observable<TaskResponseDto> {
    return this.updateStatus$Response(params, context).pipe(
      map((r: StrictHttpResponse<TaskResponseDto>): TaskResponseDto => r.body)
    );
  }

  /** Path part for operation `delete()` */
  static readonly DeletePath = '/api/tasks/{taskId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delete()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete$Response(params: Delete$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delete$(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delete$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete(params: Delete$Params, context?: HttpContext): Observable<void> {
    return this.delete$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `create()` */
  static readonly CreatePath = '/api/tasks';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `create()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  create$Response(params: Create$Params, context?: HttpContext): Observable<StrictHttpResponse<TaskResponseDto>> {
    return create(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `create$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  create(params: Create$Params, context?: HttpContext): Observable<TaskResponseDto> {
    return this.create$Response(params, context).pipe(
      map((r: StrictHttpResponse<TaskResponseDto>): TaskResponseDto => r.body)
    );
  }

  /** Path part for operation `getAll()` */
  static readonly GetAllPath = '/api/tasks/responsible';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAll()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll$Response(params?: GetAll$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<TaskResponseDto>>> {
    return getAll(this.http, this.rootUrl, params, context);
  }

  getByResponsible(): Observable<TaskResponseDto[]> {
    return this.http.get<TaskResponseDto[]>('http://localhost:8080/api/tasks/responsible');
  }

  getByProject(projectId: number): Observable<TaskResponseDto[]> {
    return this.http.get<TaskResponseDto[]>('http://localhost:8080/api/tasks/project/' + projectId);
  }
  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAll$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll(params?: GetAll$Params, context?: HttpContext): Observable<Array<TaskResponseDto>> {
    return this.getAll$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<TaskResponseDto>>): Array<TaskResponseDto> => r.body)
    );
  }

  /** Path part for operation `getAll1()` */
  static readonly GetAll1Path = '/api/tasks/project/{projectId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAll1()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll1$Response(params: GetAll1$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<TaskResponseDto>>> {
    return getAll1(this.http, this.rootUrl, params, context);
  }

  createTask(params: CreateTaskRequestDto): Observable<TaskResponseDto> {
    const url: string = 'http://localhost:8080/api/tasks';
    return this.http.post<TaskResponseDto>(url, params);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAll1$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll1(params: GetAll1$Params, context?: HttpContext): Observable<Array<TaskResponseDto>> {
    return this.getAll1$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<TaskResponseDto>>): Array<TaskResponseDto> => r.body)
    );
  }

}
