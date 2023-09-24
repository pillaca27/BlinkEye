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

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sy.dao.impl.SySeguridadconceptoDaoImpl;
import net.royal.spring.sy.dominio.BeanSySeguridadconcepto;
import net.royal.spring.sy.dominio.BeanSySeguridadconceptoPk;
import net.royal.spring.sy.dominio.dto.DtoComunSySeguridadconcepto;

@Service (value = "BeanValidarSySeguridadconcepto")
public class SySeguridadconceptoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSySeguridadconcepto";
	private static Logger logger = LogManager.getLogger(SySeguridadconceptoServicioValidar.class);

	@Autowired
	private SySeguridadconceptoDaoImpl sySeguridadconceptoDao;

	private BeanSySeguridadconcepto prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSySeguridadconcepto sySeguridadconcepto, Boolean flgInsertar) throws Exception {
     sySeguridadconcepto.setUltimousuario(usuarioActual.getUsuario());
     sySeguridadconcepto.setUltimafechamodif(new Date());
		
		// TODO SySeguridadconcepto : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return sySeguridadconcepto;
	}

	public BeanSySeguridadconcepto prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		if (sySeguridadconcepto == null)
			return sySeguridadconcepto;
		if (sySeguridadconcepto.getAuxFlgPreparadoBoolean())
			return sySeguridadconcepto;
		sySeguridadconcepto = prepararBasico(usuarioActual,sySeguridadconcepto, true);
		sySeguridadconcepto.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SySeguridadconcepto.Insertar : valores por defecto
		
		return sySeguridadconcepto;
	}

	public BeanSySeguridadconcepto prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		if (sySeguridadconcepto == null)
			return sySeguridadconcepto;
		if (sySeguridadconcepto.getAuxFlgPreparadoBoolean())
			return sySeguridadconcepto;
		sySeguridadconcepto = prepararBasico(usuarioActual,sySeguridadconcepto, false);
		sySeguridadconcepto.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SySeguridadconcepto.Actualizar : valores por defecto
		
		return sySeguridadconcepto;
	}

	public BeanSySeguridadconcepto prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		if (sySeguridadconcepto == null)
			return sySeguridadconcepto;
		if (sySeguridadconcepto.getAuxFlgPreparadoBoolean())
			return sySeguridadconcepto;
		sySeguridadconcepto = prepararBasico(usuarioActual, sySeguridadconcepto, false);
		sySeguridadconcepto.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SySeguridadconcepto.Anular : valores por defecto
		
		return sySeguridadconcepto;
	}

	public BeanSySeguridadconcepto prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		if (sySeguridadconcepto == null)
			return sySeguridadconcepto;
		if (sySeguridadconcepto.getAuxFlgPreparadoBoolean())
			return sySeguridadconcepto;
		sySeguridadconcepto.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SySeguridadconcepto.Eliminar : valores por defecto
		
		return sySeguridadconcepto;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (sySeguridadconcepto == null)
			lst.add(this.getMsjUsuarioError("syseguridadconcepto.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (sySeguridadconcepto.getPk() != null) {
			Set<ConstraintViolation<BeanSySeguridadconceptoPk>> reglasPk = validator.validate(sySeguridadconcepto.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSySeguridadconcepto>> reglas = validator.validate(sySeguridadconcepto);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SySeguridadconcepto : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		if (sySeguridadconcepto.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		sySeguridadconcepto.setAuxFlgValidadoBoolean(Boolean.TRUE);

		sySeguridadconcepto = prepararInsertar(usuarioActual, sySeguridadconcepto);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, sySeguridadconcepto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SySeguridadconcepto.Insertar : validaciones
		BeanSySeguridadconcepto b1 = sySeguridadconceptoDao.obtenerPorId(sySeguridadconcepto.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		if (sySeguridadconcepto.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		sySeguridadconcepto.setAuxFlgValidadoBoolean(Boolean.TRUE);

		sySeguridadconcepto = prepararActualizar(usuarioActual, sySeguridadconcepto);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, sySeguridadconcepto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SySeguridadconcepto.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		if (sySeguridadconcepto.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		sySeguridadconcepto.setAuxFlgValidadoBoolean(Boolean.TRUE);

		sySeguridadconcepto = prepararEliminar(usuarioActual, sySeguridadconcepto);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SySeguridadconcepto.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSySeguridadconceptoPk pk) throws Exception {
		BeanSySeguridadconcepto bean = sySeguridadconceptoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String pgrupo,String pconcepto) throws Exception {
		return coreEliminar(usuarioActual,new BeanSySeguridadconceptoPk( paplicacioncodigo, pgrupo, pconcepto));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadconcepto sySeguridadconcepto) throws Exception {
		if (sySeguridadconcepto.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		sySeguridadconcepto.setAuxFlgValidadoBoolean(Boolean.TRUE);

		sySeguridadconcepto = prepararAnular(usuarioActual, sySeguridadconcepto);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SySeguridadconcepto.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSySeguridadconceptoPk pk) throws Exception {
		BeanSySeguridadconcepto bean = sySeguridadconceptoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pgrupo,String pconcepto) throws Exception {
		return coreAnular(usuarioActual,new BeanSySeguridadconceptoPk( paplicacioncodigo, pgrupo, pconcepto));
	}

	public DtoComunSySeguridadconcepto core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunSySeguridadconcepto dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanSySeguridadconcepto sySeguridadconcepto = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, sySeguridadconcepto);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, sySeguridadconcepto);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, sySeguridadconcepto);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, sySeguridadconcepto);
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
