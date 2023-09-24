package net.royal.spring.rrhh.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.rrhh.dominio.BeanHrCapacitacion;
import net.royal.spring.rrhh.dominio.BeanHrCapacitacionPk;
import net.royal.spring.rrhh.dominio.dto.DtoHrCapacitacion;
import net.royal.spring.rrhh.dominio.dto.DtoHrEmpleadoCapacitacion;
import net.royal.spring.rrhh.dominio.filtro.FiltroHrCapacitacion;

@Repository
public class HrCapacitacionDaoImpl extends GenericoDaoImpl<BeanHrCapacitacion, BeanHrCapacitacionPk> {

	public HrCapacitacionDaoImpl() {
		super("hrcapacitacion");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DominioPaginacion listarPaginacion(FiltroHrCapacitacion filtro, SeguridadUsuarioActual usuarioActual) {

		// Limpiar Filtros
		if (UString.estaVacio(filtro.getCapacitacion()))
			filtro.setCapacitacion(null);
		if (UString.estaVacio(filtro.getCompanyOwner()))
			filtro.setCompanyOwner(null);
		if (UString.estaVacio(filtro.getEstado()))
			filtro.setEstado(null);

		// Agregar Parametros
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_capacitacion", String.class, filtro.getCapacitacion()));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompanyOwner()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_desde", Date.class, filtro.getFechaInicio()));
		parametros.add(new DominioParametroPersistencia("p_hasta", Date.class, filtro.getFechaFin()));
		parametros.add(new DominioParametroPersistencia("p_solicitante", Integer.class, usuarioActual.getPersonaId()));

		// Contar
		Integer cantidad = contar("hrcapacitacion.contarPaginacion", parametros);

		// Lista
		List lst = listarConPaginacion(filtro.getDominioPaginacion(), parametros, "hrcapacitacion.listarPaginacion",
				DtoHrCapacitacion.class);

		// Enviar resultados
		filtro.getDominioPaginacion().setPaginacionRegistrosEncontrados(cantidad);
		filtro.getDominioPaginacion().setPaginacionListaResultado(lst);

		return filtro.getDominioPaginacion();
	}

	public String generarCodigo(String companyOwner, SeguridadUsuarioActual usuarioActual) {
		String unidadReplicacion = usuarioActual.getUnidadReplicacionCodigo();
		if (UString.estaVacio(unidadReplicacion)) {
			unidadReplicacion = "LIMA";
		}
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, companyOwner));
		parametros.add(new DominioParametroPersistencia("p_unidad", String.class, unidadReplicacion));
		List lst = listarPorQuery(DtoTabla.class, "hrcapacitacion.generarCodigo", parametros);
		if (lst.size() == 0) {
			return null;
		}
		return ((DtoTabla) (lst.get(0))).getCodigo();
	}

	public DtoHrCapacitacion obtenerDto(String companyOwner, String capacitacion) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, companyOwner));
		parametros.add(new DominioParametroPersistencia("p_capacitacion", String.class, capacitacion));
		List lst = listarPorQuery(DtoHrCapacitacion.class, "hrcapacitacion.obtenerDto", parametros);
		if (lst.size() == 0) {
			return null;
		}
		return ((DtoHrCapacitacion) (lst.get(0)));
	}

}
