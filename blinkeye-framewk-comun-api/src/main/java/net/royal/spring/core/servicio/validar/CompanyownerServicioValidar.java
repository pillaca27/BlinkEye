package net.royal.spring.core.servicio.validar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.core.dao.impl.CompanyownerDaoImpl;
import net.royal.spring.core.dominio.BeanCompanyowner;
import net.royal.spring.core.dominio.BeanCompanyownerPk;
import net.royal.spring.core.dominio.dto.DtoComunCompanyowner;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarCompanyowner")
public class CompanyownerServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarCompanyowner";
	private static Logger logger = LogManager.getLogger(CompanyownerServicioValidar.class);

	@Autowired
	private CompanyownerDaoImpl companyownerDao;

	private BeanCompanyowner prepararBasico(SeguridadUsuarioActual usuarioActual,BeanCompanyowner companyowner, Boolean flgInsertar) {
		
		// TODO Companyowner : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return companyowner;
	}

	public BeanCompanyowner prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanCompanyowner companyowner) {
		if (companyowner == null)
			return companyowner;
		if (companyowner.getAuxFlgPreparadoBoolean())
			return companyowner;
		companyowner = prepararBasico(usuarioActual,companyowner, true);
		companyowner.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companyowner.Insertar : valores por defecto
		
		return companyowner;
	}

	public BeanCompanyowner prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanCompanyowner companyowner) {
		if (companyowner == null)
			return companyowner;
		if (companyowner.getAuxFlgPreparadoBoolean())
			return companyowner;
		companyowner = prepararBasico(usuarioActual,companyowner, false);
		companyowner.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companyowner.Actualizar : valores por defecto
		
		return companyowner;
	}

	public BeanCompanyowner prepararAnular(SeguridadUsuarioActual usuarioActual,BeanCompanyowner companyowner) {
		if (companyowner == null)
			return companyowner;
		if (companyowner.getAuxFlgPreparadoBoolean())
			return companyowner;
		companyowner = prepararBasico(usuarioActual, companyowner, false);
		companyowner.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companyowner.Anular : valores por defecto
		
		return companyowner;
	}

	public BeanCompanyowner prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanCompanyowner companyowner) {
		if (companyowner == null)
			return companyowner;
		if (companyowner.getAuxFlgPreparadoBoolean())
			return companyowner;
		companyowner.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companyowner.Eliminar : valores por defecto
		
		return companyowner;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanCompanyowner companyowner) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (companyowner == null)
			lst.add(this.getMsjUsuarioError("companyowner.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (companyowner.getPk() != null) {
			Set<ConstraintViolation<BeanCompanyownerPk>> reglasPk = validator.validate(companyowner.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanCompanyowner>> reglas = validator.validate(companyowner);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Companyowner : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanCompanyowner companyowner) {
		if (companyowner.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		companyowner.setAuxFlgValidadoBoolean(Boolean.TRUE);

		companyowner = prepararInsertar(usuarioActual, companyowner);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, companyowner);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Companyowner.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanCompanyowner companyowner) {
		if (companyowner.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		companyowner.setAuxFlgValidadoBoolean(Boolean.TRUE);

		companyowner = prepararActualizar(usuarioActual, companyowner);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, companyowner);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Companyowner.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanCompanyowner companyowner) {
		if (companyowner.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		companyowner.setAuxFlgValidadoBoolean(Boolean.TRUE);

		companyowner = prepararEliminar(usuarioActual, companyowner);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Companyowner.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanCompanyownerPk pk) {
		BeanCompanyowner bean = companyownerDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcompanyowner) {
		return coreEliminar(usuarioActual,new BeanCompanyownerPk( pcompanyowner));
	}


	public DtoComunCompanyowner core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunCompanyowner dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanCompanyowner companyowner = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, companyowner);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, companyowner);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, companyowner);
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
