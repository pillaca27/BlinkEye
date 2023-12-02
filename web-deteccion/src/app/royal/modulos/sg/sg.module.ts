import { SeguridadconceptoMantenimientoComponent } from './vista/concepto/seguridadconcepto-mantenimiento.component';
import { SeguridadconceptoListadoComponent } from './vista/concepto/seguridadconcepto-listado.component';
import { SeguridadgrupoService } from './servicio/seguridadgrupo.servicio';
import { SeguridadconceptoService } from './servicio/seguridadconcepto.servicio';
import { SeguridadgrupoMantenimientoComponent } from './vista/grupo/seguridadgrupo-mantenimiento.component';
import { SeguridadgrupoListadoComponent } from './vista/grupo/seguridadgrupo-listado.component';
import { PasswordModule } from 'primeng/password';
import { SeguridadConceptoComponent } from './vista/seguridad-concepto.component';
import { SySeguridadconceptoService } from './servicio/syseguridadconcepto.servicio';
import { SyPreferencesService } from './servicio/sypreferences.servicio';
import { ComunModule } from '@framework-comun/Comun.module';
import { ConcetoSeguridadMaestroServicio } from './servicio/ConcetoSeguridadMaestroServicio';
import { KeyFilterModule } from 'primeng/keyfilter';
import { SySeguridadgrupoService } from './servicio/syseguridadgrupo.servicio';
import { SySeguridadgrupoMantenimientoComponent } from './vista/syseguridadgrupo-mantenimiento.component';
import { SySeguridadgrupoListadoComponent } from './vista/syseguridadgrupo-listado.component';
import { SyPreferencesMantenimientoComponent } from './vista/sypreferences-mantenimiento.component';
import { SyPreferencesListadoComponent } from './vista/sypreferences-listado.component';
import { SeguridadAutorizacionesComponent } from './vista/seguridad-autorizaciones.component';
import { UsuarioMaestroServicio } from './servicio/UsuarioMaestroServicio';
import { UsuariomaestroListadoComponent } from './vista/usuariomaestro-listado.component';
import { MaestroperfilComponent } from './vista/maestroperfil.component';
import { TreeModule } from 'primeng/tree';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { ControlesModule } from '@framework/angular/controles/controles.module';
import { BotonesMantenimientoComponent } from './../../framework/angular/controles/botones/botones-mantenimiento.component';
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

import { AutoCompleteModule } from 'primeng/autocomplete';
import { InputNumber, InputNumberModule } from 'primeng/inputnumber';
import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';

@NgModule({
    declarations: [        
        MaestroperfilComponent,
        UsuariomaestroListadoComponent,

        SeguridadAutorizacionesComponent,
        SyPreferencesListadoComponent,
        SyPreferencesMantenimientoComponent,
        SySeguridadgrupoListadoComponent,
        SySeguridadgrupoMantenimientoComponent,
        SeguridadConceptoComponent,
        SeguridadgrupoListadoComponent,
        SeguridadgrupoMantenimientoComponent,
        SeguridadconceptoListadoComponent,
        SeguridadconceptoMantenimientoComponent
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
        // ComunModule,
        ControlesModule,
        DividerModule, 
        KeyFilterModule,
        PasswordModule,
        ComunModule     

    ],


    exports: [
                
    ],

    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        ConfirmationService,
        
        UsuarioMaestroServicio,  
        SySeguridadgrupoService,
        ConcetoSeguridadMaestroServicio,
        SyPreferencesService,
        SySeguridadconceptoService,
        SeguridadgrupoService,
        SeguridadconceptoService


    ],
})
export class SgModule { }