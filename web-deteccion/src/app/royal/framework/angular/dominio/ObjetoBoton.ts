import { MenuItem } from 'primeng/api';

export class ObjetoBoton {

    nombre: string;
    
    icono: string;

    accion: string;

    clase: string;

    items: MenuItem[] = [];

    disabled?: boolean = false;
}

