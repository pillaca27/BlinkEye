import { Component, OnInit, ViewChild, ChangeDetectorRef, Input, HostBinding } from '@angular/core';

@Component({
    selector: 'aprobacion-formulario',
    templateUrl: './aprobacion-formulario.component.html'
})
export class AprobacionFormularioComponent implements OnInit {
    @Input() public clase: string = "cienporciento";
    @Input() public claseTres: number = null;


    @HostBinding('class') clase2: string = "cienporciento";
    @HostBinding('style.height.px') height: number;

    ngOnInit() {
        //this.clase2 = this.clase;
        this.height = this.claseTres == 0 ? null : this.claseTres;
    }


}
