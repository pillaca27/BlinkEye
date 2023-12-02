import { WfTransaccionServicio } from './../servicio/wftransaccion.service';
import { DtoSeguimiento } from './../dominio/dto/DtoSeguimiento';
import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { TreeNode } from 'primeng/api';

@Component({
    templateUrl: './transaccion-seguimiento.component.html',
    selector: 'transaccion-seguimiento'
})
export class WfTransaccionSeguimientoComponent implements OnInit {

    constructor(
        private wfTransaccionServicio: WfTransaccionServicio,
    ) {

    }

    lstSeguimientoTree: TreeNode[] = [];
    lstSeguimiento: DtoSeguimiento[] = [];
    vistaArbol: boolean = true;

    ngOnInit() {

    }
    tooltipWF: string = '';

    cargando: boolean = true;

    iniciarComponenteLista(transaccionuuid: string) {
        this.iniciar(transaccionuuid, false);
    }

    iniciarComponente(transaccionuuid: string) {
        this.iniciar(transaccionuuid, true);
    }

    observacion: string = '';
    verObservacion: boolean = false;

    iniciar(transaccionUUID: string, vistaArbol: boolean) {

        this.vistaArbol = vistaArbol;

        if (transaccionUUID == null) {
            return;
        }
        this.cargando = true;

        var pTitle = this.wfTransaccionServicio.obtenerTitleWF(transaccionUUID).then(
            res => {
                this.tooltipWF = res.descripcion;
                this.observacion = res.nombre;

                if (this.observacion != null && this.observacion != undefined) {
                    this.verObservacion = true;
                    this.observacion = this.observacion.trim();
                }


            }
        );

        this.wfTransaccionServicio.listarSeguimientoGeneralWF(transaccionUUID).then(
            res => {
                var secuencia = 1;
                res.forEach(
                    x => {
                        x.secuencia = secuencia;
                        secuencia++;
                    }
                );
                this.lstSeguimiento = res;
                if (res.length > 0) {
                    var copia = [...res];
                    var inicio = { styleClass: copia[0].observacion, label: copia[0].usuario, children: [], expanded: true, expandedIcon: '', collapsedIcon: '', icon: '', key: copia[0].secuencia.toString() };
                    var actual = inicio;
                    var copia2 = [...copia];
                    copia2.shift();
                    copia2.forEach(
                        nivel => {
                            var temp = { styleClass: nivel.observacion, label: nivel.usuario, children: [], expanded: true, expandedIcon: '', collapsedIcon: '', icon: '', key: nivel.secuencia.toString() };
                            actual.children.push(temp);
                            actual = temp;
                        }
                    );
                    actual.expanded = false;
                    this.lstSeguimientoTree = [inicio];
                    this.cargando = false;
                }
            }
        )
    }

    obtenerSeguimientoDatos(secuencia: any) {
        var item = this.lstSeguimiento.find(x => x.secuencia.toString() == secuencia.toString());

        var datos = [];

        datos.push(item.usuario);
        datos.push(item.fecha);
        datos.push(item.actual);
        datos.push(item.observacion);

        var icono = "";
        var icono2 = "";
        var color = "";
        var leyenda = '';

        switch (item.estado) {
            case "S":
                icono = "fad fa-user-plus";
                icono2 = "fad fa-check-circle";
                color = "#6c757d";
                leyenda = "Registrado";
                break;
            case "A":
                icono = "fad fa-user-check";
                icono2 = "fad fa-check-circle";
                color = "#6c757d";
                leyenda = item.estadoDescripcion;//"Aprobado";
                break;
            case "R":
                icono = "fad fa-user-times";
                icono2 = "fad fa-times-circle";
                color = "#ff8888";
                leyenda = "Rechazado";
                break;
            case "D":
                icono = "fad fa-user-minus";
                icono2 = "fad fa-chevron-circle-left";
                color = "#495057";
                leyenda = "Devuelto";
                break;
            default:
                icono = "fad fa-ellipsis-h-alt";
                icono2 = "fad fa-circle";
                color = "#9eb5d2";
                if (item.estadoDescripcion == null) {
                    leyenda = "Pendiente";
                }
                else {
                    leyenda = item.estadoDescripcion;
                }
                break;
        }

        if (item.actual == 'S') {
            icono = "fad fa-user";
            icono2 = "fad fa-spinner-third";
            color = "#1aca8e";
            if (item.estadoDescripcion == null) {
                leyenda = "Pendiente";
            }
            else {
                leyenda = item.estadoDescripcion;
            }
        }
        datos.push(icono);
        datos.push(icono2);
        datos.push(color);
        datos.push(leyenda);

        datos.push(item.actividad);

        return datos;
    }
}
