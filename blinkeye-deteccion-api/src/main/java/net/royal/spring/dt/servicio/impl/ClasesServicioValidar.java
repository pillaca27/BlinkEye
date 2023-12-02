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
import net.royal.spring.dt.dao.impl.ClasesDaoImpl;

import net.royal.spring.dt.dominio.Clases;
import net.royal.spring.dt.dominio.ClasesPk;
import net.royal.spring.dt.dominio.dto.DtoClases;

@Service (value = "BeanValidarClases")
public class ClasesServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarClases";
	private static Logger logger = LogManager.getLogger(ClasesServicioValidar.class);

	@Autowired
	private ClasesDaoImpl clasesDao;

	private Clases prepararBasico(SeguridadUsuarioActual usuarioActual,Clases clases, Boolean flgInsertar) throws Exception {
		
		// TODO Clases : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return clases;
	}

	public Clases prepararInsertar(SeguridadUsuarioActual usuarioActual,Clases clases) throws Exception {
		if (clases == null)
			return clases;
		if (clases.getAuxFlgPreparadoBoolean())
			return clases;
		clases = prepararBasico(usuarioActual,clases, true);
		clases.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Clases.Insertar : valores por defecto
		
		return clases;
	}

	public Clases prepararActualizar(SeguridadUsuarioActual usuarioActual,Clases clases) throws Exception {
		if (clases == null)
			return clases;
		if (clases.getAuxFlgPreparadoBoolean())
			return clases;
		clases = prepararBasico(usuarioActual,clases, false);
		clases.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Clases.Actualizar : valores por defecto
		
		return clases;
	}

	public Clases prepararAnular(SeguridadUsuarioActual usuarioActual,Clases clases) throws Exception {
		if (clases == null)
			return clases;
		if (clases.getAuxFlgPreparadoBoolean())
			return clases;
		clases = prepararBasico(usuarioActual, clases, false);
		clases.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Clases.Anular : valores por defecto
		
		return clases;
	}

	public Clases prepararActivar(SeguridadUsuarioActual usuarioActual,Clases clases) throws Exception {
		if (clases == null)
			return clases;
		if (clases.getAuxFlgPreparadoBoolean())
			return clases;
		clases = prepararBasico(usuarioActual, clases, false);
		clases.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Clases.Activar : valores por defecto
		
		return clases;
	}

	public Clases prepararEliminar(SeguridadUsuarioActual usuarioActual,Clases clases) throws Exception {
		if (clases == null)
			return clases;
		if (clases.getAuxFlgPreparadoBoolean())
			return clases;
		clases.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Clases.Eliminar : valores por defecto
		
		return clases;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, Clases clases) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (clases == null)
			lst.add(this.getMsjUsuarioError("clases.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (clases.getPk() != null) {
			Set<ConstraintViolation<ClasesPk>> reglasPk = validator.validate(clases.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Clases>> reglas = validator.validate(clases);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Clases : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,Clases clases) throws Exception {
		if (clases.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		clases.setAuxFlgValidadoBoolean(Boolean.TRUE);

		clases = prepararInsertar(usuarioActual, clases);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, clases);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Clases.Insertar : validaciones
		Clases b1 = clasesDao.obtenerPorId(clases.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, Clases clases) throws Exception {
		if (clases.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		clases.setAuxFlgValidadoBoolean(Boolean.TRUE);

		clases = prepararActualizar(usuarioActual, clases);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, clases);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Clases.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Clases clases) throws Exception {
		if (clases.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		clases.setAuxFlgValidadoBoolean(Boolean.TRUE);

		clases = prepararEliminar(usuarioActual, clases);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Clases.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,ClasesPk pk) throws Exception {
		Clases bean = clasesDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidClase) throws Exception {
		return coreEliminar(usuarioActual,new ClasesPk( pidClase));
	}

	public DtoClases core(SeguridadUsuarioActual usuarioActual,String accion,DtoClases dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		Clases clases = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, clases);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, clases);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, clases);
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
