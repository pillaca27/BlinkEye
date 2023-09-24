package net.royal.spring.ac.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
import net.royal.spring.ac.dao.impl.AcSucursalgrupomayorDaoImpl;

import net.royal.spring.ac.dominio.BeanAcSucursalgrupomayor;
import net.royal.spring.ac.dominio.BeanAcSucursalgrupomayorPk;
import net.royal.spring.ac.dominio.dto.DtoComunAcSucursalgrupomayor;

@Service (value = "BeanValidarAcSucursalgrupomayor")
public class AcSucursalgrupomayorServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAcSucursalgrupomayor";
	private static Logger logger = LogManager.getLogger(AcSucursalgrupomayorServicioValidar.class);

	@Autowired
	private AcSucursalgrupomayorDaoImpl acSucursalgrupomayorDao;

	private BeanAcSucursalgrupomayor prepararBasico(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayor acSucursalgrupomayor, Boolean flgInsertar) throws Exception {
     acSucursalgrupomayor.setUltimousuario(usuarioActual.getUsuario());
     acSucursalgrupomayor.setUltimafechamodif(new Date());
		
		// TODO AcSucursalgrupomayor : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return acSucursalgrupomayor;
	}

	public BeanAcSucursalgrupomayor prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor == null)
			return acSucursalgrupomayor;
		if (acSucursalgrupomayor.getAuxFlgPreparadoBoolean())
			return acSucursalgrupomayor;
		acSucursalgrupomayor = prepararBasico(usuarioActual,acSucursalgrupomayor, true);
		acSucursalgrupomayor.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		acSucursalgrupomayor.setUuid(UUID.randomUUID().toString());
		
		// TODO AcSucursalgrupomayor.Insertar : valores por defecto
		
		return acSucursalgrupomayor;
	}

	public BeanAcSucursalgrupomayor prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor == null)
			return acSucursalgrupomayor;
		if (acSucursalgrupomayor.getAuxFlgPreparadoBoolean())
			return acSucursalgrupomayor;
		acSucursalgrupomayor = prepararBasico(usuarioActual,acSucursalgrupomayor, false);
		acSucursalgrupomayor.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursalgrupomayor.Actualizar : valores por defecto
		
		return acSucursalgrupomayor;
	}

	public BeanAcSucursalgrupomayor prepararAnular(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor == null)
			return acSucursalgrupomayor;
		if (acSucursalgrupomayor.getAuxFlgPreparadoBoolean())
			return acSucursalgrupomayor;
		acSucursalgrupomayor = prepararBasico(usuarioActual, acSucursalgrupomayor, false);
		acSucursalgrupomayor.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursalgrupomayor.Anular : valores por defecto
		
		return acSucursalgrupomayor;
	}

	public BeanAcSucursalgrupomayor prepararActivar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor == null)
			return acSucursalgrupomayor;
		if (acSucursalgrupomayor.getAuxFlgPreparadoBoolean())
			return acSucursalgrupomayor;
		acSucursalgrupomayor = prepararBasico(usuarioActual, acSucursalgrupomayor, false);
		acSucursalgrupomayor.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursalgrupomayor.Activar : valores por defecto
		
		return acSucursalgrupomayor;
	}

	public BeanAcSucursalgrupomayor prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor == null)
			return acSucursalgrupomayor;
		if (acSucursalgrupomayor.getAuxFlgPreparadoBoolean())
			return acSucursalgrupomayor;
		acSucursalgrupomayor.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursalgrupomayor.Eliminar : valores por defecto
		
		return acSucursalgrupomayor;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (acSucursalgrupomayor == null)
			lst.add(this.getMsjUsuarioError("acsucursalgrupomayor.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (acSucursalgrupomayor.getPk() != null) {
			Set<ConstraintViolation<BeanAcSucursalgrupomayorPk>> reglasPk = validator.validate(acSucursalgrupomayor.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanAcSucursalgrupomayor>> reglas = validator.validate(acSucursalgrupomayor);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AcSucursalgrupomayor : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupomayor.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupomayor = prepararInsertar(usuarioActual, acSucursalgrupomayor);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acSucursalgrupomayor);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcSucursalgrupomayor.Insertar : validaciones
		BeanAcSucursalgrupomayor b1 = acSucursalgrupomayorDao.obtenerPorId(acSucursalgrupomayor.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupomayor.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupomayor = prepararActualizar(usuarioActual, acSucursalgrupomayor);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acSucursalgrupomayor);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcSucursalgrupomayor.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupomayor.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupomayor = prepararEliminar(usuarioActual, acSucursalgrupomayor);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcSucursalgrupomayor.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupomayorPk pk) throws Exception {
		BeanAcSucursalgrupomayor bean = acSucursalgrupomayorDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String psucursalgrupomayor) throws Exception {
		return coreEliminar(usuarioActual,new BeanAcSucursalgrupomayorPk( psucursalgrupomayor));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupomayor.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupomayor = prepararAnular(usuarioActual, acSucursalgrupomayor);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcSucursalgrupomayor.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayorPk pk) throws Exception {
		BeanAcSucursalgrupomayor bean = acSucursalgrupomayorDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String psucursalgrupomayor) throws Exception {
		return coreAnular(usuarioActual,new BeanAcSucursalgrupomayorPk( psucursalgrupomayor));
	}

	public List<DominioMensajeUsuario> coreActivar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayor acSucursalgrupomayor) throws Exception {
		if (acSucursalgrupomayor.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupomayor.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupomayor = prepararActivar(usuarioActual, acSucursalgrupomayor);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcSucursalgrupomayor.Activar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActivar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupomayorPk pk) throws Exception {
		BeanAcSucursalgrupomayor bean = acSucursalgrupomayorDao.obtenerPorId(pk);
		return coreActivar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreActivar(SeguridadUsuarioActual usuarioActual, String psucursalgrupomayor) throws Exception {
		return coreActivar(usuarioActual,new BeanAcSucursalgrupomayorPk( psucursalgrupomayor));
	}

	public DtoComunAcSucursalgrupomayor core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunAcSucursalgrupomayor dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanAcSucursalgrupomayor acSucursalgrupomayor = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, acSucursalgrupomayor);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, acSucursalgrupomayor);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, acSucursalgrupomayor);
		if (accion.equals(ConstantePantallaAccion.ACTIVAR))
			lst = coreActivar(usuarioActual, acSucursalgrupomayor);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, acSucursalgrupomayor);
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
