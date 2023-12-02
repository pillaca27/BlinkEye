package net.royal.spring.core.servicio.validar;

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
import net.royal.spring.core.dao.impl.ZonapostalDaoImpl;

import net.royal.spring.core.dominio.BeanZonapostal;
import net.royal.spring.core.dominio.BeanZonapostalPk;
import net.royal.spring.core.dominio.dto.DtoComunZonapostal;

@Service (value = "BeanValidarZonapostal")
public class ZonapostalServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarZonapostal";
	private static Logger logger = LogManager.getLogger(ZonapostalServicioValidar.class);

	@Autowired
	private ZonapostalDaoImpl zonapostalDao;

	private BeanZonapostal prepararBasico(SeguridadUsuarioActual usuarioActual,BeanZonapostal zonapostal, Boolean flgInsertar) {
     zonapostal.setUltimousuario(usuarioActual.getUsuario());
     zonapostal.setUltimafechamodif(new Date());
		
		// TODO Zonapostal : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return zonapostal;
	}

	public BeanZonapostal prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanZonapostal zonapostal) {
		if (zonapostal == null)
			return zonapostal;
		if (zonapostal.getAuxFlgPreparadoBoolean())
			return zonapostal;
		zonapostal = prepararBasico(usuarioActual,zonapostal, true);
		zonapostal.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Zonapostal.Insertar : valores por defecto
		
		return zonapostal;
	}

	public BeanZonapostal prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanZonapostal zonapostal) {
		if (zonapostal == null)
			return zonapostal;
		if (zonapostal.getAuxFlgPreparadoBoolean())
			return zonapostal;
		zonapostal = prepararBasico(usuarioActual,zonapostal, false);
		zonapostal.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Zonapostal.Actualizar : valores por defecto
		
		return zonapostal;
	}

	public BeanZonapostal prepararAnular(SeguridadUsuarioActual usuarioActual,BeanZonapostal zonapostal) {
		if (zonapostal == null)
			return zonapostal;
		if (zonapostal.getAuxFlgPreparadoBoolean())
			return zonapostal;
		zonapostal = prepararBasico(usuarioActual, zonapostal, false);
		zonapostal.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Zonapostal.Anular : valores por defecto
		
		return zonapostal;
	}

	public BeanZonapostal prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanZonapostal zonapostal) {
		if (zonapostal == null)
			return zonapostal;
		if (zonapostal.getAuxFlgPreparadoBoolean())
			return zonapostal;
		zonapostal.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Zonapostal.Eliminar : valores por defecto
		
		return zonapostal;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanZonapostal zonapostal) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (zonapostal == null)
			lst.add(this.getMsjUsuarioError("zonapostal.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

		Set<ConstraintViolation<BeanZonapostal>> reglas = validator.validate(zonapostal);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Zonapostal : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanZonapostal zonapostal) {
		if (zonapostal.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		zonapostal.setAuxFlgValidadoBoolean(Boolean.TRUE);

		zonapostal = prepararInsertar(usuarioActual, zonapostal);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, zonapostal);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Zonapostal.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanZonapostal zonapostal) {
		if (zonapostal.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		zonapostal.setAuxFlgValidadoBoolean(Boolean.TRUE);

		zonapostal = prepararActualizar(usuarioActual, zonapostal);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, zonapostal);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Zonapostal.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanZonapostal zonapostal) {
		if (zonapostal.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		zonapostal.setAuxFlgValidadoBoolean(Boolean.TRUE);

		zonapostal = prepararEliminar(usuarioActual, zonapostal);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Zonapostal.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanZonapostalPk pk) {
		BeanZonapostal bean = zonapostalDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}


	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanZonapostal zonapostal) {
		if (zonapostal.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		zonapostal.setAuxFlgValidadoBoolean(Boolean.TRUE);

		zonapostal = prepararAnular(usuarioActual, zonapostal);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Zonapostal.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanZonapostalPk pk) {
		BeanZonapostal bean = zonapostalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}


	public DtoComunZonapostal core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunZonapostal dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanZonapostal zonapostal = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, zonapostal);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, zonapostal);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, zonapostal);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, zonapostal);
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
