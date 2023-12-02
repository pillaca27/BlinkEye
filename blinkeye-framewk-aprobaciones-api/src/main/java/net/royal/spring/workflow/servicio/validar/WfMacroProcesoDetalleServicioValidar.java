package net.royal.spring.workflow.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.workflow.dao.impl.WfMacroProcesoDetalleDaoImpl;

import net.royal.spring.workflow.dominio.WfMacroProcesoDetalle;
import net.royal.spring.workflow.dominio.WfMacroProcesoDetallePk;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProcesoDetalle;

@Service (value = "BeanValidarWfMacroProcesoDetalle")
public class WfMacroProcesoDetalleServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarWfMacroProcesoDetalle";
	private static Logger logger = LogManager.getLogger(WfMacroProcesoDetalleServicioValidar.class);

	@Autowired
	private WfMacroProcesoDetalleDaoImpl wfMacroProcesoDetalleDao;

	private WfMacroProcesoDetalle prepararBasico(SeguridadUsuarioActual usuarioActual,WfMacroProcesoDetalle wfMacroProcesoDetalle, Boolean flgInsertar) {
		if (flgInsertar) {
			wfMacroProcesoDetalle.setCreacionFecha(new Date());
			wfMacroProcesoDetalle.setCreacionUsuario(usuarioActual.getUsuario());
			wfMacroProcesoDetalle.setModificacionFecha(new Date());
			wfMacroProcesoDetalle.setModificacionUsuario(usuarioActual.getUsuario());
		} else {
			wfMacroProcesoDetalle.setModificacionFecha(new Date());
			wfMacroProcesoDetalle.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO WfMacroProcesoDetalle : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return wfMacroProcesoDetalle;
	}

	public WfMacroProcesoDetalle prepararInsertar(SeguridadUsuarioActual usuarioActual,WfMacroProcesoDetalle wfMacroProcesoDetalle) {
		if (wfMacroProcesoDetalle == null)
			return wfMacroProcesoDetalle;
		if (wfMacroProcesoDetalle.getAuxFlgPreparadoBoolean())
			return wfMacroProcesoDetalle;
		wfMacroProcesoDetalle = prepararBasico(usuarioActual,wfMacroProcesoDetalle, true);
		wfMacroProcesoDetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfMacroProcesoDetalle.Insertar : valores por defecto
		
		return wfMacroProcesoDetalle;
	}

	public WfMacroProcesoDetalle prepararActualizar(SeguridadUsuarioActual usuarioActual,WfMacroProcesoDetalle wfMacroProcesoDetalle) {
		if (wfMacroProcesoDetalle == null)
			return wfMacroProcesoDetalle;
		if (wfMacroProcesoDetalle.getAuxFlgPreparadoBoolean())
			return wfMacroProcesoDetalle;
		wfMacroProcesoDetalle = prepararBasico(usuarioActual,wfMacroProcesoDetalle, false);
		wfMacroProcesoDetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfMacroProcesoDetalle.Actualizar : valores por defecto
		
		return wfMacroProcesoDetalle;
	}

	public WfMacroProcesoDetalle prepararAnular(SeguridadUsuarioActual usuarioActual,WfMacroProcesoDetalle wfMacroProcesoDetalle) {
		if (wfMacroProcesoDetalle == null)
			return wfMacroProcesoDetalle;
		if (wfMacroProcesoDetalle.getAuxFlgPreparadoBoolean())
			return wfMacroProcesoDetalle;
		wfMacroProcesoDetalle = prepararBasico(usuarioActual, wfMacroProcesoDetalle, false);
		wfMacroProcesoDetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfMacroProcesoDetalle.Anular : valores por defecto
		
		return wfMacroProcesoDetalle;
	}

	public WfMacroProcesoDetalle prepararEliminar(SeguridadUsuarioActual usuarioActual,WfMacroProcesoDetalle wfMacroProcesoDetalle) {
		if (wfMacroProcesoDetalle == null)
			return wfMacroProcesoDetalle;
		if (wfMacroProcesoDetalle.getAuxFlgPreparadoBoolean())
			return wfMacroProcesoDetalle;
		wfMacroProcesoDetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfMacroProcesoDetalle.Eliminar : valores por defecto
		
		return wfMacroProcesoDetalle;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, WfMacroProcesoDetalle wfMacroProcesoDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (wfMacroProcesoDetalle == null)
			lst.add(this.getMsjUsuarioError("wfmacroprocesodetalle.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (wfMacroProcesoDetalle.getPk() != null) {
			Set<ConstraintViolation<WfMacroProcesoDetallePk>> reglasPk = validator.validate(wfMacroProcesoDetalle.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<WfMacroProcesoDetalle>> reglas = validator.validate(wfMacroProcesoDetalle);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO WfMacroProcesoDetalle : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,WfMacroProcesoDetalle wfMacroProcesoDetalle) {
		if (wfMacroProcesoDetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfMacroProcesoDetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfMacroProcesoDetalle = prepararInsertar(usuarioActual, wfMacroProcesoDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, wfMacroProcesoDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WfMacroProcesoDetalle.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, WfMacroProcesoDetalle wfMacroProcesoDetalle) {
		if (wfMacroProcesoDetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfMacroProcesoDetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfMacroProcesoDetalle = prepararActualizar(usuarioActual, wfMacroProcesoDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, wfMacroProcesoDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WfMacroProcesoDetalle.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfMacroProcesoDetalle wfMacroProcesoDetalle) {
		if (wfMacroProcesoDetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfMacroProcesoDetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfMacroProcesoDetalle = prepararEliminar(usuarioActual, wfMacroProcesoDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WfMacroProcesoDetalle.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfMacroProcesoDetallePk pk) {
		WfMacroProcesoDetalle bean = wfMacroProcesoDetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pmacroProcesoId,String pprocesoOrigenId,String pprocesoDestinoId) {
		return coreEliminar(usuarioActual,new WfMacroProcesoDetallePk( pmacroProcesoId, pprocesoOrigenId, pprocesoDestinoId));
	}


	public DtoWfMacroProcesoDetalle core(SeguridadUsuarioActual usuarioActual,String accion,DtoWfMacroProcesoDetalle dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		WfMacroProcesoDetalle wfMacroProcesoDetalle = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, wfMacroProcesoDetalle);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, wfMacroProcesoDetalle);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, wfMacroProcesoDetalle);
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
		} else {
			dto.setTransaccionEstado(DominioTransaccion.OK);
			dto.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		}
		dto.setAuxFlgValidadoBoolean(Boolean.TRUE);
		return dto;
	}

}
