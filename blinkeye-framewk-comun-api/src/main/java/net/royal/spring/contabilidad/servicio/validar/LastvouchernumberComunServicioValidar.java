package net.royal.spring.contabilidad.servicio.validar;

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

import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.contabilidad.dao.impl.LastvouchernumberComunDaoImpl;
import net.royal.spring.contabilidad.dominio.BeanLastvouchernumber;
import net.royal.spring.contabilidad.dominio.BeanLastvouchernumberPk;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service(value = "BeanValidarLastvouchernumberComun")
public class LastvouchernumberComunServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarLastvouchernumberComun";
	private static Logger logger = LogManager.getLogger(LastvouchernumberComunServicioValidar.class);

	@Autowired
	private LastvouchernumberComunDaoImpl lastvouchernumberDao;

	private BeanLastvouchernumber prepararBasico(SeguridadUsuarioActual usuarioActual, BeanLastvouchernumber lastvouchernumber,
			Boolean flgInsertar) {

		// TODO Lastvouchernumber : valores por defecto comunes
		// Insert/Actualizar/Anular/Eliminar

		return lastvouchernumber;
	}

	public BeanLastvouchernumber prepararActualizar(SeguridadUsuarioActual usuarioActual,
			BeanLastvouchernumber lastvouchernumber) {
		if (lastvouchernumber == null)
			return lastvouchernumber;
		if (lastvouchernumber.getAuxFlgPreparadoBoolean())
			return lastvouchernumber;
		lastvouchernumber = prepararBasico(usuarioActual, lastvouchernumber, false);
		lastvouchernumber.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO Lastvouchernumber.Actualizar : valores por defecto

		return lastvouchernumber;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual,
			BeanLastvouchernumber lastvouchernumber) {
		if (lastvouchernumber.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		lastvouchernumber.setAuxFlgValidadoBoolean(Boolean.TRUE);

		lastvouchernumber = prepararActualizar(usuarioActual, lastvouchernumber);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, lastvouchernumber);
		if (!lst.isEmpty())
			return lst;

		// TODO Lastvouchernumber.Actualizar : validaciones

		return lst;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual,
			BeanLastvouchernumber lastvouchernumber) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (lastvouchernumber == null)
			lst.add(this.getMsjUsuarioError("lastvouchernumber.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (lastvouchernumber.getPk() != null) {
			Set<ConstraintViolation<BeanLastvouchernumberPk>> reglasPk = validator.validate(lastvouchernumber.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanLastvouchernumber>> reglas = validator.validate(lastvouchernumber);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}

		if (lastvouchernumber.getMonth01() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth02() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth03() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth04() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth05() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth06() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth07() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth08() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth09() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth10() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth11() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		} else if (lastvouchernumber.getMonth12() > 9999) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VOUCHER_NUMBER));
		}

		// TODO Lastvouchernumber : validaciones comunes Insert/Actualizar

		return lst;
	}

	public BeanLastvouchernumber prepararInsertar(SeguridadUsuarioActual usuarioActual,
			BeanLastvouchernumber lastvouchernumber) {
		if (lastvouchernumber == null)
			return lastvouchernumber;
		if (lastvouchernumber.getAuxFlgPreparadoBoolean())
			return lastvouchernumber;
		lastvouchernumber = prepararBasico(usuarioActual, lastvouchernumber, true);
		lastvouchernumber.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO Lastvouchernumber.Insertar : valores por defecto

		return lastvouchernumber;
	}
	

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanLastvouchernumber lastvouchernumber) {
		if (lastvouchernumber.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		lastvouchernumber.setAuxFlgValidadoBoolean(Boolean.TRUE);

		lastvouchernumber = prepararInsertar(usuarioActual, lastvouchernumber);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, lastvouchernumber);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Lastvouchernumber.Insertar : validaciones
		
		return lst;
	}
}
