import { DtoComunWhContrato } from './../../wh/dominio/dto/DtoComunWhContrato';
 
import { Observable } from 'rxjs';
import { DtoComunMaMaestroMiscelaneosdetalle } from '@framework-comun/core/dominio/dto/DtoComunMaMaestroMiscelaneosdetalle';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DtoComunTipodocumentocxp } from '../dominio/dto/DtoComunTipodocumentocxp';
import { DtoComunMaPersonagrupo } from '../dominio/dto/DtoComunMaPersonagrupo';
import { FiltroComunMaPersonagrupo } from '../dominio/filtro/FiltroComunMaPersonagrupo';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';

@Injectable()
export class MaPersonagrupoComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/mapersonagrupocomun/`;
    private urlcomun = `${this.config.getEnv('spring-framework-comun-api')}/spring/core/mamaestromiscelaneosdetallecomun/`;
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
    
    public obtenerTabla(personagrupo: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=personagrupo;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

    public listarFiltros(personagrupo: string,descripcionlocal: string,estado: string): Promise<DtoTabla[]> {
        var filtro = new DtoTabla();
        filtro.codigo=personagrupo;
        filtro.nombre=descripcionlocal;
        filtro.estadoId=estado;
        return this.http.put(this.url + 'listarfiltros',filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    
    /*public listarMonedas(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarMonedas')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }*/








    public listarTablaPorHeader(aplicacioncodigo: string,codigotabla: string): Promise<DtoTabla[]> {
        var filtro =new DtoComunMaMaestroMiscelaneosdetalle();
        filtro.aplicacioncodigo=aplicacioncodigo;
        filtro.maestrocodigo=codigotabla;        
        return this.listarTablaPorHeaderCore(filtro);
    }

    public listarTablaPorHeaderCore(filtro: DtoComunMaMaestroMiscelaneosdetalle): Promise<DtoTabla[]> {
        return this.config.getHttp().put(this.urlcomun + 'listartablaporheader', filtro)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    public obtenerDtoPorId(personagrupo : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, codigointerno : string, generarnumeracionflag : string): Promise<DtoComunMaPersonagrupo> {
        var dto = new DtoComunMaPersonagrupo();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunMaPersonagrupo)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunMaPersonagrupo): Promise<DtoComunMaPersonagrupo> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunMaPersonagrupo)
            .catch(error => null);
    }

    public registrar(dto: DtoComunMaPersonagrupo): Promise<DtoComunMaPersonagrupo> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunMaPersonagrupo)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunMaPersonagrupo): Promise<DtoComunMaPersonagrupo> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunMaPersonagrupo)
            .catch(error => null);
    }

    public anular(dto: DtoComunMaPersonagrupo): Promise<DtoComunMaPersonagrupo> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunMaPersonagrupo)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunMaPersonagrupo): Promise<DtoComunMaPersonagrupo> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunMaPersonagrupo)
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

    public listarDtoFiltros(personagrupo : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, codigointerno : string, generarnumeracionflag : string): Promise<DtoComunMaPersonagrupo[]> {
        var filtro = new DtoComunMaPersonagrupo();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunMaPersonagrupo[])
            .catch(error => []);
    }

    public listarDtoActivos(personagrupo : string, descripcionlocal : string, descripcioningles : string, estado : string, ultimousuario : string, ultimafechamodif : Date, codigointerno : string, generarnumeracionflag : string): Promise<DtoComunMaPersonagrupo[]> {
        var filtro = new DtoComunMaPersonagrupo();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunMaPersonagrupo[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunMaPersonagrupo): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarTipoPersona(filtro: FiltroComunMaPersonagrupo): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarTipoPersona`, filtro,
            { responseType: 'blob' as 'json' });
    }

    /* COMUN SELECTORS */
    public listargrupopersonas(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listargrupopersonas')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarMonedas(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarMonedas')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarContratosOrdenCompra(): Promise<DtoComunWhContrato[]> {
        return this.config.getHttp().get(this.url + 'listarContratosOrdenCompra')
            .toPromise()
            .then(response => response as DtoComunWhContrato[])
            .catch(error => []);
    }

    public listarTipoServicio(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipoServicio')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarFormaPago(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarFormaPago')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarComprador(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarComprador')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }


    public listarAlmacenes(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarAlmacenes')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    public listarTipoDocumento(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipoDocumento')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    public listarTipoPago(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipoPago')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    public listarDetracciones(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarDetracciones')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    public listarImpuesto(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarImpuesto')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    public listarDepartamentos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarDepartamentos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarResponsables(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarResponsables')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarBancos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarBancos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarRegimenFiscal(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarRegimenFiscal')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarTipoVoucher(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipoVoucher')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarSunat(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarSunat')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarElement(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarElement')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarPrime(digito:number): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = digito+'';
        return this.config.getHttp().put(this.url + 'listarPrime',dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarReferenceFieldMst(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarReferenceFieldMst')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarPrimeType(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarPrimeType')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarPrimePadre(digito:string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dt = new DtoTabla();
        dt.codigo = digito+'';
        return this.config.getHttp().put(this.url + 'listarPrimePadre', dt)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarCostCenterMajor(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarCostCenterMajor')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarGrupoSucursal(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarGrupoSucursal')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarTipoProyecto(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipoProyecto')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarGrupoProyecto(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarGrupoProyecto')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarSucusal(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarSucusal')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarClasificacion(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarClasificacion')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarCostoDestino(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarCostoDestino')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public validacionLinea(linea:string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = linea;
        return this.config.getHttp().put(this.url + 'validacionLinea', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public validacionSubFamilia(linea:string, familia:string,subfamilia:string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = linea;
        dto.descripcion = familia;
        dto.nombre = subfamilia;
        return this.config.getHttp().put(this.url + 'validacionSubFamilia', dto )
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public validacionFamilia(linea:string, familia:string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = linea;
        dto.descripcion = familia;
        return this.config.getHttp().put(this.url + 'validacionFamilia', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarMarcas(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarMarcas')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarModelos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarModelos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarProcedencia(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarProcedencia')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarAbc(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarAbc')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public validarCuentas(cuenta:string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = cuenta;
        return this.config.getHttp().put(this.url + 'validarCuentas', dto )
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public obtenerCuentaLineaFamilia(linea:string,familia:string): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        var dto = new DtoTabla();
        dto.codigo = linea;
        dto.descripcion = familia;
        return this.config.getHttp().put(this.url + 'obtenerCuentaLineaFamilia', dto )
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public SetProveedor(proveedor): Promise<DtoTabla[]> {
        //GET CON CUERPO ACT
        return this.config.getHttp().get(this.url + 'SetProveedor/'+proveedor)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarImportesOC(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarImportesOC')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarClasificacionWH(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarClasificacionWH')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    public listarTipolicitacion(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipolicitacion')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public tipoCambio(fecha:string): Promise<DtoTabla[]> {
        //GET CON CUERPO
        var dt = new DtoTabla();
        dt.codigo = fecha;
        return this.config.getHttp().put(this.url + 'tipoCambio', dt)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarResponsablesContrato(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarResponsablesContrato')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarClasificacionContrato(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarClasificacionContrato')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarTipoContrato(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipoContrato')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarClasificacionwh(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarClasificacionwh')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarTipoItem(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipoItem')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarMotivos(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarMotivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarTipoAdenda(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarTipoAdenda')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarModalidadContratacion(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarModalidadContratacion')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarUnidades(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarUnidades')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarDepartamental(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarDepartamental')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarCategorias(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarCategorias')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarEstadosWF(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarEstadosWF')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    public listarEstadosGP(): Promise<DtoTabla[]> {
        return this.config.getHttp().get(this.url + 'listarEstadosGP')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
}