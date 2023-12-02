import { CommonModule, HashLocationStrategy, LocationStrategy } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { ControlesModule } from "@framework/angular/controles/controles.module";
import { AngularEditorModule } from "@kolkov/angular-editor";
import { NgxJsonViewerModule } from "ngx-json-viewer";
import { AccordionModule } from "primeng/accordion";
import { BlockUIModule } from "primeng/blockui";
import { ButtonModule } from "primeng/button";
import { CalendarModule } from "primeng/calendar";
import { CardModule } from "primeng/card";
import { CheckboxModule } from "primeng/checkbox";
import { ConfirmDialogModule } from "primeng/confirmdialog";
import { DialogModule } from "primeng/dialog";
import { DividerModule } from "primeng/divider";
import { DropdownModule } from "primeng/dropdown";
import { FieldsetModule } from "primeng/fieldset";
import { InputNumberModule } from "primeng/inputnumber";
import { InputTextModule } from "primeng/inputtext";
import { KeyFilterModule } from "primeng/keyfilter";
import { MenuModule } from "primeng/menu";
import { MultiSelectModule } from "primeng/multiselect";
import { PanelModule } from "primeng/panel";
import { PasswordModule } from "primeng/password";
import { RadioButtonModule } from "primeng/radiobutton";
import { ScrollPanelModule } from "primeng/scrollpanel";
import { TableModule } from "primeng/table";
import { TabViewModule } from "primeng/tabview";
import { ToastModule } from "primeng/toast";
import { TreeModule } from "primeng/tree";
import { TreeTableModule } from "primeng/treetable";
import { InputMaskModule } from 'primeng/inputmask';
import { SpinnerModule } from 'primeng/spinner';
import { PdfJsViewerModule } from "ng2-pdfjs-viewer";

import { DashboardComponent } from './db/dashboard.componente';
import { ReporteDeteccionComponent } from './dt/vista/reporte-deteccion/reporte-deteccion.component';
import { ConsultaClaseComponent } from './dt/vista/consulta-clase/consulta-clase.component';
import { ConsultaAlumnoComponent } from './dt/vista/consulta-alumno/consulta-alumno.component';

import { AlumnosService } from "./dt/servicio/alumnos.servicio";
import { ClasesService } from "./dt/servicio/clases.servicio";
import { DocentesService } from "./dt/servicio/docentes.servicio";
import { MicrosuenosService } from "./dt/servicio/microsuenos.servicio";

// import { AlumnosListadoComponent } from './dt/vista/alumnos-listado.component';
// import { AlumnosMantenimientoComponent } from './dt/vista/alumnos-mantenimiento.component';

@NgModule({
    declarations: [
        //USAR
        DashboardComponent,
        ReporteDeteccionComponent,
        ConsultaClaseComponent,
        ConsultaAlumnoComponent
        // AlumnosListadoComponent,
        // AlumnosMantenimientoComponent
    ],
    imports: [
        NgxJsonViewerModule,
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
        InputMaskModule, SpinnerModule,PdfJsViewerModule
    ],
    exports: [

    ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },

        //usar
        MicrosuenosService,
        ClasesService,
        AlumnosService,
        DocentesService

    ]
})
export class AutoserviciosModule { }