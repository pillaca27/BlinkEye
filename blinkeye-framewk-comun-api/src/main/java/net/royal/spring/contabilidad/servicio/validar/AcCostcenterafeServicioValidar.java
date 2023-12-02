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
import net.royal.spring.contabilidad.dao.impl.AcCostcenterafeDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcCostcenterafe;
import net.royal.spring.contabilidad.dominio.BeanAcCostcenterafePk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterafe;

@Service (value = "BeanValidarAcCostcenterafe")
public class AcCostcenterafeServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAcCostcenterafe";
	private static Logger logger = LogManager.getLogger(AcCostcenterafeServicioValidar.class);

	@Autowired
	private AcCostcenterafeDaoImpl acCostcenterafeDao;

	private BeanAcCostcenterafe prepararBasico(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterafe acCostcenterafe, Boolean flgInsertar) {
		
		// TODO AcCostcenterafe : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		acCostcenterafe.setLastdate(new Date());
		acCostcenterafe.setLastuser(usuarioActual.getUsuario());
		return acCostcenterafe;
	}

	public BeanAcCostcenterafe prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterafe acCostcenterafe) {
		if (acCostcenterafe == null)
			return acCostcenterafe;
		if (acCostcenterafe.getAuxFlgPreparadoBoolean())
			return acCostcenterafe;
		acCostcenterafe = prepararBasico(usuarioActual,acCostcenterafe, true);
		acCostcenterafe.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenterafe.Insertar : valores por defecto
		
		return acCostcenterafe;
	}

	public BeanAcCostcenterafe prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterafe acCostcenterafe) {
		if (acCostcenterafe == null)
			return acCostcenterafe;
		if (acCostcenterafe.getAuxFlgPreparadoBoolean())
			return acCostcenterafe;
		acCostcenterafe = prepararBasico(usuarioActual,acCostcenterafe, false);
		acCostcenterafe.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenterafe.Actualizar : valores por defecto
		
		return acCostcenterafe;
	}

	public BeanAcCostcenterafe prepararAnular(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterafe acCostcenterafe) {
		if (acCostcenterafe == null)
			return acCostcenterafe;
		if (acCostcenterafe.getAuxFlgPreparadoBoolean())
			return acCostcenterafe;
		acCostcenterafe = prepararBasico(usuarioActual, acCostcenterafe, false);
		acCostcenterafe.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenterafe.Anular : valores por defecto
		
		return acCostcenterafe;
	}

	public BeanAcCostcenterafe prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterafe acCostcenterafe) {
		if (acCostcenterafe == null)
			return acCostcenterafe;
		if (acCostcenterafe.getAuxFlgPreparadoBoolean())
			return acCostcenterafe;
		acCostcenterafe.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenterafe.Eliminar : valores por defecto
		
		return acCostcenterafe;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterafe acCostcenterafe) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (acCostcenterafe == null)
			lst.add(this.getMsjUsuarioError("accostcenterafe.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (acCostcenterafe.getPk() != null) {
			Set<ConstraintViolation<BeanAcCostcenterafePk>> reglasPk = validator.validate(acCostcenterafe.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanAcCostcenterafe>> reglas = validator.validate(acCostcenterafe);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AcCostcenterafe : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterafe acCostcenterafe) {
		if (acCostcenterafe.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcenterafe.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcenterafe = prepararInsertar(usuarioActual, acCostcenterafe);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcenterafe);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcCostcenterafe.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterafe acCostcenterafe) {
		if (acCostcenterafe.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcenterafe.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcenterafe = prepararActualizar(usuarioActual, acCostcenterafe);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcenterafe);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcCostcenterafe.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterafe acCostcenterafe) {
		if (acCostcenterafe.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcenterafe.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcenterafe = prepararEliminar(usuarioActual, acCostcenterafe);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcCostcenterafe.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterafePk pk) {
		BeanAcCostcenterafe bean = acCostcenterafeDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcostcenter,String pafe) {
		return coreEliminar(usuarioActual,new BeanAcCostcenterafePk( pcostcenter, pafe));
	}


	public DtoComunAcCostcenterafe core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunAcCostcenterafe dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanAcCostcenterafe acCostcenterafe = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, acCostcenterafe);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, acCostcenterafe);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, acCostcenterafe);
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
