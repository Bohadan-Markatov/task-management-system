import { Component } from '@angular/core';
import {RouterOutlet} from "@angular/router";
import {MenuComponent} from "../../components/menu/menu.component";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  standalone: true,
  imports: [
    MenuComponent,
    RouterOutlet
  ],
  styleUrl: './main.component.scss'
})
export class MainComponent {

}
