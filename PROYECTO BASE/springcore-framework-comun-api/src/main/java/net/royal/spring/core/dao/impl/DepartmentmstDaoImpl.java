package net.royal.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.core.dominio.BeanDepartmentmst;
import net.royal.spring.core.dominio.BeanDepartmentmstPk;
import net.royal.spring.core.dominio.dto.DtoComunDepartmentmst;
import net.royal.spring.core.dominio.filtro.FiltroComunDepartmentmst;
import net.royal.spring.core.dominio.lista.DtlComunDepartmentmst;

@Repository
public class DepartmentmstDaoImpl extends GenericoDaoImpl<BeanDepartmentmst, BeanDepartmentmstPk> {

	private static Logger logger = LogManager.getLogger(BeanDepartmentmst.class);

	public DepartmentmstDaoImpl() {
		super("departmentmst");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanDepartmentmst obtenerPorId(String pdepartment) {
		return obtenerPorId(new BeanDepartmentmstPk( pdepartment));
	}

	public BeanDepartmentmst coreInsertar(BeanDepartmentmst bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanDepartmentmst coreActualizar(BeanDepartmentmst bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunDepartmentmst obtenerDtoCore(DtoComunDepartmentmst pk) throws Exception {
		DtoComunDepartmentmst dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunDepartmentmst();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunDepartmentmst> listarDtoCore(DtoComunDepartmentmst filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Departmentmst.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_department", String.class, filtro.getDepartment()));
		parametros.add(new DominioParametroPersistencia("p_department", String.class, filtro.getDepartment()));

		List datos = this.listarPorQuery(DtoComunDepartmentmst.class, "departmentmst.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunDepartmentmst obtenerDto(DtoComunDepartmentmst pk) {
		DtoComunDepartmentmst dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_department", String.class, pk.getDepartment()));

		List lst = listarPorQuery(DtoComunDepartmentmst.class, "departmentmst.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunDepartmentmst) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunDepartmentmst filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getDepartment()))
			filtro.setDepartment(null);
		// TODO Departmentmst.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_department", String.class, filtro.getDepartment()));

		Integer registros = contar("departmentmst.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "departmentmst.listarPaginadoSentencia",DtlComunDepartmentmst.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion exportarDepartamentosMast(FiltroComunDepartmentmst filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getDepartment()))
			filtro.setDepartment(null);
		// TODO Departmentmst.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_department", String.class, filtro.getDepartment()));
		List lst = listarPorQuery(DtlComunDepartmentmst.class, "departmentmst.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
