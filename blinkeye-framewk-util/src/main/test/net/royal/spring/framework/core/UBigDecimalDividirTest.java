package net.royal.spring.framework.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import net.royal.spring.framework.util.UBigDecimal;

public class UBigDecimalDividirTest {

	public static void main(String[] args) {
		String elemento1="0";
		String elemento2="0";
		int scala=0;
		
		System.out.println("");
		System.out.println(">>>>>>> TEST 04");
		elemento1="5";
		elemento2="3";
		scala=3;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);
		//System.out.println("xx0 : "+(new BigDecimal(elemento1).divide(new BigDecimal(elemento2))));
		System.out.println("xx1 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2)));
		System.out.println("xx2 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2), scala));
		System.out.println("xx3 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2), scala,RoundingMode.HALF_EVEN));
		
		/*
		 * 2739.72602739726027397260273973 	== power builder
		 * 2739.726027    					== valor de excel
		 * */
		System.out.println("");
		System.out.println(">>>>>>> TEST 01");
		elemento1="10000";
		elemento2="3.65";
		scala=9;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);
		//System.out.println("xx0 : "+(new BigDecimal(elemento1).divide(new BigDecimal(elemento2))));
		System.out.println("xx1 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2)));
		System.out.println("xx2 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2), scala));
		System.out.println("xx3 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2), scala,RoundingMode.HALF_EVEN));
		
		System.out.println("");
		System.out.println(">>>>>>> TEST 02");
		elemento1="15";
		elemento2="3";
		scala=2;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);
		try {
			System.out.println("xx0 : "+(new BigDecimal(elemento1).divide(new BigDecimal(elemento2))));	
		} catch (Exception e) {
			System.out.println("xx0 : "+e.getMessage());
		}				
		try {
			System.out.println("xx1 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2)));
		} catch (Exception e) {
			System.out.println("xx1 : "+e.getMessage());
		}		
		System.out.println("xx2 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2), scala));
		System.out.println("xx3 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2), scala,RoundingMode.HALF_EVEN));
		
		System.out.println("");
		System.out.println(">>>>>>> TEST 03");
		elemento1="1.6";
		elemento2="9.2";
		scala=2;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);		
		//System.out.println("xx0 : "+(new BigDecimal(elemento1).divide(new BigDecimal(elemento2))));
		System.out.println("xx1 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2)));
		System.out.println("xx2 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2), scala));
		System.out.println("xx3 : "+UBigDecimal.dividir(new BigDecimal(elemento1), new BigDecimal(elemento2), scala,RoundingMode.HALF_EVEN));
	}

}
