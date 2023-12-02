package net.royal.spring.framework.core;

import java.io.IOException;

import net.royal.spring.framework.util.UString;

public class AsciiTest {

	public static void main(String[] args) {
		String tests = " 'Prueba' -  \"Prueba\"  ";
		String testsou = "";
		
		try {
			testsou = UFile.quitarCaracteresExtranos(tests);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(testsou);
		
		//testsou = tests.replaceAll(Character.toString((char)182), "A");
		//System.out.println(testsou);
		
		// TODO Auto-generated method stub
		//int aAcento = 225;
		char ch = (char)225;
		String sch = Character.toString(ch);
		//System.out.println("The ASCII value of aAcento is: " + aAcento);
		System.out.println("The ASCII value of aAcento is: " + ch);
		System.out.println("The ASCII value of aAcento is: " + sch);
		
		String entrada="hola mundo";
		String salida="";
		salida = UString.cambiarCaracteresEspecialesHTML(entrada);
		System.out.println("The ASCII value of aAcento is: " + entrada);
		System.out.println("The ASCII value of aAcento is: " + salida);
	}

}
