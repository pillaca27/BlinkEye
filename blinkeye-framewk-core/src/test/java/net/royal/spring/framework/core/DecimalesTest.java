package net.royal.spring.framework.core;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;

public class DecimalesTest {

	public static void main(String[] args) {
		
		BigDecimal monto = new BigDecimal(9.9946);
		BigDecimal monto2 = new BigDecimal(8.47458);
		
		System.out.println("CEILING: "+monto.setScale(2, RoundingMode.CEILING));
		System.out.println("DOWN: "+monto.setScale(2, RoundingMode.DOWN));
		System.out.println("CEILING: "+monto.setScale(2, RoundingMode.FLOOR));
		System.out.println("FLOOR: "+monto.setScale(2, RoundingMode.FLOOR));
		System.out.println("HALF_DOWN: "+monto.setScale(2, RoundingMode.HALF_DOWN));
		System.out.println("HALF_EVEN: "+monto.setScale(2, RoundingMode.HALF_EVEN));
		System.out.println("HALF_UP: "+monto.setScale(2, RoundingMode.HALF_UP));
		
		BigDecimal w_amount_2 = new BigDecimal(6000000.0000);
		BigDecimal str_global_gv_rate = new BigDecimal(3.854000);

		
		//w_amount_2=w_amount_2.divide(str_global_gv_rate).setScale(4,RoundingMode.HALF_UP).setScale(4,RoundingMode.HALF_UP);		
		w_amount_2 = UBigDecimal.dividir(w_amount_2, str_global_gv_rate, 4, RoundingMode.HALF_UP);		
		System.out.println("resultado "+w_amount_2);
		try {
			System.out.println("UNNECESSARY: "+monto.setScale(2, RoundingMode.UNNECESSARY));
			System.out.println("resultado "+w_amount_2);
		} catch (Exception e) {
			System.out.println("UNNECESSARY: "+e.getMessage());
		}
		
		System.out.println("UP: "+monto.setScale(2, RoundingMode.UP));			
		
	}

}
