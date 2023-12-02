import { ConstanteComun } from './../ConstanteComun';

export class ConstanteComunSistema extends ConstanteComun {
    public static APLICACION = "SY";

    public static MISC_ESTADO_GENERICO = "ESTADOGENE";
    public static MISC_MONEDA_CAMBIO = "MONETIPCA";
    public static EXPRESIONES_REGULARES_NUMERICO = /^[0-9]/;

    public static EXTENSIONES_UNO = "EXTUNO";
    public static EXTENSIONES_DOS = "EXTDOS";

    public static GENERICO_APLICACION = "APLICACION";
    public static ruta_syprocesomst_mantenimiento = "spring/comun/sy/syprocesomst-mantenimiento";
    public static ruta_syprocesomst_listado = "spring/comun/sy/syprocesomst-listado";
    //public static PARAMETRO_XXX = "SY";


    public static ruta_sypreferences_mantenimiento = "portal/seguridad/sypreferences-mantenimiento";
    public static ruta_sypreferences_listado = "portal/seguridad/sypreferences-listado";
    
    public static ruta_syseguridadgrupo_mantenimiento = "spring/sg/syseguridadgrupo-mantenimiento";
    public static ruta_syseguridadgrupo_listado = "spring/sg/syseguridadgrupo-listado";

    public static ruta_syaplicacionreportetopico_mantenimiento = "spring/comun/sy/topico-mantenimiento";
    public static ruta_syaplicacionreportetopico_listado = "spring/comun/sy/topico-listado";
    
}