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
import net.royal.spring.dt.dao.impl.AlumnosDaoImpl;

import net.royal.spring.dt.dominio.Alumnos;
import net.royal.spring.dt.dominio.AlumnosPk;
import net.royal.spring.dt.dominio.dto.DtoAlumnos;

@Service (value = "BeanValidarAlumnos")
public class AlumnosServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAlumnos";
	private static Logger logger = LogManager.getLogger(AlumnosServicioValidar.class);

	@Autowired
	private AlumnosDaoImpl alumnosDao;

	private Alumnos prepararBasico(SeguridadUsuarioActual usuarioActual,Alumnos alumnos, Boolean flgInsertar) throws Exception {
		
		// TODO Alumnos : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return alumnos;
	}

	public Alumnos prepararInsertar(SeguridadUsuarioActual usuarioActual,Alumnos alumnos) throws Exception {
		if (alumnos == null)
			return alumnos;
		if (alumnos.getAuxFlgPreparadoBoolean())
			return alumnos;
		alumnos = prepararBasico(usuarioActual,alumnos, true);
		alumnos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Alumnos.Insertar : valores por defecto
		
		return alumnos;
	}

	public Alumnos prepararActualizar(SeguridadUsuarioActual usuarioActual,Alumnos alumnos) throws Exception {
		if (alumnos == null)
			return alumnos;
		if (alumnos.getAuxFlgPreparadoBoolean())
			return alumnos;
		alumnos = prepararBasico(usuarioActual,alumnos, false);
		alumnos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Alumnos.Actualizar : valores por defecto
		
		return alumnos;
	}

	public Alumnos prepararAnular(SeguridadUsuarioActual usuarioActual,Alumnos alumnos) throws Exception {
		if (alumnos == null)
			return alumnos;
		if (alumnos.getAuxFlgPreparadoBoolean())
			return alumnos;
		alumnos = prepararBasico(usuarioActual, alumnos, false);
		alumnos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Alumnos.Anular : valores por defecto
		
		return alumnos;
	}

	public Alumnos prepararActivar(SeguridadUsuarioActual usuarioActual,Alumnos alumnos) throws Exception {
		if (alumnos == null)
			return alumnos;
		if (alumnos.getAuxFlgPreparadoBoolean())
			return alumnos;
		alumnos = prepararBasico(usuarioActual, alumnos, false);
		alumnos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Alumnos.Activar : valores por defecto
		
		return alumnos;
	}

	public Alumnos prepararEliminar(SeguridadUsuarioActual usuarioActual,Alumnos alumnos) throws Exception {
		if (alumnos == null)
			return alumnos;
		if (alumnos.getAuxFlgPreparadoBoolean())
			return alumnos;
		alumnos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Alumnos.Eliminar : valores por defecto
		
		return alumnos;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, Alumnos alumnos) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (alumnos == null)
			lst.add(this.getMsjUsuarioError("alumnos.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (alumnos.getPk() != null) {
			Set<ConstraintViolation<AlumnosPk>> reglasPk = validator.validate(alumnos.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Alumnos>> reglas = validator.validate(alumnos);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Alumnos : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,Alumnos alumnos) throws Exception {
		if (alumnos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		alumnos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		alumnos = prepararInsertar(usuarioActual, alumnos);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alumnos);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Alumnos.Insertar : validaciones
		Alumnos b1 = alumnosDao.obtenerPorId(alumnos.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, Alumnos alumnos) throws Exception {
		if (alumnos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		alumnos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		alumnos = prepararActualizar(usuarioActual, alumnos);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, alumnos);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Alumnos.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Alumnos alumnos) throws Exception {
		if (alumnos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		alumnos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		alumnos = prepararEliminar(usuarioActual, alumnos);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Alumnos.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,AlumnosPk pk) throws Exception {
		Alumnos bean = alumnosDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidAlumno) throws Exception {
		return coreEliminar(usuarioActual,new AlumnosPk( pidAlumno));
	}

	public DtoAlumnos core(SeguridadUsuarioActual usuarioActual,String accion,DtoAlumnos dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		Alumnos alumnos = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, alumnos);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, alumnos);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, alumnos);
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
