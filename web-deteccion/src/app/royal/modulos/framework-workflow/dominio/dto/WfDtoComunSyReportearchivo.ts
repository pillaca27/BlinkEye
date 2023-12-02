export class WfDtoComunSyReportearchivo {
    // PK
    aplicacioncodigo: string;
    reportecodigo: string;
    companiasocio: string;
    periodo: string;
    version: string;

    // CAMPOS
    reporte: string;
    estado: string;
    rutacompleta: string;
    ultimousuario;
    ultimafechamodif: Date;
    nombre: string;

    //AUXILIARES
    aplicacionDescripcion: string;
    auxString: string;
    asunto: string;
    asuntoPrincipal: string;

    transaccion_id:number;
}
