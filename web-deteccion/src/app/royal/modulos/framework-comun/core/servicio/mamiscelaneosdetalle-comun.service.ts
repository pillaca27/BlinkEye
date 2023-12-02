import { DtoComunMaMiscelaneosdetalle } from '../dominio/dto/DtoComunMaMiscelaneosdetalle';
import { MiscelaneosHeaderTransaccion } from '../../../../framework/modelo/MiscelaneosHeaderTransaccion';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';

@Injectable()
export class MaMiscelaneosdetalleComunService {

    dtoMiscelaneosHeaderTransaccion: MiscelaneosHeaderTransaccion = new MiscelaneosHeaderTransaccion();
    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/mamiscelaneosdetallecomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //99-MISDET-CLSDET-ACT
    public actualizar(bean: DtoComunMaMiscelaneosdetalle): Promise<DtoComunMaMiscelaneosdetalle> {
        return this.http.put(this.url + 'actualizar', bean)
            .toPromise()
            .then(response => response as DtoComunMaMiscelaneosdetalle)
            .catch(error => null);

    }

    //QQUECHOD VALIDADO
    //99-MISDET-CLSDET-ELI
    public eliminar(dto: DtoComunMaMiscelaneosdetalle): Promise<DtoComunMaMiscelaneosdetalle[]> {                        
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunMaMiscelaneosdetalle[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-MISDET-CLSDET
    public listarDtoPorHeader(aplicacioncodigo: string,codigotabla: string,compania: string): Promise<DtoComunMaMiscelaneosdetalle[]> {        
        var dto = new DtoComunMaMiscelaneosdetalle();
        dto.aplicacioncodigo = aplicacioncodigo;
        dto.codigotabla = codigotabla;
        dto.compania = compania;        
        return this.config.getHttp().put(this.url + 'listardtoporheader', dto)
            .toPromise()
            .then(response => response as DtoComunMaMiscelaneosdetalle[])
            .catch(error => []);
    }
    public obtenerDto(aplicacioncodigo: string, codigotabla: string,compania: string, codigoelemento:string): Promise<DtoComunMaMiscelaneosdetalle> {
        var dto = new DtoComunMaMiscelaneosdetalle();
        dto.aplicacioncodigo = aplicacioncodigo;
        dto.compania = compania;
        dto.codigotabla = codigotabla;
        dto.codigoelemento = codigoelemento;
        return this.obtenerDtoPk(dto);
    }
    //QQUECHOD VALIDADO
    //99-MISDET-COBDTO
    public obtenerDtoPk(dto: DtoComunMaMiscelaneosdetalle): Promise<DtoComunMaMiscelaneosdetalle> {
        return this.http.put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunMaMiscelaneosdetalle)
            .catch(error => null);
    }
    
    /*******************/
    public listarTablaPorHeader(aplicacioncodigo: string,codigotabla: string,compania: string): Promise<DtoTabla[]> {
        var filtro =new DtoComunMaMiscelaneosdetalle();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.codigotabla=codigotabla;
        filtro.compania=compania;
        return this.listarTablaPorHeaderCore(filtro);
    }
    public listarTablaPorHeaderActivos(aplicacioncodigo: string,codigotabla: string,compania: string): Promise<DtoTabla[]> {
        var filtro =new DtoComunMaMiscelaneosdetalle();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.codigotabla=codigotabla;
        filtro.compania=compania;
        filtro.estado='A';
        return this.listarTablaPorHeaderCore(filtro);
    }
    public listarTablaPorHeaderCore(filtro: DtoComunMaMiscelaneosdetalle): Promise<DtoTabla[]> {
        return this.config.getHttp().put(this.url + 'listartablaporheader', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-MISDET-CLHACT
    /*public listarPorHeaderActivos(codigoCompania: string, aplicacionCodigo: string, codigoTabla: string): Promise<DtoTabla[]> {
        this.dtoMiscelaneosHeaderTransaccion.compania = codigoCompania;
        this.dtoMiscelaneosHeaderTransaccion.aplicacioncodigo = aplicacionCodigo;
        this.dtoMiscelaneosHeaderTransaccion.codigotabla = codigoTabla;
        return this.config.getHttp().put(this.url + 'listarporheaderactivos', this.dtoMiscelaneosHeaderTransaccion)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }*/
}