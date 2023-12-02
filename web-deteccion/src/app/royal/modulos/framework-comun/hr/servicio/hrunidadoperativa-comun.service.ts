import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { DtoComunHrUnidadoperativa } from '../dominio/dto/DtoComunHrUnidadoperativa';
import { FiltroComunHrUnidadoperativa } from '../dominio/filtro/FiltroComunHrUnidadoperativa';
import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';


@Injectable()
export class HrUnidadoperativaComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/rrhh/hrunidadoperativacomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //LEONARDO
    //SELECTOR UNIDAD OPERATIVA
    public listarunidadoperativaporfiltro(dto: FiltroComunHrUnidadoperativa): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listarunidadoperativaporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    //QQUECHOD VALIDADO
    //HR-UNIOPER-CLISTA
    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //HR-UNIOPER-CLIACT
    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    } 

    //QQUECHOD VALIDADO
    //HR-UNIOPER-COBTDTO
    public obtenerTabla(tipoTrabajador: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=tipoTrabajador;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    //QQUECHOD VALIDADO
    //HR-UNIOPER-COBTENER
    public obtenerDto(unidadoperativa: string): Promise<DtoComunHrUnidadoperativa> {
        var filtro = new DtoComunHrUnidadoperativa();
        filtro.unidadoperativa=unidadoperativa;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunHrUnidadoperativa)
            .catch(error => new DtoComunHrUnidadoperativa());
    }

    public listarDtoFiltros(unidadoperativa: string,descripcion: string,estado: string): Promise<DtoComunHrUnidadoperativa[]> {
        var filtro = new DtoComunHrUnidadoperativa();
        filtro.unidadoperativa = unidadoperativa;
        filtro.descripcion = descripcion;
        filtro.estado = estado;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunHrUnidadoperativa[])
            .catch(error => []);
    } 
}