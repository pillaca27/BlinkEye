package net.royal.spring.core.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import net.royal.spring.framework.modelo.CorrelativoTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.core.dominio.BeanCorrelativosmast;
import net.royal.spring.core.dominio.BeanCorrelativosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunCorrelativosmast;
import net.royal.spring.core.dominio.filtro.FiltroComunCorrelativosmast;

@Repository
public class CorrelativosmastDaoImpl extends GenericoDaoImpl<BeanCorrelativosmast, BeanCorrelativosmastPk> {

	private static Logger logger = LogManager.getLogger(BeanCorrelativosmast.class);

	public CorrelativosmastDaoImpl() {
		super("correlativosmast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanCorrelativosmast obtenerPorId(String pcompaniacodigo,String ptipocomprobante,String pserie) {
		return obtenerPorId(new BeanCorrelativosmastPk( pcompaniacodigo, ptipocomprobante, pserie));
	}

	public BeanCorrelativosmast coreInsertar(BeanCorrelativosmast bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanCorrelativosmast coreActualizar(BeanCorrelativosmast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunCorrelativosmast obtenerDtoPorId(DtoComunCorrelativosmast pk) {
		DtoComunCorrelativosmast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, pk.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_tipocomprobante", String.class, pk.getTipocomprobante()));
		parametros.add(new DominioParametroPersistencia("p_serie", String.class, pk.getSerie()));
		List lst = listarPorQuery(DtoComunCorrelativosmast.class, "correlativosmast.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunCorrelativosmast) lst.get(0);
		return dto;
	}
	
	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunCorrelativosmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Correlativosmast.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getCompaniacodigo()))
			filtro.setCompaniacodigo(null);
		if (UString.esNuloVacio(filtro.getTipocomprobante()))
			filtro.setTipocomprobante(null);
		if (UString.esNuloVacio(filtro.getSerie()))
			filtro.setSerie(null);
		
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_tipocomprobante", String.class, filtro.getTipocomprobante()));
		parametros.add(new DominioParametroPersistencia("p_serie", String.class, filtro.getSerie()));

		Integer registros = contar("correlativosmast.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "correlativosmast.listarPaginadoSentencia",DtoComunCorrelativosmast.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion exportarParametros(FiltroComunCorrelativosmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Correlativosmast.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getCompaniacodigo()))
			filtro.setCompaniacodigo(null);
		if (UString.esNuloVacio(filtro.getTipocomprobante()))
			filtro.setTipocomprobante(null);
		if (UString.esNuloVacio(filtro.getSerie()))
			filtro.setSerie(null);
		
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_tipocomprobante", String.class, filtro.getTipocomprobante()));
		parametros.add(new DominioParametroPersistencia("p_serie", String.class, filtro.getSerie()));
		List lst = listarPorQuery(DtoComunCorrelativosmast.class, "correlativosmast.listarPaginadoSentencia", parametros);

		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DtoComunCorrelativosmast obtenerDtoCore(DtoComunCorrelativosmast pk) throws Exception {
		
		DtoComunCorrelativosmast dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunCorrelativosmast();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}
	

	public DtoComunCorrelativosmast obtenerDto(DtoComunCorrelativosmast pk) {
		DtoComunCorrelativosmast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, pk.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_tipocomprobante", String.class, pk.getTipocomprobante()));
		parametros.add(new DominioParametroPersistencia("p_serie", String.class, pk.getSerie()));

		List lst = listarPorQuery(DtoComunCorrelativosmast.class, "correlativosmast.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunCorrelativosmast) lst.get(0);
		return dto;
	}

	/*public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, DtoComunCorrelativosmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, filtro.getCompaniacodigo()));
		parametros.add(new DominioParametroPersistencia("p_tipocomprobante", String.class, filtro.getTipocomprobante()));
		parametros.add(new DominioParametroPersistencia("p_serie", String.class, filtro.getSerie()));
		Integer registros = contar("correlativosmast.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "correlativosmast.listarPaginadoSentencia",DtoComunCorrelativosmast.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}*/
	public DtoComunCorrelativosmast obtenerDto(String companiacodigo,String tipocomprobante,String serie) {
		DtoComunCorrelativosmast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_companiacodigo", String.class, companiacodigo));
		parametros.add(new DominioParametroPersistencia("p_tipocomprobante", String.class, tipocomprobante));
		parametros.add(new DominioParametroPersistencia("p_serie", String.class, serie));
		List lst = listarPorQuery(DtoComunCorrelativosmast.class, "correlativosmast.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunCorrelativosmast) lst.get(0);
		return dto;
	}
	public CorrelativoTransaccion obtenerCorrelativo(SeguridadUsuarioActual usu,CorrelativoTransaccion pk) {
		BeanCorrelativosmast entity = this.obtenerPorId(pk.getCompaniacodigo(),pk.getTipocomprobante(),pk.getSerie());
		BigDecimal correlativoNuevo;
		if (entity==null) {
			// registrar
			correlativoNuevo = new BigDecimal(1);
			entity=new BeanCorrelativosmast();
			
			entity.getPk().setCompaniacodigo(pk.getCompaniacodigo());
			entity.getPk().setTipocomprobante(pk.getTipocomprobante());
			entity.getPk().setSerie(pk.getSerie());
			entity.setCorrelativodesde(new BigDecimal(0));
			entity.setCorrelativohasta(new BigDecimal(0));
			entity.setCorrelativonumero(correlativoNuevo);
			entity.setEstado("A");
			entity.setUltimafechamodif(new Date());
			entity.setUltimousuario(usu.getUsuario());
			this.registrar(entity);
		}else {
			// actualizar
			correlativoNuevo = UBigDecimal.sumar(entity.getCorrelativonumero(), new BigDecimal(1));
			entity.setCorrelativonumero(correlativoNuevo);
			entity.setUltimafechamodif(new Date());
			entity.setUltimousuario(usu.getUsuario());
			this.actualizar(entity);
		}		
		if (UInteger.esCeroOrNulo(pk.getCerosIzquierda()))
			pk.setCerosIzquierda(10);		
		pk.setNumeroGenerado(correlativoNuevo);		
		String nro = "000000000000000000000000"+correlativoNuevo.toString();
		nro = nro.substring(nro.length() - pk.getCerosIzquierda(),
				nro.length());
		pk.setNumeroGeneradoCadena(nro);
		return pk;
	}
	public CorrelativoTransaccion obtenerCorrelativoAnio(SeguridadUsuarioActual usu,CorrelativoTransaccion pk) {
		BeanCorrelativosmast entity = this.obtenerPorId(pk.getCompaniacodigo(),pk.getTipocomprobante(),pk.getSerie());
		BigDecimal correlativoNuevo;
		if (entity==null) {
			// en base al a�o sacar el nuevo numero
			String sss = pk.getAnio().toString() + "000001";
			correlativoNuevo = new BigDecimal(sss);
			
			entity=new BeanCorrelativosmast();
			
			entity.getPk().setCompaniacodigo(pk.getCompaniacodigo());
			entity.getPk().setTipocomprobante(pk.getTipocomprobante());
			entity.getPk().setSerie(pk.getSerie());
			entity.setCorrelativodesde(new BigDecimal(0));
			entity.setCorrelativohasta(new BigDecimal(0));
			entity.setCorrelativonumero(correlativoNuevo);
			entity.setEstado("A");
			entity.setUltimafechamodif(new Date());
			entity.setUltimousuario(usu.getUsuario());
			this.registrar(entity);
		}else {
			// actualizar
			String anioactual = entity.getCorrelativonumero().toString().substring(0,4);
			if (anioactual.equals(pk.getAnio())) {
				correlativoNuevo = UBigDecimal.sumar(entity.getCorrelativonumero(), new BigDecimal(1));	
			}else {
				String sss = pk.getAnio().toString() + "000001";
				correlativoNuevo = new BigDecimal(sss);
			}
			entity.setCorrelativonumero(correlativoNuevo);
			entity.setUltimafechamodif(new Date());
			entity.setUltimousuario(usu.getUsuario());
			this.actualizar(entity);
		}		
		if (UInteger.esCeroOrNulo(pk.getCerosIzquierda()))
			pk.setCerosIzquierda(10);		
		pk.setNumeroGenerado(correlativoNuevo);		
		String nro = "000000000000000000000000"+correlativoNuevo.toString();
		nro = nro.substring(nro.length() - pk.getCerosIzquierda(),
				nro.length());
		pk.setNumeroGeneradoCadena(nro);
		return pk;
	}
	
	
	
	
	
	
	
}
