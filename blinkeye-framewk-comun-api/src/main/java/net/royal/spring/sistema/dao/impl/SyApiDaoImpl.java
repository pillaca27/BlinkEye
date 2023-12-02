package net.royal.spring.sistema.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyApi;
import net.royal.spring.sistema.dominio.BeanSyApiPk;
import net.royal.spring.sistema.dominio.dto.DtoComunWsApipath;
import net.royal.spring.sistema.dominio.filtro.FiltroComunApi;

@Repository
public class SyApiDaoImpl extends GenericoDaoImpl<BeanSyApi, BeanSyApiPk> {

	private static final long serialVersionUID = 1L;

	public SyApiDaoImpl() {
		super("syapi");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<DtoTabla> listarApis() {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List lst = listarPorQuery(DtoTabla.class, "syapi.listarApis", parametros);
		return lst;
	}

	public DominioPaginacion listarrutaspaginado(FiltroComunApi filtro) {

		if (UString.estaVacio(filtro.getRuta()))
			filtro.setRuta(null);

		DominioPaginacion paginacion = filtro.getPaginacion();
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_ruta", String.class, filtro.getRuta()));

		cantidadEncontrados = contar("syapi.contarRutaPaginacion", parametros);
		List lstResultado = listarConPaginacion(paginacion, parametros, "syapi.listarRutaPaginacion",
				DtoComunWsApipath.class);

		paginacion.setPaginacionListaResultado(lstResultado);
		paginacion.setPaginacionRegistrosEncontrados(cantidadEncontrados);
		return paginacion;
	}

	public Integer generarId() {
		Criteria criteria = getCriteria().setProjection(Projections.max("pk.idapi"));
		Integer calc = (Integer) criteria.uniqueResult();

		if (calc == null) {
			return 1;
		}
		return calc.intValue() + 1;
	}

}
