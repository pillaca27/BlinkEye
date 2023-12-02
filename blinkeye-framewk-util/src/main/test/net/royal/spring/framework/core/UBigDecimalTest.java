package net.royal.spring.framework.core;

import java.math.BigDecimal;
import java.math.RoundingMode;

import net.royal.spring.framework.util.UBigDecimal;

public class UBigDecimalTest {

	public static void main(String[] args) {
		//BigDecimal dd = new BigDecimal(755145444.33); 
		//System.out.println(UBigDecimal.convertirCadena(dd));
		String elemento1="1085.56";
		String elemento2="1.00";
		int scala=2;
		
		System.out.println("");
		System.out.println(">>>>>>> TEST 05");
		elemento1="-67";
		elemento2="25";
		scala=2;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);		
		System.out.println("xx0 : "+(new BigDecimal(elemento1).multiply(new BigDecimal(elemento2))));
		System.out.println("xx1 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2)));
		System.out.println("xx2 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala));
		System.out.println("xx3 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala,RoundingMode.HALF_EVEN));
		
		
		System.out.println("");
		System.out.println(">>>>>>> TEST 04");
		elemento1="34.203";
		elemento2="44.25";
		scala=2;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);		
		System.out.println("xx0 : "+(new BigDecimal(elemento1).multiply(new BigDecimal(elemento2))));
		System.out.println("xx1 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2)));
		System.out.println("xx2 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala));
		System.out.println("xx3 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala,RoundingMode.HALF_EVEN));
		
		System.out.println("");
		System.out.println(">>>>>>> TEST 03");
		elemento1="54.2";
		elemento2="14.20";
		scala=2;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);		
		System.out.println("xx0 : "+(new BigDecimal(elemento1).multiply(new BigDecimal(elemento2))));
		System.out.println("xx1 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2)));
		System.out.println("xx2 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala));
		System.out.println("xx3 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala,RoundingMode.HALF_EVEN));
		
		System.out.println("");
		System.out.println(">>>>>>> TEST 02");
		elemento1="1085.564";
		elemento2="1.002";
		scala=5;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);		
		System.out.println("xx0 : "+(new BigDecimal(elemento1).multiply(new BigDecimal(elemento2))));
		System.out.println("xx1 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2)));
		System.out.println("xx2 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala));
		System.out.println("xx3 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala,RoundingMode.HALF_EVEN));
		
		System.out.println("");
		System.out.println(">>>>>>> TEST (CASO ORLANDO)");
		elemento1="1085.56";
		elemento2="1.00";
		scala=2;
		System.out.println("elemento1: "+elemento1);
		System.out.println("elemento2: "+elemento2);
		System.out.println("scala    : "+scala);
		System.out.println("xx0 : "+(new BigDecimal(elemento1).multiply(new BigDecimal(elemento2))));
		System.out.println("xx1 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2)));
		System.out.println("xx2 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala));
		System.out.println("xx3 : "+UBigDecimal.multiplicar(new BigDecimal(elemento1), new BigDecimal(elemento2), scala,RoundingMode.HALF_EVEN));
	}

}
