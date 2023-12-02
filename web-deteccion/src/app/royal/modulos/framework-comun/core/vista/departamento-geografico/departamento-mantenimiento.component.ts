import { ConstanteAngular } from './../../../../../framework/angular/ConstanteAngular';
import { ConstanteComun } from '@framework-comun/ConstanteComun';
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


import { DepartamentoComunService } from '@framework-comun/core/servicio/departamento-comun.service';
import { DtoComunDepartamento } from '@framework-comun/core/dominio/dto/DtoComunDepartamento';
import { DtoComunProvincia } from '@framework-comun/core/dominio/dto/DtoComunProvincia';
import { DtoComunZonapostal } from '@framework-comun/core/dominio/dto/DtoComunZonapostal';
import { PaisComunService } from '@framework-comun/core/servicio/pais-comun.service';


@Component({
    selector: 'app-departamento-mantenimiento',
    templateUrl: 'departamento-mantenimiento.component.html',
    styles: [
        `::host :ng-deep .p-datatable .p-datatable-scrollable-header, .p-datatable .p-datatable-scrollable-footer {
            background: transparent;
        }`
    ]
})
export class DepartamentoMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

    lstEstados: SelectItem[] = [];
    lstPaises: SelectItem[] = [];
    dto: DtoComunDepartamento = new DtoComunDepartamento();
    accionnuevo: boolean = false
    blockSpecial: RegExp = /^[a-zA-Z ´áéíóú]*$/
    blockSpecialNumero: RegExp = /^[0-9]*$/;
    provincia: boolean = false
    provinciaCod: string = ''
    provinciaDescri: string = ''
    index: number = 0;


    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private confirmationService: ConfirmationService,
        private departamentoService: DepartamentoComunService,
        private paisComunService: PaisComunService,
        servicioComun: ServicioComunService,
        noAuthorizationInterceptor: NoAuthorizationInterceptor
    ) {
        super(noAuthorizationInterceptor, messageService, servicioComun);
    }

    ngOnInit() {
        this.bloquearPagina();
        this.formularioIniciar(this.route);
        this.tituloMantenimientoAsignar(this.tituloMantenimientoTipo.ESTANDAR, this, this.accion, this.objetoBoton);

        const p1 = this.listarEstados()
        const p2 = this.cargarPaises()
        Promise.all([p1, p2]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A'
                this.dto.pais = 'PER';
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER || this.accion == this.ACCIONES.ELIMINAR) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunDepartamento);
                if (tempdto != undefined && tempdto != null) {
                    this.provincia = tempdto.accion == "DISTR" ? true : false
                    this.bloquearPagina();
                    this.departamentoService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.dto.accion = tempdto.accion
                        this.dto.provincia = tempdto.pais
                        this.provinciaCod = tempdto.pais
                        this.provinciaDescri = tempdto.descripcionlarga
                        this.accionnuevo = true
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }


    onTabChange(event) {
        this.index = event.index;
    }

    limpiarDep() {
        if (this.estaVacio(this.dto.departamento)) {
            this.dto.departamento = null
        }
    }

    coreGuardar(): void {
        if (this.validarDetalleGuardado()) {
            return
        }
        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.departamentoService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.departamentoService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.ELIMINAR) {
            this.confirmationService.confirm({
                header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
                accept: () => {
                    this.bloquearPagina();
                    this.departamentoService.eliminar(this.dto).then(res => {
                        this.desbloquearPagina();
                        if (this.transaccionResultado(res)) {
                            this.coreSalir();
                        }
                    }
                    );
                },
                key: "confirm",
            });
        }
    }

    coreSalir(): void {
        this.router.navigate([ConstanteComun.ruta_departamento_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreMensaje(mensage: MensajeController): void { }

    coreAccion(accion: any): void { }

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    cargarPaises(): Promise<number> {
        this.lstPaises.push({ label: ConstanteAngular.COMBOSELECCIONE, value: null });
        return this.paisComunService.listarActivos().then(resp => {
            resp.forEach(obj => { this.lstPaises.push({ label: obj.nombre, value: obj.codigo }); })
            return 1;
        })
    }

    agregarDetalleProvincia() {
        let provincia = new DtoComunProvincia();
        provincia.pais = this.dto.pais;
        provincia.accion = 'N'
        provincia.departamento = this.dto.departamento
        provincia.provincia = null
        provincia.estado = 'A'
        this.dto.provincias.push(provincia)
    }

    coreEliminarProvincias(dto: any, index: number) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? " + "<br />" +
                "Departamento: " + dto.departamento + "<br />" +
                "Provincia: " + dto.descripcioncorta + "",
            accept: () => {
                if (this.accion == this.ACCIONES.NUEVO) {
                    this.dto.provincias.splice(index, 1);
                } else if (this.accion == this.ACCIONES.EDITAR) {
                    dto.accion = "E"
                    dto.zonaPostal.forEach(distritos => {
                        distritos.accion = "E"
                    });
                    //this.deleteProvincia=true
                }
            },
            key: "confirm",
        });
    }

    agregarDetalleDistrito() {

        let distrito = new DtoComunZonapostal()
        distrito.accion = 'N'
        distrito.departamento = this.dto.departamento
        distrito.provincia = this.dto.provincia
        distrito.estado = 'A'
        this.dto.distritos.push(distrito)
    }

    coreEliminarDistritos(dto: any, index: number) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                if (this.accion == this.ACCIONES.NUEVO) {
                    this.dto.distritos.splice(index, 1);
                } else if (this.accion == this.ACCIONES.EDITAR) {
                    dto.accion = "E"
                }
            },
            key: "confirm",
        });
    }


    valida: boolean = false

    validarP(row: any) {
        if (!this.esListaVacia(this.dto.provincias)) {
            let array = this.dto.provincias.filter(x => x.accion != "E")
            let data = array.map(x => x.provincia).filter(x => !this.estaVacio(x))
            const result = data.reduce((acc, item) => {
                if (!acc.includes(item)) {
                    acc.push(item);
                }
                return acc;
            }, [])

            if (data.length != result.length) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La provincia ya existe en el registro." });
                this.valida = true
            } else {
                this.valida = false
            }
        } else {
            this.valida = false
        }
    }

    validarBlurProvincia(provincia: any) {
        if (!this.esListaVacia(this.dto.distritos)) {
            let array = this.dto.distritos.filter(x => x.accion != "E")
            let data = array.map(x => x.codigopostal).filter(x => !this.estaVacio(x))
            const result = data.reduce((acc, item) => {
                if (!acc.includes(item)) {
                    acc.push(item);
                }
                return acc;
            }, [])

            if (data.length != result.length) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El distrito ya existe en el registro." });
                this.valida = true
            } else {
                this.valida = false
            }
        } else {
            this.valida = false
        }
    }

    validarVaciosProvincia() {
        let resultado = false
        if (!this.esListaVacia(this.dto.provincias)) {
            let array = this.dto.provincias.filter(x => x.accion != "E")
            let cod = array.map(x => x.provincia).filter(x => this.estaVacio(x))
            let descri = array.map(x => x.descripcioncorta).filter(x => this.estaVacio(x))

            if (cod.length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La provincia no debe estar vació." });
                resultado = true
            }
            if (descri.length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La descripción de la provincia no debe estar vació." });
                resultado = true
            }

            return resultado
        }
    }

    validarVaciosDistrito(distritos: any) {
        let resultado = false
        if (!this.esListaVacia(distritos.zonaPostal)) {
            let array = distritos.zonaPostal.filter(x => x.accion != "E")
            let cod = array.map(x => x.codigopostal).filter(x => this.estaVacio(x))
            let descri = array.map(x => x.descripcioncorta).filter(x => this.estaVacio(x))


            if (cod.length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El código postal no debe estar vació." });
                resultado = true
            }
            if (descri.length > 0) {
                this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La descripción del distrito no debe estar vació." });
                resultado = true
            }

            return resultado
        }
    }

    validarDetalleGuardado(): boolean {
        let resultado = false

        if (this.provincia) {
            if (this.validarVaciosDistrito(this.dto.distritos)) {
                resultado = true
            }
        } else {
            if (this.validarVaciosProvincia()) {
                resultado = true
            }
        }
        return resultado
    }
}
