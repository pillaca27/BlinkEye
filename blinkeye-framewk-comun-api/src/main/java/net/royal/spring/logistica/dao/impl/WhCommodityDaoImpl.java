package net.royal.spring.logistica.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import net.royal.spring.core.dominio.filtro.FiltroComunCommoditySub;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.logistica.dominio.BeanWhCommodity;
import net.royal.spring.logistica.dominio.BeanWhCommodityPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommoditysub;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommodity;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhCommodity;



@Repository
public class WhCommodityDaoImpl extends GenericoDaoImpl<BeanWhCommodity, BeanWhCommodityPk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(BeanWhCommodity.class);

	public WhCommodityDaoImpl() {
		super("whcommodity");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanWhCommodity obtenerPorId(String pcommodity01) {
		return obtenerPorId(new BeanWhCommodityPk( pcommodity01));
	}

	public BeanWhCommodity coreInsertar(BeanWhCommodity bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanWhCommodity coreActualizar(BeanWhCommodity bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunWhCommodity obtenerDtoCore(DtoComunWhCommodity pk) throws Exception {
		DtoComunWhCommodity dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunWhCommodity();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}


	public DtoComunWhCommodity obtenerDto(DtoComunWhCommodity pk) {
		DtoComunWhCommodity dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_commodity01", String.class, pk.getCommodity01()));

		List lst = listarPorQuery(DtoComunWhCommodity.class, "whcommodity.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunWhCommodity) lst.get(0);
		
		List<DominioParametroPersistencia> parametrosDetalle = new ArrayList<DominioParametroPersistencia>();
		parametrosDetalle.add(new DominioParametroPersistencia("p_commodity01", String.class, pk.getCommodity01()));
		List lstDetalle = listarPorQuery(DtoComunWhCommoditysub.class, "whcommodity.obtenerDetalleDto", parametrosDetalle);
		
		if(lstDetalle.size()> 0) {
			dto.setLstCommodityDetalle(lstDetalle);
		}

			
		return dto;
	}

	public DominioPaginacion exportarCommodities (FiltroComunCommoditySub filtro) {

		if (UString.esNuloVacio(filtro.getClasificacion()))
			filtro.setClasificacion(null);
		if (UString.esNuloVacio(filtro.getDescripcion1()))
			filtro.setDescripcion1("");
	
		List<DominioParametroPersistencia> parametros=new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getClasificacion()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion1()));
		
        List lista = listarPorQuery(DtoComunWhCommoditysub.class, "whcommodity.listarCommodity", parametros);
        filtro.getPaginacion().setPaginacionListaResultado(lista);
        return filtro.getPaginacion();
	}
	
	
	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunWhCommodity filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WhCommodity.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getCommodity01()))
			filtro.setCommodity01(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_commodity01", String.class, filtro.getCommodity01()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("whcommodity.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "whcommodity.listarPaginadoSentencia",DtoComunWhCommodity.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

}
