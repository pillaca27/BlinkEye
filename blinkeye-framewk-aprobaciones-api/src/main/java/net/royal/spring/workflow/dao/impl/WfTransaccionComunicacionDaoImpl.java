package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfTransaccionComunicacion;
import net.royal.spring.workflow.dominio.WfTransaccionComunicacionPk;
import net.royal.spring.workflow.dominio.dto.DtoWfTipoDocumento;

@Repository
public class WfTransaccionComunicacionDaoImpl
		extends GenericoDaoImpl<WfTransaccionComunicacion, WfTransaccionComunicacionPk> {

	private static final long serialVersionUID = 1L;

	public WfTransaccionComunicacionDaoImpl() {
		super("wftransaccioncomunicacion");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public Integer generarId(Integer transaccionid) {
		Criteria criteria = getCriteria().add(Restrictions.eq("pk.transaccionId", transaccionid))
				.setProjection(Projections.max("pk.comunicacionId"));
		Integer calc = (Integer) criteria.uniqueResult();

		if (calc == null) {
			return 1;
		}
		return calc + 1;
	}

	/**
	 * Aca registra el usuario actual (el proveedor desde el portal de proveedores)
	 * 
	 * @param bean
	 * @param usuarioActual
	 * @return
	 */
	public WfTransaccionComunicacion enviarMensaje(WfTransaccionComunicacion bean,
			SeguridadUsuarioActual usuarioActual) {
		bean.getPk().setComunicacionId(generarId(bean.getPk().getTransaccionId()));
		
		bean.setEstado("REGI");
		
		if(!UString.estaVacio(bean.getOrigen())) {
			if(bean.getOrigen().equals("PORTAL")) {
				//bean.setComunicacionPadreId(bean.getPk().getComunicacionId());
				bean.setEstado("GENR");
			}	
		}
		
		bean.setEmisorId(usuarioActual.getPersonaId());		
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		WfTransaccionComunicacionPk pk = registrar(bean);
		bean.setPk(pk);
		bean.setTransaccionEstado(DominioTransaccion.OK);
		return bean;
	}

	/**
	 * Aca el gestor registra el mensaje
	 * 
	 * @param bean
	 * @param usuarioActual
	 * @return
	 */
	public WfTransaccionComunicacion responderMensaje(WfTransaccionComunicacion bean,
			SeguridadUsuarioActual usuarioActual) {
		// se registra
		bean.getPk().setComunicacionId(generarId(bean.getPk().getTransaccionId()));
		bean.setEmisorId(usuarioActual.getPersonaId());
		bean.setEstado("REGI");
		bean.setCreacionFecha(new Date());
		bean.setCreacionUsuario(usuarioActual.getUsuario());
		bean.setModificacionFecha(new Date());
		bean.setModificacionUsuario(usuarioActual.getUsuario());
		registrar(bean);

		bean.setTransaccionEstado(DominioTransaccion.OK);
		return bean;
	}

	/**
	 * El gestor obtiene la lista de emisores que han tienen conversaciones
	 * 
	 * @param transaccionid
	 * @return
	 */
	public List<DtoTabla> obtenerEmisores(Integer transaccionid) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccionid));
		List lst = listarPorQuery(DtoTabla.class, "wftransaccioncomunicacion.obtenerEmisores", parametros);
		return lst;
	}

	/**
	 * Se lista la conversacion x emisor para que quede registro en leido
	 * 
	 * Alejandro: Traemos toda la conversacion
	 * 
	 * @param transaccionid
	 * @param emisorId
	 * @return
	 */
	public List<WfTransaccionComunicacion> obtenerConversacionesXEmisor(Integer transaccionid, Integer emisorId,
			SeguridadUsuarioActual usuarioActual) {

		// aca trae los mensajes hechos por el emisor
		Criteria criteria = getCriteria().add(Restrictions.eq("pk.transaccionId", transaccionid)).add(Restrictions.isNull("comunicacionPadreId"))
				/*.add(Restrictions.eq("emisorId", emisorId))*/;

		List<WfTransaccionComunicacion> lst = criteria.list();
		List<WfTransaccionComunicacion> respuestas = new ArrayList<WfTransaccionComunicacion>();

		//Asignando respuestas
		for (WfTransaccionComunicacion row : lst) {
			criteria = getCriteria().add(Restrictions.eq("pk.transaccionId", transaccionid))
					.add(Restrictions.eq("comunicacionPadreId", row.getPk().getComunicacionId()));
			respuestas.addAll(criteria.list());
		}

		lst.addAll(respuestas);

		for (WfTransaccionComunicacion row : lst) {
			// si estan en estado REGI se pone a LEID, pero solo a aquellas donde no es el
			// mismo emisor
			if (row.getEstado().equals("REGI")
					&& row.getEmisorId().intValue() != usuarioActual.getPersonaId().intValue()) {
				row.setEstado("LEID");
				row.setModificacionFecha(new Date());
				row.setModificacionUsuario(usuarioActual.getUsuario());
				actualizar(row);
			}
			
			if (row.getEstado().equals("GENR")
					&& row.getEmisorId().intValue() != usuarioActual.getPersonaId().intValue()) {
					row.setEstado("LEID");
					row.setModificacionFecha(new Date());
					row.setModificacionUsuario(usuarioActual.getUsuario());
					actualizar(row);
			}
			
		}
		return lst;
	}

}
