import { DominioMensajeUsuario } from "@framework/modelo/generico/DominioMensajeUsuario";
import { DtoDwCambioHorarioExcepcion } from "./DtoDwCambioHorarioExcepcion";
import { DtoWAsAutorizacionHorasDisponibles } from "./DtoWAsAutorizacionHorasDisponibles";
import { OwAsAutorizacionPreprocesoHextraEdit } from './OwAsAutorizacionPreprocesoHextraEdit';
import { DtoDddwAsConceptoaccesoSelectPre } from './DtoDddwAsConceptoaccesoSelectPre';
import { DtoDwAsMarcasEmpleado } from './DtoDwAsMarcasEmpleado';
import { DtoDwAsEventoUseridActivo } from './DtoDwAsEventoUseridActivo';
import { DtoDwAsMarcar } from "./DtoDwAsMarcar";
import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";

export class DtoAsAutorizacion extends DominioTransaccion {

    constructor() {
        super();
        this.dw_horas = new DtoWAsAutorizacionHorasDisponibles();
        this.dw_horario = [];
        this.dw_2 = [];
        this.opciones = new OwAsAutorizacionPreprocesoHextraEdit();
        this.dw_eventos = [];
        this.dwc_conceptoacceso = [];
        this.dwc_conceptoaccesofiltrado = [];
        this.dw_horarioregistro = new DtoDwCambioHorarioExcepcion();
    }

    empleado: number;
    fecha: Date;
    conceptoacceso: string;
    desde: Date;
    companiasocio: string;
    tipoautorizacion: string;
    fechafin: Date;
    hasta: Date;
    autorizadopor: string;
    fechaautorizacion: Date;
    estado: string;
    refrigerio: string;
    periodo: string;
    mandatorio: string;
    observacion: string;
    citt: string;
    estadosolicitud: string;
    solicitadopor: string;
    fechasolicitud: Date;
    sobretiempoposicion: number;
    comportamientosobretiempo: string;
    categoriaautorizacion: string;
    visadopor: string;
    fechavisado: Date;
    flagcorrido: string;
    motivorechazo: string;
    tipohorario: number;
    ultimousuario: string;
    ultimafechamodif: Date;
    nivelaprobacion: number;
    codigoproceso: string;
    numeroproceso: number;
    flagpermisoiniciofinjornada: string;
    iniciofinjornada: string;
    flagpostproceso: string;
    uuid: string;
    transaccionId: number;
    transaccionUUID: string;


    isseleccionaprobado: Boolean;
    isseleccionrechazado: Boolean;
    nombrecompleto: string;
    mensajeHorario: string;
    idtransaccion: number;
    horasacompensar: number;
    diasemana: string;
    totalHorasGanadas: number;
    cCantidadsexadecimal: string;
    totalCCantidadsexadecimal: string;
    totalCCantidadsexadecimalSeleccionadas: string;
    totalcantidadHorasSeleccionadas: number;
    tipoDiadescripcion: string;
    imagenBase64: string;
    rutaDocumento: string;
    accion: string;
    tipoAutorizacion: string;
    verInicioFinJornada: string;
    isAdmin: string;

    gv_coming_from: String;
    w_empleado: number;
    wD_fecha: Date;
    wD_fechafin: Date;
    wD_desde: Date;
    wD_hasta: Date;
    wS_desde: String;
    wS_hasta: String;

    // dw_horasautorizadas: DtoAsAsistencia[] = [];
    // dw_horasautorizadasSeleccionadas: DtoAsAsistencia[] = [];
    dw_horas: DtoWAsAutorizacionHorasDisponibles;
    dw_horario: DtoDwCambioHorarioExcepcion[] = [];
    dw_2: DtoDwAsMarcasEmpleado[] = [];
    dw_eventos: DtoDwAsEventoUseridActivo[] = [];
    dw_horarioregistro = new DtoDwCambioHorarioExcepcion();
    opciones: OwAsAutorizacionPreprocesoHextraEdit;
    dwc_conceptoacceso: DtoDddwAsConceptoaccesoSelectPre[] = [];
    dwc_conceptoaccesofiltrado: DtoDddwAsConceptoaccesoSelectPre[] = [];

    dw_detail: DtoDwAsMarcar[] = [];

    // dw_vacaciones: DtoVacaciones[] = [];
    tipo: string;
    iv_flagunamarca: string;
    iv_nogeneramarca: string;
    expresadoen: string;
}