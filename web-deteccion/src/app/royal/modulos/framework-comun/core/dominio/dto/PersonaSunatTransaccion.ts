export class PersonaSunatTransaccion{

    constructor(){
        this.actividadesEconomicas = [];
        this.comprobantesPago = [];
        this.sistemaEmisionElectronica = [];
        this.padrones = [];
    }

    ruc: string;
	tipoContribuyente: string;
	nombreComercial: string;
	fechaInscripcion: string;
	fechaInicioActividades: string;
	estadoContribuyente: string;
	condicionContribuyente: string;
	domicilioFiscal: string;
	sistemaEmisionComprobante: string;
	actividadComercioExterior: string;
	sistemaContabilidiad: string;
	actividadesEconomicas: string[];
	comprobantesPago: string[];
	sistemaEmisionElectronica: string[];
	emisorElectronicoDesde: string;
	comprobantesElectronicos: string;
	afiliadoPLEDesde: string;
	padrones: string[];

}