import { MyCurrencyPipe } from './../tipetransform/MyCurrencyPipe';
import { Directive, HostListener, ElementRef, OnInit, SimpleChanges } from "@angular/core";

@Directive({ selector: "[myCurrencyFormatter]" })
export class MyCurrencyFormatterDirective implements OnInit {

    el: HTMLInputElement;

    constructor(
        private elementRef: ElementRef,
        private currencyPipe: MyCurrencyPipe
    ) {
        this.el = this.elementRef.nativeElement;
    }

    @HostListener('change', ["$event.target.value"])
    ngOnChanges(value) {
        if (value) {
          
        }
    }

    ngOnInit() {
        this.el.value = this.currencyPipe.transform(this.el.value);
    }

    @HostListener("focus", ["$event.target.value"])
    onFocus(value) {
        this.el.value = this.currencyPipe.parse(value);
    }

    @HostListener("blur", ["$event.target.value"])
    onBlur(value) {
        this.el.value = this.currencyPipe.transform(value);
    }
}