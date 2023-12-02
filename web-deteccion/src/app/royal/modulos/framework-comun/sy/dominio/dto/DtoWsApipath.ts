import { DtoWsDefinicion } from "./DtoWsDefinicion";
import { DtoWsPathparam } from "./DtoWsPathparam";

export class DtoWsApipath {
    idApi: number;
    idPath: number;
    nombre: string;
    metodo: string;
    notas: string;

    tipodatoRequest: string;
    idDefinicionRequest: number;
    definicionRequest: DtoWsDefinicion;

    tipodatoResponse: string;
    idDefinicionResponse: number;
    definicionResponse: DtoWsDefinicion;

    ROWNUM_: number;
}
