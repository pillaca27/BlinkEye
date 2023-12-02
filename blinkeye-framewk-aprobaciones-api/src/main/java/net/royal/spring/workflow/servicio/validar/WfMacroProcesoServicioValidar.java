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
import net.royal.spring.workflow.dao.impl.WfMacroProcesoDaoImpl;

import net.royal.spring.workflow.dominio.WfMacroProceso;
import net.royal.spring.workflow.dominio.WfMacroProcesoPk;
import net.royal.spring.workflow.dominio.dto.DtoWfMacroProceso;

@Service (value = "BeanValidarWfMacroProceso")
public class WfMacroProcesoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarWfMacroProceso";
	private static Logger logger = LogManager.getLogger(WfMacroProcesoServicioValidar.class);

	@Autowired
	private WfMacroProcesoDaoImpl wfMacroProcesoDao;

	private WfMacroProceso prepararBasico(SeguridadUsuarioActual usuarioActual,WfMacroProceso wfMacroProceso, Boolean flgInsertar) {
		if (flgInsertar) {
			wfMacroProceso.setCreacionFecha(new Date());
			wfMacroProceso.setCreacionUsuario(usuarioActual.getUsuario());
			wfMacroProceso.setModificacionFecha(new Date());
			wfMacroProceso.setModificacionUsuario(usuarioActual.getUsuario());
		} else {
			wfMacroProceso.setModificacionFecha(new Date());
			wfMacroProceso.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO WfMacroProceso : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return wfMacroProceso;
	}

	public WfMacroProceso prepararInsertar(SeguridadUsuarioActual usuarioActual,WfMacroProceso wfMacroProceso) {
		if (wfMacroProceso == null)
			return wfMacroProceso;
		if (wfMacroProceso.getAuxFlgPreparadoBoolean())
			return wfMacroProceso;
		wfMacroProceso = prepararBasico(usuarioActual,wfMacroProceso, true);
		wfMacroProceso.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfMacroProceso.Insertar : valores por defecto
		
		return wfMacroProceso;
	}

	public WfMacroProceso prepararActualizar(SeguridadUsuarioActual usuarioActual,WfMacroProceso wfMacroProceso) {
		if (wfMacroProceso == null)
			return wfMacroProceso;
		if (wfMacroProceso.getAuxFlgPreparadoBoolean())
			return wfMacroProceso;
		wfMacroProceso = prepararBasico(usuarioActual,wfMacroProceso, false);
		wfMacroProceso.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfMacroProceso.Actualizar : valores por defecto
		
		return wfMacroProceso;
	}

	public WfMacroProceso prepararAnular(SeguridadUsuarioActual usuarioActual,WfMacroProceso wfMacroProceso) {
		if (wfMacroProceso == null)
			return wfMacroProceso;
		if (wfMacroProceso.getAuxFlgPreparadoBoolean())
			return wfMacroProceso;
		wfMacroProceso = prepararBasico(usuarioActual, wfMacroProceso, false);
		wfMacroProceso.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfMacroProceso.Anular : valores por defecto
		
		return wfMacroProceso;
	}

	public WfMacroProceso prepararEliminar(SeguridadUsuarioActual usuarioActual,WfMacroProceso wfMacroProceso) {
		if (wfMacroProceso == null)
			return wfMacroProceso;
		if (wfMacroProceso.getAuxFlgPreparadoBoolean())
			return wfMacroProceso;
		wfMacroProceso.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfMacroProceso.Eliminar : valores por defecto
		
		return wfMacroProceso;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, WfMacroProceso wfMacroProceso) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (wfMacroProceso == null)
			lst.add(this.getMsjUsuarioError("wfmacroproceso.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (wfMacroProceso.getPk() != null) {
			Set<ConstraintViolation<WfMacroProcesoPk>> reglasPk = validator.validate(wfMacroProceso.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<WfMacroProceso>> reglas = validator.validate(wfMacroProceso);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO WfMacroProceso : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,WfMacroProceso wfMacroProceso) {
		if (wfMacroProceso.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfMacroProceso.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfMacroProceso = prepararInsertar(usuarioActual, wfMacroProceso);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, wfMacroProceso);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WfMacroProceso.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, WfMacroProceso wfMacroProceso) {
		if (wfMacroProceso.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfMacroProceso.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfMacroProceso = prepararActualizar(usuarioActual, wfMacroProceso);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, wfMacroProceso);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WfMacroProceso.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfMacroProceso wfMacroProceso) {
		if (wfMacroProceso.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfMacroProceso.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfMacroProceso = prepararEliminar(usuarioActual, wfMacroProceso);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WfMacroProceso.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfMacroProcesoPk pk) {
		WfMacroProceso bean = wfMacroProcesoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pmacroProcesoId) {
		return coreEliminar(usuarioActual,new WfMacroProcesoPk( pmacroProcesoId));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, WfMacroProceso wfMacroProceso) {
		if (wfMacroProceso.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfMacroProceso.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfMacroProceso = prepararAnular(usuarioActual, wfMacroProceso);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WfMacroProceso.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, WfMacroProcesoPk pk) {
		WfMacroProceso bean = wfMacroProcesoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pmacroProcesoId) {
		return coreAnular(usuarioActual,new WfMacroProcesoPk( pmacroProcesoId));
	}

	public DtoWfMacroProceso core(SeguridadUsuarioActual usuarioActual,String accion,DtoWfMacroProceso dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		WfMacroProceso wfMacroProceso = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, wfMacroProceso);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, wfMacroProceso);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, wfMacroProceso);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, wfMacroProceso);
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
