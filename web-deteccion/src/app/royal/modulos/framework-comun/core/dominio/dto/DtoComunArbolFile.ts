export class DtoArbolFile {
    data: DtoFile[];
    constructor() {
        this.data = [];
    }
}
export class DtoFile {
    label: string;
    icon: string;
    expandedIcon: string;
    collapsedIcon: string;
    children: DtoFile[];
    constructor() {
        this.children = [];
    }
}