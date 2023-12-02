import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';

export class FiltroComunPersonamastclis001Wf{

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
  localidad:string
  actividadeconomica:string

  centrocosto: string;
  centrocostoNombre: string;

}
