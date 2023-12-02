package net.royal.spring.comun.api.impl;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import net.royal.spring.core.dominio.BeanCompanyownerrecurso;
import net.royal.spring.framework.constante.ConstanteDatos;
import net.royal.spring.framework.modelo.CompanyownerrecursoTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.api.impl.GenericoApiImpl;

@Repository
public class CompanyownerrecursoComunApiImpl extends GenericoApiImpl<DtoTabla> {

	public CompanyownerrecursoComunApiImpl() throws Exception {
		super(GenericoApiImpl.proxyComun, "spring/core/companyownerrecursocomun/",
				new ParameterizedTypeReference<List<DtoTabla>>() {
				});
	}

	public String obtenerImagenComoCadena(String compania, ConstanteDatos.TIPO_IMAGEN tipoImagen, String periodo,
			String tipoReporte) throws Exception {

		CompanyownerrecursoTransaccion dto = new CompanyownerrecursoTransaccion();
		dto.setCompania(compania);
		dto.setTipoImagen(tipoImagen);
		dto.setPeriodo(periodo);
		dto.setTipoReporte(tipoReporte);

		String api = apiUrl + "obtenerImagenComoCadena";
		return new RestTemplate()
				.exchange(new URI(api), HttpMethod.PUT,
						new HttpEntity<CompanyownerrecursoTransaccion>(dto, this.obtenerHeaders()), String.class)
				.getBody();
	}
	
	public BeanCompanyownerrecurso obtenerRecurso(String compania, ConstanteDatos.TIPO_IMAGEN tipoImagen, String periodo,
			String tipoReporte) throws Exception {

		CompanyownerrecursoTransaccion dto = new CompanyownerrecursoTransaccion();
		dto.setCompania(compania);
		dto.setTipoImagen(tipoImagen);
		dto.setPeriodo(periodo);
		dto.setTipoReporte(tipoReporte);

		String api = apiUrl + "obtenerRecurso";
		return new RestTemplate()
				.exchange(new URI(api), HttpMethod.PUT,
						new HttpEntity<CompanyownerrecursoTransaccion>(dto, this.getHeadersSeguro()), BeanCompanyownerrecurso.class)
				.getBody();
	}
	
}
