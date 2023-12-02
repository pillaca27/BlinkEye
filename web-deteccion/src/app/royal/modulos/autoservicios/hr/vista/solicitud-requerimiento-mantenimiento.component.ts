import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { CONVERTIR_FOTO, ServicioComunService } from '@framework-comun/servicioComun.service';
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
import { UnidadnegocioSelectorComponent } from '@framework-comun/core/vista/unidadnegocio-selector.component';
import { MiscelaneosSelectorComponent } from '@framework-comun/core/vista/miscelaneos-selector.component';
import { UnidadoperativaSelectorComponent } from '@framework-comun/hr/vista/unidadoperativa-selector.component';
import { CentroCostosComunSelectorComponent } from '@framework-comun/contabilidad/vista/centrocostoscomunselector.component';
import { CategoriaSelectorComponent } from './categoria-selector.component';
import { GradoSalarioSelectorComponent } from './gradosalario-selector.component';
import { TipotrabajadorSelectorComponent } from './tipotrabajador-selector.component';
import { TipocontratoSelectorComponent } from './tipocontrato-selector.component';
import { TipoPlanillaSelectorComponent } from '@framework-comun/pr/vista/tipoplanilla-selector.component';
import { SucursalselectorComponent } from '@framework-comun/contabilidad/vista/sucursalselector.component';
import { AfemstSelectorComponent } from '@framework-comun/core/vista/afemstcomun-selector.component';
import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
import { ConstanteFichaEmpleado } from '../dominio/dto/HrActualizacionFichaEmpleado';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { DtoHrRequerimientoreemplazo } from '../dominio/dto/DtoHrRequerimientoreemplazo';
import { OrganigramaSelectorComponent } from '@framework-comun/hr/vista/organigrama-selector.component';
import { FiltroComunOrganigramaPuestos } from '@framework-comun/hr/dominio/filtro/FiltroComunOrganigramaPuestos';

@Component({
    templateUrl: './solicitud-requerimiento-mantenimiento.component.html'
})
export class SolicitudRequerimientoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    dtoBean: DtoHrRequerimiento = new DtoHrRequerimiento();
    lstExtensiones: string[] = [];
    lstMotivoCese: SelectItem[] = [];

    @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;
    @ViewChild(WfTransaccionBotonesComponent, { static: false }) wfTransaccionBotonesComponent: WfTransaccionBotonesComponent;
    @ViewChild(UnidadnegocioSelectorComponent, { static: false }) unidadnegocioSelectorComponent: UnidadnegocioSelectorComponent;
    @ViewChild(MiscelaneosSelectorComponent, { static: false }) miscelaneosSelectorComponent: MiscelaneosSelectorComponent;
    @ViewChild(UnidadoperativaSelectorComponent, { static: false }) unidadoperativaSelectorComponent: UnidadoperativaSelectorComponent;
    @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;
    @ViewChild(CategoriaSelectorComponent, { static: false }) categoriaSelectorComponent: CategoriaSelectorComponent;
    @ViewChild(GradoSalarioSelectorComponent, { static: false }) gradoSalarioSelectorComponent: GradoSalarioSelectorComponent;
    @ViewChild(TipotrabajadorSelectorComponent, { static: false }) tipotrabajadorSelectorComponent: TipotrabajadorSelectorComponent;
    @ViewChild(TipocontratoSelectorComponent, { static: false }) tipocontratoSelectorComponent: TipocontratoSelectorComponent;
    @ViewChild(TipoPlanillaSelectorComponent, { static: false }) tipoPlanillaSelectorComponent: TipoPlanillaSelectorComponent;
    @ViewChild(SucursalselectorComponent, { static: false }) sucursalselectorComponent: SucursalselectorComponent;
    @ViewChild(AfemstSelectorComponent, { static: false }) afemstSelectorComponent: AfemstSelectorComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;
    @ViewChild(OrganigramaSelectorComponent, { static: false }) organigramaSelectorComponent: OrganigramaSelectorComponent;

    constructor(
        private confirmationService: ConfirmationService,
        private maMiscelaneosdetalleComunService: MaMiscelaneosdetalleComunService,
        private router: Router,
        private hrRequerimientoServicio: HrRequerimientoServicio,
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
        p.push(this.servicioComunLocal.listaractivos(ConstanteFichaEmpleado.COMPANIA, 'SY', ConstanteFichaEmpleado.MISC_DOCUMENTOS_EXTENSIONES_PERMITIDOS).then(res => {
            res.forEach(ele => {
                this.lstExtensiones.push(ele.codigo.trim());
            });
            return 1;
        }));
        this.lstMotivoCese.push({ value: null, label: ' -- Seleccione --' });
        p.push(this.hrRequerimientoServicio.listarMotivoCese().then(res => {
            res.forEach(ele => {
                this.lstMotivoCese.push({ value: ele.id, label: ele.nombre });
            });
            return 1;
        }));

        Promise.all(p).then(
            res => {
                if (this.accion == this.ACCIONES.NUEVO) {
                    this.nuevo();
                }
                else {
                    this.obtenerPorUUID(this.route.snapshot.params['uuid'] as string);
                }
            }
        );
    }

    nuevo() {
        this.hrRequerimientoServicio.solicitudNuevo().then(
            res => {
                this.dtoBean = res;
                this.desbloquearPagina();
            }
        );
    }

    obtenerPorUUID(uuid: string) {
        var dto = new DtoHrRequerimiento();
        if (this.accion == this.ACCIONES.WORKFLOW)
            dto.transaccionUUID = uuid;
        else
            dto.uuid = uuid;

        this.hrRequerimientoServicio.solicitudObtenerPorUuid(dto).then(
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
            this.hrRequerimientoServicio.solicitudRegistrar(this.dtoBean).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultadoSimple(res)) {
                    this.mostrarMensajeExito(this.getMensajeGrabado(res.requerimiento));
                    this.coreSalir();
                }
            });
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.hrRequerimientoServicio.solicitudActualizar(this.dtoBean).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultadoSimple(res)) {
                    this.mostrarMensajeExito(this.getMensajeActualizado(res.requerimiento));
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
            this.router.navigate([ConstanteAutoservicios.ruta_solicitud_requerimiento_listado]);
        }
    }
    coreExportar(tipo: string): void {
        throw new Error('Method not implemented.');
    }
    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'UNIDADNEGOCIO') {
            this.dtoBean.unidadnegocio = mensage.resultado.unidadnegocio;
            this.dtoBean.auxUnidadNegocioDesc = mensage.resultado.descripcionlocal;
        }
        else if (mensage.componente == 'TIPOREQUERIMIENTO') {
            this.dtoBean.tiporequerimiento = mensage.resultado.codigo;
            this.dtoBean.auxTipoRequerimientoDesc = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'MOTIVO') {
            this.dtoBean.motivo = mensage.resultado.codigo;
            this.dtoBean.auxMotivoDesc = mensage.resultado.nombre;
            this.dtoBean.puestodescripcion = null;
            this.bloquearPagina();
            this.maMiscelaneosdetalleComunService.obtenerDto('HR', 'RSMOTIVO', '999999', this.dtoBean.motivo).then(res => {
                this.desbloquearPagina();
                if (res != null) {
                    this.dtoBean.auxMotivoValorCodigo2 = res.valorcodigo2;
                    this.dtoBean.auxMotivoValorCodigo1 = res.valorcodigo1;
                    var _nuevopuesto = this.dtoBean.auxMotivoValorCodigo2;
                    var _reemplazo = this.dtoBean.auxMotivoValorCodigo1;
                    if (_nuevopuesto == 'S') {
                        this.dtoBean.auxUIHabilitarPuestoDescripcion = 'S';
                        this.dtoBean.auxUIHabilitarBtnPuesto = 'N';
                    }
                    else {
                        this.dtoBean.auxUIHabilitarPuestoDescripcion = 'N';
                        this.dtoBean.auxUIHabilitarBtnPuesto = 'S';
                    }
                    if (_reemplazo == 'S') {
                        this.dtoBean.auxUIHabilitarReemplazo = 'S';
                    }
                    else {
                        this.dtoBean.auxUIHabilitarReemplazo = 'N';
                    }
                }
            });
        }
        else if (mensage.componente == 'UNIDADOPERATIVA') {
            this.dtoBean.unidadoperativa = mensage.resultado.unidadoperativa;
            this.dtoBean.auxUnidadOperativaDesc = mensage.resultado.descripcion;
            this.dtoBean.jeferesponsable = mensage.resultado.responsable;
            this.dtoBean.jeferesponsablecompania = mensage.resultado.responsablecompania;
            this.dtoBean.auxJefeInmediatoNombreCompleto = mensage.resultado.responsablenombre;
        }
        else if (mensage.componente == 'CENTROCOSTOS') {
            this.dtoBean.centrocosto = mensage.resultado.costcenter;
            this.dtoBean.auxCentrocostoDesc = mensage.resultado.localname;
        }
        else if (mensage.componente == 'CATEGORIA') {
            this.dtoBean.categoria = mensage.resultado.cargo;
            this.dtoBean.auxCategoriaFuncionalDesc = mensage.resultado.nombre;
            this.dtoBean.gradosalario = mensage.resultado.nivelsalario;
            this.dtoBean.auxNivelSalarialDesc = mensage.resultado.nivelsalarionombre;
            this.dtoBean.auxSalarioMinimo = mensage.resultado.salariominimo;
        }
        else if (mensage.componente == 'SALARIO') {
            this.dtoBean.gradosalario = mensage.resultado.nombre;
            this.dtoBean.auxNivelSalarialDesc = mensage.resultado.descripcion;
            this.dtoBean.auxSalarioMinimo = mensage.resultado.codigo;
        }
        else if (mensage.componente == 'TIPOTRABAJADOR') {
            this.dtoBean.tipotrabajador = mensage.resultado.codigo;
            this.dtoBean.auxTipoTrabajadorDesc = mensage.resultado.nombre;
        }
        else if (mensage.componente == 'TIPOCONTRATO') {
            this.dtoBean.tipocontrato = mensage.resultado.codigo;
            this.dtoBean.auxTipoContratoDesc = mensage.resultado.nombre;
            if (mensage.resultado.estadoId == 'S') {
                this.dtoBean.auxUIVigenciavencimiento = 'S';
            }
            else {
                this.dtoBean.auxUIVigenciavencimiento = 'N';
            }
            this.dtoBean.fincontrato = null;
        }
        else if (mensage.componente == 'TIPOPLANILLA') {
            this.dtoBean.tipoplanilla = mensage.resultado.codigo;
            this.dtoBean.auxTipoPlanillaDesc = mensage.resultado.descripcion;
        }
        else if (mensage.componente == 'SUCURSAL') {
            this.dtoBean.sucursal = mensage.resultado.sucursal;
            this.dtoBean.auxSucursalDesc = mensage.resultado.descripcionlocal;
        }
        else if (mensage.componente == 'PROYECTO') {
            this.dtoBean.afe = mensage.resultado.afe;
            this.dtoBean.auxAfeDesc = mensage.resultado.localname;
        }
        else if (mensage.componente == 'REEMPLAZO') {
            if (this.dtoBean.lstReemplazos.filter(x => x.empleado == mensage.resultado.persona).length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'El empleado ya ha sido seleccionado' });
                return;
            }
            var temp = [...this.dtoBean.lstReemplazos];
            const participante: DtoHrRequerimientoreemplazo = new DtoHrRequerimientoreemplazo();
            participante.empleado = mensage.resultado.persona;
            participante.empleadocompania = mensage.resultado.companiasocio;
            participante.auxEmpleadoNombre = mensage.resultado.busqueda;
            participante.codigopuesto = mensage.resultado.codigocargo;
            participante.auxPuestoNombre = mensage.resultado.codigocargoNombre;
            participante.motivocese = mensage.resultado.motivocese;
            participante.fechacese = mensage.resultado.fechacese;
            temp.push(participante);
            this.dtoBean.lstReemplazos = temp;
        }
        else if (mensage.componente == 'ORG') {
            this.bloquearPagina();
            this.dtoBean.codigopuesto = mensage.resultado.codigopuesto;
            this.dtoBean.auxPuestoDesc = mensage.resultado.descripcion;
            this.dtoBean.codigounidad = mensage.resultado.unidadorganigrama;
            this.dtoBean.auxUnidadorganigramaDesc = mensage.resultado.unidadorganigramanombre;
            this.dtoBean.categoria = this.trimNoNulo(mensage.resultado.categoriafuncional);
            this.dtoBean.auxCategoriaFuncionalDesc = mensage.resultado.categorianombre;
            this.dtoBean.centrocosto = this.trimNoNulo(mensage.resultado.cc);
            this.dtoBean.auxCentrocostoDesc = mensage.resultado.ccNombre;
            this.dtoBean.gradosalario = this.trimNoNulo(mensage.resultado.gradosalario);
            this.dtoBean.auxNivelSalarialDesc = mensage.resultado.gradosalarionombre;
            this.dtoBean.auxSalarioMinimo = mensage.resultado.salariominimo;
            this.dtoBean.auxTiempoReclutamiento = mensage.resultado.tiemporeclutar;
            this.hrRequerimientoServicio.validarVacantes(this.dtoBean).then(
                res => {
                    this.desbloquearPagina();
                    res.transaccionListaMensajes.forEach(
                        r => {
                            this.mostrarMensajeAdvertencia(r.mensaje);
                        }
                    );
                }
            );
        }
    }
    coreAccion(accion: string): void {
        throw new Error('Method not implemented.');
    }

    agregarReemplazo() {
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'REEMPLAZO', 'EMOT'));
    }
    buscarUnidadNegocio() {
        this.unidadnegocioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADNEGOCIO', null));
    }
    buscarTipoRequerimiento() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOREQUERIMIENTO', null), 'TIPO DE REQUERIMIENTO', 'HR', 'TIPREQUERI', '999999');
    }
    buscarMotivo() {
        this.miscelaneosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'MOTIVO', null), 'MOTIVO DE REQUERIMIENTO', 'HR', 'RSMOTIVO', '999999');
    }
    buscarUnidadOperativa() {
        this.unidadoperativaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADOPERATIVA', null));
    }
    buscarCentroCosto() {
        this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTOS', null));
    }
    buscarCategoria() {
        this.categoriaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CATEGORIA', null));
    }
    buscarNivelSalarial() {
        this.gradoSalarioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'SALARIO', null));
    }
    buscarTipoTrabajador() {
        this.tipotrabajadorSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOTRABAJADOR', null));
    }
    buscarTipoContrato() {
        this.tipocontratoSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOCONTRATO', null));
    }
    buscarTipoPlanilla() {
        this.tipoPlanillaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'TIPOPLANILLA', null));
    }
    buscarSucursal() {
        this.sucursalselectorComponent.coreIniciarComponente(new MensajeController(this, 'SUCURSAL', null));
    }
    buscarProyecto() {
        this.afemstSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PROYECTO', null));
    }

    infPuestoCodigo: number;
    infPuestoNombre: string;
    verModalInfra: boolean = false;
    tieneAccesos: boolean[] = [false, false, false, false, false, false, false, false, false, false];
    infraCom1: string;
    infraCom2: string;

    infraestructura() {
        if (this.dtoBean.codigopuesto == null && this.dtoBean.auxMotivoValorCodigo2 != 'S') {
            this.messageService.add({ severity: 'warn', summary: 'Validación', detail: 'El puesto es requerido' });
            return;
        }
        this.infPuestoCodigo = this.dtoBean.codigopuesto;
        this.infPuestoNombre = this.dtoBean.auxPuestoDesc;
        this.tieneAccesos[0] = this.dtoBean.accesooficina == 'S' ? true : false;
        this.tieneAccesos[1] = this.dtoBean.accesolaptop == 'S' ? true : false;
        this.tieneAccesos[2] = this.dtoBean.accesointernet == 'S' ? true : false;
        this.tieneAccesos[3] = this.dtoBean.accesoimpresora == 'S' ? true : false;
        this.tieneAccesos[4] = this.dtoBean.accesocorreo == 'S' ? true : false;
        this.tieneAccesos[5] = this.dtoBean.accesotelefono == 'S' ? true : false;
        this.tieneAccesos[6] = this.dtoBean.accesopc == 'S' ? true : false;
        this.tieneAccesos[7] = this.dtoBean.accesocelular == 'S' ? true : false;
        this.tieneAccesos[8] = this.dtoBean.accesootros == 'S' ? true : false;
        this.tieneAccesos[9] = this.dtoBean.accesootrossistemas == 'S' ? true : false;
        this.infraCom1 = this.dtoBean.accesootroscomentario;
        this.infraCom2 = this.dtoBean.accesootrossistemascomentario;
        this.verModalInfra = true;
    }
    guardarAccesos() {
        this.dtoBean.accesooficina = this.tieneAccesos[0] ? 'S' : 'N';
        this.dtoBean.accesolaptop = this.tieneAccesos[1] ? 'S' : 'N';
        this.dtoBean.accesointernet = this.tieneAccesos[2] ? 'S' : 'N';
        this.dtoBean.accesoimpresora = this.tieneAccesos[3] ? 'S' : 'N';
        this.dtoBean.accesocorreo = this.tieneAccesos[4] ? 'S' : 'N';
        this.dtoBean.accesotelefono = this.tieneAccesos[5] ? 'S' : 'N';
        this.dtoBean.accesopc = this.tieneAccesos[6] ? 'S' : 'N';
        this.dtoBean.accesocelular = this.tieneAccesos[7] ? 'S' : 'N';
        this.dtoBean.accesootros = this.tieneAccesos[8] ? 'S' : 'N';
        this.dtoBean.accesootrossistemas = this.tieneAccesos[9] ? 'S' : 'N';
        this.dtoBean.accesootroscomentario = this.infraCom1;
        this.dtoBean.accesootrossistemascomentario = this.infraCom2;
        this.verModalInfra = false;
    }
    evaluarOtros() {
        this.infraCom1 = !this.tieneAccesos[8] ? null : this.infraCom1;
    }

    evaluarSpring() {
        this.infraCom2 = !this.tieneAccesos[9] ? null : this.infraCom2;
    }
    cargarAdjunto(event: any) {
        var files = event.files;
        if (files.length !== 1) {
            return;
        }
        this.bloquearPagina();
        var reader = new FileReader();
        reader.readAsDataURL(files[0]);
        reader.onloadend = (evt) => {
            var result = reader.result.toString().split(',')[1];
            this.desbloquearPagina();
            if (!this.contenidoFileValidoPorTipos(result, this.lstExtensiones)) {
                return;
            }
            this.dtoBean.auxDocumentoAdjuntoBase64 = result;
            this.dtoBean.rutadocumento = files[0].name;
        };
    }
    descargarAdjunto() {
        if (this.dtoBean.auxDocumentoAdjuntoBase64 == null) {
            this.mostrarMensajeAdvertencia('No se encontró el documento');
            return;
        }
        const a = document.createElement('a');
        a.href = 'data:application/octet-stream;base64,' + this.dtoBean.auxDocumentoAdjuntoBase64;

        a.setAttribute('download', this.dtoBean.rutadocumento);
        const b = document.createEvent('MouseEvents');
        b.initEvent('click', false, true);
        a.dispatchEvent(b);
    }
    eliminarReemplazo(row) {
        this.confirmationService.confirm({
            key: "confirm",
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: '¿Está seguro de eliminar este registro?',
            accept: () => {
                let lst = [...this.dtoBean.lstReemplazos];
                lst = lst.filter(obj => obj.empleado !== row.empleado);
                this.dtoBean.lstReemplazos = lst;
            }
        });
    }

    orgAnio: number;
    orgSecuencia: number;
    orgUnidad: string;

    mostrarSelectorPuestoOrg() {
        this.bloquearPagina();
        this.validarOrganigrama().then(
            response => {
                this.desbloquearPagina();
                if (response) {
                    var msj = new MensajeController(this, 'ORG', null);
                    var filtro = new FiltroComunOrganigramaPuestos();
                    filtro.companiaSocio = this.dtoBean.companyowner;
                    filtro.unidadOriginal = this.dtoBean.codigounidad;
                    filtro.puestoOriginal = this.dtoBean.codigopuesto;
                    filtro.unidadNegocio = this.orgUnidad;
                    filtro.anio = this.orgAnio;
                    filtro.secuencia = this.orgSecuencia;
                    filtro.evento = 'Browse';
                    msj.parametros = filtro;
                    this.organigramaSelectorComponent.coreIniciarComponente(msj);
                }
            }
        );
    }
    validarOrganigrama(): Promise<boolean> {
        return this.hrRequerimientoServicio.validarOrganigarma(this.dtoBean).then(
            response => {
                if (response.filter(x => x.tipoMensaje == 'ADVERTENCIA').length == 0) {
                    var pks = response.find(x => x.tipoMensaje == 'INFORMACION').mensaje.split('-');
                    this.orgAnio = parseInt(pks[0]);
                    this.orgSecuencia = parseInt(pks[1]);
                    this.orgUnidad = pks[2];
                    return true;
                }
                this.messageService.addAll(this.getMensajesUsuario(response));
                return false;
            }
        );
    }
}
