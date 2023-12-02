package net.royal.spring.logistica.dao.impl;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.logistica.dominio.BeanWhItemmast;
import net.royal.spring.logistica.dominio.BeanWhItemmastPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhItemkit;
import net.royal.spring.logistica.dominio.dto.DtoComunWhItemmast;
import net.royal.spring.logistica.dominio.dto.DtoComunWhItemunidad;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhItemmast;
import net.royal.spring.logistica.dominio.lista.DtlComunWhItemmast;

@Repository
public class WhItemmastDaoImpl extends GenericoDaoImpl<BeanWhItemmast, BeanWhItemmastPk> {

	private static Logger logger = LogManager.getLogger(BeanWhItemmast.class);

	public WhItemmastDaoImpl() {
		super("whitemmast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanWhItemmast obtenerPorId(String pitem) {
		return obtenerPorId(new BeanWhItemmastPk( pitem));
	}

	public BeanWhItemmast coreInsertar(BeanWhItemmast bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanWhItemmast coreActualizar(BeanWhItemmast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunWhItemmast obtenerDtoCore(DtoComunWhItemmast pk) throws Exception {
		DtoComunWhItemmast dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunWhItemmast();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunWhItemmast> listarDtoCore(DtoComunWhItemmast filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WhItemmast.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_item", String.class, filtro.getItem()));
		parametros.add(new DominioParametroPersistencia("p_item", String.class, filtro.getItem()));

		List datos = this.listarPorQuery(DtoComunWhItemmast.class, "whitemmast.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunWhItemmast obtenerDto(DtoComunWhItemmast pk) {
		DtoComunWhItemmast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_item", String.class, pk.getItem()));

		List lst = listarPorQuery(DtoComunWhItemmast.class, "whitemmast.obtenerDto", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunWhItemmast) lst.get(0);
			
			List lstItemUnidad= listarPorQuery(DtoComunWhItemunidad.class, "whitemmast.obtenerDtoDetalleUnidad", parametros);
			if(lstItemUnidad.size()>0) {
				dto.setLstItemUnidad(lstItemUnidad);
			}
			
			List lstItemKit= listarPorQuery(DtoComunWhItemkit.class, "whitemmast.obtenerDtoDetalleKit", parametros);
			if(lstItemKit.size()>0) {
				dto.setLstItemKit(lstItemKit);
			}
			
		}
			
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunWhItemmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if(UString.esNuloVacio(filtro.getItem()))
			filtro.setItem("");
		if(UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion("");
		if(UString.esNuloVacio(filtro.getNumerodeparte()))
			filtro.setNumerodeparte("");
		if(UString.esNuloVacio(filtro.getCodigointerno()))
			filtro.setCodigointerno(null);
		if(UString.esNuloVacio(filtro.getLinea()))
			filtro.setLinea(null);
		if(UString.esNuloVacio(filtro.getFamilia()))
			filtro.setFamilia(null);
		if(UString.esNuloVacio(filtro.getSubfamilia()))
			filtro.setSubfamilia(null);
		
		// TODO WhItemmast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_item", String.class, filtro.getItem()));
		parametros.add(new DominioParametroPersistencia("p_busqueda", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_numero", String.class, filtro.getNumerodeparte()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCodigointerno()));
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));
		parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, filtro.getSubfamilia()));

		Integer registros = contar("whitemmast.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "whitemmast.listarPaginadoSentencia",DtlComunWhItemmast.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion exportarItems( FiltroComunWhItemmast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if(UString.esNuloVacio(filtro.getItem()))
			filtro.setItem("");
		if(UString.esNuloVacio(filtro.getDescripcion()))
			filtro.setDescripcion("");
		if(UString.esNuloVacio(filtro.getNumerodeparte()))
			filtro.setNumerodeparte("");
		if(UString.esNuloVacio(filtro.getCodigointerno()))
			filtro.setCodigointerno(null);
		if(UString.esNuloVacio(filtro.getLinea()))
			filtro.setLinea(null);
		if(UString.esNuloVacio(filtro.getFamilia()))
			filtro.setFamilia(null);
		if(UString.esNuloVacio(filtro.getSubfamilia()))
			filtro.setSubfamilia(null);
		
		// TODO WhItemmast.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_item", String.class, filtro.getItem()));
		parametros.add(new DominioParametroPersistencia("p_busqueda", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_numero", String.class, filtro.getNumerodeparte()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCodigointerno()));
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));
		parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, filtro.getSubfamilia()));

		List lst = listarPorQuery(DtlComunWhItemmast.class, "whitemmast.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public List<DtoTabla> validarCuentas(String cuenta) {
		logger.debug("MaPersonagrupoRest.validarCuentas");
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO Banco.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_cuenta", String.class,cuenta));
		// TODO MaPersonagrupoRest.listaractivos : modificar query
		List datos = this.listarPorQuery(DtoTabla.class, "whitemmast.validarCuentasI",parametros);
		
		return datos;
	}

}
