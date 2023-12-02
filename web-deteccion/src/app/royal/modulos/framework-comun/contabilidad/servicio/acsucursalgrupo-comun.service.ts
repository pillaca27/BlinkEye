import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
import { DtoComunAcSucursalgrupo } from '../dominio/dto/DtoComunAcSucursalgrupo';
import { FiltroAcSucursalgrupo } from '../dominio/filtro/FiltroAcSucursalgrupo';
 

@Injectable()
export class AcSucursalgrupoComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/ac/acsucursalgrupo/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(sucursalgrupo : string, sucursalgrupomayor : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, uuid : string): Promise<DtoComunAcSucursalgrupo> {
        var dto = new DtoComunAcSucursalgrupo();
        dto.sucursalgrupo = sucursalgrupo;
        dto.sucursalgrupomayor = sucursalgrupomayor;
        dto.descripcionlocal = descripcionlocal;
        dto.descripcioningles = descripcioningles;
        dto.estado = estado;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        dto.uuid = uuid;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursalgrupo)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunAcSucursalgrupo): Promise<DtoComunAcSucursalgrupo> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursalgrupo)
            .catch(error => null);
    }

    public registrar(dto: DtoComunAcSucursalgrupo): Promise<DtoComunAcSucursalgrupo> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursalgrupo)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunAcSucursalgrupo): Promise<DtoComunAcSucursalgrupo> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursalgrupo)
            .catch(error => null);
    }

    public anular(dto: DtoComunAcSucursalgrupo): Promise<DtoComunAcSucursalgrupo> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursalgrupo)
            .catch(error => null);
    }

    public activar(dto: DtoComunAcSucursalgrupo): Promise<DtoComunAcSucursalgrupo> {
        return this.config.getHttp().put(this.url + 'activar', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursalgrupo)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunAcSucursalgrupo): Promise<DtoComunAcSucursalgrupo> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunAcSucursalgrupo)
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

    public listarDtoFiltros(sucursalgrupo : string, sucursalgrupomayor : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, uuid : string): Promise<DtoComunAcSucursalgrupo[]> {
        var filtro = new DtoComunAcSucursalgrupo();
        filtro.sucursalgrupo = sucursalgrupo;
        filtro.sucursalgrupomayor = sucursalgrupomayor;
        filtro.descripcionlocal = descripcionlocal;
        filtro.descripcioningles = descripcioningles;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        filtro.uuid = uuid;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunAcSucursalgrupo[])
            .catch(error => []);
    }

    public listarDtoActivos(sucursalgrupo : string, sucursalgrupomayor : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, uuid : string): Promise<DtoComunAcSucursalgrupo[]> {
        var filtro = new DtoComunAcSucursalgrupo();
        filtro.sucursalgrupo = sucursalgrupo;
        filtro.sucursalgrupomayor = sucursalgrupomayor;
        filtro.descripcionlocal = descripcionlocal;
        filtro.descripcioningles = descripcioningles;
        filtro.estado = estado;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        filtro.uuid = uuid;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunAcSucursalgrupo[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroAcSucursalgrupo): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
