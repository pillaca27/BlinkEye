package net.royal.spring.logistica.servicio.validar;

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
import net.royal.spring.logistica.dao.impl.WhClasesubfamiliaDaoImpl;

import net.royal.spring.logistica.dominio.BeanWhClasesubfamilia;
import net.royal.spring.logistica.dominio.BeanWhClasesubfamiliaPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasesubfamilia;

@Service (value = "BeanValidarWhClasesubfamilia")
public class WhClasesubfamiliaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarWhClasesubfamilia";
	private static Logger logger = LogManager.getLogger(WhClasesubfamiliaServicioValidar.class);

	@Autowired
	private WhClasesubfamiliaDaoImpl whClasesubfamiliaDao;

	private BeanWhClasesubfamilia prepararBasico(SeguridadUsuarioActual usuarioActual,BeanWhClasesubfamilia whClasesubfamilia, Boolean flgInsertar) {
     whClasesubfamilia.setUltimousuario(usuarioActual.getUsuario());
     whClasesubfamilia.setUltimafechamodif(new Date());
		
		// TODO WhClasesubfamilia : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return whClasesubfamilia;
	}

	public BeanWhClasesubfamilia prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanWhClasesubfamilia whClasesubfamilia) {
		if (whClasesubfamilia == null)
			return whClasesubfamilia;
		if (whClasesubfamilia.getAuxFlgPreparadoBoolean())
			return whClasesubfamilia;
		whClasesubfamilia = prepararBasico(usuarioActual,whClasesubfamilia, true);
		whClasesubfamilia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClasesubfamilia.Insertar : valores por defecto
		
		return whClasesubfamilia;
	}

	public BeanWhClasesubfamilia prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanWhClasesubfamilia whClasesubfamilia) {
		if (whClasesubfamilia == null)
			return whClasesubfamilia;
		if (whClasesubfamilia.getAuxFlgPreparadoBoolean())
			return whClasesubfamilia;
		whClasesubfamilia = prepararBasico(usuarioActual,whClasesubfamilia, false);
		whClasesubfamilia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClasesubfamilia.Actualizar : valores por defecto
		
		return whClasesubfamilia;
	}

	public BeanWhClasesubfamilia prepararAnular(SeguridadUsuarioActual usuarioActual,BeanWhClasesubfamilia whClasesubfamilia) {
		if (whClasesubfamilia == null)
			return whClasesubfamilia;
		if (whClasesubfamilia.getAuxFlgPreparadoBoolean())
			return whClasesubfamilia;
		whClasesubfamilia = prepararBasico(usuarioActual, whClasesubfamilia, false);
		whClasesubfamilia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClasesubfamilia.Anular : valores por defecto
		
		return whClasesubfamilia;
	}

	public BeanWhClasesubfamilia prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanWhClasesubfamilia whClasesubfamilia) {
		if (whClasesubfamilia == null)
			return whClasesubfamilia;
		if (whClasesubfamilia.getAuxFlgPreparadoBoolean())
			return whClasesubfamilia;
		whClasesubfamilia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClasesubfamilia.Eliminar : valores por defecto
		
		return whClasesubfamilia;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanWhClasesubfamilia whClasesubfamilia) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (whClasesubfamilia == null)
			lst.add(this.getMsjUsuarioError("whclasesubfamilia.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (whClasesubfamilia.getPk() != null) {
			Set<ConstraintViolation<BeanWhClasesubfamiliaPk>> reglasPk = validator.validate(whClasesubfamilia.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanWhClasesubfamilia>> reglas = validator.validate(whClasesubfamilia);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO WhClasesubfamilia : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhClasesubfamilia whClasesubfamilia) {
		if (whClasesubfamilia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClasesubfamilia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClasesubfamilia = prepararInsertar(usuarioActual, whClasesubfamilia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whClasesubfamilia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhClasesubfamilia.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhClasesubfamilia whClasesubfamilia) {
		if (whClasesubfamilia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClasesubfamilia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClasesubfamilia = prepararActualizar(usuarioActual, whClasesubfamilia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whClasesubfamilia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhClasesubfamilia.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhClasesubfamilia whClasesubfamilia) {
		if (whClasesubfamilia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClasesubfamilia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClasesubfamilia = prepararEliminar(usuarioActual, whClasesubfamilia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhClasesubfamilia.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhClasesubfamiliaPk pk) {
		BeanWhClasesubfamilia bean = whClasesubfamiliaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String plinea,String pfamilia,String psubfamilia) {
		return coreEliminar(usuarioActual,new BeanWhClasesubfamiliaPk( plinea, pfamilia, psubfamilia));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClasesubfamilia whClasesubfamilia) {
		if (whClasesubfamilia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClasesubfamilia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClasesubfamilia = prepararAnular(usuarioActual, whClasesubfamilia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhClasesubfamilia.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClasesubfamiliaPk pk) {
		BeanWhClasesubfamilia bean = whClasesubfamiliaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String plinea,String pfamilia,String psubfamilia) {
		return coreAnular(usuarioActual,new BeanWhClasesubfamiliaPk( plinea, pfamilia, psubfamilia));
	}

	public DtoComunWhClasesubfamilia core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunWhClasesubfamilia dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanWhClasesubfamilia whClasesubfamilia = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, whClasesubfamilia);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, whClasesubfamilia);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, whClasesubfamilia);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, whClasesubfamilia);
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
