package net.royal.spring.framework.core;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;


public class UValidador {
	public static Object validarValor(Object obj, Class clazz) {
		System.out.println("validar " + obj);
		if (clazz.equals(BigDecimal.class)) {
			if (esNulo(obj)) {
				return BigDecimal.ZERO;
			}
		} else if (clazz.equals(Integer.class)) {
			if (esNulo(obj)) {
				return Integer.valueOf(0);
			}
		} else if ((clazz.equals(String.class)) && (esNulo(obj))) {
			return "";
		}
		return obj;
	}

	/*public static String concatenarArregloValidator(List<MensajeUsuario> listaMensajeUsuario) {
		String mensajeInterno = "";
		Integer contador = 0;
		if (listaMensajeUsuario == null)
			return "";

		for (MensajeUsuario val : listaMensajeUsuario) {
			if (contador == 0) {
				mensajeInterno = val.getMensaje();
			} else {
				mensajeInterno = mensajeInterno + "\n" + val.getMensaje();
				contador++;
			}
		}
		return mensajeInterno;
	}*/

	public static String concatenarArregloValidator(Iterator<?> iterator) {
		String mensajeInterno = "";
		Integer contador = 0;
		if (iterator == null)
			return "";

		while (iterator.hasNext()) {
			ConstraintViolationImpl<?> val = (ConstraintViolationImpl<?>) iterator.next();
			if (contador == 0) {
				mensajeInterno = val.getMessage();
			} else {
				mensajeInterno = mensajeInterno + "\n" + val.getMessage();
				contador++;
			}
		}

		return mensajeInterno;
	}

	public static boolean esListaVacia(@SuppressWarnings("rawtypes") List lista) {
		if (lista == null || lista.size() == 0)
			return true;
		return false;
	}

	public static boolean esNulo(Object objeto) {
		if (objeto == null)
			return true;
		return false;
	}

	/*public static boolean esListaUsuarioSinError(List<MensajeUsuario> lista) {
		if (esListaVacia(lista))
			return true;

		for (MensajeUsuario mensajeUsuario : lista) {
			if (mensajeUsuario.getTipoMensaje().equals(tipo_mensaje.ERROR))
				return false;
		}

		return true;
	}*/

	/**
	 * Retorna FALSE cuando el objeto es diferente de NULL. Caso contrario retorna
	 * TRUE;
	 * 
	 * @param valor objeto que se evaluara, tipo Object
	 * @return TRUE o FALSE dependiendo de la condicion cumplida
	 */
	public static boolean estaVacio(Object objeto) {
		if (objeto != null)
			return false;
		return true;
	}

	/**
	 * regresa la cadena especificada en tipo Date, null si no es posible
	 * 
	 * @param variable ej. "aaa"
	 * @return boolean
	 */
	public static boolean validarEsNuloVacio(String variable) {

		if (variable == null || variable.length() == 0) {
			return true;
		}

		return false;
	}

	/**
	 * Retorna FALSE cuando el texto es diferente de NULL y diferente de vacio. Caso
	 * contrario retorna TRUE;
	 * 
	 * @param valor texto que se evaluara, tipo String
	 * @return TRUE o FALSE dependiendo de la condicion cumplida
	 */
	public static boolean estaVacio(String valor) {
		if (valor != null) {
			if (!valor.trim().equals("")) {
				return false;
			}
		}

		return true;
	}

	/**
	 * regresa la cadena especificada en tipo Date, null si no es posible
	 * 
	 * @param correo ej. "a@com.pe"
	 * @return boolean
	 */
	public static boolean validarCorreo(String correo) {

		Pattern pat = null;
		Matcher mat = null;

		pat = Pattern.compile("[a-zA-Z0-9]+[.[a-zA-Z0-9_-]+]*@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$");

		mat = pat.matcher(correo);

		if (mat.find()) {
			return true;
		} else {
			return false;
		}
	}
	


	/**
	 * Metodo que verifica si el dato ingresado es: alert(
	 * 
	 * @param valor Representa el dato a comprobar}
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isAlert(String valor) {
		return validar("alert[\\s]*\\(", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: eval(
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isEval(String valor) {
		return validar("eval[\\s]*\\(", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: onKeyUp
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isOnKeyUp(String valor) {
		return validar("onKeyUp", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: fromCharCode
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isFromCharCode(String valor) {
		return validar("fromCharCode", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: javascript
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isJavaScript(String valor) {
		return validar("javascript", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: vbscript
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isVbScript(String valor) {
		return validar("vbscript", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: http-equiv
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isHttpEquiv(String valor) {
		return validar("http-equiv", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: >
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isCaracterMayor(String valor) {
		return validar(">", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: expression
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isExpression(String valor) {
		return validar("expression[\\s]*\\(", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: innerHTML
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isInnerHTML(String valor) {
		return validar("innerHTML", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: document.body
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isDocumentBody(String valor) {
		return validar("document.body", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: document.cookie
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isDocumentCookie(String valor) {
		return validar("document.cookie", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: document.location
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isDocumentLocation(String valor) {
		return validar("document.location", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: document.write
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isDocumentWrite(String valor) {
		return validar("document.write", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: style
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isStyle(String valor) {
		return validar("style[\\s]*\\=", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: dynsrc
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isDynsrc(String valor) {
		return validar("dynsrc", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: jsessionid
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isJsessionid(String valor) {
		return validar("jsessionid", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: phpsessid
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isPhpsessid(String valor) {
		return validar("phpsessid", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: <>
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isMenorMayor(String valor) {
		return validar("((\\%3C)|<)[^\\n]+((\\%3E)|>)", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: <letra>
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isMenorMayorMedio(String valor) {
		return validar("((\\%3C)|<)((\\%2F)|V)*[a-z0-9\\%]+((\\%3E)|>)", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: </>
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isMenorMayorMedioSlash(String valor) {
		return validar("((\\%3C)|<)((\\%69)|i|(\\%49))((\\%6D)|m|(\\4D%))((\\%67)|g|(\\%47))[^\\n]+((\\%3E)|>)", valor);// mal
	}

	/**
	 * Metodo que verifica si el dato ingresado es: '
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isApostrofe(String valor) {
		return validar("(\\%27)|(\\')|(\\-\\-)|(\\%23)|(#)", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: ' ; =
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isCaracteres(String valor) {
		return validar("((\\3D)|(=))[^\\n]*((\\%27)|(\\')|(\\-\\-)|(\\%23)|(#)|(\\%3B)|(;))", valor); // mal
	}

	/**
	 * Metodo que verifica si el dato ingresado es: select
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isSelect(String valor) {
		return validar("select", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: insert
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isInsert(String valor) {
		return validar("insert", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: drop
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isDrop(String valor) {
		return validar("drop", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: update
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isUpdate(String valor) {
		return validar("update", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: alter
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isAlter(String valor) {
		return validar("([\\s]+alter|^alter)", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: delete
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isDelete(String valor) {
		return validar("delete", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: commit
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isCommit(String valor) {
		return validar("commit", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: roollback
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isRoollback(String valor) {
		return validar("roollback", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: grant
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isGrant(String valor) {
		return validar("grant", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: revoke
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isRevoke(String valor) {
		return validar("revoke", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: --
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isRayaRaya(String valor) {
		return validar("--", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: ;
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isPuntoComa(String valor) {
		return validar(";", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: =
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isIgual(String valor) {
		return validar("=", valor);
	}

	/**
	 * Metodo que verifica si el dato ingresado es: group by
	 * 
	 * @param valor Representa el dato a comprobar
	 * @return true si coicide valor con el patron de la expresion regular, false si
	 *         no hay coicidencia
	 */
	public static boolean isGroupBy(String valor) {
		return validar("group[\\s]*by", valor);
	}

	/**
	 * Metodo valida el ingreso de datos
	 * 
	 * @param valor  Representa el dato a comprobar
	 * @param expReg Representa la regla de formacion
	 * 
	 * @return boolean
	 */
	public static boolean validar(String expReg, String valor) {
		Pattern patron = Pattern.compile(expReg);
		Matcher encajador = patron.matcher(valor);
		return encajador.find();
	}

	public static boolean esCero(Integer numero) {

		if (esNulo(numero)) {
			return false;
		}

		if (numero == 0) {
			return true;
		}

		return false;
	}

	public static boolean esCerooMenor(Integer numero) {

		if (esNulo(numero)) {
			return false;
		}

		if (numero == 0) {
			return true;
		}

		if (numero < 0) {
			return true;
		}

		return false;
	}

	/**
	 * Retorna FALSE cuando el texto es igual a NULL o N o UN valor distinto a S.
	 * retorna TRUE solo en caso se pase como parametro S;
	 * 
	 * @param valor texto que se evaluara, tipo String
	 * @return TRUE o FALSE dependiendo de la condicion cumplida
	 */
	public static Boolean obtenerValorLogico(String valor) {
		if (UValidador.estaVacio(valor))
			return Boolean.FALSE;

		if (valor.equals(UConstante.CONSTANTE_OPCION_SI))
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	/**
	 * Retorna FALSE cuando el texto es igual a NULL o I o UN valor distinto a A.
	 * retorna TRUE solo en caso se pase como parametro A;
	 * 
	 * @param valor texto que se evaluara, tipo String
	 * @return TRUE o FALSE dependiendo de la condicion cumplida
	 */
	public static Boolean obtenerValorActivo(String valor) {
		if (UValidador.estaVacio(valor))
			return Boolean.FALSE;

		if (valor.equals("A"))
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	public static String obtenerValorCadenaSinNulo(String variable) {
		if (variable == null || variable.length() == 0)
			return "";
		return variable;
	}

	public static Boolean isExtensionValida(String extension) {
		return true;
	}

	/**
	 * Validar flag logico.
	 * 
	 * Funcion que recibe un valor booleano o Null y retorna N O S segun sea el
	 * caso
	 *
	 * @param condicion the condicion
	 * @return N O S segun sea el caso
	 */
	public static String validarFlagLogico(Boolean condicion) {
		if (condicion == null)
			return "N";
		if (condicion)
			return "S";
		else
			return "N";
	}

	public static Boolean validarFlag(String condicion) {
		Boolean condicionBoolean = false;
		if (condicion != null) {
			if (condicion.equals("S")) {
				condicionBoolean = true;
			}
		}
		return condicionBoolean;
	}
}
