import { DtoWfEstado } from './../dominio/dto/DtoWfEstado';
import { Injectable } from '@angular/core';
import { AppConfig } from '@env/appconfig';
import { HttpClient } from '@angular/common/http';
import { DtoWfProceso } from './../dominio/dto/DtoWfProceso';
import { WfProceso } from './../dominio/dto/WfProceso';
import { DtoTabla } from 'src/app/royal/framework/modelo/generico/dto/DtoTabla';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoSelectorDinamico } from './../dominio/dto/DtoSelectorDinamico';
import { FiltroSyDocumentos } from './../dominio/filtro/FiltroSyDocumentos';

@Injectable()
export class WfProcesoServicio {

    private rutaServicio = `${this.config.getEnv('spring-framework-workflow-api')}spring/workflow/wfproceso/`;

    constructor(private http: HttpClient, private config: AppConfig) { }

    public obtenerProcesoPorId(uuid: string): Promise<DtoWfProceso> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.put(this.rutaServicio + 'obtenerProcesoPorId/', dto)
            .toPromise()
            .then(response => response as DtoWfProceso)
            .catch(error => new DtoWfProceso());
    }

    public obtenerPlantillasRelacionadas(uuid): Promise<any[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.put(this.rutaServicio + 'obtenerPlantillasRelacionadas/', dto)
            .toPromise()
            .then(response => response as any[])
            .catch(error => []);
    }

    public listarProcesos(filtro: DtoTabla): Promise<DtoWfProceso[]> {
        return this.http.post(this.rutaServicio + 'listarProcesos', filtro)
            .toPromise()
            .then(response => response as DtoWfProceso[])
            .catch(error => []);
    }

    public listarProcesosSinVersion(filtro: DtoTabla): Promise<DtoWfProceso[]> {
        return this.http.post(this.rutaServicio + 'listarProcesosSinVersion', filtro)
            .toPromise()
            .then(response => response as DtoWfProceso[])
            .catch(error => []);
    }

    public registrarProceso(bean: DtoWfProceso): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'registrarProceso', bean)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => null);
    }

    public actualizaProceso(bean: DtoWfProceso): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'actualizaProceso', bean)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => null);
    }

    public listarSelector(id: string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = id;
        return this.http.put(this.rutaServicio + 'listarSelector', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => null);
    }

    public listarPorAplicacionWF(aplicacion: string): Promise<WfProceso[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = aplicacion;
        return this.http.put(this.rutaServicio + 'listarPorAplicacionWF', dto)
            .toPromise()
            .then(response => {
                return response as WfProceso[];
            })
            .catch(error => new Array());
    }

    public listarWF(): Promise<WfProceso[]> {
        return this.http.get(this.rutaServicio + 'listarWF')
            .toPromise()
            .then(response => {
                return response as WfProceso[];
            })
            .catch(error => new Array());
    }

    public listarEstadosPorProceso(proceso: string): Promise<DtoWfEstado[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = proceso;
        return this.http.put(this.rutaServicio + 'listarEstadosPorProceso', dto)
            .toPromise()
            .then(response => {
                return response as DtoWfEstado[];
            })
            .catch(error => new Array());
    }

    public listarSelectorpaginado(filtro: DtoSelectorDinamico): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'listarSelectorpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => null);
    }

    public exportar(uuid: string): Promise<DtoTabla> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = uuid;
        return this.http.put(this.rutaServicio + 'exportar', dto)
            .toPromise()
            .then(response => {
                return response as DtoTabla;
            })
            .catch(error => new DtoTabla());
    }

    public importar(dto: DtoTabla): Promise<DtoTabla> {
        return this.http.post(this.rutaServicio + 'importar', dto)
            .toPromise()
            .then(response => {
                return response as DtoTabla;
            })
            .catch(error => new DtoTabla());
    }

    public listarSyTipoDocumentos(filtro: FiltroSyDocumentos): Promise<DominioPaginacion> {
        return this.http.post(this.rutaServicio + 'listarSyTipoDocumentos', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public syprocesomst(): Promise<DtoTabla[]> {
        return this.http.get(this.rutaServicio + 'syprocesomst')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
}
