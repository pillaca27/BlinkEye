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
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyPreferencesDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyPreferences;
import net.royal.spring.sistema.dominio.BeanSyPreferencesPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyPreferences;

@Service (value = "BeanValidarSyPreferences")
public class SyPreferencesServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyPreferences";
	private static Logger logger = LogManager.getLogger(SyPreferencesServicioValidar.class);

	@Autowired
	private SyPreferencesDaoImpl syPreferencesDao;

	private BeanSyPreferences prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSyPreferences syPreferences, Boolean flgInsertar) throws Exception {
     syPreferences.setUltimousuario(usuarioActual.getUsuario());
     syPreferences.setUltimafechamodif(new Date());
		
		// TODO SyPreferences : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syPreferences;
	}

	public BeanSyPreferences prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSyPreferences syPreferences) throws Exception {
		if (syPreferences == null)
			return syPreferences;
		if (syPreferences.getAuxFlgPreparadoBoolean())
			return syPreferences;
		syPreferences = prepararBasico(usuarioActual,syPreferences, true);
		syPreferences.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyPreferences.Insertar : valores por defecto
		
		return syPreferences;
	}

	public BeanSyPreferences prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSyPreferences syPreferences) throws Exception {
		if (syPreferences == null)
			return syPreferences;
		if (syPreferences.getAuxFlgPreparadoBoolean())
			return syPreferences;
		syPreferences = prepararBasico(usuarioActual,syPreferences, false);
		syPreferences.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyPreferences.Actualizar : valores por defecto
		
		return syPreferences;
	}

	public BeanSyPreferences prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSyPreferences syPreferences) throws Exception {
		if (syPreferences == null)
			return syPreferences;
		if (syPreferences.getAuxFlgPreparadoBoolean())
			return syPreferences;
		syPreferences = prepararBasico(usuarioActual, syPreferences, false);
		syPreferences.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyPreferences.Anular : valores por defecto
		
		return syPreferences;
	}

	public BeanSyPreferences prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSyPreferences syPreferences) throws Exception {
		if (syPreferences == null)
			return syPreferences;
		if (syPreferences.getAuxFlgPreparadoBoolean())
			return syPreferences;
		syPreferences.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyPreferences.Eliminar : valores por defecto
		
		return syPreferences;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSyPreferences syPreferences) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syPreferences == null)
			lst.add(this.getMsjUsuarioError("sypreferences.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syPreferences.getPk() != null) {
			Set<ConstraintViolation<BeanSyPreferencesPk>> reglasPk = validator.validate(syPreferences.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyPreferences>> reglas = validator.validate(syPreferences);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyPreferences : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyPreferences syPreferences) throws Exception {
		if (syPreferences.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syPreferences.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syPreferences = prepararInsertar(usuarioActual, syPreferences);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syPreferences);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyPreferences.Insertar : validaciones
		BeanSyPreferences b1 = syPreferencesDao.obtenerPorId(syPreferences.getPk());
		if (b1!=null) {
			lst.add(this.getMsjUsuarioError("sypreferences.preference.duplicado"));			
		}
		
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyPreferences syPreferences) throws Exception {
		if (syPreferences.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syPreferences.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syPreferences = prepararActualizar(usuarioActual, syPreferences);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syPreferences);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyPreferences.Actualizar : validaciones
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyPreferences syPreferences) throws Exception {
		if (syPreferences.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syPreferences.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syPreferences = prepararEliminar(usuarioActual, syPreferences);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyPreferences.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyPreferencesPk pk) throws Exception {
		BeanSyPreferences bean = syPreferencesDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pusuario,String ppreference) throws Exception {
		return coreEliminar(usuarioActual,new BeanSyPreferencesPk( pusuario, ppreference));
	}


	public DtoComunSyPreferences core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunSyPreferences dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanSyPreferences syPreferences = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, syPreferences);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, syPreferences);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, syPreferences);
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
