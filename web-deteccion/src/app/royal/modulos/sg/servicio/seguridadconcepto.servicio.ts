import { FiltroSeguridadconcepto } from './../dominio/filtro/FiltroSeguridadconcepto';
 
import { DtoSeguridadconcepto } from './../dominio/dto/DtoSeguridadconcepto';
 
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '@framework/modelo/generico/dto/DtoTabla';
 

@Injectable()
export class SeguridadconceptoService {

    private url = `${this.config.getEnv('spring-erp-sg-api')}spring/sg/seguridadconcepto/`;
    constructor(private config: AppConfig) { }

    public obtenerDtoPorId(aplicacioncodigo : string, grupo : string, concepto : string, descripcion : string, descripcioningles : string, visibleflag : string, tipodedetalle : string, tipodeobjeto : string, objeto : string, tipodeacceso : string, objetowindow : string, workflag : string, workagregarflag : string, workmodificarflag : string, workborrarflag : string, workaprobarflag : string, webflag : string, webpage : string, webaction : string, webgrupo : string, webgruposecuencia : number, ultimousuario : string, ultimafechamodif : Date, tipodeacceso2 : string, tipodeacceso3 : string, tipodeacceso4 : string, workprocesarflag : string, workopcion1flag : string, workopcion2flag : string, workopcion3flag : string, workopcion4flag : string, workopcion5flag : string, orden : number, imagen : string, jerarquia : string, uuid : string): Promise<DtoSeguridadconcepto> {
        var dto = new DtoSeguridadconcepto();
        dto.aplicacioncodigo = aplicacioncodigo;
        dto.grupo = grupo;
        dto.concepto = concepto;
        dto.descripcion = descripcion;
        dto.descripcioningles = descripcioningles;
        dto.visibleflag = visibleflag;
        dto.tipodedetalle = tipodedetalle;
        dto.tipodeobjeto = tipodeobjeto;
        dto.objeto = objeto;
        dto.tipodeacceso = tipodeacceso;
        dto.objetowindow = objetowindow;
        dto.workflag = workflag;
        dto.workagregarflag = workagregarflag;
        dto.workmodificarflag = workmodificarflag;
        dto.workborrarflag = workborrarflag;
        dto.workaprobarflag = workaprobarflag;
        dto.webflag = webflag;
        dto.webpage = webpage;
        dto.webaction = webaction;
        dto.webgrupo = webgrupo;
        dto.webgruposecuencia = webgruposecuencia;
        dto.ultimousuario = ultimousuario;
        dto.ultimafechamodif = ultimafechamodif;
        dto.tipodeacceso2 = tipodeacceso2;
        dto.tipodeacceso3 = tipodeacceso3;
        dto.tipodeacceso4 = tipodeacceso4;
        dto.workprocesarflag = workprocesarflag;
        dto.workopcion1flag = workopcion1flag;
        dto.workopcion2flag = workopcion2flag;
        dto.workopcion3flag = workopcion3flag;
        dto.workopcion4flag = workopcion4flag;
        dto.workopcion5flag = workopcion5flag;
        dto.orden = orden;
        dto.imagen = imagen;
        dto.jerarquia = jerarquia;
        dto.uuid = uuid;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSeguridadconcepto)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoSeguridadconcepto): Promise<DtoSeguridadconcepto> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoSeguridadconcepto)
            .catch(error => null);
    }

    public registrar(dto: DtoSeguridadconcepto): Promise<DtoSeguridadconcepto> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoSeguridadconcepto)
            .catch(error => null);
    }

    public actualizar(dto: DtoSeguridadconcepto): Promise<DtoSeguridadconcepto> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoSeguridadconcepto)
            .catch(error => null);
    }

    public anular(dto: DtoSeguridadconcepto): Promise<DtoSeguridadconcepto> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoSeguridadconcepto)
            .catch(error => null);
    }

    public activar(dto: DtoSeguridadconcepto): Promise<DtoSeguridadconcepto> {
        return this.config.getHttp().put(this.url + 'activar', dto)
            .toPromise()
            .then(response => response as DtoSeguridadconcepto)
            .catch(error => null);
    }

    public eliminar(dto: DtoSeguridadconcepto): Promise<DtoSeguridadconcepto> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoSeguridadconcepto)
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

    public listarDtoFiltros(aplicacioncodigo : string, grupo : string, concepto : string, descripcion : string, descripcioningles : string, visibleflag : string, tipodedetalle : string, tipodeobjeto : string, objeto : string, tipodeacceso : string, objetowindow : string, workflag : string, workagregarflag : string, workmodificarflag : string, workborrarflag : string, workaprobarflag : string, webflag : string, webpage : string, webaction : string, webgrupo : string, webgruposecuencia : number, ultimousuario : string, ultimafechamodif : Date, tipodeacceso2 : string, tipodeacceso3 : string, tipodeacceso4 : string, workprocesarflag : string, workopcion1flag : string, workopcion2flag : string, workopcion3flag : string, workopcion4flag : string, workopcion5flag : string, orden : number, imagen : string, jerarquia : string, uuid : string): Promise<DtoSeguridadconcepto[]> {
        var filtro = new DtoSeguridadconcepto();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.grupo = grupo;
        filtro.concepto = concepto;
        filtro.descripcion = descripcion;
        filtro.descripcioningles = descripcioningles;
        filtro.visibleflag = visibleflag;
        filtro.tipodedetalle = tipodedetalle;
        filtro.tipodeobjeto = tipodeobjeto;
        filtro.objeto = objeto;
        filtro.tipodeacceso = tipodeacceso;
        filtro.objetowindow = objetowindow;
        filtro.workflag = workflag;
        filtro.workagregarflag = workagregarflag;
        filtro.workmodificarflag = workmodificarflag;
        filtro.workborrarflag = workborrarflag;
        filtro.workaprobarflag = workaprobarflag;
        filtro.webflag = webflag;
        filtro.webpage = webpage;
        filtro.webaction = webaction;
        filtro.webgrupo = webgrupo;
        filtro.webgruposecuencia = webgruposecuencia;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        filtro.tipodeacceso2 = tipodeacceso2;
        filtro.tipodeacceso3 = tipodeacceso3;
        filtro.tipodeacceso4 = tipodeacceso4;
        filtro.workprocesarflag = workprocesarflag;
        filtro.workopcion1flag = workopcion1flag;
        filtro.workopcion2flag = workopcion2flag;
        filtro.workopcion3flag = workopcion3flag;
        filtro.workopcion4flag = workopcion4flag;
        filtro.workopcion5flag = workopcion5flag;
        filtro.orden = orden;
        filtro.imagen = imagen;
        filtro.jerarquia = jerarquia;
        filtro.uuid = uuid;
        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoSeguridadconcepto[])
            .catch(error => []);
    }

    public listarDtoActivos(aplicacioncodigo : string, grupo : string, concepto : string, descripcion : string, descripcioningles : string, visibleflag : string, tipodedetalle : string, tipodeobjeto : string, objeto : string, tipodeacceso : string, objetowindow : string, workflag : string, workagregarflag : string, workmodificarflag : string, workborrarflag : string, workaprobarflag : string, webflag : string, webpage : string, webaction : string, webgrupo : string, webgruposecuencia : number, ultimousuario : string, ultimafechamodif : Date, tipodeacceso2 : string, tipodeacceso3 : string, tipodeacceso4 : string, workprocesarflag : string, workopcion1flag : string, workopcion2flag : string, workopcion3flag : string, workopcion4flag : string, workopcion5flag : string, orden : number, imagen : string, jerarquia : string, uuid : string): Promise<DtoSeguridadconcepto[]> {
        var filtro = new DtoSeguridadconcepto();
        filtro.aplicacioncodigo = aplicacioncodigo;
        filtro.grupo = grupo;
        filtro.concepto = concepto;
        filtro.descripcion = descripcion;
        filtro.descripcioningles = descripcioningles;
        filtro.visibleflag = visibleflag;
        filtro.tipodedetalle = tipodedetalle;
        filtro.tipodeobjeto = tipodeobjeto;
        filtro.objeto = objeto;
        filtro.tipodeacceso = tipodeacceso;
        filtro.objetowindow = objetowindow;
        filtro.workflag = workflag;
        filtro.workagregarflag = workagregarflag;
        filtro.workmodificarflag = workmodificarflag;
        filtro.workborrarflag = workborrarflag;
        filtro.workaprobarflag = workaprobarflag;
        filtro.webflag = webflag;
        filtro.webpage = webpage;
        filtro.webaction = webaction;
        filtro.webgrupo = webgrupo;
        filtro.webgruposecuencia = webgruposecuencia;
        filtro.ultimousuario = ultimousuario;
        filtro.ultimafechamodif = ultimafechamodif;
        filtro.tipodeacceso2 = tipodeacceso2;
        filtro.tipodeacceso3 = tipodeacceso3;
        filtro.tipodeacceso4 = tipodeacceso4;
        filtro.workprocesarflag = workprocesarflag;
        filtro.workopcion1flag = workopcion1flag;
        filtro.workopcion2flag = workopcion2flag;
        filtro.workopcion3flag = workopcion3flag;
        filtro.workopcion4flag = workopcion4flag;
        filtro.workopcion5flag = workopcion5flag;
        filtro.orden = orden;
        filtro.imagen = imagen;
        filtro.jerarquia = jerarquia;
        filtro.uuid = uuid;
        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoSeguridadconcepto[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroSeguridadconcepto): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}
