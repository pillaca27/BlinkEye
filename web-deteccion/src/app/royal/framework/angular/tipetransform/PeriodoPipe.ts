import { Pipe, PipeTransform } from '@angular/core';
import { SelectItem } from 'primeng/api';

@Pipe({
    name: 'periodoPipe'
})

export class PeriodoPipe implements PipeTransform {

    constructor() { }

    transform(value: any) {
        if (value === undefined) {
            return '';
        }
        if (value === undefined) {
            return '';
        }
        if (value == null) {
            return '';
        }

        if (value.trim().length < 6) {
            return value;
        }

        var periodo = value as string;

        return periodo.trim().substring(0, 4) + '-' + periodo.trim().substring(4, 6);
    }
}
