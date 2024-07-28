import {RouterModule, Routes} from "@angular/router";
import {MainComponent} from "./pages/main/main.component";
import {authGuard} from "../../services/guard/auth.guard";
import {NgModule} from "@angular/core";
import {ProjectTasksComponent} from "./components/project-tasks/project-tasks.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [authGuard],
    children: [
      {
        path: '',
        component: ProjectTasksComponent,
        canActivate: [authGuard]
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class ProjectTasksRoutingModule { }
