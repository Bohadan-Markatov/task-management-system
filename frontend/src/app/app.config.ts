import {ApplicationConfig, NgModule, provideZoneChangeDetection} from '@angular/core';
import {AppComponent} from "./app.component";
import {BrowserModule} from "@angular/platform-browser";
import {AppRoutes} from "./app.routes";
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from "@angular/common/http";
import {LoginComponent} from "./pages/login/login.component";
import {FormsModule} from "@angular/forms";
import {RegisterComponent} from "./pages/register/register.component";
import {HttpTokenInterceptor} from "./services/interceptor/http-token.interceptor";
import {TaskCardComponent} from "./modules/task/components/task-component/task-card.component";
import {TaskListComponent} from "./modules/task/pages/task-list/task-list.component";
import {AttachmentComponent} from "./modules/task/components/attachment/attachment.component";
import {CreateTaskComponent} from "./modules/task/components/create-task/create-task.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    TaskListComponent,
    TaskCardComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutes,
        HttpClientModule,
        FormsModule,
        AttachmentComponent,
    ],
  providers: [
    HttpClient,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpTokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
})
export class AppConfig { }
