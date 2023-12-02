package net.royal.spring.comun.api.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosdetalle;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.api.impl.GenericoApiImpl;

@Repository
public class MaMiscelaneosdetalleComunApiImpl extends GenericoApiImpl<DtoComunMaMiscelaneosdetalle> {

	public MaMiscelaneosdetalleComunApiImpl() throws Exception {
		super(GenericoApiImpl.proxyComun, "spring/core/mamiscelaneosdetallecomun",
				new ParameterizedTypeReference<List<DtoComunMaMiscelaneosdetalle>>() {
				});
	}

	private DtoComunMaMiscelaneosdetalle obtenerDto(DtoComunMaMiscelaneosdetalle dto) {
		return this.putObtenerPorId("obtenerdto", dto);
	}

	public List<DtoComunMaMiscelaneosdetalle> listardtoactivosporaplicacioncodigotabla(
			DtoComunMaMiscelaneosdetalle dto) {
		return this.putToLista("listardtoactivosporaplicacioncodigotabla", dto);
	}

	/**
	 * f_sql_get_miscelaneo
	 * 
	 * @param par_companyowner
	 * @param par_aplicacion
	 * @param par_tabla
	 * @param par_codigoelemento
	 * @param par_dato
	 * @return
	 */
	public Object obtenerDato(String par_companyowner, String par_aplicacion, String par_tabla,
			String par_codigoelemento, String par_dato) {

		par_companyowner = UString.trimSinNulo(par_companyowner);

		if (par_companyowner.length() > 6) {
			par_companyowner = par_companyowner.substring(0, 6);
		}

		DtoComunMaMiscelaneosdetalle pk = new DtoComunMaMiscelaneosdetalle();
		pk.setAplicacioncodigo(par_aplicacion);
		pk.setCodigotabla(par_tabla);
		pk.setCompania(par_companyowner);
		pk.setCodigoelemento(par_codigoelemento);

		DtoComunMaMiscelaneosdetalle dto = obtenerDto(pk);

		if (dto == null) {
			return null;
		}

		switch (par_dato) {
		case "descripcion":
			return UString.trimSinNulo(dto.getDescripcionlocal());
		case "valorcodigo1":
			return UString.trimSinNulo(dto.getValorcodigo1());
		case "valorcodigo2":
			return UString.trimSinNulo(dto.getValorcodigo2());
		case "valorcodigo3":
			return UString.trimSinNulo(dto.getValorcodigo3());
		case "valorcodigo4":
			return UString.trimSinNulo(dto.getValorcodigo4());
		case "valorcodigo5":
			return UString.trimSinNulo(dto.getValorcodigo5());
		case "valorcodigo6":
			return UString.trimSinNulo(dto.getValorcodigo6());
		case "valorcodigo7":
			return UString.trimSinNulo(dto.getValorcodigo7());
		case "valorcodigo8":
			return UString.trimSinNulo(dto.getValorcodigo8());
		case "valorcodigo9":
			return UString.trimSinNulo(dto.getValorcodigo9());
		case "valorcodigo10":
			return UString.trimSinNulo(dto.getValorcodigo10());
		case "valornumerico":
			return dto.getValornumerico();
		case "valorfecha":
			return dto.getValorfecha();
		default:
			return null;
		}
	}
}
