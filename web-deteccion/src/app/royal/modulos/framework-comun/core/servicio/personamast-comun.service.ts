import { DtoTabla } from './../../../../framework/modelo/generico/dto/DtoTabla';
import { Observable } from 'rxjs';
import { FiltroComunPersonamast } from './../dominio/filtro/FiltroComunPersonamast';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DominioPaginacion } from '../../../../framework/modelo/generico/DominioPaginacion';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { FiltroComunPersonamastclis001 } from '../dominio/filtro/FiltroComunPersonamastclis001';
import { PersonamastTransaccion } from '@framework/modelo/PersonamastTransaccion';
import { FiltroComunPersonamastListaRapida } from '../dominio/filtro/FiltroComunPersonamastListaRapida';
import { DtoComunPersonamast } from '../dominio/dto/DtoComunPersonamast';
import { ParametroTransaccion } from '@framework/modelo/ParametroTransaccion';

@Injectable()
export class PersonamastComunService {

    private url = `${this.config.getEnv('spring-framework-comun-api')}spring/core/personamastcomun/`;
    private urlparametros = `${this.config.getEnv('spring-framework-comun-api')}spring/core/parametrosmastcomun/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    /*obtenerDto(persona: number): Promise<DtoComunPersonamast> {
        var dtoempleado = new DtoComunPersonamast();
        dtoempleado.persona = persona;
        return this.http.put(this.url + 'obtenerdto', dtoempleado)
            .toPromise()
            .then(response => response as DtoComunPersonamast)
            .catch(error => new DtoComunPersonamast());
    }*/

    //QQUECHOD VALIDADO
    //99-PERSON-COBTAPI
    obtenerPersonaPorDtoApi(persona: number): Promise<PersonamastTransaccion> {
        var dtoempleado = new PersonamastTransaccion();
        dtoempleado.persona = persona;
        return this.http.put(this.url + 'obtenerpersonapordtoapi', dtoempleado)
            .toPromise()
            .then(response => response as PersonamastTransaccion)
            .catch(error => new PersonamastTransaccion());
    }
 
    //QQUECHOD VALIDADO
    //99-PERSON-CLIS001
    clis001(filtro: FiltroComunPersonamastclis001): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'clis001', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    //QQUECHOD VALIDADO
    //99-PERSON-CLISRAP
    listaRapida(busqueda: string,estado:string): Promise<PersonamastTransaccion[]> {
        var filtro = new FiltroComunPersonamastListaRapida();
        filtro.busqueda = busqueda;
        filtro.estado=estado;
        return this.http.put(this.url + 'listarapida', filtro)
            .toPromise()
            .then(response => response as PersonamastTransaccion[])
            .catch(error => []);
    }

    //QQUECHOD VALIDADO
    //99-PERSON-CLISRAP
    listaRapidaActivos(busqueda: string): Promise<PersonamastTransaccion[]> {
        var filtro = new FiltroComunPersonamastListaRapida();
        filtro.busqueda = busqueda;
        return this.http.put(this.url + 'listarapidaactivos', filtro)
            .toPromise()
            .then(response => response as PersonamastTransaccion[])
            .catch(error => []);
    }


    listarclienteproveedorotro(filtro: FiltroComunPersonamastclis001): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarclienteproveedorotro', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    listargestor(filtro: FiltroComunPersonamastclis001): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listargestor', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    listarTiposProveedor(filtro: FiltroComunPersonamastclis001): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarTiposProveedor', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    listarTiposProveedorCotizacion(filtro: FiltroComunPersonamastclis001): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarTiposProveedorCotizacion', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    listarproveedor(filtro: FiltroComunPersonamastclis001): Promise<DominioPaginacion> {
        return this.http.put(this.url + 'listarproveedor', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }


    obtenerTipoProveedor(codPersona: number): Promise<PersonamastTransaccion> {
        var filtro: FiltroComunPersonamastclis001 = new FiltroComunPersonamastclis001()
        filtro.persona = codPersona;
        return this.http.put(this.url + 'obtenerTipoProveedor', filtro)
            .toPromise()
            .then(response => response as PersonamastTransaccion)
            .catch(error => new PersonamastTransaccion());
    }


    /*public parametroobtener(companiaCodigo: string, aplicacionCodigo: string, parametroClave: string): Promise<ParametroTransaccion> {
        var parametrotransaccion: ParametroTransaccion = new ParametroTransaccion();
        parametrotransaccion.companiacodigo = companiaCodigo;
        parametrotransaccion.aplicacioncodigo = aplicacionCodigo;
        parametrotransaccion.parametroclave = parametroClave;      
        return this.config.getHttp().put(this.urlparametros + 'parametroobtener', parametrotransaccion)
            .toPromise()
            .then(response => response as ParametroTransaccion)
            .catch(error => null);
    }*/ 


    






    public obtenerDtoPorId(persona : number, origen : string, apellidopaterno : string, apellidomaterno : string, nombres : string, nombrecompleto : string, busqueda : string, tipodocumento : string, documento : string, codigobarras : string, escliente : string, esproveedor : string, esempleado : string, esotro : string, tipopersona : string, fechanacimiento : Date, ciudadnacimiento : string, sexo : string, nacionalidad : string, estadocivil : string, nivelinstruccion : string, direccion : string, codigopostal : string, provincia : string, departamento : string, telefono : string, fax : string, documentofiscal : string, documentoidentidad : string, carnetextranjeria : string, documentomilitarfa : string, tipobrevete : string, brevete : string, pasaporte : string, nombreemergencia : string, direccionemergencia : string, telefonoemergencia : string, bancomonedalocal : string, tipocuentalocal : string, cuentamonedalocal : string, bancomonedaextranjera : string, tipocuentaextranjera : string, cuentamonedaextranjera : string, personaant : string, correoelectronico : string, clasepersonacodigo : string, enfermedadgraveflag : string, estado : string, ultimousuario : string, ultimafechamodif : Date, tipopersonausuario : string, ingresofecharegistro : Date, ingresoaplicacioncodigo : string, ingresousuario : string, pymeflag : string, grupoempresarial : string, personaclasificacion : string, tarjetadecredito : string, flagactualizacion : string, celular : string, parentescoemergencia : string, celularemergencia : string, lugarnacimiento : string, sunatnacionalidad : string, sunatvia : string, sunatzona : string, sunatubigeo : string, sunatdomiciliado : string, breveteFecvcto : Date, paisemisor : string, codigoldn : string, codigointerbancario : string, direccionreferencia : string, sunatconvenio : string, flagsolicitausuario : string, carnetextranjeriaFecvcto : Date, pais : string, sunatndconvenio : string, sunatndtiporenta : string, sunatndexoneracion : string, sunatndservicio : string): Promise<DtoComunPersonamast> {
        var dto = new DtoComunPersonamast();

        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunPersonamast)
            .catch(error => null);
    }

    public obtenerDto(dto: DtoComunPersonamast): Promise<DtoComunPersonamast> {
        return this.config.getHttp().put(this.url + 'obtenerdto', dto)
            .toPromise()
            .then(response => response as DtoComunPersonamast)
            .catch(error => null);
    }

    public registrar(dto: DtoComunPersonamast): Promise<DtoComunPersonamast> {
        return this.config.getHttp().post(this.url + 'registrar', dto)
            .toPromise()
            .then(response => response as DtoComunPersonamast)
            .catch(error => null);
    }

    public listarZonas(dto: DtoComunPersonamast): Promise<DtoTabla[]> {
        return this.config.getHttp().post(this.url + 'listarZonas', dto)
            .toPromise()
            .then(response => response as DtoTabla[])
            .catch(error => []);
    }
    public actualizar(dto: DtoComunPersonamast): Promise<DtoComunPersonamast> {
        return this.config.getHttp().put(this.url + 'actualizar', dto)
            .toPromise()
            .then(response => response as DtoComunPersonamast)
            .catch(error => null);
    }
    public actualizarMasivo(dto: DtoComunPersonamast[]): Promise<DtoComunPersonamast[]> {
        return this.config.getHttp().put(this.url + 'actualizarMasivo', dto)
            .toPromise()
            .then(response => response as DtoComunPersonamast)
            .catch(error => null);
    }

    public anular(dto: DtoComunPersonamast): Promise<DtoComunPersonamast> {
        return this.config.getHttp().put(this.url + 'anular', dto)
            .toPromise()
            .then(response => response as DtoComunPersonamast)
            .catch(error => null);
    }

    public eliminar(dto: DtoComunPersonamast): Promise<DtoComunPersonamast> {
        return this.config.getHttp().put(this.url + 'eliminar', dto)
            .toPromise()
            .then(response => response as DtoComunPersonamast)
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

    public listarDtoFiltros(persona : number, origen : string, apellidopaterno : string, apellidomaterno : string, nombres : string, nombrecompleto : string, busqueda : string, tipodocumento : string, documento : string, codigobarras : string, escliente : string, esproveedor : string, esempleado : string, esotro : string, tipopersona : string, fechanacimiento : Date, ciudadnacimiento : string, sexo : string, nacionalidad : string, estadocivil : string, nivelinstruccion : string, direccion : string, codigopostal : string, provincia : string, departamento : string, telefono : string, fax : string, documentofiscal : string, documentoidentidad : string, carnetextranjeria : string, documentomilitarfa : string, tipobrevete : string, brevete : string, pasaporte : string, nombreemergencia : string, direccionemergencia : string, telefonoemergencia : string, bancomonedalocal : string, tipocuentalocal : string, cuentamonedalocal : string, bancomonedaextranjera : string, tipocuentaextranjera : string, cuentamonedaextranjera : string, personaant : string, correoelectronico : string, clasepersonacodigo : string, enfermedadgraveflag : string, estado : string, ultimousuario : string, ultimafechamodif : Date, tipopersonausuario : string, ingresofecharegistro : Date, ingresoaplicacioncodigo : string, ingresousuario : string, pymeflag : string, grupoempresarial : string, personaclasificacion : string, tarjetadecredito : string, flagactualizacion : string, celular : string, parentescoemergencia : string, celularemergencia : string, lugarnacimiento : string, sunatnacionalidad : string, sunatvia : string, sunatzona : string, sunatubigeo : string, sunatdomiciliado : string, breveteFecvcto : Date, paisemisor : string, codigoldn : string, codigointerbancario : string, direccionreferencia : string, sunatconvenio : string, flagsolicitausuario : string, carnetextranjeriaFecvcto : Date, pais : string, sunatndconvenio : string, sunatndtiporenta : string, sunatndexoneracion : string, sunatndservicio : string): Promise<DtoComunPersonamast[]> {
        var filtro = new DtoComunPersonamast();

        return this.config.getHttp().put(this.url + 'listardtofiltros', filtro)
            .toPromise()
            .then(response => response as DtoComunPersonamast[])
            .catch(error => []);
    }

    public listarDtoActivos(persona : number, origen : string, apellidopaterno : string, apellidomaterno : string, nombres : string, nombrecompleto : string, busqueda : string, tipodocumento : string, documento : string, codigobarras : string, escliente : string, esproveedor : string, esempleado : string, esotro : string, tipopersona : string, fechanacimiento : Date, ciudadnacimiento : string, sexo : string, nacionalidad : string, estadocivil : string, nivelinstruccion : string, direccion : string, codigopostal : string, provincia : string, departamento : string, telefono : string, fax : string, documentofiscal : string, documentoidentidad : string, carnetextranjeria : string, documentomilitarfa : string, tipobrevete : string, brevete : string, pasaporte : string, nombreemergencia : string, direccionemergencia : string, telefonoemergencia : string, bancomonedalocal : string, tipocuentalocal : string, cuentamonedalocal : string, bancomonedaextranjera : string, tipocuentaextranjera : string, cuentamonedaextranjera : string, personaant : string, correoelectronico : string, clasepersonacodigo : string, enfermedadgraveflag : string, estado : string, ultimousuario : string, ultimafechamodif : Date, tipopersonausuario : string, ingresofecharegistro : Date, ingresoaplicacioncodigo : string, ingresousuario : string, pymeflag : string, grupoempresarial : string, personaclasificacion : string, tarjetadecredito : string, flagactualizacion : string, celular : string, parentescoemergencia : string, celularemergencia : string, lugarnacimiento : string, sunatnacionalidad : string, sunatvia : string, sunatzona : string, sunatubigeo : string, sunatdomiciliado : string, breveteFecvcto : Date, paisemisor : string, codigoldn : string, codigointerbancario : string, direccionreferencia : string, sunatconvenio : string, flagsolicitausuario : string, carnetextranjeriaFecvcto : Date, pais : string, sunatndconvenio : string, sunatndtiporenta : string, sunatndexoneracion : string, sunatndservicio : string): Promise<DtoComunPersonamast[]> {
        var filtro = new DtoComunPersonamast();

        return this.config.getHttp().put(this.url + 'listardtoactivos', filtro)
            .toPromise()
            .then(response => response as DtoComunPersonamast[])
            .catch(error => []);
    }

    public listarpaginado(filtro: FiltroComunPersonamast): Promise<DominioPaginacion> {
        filtro.paginacion.paginacionListaResultado = null;
        return this.config.getHttp().put(this.url + 'listarpaginado', filtro)
            .toPromise()
            .then(response => response as DominioPaginacion)
            .catch(error => new DominioPaginacion());
    }

    public parametroobtener(companiaCodigo: string, aplicacionCodigo: string, parametroClave: string): Promise<ParametroTransaccion> {
        var parametrotransaccion: ParametroTransaccion = new ParametroTransaccion();
        parametrotransaccion.companiacodigo = companiaCodigo;
        parametrotransaccion.aplicacioncodigo = aplicacionCodigo;
        parametrotransaccion.parametroclave = parametroClave;      
        return this.config.getHttp().put(this.urlparametros + 'parametroobtener', parametrotransaccion)
            .toPromise()
            .then(response => response as ParametroTransaccion)
            .catch(error => null);
    } 

    public exportarPersonas(filtro: FiltroComunPersonamast): Observable<Blob> {
        return this.config.getHttp().post<Blob>(`${this.url}/exportarPersonas`, filtro,
            { responseType: 'blob' as 'json' });
    }
}