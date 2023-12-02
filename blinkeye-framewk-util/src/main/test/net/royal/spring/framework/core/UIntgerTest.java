package net.royal.spring.framework.core;

import net.royal.spring.framework.util.UInteger;

public class UIntgerTest {

	public static void main(String[] args) throws Exception {
		String s = UInteger.obtenerValorCadenaRelleno(1,"0000000000000",10);
		System.out.println(s);
		
		s = UInteger.obtenerValorCadenaRelleno(198,"0000000000000",10);
		System.out.println(s);
		
		s = UInteger.obtenerValorCadenaRelleno(19876,"0000000000000",10);
		System.out.println(s);
	}
}
