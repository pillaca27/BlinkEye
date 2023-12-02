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
import net.royal.spring.core.dao.impl.ZonadespachoDaoImpl;

import net.royal.spring.core.dominio.BeanZonadespacho;
import net.royal.spring.core.dominio.BeanZonadespachoPk;
import net.royal.spring.core.dominio.dto.DtoComunZonadespacho;

@Service (value = "BeanValidarZonadespacho")
public class ZonadespachoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarZonadespacho";
	private static Logger logger = LogManager.getLogger(ZonadespachoServicioValidar.class);

	@Autowired
	private ZonadespachoDaoImpl zonadespachoDao;

	private BeanZonadespacho prepararBasico(SeguridadUsuarioActual usuarioActual,BeanZonadespacho zonadespacho, Boolean flgInsertar) {
     zonadespacho.setUltimousuario(usuarioActual.getUsuario());
     zonadespacho.setUltimafechamodif(new Date());
		
		// TODO Zonadespacho : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return zonadespacho;
	}

	public BeanZonadespacho prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanZonadespacho zonadespacho) {
		if (zonadespacho == null)
			return zonadespacho;
		if (zonadespacho.getAuxFlgPreparadoBoolean())
			return zonadespacho;
		zonadespacho = prepararBasico(usuarioActual,zonadespacho, true);
		zonadespacho.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Zonadespacho.Insertar : valores por defecto
		
		return zonadespacho;
	}

	public BeanZonadespacho prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanZonadespacho zonadespacho) {
		if (zonadespacho == null)
			return zonadespacho;
		if (zonadespacho.getAuxFlgPreparadoBoolean())
			return zonadespacho;
		zonadespacho = prepararBasico(usuarioActual,zonadespacho, false);
		zonadespacho.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Zonadespacho.Actualizar : valores por defecto
		
		return zonadespacho;
	}

	public BeanZonadespacho prepararAnular(SeguridadUsuarioActual usuarioActual,BeanZonadespacho zonadespacho) {
		if (zonadespacho == null)
			return zonadespacho;
		if (zonadespacho.getAuxFlgPreparadoBoolean())
			return zonadespacho;
		zonadespacho = prepararBasico(usuarioActual, zonadespacho, false);
		zonadespacho.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Zonadespacho.Anular : valores por defecto
		
		return zonadespacho;
	}

	public BeanZonadespacho prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanZonadespacho zonadespacho) {
		if (zonadespacho == null)
			return zonadespacho;
		if (zonadespacho.getAuxFlgPreparadoBoolean())
			return zonadespacho;
		zonadespacho.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Zonadespacho.Eliminar : valores por defecto
		
		return zonadespacho;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanZonadespacho zonadespacho) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (zonadespacho == null)
			lst.add(this.getMsjUsuarioError("zonadespacho.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (zonadespacho.getPk() != null) {
			Set<ConstraintViolation<BeanZonadespachoPk>> reglasPk = validator.validate(zonadespacho.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanZonadespacho>> reglas = validator.validate(zonadespacho);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Zonadespacho : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanZonadespacho zonadespacho) {
		if (zonadespacho.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		zonadespacho.setAuxFlgValidadoBoolean(Boolean.TRUE);

		zonadespacho = prepararInsertar(usuarioActual, zonadespacho);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, zonadespacho);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Zonadespacho.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanZonadespacho zonadespacho) {
		if (zonadespacho.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		zonadespacho.setAuxFlgValidadoBoolean(Boolean.TRUE);

		zonadespacho = prepararActualizar(usuarioActual, zonadespacho);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, zonadespacho);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Zonadespacho.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanZonadespacho zonadespacho) {
		if (zonadespacho.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		zonadespacho.setAuxFlgValidadoBoolean(Boolean.TRUE);

		zonadespacho = prepararEliminar(usuarioActual, zonadespacho);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Zonadespacho.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanZonadespachoPk pk) {
		BeanZonadespacho bean = zonadespachoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pzonadespacho) {
		return coreEliminar(usuarioActual,new BeanZonadespachoPk( pzonadespacho));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanZonadespacho zonadespacho) {
		if (zonadespacho.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		zonadespacho.setAuxFlgValidadoBoolean(Boolean.TRUE);

		zonadespacho = prepararAnular(usuarioActual, zonadespacho);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Zonadespacho.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanZonadespachoPk pk) {
		BeanZonadespacho bean = zonadespachoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pzonadespacho) {
		return coreAnular(usuarioActual,new BeanZonadespachoPk( pzonadespacho));
	}

	public DtoComunZonadespacho core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunZonadespacho dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanZonadespacho zonadespacho = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, zonadespacho);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, zonadespacho);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, zonadespacho);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, zonadespacho);
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
