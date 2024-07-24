/* tslint:disable */
/* eslint-disable */
import { ProjectResponseDto } from '../models/project-response-dto';
import { UserResponseDto } from '../models/user-response-dto';
export interface TaskResponseDto {
  deadline?: string;
  description?: string;
  id?: number;
  name?: string;
  priority?: 'LOW' | 'MEDIUM' | 'HIGH';
  project?: ProjectResponseDto;
  responsibleUser?: UserResponseDto;
  status?: 'NOT_STARTED' | 'IN_PROGRESS' | 'COMPLETED';
}
