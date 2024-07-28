import {Component, EventEmitter, Input, Output} from '@angular/core';
import {CommentResponseDto} from "../../../../services/models/comment-response-dto";
import {FormsModule} from "@angular/forms";
import {CommonModule} from "@angular/common";
import {CommentControllerService} from "../../../../services/services/comment-controller.service";
import {Delete2$Params} from "../../../../services/fn/comment-controller/delete-2";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-my-comment',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule
  ],
  templateUrl: './my-comment.component.html',
  styleUrl: './my-comment.component.scss'
})
export class MyCommentComponent {
  errorMsg: Array<string> = [];
  @Input() comment!: CommentResponseDto
  @Output() commentDelete = new EventEmitter<number>();
  @Output() commentUpdate = new EventEmitter<CommentResponseDto>();


  isEditMode: boolean = false;
  editedText: string = '';

  constructor(private commentService: CommentControllerService) {
  }

  delete() {
    const params: Delete2$Params = {
      commentId: this.comment.id as number
    }
    this.commentService.delete2(params).subscribe(() => {
      this.commentDelete.emit(this.comment.id)
      })
  }

  update(text: string) {
    if (text !== this.comment.text) {
      this.commentService.upd(text, this.comment.id as number).subscribe(
        (updatedComment) => {
          this.commentUpdate.emit(updatedComment)
        },
        (err) => {
          const error: HttpErrorResponse = err;
          this.errorMsg = error.error.errors;
        }
      );
    }
  }

  update1(text: string) {
  this.commentService.upd(text, this.comment.id as number).subscribe(
    (updatedComment) => {
      this.commentUpdate.emit(updatedComment);
    },
    (error) => {
      // Здесь вы можете обработать ошибку
      console.error('Error updating comment:', error);
      this.errorMsg = error.error.errors || ['An unexpected error occurred'];
    }
  );
}

  toggleEditMode() {
    this.isEditMode = !this.isEditMode;
    if (this.isEditMode) {
      this.editedText = this.comment.text || '';
    }
  }

  saveEdit() {
    this.update(this.editedText);
    this.toggleEditMode();
  }
}
