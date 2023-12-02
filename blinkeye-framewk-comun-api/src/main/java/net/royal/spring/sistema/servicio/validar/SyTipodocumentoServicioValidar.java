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
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyTipodocumentoDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyTipodocumento;
import net.royal.spring.sistema.dominio.BeanSyTipodocumentoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumento;
import net.royal.spring.sistema.dominio.dto.DtoComunSyTipodocumentoproceso;

@Service(value = "BeanValidarSyTipodocumento")
public class SyTipodocumentoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyTipodocumento";
	private static Logger logger = LogManager.getLogger(SyTipodocumentoServicioValidar.class);

	@Autowired
	private SyTipodocumentoDaoImpl syTipodocumentoDao;

	private BeanSyTipodocumento prepararBasico(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento syTipodocumento,
			Boolean flgInsertar) {
		syTipodocumento.setUltimousuario(usuarioActual.getUsuario());
		syTipodocumento.setUltimafechamodif(new Date());

		// TODO SyTipodocumento : valores por defecto comunes
		// Insert/Actualizar/Anular/Eliminar

		return syTipodocumento;
	}

	public BeanSyTipodocumento prepararInsertar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento syTipodocumento) {
		if (syTipodocumento == null)
			return syTipodocumento;
		if (syTipodocumento.getAuxFlgPreparadoBoolean())
			return syTipodocumento;
		syTipodocumento = prepararBasico(usuarioActual, syTipodocumento, true);
		syTipodocumento.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO SyTipodocumento.Insertar : valores por defecto

		return syTipodocumento;
	}

	public BeanSyTipodocumento prepararActualizar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento syTipodocumento) {
		if (syTipodocumento == null)
			return syTipodocumento;
		if (syTipodocumento.getAuxFlgPreparadoBoolean())
			return syTipodocumento;
		syTipodocumento = prepararBasico(usuarioActual, syTipodocumento, false);
		syTipodocumento.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO SyTipodocumento.Actualizar : valores por defecto

		return syTipodocumento;
	}

	public BeanSyTipodocumento prepararAnular(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento syTipodocumento) {
		if (syTipodocumento == null)
			return syTipodocumento;
		if (syTipodocumento.getAuxFlgPreparadoBoolean())
			return syTipodocumento;
		syTipodocumento = prepararBasico(usuarioActual, syTipodocumento, false);
		syTipodocumento.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO SyTipodocumento.Anular : valores por defecto

		return syTipodocumento;
	}

	public BeanSyTipodocumento prepararEliminar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumento syTipodocumento) {
		if (syTipodocumento == null)
			return syTipodocumento;
		if (syTipodocumento.getAuxFlgPreparadoBoolean())
			return syTipodocumento;
		syTipodocumento.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO SyTipodocumento.Eliminar : valores por defecto

		return syTipodocumento;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual,
			BeanSyTipodocumento syTipodocumento) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
	
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syTipodocumento == null)
			lst.add(this.getMsjUsuarioError("sytipodocumento.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syTipodocumento.getPk() != null) {
			Set<ConstraintViolation<BeanSyTipodocumentoPk>> reglasPk = validator.validate(syTipodocumento.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyTipodocumento>> reglas = validator.validate(syTipodocumento);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}

		// TODO SyTipodocumento : validaciones comunes Insert/Actualizar

		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,
			BeanSyTipodocumento syTipodocumento) {
		syTipodocumento = prepararInsertar(usuarioActual, syTipodocumento);
		
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syTipodocumento);
		
		BeanSyTipodocumento obj = syTipodocumentoDao
				.obtenerPorId(new BeanSyTipodocumentoPk(syTipodocumento.getPk().getTipodocumentoid()));
		
		if(!UValidador.esNulo(obj)) {
			lst.add(this.getMsjUsuarioError("Ya existe un registro con estos Datos"));
		}

		
		if (!lst.isEmpty())
			return lst;

		// TODO SyTipodocumento.Insertar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual,
			BeanSyTipodocumento syTipodocumento) {
		syTipodocumento = prepararActualizar(usuarioActual, syTipodocumento);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syTipodocumento);
		if (!lst.isEmpty())
			return lst;

		// TODO SyTipodocumento.Actualizar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,
			BeanSyTipodocumento syTipodocumento) {
		syTipodocumento = prepararEliminar(usuarioActual, syTipodocumento);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO SyTipodocumento.Eliminar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoPk pk) {
		BeanSyTipodocumento bean = syTipodocumentoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, String ptipodocumentoid) {
		return coreEliminar(usuarioActual, new BeanSyTipodocumentoPk(ptipodocumentoid));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual,
			BeanSyTipodocumento syTipodocumento) {
		syTipodocumento = prepararAnular(usuarioActual, syTipodocumento);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO SyTipodocumento.Anular : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyTipodocumentoPk pk) {
		BeanSyTipodocumento bean = syTipodocumentoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String ptipodocumentoid) {
		return coreAnular(usuarioActual, new BeanSyTipodocumentoPk(ptipodocumentoid));
	}

	@Transactional
	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual, String accion, DtoComunSyTipodocumento dto)
			throws UException {
		BeanSyTipodocumento sytipodocumento = dto.obtenerBean();
		List<DominioMensajeUsuario> lst = core(usuarioActual, accion, sytipodocumento);
		return lst;
	}

	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual, String accion,
			BeanSyTipodocumento syTipodocumento) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, syTipodocumento);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, syTipodocumento);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			return coreAnular(usuarioActual, syTipodocumento);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			return coreEliminar(usuarioActual, syTipodocumento);
		return lst;
	}

}
