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
import net.royal.spring.logistica.dominio.BeanWhClasesubfamilia;
import net.royal.spring.logistica.dominio.BeanWhClasesubfamiliaPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasesubfamilia;
import net.royal.spring.logistica.dominio.filtro.FiltroComunWhClasesubfamilia;


@Repository
public class WhClasesubfamiliaDaoImpl extends GenericoDaoImpl<BeanWhClasesubfamilia, BeanWhClasesubfamiliaPk> {

	private static Logger logger = LogManager.getLogger(BeanWhClasesubfamilia.class);

	public WhClasesubfamiliaDaoImpl() {
		super("whclasesubfamilia");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanWhClasesubfamilia obtenerPorId(String plinea,String pfamilia,String psubfamilia) {
		return obtenerPorId(new BeanWhClasesubfamiliaPk( plinea, pfamilia, psubfamilia));
	}

	public BeanWhClasesubfamilia coreInsertar(BeanWhClasesubfamilia bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanWhClasesubfamilia coreActualizar(BeanWhClasesubfamilia bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunWhClasesubfamilia obtenerDtoCore(DtoComunWhClasesubfamilia pk) throws Exception {
		DtoComunWhClasesubfamilia dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunWhClasesubfamilia();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunWhClasesubfamilia> listarDtoCore(DtoComunWhClasesubfamilia filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WhClasesubfamilia.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));
		parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, filtro.getSubfamilia()));
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));
		parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, filtro.getSubfamilia()));

		List datos = this.listarPorQuery(DtoComunWhClasesubfamilia.class, "whclasesubfamilia.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunWhClasesubfamilia obtenerDto(DtoComunWhClasesubfamilia pk) {
		DtoComunWhClasesubfamilia dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, pk.getFamilia()));
		parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, pk.getSubfamilia()));
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, pk.getFamilia()));
		parametros.add(new DominioParametroPersistencia("p_subfamilia", String.class, pk.getSubfamilia()));

		List lst = listarPorQuery(DtoComunWhClasesubfamilia.class, "whclasesubfamilia.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunWhClasesubfamilia) lst.get(0);
		return dto;
	}


}
