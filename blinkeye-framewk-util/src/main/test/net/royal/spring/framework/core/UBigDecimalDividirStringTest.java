package net.royal.spring.framework.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import net.royal.spring.framework.util.UBigDecimal;

public class UBigDecimalDividirStringTest {

	public static void main(String[] args) {
		String elemento1="0";
		String elemento2="0";
		int scala=0;
		
		/*
		 * 2739.7260273972 == power builder
		 * 2739.726027     == valor de excel
		 * */
		System.out.println("");
		System.out.println(">>>>>>> TEST 01");
		elemento1="10000";
		elemento2="3.65";
		scala=9;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);		
		
		System.out.println("xx2 : "+UBigDecimal.dividir(elemento1, elemento2));
		System.out.println("xx2 : "+UBigDecimal.dividir(elemento1, elemento2, scala));
		System.out.println("xx3 : "+UBigDecimal.dividir(elemento1, elemento2, scala,RoundingMode.HALF_EVEN));

	}

}
