export class DtoEncuestaEjecucion {
    compania: string;
    periodo: string;
    encuesta: number;
    descripcion: string;
    instrucciones: string;
    detalle: DtoEncuestaEjecucionDetalle[];
    areas: string[];
    constructor() {
        this.detalle = [];
        this.areas = []
    }
}
export class DtoEncuestaEjecucionDetalle {
    pregunta: number;
    descripcion: string;
    areaDescripcion: string;
    tipo: string;
    observacion: string;
    valor: number;
    valores: DtoEncuestaEjecucionDetalleValor[];
    constructor() {
        this.valores = [];
    }
}

export class DtoEncuestaEjecucionDetalleValor {
    valor: number;
    descripcion: string;
}