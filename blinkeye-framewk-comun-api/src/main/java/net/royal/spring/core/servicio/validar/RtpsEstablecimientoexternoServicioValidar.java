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
import net.royal.spring.core.dao.impl.RtpsEstablecimientoexternoDaoImpl;

import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;

@Service (value = "BeanValidarRtpsEstablecimientoexterno")
public class RtpsEstablecimientoexternoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarRtpsEstablecimientoexterno";
	private static Logger logger = LogManager.getLogger(RtpsEstablecimientoexternoServicioValidar.class);

	@Autowired
	private RtpsEstablecimientoexternoDaoImpl rtpsEstablecimientoexternoDao;

	private BeanRtpsEstablecimientoexterno prepararBasico(SeguridadUsuarioActual usuarioActual,BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno, Boolean flgInsertar) throws Exception {
     rtpsEstablecimientoexterno.setUltimousuario(usuarioActual.getUsuario());
     rtpsEstablecimientoexterno.setUltimafechamodif(new Date());
		
		// TODO RtpsEstablecimientoexterno : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return rtpsEstablecimientoexterno;
	}

	public BeanRtpsEstablecimientoexterno prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		if (rtpsEstablecimientoexterno == null)
			return rtpsEstablecimientoexterno;
		if (rtpsEstablecimientoexterno.getAuxFlgPreparadoBoolean())
			return rtpsEstablecimientoexterno;
		rtpsEstablecimientoexterno = prepararBasico(usuarioActual,rtpsEstablecimientoexterno, true);
		rtpsEstablecimientoexterno.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Insertar : valores por defecto
		
		return rtpsEstablecimientoexterno;
	}

	public BeanRtpsEstablecimientoexterno prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		if (rtpsEstablecimientoexterno == null)
			return rtpsEstablecimientoexterno;
		if (rtpsEstablecimientoexterno.getAuxFlgPreparadoBoolean())
			return rtpsEstablecimientoexterno;
		rtpsEstablecimientoexterno = prepararBasico(usuarioActual,rtpsEstablecimientoexterno, false);
		rtpsEstablecimientoexterno.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Actualizar : valores por defecto
		
		return rtpsEstablecimientoexterno;
	}

	public BeanRtpsEstablecimientoexterno prepararAnular(SeguridadUsuarioActual usuarioActual,BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		if (rtpsEstablecimientoexterno == null)
			return rtpsEstablecimientoexterno;
		if (rtpsEstablecimientoexterno.getAuxFlgPreparadoBoolean())
			return rtpsEstablecimientoexterno;
		rtpsEstablecimientoexterno = prepararBasico(usuarioActual, rtpsEstablecimientoexterno, false);
		rtpsEstablecimientoexterno.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Anular : valores por defecto
		
		return rtpsEstablecimientoexterno;
	}

	public BeanRtpsEstablecimientoexterno prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		if (rtpsEstablecimientoexterno == null)
			return rtpsEstablecimientoexterno;
		if (rtpsEstablecimientoexterno.getAuxFlgPreparadoBoolean())
			return rtpsEstablecimientoexterno;
		rtpsEstablecimientoexterno.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Eliminar : valores por defecto
		
		return rtpsEstablecimientoexterno;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (rtpsEstablecimientoexterno == null)
			lst.add(this.getMsjUsuarioError("rtpsestablecimientoexterno.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (rtpsEstablecimientoexterno.getPk() != null) {
			Set<ConstraintViolation<BeanRtpsEstablecimientoexternoPk>> reglasPk = validator.validate(rtpsEstablecimientoexterno.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanRtpsEstablecimientoexterno>> reglas = validator.validate(rtpsEstablecimientoexterno);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO RtpsEstablecimientoexterno : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		if (rtpsEstablecimientoexterno.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEstablecimientoexterno.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEstablecimientoexterno = prepararInsertar(usuarioActual, rtpsEstablecimientoexterno);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, rtpsEstablecimientoexterno);
		if (!lst.isEmpty())
			return lst;
		
		// TODO RtpsEstablecimientoexterno.Insertar : validaciones
		BeanRtpsEstablecimientoexterno b1 = rtpsEstablecimientoexternoDao.obtenerPorId(rtpsEstablecimientoexterno.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		if (rtpsEstablecimientoexterno.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEstablecimientoexterno.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEstablecimientoexterno = prepararActualizar(usuarioActual, rtpsEstablecimientoexterno);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, rtpsEstablecimientoexterno);
		if (!lst.isEmpty())
			return lst;
		
		// TODO RtpsEstablecimientoexterno.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		if (rtpsEstablecimientoexterno.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEstablecimientoexterno.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEstablecimientoexterno = prepararEliminar(usuarioActual, rtpsEstablecimientoexterno);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO RtpsEstablecimientoexterno.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEstablecimientoexternoPk pk) throws Exception {
		BeanRtpsEstablecimientoexterno bean = rtpsEstablecimientoexternoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pcodigoautomatico) throws Exception {
		return coreEliminar(usuarioActual,new BeanRtpsEstablecimientoexternoPk( pcodigoautomatico));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno) throws Exception {
		if (rtpsEstablecimientoexterno.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEstablecimientoexterno.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEstablecimientoexterno = prepararAnular(usuarioActual, rtpsEstablecimientoexterno);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO RtpsEstablecimientoexterno.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEstablecimientoexternoPk pk) throws Exception {
		BeanRtpsEstablecimientoexterno bean = rtpsEstablecimientoexternoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreAnular(usuarioActual,new BeanRtpsEstablecimientoexternoPk( pcodigoautomatico));
	}

	public DtoComunRtpsEstablecimientoexterno core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunRtpsEstablecimientoexterno dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanRtpsEstablecimientoexterno rtpsEstablecimientoexterno = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, rtpsEstablecimientoexterno);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, rtpsEstablecimientoexterno);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, rtpsEstablecimientoexterno);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, rtpsEstablecimientoexterno);
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
