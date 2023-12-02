import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';
import { MenuItem, SelectItem, MessageService, LazyLoadEvent, ConfirmationService } from 'primeng/api';
import { DominioMensajeUsuario } from '@framework/modelo/generico/DominioMensajeUsuario';
import { ElementRef } from '@angular/core';
import { Calendar } from 'primeng/calendar';
import { Dropdown } from 'primeng/dropdown';
import { InputNumber } from 'primeng/inputnumber';
export class FormularioComponent extends PrincipalBaseComponent {
    constructor(        
        noAuthorizationInterceptor: NoAuthorizationInterceptor,
        messageService: MessageService,        
        servicioComun: ServicioComunService        
         ) {     
             super(noAuthorizationInterceptor, messageService, servicioComun);          
        }


        validarControlesRequeridos(lstRequeridos: any[]): DominioMensajeUsuario[] {
            var mensajes = [];
            lstRequeridos.forEach(reg => {
    
                var valor = null;
                var title = '';
    
                if (reg.control instanceof ElementRef) {
                    valor = (reg.control as ElementRef).nativeElement.value;
                    title = (reg.control as ElementRef).nativeElement.title;
                }
                /*if (reg.control instanceof InputTextarea) {
                    valor = (reg.control as InputTextarea).
                    title = (reg.control as InputTextarea).nativeElement.title;
                }*/
                if (reg.control instanceof InputNumber) {
                    valor = (reg.control as InputNumber).value;
                    title = (reg.control as InputNumber).title;
                    //title = (reg.control as InputNumber).placeholder;
                }
                if (reg.control instanceof Calendar) {
                    var localc = (reg.control as Calendar).el;
                    valor = (reg.control as Calendar).value;
                    //title = (reg.control as Calendar).placeholder;
                    title = localc.nativeElement.title;
                }
                if (reg.control instanceof Dropdown) {
                    var localc = (reg.control as Calendar).el;
                    valor = (reg.control as Dropdown).value;
                    //title = (reg.control as Dropdown).tooltip;
                    //title = (reg.control as Dropdown).title; //si se muestra como emnsaje
                    //title = (reg.control as Dropdown).placeholder;
                    title = localc.nativeElement.title;
                }
    
                if (valor == null || valor == '') {
                    var m = new DominioMensajeUsuario();
                    m.tipoMensaje = 'warn';
                    m.titulo = 'Campo requerido';
                    m.mensaje = title;
                    mensajes.push(m);
                } else if (reg.regexp != null && reg.regexp != undefined) {
                    if (!new RegExp(reg.regexp.formato).test(valor)) {
                        var m = new DominioMensajeUsuario();
                        m.tipoMensaje = 'warn';
                        m.titulo = 'Formato: ' + reg.regexp.nombre;
                        m.mensaje = title;
                        mensajes.push(m);
                    }
                }
    
            });
            return mensajes;
        }
}