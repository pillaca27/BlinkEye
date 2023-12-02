import { FiltroSySeguridadconcepto } from './../filtro/FiltroSySeguridadconcepto';
import { DtoSySeguridadconcepto } from './DtoSySeguridadconcepto';

export class DtoSeguridadConceptoList{
    

    constructor(){
        this.listado = [];
        this.filtro = new FiltroSySeguridadconcepto();
    }

    
    filtro: FiltroSySeguridadconcepto;
    listado: DtoSySeguridadconcepto[];


}