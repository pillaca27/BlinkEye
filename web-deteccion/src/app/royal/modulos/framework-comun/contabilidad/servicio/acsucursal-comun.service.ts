import { Observable } from 'rxjs';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunAcSucursal } from '../dominio/dto/DtoComunAcSucursal';
import { FiltroComunAcSucursal } from '../dominio/filtro/FiltroComunAcSucursal';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';

@Injectable()
export class AcSucursalComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/contabilidad/acsucursalcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //AC-SUCUR-LISACT
    public listarActivos(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //AC-SUCUR-LISTAR
    public listar(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //AC-SUCUR-OBTTAB
    public obtenerTabla(costcenter: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=costcenter;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

/*     //QQUECHOD VALIDADO
    //AC-SUCUR-OBTDTO
    public obtenerDto(sucursal: string): Promise<DtoComunAcSucursal> {
        var filtro = new DtoComunAcSucursal();
        filtro.sucursal=sucursal;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAcSucursal)
            .catch(error => new DtoComunAcSucursal());
    }  */

    //QQUECHOD VALIDADO
    //AC-SUCUR-LISDTOFIL
    public listarDtoFiltros(sucursal: string,descripcionlocal: string,estado: string): Promise<DtoComunAcSucursal[]> {
        var filtro = new DtoComunAcSucursal();
        filtro.sucursal=sucursal;
        filtro.descripcionlocal=descripcionlocal;
        filtro.estado=estado;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAcSucursal[])
            .catch(error => []);
    }
    public listarDto(): Promise<DtoComunAcSucursal[]> {        
        return this.listarDtoFiltros(null,null,null);
    }
    public listarDtoActivos(): Promise<DtoComunAcSucursal[]> {        
        return this.listarDtoFiltros(null,null,"A");
    }
    
    // LEONARDO
    // SELECTOR SUCURSAL
    public listarsucursalporfiltro(filtro: FiltroComunAcSucursal): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarsucursalporfiltro', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    } 

    /********** MANTENIMIENTO ***********/ 

    public obtenerDtoPorId(sucursal : string, sucursalgrupo : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, cuentacontabledefecto : string, almacencodigo : string, racionperfil : string, departamento : string, provincia : string, codigopostal : string, pais : string, direccion : string, responsable : number): Promise<DtoComunAcSucursal> {
        var dto = new DtoComunAcSucursal();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursal)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunAcSucursal): Promise<DtoComunAcSucursal> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursal)
            .catch(error => null);
    }

    public registrar(dto: DtoComunAcSucursal): Promise<DtoComunAcSucursal> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursal)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunAcSucursal): Promise<DtoComunAcSucursal> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursal)
            .catch(error => null);
    }

    public anular(dto: DtoComunAcSucursal): Promise<DtoComunAcSucursal> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursal)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunAcSucursal): Promise<DtoComunAcSucursal> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursal)
            .catch(error => null);
    }

    /* public listar(): Promise<DtoTabla[]> {
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

    public listarDtoFiltros(sucursal : string, sucursalgrupo : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, cuentacontabledefecto : string, almacencodigo : string, racionperfil : string, departamento : string, provincia : string, codigopostal : string, pais : string, direccion : string, responsable : number): Promise<DtoComunAcSucursal[]> {
        var filtro = new DtoComunAcSucursal();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunAcSucursal[])
            .catch(error => []);
    }

    public listarDtoActivos(sucursal : string, sucursalgrupo : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, cuentacontabledefecto : string, almacencodigo : string, racionperfil : string, departamento : string, provincia : string, codigopostal : string, pais : string, direccion : string, responsable : number): Promise<DtoComunAcSucursal[]> {
        var filtro = new DtoComunAcSucursal();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunAcSucursal[])
            .catch(error => []);
    } */

    public listarpaginado(filtro: FiltroComunAcSucursal): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarSucursales(filtro: FiltroComunAcSucursal): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarSucursales`, filtro,
            { responseType: 'blob' as 'json' });
    }
}