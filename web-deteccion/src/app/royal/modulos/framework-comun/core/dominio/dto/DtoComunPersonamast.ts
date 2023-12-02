import { DtoComunMaPersonapersonagrupo } from './DtoComunMaPersonapersonagrupo';
 
import { DtoComunProveedormast } from './DtoComunProveedormast';
import { SeguridadUsuarioActual } from '@framework/modelo/seguridad/SeguridadUsuarioActual';
 
 
import { DominioTransaccion } from '../../../../../framework/modelo/generico/DominioTransaccion';
import { DtoComunMaPersonacuentabancaria } from './DtoComunMaPersonacuentabancaria';

export class DtoComunPersonamast extends DominioTransaccion {

    constructor(){
        super();
        this.esEmpleadoC=false
        this.esProveedorC=false
        this.esClienteC=false
        this.domicliadoPais=false
        this.esproveedor="N"
        this.escliente="N"
        this.esempleado="N"
        this.usuarioActual= new SeguridadUsuarioActual()
        this.dtoProveedor=new DtoComunProveedormast()
        this.dtoMaPersonapersonagrupo=[]
        this.dtoPersonacuentabancaria=[]
        this.flagRuc=true
    }


    persona : number;
    origen : string;
    apellidopaterno : string;
    apellidomaterno : string;
    nombres : string;
    nombrecompleto : string;
    busqueda : string;
    tipodocumento : string;
    documento : string;
    codigobarras : string;
    tipopersonausuario : string;
    escliente : string;
    esproveedor : string;
    esempleado : string;
    esotro : string;
    tipopersona : string;
    fechanacimiento : Date;
    ciudadnacimiento : string;
    sexo : string;
    nacionalidad : string;
    estadocivil : string;
    nivelinstruccion : string;
    direccion : string;
    codigopostal : string;
    departamento : string;
    telefono : string;
    fax : string;
    documentofiscal : string;
    documentoidentidad : string;
    carnetextranjeria : string;
    documentomilitarfa : string;
    tipobrevete : string;
    brevete : string;
    pasaporte : string;
    nombreemergencia : string;
    direccionemergencia : string;
    telefonoemergencia : string;
    bancomonedalocal : string;
    tipocuentalocal : string;
    cuentamonedalocal : string;
    bancomonedaextranjera : string;
    tipocuentaextranjera : string;
    cuentamonedaextranjera : string;
    personaant : string;
    correoelectronico : string;
    clasepersonacodigo : string;
    estado : string;
    ultimousuario : string;
    ultimafechamodif : Date;
    cuentamonedalocalTmp : string;
    cuentamonedaextranjeraTmp : string;
    enfermedadgraveflag : string;
    provincia : string;
    tarjetadecredito : string;
    flagactualizacion : string;
    ingresofecharegistro : Date;
    ingresoaplicacioncodigo : string;
    ingresousuario : string;
    pymeflag : string;
    grupoempresarial : string;
    celular : string;
    celularemergencia : string;
    lugarnacimiento : string;
    parentescoemergencia : string;
    personaclasificacion : string;
    direccionreferencia : string;
    flagrepetido : string;
    coddiscamec : string;
    fecinidiscamec : Date;
    fecfindiscamec : Date;
    codlicarma : string;
    marcaarma : string;
    seriearma : string;
    inicioarma : Date;
    vencimientoarma : Date;
    segurodiscamec : string;
    correlativosctr : string;
    sunatzona : string;
    sunatubigeo : string;
    sunatnacionalidad : string;
    sunatvia : string;
    sunatdomiciliado : string;
    codigoldn : string;
    paisemisor : string;
    sunatndconvenio : string;
    sunatndtiporenta : string;
    sunatndexoneracion : string;
    sunatndservicio : string;
    codigousuario : string;
    pais : string;
    flagsolicitausuario : string;
    carnetextranjeriaFecvcto : Date;
    breveteFecvcto : Date;
    codigointerbancario : string;














   
    sunatconvenio : string;
 
    correoelectronicootros:string

    esProveedorC?:boolean
    esClienteC?:boolean
    esEmpleadoC?:boolean
    esPersonaC?:boolean
    domicliadoPais?:boolean
    ubigeo?:string
    flagRuc:boolean

    usuarioActual:SeguridadUsuarioActual
    dtoProveedor:DtoComunProveedormast
    dtoMaPersonapersonagrupo:DtoComunMaPersonapersonagrupo[]
    dtoPersonacuentabancaria:DtoComunMaPersonacuentabancaria[]
}