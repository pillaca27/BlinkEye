import { EmailConfiguracion } from './../../../../framework/modelo/correo/EmailConfiguracion';
import { AppConfig } from '../../../../../../environments/appconfig';
import { DtoComunEmailTest } from '../dominio/dto/DtoComunEmailTest';
import { Injectable, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { EmailTransaccion } from '@framework/modelo/correo/EmailTransaccion';


@Injectable()
export class SyCorreoComunServicio {

    private rutaServicio = `${this.config.getEnv('spring-framework-comun-api')}/spring/sistema/sycorreocomun/`;
    private rutaServicioMotor = `${this.config.getEnv('spring-framework-comun-api')}/publico/correomotor/`;
    constructor(private http: HttpClient, private config: AppConfig) { }

    //QQUECHOD VALIDADO
    //SY-CORREO-CONFIG
    public obtenerConfiguracion(): Promise<EmailConfiguracion> {
        return this.http.get(this.rutaServicio + 'obtenerconfiguracion')
          .toPromise()
          .then(response => response as EmailConfiguracion)
          .catch(error => new EmailConfiguracion());          
    }

    //QQUECHOD VALIDADO
    //SY-CORREO-CENVIAR
    public enviarCorreo( emailEnviar: EmailTransaccion ): Promise<EmailTransaccion> {                
        return this.http.put(this.rutaServicio + 'enviarcorreo', emailEnviar)
            .toPromise()
            .then(response => response as EmailTransaccion)
            .catch(error => null);
    }

    //QQUECHOD VALIDADO
    //SY-CORREO-CENVPRB
    public enviarCorreoPrueba( emailEnviar: DtoComunEmailTest ): Promise<EmailTransaccion> {                
      return this.http.put(this.rutaServicio + 'enviarcorreoprueba', emailEnviar)
          .toPromise()
          .then(response => response as EmailTransaccion)
          .catch(error => null);
  }
 
  public enviarCorreoPruebaAlertas( emailEnviar: DtoComunEmailTest ): Promise<EmailTransaccion> {                
    return this.http.put(this.rutaServicioMotor + 'enviarcorreo', emailEnviar.correo)
        .toPromise()
        .then(response => response as EmailTransaccion)
        .catch(error => null);
  }

  

}
