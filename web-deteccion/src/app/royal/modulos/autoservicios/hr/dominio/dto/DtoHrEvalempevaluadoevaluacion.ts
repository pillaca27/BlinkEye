export class DtoHrEvalempevaluadoevaluacion {
    companyowner: string;
    evaluacion: number;
    empleado: number;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    empleadocomentario: string;
    tipoevaluador: string;
    evaluadorcomentario: string;
    fechaevaluacion: Date;
    peso: number;
    puntoscompetencia: number;
    pesocompetencia: number;
    puntosfuncion: number;
    pesofuncion: number;
    puntosobjpuesto: number;
    pesoobjpuesto: number;
    puntosobjempleado: number;
    pesoobjempleado: number;
    puntosinccritico: number;
    pesoinccritico: number;
    flagcorreoenviado: string;
    estado: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxUIVerTabCompetencias: string;
    auxUIVerTabFunciones: string;
    auxUIVerTabObjPuesto: string;
    auxUIVerTabObjEmpleado: string;
    auxUIVerTabIncidentes: string;
    auxUIVerTabFortalezas: string;
    auxUIVerTabNecesidades: string;
    auxUIVerTabMejoras: string;
    auxUIVerTabRevisiones: string;

    auxFechaHoraInicio: string;
    auxFechaHoraFin: string;
    auxEstadoEvaluacion: string;
    auxFechaEvaluacion: string;
    auxEmpleadoNombre: string;
    auxCompaniaNombre: string;
    auxUnidadNegocioNombre: string;
    auxSucursalNombre: string;
    auxUnidadOperativaNombre: string;
    auxTipoPlanillaNombre: string;
    auxCentroCostoNombre: string;
    auxPuestoNombre: string;
    auxEvaluadorNombre: string;
    auxEvaluadorPuestoNombre: string;
    auxTipoEvaluadorNombre: string;
    auxTipoEvaluadorCodigo: string;
    auxFotoBase64: string;
    auxPlantilla: string;
    auxFlagEvaluacionCompetencia: string;

    listaCompetencias: DtoHrEvalempcompetencia[];
    listaFunciones: DtoHrEvalempfuncion[];
    listaObjPuesto: DtoHrEvalempobjpuesto[];
    listaObjEmpleado: DtoHrEvalempobjempleado[];
    listaIncidentes1: DtoHrEmpleadoincidente[];
    listaIncidentes2: DtoHrEmpleadoincidente[];
    listaFortalezas: DtoHrEvalempfortaleza[];
    listaNecesidades: DtoHrEvalempnecesidad[];
    listaMejoras: DtoHrEvalempmejora[];
    listaRevisiones: DtoHrEvalemprevision[];

    constructor() {
        this.listaCompetencias = [];
        this.listaFunciones = [];
        this.listaObjPuesto = [];

        this.listaObjEmpleado = [];
        this.listaIncidentes1 = [];
        this.listaIncidentes2 = [];
        this.listaFortalezas = [];

        this.listaNecesidades = [];
        this.listaMejoras = [];
        this.listaRevisiones = [];
    }
}
export class DtoHrEvalempcompetencia {
    companyowner: string;
    evaluacion: number;
    empleado: number;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    secuencia: number;
    plantilla: string;
    competencia: number;
    orden: number;
    calificacion: number;
    calificaciondesde: number;
    calificacionhasta: number;
    comentario: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxCompetenciaNombre: string;
    auxCompetenciaExplicacion: string;
    auxCalfDesdeNombre: string;
    auxCalfHastaNombre: string;
    auxValorRequerido: number;
    auxTipoCalificacion: string;

    listaComportamientos: DtoHrEvalempcompetenciacomp[];
    listaOpciones: DtoEvaluacionComportamientoOpciones[];

    constructor() {
        this.listaComportamientos = [];
        this.listaOpciones = [];
    }

}
export class DtoHrEvalempcompetenciacomp {
    companyowner: string;
    evaluacion: number;
    empleado: number;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    secuencia: number;
    comportamientosecuencia: number;
    comportamiento: number;
    competencia: number;
    orden: number;
    calificacion: number;
    calificaciondesde: number;
    calificacionhasta: number;
    comentario: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxComportamientoNombre: string;
    auxComportamientoExplicacion: string;

    listaOpciones: DtoEvaluacionComportamientoOpciones[];

    constructor() {
        this.listaOpciones = [];
    }
}

export class DtoEvaluacionComportamientoOpciones {
    descripcion: string;
    explicacion: string;
    valorMaximo: number;
}

export class DtoHrEvalempfuncion {
    companyowner: string;
    evaluacion: number;
    empleado: number;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    secuencia: number;
    funcion: number;
    descripcion: string;
    calificacion: number;
    peso: number;
    comentario: string;
    ultimousuario: string;
    ultimafechamodif: Date;

}
export class DtoHrEvalempobjpuesto {
    companyowner: string;
    evaluacion: number;
    empleado: number;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    secuencia: number;
    objetivo: number;
    descripcion: string;
    calificacion: number;
    peso: number;
    comentario: string;
    ultimousuario: string;
    ultimafechamodif: Date;
}

export class DtoHrEvalempobjempleado {
    companyowner: string;
    evaluacion: number;
    empleado: number;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    secuencia: number;
    objetivo: number;
    descripcion: string;
    calificacion: number;
    peso: number;
    comentario: string;
    ultimousuario: string;
    ultimafechamodif: Date;
}
export class DtoHrEmpleadoincidente {
    empleado: number;
    companiasocio: string;
    secuencia: number;
    tipo: string;
    fecha;
    documento: string;
    descripcion: string;
    tipoincidente: string;
    fechadesde;
    fechahasta;
    diassuspension: number;
    responsable: number;
    responsablecompania: string;
    rutadocumento: string;
    comentario: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxTipoIncidenteNombre: string;
    auxPeso: number;
    auxPeriodoSuspension: string;
}
export class DtoHrEvalempfortaleza {
    companyowner: string;
    evaluacion: number;
    empleado: number;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    secuencia: number;
    tipo: string;
    descripcion: string;
    ultimousuario: string;
    ultimafechamodif: Date;
}

export class DtoHrEvalempnecesidad {
    companyowner: string;
    evaluacion: number;
    empleado: number;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    secuencia: number;
    descripcion: string;
    curso: number;
    capacitacion: string;
    capacitacioncompania: string;
    prioridad: number;
    objetivo: string;
    ultimousuario: string;
    ultimafechamodif: Date;

    auxCursoNombre: string;
}
export class DtoHrEvalempmejora {
    companyowner: string;
    evaluacion: number;
    empleadov;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    secuencia: number;
    accion: string;
    propuesta: string;
    plazo: string;
    flagcumplio: string;
    ultimousuario: string;
    ultimafechamodif: Date;
}
export class DtoHrEvalemprevision {
    companyowner: string;
    evaluacion: number;
    empleado: number;
    empleadocompania: string;
    evaluador: number;
    evaluadorcompania: string;
    secuencia: number;
    descripcion: string;
    observacion: string;
    flagcumplio: string;
    ultimousuario: string;
    ultimafechamodif: Date;
}