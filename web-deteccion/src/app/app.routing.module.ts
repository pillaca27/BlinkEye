import { RouterModule } from '@angular/router';
import { NgModule, ViewChild } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { AppComunLoginComponent } from './royal/modulos/framework-seguridad/vista/app.comun-login.component';
import { DashboardComponent } from './royal/modulos/autoservicios/db/dashboard.componente';
import { ReporteDeteccionComponent } from './royal/modulos/autoservicios/dt/vista/reporte-deteccion/reporte-deteccion.component';
import { ConsultaClaseComponent } from './royal/modulos/autoservicios/dt/vista/consulta-clase/consulta-clase.component';

@NgModule({

    imports: [
        RouterModule.forRoot([
            { path: '', component: AppComunLoginComponent },
            {
                path: 'spring', component: AppMainComponent,
                children: [
                    { path: '', component: DashboardComponent },
                    {
                        path: 'dt',
                        children: [
                            { path: 'reporte-deteccion', component: ReporteDeteccionComponent },
                            { path: 'consulta-clase', component: ConsultaClaseComponent },
                        ]
                    },
                    // {
                    //     path: 'comun',
                    //     children: [
                    //         {
                    //             path: 'sy',
                    //             children: [
                    //                 { path: 'syreporte-listado', component: SyReporteListadoComponent },
                    //                 { path: 'syreporte-mantenimiento', component: SyReporteComponent },
                    //                 { path: 'syreporte-mantenimiento/:accion', component: SyReporteComponent },
                    //                 { path: 'syreporte-mantenimiento/:accion/:aplicacionCodigo/:reporteCodigo/:reporteTipo/:uuid', component: SyReporteComponent },
                    //             ],
                    //         }
                    //     ]
                    // },
                ]
            },
            { path: 'error', component: AppComunLoginComponent },
            { path: 'notfound', component: AppComunLoginComponent },
            { path: '**', redirectTo: '/notfound' },
        ], { scrollPositionRestoration: 'enabled' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
