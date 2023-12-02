import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunAcReferenciafiscal } from '../dominio/dto/DtoComunAcReferenciafiscal';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FiltroComunAcReferenciafiscalSelector } from '../dominio/filtro/FiltroComunAcReferenciafiscalSelector';

@Injectable()
export class AcReferenciafiscalComunService {

    dtoAcreferenciafiscal: DtoComunAcReferenciafiscal = new DtoComunAcReferenciafiscal();
    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/contabilidad/acreferenciafiscalcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //AC-REFFIS-C0001
    public listarreferenciafiscalactual(tipoReferenciaFiscal: string, nivel: string): Promise<DtoTabla[]> {        
        this.dtoAcreferenciafiscal.tiporeferenciafiscal = tipoReferenciaFiscal;
        this.dtoAcreferenciafiscal.nivel = nivel;
        return this.config.getHttp().put(this.url + 'listarreferenciafiscalactual', this.dtoAcreferenciafiscal)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
 
    public listarDtoPorAnioTipoNivel(filtro: FiltroComunAcReferenciafiscalSelector): Promise<DtoComunAcReferenciafiscal[]> {        
        return this.config.getHttp().put(this.url + 'listardtoporaniotiponivel', filtro)
            .toPromise()
            .then(response => response as DtoComunAcReferenciafiscal[])
            .catch(error => []);
    }

    public listarDtoPorSeguridadAnioTipoNivel(filtro: FiltroComunAcReferenciafiscalSelector): Promise<DtoComunAcReferenciafiscal[]> {        
        return this.config.getHttp().put(this.url + 'listardtoporseguridadaniotiponivel', filtro)
            .toPromise()
            .then(response => response as DtoComunAcReferenciafiscal[])
            .catch(error => []);
    }
}