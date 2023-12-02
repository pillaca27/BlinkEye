package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.modelo.WorkFlowResultado;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfProcesos;
import net.royal.spring.workflow.dominio.WfProcesosPk;
import net.royal.spring.workflow.dominio.dto.DtoExportarWfProceso;

@Repository
public class WfProcesosDaoImpl extends GenericoDaoImpl<WfProcesos, WfProcesosPk> {

	private static final long serialVersionUID = 1L;

	public WfProcesosDaoImpl() {
		super("wfprocesos");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfProcesos registrarProceso(WfProcesos procesoBean) {
		this.registrar(procesoBean);
		return procesoBean;
	}

	public List<WfProcesos> listarPorAplicacionWF(String idAplicacion) {
		Criteria criteria = getCriteria();
		criteria = criteria.add(Restrictions.eq("aplicacionid", idAplicacion));
		criteria = criteria.add(Restrictions.eq("estado", "A"));
		return criteria.list();
	}

	public List<WfProcesos> listarWF() {
		Criteria criteria = getCriteria();
		criteria = criteria.add(Restrictions.eq("estado", "A"));
		return criteria.list();
	}

	public DtoTabla obtenerProcesoSolicitante(Integer persona, String proceso) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_persona_id", Integer.class, persona));
		parametros.add(new DominioParametroPersistencia("p_proceso_id", String.class, proceso));
		List lst = this.listarPorQuery(DtoTabla.class, "wfprocesos.obtenerProcesoSolicitante", parametros);
		if (lst.size() == 1) {
			return (DtoTabla) lst.get(0);
		} else {
			return new DtoTabla();
		}
	}

	public HashMap agregarParametros(Boolean flgBuscar, HashMap<String, Object> map,
			WorkFlowResultado workFlowTransaccion) {
		try {
			if (map == null)
				return map;
			if (workFlowTransaccion == null)
				return map;

			String accion = UString.obtenerValorCadenaSinNulo(workFlowTransaccion.getAccion());
			String accionNombre = "", accionDescripcion = "";
			String fe = "";
			if (workFlowTransaccion.getFechaRegistro() != null)
				fe = UFechaHora.convertirFechaCadena(workFlowTransaccion.getFechaRegistro(), "dd/MM/yyyy hh:mm:ss aaa");

			if (accion.equals("APROBAR")) {
				accionNombre = "Aprobado";
				accionDescripcion = "Proceso Aprobado";
			}
			if (accion.equals("SEGUIMIENTO")) {
				accionNombre = "En Seguimiento";
				accionDescripcion = "Proceso En Seguimiento";
			}
			if (accion.equals("RECHAZAR")) {
				accionNombre = "Rechazado";
				accionDescripcion = "Proceso Rechazado";
			}
			if (accion.equals("DEVOLVER")) {
				accionNombre = "Devuelto";
				accionDescripcion = "Proceso Devuelto";
			}
			if (accion.equals("GUARDA") || accion.equals("GUARDAR")) {
				accionNombre = "Registrado";
				accionDescripcion = "Proceso Registrado";
			}

			// logger.debug("-->> DARIO SI ENTRO A VER LOS PARAMETROS");
			// logger.debug("-->> DARIO SI ENTRO A VER LOS PARAMETROS:"+accionDescripcion);
			// logger.debug("-->> DARIO SI ENTRO A VER LOS PARAMETROS:"+accion);
			// logger.debug("-->> DARIO SI ENTRO A VER LOS PARAMETROS:"+accionNombre);

			DtoTabla d = obtenerProcesoSolicitante(workFlowTransaccion.getSolicitanteId(),
					workFlowTransaccion.getCodigoproceso());
			String persona = UInteger.obtenerValorEnteroSinNulo(workFlowTransaccion.getSolicitanteId()).toString();
			String proceso = workFlowTransaccion.getCodigoproceso();
			if (!UString.esNuloVacio(d.getCodigo()))
				persona = d.getCodigo();
			if (!UString.esNuloVacio(d.getNombre()))
				proceso = d.getNombre();

			if (flgBuscar) {
				// buscar parametros para que no se repitan
				logger.debug("-->> DARIO NO ENTRO A VER LOS PARAMETROS");
				Object otransaccionId = map.get("p_transaccionId");
				if (otransaccionId == null) {
					map.remove("p_transaccionId");
					map.put("p_transaccionId", workFlowTransaccion.getTransaccionid());
				}
				Object otransaccionAccionDescripcion = map.get("p_transaccionAccionDescripcion");
				if (otransaccionAccionDescripcion == null) {
					map.remove("p_transaccionAccionDescripcion");
					map.put("p_transaccionAccionDescripcion", accionDescripcion);
				}
				Object otransaccionAccionId = map.get("p_transaccionAccionId");
				if (otransaccionAccionId == null) {
					map.remove("p_transaccionAccionId");
					map.put("p_transaccionAccionId", accion);
				}
				Object otransaccionAccionNombre = map.get("p_transaccionAccionNombre");
				if (otransaccionAccionNombre == null) {
					map.remove("p_transaccionAccionNombre");
					map.put("p_transaccionAccionNombre", accionNombre);
				}
				// ----------->
				Object otransaccionSolicitanteNombre = map.get("p_transaccionSolicitanteNombre");
				if (otransaccionSolicitanteNombre == null) {
					map.remove("p_transaccionSolicitanteNombre");
					map.put("p_transaccionSolicitanteNombre", persona);
				}
				Object otransaccionFechaRegistro_mdformatofechahora = map
						.get("p_transaccionFechaRegistro_mdformatofechahora");
				if (otransaccionFechaRegistro_mdformatofechahora == null) {
					map.remove("p_transaccionFechaRegistro_mdformatofechahora");
					map.put("p_transaccionFechaRegistro_mdformatofechahora", fe);
				}

				Object otransaccionReferencia = map.get("p_transaccionReferencia");
				if (otransaccionReferencia == null) {
					map.remove("p_transaccionReferencia");
					map.put("p_transaccionReferencia", workFlowTransaccion.getReferencia());
				}
				Object otransaccionProcesoNombre = map.get("p_transaccionProcesoNombre");
				if (otransaccionProcesoNombre == null) {
					map.remove("p_transaccionProcesoNombre");
					map.put("p_transaccionProcesoNombre", proceso);
				}
			} else {
				map.put("p_transaccionId", workFlowTransaccion.getTransaccionid());
				map.put("p_transaccionSolicitanteNombre", persona);
				map.put("p_transaccionFechaRegistro_mdformatofechahora", fe);
				map.put("p_transaccionReferencia", workFlowTransaccion.getReferencia());
				map.put("p_transaccionProcesoNombre", proceso);
				map.put("p_transaccionAccionDescripcion", accionDescripcion);
				map.put("p_transaccionAccionId", accion);
				map.put("p_transaccionAccionNombre", accionNombre);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	public List<DtoExportarWfProceso> exportar(String proceso, Integer version) {
		List<DtoExportarWfProceso> lst = new ArrayList<DtoExportarWfProceso>();
		for (Object r : getCriteria().add(Restrictions.eq("pk.procesoid", proceso)).list()) {
			WfProcesos row = (WfProcesos) r;
			DtoExportarWfProceso dto = new DtoExportarWfProceso();
			dto.setProcesoId(row.getPk().getProcesoid());
			dto.setAplicacionId(row.getAplicacionid());
			dto.setNombre(row.getNombre());
			lst.add(dto);
		}
		return lst;
	}

	public void importar(DtoExportarWfProceso row, SeguridadUsuarioActual usuarioActual, boolean existeProceso) {

		// caso excepcion de tabla importar
		WfProcesos bean = null;

		if (existeProceso) {
			bean = obtenerPorId(new WfProcesosPk(row.getProcesoId()));
		} else {
			bean = new WfProcesos();
			bean.getPk().setProcesoid(row.getProcesoId());
		}

		bean.setAplicacionid(row.getAplicacionId());
		bean.setNombre(row.getNombre());
		bean.setEstado("A");
		bean.setCreacionfecha(new Date());
		bean.setCreacionusuario(usuarioActual.getUsuario());
		bean.setModificacionfecha(new Date());
		bean.setModificacionusuario(usuarioActual.getUsuario());
		if (existeProceso) {
			actualizar(bean);
		} else {
			registrar(bean);
		}
	}
}
