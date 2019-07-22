
import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-done-modal',
  templateUrl: './done-modal.component.html',
  styleUrls: ['./done-modal.component.css']
})
export class DoneModalComponent {
  @Input() name;

  constructor(public activeModal: NgbActiveModal) {
  }
}
