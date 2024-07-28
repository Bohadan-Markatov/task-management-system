import {Component, EventEmitter, Input, Output} from '@angular/core';
import {UserResponseDto} from "../../../../services/models/user-response-dto";
import {ReactiveFormsModule} from "@angular/forms";
import {ProjectControllerService} from "../../../../services/services/project-controller.service";
import {DeleteTeamMember$Params} from "../../../../services/fn/project-controller/delete-team-member";
import {CreateTaskComponent} from "../create-task/create-task.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {TaskProjectComponent} from "../task-project-component/task-project.component";

@Component({
  selector: 'app-team-member',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './team-member.component.html',
  styleUrl: './team-member.component.scss'
})
export class TeamMemberComponent {
  @Input() projectId?: number;
  @Input() user!: UserResponseDto;
  @Output() userDeleted = new EventEmitter<number>();

  constructor(private projectService: ProjectControllerService, private modalService: NgbModal) {
  }

  addTask() {
    const modalRef = this.modalService.open(CreateTaskComponent,
      {size: "lg", windowClass: 'custom-modal-create-task'});
    modalRef.componentInstance.projectId = this.projectId;
    modalRef.componentInstance.userId = this.user.id;

    modalRef.result.then((result) => {
      if (result) {
      }
    }).catch((error) => {
      console.log('Modal dismissed:', error);
    });

  }

  delete() {
    const param: DeleteTeamMember$Params = {
      projectId: this.projectId as number,
      teamMemberId: this.user.id as number
    }
    this.projectService.deleteTeamMember(param).subscribe(() => {
      this.userDeleted.emit(this.user.id);
    });
  }

}
