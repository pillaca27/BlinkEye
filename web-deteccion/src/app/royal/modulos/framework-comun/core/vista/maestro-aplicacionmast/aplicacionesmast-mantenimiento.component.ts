import { SelectItem, MessageService, ConfirmationService } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { UIMantenimientoController } from '@framework/angular/interface/UIMantenimientoController';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { convertDateStringsToDates } from "@framework/angular/funciones/dateutils";
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { DtoComunAplicacionesmast } from '@framework-comun/sg/dominio/dto/DtoComunAplicacionesmast';
import { AplicacionesmastComunService } from '@framework-comun/sg/servicio/aplicacionesmast-comun.service';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
 

@Component({
    selector: 'app-aplicacionesmast-mantenimiento',
    templateUrl: 'aplicacionesmast-mantenimiento.component.html',
    styleUrls: ['./aplicacionesmast-mantenimiento.component.scss']
})
export class AplicacionesmastMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    lstEstadoDisponible: SelectItem[] = [];
    lstDepartamentos: SelectItem[] = [];
    lstSistemaFuente: SelectItem[] = [];
    blockSpecialAplicacion: RegExp = /^[a-zA-Z0-9]*$/;

    dto: DtoComunAplicacionesmast = new DtoComunAplicacionesmast();
    indicadorNuevo: boolean = false
    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        private aplicacionesmastService: AplicacionesmastComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        /*  const p1 = this.miscelaneosListar(this.comboEtiquetaTipo.MANTENIMIENTO,
             ConstanteComunSistema.APLICACION, ConstanteComunSistema.MISC_ESTADO_GENERICO, ConstanteComunSistema.DEFECTO_COMPANIA,
             this.lstEstados); */
        const p1 = this.listarDepartamentos()
        const p2 = this.listarsistemafuente()
        const p3 = this.listarEstados()
        const p4 = this.listarEstadosDisponibles()

        Promise.all([p1, p2, p3, p4]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.indicadorNuevo = true
                this.dto.estado = 'A'
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunAplicacionesmast);
                if (tempdto != undefined && tempdto != null) {
                    this.bloquearPagina();
                    this.aplicacionesmastService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;

                        console.log(resp);
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }

    coreGuardar(): void {
        this.validarPeriodo()

        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.aplicacionesmastService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.aplicacionesmastService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        }else if(this.accion == this.ACCIONES.ELIMINAR){
            this.confirmationService.confirm({
             header: 'Confirmación',icon: 'fa fa-question-circle',message: '¿Desea eliminar este registro?',
             accept: () => {
                 this.bloquearPagina();
                 this.messageService.clear();
                 this.aplicacionesmastService.eliminar(this.dto).then( res =>{
                     this.desbloquearPagina();                
                     if(this.transaccionResultado(res)){
                       this.coreSalir();
                     }
                 });
             },
             key: "confirm"
           });  
         } 
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_aplicacionesmast_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreMensaje(mensage: MensajeController): void { }

    coreAccion(accion: any): void { }

    listarDepartamentos(): Promise<number> {
        this.lstDepartamentos.push({ label: '--Seleccione--', value: null });
        return this.aplicacionesmastService.listardepartamentos().then(res => {
            res.forEach(ele => {
                this.lstDepartamentos.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }
    listarsistemafuente(): Promise<number> {
        this.lstSistemaFuente.push({ label: '--Seleccione--', value: null });
        return this.aplicacionesmastService.listarsistemafuente().then(res => {
            res.forEach(ele => {
                this.lstSistemaFuente.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }
    listarEstados() {
        this.lstEstados.push({ label: '--Seleccione--', value: null });
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    listarEstadosDisponibles() {
        this.lstEstadoDisponible.push({ label: '--Seleccione--', value: null });
        this.lstEstadoDisponible.push({ label: 'Si', value: 'S' });
        this.lstEstadoDisponible.push({ label: 'No', value: 'N' });
    }

    validarPeriodo() {
        this.validarTolowerCase()
        if(!this.estaVacio(this.dto.ultimoperiodocontable)){
            if (this.dto.ultimoperiodocontable.length == 6) {
                let anio = this.dto.ultimoperiodocontable.substr(0, 4)
                if (+anio >= 2000 && +anio <= 2050) {
                    let mes = this.dto.ultimoperiodocontable.substr(4, 2)
                    let mesInt = parseInt(mes)
                    this.dto.codigocontablevalid = mesInt < 13 && mesInt >= 1 ? "VALID" : "NO_VALID"
                } else {
                    this.dto.codigocontablevalid = "NO_VALID"
                }
            }
        }

    }

    validarTolowerCase(){
        if(!this.estaVacio(this.dto.aplicacioncodigo)){
            this.dto.aplicacioncodigo=this.dto.aplicacioncodigo.toUpperCase()
        }
        if(!this.estaVacio(this.dto.aplicacionusuario)){
            this.dto.aplicacionusuario=this.dto.aplicacionusuario.toUpperCase()
        }
        if(!this.estaVacio(this.dto.aplicacionusuario02)){
            this.dto.aplicacionusuario02=this.dto.aplicacionusuario02.toUpperCase()
        }
        if(!this.estaVacio(this.dto.aplicacionusuario03)){
            this.dto.aplicacionusuario03=this.dto.aplicacionusuario03.toUpperCase()
        }
        if(!this.estaVacio(this.dto.aplicacionusuario04)){
            this.dto.aplicacionusuario04=this.dto.aplicacionusuario04.toUpperCase()
        }
    }

    validarMayus(){
        if(!this.estaVacio(this.dto.aplicacioncodigo)){
            this.dto.aplicacioncodigo=this.dto.aplicacioncodigo.toUpperCase()
            console.log(this.dto.aplicacioncodigo.toUpperCase())
        }
    }
}
