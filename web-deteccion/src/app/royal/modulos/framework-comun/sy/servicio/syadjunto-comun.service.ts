import { Observable } from 'rxjs';
import { SyAdjunto } from './../dominio/dto/SyAdjunto';
import { DtoComunSyReporte } from '../dominio/dto/DtoComunSyReporte';
import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { DominioMensajeUsuario } from '../../../../framework/modelo/generico/DominioMensajeUsuario';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FiltroComunSyReporte } from '../dominio/filtro/FiltroComunSyReporte';

@Injectable()
export class SyAdjuntoComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/sistema/syadjunto/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    public obtenerDto(bean: SyAdjunto): Promise<SyAdjunto> {
        return this.http.put(this.url + 'obtenerdto', bean)
            .toPromise()
            .then(response => response as SyAdjunto)
            .catch(error => null);
    }
    
    public actualizar(bean: SyAdjunto): Promise<any> {
        return this.http.put(this.url + 'actualizar', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    public descargarArchivo(bean: SyAdjunto): Promise<any> {
        return this.http.put(this.url + 'descargarArchivo', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }    

    public registrar(bean: SyAdjunto): Promise<any> {
        return this.http.post(this.url + 'registrar', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }

    public registrarWH(bean: SyAdjunto): Promise<any> {
        return this.http.post(this.url + 'registrarWH', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }
    public registrarLstWH(bean: SyAdjunto[]): Promise<any> {
        return this.http.post(this.url + 'registrarLstWH', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }
    public actualizarWH(bean: SyAdjunto): Promise<any> {
        return this.http.put(this.url + 'actualizarWH', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }
    public eliminar(bean: SyAdjunto): Promise<any> {
        return this.http.post(this.url + 'eliminar', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }
    public eliminarWH(bean: SyAdjunto): Promise<any> {
        return this.http.post(this.url + 'eliminarWH', bean)
            .toPromise()
            .then(response => response as any)
            .catch(error => null);
    }
    public listar(bean: SyAdjunto): Promise<SyAdjunto[]> {
        return this.http.post(this.url + 'listar', bean)
            .toPromise()
            .then(response => response as SyAdjunto[])
            .catch(error => []);
    }    


    downloadFile(clavetabla: string, nombretabla: string, secuencia: number ): Observable<Blob> {
        const params = new HttpParams()
            .set('clavetabla', clavetabla)
            .set('nombretabla', nombretabla)
            .set('secuencia', JSON.stringify(secuencia));

            var dto = new DtoTabla();
            dto.codigo=clavetabla;
            dto.descripcion=nombretabla;
            dto.id=secuencia;

        //GET CON CUERPO ACT
        return this.config.getHttp().put<Blob>(`${this.url}descargarArchivo`, dto,
            { responseType: 'blob' as 'json' });
    }

}