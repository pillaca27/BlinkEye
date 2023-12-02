
import { DomSanitizer } from '@angular/platform-browser';
import { DtoFlujoAdjunto } from './DtoFlujoTransaccionRequest';

export class DtoWfTransaccionAdjunto extends DtoFlujoAdjunto {
    private _inner: any = '';
    tamanio: number;
    altura: number;

    tipo: string;

    inner: any;

    _inner2: any;

    constructor(private sanitizer: DomSanitizer) {
        super();
    }

    get inner2(): any {
        if (this.tipo == "HTML") {
            return this.sanitizer.bypassSecurityTrustHtml(this._inner2);
        }
        else if (this.tipo == "PDF") {
            return this.sanitizer.bypassSecurityTrustResourceUrl(this._inner2);
        }

    }
    set inner2(theBar: any) {
        this._inner2 = theBar;
    }

    /*get inner(): any {
        if (this.tipo == "HTML") {
            return this.sanitizer.bypassSecurityTrustHtml(this._inner);
        }
        else if (this.tipo == "PDF") {
            return this.sanitizer.bypassSecurityTrustResourceUrl(this._inner);
        }

    }
    set inner(theBar: any) {
        this._inner = theBar;
    }*/

}