import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { MessageService } from 'primeng/api';
import { CanvasComponent } from './canvas.component';

import * as pdfjsLib from 'pdfjs-dist/build/pdf';
import * as pdfjsWorker from 'pdfjs-dist/build/pdf.worker.entry';

import { PrincipalBaseComponent } from '@framework/angular/component/PrincipalBaseComponent';
import { NoAuthorizationInterceptor } from '@framework/angular/interceptor/NoAuthorizationInterceptor';
import { ServicioComunService } from '@framework-comun/servicioComun.service';

@Component({
    selector: 'app-firma',
    templateUrl: './firma.component.html'
})
export class FirmaComponent extends PrincipalBaseComponent implements OnInit {

    @Output() block = new EventEmitter();
    @Output() unBlock = new EventEmitter();
    @Output() aceptar = new EventEmitter();

    @ViewChild('cPDF', { static: false }) canvasPDF: CanvasComponent;
    @ViewChild('cIMG', { static: false }) canvasIMG: CanvasComponent;
    @ViewChild('cREN', { static: false }) canvasREN: CanvasComponent;

    constructor(
        noAuthorizationInterceptor: NoAuthorizationInterceptor, 
        servicioComun: ServicioComunService,
        messageService: MessageService
    ) { super(noAuthorizationInterceptor, messageService,servicioComun); }


    x: number = 0;
    y: number = 0;
    w: number = 0;
    h: number = 0;

    aceptarPosicion() {
        this.aceptar.emit();
    }

    async cargarObjeto(object: any) {
        this.block.emit();
        pdfjsLib.GlobalWorkerOptions.workerSrc = pdfjsWorker;
        pdfjsLib.disableWorker = true;

        const load = await pdfjsLib.getDocument(object).promise;
        load.getPage(1).then(
            p1 => {
                var myCanvas = this.canvasPDF.canvas.nativeElement;
                var context = myCanvas.getContext("2d");
                var scale = 1;
                var viewport = p1.getViewport({ scale: scale });
                myCanvas.width = viewport.width;
                myCanvas.height = viewport.height;

                p1.render({
                    canvasContext: context,
                    viewport: viewport
                });
                this.canvasIMG.redimensionar(viewport.width, viewport.height, 1);

                this.h = 40;
                this.w = 120;
                this.x = viewport.width - this.w - 20;
                this.y = viewport.height - this.h - 20;

                this.dibujarImagenEnRen();
            }
        );
    }

    async cargar() {
        //this.cargarObjeto('/assets/00000100_8480_20180505.pdf');
        this.cargarObjeto(this.base64Original);
    }

    verSelector: boolean = false;

    BASE64_MARKER = ';base64,';

    convertDataURIToBinary(base64) {

        var raw = '';

        var indice = base64.indexOf(this.BASE64_MARKER);

        if (indice > -1) {
            raw = window.atob(base64.substring(indice + this.BASE64_MARKER.length));
        }
        else {
            raw = window.atob(base64);
        }

        var rawLength = raw.length;
        var array = new Uint8Array(new ArrayBuffer(rawLength));

        for (var i = 0; i < rawLength; i++) {
            array[i] = raw.charCodeAt(i);
        }
        return array;
    }

    base64Original: Uint8Array;
    base64Firma: string;

    iniciarComponente(base64: string, base64Firma: string) {

        console.log("FirmaComponent.iniciarComponente");

        this.base64Firma = base64Firma;
        var pdfAsArray = this.convertDataURIToBinary(base64);
        this.base64Original = pdfAsArray;
        this.cargarObjeto(this.base64Original);
    }

    ngOnInit() {
        super.ngOnInit();
        this.w = 0;
        this.h = 0;
        this.x = 0;
        this.y = 0;
    }

    refresh() {
        this.x = this.canvasIMG.x;
        this.y = this.canvasIMG.height - this.h - this.canvasIMG.y;
    }

    cargarFolio(event: any) {

        this.block.emit();

        var files = event.files;

        if (files.length != 1) {
            this.unBlock.emit();
            return;
        }

        if (files[0].size > 3000000) {
            this.mostrarMensajeAdvertencia('El tamaño supera el límite de ' + 3 + 'mb');
            this.unBlock.emit();
            return null;
        }

        if (files[0].size == 0) {
            this.mostrarMensajeAdvertencia('El archivo se encuentra dañado.');
            this.desbloquearPagina();
            return null;
        }

        var reader = new FileReader();
        reader.readAsDataURL(files[0]);

        reader.onloadend = (evt) => {
            var result = reader.result;
            var archivo = result.toString();
            this.img.src = archivo;
            setTimeout(() => { this.cargarFolio2(); }, 700);
        };

    }

    verSelectorFirma = false;

    cargarFolio2() {
        var ctx = this.canvasREN.cx;
        ctx.drawImage(this.img, 0, 0, this.img.width, this.img.height, 0, 0, this.canvasREN.width, this.canvasREN.height);
        this.agregarImagen();
    }

    img: any = new Image();

    agregarImagen() {
        this.block.emit();
        var canvas = this.canvasREN.canvas.nativeElement;
        const base64 = canvas.toDataURL("image/png");
        this.img.src = base64;
        setTimeout(() => { this.agregarImagen2(); }, 700);
    }

    agregarImagen2() {
        this.canvasIMG.dibujarImagenRectangulo(this.x, this.canvasIMG.height - this.h - this.y, this.w, this.h, this.img, this.img.width, this.img.height);
        this.unBlock.emit();
        this.verSelector = true;
        this.cancelarCuadroImagen();
        this.cancelarCuadroTam();
    }

    dibujarImagen() {
        this.canvasREN.cx.beginPath();
        this.canvasREN.cx.clearRect(0, 0, this.canvasREN.width, this.canvasREN.height);
        this.canvasREN.cx.stroke();
        this.unBlock.emit();
        this.verSelector = true;
    }

    dibujarImagenEnRen() {
        if (this.estaVacio(this.base64Firma)) {
            this.dibujarImagen();
            return;
        }
        this.img.src = 'data:image/jpeg;base64,' + this.base64Firma;
        setTimeout(() => { this.cargarFolio2(); }, 700);
    }

    verSelectorTam: boolean = false;

    mostrarCuadroImagen() {

        this.canvasREN.cx.beginPath();
        this.canvasREN.cx.clearRect(0, 0, this.canvasREN.width, this.canvasREN.height);
        this.canvasREN.cx.stroke();

        this.verSelectorFirma = true;
    }

    aceptarCuadroImagen() {
        this.agregarImagen();
    }

    cancelarCuadroImagen() {
        this.verSelectorFirma = false;
    }

    mostrarCuadroTam() {
        this.verSelectorTam = true;
    }

    aceptarCuadroTam() {
        this.agregarImagen();
    }

    cancelarCuadroTam() {
        this.verSelectorTam = false;
    }
}