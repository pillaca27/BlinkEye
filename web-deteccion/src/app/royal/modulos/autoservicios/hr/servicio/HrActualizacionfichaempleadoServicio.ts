import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConfig } from '@env/appconfig';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { HrActualizacionFichaEmpleado } from '../dominio/dto/HrActualizacionFichaEmpleado';
import { DtoHrGradoInstruccionConsulta } from '../dominio/dto/DtoHrGradoInstruccionConsulta';

@Injectable()
export class HrActualizacionfichaempleadoServicio {

    private rutaServicio = `${this.config.getEnv('spring-autoservicios-api')}/spring/rrhh/hractualizacionfichaempleado/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public listarGradosActivos(): Promise<DtoHrGradoInstruccionConsulta[]> {
        return this.http.get(this.rutaServicio + 'listarGradosActivos')
            .toPromise()
            .then(response => response as DtoHrGradoInstruccionConsulta[])
            .catch(error => []);
    }

    public listarCursosActivos(tipoMaestro: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo = tipoMaestro;
        return this.http.post(this.rutaServicio + 'listarCursosActivos', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarProfesionesActivas(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarProfesionesActivas')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    
    public listarBancosActivos(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarBancosActivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarAfpActivos(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarAfpActivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarTipoContratosActivos(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarTipoContratosActivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarPaisesActivos(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'listarPaisesActivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerPorTransaccion(uuid: string): Promise<HrActualizacionFichaEmpleado> {
        var filtro = new DtoTabla();
        filtro.codigo = uuid;
        return this.http.post(this.rutaServicio + 'obtenerPorTransaccion', filtro)
            .toPromise()
            .then(response => response as HrActualizacionFichaEmpleado)
            .catch(error => new HrActualizacionFichaEmpleado());
    }

    public obtenerPorEmpleado(): Promise<HrActualizacionFichaEmpleado> {
        return this.http.get(this.rutaServicio + 'obtenerPorEmpleado')
            .toPromise()
            .then(response => response as HrActualizacionFichaEmpleado)
            .catch(error => new HrActualizacionFichaEmpleado());
    }

    public listarDepartamentosActivos(pais: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo = pais;
        return this.http.post(this.rutaServicio + 'listarDepartamentosActivos', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarProvinciasActivos(pais: string, departamento: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo = pais;
        filtro.nombre = departamento;
        return this.http.post(this.rutaServicio + 'listarProvinciasActivos', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarDistritosActivos(pais: string, departamento: string, provincia: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo = pais;
        filtro.nombre = departamento;
        filtro.descripcion = provincia;
        return this.http.post(this.rutaServicio + 'listarDistritosActivos', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public guardar(dto: HrActualizacionFichaEmpleado): Promise<HrActualizacionFichaEmpleado> {
        return this.http.post(this.rutaServicio + 'guardar', dto)
            .toPromise()
            .then(response => response as HrActualizacionFichaEmpleado)
            .catch(error => new HrActualizacionFichaEmpleado());
    }

    public enviar(dto: HrActualizacionFichaEmpleado): Promise<HrActualizacionFichaEmpleado> {
        return this.http.post(this.rutaServicio + 'enviar', dto)
            .toPromise()
            .then(response => response as HrActualizacionFichaEmpleado)
            .catch(error => new HrActualizacionFichaEmpleado());
    }

    public anular(dto: HrActualizacionFichaEmpleado): Promise<HrActualizacionFichaEmpleado> {
        return this.http.post(this.rutaServicio + 'anular', dto)
            .toPromise()
            .then(response => response as HrActualizacionFichaEmpleado)
            .catch(error => new HrActualizacionFichaEmpleado());
    }
}
