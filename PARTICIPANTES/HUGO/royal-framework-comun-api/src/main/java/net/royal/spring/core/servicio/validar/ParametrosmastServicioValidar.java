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
import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;

import net.royal.spring.core.dominio.BeanParametrosmast;
import net.royal.spring.core.dominio.BeanParametrosmastPk;
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.dto.DtoComunParametrosmast;

@Service (value = "BeanValidarParametrosmast")
public class ParametrosmastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarParametrosmast";
	private static Logger logger = LogManager.getLogger(ParametrosmastServicioValidar.class);

	@Autowired
	private ParametrosmastDaoImpl parametrosmastDao;

	private BeanParametrosmast prepararBasico(SeguridadUsuarioActual usuarioActual,BeanParametrosmast parametrosmast, Boolean flgInsertar) {
     parametrosmast.setUltimousuario(usuarioActual.getUsuario());
     parametrosmast.setUltimafechamodif(new Date());
		
		// TODO Parametrosmast : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return parametrosmast;
	}

	public BeanParametrosmast prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanParametrosmast parametrosmast) {
		if (parametrosmast == null)
			return parametrosmast;
		if (parametrosmast.getAuxFlgPreparadoBoolean())
			return parametrosmast;
		parametrosmast = prepararBasico(usuarioActual,parametrosmast, true);
		parametrosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Parametrosmast.Insertar : valores por defecto
		
		return parametrosmast;
	}

	public BeanParametrosmast prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanParametrosmast parametrosmast) {
		if (parametrosmast == null)
			return parametrosmast;
		if (parametrosmast.getAuxFlgPreparadoBoolean())
			return parametrosmast;
		parametrosmast = prepararBasico(usuarioActual,parametrosmast, false);
		parametrosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Parametrosmast.Actualizar : valores por defecto
		
		return parametrosmast;
	}

	public BeanParametrosmast prepararAnular(SeguridadUsuarioActual usuarioActual,BeanParametrosmast parametrosmast) {
		if (parametrosmast == null)
			return parametrosmast;
		if (parametrosmast.getAuxFlgPreparadoBoolean())
			return parametrosmast;
		parametrosmast = prepararBasico(usuarioActual, parametrosmast, false);
		parametrosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Parametrosmast.Anular : valores por defecto
		
		return parametrosmast;
	}

	public BeanParametrosmast prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanParametrosmast parametrosmast) {
		if (parametrosmast == null)
			return parametrosmast;
		if (parametrosmast.getAuxFlgPreparadoBoolean())
			return parametrosmast;
		parametrosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Parametrosmast.Eliminar : valores por defecto
		
		return parametrosmast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (parametrosmast == null)
			lst.add(this.getMsjUsuarioError("parametrosmast.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (parametrosmast.getPk() != null) {
			Set<ConstraintViolation<BeanParametrosmastPk>> reglasPk = validator.validate(parametrosmast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanParametrosmast>> reglas = validator.validate(parametrosmast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Parametrosmast : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanParametrosmast parametrosmast) {
		if (parametrosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		parametrosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		parametrosmast = prepararInsertar(usuarioActual, parametrosmast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, parametrosmast);
		
		BeanParametrosmast param = parametrosmastDao.obtenerPorId(parametrosmast.getPk());
		if(param != null) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_UNICA_PARAMETRO));			
		}
		
		if(UString.esNuloVacio(parametrosmast.getPk().getAplicacioncodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_APLICACION_CODIGO));
		}
		
		if(UString.esNuloVacio(parametrosmast.getPk().getCompaniacodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_COMPANIA_CODIGO));
		}
		
		if(UString.esNuloVacio(parametrosmast.getPk().getParametroclave())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TABLA_CODIGO));
		}
		
		if(UString.esNuloVacio(parametrosmast.getDescripcionparametro())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TABLA_DESCRIPCION));
		}
		
		if(UString.esNuloVacio(parametrosmast.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_ESTADO));
		}
		
		if(UString.esNuloVacio(parametrosmast.getTipodedatoflag())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TIPO));
		}else {
			if(parametrosmast.getTipodedatoflag().equals("N")) {
				if(parametrosmast.getNumero() == null) {
					lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TIPO_NUMERO));
				}
			}
			if(parametrosmast.getTipodedatoflag().equals("T")) {
				if(UString.esNuloVacio(parametrosmast.getTexto())) {
					lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TIPO_TEXTO));
				}
			}
			if(parametrosmast.getTipodedatoflag().equals("F")) {
				if(parametrosmast.getFecha() == null) {
					lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TIPO_FECHA));
				}
			}
		}
				
		
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO Parametrosmast.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast) {
		if (parametrosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		parametrosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		parametrosmast = prepararActualizar(usuarioActual, parametrosmast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, parametrosmast);
		
		
		if(UString.esNuloVacio(parametrosmast.getPk().getAplicacioncodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_APLICACION_CODIGO));
		}
		
		if(UString.esNuloVacio(parametrosmast.getPk().getCompaniacodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_COMPANIA_CODIGO));
		}
		
		if(UString.esNuloVacio(parametrosmast.getPk().getParametroclave())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TABLA_CODIGO));
		}
		
		if(UString.esNuloVacio(parametrosmast.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_ESTADO));
		}
		
		if(UString.esNuloVacio(parametrosmast.getDescripcionparametro())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TABLA_DESCRIPCION));
		}
		
		if(UString.esNuloVacio(parametrosmast.getTipodedatoflag())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TIPO));
		}else {
			if(parametrosmast.getTipodedatoflag().equals("N")) {
				if(parametrosmast.getNumero() == null) {
					lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TIPO_NUMERO));
				}
			}
			if(parametrosmast.getTipodedatoflag().equals("T")) {
				if(UString.esNuloVacio(parametrosmast.getTexto())) {
					lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TIPO_TEXTO));
				}
			}
			if(parametrosmast.getTipodedatoflag().equals("F")) {
				if(parametrosmast.getFecha() == null) {
					lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_PARAM_RESTRICCION_TIPO_FECHA));
				}
			}
		}
		
		
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO Parametrosmast.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanParametrosmast parametrosmast) {
		if (parametrosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		parametrosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		parametrosmast = prepararEliminar(usuarioActual, parametrosmast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Parametrosmast.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanParametrosmastPk pk) {
		BeanParametrosmast bean = parametrosmastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcompaniacodigo,String paplicacioncodigo,String pparametroclave) {
		return coreEliminar(usuarioActual,new BeanParametrosmastPk( pcompaniacodigo, paplicacioncodigo, pparametroclave));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast) {
		if (parametrosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		parametrosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		parametrosmast = prepararAnular(usuarioActual, parametrosmast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Parametrosmast.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanParametrosmastPk pk) {
		BeanParametrosmast bean = parametrosmastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo,String paplicacioncodigo,String pparametroclave) {
		return coreAnular(usuarioActual,new BeanParametrosmastPk( pcompaniacodigo, paplicacioncodigo, pparametroclave));
	}

	public DtoComunParametrosmast core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunParametrosmast dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanParametrosmast parametrosmast = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, parametrosmast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, parametrosmast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, parametrosmast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, parametrosmast);
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
