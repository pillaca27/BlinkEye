package net.royal.spring.framework.util;

public class UObject {
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
	
}
