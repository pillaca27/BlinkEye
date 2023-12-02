import { WfDtoComunAplicacionesmast } from './../dominio/dto/WfDtoComunAplicacionesmast';
import { DtoTabla } from './../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from './../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class WfAplicacionesmastComunService {

    private url = `${this.config.getEnv('spring-framework-workflow-api')}/spring/comun/wfaplicacionesmastcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }    

    //QQUECHOD VALIDADO
    //SG-APLICA-CLISTA  
    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //SG-APLICA-CLIACT
    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    
    public obtenerTabla(aplicacioncodigo: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=aplicacioncodigo;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(aplicacioncodigo: string,descripcioncorta: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=aplicacioncodigo;
        filtro.nombre=descripcioncorta;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerDto(aplicacioncodigo: string): Promise<WfDtoComunAplicacionesmast> {
        var filtro = new WfDtoComunAplicacionesmast();
        filtro.aplicacioncodigo=aplicacioncodigo;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as WfDtoComunAplicacionesmast)
            .catch(error => new WfDtoComunAplicacionesmast());
    } 

    public listarDtoFiltros(aplicacioncodigo: string,descripcioncorta: string,estado: string): Promise<WfDtoComunAplicacionesmast[]> {
        var filtro = new WfDtoComunAplicacionesmast();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.descripcioncorta=descripcioncorta;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as WfDtoComunAplicacionesmast[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //SG-APLICA-C0001
    public listarAplicacionPorUsuario(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaraplicacionporusuario')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

}