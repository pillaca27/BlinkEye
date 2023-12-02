import { GpProveedorService } from './../../../../proveedor/registro/servicio/gpproveedor.service';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { AdjuntosSimpleComponent } from './../../adjuntos-simple/vista/adjuntossimple.component';
import { DtoComunSyDocumentoCabecera } from './../../adjuntos-simple/dominio/DtoComunSyDocumentoCabecera';
import { ConstanteAngular } from '@framework/angular/ConstanteAngular';
import { TituloListadoComponent } from '@framework/angular/controles/titulos/titulo-listado.component';
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { SelectItem, MessageService, LazyLoadEvent } from 'primeng/api';
import { BaseComponentLista } from '@framework/angular/component/BaseComponentLista';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { DominioMensajeUsuario } from '@framework/modelo/generico/DominioMensajeUsuario';


@Component({
    selector: 'app-vistaGeneral',
    templateUrl: './vistaGeneral.component.html'
})
export class VistaGeneralComponent extends BaseComponentLista implements OnInit, UIMantenimientoController {

    persona: string = 'Seleccionar persona';
    proveedor: string = 'Seleccionar proveedor';
    cuenta: string = 'Seleccionar Cuenta';
    juicio: string = 'Seleccionar Juicio';
    proyecto: string = null;
    referencia: string = 'Seleccionar Referencia';
    @ViewChild(TituloListadoComponent, { static: false }) TituloListadoComponent: TituloListadoComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;

    @ViewChild(AdjuntosSimpleComponent, { static: false }) adjuntosSimpleComponent: AdjuntosSimpleComponent;
    dtoCabecera: DtoComunSyDocumentoCabecera = new DtoComunSyDocumentoCabecera();
    
    verSelectorMantenimiento: boolean = false;

    constructor(
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        private gpProveedorService: GpProveedorService,
        private router: Router,
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {

    }

    //            
    coreGuardar(): void {         
        
        this.adjuntosSimpleComponent.procesarAdjuntos();
        
    }
    coreExportar(tipo: string): void {
    }
    coreSalir(): void {
    }
    coreAccion(accion: string): void {
    }

    coreMensaje(mensage: MensajeController) {
    }  

    ejemplo() {
        // this.router.navigate([ConstanteComun.ruta_logistica_mantenimiento], { skipLocationChange: true })
    }

    buscar(){                       
        this.adjuntosSimpleComponent.coreIniciarComponente(this.dtoCabecera, new MensajeController(this, 'ADJUNTOS', ''));
    }

    buscarConPlantilla(){                       
        var listaPlantilla = [];
        this.bloquearPagina();
        // this.gpProveedorService.obtenerDocumentos('DOCPLAN').then(resp =>{
        this.gpProveedorService.obtenerDocumentos('DOCFILT').then(resp =>{
            this.desbloquearPagina();
            listaPlantilla = resp;
            this.adjuntosSimpleComponent.coreIniciarComponenteConPlantilla(this.dtoCabecera, listaPlantilla);
        });

        // var tabla = new DtoTabla();
        // var tabla2 = new DtoTabla();
        // var tabla3 = new DtoTabla();
        // tabla.codigo = 'FRUC';
        // tabla.nombre = '<a href="https://disenowebakus.net/enlaces-hipervinculos-tag-ancla-html.php" target="_blank">Enlace a otra página</a>';
        // //Si es obligatorio o no
        // tabla.estadoId = 'S';
        // tabla.descripcion = 'plantilla\plantilla1.pdf';

        // tabla2.codigo = 'DNI';
        // tabla2.nombre = 'DNI del Representante Legal';
        // tabla3.estadoId = 'S';
        // tabla3.descripcion = 'plantilla\plantilla1.pdf';

        // tabla3.codigo = 'CONS';
        // tabla3.nombre = 'Constitución de la Empresa (solo para personas jurídicas)';
        // tabla2.estadoId = 'N';
        // tabla2.descripcion = 'plantilla\plantilla1.pdf';

        // listaPlantilla.push(tabla);
        // listaPlantilla.push(tabla2);
        // listaPlantilla.push(tabla3);        
        
    }
    

    agregar()
    {
        
    }





}

