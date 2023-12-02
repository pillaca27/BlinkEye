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
import net.royal.spring.dt.dao.impl.DocentesDaoImpl;

import net.royal.spring.dt.dominio.Docentes;
import net.royal.spring.dt.dominio.DocentesPk;
import net.royal.spring.dt.dominio.dto.DtoDocentes;

@Service (value = "BeanValidarDocentes")
public class DocentesServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarDocentes";
	private static Logger logger = LogManager.getLogger(DocentesServicioValidar.class);

	@Autowired
	private DocentesDaoImpl docentesDao;

	private Docentes prepararBasico(SeguridadUsuarioActual usuarioActual,Docentes docentes, Boolean flgInsertar) throws Exception {
		
		// TODO Docentes : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return docentes;
	}

	public Docentes prepararInsertar(SeguridadUsuarioActual usuarioActual,Docentes docentes) throws Exception {
		if (docentes == null)
			return docentes;
		if (docentes.getAuxFlgPreparadoBoolean())
			return docentes;
		docentes = prepararBasico(usuarioActual,docentes, true);
		docentes.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Docentes.Insertar : valores por defecto
		
		return docentes;
	}

	public Docentes prepararActualizar(SeguridadUsuarioActual usuarioActual,Docentes docentes) throws Exception {
		if (docentes == null)
			return docentes;
		if (docentes.getAuxFlgPreparadoBoolean())
			return docentes;
		docentes = prepararBasico(usuarioActual,docentes, false);
		docentes.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Docentes.Actualizar : valores por defecto
		
		return docentes;
	}

	public Docentes prepararAnular(SeguridadUsuarioActual usuarioActual,Docentes docentes) throws Exception {
		if (docentes == null)
			return docentes;
		if (docentes.getAuxFlgPreparadoBoolean())
			return docentes;
		docentes = prepararBasico(usuarioActual, docentes, false);
		docentes.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Docentes.Anular : valores por defecto
		
		return docentes;
	}

	public Docentes prepararActivar(SeguridadUsuarioActual usuarioActual,Docentes docentes) throws Exception {
		if (docentes == null)
			return docentes;
		if (docentes.getAuxFlgPreparadoBoolean())
			return docentes;
		docentes = prepararBasico(usuarioActual, docentes, false);
		docentes.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Docentes.Activar : valores por defecto
		
		return docentes;
	}

	public Docentes prepararEliminar(SeguridadUsuarioActual usuarioActual,Docentes docentes) throws Exception {
		if (docentes == null)
			return docentes;
		if (docentes.getAuxFlgPreparadoBoolean())
			return docentes;
		docentes.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Docentes.Eliminar : valores por defecto
		
		return docentes;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, Docentes docentes) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (docentes == null)
			lst.add(this.getMsjUsuarioError("docentes.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (docentes.getPk() != null) {
			Set<ConstraintViolation<DocentesPk>> reglasPk = validator.validate(docentes.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Docentes>> reglas = validator.validate(docentes);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Docentes : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,Docentes docentes) throws Exception {
		if (docentes.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		docentes.setAuxFlgValidadoBoolean(Boolean.TRUE);

		docentes = prepararInsertar(usuarioActual, docentes);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, docentes);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Docentes.Insertar : validaciones
		Docentes b1 = docentesDao.obtenerPorId(docentes.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, Docentes docentes) throws Exception {
		if (docentes.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		docentes.setAuxFlgValidadoBoolean(Boolean.TRUE);

		docentes = prepararActualizar(usuarioActual, docentes);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, docentes);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Docentes.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Docentes docentes) throws Exception {
		if (docentes.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		docentes.setAuxFlgValidadoBoolean(Boolean.TRUE);

		docentes = prepararEliminar(usuarioActual, docentes);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Docentes.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,DocentesPk pk) throws Exception {
		Docentes bean = docentesDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pidDocente) throws Exception {
		return coreEliminar(usuarioActual,new DocentesPk( pidDocente));
	}

	public DtoDocentes core(SeguridadUsuarioActual usuarioActual,String accion,DtoDocentes dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		Docentes docentes = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, docentes);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, docentes);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, docentes);
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
