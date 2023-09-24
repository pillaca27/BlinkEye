package net.royal.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.BeanProvincia;
import net.royal.spring.core.dominio.BeanProvinciaPk;
import net.royal.spring.core.dominio.dto.DtoComunProvincia;
import net.royal.spring.core.dominio.filtro.FiltroComunProvincia;
import net.royal.spring.core.dominio.lista.DtlComunProvincia;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class ProvinciaDaoImpl extends GenericoDaoImpl<BeanProvincia, BeanProvinciaPk> {

	private static Logger logger = LogManager.getLogger(BeanProvincia.class);

	public ProvinciaDaoImpl() {
		super("provincia");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanProvincia obtenerPorId() {
		return obtenerPorId(new BeanProvinciaPk());
	}

	public BeanProvincia coreInsertar(BeanProvincia bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanProvincia coreActualizar(BeanProvincia bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunProvincia obtenerDtoCore(DtoComunProvincia pk) throws Exception {
		DtoComunProvincia dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunProvincia();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunProvincia> listarDtoCore(DtoComunProvincia filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Provincia.listarDtoCore : modificar query / modificar propiedades

		List datos = this.listarPorQuery(DtoComunProvincia.class, "provincia.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunProvincia obtenerDto(DtoComunProvincia pk) {
		DtoComunProvincia dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		List lst = listarPorQuery(DtoComunProvincia.class, "provincia.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunProvincia) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunProvincia filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Provincia.listarPaginado : modificar query / modificar propiedades

		Integer registros = contar("provincia.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "provincia.listarPaginadoSentencia",DtlComunProvincia.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
