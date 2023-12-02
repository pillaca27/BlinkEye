import { DtoWsDefinicionpropiedad } from "./DtoWsDefinicionpropiedad";
export class DtoWsDefinicion {
    idDefinicion: number;
    idApi: number;
    nombre: string;
    propiedades: DtoWsDefinicionpropiedad[];
    constructor() {
        this.propiedades = [];
    }
}