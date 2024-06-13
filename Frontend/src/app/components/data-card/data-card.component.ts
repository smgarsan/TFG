import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-data-card',
  standalone: true,
  imports: [],
  templateUrl: './data-card.component.html',
  styleUrl: './data-card.component.scss'
})
export class DataCardComponent {
  @Input() imageSrc?: string;
  @Input() title?: string;
  @Input() data?: string;

}
