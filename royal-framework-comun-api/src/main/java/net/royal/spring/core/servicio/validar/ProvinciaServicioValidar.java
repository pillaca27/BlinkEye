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
import net.royal.spring.core.dao.impl.ProvinciaDaoImpl;

import net.royal.spring.core.dominio.BeanProvincia;
import net.royal.spring.core.dominio.BeanProvinciaPk;
import net.royal.spring.core.dominio.dto.DtoComunProvincia;

@Service (value = "BeanValidarProvincia")
public class ProvinciaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarProvincia";
	private static Logger logger = LogManager.getLogger(ProvinciaServicioValidar.class);

	@Autowired
	private ProvinciaDaoImpl provinciaDao;

	private BeanProvincia prepararBasico(SeguridadUsuarioActual usuarioActual,BeanProvincia provincia, Boolean flgInsertar) {
     provincia.setUltimousuario(usuarioActual.getUsuario());
     provincia.setUltimafechamodif(new Date());
		
		// TODO Provincia : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return provincia;
	}

	public BeanProvincia prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanProvincia provincia) {
		if (provincia == null)
			return provincia;
		if (provincia.getAuxFlgPreparadoBoolean())
			return provincia;
		provincia = prepararBasico(usuarioActual,provincia, true);
		provincia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Provincia.Insertar : valores por defecto
		
		return provincia;
	}

	public BeanProvincia prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanProvincia provincia) {
		if (provincia == null)
			return provincia;
		if (provincia.getAuxFlgPreparadoBoolean())
			return provincia;
		provincia = prepararBasico(usuarioActual,provincia, false);
		provincia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Provincia.Actualizar : valores por defecto
		
		return provincia;
	}

	public BeanProvincia prepararAnular(SeguridadUsuarioActual usuarioActual,BeanProvincia provincia) {
		if (provincia == null)
			return provincia;
		if (provincia.getAuxFlgPreparadoBoolean())
			return provincia;
		provincia = prepararBasico(usuarioActual, provincia, false);
		provincia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Provincia.Anular : valores por defecto
		
		return provincia;
	}

	public BeanProvincia prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanProvincia provincia) {
		if (provincia == null)
			return provincia;
		if (provincia.getAuxFlgPreparadoBoolean())
			return provincia;
		provincia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Provincia.Eliminar : valores por defecto
		
		return provincia;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanProvincia provincia) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (provincia == null)
			lst.add(this.getMsjUsuarioError("provincia.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<BeanProvincia>> reglas = validator.validate(provincia);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Provincia : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanProvincia provincia) {
		if (provincia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		provincia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		provincia = prepararInsertar(usuarioActual, provincia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, provincia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Provincia.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanProvincia provincia) {
		if (provincia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		provincia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		provincia = prepararActualizar(usuarioActual, provincia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, provincia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Provincia.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanProvincia provincia) {
		if (provincia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		provincia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		provincia = prepararEliminar(usuarioActual, provincia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Provincia.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanProvinciaPk pk) {
		BeanProvincia bean = provinciaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanProvincia provincia) {
		if (provincia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		provincia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		provincia = prepararAnular(usuarioActual, provincia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Provincia.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanProvinciaPk pk) {
		BeanProvincia bean = provinciaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}


	public DtoComunProvincia core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunProvincia dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanProvincia provincia = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, provincia);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, provincia);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, provincia);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, provincia);
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
