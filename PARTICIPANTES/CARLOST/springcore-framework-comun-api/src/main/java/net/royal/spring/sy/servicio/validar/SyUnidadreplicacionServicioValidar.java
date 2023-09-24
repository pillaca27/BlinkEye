package net.royal.spring.sy.servicio.validar;

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

import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dominio.BeanMaUnidadnegocio;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
 
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sy.dao.impl.SyUnidadreplicacionDaoImpl;

import net.royal.spring.sy.dominio.BeanSyUnidadreplicacion;
import net.royal.spring.sy.dominio.BeanSyUnidadreplicacionPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyUnidadreplicacion;

@Service (value = "BeanValidarSyUnidadreplicacion")
public class SyUnidadreplicacionServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyUnidadreplicacion";
	private static Logger logger = LogManager.getLogger(SyUnidadreplicacionServicioValidar.class);

	@Autowired
	private SyUnidadreplicacionDaoImpl syUnidadreplicacionDao;

	private BeanSyUnidadreplicacion prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSyUnidadreplicacion syUnidadreplicacion, Boolean flgInsertar) {
     syUnidadreplicacion.setUltimousuario(usuarioActual.getUsuario());
     syUnidadreplicacion.setUltimafechamodif(new Date());
		
		// TODO SyUnidadreplicacion : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syUnidadreplicacion;
	}

	public BeanSyUnidadreplicacion prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSyUnidadreplicacion syUnidadreplicacion) {
		if (syUnidadreplicacion == null)
			return syUnidadreplicacion;
		if (syUnidadreplicacion.getAuxFlgPreparadoBoolean())
			return syUnidadreplicacion;
		syUnidadreplicacion = prepararBasico(usuarioActual,syUnidadreplicacion, true);
		syUnidadreplicacion.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyUnidadreplicacion.Insertar : valores por defecto
		
		return syUnidadreplicacion;
	}

	public BeanSyUnidadreplicacion prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSyUnidadreplicacion syUnidadreplicacion) {
		if (syUnidadreplicacion == null)
			return syUnidadreplicacion;
		if (syUnidadreplicacion.getAuxFlgPreparadoBoolean())
			return syUnidadreplicacion;
		syUnidadreplicacion = prepararBasico(usuarioActual,syUnidadreplicacion, false);
		syUnidadreplicacion.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyUnidadreplicacion.Actualizar : valores por defecto
		
		return syUnidadreplicacion;
	}

	public BeanSyUnidadreplicacion prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSyUnidadreplicacion syUnidadreplicacion) {
		if (syUnidadreplicacion == null)
			return syUnidadreplicacion;
		if (syUnidadreplicacion.getAuxFlgPreparadoBoolean())
			return syUnidadreplicacion;
		syUnidadreplicacion = prepararBasico(usuarioActual, syUnidadreplicacion, false);
		syUnidadreplicacion.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyUnidadreplicacion.Anular : valores por defecto
		
		return syUnidadreplicacion;
	}

	public BeanSyUnidadreplicacion prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSyUnidadreplicacion syUnidadreplicacion) {
		if (syUnidadreplicacion == null)
			return syUnidadreplicacion;
		if (syUnidadreplicacion.getAuxFlgPreparadoBoolean())
			return syUnidadreplicacion;
		syUnidadreplicacion.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyUnidadreplicacion.Eliminar : valores por defecto
		
		return syUnidadreplicacion;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSyUnidadreplicacion syUnidadreplicacion) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syUnidadreplicacion == null)
			lst.add(this.getMsjUsuarioError("syunidadreplicacion.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syUnidadreplicacion.getPk() != null) {
			Set<ConstraintViolation<BeanSyUnidadreplicacionPk>> reglasPk = validator.validate(syUnidadreplicacion.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyUnidadreplicacion>> reglas = validator.validate(syUnidadreplicacion);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyUnidadreplicacion : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyUnidadreplicacion syUnidadreplicacion) {
		if (syUnidadreplicacion.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syUnidadreplicacion.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(syUnidadreplicacion.getPk().getUnidadreplicacion())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_CODIGO));
		}else {
			BeanSyUnidadreplicacion unidadReplicacionDao= syUnidadreplicacionDao.obtenerPorId(syUnidadreplicacion.getPk());
			if(unidadReplicacionDao != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_CODIGO_VALID));
			}
			
			if(syUnidadreplicacion.getPk().getUnidadreplicacion().length() != 4) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_CODIGO_VALID_CHAR));
			}
		}
		if(UString.esNuloVacio(syUnidadreplicacion.getDescripcionlocal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_DESCRIPCION));
		}
		if(syUnidadreplicacion.getRangopersonainicio()==null) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_INICIO));
		}
		if(UInteger.esCeroOrNulo(syUnidadreplicacion.getRangopersonafin())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_FIN));
		}
		if(syUnidadreplicacion.getPersonaactual()==null) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_PERSONA));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		syUnidadreplicacion = prepararInsertar(usuarioActual, syUnidadreplicacion);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syUnidadreplicacion);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyUnidadreplicacion.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyUnidadreplicacion syUnidadreplicacion) {
		if (syUnidadreplicacion.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syUnidadreplicacion.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(syUnidadreplicacion.getDescripcionlocal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_DESCRIPCION));
		}
		if(syUnidadreplicacion.getRangopersonainicio()==null) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_INICIO));
		}
		if(UInteger.esCeroOrNulo(syUnidadreplicacion.getRangopersonafin())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_FIN));
		}
		if(syUnidadreplicacion.getPersonaactual()==null) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_REPLICACION_PERSONA));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		syUnidadreplicacion = prepararActualizar(usuarioActual, syUnidadreplicacion);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syUnidadreplicacion);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyUnidadreplicacion.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyUnidadreplicacion syUnidadreplicacion) {
		if (syUnidadreplicacion.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syUnidadreplicacion.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syUnidadreplicacion = prepararEliminar(usuarioActual, syUnidadreplicacion);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyUnidadreplicacion.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyUnidadreplicacionPk pk) {
		BeanSyUnidadreplicacion bean = syUnidadreplicacionDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String punidadreplicacion) {
		return coreEliminar(usuarioActual,new BeanSyUnidadreplicacionPk( punidadreplicacion));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyUnidadreplicacion syUnidadreplicacion) {
		if (syUnidadreplicacion.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syUnidadreplicacion.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syUnidadreplicacion = prepararAnular(usuarioActual, syUnidadreplicacion);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyUnidadreplicacion.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyUnidadreplicacionPk pk) {
		BeanSyUnidadreplicacion bean = syUnidadreplicacionDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String punidadreplicacion) {
		return coreAnular(usuarioActual,new BeanSyUnidadreplicacionPk( punidadreplicacion));
	}

	public DtoComunSyUnidadreplicacion core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunSyUnidadreplicacion dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanSyUnidadreplicacion syUnidadreplicacion = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, syUnidadreplicacion);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, syUnidadreplicacion);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, syUnidadreplicacion);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, syUnidadreplicacion);
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
