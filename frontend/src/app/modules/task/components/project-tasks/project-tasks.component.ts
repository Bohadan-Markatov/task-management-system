import {Component, Input, OnInit} from '@angular/core';
import {TaskResponseDto} from "../../../../services/models/task-response-dto";
import {NgForOf, NgIf} from "@angular/common";
import {ProjectTaskComponent} from "../project-task/project-task.component";
import {ActivatedRoute, Router} from "@angular/router";
import {TaskControllerService} from "../../../../services/services/task-controller.service";

@Component({
  selector: 'app-project-tasks',
  standalone: true,
  imports: [
    NgForOf,
    NgIf,
    ProjectTaskComponent
  ],
  templateUrl: './project-tasks.component.html',
  styleUrl: './project-tasks.component.scss'
})
export class ProjectTasksComponent implements OnInit {
  @Input() projectId!: number;
  taskResponse: Array<TaskResponseDto> = [];

  constructor(private route: ActivatedRoute, private taskService: TaskControllerService) {
  }

  ngOnInit(): void {
    this.projectId = this.route.snapshot.params['projectId'];
    this.taskService.getByProject(this.projectId).subscribe({
        next: (tasks) => {
          this.taskResponse = tasks;
        }
      }
    )
  }

  onTaskDeleted(taskId: number) {
    this.taskResponse = this.taskResponse.filter(t => t.id !== taskId)
  }
}
