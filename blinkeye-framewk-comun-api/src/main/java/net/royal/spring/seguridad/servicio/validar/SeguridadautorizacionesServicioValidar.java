package net.royal.spring.seguridad.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.seguridad.dao.impl.SeguridadautorizacionesComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizaciones;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionesPk;

@Service(value = "BeanValidarSeguridadautorizaciones")
public class SeguridadautorizacionesServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSeguridadautorizaciones";

	@Autowired
	private SeguridadautorizacionesComunDaoImpl seguridadautorizacionesDao;

	private BeanSeguridadautorizaciones prepararBasico(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones, Boolean flgInsertar) {
	

//			if (seguridadautorizaciones.getEstado().equals(ConstanteEstadoGenerico.ACTIVO)
//					|| seguridadautorizaciones.getOpcionagregarflag().equals("S")
//					|| seguridadautorizaciones.getOpcionborrarflag().equals("S")
//					|| seguridadautorizaciones.getOpcionmodificarflag().equals("S")
//					|| seguridadautorizaciones.getOpcionaprobarflag().equals("S")) {
//				
//				seguridadautorizaciones.setEstado(ConstanteEstadoGenerico.ACTIVO);
//				seguridadautorizaciones.setUltimousuario(usuarioActual.getUsuario());
//				seguridadautorizaciones.setUltimafechamodif(new Date());
//			} 

		return seguridadautorizaciones;
	}

	public BeanSeguridadautorizaciones prepararInsertar(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones) {
		if (seguridadautorizaciones == null)
			return seguridadautorizaciones;
		if (seguridadautorizaciones.getAuxFlgPreparadoBoolean())
			return seguridadautorizaciones;
		seguridadautorizaciones = prepararBasico(usuarioActual, seguridadautorizaciones, true);
		seguridadautorizaciones.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return seguridadautorizaciones;
	}

	public BeanSeguridadautorizaciones prepararActualizar(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones) {
		if (seguridadautorizaciones == null)
			return seguridadautorizaciones;
		if (seguridadautorizaciones.getAuxFlgPreparadoBoolean())
			return seguridadautorizaciones;
		seguridadautorizaciones = prepararBasico(usuarioActual, seguridadautorizaciones, false);
		seguridadautorizaciones.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return seguridadautorizaciones;
	}

	public BeanSeguridadautorizaciones prepararAnular(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones) {
		if (seguridadautorizaciones == null)
			return seguridadautorizaciones;
		if (seguridadautorizaciones.getAuxFlgPreparadoBoolean())
			return seguridadautorizaciones;
		seguridadautorizaciones = prepararBasico(usuarioActual, seguridadautorizaciones, false);
		seguridadautorizaciones.setEstado(ConstanteEstadoGenerico.INACTIVO);
		seguridadautorizaciones.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return seguridadautorizaciones;
	}

	public BeanSeguridadautorizaciones prepararEliminar(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones) {
		if (seguridadautorizaciones == null)
			return seguridadautorizaciones;
		if (seguridadautorizaciones.getAuxFlgPreparadoBoolean())
			return seguridadautorizaciones;
		seguridadautorizaciones.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return seguridadautorizaciones;
	}

	@SuppressWarnings({ "rawtypes" })
	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(usuarioActual.CONSTRAINTS_NOTNULL));
		if (seguridadautorizaciones == null)
			lst.add(this.getMsjUsuarioError("seguridadautorizaciones.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (seguridadautorizaciones.getPk() != null) {
			Set<ConstraintViolation<BeanSeguridadautorizacionesPk>> reglasPk = validator
					.validate(seguridadautorizaciones.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSeguridadautorizaciones>> reglas = validator.validate(seguridadautorizaciones);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		if(!seguridadautorizaciones.getAuxAccion().equals(ConstantePantallaAccion.INSERTAR)) {
			if (seguridadautorizaciones.getEstado().equals(ConstanteEstadoGenerico.ACTIVO)
					|| seguridadautorizaciones.getOpcionagregarflag().equals("S")
					|| seguridadautorizaciones.getOpcionborrarflag().equals("S")
					|| seguridadautorizaciones.getOpcionmodificarflag().equals("S")
					|| seguridadautorizaciones.getOpcionaprobarflag().equals("S")) {
				
				seguridadautorizaciones.setEstado(ConstanteEstadoGenerico.ACTIVO);
				seguridadautorizaciones.setUltimousuario(usuarioActual.getUsuario());
				seguridadautorizaciones.setUltimafechamodif(new Date());
			} 
		} 
		seguridadautorizaciones.setUltimousuario(usuarioActual.getUsuario());
		seguridadautorizaciones.setUltimafechamodif(new Date());
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones) {
		seguridadautorizaciones = prepararInsertar(usuarioActual, seguridadautorizaciones);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, seguridadautorizaciones);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones) {
		seguridadautorizaciones = prepararActualizar(usuarioActual, seguridadautorizaciones);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, seguridadautorizaciones);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones) {
		seguridadautorizaciones = prepararEliminar(usuarioActual, seguridadautorizaciones);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizacionesPk pk) {
		BeanSeguridadautorizaciones bean = seguridadautorizacionesDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,
			String pgrupo, String pconcepto, String pusuario) {
		return coreEliminar(usuarioActual,
				new BeanSeguridadautorizacionesPk(paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual,
			BeanSeguridadautorizaciones seguridadautorizaciones) {
		seguridadautorizaciones = prepararAnular(usuarioActual, seguridadautorizaciones);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionesPk pk) {
		BeanSeguridadautorizaciones bean = seguridadautorizacionesDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,
			String pgrupo, String pconcepto, String pusuario) {
		return coreAnular(usuarioActual, new BeanSeguridadautorizacionesPk(paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual, String accion,
			BeanSeguridadautorizaciones seguridadautorizaciones, String paplicacioncodigo, String pgrupo, String pconcepto,
			String pusuario) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, seguridadautorizaciones);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, seguridadautorizaciones);
		if (accion.equals(ConstantePantallaAccion.ANULAR)) {
			BeanSeguridadautorizacionesPk pk = new BeanSeguridadautorizacionesPk(paplicacioncodigo, pgrupo, pconcepto,
					pusuario);
			return coreAnular(usuarioActual, pk);
		}
		if (accion.equals(ConstantePantallaAccion.ELIMINAR)) {
			BeanSeguridadautorizacionesPk pk = new BeanSeguridadautorizacionesPk(paplicacioncodigo, pgrupo, pconcepto,
					pusuario);
			return coreEliminar(usuarioActual, pk);
		}
		return lst;
	}

}
