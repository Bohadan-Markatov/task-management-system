import {Delete$Params} from "../../../../services/fn/task-controller/delete";
import {Component, EventEmitter, Input, OnInit, Output} from "@angular/core";
import {TaskControllerService} from "../../../../services/services/task-controller.service";
import {TaskResponseDto} from "../../../../services/models/task-response-dto";
import { HttpClient} from '@angular/common/http';
import {TaskProjectComponent} from "../task-project-component/task-project.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {AttachmentControllerService} from "../../../../services/services/attachment-controller.service";
import {AttachmentResponseDto} from "../../../../services/models/attachment-response-dto";
import {CommentsPageComponent} from "../comments-page/comments-page.component";
import {FormsModule} from "@angular/forms";
import {AttachmentComponent} from "../attachment/attachment.component";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-project-task',
  templateUrl: './project-task.component.html',
  standalone: true,
  imports: [
    FormsModule,
    AttachmentComponent,
    NgForOf
  ],
  // standalone: true,
  styleUrl: './project-task.component.scss'
})
export class ProjectTaskComponent implements OnInit {
  statusOptions: ('NOT_STARTED' | 'IN_PROGRESS' | 'COMPLETED')[] = ['NOT_STARTED', 'IN_PROGRESS', 'COMPLETED'];
  attachments: Array<AttachmentResponseDto> = [];

  constructor(private taskService: TaskControllerService, private attachmentService: AttachmentControllerService, private http: HttpClient, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.findAllAttachments();
  }

  @Input() task!: TaskResponseDto;
  @Output() statusUpdated: EventEmitter<TaskResponseDto> = new EventEmitter<TaskResponseDto>();
  @Output() taskDeleted = new EventEmitter<number>();

  onStatusChange(event: Event) {
    const selectedValue = (event.target as HTMLSelectElement).value as 'NOT_STARTED' | 'IN_PROGRESS' | 'COMPLETED';
    this.task.status = selectedValue;

    const dto = {
      status: selectedValue
    };

    const url = `http://localhost:8080/api/tasks/${this.task.id}`;
    const jsonBody1 = { status: selectedValue };

    this.http.put(url, jsonBody1).subscribe();
  }

  onAttachmentDeleted(attachmentId: number) {
    this.attachments = this.attachments.filter(a => a.id !== attachmentId);
  }


  delete() {
    const params: Delete$Params = {
      taskId: this.task.id as number
    };
    this.taskService.delete(params).subscribe(() => {
      this.taskDeleted.emit(this.task.id);
    });
  }

  openCommentDetailsModal() {
    const modalRef = this.modalService.open(CommentsPageComponent,
      { size: 'lg', windowClass: 'custom-modal-class' });
    modalRef.componentInstance.task = this.task;
  }

  private findAllAttachments() {
    this.attachmentService.getAll(this.task.id as number).subscribe({
      next: (attachments) => {
        this.attachments = attachments;
      }
    })
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      this.uploadFile(file);
    }
  }

  private uploadFile(file: File) {
    const formData = new FormData();
    formData.append('file', file);

    const url = `http://localhost:8080/api/attachments/task/${this.task.id}`;

    this.http.post<AttachmentResponseDto>(url, formData).subscribe({
      next: (response) => {
        this.attachments.push(response);
      },
      error: (err) => {
        console.error('Error uploading file', err);
      }
    });
  }

  openComments() {

  }
}
