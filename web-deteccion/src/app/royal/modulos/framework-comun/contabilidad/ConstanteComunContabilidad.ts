import { ConstanteComun } from './../ConstanteComun';

export class ConstanteComunContabilidad extends ConstanteComun {
    public static APLICACION = "AC";

    //public static MISCELANEO_ESTADOS_GENERICOS = "ESTADOGENE";

    //public static PARAMETRO_XXX = "SY";


    public static GENERICO_SELECTOR = 'GENERICO_SELECTOR';

    public static ruta_acsucursalgrupo_listado = "spring/comun/ac/gruposucursal-listado"
    public static ruta_acsucursalgrupo_mantenimiento = "spring/comun/ac/gruposucursal-mantenimiento"

}