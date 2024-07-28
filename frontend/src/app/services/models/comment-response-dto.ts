/* tslint:disable */
/* eslint-disable */
import {UserResponseDto} from "./user-response-dto";

export interface CommentResponseDto {
  author?: UserResponseDto;
  changed?: boolean;
  id?: number;
  publicationDate?: string;
  taskId?: number;
  text?: string;
}
