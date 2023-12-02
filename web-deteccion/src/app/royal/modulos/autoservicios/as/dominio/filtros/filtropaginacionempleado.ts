import { DominioPaginacion } from "@framework/modelo/generico/DominioPaginacion";
import { DtoTabla } from "@framework/modelo/generico/dto/DtoTabla";
import { DtoPrSeguridadCompleto } from './../../../pr/dominio/dto/DtoPrSeguridadCompleto';

export class FiltroPaginacionEmpleado {
    constructor() {
        this.paginacion = new DominioPaginacion();
    }

    idcompaniasocio: string;
    idsucursal: string;
    idunidadnegocioasignada: string;
    empleadoid: number;
    empleaadonombrecompleto: string;
    empleadoestado: string;
    empleadodocumento: string;
    empleadojefe: number;
    empleadousuario: string;
    empleadonovalida: string;
    fechaDesde: Date;
    fechaHasta: Date;
    empleadoconceptoacceso: string;
    estado: string;
    jefeUnidad: number;
    idarea: number;
    idunidadoperativa: string;
    puesto: number;
    esAdministradorWeb: string;
    idtipoplanilla: string;
    idcentrocosto: string;
    listaMarcas: any[] = [];

    tipoExportar: string;
    isAdmin: string;
    paginacion: DominioPaginacion;

    par_allcompania: boolean;
    par_alltipoplanilla: boolean;
    par_allcentrocosto: boolean;
    par_allunidadnegocio: boolean;
    par_allsucursal: boolean;
    par_allarea: boolean;
    par_allpuesto: boolean;
    par_alltipohorario: boolean;


    cbx_impar: boolean;
    cbx_cesados: boolean;
    cbx_sinmarca: boolean;
    cbx_convacaciones: boolean;
    cbx_conenfermedad: boolean;

    cbx_Trabajo: boolean;
    cbx_Falta: boolean;
    cbx_Sobretiempo: boolean;
    cbx_Sobretiempo_c: boolean;
    cbx_sobretiempona: boolean;
    cbx_Salida: boolean;
    cbx_Tardanza: boolean;
    cbx_permisosgh: boolean;
    cbx_Marcacion: boolean;

    listaCompanias: DtoTabla[] = [];
    listaTipoPlanilla: DtoTabla[] = [];
    listaSucursal: DtoTabla[] = [];
    listaUnidadNegocio: DtoTabla[] = [];
    listaArea: DtoTabla[] = [];
    listaCentroCosto: DtoTabla[] = [];

    conceptoacceso: string;
    DtoPrSeguridad: DtoPrSeguridadCompleto;

}
