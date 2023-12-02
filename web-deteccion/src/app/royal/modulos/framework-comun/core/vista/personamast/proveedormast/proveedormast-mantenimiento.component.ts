import { ConstanteComun } from '@framework-comun/ConstanteComun';
import { PersonamastComunService } from './../../../servicio/personamast-comun.service';
import { SelectItem, MessageService } from 'primeng/api';
import { Component, OnInit, ViewChild } from '@angular/core';
import { AuditoriaComponent } from '@framework/angular/controles/auditoria/auditoria.component';
import { BotonesMantenimientoComponent } from '@framework/angular/controles/botones/botones-mantenimiento.component';
import { FormularioComponent } from '@framework/angular/component/FormularioComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { MensajeController } from '@framework/angular/dominio/MensajeController';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
 
 
import { ActivatedRoute, Router } from '@angular/router';
import { convertDateStringsToDates } from '@framework/angular/funciones/dateutils';
 
import { MaPersonagrupoComunService } from '@framework-comun/core/servicio/mapersonagrupo-comun.service';
import { DtoComunProveedormast } from '@framework-comun/core/dominio/dto/DtoComunProveedormast';
import { ProveedormastComunService } from '@framework-comun/core/servicio/proveedormast-comun.service';
import { DtoComunPersonamast } from '@framework-comun/core/dominio/dto/DtoComunPersonamast';

@Component({
  selector: 'app-proveedormast-mantenimiento',
  templateUrl: 'proveedormast-mantenimiento.component.html'
})
export class ProveedormastMantenimientoComponent extends FormularioComponent implements OnInit {

  @ViewChild(AuditoriaComponent, { static: false }) auditoriaComponent: AuditoriaComponent;
  @ViewChild(BotonesMantenimientoComponent, { static: false }) botonesComponent: BotonesMantenimientoComponent;

  lstEstados: SelectItem[] = [];
  lstMonedas: SelectItem[] = []
  lstTipoServicio: SelectItem[] = []
  lstFormaPago: SelectItem[] = []
  lstTipoDocumento: SelectItem[] = []
  lstTipoPago: SelectItem[] = []
  lstDestracciones: SelectItem[] = []
  lstImpuestos: SelectItem[] = []

  dto: DtoComunProveedormast = new DtoComunProveedormast();
  checheNo: boolean = false
  retenerPago: boolean = false
  checExonerado: boolean = false
  checkDetraccion: boolean = false

  constructor(
    messageService: MessageService,
    private comunServive: MaPersonagrupoComunService,
    private proveedormastService: ProveedormastComunService,
    private personamastService: PersonamastComunService,
    servicioComun: ServicioComunService,
    private route: ActivatedRoute,
    private router: Router,
    noAuthorizationInterceptor: NoAuthorizationInterceptor
  ) {
    super(noAuthorizationInterceptor, messageService, servicioComun);
  }

  ngOnInit() {
    this.cargarSelect()
  }

  iniciarComponenteProveedor(dto: any, objetoTitulo: any,accion:any) {
    this.bloquearPagina();
    this.objetoTitulo = objetoTitulo
    this.accion=accion
    let tempdtoPer = convertDateStringsToDates(JSON.parse(this.route.snapshot.params['dto'] as string) as DtoComunPersonamast);
    let dtoTemp = new DtoComunProveedormast();
    dtoTemp.proveedor = tempdtoPer.persona


    const p1 = this.listarMonedas();
    const p2 = this.listarTipoServicio();
    const p3 = this.listarFormaPago();
    const p4 = this.listarParametrosDocumento();
    const p5 = this.listarParametrosTipoPago()
    const p6 = this.listarTipoDocumento();
    const p7 = this.listarDetracciones();
    const p8 = this.listarTipoPago();

    Promise.all([p1, p2, p3, p4, p5, p6, p7,p8]).then(resp => {
      this.desbloquearPagina();
      this.dto.usuarioActual = dto.usuarioActual
      if (tempdtoPer.esproveedor == "N") {
        this.dto.proveedor = dto.persona
        this.dto.monedapago = 'LO'
        this.dto.accion = "N"
        this.dto.suspensionfonaviflag = 'N'
      
       

      } else {
        var tempdto = dtoTemp
        if (tempdto != undefined && tempdto != null) {
          this.bloquearPagina();
          this.proveedormastService.obtenerDto(tempdto).then(resp => {
            this.dto = resp;
            this.dto.accion = "A"
            this.validarChecksInit()
            this.desbloquearPagina();
          });
        }
      }
    });
  }

  cargarSelect() {
    this.lstMonedas.push({ label: 'Todos', value: null });
    this.lstTipoServicio.push({ label: 'Todos', value: null });
    this.lstFormaPago.push({ label: 'Todos', value: null });
    this.lstDestracciones.push({ label: 'Todos', value: null });
    this.lstImpuestos.push({ label: 'Todos', value: null });
  }

  parametrosSelect() {
    this.lstTipoDocumento.push({ label: 'Todos', value: null });
  }

  listarMonedas(): Promise<number> {
    return this.comunServive.listarMonedas().then(res => {
      res.forEach(ele => {
        this.lstMonedas.push({ label: ele.codigo.trim() + " " + ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarTipoServicio(): Promise<number> {
    return this.comunServive.listarTipoServicio().then(res => {
      res.forEach(ele => {
        this.lstTipoServicio.push({ label: ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarFormaPago(): Promise<number> {
    return this.comunServive.listarFormaPago().then(res => {
      res.forEach(ele => {
        this.lstFormaPago.push({ label: ele.nombre.concat("-").concat(ele.descripcion.trim()), value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarTipoDocumento(): Promise<number> {
    return this.comunServive.listarTipoDocumento().then(res => {
      res.forEach(ele => {
        this.lstTipoDocumento.push({ label: ele.codigo.trim() + " " + ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarTipoPago(): Promise<number> {
    return this.comunServive.listarTipoPago().then(res => {
      res.forEach(ele => {
        this.lstTipoPago.push({ label: ele.codigo.trim() + "  " + ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarDetracciones(): Promise<number> {
    return this.comunServive.listarDetracciones().then(res => {
      res.forEach(ele => {
        this.lstDestracciones.push({ label: ele.codigo.trim() + " " + ele.nombre, value: ele.codigo.trim() });
      });
      return 1;
    });
  }

  listarParametrosDocumento(): Promise<number> {
    return this.personamastService.parametroobtener('999999','AP','DOCDEFAULT').then(
        res=> {
          if(res){
            this.lstTipoDocumento.push({ label: res.descripcionparametro, value: res.texto.trim() });
            this.dto.tipodocumentodefault=res.texto.trim()
          }else{
            this.lstTipoDocumento.push({ label: 'Default (999999)', value: 'AP' });
            this.dto.tipodocumentodefault='AP'
          }
            return 1
        }
    )  
}

listarParametrosTipoPago(): Promise<number> {
  return this.personamastService.parametroobtener('999999','AP','TIPOPAGODF').then(
      res=> {
        if(res){
          this.lstTipoPago.push({ label: res.descripcionparametro, value: res.texto.trim() });
          this.dto.tipopago=res.texto.trim()
        }else{
          this.lstTipoPago.push({ label: 'Default (999999)', value: 'AP' });
          this.dto.tipopago='AP'
        }
          return 1
      }
  )  
}

  validarChecks() {
    if (this.checheNo) {
      this.dto.chequenonegociableflag = 'S'
    }else{
      this.dto.chequenonegociableflag = ''
    }
    if (this.retenerPago) {
      this.dto.retenerpagosflag = 'S'
    }
    else{
      this.dto.retenerpagosflag = ''
    }
    if (this.checExonerado) {
      this.dto.exoneradomediopresentacion = 'S'
    }
    else{
      this.dto.exoneradomediopresentacion = ''
    }
    if (this.checkDetraccion) {
      this.dto.detraccioncodigoflag = 'S'
    }else{
      this.dto.detraccioncodigoflag = ''
    }

  }


  validarChecksInit() {
    if (this.dto.chequenonegociableflag == 'S') {
      this.checheNo = true
    }
    if (this.dto.retenerpagosflag == 'S') {
      this.retenerPago = true
    }
    if (this.dto.exoneradomediopresentacion == 'S') {
      this.checExonerado = true
    }
    if (this.dto.detraccioncodigoflag == 'S') {
      this.checkDetraccion = true
    }

  }

  coreGuardar() {
    this.validarChecks()
    if (this.dto.accion == "N") {
      this.bloquearPagina();
      this.dto.tipoproveedor='N'
      this.proveedormastService.registrar(this.dto).then(res => {
        this.desbloquearPagina();
        if (this.transaccionResultado(res)) {
          this.coreSalir();
        }
      }
      );
    } else if (this.dto.accion == "A") {
      this.bloquearPagina();
      this.proveedormastService.actualizar(this.dto).then(res => {
        this.desbloquearPagina();
        if (this.transaccionResultado(res)) {
          this.coreSalir();
        }
      }
      );
    }
  }

  coreEliminar(){
    this.bloquearPagina();
    this.proveedormastService.eliminar(this.dto).then(res => {
      this.desbloquearPagina();
      if (this.transaccionResultado(res)) {
        this.coreSalir();
      }
    }
    );
  }

  coreSalir(): void {
    this.router.navigate([ConstanteComun.ruta_personamast_listado], { skipLocationChange: true })
  }


  coreExportar(tipo: string): void { }

  coreMensaje(mensage: MensajeController): void { }

  coreAccion(accion: any): void { }

  observacionesKey(){

    if(!this.estaVacio(this.dto.comentarioservicio)){
      this.dto.comentariousuario=this.getUsuarioActual().usuario
      this.dto.comentariofecha=new Date
    }
  }
}
