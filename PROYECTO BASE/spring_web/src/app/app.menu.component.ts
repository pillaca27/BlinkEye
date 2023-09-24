import {Component, OnInit} from '@angular/core';
import {AppMainComponent} from './app.main.component';

@Component({
    selector: 'app-menu',
    template: `
		<ul class="layout-menu">
			<li app-menuitem *ngFor="let item of model; let i = index;" [item]="item" [index]="i" [root]="true"></li>
		</ul>
    `
})
export class AppMenuComponent implements OnInit {

    model: any[];

    constructor(public app: AppMainComponent) {}

    ngOnInit() {
        this.model = [
            {
                label: 'RRHH', icon: 'pi pi-fw pi-briefcase', routerLink: ['/erp'],
                items: [
                    {label: 'Capacitaciones', icon: 'pi pi-fw pi-pencil', routerLink: ['/portal/erp/capacitaciones']},
                ]
            },
        ];
    }
}
