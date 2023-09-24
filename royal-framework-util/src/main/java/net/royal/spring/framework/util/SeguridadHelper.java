package net.royal.spring.framework.util;

public class SeguridadHelper {
	/**
	 * Encripta el texto enviad como parametro
	 *
	 * @param texto cadena de texto que se encriptada, tipo String
	 * @return texto encriptado, tipo String
	 */
	public static String springEncriptar(String texto) {

		String desencriptado = null;

		if (texto != null && !texto.equals("")) {

			desencriptado = "";
			// Contador que sirve para agregar valor al ascii generado
			// por cada caracter del texto
			int plus = 1;

			for (int i = 0; i < texto.length(); i++) {
				char caracter = texto.charAt(i);

				int ascii = UString.convertChar2Ascii(caracter) + plus;

				desencriptado = desencriptado + UString.convertAscii2Char(ascii);

				plus++;
			}

			return desencriptado;
		}
		return null;
	}

	/**
	 * Desencripta el password enviado como parametro
	 *
	 * @param password texto encriptado, tipo String
	 * @return texto desencriptado, tipo String
	 */
	public static String springDesencriptar(String password) {

		String encriptado = null;

		if (password != null && !password.equals("")) {

			encriptado = "";
			int minus = 1;

			for (int i = 0; i < password.length(); i++) {
				char caracter = password.charAt(i);

				int ascii = UString.convertChar2Ascii(caracter) - minus;
				encriptado = encriptado + UString.convertAscii2Char(ascii);

				minus++;
			}

			return encriptado;
		}
		return null;
	}
}
