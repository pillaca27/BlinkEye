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
import net.royal.spring.contabilidad.dao.impl.AcCostcenterdestvalidDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcCostcenterdestvalid;
import net.royal.spring.contabilidad.dominio.BeanAcCostcenterdestvalidPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterdestvalid;

@Service (value = "BeanValidarAcCostcenterdestvalid")
public class AcCostcenterdestvalidServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAcCostcenterdestvalid";
	private static Logger logger = LogManager.getLogger(AcCostcenterdestvalidServicioValidar.class);

	@Autowired
	private AcCostcenterdestvalidDaoImpl acCostcenterdestvalidDao;

	private BeanAcCostcenterdestvalid prepararBasico(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterdestvalid acCostcenterdestvalid, Boolean flgInsertar) {
		
		// TODO AcCostcenterdestvalid : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		acCostcenterdestvalid.setLastdate(new Date());
		acCostcenterdestvalid.setLastuser(usuarioActual.getUsuario());
		return acCostcenterdestvalid;
	}

	public BeanAcCostcenterdestvalid prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterdestvalid acCostcenterdestvalid) {
		if (acCostcenterdestvalid == null)
			return acCostcenterdestvalid;
		if (acCostcenterdestvalid.getAuxFlgPreparadoBoolean())
			return acCostcenterdestvalid;
		acCostcenterdestvalid = prepararBasico(usuarioActual,acCostcenterdestvalid, true);
		acCostcenterdestvalid.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenterdestvalid.Insertar : valores por defecto
		
		return acCostcenterdestvalid;
	}

	public BeanAcCostcenterdestvalid prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterdestvalid acCostcenterdestvalid) {
		if (acCostcenterdestvalid == null)
			return acCostcenterdestvalid;
		if (acCostcenterdestvalid.getAuxFlgPreparadoBoolean())
			return acCostcenterdestvalid;
		acCostcenterdestvalid = prepararBasico(usuarioActual,acCostcenterdestvalid, false);
		acCostcenterdestvalid.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenterdestvalid.Actualizar : valores por defecto
		
		return acCostcenterdestvalid;
	}

	public BeanAcCostcenterdestvalid prepararAnular(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterdestvalid acCostcenterdestvalid) {
		if (acCostcenterdestvalid == null)
			return acCostcenterdestvalid;
		if (acCostcenterdestvalid.getAuxFlgPreparadoBoolean())
			return acCostcenterdestvalid;
		acCostcenterdestvalid = prepararBasico(usuarioActual, acCostcenterdestvalid, false);
		acCostcenterdestvalid.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenterdestvalid.Anular : valores por defecto
		
		return acCostcenterdestvalid;
	}

	public BeanAcCostcenterdestvalid prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterdestvalid acCostcenterdestvalid) {
		if (acCostcenterdestvalid == null)
			return acCostcenterdestvalid;
		if (acCostcenterdestvalid.getAuxFlgPreparadoBoolean())
			return acCostcenterdestvalid;
		acCostcenterdestvalid.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenterdestvalid.Eliminar : valores por defecto
		
		return acCostcenterdestvalid;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterdestvalid acCostcenterdestvalid) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (acCostcenterdestvalid == null)
			lst.add(this.getMsjUsuarioError("accostcenterdestvalid.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (acCostcenterdestvalid.getPk() != null) {
			Set<ConstraintViolation<BeanAcCostcenterdestvalidPk>> reglasPk = validator.validate(acCostcenterdestvalid.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanAcCostcenterdestvalid>> reglas = validator.validate(acCostcenterdestvalid);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AcCostcenterdestvalid : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterdestvalid acCostcenterdestvalid) {
		if (acCostcenterdestvalid.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcenterdestvalid.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcenterdestvalid = prepararInsertar(usuarioActual, acCostcenterdestvalid);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcenterdestvalid);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcCostcenterdestvalid.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenterdestvalid acCostcenterdestvalid) {
		if (acCostcenterdestvalid.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcenterdestvalid.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcenterdestvalid = prepararActualizar(usuarioActual, acCostcenterdestvalid);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcenterdestvalid);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcCostcenterdestvalid.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterdestvalid acCostcenterdestvalid) {
		if (acCostcenterdestvalid.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcenterdestvalid.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcenterdestvalid = prepararEliminar(usuarioActual, acCostcenterdestvalid);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcCostcenterdestvalid.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenterdestvalidPk pk) {
		BeanAcCostcenterdestvalid bean = acCostcenterdestvalidDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcostcenter,String pcostcenterdestination) {
		return coreEliminar(usuarioActual,new BeanAcCostcenterdestvalidPk( pcostcenter, pcostcenterdestination));
	}


	public DtoComunAcCostcenterdestvalid core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunAcCostcenterdestvalid dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanAcCostcenterdestvalid acCostcenterdestvalid = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, acCostcenterdestvalid);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, acCostcenterdestvalid);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, acCostcenterdestvalid);
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
