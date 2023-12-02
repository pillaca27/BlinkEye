package net.royal.spring.framework.core;

import net.royal.spring.framework.util.UString;

public class UStringTests01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String valor = null;
		
		//valor = null;
		//System.out.println(UString.esNuloVacio(valor));
		
		//valor="";
		//System.out.println(UString.esNuloVacio(valor));
		
		//valor="dario";
		//System.out.println(UString.esNuloVacio(valor));
		
		valor="\n";
		System.out.println(UString.esNuloVacio(valor));
		
		valor="\n";
		System.out.println(UString.esNuloVacioTrim(valor));
		
		valor="\r";
		System.out.println(UString.esNuloVacioTrim(valor));
	}

}
