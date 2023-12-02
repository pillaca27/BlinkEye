import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FiltroComunHrCursodescripcion } from '../dominio/filtro/FiltroComunHrCursodescripcion';
import { DtoComunHrCursodescripcion } from '../dominio/dto/DtoComunHrCursodescripcion';

@Injectable()
export class HrCursodescripcionComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/rrhh/hrcursodescripcioncomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //LEONARDO
    //HRCURSODESCRIPCION SELECTOR
    public listarcursoporfiltro(dto: FiltroComunHrCursodescripcion): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listarcursoporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    //QQUECHOD VALIDADO
    //HR-CURSO-LISTAR
    public listar(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //HR-CURSO-LISACT
    public listarActivos(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //HR-CURSO-OBTTAB
    public obtenerTabla(curso: number): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.id=curso;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    }

    //QQUECHOD VALIDADO
    //HR-CURSO-OBTDTO
    public obtenerDto(curso: number): Promise<DtoComunHrCursodescripcion> {
        var filtro = new DtoComunHrCursodescripcion();
        filtro.curso=curso;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunHrCursodescripcion)
            .catch(error => new DtoComunHrCursodescripcion());
    } 

    //QQUECHOD VALIDADO
    //HR-CURSO-LISDTOFIL
    public listarDtoFiltros(curso:number,descripcion: string, tipo: string,area:string,estado:string): Promise<DtoComunHrCursodescripcion[]> {    
        var filtro = new DtoComunHrCursodescripcion();
        filtro.curso = curso;
        filtro.tipo = tipo;
        filtro.area = area;
        filtro.tipo = tipo;
        filtro.estado = estado;
        return this.http.put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunHrCursodescripcion[])
            .catch(error => []);
    }     
    
    //QQUECHOD VALIDADO
    //HR-CURSO-CLSPAG
    public listarcursospaginacion(filtro: FiltroComunHrCursodescripcion): Promise<DominioPaginacion> {

        return this.http.put(this.url + 'listarcursospaginacion', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    //QQUECHOD VALIDADO
    //HR-CURSO-C0001
    public listarPorDescripcionTipo(descripcion: string, tipo: string): Promise<DtoTabla[]> {    
        var filtro = new FiltroComunHrCursodescripcion();
        filtro.tipo = tipo;
        filtro.descripcion = descripcion;
        return this.http.put(this.url + 'listarpordescripciontipo', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }     
}