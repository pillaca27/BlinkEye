import { EmailConfiguracion } from 'src/app/royal/framework/modelo/correo/EmailConfiguracion';
import { EmailTransaccion } from 'src/app/royal/framework/modelo/correo/EmailTransaccion';

export class DtoComunEmailTest{	
    constructor() {
        this.config = new EmailConfiguracion();
        this.correo = new EmailTransaccion();
    }
    config: EmailConfiguracion;
    correo: EmailTransaccion;
}