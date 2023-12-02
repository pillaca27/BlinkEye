package net.royal.spring.core.servicio.validar;

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
import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.core.dao.impl.MaMiscelaneosheaderDaoImpl;

import net.royal.spring.core.dominio.BeanMaMiscelaneosheader;
import net.royal.spring.core.dominio.BeanMaMiscelaneosheaderPk;
import net.royal.spring.core.dominio.BeanParametrosmast;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosheader;

@Service (value = "BeanValidarMaMiscelaneosheader")
public class MaMiscelaneosheaderServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarMaMiscelaneosheader";
	private static Logger logger = LogManager.getLogger(MaMiscelaneosheaderServicioValidar.class);

	@Autowired
	private MaMiscelaneosheaderDaoImpl maMiscelaneosheaderDao;

	private BeanMaMiscelaneosheader prepararBasico(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosheader maMiscelaneosheader, Boolean flgInsertar) {
     maMiscelaneosheader.setUltimousuario(usuarioActual.getUsuario());
     maMiscelaneosheader.setUltimafechamodif(new Date());
		
		// TODO MaMiscelaneosheader : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return maMiscelaneosheader;
	}

	public BeanMaMiscelaneosheader prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosheader maMiscelaneosheader) {
		if (maMiscelaneosheader == null)
			return maMiscelaneosheader;
		if (maMiscelaneosheader.getAuxFlgPreparadoBoolean())
			return maMiscelaneosheader;
		maMiscelaneosheader = prepararBasico(usuarioActual,maMiscelaneosheader, true);
		maMiscelaneosheader.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaMiscelaneosheader.Insertar : valores por defecto
		
		return maMiscelaneosheader;
	}

	public BeanMaMiscelaneosheader prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosheader maMiscelaneosheader) {
		if (maMiscelaneosheader == null)
			return maMiscelaneosheader;
		if (maMiscelaneosheader.getAuxFlgPreparadoBoolean())
			return maMiscelaneosheader;
		maMiscelaneosheader = prepararBasico(usuarioActual,maMiscelaneosheader, false);
		maMiscelaneosheader.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaMiscelaneosheader.Actualizar : valores por defecto
		
		return maMiscelaneosheader;
	}

	public BeanMaMiscelaneosheader prepararAnular(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosheader maMiscelaneosheader) {
		if (maMiscelaneosheader == null)
			return maMiscelaneosheader;
		if (maMiscelaneosheader.getAuxFlgPreparadoBoolean())
			return maMiscelaneosheader;
		maMiscelaneosheader = prepararBasico(usuarioActual, maMiscelaneosheader, false);
		maMiscelaneosheader.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaMiscelaneosheader.Anular : valores por defecto
		
		return maMiscelaneosheader;
	}

	public BeanMaMiscelaneosheader prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosheader maMiscelaneosheader) {
		if (maMiscelaneosheader == null)
			return maMiscelaneosheader;
		if (maMiscelaneosheader.getAuxFlgPreparadoBoolean())
			return maMiscelaneosheader;
		maMiscelaneosheader.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaMiscelaneosheader.Eliminar : valores por defecto
		
		return maMiscelaneosheader;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosheader maMiscelaneosheader) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (maMiscelaneosheader == null)
			lst.add(this.getMsjUsuarioError("mamiscelaneosheader.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (maMiscelaneosheader.getPk() != null) {
			Set<ConstraintViolation<BeanMaMiscelaneosheaderPk>> reglasPk = validator.validate(maMiscelaneosheader.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanMaMiscelaneosheader>> reglas = validator.validate(maMiscelaneosheader);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO MaMiscelaneosheader : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosheader maMiscelaneosheader) {
		if (maMiscelaneosheader.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maMiscelaneosheader.setAuxFlgValidadoBoolean(Boolean.TRUE);
		maMiscelaneosheader.setUuid(UUID.randomUUID().toString());
		maMiscelaneosheader = prepararInsertar(usuarioActual, maMiscelaneosheader);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maMiscelaneosheader);
		
		BeanMaMiscelaneosheader param = maMiscelaneosheaderDao.obtenerPorId(maMiscelaneosheader.getPk());
		if(param != null) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_UNICA));			
		}
		
		if(UString.esNuloVacio(maMiscelaneosheader.getPk().getAplicacioncodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_APLICACION_CODIGO));
		}
		
		if(UString.esNuloVacio(maMiscelaneosheader.getPk().getCompania())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_COMPANIA_CODIGO));
		}
		
		if(UString.esNuloVacio(maMiscelaneosheader.getPk().getCodigotabla())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_TABLA_CODIGO));
		}
		
		if(UString.esNuloVacio(maMiscelaneosheader.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_ESTADO));
		}		
		
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaMiscelaneosheader.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosheader maMiscelaneosheader) {
		if (maMiscelaneosheader.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maMiscelaneosheader.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maMiscelaneosheader = prepararActualizar(usuarioActual, maMiscelaneosheader);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maMiscelaneosheader);
		
		if(UString.esNuloVacio(maMiscelaneosheader.getPk().getAplicacioncodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_APLICACION_CODIGO));
		}
		
		if(UString.esNuloVacio(maMiscelaneosheader.getPk().getCompania())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_COMPANIA_CODIGO));
		}
		
		if(UString.esNuloVacio(maMiscelaneosheader.getPk().getCodigotabla())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_TABLA_CODIGO));
		}
		
		if(UString.esNuloVacio(maMiscelaneosheader.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_ESTADO));
		}
		
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaMiscelaneosheader.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosheader maMiscelaneosheader) {
		if (maMiscelaneosheader.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maMiscelaneosheader.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maMiscelaneosheader = prepararEliminar(usuarioActual, maMiscelaneosheader);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaMiscelaneosheader.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaMiscelaneosheaderPk pk) {
		BeanMaMiscelaneosheader bean = maMiscelaneosheaderDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String pcodigotabla,String pcompania) {
		return coreEliminar(usuarioActual,new BeanMaMiscelaneosheaderPk( paplicacioncodigo, pcodigotabla, pcompania));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosheader maMiscelaneosheader) {
		if (maMiscelaneosheader.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maMiscelaneosheader.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maMiscelaneosheader = prepararAnular(usuarioActual, maMiscelaneosheader);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaMiscelaneosheader.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaMiscelaneosheaderPk pk) {
		BeanMaMiscelaneosheader bean = maMiscelaneosheaderDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pcodigotabla,String pcompania) {
		return coreAnular(usuarioActual,new BeanMaMiscelaneosheaderPk( paplicacioncodigo, pcodigotabla, pcompania));
	}

	public DtoComunMaMiscelaneosheader core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunMaMiscelaneosheader dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanMaMiscelaneosheader maMiscelaneosheader = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, maMiscelaneosheader);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, maMiscelaneosheader);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, maMiscelaneosheader);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, maMiscelaneosheader);
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
