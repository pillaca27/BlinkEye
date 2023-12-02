import { CommonModule, HashLocationStrategy, LocationStrategy } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
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
import { ComunModule } from "@framework-comun/Comun.module";
import { ControlesModule } from "@framework/angular/controles/controles.module";

import { PostulanteDatosPersonalesComponent } from "./vista/postulante-datospersonales.component";
import { PostulanteDireccionesComponent } from "./vista/postulante-direcciones.component";
import { PostulanteInstruccionBasicaComponent } from "./vista/postulante-instruccionbasica.component";
import { PostulanteInstruccionSuperiorComponent } from "./vista/postulante-instruccionsuperior.component";
import { PostulanteCursosComponent } from "./vista/postulante-cursos.component";
import { PostulanteIdiomasComponent } from "./vista/postulante-idiomas.component";
import { PostulanteInformaticaComponent } from "./vista/postulante-informatica.component";
import { PostulanteExperienciaComponent } from "./vista/postulante-experiencia.component";
import { PostulanteReferenciaComponent } from "./vista/postulante-referencia.component";
import { PostulanteDocumentosComponent } from "./vista/postulante-documentos.component";
import { PostulanteDependientesComponent } from "./vista/postulante-dependientes.component";
import { PostulanteLoginComponent } from "./vista/postulante-login.component";
import { HrPostulanteService } from "./servicio/hrpostulante.service";
import { PostulanteUbicacionGeograficaSelectorComponent } from "./vista/postulante-ubicaciongeografica-selector.component";
import { HrActualizacionfichaempleadoServicio } from "../autoservicios/hr/servicio/HrActualizacionfichaempleadoServicio";
import { PostulantePostularComponent } from "./vista/postulante-postular.component";
import { PostulanteReqAsocComponent } from "./vista/postulante-reqasoc.component";
import { PostulanteRequerimientoAprobadoSelectorComponent } from "./vista/postulante-requerimientoaprobado-selector.component";

@NgModule({
    declarations: [
        PostulanteDatosPersonalesComponent,
        PostulanteDireccionesComponent,
        PostulanteInstruccionBasicaComponent,
        PostulanteInstruccionSuperiorComponent,
        PostulanteCursosComponent,
        PostulanteIdiomasComponent,
        PostulanteInformaticaComponent,
        PostulanteExperienciaComponent,
        PostulanteReferenciaComponent,
        PostulanteDocumentosComponent,
        PostulanteDependientesComponent,
        PostulanteLoginComponent,
        PostulanteUbicacionGeograficaSelectorComponent,
        PostulantePostularComponent,
        PostulanteReqAsocComponent,
        PostulanteRequerimientoAprobadoSelectorComponent
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
        ComunModule,
        InputMaskModule, SpinnerModule, PdfJsViewerModule
    ],
    exports: [
        PostulanteDatosPersonalesComponent,
        PostulanteDireccionesComponent,
        PostulanteInstruccionBasicaComponent,
        PostulanteInstruccionSuperiorComponent,
        PostulanteCursosComponent,
        PostulanteIdiomasComponent,
        PostulanteInformaticaComponent,
        PostulanteExperienciaComponent,
        PostulanteReferenciaComponent,
        PostulanteDocumentosComponent,
        PostulanteDependientesComponent,
        PostulanteLoginComponent,
        PostulanteReqAsocComponent,
        PostulanteRequerimientoAprobadoSelectorComponent
    ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        HrPostulanteService,
        HrActualizacionfichaempleadoServicio
    ]
})
export class PostulanteModule { }