import { CommonModule, DatePipe } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ComunModule } from '@framework-comun/Comun.module';
import { ControlesModule } from '@framework/angular/controles/controles.module';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { AccordionModule } from 'primeng/accordion';
import { BlockUIModule } from 'primeng/blockui';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CardModule } from 'primeng/card';
import { CheckboxModule } from 'primeng/checkbox';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { DialogModule } from 'primeng/dialog';
import { DividerModule } from 'primeng/divider';
import { DropdownModule } from 'primeng/dropdown';
import { FieldsetModule } from 'primeng/fieldset';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextModule } from 'primeng/inputtext';
import { KeyFilterModule } from 'primeng/keyfilter';
import { MenuModule } from 'primeng/menu';
import { MultiSelectModule } from 'primeng/multiselect';
import { PanelModule } from 'primeng/panel';
import { PasswordModule } from 'primeng/password';
import { RadioButtonModule } from 'primeng/radiobutton';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { TableModule } from 'primeng/table';
import { TabViewModule } from 'primeng/tabview';
import { ToastModule } from 'primeng/toast';
import { TooltipModule } from 'primeng/tooltip';
import { TreeModule } from 'primeng/tree';
import { TreeTableModule } from 'primeng/treetable';
import { AsAutorizacionService } from './servicio/as_autorizacion.servicio';
import { AsAutorizacionListadoComponent } from './vista/asautorizaciones/as_autorizacion-listado.component';
import { AsAutorizacionMantenimientoComponent } from './vista/asautorizaciones/as_autorizacion-mantenimiento.component';
import { ReporteMarcaComponent } from './vista/reporte-marcas/reporte-marca.component';
import { AsAccesosdiariosService } from './servicio/asaccesosdiarios.service';
import { PdfJsViewerModule } from 'ng2-pdfjs-viewer';
import { ReporteAsistenciaComponent } from './vista/reporte-asistencia/reporte-asistencia.component';
import { ReporteAsitenciaDetallaComponent } from './vista/reporte-asistenciadetallada/reporte-asistenciadetalla.component';
import { AsMarcacionComponent } from './vista/registro-marcacion/asmarcacion.component';
import { FiltrosFechasComponent } from './vista/filtros-fechas/uo_filtro-fechas.component';
import { WorkflowModule } from '@framework-workflow/workflow.module';

@NgModule({
    imports: [
        PdfJsViewerModule,
        TooltipModule,
        CommonModule,
        FormsModule,
        CheckboxModule,
        DialogModule,
        BlockUIModule,
        TableModule,
        InputTextModule,
        DropdownModule,
        ButtonModule,
        PanelModule,
        AccordionModule,
        TreeModule,
        ScrollPanelModule,
        CalendarModule,
        ConfirmDialogModule,
        FieldsetModule,
        MenuModule,
        ControlesModule,
        InputNumberModule,
        CardModule,
        AngularEditorModule,
        ToastModule,
        TabViewModule,
        DividerModule,
        RadioButtonModule,
        KeyFilterModule,
        TreeTableModule,
        MultiSelectModule,
        PasswordModule,
        ComunModule,
        WorkflowModule
    ],
    exports: [],
    declarations: [
        AsAutorizacionListadoComponent,
        AsAutorizacionMantenimientoComponent,
        ReporteMarcaComponent,
        ReporteAsistenciaComponent,
        ReporteAsitenciaDetallaComponent,
        AsMarcacionComponent,
        FiltrosFechasComponent
    ],
    providers: [
        AsAutorizacionService,
        AsAccesosdiariosService,
        DatePipe
    ],
})
export class AsAutorizacionModule { }
