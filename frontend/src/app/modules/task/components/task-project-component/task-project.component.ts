import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ProjectResponseDto} from "../../../../services/models/project-response-dto";
import {ProjectControllerService} from "../../../../services/services/project-controller.service";
import {quitProject, QuitProject$Params} from "../../../../services/fn/project-controller/quit-project";
import {FormsModule} from "@angular/forms";
import {NgForOf} from "@angular/common";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-task-project',
  standalone: true,
  imports: [
    FormsModule,
    NgForOf
  ],
  templateUrl: './task-project.component.html',
  styleUrl: './task-project.component.scss'
})
export class TaskProjectComponent {
  @Input() project!: ProjectResponseDto;
  @Output() projectQuit = new EventEmitter<number>();

  constructor(private projectService: ProjectControllerService, public activeModal: NgbActiveModal) {
  }

  closeProject() {
    this.activeModal.close();
  }

  quitProject() {
    const params: QuitProject$Params = {
      projectId: this.project.id as number
    }
    this.projectService.quitProject(params).subscribe(() => {
      this.projectQuit.emit(this.project.id)
      this.closeProject()
    });
  }
}
