import { Component, OnInit, ViewChild, ChangeDetectorRef } from '@angular/core';
import { Router, ActivatedRoute, Route } from '@angular/router';
import { ConfirmationService, MessageService, SelectItem } from 'primeng/api';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { ConstanteAutoservicios } from '../../ConstanteAutoservicios';
import { HrCapacitacionServicio } from '../servicio/HrCapacitacionServicio';
import { HrEjecucionevaluaciondesempenioServicio } from '../servicio/HrEjecucionevaluaciondesempenioServicio';
import { DtoEvaluacionComportamientoOpciones, DtoHrEvalempcompetencia, DtoHrEvalempcompetenciacomp, DtoHrEvalempevaluadoevaluacion, DtoHrEvalempfortaleza, DtoHrEvalempfuncion, DtoHrEvalempmejora, DtoHrEvalempnecesidad, DtoHrEvalempobjempleado } from '../dominio/dto/DtoHrEvalempevaluadoevaluacion';
import { DtoEvaluacionParticipante } from '../dominio/dto/DtoEvaluacionParticipante';
import { DtoEvaluacionDisponibleListado } from '../dominio/dto/DtoEvaluacionDisponibleListado';
import { CursoSelectorComponent } from '@framework-comun/hr/vista/curso-selector.component';

@Component({
    templateUrl: './evaluaciondesempenio-evaluacion.component.html'
})
export class EvaluacionDesempenioEvaluacionComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    dtoPadre: DtoEvaluacionDisponibleListado;
    dtoBean: DtoHrEvalempevaluadoevaluacion = new DtoHrEvalempevaluadoevaluacion();
    lstPrioridad: SelectItem[] = [];
    lstTipoFortalezas: SelectItem[] = [];
    lstCumplio: SelectItem[] = [];
    @ViewChild(CursoSelectorComponent, { static: false }) cursoSelectorComponent: CursoSelectorComponent;

    constructor(
        private hrEjecucionevaluaciondesempenioServicio: HrEjecucionevaluaciondesempenioServicio,
        private confirmationService: ConfirmationService,
        private router: Router,
        private route: ActivatedRoute,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
        this.dtoBean.auxFotoBase64 = 'assets/imagenes/usrDefault.jpg';
    }

    ngOnInit() {
        super.ngOnInit();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);
        this.dtoPadre = JSON.parse(this.route.snapshot.params['dtoPadre'] as string) as DtoEvaluacionDisponibleListado;

        this.lstCumplio.push({ value: 'S', label: 'SI' });
        this.lstCumplio.push({ value: 'N', label: 'NO' });

        this.lstPrioridad.push({ value: null, label: ' -- Seleccione -- ' });
        this.lstPrioridad.push({ value: 1, label: 'ALTA' });
        this.lstPrioridad.push({ value: 2, label: 'MEDIA' });
        this.lstPrioridad.push({ value: 3, label: 'BAJA' });

        this.lstTipoFortalezas.push({ value: null, label: ' -- Seleccione -- ' });
        var p = [];
        p.push(this.servicioComun.listaractivos('999999', 'HR', 'EDTIPFORTA')
            .then(respuesta => {
                respuesta.forEach(obj => this.lstTipoFortalezas.push({ label: obj.nombre, value: obj.codigo.trim() })); return 1;
            }));

        this.bloquearPagina();
        Promise.all(p).then(
            f => {
                this.hrEjecucionevaluaciondesempenioServicio.obtenerEvaluacion(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoEvaluacionParticipante).then(
                    res => {
                        if (res.auxFotoBase64 == null) {
                            res.auxFotoBase64 = 'assets/imagenes/usrDefault.jpg';
                        }
                        this.dtoBean = res;
                        this.desbloquearPagina();
                    }
                );
            });
    }

    coreGuardar(): void {
        this.bloquearPagina();
        this.hrEjecucionevaluaciondesempenioServicio.guardarEvaluacion(this.dtoBean).then(res => {
            this.desbloquearPagina();
            if (this.transaccionResultadoSimple(res)) {
                this.mostrarMensajeExito(this.getMensajeActualizadoSinCodigo());
                this.coreSalir();
            }
        });
    }
    terminarEvaluacion() {
        this.confirmationService.confirm({
            key: 'c',
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: 'Si termina la Evaluación, ya no podrá realizar cambios. ¿Desea continuar?',
            accept: () => {
                this.bloquearPagina();
                this.hrEjecucionevaluaciondesempenioServicio.terminarEvaluacion(this.dtoBean).then(res => {
                    this.desbloquearPagina();
                    if (this.transaccionResultadoSimple(res)) {
                        this.mostrarMensajeExito(this.getMensajeActualizadoSinCodigo());
                        this.coreSalir();
                    }
                });
            }
        });
    }
    coreSalir(): void {
        if (this.accion == this.ACCIONES.VER) {
            this.router.navigate([ConstanteAutoservicios.ruta_evaluaciondesempenio_participantes, this.ACCIONES.VER, JSON.stringify(this.dtoPadre)], { skipLocationChange: true });
        }
        else {
            this.router.navigate([ConstanteAutoservicios.ruta_evaluaciondesempenio_participantes, this.ACCIONES.EDITAR, JSON.stringify(this.dtoPadre)], { skipLocationChange: true });
        }
    }
    coreExportar(tipo: string): void {
    }
    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'CURSO-MEJORA') {
            var temp = [...this.dtoBean.listaNecesidades];
            const aux = temp.find(obj => obj.secuencia == this.secuenciaMejora);
            const index = temp.indexOf(aux);
            temp[index].curso = mensage.resultado.curso;
            temp[index].auxCursoNombre = mensage.resultado.descripcion;
            this.dtoBean.listaNecesidades = temp;
        }
    }
    coreAccion(accion: string): void {
    }
    agregarMejora() {
        var temp = [...this.dtoBean.listaMejoras];
        var nuevo = new DtoHrEvalempmejora();
        if (temp.length === 0) {
            nuevo.secuencia = 1;
        }
        else {
            let max = temp[0].secuencia;
            temp.forEach(reg => { if (reg.secuencia > max) { max = reg.secuencia; } });
            nuevo.secuencia = max + 1;
        }
        temp.push(nuevo);
        this.dtoBean.listaMejoras = temp;
    }
    estadoAccionMejora(row: DtoHrEvalempmejora) {
        if (row.flagcumplio == 'S') {
            return 'SI';
        }
        if (row.flagcumplio == 'N') {
            return 'NO';
        }
        return 'PENDIENTE';
    }
    eliminarMejora(row: DtoHrEvalempmejora) {
        this.confirmationService.confirm({
            key: 'c',
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                let lst = [...this.dtoBean.listaMejoras];
                const aux = lst.find(obj => obj.secuencia == row.secuencia);
                lst = lst.filter(x => x.secuencia !== aux.secuencia);
                this.dtoBean.listaMejoras = lst;
            }
        });
    }
    agregarNecesidad() {
        var temp = [...this.dtoBean.listaNecesidades];
        var nuevo = new DtoHrEvalempnecesidad();
        if (temp.length === 0) {
            nuevo.secuencia = 1;
        }
        else {
            let max = temp[0].secuencia;
            temp.forEach(reg => { if (reg.secuencia > max) { max = reg.secuencia; } });
            nuevo.secuencia = max + 1;
        }
        temp.push(nuevo);
        this.dtoBean.listaNecesidades = temp;
    }
    eliminarNecesidad(row: DtoHrEvalempnecesidad) {
        this.confirmationService.confirm({
            key: 'c',
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                let lst = [...this.dtoBean.listaNecesidades];
                const aux = lst.find(obj => obj.secuencia == row.secuencia);
                lst = lst.filter(x => x.secuencia !== aux.secuencia);
                this.dtoBean.listaNecesidades = lst;
            }
        });
    }

    secuenciaMejora: number;

    seleccionarCurso(row: DtoHrEvalempnecesidad) {
        this.secuenciaMejora = row.secuencia;
        this.cursoSelectorComponent.coreIniciarComponente(new MensajeController(this, 'CURSO-MEJORA', null));
    }

    agregarFoda() {
        var temp = [...this.dtoBean.listaFortalezas];
        var nuevo = new DtoHrEvalempfortaleza();
        if (temp.length === 0) {
            nuevo.secuencia = 1;
        }
        else {
            let max = temp[0].secuencia;
            temp.forEach(reg => { if (reg.secuencia > max) { max = reg.secuencia; } });
            nuevo.secuencia = max + 1;
        }
        temp.push(nuevo);
        this.dtoBean.listaFortalezas = temp;
    }
    eliminarFortaleza(row: DtoHrEvalempfortaleza) {
        this.confirmationService.confirm({
            key: 'c',
            header: 'Confirmación',
            icon: 'fa fa-question-circle',
            message: this.getMensajePreguntaEliminar(),
            accept: () => {
                let lst = [...this.dtoBean.listaFortalezas];
                const aux = lst.find(obj => obj.secuencia == row.secuencia);
                lst = lst.filter(x => x.secuencia !== aux.secuencia);
                this.dtoBean.listaFortalezas = lst;
            }
        });
    }
    resultadoObjEmpleado(row: any) {
        var c1 = row.calificacion;
        c1 = c1 == null || c1 == undefined ? 0 : c1;
        var c2 = row.peso;
        c2 = c2 == null || c2 == undefined ? 0 : c2;
        if (row.peso == 0) {
            return 0;
        }
        return c1 * c2 / 100;
    }
    sumarCumplimiento(lst: any[]) {
        var t = 0;
        lst.forEach(r => {
            t = t + r.calificacion;
        });
        return t;
    }
    sumarCumplimientoAvg(lst: any[]) {
        if (lst.length == 0) {
            return 0;
        }
        var t = 0;
        lst.forEach(r => {
            t = t + r.calificacion;
        });
        return t / lst.length;
    }
    sumarPeso(lst: any[]) {
        var t = 0;
        lst.forEach(r => {
            t = t + r.peso;
        });
        return t;
    }
    sumarResultado(lst: any[]) {
        var t = 0;
        lst.forEach(r => {
            t = t + this.resultadoObjEmpleado(r);
        });
        return t;
    }
    sumarPuntajeCompetencia() {
        var s = 0;
        this.dtoBean.listaCompetencias.forEach(x => {
            var ca = x.calificacion;
            ca = ca == null || ca == undefined ? 0 : ca;
            s = s + ca;
        });
        return s / this.dtoBean.listaCompetencias.length;
    }

    verModalCalificacionCompetencia: boolean = false;
    verModalCalificacionCompetenciaComportamiento: boolean = false;
    competenciaSecuencia: number
    competenciaNombre: string;
    competenciaCalificacion: number;
    competenciaExplicacion: string;
    competenciaComentario: string;
    competenciaSeleccionada: DtoHrEvalempcompetenciacomp = new DtoHrEvalempcompetenciacomp();
    listaComportamientos: DtoHrEvalempcompetenciacomp[] = [];
    listaOpciones: DtoEvaluacionComportamientoOpciones[] = [];
    tope1: number;

    evaluarCompetencia(row: DtoHrEvalempcompetencia) {
        this.competenciaSeleccionada = new DtoHrEvalempcompetenciacomp();
        this.competenciaSecuencia = row.secuencia;
        this.competenciaNombre = row.auxCompetenciaNombre;
        this.competenciaExplicacion = row.auxCompetenciaExplicacion;
        this.competenciaCalificacion = row.calificacion;
        this.competenciaComentario = row.comentario;

        if (this.dtoBean.auxFlagEvaluacionCompetencia == 'C') {
            this.listaComportamientos = [];
            row.listaComportamientos.forEach(r => {
                var c = new DtoHrEvalempcompetenciacomp();
                c.comportamientosecuencia = r.comportamientosecuencia;
                c.auxComportamientoNombre = r.auxComportamientoNombre;
                c.auxComportamientoExplicacion = r.auxComportamientoExplicacion;
                c.calificacion = r.calificacion == null || r.calificacion == undefined ? 0 : r.calificacion;
                c.listaOpciones = r.listaOpciones;
                this.listaComportamientos.push(c);
            });
            this.verModalCalificacionCompetenciaComportamiento = true;
        }
        else {
            this.tope1 = row.calificacionhasta;
            this.listaOpciones = row.listaOpciones;
            this.verModalCalificacionCompetencia = true;
        }
    }
    guardarCalificacionComportamiento() {
        var valida = true;
        var i = 1;
        this.listaComportamientos.forEach(x => {
            var maximo = 0;

            x.listaOpciones.forEach(r => {
                if (r.valorMaximo > maximo) {
                    maximo = r.valorMaximo;
                }
            });
            if (x.calificacion == null || x.calificacion == undefined || x.calificacion == 0) {
                this.mostrarMensajeAdvertencia('Fila ' + i + ': Debe especificar las calificación');
                valida = false;
            }
            else if (x.calificacion > maximo) {
                this.mostrarMensajeAdvertencia('Fila ' + i + ': El valor elegido está fuera del rango permitido');
                valida = false;
            }
            i++;
        });
        if (!valida) {
            return;
        }
        var temp = [...this.dtoBean.listaCompetencias];
        const aux = temp.find(obj => obj.secuencia == this.competenciaSecuencia);
        const index = temp.indexOf(aux);
        var vv = temp[index];
        vv.calificacion = this.competenciaCalificacion;
        vv.comentario = this.competenciaComentario;
        if (this.listaComportamientos.length > 0) {
            var s = 0;
            vv.listaComportamientos.forEach(x => {
                var d = this.listaComportamientos.find(y => y.comportamientosecuencia == x.comportamientosecuencia);
                x.calificacion = d.calificacion;
                s = s + x.calificacion;
            });
            vv.calificacion = s / vv.listaComportamientos.length;
        }
        this.dtoBean.listaCompetencias = temp;
        this.verModalCalificacionCompetenciaComportamiento = false;
    }
    guardarCalificacion() {
        if (this.competenciaCalificacion == null || this.competenciaCalificacion == undefined || this.competenciaCalificacion == 0) {
            this.mostrarMensajeAdvertencia('Debe especificar la calificación');
            return;
        }
        if (this.competenciaCalificacion > this.tope1) {
            this.mostrarMensajeAdvertencia('El valor elegido está fuera del rango permitido');
            return;
        }
        var temp = [...this.dtoBean.listaCompetencias];
        const aux = temp.find(obj => obj.secuencia == this.competenciaSecuencia);
        const index = temp.indexOf(aux);
        var vv = temp[index];
        vv.calificacion = this.competenciaCalificacion;
        vv.comentario = this.competenciaComentario;
        this.dtoBean.listaCompetencias = temp;
        this.verModalCalificacionCompetencia = false;
    }
    seleccionarValor(row: DtoEvaluacionComportamientoOpciones) {
        this.competenciaSeleccionada.calificacion = row.valorMaximo;
        this.verModalCalificacionValores = false;
    }
    seleccionarValorCompetencia(row: DtoEvaluacionComportamientoOpciones) {
        this.competenciaCalificacion = row.valorMaximo;
    }

    verModalCalificacionValores: boolean = false;

    competenciaCalificacionSumada() {
        if (this.listaComportamientos.length == 0) {
            return 0;
        }
        var t = 0;
        this.listaComportamientos.forEach(x => {
            t = t + x.calificacion;
        });
        return t / this.listaComportamientos.length;
    }

    verValores(row: DtoHrEvalempcompetenciacomp) {
        this.competenciaSeleccionada = row;
        this.verModalCalificacionValores = true;
    }
}
