package net.royal.spring.contabilidad.dao.impl;

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
import net.royal.spring.contabilidad.dominio.BeanAcSucursalgrupomayor;
import net.royal.spring.contabilidad.dominio.BeanAcSucursalgrupomayorPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcSucursalgrupomayor;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcSucursalgrupomayor;

@Repository
public class AcSucursalgrupomayorDaoImpl extends GenericoDaoImpl<BeanAcSucursalgrupomayor, BeanAcSucursalgrupomayorPk> {

	private static Logger logger = LogManager.getLogger(BeanAcSucursalgrupomayor.class);

	public AcSucursalgrupomayorDaoImpl() {
		super("acsucursalgrupomayor");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanAcSucursalgrupomayor obtenerPorId(String psucursalgrupomayor) {
		return obtenerPorId(new BeanAcSucursalgrupomayorPk( psucursalgrupomayor));
	}

	public BeanAcSucursalgrupomayor coreInsertar(BeanAcSucursalgrupomayor bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanAcSucursalgrupomayor coreActualizar(BeanAcSucursalgrupomayor bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunAcSucursalgrupomayor obtenerDtoCore(DtoComunAcSucursalgrupomayor pk) throws Exception {
		DtoComunAcSucursalgrupomayor dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunAcSucursalgrupomayor();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoComunAcSucursalgrupomayor obtenerDtoPorUuid(String uuid) {
		DtoComunAcSucursalgrupomayor dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunAcSucursalgrupomayor.class, "acsucursalgrupomayor.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1)
			dto = (DtoComunAcSucursalgrupomayor) lst.get(0);
		return dto;
	}
	
	public DtoComunAcSucursalgrupomayor obtenerDto(DtoComunAcSucursalgrupomayor pk) {
		DtoComunAcSucursalgrupomayor dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_sucursalgrupomayor", String.class, pk.getSucursalgrupomayor()));

		List lst = listarPorQuery(DtoComunAcSucursalgrupomayor.class, "acsucursalgrupomayor.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunAcSucursalgrupomayor) lst.get(0);
		return dto;
	}

	public List<DtoComunAcSucursalgrupomayor> listarDtoCore(DtoComunAcSucursalgrupomayor filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcSucursalgrupomayor.listarDtoCore : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getSucursalgrupomayor()))
			filtro.setSucursalgrupomayor(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_sucursalgrupomayor", String.class, filtro.getSucursalgrupomayor()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		List datos = this.listarPorQuery(DtoComunAcSucursalgrupomayor.class, "acsucursalgrupomayor.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunAcSucursalgrupomayor filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcSucursalgrupomayor.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getSucursalgrupomayor()))
			filtro.setSucursalgrupomayor(null);
		if (UString.esNuloVacio(filtro.getDescripcionlocal()))
			filtro.setDescripcionlocal(null);
		if (UString.esNuloVacio(filtro.getEstado()))
			filtro.setEstado(null);

		parametros.add(new DominioParametroPersistencia("p_sucursalgrupomayor", String.class, filtro.getSucursalgrupomayor()));
		parametros.add(new DominioParametroPersistencia("p_descripcionlocal", String.class, filtro.getDescripcionlocal()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("acsucursalgrupomayor.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "acsucursalgrupomayor.listarPaginadoSentencia",DtoComunAcSucursalgrupomayor.class);
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
		List datos = this.listarPorQuery(DtoTabla.class, "acsucursalgrupomayor.listaCortaPorNombre", parametros);
		logger.debug(datos.size());
		return datos;
	}

}
