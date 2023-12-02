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
import net.royal.spring.workflow.dao.impl.WfTransaccionMacroDetalleDaoImpl;

import net.royal.spring.workflow.dominio.WfTransaccionMacroDetalle;
import net.royal.spring.workflow.dominio.WfTransaccionMacroDetallePk;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacroDetalle;

@Service (value = "BeanValidarWfTransaccionMacroDetalle")
public class WfTransaccionMacroDetalleServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarWfTransaccionMacroDetalle";
	private static Logger logger = LogManager.getLogger(WfTransaccionMacroDetalleServicioValidar.class);

	@Autowired
	private WfTransaccionMacroDetalleDaoImpl wfTransaccionMacroDetalleDao;

	private WfTransaccionMacroDetalle prepararBasico(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroDetalle wfTransaccionMacroDetalle, Boolean flgInsertar) {
		if (flgInsertar) {
			wfTransaccionMacroDetalle.setCreacionFecha(new Date());
			wfTransaccionMacroDetalle.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			wfTransaccionMacroDetalle.setModificacionFecha(new Date());
			wfTransaccionMacroDetalle.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO WfTransaccionMacroDetalle : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return wfTransaccionMacroDetalle;
	}

	public WfTransaccionMacroDetalle prepararInsertar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroDetalle wfTransaccionMacroDetalle) {
		if (wfTransaccionMacroDetalle == null)
			return wfTransaccionMacroDetalle;
		if (wfTransaccionMacroDetalle.getAuxFlgPreparadoBoolean())
			return wfTransaccionMacroDetalle;
		wfTransaccionMacroDetalle = prepararBasico(usuarioActual,wfTransaccionMacroDetalle, true);
		wfTransaccionMacroDetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfTransaccionMacroDetalle.Insertar : valores por defecto
		
		return wfTransaccionMacroDetalle;
	}

	public WfTransaccionMacroDetalle prepararActualizar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroDetalle wfTransaccionMacroDetalle) {
		if (wfTransaccionMacroDetalle == null)
			return wfTransaccionMacroDetalle;
		if (wfTransaccionMacroDetalle.getAuxFlgPreparadoBoolean())
			return wfTransaccionMacroDetalle;
		wfTransaccionMacroDetalle = prepararBasico(usuarioActual,wfTransaccionMacroDetalle, false);
		wfTransaccionMacroDetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfTransaccionMacroDetalle.Actualizar : valores por defecto
		
		return wfTransaccionMacroDetalle;
	}

	public WfTransaccionMacroDetalle prepararAnular(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroDetalle wfTransaccionMacroDetalle) {
		if (wfTransaccionMacroDetalle == null)
			return wfTransaccionMacroDetalle;
		if (wfTransaccionMacroDetalle.getAuxFlgPreparadoBoolean())
			return wfTransaccionMacroDetalle;
		wfTransaccionMacroDetalle = prepararBasico(usuarioActual, wfTransaccionMacroDetalle, false);
		wfTransaccionMacroDetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfTransaccionMacroDetalle.Anular : valores por defecto
		
		return wfTransaccionMacroDetalle;
	}

	public WfTransaccionMacroDetalle prepararEliminar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroDetalle wfTransaccionMacroDetalle) {
		if (wfTransaccionMacroDetalle == null)
			return wfTransaccionMacroDetalle;
		if (wfTransaccionMacroDetalle.getAuxFlgPreparadoBoolean())
			return wfTransaccionMacroDetalle;
		wfTransaccionMacroDetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfTransaccionMacroDetalle.Eliminar : valores por defecto
		
		return wfTransaccionMacroDetalle;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, WfTransaccionMacroDetalle wfTransaccionMacroDetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (wfTransaccionMacroDetalle == null)
			lst.add(this.getMsjUsuarioError("wftransaccionmacrodetalle.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (wfTransaccionMacroDetalle.getPk() != null) {
			Set<ConstraintViolation<WfTransaccionMacroDetallePk>> reglasPk = validator.validate(wfTransaccionMacroDetalle.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<WfTransaccionMacroDetalle>> reglas = validator.validate(wfTransaccionMacroDetalle);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO WfTransaccionMacroDetalle : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroDetalle wfTransaccionMacroDetalle) {
		if (wfTransaccionMacroDetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfTransaccionMacroDetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfTransaccionMacroDetalle = prepararInsertar(usuarioActual, wfTransaccionMacroDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, wfTransaccionMacroDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WfTransaccionMacroDetalle.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, WfTransaccionMacroDetalle wfTransaccionMacroDetalle) {
		if (wfTransaccionMacroDetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfTransaccionMacroDetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfTransaccionMacroDetalle = prepararActualizar(usuarioActual, wfTransaccionMacroDetalle);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, wfTransaccionMacroDetalle);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WfTransaccionMacroDetalle.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroDetalle wfTransaccionMacroDetalle) {
		if (wfTransaccionMacroDetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfTransaccionMacroDetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfTransaccionMacroDetalle = prepararEliminar(usuarioActual, wfTransaccionMacroDetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WfTransaccionMacroDetalle.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroDetallePk pk) {
		WfTransaccionMacroDetalle bean = wfTransaccionMacroDetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer ptransaccionMacroId,Integer ptransaccionMacroDetalleId) {
		return coreEliminar(usuarioActual,new WfTransaccionMacroDetallePk( ptransaccionMacroId, ptransaccionMacroDetalleId));
	}


	public DtoWfTransaccionMacroDetalle core(SeguridadUsuarioActual usuarioActual,String accion,DtoWfTransaccionMacroDetalle dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		WfTransaccionMacroDetalle wfTransaccionMacroDetalle = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, wfTransaccionMacroDetalle);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, wfTransaccionMacroDetalle);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, wfTransaccionMacroDetalle);
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
