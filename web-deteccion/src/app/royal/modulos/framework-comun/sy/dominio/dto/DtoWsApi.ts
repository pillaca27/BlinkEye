import { DtoWsApipath } from "./DtoWsApipath";
import { DtoWsDefinicion } from "./DtoWsDefinicion";

export class DtoWsApi {

    idApi: number;
    host: string;
    nombre: string;
    paths: DtoWsApipath[];
    definiciones: DtoWsDefinicion[];

    constructor() {
        this.paths = [];
        this.definiciones = [];
    }
}