export class ParametroPaginacionGenerico {

    constructor() {
        this.paginacionRegistrosEncontrados = 0;
        this.paginacionRegistrosPorPagina = 0;
        this.paginacionRegistroInicio = 0;
    }

    paginacionRegistroInicio: number;
    paginacionRegistrosPorPagina: number;
    paginacionRegistrosEncontrados: number;
    paginacionListaResultado: any[];

    paginacionOrdenAtributo: string;
    paginacionOrdenDireccion: any;
}

