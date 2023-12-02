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
import net.royal.spring.seguridad.dao.impl.SySeguridadautorizacionesComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSySeguridadautorizaciones;
import net.royal.spring.seguridad.dominio.BeanSySeguridadautorizacionesPk;

@Service (value = "BeanValidarSySeguridadautorizaciones")
public class SySeguridadautorizacionesServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSySeguridadautorizaciones";
	 
	@Autowired
	private SySeguridadautorizacionesComunDaoImpl sySeguridadautorizacionesDao;

	private BeanSySeguridadautorizaciones prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSySeguridadautorizaciones sySeguridadautorizaciones, Boolean flgInsertar) {
		if (UString.estaVacio(sySeguridadautorizaciones.getEstado()))
			sySeguridadautorizaciones.setEstado(ConstanteEstadoGenerico.ACTIVO);
     sySeguridadautorizaciones.setUltimousuario(usuarioActual.getUsuario());
     sySeguridadautorizaciones.setUltimafechamodif(new Date());
		return sySeguridadautorizaciones;
	}

	public BeanSySeguridadautorizaciones prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadautorizaciones sySeguridadautorizaciones) {
		if (sySeguridadautorizaciones == null)
			return sySeguridadautorizaciones;
		if (sySeguridadautorizaciones.getAuxFlgPreparado())
			return sySeguridadautorizaciones;
		sySeguridadautorizaciones = prepararBasico(usuarioActual,sySeguridadautorizaciones, true);
		sySeguridadautorizaciones.setAuxFlgPreparado(Boolean.TRUE);
		return sySeguridadautorizaciones;
	}

	public BeanSySeguridadautorizaciones prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadautorizaciones sySeguridadautorizaciones) {
		if (sySeguridadautorizaciones == null)
			return sySeguridadautorizaciones;
		if (sySeguridadautorizaciones.getAuxFlgPreparado())
			return sySeguridadautorizaciones;
		sySeguridadautorizaciones = prepararBasico(usuarioActual,sySeguridadautorizaciones, false);
		sySeguridadautorizaciones.setAuxFlgPreparado(Boolean.TRUE);
		return sySeguridadautorizaciones;
	}

	public BeanSySeguridadautorizaciones prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSySeguridadautorizaciones sySeguridadautorizaciones) {
		if (sySeguridadautorizaciones == null)
			return sySeguridadautorizaciones;
		if (sySeguridadautorizaciones.getAuxFlgPreparado())
			return sySeguridadautorizaciones;
		sySeguridadautorizaciones = prepararBasico(usuarioActual, sySeguridadautorizaciones, false);
		sySeguridadautorizaciones.setEstado(ConstanteEstadoGenerico.INACTIVO);
		sySeguridadautorizaciones.setAuxFlgPreparado(Boolean.TRUE);
		return sySeguridadautorizaciones;
	}

	public BeanSySeguridadautorizaciones prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadautorizaciones sySeguridadautorizaciones) {
		if (sySeguridadautorizaciones == null)
			return sySeguridadautorizaciones;
		if (sySeguridadautorizaciones.getAuxFlgPreparado())
			return sySeguridadautorizaciones;
		sySeguridadautorizaciones.setAuxFlgPreparado(Boolean.TRUE);
		return sySeguridadautorizaciones;
	}
	@SuppressWarnings({ "rawtypes" })
	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizaciones sySeguridadautorizaciones) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (sySeguridadautorizaciones == null)
			lst.add(this.getMsjUsuarioError("syseguridadautorizaciones.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (sySeguridadautorizaciones.getPk() != null) {
			Set<ConstraintViolation<BeanSySeguridadautorizacionesPk>> reglasPk = validator.validate(sySeguridadautorizaciones.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSySeguridadautorizaciones>> reglas = validator.validate(sySeguridadautorizaciones);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadautorizaciones sySeguridadautorizaciones) {
		sySeguridadautorizaciones = prepararInsertar(usuarioActual, sySeguridadautorizaciones);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, sySeguridadautorizaciones);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizaciones sySeguridadautorizaciones) {
		sySeguridadautorizaciones = prepararActualizar(usuarioActual, sySeguridadautorizaciones);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, sySeguridadautorizaciones);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadautorizaciones sySeguridadautorizaciones) {
		sySeguridadautorizaciones = prepararEliminar(usuarioActual, sySeguridadautorizaciones);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadautorizacionesPk pk) {
		BeanSySeguridadautorizaciones bean = sySeguridadautorizacionesDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) {
		return coreEliminar(usuarioActual,new BeanSySeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizaciones sySeguridadautorizaciones) {
		sySeguridadautorizaciones = prepararAnular(usuarioActual, sySeguridadautorizaciones);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadautorizacionesPk pk) {
		BeanSySeguridadautorizaciones bean = sySeguridadautorizacionesDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) {
		return coreAnular(usuarioActual,new BeanSySeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario));
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,BeanSySeguridadautorizaciones sySeguridadautorizaciones, String paplicacioncodigo,String pgrupo,String pconcepto,String pusuario) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, sySeguridadautorizaciones);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, sySeguridadautorizaciones);
		if (accion.equals(ConstantePantallaAccion.ANULAR)){
			BeanSySeguridadautorizacionesPk pk = new BeanSySeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario);
			return coreAnular(usuarioActual, pk);
		}
		if (accion.equals(ConstantePantallaAccion.ELIMINAR)){
			BeanSySeguridadautorizacionesPk pk = new BeanSySeguridadautorizacionesPk( paplicacioncodigo, pgrupo, pconcepto, pusuario);
			return coreEliminar(usuarioActual, pk);
		}
		return lst;
	}

}
