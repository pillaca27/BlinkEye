import { DtoComunCompanyownerrecurso } from './../dominio/dto/dtocompanyownerrecurso';
import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from './../../../../../../environments/appconfig';
import { Inject, Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Companyownerrecurso, CompanyownerrecursoPk } from '../dominio/dto/companyownerrecurso';

@Injectable()
export class CompaniaRecursosService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/companyownerrecursocomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    obtenerListaRecursosDetalle(tipoRecurso: string): Promise<Companyownerrecurso[]> {
        var dto = new DtoTabla();
        dto.codigo = tipoRecurso;
        return this.http.put(this.url + 'listarporrecurso', dto)
            .toPromise()
            .then(response => {
                return response as Companyownerrecurso[];
            })
            .catch(error => new Array());
    }

    obtenerPorId(compania: string, tipoRecurso: string, periodo: string): Promise<DtoComunCompanyownerrecurso> {
        var dto = new DtoComunCompanyownerrecurso();
        dto.tiporecurso = tipoRecurso;
        dto.companyowner = compania;
        dto.periodo = periodo;

        return this.http.put(this.url + 'obtenerporid', dto)
            .toPromise()
            .then(response => {
                return response as DtoComunCompanyownerrecurso;
            });
    }

    registrar(bean: Companyownerrecurso): Promise<any> {

        return this.http.post(this.url + 'registrar', bean)
            .toPromise()
            .then(response => {
                return response as any;
            })
            .catch(error => null);
    }

    

    actualizar(bean: Companyownerrecurso): Promise<any> {
        return this.http.post(this.url + 'actualizar', bean)
            .toPromise()
            .then(response => {
                return response as any;
            })
            .catch(error => null);
    }

    eliminar(bean: DtoComunCompanyownerrecurso): Promise<any> {
        return this.http.put(this.url + 'eliminarportiporecurso', bean)
            .toPromise()
            .then(response => {
                return response as any;
            })
            .catch(error => null);
    }

    obtenerImagenCompaniaActual(): Promise<string> {
        return this.http.get(this.url + 'obtenerimagencompaniaactual')
            .toPromise()
            .then(response => {
                const d = (response as any).base64;

                return CONVERTIR_FOTO(d);
            })
            .catch(error => 'assets/layout/images/logo-wiener.png');
    }

}

export function CONVERTIR_FOTO(d) {
    if (d === undefined || d == null || d === '') {
        d = 'assets/layout/images/logo-wiener.png';
    } else {
        d = 'data:image/png;base64,' + d;
    }
    return d;
}