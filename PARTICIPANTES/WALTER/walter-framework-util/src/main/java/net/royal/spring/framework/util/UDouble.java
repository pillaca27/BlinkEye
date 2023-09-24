package net.royal.spring.framework.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class UDouble {
	public static String obtenerValorCadena(Double numero) {
		if(numero==null) {
			return "0.00";
		}
		DecimalFormat df = new DecimalFormat("#,###,##0.00");
		String formatted = df.format(numero); 
		
		return formatted;
	}
	
	public static double roundDouble(double d, int places) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(d));
        bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
	
	public static Double obtenerValorDoubleSinNulo(Double variable) {
		if (variable == null)
			return 0.0;
		return variable;
	}
}
