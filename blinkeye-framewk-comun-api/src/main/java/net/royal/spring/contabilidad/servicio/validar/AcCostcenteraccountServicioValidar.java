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
import net.royal.spring.contabilidad.dao.impl.AcCostcenteraccountDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcCostcenteraccount;
import net.royal.spring.contabilidad.dominio.BeanAcCostcenteraccountPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenteraccount;

@Service (value = "BeanValidarAcCostcenteraccount")
public class AcCostcenteraccountServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAcCostcenteraccount";
	private static Logger logger = LogManager.getLogger(AcCostcenteraccountServicioValidar.class);

	@Autowired
	private AcCostcenteraccountDaoImpl acCostcenteraccountDao;

	private BeanAcCostcenteraccount prepararBasico(SeguridadUsuarioActual usuarioActual,BeanAcCostcenteraccount acCostcenteraccount, Boolean flgInsertar) {
		
		// TODO AcCostcenteraccount : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		acCostcenteraccount.setLastdate(new Date());
		acCostcenteraccount.setLastuser(usuarioActual.getUsuario());
		return acCostcenteraccount;
	}

	public BeanAcCostcenteraccount prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenteraccount acCostcenteraccount) {
		if (acCostcenteraccount == null)
			return acCostcenteraccount;
		if (acCostcenteraccount.getAuxFlgPreparadoBoolean())
			return acCostcenteraccount;
		acCostcenteraccount = prepararBasico(usuarioActual,acCostcenteraccount, true);
		acCostcenteraccount.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenteraccount.Insertar : valores por defecto
		
		return acCostcenteraccount;
	}

	public BeanAcCostcenteraccount prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenteraccount acCostcenteraccount) {
		if (acCostcenteraccount == null)
			return acCostcenteraccount;
		if (acCostcenteraccount.getAuxFlgPreparadoBoolean())
			return acCostcenteraccount;
		acCostcenteraccount = prepararBasico(usuarioActual,acCostcenteraccount, false);
		acCostcenteraccount.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenteraccount.Actualizar : valores por defecto
		
		return acCostcenteraccount;
	}

	public BeanAcCostcenteraccount prepararAnular(SeguridadUsuarioActual usuarioActual,BeanAcCostcenteraccount acCostcenteraccount) {
		if (acCostcenteraccount == null)
			return acCostcenteraccount;
		if (acCostcenteraccount.getAuxFlgPreparadoBoolean())
			return acCostcenteraccount;
		acCostcenteraccount = prepararBasico(usuarioActual, acCostcenteraccount, false);
		acCostcenteraccount.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenteraccount.Anular : valores por defecto
		
		return acCostcenteraccount;
	}

	public BeanAcCostcenteraccount prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenteraccount acCostcenteraccount) {
		if (acCostcenteraccount == null)
			return acCostcenteraccount;
		if (acCostcenteraccount.getAuxFlgPreparadoBoolean())
			return acCostcenteraccount;
		acCostcenteraccount.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcCostcenteraccount.Eliminar : valores por defecto
		
		return acCostcenteraccount;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanAcCostcenteraccount acCostcenteraccount) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (acCostcenteraccount == null)
			lst.add(this.getMsjUsuarioError("accostcenteraccount.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (acCostcenteraccount.getPk() != null) {
			Set<ConstraintViolation<BeanAcCostcenteraccountPk>> reglasPk = validator.validate(acCostcenteraccount.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanAcCostcenteraccount>> reglas = validator.validate(acCostcenteraccount);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AcCostcenteraccount : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenteraccount acCostcenteraccount) {
		if (acCostcenteraccount.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcenteraccount.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcenteraccount = prepararInsertar(usuarioActual, acCostcenteraccount);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcenteraccount);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcCostcenteraccount.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcenteraccount acCostcenteraccount) {
		if (acCostcenteraccount.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcenteraccount.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcenteraccount = prepararActualizar(usuarioActual, acCostcenteraccount);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcenteraccount);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcCostcenteraccount.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenteraccount acCostcenteraccount) {
		if (acCostcenteraccount.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcenteraccount.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcenteraccount = prepararEliminar(usuarioActual, acCostcenteraccount);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcCostcenteraccount.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcCostcenteraccountPk pk) {
		BeanAcCostcenteraccount bean = acCostcenteraccountDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcostcenter,String paccount) {
		return coreEliminar(usuarioActual,new BeanAcCostcenteraccountPk( pcostcenter, paccount));
	}


	public DtoComunAcCostcenteraccount core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunAcCostcenteraccount dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanAcCostcenteraccount acCostcenteraccount = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, acCostcenteraccount);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, acCostcenteraccount);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, acCostcenteraccount);
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
