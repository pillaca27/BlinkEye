package net.royal.spring.sistema.servicio.validar;

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
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyAdjuntoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyAdjunto;
import net.royal.spring.sistema.dominio.BeanSyAdjuntoPk;

@Service (value = "BeanValidarSyAdjunto")
public class SyAdjuntoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyAdjunto";
	private static Logger logger = LogManager.getLogger(SyAdjuntoServicioValidar.class);

	@Autowired
	private SyAdjuntoDaoImpl syAdjuntoDao;

	private BeanSyAdjunto prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto, Boolean flgInsertar) {
     syAdjunto.setUltimousuario(usuarioActual.getUsuario());
     syAdjunto.setUltimafechamodif(new Date());
		
		// TODO SyAdjunto : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syAdjunto;
	}

	public BeanSyAdjunto prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto) {
		if (syAdjunto == null)
			return syAdjunto;
		if (syAdjunto.getAuxFlgPreparado())
			return syAdjunto;
		syAdjunto = prepararBasico(usuarioActual,syAdjunto, true);
		syAdjunto.setAuxFlgPreparado(Boolean.TRUE);		
		// TODO SyAdjunto.Insertar : valores por defecto
		
		return syAdjunto;
	}

	public BeanSyAdjunto prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto) {
		if (syAdjunto == null)
			return syAdjunto;
		if (syAdjunto.getAuxFlgPreparado())
			return syAdjunto;
		syAdjunto = prepararBasico(usuarioActual,syAdjunto, false);
		syAdjunto.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO SyAdjunto.Actualizar : valores por defecto
		
		return syAdjunto;
	}

	public BeanSyAdjunto prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto) {
		if (syAdjunto == null)
			return syAdjunto;
		if (syAdjunto.getAuxFlgPreparado())
			return syAdjunto;
		syAdjunto = prepararBasico(usuarioActual, syAdjunto, false);
		syAdjunto.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO SyAdjunto.Anular : valores por defecto
		
		return syAdjunto;
	}

	public BeanSyAdjunto prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto) {
		if (syAdjunto == null)
			return syAdjunto;
		if (syAdjunto.getAuxFlgPreparado())
			return syAdjunto;
		syAdjunto.setAuxFlgPreparado(Boolean.TRUE);
		
		// TODO SyAdjunto.Eliminar : valores por defecto
		
		return syAdjunto;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSyAdjunto syAdjunto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syAdjunto == null)
			lst.add(this.getMsjUsuarioError("syadjunto.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syAdjunto.getPk() != null) {
			Set<ConstraintViolation<BeanSyAdjuntoPk>> reglasPk = validator.validate(syAdjunto.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyAdjunto>> reglas = validator.validate(syAdjunto);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyAdjunto : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto) {
		syAdjunto = prepararInsertar(usuarioActual, syAdjunto);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syAdjunto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyAdjunto.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyAdjunto syAdjunto) {
		syAdjunto = prepararActualizar(usuarioActual, syAdjunto);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syAdjunto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyAdjunto.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto) {
		syAdjunto = prepararEliminar(usuarioActual, syAdjunto);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyAdjunto.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyAdjuntoPk pk) {
		BeanSyAdjunto bean = syAdjuntoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pnombretabla,String pclavetabla,Integer psecuencia) {
		return coreEliminar(usuarioActual,new BeanSyAdjuntoPk( pnombretabla, pclavetabla, psecuencia));
	}


	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,BeanSyAdjunto syAdjunto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, syAdjunto);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, syAdjunto);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, syAdjunto);
		return lst;
	}

}
