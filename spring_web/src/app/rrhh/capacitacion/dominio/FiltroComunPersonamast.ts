import { DominioPaginacion } from './../../../util/DominioPaginacion';

export class FiltroComunPersonamast {

  constructor() {
    this.paginacion = new DominioPaginacion();
  }
  paginacion: DominioPaginacion;

  persona: number;
  busqueda: string;
  documento: string;
  documentofiscal: string;
  documentoidentidad: string;
  estado: string;

  adicional1: string;
}
