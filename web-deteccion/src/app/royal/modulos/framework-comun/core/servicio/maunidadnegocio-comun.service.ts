import { Observable } from 'rxjs';
import { FiltroComunMaUnidadnegocio } from './../dominio/filtro/FiltroComunMaUnidadnegocio';
import { DominioPaginacion } from './../../../../framework/modelo/generico/DominioPaginacion';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunMaUnidadnegocio } from '../dominio/dto/DtoComunMaUnidadnegocio';

@Injectable()
export class MaUnidadnegocioComunService {

    dtoMaunidadnegocioTransaccion: DtoTabla = new DtoTabla();
    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/maunidadnegociocomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //LEONARDO
    //SELECTOR COMPANIA
    public listarunidadnegocioporfiltro(dto: FiltroComunMaUnidadnegocio): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listarunidadnegocioporfiltro', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    //QQUECHOD VALIDADO
    //99-UNINEG-CLISTA
    public listar(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-UNINEG-CLIACT
    public listarActivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    } 

    //QQUECHOD VALIDADO
    //99-UNINEG-COBTDTO
    public obtenertabla(unidadnegocio: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=unidadnegocio;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    /*public obtenerDto(unidadnegocio: string): Promise<DtoComunMaUnidadnegocio> {
        var filtro = new DtoComunMaUnidadnegocio();
        filtro.unidadnegocio=unidadnegocio;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio)
            .catch(error => new DtoComunMaUnidadnegocio());
    }*/

    public listarDtoFiltros(unidadnegocio : string,descripcionlocal:string,estado:string): Promise<DtoComunMaUnidadnegocio[]> {
        var filtro = new DtoComunMaUnidadnegocio();
        filtro.unidadnegocio=unidadnegocio;
        filtro.descripcionlocal=descripcionlocal;
        filtro.estado=estado;
        return this.config.getHttp().put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-UNINEG-C0001
    public listaractivosporcompania(codigoCompania: string): Promise<DtoTabla[]> {            
        this.dtoMaunidadnegocioTransaccion.codigo = codigoCompania;        
        return this.config.getHttp().put(this.url + 'listaractivosporcompania', this.dtoMaunidadnegocioTransaccion)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarporseguridad(): Promise<DtoTabla[]> {                    
        return this.config.getHttp().get(this.url + 'listarporseguridad')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    



    public obtenerDtoPorId(unidadnegocio : string, zona : string, descripcionlocal : string, descripcioningles : string, direccion : string, telefonos : string, registropatronalplanilla : string, estado : string, ultimousuario : string, ultimafechamodif : Date, companiasocio : string, ledger : string, representantedocumento : string, representante : string, persona : number): Promise<DtoComunMaUnidadnegocio> {
        var dto = new DtoComunMaUnidadnegocio();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunMaUnidadnegocio): Promise<DtoComunMaUnidadnegocio> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio)
            .catch(error => null);
    }

    public registrar(dto: DtoComunMaUnidadnegocio): Promise<DtoComunMaUnidadnegocio> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunMaUnidadnegocio): Promise<DtoComunMaUnidadnegocio> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio)
            .catch(error => null);
    }

    public anular(dto: DtoComunMaUnidadnegocio): Promise<DtoComunMaUnidadnegocio> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunMaUnidadnegocio): Promise<DtoComunMaUnidadnegocio> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio)
            .catch(error => null);
    }

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

    /*public listarDtoFiltros(unidadnegocio : string, zona : string, descripcionlocal : string, descripcioningles : string, direccion : string, telefonos : string, registropatronalplanilla : string, estado : string, ultimousuario : string, ultimafechamodif : Date, companiasocio : string, ledger : string, representantedocumento : string, representante : string, persona : number): Promise<DtoComunMaUnidadnegocio[]> {
        var filtro = new DtoComunMaUnidadnegocio();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio[])
            .catch(error => []);
    }*/

    public listarDtoActivos(unidadnegocio : string, zona : string, descripcionlocal : string, descripcioningles : string, direccion : string, telefonos : string, registropatronalplanilla : string, estado : string, ultimousuario : string, ultimafechamodif : Date, companiasocio : string, ledger : string, representantedocumento : string, representante : string, persona : number): Promise<DtoComunMaUnidadnegocio[]> {
        var filtro = new DtoComunMaUnidadnegocio();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunMaUnidadnegocio[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunMaUnidadnegocio): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarUnidadNegocio(filtro: FiltroComunMaUnidadnegocio): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarUnidadNegocio`, filtro,
            { responseType: 'blob' as 'json' });
    }
}