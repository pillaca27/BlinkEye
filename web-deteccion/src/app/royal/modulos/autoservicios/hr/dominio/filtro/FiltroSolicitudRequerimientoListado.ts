import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";

export class FiltroSolicitudRequerimientoListado {
    paginacion: DominioPaginacion;
    estado: string;
    fechaRegistroDesde: Date;
    fechaRegistroHasta: Date;
    compania: string;
    centroCosto: string;
    tipo: string;
    unidadNegocio: string;
    codigoCargo: number;
    motivo: string;
    sucursal: string;
    unidadOperativa: string;
    tipoPlanilla: string;
    estadoPostulante: string;
    postulante: number;
    responsable: number;
    requerimiento: string;

    auxCompaniaNombre: string;
    auxCentroCostoNombre: string;
    auxTipoNombre: string;
    auxUnidadnegocioNombre: string;
    auxCodigoCargoNombre: string;
    auxMotivoNombre: string;
    auxSucursalNombre: string;
    auxUnidadOperativaNombre: string;
    auxTipoPlanillaNombre: string;
    auxEstadoNombre: string;
    auxEstadoPostulanteNombre: string;
    auxPostulanteNombre: string;
    auxResponsableNombre: string;

    constructor() {
        this.paginacion = new DominioPaginacion();
    }
}