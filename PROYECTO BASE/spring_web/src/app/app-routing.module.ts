import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { AppComunLoginComponent } from './seguridad/vista/app.comun-login.component';

@NgModule({
    imports: [
        RouterModule.forRoot([

            { path: '', component: AppComunLoginComponent },
            // {
            //     path: 'portal', component: AppMainComponent,
            //     children: [

            //         { path: 'erp/capacitaciones', component: CapacitacionesListadoComponent },
            //         { path: 'erp/capacitaciones-mantenimiento', component: CapacitacionesMantenimientoComponent },
            //         { path: 'erp/capacitaciones-mantenimiento/:dto/:accion', component: CapacitacionesMantenimientoComponent },
            //     ]
            // },
            { path: '**', redirectTo: '/notfound' },


        ], { scrollPositionRestoration: 'enabled' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
