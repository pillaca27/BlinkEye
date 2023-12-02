import { BotonesListadoComponent } from './botones/botones-listado.component';
import { BotonesMantenimientoComponent } from './botones/botones-mantenimiento.component';
import { SafePipeComp } from '@framework/angular/tipetransform/SafePipeComp';
import { LabelFromItemComp } from '@framework/angular/tipetransform/LabelFromItemComp';
import { DialogAyuda } from './selectores/paginaayuda/vista/dialog-ayuda';
import { TipoExportarSelectorComponent } from './selectores/tipoExportar/vista/tipoExportar.component';
import { BlockUIModule } from 'primeng/blockui';
import { TableModule } from 'primeng/table';
import { InputMaskModule } from 'primeng/inputmask';
import { DropdownModule } from 'primeng/dropdown';
import { RadioButtonModule } from 'primeng/radiobutton';
import { PanelModule } from 'primeng/panel';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DialogModule } from 'primeng/dialog';
import { TituloListadoComponent } from './titulos/titulo-listado.component';
import { FooterMantenimientoComponent } from './footer/footer-mantenimiento.component';
import { TituloMantenimientoComponent } from './titulos/titulo-mantenimiento.component';
import { NgModule } from '@angular/core';
import { AuditoriaComponent } from './auditoria/auditoria.component';                     
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { TreeModule } from 'primeng/tree';
import { ChartModule } from 'primeng/chart';
import { CardModule } from 'primeng/card';
import { TabViewModule } from 'primeng/tabview';
import { FieldsetModule } from 'primeng/fieldset';
import { CalendarModule } from 'primeng/calendar';
import { CheckboxModule } from 'primeng/checkbox';
import { SpinnerModule } from 'primeng/spinner';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ButtonModule } from 'primeng/button';
import { SplitButtonModule } from 'primeng/splitbutton';
import { DividerModule } from 'primeng/divider';
import { CurrencyFormatPipe } from '../tipetransform/CurrencyFormatPipe';
import { MyCurrencyPipe } from '../tipetransform/MyCurrencyPipe';
import { ToolbarModule } from 'primeng/toolbar';

@NgModule({
    declarations: [
        TituloListadoComponent,
        TituloMantenimientoComponent,
        AuditoriaComponent,
        FooterMantenimientoComponent,
        BotonesMantenimientoComponent,
        BotonesListadoComponent,
        
        DialogAyuda,
        TipoExportarSelectorComponent,

        LabelFromItemComp,
        SafePipeComp,
        CurrencyFormatPipe,
        MyCurrencyPipe

    ],

    imports: [

        CommonModule,
        FormsModule,
        DialogModule,
        InputTextareaModule,
        PanelModule,
        RadioButtonModule,
        DropdownModule, InputMaskModule, ButtonModule,
        TableModule, BlockUIModule,
        InputTextModule, SpinnerModule, CalendarModule, CheckboxModule,
        ConfirmDialogModule, TabViewModule, FieldsetModule,
        TreeModule, ChartModule, CardModule,
    
        SplitButtonModule,
        DividerModule,
        ToolbarModule

    ],


    exports: [
        TituloListadoComponent,
        TituloMantenimientoComponent,
        AuditoriaComponent,
        FooterMantenimientoComponent,
        BotonesMantenimientoComponent,
        BotonesListadoComponent,
        DialogAyuda,
        TipoExportarSelectorComponent,  
        LabelFromItemComp,
        SafePipeComp,
        CurrencyFormatPipe,
        MyCurrencyPipe,
    ],

    providers: []

})
export class ControlesModule { }