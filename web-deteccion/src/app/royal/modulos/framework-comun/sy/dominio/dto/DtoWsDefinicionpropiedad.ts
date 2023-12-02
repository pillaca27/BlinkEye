import { DtoWsDefinicion } from "./DtoWsDefinicion";

export class DtoWsDefinicionpropiedad {
    nombre: string;
    tipodato: string;
    requerido: string;
    definicion: DtoWsDefinicion;
    idDefinicion: number;
    constructor() {
    }
}