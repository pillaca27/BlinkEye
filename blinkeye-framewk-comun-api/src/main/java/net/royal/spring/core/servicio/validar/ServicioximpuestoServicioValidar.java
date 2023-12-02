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
import net.royal.spring.core.dao.impl.ServicioximpuestoDaoImpl;

import net.royal.spring.core.dominio.BeanServicioximpuesto;
import net.royal.spring.core.dominio.BeanServicioximpuestoPk;
import net.royal.spring.core.dominio.dto.DtoComunServicioximpuesto;

@Service (value = "BeanValidarServicioximpuesto")
public class ServicioximpuestoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarServicioximpuesto";
	private static Logger logger = LogManager.getLogger(ServicioximpuestoServicioValidar.class);

	@Autowired
	private ServicioximpuestoDaoImpl servicioximpuestoDao;

	private BeanServicioximpuesto prepararBasico(SeguridadUsuarioActual usuarioActual,BeanServicioximpuesto servicioximpuesto, Boolean flgInsertar) {
     servicioximpuesto.setUltimousuario(usuarioActual.getUsuario());
     servicioximpuesto.setUltimafechamodif(new Date());
		
		// TODO Servicioximpuesto : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return servicioximpuesto;
	}

	public BeanServicioximpuesto prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanServicioximpuesto servicioximpuesto) {
		if (servicioximpuesto == null)
			return servicioximpuesto;
		if (servicioximpuesto.getAuxFlgPreparadoBoolean())
			return servicioximpuesto;
		servicioximpuesto = prepararBasico(usuarioActual,servicioximpuesto, true);
		servicioximpuesto.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Servicioximpuesto.Insertar : valores por defecto
		
		return servicioximpuesto;
	}

	public BeanServicioximpuesto prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanServicioximpuesto servicioximpuesto) {
		if (servicioximpuesto == null)
			return servicioximpuesto;
		if (servicioximpuesto.getAuxFlgPreparadoBoolean())
			return servicioximpuesto;
		servicioximpuesto = prepararBasico(usuarioActual,servicioximpuesto, false);
		servicioximpuesto.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Servicioximpuesto.Actualizar : valores por defecto
		
		return servicioximpuesto;
	}

	public BeanServicioximpuesto prepararAnular(SeguridadUsuarioActual usuarioActual,BeanServicioximpuesto servicioximpuesto) {
		if (servicioximpuesto == null)
			return servicioximpuesto;
		if (servicioximpuesto.getAuxFlgPreparadoBoolean())
			return servicioximpuesto;
		servicioximpuesto = prepararBasico(usuarioActual, servicioximpuesto, false);
		servicioximpuesto.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Servicioximpuesto.Anular : valores por defecto
		
		return servicioximpuesto;
	}

	public BeanServicioximpuesto prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanServicioximpuesto servicioximpuesto) {
		if (servicioximpuesto == null)
			return servicioximpuesto;
		if (servicioximpuesto.getAuxFlgPreparadoBoolean())
			return servicioximpuesto;
		servicioximpuesto.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Servicioximpuesto.Eliminar : valores por defecto
		
		return servicioximpuesto;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanServicioximpuesto servicioximpuesto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (servicioximpuesto == null)
			lst.add(this.getMsjUsuarioError("servicioximpuesto.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (servicioximpuesto.getPk() != null) {
			Set<ConstraintViolation<BeanServicioximpuestoPk>> reglasPk = validator.validate(servicioximpuesto.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanServicioximpuesto>> reglas = validator.validate(servicioximpuesto);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Servicioximpuesto : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanServicioximpuesto servicioximpuesto) {
		if (servicioximpuesto.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		servicioximpuesto.setAuxFlgValidadoBoolean(Boolean.TRUE);

		servicioximpuesto = prepararInsertar(usuarioActual, servicioximpuesto);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, servicioximpuesto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Servicioximpuesto.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanServicioximpuesto servicioximpuesto) {
		if (servicioximpuesto.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		servicioximpuesto.setAuxFlgValidadoBoolean(Boolean.TRUE);

		servicioximpuesto = prepararActualizar(usuarioActual, servicioximpuesto);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, servicioximpuesto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Servicioximpuesto.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanServicioximpuesto servicioximpuesto) {
		if (servicioximpuesto.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		servicioximpuesto.setAuxFlgValidadoBoolean(Boolean.TRUE);

		servicioximpuesto = prepararEliminar(usuarioActual, servicioximpuesto);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Servicioximpuesto.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanServicioximpuestoPk pk) {
		BeanServicioximpuesto bean = servicioximpuestoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String ptiposervicio,String pimpuesto) {
		return coreEliminar(usuarioActual,new BeanServicioximpuestoPk( ptiposervicio, pimpuesto));
	}


	public DtoComunServicioximpuesto core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunServicioximpuesto dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanServicioximpuesto servicioximpuesto = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, servicioximpuesto);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, servicioximpuesto);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, servicioximpuesto);
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
