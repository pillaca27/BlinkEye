package net.royal.spring.logistica.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.logistica.dominio.BeanWhClasefamilia;
import net.royal.spring.logistica.dominio.BeanWhClasefamiliaPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasefamilia;

@Repository
public class WhClasefamiliaDaoImpl extends GenericoDaoImpl<BeanWhClasefamilia, BeanWhClasefamiliaPk> {

	private static Logger logger = LogManager.getLogger(BeanWhClasefamilia.class);

	public WhClasefamiliaDaoImpl() {
		super("whclasefamilia");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanWhClasefamilia obtenerPorId(String plinea,String pfamilia) {
		return obtenerPorId(new BeanWhClasefamiliaPk( plinea, pfamilia));
	}

	public BeanWhClasefamilia coreInsertar(BeanWhClasefamilia bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanWhClasefamilia coreActualizar(BeanWhClasefamilia bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunWhClasefamilia obtenerDtoCore(DtoComunWhClasefamilia pk) throws Exception {
		DtoComunWhClasefamilia dto = obtenerDto(pk);
		if (dto==null) {
		    dto = new DtoComunWhClasefamilia();
		    dto.setTransaccionEstado(DominioTransaccion.NO_ENCONTRADO);
		    dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "no encontrado"));
		    return dto;
		}
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public List<DtoComunWhClasefamilia> listarDtoCore(DtoComunWhClasefamilia filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO WhClasefamilia.listarDtoCore : modificar query / modificar propiedades
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, filtro.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, filtro.getFamilia()));

		List datos = this.listarPorQuery(DtoComunWhClasefamilia.class, "whclasefamilia.listarDtoCore", parametros);
		logger.debug(datos.size());
		return datos;
	}

	public DtoComunWhClasefamilia obtenerDto(DtoComunWhClasefamilia pk) {
		DtoComunWhClasefamilia dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, pk.getFamilia()));
		parametros.add(new DominioParametroPersistencia("p_linea", String.class, pk.getLinea()));
		parametros.add(new DominioParametroPersistencia("p_familia", String.class, pk.getFamilia()));

		List lst = listarPorQuery(DtoComunWhClasefamilia.class, "whclasefamilia.obtenerDto", parametros);
		if (lst.size() == 1)
			dto = (DtoComunWhClasefamilia) lst.get(0);
		return dto;
	}


}
