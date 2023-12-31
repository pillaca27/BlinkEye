package net.royal.spring.sistema.dao.impl;

import java.math.BigDecimal;
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
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyPreferences;
import net.royal.spring.sistema.dominio.BeanSyPreferencesPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyPreferences;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyPreferences;

@Repository
public class SyPreferencesDaoImpl extends GenericoDaoImpl<BeanSyPreferences, BeanSyPreferencesPk> {

	private static Logger logger = LogManager.getLogger(BeanSyPreferences.class);

	public SyPreferencesDaoImpl() {
		super("sypreferences");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanSyPreferences obtenerPorId(String pusuario,String ppreference) {
		return obtenerPorId(new BeanSyPreferencesPk( pusuario, ppreference));
	}

	public BeanSyPreferences coreInsertar(BeanSyPreferences bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanSyPreferences coreActualizar(BeanSyPreferences bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunSyPreferences obtenerDtoCore(DtoComunSyPreferences pk) throws Exception {
		DtoComunSyPreferences dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunSyPreferences();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunSyPreferences> listarDtoCore(DtoComunSyPreferences filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyPreferences.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getUsuario()))
			filtro.setUsuario(null);
		if (UString.esNuloVacio(filtro.getPreference()))
			filtro.setPreference(null);

		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_preference", String.class, filtro.getPreference()));

		List datos = this.listarPorQuery(DtoComunSyPreferences.class, "sypreferences.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunSyPreferences obtenerDto(DtoComunSyPreferences pk) {
		DtoComunSyPreferences dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, pk.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_preference", String.class, pk.getPreference()));

		List lst = listarPorQuery(DtoComunSyPreferences.class, "sypreferences.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunSyPreferences) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunSyPreferences filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO SyPreferences.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getUsuario()))
			filtro.setUsuario(null);
		if (UString.esNuloVacio(filtro.getPreference()))
			filtro.setPreference(null);

		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, filtro.getUsuario()));
		parametros.add(new DominioParametroPersistencia("p_preference", String.class, filtro.getPreference()));

		Integer registros = contar("sypreferences.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "sypreferences.listarPaginadoSentencia",DtoComunSyPreferences.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public List<DtoTabla> listaCortaPorNombre(DtoTabla filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(filtro.getNombre()))
			filtro.setNombre(null);
		if (UString.esNuloVacio(filtro.getEstadoId()))
			filtro.setEstadoId(null);
		//
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estadoid", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "sypreferences.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}
	
	public DtoComunSyPreferences registrarPreferencias(SeguridadUsuarioActual usuarioActual, DtoComunSyPreferences dto) throws Exception{
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
	
				parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, dto.getUsuario()));
				parametros.add(new DominioParametroPersistencia("p_preference", String.class, dto.getPreference()));
				parametros.add(new DominioParametroPersistencia("p_aplicacioncodigo", String.class, dto.getAplicacioncodigo()));
				parametros.add(new DominioParametroPersistencia("p_tipovalor", String.class, dto.getTipovalor()));
				parametros.add(new DominioParametroPersistencia("p_valorstring", String.class, dto.getValorstring()));
				
				this.ejecutarPorQuery("sypreferences.listaPreferencias", parametros);
				return dto;
		
	}
}
