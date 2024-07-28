/* tslint:disable */
/* eslint-disable */
export interface CreateTaskRequestDto {
  deadline: string;
  description: string;
  name: string;
  priority?: 'LOW' | 'MEDIUM' | 'HIGH';
  projectId: number;
  responsibleUserId: number;
  status?: 'NOT_STARTED' | 'IN_PROGRESS' | 'COMPLETED';
}
