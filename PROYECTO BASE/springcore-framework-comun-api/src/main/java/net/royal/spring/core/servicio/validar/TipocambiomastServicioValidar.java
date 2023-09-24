package net.royal.spring.core.servicio.validar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.TipocambiomastDaoImpl;
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.BeanTipocambiomastPk;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service(value = "BeanValidarTipocambiomast")
public class TipocambiomastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarTipocambiomast";
	private static Logger logger = LogManager.getLogger(TipocambiomastServicioValidar.class);

	@Autowired
	private TipocambiomastDaoImpl tipocambiomastDao;

	private BeanTipocambiomast prepararBasico(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast,
			Boolean flgInsertar) {
		tipocambiomast.setUltimousuario(usuarioActual.getUsuario());
		tipocambiomast.setUltimafechamodif(new Date());

		// TODO BeanTipocambiomast : valores por defecto comunes
		// Insert/Actualizar/Anular/Eliminar

		return tipocambiomast;
	}

	public BeanTipocambiomast prepararInsertar(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast == null)
			return tipocambiomast;
		if (tipocambiomast.getAuxFlgPreparadoBoolean())
			return tipocambiomast;
		tipocambiomast.getPk().setFechacambio(removeTime(tipocambiomast.getPk().getFechacambio()));
		tipocambiomast = prepararBasico(usuarioActual, tipocambiomast, true);
		tipocambiomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO BeanTipocambiomast.Insertar : valores por defecto

		return tipocambiomast;
	}

	public BeanTipocambiomast prepararActualizar(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast == null)
			return tipocambiomast;
		if (tipocambiomast.getAuxFlgPreparadoBoolean())
			return tipocambiomast;

		tipocambiomast.getPk().setFechacambio(removeTime(tipocambiomast.getPk().getFechacambio()));
		tipocambiomast = prepararBasico(usuarioActual, tipocambiomast, false);
		tipocambiomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO BeanTipocambiomast.Actualizar : valores por defecto

		return tipocambiomast;
	}

	public BeanTipocambiomast prepararAnular(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast == null)
			return tipocambiomast;
		if (tipocambiomast.getAuxFlgPreparadoBoolean())
			return tipocambiomast;
		tipocambiomast = prepararBasico(usuarioActual, tipocambiomast, false);
		tipocambiomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO BeanTipocambiomast.Anular : valores por defecto

		return tipocambiomast;
	}

	public BeanTipocambiomast prepararEliminar(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast == null)
			return tipocambiomast;
		if (tipocambiomast.getAuxFlgPreparadoBoolean())
			return tipocambiomast;
		tipocambiomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO BeanTipocambiomast.Eliminar : valores por defecto

		return tipocambiomast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual,
			BeanTipocambiomast tipocambiomast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (tipocambiomast == null)
			lst.add(this.getMsjUsuarioError("tipocambiomast.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (tipocambiomast.getPk() != null) {
			Set<ConstraintViolation<BeanTipocambiomastPk>> reglasPk = validator.validate(tipocambiomast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanTipocambiomast>> reglas = validator.validate(tipocambiomast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}

		// TODO BeanTipocambiomast : validaciones comunes Insert/Actualizar

		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,
			BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipocambiomast.setAuxFlgValidadoBoolean(Boolean.TRUE);
		tipocambiomast = prepararInsertar(usuarioActual, tipocambiomast);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();

		BeanTipocambiomast tipocambioDao = tipocambiomastDao.obtenerPorId(tipocambiomast.getPk());
		if (tipocambioDao != null) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIPO_CAMBIO));
		}

		if (!lstRes.isEmpty())
			return lstRes;

		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipocambiomast);
		if (!lst.isEmpty())
			return lst;

		// TODO BeanTipocambiomast.Insertar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual,
			BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipocambiomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipocambiomast = prepararActualizar(usuarioActual, tipocambiomast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipocambiomast);
		if (!lst.isEmpty())
			return lst;

		// TODO BeanTipocambiomast.Actualizar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,
			BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipocambiomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipocambiomast = prepararEliminar(usuarioActual, tipocambiomast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO BeanTipocambiomast.Eliminar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, BeanTipocambiomastPk pk) {
		BeanTipocambiomast bean = tipocambiomastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, String pmonedacodigo,
			String pmonedacambiocodigo, java.util.Date pfechacambio) {
		return coreEliminar(usuarioActual, new BeanTipocambiomastPk(pmonedacodigo, pmonedacambiocodigo, pfechacambio));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipocambiomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipocambiomast = prepararAnular(usuarioActual, tipocambiomast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO BeanTipocambiomast.Anular : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanTipocambiomastPk pk) {
		BeanTipocambiomast bean = tipocambiomastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pmonedacodigo,
			String pmonedacambiocodigo, java.util.Date pfechacambio) {
		return coreAnular(usuarioActual, new BeanTipocambiomastPk(pmonedacodigo, pmonedacambiocodigo, pfechacambio));
	}

	public DtoComunTipocambiomast core(SeguridadUsuarioActual usuarioActual, String accion, DtoComunTipocambiomast dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanTipocambiomast tipocambiomast = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, tipocambiomast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, tipocambiomast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, tipocambiomast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, tipocambiomast);
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

	public static Date removeTime(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

}
