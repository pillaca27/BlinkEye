package net.royal.spring.workflow.boot;

public class SpringWorkflowConstanteBoot {
	public static final String NOMBRE = "framework.workflow";

	public static final String WF_ACCION_APROBAR = "APROBAR";
	public static final String WF_ACCION_SEGUIMIENTO = "SEGUIMIENTO";
	public static final String WF_ACCION_RECHAZAR = "RECHAZAR";
	public static final String WF_ACCION_DEVOLVER = "DEVOLVER";

	public static final String WF_CONDICIONAPROBACION_SOLOUNO = "SOLOUNO";
	public static final String WF_CONDICIONAPROBACION_TODOS = "TODOS";
	public static final String WF_CONDICIONAPROBACION_TODOSUNAPR = "TODOSUNAPR";
	public static final String WF_CONDICIONAPROBACION_ALMENOS = "ALMENOS";
	
	public static final String WF_TIPOAPROBADOR_PERS = "PERS";
	public static final String WF_TIPOAPROBADOR_JEIN = "JEIN";
	public static final String WF_TIPOAPROBADOR_TRAP = "TRAP";
	public static final String WF_TIPOAPROBADOR_SOLI = "SOLI";
	public static final String WF_TIPOAPROBADOR_REFE = "REFE";
	
	public static final String WF_TIPOREPOSITORIO = "SY";
	public static final String WF_TIPOREPOSITORIO_FS = "FS";
	public static final String WF_TIPOREPOSITORIO_SY = "SY";
	
	//VALIDACIONES SY REPORTE ARCHIVO
		public static final String VAL_RE_AR_RESTRICCION_UNICA = "El registro ya existe.";
		public static final String VAL_RE_AR_RESTRICCION_COMPANIA_CODIGO = "La compa\u00F1\u00EDa no debe estar vac\u00EDa";
		public static final String VAL_RE_AR_RESTRICCION_PERIODO = "El periodo no debe estar vac\u00EDa";
		public static final String VAL_RE_AR_RESTRICCION_VERSION = "La versi\u00F3n no debe estar vac\u00EDa";
		public static final String VAL_RE_AR_RESTRICCION_ARCHIVO = "El archivo est\u00E1 vac\u00EDo";
}
