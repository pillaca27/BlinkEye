package net.royal.spring.framework.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class UDouble {
	
	public static String obtenerValorCadena(double numero) {
		return obtenerValorCadena(numero,"#,###,##0.00");
	}
	
	public static String obtenerValorCadena(double numero,String formato) {
		DecimalFormat df = new DecimalFormat(formato);
		String formatted = df.format(numero);
		return formatted;
	}
	
	public static double roundDouble(double d, int places) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
	
	public static String convertirCadenaDefaultVacio(Double numero) {
		String formato = "#,###,##0.00";
		
		if (numero == null)
			return "";

		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("EN"));
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern(formato);

		return df.format(numero);
	}

}
