import {Component, EventEmitter, Input, Output} from '@angular/core';
import {NgForOf} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {AttachmentResponseDto} from "../../../../services/models/attachment-response-dto";
import {AttachmentControllerService} from "../../../../services/services/attachment-controller.service";
import {Delete3$Params} from "../../../../services/fn/attachment-controller/delete-3";

@Component({
  selector: 'app-attachment',
  standalone: true,
    imports: [
        NgForOf,
        ReactiveFormsModule
    ],
  templateUrl: './attachment.component.html',
  styleUrl: './attachment.component.scss'
})
export class AttachmentComponent {
  @Input() attachment!: AttachmentResponseDto
  @Output() attachmentDeleted = new EventEmitter<number>();

  constructor(private attachmentService: AttachmentControllerService) {
  }

  goByLink() {
    window.open(this.attachment.filePublicLink, '_blank');
  }

  delete() {
    const params: Delete3$Params = {
      attachmentId: this.attachment.id as number
    };
    this.attachmentService.delete3(params).subscribe(() => {
      this.attachmentDeleted.emit(this.attachment.id);
    });
  }
}
