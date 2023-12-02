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
import net.royal.spring.sistema.dao.impl.SyTipodocumentoprocesoDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyTipodocumentoproceso;
import net.royal.spring.sistema.dominio.BeanSyTipodocumentoprocesoPk;

@Service (value = "BeanValidarSyTipodocumentoproceso")
public class SyTipodocumentoprocesoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyTipodocumentoproceso";
	private static Logger logger = LogManager.getLogger(SyTipodocumentoprocesoServicioValidar.class);

	@Autowired
	private SyTipodocumentoprocesoDaoImpl syTipodocumentoprocesoDao;

	private BeanSyTipodocumentoproceso prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSyTipodocumentoproceso syTipodocumentoproceso, Boolean flgInsertar) {
     syTipodocumentoproceso.setUltimousuario(usuarioActual.getUsuario());
     syTipodocumentoproceso.setUltimafechamodif(new Date());
		
		// TODO SyTipodocumentoproceso : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syTipodocumentoproceso;
	}

	public BeanSyTipodocumentoproceso prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSyTipodocumentoproceso syTipodocumentoproceso) {
		if (syTipodocumentoproceso == null)
			return syTipodocumentoproceso;
		if (syTipodocumentoproceso.getAuxFlgPreparadoBoolean())
			return syTipodocumentoproceso;
		syTipodocumentoproceso = prepararBasico(usuarioActual,syTipodocumentoproceso, true);
		syTipodocumentoproceso.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyTipodocumentoproceso.Insertar : valores por defecto
		
		return syTipodocumentoproceso;
	}

	public BeanSyTipodocumentoproceso prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSyTipodocumentoproceso syTipodocumentoproceso) {
		if (syTipodocumentoproceso == null)
			return syTipodocumentoproceso;
		if (syTipodocumentoproceso.getAuxFlgPreparadoBoolean())
			return syTipodocumentoproceso;
		syTipodocumentoproceso = prepararBasico(usuarioActual,syTipodocumentoproceso, false);
		syTipodocumentoproceso.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyTipodocumentoproceso.Actualizar : valores por defecto
		
		return syTipodocumentoproceso;
	}

	public BeanSyTipodocumentoproceso prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSyTipodocumentoproceso syTipodocumentoproceso) {
		if (syTipodocumentoproceso == null)
			return syTipodocumentoproceso;
		if (syTipodocumentoproceso.getAuxFlgPreparadoBoolean())
			return syTipodocumentoproceso;
		syTipodocumentoproceso = prepararBasico(usuarioActual, syTipodocumentoproceso, false);
		syTipodocumentoproceso.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyTipodocumentoproceso.Anular : valores por defecto
		
		return syTipodocumentoproceso;
	}

	public BeanSyTipodocumentoproceso prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSyTipodocumentoproceso syTipodocumentoproceso) {
		if (syTipodocumentoproceso == null)
			return syTipodocumentoproceso;
		if (syTipodocumentoproceso.getAuxFlgPreparadoBoolean())
			return syTipodocumentoproceso;
		syTipodocumentoproceso.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyTipodocumentoproceso.Eliminar : valores por defecto
		
		return syTipodocumentoproceso;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoproceso syTipodocumentoproceso) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syTipodocumentoproceso == null)
			lst.add(this.getMsjUsuarioError("sytipodocumentoproceso.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syTipodocumentoproceso.getPk() != null) {
			Set<ConstraintViolation<BeanSyTipodocumentoprocesoPk>> reglasPk = validator.validate(syTipodocumentoproceso.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyTipodocumentoproceso>> reglas = validator.validate(syTipodocumentoproceso);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyTipodocumentoproceso : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyTipodocumentoproceso syTipodocumentoproceso) {
		syTipodocumentoproceso = prepararInsertar(usuarioActual, syTipodocumentoproceso);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syTipodocumentoproceso);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyTipodocumentoproceso.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoproceso syTipodocumentoproceso) {
		syTipodocumentoproceso = prepararActualizar(usuarioActual, syTipodocumentoproceso);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syTipodocumentoproceso);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyTipodocumentoproceso.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyTipodocumentoproceso syTipodocumentoproceso) {
		syTipodocumentoproceso = prepararEliminar(usuarioActual, syTipodocumentoproceso);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyTipodocumentoproceso.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyTipodocumentoprocesoPk pk) {
		BeanSyTipodocumentoproceso bean = syTipodocumentoprocesoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String ptipodocumentoid,String paplicacioncodigo,String pprocesocodigo) {
		return coreEliminar(usuarioActual,new BeanSyTipodocumentoprocesoPk( ptipodocumentoid, paplicacioncodigo, pprocesocodigo));
	}


	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,BeanSyTipodocumentoproceso syTipodocumentoproceso) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, syTipodocumentoproceso);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, syTipodocumentoproceso);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, syTipodocumentoproceso);
		return lst;
	}

}
