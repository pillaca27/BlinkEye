package net.royal.spring.logistica.servicio.validar;

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
import net.royal.spring.logistica.dao.impl.WhCommodityDaoImpl;

import net.royal.spring.logistica.dominio.BeanWhCommodity;
import net.royal.spring.logistica.dominio.BeanWhCommodityPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommodity;

@Service (value = "BeanValidarWhCommodity")
public class WhCommodityServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarWhCommodity";
	private static Logger logger = LogManager.getLogger(WhCommodityServicioValidar.class);

	@Autowired
	private WhCommodityDaoImpl whCommodityDao;

	private BeanWhCommodity prepararBasico(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommodity, Boolean flgInsertar) throws Exception {
     whCommodity.setUltimousuario(usuarioActual.getUsuario());
     whCommodity.setUltimafechamodif(new Date());
		
		// TODO WhCommodity : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return whCommodity;
	}

	public BeanWhCommodity prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommodity) throws Exception {
		if (whCommodity == null)
			return whCommodity;
		if (whCommodity.getAuxFlgPreparadoBoolean())
			return whCommodity;
		whCommodity = prepararBasico(usuarioActual,whCommodity, true);
		whCommodity.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommodity.Insertar : valores por defecto
		
		return whCommodity;
	}

	public BeanWhCommodity prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommodity) throws Exception {
		if (whCommodity == null)
			return whCommodity;
		if (whCommodity.getAuxFlgPreparadoBoolean())
			return whCommodity;
		whCommodity = prepararBasico(usuarioActual,whCommodity, false);
		whCommodity.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommodity.Actualizar : valores por defecto
		
		return whCommodity;
	}

	public BeanWhCommodity prepararAnular(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommodity) throws Exception {
		if (whCommodity == null)
			return whCommodity;
		if (whCommodity.getAuxFlgPreparadoBoolean())
			return whCommodity;
		whCommodity = prepararBasico(usuarioActual, whCommodity, false);
		whCommodity.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommodity.Anular : valores por defecto
		
		return whCommodity;
	}

	public BeanWhCommodity prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommodity) throws Exception {
		if (whCommodity == null)
			return whCommodity;
		if (whCommodity.getAuxFlgPreparadoBoolean())
			return whCommodity;
		whCommodity.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommodity.Eliminar : valores por defecto
		
		return whCommodity;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanWhCommodity whCommodity) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (whCommodity == null)
			lst.add(this.getMsjUsuarioError("whcommodity.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (whCommodity.getPk() != null) {
			Set<ConstraintViolation<BeanWhCommodityPk>> reglasPk = validator.validate(whCommodity.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanWhCommodity>> reglas = validator.validate(whCommodity);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO WhCommodity : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommodity) throws Exception {
		if (whCommodity.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommodity.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommodity = prepararInsertar(usuarioActual, whCommodity);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whCommodity);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhCommodity.Insertar : validaciones
		BeanWhCommodity b1 = whCommodityDao.obtenerPorId(whCommodity.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhCommodity whCommodity) throws Exception {
		if (whCommodity.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommodity.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommodity = prepararActualizar(usuarioActual, whCommodity);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whCommodity);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhCommodity.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommodity) throws Exception {
		if (whCommodity.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommodity.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommodity = prepararEliminar(usuarioActual, whCommodity);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhCommodity.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhCommodityPk pk) throws Exception {
		BeanWhCommodity bean = whCommodityDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcommodity01) throws Exception {
		return coreEliminar(usuarioActual,new BeanWhCommodityPk( pcommodity01));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhCommodity whCommodity) throws Exception {
		if (whCommodity.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommodity.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommodity = prepararAnular(usuarioActual, whCommodity);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhCommodity.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhCommodityPk pk) throws Exception {
		BeanWhCommodity bean = whCommodityDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pcommodity01) throws Exception {
		return coreAnular(usuarioActual,new BeanWhCommodityPk( pcommodity01));
	}

	public DtoComunWhCommodity core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunWhCommodity dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanWhCommodity whCommodity = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, whCommodity);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, whCommodity);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, whCommodity);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, whCommodity);
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
