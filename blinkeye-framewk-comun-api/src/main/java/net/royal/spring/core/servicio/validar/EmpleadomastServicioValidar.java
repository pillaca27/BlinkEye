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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.EmpleadomastDaoImpl;
import net.royal.spring.core.dominio.BeanEmpleadomast;
import net.royal.spring.core.dominio.BeanEmpleadomastPk;
import net.royal.spring.core.dominio.dto.DtoComunEmpleadomast;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarEmpleadomast")
public class EmpleadomastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarEmpleadomast";
	private static Logger logger = LogManager.getLogger(EmpleadomastServicioValidar.class);

	@Autowired
	private EmpleadomastDaoImpl empleadomastDao;

	private BeanEmpleadomast prepararBasico(SeguridadUsuarioActual usuarioActual,BeanEmpleadomast empleadomast, Boolean flgInsertar) {
     empleadomast.setUltimousuario(usuarioActual.getUsuario());
     empleadomast.setUltimafechamodif(new Date());
		
		// TODO Empleadomast : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return empleadomast;
	}

	public BeanEmpleadomast prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanEmpleadomast empleadomast) {
		if (empleadomast == null)
			return empleadomast;
		if (empleadomast.getAuxFlgPreparadoBoolean())
			return empleadomast;
		empleadomast = prepararBasico(usuarioActual,empleadomast, true);
		empleadomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Empleadomast.Insertar : valores por defecto
		
		return empleadomast;
	}

	public BeanEmpleadomast prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanEmpleadomast empleadomast) {
		if (empleadomast == null)
			return empleadomast;
		if (empleadomast.getAuxFlgPreparadoBoolean())
			return empleadomast;
		empleadomast = prepararBasico(usuarioActual,empleadomast, false);
		empleadomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Empleadomast.Actualizar : valores por defecto
		
		return empleadomast;
	}

	public BeanEmpleadomast prepararAnular(SeguridadUsuarioActual usuarioActual,BeanEmpleadomast empleadomast) {
		if (empleadomast == null)
			return empleadomast;
		if (empleadomast.getAuxFlgPreparadoBoolean())
			return empleadomast;
		empleadomast = prepararBasico(usuarioActual, empleadomast, false);
		empleadomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Empleadomast.Anular : valores por defecto
		
		return empleadomast;
	}

	public BeanEmpleadomast prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanEmpleadomast empleadomast) {
		if (empleadomast == null)
			return empleadomast;
		if (empleadomast.getAuxFlgPreparadoBoolean())
			return empleadomast;
		empleadomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Empleadomast.Eliminar : valores por defecto
		
		return empleadomast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanEmpleadomast empleadomast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (empleadomast == null)
			lst.add(this.getMsjUsuarioError("empleadomast.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (empleadomast.getPk() != null) {
			Set<ConstraintViolation<BeanEmpleadomastPk>> reglasPk = validator.validate(empleadomast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanEmpleadomast>> reglas = validator.validate(empleadomast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
	
		if(UString.esNuloVacio(empleadomast.getUnidadnegocioasignada())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_EMP_UNIDAD_NEGOCIO));
		}
		if(UString.esNuloVacio(empleadomast.getCentrocostos())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_EMP_CENTRO_COSTOS));
		}
		if(UString.esNuloVacio(empleadomast.getDeptoorganizacion())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_EMP_DEPARTAMENTO));
		}
		// TODO Empleadomast : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanEmpleadomast empleadomast) {
		if (empleadomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		empleadomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		empleadomast = prepararInsertar(usuarioActual, empleadomast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, empleadomast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Empleadomast.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanEmpleadomast empleadomast) {
		if (empleadomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		empleadomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		empleadomast = prepararActualizar(usuarioActual, empleadomast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, empleadomast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Empleadomast.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanEmpleadomast empleadomast) {
		if (empleadomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		empleadomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		List<DtoTabla> dto = empleadomastDao.obtenerDtoDetalle(empleadomast.getPk());
		if(dto.size() > 0) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_EMP_ELIMINAR));
		}
		
		if (!lstRes.isEmpty())
			return lstRes;
		
		empleadomast = prepararEliminar(usuarioActual, empleadomast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Empleadomast.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanEmpleadomastPk pk) {
		BeanEmpleadomast bean = empleadomastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pempleado,String pcompaniasocio) {
		return coreEliminar(usuarioActual,new BeanEmpleadomastPk( pempleado, pcompaniasocio));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanEmpleadomast empleadomast) {
		if (empleadomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		empleadomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		empleadomast = prepararAnular(usuarioActual, empleadomast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Empleadomast.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanEmpleadomastPk pk) {
		BeanEmpleadomast bean = empleadomastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pempleado,String pcompaniasocio) {
		return coreAnular(usuarioActual,new BeanEmpleadomastPk( pempleado, pcompaniasocio));
	}

	public DtoComunEmpleadomast core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunEmpleadomast dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanEmpleadomast empleadomast = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, empleadomast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, empleadomast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, empleadomast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, empleadomast);
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
