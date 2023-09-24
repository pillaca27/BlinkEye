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
import net.royal.spring.core.dominio.BeanMaPersonapersonagrupo;
import net.royal.spring.core.dominio.BeanMaPersonapersonagrupoPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonapersonagrupo;
import net.royal.spring.core.dominio.filtro.FiltroComunMaPersonapersonagrupo;
import net.royal.spring.core.dominio.lista.DtlComunMaPersonapersonagrupo;

@Repository
public class MaPersonapersonagrupoDaoImpl extends GenericoDaoImpl<BeanMaPersonapersonagrupo, BeanMaPersonapersonagrupoPk> {

	private static Logger logger = LogManager.getLogger(BeanMaPersonapersonagrupo.class);

	public MaPersonapersonagrupoDaoImpl() {
		super("mapersonapersonagrupo");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanMaPersonapersonagrupo obtenerPorId(Integer ppersona,String ppersonagrupo) {
		return obtenerPorId(new BeanMaPersonapersonagrupoPk( ppersona, ppersonagrupo));
	}

	public BeanMaPersonapersonagrupo coreInsertar(BeanMaPersonapersonagrupo bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanMaPersonapersonagrupo coreActualizar(BeanMaPersonapersonagrupo bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunMaPersonapersonagrupo obtenerDtoCore(DtoComunMaPersonapersonagrupo pk) throws Exception {
		DtoComunMaPersonapersonagrupo dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunMaPersonapersonagrupo();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunMaPersonapersonagrupo> listarDtoCore(DtoComunMaPersonapersonagrupo filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO MaPersonapersonagrupo.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, filtro.getPersonagrupo()));
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, filtro.getPersonagrupo()));

		List datos = this.listarPorQuery(DtoComunMaPersonapersonagrupo.class, "mapersonapersonagrupo.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunMaPersonapersonagrupo obtenerDto(DtoComunMaPersonapersonagrupo pk) {
		DtoComunMaPersonapersonagrupo dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_persona", Integer.class, pk.getPersona()));


		List lst = listarPorQuery(DtoComunMaPersonapersonagrupo.class, "mapersonapersonagrupo.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunMaPersonapersonagrupo) lst.get(0);
		return dto;
	}
	
	public  List<DtoComunMaPersonapersonagrupo> obtenerLstDto(DtoComunMaPersonapersonagrupo pk) {
		List<DtoComunMaPersonapersonagrupo> dto = new ArrayList<DtoComunMaPersonapersonagrupo>();
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_persona", Integer.class, pk.getPersona()));


		List lst = listarPorQuery(DtoComunMaPersonapersonagrupo.class, "mapersonapersonagrupo.listarPaginadoSentencia", parametros);
		if (lst.size() > 0)
			dto = (List<DtoComunMaPersonapersonagrupo>) lst;
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunMaPersonapersonagrupo filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO MaPersonapersonagrupo.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, filtro.getPersonagrupo()));
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_personagrupo", String.class, filtro.getPersonagrupo()));

		Integer registros = contar("mapersonapersonagrupo.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "mapersonapersonagrupo.listarPaginadoSentencia",DtlComunMaPersonapersonagrupo.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
