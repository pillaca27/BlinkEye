package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfReemplazo;
import net.royal.spring.workflow.dominio.WfReemplazoPk;
import net.royal.spring.workflow.dominio.dto.DtoReemplazo;
import net.royal.spring.workflow.dominio.filtro.FiltroReemplazo;

@SuppressWarnings({ "rawtypes", "unchecked" })
@Repository
public class WfReemplazoDaoImpl extends GenericoDaoImpl<WfReemplazo, WfReemplazoPk> {

	private static final long serialVersionUID = 1L;

	public WfReemplazoDaoImpl() {
		super("wfreemplazo");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<WfReemplazo> obtenerReemplazos() {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List query = this.listarPorQuery(WfReemplazo.class, "wfreemplazo.obtenerReemplazos", parametros);
		return ((List<WfReemplazo>) query);
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DominioPaginacion paginacion,
			FiltroReemplazo filtro) {

		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UInteger.esCeroOrNulo(filtro.getId()))
			filtro.setId(null);

		if (UInteger.esCeroOrNulo(filtro.getReemplazo()))
			filtro.setReemplazo(null);

		if (UString.estaVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_empleado", Integer.class, filtro.getId()));
		parametros.add(new DominioParametroPersistencia("p_reemplazo", Integer.class, filtro.getReemplazo()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		cantidadEncontrados = this.contar("wfreemplazo.solicitudListarContar", parametros);

		List lstResultado = this.listarConPaginacion(paginacion, parametros, "wfreemplazo.solicitudListarPaginacion",
				DtoReemplazo.class);

		paginacion.setPaginacionListaResultado(lstResultado);
		paginacion.setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return paginacion;
	}

	public Integer generarSecuencia() {
		Criteria criteria = getCriteria().setProjection(Projections.max("pk.secuencia"));
		Integer calc = (Integer) criteria.uniqueResult();
		if (UInteger.esCeroOrNulo(calc)) {
			return 1;
		}
		return calc + 1;
	}

	public void reemplazarNivelesAprobacion(WfReemplazo bean, SeguridadUsuarioActual usuarioActual) {

		List<DominioParametroPersistencia> parametrosPreferencia = new ArrayList<DominioParametroPersistencia>();

		parametrosPreferencia
				.add(new DominioParametroPersistencia("p_anterior", Integer.class, bean.getEmpleadoAntiguo()));
		parametrosPreferencia.add(new DominioParametroPersistencia("p_nuevo", Integer.class, bean.getEmpleadoNuevo()));
		parametrosPreferencia
				.add(new DominioParametroPersistencia("p_usuario", String.class, usuarioActual.getUsuario()));

		ejecutarPorQuery("wfreemplazo.reemplazarNivelesAprobacion", parametrosPreferencia);

	}

	public String obtenerNombreCompletoPersona(Integer empleadoAntiguo) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_persona", Integer.class, empleadoAntiguo));
		List ls = listarPorQuery(DtoTabla.class, "wfreemplazo.obtenerNombreCompletoPersona", parametros);
		if (ls.size() == 0) {
			return empleadoAntiguo.toString();
		}
		return ((DtoTabla) ls.get(0)).getCodigo();
	}
}
