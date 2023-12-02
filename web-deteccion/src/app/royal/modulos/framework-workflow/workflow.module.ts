import { WfAplicacionesmastComunService } from './servicio/wfaplicacionesmast-comun.service';
import { WfSyReportearchivoComunService } from './servicio/wfsyreportearchivo-comun.service';
import { WfSyReporteDetalleMantenimientoComponent } from './vista/wf-syreporte-detalle-mantenimiento.component';
import { WfPersonamastComunService } from './servicio/wfpersonamast-comun.service';
import { WfPersonaComunSelectorComponent } from './vista/wf-personacomunselector.component';
import { TreeModule } from 'primeng/tree';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { WfTransaccionComunicacionServicio } from './servicio/wftransaccioncomunicacion.service';
import { WfTransaccionNotificacionMasivaComponent } from './vista/transaccion-notificacion-masiva.component';
import { WfTransaccionChatComponent } from './vista/transaccion-chat.component';
import { WfTransaccionDocumentosXUsuarioComponent } from './vista/transaccion-documentosxusuario.component';
import { WfTransaccionPlanificacionComponent } from './vista/transaccion-planificacion.component';
import { CanvasComponent } from './vista/canvas.component';
import { FirmaComponent } from './vista/firma.component';
import { WfTransaccionSeguimientoMacroProcesoComponent } from './vista/transaccion-seguimiento-macroproceso.component';
import { WfTransaccionSeguimientoComponent } from './vista/transaccion-seguimiento.component';
import { WfTransaccionBotonesComponent } from './vista/transaccion-botones.component';
import { WfTransaccionListadoCorreoComponent } from './vista/transaccion-listado-correos.component';
import { WfMacroProcesoDetalleMantenimientoComponent } from './vista/wfmacroprocesodetalle-mantenimiento.component';
import { WfMacroProcesoDetalleListadoComponent } from './vista/wfmacroprocesodetalle-listado.component';
import { WfMacroProcesoMantenimientoComponent } from './vista/wfmacroproceso-mantenimiento.component';
import { WfMacroProcesoListadoComponent } from './vista/wfmacroproceso-listado.component';
import { WfMacroProcesoDetalleService } from './servicio/wfmacroprocesodetalle.servicio';
import { WfMacroProcesoService } from './servicio/wfmacroproceso.servicio';
import { NgModule } from '@angular/core';
import { CommonModule, LocationStrategy, HashLocationStrategy } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TableModule, Table } from 'primeng/table';
import { PanelModule } from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { BlockUIModule } from 'primeng/blockui';
import { SpinnerModule } from 'primeng/spinner';
import { CalendarModule } from 'primeng/calendar';
import { CheckboxModule } from 'primeng/checkbox';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { TabViewModule } from 'primeng/tabview';
import { FieldsetModule } from 'primeng/fieldset';
import { DialogModule } from 'primeng/dialog';
import { FullCalendarModule } from 'primeng/fullcalendar';
import { RadioButtonModule } from 'primeng/radiobutton';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { ToastModule } from 'primeng/toast';

import { WfProcesoListadoComponent } from './vista/proceso-listado.component';
import { ProcesoMantenimientoComponent } from './vista/proceso-mantenimiento.component';
import { AprobacionListadoComponent } from './vista/aprobacion-listado.component';
import { WfTransaccionListadoComponent } from './vista/transaccion-listado.component';
import { WfReemplazoListadoComponent } from './vista/reemplazo-listado.component';

import { WfProcesoServicio } from './servicio/wfproceso.service';
import { WfTransaccionServicio } from './servicio/wftransaccion.service';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { WfReemplazoServicio } from './servicio/WfReemplazoServicio';
import { InputNumber, InputNumberModule } from 'primeng/inputnumber';
import { CardModule } from 'primeng/card';
import { WfTransaccionMantenimientoComponent } from './vista/transaccion-mantenimiento.component';
import { DividerModule } from 'primeng/divider';
import { AprobacionFormularioComponent } from './vista/aprobacion-formulario.component';
import { WfTransaccionListadoAdministradorComponent } from './vista/transaccion-listado-administrador.component';
import { WfTransaccionVistaAvanzadaComponent } from './vista/transaccion-vista-avanzada.component';
import { ControlesModule } from '@framework/angular/controles/controles.module';
import { SolicitudRenovacioncontratoMantenimientoComponent } from './vista/solicitud-renovacioncontrato-mantenimiento.component';
import { MaMiscelaneosdetalleComunService } from '@framework-comun/core/servicio/mamiscelaneosdetalle-comun.service';
import { HrRenovacioncontratoServicio } from '../autoservicios/hr/servicio/HrRenovacioncontratoServicio';

@NgModule({
    declarations: [
        WfProcesoListadoComponent,
        ProcesoMantenimientoComponent,
        AprobacionListadoComponent,
        WfTransaccionListadoComponent,
        WfReemplazoListadoComponent,
        WfTransaccionMantenimientoComponent,
        AprobacionFormularioComponent,
        WfTransaccionListadoAdministradorComponent,
        WfTransaccionVistaAvanzadaComponent,
        WfMacroProcesoListadoComponent,
        WfMacroProcesoMantenimientoComponent,
        WfMacroProcesoDetalleListadoComponent,
        WfMacroProcesoDetalleMantenimientoComponent,
        WfTransaccionListadoCorreoComponent,

        //ESTABAN EN COMUN
        WfTransaccionBotonesComponent,
        WfTransaccionSeguimientoComponent,
        WfTransaccionSeguimientoMacroProcesoComponent,
        FirmaComponent,
        CanvasComponent,
        WfTransaccionPlanificacionComponent,
        WfTransaccionDocumentosXUsuarioComponent,
        WfTransaccionChatComponent,
        WfTransaccionNotificacionMasivaComponent,
        WfPersonaComunSelectorComponent,
        WfSyReporteDetalleMantenimientoComponent,
        SolicitudRenovacioncontratoMantenimientoComponent

    ],

    imports: [
        CommonModule,
        FormsModule,
        ToastModule,
        TableModule, PanelModule, BlockUIModule,
        InputTextModule, ButtonModule, DropdownModule,
        SpinnerModule, CalendarModule, CheckboxModule,
        ConfirmDialogModule, TabViewModule, FieldsetModule,
        DialogModule,
        TableModule,
        RadioButtonModule,
        FullCalendarModule,
        ToggleButtonModule,
        ToggleButtonModule,
        AutoCompleteModule,
        InputNumberModule,
        CardModule,
        ScrollPanelModule,
        AngularEditorModule,
        TreeModule,
        ControlesModule,
        DividerModule    
    ],


    exports: [
        //ESTABAN EN COMUN
        WfTransaccionBotonesComponent,
        WfTransaccionSeguimientoComponent,
        WfTransaccionSeguimientoMacroProcesoComponent,
        FirmaComponent,
        CanvasComponent,
        WfTransaccionPlanificacionComponent,
        WfTransaccionDocumentosXUsuarioComponent,
        WfTransaccionChatComponent,
        WfTransaccionNotificacionMasivaComponent,
        SolicitudRenovacioncontratoMantenimientoComponent
    ],

    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        ConfirmationService,
        WfProcesoServicio,
        WfTransaccionServicio,
        WfReemplazoServicio,
        WfMacroProcesoService,
        WfMacroProcesoDetalleService,
        //SyReporteComunService,
        //ESTABAN EN COMUN
        WfTransaccionComunicacionServicio,
        WfPersonamastComunService,
        WfSyReportearchivoComunService,
        WfAplicacionesmastComunService,
        MaMiscelaneosdetalleComunService,
        HrRenovacioncontratoServicio,

    ],
})
export class WorkflowModule { }