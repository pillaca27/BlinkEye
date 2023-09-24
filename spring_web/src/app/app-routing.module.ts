import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppMainComponent } from './app.main.component';
import { AppComunLoginComponent } from './seguridad/vista/app.comun-login.component';
import { CapacitacionListadoComponent } from './rrhh/capacitacion/vista/capacitacion-listado.component';
import { CapacitacionesMantenimientoComponent } from './rrhh/capacitacion/vista/capacitaciones-mantenimiento.component';
import { EspecialidadListadoComponent } from './rrhh/capacitacion/vista/especialidad-listado.component';
import { EspecialidadesMantenimientoComponent } from './rrhh/capacitacion/vista/especialidades-mantenimiento.component';
import { Especialidad2ListadoComponent } from './rrhh/capacitacion/vista/especialidad2-listado.component';
import { Especialidad2MantenimientoComponent } from './rrhh/capacitacion/vista/especialidad2-mantenimiento.component';

@NgModule({
    imports: [
        RouterModule.forRoot([

            { path: '', component: AppComunLoginComponent },
            {
                path: 'portal', component: AppMainComponent,
                children: [
                    { path: 'erp/especialidad', component: EspecialidadListadoComponent },
                    { path: 'erp/especialidades-mantenimiento', component: EspecialidadesMantenimientoComponent},
                    { path: 'erp/especialidades-mantenimiento/:dto/:accion', component: EspecialidadesMantenimientoComponent },
                    { path: 'erp/especialidad2', component: Especialidad2ListadoComponent },
                    { path: 'erp/especialidad2-mantenimiento', component: Especialidad2MantenimientoComponent },
                    { path: 'erp/especialidad2-mantenimiento/:dto/:accion', component: Especialidad2MantenimientoComponent },
                    { path: 'erp/capacitacion', component: CapacitacionListadoComponent },
                    { path: 'erp/capacitaciones-mantenimiento', component: CapacitacionesMantenimientoComponent },
                    { path: 'erp/capacitaciones-mantenimiento/:dto/:accion', component: CapacitacionesMantenimientoComponent },
                ]
            },

            { path: '**', redirectTo: '/notfound' },


        ], { scrollPositionRestoration: 'enabled' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
