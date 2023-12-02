import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, MessageService } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { HrRequerimientoServicio } from '../servicio/HrRequerimientoServicio';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { ConstanteAutoservicios } from '../../ConstanteAutoservicios';
import { DtoHrRequerimiento } from '../dominio/dto/DtoHrRequerimiento';
import { FiltroSolicitudRequerimientoListado } from '../dominio/filtro/FiltroSolicitudRequerimientoListado';
import { WfTransaccionSeguimientoComponent } from '@framework-workflow/vista/transaccion-seguimiento.component';
import { WfTransaccionBotonesComponent } from '@framework-workflow/vista/transaccion-botones.component';
import { ConstanteWorkflow } from '@framework-workflow/ConstanteWorkflow';
import { DtoHrCapacitacion, DtoHrCapacitacionempleado, DtoHrCapacitacionhorario, DtoHrCapacitacionhorarioresumen } from '../dominio/dto/DtoHrCapacitacion';
import { HrCapacitacionServicio } from '../servicio/HrCapacitacionServicio';
import { CentroCostosComunSelectorComponent } from '@framework-comun/contabilidad/vista/centrocostoscomunselector.component';
import { UnidadnegocioSelectorComponent } from '@framework-comun/core/vista/unidadnegocio-selector.component';
import { SucursalselectorComponent } from '@framework-comun/contabilidad/vista/sucursalselector.component';
import { AfemstSelectorComponent } from '@framework-comun/core/vista/afemstcomun-selector.component';
import { PaisSelectorComponent } from '@framework-comun/core/vista/pais-selector.component';
import { CursoSelectorComponent } from '@framework-comun/hr/vista/curso-selector.component';
import { MiscelaneosSelectorComponent } from '@framework-comun/core/vista/miscelaneos-selector.component';
import { HrCentroEstudiosSelectorComponent } from '@framework-comun/hr/vista/hrcentroestudiosselector.component';
import { EncuestaPlantillaSelectorComponent } from '@framework-comun/hr/vista/encuestaplantilla-selector.component';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { accionSolicitada } from '@framework/angular/component/BaseComponent';
import { trimTrailingNulls } from '@angular/compiler/src/render3/view/util';
import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
import { DtoComunMaMiscelaneosdetalle } from '@framework-comun/core/dominio/dto/DtoComunMaMiscelaneosdetalle';

@Component({
    templateUrl: './solicitud-capacitacion-mantenimiento.component.html'
})
export class SolicitudCapacitacionMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    dtoBean: DtoHrCapacitacion = new DtoHrCapacitacion();

    financiamientosBean: DtoComunMaMiscelaneosdetalle[] = [];

    @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;
    @ViewChild(WfTransaccionBotonesComponent, { static: false }) wfTransaccionBotonesComponent: WfTransaccionBotonesComponent;
    @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;
    @ViewChild(UnidadnegocioSelectorComponent, { static: false }) unidadnegocioSelectorComponent: UnidadnegocioSelectorComponent;
    @ViewChild(SucursalselectorComponent, { static: false }) sucursalselectorComponent: SucursalselectorComponent;
    @ViewChild(AfemstSelectorComponent, { static: false }) afemstSelectorComponent: AfemstSelectorComponent;
    @ViewChild(PaisSelectorComponent, { static: false }) paisSelectorComponent: PaisSelectorComponent;
    @ViewChild(CursoSelectorComponent, { static: false }) cursoSelectorComponent: CursoSelectorComponent;
    @ViewChild(MiscelaneosSelectorComponent, { static: false }) miscelaneosSelectorComponent: MiscelaneosSelectorComponent;
    @ViewChild(HrCentroEstudiosSelectorComponent, { static: false }) hrCentroEstudiosSelectorComponent: HrCentroEstudiosSelectorComponent;
    @ViewChild(EncuestaPlantillaSelectorComponent, { static: false }) encuestaPlantillaSelectorComponent: EncuestaPlantillaSelectorComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;

    constructor(
        private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
        private confirmationService: ConfirmationService,
        private router: Router,
        private hrCapacitacionServicio: HrCapacitacionServicio,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }


    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        this.bloquearPagina();

        var p = [];

        p.push(this.maMiscelaneosdetalleComunService.listarDtoPorHeader('HR', 'CPFINANCIA', '999999')
            .then(respuesta => {
                this.financiamientosBean = respuesta;
            }));

        Promise.all(p).then(res => {
            if (this.accion == this.ACCIONES.NUEVO) {
                this.nuevo();
            }
            else {
                this.obtenerPorUUID(this.route.snapshot.params['uuid'] as string);
            }
        });
    }

    nuevo() {
        this.hrCapacitacionServicio.solicitudNuevo().then(
            res => {
                this.dtoBean = res;
                this.desbloquearPagina();
            }
        );
    }

    obtenerPorUUID(uuid: string) {
        var dto = new DtoHrCapacitacion();
        if (this.accion == this.ACCIONES.WORKFLOW)
            dto.transaccionUUID = uuid;
        else
            dto.uuid = uuid;

        this.hrCapacitacionServicio.solicitudObtenerPorUuid(dto).then(
            res => {
                this.dtoBean = res;
                this.desbloquearPagina();
                if (this.dtoBean.transaccionUUID != null) {
                    this.wfTransaccionSeguimientoComponent.iniciarComponente(this.dtoBean.transaccionUUID);
                    this.wfTransaccionBotonesComponent.iniciarComponente(this.dtoBean.transaccionUUID, this.accion == this.ACCIONES.WORKFLOW ? 'S' : 'N', false);
                }
            }
        );
    }

    coreGuardar(): void {
        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.hrCapacitacionServicio.solicitudRegistrar(this.dtoBean).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultadoSimple(res)) {
                    this.mostrarMensajeExito(this.getMensajeGrabado(res.capacitacion));
                    this.coreSalir();
                }
            });
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.hrCapacitacionServicio.solicitudActualizar(this.dtoBean).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultadoSimple(res)) {
                    this.mostrarMensajeExito(this.getMensajeActualizado(res.capacitacion));
                    this.coreSalir();
                }
            });
        }
    }
    coreSalir(): void {
        if (this.accion == this.ACCIONES.WORKFLOW) {
            this.router.navigate([ConstanteWorkflow.ruta_wf_aprobacion_listado]);
        }
        else {
            this.router.navigate([ConstanteAutoservicios.ruta_solicitud_capacitacion_listado]);
        }
    }
    coreExportar(tipo: string): void {
        throw new Error('Method not implemented.');
    }
    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'CENTROCOSTOS') {
            this.dtoBean.centrocosto = mensage.resultado.costcenter;
            this.dtoBean.auxCentroCostoDesc = mensage.resultado.localname;
        }
        else if (mensage.componente == 'UNIDADNEGOCIO') {
            this.dtoBean.unidadnegocio = mensage.resultado.unidadnegocio;
            this.dtoBean.auxUnidadNegocioDesc = mensage.resultado.descripcionlocal;
        }
        else if (mensage.componente == 'SUCURSAL') {
            this.dtoBean.sucursal = mensage.resultado.sucursal;
            this.dtoBean.auxSucursalDesc = mensage.resultado.descripcionlocal;
        }
        else if (mensage.componente == 'PROYECTO') {
            this.dtoBean.afe = mensage.resultado.afe;
            this.dtoBean.auxAfeDesc = mensage.resultado.localname;
        }
        else if (mensage.componente == 'PAIS') {
            this.dtoBean.pais = this.trim(mensage.resultado.pais);
            this.dtoBean.auxPaisDesc = mensage.resultado.descripcioncorta;
        }
        else if (mensage.componente == 'CURSO') {
            this.dtoBean.curso = mensage.resultado.curso;
            this.dtoBean.auxCursoDesc = mensage.resultado.descripcion;
            this.dtoBean.auxCursoAreaDesc = mensage.resultado.areaNombre;
            this.dtoBean.cursotipo = mensage.resultado.tipo;
            this.dtoBean.auxCursoTipoDesc = mensage.resultado.tiponombre;
        }
        else if (mensage.componente == 'TIPOCURSO') {
            this.dtoBean.cursotipo = mensage.resultado.codigo;
            this.dtoBean.auxCursoTipoDesc = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'CENTROESTUDIO') {
            this.dtoBean.centrocapacitacion = mensage.resultado.id;
            this.dtoBean.auxCursoCentroCapacitacionDesc = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'MODALIDAD') {
            this.dtoBean.modalidad = mensage.resultado.codigo;
            this.dtoBean.auxCursoModalidaDesc = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'PLANTILLAEMPLEADO') {
            this.dtoBean.plantillaencuestaempleado = mensage.resultado.id;
            this.dtoBean.auxPlantillaEmpleadoDesc = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'PLANTILLAJEFE') {
            this.dtoBean.plantillaencuestajefe = mensage.resultado.id;
            this.dtoBean.auxPlantillaJefeDesc = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'FINANCIAMIENTO') {
            this.dtoBean.financiamiento = mensage.resultado.codigo;
            this.dtoBean.auxFinanciamientoDesc = mensage.resultado.nombre;
            this.dtoBean.porcentajeempresa = this.financiamientosBean.find(x => x.codigoelemento.trim() == this.dtoBean.financiamiento).valornumerico;
        }
        else if (mensage.componente == 'PARTICIPANTE') {
            if (this.dtoBean.lstParticipantes.filter(x => x.empleado == mensage.resultado.persona).length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'El empleado ya ha sido seleccionado' });
                return;
            }
            var temp = [...this.dtoBean.lstParticipantes];
            const participante: DtoHrCapacitacionempleado = new DtoHrCapacitacionempleado();
            participante.empleado = mensage.resultado.persona;
            participante.empleadocompania = mensage.resultado.companiasocio;
            participante.auxEmpleadoNombre = mensage.resultado.busqueda;
            participante.auxSexoId = mensage.resultado.sexo;
            participante.auxTipoContratoNombre = mensage.resultado.tipocontratoNombre;
            participante.auxTerminoContrato = mensage.resultado.fechafincontrato;
            participante.auxCorreo = mensage.resultado.correointerno;
            participante.auxSituacionEmpleadoNombre = mensage.resultado.estadoEmpleadoNombre;
            participante.auxCompaniaNombre = mensage.resultado.companiasocioNombre;
            participante.auxUnidadNegocioNombre = mensage.resultado.unidadnegocioasignadaNombre;
            participante.auxSucursalNombre = mensage.resultado.sucursalnombre;
            participante.auxUnidadOperativaNombre = mensage.resultado.unidadoperativaNombre;
            participante.auxTipoPlanillaNombre = mensage.resultado.tipoplanillaNombre;
            participante.auxCentroCostoNombre = mensage.resultado.centrocostosNombre;
            participante.auxPuestoNombre = mensage.resultado.codigocargoNombre;
            participante.importegasto = 0;
            participante.flagaprobado = 'N';
            participante.nota = 0;
            participante.flagasistencia = 'N';
            participante.diasasistencia = 0;
            participante.horasasistencia = 0;
            temp.push(participante);
            this.dtoBean.lstParticipantes = temp;
            this.dtoBean.numeroparticipantes = this.dtoBean.lstParticipantes.length;
        }
    }
    coreAccion(accion: string): void {
        throw new Error('Method not implemented.');
    }

    agregarParticipante() {
        this.messageService.clear();
        if (this.dtoBean.numerovacantes == null || this.dtoBean.numerovacantes === undefined) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'El número de vacantes es requerido' });
            return;
        } else if (this.dtoBean.numerovacantes <= this.dtoBean.numeroparticipantes) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'No se encuentran vacantes disponibles' });
            return;
        }
        if (this.dtoBean.lstParticipantes.length == this.dtoBean.numerovacantes) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'N° de Vacantes cubierto, verifíque los datos.' });
            return;
        }
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PARTICIPANTE', 'EMOT'));
    }

    eliminarParticipante(row: DtoHrCapacitacionempleado) {
        this.confirmationService.confirm({
            key: "confirm",
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Está seguro de eliminar este registro?',
            accept: () => {
                let lst = [...this.dtoBean.lstParticipantes];
                lst = lst.filter(obj => obj.empleado !== row.empleado);
                this.dtoBean.lstParticipantes = lst;
                this.dtoBean.numeroparticipantes = this.dtoBean.lstParticipantes.length;
            }
        });
    }

    imprimirConstancia(row: DtoHrCapacitacionempleado) {

    }

    agregarHorarioDetalle() {
        this.messageService.clear();
        if (this.dtoBean.fechadesde == null) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'La Fecha de inicio de la Capacitación es requerida' });
            return;
        }
        if (this.dtoBean.fechahasta == null) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'La Fecha de Término de la Capacitación es requerida' });
            return;
        }
        this.diasMarcadosTodos = false;
        this.horarioModal = new DtoHrCapacitacionhorario();
        this.horarioModal.fechadesde = this.dtoBean.fechadesde;
        this.horarioModal.fechahasta = this.dtoBean.fechahasta;
        this.accionHorario = this.ACCIONES.NUEVO;
        this.diasMarcados = [false, false, false, false, false, false, false];
        this.verModalHorario = true;
    }

    sumarTotalesHorasDias() {
        var h = 0;
        var d = 0;
        this.dtoBean.lstHorarioResumen.forEach(
            r => {
                h = h + r.totalhoras;
                d = d + r.totaldias;
            }
        );
        this.dtoBean.totaldias = d;
        this.dtoBean.totalhoras = h;
    }

    eliminarHorarioDetalle(row: DtoHrCapacitacionhorario) {
        this.confirmationService.confirm({
            key: "confirm",
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Está seguro de eliminar este registro?',
            accept: () => {
                let lst = [...this.dtoBean.lstHorarioDetalle];
                lst = lst.filter(obj => obj.secuencia !== row.secuencia);
                this.dtoBean.lstHorarioDetalle = lst;
                this.bloquearPagina();
                this.hrCapacitacionServicio.generarResumenDesdeDetalle(this.dtoBean).then(res => {
                    this.desbloquearPagina();
                    this.dtoBean = res;
                    this.sumarTotalesHorasDias();
                    this.verModalHorario = false;
                });
            }
        });
    }

    verModalHorario: boolean = false;
    verModalHorarioResumen: boolean = false;
    accionHorario: accionSolicitada = this.ACCIONES.NUEVO;
    horarioModal: DtoHrCapacitacionhorario = new DtoHrCapacitacionhorario();
    diasMarcados: boolean[] = [false, false, false, false, false, false, false];
    diasMarcadosTodos: boolean = false;

    horarioResumenAnio: number;
    horarioResumenMes: number;


    horarioResumenModal: DtoHrCapacitacionhorarioresumen = new DtoHrCapacitacionhorarioresumen();

    editarHorarioResumen(row: DtoHrCapacitacionhorarioresumen) {
        this.horarioResumenModal = this.clonarHorarioResumen(row);
        this.accionHorario = this.ACCIONES.EDITAR;
        this.verModalHorarioResumen = true;
    }

    editarHorarioDetalle(row: DtoHrCapacitacionhorario) {
        this.diasMarcadosTodos = false;
        this.horarioModal = this.clonarHorario(row);
        this.accionHorario = this.ACCIONES.EDITAR;
        this.verModalHorario = true;
    }
    verHorarioDetalle(row: DtoHrCapacitacionhorario) {
        this.diasMarcadosTodos = false;
        this.horarioModal = this.clonarHorario(row);
        this.accionHorario = this.ACCIONES.VER;
        this.verModalHorario = true;
    }
    clonarHorario(obj: DtoHrCapacitacionhorario): DtoHrCapacitacionhorario {
        const exp = new DtoHrCapacitacionhorario();
        for (const prop in obj) {
            exp[prop] = obj[prop];
        }
        this.diasMarcados[0] = exp.lunes == 'S' ? true : false;
        this.diasMarcados[1] = exp.martes == 'S' ? true : false;
        this.diasMarcados[2] = exp.miercoles == 'S' ? true : false;
        this.diasMarcados[3] = exp.jueves == 'S' ? true : false;
        this.diasMarcados[4] = exp.viernes == 'S' ? true : false;
        this.diasMarcados[5] = exp.sabado == 'S' ? true : false;
        this.diasMarcados[6] = exp.domingo == 'S' ? true : false;
        return exp;
    }
    clonarHorarioResumen(obj: DtoHrCapacitacionhorarioresumen): DtoHrCapacitacionhorarioresumen {
        const exp = new DtoHrCapacitacionhorarioresumen();
        for (const prop in obj) {
            exp[prop] = obj[prop];
        }
        return exp;
    }
    guardarHorario() {
        var valida = true;
        this.messageService.clear();

        this.horarioModal.lunes = this.diasMarcados[0] ? 'S' : 'N';
        this.horarioModal.martes = this.diasMarcados[1] ? 'S' : 'N';
        this.horarioModal.miercoles = this.diasMarcados[2] ? 'S' : 'N';
        this.horarioModal.jueves = this.diasMarcados[3] ? 'S' : 'N';
        this.horarioModal.viernes = this.diasMarcados[4] ? 'S' : 'N';
        this.horarioModal.sabado = this.diasMarcados[5] ? 'S' : 'N';
        this.horarioModal.domingo = this.diasMarcados[6] ? 'S' : 'N';

        this.horarioModal.horainiciolunes = this.horarioModal.horainiciolunes == null ? null : new Date(this.horarioModal.horainiciolunes.setSeconds(0));
        this.horarioModal.horainiciomartes = this.horarioModal.horainiciomartes == null ? null : new Date(this.horarioModal.horainiciomartes.setSeconds(0));
        this.horarioModal.horainiciomiercoles = this.horarioModal.horainiciomiercoles == null ? null : new Date(this.horarioModal.horainiciomiercoles.setSeconds(0));
        this.horarioModal.horainiciojueves = this.horarioModal.horainiciojueves == null ? null : new Date(this.horarioModal.horainiciojueves.setSeconds(0));
        this.horarioModal.horainicioviernes = this.horarioModal.horainicioviernes == null ? null : new Date(this.horarioModal.horainicioviernes.setSeconds(0));
        this.horarioModal.horainiciosabado = this.horarioModal.horainiciosabado == null ? null : new Date(this.horarioModal.horainiciosabado.setSeconds(0));
        this.horarioModal.horainiciodomingo = this.horarioModal.horainiciodomingo == null ? null : new Date(this.horarioModal.horainiciodomingo.setSeconds(0));
        this.horarioModal.horafinlunes = this.horarioModal.horafinlunes == null ? null : new Date(this.horarioModal.horafinlunes.setSeconds(0));
        this.horarioModal.horafinmartes = this.horarioModal.horafinmartes == null ? null : new Date(this.horarioModal.horafinmartes.setSeconds(0));
        this.horarioModal.horafinmiercoles = this.horarioModal.horafinmiercoles == null ? null : new Date(this.horarioModal.horafinmiercoles.setSeconds(0));
        this.horarioModal.horafinjueves = this.horarioModal.horafinjueves == null ? null : new Date(this.horarioModal.horafinjueves.setSeconds(0));
        this.horarioModal.horafinviernes = this.horarioModal.horafinviernes == null ? null : new Date(this.horarioModal.horafinviernes.setSeconds(0));
        this.horarioModal.horafinsabado = this.horarioModal.horafinsabado == null ? null : new Date(this.horarioModal.horafinsabado.setSeconds(0));
        this.horarioModal.horafindomingo = this.horarioModal.horafindomingo == null ? null : new Date(this.horarioModal.horafindomingo.setSeconds(0));

        if (this.horarioModal.fechadesde == null || this.horarioModal.fechahasta == null) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el rango de fechas' });
            valida = false;
        }
        else if (this.horarioModal.fechadesde > this.horarioModal.fechahasta) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de fechas correcto' });
            valida = false;
        }
        else {
            //Validar si esta dentro del rango de la capacitacion
            this.horarioModal.fechadesde = this.horarioModal.fechadesde == null ? null : new Date(this.horarioModal.fechadesde.setSeconds(0));
            this.horarioModal.fechadesde = this.horarioModal.fechadesde == null ? null : new Date(this.horarioModal.fechadesde.setMinutes(0));
            this.horarioModal.fechadesde = this.horarioModal.fechadesde == null ? null : new Date(this.horarioModal.fechadesde.setHours(0));

            this.horarioModal.fechahasta = this.horarioModal.fechahasta == null ? null : new Date(this.horarioModal.fechahasta.setSeconds(0));
            this.horarioModal.fechahasta = this.horarioModal.fechahasta == null ? null : new Date(this.horarioModal.fechahasta.setMinutes(0));
            this.horarioModal.fechahasta = this.horarioModal.fechahasta == null ? null : new Date(this.horarioModal.fechahasta.setHours(0));

            this.dtoBean.fechadesde = this.dtoBean.fechadesde == null ? null : new Date(this.dtoBean.fechadesde.setSeconds(0));
            this.dtoBean.fechadesde = this.dtoBean.fechadesde == null ? null : new Date(this.dtoBean.fechadesde.setMinutes(0));
            this.dtoBean.fechadesde = this.dtoBean.fechadesde == null ? null : new Date(this.dtoBean.fechadesde.setHours(0));

            this.dtoBean.fechahasta = this.dtoBean.fechahasta == null ? null : new Date(this.dtoBean.fechahasta.setSeconds(0));
            this.dtoBean.fechahasta = this.dtoBean.fechahasta == null ? null : new Date(this.dtoBean.fechahasta.setMinutes(0));
            this.dtoBean.fechahasta = this.dtoBean.fechahasta == null ? null : new Date(this.dtoBean.fechahasta.setHours(0));

            var validaRango = true;

            if (!(this.horarioModal.fechadesde.getTime() <= this.dtoBean.fechahasta.getTime() && this.horarioModal.fechadesde.getTime() >= this.dtoBean.fechadesde.getTime())) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'La Fecha Desde debe estar en el rango de fechas de la Capacitación' });
                validaRango = false;
            }
            if (!(this.horarioModal.fechahasta.getTime() <= this.dtoBean.fechahasta.getTime() && this.horarioModal.fechahasta.getTime() >= this.dtoBean.fechadesde.getTime())) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'La Fecha Hasta debe estar en el rango de fechas de la Capacitación' });
                validaRango = false;
            }

            if (!validaRango) {
                valida = false;
            }
        }

        if (this.diasMarcados.filter(x => x == true).length == 0) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Seleccione el(los) día(s) del horario' });
            valida = false;
        }

        if (!this.diasMarcados[0]) {
            this.horarioModal.horainiciolunes = null;
            this.horarioModal.horafinlunes = null;
        }
        else {
            if (this.horarioModal.horainiciolunes == null || this.horarioModal.horafinlunes == null) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el rango de horas para el día Lunes' });
                valida = false;
            }
            else if (this.horarioModal.horainiciolunes > this.horarioModal.horafinlunes) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Lunes' });
                valida = false;
            }
            else if (this.horarioModal.horainiciolunes.toString() == this.horarioModal.horafinlunes.toString()) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Lunes' });
                valida = false;
            }
        }
        if (!this.diasMarcados[1]) {
            this.horarioModal.horainiciomartes = null;
            this.horarioModal.horafinmartes = null;
        }
        else {
            if (this.horarioModal.horainiciomartes == null || this.horarioModal.horafinmartes == null) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el rango de horas para el día Martes' });
                valida = false;
            }
            else if (this.horarioModal.horainiciomartes > this.horarioModal.horafinmartes) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Martes' });
                valida = false;
            }
            else if (this.horarioModal.horainiciomartes.toString() == this.horarioModal.horafinmartes.toString()) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Martes' });
                valida = false;
            }
        }
        if (!this.diasMarcados[2]) {
            this.horarioModal.horainiciomiercoles = null;
            this.horarioModal.horafinmiercoles = null;
        }
        else {
            if (this.horarioModal.horainiciomiercoles == null || this.horarioModal.horafinmiercoles == null) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el rango de horas para el día Miercoles' });
                valida = false;
            }
            else if (this.horarioModal.horainiciomiercoles > this.horarioModal.horafinmiercoles) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Miercoles' });
                valida = false;
            }
            else if (this.horarioModal.horainiciomiercoles.toString() == this.horarioModal.horafinmiercoles.toString()) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Miercoles' });
                valida = false;
            }
        }
        if (!this.diasMarcados[3]) {
            this.horarioModal.horainiciojueves = null;
            this.horarioModal.horafinjueves = null;
        }
        else {
            if (this.horarioModal.horainiciojueves == null || this.horarioModal.horafinjueves == null) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el rango de horas para el día Jueves' });
                valida = false;
            }
            else if (this.horarioModal.horainiciojueves > this.horarioModal.horafinjueves) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Jueves' });
                valida = false;
            }
            else if (this.horarioModal.horainiciojueves.toString() == this.horarioModal.horafinjueves.toString()) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Jueves' });
                valida = false;
            }
        }
        if (!this.diasMarcados[4]) {
            this.horarioModal.horainicioviernes = null;
            this.horarioModal.horafinviernes = null;
        }
        else {
            if (this.horarioModal.horainicioviernes == null || this.horarioModal.horafinviernes == null) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el rango de horas para el día Viernes' });
                valida = false;
            }
            else if (this.horarioModal.horainicioviernes > this.horarioModal.horafinviernes) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Viernes' });
                valida = false;
            }
            else if (this.horarioModal.horainicioviernes.toString() == this.horarioModal.horafinviernes.toString()) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Viernes' });
                valida = false;
            }
        }
        if (!this.diasMarcados[5]) {
            this.horarioModal.horainiciosabado = null;
            this.horarioModal.horafinsabado = null;
        }
        else {
            if (this.horarioModal.horainiciosabado == null || this.horarioModal.horafinsabado == null) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el rango de horas para el día Sabado' });
                valida = false;
            }
            else if (this.horarioModal.horainiciosabado > this.horarioModal.horafinsabado) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Sabado' });
                valida = false;
            }
            else if (this.horarioModal.horainiciosabado.toString() == this.horarioModal.horafinsabado.toString()) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Sabado' });
                valida = false;
            }
        }
        if (!this.diasMarcados[6]) {
            this.horarioModal.horainiciodomingo = null;
            this.horarioModal.horafindomingo = null;
        }
        else {
            if (this.horarioModal.horainiciodomingo == null || this.horarioModal.horafindomingo == null) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el rango de horas para el día Domingo' });
                valida = false;
            }
            else if (this.horarioModal.horainiciodomingo > this.horarioModal.horafindomingo) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Domingo' });
                valida = false;
            }
            else if (this.horarioModal.horainiciodomingo.toString() == this.horarioModal.horafindomingo.toString()) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingresar un rango de horas correcto para el día Domingo' });
                valida = false;
            }
        }

        if (!valida) {
            return;
        }

        var temp = [...this.dtoBean.lstHorarioDetalle];
        if (this.accionHorario == this.ACCIONES.NUEVO) {
            var max = 1;
            if (this.dtoBean.lstHorarioDetalle.length > 0) {
                max = Math.max(...this.dtoBean.lstHorarioDetalle.map(o => o.secuencia)) + 1;
            }
            this.horarioModal.secuencia = max;
            temp.push(this.horarioModal);
            this.dtoBean.lstHorarioDetalle = temp;
        }
        if (this.accionHorario == this.ACCIONES.EDITAR) {
            const aux = temp.find(obj => obj.secuencia === this.horarioModal.secuencia);
            const index = temp.indexOf(aux);
            temp[index] = this.horarioModal;
            this.dtoBean.lstHorarioDetalle = temp;
        }
        this.bloquearPagina();
        this.hrCapacitacionServicio.generarResumenDesdeDetalle(this.dtoBean).then(res => {
            this.desbloquearPagina();
            this.dtoBean = res;
            this.sumarTotalesHorasDias();
            this.verModalHorario = false;
        });
    }

    contarDiasSeleccionados() {
        return this.diasMarcados.filter(x => x == true).length;
    }

    agregarHorarioResumen() {
        var n = new DtoHrCapacitacionhorarioresumen();
        this.horarioResumenAnio = new Date().getFullYear();
        this.horarioResumenMes = new Date().getMonth() + 1;
        this.horarioResumenModal = new DtoHrCapacitacionhorarioresumen();
        this.horarioResumenModal.totaldias = 1;
        this.horarioResumenModal.totalhoras = 1;
        this.accionHorario = this.ACCIONES.NUEVO;
        this.verModalHorarioResumen = true;
    }

    guardarHorarioResumen() {
        var valida = true;
        if (this.horarioResumenAnio == null || this.horarioResumenAnio == undefined || this.horarioResumenAnio < new Date().getFullYear()) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el periodo' });
            valida = false;
        }
        if (this.horarioResumenMes == null || this.horarioResumenMes == undefined) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el periodo' });
            valida = false;
        }
        else {
            if (this.horarioResumenMes > 12 || this.horarioResumenMes < 1) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el periodo' });
                valida = false;
            }
        }

        this.horarioResumenModal.periodo = this.horarioResumenAnio.toString() + (this.horarioResumenMes < 10 ? '0' + this.horarioResumenMes.toString() : this.horarioResumenMes.toString());

        if (this.horarioResumenModal.totaldias == null || this.horarioResumenModal.totaldias == undefined) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el Total de días' });
            valida = false;
        }
        else {
            if (this.horarioResumenModal.totaldias < 1) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el Total de días' });
                valida = false;
            }
        }

        if (this.horarioResumenModal.totalhoras == null || this.horarioResumenModal.totalhoras == undefined) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el Total de horas' });
            valida = false;
        }
        else {
            if (this.horarioResumenModal.totalhoras < 1) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'Ingrese el Total de horas' });
                valida = false;
            }
        }

        if (this.dtoBean.lstHorarioResumen.filter(x => x.periodo == this.horarioResumenModal.periodo).length > 0) {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'El periodo ya ha sido ingresado' });
            valida = false;
        }

        if (!valida) {
            return;
        }

        var temp = [...this.dtoBean.lstHorarioResumen];
        if (this.accionHorario == this.ACCIONES.NUEVO) {
            temp.push(this.horarioResumenModal);
            this.dtoBean.lstHorarioResumen = temp;
        }
        if (this.accionHorario == this.ACCIONES.EDITAR) {
            const aux = temp.find(obj => obj.periodo === this.horarioResumenModal.periodo);
            const index = temp.indexOf(aux);
            temp[index] = this.horarioResumenModal;
            this.dtoBean.lstHorarioResumen = temp;
        }

        this.verModalHorarioResumen = false;
        this.sumarTotalesHorasDias();
    }

    eliminarHorarioResumen(row: DtoHrCapacitacionhorarioresumen) {
        this.confirmationService.confirm({
            key: "confirm",
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Está seguro de eliminar este registro?',
            accept: () => {
                let lst = [...this.dtoBean.lstHorarioResumen];
                lst = lst.filter(obj => obj.periodo !== row.periodo);
                this.dtoBean.lstHorarioResumen = lst;
                this.sumarTotalesHorasDias();
            }
        });
    }
    buscarCentroCosto() {
        this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTOS', null));
    }
    buscarUnidadNegocio() {
        this.unidadnegocioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADNEGOCIO', null));
    }
    buscarSucursal() {
        this.sucursalselectorComponent.coreIniciarComponente(new MensajeController(this, 'SUCURSAL', null));
    }
    buscarProyecto() {
        this.afemstSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PROYECTO', null));
    }
    buscarPais() {
        this.paisSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PAIS', null));
    }
    buscarCurso() {
        this.cursoSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CURSO', null));
    }
    buscarTipoCurso() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOCURSO', null), 'TIPO DE CURSO', 'HR', 'TIPOCURSO', '999999');
    }
    buscarCentroCapacitacion() {
        this.hrCentroEstudiosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROESTUDIO', null));
    }
    buscarModalidad() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'MODALIDAD', null), 'MODALIDAD', 'HR', 'CPMODALIDA', '999999');
    }
    buscarPlantillaEmpleado() {
        this.encuestaPlantillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PLANTILLAEMPLEADO', null), 'E');
    }
    buscarPlantillaJefe() {
        this.encuestaPlantillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PLANTILLAJEFE', null), 'J');
    }
    buscarFinanciamiento() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'FINANCIAMIENTO', null), 'FINANCIAMIENTO', 'HR', 'CPFINANCIA', '999999');
    }
    onChangeDias(dia: number, value: any) {
        console.log(dia, value);
        if (!value) {
            switch (dia) {
                case 1: { this.horarioModal.horainiciolunes = null; this.horarioModal.horafinlunes = null; break; }
                case 2: { this.horarioModal.horainiciomartes = null; this.horarioModal.horafinmartes = null; break; }
                case 3: { this.horarioModal.horainiciomiercoles = null; this.horarioModal.horafinmiercoles = null; break; }
                case 4: { this.horarioModal.horainiciojueves = null; this.horarioModal.horafinjueves = null; break; }
                case 5: { this.horarioModal.horainicioviernes = null; this.horarioModal.horafinviernes = null; break; }
                case 6: { this.horarioModal.horainiciosabado = null; this.horarioModal.horafinsabado = null; break; }
                case 7: { this.horarioModal.horainiciodomingo = null; this.horarioModal.horafindomingo = null; break; }
            }
        }
    }
    onChangeDiasTodos(value: boolean) {
        if (value) {
            this.diasMarcados = [true, true, true, true, true, true, true,];
        }
    }
    onChangeCursoNuevo(check) {
        this.dtoBean.cursonuevodescripcion = null;
    }

    booleanTrue: boolean = true;
    booleanFalse: boolean = false;
}
