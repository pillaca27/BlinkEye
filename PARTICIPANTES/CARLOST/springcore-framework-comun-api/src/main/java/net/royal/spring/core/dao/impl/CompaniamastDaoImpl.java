package net.royal.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.BeanCompaniamast;
import net.royal.spring.core.dominio.BeanCompaniamastPk;
import net.royal.spring.core.dominio.dto.DtoComunCompaniamast;
import net.royal.spring.core.dominio.dto.DtoComunCompanyowner;
import net.royal.spring.core.dominio.dto.DtoComunReportingcompany;
import net.royal.spring.core.dominio.filtro.FiltroComunCompaniamast;
import net.royal.spring.core.dominio.lista.DtlComunCompaniamast;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class CompaniamastDaoImpl extends GenericoDaoImpl<BeanCompaniamast, BeanCompaniamastPk> {

	private static Logger logger = LogManager.getLogger(BeanCompaniamast.class);

	public CompaniamastDaoImpl() {
		super("companiamast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanCompaniamast obtenerPorId(String pcompaniacodigo) {
		return obtenerPorId(new BeanCompaniamastPk( pcompaniacodigo));
	}

	public BeanCompaniamast coreInsertar(BeanCompaniamast bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanCompaniamast coreActualizar(BeanCompaniamast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunCompaniamast obtenerDtoCore(DtoComunCompaniamast pk) throws Exception {
		DtoComunCompaniamast dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunCompaniamast();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, dto.getCompaniacodigo()));
		List lstDetalle1 = listarPorQuery(DtoComunCompanyowner.class, "companiamast.obtenerDtoDetalle1", parametros);
		List lstDetalle2 = listarPorQuery(DtoComunReportingcompany.class, "companiamast.obtenerDtoDetalle2", parametros);
		
		if(lstDetalle1.size()>0) {
			dto.setDetalle1(lstDetalle1);
		}
		if(lstDetalle2.size()>0) {
			dto.setDetalle2(lstDetalle2);
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunCompaniamast> listarDtoCore(DtoComunCompaniamast filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Companiamast.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));

		List datos = this.listarPorQuery(DtoComunCompaniamast.class, "companiamast.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunCompaniamast obtenerDto(DtoComunCompaniamast pk) {
		DtoComunCompaniamast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, pk.getCompaniacodigo()));

		List lst = listarPorQuery(DtoComunCompaniamast.class, "companiamast.obtenerDto", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunCompaniamast) lst.get(0);		
		}
			
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunCompaniamast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getCompaniacodigo()))
			filtro.setCompaniacodigo("");
		
		// TODO Companiamast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));

		Integer registros = contar("companiamast.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "companiamast.listarPaginadoSentencia",DtlComunCompaniamast.class);
		
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion exportarCompanias(FiltroComunCompaniamast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getCompaniacodigo()))
			filtro.setCompaniacodigo("");
		
		// TODO Companiamast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));

		List lst = listarPorQuery(DtlComunCompaniamast.class, "companiamast.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
