package net.royal.spring.framework.core;

import net.royal.spring.framework.util.UString;

public class AsciiTest {

	public static void main(String[] args) {
		String tests = "hola dario á ddd";
		String testsou = "";
		testsou = tests.replace(Character.toString((char)225), "&aacute;");
		System.out.println(testsou);
		
		// TODO Auto-generated method stub
		//int aAcento = 225;
		char ch = (char)225;
		String sch = Character.toString(ch);
		//System.out.println("The ASCII value of aAcento is: " + aAcento);
		System.out.println("The ASCII value of aAcento is: " + ch);
		System.out.println("The ASCII value of aAcento is: " + sch);
		
		String entrada="hola á mundo";
		String salida="";
		salida = UString.cambiarCaracteresEspecialesHTML(entrada);
		System.out.println("The ASCII value of aAcento is: " + entrada);
		System.out.println("The ASCII value of aAcento is: " + salida);
	}

}
