export class DecimalesDto {

    constructor(){
        this.labelcantidad = "1.2-2";
        this.labelprecio = "1.2-2";
        this.labeltotal = "1.2-2";
    
        this.inputcantidadmin = 2;
        this.inputcantidadmax = 2;
    
        this.inputpreciomin = 2;
        this.inputpreciomax = 2;
    
        this.inputtotalmin = 2;
        this.inputtotalmax = 2;
    }

    labelcantidad: string;
    labelprecio: string;
    labeltotal: string;

    inputcantidadmin: number;
    inputcantidadmax: number;

    inputpreciomin: number;
    inputpreciomax: number;

    inputtotalmin: number;
    inputtotalmax: number;
}