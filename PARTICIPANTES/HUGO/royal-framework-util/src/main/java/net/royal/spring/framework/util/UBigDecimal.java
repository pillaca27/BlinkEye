package net.royal.spring.framework.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class UBigDecimal {
	public static BigDecimal VALOR_TEMPORAL = new BigDecimal(-1);
	
	public static BigDecimal obtenerValorSinNulo(BigDecimal numero) {
		if (numero == null) {
			return new BigDecimal(0);
		}
		
		return numero;
	}
	public static boolean esNulo(BigDecimal numero) {
		if (numero == null) {
			return true;
		}		
		return false;
	}
	public static boolean esCeroOrNulo(BigDecimal numero) {
		if (numero == null) {
			return true;
		}
		if (numero.compareTo(BigDecimal.ZERO)==0) {
			return true;
		}
		return false;
	}

	public static BigDecimal restar(BigDecimal sumando1, BigDecimal sumando2) {
		if (sumando1 == null)
			sumando1 = BigDecimal.ZERO;
		if (sumando2 == null)
			sumando2 = BigDecimal.ZERO;
		return sumando1.subtract(sumando2);
	}

	public static BigDecimal restar(BigDecimal sumando1, BigDecimal sumando2, Integer scale) {
		if (sumando1 == null)
			sumando1 = BigDecimal.ZERO;
		if (sumando2 == null)
			sumando2 = BigDecimal.ZERO;
		if (scale == null)
			return sumando1.subtract(sumando2);
		else
			return sumando1.subtract(sumando2).setScale(scale);
	}

	public static BigDecimal sumar(BigDecimal sumando1, BigDecimal sumando2) {
		if (sumando1 == null)
			sumando1 = BigDecimal.ZERO;
		if (sumando2 == null)
			sumando2 = BigDecimal.ZERO;
		return sumando1.add(sumando2);
	}

	public static BigDecimal sumar(BigDecimal sumando1, BigDecimal sumando2, Integer scale) {
		if (sumando1 == null)
			sumando1 = BigDecimal.ZERO;
		if (sumando2 == null)
			sumando2 = BigDecimal.ZERO;
		if (scale == null)
			return sumando1.add(sumando2);
		else
			return sumando1.add(sumando2).setScale(scale);
	}

	public static BigDecimal multiplicar(BigDecimal elemento1, BigDecimal elemento2, Integer scale) {
		if (elemento1 == null)
			elemento1 = BigDecimal.ZERO;
		if (elemento2 == null)
			elemento2 = BigDecimal.ZERO;
		
		MathContext mc = new MathContext(scale, RoundingMode.HALF_EVEN);
		try {
			
			
			BigDecimal xx = elemento1.multiply(elemento2, mc).setScale(scale, RoundingMode.HALF_EVEN);
			return xx;
		} catch (Exception e) {
		}
		return elemento1.multiply(elemento2, mc).setScale(scale, RoundingMode.HALF_EVEN);
	}

	public static BigDecimal dividir(BigDecimal elemento1, BigDecimal elemento2, Integer scale) {
		MathContext mc = new MathContext(9, RoundingMode.HALF_EVEN);
		if (elemento1 == null)
			elemento1 = BigDecimal.ZERO;
		if (elemento2 == null)
			elemento2 = BigDecimal.ZERO;
		if (elemento2 == BigDecimal.ZERO)
			return BigDecimal.ZERO;
		return elemento1.divide(elemento2,  mc)
				.setScale(scale, RoundingMode.HALF_EVEN);
	}
	
	public static Boolean esMayorIgualque(BigDecimal valor1, BigDecimal valor2) {
		Integer valor = valor1.compareTo(valor2);
		if (valor == 0 || valor == 1) {
			return true;
		}
		return false;
	}
	
	public static Boolean esMayorQue(BigDecimal valor1, BigDecimal valor2) {
		Integer valor = valor1.compareTo(valor2);
		if (valor == 1) {
			return true;
		}
		return false;
	}
	
	public static Boolean esMenorQue(BigDecimal valor1, BigDecimal valor2) {
		Integer valor = valor1.compareTo(valor2);
		if ( valor == -1) {
			return true;
		}
		return false;
	}
	

	public static Boolean esMenorIgualque(BigDecimal valor1, BigDecimal valor2) {
		Integer valor = valor1.compareTo(valor2);
		if (valor == 0 || valor == -1) {
			return true;
		}
		return false;
	}

	public static Boolean esDiferente(BigDecimal valor1, BigDecimal valor2) {
		Integer valor = valor1.compareTo(valor2);
		if (valor == 1 || valor == -1) {
			return true;
		}
		return false;
	}
	
	private static final String[] UNIDADES = { "", "UN ", "DOS ", "TRES ", "CUATRO ", "CINCO ", "SEIS ", "SIETE ",
			"OCHO ", "NUEVE ", "DIEZ ", "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS", "DIECISIETE",
			"DIECIOCHO", "DIECINUEVE", "VEINTE" };

	private static final String[] DECENAS = { "VENTI", "TREINTA ", "CUARENTA ", "CINCUENTA ", "SESENTA ", "SETENTA ",
			"OCHENTA ", "NOVENTA ", "CIEN " };

	private static final String[] CENTENAS = { "CIENTO ", "DOSCIENTOS ", "TRESCIENTOS ", "CUATROCIENTOS ",
			"QUINIENTOS ", "SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS " };
	
	public static String convertirCadena(BigDecimal numero, String formato) {
		if (formato == null)
			formato = "#,###,###.00";
		if (numero == null)
			return "0.00";

		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("EN"));
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern(formato);

		return df.format(numero);
	}
	
	public static String convertirCadenaDefaultVacio(BigDecimal numero) {
		String formato = "#,###,##0.00";
		
		if (numero == null)
			return "";

		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("EN"));
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern(formato);

		return df.format(numero);
	}
	
	public static String convertirCadenaDefaultVacioFormato(BigDecimal numero, String formato) {
		//String formato = "#,###,##0.00000";
		
		if (numero == null)
			return "";

		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("EN"));
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern(formato);

		return df.format(numero);
	}

	public static String convertirCadena(BigDecimal numero) {
		return convertirCadena(numero, null);
	}
	
	public static String convertNumberToLetter(BigDecimal bigdecimal) throws NumberFormatException {

		if (bigdecimal == null) {
			return null;
		}

		String number = bigdecimal.toString();

		double doubleNumber = Double.parseDouble(number);

		StringBuilder converted = new StringBuilder();

		String patternThreeDecimalPoints = "#.###";

		DecimalFormat format = new DecimalFormat(patternThreeDecimalPoints);
		format.setRoundingMode(RoundingMode.DOWN);

		if (doubleNumber > 999999999)
			throw new NumberFormatException("El numero es mayor de 999'999.999, " + "no es posible convertirlo");

		if (doubleNumber < 0)
			throw new NumberFormatException("El numero debe ser positivo");

		String splitNumber[] = String.valueOf(doubleNumber).replace('.', '#').split("#");

		int millon = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 8))
				+ String.valueOf(getDigitAt(splitNumber[0], 7)) + String.valueOf(getDigitAt(splitNumber[0], 6)));
		if (millon == 1)
			converted.append("UN MILLON ");
		else if (millon > 1)
			converted.append("").append(convertNumber(String.valueOf(millon))).append("MILLONES ");

		int miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 5))
				+ String.valueOf(getDigitAt(splitNumber[0], 4)) + String.valueOf(getDigitAt(splitNumber[0], 3)));
		if (millon >= 1) {
			if (miles == 1)
				converted.append(convertNumber(String.valueOf(miles))).append("MIL ");
			else if (miles > 1)
				converted.append(convertNumber(String.valueOf(miles))).append("MIL ");
		} else {
			if (miles == 1)
				converted.append("UN MIL ");

			if (miles > 1)
				converted.append("").append(convertNumber(String.valueOf(miles))).append("MIL ");
		}

		int cientos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 2))
				+ String.valueOf(getDigitAt(splitNumber[0], 1)) + String.valueOf(getDigitAt(splitNumber[0], 0)));
		if (miles >= 1 || millon >= 1) {
			if (cientos >= 1)
				converted.append(convertNumber(String.valueOf(cientos)));
		} else {
			if (cientos == 1)
				converted.append("UN ");
			if (cientos > 1)
				converted.append("").append(convertNumber(String.valueOf(cientos)));
		}

		if (millon + miles + cientos == 0)
			converted.append("CERO ");

		String valor = splitNumber[1];
		if (valor.length() == 1) {
			converted.append(splitNumber[1]).append("0").append("/100 ");
		} else {
			converted.append(splitNumber[1]).append("/100 ");
		}
		converted.append("SOLES");
		return converted.toString();
	}

	private static String convertNumber(String number) {

		if (number.length() > 3)
			throw new NumberFormatException("La longitud maxima debe ser 3 digitos");

		if (number.equals("100")) {
			return "CIEN ";
		}

		StringBuilder output = new StringBuilder();
		if (getDigitAt(number, 2) != 0)
			output.append(CENTENAS[getDigitAt(number, 2) - 1]);

		int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1)) + String.valueOf(getDigitAt(number, 0)));

		if (k <= 20)
			output.append(UNIDADES[k]);
		else if (k > 30 && getDigitAt(number, 0) != 0)
			output.append(DECENAS[getDigitAt(number, 1) - 2]).append("Y ").append(UNIDADES[getDigitAt(number, 0)]);
		else
			output.append(DECENAS[getDigitAt(number, 1) - 2]).append(UNIDADES[getDigitAt(number, 0)]);

		return output.toString();
	}

	private static int getDigitAt(String origin, int position) {
		if (origin.length() > position && position >= 0)
			return origin.charAt(origin.length() - position - 1) - 48;
		return 0;
	}

	public static String convertIntToLetter(int doubleNumber) throws NumberFormatException {

		StringBuilder converted = new StringBuilder();

		if (doubleNumber > 999999999)
			throw new NumberFormatException("El numero es mayor de 999'999.999, " + "no es posible convertirlo");

		if (doubleNumber < 0)
			throw new NumberFormatException("El numero debe ser positivo");

		String splitNumber[] = String.valueOf(doubleNumber).replace('.', '#').split("#");

		int millon = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 8))
				+ String.valueOf(getDigitAt(splitNumber[0], 7)) + String.valueOf(getDigitAt(splitNumber[0], 6)));
		if (millon == 1)
			converted.append("UN MILLON ");
		else if (millon > 1)
			converted.append("").append(convertNumber(String.valueOf(millon))).append("MILLONES ");

		int miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 5))
				+ String.valueOf(getDigitAt(splitNumber[0], 4)) + String.valueOf(getDigitAt(splitNumber[0], 3)));
		if (millon >= 1) {
			if (miles == 1)
				converted.append(convertNumber(String.valueOf(miles))).append("MIL ");
			else if (miles > 1)
				converted.append(convertNumber(String.valueOf(miles))).append("MIL ");
		} else {
			if (miles == 1)
				converted.append("UN MIL ");

			if (miles > 1)
				converted.append("").append(convertNumber(String.valueOf(miles))).append("MIL ");
		}

		int cientos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], 2))
				+ String.valueOf(getDigitAt(splitNumber[0], 1)) + String.valueOf(getDigitAt(splitNumber[0], 0)));
		if (miles >= 1 || millon >= 1) {
			if (cientos >= 1)
				converted.append(convertNumber(String.valueOf(cientos)));
		} else {
			if (cientos == 1)
				converted.append("UN ");
			if (cientos > 1)
				converted.append("").append(convertNumber(String.valueOf(cientos)));
		}

		if (millon + miles + cientos == 0)
			converted.append("CERO ");

		return converted.toString();
	}
}
