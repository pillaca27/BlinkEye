import { FiltroComunHrPuestoempresa } from './hr/dominio/filtro/FiltroComunHrPuestoempresa';
import { FiltroComunHrCursodescripcion } from './hr/dominio/filtro/FiltroComunHrCursodescripcion';
import { Component, OnInit, ViewChild } from '@angular/core';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { BaseComponentLista } from '@framework/angular/component/BaseComponentLista';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { MessageService } from 'primeng/api';
import { PaisSelectorComponent } from './core/vista/pais-selector.component';
import { UbicacionGeograficaSelectorComponent } from './core/vista/ubicaciongeografica-selector.component';
import { CursoSelectorComponent } from './hr/vista/curso-selector.component';
import { HrCentroEstudiosSelectorComponent } from './hr/vista/hrcentroestudiosselector.component';
import { CentroCostosComunSelectorComponent } from './contabilidad/vista/centrocostoscomunselector.component';
import { CompaniaSelectorComponent } from './core/vista/compania-selector.component';
import { UnidadnegocioSelectorComponent } from './core/vista/unidadnegocio-selector.component';
import { SucursalselectorComponent } from './contabilidad/vista/sucursalselector.component';
import { AfemstSelectorComponent } from './core/vista/afemstcomun-selector.component';
import { UnidadoperativaSelectorComponent } from './hr/vista/unidadoperativa-selector.component';
import { PuestoSelectorComponent } from './hr/vista/puesto-selector.component';
import { EtapaevaluacionSelectorComponent } from './hr/vista/etapaevaluacion-selector.component';
import { FiltroComunHrReqetapa } from './hr/dominio/filtro/FiltroComunHrReqetapa';

@Component({
    selector: 'app-comuntest',
    templateUrl: './comuntest.component.html'
})
export class ComunTestComponent extends BaseComponentLista implements OnInit, UIMantenimientoController {

    @ViewChild(UbicacionGeograficaSelectorComponent, { static: false }) ubicacionGeograficaSelectorComponent: UbicacionGeograficaSelectorComponent;
    @ViewChild(PaisSelectorComponent, { static: false }) paisSelectorComponent: PaisSelectorComponent;
    @ViewChild(CursoSelectorComponent, { static: false }) cursoSelectorComponent: CursoSelectorComponent;
    @ViewChild(HrCentroEstudiosSelectorComponent, { static: false }) hrCentroEstudiosSelectorComponent: HrCentroEstudiosSelectorComponent;
    @ViewChild(CentroCostosComunSelectorComponent, { static: false }) centroCostosComunSelectorComponent: CentroCostosComunSelectorComponent;
    @ViewChild(CompaniaSelectorComponent, { static: false }) companiaSelectorComponent: CompaniaSelectorComponent;
    @ViewChild(UnidadnegocioSelectorComponent, { static: false }) unidadnegocioSelectorComponent: UnidadnegocioSelectorComponent;
    @ViewChild(SucursalselectorComponent, { static: false }) sucursalselectorComponent: UnidadnegocioSelectorComponent;
    @ViewChild(AfemstSelectorComponent, { static: false }) afemstSelectorComponent: AfemstSelectorComponent;
    @ViewChild(UnidadoperativaSelectorComponent, { static: false }) unidadoperativaSelectorComponent: UnidadoperativaSelectorComponent;
    @ViewChild(PuestoSelectorComponent, { static: false }) puestoSelectorComponent: PuestoSelectorComponent;
    @ViewChild(EtapaevaluacionSelectorComponent, { static: false }) etapaevaluacionSelectorComponent: EtapaevaluacionSelectorComponent;

    constructor(
        messageService: MessageService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    codigo: string;
    codigoCompania: string;

    nombreUbigeo: string;
    nombrePais: string;
    nombreCurso: string;
    nombreCentroEstudio: string;
    nombreCentroCosto: string;
    nombreCompania: string;
    nombreUnidadnegocio: string;
    nombreSucursal: string;
    nombreProyecto: string;
    nombreUnidadOperativa: string;
    nombrePuesto: string;
    nombreEtapaEvaluacion: string;

    ngOnInit() {
        this.tituloListadoAsignarConTitulo(this.tituloListadoTipo.ESTANDAR, this, 'Pagina de pruebas', this.objetoBoton);
    }

    coreGuardar(): void {
    }

    coreExportar(tipo: string): void {
    }

    coreSalir(): void {
    }

    coreAccion(accion: string): void {
        console.log(accion);
    }

    coreMensaje(mensage: MensajeController) {
        console.log(mensage.resultado);
        if (mensage.componente == 'UBIGEO') {
            this.codigo = mensage.resultado.codigoelemento;
            this.nombreUbigeo = mensage.resultado.descripcion;
        }
        if (mensage.componente == 'PAIS') {
            this.codigo = mensage.resultado.codigo;
            this.nombrePais = mensage.resultado.descripcioncorta;
        }
        if (mensage.componente == 'CURSO') {
            this.codigo = mensage.resultado.curso;
            this.nombreCurso = mensage.resultado.descripcion;
        }
        if (mensage.componente == 'CENTROESTUDIO') {
            this.codigo = mensage.resultado.id;
            this.nombreCentroEstudio = mensage.resultado.nombre;
        }
        if (mensage.componente == 'CENTROCOSTOS') {
            this.codigo = mensage.resultado.costcenter;
            this.nombreCentroCosto = mensage.resultado.localname;
        }
        if (mensage.componente == 'COMPANIA') {
            this.codigoCompania = mensage.resultado.companyowner;
            this.nombreCompania = mensage.resultado.description;
        }
        if (mensage.componente == 'UNIDADNEGOCIO') {
            this.codigo = mensage.resultado.unidadnegocio;
            this.nombreUnidadnegocio = mensage.resultado.descripcionlocal;
        }
        if (mensage.componente == 'SUCURSAL') {
            this.codigo = mensage.resultado.unidadnegocio;
            this.nombreSucursal = mensage.resultado.descripcionlocal;
        }
        if (mensage.componente == 'PROYECTO') {
            this.codigo = mensage.resultado.afe;
            this.nombreProyecto = mensage.resultado.localname;
        }
        if (mensage.componente == 'UNIDADOPERATIVA') {
            this.codigo = mensage.resultado.unidadoperativa;
            this.nombreUnidadOperativa = mensage.resultado.descripcion;
        }
        if (mensage.componente == 'PUESTO') {
            this.codigo = mensage.resultado.codigopuesto;
            this.nombrePuesto = mensage.resultado.descripcion;
        }
        if (mensage.componente == 'ETAPAEVALUACION') {
            this.codigo = mensage.resultado.etapa;
            this.nombreEtapaEvaluacion = mensage.resultado.descripcion;
        }
    }

    mostrarSelectorUbigeo() {
        this.ubicacionGeograficaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UBIGEO', null));
    }
    mostrarSelectorPais() {
        this.paisSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PAIS', null));
    }
    mostrarSelectorCurso() {
        let filtro: FiltroComunHrCursodescripcion = new FiltroComunHrCursodescripcion();
        /*filtro.tipomaestro = 'C';*/
        let msj = new MensajeController(this, 'CURSO', null)
        if (!this.estaVacio(filtro.tipomaestro)) {
            msj.parametros = filtro;
        }

        this.cursoSelectorComponent.coreIniciarComponente(msj);
    }
    mostrarSelectorCentroEstudios() {
        this.hrCentroEstudiosSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROESTUDIO', null));
    }
    mostrarSelectorCentroCostos() {
        this.centroCostosComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CENTROCOSTOS', null));
    }
    mostrarSelectorCompania() {
        this.companiaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'COMPANIA', null));
    }
    mostrarSelectorUnidadNegocio() {
        this.unidadnegocioSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADNEGOCIO', null));
    }
    mostrarSelectorSucursal() {
        this.sucursalselectorComponent.coreIniciarComponente(new MensajeController(this, 'SUCURSAL', null));
    }
    mostrarSelectorProyecto() {
        this.afemstSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PROYECTO', null));
    }
    mostrarSelectorUnidadOperativa() {
        this.unidadoperativaSelectorComponent.coreIniciarComponente(new MensajeController(this, 'UNIDADOPERATIVA', null));
    }
    mostrarSelectorPuesto() {
        /*if (this.estaVacio(this.codigoCompania)) {
            this.mostrarMensajeAdvertencia("Debe especificar la Compañía")
            return;
        } */


        let msj = new MensajeController(this, 'PUESTO', null);
        let filtro: FiltroComunHrPuestoempresa = new FiltroComunHrPuestoempresa();

        filtro.companyowner = '01000000';
        filtro.unidadnegocio = 'D3F@'
        filtro.secuencia = 8;
        filtro.anio = 2022;
        filtro.evento = 'Browse';
        msj.parametros = filtro;

        this.puestoSelectorComponent.coreIniciarComponente(msj);
    }

    mostrarSelectorEtapaEvaluacion() {
        let msj = new MensajeController(this, 'ETAPAEVALUACION', null);

        let filtro: FiltroComunHrReqetapa = new FiltroComunHrReqetapa();
        filtro.companyowner = '01000000'; //this.codigoCompania
        msj.parametros = filtro;
        this.etapaevaluacionSelectorComponent.coreIniciarComponente(msj);
    }

}

