import { DominioTransaccion } from '../generico/DominioTransaccion';
import { SeguridadUsuarioLogin } from './SeguridadUsuarioLogin';

export class SeguridadUsuarioActual extends SeguridadUsuarioLogin {
    personaId: number;
    personaTipoDocumentoId: string;
    personaNroDocumento: string;
    personaNombreCompleto: string;
    personaFotoUrl: string;
    personaNombres: string;
    personaApellidos: string;
    personaApellidoPaterno: string;
    personaApellidoMaterno: string;
    // unidadNegocioAsignadaCodigo: string;
    // unidadNegocioAsignadaNombre: string;
    // sucursalCodigo: string;
    // sucursalNombre: string;
    // departamentoCodigo: string;
    // departamentoNombre: string;
    // puestoEmpresaCodigo: string;
    // puestoEmpresaNombre: string;
    // centroCostosCodigo: string;
    // centroCostosNombre: string;
    unidadReplicacionCodigo: string;
    unidadReplicacionNombre: string;

    esAdministradorWh:string;
}