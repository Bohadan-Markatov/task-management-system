import {Component, OnInit} from '@angular/core';
import {TaskControllerService} from "../../../../services/services/task-controller.service";
import {TaskResponseDto} from "../../../../services/models/task-response-dto";

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.scss'],
})
export class TaskListComponent implements OnInit {
  taskResponse: Array<TaskResponseDto> = [];

  constructor(
    private taskService: TaskControllerService,
  ) {
  }

  ngOnInit(): void {
    this.findAllTasks();
  }

  onTaskDeleted(taskId: number) {
    this.taskResponse = this.taskResponse.filter(a => a.id !== taskId)
  }

  onProjectQuit(projectId: number) {
    this.taskResponse = this.taskResponse.filter(task => task.project?.id !== projectId)
  }

  private findAllTasks() {
    this.taskService.getByResponsible().subscribe({
      next: (tasks) => {
        this.taskResponse = tasks;
      }
    })
  }
}
