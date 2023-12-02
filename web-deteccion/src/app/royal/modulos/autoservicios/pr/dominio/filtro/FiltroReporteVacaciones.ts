import { DominioTransaccion } from "@framework/modelo/generico/DominioTransaccion";
import { FiltroReporteVacacionesPago } from "./FiltroReporteVacacionesPago";
import { FiltroReporteVacacionesPendientesGoce } from "./FiltroReporteVacacionesPendientesGoce";
import { FiltroReporteVacacionesResumen } from "./FiltroReporteVacacionesResumen";
import { FiltroReporteVacacionesUtilizacion } from "./FiltroReporteVacacionesUtilizacion";

export class FiltroReporteVacaciones {
    filtroPago: FiltroReporteVacacionesPago;
    filtroUtilizacion: FiltroReporteVacacionesUtilizacion;
    filtroResumen: FiltroReporteVacacionesResumen;
    filtroPendientes: FiltroReporteVacacionesPendientesGoce;
    constructor() {
        this.filtroPago = new FiltroReporteVacacionesPago();
        this.filtroUtilizacion = new FiltroReporteVacacionesUtilizacion();
        this.filtroResumen = new FiltroReporteVacacionesResumen();
        this.filtroPendientes = new FiltroReporteVacacionesPendientesGoce();
    }
}