import { DominioMensajeUsuario } from "@framework/modelo/generico/DominioMensajeUsuario";
import { AsAccesosdiarios } from "./asaccesosdiarios";
import { DtoDwAsMarcar } from "./DtoDwAsMarcar";

export class DtoAccesosdiarios {

    constructor() {
        this.bean = new DtoDwAsMarcar();
    }
    
    bean: DtoDwAsMarcar;
    lista: AsAccesosdiarios[] = [];
    listaErrores: DominioMensajeUsuario[] = [];
}