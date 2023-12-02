package net.royal.spring.seguridad.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.seguridad.dao.impl.UsuarioComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanUsuario;
import net.royal.spring.seguridad.dominio.BeanUsuarioPk;

@Service(value = "BeanValidarUsuario")
public class UsuarioServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarUsuario";
	private static Logger logger = LogManager.getLogger(UsuarioServicioValidar.class);

	@Autowired
	private UsuarioComunDaoImpl usuarioDao;

	private BeanUsuario prepararBasico(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario, Boolean flgInsertar) {
		usuario.setUltimousuario(usuarioActual.getUsuario());
		usuario.setUltimafechamodif(new Date());
		if (flgInsertar) {
//                usuario.setUsuariocreacion(usuarioActual.getUsuario());
//                usuario.setFechacreacion(new Date());
		} else {
			// usuario.setUsuariomodificacion(usuarioActual.getUsuario());
			// usuario.setFechamodificacion(new Date());
		}
		return usuario;
	}

	public BeanUsuario prepararInsertar(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) {
		if (usuario == null)
			return usuario;
		if (usuario.getAuxFlgPreparadoBoolean())
			return usuario;
		usuario = prepararBasico(usuarioActual, usuario, true);
		usuario.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return usuario;
	}

	public BeanUsuario prepararActualizar(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) {
		if (usuario == null)
			return usuario;
		if (usuario.getAuxFlgPreparadoBoolean())
			return usuario;
		usuario = prepararBasico(usuarioActual, usuario, false);
		usuario.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return usuario;
	}

	public BeanUsuario prepararAnular(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) {
		if (usuario == null)
			return usuario;
		if (usuario.getAuxFlgPreparadoBoolean())
			return usuario;
		usuario = prepararBasico(usuarioActual, usuario, false);
		usuario.setEstado(ConstanteEstadoGenerico.INACTIVO);
		usuario.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return usuario;
	}

	public BeanUsuario prepararEliminar(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) {
		if (usuario == null)
			return usuario;
		if (usuario.getAuxFlgPreparadoBoolean())
			return usuario;
		usuario.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		return usuario;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (usuario == null)
			lst.add(this.getMsjUsuarioError("usuario.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (usuario.getPk() != null) {
			Set<ConstraintViolation<BeanUsuarioPk>> reglasPk = validator.validate(usuario.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanUsuario>> reglas = validator.validate(usuario);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) {
		usuario = prepararInsertar(usuarioActual, usuario);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, usuario);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) {
		usuario = prepararActualizar(usuarioActual, usuario);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, usuario);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) {
		usuario = prepararEliminar(usuarioActual, usuario);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, BeanUsuarioPk pk) {
		BeanUsuario bean = usuarioDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, String pusuario) {
		return coreEliminar(usuarioActual, new BeanUsuarioPk(pusuario));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) {
		usuario = prepararAnular(usuarioActual, usuario);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanUsuarioPk pk) {
		BeanUsuario bean = usuarioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pusuario) {
		return coreAnular(usuarioActual, new BeanUsuarioPk(pusuario));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual, String accion, BeanUsuario usuario) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, usuario);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, usuario);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, usuario);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, usuario);
		return lst;
	}

}
