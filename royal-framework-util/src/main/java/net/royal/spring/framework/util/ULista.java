package net.royal.spring.framework.util;

import java.util.List;

public class ULista {
	public static boolean esListaVacia(@SuppressWarnings("rawtypes") List lista) {
		if (lista == null || lista.size() == 0)
			return true;
		return false;
	}
}
