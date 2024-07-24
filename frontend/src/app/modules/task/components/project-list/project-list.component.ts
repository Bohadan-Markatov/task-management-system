import {Component, OnInit} from '@angular/core';
import {ProjectResponseDto} from "../../../../services/models/project-response-dto";
import {ProjectControllerService} from "../../../../services/services/project-controller.service";
import {NgForOf} from "@angular/common";
import {TeamMemberComponent} from "../team-member/team-member.component";
import {ProjectsComponent} from "../projects/projects.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {CreateProjectComponent} from "../create-project/create-project.component";

@Component({
  selector: 'app-project-list',
  standalone: true,
  imports: [
    NgForOf,
    TeamMemberComponent,
    ProjectsComponent,
    CreateProjectComponent
  ],
  templateUrl: './project-list.component.html',
  styleUrl: './project-list.component.scss'
})
export class ProjectListComponent implements OnInit {
  projects: Array<ProjectResponseDto> = [];

  constructor(private projectService: ProjectControllerService, private modalService: NgbModal) {
  }


  ngOnInit(): void {
    this.findAllProjects();
  }

  onProjectDelete(projectId: number) {
    this.projects = this.projects.filter(p => p.id !== projectId);
  }

  findAllProjects() {
    this.projectService.getAll().subscribe( {
      next: (project) => {
        this.projects = project;
      }
    })
  }

  createProject() {
    const modalRef = this.modalService.open(CreateProjectComponent,
      {size: "lg", windowClass: 'custom-modal-create-project'});

    modalRef.result.then((result) => {
      if (result) {
      }
    }).catch((error) => {
      console.log('Modal dismissed:', error);
    });

    modalRef.componentInstance.projectAdded.subscribe((project: ProjectResponseDto) => {
      this.onProjectAdded(project);
    });
  }

  onProjectAdded(project: ProjectResponseDto) {
    this.projects.push(project);
  }
}
