import { Directive, OnInit, forwardRef, ElementRef, HostListener, Renderer2 } from '@angular/core';
import { DecimalPipe } from '@angular/common';

@Directive({
  selector: '[CurrencyFormat]'
})
export class CurrencyFormatPipe implements OnInit {
  //currencyChars = new RegExp( /^\d*\.?\d*$/);//new RegExp('[\,]', 'g'); 

  constructor(public el: ElementRef, public renderer: Renderer2, private decimalPipe: DecimalPipe) { }

  ngOnInit() {
    this.format(this.el.nativeElement.value);
  }

  @HostListener('input', ["$event.target.value"]) onInput(e: string) {
    this.format(e);
  };

  @HostListener('paste', ['$event']) onPaste(event: ClipboardEvent) {
    event.preventDefault();
    this.format(event.clipboardData.getData('text/plain'));
  }

  format(val: string) {
    if (val != '' || val != undefined || val != null) {
      val = val.split(',').join('');
      let numberFormat;
      if (!this.isNumeric(val)) {
        numberFormat = parseFloat(val);
        val = numberFormat.toString();
      }


      let usd;
      let puntoencontrado = val.indexOf('.');
      if (puntoencontrado > 0) {
        if (puntoencontrado == val.length - 1) {
          usd = val;
        } else {
          usd = this.decimalPipe.transform(val);
        }
      } else {
        usd = this.decimalPipe.transform(val);
      }

      this.renderer.setProperty(this.el.nativeElement, 'value', usd);
    }
  }

  isNumeric(str) {
    if (typeof str != "string") return false
    return !isNaN(str as any) &&
      !isNaN(parseFloat(str))
  }
}