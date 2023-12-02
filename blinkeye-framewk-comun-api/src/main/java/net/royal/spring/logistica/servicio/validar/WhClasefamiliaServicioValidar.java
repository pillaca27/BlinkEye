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
import net.royal.spring.logistica.dao.impl.WhClasefamiliaDaoImpl;

import net.royal.spring.logistica.dominio.BeanWhClasefamilia;
import net.royal.spring.logistica.dominio.BeanWhClasefamiliaPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasefamilia;

@Service (value = "BeanValidarWhClasefamilia")
public class WhClasefamiliaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarWhClasefamilia";
	private static Logger logger = LogManager.getLogger(WhClasefamiliaServicioValidar.class);

	@Autowired
	private WhClasefamiliaDaoImpl whClasefamiliaDao;

	private BeanWhClasefamilia prepararBasico(SeguridadUsuarioActual usuarioActual,BeanWhClasefamilia whClasefamilia, Boolean flgInsertar) {
     whClasefamilia.setUltimousuario(usuarioActual.getUsuario());
     whClasefamilia.setUltimafechamodif(new Date());
		
		// TODO WhClasefamilia : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return whClasefamilia;
	}

	public BeanWhClasefamilia prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanWhClasefamilia whClasefamilia) {
		if (whClasefamilia == null)
			return whClasefamilia;
		if (whClasefamilia.getAuxFlgPreparadoBoolean())
			return whClasefamilia;
		whClasefamilia = prepararBasico(usuarioActual,whClasefamilia, true);
		whClasefamilia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClasefamilia.Insertar : valores por defecto
		
		return whClasefamilia;
	}

	public BeanWhClasefamilia prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanWhClasefamilia whClasefamilia) {
		if (whClasefamilia == null)
			return whClasefamilia;
		if (whClasefamilia.getAuxFlgPreparadoBoolean())
			return whClasefamilia;
		whClasefamilia = prepararBasico(usuarioActual,whClasefamilia, false);
		whClasefamilia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClasefamilia.Actualizar : valores por defecto
		
		return whClasefamilia;
	}

	public BeanWhClasefamilia prepararAnular(SeguridadUsuarioActual usuarioActual,BeanWhClasefamilia whClasefamilia) {
		if (whClasefamilia == null)
			return whClasefamilia;
		if (whClasefamilia.getAuxFlgPreparadoBoolean())
			return whClasefamilia;
		whClasefamilia = prepararBasico(usuarioActual, whClasefamilia, false);
		whClasefamilia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClasefamilia.Anular : valores por defecto
		
		return whClasefamilia;
	}

	public BeanWhClasefamilia prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanWhClasefamilia whClasefamilia) {
		if (whClasefamilia == null)
			return whClasefamilia;
		if (whClasefamilia.getAuxFlgPreparadoBoolean())
			return whClasefamilia;
		whClasefamilia.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClasefamilia.Eliminar : valores por defecto
		
		return whClasefamilia;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanWhClasefamilia whClasefamilia) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (whClasefamilia == null)
			lst.add(this.getMsjUsuarioError("whclasefamilia.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (whClasefamilia.getPk() != null) {
			Set<ConstraintViolation<BeanWhClasefamiliaPk>> reglasPk = validator.validate(whClasefamilia.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanWhClasefamilia>> reglas = validator.validate(whClasefamilia);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO WhClasefamilia : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhClasefamilia whClasefamilia) {
		if (whClasefamilia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClasefamilia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClasefamilia = prepararInsertar(usuarioActual, whClasefamilia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whClasefamilia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhClasefamilia.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhClasefamilia whClasefamilia) {
		if (whClasefamilia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClasefamilia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClasefamilia = prepararActualizar(usuarioActual, whClasefamilia);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whClasefamilia);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhClasefamilia.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhClasefamilia whClasefamilia) {
		if (whClasefamilia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClasefamilia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClasefamilia = prepararEliminar(usuarioActual, whClasefamilia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhClasefamilia.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhClasefamiliaPk pk) {
		BeanWhClasefamilia bean = whClasefamiliaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String plinea,String pfamilia) {
		return coreEliminar(usuarioActual,new BeanWhClasefamiliaPk( plinea, pfamilia));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClasefamilia whClasefamilia) {
		if (whClasefamilia.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClasefamilia.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClasefamilia = prepararAnular(usuarioActual, whClasefamilia);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhClasefamilia.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClasefamiliaPk pk) {
		BeanWhClasefamilia bean = whClasefamiliaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String plinea,String pfamilia) {
		return coreAnular(usuarioActual,new BeanWhClasefamiliaPk( plinea, pfamilia));
	}

	public DtoComunWhClasefamilia core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunWhClasefamilia dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanWhClasefamilia whClasefamilia = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, whClasefamilia);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, whClasefamilia);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, whClasefamilia);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, whClasefamilia);
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
