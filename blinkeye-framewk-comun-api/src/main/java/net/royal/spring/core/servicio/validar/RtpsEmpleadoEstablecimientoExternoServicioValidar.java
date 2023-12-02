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
import net.royal.spring.core.dao.impl.RtpsEmpleadoEstablecimientoExternoDaoImpl;
import net.royal.spring.core.dao.impl.RtpsEstablecimientoexternoDaoImpl;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExternoPk;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;

@Service (value = "BeanValidarRtpsEmpleadoEstablecimientoExterno")
public class RtpsEmpleadoEstablecimientoExternoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarRtpsEmpleadoEstablecimientoExterno";
	private static Logger logger = LogManager.getLogger(RtpsEmpleadoEstablecimientoExternoServicioValidar.class);

	@Autowired
	private RtpsEmpleadoEstablecimientoExternoDaoImpl rtpsEmpleadoEstablecimientoExternoDao;

	private BeanRtpsEmpleadoEstablecimientoExterno prepararBasico(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno, Boolean flgInsertar) throws Exception {
		rtpsEmpleadoEstablecimientoExterno.setUltimousuario(usuarioActual.getUsuario());
		rtpsEmpleadoEstablecimientoExterno.setUltimafechamodif(new Date());
		
		// TODO RtpsEstablecimientoexterno : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return rtpsEmpleadoEstablecimientoExterno;
	}

	public BeanRtpsEmpleadoEstablecimientoExterno prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		if (rtpsEmpleadoEstablecimientoExterno == null)
			return rtpsEmpleadoEstablecimientoExterno;
		if (rtpsEmpleadoEstablecimientoExterno.getAuxFlgPreparadoBoolean())
			return rtpsEmpleadoEstablecimientoExterno;
		rtpsEmpleadoEstablecimientoExterno = prepararBasico(usuarioActual,rtpsEmpleadoEstablecimientoExterno, true);
		rtpsEmpleadoEstablecimientoExterno.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Insertar : valores por defecto
		
		return rtpsEmpleadoEstablecimientoExterno;
	}

	public BeanRtpsEmpleadoEstablecimientoExterno prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		if (rtpsEmpleadoEstablecimientoExterno == null)
			return rtpsEmpleadoEstablecimientoExterno;
		if (rtpsEmpleadoEstablecimientoExterno.getAuxFlgPreparadoBoolean())
			return rtpsEmpleadoEstablecimientoExterno;
		rtpsEmpleadoEstablecimientoExterno = prepararBasico(usuarioActual,rtpsEmpleadoEstablecimientoExterno, false);
		rtpsEmpleadoEstablecimientoExterno.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Actualizar : valores por defecto
		
		return rtpsEmpleadoEstablecimientoExterno;
	}

	public BeanRtpsEmpleadoEstablecimientoExterno prepararAnular(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		if (rtpsEmpleadoEstablecimientoExterno == null)
			return rtpsEmpleadoEstablecimientoExterno;
		if (rtpsEmpleadoEstablecimientoExterno.getAuxFlgPreparadoBoolean())
			return rtpsEmpleadoEstablecimientoExterno;
		rtpsEmpleadoEstablecimientoExterno = prepararBasico(usuarioActual, rtpsEmpleadoEstablecimientoExterno, false);
		rtpsEmpleadoEstablecimientoExterno.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Anular : valores por defecto
		
		return rtpsEmpleadoEstablecimientoExterno;
	}

	public BeanRtpsEmpleadoEstablecimientoExterno prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		if (rtpsEmpleadoEstablecimientoExterno == null)
			return rtpsEmpleadoEstablecimientoExterno;
		if (rtpsEmpleadoEstablecimientoExterno.getAuxFlgPreparadoBoolean())
			return rtpsEmpleadoEstablecimientoExterno;
		rtpsEmpleadoEstablecimientoExterno.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Eliminar : valores por defecto
		
		return rtpsEmpleadoEstablecimientoExterno;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (rtpsEmpleadoEstablecimientoExterno == null)
			lst.add(this.getMsjUsuarioError("rtpsestablecimientoexterno.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (rtpsEmpleadoEstablecimientoExterno.getPk() != null) {
			Set<ConstraintViolation<BeanRtpsEmpleadoEstablecimientoExternoPk>> reglasPk = validator.validate(rtpsEmpleadoEstablecimientoExterno.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanRtpsEmpleadoEstablecimientoExterno>> reglas = validator.validate(rtpsEmpleadoEstablecimientoExterno);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO RtpsEstablecimientoexterno : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		if (rtpsEmpleadoEstablecimientoExterno.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpleadoEstablecimientoExterno.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpleadoEstablecimientoExterno = prepararInsertar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		if (!lst.isEmpty())
			return lst;
		
		// TODO RtpsEstablecimientoexterno.Insertar : validaciones
		BeanRtpsEmpleadoEstablecimientoExterno b1 = rtpsEmpleadoEstablecimientoExternoDao.obtenerPorId(rtpsEmpleadoEstablecimientoExterno.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		if (rtpsEmpleadoEstablecimientoExterno.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpleadoEstablecimientoExterno.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpleadoEstablecimientoExterno = prepararActualizar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		if (!lst.isEmpty())
			return lst;
		
		// TODO RtpsEstablecimientoexterno.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		if (rtpsEmpleadoEstablecimientoExterno.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpleadoEstablecimientoExterno.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpleadoEstablecimientoExterno = prepararEliminar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO RtpsEstablecimientoexterno.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoExternoPk pk) throws Exception {
		BeanRtpsEmpleadoEstablecimientoExterno bean = rtpsEmpleadoEstablecimientoExternoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pcodigoautomatico) throws Exception {
		return coreEliminar(usuarioActual,new BeanRtpsEmpleadoEstablecimientoExternoPk( pcodigoautomatico));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		if (rtpsEmpleadoEstablecimientoExterno.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpleadoEstablecimientoExterno.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpleadoEstablecimientoExterno = prepararAnular(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO RtpsEstablecimientoexterno.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoExternoPk pk) throws Exception {
		BeanRtpsEmpleadoEstablecimientoExterno bean = rtpsEmpleadoEstablecimientoExternoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreAnular(usuarioActual,new BeanRtpsEmpleadoEstablecimientoExternoPk( pcodigoautomatico));
	}

	public DtoComunRtpsEmpleadoEstablecimientoExterno core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
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
