package net.royal.spring.contabilidad.dao.impl;

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
import net.royal.spring.contabilidad.dominio.BeanAcCostcentermst;
import net.royal.spring.contabilidad.dominio.BeanAcCostcentermstPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenteraccount;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterafe;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcentergroup;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterdestvalid;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcentermst;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcentervendor;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcCostcentergroup;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcCostcentermst;
import net.royal.spring.contabilidad.dominio.lista.DtlComunAcCostcentermst;

@Repository
public class AcCostcentermstDaoImpl extends GenericoDaoImpl<BeanAcCostcentermst, BeanAcCostcentermstPk> {

	private static Logger logger = LogManager.getLogger(BeanAcCostcentermst.class);

	public AcCostcentermstDaoImpl() {
		super("accostcentermst");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DtoComunAcCostcentermst obtenerDtoPorUuid(String uuid) {
		DtoComunAcCostcentermst dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunAcCostcentermst.class, "accostcentermst.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunAcCostcentermst) lst.get(0); 
		}
		return dto;
	}
	
	public BeanAcCostcentermst obtenerPorId(String pcostcenter) {
		return obtenerPorId(new BeanAcCostcentermstPk( pcostcenter));
	}

	public BeanAcCostcentermst coreInsertar(BeanAcCostcentermst bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanAcCostcentermst coreActualizar(BeanAcCostcentermst bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunAcCostcentermst obtenerDtoCore(DtoComunAcCostcentermst pk) throws Exception {
		DtoComunAcCostcentermst dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunAcCostcentermst();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunAcCostcentermst> listarDtoCore(DtoComunAcCostcentermst filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO AcCostcentermst.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));

		List datos = this.listarPorQuery(DtoComunAcCostcentermst.class, "accostcentermst.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunAcCostcentermst obtenerDto(DtoComunAcCostcentermst pk) {
		DtoComunAcCostcentermst dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, pk.getCostcenter()));


		List lst = listarPorQuery(DtoComunAcCostcentermst.class, "accostcentermst.obtenerDto", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunAcCostcentermst) lst.get(0);
			
			List lstDetalle1 = listarPorQuery(DtoComunAcCostcenteraccount.class, "accostcentermst.obtenerDtoDetalle1", parametros);
			if(lstDetalle1.size()>0) {
				dto.setLstDetalle1(lstDetalle1);
			}
			
			List lstDetalle2 = listarPorQuery(DtoComunAcCostcentervendor.class, "accostcentermst.obtenerDtoDetalle2", parametros);
			if(lstDetalle2.size()>0) {
				dto.setLstDetalle2(lstDetalle2);
			}
			
			List lstDetalle3 = listarPorQuery(DtoComunAcCostcenterdestvalid.class, "accostcentermst.obtenerDtoDetalle3", parametros);
			if(lstDetalle3.size()>0) {
				dto.setLstDetalle3(lstDetalle3);
			}
			
			List lstDetalle4 = listarPorQuery(DtoComunAcCostcenterafe.class, "accostcentermst.obtenerDtoDetalle4", parametros);
			if(lstDetalle4.size()>0) {
				dto.setLstDetalle4(lstDetalle4);
			}
		}
			
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunAcCostcentermst filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if(UString.esNuloVacio(filtro.getCostcenter()))
			filtro.setCostcenter("");
		// TODO AcCostcentermst.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));


		Integer registros = contar("accostcentermst.listarPaginadoContar", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.listarPaginadoSentencia",DtlComunAcCostcentermst.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
	
	public DominioPaginacion listarpaginadoGrupo(SeguridadUsuarioActual usuarioActual, FiltroComunAcCostcentergroup filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if(UString.esNuloVacio(filtro.getCostcentergroup()))
			filtro.setCostcentergroup("");
		// TODO AcCostcentermst.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_grupo", String.class, filtro.getCostcentergroup()));


		Integer registros = contar("accostcentermst.listarPaginadoContarGrupo", parametros);
		logger.debug(registros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "accostcentermst.listarPaginadoSentenciaGrupo",DtoComunAcCostcentergroup.class);
		logger.debug(lst.size());
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public DominioPaginacion exportarCentroCostos( FiltroComunAcCostcentermst filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if(UString.esNuloVacio(filtro.getCostcenter()))
			filtro.setCostcenter("");
		// TODO AcCostcentermst.listarPaginado : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_costcenter", String.class, filtro.getCostcenter()));
		List lst = this.listarPorQuery(DtlComunAcCostcentermst.class, "accostcentermst.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
}
