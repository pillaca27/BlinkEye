import { WfTransaccionChatComponent } from './../../../framework-workflow/vista/transaccion-chat.component';
import { DtoSeguridadConceptoList } from './../dominio/dto/DtoSeguridadConceptoList';
import { CentroCostosComunSelectorComponent } from './../../contabilidad/vista/centrocostoscomunselector.component';
import { FiltroSySeguridadconcepto } from './../dominio/filtro/FiltroSySeguridadconcepto';
import { SySeguridadconceptoService } from './../servicio/syseguridadconcepto.servicio';
import { AplicacionesmastComunService } from './../../seguridad/servicio/aplicacionesmast-comun.service';
import { Observable } from 'rxjs';
import { WhCotizacionComparacionPreciosComponent } from '@erp-logistica/cotizaciones/vista/whcotizacion-comparacionprecios.component';
import { UnidadesmastComunService } from './../../../framework-comun/core/servicio/unidadesmast-comun.service';
import { SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { Table } from 'primeng/table';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { UIListadoController } from '@framework/angular/interface/UIListadoController';
import { BotonesListadoComponent } from '@framework/angular/controles/botones/botones-listado.component';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { CompanyownerComunService } from '../../../framework-comun/core/servicio/companyowner-comun.service';
import { MaUnidadnegocioComunService } from '../../../framework-comun/core/servicio/maunidadnegocio-comun.service';
import { UsuarioService } from '../../../framework-seguridad/servicio/spring/seguridad/usuario.service';
import { PersonaComunSelectorComponent } from '../../../framework-comun/core/vista/personacomunselector.component';
import { WhCotizacionConsultaChatComponent } from '@erp-logistica/cotizacionconsulta/vista/whcotizacionconsulta-chat.component';
import { ConstanteLogistica } from '@erp-logistica/ConstanteLogistica';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { WhSubastaService } from '@erp-logistica/subasta/servicio/whsubasta.servicio';
import { DtoSySeguridadconcepto } from '../dominio/dto/DtoSySeguridadconcepto';

@Component({
    selector: 'app-seguridad-concepto-listado',
    templateUrl: './seguridad-concepto.component.html',
})
export class SeguridadConceptoComponent extends FormularioComponent implements OnInit, UIListadoController {

    @ViewChild(BotonesListadoComponent, { static: false }) botonesComponent: BotonesListadoComponent;
    @ViewChild(Table, { static: false }) dt: Table;
    @ViewChild(Table, { static: false }) dtDetalle: Table;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;
    @ViewChild(WhCotizacionConsultaChatComponent, { static: false }) whCotizacionConsultaChatComponent: WhCotizacionConsultaChatComponent;
    @ViewChild(WfTransaccionChatComponent, { static: false }) wfTransaccionChatComponent: WfTransaccionChatComponent;
    @ViewChild(WhCotizacionComparacionPreciosComponent, { static: false }) whCotizacionComparacionPreciosComponent: WhCotizacionComparacionPreciosComponent;
    @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;
    

    lstCompanias: SelectItem[] = [];
    lstEstados: SelectItem[] = [];
    lstUnidades: SelectItem[] = [];
    lstUnidadesmst: SelectItem[] = [];

    checkTrue: boolean = true;
    checkFalse: boolean = false;

    prtCompania: boolean = false;
    prtFecha: boolean = false;
    prtUnidad: boolean = false;
    prtEstado: boolean = false;

    modoInput: string;
    iv_accion: string;

    ingresoPrecios: boolean = false;
    aprobarIngresoPrecios: boolean = false;
    revisionIngresoPrecios: boolean = false;
    
    verPreciosComparador: boolean = true;

    lstAplicacion: SelectItem[] = [];

    constructor(
        private whSubastaService: WhSubastaService,
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private companyownerComunService: CompanyownerComunService,
        private sySeguridadconceptoService: SySeguridadconceptoService,
        private unidadnegocioComunService: MaUnidadnegocioComunService,
        private unidadesService: UnidadesmastComunService,
        private usuarioService: UsuarioService,
        private confirmationService: ConfirmationService,
        private aplicaciones: AplicacionesmastComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
        this.route.data.subscribe(x => this.modoInput = x.tipo);

        const p1 = this.cargarCompanias();
        // const p2 = this.miscelaneosListar(this.comboEtiquetaTipo.LISTADO, WhCotizacionConstante.APLICACION_CODIGO, WhCotizacionConstante.MISCELANEO_ESTADO, WhCotizacionConstante.DEFECTO_COMPANIA, this.lstEstados);
        const p3 = this.cargarUnidadesNeg();
        const p4 = this.listarUnidades();        
        // p2, 
        Promise.all([p1, p4, p3]).then((resp) => {
            this.formularioOninit = false;

            this.dto.filtro.aplicacioncodigo = null;
            this.lstAplicacion.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
            this.aplicaciones.listarActivos().then(
                res => {
                    res.forEach(r => {
                        this.lstAplicacion.push({ label: r.nombre, value: r.codigo.trim() });
                    });
                    
                    this.dto.filtro.aplicacioncodigo = "SY";
                    this.dto.filtro.grupo = 'CENTROCOSTOS';

                    this.coreBuscar();
                }
            );
          
        });
    }
    

    cargarCompanias(): Promise<number> {
        this.lstCompanias.push({ label: '-- Todos --', value: null })
        return this.companyownerComunService.listarActivos().then(resp => {
            resp.forEach(obj => this.lstCompanias.push({ label: obj.nombre, value: obj.codigo.trim() }));
            return 1;
        });
    }

    cargarUnidadesNeg(): Promise<number> {
        this.lstUnidades.push({ label: '-- Todos --', value: null })
        return this.unidadnegocioComunService.listarActivos().then(resp => {
            resp.forEach(obj => this.lstUnidades.push({ label: obj.nombre, value: obj.codigo.trim() }));
            return 1;
        });
    }

    coreBuscar(): void {
        this.grillaCargarDatos();
    }

    grillaCargarDatos() {
        
            this.bloquearPagina();
            this.dto.listado = [];
        
            this.sySeguridadconceptoService.listarPaginadoSeguridadConcepto(this.dto.filtro).then((res) => {
                 this.dto.listado = res;
                 this.desbloquearPagina();
            });        


    }

    coreBusquedaRapida(filtro: string): void { }

    coreExportar(tipo: string): void {
        // this.filtro.tipoexportar = tipo;
        // this.exportar()
    }

    coreMensaje(mensage: MensajeController): void {
        console.log("coremensaje");
        console.log(mensage);

        if (mensage.componente == 'CENTROCOSTO' && mensage.parametros != 'MULTIPLE') {
            console.log(mensage.resultado.costcenter);
            this.cambiarConceptos(mensage.resultado.costcenter,mensage.resultado.localname);
            this.mostrarMensajeExito('Acción realizada correctamente.');
        }
        if (mensage.componente == 'CENTROCOSTO' && mensage.parametros == 'MULTIPLE') {
            mensage.resultado.forEach(dw => {
                console.log(dw.costcenter);
                this.cambiarConceptos(dw.costcenter,dw.localname);
            });
            this.mostrarMensajeExito('Acción realizada correctamente.');
        }
        
    }

    coreFiltro(val: boolean): void { }

    coreAccion(accion: string): void { }

    coreNuevo(): void {
        //this.router.navigate(['', this.ACCIONES.NUEVO], { skipLocationChange: true })
    }

    coreEditar(dto: any) {                        
        //this.router.navigate(['portal/logistica/whquotation-vendor-edit', this.ACCIONES.EDITAR, '1'], { skipLocationChange: true });
    }
    

    

    coreComentario(dto: any) {    
    }

    coreVer(dto: any) {        
    }
    
    coreAnular(dto: any) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea anular este registro ? ",
            accept: () => {
                this.bloquearPagina();
                // this.whCotizacionService.anular(dto).then((res) => {
                //     this.desbloquearPagina();
                //     if (this.transaccionResultado(res))
                //         this.coreBuscar();
                // });
            },
            key: "confirm",
        });
    }

    coreEliminar(dto: any) {        

        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar la Cotización? ",
            accept: () => {
                // this.bloquearPagina();
                // this.messageService.clear();
                // this.whCotizacionService.eliminar(dto).then((res) => {
                //     this.desbloquearPagina();
                //     if (this.transaccionResultado(res))
                //         this.coreBuscar();
                // });
            },
            key: "confirm",
        });
    }

    mostrarSelectorEmpleado() {
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'EMPLEADO', 'PREMOT'));
    }

    limpiarEmpleado() {
        // this.filtro.proveedor = null;
        // this.filtro.proveedorNombre = '';
    }

    listarUnidades(): Promise<number> {
        return this.unidadesService.listarActivos().then(res => {
            res.forEach(ele => {
                this.lstUnidadesmst.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }


    mostrarSelectorCentroCosto() {
        this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTO', ''), true);
    }

    dto:DtoSeguridadConceptoList = new DtoSeguridadConceptoList();

    coreGuardar(){  
        this.bloquearPagina();      
        this.sySeguridadconceptoService.actualizarSeguridadConcepto(this.dto).then(resp=>{
            if(this.transaccionResultado(resp)){
                this.desbloquearPagina();
                this.dto.listado=[];
                this.coreBuscar();
            };
        });
    }


    cambiarConceptos(centrocosto:string, nombre: string){
        
        
        var res = this.dto.listado.find(e=>e.concepto.trim()==centrocosto.trim());

        if(res != null && res != undefined){
            res.descripcionlocal = nombre;
            res.ultimafechamodif = new Date();
            res.ultimousuario = this.getUsuarioActual().usuario;
            res.flgactualizar='S';
            res.color='#fdeda9';
        }else{
            res = new DtoSySeguridadconcepto();
            res.concepto = centrocosto;
            res.descripcionlocal = nombre;
            res.ultimafechamodif = new Date();
            res.ultimousuario = this.getUsuarioActual().usuario;
            res.estado = 'A';
            res.flgactualizar='S';
            res.color='#a9fda9';
            this.dto.listado.push(res);
        }      
    }
    

}
