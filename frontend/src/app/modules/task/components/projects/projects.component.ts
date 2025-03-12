import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ProjectResponseDto} from "../../../../services/models/project-response-dto";
import {AttachmentComponent} from "../attachment/attachment.component";
import {NgForOf, NgIf} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TeamMemberComponent} from "../team-member/team-member.component";
import {ProjectControllerService} from "../../../../services/services/project-controller.service";
import {Delete1$Params} from "../../../../services/fn/project-controller/delete-1";
import {HttpErrorResponse} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-projects',
  standalone: true,
  imports: [
    AttachmentComponent,
    NgForOf,
    ReactiveFormsModule,
    TeamMemberComponent,
    FormsModule,
    NgIf
  ],
  templateUrl: './projects.component.html',
  styleUrl: './projects.component.scss'
})
export class ProjectsComponent {
  errorMsg: Array<string> = [];
  teamMemberEmail: string = '';
  @Input() project!: ProjectResponseDto;
  @Output() projectDeleted = new EventEmitter<number>();

  constructor(private projectService: ProjectControllerService, private router: Router,) {
  }

  onUserDeleted(teamMemberId: number) {
    this.project.teamMembers = this.project.teamMembers.filter(t => t.id !== teamMemberId);
  }

  allTasks() {
    this.router.navigate(['/tasks', this.project.id]);
  }

  addTeamMember() {
    this.errorMsg = [];
    this.projectService.addMember(this.teamMemberEmail, this.project.id as number).subscribe({
      next: (newMember) => {
        this.project.teamMembers.push(newMember);
        this.teamMemberEmail = '';
      },
      error: (err) => {
        const error: HttpErrorResponse = err;
        this.errorMsg = error.error.errors;
        this.teamMemberEmail = '';
      }
    });
  }

  delete() {
    const param: Delete1$Params = {
      projectId: this.project.id as number
    }
    this.projectService.delete1(param).subscribe(() => {
      this.projectDeleted.emit(this.project.id);
    });
  }
}
