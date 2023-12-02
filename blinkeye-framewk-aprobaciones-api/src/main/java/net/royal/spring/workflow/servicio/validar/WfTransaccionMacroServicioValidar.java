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
import net.royal.spring.workflow.dao.impl.WfTransaccionMacroDaoImpl;

import net.royal.spring.workflow.dominio.WfTransaccionMacro;
import net.royal.spring.workflow.dominio.WfTransaccionMacroPk;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionMacro;

@Service (value = "BeanValidarWfTransaccionMacro")
public class WfTransaccionMacroServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarWfTransaccionMacro";
	private static Logger logger = LogManager.getLogger(WfTransaccionMacroServicioValidar.class);

	@Autowired
	private WfTransaccionMacroDaoImpl wfTransaccionMacroDao;

	private WfTransaccionMacro prepararBasico(SeguridadUsuarioActual usuarioActual,WfTransaccionMacro wfTransaccionMacro, Boolean flgInsertar) {
		if (flgInsertar) {
			wfTransaccionMacro.setCreacionFecha(new Date());
			wfTransaccionMacro.setCreacionUsuario(usuarioActual.getUsuario());
		} else {
			wfTransaccionMacro.setModificacionFecha(new Date());
			wfTransaccionMacro.setModificacionUsuario(usuarioActual.getUsuario());
		}
		
		// TODO WfTransaccionMacro : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return wfTransaccionMacro;
	}

	public WfTransaccionMacro prepararInsertar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacro wfTransaccionMacro) {
		if (wfTransaccionMacro == null)
			return wfTransaccionMacro;
		if (wfTransaccionMacro.getAuxFlgPreparadoBoolean())
			return wfTransaccionMacro;
		wfTransaccionMacro = prepararBasico(usuarioActual,wfTransaccionMacro, true);
		wfTransaccionMacro.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfTransaccionMacro.Insertar : valores por defecto
		
		return wfTransaccionMacro;
	}

	public WfTransaccionMacro prepararActualizar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacro wfTransaccionMacro) {
		if (wfTransaccionMacro == null)
			return wfTransaccionMacro;
		if (wfTransaccionMacro.getAuxFlgPreparadoBoolean())
			return wfTransaccionMacro;
		wfTransaccionMacro = prepararBasico(usuarioActual,wfTransaccionMacro, false);
		wfTransaccionMacro.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfTransaccionMacro.Actualizar : valores por defecto
		
		return wfTransaccionMacro;
	}

	public WfTransaccionMacro prepararAnular(SeguridadUsuarioActual usuarioActual,WfTransaccionMacro wfTransaccionMacro) {
		if (wfTransaccionMacro == null)
			return wfTransaccionMacro;
		if (wfTransaccionMacro.getAuxFlgPreparadoBoolean())
			return wfTransaccionMacro;
		wfTransaccionMacro = prepararBasico(usuarioActual, wfTransaccionMacro, false);
		wfTransaccionMacro.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfTransaccionMacro.Anular : valores por defecto
		
		return wfTransaccionMacro;
	}

	public WfTransaccionMacro prepararEliminar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacro wfTransaccionMacro) {
		if (wfTransaccionMacro == null)
			return wfTransaccionMacro;
		if (wfTransaccionMacro.getAuxFlgPreparadoBoolean())
			return wfTransaccionMacro;
		wfTransaccionMacro.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WfTransaccionMacro.Eliminar : valores por defecto
		
		return wfTransaccionMacro;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, WfTransaccionMacro wfTransaccionMacro) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (wfTransaccionMacro == null)
			lst.add(this.getMsjUsuarioError("wftransaccionmacro.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (wfTransaccionMacro.getPk() != null) {
			Set<ConstraintViolation<WfTransaccionMacroPk>> reglasPk = validator.validate(wfTransaccionMacro.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<WfTransaccionMacro>> reglas = validator.validate(wfTransaccionMacro);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO WfTransaccionMacro : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacro wfTransaccionMacro) {
		if (wfTransaccionMacro.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfTransaccionMacro.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfTransaccionMacro = prepararInsertar(usuarioActual, wfTransaccionMacro);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, wfTransaccionMacro);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WfTransaccionMacro.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, WfTransaccionMacro wfTransaccionMacro) {
		if (wfTransaccionMacro.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfTransaccionMacro.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfTransaccionMacro = prepararActualizar(usuarioActual, wfTransaccionMacro);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, wfTransaccionMacro);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WfTransaccionMacro.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacro wfTransaccionMacro) {
		if (wfTransaccionMacro.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		wfTransaccionMacro.setAuxFlgValidadoBoolean(Boolean.TRUE);

		wfTransaccionMacro = prepararEliminar(usuarioActual, wfTransaccionMacro);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WfTransaccionMacro.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfTransaccionMacroPk pk) {
		WfTransaccionMacro bean = wfTransaccionMacroDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer ptransaccionMacroId) {
		return coreEliminar(usuarioActual,new WfTransaccionMacroPk( ptransaccionMacroId));
	}


	public DtoWfTransaccionMacro core(SeguridadUsuarioActual usuarioActual,String accion,DtoWfTransaccionMacro dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		WfTransaccionMacro wfTransaccionMacro = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, wfTransaccionMacro);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, wfTransaccionMacro);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, wfTransaccionMacro);
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
