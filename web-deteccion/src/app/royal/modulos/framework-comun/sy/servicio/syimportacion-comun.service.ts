import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunSyImportacion } from '../dominio/dto/DtoComunSyImportacion';

@Injectable()
export class SyImportacionComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/sistema/syimportacioncomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
 
    public obtenerDto(aplicacioncodigo: string,tablacodigo:string): Promise<DtoComunSyImportacion> {
        var filtro = new DtoComunSyImportacion();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.tablacodigo=tablacodigo;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunSyImportacion)
            .catch(error => new DtoComunSyImportacion());
    } 

    public listarDtoFiltros(aplicacioncodigo: string,tablacodigo:string,descripcionlocal: string,estado: string): Promise<DtoComunSyImportacion[]> {
        var filtro = new DtoComunSyImportacion();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.tablacodigo=tablacodigo;
        filtro.descripcionlocal=descripcionlocal;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunSyImportacion[])
            .catch(error => []);
    }

}