package net.royal.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.BeanTipocambiomastPk;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.core.dominio.filtro.FiltroComunTipocambiomast;
import net.royal.spring.core.dominio.lista.DtlComunTipocambiomast;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class TipocambiomastDaoImpl extends GenericoDaoImpl<BeanTipocambiomast, BeanTipocambiomastPk> {

	private static Logger logger = LogManager.getLogger(BeanTipocambiomast.class);

	public TipocambiomastDaoImpl() {
		super("tipocambiomast");  
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanTipocambiomast obtenerPorId(String pmonedacodigo,String pmonedacambiocodigo,java.util.Date pfechacambio) {
		return obtenerPorId(new BeanTipocambiomastPk( pmonedacodigo, pmonedacambiocodigo, pfechacambio));
	}

	public BeanTipocambiomast coreInsertar(BeanTipocambiomast bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanTipocambiomast coreActualizar(BeanTipocambiomast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunTipocambiomast obtenerDtoCore(DtoComunTipocambiomast pk) throws Exception {
		DtoComunTipocambiomast dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunTipocambiomast();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunTipocambiomast> listarDtoCore(DtoComunTipocambiomast filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO BeanTipocambiomast.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_monedacodigo", String.class, filtro.getMonedacodigo()));
		parametros.add(new DominioParametroPersistencia("p_monedacambiocodigo", String.class, filtro.getMonedacambiocodigo()));
		parametros.add(new DominioParametroPersistencia("p_fechacambio", String.class, filtro.getFechacambio()));
		parametros.add(new DominioParametroPersistencia("p_monedacodigo", String.class, filtro.getMonedacodigo()));
		parametros.add(new DominioParametroPersistencia("p_monedacambiocodigo", String.class, filtro.getMonedacambiocodigo()));
		parametros.add(new DominioParametroPersistencia("p_fechacambio", String.class, filtro.getFechacambio()));

		List datos = this.listarPorQuery(DtoComunTipocambiomast.class, "tipocambiomast.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunTipocambiomast obtenerDto(DtoComunTipocambiomast pk) {
		DtoComunTipocambiomast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_fechacambio", String.class, pk.getFechacambiostring()));

		List lst = listarPorQuery(DtoComunTipocambiomast.class, "tipocambiomast.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunTipocambiomast) lst.get(0);
		return dto;
	}
	
	public DtoComunTipocambiomast obtenerTipoCambio(String fecha) {
		DtoComunTipocambiomast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_fechacambio", String.class, fecha));

		List lst = listarPorQuery(DtoComunTipocambiomast.class, "tipocambiomast.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunTipocambiomast) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunTipocambiomast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO BeanTipocambiomast.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getFechacambiostring()))
			filtro.setFechacambiostring("20000101");
		
		parametros.add(new DominioParametroPersistencia("p_fechacambio", String.class, filtro.getFechacambiostring()));

		Integer registros = contar("tipocambiomast.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "tipocambiomast.listarPaginadoSentencia",DtlComunTipocambiomast.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public DominioPaginacion exportarTipoCambio(FiltroComunTipocambiomast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO BeanTipocambiomast.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getFechacambiostring()))
			filtro.setFechacambiostring("");
		
		parametros.add(new DominioParametroPersistencia("p_fechacambio", String.class, filtro.getFechacambiostring()));

		List lst = listarPorQuery(DtlComunTipocambiomast.class, "tipocambiomast.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
}
