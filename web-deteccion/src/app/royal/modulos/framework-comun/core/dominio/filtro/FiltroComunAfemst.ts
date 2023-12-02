import { DominioPaginacion } from './../../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroComunAfemst{
  compania: string;
  afe: string;
  localname: string;
  afetype: string;
  status: string;

  paginacion: DominioPaginacion;

  constructor(){
    this.paginacion = new DominioPaginacion();
  }

}