import { SgModule } from './royal/modulos/sg/sg.module';

/* //COMENTADO LEONARDO  
import { ProveedorModule } from './royal/modulos/proveedor/Proveedor.module';
import { NuevoProveedorComponent } from './royal/modulos/proveedor/login-proveedor/vista/nuevoproveedor.component';
import { AppProveedorLoginComponent } from './royal/modulos/proveedor/login-proveedor/vista/login-proveedor.component'; 
*/

//import { LoginProveedorService } from './royal/modulos/proveedor/login-proveedor/servicio/loginProveedor.service';
import { MyCurrencyFormatterDirective } from './royal/framework/angular/directives/MyCurrencyFormatterDirective';
import { UsuarioService } from './royal/modulos/framework-seguridad/servicio/spring/seguridad/usuario.service';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';

import { WorkflowModule } from './royal/modulos/framework-workflow/workflow.module';
import { ComunModule } from './royal/modulos/framework-comun/Comun.module';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CurrencyPipe, DecimalPipe, HashLocationStrategy, LocationStrategy } from '@angular/common';
import { AppRoutingModule } from './app.routing.module';

import { AccordionModule } from 'primeng/accordion';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { ButtonModule } from 'primeng/button';
import { CalendarModule } from 'primeng/calendar';
import { CardModule } from 'primeng/card';
import { CarouselModule } from 'primeng/carousel';
import { ChartModule } from 'primeng/chart';
import { CheckboxModule } from 'primeng/checkbox';
import { ChipsModule } from 'primeng/chips';
import { CodeHighlighterModule } from 'primeng/codehighlighter';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ColorPickerModule } from 'primeng/colorpicker';
import { ContextMenuModule } from 'primeng/contextmenu';
import { DataViewModule } from 'primeng/dataview';
import { DialogModule } from 'primeng/dialog';
import { DropdownModule } from 'primeng/dropdown';
import { FieldsetModule } from 'primeng/fieldset';
import { FileUploadModule } from 'primeng/fileupload';
import { FullCalendarModule } from 'primeng/fullcalendar';
import { GalleriaModule } from 'primeng/galleria';
import { InplaceModule } from 'primeng/inplace';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputMaskModule } from 'primeng/inputmask';
import { InputSwitchModule } from 'primeng/inputswitch';
import { InputTextModule } from 'primeng/inputtext';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { LightboxModule } from 'primeng/lightbox';
import { ListboxModule } from 'primeng/listbox';
import { MegaMenuModule } from 'primeng/megamenu';
import { MenuModule } from 'primeng/menu';
import { MenubarModule } from 'primeng/menubar';
import { MessagesModule } from 'primeng/messages';
import { MessageModule } from 'primeng/message';
import { MultiSelectModule } from 'primeng/multiselect';
import { OrderListModule } from 'primeng/orderlist';
import { OrganizationChartModule } from 'primeng/organizationchart';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { PaginatorModule } from 'primeng/paginator';
import { PanelModule } from 'primeng/panel';
import { PanelMenuModule } from 'primeng/panelmenu';
import { PasswordModule } from 'primeng/password';
import { PickListModule } from 'primeng/picklist';
import { ProgressBarModule } from 'primeng/progressbar';
import { RadioButtonModule } from 'primeng/radiobutton';
import { RatingModule } from 'primeng/rating';
import { RippleModule } from 'primeng/ripple';
import { ScrollPanelModule } from 'primeng/scrollpanel';
import { SelectButtonModule } from 'primeng/selectbutton';
import { SidebarModule } from 'primeng/sidebar';
import { SlideMenuModule } from 'primeng/slidemenu';
import { SliderModule } from 'primeng/slider';
import { SplitButtonModule } from 'primeng/splitbutton';
import { StepsModule } from 'primeng/steps';
import { TabMenuModule } from 'primeng/tabmenu';
import { TableModule } from 'primeng/table';
import { TabViewModule } from 'primeng/tabview';
import { TerminalModule } from 'primeng/terminal';
import { TieredMenuModule } from 'primeng/tieredmenu';
import { ToastModule } from 'primeng/toast';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { ToolbarModule } from 'primeng/toolbar';
import { TooltipModule } from 'primeng/tooltip';
import { TreeModule } from 'primeng/tree';
import { TreeTableModule } from 'primeng/treetable';
import { VirtualScrollerModule } from 'primeng/virtualscroller';

import { AppCodeModule } from './app.code.component';
import { AppComponent } from './app.component';
import { AppMainComponent } from './app.main.component';
import { AppConfigComponent } from './app.config.component';
import { AppRightmenuComponent } from './app.rightmenu.component';
import { AppMenuComponent } from './app.menu.component';
import { AppMenuitemComponent } from './app.menuitem.component';
import { AppTopBarComponent } from './app.topbar.component';
import { AppSearchComponent } from './app.search.component';
import { AppFooterComponent } from './app.footer.component';

import { BreadcrumbService } from './app.breadcrumb.service';
import { MenuService } from './app.menu.service';

import { AppComunLoginComponent } from './royal/modulos/framework-seguridad/vista/app.comun-login.component';
import { AppConfig } from 'src/environments/appconfig';
import { LoginService } from '@framework-seguridad/servicio/autorizacion/seguridad/login.service';
import { MessageService, SharedModule } from 'primeng/api';
import { BlockUIModule } from 'primeng/blockui';
import { DividerModule } from 'primeng/divider';
import { ContraseniaMantenientoComponent } from './contrasenia-mantenimiento.component';
/* //COMENTADO LEONARDO  import { ProveedorService } from './royal/modulos/proveedor/registro/servicio/proveedor.service';
import { LogisticaModule } from './royal/modulos/logistica/logistica.module';*/
 
import { RecaptchaV3Module, RECAPTCHA_V3_SITE_KEY } from 'ng-recaptcha';
import { AutoserviciosModule } from './royal/modulos/autoservicios/autoservicios.module';
import {AsAutorizacionModule} from './royal/modulos/autoservicios/as/asautorizacion.module';
import { PostulanteModule } from './royal/modulos/postulante/postulante.module';

const apiKeyCaptcha = "6LdWclEdAAAAAGNrP22eEc-dRrowZMQvEZX2Ak-O"; // initialized

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        AppRoutingModule,
        HttpClientModule,
        BrowserAnimationsModule,
        AccordionModule,
        AutoCompleteModule,
        BreadcrumbModule,
        ButtonModule,
        CalendarModule,
        CardModule,
        CarouselModule,
        ChartModule,
        CheckboxModule,
        ChipsModule,
        CodeHighlighterModule,
        ConfirmDialogModule,
        ColorPickerModule,
        ContextMenuModule,
        DataViewModule,
        DialogModule,
        DropdownModule,
        FieldsetModule,
        FileUploadModule,
        FullCalendarModule,
        GalleriaModule,
        InplaceModule,
        InputNumberModule,
        InputMaskModule,
        InputSwitchModule,
        InputTextModule,
        InputTextareaModule,
        LightboxModule,
        ListboxModule,
        MegaMenuModule,
        MenuModule,
        MenubarModule,
        MessageModule,
        MessagesModule,
        MultiSelectModule,
        OrderListModule,
        OrganizationChartModule,
        OverlayPanelModule,
        PaginatorModule,
        PanelModule,
        PanelMenuModule,
        PasswordModule,
        PickListModule,
        ProgressBarModule,
        RadioButtonModule,
        RatingModule,
        RippleModule,
        ScrollPanelModule,
        SelectButtonModule,
        SidebarModule,
        SlideMenuModule,
        SliderModule,
        SplitButtonModule,
        StepsModule,
        TableModule,
        TabMenuModule,
        TabViewModule,
        TerminalModule,
        TieredMenuModule,
        ToastModule,
        ToggleButtonModule,
        ToolbarModule,
        TooltipModule,
        TreeModule,
        TreeTableModule,
        VirtualScrollerModule,
        AppCodeModule,
        ToastModule,
        BlockUIModule,
        WorkflowModule,
        ComunModule,
        SharedModule,
        DividerModule,
   
        RecaptchaV3Module,
        AutoserviciosModule,
        SgModule,
        AsAutorizacionModule,
        PostulanteModule
    ],
    declarations: [
        AppComponent,
        AppMainComponent,
        AppRightmenuComponent,
        AppMenuComponent,
        AppMenuitemComponent,
        AppConfigComponent,
        AppTopBarComponent,
        AppSearchComponent,
        AppFooterComponent,
        AppComunLoginComponent,
        MyCurrencyFormatterDirective,
        ContraseniaMantenientoComponent,

        /* //COMENTADO LEONARDO   AppProveedorLoginComponent,
          NuevoProveedorComponent, */
    ],
    providers: [
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        { provide: HTTP_INTERCEPTORS, useExisting: NoAuthorizationInterceptor, multi: true },
        { provide: RECAPTCHA_V3_SITE_KEY, useValue: apiKeyCaptcha },
        MenuService, BreadcrumbService,
        {
            provide: APP_INITIALIZER,
            useFactory: httpAppConfig,
            deps: [AppConfig], multi: true,
        },
        NoAuthorizationInterceptor,
        MessageService,
        AppConfig,
        LoginService,
        UsuarioService,
        //LoginProveedorService,
        /*   
          ProveedorService, */
        CurrencyPipe,
        DecimalPipe,
    ],
    exports: [
        MyCurrencyFormatterDirective,
        //  ContraseniaMantenientoComponent
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
export function httpAppConfig(config: AppConfig) {
    return () => config.load();
}
