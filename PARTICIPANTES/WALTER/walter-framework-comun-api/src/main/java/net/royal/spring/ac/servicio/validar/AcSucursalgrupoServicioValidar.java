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
import net.royal.spring.ac.dao.impl.AcSucursalgrupoDaoImpl;

import net.royal.spring.ac.dominio.BeanAcSucursalgrupo;
import net.royal.spring.ac.dominio.BeanAcSucursalgrupoPk;
import net.royal.spring.ac.dominio.dto.DtoComunAcSucursalgrupo;


@Service (value = "BeanValidarAcSucursalgrupo")
public class AcSucursalgrupoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAcSucursalgrupo";
	private static Logger logger = LogManager.getLogger(AcSucursalgrupoServicioValidar.class);

	@Autowired
	private AcSucursalgrupoDaoImpl acSucursalgrupoDao;

	private BeanAcSucursalgrupo prepararBasico(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupo acSucursalgrupo, Boolean flgInsertar) throws Exception {
     acSucursalgrupo.setUltimousuario(usuarioActual.getUsuario());
     acSucursalgrupo.setUltimafechamodif(new Date());
		
		// TODO AcSucursalgrupo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return acSucursalgrupo;
	}

	public BeanAcSucursalgrupo prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo == null)
			return acSucursalgrupo;
		if (acSucursalgrupo.getAuxFlgPreparadoBoolean())
			return acSucursalgrupo;
		acSucursalgrupo = prepararBasico(usuarioActual,acSucursalgrupo, true);
		acSucursalgrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		acSucursalgrupo.setUuid(UUID.randomUUID().toString());
		
		// TODO AcSucursalgrupo.Insertar : valores por defecto
		
		return acSucursalgrupo;
	}

	public BeanAcSucursalgrupo prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo == null)
			return acSucursalgrupo;
		if (acSucursalgrupo.getAuxFlgPreparadoBoolean())
			return acSucursalgrupo;
		acSucursalgrupo = prepararBasico(usuarioActual,acSucursalgrupo, false);
		acSucursalgrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursalgrupo.Actualizar : valores por defecto
		
		return acSucursalgrupo;
	}

	public BeanAcSucursalgrupo prepararAnular(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo == null)
			return acSucursalgrupo;
		if (acSucursalgrupo.getAuxFlgPreparadoBoolean())
			return acSucursalgrupo;
		acSucursalgrupo = prepararBasico(usuarioActual, acSucursalgrupo, false);
		acSucursalgrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursalgrupo.Anular : valores por defecto
		
		return acSucursalgrupo;
	}

	public BeanAcSucursalgrupo prepararActivar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo == null)
			return acSucursalgrupo;
		if (acSucursalgrupo.getAuxFlgPreparadoBoolean())
			return acSucursalgrupo;
		acSucursalgrupo = prepararBasico(usuarioActual, acSucursalgrupo, false);
		acSucursalgrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursalgrupo.Activar : valores por defecto
		
		return acSucursalgrupo;
	}

	public BeanAcSucursalgrupo prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo == null)
			return acSucursalgrupo;
		if (acSucursalgrupo.getAuxFlgPreparadoBoolean())
			return acSucursalgrupo;
		acSucursalgrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursalgrupo.Eliminar : valores por defecto
		
		return acSucursalgrupo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (acSucursalgrupo == null)
			lst.add(this.getMsjUsuarioError("acsucursalgrupo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (acSucursalgrupo.getPk() != null) {
			Set<ConstraintViolation<BeanAcSucursalgrupoPk>> reglasPk = validator.validate(acSucursalgrupo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanAcSucursalgrupo>> reglas = validator.validate(acSucursalgrupo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AcSucursalgrupo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupo = prepararInsertar(usuarioActual, acSucursalgrupo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acSucursalgrupo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcSucursalgrupo.Insertar : validaciones
		BeanAcSucursalgrupo b1 = acSucursalgrupoDao.obtenerPorId(acSucursalgrupo.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("acsucursalgrupo.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupo = prepararActualizar(usuarioActual, acSucursalgrupo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acSucursalgrupo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcSucursalgrupo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupo = prepararEliminar(usuarioActual, acSucursalgrupo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcSucursalgrupo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalgrupoPk pk) throws Exception {
		BeanAcSucursalgrupo bean = acSucursalgrupoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String psucursalgrupo) throws Exception {
		return coreEliminar(usuarioActual,new BeanAcSucursalgrupoPk( psucursalgrupo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupo = prepararAnular(usuarioActual, acSucursalgrupo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcSucursalgrupo.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupoPk pk) throws Exception {
		BeanAcSucursalgrupo bean = acSucursalgrupoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String psucursalgrupo) throws Exception {
		return coreAnular(usuarioActual,new BeanAcSucursalgrupoPk( psucursalgrupo));
	}

	public List<DominioMensajeUsuario> coreActivar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupo acSucursalgrupo) throws Exception {
		if (acSucursalgrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursalgrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursalgrupo = prepararActivar(usuarioActual, acSucursalgrupo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcSucursalgrupo.Activar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActivar(SeguridadUsuarioActual usuarioActual, BeanAcSucursalgrupoPk pk) throws Exception {
		BeanAcSucursalgrupo bean = acSucursalgrupoDao.obtenerPorId(pk);
		return coreActivar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreActivar(SeguridadUsuarioActual usuarioActual, String psucursalgrupo) throws Exception {
		return coreActivar(usuarioActual,new BeanAcSucursalgrupoPk( psucursalgrupo));
	}

	public DtoComunAcSucursalgrupo core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunAcSucursalgrupo dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanAcSucursalgrupo acSucursalgrupo = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, acSucursalgrupo);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, acSucursalgrupo);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, acSucursalgrupo);
		if (accion.equals(ConstantePantallaAccion.ACTIVAR))
			lst = coreActivar(usuarioActual, acSucursalgrupo);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, acSucursalgrupo);
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
