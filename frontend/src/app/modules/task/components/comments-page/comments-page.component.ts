import {Component, Input, OnInit} from '@angular/core';
import {CommentResponseDto} from "../../../../services/models/comment-response-dto";
import {TaskResponseDto} from "../../../../services/models/task-response-dto";
import {CommentControllerService} from "../../../../services/services/comment-controller.service";
import {MyCommentComponent} from "../my-comment/my-comment.component";
import {CommentComponent} from "../comment/comment.component";
import {CommonModule} from "@angular/common";
import {AttachmentComponent} from "../attachment/attachment.component";
import {FormsModule} from "@angular/forms";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-comments-page',
  standalone: true,
  imports: [
    MyCommentComponent,
    CommentComponent,
    CommonModule,
    AttachmentComponent,
    FormsModule
  ],
  templateUrl: './comments-page.component.html',
  styleUrl: './comments-page.component.scss'
})
export class CommentsPageComponent implements OnInit {
  comment: string = "";
  comments: Array<CommentResponseDto> = [];
  @Input()task: TaskResponseDto = {};
  errorMsg: Array<string> = [];

  constructor(private commentService: CommentControllerService, public activeModal: NgbActiveModal) {
  }

  ngOnInit(): void {
    this.commentService.getAll(this.task.id as number).subscribe({
      next: (comments) => {
        this.comments = comments;
      }
    })
  }

  onCommentDeleted(commentId: number) {
    this.comments = this.comments.filter(c => c.id !== commentId);
  }

  onCommentUpdate(updatedComment: CommentResponseDto) {
    const index = this.comments.findIndex(c => c.id === updatedComment.id);
    if (index !== -1) {
      this.comments[index] = updatedComment;
    }
  }

  addComment() {
    this.commentService.add(this.comment, this.task.id as number).subscribe({
      next: (newComment) => {
        this.comments.push(newComment);
        this.comment = "";
      },
      error: (err) => {
        const error: HttpErrorResponse = err;
        this.errorMsg = error.error.errors;
      }
    });
  }

  protected readonly localStorage = localStorage;

  closeProject() {
    this.activeModal.close();
  }
}
