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
import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.MaPersonagrupoDaoImpl;

import net.royal.spring.core.dominio.BeanMaPersonagrupo;
import net.royal.spring.core.dominio.BeanMaPersonagrupoPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonagrupo;

@Service (value = "BeanValidarMaPersonagrupo")
public class MaPersonagrupoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarMaPersonagrupo";
	private static Logger logger = LogManager.getLogger(MaPersonagrupoServicioValidar.class);

	@Autowired
	private MaPersonagrupoDaoImpl maPersonagrupoDao;

	private BeanMaPersonagrupo prepararBasico(SeguridadUsuarioActual usuarioActual,BeanMaPersonagrupo maPersonagrupo, Boolean flgInsertar) {
     maPersonagrupo.setUltimousuario(usuarioActual.getUsuario());
     maPersonagrupo.setUltimafechamodif(new Date());
		
		// TODO MaPersonagrupo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return maPersonagrupo;
	}

	public BeanMaPersonagrupo prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanMaPersonagrupo maPersonagrupo) {
		if (maPersonagrupo == null)
			return maPersonagrupo;
		if (maPersonagrupo.getAuxFlgPreparadoBoolean())
			return maPersonagrupo;
		maPersonagrupo = prepararBasico(usuarioActual,maPersonagrupo, true);
		maPersonagrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonagrupo.Insertar : valores por defecto
		
		return maPersonagrupo;
	}

	public BeanMaPersonagrupo prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanMaPersonagrupo maPersonagrupo) {
		if (maPersonagrupo == null)
			return maPersonagrupo;
		if (maPersonagrupo.getAuxFlgPreparadoBoolean())
			return maPersonagrupo;
		maPersonagrupo = prepararBasico(usuarioActual,maPersonagrupo, false);
		maPersonagrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonagrupo.Actualizar : valores por defecto
		
		return maPersonagrupo;
	}

	public BeanMaPersonagrupo prepararAnular(SeguridadUsuarioActual usuarioActual,BeanMaPersonagrupo maPersonagrupo) {
		if (maPersonagrupo == null)
			return maPersonagrupo;
		if (maPersonagrupo.getAuxFlgPreparadoBoolean())
			return maPersonagrupo;
		maPersonagrupo = prepararBasico(usuarioActual, maPersonagrupo, false);
		maPersonagrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonagrupo.Anular : valores por defecto
		
		return maPersonagrupo;
	}

	public BeanMaPersonagrupo prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanMaPersonagrupo maPersonagrupo) {
		if (maPersonagrupo == null)
			return maPersonagrupo;
		if (maPersonagrupo.getAuxFlgPreparadoBoolean())
			return maPersonagrupo;
		maPersonagrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonagrupo.Eliminar : valores por defecto
		
		return maPersonagrupo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanMaPersonagrupo maPersonagrupo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (maPersonagrupo == null)
			lst.add(this.getMsjUsuarioError("mapersonagrupo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (maPersonagrupo.getPk() != null) {
			Set<ConstraintViolation<BeanMaPersonagrupoPk>> reglasPk = validator.validate(maPersonagrupo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanMaPersonagrupo>> reglas = validator.validate(maPersonagrupo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO MaPersonagrupo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaPersonagrupo maPersonagrupo) {
		if (maPersonagrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonagrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonagrupo = prepararInsertar(usuarioActual, maPersonagrupo);
		
		List<DominioMensajeUsuario> lstValid = new ArrayList<DominioMensajeUsuario>();

		if(UString.esNuloVacio(maPersonagrupo.getPk().getPersonagrupo())) {
			lstValid.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_GRPER_RESTRICCION_GRUPO));
		}else {
			BeanMaPersonagrupo grupo = maPersonagrupoDao.obtenerPorId(maPersonagrupo.getPk().getPersonagrupo());
			if(grupo != null) {
				lstValid.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_GRPER_UNICO_GRUPO));
			}
		}
		
		if(UString.esNuloVacio(maPersonagrupo.getDescripcionlocal())) {
			lstValid.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_GRPER_RESTRICCION_DESCRI));
		}
		
		if (!lstValid.isEmpty())
			return lstValid;
		
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maPersonagrupo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaPersonagrupo.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaPersonagrupo maPersonagrupo) {
		if (maPersonagrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonagrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonagrupo = prepararActualizar(usuarioActual, maPersonagrupo);
		
		List<DominioMensajeUsuario> lstValid = new ArrayList<DominioMensajeUsuario>();	
		if(UString.esNuloVacio(maPersonagrupo.getDescripcionlocal())) {
			lstValid.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_GRPER_RESTRICCION_DESCRI));
		}
		
		if (!lstValid.isEmpty())
			return lstValid;
		
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maPersonagrupo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaPersonagrupo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaPersonagrupo maPersonagrupo) {
		if (maPersonagrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonagrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonagrupo = prepararEliminar(usuarioActual, maPersonagrupo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaPersonagrupo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaPersonagrupoPk pk) {
		BeanMaPersonagrupo bean = maPersonagrupoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String ppersonagrupo) {
		return coreEliminar(usuarioActual,new BeanMaPersonagrupoPk( ppersonagrupo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaPersonagrupo maPersonagrupo) {
		if (maPersonagrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonagrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonagrupo = prepararAnular(usuarioActual, maPersonagrupo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaPersonagrupo.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaPersonagrupoPk pk) {
		BeanMaPersonagrupo bean = maPersonagrupoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String ppersonagrupo) {
		return coreAnular(usuarioActual,new BeanMaPersonagrupoPk( ppersonagrupo));
	}

	public DtoComunMaPersonagrupo core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunMaPersonagrupo dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanMaPersonagrupo maPersonagrupo = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, maPersonagrupo);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, maPersonagrupo);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, maPersonagrupo);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, maPersonagrupo);
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
