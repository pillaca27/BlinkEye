export class ConstanteWorkflow {

    public static APLICACION = "WF";
    public static DEFECTO_COMPANIA = "999999";
    public static MISC_ESTADO_GENERICO = "ESTADOGENE"

    public static PARAMETRO_HABFIRELEC = "HABFIRELEC";
    public static PARAMETRO_HABFIRIMAG = "HABFIRIMAG";

    static readonly SOLICITUD_ACCION_APROBAR: string = 'APROBAR';
    static readonly SOLICITUD_ACCION_RECHAZAR: string = 'RECHAZAR';
    static readonly SOLICITUD_ACCION_DEVOLVER: string = 'DEVOLVER';
    static readonly PROCESO_CAMBIO_HORARIO = 'CH';
    static readonly PROCESO_ASISTENCIA_SOLICITUDES = 'SO';
    static readonly PROCESO_RRHH_CAPACITACION = 'CP';
    static readonly PROCESO_RRHH_RECLUTAMIENTO = 'RS';
    static readonly PROCESO_RRHH_FICHA_RECLUTAMIENTO = 'FR';
    static readonly PROCESO_RRHH_FICHA_PERSONAL = 'FP';
    static readonly PROCESO_PLANILLA_PRESTAMO = 'PR';
    static readonly PROCESO_PLANILLA_VACACION_WEB = 'VW';
    static readonly PROCESO_SOLICITUD_CONTRATO = 'CC';

    public static ruta_wf_proceso_listado = "spring/wf/proceso-listado";
    public static ruta_wf_proceso_mantenimiento = "spring/wf/proceso-mantenimiento";
    public static ruta_wf_aprobacion_listado = "spring/wf/aprobacion-listado";
    public static ruta_wf_correo_listado = "portal/wf-transaccion-correo-listado";

    public static ruta_wftransaccion_listado = "spring/wf/transaccion-listado";
    public static ruta_wftransaccion_mantenimiento = "spring/wf/transaccion-mantenimiento";

    public static ruta_wfmacroproceso_mantenimiento = "spring/wf/wfmacroproceso-mantenimiento";
    public static ruta_wfmacroproceso_listado = "spring/wf/wfmacroproceso-listado";

    public static ruta_wfmacroprocesodetalle_mantenimiento = "spring/wf/wfmacroprocesodetalle-listado";
    public static ruta_wfmacroprocesodetalle_listado = "spring/wf/wfmacroprocesodetalle-listado";
}