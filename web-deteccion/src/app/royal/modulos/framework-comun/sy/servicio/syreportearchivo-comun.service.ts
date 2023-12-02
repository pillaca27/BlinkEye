import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { DtoComunSyReporte } from '../dominio/dto/DtoComunSyReporte';
import { DtoComunSyReportearchivo } from '../dominio/dto/DtoComunSyReportearchivo';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FiltroComunSyReportearchivo } from '../dominio/filtro/FiltroComunSyReportearchivo';


@Injectable()
export class SyReportearchivoComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/sistema/syreportearchivocomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //SY-REPARCH-COBTDTO
    public obtenerDto(bean: DtoComunSyReportearchivo): Promise<DtoComunSyReportearchivo> {
        return this.http.put(this.url + 'obtenerdto', bean)
            .toPromise()
            .then(response => response as DtoComunSyReportearchivo)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-REPARCH-CACTUA
    public actualizar(bean: DtoComunSyReportearchivo): Promise<any> {
        bean.auxString = this.cambiarHtml(bean.auxString);
        return this.http.put(this.url + 'actualizar', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-REPARCH-CREGIST    
    public registrar(bean: DtoComunSyReportearchivo): Promise<any> {
        bean.auxString = this.cambiarHtml(bean.auxString);
        return this.http.post(this.url + 'registrar', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }  
    
    //QQUECHOD VALIDADO
    //SY-REPARCH-CELIMI
    public eliminar(pk: DtoComunSyReportearchivo): Promise<any> {
        return this.http.put(this.url + 'eliminar', pk)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-REPARCH-CLISPAG
    public listarPaginado(filtro: FiltroComunSyReportearchivo): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarpaginado', filtro)
            .toPromise() 
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }
    
    cambiarHtml(valor: string) {
        if (valor == undefined) return null;
        if (valor == null) return null;

        var cadena = valor.replace(new RegExp('&lt;', 'g'), '<');
        cadena = valor.replace(new RegExp('&gt;', 'g'), '>');

        return cadena;
    }

}