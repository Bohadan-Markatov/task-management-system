/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { CommentResponseDto } from '../models/comment-response-dto';
import { create2 } from '../fn/comment-controller/create-2';
import { Create2$Params } from '../fn/comment-controller/create-2';
import { delete2 } from '../fn/comment-controller/delete-2';
import { Delete2$Params } from '../fn/comment-controller/delete-2';
import { getAll3 } from '../fn/comment-controller/get-all-3';
import { GetAll3$Params } from '../fn/comment-controller/get-all-3';
import { update } from '../fn/comment-controller/update';
import { Update$Params } from '../fn/comment-controller/update';
import {AttachmentResponseDto} from "../models/attachment-response-dto";
import {CommentRequestDto} from "../models/comment-request-dto";

@Injectable({ providedIn: 'root' })
export class CommentControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `update()` */
  static readonly UpdatePath = '/api/comments/{commentId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `update()` instead.
   *
   * This method doesn't expect any request body.
   */
  update$Response(params: Update$Params, context?: HttpContext): Observable<StrictHttpResponse<CommentResponseDto>> {
    return update(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `update$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  update(params: Update$Params, context?: HttpContext): Observable<CommentResponseDto> {
    return this.update$Response(params, context).pipe(
      map((r: StrictHttpResponse<CommentResponseDto>): CommentResponseDto => r.body)
    );
  }

  /** Path part for operation `delete2()` */
  static readonly Delete2Path = '/api/comments/{commentId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delete2()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete2$Response(params: Delete2$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delete2(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delete2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete2(params: Delete2$Params, context?: HttpContext): Observable<void> {
    return this.delete2$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getAll3()` */
  static readonly GetAll3Path = '/api/comments/task/{taskId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAll3()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll3$Response(params: GetAll3$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<CommentResponseDto>>> {
    return getAll3(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAll3$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll3(params: GetAll3$Params, context?: HttpContext): Observable<Array<CommentResponseDto>> {
    return this.getAll3$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<CommentResponseDto>>): Array<CommentResponseDto> => r.body)
    );
  }

  /** Path part for operation `create2()` */
  static readonly Create2Path = '/api/comments/task/{taskId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `create2()` instead.
   *
   * This method doesn't expect any request body.
   */
  create2$Response(params: Create2$Params, context?: HttpContext): Observable<StrictHttpResponse<CommentResponseDto>> {
    return create2(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `create2$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  create2(params: Create2$Params, context?: HttpContext): Observable<CommentResponseDto> {
    return this.create2$Response(params, context).pipe(
      map((r: StrictHttpResponse<CommentResponseDto>): CommentResponseDto => r.body)
    );
  }

  getAll(id: number): Observable<CommentResponseDto[]> {
    return this.http.get<AttachmentResponseDto[]>(`http://localhost:8080/api/comments/task/${id}`)
  }

  add(text: string, id: number): Observable<CommentResponseDto> {
    const param: CommentRequestDto = {
      text: text
    }
    return this.http.post<CommentResponseDto>(`http://localhost:8080/api/comments/task/${id}`, param)
  }

  upd(text: string, id: number): Observable<CommentResponseDto> {
    const param: CommentRequestDto = {
      text: text
    }
    return this.http.put<CommentResponseDto>(`http://localhost:8080/api/comments/${id}`, param)
  }
}
