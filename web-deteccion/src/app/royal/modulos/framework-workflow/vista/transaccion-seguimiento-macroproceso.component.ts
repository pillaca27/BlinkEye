import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { DtoJerarquiaMacroProceso } from './../dominio/dto/DtoJerarquiaMacroProceso';
import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MessageService, TreeNode } from 'primeng/api';
import { WfTransaccionSeguimientoComponent } from './transaccion-seguimiento.component';

@Component({
    templateUrl: './transaccion-seguimiento-macroproceso.component.html',
    selector: 'transaccion-seguimiento-macroproceso',
    styles: [
        `
        .p-tree-toggler.pi.pi-fw.pi-minus.ng-star-inserted {
            display: none;
          }
        `
    ]
})
export class WfTransaccionSeguimientoMacroProcesoComponent extends FormularioComponent implements OnInit {

    @ViewChild(WfTransaccionSeguimientoComponent, { static: false }) wfTransaccionSeguimientoComponent: WfTransaccionSeguimientoComponent;

    constructor(
        private wfTransaccionServicio: WfTransaccionServicio,
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    lstSeguimientoTree: TreeNode[] = [];
    lstSeguimiento: DtoJerarquiaMacroProceso[] = [];

    ngOnInit() {

    }

    cargando: boolean = true;

    iniciarComponente(transaccionUUID: string) {
        this.cargando = true;

        if (transaccionUUID == null) {
            return;
        }

        this.wfTransaccionServicio.listarJerarquiaMacroProceso(transaccionUUID).then(
            res => {
                this.cargando = false;
                this.lstSeguimiento = res;

                //armar arbol
                var nodos: TreeNode[] = [];
                this.lstSeguimiento.forEach(row => {
                    row.viendoSeguimiento = false;
                    nodos.push({ children: [], expanded: true, expandedIcon: '', collapsedIcon: '', icon: '', key: row.transaccion.toString(), data: row.padre });
                });

                //asignar hijos
                nodos.forEach(row => {
                    row.children = nodos.filter(x => (x.data as number) == parseInt(row.key as string));
                    if (row.children.length == 0) {
                        row.expanded = false;
                    }
                });

                //El arbol comienza por el nodo sin padre
                this.lstSeguimientoTree = nodos.filter(x => x.data == null);
            }
        )
    }

    obtenerSeguimientoDatos(transaccion: string) {
        var item = this.lstSeguimiento.find(x => x.transaccion.toString() == transaccion.toString());
        var datos = [];
        datos.push(item.dato1);
        datos.push(item.dato2);
        datos.push(item.dato3);
        datos.push(item.dato4);
        datos.push(item.dato5);
        datos.push(item.dato6);
        datos.push(item.dato7);
        return datos;
    }

    estaViendoSeguimiento(transaccion: string) {
        var item = this.lstSeguimiento.find(x => x.transaccion.toString() == transaccion.toString());
        if (item.viendoSeguimiento) {
            return '#e8a7a7';
        }
        return ''
    }

    verSeguimiento(transaccion: string) {

        //limpiar componente seguimiento
        this.wfTransaccionSeguimientoComponent.cargando = true;
        this.wfTransaccionSeguimientoComponent.lstSeguimiento = [];
        this.wfTransaccionSeguimientoComponent.lstSeguimientoTree = [];

        //si da click de nuevo, quitar la seleccion
        var previaSeleccionado = this.lstSeguimiento.find(x => x.viendoSeguimiento);
        if (previaSeleccionado) {
            var transaccionPrevia = previaSeleccionado.transaccion;
            if (transaccionPrevia.toString() == transaccion) {
                previaSeleccionado.viendoSeguimiento = false;
                return;
            }
        }

        //Reseteando
        this.lstSeguimiento.forEach(x => x.viendoSeguimiento = false);

        //Marcado como viendo
        var item = this.lstSeguimiento.find(x => x.transaccion.toString() == transaccion.toString());
        item.viendoSeguimiento = true;

        this.mostrarMensajeInfo("Cargando seguimiento");

        //Actualizando componente seguimiento
        this.wfTransaccionSeguimientoComponent.iniciarComponente(item.transaccionUUID);
    }
}
