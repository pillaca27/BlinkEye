import { Observable } from 'rxjs';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { FiltroComunBanco } from './../dominio/filtro/FiltroComunBanco';
import { DtoComunBanco } from './../dominio/dto/DtoComunBanco';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';

@Injectable()
export class BancoComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/bancocomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    /*//QQUECHOD VALIDADO
    //HR-BANCO-COBTDTO
    public obtenerTabla(banco: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=banco;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    //QQUECHOD VALIDADO
    //99-BANCO-CLISTA
    public listar(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-BANCO-CLIACT
    public listarActivos(): Promise<DtoTabla[]> {        
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarFiltros(banco: string,descripcioncorta: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=banco;
        filtro.nombre=descripcioncorta;
        filtro.estadoId=estado;
        return this.config.getHttp().put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }*/



    //MANTENMIMIENTO


    public obtenerDtoPorId(banco : string, descripcioncorta : string, descripcionlarga : string, banconumero : number, personacontacto : string, conciliacionautomaticaflag : string, formatopropioflag : string, formatodatawindow : string, estado : string, ultimafechamodif : Date, timestamp : object, ultimousuario : string, conciliacionformatoflag : string, codigointerfaseafp : string, disponibleedflag : string, tasaefectivaanual : number, codigofiscal : string): Promise<DtoComunBanco> {
        var dto = new DtoComunBanco();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunBanco)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunBanco): Promise<DtoComunBanco> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunBanco)
            .catch(error => null);
    }

    public registrar(dto: DtoComunBanco): Promise<DtoComunBanco> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunBanco)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunBanco): Promise<DtoComunBanco> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunBanco)
            .catch(error => null);
    }

    public anular(dto: DtoComunBanco): Promise<DtoComunBanco> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunBanco)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunBanco): Promise<DtoComunBanco> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunBanco)
            .catch(error => null);
    }

    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarDtoFiltros(banco : string, descripcioncorta : string, descripcionlarga : string, banconumero : number, personacontacto : string, conciliacionautomaticaflag : string, formatopropioflag : string, formatodatawindow : string, estado : string, ultimafechamodif : Date, timestamp : object, ultimousuario : string, conciliacionformatoflag : string, codigointerfaseafp : string, disponibleedflag : string, tasaefectivaanual : number, codigofiscal : string): Promise<DtoComunBanco[]> {
        var filtro = new DtoComunBanco();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunBanco[])
            .catch(error => []);
    }

    public listarDtoActivos(banco : string, descripcioncorta : string, descripcionlarga : string, banconumero : number, personacontacto : string, conciliacionautomaticaflag : string, formatopropioflag : string, formatodatawindow : string, estado : string, ultimafechamodif : Date, timestamp : object, ultimousuario : string, conciliacionformatoflag : string, codigointerfaseafp : string, disponibleedflag : string, tasaefectivaanual : number, codigofiscal : string): Promise<DtoComunBanco[]> {
        var filtro = new DtoComunBanco();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunBanco[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunBanco): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarBancos(filtro: FiltroComunBanco): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarBancos`, filtro,
            { responseType: 'blob' as 'json' });
    }
}