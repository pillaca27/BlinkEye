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
import net.royal.spring.core.dominio.BeanMaPersonacuentabancaria;
import net.royal.spring.core.dominio.BeanMaPersonacuentabancariaPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonacuentabancaria;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonapersonagrupo;
import net.royal.spring.core.dominio.filtro.FiltroComunMaPersonacuentabancaria;
import net.royal.spring.core.dominio.lista.DtlComunMaPersonacuentabancaria;

@Repository
public class MaPersonacuentabancariaDaoImpl extends GenericoDaoImpl<BeanMaPersonacuentabancaria, BeanMaPersonacuentabancariaPk> {

	private static Logger logger = LogManager.getLogger(BeanMaPersonacuentabancaria.class);

	public MaPersonacuentabancariaDaoImpl() {
		super("mapersonacuentabancaria");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanMaPersonacuentabancaria obtenerPorId(Integer ppersona,Integer psecuencia) {
		return obtenerPorId(new BeanMaPersonacuentabancariaPk( ppersona, psecuencia));
	}

	public BeanMaPersonacuentabancaria coreInsertar(BeanMaPersonacuentabancaria bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanMaPersonacuentabancaria coreActualizar(BeanMaPersonacuentabancaria bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunMaPersonacuentabancaria obtenerDtoCore(DtoComunMaPersonacuentabancaria pk) throws Exception {
		DtoComunMaPersonacuentabancaria dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunMaPersonacuentabancaria();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunMaPersonacuentabancaria> listarDtoCore(DtoComunMaPersonacuentabancaria filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO MaPersonacuentabancaria.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", String.class, filtro.getSecuencia()));
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", String.class, filtro.getSecuencia()));

		List datos = this.listarPorQuery(DtoComunMaPersonacuentabancaria.class, "mapersonacuentabancaria.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}
	
	public  List<DtoComunMaPersonacuentabancaria> obtenerLstDto(DtoComunMaPersonacuentabancaria pk) {
		List<DtoComunMaPersonacuentabancaria> dto = new ArrayList<DtoComunMaPersonacuentabancaria>();
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_persona", Integer.class, pk.getPersona()));
		List lst = listarPorQuery(DtoComunMaPersonacuentabancaria.class, "mapersonacuentabancaria.listarPaginadoSentencia", parametros);
		if (lst.size() > 0)
			dto = (List<DtoComunMaPersonacuentabancaria>) lst;
		return dto;
	}

	public DtoComunMaPersonacuentabancaria obtenerDto(DtoComunMaPersonacuentabancaria pk) {
		DtoComunMaPersonacuentabancaria dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, pk.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", String.class, pk.getSecuencia()));
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, pk.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", String.class, pk.getSecuencia()));

		List lst = listarPorQuery(DtoComunMaPersonacuentabancaria.class, "mapersonacuentabancaria.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunMaPersonacuentabancaria) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunMaPersonacuentabancaria filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO MaPersonacuentabancaria.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", String.class, filtro.getSecuencia()));
		parametros.add(new DominioParametroPersistencia("p_persona", String.class, filtro.getPersona()));
		parametros.add(new DominioParametroPersistencia("p_secuencia", String.class, filtro.getSecuencia()));

		Integer registros = contar("mapersonacuentabancaria.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "mapersonacuentabancaria.listarPaginadoSentencia",DtlComunMaPersonacuentabancaria.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
