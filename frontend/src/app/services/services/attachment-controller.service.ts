/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { AttachmentResponseDto } from '../models/attachment-response-dto';
import { create3 } from '../fn/attachment-controller/create-3';
import { Create3$Params } from '../fn/attachment-controller/create-3';
import { delete3 } from '../fn/attachment-controller/delete-3';
import { Delete3$Params } from '../fn/attachment-controller/delete-3';
import { getAll4 } from '../fn/attachment-controller/get-all-4';
import { GetAll4$Params } from '../fn/attachment-controller/get-all-4';
import {TaskResponseDto} from "../models/task-response-dto";

@Injectable({ providedIn: 'root' })
export class AttachmentControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getAll4()` */
  static readonly GetAll4Path = '/api/attachments/task/{taskId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAll4()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll4$Response(params: GetAll4$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<AttachmentResponseDto>>> {
    return getAll4(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAll4$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAll4(params: GetAll4$Params, context?: HttpContext): Observable<Array<AttachmentResponseDto>> {
    return this.getAll4$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<AttachmentResponseDto>>): Array<AttachmentResponseDto> => r.body)
    );
  }

  /** Path part for operation `create3()` */
  static readonly Create3Path = '/api/attachments/task/{taskId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `create3()` instead.
   *
   * This method doesn't expect any request body.
   */
  create3$Response(params: Create3$Params, context?: HttpContext): Observable<StrictHttpResponse<AttachmentResponseDto>> {
    return create3(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `create3$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  create3(params: Create3$Params, context?: HttpContext): Observable<AttachmentResponseDto> {
    return this.create3$Response(params, context).pipe(
      map((r: StrictHttpResponse<AttachmentResponseDto>): AttachmentResponseDto => r.body)
    );
  }

  /** Path part for operation `delete3()` */
  static readonly Delete3Path = '/api/attachments/{attachmentId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `delete3()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete3$Response(params: Delete3$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return delete3(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `delete3$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  delete3(params: Delete3$Params, context?: HttpContext): Observable<void> {
    return this.delete3$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  getAll(id: number): Observable<AttachmentResponseDto[]> {
    return this.http.get<AttachmentResponseDto[]>(`http://localhost:8080/api/attachments/task/${id}`)
  }
}
