package net.royal.spring.framework.core;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UString;

public class UMetadatos {

	public static List<DominioParametroPersistencia> obtenerParametros(Map<String, Object> map) {
		return obtenerParametros(map, null);
	}

	public static List<DominioParametroPersistencia> obtenerParametros(Map<String, Object> map,
			List<DominioParametroPersistencia> lst) {
		if (lst == null)
			lst = new ArrayList<DominioParametroPersistencia>();

		for (Map.Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			//System.out.println(key);
			//System.out.println(value.getClass().getName());
			if (value==null) {
				lst.add(new DominioParametroPersistencia(key, String.class, value));
			}else {
				switch (value.getClass().getName()) {
				case "java.lang.String":
					lst.add(new DominioParametroPersistencia(key, String.class, value));
					break;
				case "java.lang.Integer":
					lst.add(new DominioParametroPersistencia(key, Integer.class, value));
					break;
				case "java.math.BigDecimal":
					lst.add(new DominioParametroPersistencia(key, BigDecimal.class, value));
					break;
				case "java.util.Date":
					lst.add(new DominioParametroPersistencia(key, java.util.Date.class, value));
					break;
				case "double":
					lst.add(new DominioParametroPersistencia(key, double.class, value));
					break;
				default:
					break;
				}
			}			
		}
		return lst;
	}
	public static Map obtener(Object obj) {
		return obtener(obj,null,true,null);
	}
	public static Map obtener(Object obj,Map<String, Object> map,Boolean flgMultinivel,String nivel) {
		//Map<String, Object> map = new HashMap<String, Object>();
		if (map==null)
			map = new HashMap<String, Object>();
		if (obj == null)
			return map;
		nivel = UString.obtenerValorCadenaSinNulo(nivel);
		
		for (Field f : obj.getClass().getDeclaredFields()) {
			String nombre = null;
			Object value = null;
			Object valueReal = null;
			Class type = null;
			try {
				f.setAccessible(true);
				type = f.getType();
				nombre = f.getName();
				value = f.get(obj);

				switch (type.getName()) {
				case "java.lang.String":
					valueReal = value;
					if (value == null)
						map.put("p_" + nivel + nombre, "");
					else
						map.put("p_" + nivel + nombre, valueReal);
					break;
				case "java.lang.Integer":
					valueReal = value;
					if (value == null) {
						map.put("p_" + nivel + nombre, "");
						map.put("p_" + nivel + nombre + "_mdformato", "");
					} else {
						map.put("p_" + nivel + nombre, valueReal);
						map.put("p_" + nivel + nombre + "_mdformato", String.format("%,d", value));
					}
					break;
				case "java.math.BigDecimal":
					valueReal = value;
					if (value == null) {
						map.put("p_" + nivel + nombre, "");
						map.put("p_" + nivel + nombre + "_mdformato", "");
					} else {
						map.put("p_" + nivel + nombre, valueReal);
						map.put("p_" + nivel + nombre + "_mdformato",UBigDecimal.convertirCadena((BigDecimal) value, "#,###,###.0000"));
					}
					break;
				case "double":
					valueReal = value;
					map.put("p_" + nivel + nombre, valueReal);
					break;
				case "java.util.Date":
					valueReal = value;
					if (value == null) {
						map.put("p_" + nivel + nombre, "");
						map.put("p_" + nivel + nombre + "_mdformatofechahora", "");
						map.put("p_" + nivel + nombre + "_mdformatofecha", "");
						map.put("p_" + nivel + nombre + "_mdformatohora", "");
					} else {
						map.put("p_" + nivel + nombre, valueReal);
						map.put("p_" + nivel + nombre + "_mdformatofechahora",UFechaHora.convertirFechaCadena((Date) value, "dd/MM/yyyy hh:mm:ss aaa"));
						map.put("p_" + nivel + nombre + "_mdformatofecha",UFechaHora.convertirFechaCadena((Date) value, "dd/MM/yyyy"));
						map.put("p_" + nivel + nombre + "_mdformatohora",UFechaHora.convertirFechaCadena((Date) value, "hh:mm:ss aaa"));
					}
					break;
				default:
					System.out.println("==> default");
					System.out.println(type.getName());
					System.out.println(nombre);		
					valueReal = value;
					if (value == null) {
						//map.put("p_" + nombre, "");
					}						
					else {
						//map.put("p_" + nombre, valueReal);
						if (flgMultinivel)
							map = obtener(valueReal,map,false,nombre+"_");
					}						
				}

			} catch (Exception e) {
				System.out.println("==> Exception");
				System.out.println(type.getName());
				System.out.println(nombre);								
				e.printStackTrace();
			}

		}
		return map;
	}
}
