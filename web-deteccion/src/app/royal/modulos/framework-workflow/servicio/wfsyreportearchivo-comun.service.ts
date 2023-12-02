import { DominioPaginacion } from './../../../framework/modelo/generico/DominioPaginacion';
import { WfDtoComunSyReportearchivo } from './../dominio/dto/WfDtoComunSyReportearchivo';
import { AppConfig } from './../../../../../environments/appconfig';
import { FiltroComunSyReportearchivoWf } from './../dominio/filtro/FiltroComunSyReportearchivoWf';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class WfSyReportearchivoComunService {

    private url = `${this.config.getEnv('spring-framework-workflow-api')}/spring/comun/wfsyreportearchivocomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //SY-REPARCH-COBTDTO
    public obtenerDto(bean: WfDtoComunSyReportearchivo): Promise<WfDtoComunSyReportearchivo> {
        return this.http.put(this.url + 'obtenerdto', bean)
            .toPromise()
            .then(response => response as WfDtoComunSyReportearchivo)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-REPARCH-CACTUA
    public actualizar(bean: WfDtoComunSyReportearchivo): Promise<any> {
        bean.auxString = this.cambiarHtml(bean.auxString);
        return this.http.put(this.url + 'actualizar', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-REPARCH-CREGIST    
    public registrar(bean: WfDtoComunSyReportearchivo): Promise<any> {
        bean.auxString = this.cambiarHtml(bean.auxString);
        return this.http.post(this.url + 'registrar', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }  
    
    //QQUECHOD VALIDADO
    //SY-REPARCH-CELIMI
    public eliminar(pk: WfDtoComunSyReportearchivo): Promise<any> {
        return this.http.put(this.url + 'eliminar', pk)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-REPARCH-CLISPAG
    public listarPaginado(filtro: FiltroComunSyReportearchivoWf): Promise<DominioPaginacion> {
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