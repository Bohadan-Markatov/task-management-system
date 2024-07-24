import {Component, Input} from '@angular/core';
import {ProjectResponseDto} from "../../../../services/models/project-response-dto";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {TaskControllerService} from "../../../../services/services/task-controller.service";
import {CreateTaskRequestDto} from "../../../../services/models/create-task-request-dto";
import {Create$Params} from "../../../../services/fn/task-controller/create";
import {HttpErrorResponse} from "@angular/common/http";
import {AttachmentResponseDto} from "../../../../services/models/attachment-response-dto";
import {log} from "@angular-devkit/build-angular/src/builders/ssr-dev-server";

@Component({
  selector: 'app-create-task',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf,
    NgIf
  ],
  templateUrl: './create-task.component.html',
  styleUrl: './create-task.component.scss'
})
export class CreateTaskComponent {
  name: string = '';
  description: string = '';
  priority: 'LOW' | 'MEDIUM' | 'HIGH' = 'LOW';
  status: 'NOT_STARTED' | 'IN_PROGRESS' | 'COMPLETED' = 'NOT_STARTED';
  deadline: string = '';
  priorityOptions: ('LOW' | 'MEDIUM' | 'HIGH')[] = ['LOW', 'MEDIUM', 'HIGH'];
  statusOptions: ('NOT_STARTED' | 'IN_PROGRESS' | 'COMPLETED')[] = ['NOT_STARTED', 'IN_PROGRESS', 'COMPLETED'];
  errorMsg: Array<string> = [];
  @Input() projectId!: number;
  @Input() userId!: number;
  constructor(public activeModal: NgbActiveModal, private taskService: TaskControllerService) {
  }

  close() {
    this.activeModal.close();
  }

  create() {
    const param: CreateTaskRequestDto = {
      deadline: this.deadline,
      description: this.description,
      name: this.name,
      status: this.status,
      priority: this.priority,
      projectId: this.projectId,
      responsibleUserId: this.userId
    }

    this.taskService.createTask(param).subscribe({
      next: (response) => {
        this.close();
      },
      error: (err) => {
        const error: HttpErrorResponse = err;
        this.errorMsg = error.error.errors;
      }
    })
  }
}
