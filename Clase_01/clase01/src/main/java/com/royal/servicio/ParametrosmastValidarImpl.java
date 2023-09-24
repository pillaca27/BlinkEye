package com.royal.servicio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.royal.dao.ParametrosmastDaoImpl;
import com.royal.dominio.Parametrosmast;
import com.royal.dominio.ParametrosmastPk;
import com.royal.dominio.dto.DtoParametros;
import com.royal.genericos.GenericoServicioValidar;
import com.royal.util.ConstantePantallaAccion;
import com.royal.util.DominioMensajeUsuario;
import com.royal.util.DominioTransaccion;
import com.royal.util.SeguridadUsuarioActual;

@Service(value = "BeanValidarParametrosmast")
public class ParametrosmastValidarImpl extends GenericoServicioValidar {
	
	@Autowired
	private ParametrosmastDaoImpl parametrosmastDaoImpl;
	
	
	public DtoParametros core(SeguridadUsuarioActual usuarioActual, String accion, DtoParametros dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		Parametrosmast parametrosmast = dto.obtenerBean();
		
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, parametrosmast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, parametrosmast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, parametrosmast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, parametrosmast);
		
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
	
	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual,
			Parametrosmast parametrosmast) {
		if (parametrosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		parametrosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		parametrosmast = prepararActualizar(usuarioActual, parametrosmast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, parametrosmast);
		if (!lst.isEmpty())
			return lst;

		return lst;
	}
	
	public Parametrosmast prepararActualizar(SeguridadUsuarioActual usuarioActual, Parametrosmast parametrosmast) {
		if (parametrosmast == null)
			return parametrosmast;
		if (parametrosmast.getAuxFlgPreparadoBoolean())
			return parametrosmast;

		parametrosmast = prepararBasico(usuarioActual, parametrosmast, false);
		parametrosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		return parametrosmast;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,
			Parametrosmast parametrosmast) {
		if (parametrosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		parametrosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		parametrosmast = prepararEliminar(usuarioActual, parametrosmast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO Tipocambiomast.Eliminar : validaciones

		return lst;
	}

	public Parametrosmast prepararEliminar(SeguridadUsuarioActual usuarioActual, Parametrosmast parametrosmast) {
		if (parametrosmast == null)
			return parametrosmast;
		if (parametrosmast.getAuxFlgPreparadoBoolean())
			return parametrosmast;
		parametrosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		return parametrosmast;
	}
	
	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, ParametrosmastPk pk) {
		Parametrosmast bean = parametrosmastDaoImpl.obtenerPorId(pk);
		return coreEliminar(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, String companiacodigo,
			String aplicacioncodigo, String parametroclave) {
		return coreEliminar(usuarioActual, new ParametrosmastPk(companiacodigo,  aplicacioncodigo,  parametroclave));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Parametrosmast parametrosmast) {
		if (parametrosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		parametrosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		parametrosmast = prepararAnular(usuarioActual, parametrosmast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		return lst;
	}
	
	public Parametrosmast prepararAnular(SeguridadUsuarioActual usuarioActual, Parametrosmast parametrosmast) {
		if (parametrosmast == null)
			return parametrosmast;
		if (parametrosmast.getAuxFlgPreparadoBoolean())
			return parametrosmast;
		parametrosmast = prepararBasico(usuarioActual, parametrosmast, false);
		parametrosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		return parametrosmast;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual,ParametrosmastPk pk) {
		Parametrosmast bean = parametrosmastDaoImpl.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String companiacodigo,
			String aplicacioncodigo, String parametroclave) {
		return coreAnular(usuarioActual,  new ParametrosmastPk(companiacodigo,  aplicacioncodigo,  parametroclave));
	}

	
	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,
			Parametrosmast parametrosmast) {
		parametrosmast = prepararInsertar(usuarioActual, parametrosmast);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();

		Parametrosmast parametroDao = parametrosmastDaoImpl.obtenerPorId(parametrosmast.getPk());
		if (parametroDao != null) {
			lstRes.add(this.getMsjUsuarioError("Ya existe un objeto con las mismas variables"));
		}

		if (!lstRes.isEmpty())
			return lstRes;

		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, parametrosmast);
		if (!lst.isEmpty())
			return lst;

		return lst;
	}

	public Parametrosmast prepararInsertar(SeguridadUsuarioActual usuarioActual, Parametrosmast parametrosmast) {
		if (parametrosmast == null)
			return parametrosmast;
		parametrosmast = prepararBasico(usuarioActual, parametrosmast, true);
		parametrosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return parametrosmast;
	}
	
	private Parametrosmast prepararBasico(SeguridadUsuarioActual usuarioActual, Parametrosmast parametrosmast,
			Boolean flgInsertar) {
		parametrosmast.setUltimousuario(usuarioActual.getUsuario());
		parametrosmast.setUltimafechamodif(new Date());

		return parametrosmast;
	}


	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual,
			Parametrosmast parametrosmast) {

		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (parametrosmast == null)
			lst.add(this.getMsjUsuarioError("El objeto enviado es nulo"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (parametrosmast.getPk() != null) {
			Set<ConstraintViolation<ParametrosmastPk>> reglasPk = validator.validate(parametrosmast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Parametrosmast>> reglas = validator.validate(parametrosmast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		return lst;

	}
}