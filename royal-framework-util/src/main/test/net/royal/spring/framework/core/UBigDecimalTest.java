package net.royal.spring.framework.core;

import java.math.BigDecimal;

import net.royal.spring.framework.util.UBigDecimal;

public class UBigDecimalTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigDecimal dd = new BigDecimal(755145444.33); 
		System.out.println(UBigDecimal.convertirCadena(dd));
	}

}
