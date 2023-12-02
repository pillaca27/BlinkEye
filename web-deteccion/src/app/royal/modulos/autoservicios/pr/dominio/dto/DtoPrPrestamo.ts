export class DtoPrPrestamo {
    nroprestamo: number;
    tipoprestamo: string;
    empleado: number;
    companiasocio: string;
    fechasolicitud: Date;
    fechaaprobacion: Date;
    monedadocumento: string;
    montosolicitado: number;
    interesprestamo: number;
    montoprestamo: number;
    monedapago: string;
    montopagado: number;
    saldodeuda: number;
    otorgadoflag: string;
    tipopago: string;
    numerocheque: number;
    cuentabancaria: string;
    preparadopor: number;
    aprobadopor: number;
    motivorechazo: string;
    montoprestamolocal: number;
    montoprestamoextranjera: number;
    saldodeudalocal: number;
    saldodeudaextranjera: number;
    voucher: string;
    numerorecibo: string;
    comentario: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;
    tipodocumentoref: string;
    documentoref: string;
    concepto: string;
    codigoproceso: string;
    numeroproceso: number;
    nivelaprobacion: number;
    uuid: string;
    transaccionId: number;
    transaccionUUID: string;

    //Auxiliares
    auxEmpleadoNombre: string;
    auxPreparadoPorNombre: string;
    auxAprobadoPorNombre: string;
    auxNroCuotas: number;
    auxMontoMensual: number;
    auxPeriodo: string;

    //UI
    auxUIHabilitarConcepto: string;

    lstPeriodos: DtoPrCuentacorriente[];

    constructor() {
        this.lstPeriodos = [];
    }
}

export class DtoPrCuentacorriente {
    nroprestamo: number;
    tipoprestamo: string;
    empleado: number;
    companiasocio: string;
    periodo: string;
    cuotapago: number;
    saldoinicial: number;
    montodescuento: number;
    montoefectivo: number;
    saldofinal: number;
    interes: number;
    amortizacion: number;
    cuotaquincena: number;
    cuotafindemes: number;
    cuotagratificacion: number;
    cuotautilidades: number;
    flagtipoprocesocta: string;
    tipoprocesocta: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;
}