package net.royal.spring.core.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.exolab.castor.types.DateTime;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dominio.BeanError;
import net.royal.spring.core.dominio.filtro.FiltroComunPaginacionErrores;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.dto.DtoComunError;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyLogWeb;

@Repository
public class ErrorDaoImpl extends GenericoDaoImpl<BeanError, Integer> {

	public ErrorDaoImpl() {
		super("error");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	/*
	 * public ErrorBean obtenerPorId(Integer pidError) { return
	 * obtenerPorId(pidError); }
	 */

	public Integer contarErroresPorReglaNegocio(Integer idReglaNegocio) {
		org.hibernate.Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("estado", "ACT"));
		cri.add(Restrictions.eq("idReglaNegocio", idReglaNegocio));
		cri.setProjection(Projections.rowCount());
		Long count = (Long) cri.uniqueResult();
		return count.intValue();
	}

	/*
	 * public Integer generarSecuencia() { Criteria c =
	 * this.getCriteria().setProjection(Projections.projectionList().add(Projections
	 * .max("pk.idError"))); return this.incrementarInteger(c); }
	 */

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunSyLogWeb filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_desde", Date.class, filtro.getDesde()));
		parametros.add(new DominioParametroPersistencia("p_hasta", Date.class, filtro.getHasta()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("error.contarlistarPaginacion", parametros);
		List<?> datos = listarConPaginacion(filtro.getPaginacion(), parametros, "error.listarPaginacion",
				DtoComunError.class);

		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	public DominioPaginacion listarErrores(FiltroComunPaginacionErrores filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		Date dd=null;
		if (filtro.getFechaPreparacionInicio()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionInicio());
			filtro.setFechaPreparacionInicio(dd);
		}
		if (filtro.getFechaPreparacionFin()!=null) {
			dd = UFechaHora.obtenerFechaHoraInicioDia(filtro.getFechaPreparacionFin());
			filtro.setFechaPreparacionFin(dd);
		}
		
		parametros.add(new DominioParametroPersistencia("idreglanegocio", BigDecimal.class, filtro.getIdreglanegocio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionInicio", DateTime.class,filtro.getFechaPreparacionInicio()));
		parametros.add(new DominioParametroPersistencia("fechaPreparacionFin", DateTime.class, filtro.getFechaPreparacionFin()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getP_estado()));

		Integer registros = contar("error.alertasContarlistarPaginacion", parametros);
		List datos = listarConPaginacion(filtro.getPaginacion(), parametros, "error.alertasListarPaginacion", DtoComunError.class);
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros.intValue());
		filtro.getPaginacion().setPaginacionListaResultado(datos);
		return filtro.getPaginacion();
	}

	@Transactional
	public DtoComunError actualizarEstadoMasivo(DtoComunError error) throws UException {
		for (DtoComunError bean : error.getLstParaModificar()) {
			int id = bean.getIdError().intValue();
			BeanError dataBean = obtenerPorId(id);
			dataBean.setEstado(bean.getEstado());
			actualizar(dataBean);
		}
		return error;
	}

	@Transactional
	public void elimpiarInformativos(BeanError error) throws UException {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("estado", String.class, error.getEstado()));
		ejecutarPorQuery("error.eliminarRegistrosLimpiar", parametros);
	}

}
