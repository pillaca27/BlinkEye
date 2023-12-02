export class AsAccesosdiariosPk {
    carnetid: string;
    fecha: Date;
    empleado: number;
}

export class AsAccesosdiarios {

    constructor() {
        this.pk = new AsAccesosdiariosPk();
    }

    pk: AsAccesosdiariosPk;

    secuencia: number;
    tipovalidacion: string;
    carnetsupervisor: string;
    empleadosupervisor: number;
    hora: Date;
    observacion: string;
    lugarrecoleccion: string;
    unidadreplicacion: string;
    ano: number;
    mes: number;
    dia: number;
    accion: string;
    ultimaFechaModif: Date;
    ultimoUsuario: string;
    fechaDescripcion: string;
    horaDescripcion: string;

    compania: string;
    inicio: Date;
    fin: Date;
    fechaaux: string;

}
