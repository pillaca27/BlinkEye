package net.royal.spring.framework.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class UDateUtil.
 *
 */
public class UFechaHora {

	/**
	 * Variable para tomar milisegundos del d�a
	 */
	private static final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;

	/**
	 * regresa la cadena especificada en tipo Date, null si no es posible
	 *
	 * @param cadena  que representa el valor de la fecha este debete tener el mismo
	 *                formato que se especifica en el parametro formato ej.
	 *                "1980-06-01"
	 * @param formato ej. "yyyy-mm-dd"
	 * @return Date valor de retorno retorna NULL si la cadena esta vacia
	 * @throws Exception si ocurre un error al momento de realizar la creacion del
	 *                   tipo Date
	 */
	public static Date convertirCadenaFecha(String cadena, String formato) throws Exception {

		Date date = null;

		if (cadena == null)
			return null;

		if (cadena.length() == 0)
			return null;

		SimpleDateFormat formateador = new SimpleDateFormat(formato);

		long ltime = formateador.parse(cadena).getTime();

		date = new Date(ltime);

		return date;
	}
	
	public static Date convertirCadenaFecha(String cadena) throws Exception {

		Date date = null;

		if (cadena == null)
			return null;

		if (cadena.length() == 0)
			return null;

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");

		long ltime = formateador.parse(cadena).getTime();

		date = new Date(ltime);

		return date;
	}
	
	public static Date convertirCadenaFechaConHora(String cadena) throws Exception {

		Date date = null;

		if (cadena == null)
			return null;

		if (cadena.length() == 0)
			return null;

		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

		long ltime = formateador.parse(cadena).getTime();

		date = new Date(ltime);

		return date;
	}

	public static Calendar covertirDateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	/**
	 * regresa la cadena especificada segun el formato enviado, si el formato es
	 * invalido se genera una excepcion
	 *
	 * @param date    ej. "1980-06-01"
	 * @param formato ej. "yyyy-mm-dd"
	 * @return String
	 * @throws Exception genera una exepcion si no se peude transformar
	 */
	public static String convertirFechaCadena(Date date, String formato) throws Exception {

		String cadena = null;
		if (date == null)
			return null;
		if (!esNuloVacio(date) && !UString.estaVacio(formato)) {
			SimpleDateFormat formateador = new SimpleDateFormat(formato);
			cadena = formateador.format(date);
		}
		// logger.debug("cadena " + cadena);
		return cadena;
	}

	public static String convertirFechaCadenaDetallada(Date fecha) {
		String formato = null;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss.SS");
		if (fecha != null) {
			formato = df.format(fecha);
		}
		return formato;
	}

	public static String convertirFechaCadena(Date fecha) {
		String formato = null;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		if (fecha != null) {
			formato = df.format(fecha);
		}
		return formato;
	}
	
	public static Date convertirFechaCadenaPeriodo(String periodo) {
		Date fecha_ap=new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (periodo != null) {
			try {
				String anio= periodo.substring(0,4);
				String mes= periodo.substring(4);
				String dia = "01";
				periodo= dia+"/"+mes+"/"+anio;
				fecha_ap= df.parse(periodo);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fecha_ap;
	}

	public static String convertirFechaCadenaSinHora(Date fecha) {
		String formato = null;
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		if (fecha != null) {
			formato = df.format(fecha);
		}
		return formato;
	}
	
	public static String convertirFechaCadenaSinHoraTc(Date fecha) {
		String formato = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		if (fecha != null) {
			formato = df.format(fecha);
		}
		return formato;
	}

	public static int obtenerNocionTiempo() {

		Calendar horaActual = Calendar.getInstance(TimeZone.getTimeZone("GMT-05"));

		int hora = horaActual.get(Calendar.HOUR_OF_DAY);
		int nocionTiempo = 0;

		if (hora <= 23)
			nocionTiempo = 2;

		if (hora <= 17)
			nocionTiempo = 1;

		if (hora <= 11)
			nocionTiempo = 0;

		return nocionTiempo;
	}

	public static long obtenerTime(Date date, String formato) throws Exception {

		long time = 0;

		SimpleDateFormat formateador = new SimpleDateFormat(formato);

		String cadena = convertirFechaCadena(date, formato);

		time = formateador.parse(cadena).getTime();

		return time;
	}

	/**
	 * regresa la cadena especificada en tipo Date, null si no es posible
	 *
	 * @param fecha   ej. "1980-06-01"
	 * @param formato ej. "yyyy-mm-dd"
	 * @return int
	 */
	public static int extraerFecha(Date fecha, String formato) throws Exception {

		int formateado = 0;

		SimpleDateFormat formateador = new SimpleDateFormat(formato);

		try {

			formateado = Integer.parseInt(formateador.format(fecha));

		} catch (Exception e) {

		}

		return formateado;
	}

	/**
	 * regresa la cadena especificada en tipo Date, null si no es posible
	 *
	 * @param cadena  ej. "1980-06-01"
	 * @param formato ej. "yyyy-mm-dd"
	 * @return Date
	 */
	public static boolean validarConvertirCadenaFecha(String cadena, String formato) throws Exception {

		SimpleDateFormat formateador = new SimpleDateFormat(formato);

		try {

			formateador.parse(cadena).getTime();

		} catch (ParseException e) {
			return false;
		}

		return true;
	}

	public static boolean validarFechasIguales(Date fechaInicio, Date fechaFin) {
		boolean fechaIgual = false;
		int iniAnio, iniMes, iniDia;
		int finAnio, finMes, finDia;
		try {
			iniAnio = extraerFecha(fechaInicio, "yyyy");
			iniMes = extraerFecha(fechaInicio, "MM");
			iniDia = extraerFecha(fechaInicio, "dd");

			finAnio = extraerFecha(fechaFin, "yyyy");
			finMes = extraerFecha(fechaFin, "MM");
			finDia = extraerFecha(fechaFin, "dd");

			if ((iniAnio == finAnio) && (iniMes == finMes) && (iniDia == finDia))
				fechaIgual = true;

		} catch (Exception e) {
			fechaIgual = false;
		}
		return fechaIgual;
	}

	/**
	 * regresa la cadena especificada en tipo Date, null si no es posible
	 *
	 * @param cadena 22/06/1986
	 *
	 * @return boolean
	 */
	public static boolean validarFecha(String cadena) {

		Pattern pat = null;
		Matcher mat = null;

		pat = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])\\/(0?[1-9]|1[012])\\/[0-9]{4}$");
		mat = pat.matcher(cadena);

		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validarFecha(String fecha, String pattern) throws Exception {
		boolean flgExito = true;
		Date date = null;

		try {
			date = UFechaHora.convertirCadenaFecha(fecha, pattern);
		} catch (Exception e) {
			flgExito = false;
			return flgExito;
		}

		if (date == null)
			flgExito = false;

		return flgExito;
	}

	/**
	 * regresa la cadena especificada en tipo Date, null si no es posible
	 *
	 * @param cadena 22:06
	 *
	 * @return boolean
	 */
	public static boolean validarHora(String cadena) {

		Pattern pat = null;
		Matcher mat = null;

		pat = Pattern.compile("^(0\\d|1\\d|2[0-3]):([0-5]\\d)$");
		mat = pat.matcher(cadena);

		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}

	public static String obtenerDiaEnCadena(int dia) {

		switch (dia) {
		case 1:
			return "Domingo";
		case 2:
			return "Lunes";
		case 3:
			return "Martes";
		case 4:
			return "Mi�rcoles";
		case 5:
			return "Jueves";
		case 6:
			return "Viernes";
		case 7:
			return "S�bado";
		default:
			return "";
		}
	}

	public static Date addSecondToDate(Date date, int seconds) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		calendarDate.add(Calendar.SECOND, seconds);
		return calendarDate.getTime();
	}

	/**
	 * Agrega o quita minutos a una fecha dada. Para quitar minutos hay que sumarle
	 * valores negativos.
	 * 
	 * @param date
	 * @param minutes
	 * @return
	 */
	public static Date addMinutesToDate(Date date, int minutes) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		calendarDate.add(Calendar.MINUTE, minutes);
		return calendarDate.getTime();
	}

	public static Date addDaysToDate(Date date, int days) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		calendarDate.add(Calendar.MINUTE, 60 * 24 * days);
		return calendarDate.getTime();
	}

	public static Date addMonthToDate(Date date, int month) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		calendarDate.add(Calendar.MONTH, month);
		return calendarDate.getTime();
	}

	public static Date addYearToDate(Date date, int years) {
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(date);
		calendarDate.add(Calendar.YEAR, years);
		return calendarDate.getTime();
	}
	
	public static Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static String obtenerMesPeriodoEnCadena(String mes) {
		if (mes == null)
			mes = "00";
		switch (mes) {
		case "01":
			return "Enero";
		case "02":
			return "Febrero";
		case "03":
			return "Marzo";
		case "04":
			return "Abril";
		case "05":
			return "Mayo";
		case "06":
			return "Junio";
		case "07":
			return "Julio";
		case "08":
			return "Agosto";
		case "09":
			return "Septiembre";
		case "10":
			return "Octubre";
		case "11":
			return "Noviembre";
		case "12":
			return "Noviembre";
		}
		return "";
	}

	public static String obtenerMesEnCadena(int mes) {

		switch (mes) {
		case 0:
			return "Enero";
		case 1:
			return "Febrero";
		case 2:
			return "Marzo";
		case 3:
			return "Abril";
		case 4:
			return "Mayo";
		case 5:
			return "Junio";
		case 6:
			return "Julio";
		case 7:
			return "Agosto";
		case 8:
			return "Septiembre";
		case 9:
			return "Octubre";
		case 10:
			return "Noviembre";
		default:
			return "Diciembre";

		}
	}

	public static String fechaActualFormatoLargo() {
		String fechaActual;

		Calendar fec = Calendar.getInstance();

		fechaActual = obtenerDiaEnCadena(fec.get(Calendar.DAY_OF_WEEK)) + ", " + fec.get(Calendar.DATE) + " de "
				+ obtenerMesEnCadena(fec.get(Calendar.MONTH)) + " del " + fec.get(Calendar.YEAR);

		return fechaActual;
	}

	public static String formatearFecha(Date fecha, String pattern) {
		String fechaFormateada = null;
		if (fecha != null && pattern != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			fechaFormateada = df.format(fecha);
		}
		return fechaFormateada;
	}

	/**
	 * regresa la cadena especificada en tipo Date, null si no es posible
	 *
	 * @param cadena    ej. "1980-06-01"
	 * @param formato   ej. "yyyy-mm-dd"
	 * @param tipoFecha 0 Ninguno 1 Fecha Inicial 2 Fecha Final
	 * @return Date
	 * @throws Exception
	 */
	public static Date convertirCadenaFecha(String cadena, String formato, int tipoFecha) throws Exception {
		Date _fecha;
		_fecha = convertirCadenaFecha(cadena, formato);

		if (tipoFecha == 1) {
			_fecha = obtenerFechaHoraInicioDia(_fecha);
		}
		if (tipoFecha == 2) {
			_fecha = obtenerFechaHoraFinDia(_fecha);
		}
		return _fecha;
	}

	public static Date obtenerFechaHoraActual() throws Exception {
		Date _fecha = new Date();
		_fecha = obtenerFechaHoraActual(_fecha);
		return _fecha;
	}

	@SuppressWarnings("deprecation")
	public static Date obtenerFechaHoraActual(Date _fecha) throws Exception {
		if (_fecha == null)
			return null;
		_fecha.setHours(12);
		_fecha.setMinutes(0);
		_fecha.setSeconds(0);
		return _fecha;
	}

	public static Date obtenerFechaHoraInicioDia(Date _fecha) throws Exception {
		if (_fecha == null)
			return null;
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(_fecha);
		fecha.set(Calendar.HOUR_OF_DAY, 0);
		fecha.set(Calendar.MINUTE, 0);
		fecha.set(Calendar.SECOND, 0);
		_fecha = fecha.getTime();
		return _fecha;
	}

	public static Date obtenerFechaHoraFinDia(Date _fecha) throws Exception {
		if (_fecha == null)
			return null;

		Calendar fecha = Calendar.getInstance();
		fecha.setTime(_fecha);

		fecha.set(Calendar.HOUR_OF_DAY, 23);
		fecha.set(Calendar.MINUTE, 59);
		fecha.set(Calendar.SECOND, 59);

		_fecha = fecha.getTime();
		return _fecha;
	}

	/**
	 * 
	 * @param fechaInicio
	 * @param fechaFin
	 * @return cantidad de d�as
	 * @throws Exception
	 * Observaci\u00F3n Luis Vargas: Se quit\u00E1 el @throws Exception, se coordin\u00F3 con Dario Qquecho
	 */
	public static Integer obtenerDiferenciaDias(Date fechaInicio, Date fechaFin) {
		if (fechaInicio == null || fechaFin == null)
			return null;
		Integer diferencia;
		diferencia = (int) ((fechaFin.getTime() - fechaInicio.getTime()) / MILLSECS_PER_DAY);
		return diferencia;
	}

	public static String validarRangoFechaDentroDeRango(Date fechaini, Date fechafin, Date fechaValidarInicio,
			Date fechaValidarFin) throws Exception {
		if (fechaini == null) {
			return "generico_fecha_requeridoinicial";
		}
		if (fechafin == null) {
			return "generico_fecha_requeridofinal";
		}

		fechafin = obtenerFechaHoraFinDia(fechafin);

		if (fechaValidarInicio.before(fechaini)) {
			return "generico_fecharango_validacionrango_fechainiciomenorfechainicio";
		}
		if (fechaValidarInicio.after(fechafin)) {
			return "generico_fecharango_validacionrango_fechainiciomayorfechafin";
		}
		if (fechaValidarFin.before(fechaini)) {
			return "generico_fecharango_validacionrango_fechafinmenorfechainicio";
		}
		if (fechaValidarFin.after(fechafin)) {
			return "generico_fecharango_validacionrango_fechafinmayorfechafin";
		}

		return null;
	}

	public static Boolean validarFechaEnRango(Date fechaini, Date fechafin, Date fechaValidar) {
		if (fechaini == null) {
			// LOGGER.debug("validarFechaDentroDeRango = generico_fecha_requeridoinicial");
			return false;
		}
		if (fechafin == null) {
			// LOGGER.debug("validarFechaDentroDeRango = generico_fecha_requeridofinal");
			return false;
		}
		if (fechaValidar == null) {
			return false;
		}
		Calendar calIni = Calendar.getInstance();
		calIni.setTime(fechaini);

		Calendar calFin = Calendar.getInstance();
		calFin.setTime(fechafin);

		Calendar calAct = Calendar.getInstance();
		calAct.setTime(fechaValidar);

		long inicio = calIni.getTimeInMillis();
		long fin = calFin.getTimeInMillis();
		long actual = calAct.getTimeInMillis();

		if ((actual >= inicio) && (actual <= fin))
			return true;
		return false;
	}

	public static String validarFechaDentroDeRango(Date fechaini, Date fechafin, Date fechaValidar) {
		if (fechaini == null) {
			return "generico_fecha_requeridoinicial";
		}
		if (fechafin == null) {
			return "generico_fecha_requeridofinal";
		}
		if (fechaValidar.before(fechaini)) {
			return "generico_fecharango_validacion_fechamenorfechainicio";
		}

		if (fechaValidar.after(fechafin)) {
			return "generico_fecharango_validacion_fechamayorfechafin";
		}
		return null;
	}

	public static String validarRangoFecha(Date fechaini, Date fechafin) {
		if (fechaini == null) {
			return "generico_fecha_requeridoinicial";
		}
		if (fechafin == null) {
			return "generico_fecha_requeridofinal";
		}

		if (fechafin.before(fechaini)) {
			return "generico_fecharango_validacion_fechainiciomayorfinal";
		}
		return null;
	}

	public static Integer calcularEdad(String fecha) {
		Date fechaNac = null;
		try {

			fechaNac = new SimpleDateFormat("dd-MM-yyyy").parse(fecha);
		} catch (Exception ex) {

		}
		Calendar fechaNacimiento = Calendar.getInstance();

		Calendar fechaActual = Calendar.getInstance();

		fechaNacimiento.setTime(fechaNac);

		int anio = fechaActual.get(Calendar.YEAR) - fechaNacimiento.get(Calendar.YEAR);
		int mes = fechaActual.get(Calendar.MONTH) - fechaNacimiento.get(Calendar.MONTH);
		int dia = fechaActual.get(Calendar.DATE) - fechaNacimiento.get(Calendar.DATE);

		if (mes < 0 || (mes == 0 && dia < 0)) {
			anio--;
		}

		return anio;
	}

	public static Date obtenerPrimerDiaAnio() {

		Date fecha = new Date();

		GregorianCalendar cal = new GregorianCalendar();
		cal.set(cal.get(Calendar.YEAR), cal.getMinimum(Calendar.MONTH), cal.getMinimum(Calendar.DATE),
				cal.getMinimum(Calendar.HOUR_OF_DAY), cal.getMinimum(Calendar.MINUTE), cal.getMinimum(Calendar.SECOND));

		fecha = cal.getTime();

		return fecha;
	}

	public static Date obtenerPrimerDiaAnio(Integer anio) {

		Date fecha = new Date();

		GregorianCalendar cal = new GregorianCalendar();
		cal.set(anio, cal.getMinimum(Calendar.MONTH), cal.getMinimum(Calendar.DATE),
				cal.getMinimum(Calendar.HOUR_OF_DAY), cal.getMinimum(Calendar.MINUTE), cal.getMinimum(Calendar.SECOND));

		fecha = cal.getTime();

		return fecha;
	}

	public static Date obtenerUltimoDiaAnio() {

		Date fecha = new Date();

		Calendar cal = new GregorianCalendar();
		cal.set(cal.get(Calendar.YEAR), cal.getActualMaximum(Calendar.MONTH), cal.getMaximum(Calendar.DAY_OF_MONTH),
				cal.getMaximum(Calendar.HOUR_OF_DAY), cal.getMaximum(Calendar.MINUTE), cal.getMaximum(Calendar.SECOND));

		fecha = cal.getTime();

		return fecha;
	}

	public static Date obtenerUltimoDiaAnio(Integer anio) {

		Date fecha = new Date();

		Calendar cal = new GregorianCalendar();
		cal.set(anio, cal.getActualMaximum(Calendar.MONTH), cal.getMaximum(Calendar.DAY_OF_MONTH),
				cal.getMaximum(Calendar.HOUR_OF_DAY), cal.getMaximum(Calendar.MINUTE), cal.getMaximum(Calendar.SECOND));

		fecha = cal.getTime();

		return fecha;
	}

	public static Integer obtenerDiasTranscurridos(Date fechaMayor, Date fechaMenor) {
		GregorianCalendar fec1 = new GregorianCalendar();
		fec1.setTime(fechaMayor);

		GregorianCalendar fec2 = new GregorianCalendar();
		fec2.setTime(fechaMenor);

		Long difms = fec1.getTimeInMillis() - fec2.getTimeInMillis();
		Long difd = difms / (1000 * 60 * 60 * 24);
		return difd.intValue();

	}

	public static Date obtenerFechaMasdias(Date fecha, Integer dias) {
		GregorianCalendar fec1 = new GregorianCalendar();
		fec1.setTime(fecha);
		fec1.add(Calendar.DATE, dias);
		return fec1.getTime();
	}

	public static Integer obtenerFechaParte(Date fecha, String part) {
		Integer parte = null;

		GregorianCalendar fec = new GregorianCalendar();
		fec.setTime(fecha);

		if (part.equals("YY")) {
			parte = fec.get(Calendar.YEAR);
		}

		if (part.equals("MM")) {
			parte = fec.get(Calendar.MONTH) + 1;
		}

		if (part.equals("DD")) {
			parte = fec.get(Calendar.DATE);
		}

		if (part.equals("HH")) {
			parte = fec.get(Calendar.HOUR_OF_DAY);
		}

		if (part.equals("MI")) {
			parte = fec.get(Calendar.MINUTE);
		}

		if (part.equals("SS")) {
			parte = fec.get(Calendar.SECOND);
		}
		return parte;
	}

	public static Date obtenerFechaCompuesta(Date fecha, Date hora) {

		Calendar fechabase = new GregorianCalendar();

		if (esNuloVacio(fecha) || esNuloVacio(hora)) {
			return null;
		}

		fechabase.setTime(fecha);

		Calendar horabase = new GregorianCalendar();
		if (esNuloVacio(hora)) {
			horabase.set(Calendar.HOUR_OF_DAY, 0);
			horabase.set(Calendar.MINUTE, 0);
			horabase.set(Calendar.SECOND, 0);
		} else {
			horabase.setTime(hora);
		}

		fechabase.set(GregorianCalendar.HOUR_OF_DAY, horabase.get(GregorianCalendar.HOUR_OF_DAY));
		fechabase.set(GregorianCalendar.MINUTE, horabase.get(GregorianCalendar.MINUTE));
		fechabase.set(GregorianCalendar.SECOND, horabase.get(GregorianCalendar.SECOND));

		return fechabase.getTime();
	}

	/**
	 * Obtiene el n�mero de d�a de la semana.
	 *
	 * @param fecha : fecha a procesar
	 * @return numero de dia de semana comenzando por domingo.
	 */
	public static Integer obtenerNumeroDiaSemana(Date fecha) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		calendar.setTime(fecha);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static Integer obtenerSegundosTranscurridos(Date fechaMayor, Date fechaMenor) {
		GregorianCalendar fec1 = new GregorianCalendar();
		fec1.setTime(fechaMayor);

		GregorianCalendar fec2 = new GregorianCalendar();
		fec2.setTime(fechaMenor);

		Long difms = fec1.getTimeInMillis() - fec2.getTimeInMillis();
		Long difd = difms / 1000;
		return difd.intValue();
	}

	@SuppressWarnings("deprecation")
	public static Date obtenerFechaHoraInicioDiaDate(Date _fecha) throws Exception {
		if (_fecha == null)
			return null;

		_fecha.setHours(0);
		_fecha.setMinutes(0);
		_fecha.setSeconds(0);

		return _fecha;
	}

	/**
	 * 
	 * Funcion que retorna los a�os, meses y dias transcurridos entre dos fechas
	 * retornara asi 2 A�os 11 meses 5 dias
	 * 
	 * @throws ParseException
	 */
	public static Boolean esNuloVacio(Date fecha) {
		if (fecha != null)
			return false;
		return true;
	}

	public static String obteneraniomesdias(Date fechainicial, Date fechaactual) {
		if (esNuloVacio(fechainicial))
			return null;

		if (esNuloVacio(fechaactual))
			return null;

		String fechaInicio = new SimpleDateFormat("dd/MM/yyyy").format(fechainicial);
		String fechaActual = new SimpleDateFormat("dd/MM/yyyy").format(fechaactual);

		Date date = new Date();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		fechaActual = df.format(date);
		String[] aFechaIng = fechaInicio.split("/");
		Integer diaInicio = Integer.parseInt(aFechaIng[0]);
		Integer mesInicio = Integer.parseInt(aFechaIng[1]);
		Integer anioInicio = Integer.parseInt(aFechaIng[2]);

		String[] aFecha = fechaActual.split("/");
		Integer diaActual = Integer.parseInt(aFecha[0]);
		Integer mesActual = Integer.parseInt(aFecha[1]);
		Integer anioActual = Integer.parseInt(aFecha[2]);

		int b = 0;
		b = b + b;
		Integer dias = 0;
		Integer mes = 0;
		Integer anios = 0;
		Integer meses = 0;
		mes = mesInicio - 1;
		if (mes == 2) {
			if ((anioActual % 4 == 0) && ((anioActual % 100 != 0) || (anioActual % 400 == 0))) {
				b = 29;
			} else {
				b = 28;
			}
		} else if (mes <= 7) {
			if (mes == 0) {
				b = 31;
			} else if (mes % 2 == 0) {
				b = 30;
			} else {
				b = 31;
			}
		} else if (mes > 7) {
			if (mes % 2 == 0) {
				b = 31;
			} else {
				b = 30;
			}
		}
		if ((anioInicio > anioActual) || (anioInicio == anioActual && mesInicio > mesActual)
				|| (anioInicio == anioActual && mesInicio == mesActual && diaInicio > diaActual)) {
		} else {
			if (mesInicio <= mesActual) {
				anios = anioActual - anioInicio;
				if (diaInicio <= diaActual) {
					meses = mesActual - mesInicio;
					// dias = b - (diaInicio - diaActual);
					dias = (diaInicio - diaActual);
				} else {
					if (mesActual == mesInicio) {
						anios = anios - 1;
					}
					meses = (mesActual - mesInicio - 1 + 12) % 12;
					// dias = b - (diaInicio - diaActual);
					dias = (diaInicio - diaActual);
				}
			} else {
				anios = anioActual - anioInicio - 1;
				if (diaInicio > diaActual) {
					meses = mesActual - mesInicio - 1 + 12;
					// dias = b - (diaInicio - diaActual);
					dias = (diaInicio - diaActual);
				} else {
					meses = mesActual - mesInicio + 12;
					dias = diaActual - diaInicio;
				}
			}
		}

		return anios.toString() + "  A�os  " + Math.abs(meses) + "  Meses  " + Math.abs(dias) + "  D�as  ";

	}

	public Date fechaActual() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	public static Date obtenerFechaActual(Date hora) {
		Calendar calendar = Calendar.getInstance();
		Calendar calReturn = null;
		GregorianCalendar calHora = new GregorianCalendar();
		calHora.setTime(hora);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH); // Jan = 0, dec = 11
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
//		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
//		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
//		int weekOfMonth = calendar.get(Calendar.WEEK_OF_MONTH);

//		int hour = calendar.get(Calendar.HOUR); // 12 hour clock
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
//		int millisecond = calendar.get(Calendar.MILLISECOND);

		hourOfDay = calHora.get(Calendar.HOUR_OF_DAY);
		minute = calHora.get(Calendar.MINUTE);
		second = calHora.get(Calendar.SECOND);

		calReturn = new GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute, second);

		return calReturn.getTime();
	}

	/**
	 * Obtener un periodo en base a uno existente y la cantidad de meses siguientes
	 * o anteriores. Si el mes es negativo traer� un periodo anterior seg�n los
	 * meses, si es positivo traer� un periodo posterior seg�n los meses.
	 * 
	 * @author valenzuelad
	 * 
	 * @param periodo Periodo base, para calcular el periodo siguiente.
	 * @param mes     Mes positivo o negativo, para calcular el periodo siguiente.
	 * @return {@link String} Periodo Calculado
	 * @see UFechaHora.calcularperiodoRango
	 * @tablas no presenta
	 * @powerbuilder no presenta
	 * @pre_condiciones no presenta
	 * @post_condicion no presenta
	 * @usado_por
	 *            <ul>
	 *            <li><strong>M�dulo:</strong>&nbsp Log�stica, Tesorer�a
	 *            (AsPeriodoServicio)</li>
	 *            <li><strong>Funci�n:</strong>&nbsp calcularperiodoRango()</li>
	 *            </ul>
	 * @comentario Nombre anterior del m�todo: FCalculateNextPeriod
	 */
	public static String calcularPeriodo(String periodo, Integer meses) {

		Integer w_year, w_month, w_inicio;
		w_year = Integer.parseInt(periodo.substring(0, 4));
		w_month = Integer.parseInt(periodo.substring(4, 6));
		w_month = w_month - meses;

		if (w_month > 12) {
			w_year = w_year + (w_month / 12);
			w_month = w_month % 12;
		}

		if (UInteger.esCeroOrNulo(w_month)) {
			w_year = w_year - 1;
			w_month = 12;
		}

		if (w_month < 1) {
			w_month = Integer.parseInt(periodo.substring(4, 6));
			if (Math.abs(meses) < w_month) {
				w_month = w_month - meses;
			} else {
				if (Math.abs(meses) == 12) {
					w_month = Integer.parseInt(periodo.substring(4, 6));
					w_year = w_year - 1;
				} else {
					w_inicio = 13;
					if (w_month > 1) {
						w_inicio = w_inicio + (w_month - 1);
					}
					w_month = w_inicio - Math.abs(meses);
					w_year = w_year - 1;
				}
			}
		}

		String mes = String.format("%02d", w_month);

		return w_year.toString() + mes;

	}

	/**
	 * Listar el Primer y �ltimo d�a del Periodo, Soporta los periodos
	 * correspondientes a meses bisiestos.
	 * 
	 * @author valenzuelad
	 * 
	 * @param periodo Periodo base, para obtener la primera y �ltima fecha.
	 * @return List<{@link Date}> Primera y �ltima fecha del Periodo.
	 * @see UFechaHora.calcularPeriodo
	 * @tablas no presenta
	 * @powerbuilder no presenta
	 * @pre_condiciones no presenta
	 * @post_condicion no presenta
	 * @usado_por
	 *            <ul>
	 *            <li><strong>M�dulo:</strong>&nbsp Log�stica, Tesorer�a
	 *            (AsPeriodoServicio)</li>
	 *            <li><strong>Funci�n:</strong>&nbsp L=iniciarControladora(),
	 *            T=abrir(),ejecutarBotonCompletar()</li>
	 *            </ul>
	 * @comentario no presenta
	 */
	public static List<Date> calcularperiodoRango(String periodo) throws ParseException {
		if (periodo == null)
			periodo = new SimpleDateFormat("yyyyMM").format(new Date());

		List<Date> resultado = new ArrayList<Date>();

		String w_next;
		Date par_first_day, par_last_day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		w_next = calcularPeriodo(periodo, 1);

		String stringFecha1 = periodo.substring(0, 4) + "/" + periodo.substring(4, 6) + "/" + "01";
		par_first_day = sdf.parse(stringFecha1);

		String stringFecha2 = w_next.substring(0, 4) + "/" + w_next.substring(4, 6) + "/" + "01";
		par_last_day = sdf.parse(stringFecha2);

		par_last_day = UFechaHora.addDaysToDate(par_last_day, -1);

		resultado.add(par_first_day);
		resultado.add(par_last_day);

		return resultado;

	}

	public static String calcularFechaDocumento() {
			return new SimpleDateFormat("ddMMyyyy").format(new Date());
	}

	public static String calcularPeriodoPorFecha(Date fecha) {
		if (fecha != null)
			return new SimpleDateFormat("yyyyMM").format(fecha);
		else
			return new SimpleDateFormat("yyyyMM").format(new Date());
	}

	public static Integer obtenerMinutos(Date horaInicioLunes, Date horaFinLunes) {
		long duration = horaFinLunes.getTime() - horaInicioLunes.getTime();

		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		return new Integer((int) diffInMinutes);
	}

	@SuppressWarnings("deprecation")
	public static String obtenerMes(Integer mes) {
		SimpleDateFormat d = new SimpleDateFormat("MMMM", new Locale("es", "ES"));
		Date date = new Date();
		date.setMonth(mes - 1);
		return d.format(date);
	}

	public static Date obtenerInicioMesActual() {
		Date begining;
		Calendar calendar = getCalendarForNow();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		setTimeToBeginningOfDay(calendar);
		begining = calendar.getTime();
		return begining;
	}

	public static Date obtenerFinMesActual() {
		Date end;
		Calendar calendar = getCalendarForNow();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		setTimeToEndofDay(calendar);
		end = calendar.getTime();
		return end;
	}

	private static Calendar getCalendarForNow() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		return calendar;
	}

	private static void setTimeToBeginningOfDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	private static void setTimeToEndofDay(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
	}
	
	public static Date calcularfechaporperiodo(String periodo) throws ParseException {
		if (periodo == null)
			periodo = new SimpleDateFormat("yyyyMM").format(new Date());


		String w_next;
		Date  par_last_day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		w_next = calcularPeriodo(periodo, 1);

		String stringFecha2 = w_next.substring(0, 4) + "/" + w_next.substring(4, 6) + "/" + "01";
		par_last_day = sdf.parse(stringFecha2);

		par_last_day = UFechaHora.addDaysToDate(par_last_day, 0);
		Calendar cal = Calendar.getInstance();cal.setTime(par_last_day); cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE)); Date lastDayOfMonth = cal.getTime();


		return lastDayOfMonth;

	}

    public static Date removerHora(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Boolean fechaEsMayorQue(Date valor1, Date valor2) {
		Integer valor = valor1.compareTo(valor2);
		if (valor == 1) {
			return true;
		}
		return false;
	}

	public static Boolean fechaEsMenorQue(Date valor1, Date valor2) {
		Integer valor = valor1.compareTo(valor2);
		if (valor == -1) {
			return true;
		}
		return false;
	}
    
	public static String obtenerHorasString(Date fechaParam) throws Exception {

		if (fechaParam == null) {
			return "";
		}

		String horaCadena = UFechaHora.convertirFechaCadena(fechaParam, "HH:mm:ss");
		return horaCadena;
	}

	public static Boolean horaEsIgualACero(Date fechaHoraDesde) throws Exception {

		Date fechaHoraHasta = new SimpleDateFormat("HH:mm:ss").parse("00:00:00");

		String horaDesde = UFechaHora.convertirFechaCadena(fechaHoraDesde, "HH:mm:ss");
		String horaHasta = UFechaHora.convertirFechaCadena(fechaHoraHasta, "HH:mm:ss");

		LocalTime valor1 = LocalTime.parse(horaDesde);
		LocalTime valor2 = LocalTime.parse(horaHasta);
		Integer valor = valor1.compareTo(valor2);
		if (valor == 0) {
			return true;
		}
		return false;
	}
	
	public static Boolean fechaEsMenorIgualque(Date valor1, Date valor2) {
		Integer valor = valor1.compareTo(valor2);
		if (valor == 0 || valor == -1) {
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param valor1
	 * @param valor2
	 * @return boolean
	 * Observaci\u00F3n Luis Vargas: Se cre\u00E1 la funcionalidad, se coordin\u00F3 con Dario Qquecho
	 */
	public static Boolean fechaEsMayorIgualque(Date valor1, Date valor2) {
		Integer valor = valor1.compareTo(valor2);
		if (valor == 0 || valor == 1) {
			return true;
		}
		return false;
	}
	
	/**
	 * Devuelve la fecha que corresponde al fin de un MES en un A\u00D1O determinado.
	 * 
	 * @param par_ano
	 * @param par_mes
	 * @return date
	 * Observaci\u00F3n Luis Vargas: Se cre\u00E1 la funcionalidad, se coordin\u00F3 con Dario Qquecho
	 */
	public static Date obtenerFechaFinDeMes(Integer par_ano, Integer par_mes) {
		// Devuelve la fecha que corresponde al fin de un MES en un A\u00D1O determinado.
		Integer mod;
		Integer dia = 0;
		Boolean biciesto;
		Date fecha = new Date();
		
		mod = par_ano%4;

		if (mod.equals(0)) { 
			biciesto = true;
		} else {
			biciesto = false;
		}

		switch(par_mes)
		{
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: {
				dia = 31;
				break;
			}
			
			case 4:
			case 6:
			case 9:
			case 11: {
				dia = 30;
				break;
			}
			
			case 2: {
				dia = 28;
				
				if (biciesto) {
					dia = 29;
				}
				break;
			}
		}
		
		fecha.setDate(dia);
		/* Neil Anotaci�n: El valor del primer mes es 0, se resta -1 para tener el mes correcto */
		fecha.setMonth(par_mes - 1);
		fecha.setYear(par_ano);
		
		return fecha;
	}
	
	public static Integer obtenerHora(Date fechaParam) {

		if (fechaParam == null) {
			return 0;
		}

		Integer hora = null;
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(fechaParam);

		hora = fecha.get(Calendar.HOUR_OF_DAY);

		return hora;
	}
	
	public static Integer obtenerMinutos(Date fechaParam) {

		if (fechaParam == null) {
			return 0;
		}

		Integer minuto = null;
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(fechaParam);

		minuto = fecha.get(Calendar.MINUTE);

		return minuto;
	}

	public static Boolean horaEsIgual(Date fechaHoraDesde, Date fechaHoraHasta) throws Exception {
		String horaDesde = UFechaHora.convertirFechaCadena(fechaHoraDesde, "HH:mm:ss");
		String horaHasta = UFechaHora.convertirFechaCadena(fechaHoraHasta, "HH:mm:ss");

		LocalTime valor1 = LocalTime.parse(horaDesde);
		LocalTime valor2 = LocalTime.parse(horaHasta);
		Integer valor = valor1.compareTo(valor2);
		if (valor == 0) {
			return true;
		}
		return false;
	}
	
	public static Integer obtenerMes(Date fechaParam) {

		if (fechaParam == null) {
			return 0;
		}

		Integer hora = null;
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(fechaParam);

		hora = fecha.get(Calendar.MONTH);

		return hora;
	}
	
	public static Integer obtenerDia(Date fechaParam) {

		if (fechaParam == null) {
			return 0;
		}

		Integer hora = null;
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(fechaParam);

		hora = fecha.get(Calendar.DAY_OF_MONTH);

		return hora;
	}

	public static boolean validarFecha1MayorFecha2SinHora(Date fecha1, Date fecha2) {
		if (removerHora(fecha1).after(removerHora(fecha2))) {
			return true;
		}
		return false;
	}
	
	public static boolean springValidarFechaValida(Date par_fecha) {
		SimpleDateFormat sdf_yyyymmdd = new SimpleDateFormat("YYYYMMDD");
		Date w_hoy = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(w_hoy);
		String w_fecha = sdf_yyyymmdd.format(par_fecha);
		int w_ano = Integer.parseInt(w_fecha.substring(0, 4));
		if (Math.abs(w_ano - calendar.get(Calendar.YEAR)) > 100) {
			return false;
		}
		return true;
	}
	
	public static String obtenerDiaFechaCadena(Date fecha) {
		int dia = obtenerNumeroDiaSemana(fecha);
		
		switch (dia) {
			case 1:
				return "DOMINGO";
			case 2:
				return "LUNES";
			case 3:
				return "MARTES";
			case 4:
				return "MIERCOLES";
			case 5:
				return "JUEVES";
			case 6:
				return "VIERNES";
			case 7:
				return "SABADO";
			default:
				return "";
		}
	}
	
	public static String obtenerFechaCadena(Date pFecha) {
		String fechaAno = UFechaHora.obtenerAnio(pFecha).toString();
		String fechaMes = String.valueOf(UFechaHora.obtenerMes(pFecha) + 1);
		String fechaDia = UFechaHora.obtenerDia(pFecha).toString();
		
		if (fechaMes.length() == 1)
			fechaMes = "0" + fechaMes;
		
		if (fechaDia.length() == 1)
			fechaDia = "0" + fechaDia;
		
		return fechaAno + fechaMes + fechaDia;
	}
	
	/**
	 * Funcion migrada de Win: f_convierte_tiempo_a_dias
	 */
	public static Integer convertirTiempoADias(String pTiempo) {
		int wAnos = Integer.parseInt(UString.spring_MID(pTiempo, 1, 2));
		int wMeses = Integer.parseInt(UString.spring_MID(pTiempo, 3, 2));
		int wDias = Integer.parseInt(UString.spring_MID(pTiempo, 5, 2));
		
		return (wAnos * 360) + (wMeses * 30) + wDias;
	}
	
	/**
	 * Funcion migrada de Win: f_relative_date_tiempo_real2 
	 */
	public static String obtenerFechaTiempoRelativa(Date pFechaInicio, Date pFechaFin) {
		// Calcula, a partir de dos fechas DATE, el tiempo en A�OS, MESES y DIAS
		// No en terminos de dias reales, sino en tiempo real (ANOS, MESES y DIAS)
		String wResultado = "";
		int wAnoInicio = 0, wAnoFin = 0, wMesInicio = 0, wMesFin = 0;
		int wDiaInicio = 0, wDiaFin = 0;
		int wAnos = 0, wMeses = 0, wDias = 0;
		boolean wAniversario = false;
		
		wAnoInicio = obtenerAnio(pFechaInicio);
		wMesInicio = obtenerMes(pFechaInicio) + 1;
		wDiaInicio = obtenerDia(pFechaInicio);
		
		wAnoFin = obtenerAnio(pFechaFin);
		wMesFin = obtenerMes(pFechaFin) + 1;
		wDiaFin = obtenerDia(pFechaFin);
		
		// Determinar cuantos a�os hay de diferencia
		wAnos = wAnoFin - wAnoInicio;
		
		// Si el mes fin es menor que el mes inicio, restarle un a�o
		if (wMesFin <= wMesInicio)
			wAnos--;
		
		// Determinar cuantos meses hay de diferencia
		
		// Mes FINAL = Multiplicar los a�os de diferencia (w%_anofin - w%_anoinicio) * 12 
		//					y sumarlo al mes final
		// Mes INICIAL = mesinicio
		
		wMeses = (((wAnoFin - wAnoInicio) * 12) + wMesFin) - wMesInicio;
		
		// Si los meses superan a 11, sacarle el modulo de 12
		if (wMeses >= 12)
			wMeses = wMeses % 12;
		
		// Si el modulo es 0 quiere decir que es el mes de aniversario
		if (wMeses == 0)	
			wAniversario = true;
		
		if (wDiaFin >= wDiaInicio) {
			// Si el dia incial es menor que el final, restar el final menos el inicial
			wDias = wDiaFin - wDiaInicio;
			// A�adir un mes si es el aniversario
			if (wAniversario) {
				wAnos++;
				wMeses = 0;
			}
		} else {
			// Si no, restar uno a los meses y
			// Los dias seran la suma de A + B, donde
			// A = dias entre el fin de mes del mes anterior a la fecha final menos dia inicial
			// B = dia final
			if (wMeses == 0)
				wMeses = 11;
			else
				wMeses--;
			
			int wA = 0, wB = 0;
			int wAnoTemp = 0, wMesTemp = 0;
			int wDiaFinal = 0;
			
			if (wMesFin == 1) {
				wAnoTemp = wAnoFin - 1;
				wMesTemp = 12;
			} else {
				wAnoTemp = wAnoFin;
				wMesTemp = wMesFin - 1;
			}
			
			wDiaFinal = obtenerDia(obtenerFechaFinDeMes(wAnoTemp, wMesTemp));
			
			wA = wDiaFinal - wDiaInicio;
			wB = wDiaFin;
			
			wDias = wA + wB;
		}
		
		if (wAnos < 0 || wMeses < 0 || wDias < 0) {
			wResultado = "000000";
		} else {
			String strAnos = String.format("%02d", wAnos);
			String strMeses = String.format("%02d", wMeses);
			String strDias = String.format("%02d", wDias);
			wResultado = strAnos + strMeses + strDias;
		}
			
		return wResultado;
	}
	
	public static Date calcularfechaporperiododefault(String periodo) throws ParseException {
		if (periodo == null)
			periodo = new SimpleDateFormat("yyyyMM").format(new Date());


		String w_next;
		Date  par_last_day;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		w_next = calcularPeriodo(periodo, 0);

		String stringFecha2 = w_next.substring(0, 4) + "/" + w_next.substring(4, 6) + "/" + "01";
		par_last_day = sdf.parse(stringFecha2);

		par_last_day = UFechaHora.addDaysToDate(par_last_day, 0);
		Calendar cal = Calendar.getInstance();cal.setTime(par_last_day); cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE)); Date lastDayOfMonth = cal.getTime();


		return lastDayOfMonth;

	}

	public static Integer obtenerAnio() {

		Integer anio = null;

		Calendar cal = Calendar.getInstance();
		anio = cal.get(Calendar.YEAR);

		return anio;
	}
    
    public static Integer obtenerAnio(Date fechaParam) {

		if (fechaParam == null) {
			return 0;
		}

		Integer hora = null;
		Calendar fecha = Calendar.getInstance();
		fecha.setTime(fechaParam);

		hora = fecha.get(Calendar.YEAR);

		return hora;
	}
    
}