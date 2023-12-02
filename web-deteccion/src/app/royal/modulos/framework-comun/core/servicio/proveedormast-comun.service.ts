import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunProveedormast } from '../dominio/dto/DtoComunProveedormast';

@Injectable()
export class ProveedormastComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/proveedormastcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    /*public listar(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }*/

    /*public listarActivos(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }*/
    
    public obtenerTabla(proveedor : number): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.id=proveedor;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(proveedor : number,busqueda: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.id=proveedor;
        filtro.nombre=busqueda;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    /*public obtenerDto(proveedor : number): Promise<DtoComunProveedormast> {
        var filtro = new DtoComunProveedormast();
        filtro.proveedor=proveedor;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunProveedormast)
            .catch(error => new DtoComunProveedormast());
    } */

    public DtoComunProveedormast(proveedor : number,busqueda: string,estado: string): Promise<DtoComunProveedormast[]> {
        var filtro = new DtoComunProveedormast();
        filtro.proveedor=proveedor;
        filtro.busqueda=busqueda;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunProveedormast[])
            .catch(error => []);
    }
    









    public obtenerDtoPorId(proveedor : number, tiposervicio : string, tipoproveedor : string, pagaranombre : string, pagarendireccion : string, tipopago : string, monedapago : string, formadepago : string, representantelegal : string, responsible : string, cuentacontablegasto : string, chequenonegociableflag : string, suspensionrentaflag : string, suspensionfonaviflag : string, exoneradodeclaracion : string, exoneradofechainicio : Date, exoneradofechafinal : Date, numerodiaspago : number, numerodiasentrega : number, tipodocumentodefault : string, cobradortipodocumento : string, cobradornumerodocumento : string, cobradornombre : string, comentarioservicio : string, comentariofecha : Date, comentariousuario : string, clasificacionfiscal : string, intervencionfiscalflag : string, intervencionfiscaldocumento : string, registropublico : string, licenciamunicipal : string, fechaconstitucion : Date, personacontacto : string, experienciatiempo : string, proveedorrelacionado : number, detraccioncodigoflag : string, detraccioncodigo : string, detraccioncuentabancaria : string, exoneradofonavideclaracion : string, exoneradofonavifechainicio : Date, exoneradofonavifechafinal : Date, retenerpagosflag : string, exoneradomediopresentacion : string, intervencionfiscalcomentario : string, intervencionfiscalfecha : Date, fechaautorizacionfiscal : Date, detraccionnoafectoflag : string, afpretencionflag : string, afpcodigo : string, afpcuspp : string, afptipocomision : string): Promise<DtoComunProveedormast> {
        var dto = new DtoComunProveedormast();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunProveedormast)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunProveedormast): Promise<DtoComunProveedormast> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunProveedormast)
            .catch(error => null);
    }

    public registrar(dto: DtoComunProveedormast): Promise<DtoComunProveedormast> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunProveedormast)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunProveedormast): Promise<DtoComunProveedormast> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunProveedormast)
            .catch(error => null);
    }

    public anular(dto: DtoComunProveedormast): Promise<DtoComunProveedormast> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunProveedormast)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunProveedormast): Promise<DtoComunProveedormast> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunProveedormast)
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

    public listarDtoFiltros(proveedor : number, tiposervicio : string, tipoproveedor : string, pagaranombre : string, pagarendireccion : string, tipopago : string, monedapago : string, formadepago : string, representantelegal : string, responsible : string, cuentacontablegasto : string, chequenonegociableflag : string, suspensionrentaflag : string, suspensionfonaviflag : string, exoneradodeclaracion : string, exoneradofechainicio : Date, exoneradofechafinal : Date, numerodiaspago : number, numerodiasentrega : number, tipodocumentodefault : string, cobradortipodocumento : string, cobradornumerodocumento : string, cobradornombre : string, comentarioservicio : string, comentariofecha : Date, comentariousuario : string, clasificacionfiscal : string, intervencionfiscalflag : string, intervencionfiscaldocumento : string, registropublico : string, licenciamunicipal : string, fechaconstitucion : Date, personacontacto : string, experienciatiempo : string, proveedorrelacionado : number, detraccioncodigoflag : string, detraccioncodigo : string, detraccioncuentabancaria : string, exoneradofonavideclaracion : string, exoneradofonavifechainicio : Date, exoneradofonavifechafinal : Date, retenerpagosflag : string, exoneradomediopresentacion : string, intervencionfiscalcomentario : string, intervencionfiscalfecha : Date, fechaautorizacionfiscal : Date, detraccionnoafectoflag : string, afpretencionflag : string, afpcodigo : string, afpcuspp : string, afptipocomision : string): Promise<DtoComunProveedormast[]> {
        var filtro = new DtoComunProveedormast();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunProveedormast[])
            .catch(error => []);
    }

    public listarDtoActivos(proveedor : number, tiposervicio : string, tipoproveedor : string, pagaranombre : string, pagarendireccion : string, tipopago : string, monedapago : string, formadepago : string, representantelegal : string, responsible : string, cuentacontablegasto : string, chequenonegociableflag : string, suspensionrentaflag : string, suspensionfonaviflag : string, exoneradodeclaracion : string, exoneradofechainicio : Date, exoneradofechafinal : Date, numerodiaspago : number, numerodiasentrega : number, tipodocumentodefault : string, cobradortipodocumento : string, cobradornumerodocumento : string, cobradornombre : string, comentarioservicio : string, comentariofecha : Date, comentariousuario : string, clasificacionfiscal : string, intervencionfiscalflag : string, intervencionfiscaldocumento : string, registropublico : string, licenciamunicipal : string, fechaconstitucion : Date, personacontacto : string, experienciatiempo : string, proveedorrelacionado : number, detraccioncodigoflag : string, detraccioncodigo : string, detraccioncuentabancaria : string, exoneradofonavideclaracion : string, exoneradofonavifechainicio : Date, exoneradofonavifechafinal : Date, retenerpagosflag : string, exoneradomediopresentacion : string, intervencionfiscalcomentario : string, intervencionfiscalfecha : Date, fechaautorizacionfiscal : Date, detraccionnoafectoflag : string, afpretencionflag : string, afpcodigo : string, afpcuspp : string, afptipocomision : string): Promise<DtoComunProveedormast[]> {
        var filtro = new DtoComunProveedormast();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunProveedormast[])
            .catch(error => []);
    }

}