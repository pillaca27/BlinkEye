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

import net.royal.spring.seguridad.dao.impl.SeguridadperfilusuarioComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadperfilusuario;
import net.royal.spring.seguridad.dominio.BeanSeguridadperfilusuarioPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarSeguridadperfilusuario")
public class SeguridadperfilusuarioServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSeguridadperfilusuario";
	 
	@Autowired
	private SeguridadperfilusuarioComunDaoImpl seguridadperfilusuarioDao;

	private BeanSeguridadperfilusuario prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSeguridadperfilusuario seguridadperfilusuario, Boolean flgInsertar) {
		if (UString.estaVacio(seguridadperfilusuario.getEstado()))
			seguridadperfilusuario.setEstado(ConstanteEstadoGenerico.ACTIVO);
     seguridadperfilusuario.setUltimousuario(usuarioActual.getUsuario());
     seguridadperfilusuario.setUltimafechamodif(new Date());
		return seguridadperfilusuario;
	}

	public BeanSeguridadperfilusuario prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSeguridadperfilusuario seguridadperfilusuario) {
		if (seguridadperfilusuario == null)
			return seguridadperfilusuario;
		if (seguridadperfilusuario.getAuxFlgPreparado())
			return seguridadperfilusuario;
		seguridadperfilusuario = prepararBasico(usuarioActual,seguridadperfilusuario, true);
		seguridadperfilusuario.setAuxFlgPreparado(Boolean.TRUE);
		return seguridadperfilusuario;
	}

	public BeanSeguridadperfilusuario prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSeguridadperfilusuario seguridadperfilusuario) {
		if (seguridadperfilusuario == null)
			return seguridadperfilusuario;
		if (seguridadperfilusuario.getAuxFlgPreparado())
			return seguridadperfilusuario;
		seguridadperfilusuario = prepararBasico(usuarioActual,seguridadperfilusuario, false);
		seguridadperfilusuario.setAuxFlgPreparado(Boolean.TRUE);
		return seguridadperfilusuario;
	}

	public BeanSeguridadperfilusuario prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSeguridadperfilusuario seguridadperfilusuario) {
		if (seguridadperfilusuario == null)
			return seguridadperfilusuario;
		if (seguridadperfilusuario.getAuxFlgPreparado())
			return seguridadperfilusuario;
		seguridadperfilusuario = prepararBasico(usuarioActual, seguridadperfilusuario, false);
		seguridadperfilusuario.setEstado(ConstanteEstadoGenerico.INACTIVO);
		seguridadperfilusuario.setAuxFlgPreparado(Boolean.TRUE);
		return seguridadperfilusuario;
	}

	public BeanSeguridadperfilusuario prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSeguridadperfilusuario seguridadperfilusuario) {
		if (seguridadperfilusuario == null)
			return seguridadperfilusuario;
		if (seguridadperfilusuario.getAuxFlgPreparado())
			return seguridadperfilusuario;
		seguridadperfilusuario.setAuxFlgPreparado(Boolean.TRUE);
		return seguridadperfilusuario;
	}
	@SuppressWarnings({ "rawtypes", "unused" })
	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuario seguridadperfilusuario) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		BeanSeguridadperfilusuario seguridadvalida = new BeanSeguridadperfilusuario();
		
		seguridadvalida = seguridadperfilusuarioDao.obtenerPorId(seguridadperfilusuario.getPk());
		//seguridadvalida = seguridadperfilusuarioDao.obtenerPorId(seguridadperfilusuario.getPk().getPerfil());
		
		if(seguridadvalida!=null) {
			lst.add(this.getMsjUsuarioError("usuario o perfil duplicado"));
		}
		
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(usuarioActual.CONSTRAINTS_NOTNULL));
		if (seguridadperfilusuario == null)
			lst.add(this.getMsjUsuarioError("seguridadperfilusuario.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (seguridadperfilusuario.getPk() != null) {
			Set<ConstraintViolation<BeanSeguridadperfilusuarioPk>> reglasPk = validator.validate(seguridadperfilusuario.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSeguridadperfilusuario>> reglas = validator.validate(seguridadperfilusuario);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSeguridadperfilusuario seguridadperfilusuario) {
		seguridadperfilusuario = prepararInsertar(usuarioActual, seguridadperfilusuario);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, seguridadperfilusuario);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuario seguridadperfilusuario) {
		seguridadperfilusuario = prepararActualizar(usuarioActual, seguridadperfilusuario);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, seguridadperfilusuario);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSeguridadperfilusuario seguridadperfilusuario) {
		seguridadperfilusuario = prepararEliminar(usuarioActual, seguridadperfilusuario);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSeguridadperfilusuarioPk pk) {
		BeanSeguridadperfilusuario bean = seguridadperfilusuarioDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pperfil,String pusuario) {
		return coreEliminar(usuarioActual,new BeanSeguridadperfilusuarioPk( pperfil, pusuario));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuario seguridadperfilusuario) {
		seguridadperfilusuario = prepararAnular(usuarioActual, seguridadperfilusuario);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSeguridadperfilusuarioPk pk) {
		BeanSeguridadperfilusuario bean = seguridadperfilusuarioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pperfil,String pusuario) {
		return coreAnular(usuarioActual,new BeanSeguridadperfilusuarioPk( pperfil, pusuario));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,BeanSeguridadperfilusuario seguridadperfilusuario, String pperfil,String pusuario) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, seguridadperfilusuario);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, seguridadperfilusuario);
		if (accion.equals(ConstantePantallaAccion.ANULAR)){
			BeanSeguridadperfilusuarioPk pk = new BeanSeguridadperfilusuarioPk( pperfil, pusuario);
			return coreAnular(usuarioActual, pk);
		}
		if (accion.equals(ConstantePantallaAccion.ELIMINAR)){
			BeanSeguridadperfilusuarioPk pk = new BeanSeguridadperfilusuarioPk( pperfil, pusuario);
			return coreEliminar(usuarioActual, pk);
		}
		return lst;
	}

}
