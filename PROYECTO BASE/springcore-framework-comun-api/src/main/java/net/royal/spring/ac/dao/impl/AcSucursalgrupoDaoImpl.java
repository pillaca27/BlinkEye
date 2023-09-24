package net.royal.spring.ac.dao.impl;

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
import net.royal.spring.ac.dominio.BeanAcSucursalgrupo;
import net.royal.spring.ac.dominio.BeanAcSucursalgrupoPk;
import net.royal.spring.ac.dominio.dto.DtoComunAcSucursalgrupo;
import net.royal.spring.ac.dominio.filtro.FiltroComunAcSucursalgrupo;

@Repository
public class AcSucursalgrupoDaoImpl extends GenericoDaoImpl<BeanAcSucursalgrupo, BeanAcSucursalgrupoPk> {

	private static Logger logger = LogManager.getLogger(BeanAcSucursalgrupo.class);

	public AcSucursalgrupoDaoImpl() {
		super("acsucursalgrupo");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanAcSucursalgrupo obtenerPorId(String psucursalgrupo) {
		return obtenerPorId(new BeanAcSucursalgrupoPk( psucursalgrupo));
	}

	public BeanAcSucursalgrupo coreInsertar(BeanAcSucursalgrupo bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanAcSucursalgrupo coreActualizar(BeanAcSucursalgrupo bean) {
		this.actualizar(bean);
		return bean;
	}
	
	public DtoComunAcSucursalgrupo obtenerDtoPorUuid(String uuid) {
		DtoComunAcSucursalgrupo dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunAcSucursalgrupo.class, "acsucursalgrupo.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunAcSucursalgrupo) lst.get(0); 
		}
		return dto;
	}

	public DtoComunAcSucursalgrupo obtenerDtoCore(DtoComunAcSucursalgrupo pk) throws Exception {
		DtoComunAcSucursalgrupo dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunAcSucursalgrupo();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoComunAcSucursalgrupo obtenerDto(DtoComunAcSucursalgrupo pk) {
		DtoComunAcSucursalgrupo dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_sucursalgrupo", String.class, pk.getSucursalgrupo()));

		List lst = listarPorQuery(DtoComunAcSucursalgrupo.class, "acsucursalgrupo.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunAcSucursalgrupo) lst.get(0);
		return dto;
	}

	public List<DtoComunAcSucursalgrupo> listarDtoCore(DtoComunAcSucursalgrupo filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcSucursalgrupo.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getSucursalgrupo()))
			filtro.setSucursalgrupo(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_sucursalgrupo", String.class, filtro.getSucursalgrupo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoComunAcSucursalgrupo.class, "acsucursalgrupo.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunAcSucursalgrupo filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcSucursalgrupo.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getSucursalgrupo()))
			filtro.setSucursalgrupo(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_sucursalgrupo", String.class, filtro.getSucursalgrupo()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_sucursalgrupomayor", String.class, filtro.getSucursalgrupomayor()));

		Integer registros = contar("acsucursalgrupo.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "acsucursalgrupo.listarPaginadoSentencia",DtoComunAcSucursalgrupo.class);
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
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));
		parametros.add(new DominioParametroPersistencia("p_estadoid", String.class, filtro.getEstadoId()));
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursalgrupo.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

}
