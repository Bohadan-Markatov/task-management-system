import {RouterModule, Routes} from '@angular/router';
import {NgModule} from "@angular/core";
import {LoginComponent} from "./pages/login/login.component";
import {RegisterComponent} from "./pages/register/register.component";
import {TaskRoutingModule} from "./modules/task/task-routing.module";
import {authGuard} from "./services/guard/auth.guard";
import {OAuth2CallbackComponent} from "./modules/task/components/oauth2-callback/oauth2-callback.component";

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'register',
    component: RegisterComponent
  },
  {
    path: 'tasks',
    loadChildren: () => import('./modules/task/task.module').then(m => m.TaskModule),
    canActivate: [authGuard]
  },
  {
    path: 'projects',
    loadChildren: () => import('./modules/task/project.module').then(m => m.ProjectModule),
    canActivate: [authGuard]
  },
  {
    path: 'tasks/:projectId',
    loadChildren: () => import('./modules/task/project-tasks.module').then(m => m.ProjectTasksModule)
  },
  { path: 'oauth2/callback', component: OAuth2CallbackComponent },
  { path: '**', redirectTo: 'login' }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutes { }
