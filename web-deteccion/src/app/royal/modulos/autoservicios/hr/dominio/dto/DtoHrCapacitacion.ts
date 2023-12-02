export class DtoHrCapacitacion {
    companyowner: string;
    capacitacion: string;
    unidadreplicacion: string;
    fechasolicitud: Date;
    fechatermino: Date;
    flagprogramado: string;
    anioplan: number;
    solicitante: number;
    solicitantecompania: string;
    fechadesde: Date;
    fechahasta: Date;
    flagconvenio: string;
    flaghorario: string;
    numerovacantes: number;
    numeroparticipantes: number;
    tipocapacitacion: string;
    unidadnegocio: string;
    sucursal: string;
    centrocosto: string;
    afe: string;
    flagcursonuevo: string;
    cursonuevodescripcion: string;
    curso: number;
    cursotipo: string;
    centrocapacitacion: number;
    modalidad: string;
    pais: string;
    lugar: string;
    nombrecapacitador: string;
    observacion: string;
    plantillaencuestaempleado: number;
    plantillaencuestajefe: number;
    evaluacion: number;
    financiamiento: string;
    porcentajeempresa: number;
    costototalestimadolocal: number;
    costototalestimadoextranjero: number;
    costosubtotal: number;
    costoimpuestos: number;
    costototal: number;
    costosubtotalext: number;
    costoimpuestosext: number;
    costototalext: number;
    totaldias: number;
    totalhoras: number;
    flagevaluacion: string;
    motivoRechazo: string;
    motivoAnulado: string;
    estadoSuspendido: string;
    motivoSuspendido: string;
    motivoNosuspendido: string;
    flaglogistica: string;
    logisticaestado: string;
    logisticarequisicion: string;
    logisticacompaniasocio: string;
    correoasunto: string;
    correodetalle: string;
    correoadjunto: string;
    correocopia: string;
    correoincluirusuario: string;
    codigoproceso: string;
    numeroproceso: number;
    nivelaprobacion: number;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;
    uuid: string;
    transaccionId: number;
    transaccionUUID: string;

    //Auxiliares
    auxCentroCostoDesc: string;
    auxEstadoDesc: string;
    auxEstadoLogisticaDesc: string;
    auxSolicitanteDesc: string;
    auxSolicitanteUnidadOrganigramaDesc: string;
    auxUnidadNegocioDesc: string;
    auxSucursalDesc: string;
    auxFinanciamientoDesc: string;
    auxPaisDesc: string;
    auxCompaniaDesc: string;
    auxAfeDesc: string;
    auxCursoNuevo: boolean;
    auxCursoDesc: string;
    auxCursoAreaDesc: string;
    auxCursoTipoDesc: string;
    auxCursoCentroCapacitacionDesc: string;
    auxCursoModalidaDesc: string;
    auxPlantillaEmpleadoDesc: string;
    auxPlantillaJefeDesc: string;
    auxflaglogisticaParametro: string;
    auxFlagLogistica: boolean;

    lstFundamentacion: DtoHrCapacitacionfundament[];
    lstParticipantes: DtoHrCapacitacionempleado[];
    lstHorarioDetalle: DtoHrCapacitacionhorario[];
    lstHorarioResumen: DtoHrCapacitacionhorarioresumen[];

    constructor() {
        this.lstFundamentacion = [];
        this.lstParticipantes = [];
        this.lstHorarioDetalle = [];
        this.lstHorarioResumen = [];
    }
}

export class DtoHrCapacitacionfundament {
    companyowner: string;
    capacitacion: string;
    fundamentacion: string;
    descripcion: string;
    detalle: string;
    ultimousuario: string;
    ultimafechamodif: Date;
}
export class DtoHrCapacitacionempleado {
    companyowner: string;
    capacitacion: string;
    empleado: number;
    empleadocompania: string;
    fechapermanencia: Date;
    fechaconvenio: Date;
    importegasto: number;
    flagaprobado: string;
    nota: number;
    flagasistencia: string;
    diasasistencia: number;
    horasasistencia: number;
    comentario: string;
    flagcorreoenviado: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxEmpleadoNombre: string;
    auxSexoId: string;
    auxTipoContratoNombre: string;
    auxTerminoContrato: Date;
    auxCorreo: string;
    auxSituacionEmpleadoNombre: string;
    auxCompaniaNombre: string;
    auxUnidadNegocioNombre: string;
    auxSucursalNombre: string;
    auxUnidadOperativaNombre: string;
    auxTipoPlanillaNombre: string;
    auxCentroCostoNombre: string;
    auxPuestoNombre: string;
}
export class DtoHrCapacitacionhorario {
    companyowner: string;
    capacitacion: string;
    secuencia: number;
    estado: string;
    fechadesde: Date;
    fechahasta: Date;
    lunes: string;
    horainiciolunes: Date;
    horafinlunes: Date;
    martes: string;
    horainiciomartes: Date;
    horafinmartes: Date;
    miercoles: string;
    horainiciomiercoles: Date;
    horafinmiercoles: Date;
    jueves: string;
    horainiciojueves: Date;
    horafinjueves: Date;
    viernes: string;
    horainicioviernes: Date;
    horafinviernes: Date;
    sabado: string;
    horainiciosabado: Date;
    horafinsabado: Date;
    domingo: string;
    horainiciodomingo: Date;
    horafindomingo: Date;
    totaldias: number;
    totalhoras: number;
    ultimousuario: string;
    ultimafechamodif: Date;

}

export class DtoHrCapacitacionhorarioresumen {
    companyowner: string;
    capacitacion: string;
    periodo: string;
    totaldias: number;
    totalhoras: number;
    ultimousuario: string;
    ultimafechamodif: Date;
}