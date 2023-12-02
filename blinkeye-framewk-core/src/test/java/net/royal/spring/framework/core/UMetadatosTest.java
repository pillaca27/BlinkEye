package net.royal.spring.framework.core;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;

public class UMetadatosTest {

	public static void main(String[] args) {
		// TODO Auto-generated me89thod stub
		MiClase obj = new MiClase();
		obj.setPk(new MiClasePk());
		obj.setDni("10700044");
		obj.setEdad(416670697);
		obj.setFechaNacimiento(new Date());
		obj.setSueldo(new BigDecimal(2034505.7364));
		obj.setImpuesto(2602740233.23163);
		
		obj.getPk().setCompania("00010000");
		obj.getPk().setSecuencia(976);
		
		@SuppressWarnings("unchecked")
		Map<String, Object> map = UMetadatos.obtener(obj);
		
		for(Map.Entry<String, Object> entry : map.entrySet()) {
		    String key = entry.getKey();
		    Object value = entry.getValue();
		    System.out.println(key);
			System.out.println("    ::::"+value);
		}
		System.out.println("=======================================");
		List<DominioParametroPersistencia> lst = UMetadatos.obtenerParametros(map);
		for (DominioParametroPersistencia d : lst) {
			System.out.println(d.getCampo());
			System.out.println("    >>>"+d.getValor());
		}
		System.out.println(map);
	}

}
