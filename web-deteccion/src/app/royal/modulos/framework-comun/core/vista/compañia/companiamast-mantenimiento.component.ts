import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { DtoComunCompanyowner } from '@framework-comun/core/dominio/dto/DtoComunCompanyowner';
import { MaPersonagrupoComunService } from './../../servicio/mapersonagrupo-comun.service';
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
 
import { PersonaComunSelectorComponent } from '@framework-comun/core/vista/personacomunselector.component';
 
import { SeguridadUsuarioLogin } from '@framework/modelo/seguridad/SeguridadUsuarioLogin';
import { DtoComunCompaniamast } from '@framework-comun/core/dominio/dto/DtoComunCompaniamast';
import { CompaniamastComunService } from '@framework-comun/core/servicio/companiamast-comun.service';
//import { LoginProveedorService } from 'src/app/royal/modulos/proveedor/login-proveedor/servicio/loginProveedor.service';
import { DtoComunReportingcompany } from '@framework-comun/core/dominio/dto/DtoComunReportingcompany';
import { LoginService } from '@framework-seguridad/servicio/autorizacion/seguridad/login.service';
 

@Component({
    selector: 'app-companiamast-mantenimiento',
    templateUrl: 'companiamast-mantenimiento.component.html',
    styles: [`:host ::ng-deep .p-button.p-button-icon-only {
        width: 2.357rem;
        padding: 0.2rem 0;
    }
    :host ::ng-deep .p-datatable-scrollable-body {
      padding-right: 0px;
  }
  .tablaColumnaIzquierda2{
    text-align: left !important;
    width: 100px;
  }
  .tablaColumnaCodigoChico2 {
    text-align: right !important;
    width: 80px;
  }`]
})
export class CompaniamastMantenimientoComponent extends FormularioComponent implements OnInit, UIMantenimientoController {

    @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
    @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;
    @ViewChild(PersonaComunSelectorComponent, { static: false }) personaComunSelectorComponent: PersonaComunSelectorComponent;

    lstEstados: SelectItem[] = [];
    lstTipoCompania: SelectItem[] = []
    lstMonedas: SelectItem[] = []
    lstCompanias: SelectItem[] = []
    lstCompanias2: SelectItem[] = []

    dto: DtoComunCompaniamast = new DtoComunCompaniamast();
    accionnuevo: boolean = false
    blockSpecial: RegExp = /^[a-zA-Z0-9]*$/;
    blockSpecialNumero: RegExp = /^[0-9]*$/;
    uploadedFiles: any[] = [];
    logoAnterior:string=''

    constructor(
        messageService: MessageService,
        private router: Router,
        private route: ActivatedRoute,
        private companiamastService: CompaniamastComunService,
        private confirmationService: ConfirmationService,
        private companiasService: LoginService,
        private comunServive: MaPersonagrupoComunService,
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
        const p2 = this.listarTipoCompañia()
        const p3 = this.listarMonedas()
        const p4 = this.listarCompanias()

        Promise.all([p1, p2, p3, p4]).then(resp => {
            this.desbloquearPagina();
            if (this.accion == this.ACCIONES.NUEVO) {
                this.dto.estado = 'A'
                this.dto.tipocompania = 'P'
                this.dto.monedapordefecto = 'LO'
                this.dto.propietariopordefecto = '00'
                this.dto.afectoigvflag = 'S'
                this.dto.check1 = true
            } else if (this.accion == this.ACCIONES.EDITAR || this.accion == this.ACCIONES.VER
                || this.accion == this.ACCIONES.ELIMINAR) {
                var tempdto = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunCompaniamast);
                if (tempdto != undefined && tempdto != null) {
                    this.logoAnterior=tempdto
                    this.bloquearPagina();
                    this.companiamastService.obtenerDto(tempdto).then(resp => {
                        this.dto = resp;
                        this.logoAnterior= this.dto.logofile 
                        this.accionnuevo = true
                        this.validarCheck()
                        this.desbloquearPagina();
                    });
                }
            }
        });
    }

    validarCheck() {

        if (this.dto.afectoigvflag == "S") {
            this.dto.check1 = true
        }
        if (this.dto.creditofiscalfactorflag == "S") {
            this.dto.check2 = true
        }
        if (this.dto.factorrvalidacion == "S") {
            this.dto.check3 = true
        }
        if (this.dto.cuentaprovisionsbsflag == "S") {
            this.dto.check4 = true
        }
        if (this.dto.afectoretencionigvflag == "S") {
            this.dto.check5 = true
        }
        this.flagCompaniaReportera=this.dto.tipocompania=="R" ? true :false
    }

    validarChecksGuardar() {
        this.dto.afectoigvflag = this.dto.check1 ? "S" : "N"
        this.dto.creditofiscalfactorflag = this.dto.check2 ? "S" : "N"
        this.dto.factorrvalidacion = this.dto.check3 ? "S" : "N"
        this.dto.cuentaprovisionsbsflag = this.dto.check4 ? "S" : "N"
        this.dto.afectoretencionigvflag = this.dto.check5 ? "S" : "N"
    }

    flagCompaniaReportera:boolean=false
    tipoCompaniaChange(){

        if(this.dto.tipocompania=="R"){
            let detalle1 = new DtoComunCompanyowner()
            detalle1.accion = 'N'
            detalle1.owner='00'
            detalle1.percentage=100
            detalle1.description = this.dto.descripcioncorta
            this.dto.detalle1.push(detalle1)
            this.flagCompaniaReportera=true
        }else{
            this.dto.detalle1=[]
            this.flagCompaniaReportera=false
        }
    }


    cargarLogoCompania(){
        if(this.file != null) {
            this.companiamastService.subirLogo(this.file,this.logoAnterior ).subscribe(
                response => {
                    console.log(response)
                }
            )
        }
    }

    eliminarLogoCompania(){
        if(!this.estaVacio(this.logoAnterior)) {
            this.companiamastService.delete_upload(this.logoAnterior).subscribe(
                response => {
                    console.log(response)
                }
            )
        }
    }

    coreGuardar(): void {
        this.validarChecksGuardar()
        if (this.validarDetalles()) {
            return
        }
        if (this.accion == this.ACCIONES.NUEVO) {
            this.bloquearPagina();
            this.companiamastService.registrar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) { 
                    this.cargarLogoCompania()                 
                    this.coreSalir();
                }
            }
            );
        } else if (this.accion == this.ACCIONES.EDITAR) {
            this.bloquearPagina();
            this.companiamastService.actualizar(this.dto).then(res => {
                this.desbloquearPagina();
                if (this.transaccionResultado(res)) {
                    this.cargarLogoCompania()
                    this.coreSalir();
                }
            }
            );
        }
        else if (this.accion == this.ACCIONES.ELIMINAR) {
            this.confirmationService.confirm({
                header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
                accept: () => {
                    this.bloquearPagina();
                    this.companiamastService.eliminar(this.dto).then(res => {
                        this.desbloquearPagina();
                        if (this.transaccionResultado(res)) {
                            this.eliminarLogoCompania()
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
        this.router.navigate([ConstanteComun.ruta_companiamast_listado], { skipLocationChange: true })
    }

    coreExportar(tipo: string): void { }

    coreMensaje(mensage: MensajeController): void {
        if (mensage.componente == 'PRCLOT') {
            this.dto.persona = mensage.resultado.persona
            this.dto.personaDescri = mensage.resultado.persona + " - " + mensage.resultado.busqueda;
        }
    }

    coreAccion(accion: any): void { }

    mostrarPersona() {
        this.personaComunSelectorComponent.coreIniciarComponente(new MensajeController(this, 'PRCLOT', 'PRCLOT'));
    }

    limpiar() {
        this.dto.personaDescri = ''
        this.dto.persona = null
    }

    listarMonedas(): Promise<number> {
        return this.comunServive.listarMonedas().then(res => {
            res.forEach(ele => {
                this.lstMonedas.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    listarEstados() {
        this.lstEstados.push({ label: 'Inactivo', value: 'I' });
        this.lstEstados.push({ label: 'Activo', value: 'A' });
    }

    listarTipoCompañia() {
        this.lstTipoCompania.push({ label: 'Principal', value: 'P' });
        this.lstTipoCompania.push({ label: 'Reporteadora ', value: 'R' });
    }

    listarCompanias(): Promise<number> {
        this.lstCompanias.push({ label: 'Todos', value: null });
        let usuario = new SeguridadUsuarioLogin()
        usuario.usuario = this.getUsuarioActual().usuario;
        return this.companiasService.listarcompaniasporusuario(usuario).then(res => {
            res.forEach(ele => {
                this.lstCompanias.push({ label: ele.codigo.trim(), value: ele.codigo.trim() });
                this.lstCompanias2.push({ label: ele.nombre, value: ele.codigo.trim() });
            });
            return 1;
        });
    }

    public file: File = null;
    cargarArchivo(event: any) {
        this.bloquearPagina();
        const files = event.files;
        return this.companiamastService.logo_compania(files[0].name).then(res => {
            if(!this.esListaVacia(res)){
                this.desbloquearPagina();
                this.mostrarMensajeAdvertencia("El logo ya se encuentra registrado.")
                return
            }
           
            this.file =    event.files[0];
            if (files.length !== 1) {
                this.desbloquearPagina();
                return;
            }
            const filePath = files[0].name;
            const reader = new FileReader();
            reader.readAsDataURL(files[0]);
            reader.onloadend = (evt) => {
                const result = reader.result as string;
                this.dto.logofile = filePath;
                this.desbloquearPagina();
            };
        });
    }

    

    agregarDetalle1() {
        let detalle1 = new DtoComunCompanyowner()
        detalle1.accion = 'N'
        detalle1.owner = this.esListaVacia(this.dto.detalle1.filter(x => x.accion != "E")) ? '00' : ''
        this.dto.detalle1.push(detalle1)
    }

    agregarDetalle2() {
        let detalle2 = new DtoComunReportingcompany()
        detalle2.accion = 'N'
        this.dto.detalle2.push(detalle2)
    }

    coreEliminarDetalle1(dto: any, index: number) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                if (dto.accion == "N") {
                    this.dto.detalle1.splice(index, 1);
                } else if (dto.accion == "A") {
                    dto.accion = "E"
                }
            },
            key: "confirm",
        });
    }

    coreEliminarDetalle2(dto: any, index: number) {
        this.confirmationService.confirm({
            header: "Confirmación", icon: "fa fa-question-circle", message: "¿Desea eliminar este registro ? ",
            accept: () => {
                if (dto.accion == "N") {
                    this.dto.detalle2.splice(index, 1);
                } else if (dto.accion == "A") {
                    dto.accion = "E"
                }
            },
            key: "confirm",
        });
    }

    changueCompania(dto: any) {
        let data = this.lstCompanias2.filter(x => x.value == dto.companiacodigo)
        if (!this.esListaVacia(data)) {
            dto.companiacodigoDescri = data[0].label
        }

    }

    validarDetalles(): boolean {
        let resultado = false
        if (this.dto.tipocompania == 'P') {
            let arrayVacio = this.dto.detalle1.filter(x => x.accion != "E")
            if (!this.esListaVacia(arrayVacio)) {
                let data = arrayVacio.map(x => x.owner)
                let descripcion = arrayVacio.map(x => x.description)
                let porcentaje = arrayVacio.map(x => this.esNumeroVacio(x.percentage) ? 0 : +x.percentage).filter(x => !this.esNumeroVacio(x))

                if (data.filter(x => this.estaVacio(x)).length > 0) {
                    this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El socio no debe estar vació." });
                    resultado = true
                }else{
                    const result = data.reduce((acc, item) => {
                        if (!acc.includes(item)) {
                            acc.push(item);
                        }
                        return acc;
                    }, [])
    
                    if (result.length != arrayVacio.length) {
                        this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "El socio no debe estar duplicado." });
                        resultado = true
                    }
                }
                if (descripcion.filter(x => this.estaVacio(x)).length > 0) {
                    this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La descripción del socio no debe estar vació." });
                    resultado = true
                }

                let total = porcentaje.reduce((a, b) => a + b, 0);
                if (total != 100) {
                    this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La suma de los % debe ser 100." });
                    resultado = true
                }


                return resultado
            }
            else {
                if(this.accion != this.ACCIONES.ELIMINAR){
                    this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "Por lo menos debe haber un socio y la suma de los % debe ser 100." });
                return resultado = true
                }
                
            }
        } else {
            let arrayVacio = this.dto.detalle1.filter(x => x.accion != "E")
            let arrayVacio2 = this.dto.detalle2.filter(x => x.accion != "E")
            if (!this.esListaVacia(arrayVacio)) {
                
                if (arrayVacio.length != 1) {
                    this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "Debe solo haber un solo socio = 00." });
                    resultado = true
                }

                let data = arrayVacio2.map(x => x.companyowner)
                if (data.filter(x => this.estaVacio(x)).length > 0) {
                    this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La Compañia socio no debe estar vació." });
                    resultado = true
                }else{
                    const result = data.reduce((acc, item) => {
                        if (!acc.includes(item)) {
                            acc.push(item);
                        }
                        return acc;
                    }, [])
    
                    if (result.length != arrayVacio2.length) {
                        this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "La compañia socio no debe estar duplicado." });
                        resultado = true
                    }
                }
                if (this.esListaVacia(arrayVacio2)) {
                    this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "Por lo menos debe haber un participante." });
                    resultado = true
                }


                return resultado
            } else {
                if(this.accion != this.ACCIONES.ELIMINAR){
                    this.messageService.add({ severity: 'warn', summary: 'Mensaje', detail: "Debe solo haber un solo socio = 00." });
                    return resultado = true
                }

            }
        }

    }
}
