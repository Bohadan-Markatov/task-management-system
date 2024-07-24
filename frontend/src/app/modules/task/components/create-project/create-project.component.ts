import {Component, EventEmitter, Output} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgForOf, NgIf} from "@angular/common";
import {ProjectResponseDto} from "../../../../services/models/project-response-dto";
import {ProjectControllerService} from "../../../../services/services/project-controller.service";
import {CreateProjectRequestDto} from "../../../../services/models/create-project-request-dto";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-create-project',
  standalone: true,
    imports: [
        FormsModule,
        NgForOf,
        NgIf,
        ReactiveFormsModule
    ],
  templateUrl: './create-project.component.html',
  styleUrl: './create-project.component.scss'
})
export class CreateProjectComponent {
  @Output() projectAdded = new EventEmitter<ProjectResponseDto>();
  errorMsg: Array<string> = [];
  name: string = '';
  description: string = '';

  constructor(private projectService: ProjectControllerService, public activeModal: NgbActiveModal) {
  }

  create() {
    const param: CreateProjectRequestDto = {
      name: this.name,
      description: this.description
    }
    this.projectService.createProject(param).subscribe({
      next: (response) => {
        this.projectAdded.emit(response)
        this.activeModal.close();
      },
      error: (err) => {
        this.errorMsg = err.error.errors;
      }
    })
  }

  close() {
    this.activeModal.close();
  }
}
