/* tslint:disable */
/* eslint-disable */
import { UserResponseDto } from '../models/user-response-dto';
export interface ProjectResponseDto {
  description?: string;
  id?: number;
  manager?: UserResponseDto;
  name?: string;
  teamMembers: Array<UserResponseDto>;
}
