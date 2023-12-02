package net.royal.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.core.dominio.BeanMaPersonagrupo;
import net.royal.spring.core.dominio.BeanMaPersonagrupoPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonagrupo;
import net.royal.spring.core.dominio.filtro.FiltroComunMaPersonagrupo;
import net.royal.spring.core.dominio.lista.DtlComunMaPersonagrupo;

@Repository
public class MaPersonagrupoDaoImpl extends GenericoDaoImpl<BeanMaPersonagrupo, BeanMaPersonagrupoPk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(BeanMaPersonagrupo.class);

	public MaPersonagrupoDaoImpl() {
		super("mapersonagrupo"); 
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanMaPersonagrupo obtenerPorId(String ppersonagrupo) {
		return obtenerPorId(new BeanMaPersonagrupoPk( ppersonagrupo));
	}

	public BeanMaPersonagrupo coreInsertar(BeanMaPersonagrupo bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanMaPersonagrupo coreActualizar(BeanMaPersonagrupo bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunMaPersonagrupo obtenerDtoCore(DtoComunMaPersonagrupo pk) throws Exception {
		DtoComunMaPersonagrupo dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunMaPersonagrupo();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	@SuppressWarnings("unchecked")
	public List<DtoComunMaPersonagrupo> listarDtoCore(DtoComunMaPersonagrupo filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO MaPersonagrupo.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, filtro.getPersonagrupo()));

		List<?> datos = this.listarPorQuery(DtoComunMaPersonagrupo.class, "mapersonagrupo.listarDtoCore", parametros);
		logger.debug(datos.size());
		return (List<DtoComunMaPersonagrupo>) datos;
	}

	public DtoComunMaPersonagrupo obtenerDto(DtoComunMaPersonagrupo pk) {
		DtoComunMaPersonagrupo dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, pk.getPersonagrupo()));

		List<?> lst = listarPorQuery(DtoComunMaPersonagrupo.class, "mapersonagrupo.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunMaPersonagrupo) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunMaPersonagrupo filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getPersonagrupo()))
			filtro.setPersonagrupo(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);
		// TODO MaPersonagrupo.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, filtro.getPersonagrupo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("mapersonagrupo.listarPaginadoContar", parametros);
		logger.debug(registros);
		List<?> lst = listarConPaginacion(filtro.getPaginacion(), parametros, "mapersonagrupo.listarPaginadoSentencia",DtlComunMaPersonagrupo.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion exportarTipoPersona( FiltroComunMaPersonagrupo filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.esNuloVacio(filtro.getPersonagrupo()))
			filtro.setPersonagrupo(null);
		// TODO MaPersonagrupo.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, filtro.getPersonagrupo()));
		List<?> lst = listarPorQuery(DtlComunMaPersonagrupo.class, "mapersonagrupo.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
