package net.royal.spring.dt.servicio.impl;

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
import net.royal.spring.dt.dao.impl.MicrosuenosDaoImpl;

import net.royal.spring.dt.dominio.Microsuenos;
import net.royal.spring.dt.dominio.MicrosuenosPk;
import net.royal.spring.dt.dominio.dto.DtoMicrosuenos;

@Service (value = "BeanValidarMicrosuenos")
public class MicrosuenosServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarMicrosuenos";
	private static Logger logger = LogManager.getLogger(MicrosuenosServicioValidar.class);

	@Autowired
	private MicrosuenosDaoImpl microsuenosDao;

	private Microsuenos prepararBasico(SeguridadUsuarioActual usuarioActual,Microsuenos microsuenos, Boolean flgInsertar) throws Exception {
		
		// TODO Microsuenos : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return microsuenos;
	}

	public Microsuenos prepararInsertar(SeguridadUsuarioActual usuarioActual,Microsuenos microsuenos) throws Exception {
		if (microsuenos == null)
			return microsuenos;
		if (microsuenos.getAuxFlgPreparadoBoolean())
			return microsuenos;
		microsuenos = prepararBasico(usuarioActual,microsuenos, true);
		microsuenos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Microsuenos.Insertar : valores por defecto
		
		return microsuenos;
	}

	public Microsuenos prepararActualizar(SeguridadUsuarioActual usuarioActual,Microsuenos microsuenos) throws Exception {
		if (microsuenos == null)
			return microsuenos;
		if (microsuenos.getAuxFlgPreparadoBoolean())
			return microsuenos;
		microsuenos = prepararBasico(usuarioActual,microsuenos, false);
		microsuenos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Microsuenos.Actualizar : valores por defecto
		
		return microsuenos;
	}

	public Microsuenos prepararAnular(SeguridadUsuarioActual usuarioActual,Microsuenos microsuenos) throws Exception {
		if (microsuenos == null)
			return microsuenos;
		if (microsuenos.getAuxFlgPreparadoBoolean())
			return microsuenos;
		microsuenos = prepararBasico(usuarioActual, microsuenos, false);
		microsuenos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Microsuenos.Anular : valores por defecto
		
		return microsuenos;
	}

	public Microsuenos prepararActivar(SeguridadUsuarioActual usuarioActual,Microsuenos microsuenos) throws Exception {
		if (microsuenos == null)
			return microsuenos;
		if (microsuenos.getAuxFlgPreparadoBoolean())
			return microsuenos;
		microsuenos = prepararBasico(usuarioActual, microsuenos, false);
		microsuenos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Microsuenos.Activar : valores por defecto
		
		return microsuenos;
	}

	public Microsuenos prepararEliminar(SeguridadUsuarioActual usuarioActual,Microsuenos microsuenos) throws Exception {
		if (microsuenos == null)
			return microsuenos;
		if (microsuenos.getAuxFlgPreparadoBoolean())
			return microsuenos;
		microsuenos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Microsuenos.Eliminar : valores por defecto
		
		return microsuenos;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, Microsuenos microsuenos) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (microsuenos == null)
			lst.add(this.getMsjUsuarioError("microsuenos.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (microsuenos.getPk() != null) {
			Set<ConstraintViolation<MicrosuenosPk>> reglasPk = validator.validate(microsuenos.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Microsuenos>> reglas = validator.validate(microsuenos);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Microsuenos : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,Microsuenos microsuenos) throws Exception {
		if (microsuenos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		microsuenos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		microsuenos = prepararInsertar(usuarioActual, microsuenos);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, microsuenos);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Microsuenos.Insertar : validaciones
		Microsuenos b1 = microsuenosDao.obtenerPorId(microsuenos.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, Microsuenos microsuenos) throws Exception {
		if (microsuenos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		microsuenos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		microsuenos = prepararActualizar(usuarioActual, microsuenos);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, microsuenos);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Microsuenos.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Microsuenos microsuenos) throws Exception {
		if (microsuenos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		microsuenos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		microsuenos = prepararEliminar(usuarioActual, microsuenos);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Microsuenos.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,MicrosuenosPk pk) throws Exception {
		Microsuenos bean = microsuenosDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidMicrosueno) throws Exception {
		return coreEliminar(usuarioActual,new MicrosuenosPk( pidMicrosueno));
	}

	public DtoMicrosuenos core(SeguridadUsuarioActual usuarioActual,String accion,DtoMicrosuenos dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		Microsuenos microsuenos = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, microsuenos);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, microsuenos);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, microsuenos);
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
