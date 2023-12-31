import {
    Component, Input, ElementRef, AfterViewInit, ViewChild, EventEmitter, Output
} from '@angular/core';
import { fromEvent } from 'rxjs';
import { switchMap, takeUntil, pairwise } from 'rxjs/operators'

@Component({
    selector: 'app-canvas',
    template: '<canvas #canvas></canvas>',
    styles: ['canvas { border: 1px solid #000; position:absolute }']
})
export class CanvasComponent implements AfterViewInit {

    @ViewChild('canvas', { static: false }) public canvas: ElementRef;

    @Input() public width = 0;
    @Input() public height = 0;
    @Input() public dibujar = false;
    @Input() public canDrag = false;
    @Input() public absolute = true;

    @Output() guardarFirmaAction = new EventEmitter();
    @Output() refresh = new EventEmitter();

    cx: CanvasRenderingContext2D;

    redimensionar(w: number, h: number, z: number) {

        const canvasEl: HTMLCanvasElement = this.canvas.nativeElement;

        canvasEl.width = w;
        canvasEl.height = h;
        canvasEl.style.zIndex = '' + z;

        this.width = w;
        this.height = h;
    }

    public ngAfterViewInit() {
        const canvasEl: HTMLCanvasElement = this.canvas.nativeElement;
        this.cx = canvasEl.getContext('2d');

        canvasEl.width = this.width;
        canvasEl.height = this.height;

        if (this.dibujar) {
            this.cx.lineWidth = 2;
            this.cx.lineCap = 'round';
            this.cx.strokeStyle = '#000';
            this.captureEvents(canvasEl);
        }

        if (this.canDrag) {
            this.dragging(canvasEl);
        }
    }

    private dragging(canvasEl: HTMLCanvasElement) {
        //https://riptutorial.com/html5-canvas/example/18918/dragging-circles---rectangles-around-the-canvas

        fromEvent(canvasEl, 'mousedown')
            .pipe(
                switchMap((e) => {
                    // after a mouse down, we'll record all mouse moves
                    return fromEvent(canvasEl, 'mousemove')
                        .pipe(
                            // we'll stop (and unsubscribe) once the user releases the mouse
                            // this will trigger a 'mouseup' event    
                            takeUntil(fromEvent(canvasEl, 'mouseup')),
                            // we'll also stop (and unsubscribe) once the mouse leaves the canvas (mouseleave event)
                            takeUntil(fromEvent(canvasEl, 'mouseleave')),
                            // pairwise lets us get the previous value to draw a line from
                            // the previous point to the current point    
                            pairwise()
                        )
                })
            )
            .subscribe((res: [MouseEvent, MouseEvent]) => {
                const rect = canvasEl.getBoundingClientRect();

                // previous and current position with the offset
                const prevPos = {
                    x: res[0].clientX - rect.left,
                    y: res[0].clientY - rect.top
                };

                const currentPos = {
                    x: res[1].clientX - rect.left,
                    y: res[1].clientY - rect.top
                };

                // this method we'll implement soon to do the actual drawing
                this.drawOnCanvas2(prevPos, currentPos);
            });
    }

    private captureEvents(canvasEl: HTMLCanvasElement) {
        // this will capture all mousedown events from the canvas element
        fromEvent(canvasEl, 'mousedown')
            .pipe(
                switchMap((e) => {
                    // after a mouse down, we'll record all mouse moves
                    return fromEvent(canvasEl, 'mousemove')
                        .pipe(
                            // we'll stop (and unsubscribe) once the user releases the mouse
                            // this will trigger a 'mouseup' event    
                            takeUntil(fromEvent(canvasEl, 'mouseup')),
                            // we'll also stop (and unsubscribe) once the mouse leaves the canvas (mouseleave event)
                            takeUntil(fromEvent(canvasEl, 'mouseleave')),
                            // pairwise lets us get the previous value to draw a line from
                            // the previous point to the current point    
                            pairwise()
                        )
                })
            )
            .subscribe((res: [MouseEvent, MouseEvent]) => {
                const rect = canvasEl.getBoundingClientRect();

                // previous and current position with the offset
                const prevPos = {
                    x: res[0].clientX - rect.left,
                    y: res[0].clientY - rect.top
                };

                const currentPos = {
                    x: res[1].clientX - rect.left,
                    y: res[1].clientY - rect.top
                };

                // this method we'll implement soon to do the actual drawing
                this.drawOnCanvas(prevPos, currentPos);
            });
    }

    private drawOnCanvas(prevPos: { x: number, y: number }, currentPos: { x: number, y: number }) {
        if (!this.cx) { return; }
        this.cx.beginPath();
        if (prevPos) {
            this.cx.moveTo(prevPos.x, prevPos.y); // from
            this.cx.lineTo(currentPos.x, currentPos.y);
            this.cx.stroke();
        }
    }

    private drawOnCanvas2(prevPos: { x: number, y: number }, currentPos: { x: number, y: number }) {
        if (!this.cx) { return; }

        if (prevPos) {
            var x = parseInt('' + currentPos.x);
            var y = parseInt('' + currentPos.y);
            this.dibujarImagenRectangulo(x, y, this.w, this.h, this.img, this.wI, this.hI);
            this.refresh.emit();
        }
    }


    x: number;
    y: number;
    w: number;
    h: number;
    img: any;
    wI: number;
    hI: number;

    dibujarRectangulo(x, y, w, h) {

        this.cx.beginPath();
        this.cx.clearRect(0, 0, this.width, this.height);
        this.cx.rect(x, y, w, h);
        this.cx.stroke();

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    dibujarImagenRectangulo(x, y, w, h, img, wI, hI) {

        this.cx.beginPath();
        this.cx.clearRect(0, 0, this.width, this.height);
        this.cx.rect(x, y, w, h);
        this.cx.stroke();

        this.cx.drawImage(img, 0, 0, wI, hI, x, y, w, h);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.img = img;
        this.wI = wI;
        this.hI = hI;
    }


}
