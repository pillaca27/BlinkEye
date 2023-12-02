import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunSyImportaciondetalle } from '../dominio/dto/DtoComunSyImportaciondetalle';

@Injectable()
export class SyImportaciondetalleComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/sistema/syimportaciondetallecomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
 
    public obtenerDto(aplicacioncodigo: string,tablacodigo:string,linea:number): Promise<DtoComunSyImportaciondetalle> {
        var filtro = new DtoComunSyImportaciondetalle();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.tablacodigo=tablacodigo;
        filtro.linea=linea;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunSyImportaciondetalle)
            .catch(error => new DtoComunSyImportaciondetalle());
    } 

    public listarDtoFiltros(aplicacioncodigo: string,tablacodigo:string,linea:number,descripcioncampo: string): Promise<DtoComunSyImportaciondetalle[]> {
        var filtro = new DtoComunSyImportaciondetalle();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.tablacodigo=tablacodigo;
        filtro.linea=linea;        
        filtro.descripcioncampo=descripcioncampo;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunSyImportaciondetalle[])
            .catch(error => []);
    }

    public listarDtoPorTabla(aplicacioncodigo: string,tablacodigo:string): Promise<DtoComunSyImportaciondetalle[]> {
        var filtro = new DtoComunSyImportaciondetalle();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.tablacodigo=tablacodigo;
        return this.http.put(this.url + 'listardtoportabla',filtro)
            .toPromise()
            .then(response => response as DtoComunSyImportaciondetalle[])
            .catch(error => []);
    }

}