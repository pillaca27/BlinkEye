package net.royal.spring.framework.core;

import org.apache.commons.lang.CharUtils;

public class UCharacter {
	public static Character stringToCharacter(String valor) {
		if (valor == null)
			return null;
		if (valor.length() == 0)
			return null;

		return CharUtils.toCharacterObject(valor);
	}
}
