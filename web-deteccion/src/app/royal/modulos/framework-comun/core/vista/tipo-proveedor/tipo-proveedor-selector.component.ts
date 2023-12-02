import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FiltroComunPersonamastclis001 } from '@framework-comun/core/dominio/filtro/FiltroComunPersonamastclis001';
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
import { PersonamastComunService } from '@framework-comun/core/servicio/personamast-comun.service';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BaseComponent } from '@framework/angular/component/BaseComponent';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UISelectorController } from '@framework/angular/interface/UISelectorController';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { SelectItem, MessageService, LazyLoadEvent } from 'primeng/api';
import { Table } from 'primeng/table';
 

@Component({
    selector: 'app-tipo-proveedor-selector',
    templateUrl: './tipo-proveedor-selector.component.html',
    styleUrls: ['./tipo-proveedor-selector.component.scss']
})
export class TipoProveedorSelectorComponent extends BaseComponent implements OnInit, UISelectorController {

    registrosPorPagina: number = 25;
    verSelector = false;
    lstEstados: SelectItem[] = [];
    lstLocalidad: SelectItem[] = []
    lstActividadEconomica:SelectItem[]=[]
    filtro: FiltroComunPersonamastclis001 = new FiltroComunPersonamastclis001();
    paginacion: DominioPaginacion = new DominioPaginacion();
    titulo: string;
    flagVerSeleccionar: boolean = false;
    tag:string;

    tipoFormulario:string='DEFAULT'

    constructor(
        private personaService: PersonamastComunService,
        private cdref: ChangeDetectorRef,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        private comunServive: MaPersonagrupoComunService,
        messageService: MessageService,
        servicioComun: ServicioComunService) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngAfterContentChecked() {
        this.cdref.detectChanges();
    }

    ngOnInit() {

        this.verSelector = false;
        this.titulo = '';
        this.listarEstados();
        this.listarDepartamental()
        this.listarCategorias()
        this.filtro.estado = 'A';
    }



    cargarEvent(event: LazyLoadEvent) {
        if (!this.verSelector) {
            return;
        }

        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionListaResultado = [];
        this.filtro.paginacion.paginacionRegistroInicio = event.first;
        this.filtro.paginacion.paginacionRegistrosPorPagina = event.rows;
        this.personaService.listarTiposProveedor(this.filtro)
            .then(pg => {

                this.filtro.paginacion.paginacionListaResultado = pg.paginacionListaResultado;
                this.filtro.paginacion.paginacionRegistroInicio = pg.paginacionRegistroInicio;
                this.filtro.paginacion.paginacionRegistrosEncontrados = pg.paginacionRegistrosEncontrados;
                this.filtro.paginacion.paginacionRegistrosPorPagina = pg.paginacionRegistrosPorPagina;
                this.mensajeController.componenteDestino.desbloquearPagina();
            });

    }

    listarDefecto() {

        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.personaService.listarTiposProveedor(this.filtro)
            .then(pg => {
                this.filtro.paginacion.paginacionListaResultado = pg.paginacionListaResultado;
                this.filtro.paginacion.paginacionRegistroInicio = pg.paginacionRegistroInicio;
                this.filtro.paginacion.paginacionRegistrosEncontrados = pg.paginacionRegistrosEncontrados;
                this.filtro.paginacion.paginacionRegistrosPorPagina = pg.paginacionRegistrosPorPagina;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
    }

    listarDefectoCotix() {

        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.personaService.listarTiposProveedorCotizacion(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
    }

    listarDefectoCotixDt() {
        this.mensajeController.componenteDestino.bloquearPagina();
        this.filtro.paginacion.paginacionRegistroInicio = 0;
        this.filtro.paginacion.paginacionRegistrosPorPagina = this.registrosPorPagina;
        this.personaService.listarTiposProveedorCotizacion(this.filtro)
            .then(pg => {
                this.filtro.paginacion = pg;
                this.mensajeController.componenteDestino.desbloquearPagina();
                this.verSelector = true;
            });
    }

    listarDepartamental(): Promise<number> {
        this.lstLocalidad.push({ label: 'Todos', value: null });
        return this.comunServive.listarDepartamental().then(res => {
            res.forEach(ele => {
                this.lstLocalidad.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    listarCategorias(): Promise<number> {
        this.lstActividadEconomica.push({ label: 'Todos', value: null });
        return this.comunServive.listarCategorias().then(res => {
            res.forEach(ele => {
                this.lstActividadEconomica.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    listarEstados() {
        this.lstEstados.push({ label: 'Todos', value: null });
        this.lstEstados.push({ label: 'Nacional', value: 'N' });
        this.lstEstados.push({ label: 'Extranjero', value: 'E' });
    }

    coreBusquedaRapida(filtro: string) {
    };

    coreBuscar(dt: Table) {
        dt.reset();
    }

    coreBuscarCotix() {
        this.listarDefectoCotixDt()
    }

    preBuscar(event?: any, tb?: any) {
        if (event.keyCode === 13) {
            this.coreBuscar(tb);
        }
    }

    preBuscarCotix(event?: any) {
        if (event.keyCode === 13) {
            this.coreBuscarCotix()
        }
    }

    coreFiltro(flag: boolean) {
    };

    coreSalir() {
        this.mensajeController.componenteDestino.desbloquearPagina();
        this.verSelector = false;
    };

    coreSeleccionar(dto: any) {
        if (dto === null) {
            this.mostrarMensajeInfo('Debe seleccionar un registro');
            return;
        }
        this.mensajeController.resultado = dto;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }

    seleccionProveedores: FiltroComunPersonamastclis001[] = [];

    seleccionProv: FiltroComunPersonamastclis001 = new FiltroComunPersonamastclis001();

    coreSeleccionarMultiple() {
        if (this.esListaVacia(this.seleccionProveedores)) {
            this.mostrarMensajeInfo('Debe seleccionar un registro');
            return;
        }
        this.mensajeController.resultado = this.seleccionProveedores;
        if(this.flagVerSeleccionar){
            this.mensajeController.parametros = 'MULTIPLE';
        }else{
            this.mensajeController.parametros = null;
        }
        
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }
    

    registroSeleccionado:any
    coreSeleccionarTodo() {
        if (this.registroSeleccionado == null) {
            this.mostrarMensajeInfo('Debe seleccionar uno o mas registro');
            return;
        }
        this.mensajeController.resultado = this.registroSeleccionado ;
        this.mensajeController.componenteDestino.coreMensaje(this.mensajeController);
        this.coreSalir();
    }


    coreExportar() {
    }
    coreMensaje() {
    }
    coreAccion() {
    }
    coreIniciarComponente(msj: MensajeController) {
        this.seleccionProveedores = [];
        this.mensajeController = msj;
        this.verSelector = true;
        this.titulo = 'PROVEEDOR';
        this.filtro = new FiltroComunPersonamastclis001();
        this.mensajeController.componenteDestino.bloquearPagina();
        this.tipoFormulario='DEFAULT'
        this.mensajeController.parametros = '';
        this.flagVerSeleccionar = false;
        this.listarDefecto();
    }

    coreIniciarComponenteMultiple(msj: MensajeController) {
        this.seleccionProveedores = [];
        this.mensajeController = msj;
        this.verSelector = true;
        this.titulo = 'PROVEEDOR';
        this.filtro = new FiltroComunPersonamastclis001();
        this.mensajeController.componenteDestino.bloquearPagina();
        this.mensajeController.parametros = 'MULTIPLE';
        this.tipoFormulario='DEFAULT'
        this.flagVerSeleccionar = true;
        this.listarDefecto();
    }    

    coreIniciarComponenteCotix(msj: MensajeController) {
        this.seleccionProveedores = [];
        this.mensajeController = msj;
        this.verSelector = true;
        this.titulo = 'PROVEEDOR';
        this.filtro = new FiltroComunPersonamastclis001();
        this.mensajeController.componenteDestino.bloquearPagina();
        this.tipoFormulario='COTIX'
        this.registroSeleccionado=null
        this.mensajeController.parametros = '';
        this.flagVerSeleccionar = false;
        this.listarDefectoCotix();
    }

}
