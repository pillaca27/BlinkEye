package net.royal.spring.rrhh.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.rrhh.dao.impl.HrEspecialidad2DaoImpl;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidad2;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidad2Pk;
import net.royal.spring.rrhh.dominio.dto.DtoHrEspecialidad2;

@Service
public class HrEspecialidad2ValidarImpl extends GenericoServicioValidar{
	
	@Autowired
	private HrEspecialidad2DaoImpl hrEspecialidad2DaoImpl;
	
	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,
			BeanHrEspecialidad2 especialidad2) {
		especialidad2 = prepararInsertar(usuarioActual, especialidad2);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();

		BeanHrEspecialidad2 dao = hrEspecialidad2DaoImpl.obtenerPorId(especialidad2.getPk());
		if (dao != null) {
			lstRes.add(this.getMsjUsuarioError("Ya existe un objeto con las mismas variables"));
		}
		else 
		{
			especialidad2.getPk().setEspecialidad(Integer.parseInt(hrEspecialidad2DaoImpl.generarCodigo()));
		}

		if (!lstRes.isEmpty())
			return lstRes;

		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, especialidad2);
		if (!lst.isEmpty())
			return lst;

		return lst;
	}
	
	public BeanHrEspecialidad2 prepararInsertar(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 especialidad2) {
		if (especialidad2 == null)
			return especialidad2;
		especialidad2 = prepararBasico(usuarioActual, especialidad2, true);
		especialidad2.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return especialidad2;
	}
	
	private BeanHrEspecialidad2 prepararBasico(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 especialidad2,
			Boolean flgInsertar) {
		especialidad2.setUltimoUsuario(usuarioActual.getUsuario());
		especialidad2.setUltimaFechaModif(new Date());

		return especialidad2;
	}
	
	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual,
			BeanHrEspecialidad2 especialidad2) {

		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (especialidad2 == null)
			lst.add(this.getMsjUsuarioError("El objeto enviado es nulo"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (especialidad2.getPk() != null) {
			Set<ConstraintViolation<BeanHrEspecialidad2Pk>> reglasPk = validator.validate(especialidad2.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanHrEspecialidad2>> reglas = validator.validate(especialidad2);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		return lst;

	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual,
			BeanHrEspecialidad2 especialidad2) {
		if (especialidad2.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		especialidad2.setAuxFlgValidadoBoolean(Boolean.TRUE);

		especialidad2 = prepararActualizar(usuarioActual, especialidad2);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, especialidad2);
		if (!lst.isEmpty())
			return lst;

		return lst;
	}
	
	public BeanHrEspecialidad2 prepararActualizar(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 especialidad2) {
		if (especialidad2 == null)
			return especialidad2;
		if (especialidad2.getAuxFlgPreparadoBoolean())
			return especialidad2;

		especialidad2 = prepararBasico(usuarioActual, especialidad2, false);
		especialidad2.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		return especialidad2;
	}
	
	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 bean) {
		if (bean.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		bean.setAuxFlgValidadoBoolean(Boolean.TRUE);

		bean = prepararAnular(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		return lst;
	}
	
	public BeanHrEspecialidad2 prepararAnular(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 bean) {
		if (bean == null)
			return bean;
		if (bean.getAuxFlgPreparadoBoolean())
			return bean;
		bean = prepararBasico(usuarioActual, bean, false);
		bean.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		return bean;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,
			BeanHrEspecialidad2 especialidad2) {
		if (especialidad2.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		especialidad2.setAuxFlgValidadoBoolean(Boolean.TRUE);

		especialidad2 = prepararEliminar(usuarioActual, especialidad2);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO Tipocambiomast.Eliminar : validaciones

		return lst;
	}

	public BeanHrEspecialidad2 prepararEliminar(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 especialidad2) {
		if (especialidad2 == null)
			return especialidad2;
		if (especialidad2.getAuxFlgPreparadoBoolean())
			return especialidad2;
		especialidad2.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		return especialidad2;
	}	

}
