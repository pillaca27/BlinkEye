import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { DtoComunHrGradoinstruccion } from '../dominio/dto/DtoComunHrGradoinstruccion';
import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { FiltroComunHrGradoinstruccion } from '../dominio/filtro/FiltroComunHrGradoinstruccion';

@Injectable()
export class HrGradoinstruccionComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/rrhh/hrgradoinstruccioncomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //RH-GRAINST-LISTAR
    public listar(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //RH-GRAINST-LISACT
    public listarActivos(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //HR-GRAINST-OBTTAB
    public obtenerTabla(gradoinstruccion: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=gradoinstruccion;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    //QQUECHOD VALIDADO
    //HR-GRAINST-OBTDTO
    public obtenerDto(gradoinstruccion: string): Promise<DtoComunHrGradoinstruccion> {
        var filtro = new DtoComunHrGradoinstruccion();
        filtro.gradoinstruccion=gradoinstruccion;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunHrGradoinstruccion)
            .catch(error => new DtoComunHrGradoinstruccion());
    } 

    //QQUECHOD VALIDADO
    //HR-GRAINST-LISDTOFIL
    public listarDtoFiltros(gradoinstruccion:string,descripcion: string,estado:string): Promise<DtoTabla[]> {    
        var filtro = new DtoComunHrGradoinstruccion();
        filtro.gradoinstruccion = gradoinstruccion;
        filtro.descripcion = descripcion;
        filtro.estado = estado;
        return this.http.put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    } 

    //MALCAN VALIDADO
    //HRGRADOINSTRUCCION PAGINADO
    public listarPaginado(dto: FiltroComunHrGradoinstruccion): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listarpaginado', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}