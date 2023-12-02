import { DominioPaginacion } from '../../../../../framework/modelo/generico/DominioPaginacion';


export class DtoComunSyReporte {
    aplicacioncodigo: string;    
    reportecodigo: string;

    descripcionlocal: string;
    descripcioningles: string;
    topico: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    asunto: string;
    tiporeporte: string;
    padreIdAplicacion: string;
    padreIdReporte: string;

    // auxiliar
    aplicacionDescripcion: string;
    uuid: string;
}
