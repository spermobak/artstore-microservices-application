import { Component, Input } from '@angular/core';
import { trigger, style, animate, transition } from '@angular/animations';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  animations: [
    trigger('rotate', [
      transition('* => *', [
        style({ transform: 'rotate(0)' }),
        animate('1s', style({ transform: 'rotate(360deg)' }))
      ])
    ])
  ]
})
export class HeaderComponent {
	@Input() pageTitle!: string;
	@Input() logoSrc!: string;
}
