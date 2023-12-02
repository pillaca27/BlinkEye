import { Component, OnInit, ViewChild } from '@angular/core';
import { MessageService, SelectItem } from 'primeng/api';
import { Router, ActivatedRoute } from '@angular/router';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { ServicioComunService } from '@framework-comun/servicioComun.service';

@Component({
  selector: 'app-dialog-ayuda',
  templateUrl: './dialog-ayuda.html',
  styleUrls: []
})
export class DialogAyuda extends PrincipalBaseComponent implements OnInit {

  verModal: boolean = false;
  url: string = '';

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    servicioComun: ServicioComunService,
    noAuthorizationInterceptor: NoAuthorizationInterceptor, messageService: MessageService) {
    super(noAuthorizationInterceptor, messageService,servicioComun);
  }



  ngOnInit() {
  }

  iniciarComponente(ver: boolean, ruta: string) {
    console.log('ruta', ruta);
    this.verModal = ver;
    this.url = ruta;
  }

}
