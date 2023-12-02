package net.royal.spring.contabilidad.servicio.validar;

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
import net.royal.spring.contabilidad.dao.impl.AcCostcentervendorDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcCostcentervendor;
import net.royal.spring.contabilidad.dominio.BeanAcCostcentervendorPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcentervendor;

@Service (value = "BeanValidarAcCostcentervendor")
public class AcCostcentervendorServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAcCostcentervendor";
	private static Logger logger = LogManager.getLogger(AcCostcentervendorServicioValidar.class);

	@Autowired
	private AcCostcentervendorDaoImpl acCostcentervendorDao;

	private BeanAcCostcentervendor prepararBasico(SeguridadUsuarioActual usuarioActual,BeanAcCostcentervendor acCostcentervendor, Boolean flgInsertar) {
		
		// TODO AcCostcentervendor : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		acCostcentervendor.setLastdate(new Date());
		acCostcentervendor.setLastuser(usuarioActual.getUsuario());
		return acCostcentervendor;
	}

	public BeanAcCostcentervendor prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcentervendor acCostcentervendor) {
		if (acCostcentervendor == null)
			return acCostcentervendor;
		if (acCostcentervendor.getAuxFlgPreparadoBoolean())
			return acCostcentervendor;
		acCostcentervendor = prepararBasico(usuarioActual,acCostcentervendor, true);
		acCostcentervendor.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcentervendor.Insertar : valores por defecto
		
		return acCostcentervendor;
	}

	public BeanAcCostcentervendor prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanAcCostcentervendor acCostcentervendor) {
		if (acCostcentervendor == null)
			return acCostcentervendor;
		if (acCostcentervendor.getAuxFlgPreparadoBoolean())
			return acCostcentervendor;
		acCostcentervendor = prepararBasico(usuarioActual,acCostcentervendor, false);
		acCostcentervendor.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcentervendor.Actualizar : valores por defecto
		
		return acCostcentervendor;
	}

	public BeanAcCostcentervendor prepararAnular(SeguridadUsuarioActual usuarioActual,BeanAcCostcentervendor acCostcentervendor) {
		if (acCostcentervendor == null)
			return acCostcentervendor;
		if (acCostcentervendor.getAuxFlgPreparadoBoolean())
			return acCostcentervendor;
		acCostcentervendor = prepararBasico(usuarioActual, acCostcentervendor, false);
		acCostcentervendor.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcentervendor.Anular : valores por defecto
		
		return acCostcentervendor;
	}

	public BeanAcCostcentervendor prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcentervendor acCostcentervendor) {
		if (acCostcentervendor == null)
			return acCostcentervendor;
		if (acCostcentervendor.getAuxFlgPreparadoBoolean())
			return acCostcentervendor;
		acCostcentervendor.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcentervendor.Eliminar : valores por defecto
		
		return acCostcentervendor;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanAcCostcentervendor acCostcentervendor) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (acCostcentervendor == null)
			lst.add(this.getMsjUsuarioError("accostcentervendor.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (acCostcentervendor.getPk() != null) {
			Set<ConstraintViolation<BeanAcCostcentervendorPk>> reglasPk = validator.validate(acCostcentervendor.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanAcCostcentervendor>> reglas = validator.validate(acCostcentervendor);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AcCostcentervendor : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcentervendor acCostcentervendor) {
		if (acCostcentervendor.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcentervendor.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcentervendor = prepararInsertar(usuarioActual, acCostcentervendor);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcentervendor);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcCostcentervendor.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentervendor acCostcentervendor) {
		if (acCostcentervendor.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcentervendor.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcentervendor = prepararActualizar(usuarioActual, acCostcentervendor);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcentervendor);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcCostcentervendor.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcentervendor acCostcentervendor) {
		if (acCostcentervendor.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcentervendor.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcentervendor = prepararEliminar(usuarioActual, acCostcentervendor);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcCostcentervendor.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcentervendorPk pk) {
		BeanAcCostcentervendor bean = acCostcentervendorDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcostcenter,Integer pvendor) {
		return coreEliminar(usuarioActual,new BeanAcCostcentervendorPk( pcostcenter, pvendor));
	}


	public DtoComunAcCostcentervendor core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunAcCostcentervendor dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanAcCostcentervendor acCostcentervendor = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, acCostcentervendor);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, acCostcentervendor);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, acCostcentervendor);
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
