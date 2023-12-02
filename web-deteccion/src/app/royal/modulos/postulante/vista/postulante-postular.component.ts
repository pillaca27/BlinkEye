import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, LazyLoadEvent, SelectItem } from 'primeng/api';
import { MessageService } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { ConstantePostulante } from "../ConstantePostulante";
import { HrPostulanteService } from '../servicio/hrpostulante.service';
import { DtoHrPostulante } from '../dominio/DtoHrPostulante';
import { DtoHrPostulantereferencia } from '../dominio/DtoHrPostulantereferencia';
import { FiltroPostulantePostular } from '../dominio/filtro/FiltroPostulantePostular';
import { DtoPostulanteRequerimientoPostularListado } from '../dominio/dto/DtoPostulanteRequerimientoPostularListado';

@Component({
    templateUrl: './postulante-postular.component.html'
})
export class PostulantePostularComponent extends FormularioComponent implements OnInit {

    @ViewChild('vPerfil', { static: false }) vPerfil;

    constructor(
        private hrPostulanteService: HrPostulanteService,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService, servicioComun); }


    filtro: FiltroPostulantePostular = new FiltroPostulantePostular();
    verRequerimientos: boolean = true;
    verPerfil: boolean = false;

    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.tituloListadoAsignar(this.tituloListadoTipo.ESTANDAR, this, this.objetoBoton);
        this.buscar();
    }

    registroSeleccionado: any;
    lista: any[] = [];
    perfil: DtoPostulanteRequerimientoPostularListado = new DtoPostulanteRequerimientoPostularListado();

    buscar() {
        this.bloquearPagina();
        this.hrPostulanteService.requerimientosPostular(this.filtro).then(
            res => {
                this.lista = res;
                this.desbloquearPagina();
            }
        );
    }

    salir() {
        this.verRequerimientos = true;
        this.verPerfil = false;
    }

    postular(row: DtoPostulanteRequerimientoPostularListado) {
        this.perfil = row;
        this.verRequerimientos = false;
        this.verPerfil = true;
        this.bloquearPagina();
        this.hrPostulanteService.perfilRequerimiento(row).then(res => {
            this.desbloquearPagina();
            this.vPerfil.pdfSrc = res.reporte;
            this.vPerfil.refresh();
        });
    }

    registrarPostulacion() {
        this.bloquearPagina();
        this.hrPostulanteService.registrarPostulacion(this.perfil).then(res => {
            this.desbloquearPagina();
            if (this.transaccionResultadoSimple(res)) {
                this.mostrarMensajeExito("Ha postulado correctamente al requerimiento " + this.perfil.requerimiento);
                this.verRequerimientos = true;
                this.verPerfil = false;
                this.lista = [];
                this.registroSeleccionado = null;
                this.buscar();
            }
        });
    }
}
