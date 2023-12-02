package net.royal.spring.sistema.servicio.validar;

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
import net.royal.spring.sistema.dao.impl.SySeguridadgrupoDaoImpl;

import net.royal.spring.sistema.dominio.BeanSySeguridadgrupo;
import net.royal.spring.sistema.dominio.BeanSySeguridadgrupoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSySeguridadgrupo;

@Service (value = "BeanValidarSySeguridadgrupo")
public class SySeguridadgrupoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSySeguridadgrupo";
	private static Logger logger = LogManager.getLogger(SySeguridadgrupoServicioValidar.class);

	@Autowired
	private SySeguridadgrupoDaoImpl sySeguridadgrupoDao;

	private BeanSySeguridadgrupo prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSySeguridadgrupo sySeguridadgrupo, Boolean flgInsertar) throws Exception {
     sySeguridadgrupo.setUltimousuario(usuarioActual.getUsuario());
     sySeguridadgrupo.setUltimafechamodif(new Date());
		
		// TODO SySeguridadgrupo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return sySeguridadgrupo;
	}

	public BeanSySeguridadgrupo prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		if (sySeguridadgrupo == null)
			return sySeguridadgrupo;
		if (sySeguridadgrupo.getAuxFlgPreparadoBoolean())
			return sySeguridadgrupo;
		sySeguridadgrupo = prepararBasico(usuarioActual,sySeguridadgrupo, true);
		sySeguridadgrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SySeguridadgrupo.Insertar : valores por defecto
		
		return sySeguridadgrupo;
	}

	public BeanSySeguridadgrupo prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		if (sySeguridadgrupo == null)
			return sySeguridadgrupo;
		if (sySeguridadgrupo.getAuxFlgPreparadoBoolean())
			return sySeguridadgrupo;
		sySeguridadgrupo = prepararBasico(usuarioActual,sySeguridadgrupo, false);
		sySeguridadgrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SySeguridadgrupo.Actualizar : valores por defecto
		
		return sySeguridadgrupo;
	}

	public BeanSySeguridadgrupo prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		if (sySeguridadgrupo == null)
			return sySeguridadgrupo;
		if (sySeguridadgrupo.getAuxFlgPreparadoBoolean())
			return sySeguridadgrupo;
		sySeguridadgrupo = prepararBasico(usuarioActual, sySeguridadgrupo, false);
		sySeguridadgrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SySeguridadgrupo.Anular : valores por defecto
		
		return sySeguridadgrupo;
	}

	public BeanSySeguridadgrupo prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		if (sySeguridadgrupo == null)
			return sySeguridadgrupo;
		if (sySeguridadgrupo.getAuxFlgPreparadoBoolean())
			return sySeguridadgrupo;
		sySeguridadgrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SySeguridadgrupo.Eliminar : valores por defecto
		
		return sySeguridadgrupo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (sySeguridadgrupo == null)
			lst.add(this.getMsjUsuarioError("syseguridadgrupo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (sySeguridadgrupo.getPk() != null) {
			Set<ConstraintViolation<BeanSySeguridadgrupoPk>> reglasPk = validator.validate(sySeguridadgrupo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSySeguridadgrupo>> reglas = validator.validate(sySeguridadgrupo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SySeguridadgrupo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		if (sySeguridadgrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		sySeguridadgrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		sySeguridadgrupo = prepararInsertar(usuarioActual, sySeguridadgrupo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, sySeguridadgrupo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SySeguridadgrupo.Insertar : validaciones
		BeanSySeguridadgrupo b1 = sySeguridadgrupoDao.obtenerPorId(sySeguridadgrupo.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("syseguridadgrupo.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		if (sySeguridadgrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		sySeguridadgrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		sySeguridadgrupo = prepararActualizar(usuarioActual, sySeguridadgrupo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, sySeguridadgrupo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SySeguridadgrupo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		if (sySeguridadgrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		sySeguridadgrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		sySeguridadgrupo = prepararEliminar(usuarioActual, sySeguridadgrupo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SySeguridadgrupo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadgrupoPk pk) throws Exception {
		BeanSySeguridadgrupo bean = sySeguridadgrupoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String pgrupo) throws Exception {
		return coreEliminar(usuarioActual,new BeanSySeguridadgrupoPk( paplicacioncodigo, pgrupo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadgrupo sySeguridadgrupo) throws Exception {
		if (sySeguridadgrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		sySeguridadgrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		sySeguridadgrupo = prepararAnular(usuarioActual, sySeguridadgrupo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SySeguridadgrupo.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadgrupoPk pk) throws Exception {
		BeanSySeguridadgrupo bean = sySeguridadgrupoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo) throws Exception {
		return coreAnular(usuarioActual,new BeanSySeguridadgrupoPk( paplicacioncodigo, pgrupo));
	}

	public DtoComunSySeguridadgrupo core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunSySeguridadgrupo dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanSySeguridadgrupo sySeguridadgrupo = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, sySeguridadgrupo);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, sySeguridadgrupo);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, sySeguridadgrupo);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, sySeguridadgrupo);
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
