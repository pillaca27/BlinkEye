import { FiltroComunAcCostcentermst } from '../dominio/filtro/FiltroComunAcCostcentermst';
import { DtoTabla } from '../../../../framework/modelo/generico/dto/DtoTabla';
import { AppConfig } from '../../../../../../environments/appconfig';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { DominioPaginacion } from '@framework/modelo/generico/DominioPaginacion';
import { DtoComunAcCostcentermst } from '../dominio/dto/DtoComunAcCostcentermst';
import { FiltroComunAcCostcentergroup } from '../dominio/filtro/FiltroComunAcCostcentergroup';
import { Observable } from 'rxjs';

@Injectable()
export class AcCostcenterComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}/spring/contabilidad/accostcentermstcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //AC-CCOSTOS-LISTAR
    public listar(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listar')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //AC-CCOSTOS-LISACT
    public listarActivos(): Promise<DtoTabla[]> {                
        return this.config.getHttp().get(this.url + 'listaractivos')
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    
    //QQUECHOD VALIDADO
    //AC-CCOSTOS-OBTTAB
    public obtenerTabla(costcenter: string): Promise<DtoTabla> {
        var filtro = new DtoTabla();
        filtro.codigo=costcenter;
        return this.http.put(this.url + 'obtenertabla', filtro)
            .toPromise()
            .then(response => response as DtoTabla)
            .catch(error => new DtoTabla());
    } 

 /*    //QQUECHOD VALIDADO
    //AC-CCOSTOS-OBTDTO
    public obtenerDto(costcenter: string): Promise<DtoComunAcCostcentermst> {
        var filtro = new DtoComunAcCostcentermst();
        filtro.costcenter=costcenter;
        return this.http.put(this.url + 'obtenerdto', filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst)
            .catch(error => new DtoComunAcCostcentermst());
    }  */

    //QQUECHOD VALIDADO
    //AC-CCOSTOS-LISDTOFIL
    public listarDtoFiltros(costcenter: string,localname: string,status: string): Promise<DtoComunAcCostcentermst[]> {
        var filtro = new DtoComunAcCostcentermst();
        filtro.costcenter=costcenter;
        filtro.localname=localname;
        filtro.status=status;
        return this.http.put(this.url + 'listardtofiltros',filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst[])
            .catch(error => []);
    }
    public listarDto(): Promise<DtoComunAcCostcentermst[]> {
        return this.listarDtoFiltros(null,null,null);
    }
    public listarDtoActivos(): Promise<DtoComunAcCostcentermst[]> {
        return this.listarDtoFiltros(null,null,"A");
    }

    //QQUECHOD VALIDADO
    //AC-CCOSTOS-C0002
    listarautorizados(filtro: FiltroComunAcCostcentermst): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarautorizados', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion);
    }

    //QQUECHOD VALIDADO
    //AC-CCOSTOS-C0003
    listarsupervisados(filtro: FiltroComunAcCostcentermst): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarsupervisados', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion);
    }

    //QQUECHOD VALIDADO
    //AC-CCOSTOS-C0004
    listarempleados(filtro: FiltroComunAcCostcentermst): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarempleados', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion);
    }


    //QQUECHOD VALIDADO
    //AC-CCOSTOS-TODOS
    listarTodos(filtro: FiltroComunAcCostcentermst): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarTodos', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion);
    }


     /********************* MANTENIMIENTO ******************/

     public obtenerDtoPorId(costcenter:string): Promise<DtoComunAcCostcentermst> {
        var dto = new DtoComunAcCostcentermst();
        dto.costcenter = costcenter;
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunAcCostcentermst): Promise<DtoComunAcCostcentermst> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst)
            .catch(error => null);
    }

    public registrar(dto: DtoComunAcCostcentermst): Promise<DtoComunAcCostcentermst> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunAcCostcentermst): Promise<DtoComunAcCostcentermst> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst)
            .catch(error => null);
    }

    public anular(dto: DtoComunAcCostcentermst): Promise<DtoComunAcCostcentermst> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunAcCostcentermst): Promise<DtoComunAcCostcentermst> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst)
            .catch(error => null);
    }

/*     public listar(): Promise<DtoTabla[]> {
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

    public listarDtoFiltros(costcenter : string, localname : string, englishname : string, costcenterclasification : string, costcentergroup : string, costcentersubgroup : string, costcenterrelation : string, responsible : string, expensefinanceflag : string, expenseadministrativeflag : string, expensesalesflag : string, expenseproductionflag : string, costcenternext : string, vendor : number, amountinvoices : number, amountrequisitions : number, amountadvances : number, amountothers01 : number, amountothers02 : number, amountothers03 : number, vendorsignfile : string, account : string, status : string, lastuser : string, lastdate : Date, costproductionlevel : string, accountdestination : string, costcentertype : string, incomeflag : string, accountinflation : string, expensedirectflag : string, expensefixedflag : string, sucursal : string, internalnumber : string, racionperfil : string, racionperfilmantenimiento : string, address : string, multicompanyflag : string, cpautomaticinvoflag : string, cpautomaticrequflag : string): Promise<DtoComunAcCostcentermst[]> {
        var filtro = new DtoComunAcCostcentermst();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst[])
            .catch(error => []);
    }

    public listarDtoActivos(costcenter : string, localname : string, englishname : string, costcenterclasification : string, costcentergroup : string, costcentersubgroup : string, costcenterrelation : string, responsible : string, expensefinanceflag : string, expenseadministrativeflag : string, expensesalesflag : string, expenseproductionflag : string, costcenternext : string, vendor : number, amountinvoices : number, amountrequisitions : number, amountadvances : number, amountothers01 : number, amountothers02 : number, amountothers03 : number, vendorsignfile : string, account : string, status : string, lastuser : string, lastdate : Date, costproductionlevel : string, accountdestination : string, costcentertype : string, incomeflag : string, accountinflation : string, expensedirectflag : string, expensefixedflag : string, sucursal : string, internalnumber : string, racionperfil : string, racionperfilmantenimiento : string, address : string, multicompanyflag : string, cpautomaticinvoflag : string, cpautomaticrequflag : string): Promise<DtoComunAcCostcentermst[]> {
        var filtro = new DtoComunAcCostcentermst();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunAcCostcentermst[])
            .catch(error => []);
    } */

    public listarpaginado(filtro: FiltroComunAcCostcentermst): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public listarpaginadoGrupo(filtro: FiltroComunAcCostcentergroup): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginadoGrupo', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public exportarCentroCostos(filtro: FiltroComunAcCostcentermst): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarCentroCostos`, filtro,
            { responseType: 'blob' as 'json' });
    }
}