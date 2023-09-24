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
import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.core.dao.impl.MaMiscelaneosdetalleDaoImpl;

import net.royal.spring.core.dominio.BeanMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.BeanMaMiscelaneosdetallePk;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosheader;

@Service (value = "BeanValidarMaMiscelaneosdetalle")
public class MaMiscelaneosdetalleServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarMaMiscelaneosdetalle";
	private static Logger logger = LogManager.getLogger(MaMiscelaneosdetalleServicioValidar.class);

	@Autowired
	private MaMiscelaneosdetalleDaoImpl maMiscelaneosdetalleDao;

	private BeanMaMiscelaneosdetalle prepararBasico(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosdetalle maMiscelaneosdetalle, Boolean flgInsertar) {
     maMiscelaneosdetalle.setUltimousuario(usuarioActual.getUsuario());
     maMiscelaneosdetalle.setUltimafechamodif(new Date());
		
		
		return maMiscelaneosdetalle;
	}
	
	
	public List<DominioMensajeUsuario> validar (SeguridadUsuarioActual usuario, DtoComunMaMiscelaneosheader bean) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		for (DtoComunMaMiscelaneosdetalle dto : bean.getDetalle()) {
			if(dto.getAuxAccion().equals("N")) {
				List<DominioMensajeUsuario> lstNuevo = coreInsertar(usuario, dto.obtenerBean());
				for (DominioMensajeUsuario mensaje : lstNuevo) {
					lst.add(mensaje);
				}
			}
			if(dto.getAuxAccion().equals("A")) {
				List<DominioMensajeUsuario> lstActualizar = coreActualizar(usuario, dto.obtenerBean());
				for (DominioMensajeUsuario mensaje : lstActualizar) {
					lst.add(mensaje);
				}
			}
		}
		
		return lst;
	}

	public BeanMaMiscelaneosdetalle prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosdetalle maMiscelaneosdetalle) {
		if (maMiscelaneosdetalle == null)
			return maMiscelaneosdetalle;
		if (maMiscelaneosdetalle.getAuxFlgPreparadoBoolean())
			return maMiscelaneosdetalle;
		maMiscelaneosdetalle = prepararBasico(usuarioActual,maMiscelaneosdetalle, true);
		maMiscelaneosdetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaMiscelaneosdetalle.Insertar : valores por defecto
		
		return maMiscelaneosdetalle;
	}

	public BeanMaMiscelaneosdetalle prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosdetalle maMiscelaneosdetalle) {
		if (maMiscelaneosdetalle == null)
			return maMiscelaneosdetalle;
		if (maMiscelaneosdetalle.getAuxFlgPreparadoBoolean())
			return maMiscelaneosdetalle;
		maMiscelaneosdetalle = prepararBasico(usuarioActual,maMiscelaneosdetalle, false);
		maMiscelaneosdetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		
		return maMiscelaneosdetalle;
	}

	public BeanMaMiscelaneosdetalle prepararAnular(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosdetalle maMiscelaneosdetalle) {
		if (maMiscelaneosdetalle == null)
			return maMiscelaneosdetalle;
		if (maMiscelaneosdetalle.getAuxFlgPreparadoBoolean())
			return maMiscelaneosdetalle;
		maMiscelaneosdetalle = prepararBasico(usuarioActual, maMiscelaneosdetalle, false);
		maMiscelaneosdetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		
		return maMiscelaneosdetalle;
	}

	public BeanMaMiscelaneosdetalle prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosdetalle maMiscelaneosdetalle) {
		if (maMiscelaneosdetalle == null)
			return maMiscelaneosdetalle;
		if (maMiscelaneosdetalle.getAuxFlgPreparadoBoolean())
			return maMiscelaneosdetalle;
		maMiscelaneosdetalle.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaMiscelaneosdetalle.Eliminar : valores por defecto
		
		return maMiscelaneosdetalle;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosdetalle maMiscelaneosdetalle) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (maMiscelaneosdetalle == null)
			lst.add(this.getMsjUsuarioError("mamiscelaneosdetalle.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (maMiscelaneosdetalle.getPk() != null) {
			Set<ConstraintViolation<BeanMaMiscelaneosdetallePk>> reglasPk = validator.validate(maMiscelaneosdetalle.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanMaMiscelaneosdetalle>> reglas = validator.validate(maMiscelaneosdetalle);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosdetalle maMiscelaneosdetalle) {
		if (maMiscelaneosdetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maMiscelaneosdetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maMiscelaneosdetalle = prepararInsertar(usuarioActual, maMiscelaneosdetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();//coreBasico(usuarioActual, maMiscelaneosdetalle);
		
		if(UString.esNuloVacio(maMiscelaneosdetalle.getPk().getCodigoelemento())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_CODIGO_ELEMENTO));
		}
		
		if(UString.esNuloVacio(maMiscelaneosdetalle.getDescripcionlocal())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_DESCRIPCION));
		}
		
		if(UString.esNuloVacio(maMiscelaneosdetalle.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_ESTADO));
		}
		
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaMiscelaneosdetalle.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosdetalle maMiscelaneosdetalle) {
		if (maMiscelaneosdetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maMiscelaneosdetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maMiscelaneosdetalle = prepararActualizar(usuarioActual, maMiscelaneosdetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();//coreBasico(usuarioActual, maMiscelaneosdetalle);
		
		if(UString.esNuloVacio(maMiscelaneosdetalle.getPk().getCodigoelemento())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_CODIGO_ELEMENTO));
		}

		if(UString.esNuloVacio(maMiscelaneosdetalle.getDescripcionlocal())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_DESCRIPCION));
		}
		
		if(UString.esNuloVacio(maMiscelaneosdetalle.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_ESTADO));
		}
		
		if (!lst.isEmpty())
			return lst;
		
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosdetalle maMiscelaneosdetalle) {
		if (maMiscelaneosdetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maMiscelaneosdetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maMiscelaneosdetalle = prepararEliminar(usuarioActual, maMiscelaneosdetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaMiscelaneosdetalle.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosdetallePk pk) {
		BeanMaMiscelaneosdetalle bean = maMiscelaneosdetalleDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String pcodigotabla,String pcompania,String pcodigoelemento) {
		return coreEliminar(usuarioActual,new BeanMaMiscelaneosdetallePk( paplicacioncodigo, pcodigotabla, pcompania, pcodigoelemento));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosdetalle maMiscelaneosdetalle) {
		if (maMiscelaneosdetalle.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maMiscelaneosdetalle.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maMiscelaneosdetalle = prepararAnular(usuarioActual, maMiscelaneosdetalle);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosdetallePk pk) {
		BeanMaMiscelaneosdetalle bean = maMiscelaneosdetalleDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pcodigotabla,String pcompania,String pcodigoelemento) {
		return coreAnular(usuarioActual,new BeanMaMiscelaneosdetallePk( paplicacioncodigo, pcodigotabla, pcompania, pcodigoelemento));
	}

	public DtoComunMaMiscelaneosdetalle core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunMaMiscelaneosdetalle dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanMaMiscelaneosdetalle maMiscelaneosdetalle = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, maMiscelaneosdetalle);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, maMiscelaneosdetalle);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, maMiscelaneosdetalle);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, maMiscelaneosdetalle);
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
