import { MensajeController } from './../../../framework/angular/dominio/MensajeController';
import { ConstanteWorkflow } from './../ConstanteWorkflow';
import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { BaseComponent } from './../../../framework/angular/component/BaseComponent';
import { DtoFlujoAdjunto } from './../dominio/dto/DtoFlujoTransaccionRequest';
import { DtoFlujoSolicitud } from './../dominio/dto/DtoFlujoSolicitud';
import { DtoAprobacionAccion, DtoAprobacionAcciones } from './../dominio/dto/DtoAprobacionAcciones';
import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { FirmaComponent } from './firma.component';
import { DominioMensajeUsuario } from 'src/app/royal/framework/modelo/generico/DominioMensajeUsuario';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';

@Component({
    templateUrl: './transaccion-documentosxusuario.component.html',
    selector: 'transaccion-documentosxusuario'
})
export class WfTransaccionDocumentosXUsuarioComponent extends PrincipalBaseComponent implements OnInit {

    constructor(
        private wfTransaccionServicio: WfTransaccionServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,
        servicioComun: ServicioComunService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {

    }

    cargando: boolean = true;
    transaccionUUID: string;
    adjuntos: DtoFlujoAdjunto[] = [];
    tipoDocs: SelectItem[] = [];
    tipoDocsRaw: DtoTabla[] = [];
    tipoDocsNivelRaw: DtoTabla[] = [];
    tipoDocsNivel: SelectItem[] = [];


    iniciarComponente(transaccionUUID: string) {

        this.cargando = true;
        this.transaccionUUID = transaccionUUID;

        if (transaccionUUID == null) {
            return;
        }

        var p = [];
        this.tipoDocs = [];

        var p2 = this.wfTransaccionServicio.sytipodocumento().then(
            docs => {
                this.tipoDocsRaw = docs;
                this.tipoDocs.push({ value: null, label: ' -- Seleccione --' });
                docs.forEach(y => {
                    this.tipoDocs.push({ value: y.codigo.trim(), label: y.descripcion });
                });
            }
        );
        p.push(p2);
        var p3 = this.wfTransaccionServicio.obtenerAdjuntosSoloLectura(transaccionUUID).then(
            x => {
                x.forEach(doc => doc.transaccionUUID = this.transaccionUUID);
                this.adjuntos = x;
            }
        )
        p.push(p3);

        Promise.all(p).then(
            x => {
                this.cargando = false;
            }
        )
    }

    descargarDocumento(secuencia: number) {
        if (secuencia == null || secuencia == undefined) {
            this.mostrarMensajeAdvertencia("El adjunto está pendiente");
            return;
        }
        const doc = this.adjuntos.find(x => x.secuencia == secuencia);
        if (doc === null) {
            return;
        }

        this.bloquearPagina();
        doc.transaccionUUID = this.transaccionUUID;
        this.wfTransaccionServicio.verAdjunto(doc).then(
            yy => {
                this.desbloquearPagina();
                if (yy.archivostring == null) {
                    this.mostrarMensajeAdvertencia("No se encontró el archivo");
                    return;
                }
                const a = document.createElement('a');
                a.href = 'data:application/octet-stream;base64,' + yy.archivostring;
                a.setAttribute('download',this.reemplazarRuta( yy.archivonombre, ',', '_'));
                const b = document.createEvent('MouseEvents');
                b.initEvent('click', false, true);
                a.dispatchEvent(b);
            }
        );

    }

    verPlantilla(plantilla) {
        this.bloquearPagina();
        var dtoFlujoAdjunto = new DtoFlujoAdjunto();
        dtoFlujoAdjunto.transaccionUUID = this.transaccionUUID;
        dtoFlujoAdjunto.archivonombre = plantilla;
        this.wfTransaccionServicio.verPlantilla(dtoFlujoAdjunto).then(
            yy => {
                this.desbloquearPagina();
                if (yy.archivostring == null) {
                    this.mostrarMensajeAdvertencia("No se encontró el archivo");
                    return;
                }
                const a = document.createElement('a');
                a.href = 'data:application/octet-stream;base64,' + yy.archivostring;
                a.setAttribute('download',this.reemplazarRuta( yy.archivonombre, ',', '_'));
                const b = document.createEvent('MouseEvents');
                b.initEvent('click', false, true);
                a.dispatchEvent(b);
            }
        );
    }

    tieneLink(tipo: string) {
        var tipoDto = this.tipoDocsRaw.find(x => x.codigo.trim() == tipo);
        if (tipoDto) {
            if (!this.estaVacio(tipoDto.estadoId)) {
                return true;
            }
            return false;
        }
        return false;
    }

    irLink(tipo: string) {
        var tipoDto = this.tipoDocsRaw.find(x => x.codigo.trim() == tipo);
        if (tipoDto) {
            if (!this.estaVacio(tipoDto.estadoId)) {
                window.open(tipoDto.estadoId, '_blank').focus();
            }
        }
    }

}
