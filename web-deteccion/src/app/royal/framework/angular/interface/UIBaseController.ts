import { MensajeController } from './../dominio/MensajeController';

export declare interface UIBaseController {
    // generico 
    coreExportar(tipo: string): void;    
    coreMensaje(mensage: MensajeController): void;
    coreAccion(accion: string): void;
}
