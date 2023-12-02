import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { EmpleadomastTransaccion } from './../../../../framework/modelo/EmpleadomastTransaccion';
import { DominioArchivo } from './../../../../framework/modelo/generico/DominioArchivo';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
 
import { FiltroComunEmpleadomastListaRapida } from '../dominio/filtro/FiltroComunEmpleadomastListaRapida';
 
import { DtoComunEmpleadomast } from '../dominio/dto/DtoComunEmpleadomast';
import { FiltroComunEmpleado } from '../dominio/filtro/FiltroComunEmpleado';

@Injectable()
export class EmpleadomastComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/core/empleadomastcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }
    
    /*obtenerDto(empleado: number): Promise<DtoComunEmpleadomast> {
        var dtoempleado = new DtoComunEmpleadomast();
        dtoempleado.empleado = empleado;
        return this.http.put(this.url + 'obtenerdto', dtoempleado)
            .toPromise()
            .then(response => response as DtoComunEmpleadomast)
            .catch(error => new DtoComunEmpleadomast());
    }*/

    public obtenerFotoActual(): Promise<DominioArchivo> {
        return this.config.getHttp().get(this.url + 'listarporusuarioactual')
            .toPromise()
            .then(response => response as DominioArchivo)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //99-EMPLEA-COBTAPI
    obtenerEmpleadoPorDtoApi(compania: string, empleado: number): Promise<EmpleadomastTransaccion> {
        var dtoempleado = new EmpleadomastTransaccion();
        dtoempleado.companiasocio = compania;
        dtoempleado.persona = empleado;
        dtoempleado.empleado = empleado;
        return this.http.put(this.url + 'obtenerempleadopordtoapi', dtoempleado)
            .toPromise()
            .then(response => response as EmpleadomastTransaccion)
            .catch(error => new EmpleadomastTransaccion());
    }

    //QQUECHOD VALIDADO
    //99-EMPLEA-COBAPPE
    obtenerEmpleadoPorDtoApiPersona(empleado: number): Promise<EmpleadomastTransaccion> {
        var dtoempleado = new EmpleadomastTransaccion();
        dtoempleado.persona = empleado;
        return this.http.put(this.url + 'obtenerempleadopordtoapipersona', dtoempleado)
            .toPromise()
            .then(response => response as EmpleadomastTransaccion)
            .catch(error => new EmpleadomastTransaccion());
    }

    //QQUECHOD VALIDADO
    //99-EMPLEA-CLISRAP
    listaRapida(compania: string, busqueda: string, estadoempleado: string): Promise<EmpleadomastTransaccion[]> {
        var filtro = new FiltroComunEmpleadomastListaRapida();
        filtro.companiasocio = compania;
        filtro.busqueda = busqueda;
        filtro.estadoempleado = estadoempleado;
        return this.http.put(this.url + 'listarapida', filtro)
            .toPromise()
            .then(response => response as EmpleadomastTransaccion[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-EMPLEA-CLISRAP 
    listaRapidaActivos(compania: string, busqueda: string): Promise<EmpleadomastTransaccion[]> {
        var filtro = new FiltroComunEmpleadomastListaRapida();
        filtro.companiasocio = compania;
        filtro.busqueda = busqueda;
        return this.http.put(this.url + 'listarapidaactivos', filtro)
            .toPromise()
            .then(response => response as EmpleadomastTransaccion[])
            .catch(error => []);
    }


    listarcumpleanios(filtro: any): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarcumpleanios', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    listaraniversarios(filtro: any): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listaraniversarios', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }






    public obtenerDtoPorId(empleado : number, companiasocio : string, tipopago : string, tipotrabajador : string, tipoplanilla : string, raza : string, religion : string, tipovisa : string, visafechainicio : Date, visafechaexpiracion : Date, serviciomilitardesde : Date, serviciomilitarhasta : Date, numeroarchivo : string, unidadnegocioasignada : string, locaciondepago : string, codigousuario : string, tipoasistenciasocial : string, centroasistenciasocial : string, tipocarnetasistenciasocial : string, carnetasistenciasocial : string, tipopension : string, fechainiciopension : Date, fechaterminopension : Date, codigoafp : string, numeroafp : string, bancocts : string, tipocuentacts : string, tipomonedacts : string, numerocuentacts : string, estadoempleado : string, fechaingreso : Date, fechaultimaplanilla : Date, tipocontrato : string, fechainiciocontrato : Date, fechafincontrato : Date, fechacese : Date, razoncese : string, motivocese : number, contratista : number, centrocostos : string, afe : string, deptoorganizacion : string, departamentooperacional : string, tipohorario : number, gradosalario : string, cargo : string, nivelacceso : string, flagipssvida : string, flagacctrabajo : string, correointerno : string, sueldoactuallocal : number, sueldoactualdolar : number, sueldoanteriorlocal : number, sueldoanteriordolar : number, monedapago : string, perfil : number, foto : string, fechaliquidacion : Date, fechareingreso : Date, unidadreplicacion : string, codigocargo : number, ultimafechapagovacacion : Date, estado : string, sucursal : string, ruccentroasistenciasocial : string, tarjetadecredito : string, plantillaconcepto : number, flagsmf : string, fechavacaciones : Date, flagtrabajadorpensionista : string, flagsctrsalud : string, flagsctrpension : string, flagdiscapacidad : string, flagsujetocontrol : string, flagsindicalizado : string, flagdomiciliado : string, niveleducativortps : string, flagregimenalternativo : string, flagjornadamaxima : string, flaghorarionocturno : string, flagotrosquinta : string, flagquintaexonerada : string, situacionespecial : string, flagmadreresponsabilidad : string, flagcentroformacion : string, tipomodalidadformativa : string, prestadorservicio : string, flagasegurapension : string, categoriaocupacional : string, flagconveniodobletrib : string, firmadigitalizada : string, flagdeconfianza : string, fechabajaeps : Date, codigounidad : number, division : string, oficina : string, responsableempleado : string, responsablejefe : string, tipocomisionafp : string, locacionasignada : string, ultimousuario : string, ultimafechamodif : Date, motivo : string, costcenterdestination : string, unidadtrabajo : string, jeferesponsable : number, ordenOrganigrama : string, unidadoperativa : string, empleadorelacionado : number, posicion : string, grupoocupacional : number, estadonivelacion : string, aprobadornivelacion : number, solicitadornivelacion : number, flageducacioncompletaiep : string): Promise<DtoComunEmpleadomast> {
        var dto = new DtoComunEmpleadomast();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunEmpleadomast)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunEmpleadomast): Promise<DtoComunEmpleadomast> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunEmpleadomast)
            .catch(error => null);
    }

    public registrar(dto: DtoComunEmpleadomast): Promise<DtoComunEmpleadomast> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunEmpleadomast)
            .catch(error => null);
    }

    public actualizar(dto: DtoComunEmpleadomast): Promise<DtoComunEmpleadomast> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunEmpleadomast)
            .catch(error => null);
    }

    public anular(dto: DtoComunEmpleadomast): Promise<DtoComunEmpleadomast> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunEmpleadomast)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunEmpleadomast): Promise<DtoComunEmpleadomast> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunEmpleadomast)
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

    public listarDtoFiltros(empleado : number, companiasocio : string, tipopago : string, tipotrabajador : string, tipoplanilla : string, raza : string, religion : string, tipovisa : string, visafechainicio : Date, visafechaexpiracion : Date, serviciomilitardesde : Date, serviciomilitarhasta : Date, numeroarchivo : string, unidadnegocioasignada : string, locaciondepago : string, codigousuario : string, tipoasistenciasocial : string, centroasistenciasocial : string, tipocarnetasistenciasocial : string, carnetasistenciasocial : string, tipopension : string, fechainiciopension : Date, fechaterminopension : Date, codigoafp : string, numeroafp : string, bancocts : string, tipocuentacts : string, tipomonedacts : string, numerocuentacts : string, estadoempleado : string, fechaingreso : Date, fechaultimaplanilla : Date, tipocontrato : string, fechainiciocontrato : Date, fechafincontrato : Date, fechacese : Date, razoncese : string, motivocese : number, contratista : number, centrocostos : string, afe : string, deptoorganizacion : string, departamentooperacional : string, tipohorario : number, gradosalario : string, cargo : string, nivelacceso : string, flagipssvida : string, flagacctrabajo : string, correointerno : string, sueldoactuallocal : number, sueldoactualdolar : number, sueldoanteriorlocal : number, sueldoanteriordolar : number, monedapago : string, perfil : number, foto : string, fechaliquidacion : Date, fechareingreso : Date, unidadreplicacion : string, codigocargo : number, ultimafechapagovacacion : Date, estado : string, sucursal : string, ruccentroasistenciasocial : string, tarjetadecredito : string, plantillaconcepto : number, flagsmf : string, fechavacaciones : Date, flagtrabajadorpensionista : string, flagsctrsalud : string, flagsctrpension : string, flagdiscapacidad : string, flagsujetocontrol : string, flagsindicalizado : string, flagdomiciliado : string, niveleducativortps : string, flagregimenalternativo : string, flagjornadamaxima : string, flaghorarionocturno : string, flagotrosquinta : string, flagquintaexonerada : string, situacionespecial : string, flagmadreresponsabilidad : string, flagcentroformacion : string, tipomodalidadformativa : string, prestadorservicio : string, flagasegurapension : string, categoriaocupacional : string, flagconveniodobletrib : string, firmadigitalizada : string, flagdeconfianza : string, fechabajaeps : Date, codigounidad : number, division : string, oficina : string, responsableempleado : string, responsablejefe : string, tipocomisionafp : string, locacionasignada : string, ultimousuario : string, ultimafechamodif : Date, motivo : string, costcenterdestination : string, unidadtrabajo : string, jeferesponsable : number, ordenOrganigrama : string, unidadoperativa : string, empleadorelacionado : number, posicion : string, grupoocupacional : number, estadonivelacion : string, aprobadornivelacion : number, solicitadornivelacion : number, flageducacioncompletaiep : string): Promise<DtoComunEmpleadomast[]> {
        var filtro = new DtoComunEmpleadomast();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunEmpleadomast[])
            .catch(error => []);
    }

    public listarDtoActivos(empleado : number, companiasocio : string, tipopago : string, tipotrabajador : string, tipoplanilla : string, raza : string, religion : string, tipovisa : string, visafechainicio : Date, visafechaexpiracion : Date, serviciomilitardesde : Date, serviciomilitarhasta : Date, numeroarchivo : string, unidadnegocioasignada : string, locaciondepago : string, codigousuario : string, tipoasistenciasocial : string, centroasistenciasocial : string, tipocarnetasistenciasocial : string, carnetasistenciasocial : string, tipopension : string, fechainiciopension : Date, fechaterminopension : Date, codigoafp : string, numeroafp : string, bancocts : string, tipocuentacts : string, tipomonedacts : string, numerocuentacts : string, estadoempleado : string, fechaingreso : Date, fechaultimaplanilla : Date, tipocontrato : string, fechainiciocontrato : Date, fechafincontrato : Date, fechacese : Date, razoncese : string, motivocese : number, contratista : number, centrocostos : string, afe : string, deptoorganizacion : string, departamentooperacional : string, tipohorario : number, gradosalario : string, cargo : string, nivelacceso : string, flagipssvida : string, flagacctrabajo : string, correointerno : string, sueldoactuallocal : number, sueldoactualdolar : number, sueldoanteriorlocal : number, sueldoanteriordolar : number, monedapago : string, perfil : number, foto : string, fechaliquidacion : Date, fechareingreso : Date, unidadreplicacion : string, codigocargo : number, ultimafechapagovacacion : Date, estado : string, sucursal : string, ruccentroasistenciasocial : string, tarjetadecredito : string, plantillaconcepto : number, flagsmf : string, fechavacaciones : Date, flagtrabajadorpensionista : string, flagsctrsalud : string, flagsctrpension : string, flagdiscapacidad : string, flagsujetocontrol : string, flagsindicalizado : string, flagdomiciliado : string, niveleducativortps : string, flagregimenalternativo : string, flagjornadamaxima : string, flaghorarionocturno : string, flagotrosquinta : string, flagquintaexonerada : string, situacionespecial : string, flagmadreresponsabilidad : string, flagcentroformacion : string, tipomodalidadformativa : string, prestadorservicio : string, flagasegurapension : string, categoriaocupacional : string, flagconveniodobletrib : string, firmadigitalizada : string, flagdeconfianza : string, fechabajaeps : Date, codigounidad : number, division : string, oficina : string, responsableempleado : string, responsablejefe : string, tipocomisionafp : string, locacionasignada : string, ultimousuario : string, ultimafechamodif : Date, motivo : string, costcenterdestination : string, unidadtrabajo : string, jeferesponsable : number, ordenOrganigrama : string, unidadoperativa : string, empleadorelacionado : number, posicion : string, grupoocupacional : number, estadonivelacion : string, aprobadornivelacion : number, solicitadornivelacion : number, flageducacioncompletaiep : string): Promise<DtoComunEmpleadomast[]> {
        var filtro = new DtoComunEmpleadomast();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunEmpleadomast[])
            .catch(error => []);
    }

    //MALCAN VALIDADO
    //EMPLEADOMAST PAGINADO
    public listarPaginadoCore(dto: FiltroComunEmpleado): Promise<DominioPaginacion> {        
        return this.config.getHttp().post(this.url + 'listarpaginadocore', dto)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

}