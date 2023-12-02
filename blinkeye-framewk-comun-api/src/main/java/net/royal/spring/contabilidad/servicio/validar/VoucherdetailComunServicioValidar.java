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

import net.royal.spring.contabilidad.dao.impl.VoucherdetailComunDaoImpl;
import net.royal.spring.contabilidad.dominio.BeanVoucherdetail;
import net.royal.spring.contabilidad.dominio.BeanVoucherdetailPk;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service(value = "BeanValidarVoucherdetailComun")
public class VoucherdetailComunServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarVoucherdetailComun";
	private static Logger logger = LogManager.getLogger(VoucherdetailComunServicioValidar.class);

	@Autowired
	private VoucherdetailComunDaoImpl voucherdetailDao;

	public BeanVoucherdetail prepararInsertar(SeguridadUsuarioActual usuarioActual, BeanVoucherdetail voucherdetail) {
		if (voucherdetail == null)
			return voucherdetail;
		if (voucherdetail.getAuxFlgPreparadoBoolean())
			return voucherdetail;
		voucherdetail = prepararBasico(usuarioActual, voucherdetail, true);
		voucherdetail.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		String period = voucherdetail.getPk().getPeriod().replace("-", "");
		voucherdetail.getPk().setPeriod(period);
		voucherdetail.setStatus(UString.esNuloVacio(voucherdetail.getStatus()) ? "V" : voucherdetail.getStatus());
		// TODO Voucherdetail.Insertar : valores por defecto

		return voucherdetail;
	}

	private BeanVoucherdetail prepararBasico(SeguridadUsuarioActual usuarioActual, BeanVoucherdetail voucherdetail,
			Boolean flgInsertar) {

		// TODO Voucherdetail : valores por defecto comunes
		// Insert/Actualizar/Anular/Eliminar

		return voucherdetail;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual, BeanVoucherdetail voucherdetail) {
		if (voucherdetail.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		voucherdetail.setAuxFlgValidadoBoolean(Boolean.TRUE);

		voucherdetail = prepararInsertar(usuarioActual, voucherdetail);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, voucherdetail);
		if (!lst.isEmpty())
			return lst;

		// TODO Voucherdetail.Insertar : validaciones

		return lst;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanVoucherdetail voucherdetail) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (voucherdetail == null)
			lst.add(this.getMsjUsuarioError("voucherdetail.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (voucherdetail.getPk() != null) {
			Set<ConstraintViolation<BeanVoucherdetailPk>> reglasPk = validator.validate(voucherdetail.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanVoucherdetail>> reglas = validator.validate(voucherdetail);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}

		// TODO Voucherdetail : validaciones comunes Insert/Actualizar

		return lst;
	}

}
