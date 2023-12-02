import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroComunAcReferenciafiscalSelector{  
  constructor() {
      this.paginacion = new DominioPaginacion();
  }
  paginacion: DominioPaginacion;

  ano : string;
  tiporeferenciafiscal : string;
  nivel : string;
}