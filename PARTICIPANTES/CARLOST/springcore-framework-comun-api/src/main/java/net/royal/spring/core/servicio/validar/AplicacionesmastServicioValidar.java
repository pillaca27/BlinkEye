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
import net.royal.spring.core.dao.impl.AplicacionesmastDaoImpl;

import net.royal.spring.core.dominio.BeanAplicacionesmast;
import net.royal.spring.core.dominio.BeanAplicacionesmastPk;
import net.royal.spring.core.dominio.dto.DtoComunAplicacionesmast;

@Service (value = "BeanValidarAplicacionesmast")
public class AplicacionesmastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAplicacionesmast";
	private static Logger logger = LogManager.getLogger(AplicacionesmastServicioValidar.class);

	@Autowired
	private AplicacionesmastDaoImpl aplicacionesmastDao;

	private BeanAplicacionesmast prepararBasico(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmast aplicacionesmast, Boolean flgInsertar) {
     aplicacionesmast.setUltimousuario(usuarioActual.getUsuario());
     aplicacionesmast.setUltimafechamodif(new Date());
		
		// TODO Aplicacionesmast : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return aplicacionesmast;
	}

	public BeanAplicacionesmast prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmast aplicacionesmast) {
		if (aplicacionesmast == null)
			return aplicacionesmast;
		if (aplicacionesmast.getAuxFlgPreparadoBoolean())
			return aplicacionesmast;
		aplicacionesmast = prepararBasico(usuarioActual,aplicacionesmast, true);
		aplicacionesmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Aplicacionesmast.Insertar : valores por defecto
		
		return aplicacionesmast;
	}

	public BeanAplicacionesmast prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmast aplicacionesmast) {
		if (aplicacionesmast == null)
			return aplicacionesmast;
		if (aplicacionesmast.getAuxFlgPreparadoBoolean())
			return aplicacionesmast;
		aplicacionesmast = prepararBasico(usuarioActual,aplicacionesmast, false);
		aplicacionesmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Aplicacionesmast.Actualizar : valores por defecto
		
		return aplicacionesmast;
	}

	public BeanAplicacionesmast prepararAnular(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmast aplicacionesmast) {
		if (aplicacionesmast == null)
			return aplicacionesmast;
		if (aplicacionesmast.getAuxFlgPreparadoBoolean())
			return aplicacionesmast;
		aplicacionesmast = prepararBasico(usuarioActual, aplicacionesmast, false);
		aplicacionesmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Aplicacionesmast.Anular : valores por defecto
		
		return aplicacionesmast;
	}

	public BeanAplicacionesmast prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmast aplicacionesmast) {
		if (aplicacionesmast == null)
			return aplicacionesmast;
		if (aplicacionesmast.getAuxFlgPreparadoBoolean())
			return aplicacionesmast;
		aplicacionesmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Aplicacionesmast.Eliminar : valores por defecto
		
		return aplicacionesmast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast aplicacionesmast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (!lst.isEmpty())
			return lst;
		/*Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (aplicacionesmast.getPk() != null) {
			Set<ConstraintViolation<AplicacionesmastPk>> reglasPk = validator.validate(aplicacionesmast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<Aplicacionesmast>> reglas = validator.validate(aplicacionesmast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}*/
		
		// TODO Aplicacionesmast : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmast aplicacionesmast) {
		if (aplicacionesmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		aplicacionesmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		aplicacionesmast = prepararInsertar(usuarioActual, aplicacionesmast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, aplicacionesmast);
		
		BeanAplicacionesmast aplicacionesmastId= aplicacionesmastDao.obtenerPorId(aplicacionesmast.getPk());
		if(aplicacionesmastId != null) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_UNICA_APLICACION_CODIGO));
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getPk().getAplicacioncodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_APLICACION_CODIGO));
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getDescripcioncorta())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_TABLA_DESCRIPCION));
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_ESTADO));
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getUltimoperiodocontable())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_PERIODO));
		}
		
		if(!UString.esNuloVacio(aplicacionesmast.getUltimoperiodocontable())) {
			if(aplicacionesmast.getUltimoperiodocontable().length()<6) {
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_PERIODO_LENGTH));
			}else if(aplicacionesmast.getCodigocontablevalid().equals("NO_VALID")) {
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_PERIODO_NO_VALID));
			}
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getDepartamentorevisor())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_DEPARTAMENTO));
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getSistemafuente())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_CODIGO_SISTEMA));
		}
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO Aplicacionesmast.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast aplicacionesmast) {
		if (aplicacionesmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		aplicacionesmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		aplicacionesmast = prepararActualizar(usuarioActual, aplicacionesmast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, aplicacionesmast);
		
		
		if(UString.esNuloVacio(aplicacionesmast.getPk().getAplicacioncodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_APLICACION_CODIGO));
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getDescripcioncorta())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_TABLA_DESCRIPCION));
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_ESTADO));
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getUltimoperiodocontable())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_PERIODO));
		}
		
		if(!UString.esNuloVacio(aplicacionesmast.getUltimoperiodocontable())) {
			if(aplicacionesmast.getUltimoperiodocontable().length()<6) {
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_PERIODO_LENGTH));
			}else if(aplicacionesmast.getCodigocontablevalid().equals("NO_VALID")) {
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_PERIODO_NO_VALID));
			}
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getDepartamentorevisor())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_RESTRICCION_DEPARTAMENTO));
		}
		
		if(UString.esNuloVacio(aplicacionesmast.getSistemafuente())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_APLI_CODIGO_SISTEMA));
		}
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO Aplicacionesmast.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmast aplicacionesmast) {
		if (aplicacionesmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		aplicacionesmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		aplicacionesmast = prepararEliminar(usuarioActual, aplicacionesmast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Aplicacionesmast.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAplicacionesmastPk pk) {
		BeanAplicacionesmast bean = aplicacionesmastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo) {
		return coreEliminar(usuarioActual,new BeanAplicacionesmastPk( paplicacioncodigo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmast aplicacionesmast) {
		if (aplicacionesmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		aplicacionesmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		aplicacionesmast = prepararAnular(usuarioActual, aplicacionesmast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Aplicacionesmast.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanAplicacionesmastPk pk) {
		BeanAplicacionesmast bean = aplicacionesmastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo) {
		return coreAnular(usuarioActual,new BeanAplicacionesmastPk( paplicacioncodigo));
	}

	public DtoComunAplicacionesmast core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunAplicacionesmast dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanAplicacionesmast aplicacionesmast = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, aplicacionesmast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, aplicacionesmast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, aplicacionesmast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, aplicacionesmast);
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
	
	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual, String accion, BeanAplicacionesmast aplicacionesmast,
			String paplicacioncodigo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, aplicacionesmast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, aplicacionesmast);
		if (accion.equals(ConstantePantallaAccion.ANULAR)) {
			BeanAplicacionesmastPk pk = new BeanAplicacionesmastPk(paplicacioncodigo);
			return coreAnular(usuarioActual, pk);
		}
		if (accion.equals(ConstantePantallaAccion.ELIMINAR)) {
			BeanAplicacionesmastPk pk = new BeanAplicacionesmastPk(paplicacioncodigo);
			return coreEliminar(usuarioActual, pk);
		}
		return lst;
	}

}
