package net.royal.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.BeanCompanyowner;
import net.royal.spring.core.dominio.BeanCompanyownerPk;
import net.royal.spring.core.dominio.dto.DtoComunCompanyowner;
import net.royal.spring.core.dominio.filtro.FiltroComunCompanyowner;
import net.royal.spring.core.dominio.lista.DtlComunCompanyowner;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class CompanyownerDaoImpl extends GenericoDaoImpl<BeanCompanyowner, BeanCompanyownerPk> {

	private static Logger logger = LogManager.getLogger(BeanCompanyowner.class);

	public CompanyownerDaoImpl() {
		super("companyowner");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanCompanyowner obtenerPorId(String pcompanyowner) {
		return obtenerPorId(new BeanCompanyownerPk( pcompanyowner));
	}

	public BeanCompanyowner coreInsertar(BeanCompanyowner bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanCompanyowner coreActualizar(BeanCompanyowner bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunCompanyowner obtenerDtoCore(DtoComunCompanyowner pk) throws Exception {
		DtoComunCompanyowner dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunCompanyowner();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunCompanyowner> listarDtoCore(DtoComunCompanyowner filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Companyowner.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));

		List datos = this.listarPorQuery(DtoComunCompanyowner.class, "companyowner.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunCompanyowner obtenerDto(DtoComunCompanyowner pk) {
		DtoComunCompanyowner dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, pk.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, pk.getCompanyowner()));

		List lst = listarPorQuery(DtoComunCompanyowner.class, "companyowner.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunCompanyowner) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunCompanyowner filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Companyowner.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));
		parametros.add(new DominioParametroPersistencia("p_companyowner", String.class, filtro.getCompanyowner()));

		Integer registros = contar("companyowner.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "companyowner.listarPaginadoSentencia",DtlComunCompanyowner.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
