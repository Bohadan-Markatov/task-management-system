import {Component, Input} from '@angular/core';
import {CommentResponseDto} from "../../../../services/models/comment-response-dto";
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-comment',
  standalone: true,
  imports: [
    CommonModule
  ],
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.scss'
})
export class CommentComponent {
  @Input() comment!: CommentResponseDto

}
