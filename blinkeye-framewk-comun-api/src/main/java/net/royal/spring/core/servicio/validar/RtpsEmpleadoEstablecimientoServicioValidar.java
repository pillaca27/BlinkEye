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
import net.royal.spring.core.dao.impl.RtpsEmpleadoEstablecimientoDaoImpl;
import net.royal.spring.core.dao.impl.RtpsEstablecimientoexternoDaoImpl;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimiento;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoPk;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpleadoEstablecimiento;

@Service (value = "BeanValidarRtpsEmpleadoEstablecimiento")
public class RtpsEmpleadoEstablecimientoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarRtpsEmpleadoEstablecimiento";
	private static Logger logger = LogManager.getLogger(RtpsEmpleadoEstablecimientoServicioValidar.class);

	@Autowired
	private RtpsEmpleadoEstablecimientoDaoImpl rtpsEmpleadoEstablecimientoDao;

	private BeanRtpsEmpleadoEstablecimiento prepararBasico(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento, Boolean flgInsertar) throws Exception {
		rtpsEmpleadoEstablecimiento.setUltimousuario(usuarioActual.getUsuario());
		rtpsEmpleadoEstablecimiento.setUltimafechamodif(new Date());
		
		// TODO RtpsEstablecimientoexterno : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return rtpsEmpleadoEstablecimiento;
	}

	public BeanRtpsEmpleadoEstablecimiento prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		if (rtpsEmpleadoEstablecimiento == null)
			return rtpsEmpleadoEstablecimiento;
		if (rtpsEmpleadoEstablecimiento.getAuxFlgPreparadoBoolean())
			return rtpsEmpleadoEstablecimiento;
		rtpsEmpleadoEstablecimiento = prepararBasico(usuarioActual,rtpsEmpleadoEstablecimiento, true);
		rtpsEmpleadoEstablecimiento.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Insertar : valores por defecto
		
		return rtpsEmpleadoEstablecimiento;
	}

	public BeanRtpsEmpleadoEstablecimiento prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		if (rtpsEmpleadoEstablecimiento == null)
			return rtpsEmpleadoEstablecimiento;
		if (rtpsEmpleadoEstablecimiento.getAuxFlgPreparadoBoolean())
			return rtpsEmpleadoEstablecimiento;
		rtpsEmpleadoEstablecimiento = prepararBasico(usuarioActual,rtpsEmpleadoEstablecimiento, false);
		rtpsEmpleadoEstablecimiento.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Actualizar : valores por defecto
		
		return rtpsEmpleadoEstablecimiento;
	}

	public BeanRtpsEmpleadoEstablecimiento prepararAnular(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		if (rtpsEmpleadoEstablecimiento == null)
			return rtpsEmpleadoEstablecimiento;
		if (rtpsEmpleadoEstablecimiento.getAuxFlgPreparadoBoolean())
			return rtpsEmpleadoEstablecimiento;
		rtpsEmpleadoEstablecimiento = prepararBasico(usuarioActual, rtpsEmpleadoEstablecimiento, false);
		rtpsEmpleadoEstablecimiento.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Anular : valores por defecto
		
		return rtpsEmpleadoEstablecimiento;
	}

	public BeanRtpsEmpleadoEstablecimiento prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		if (rtpsEmpleadoEstablecimiento == null)
			return rtpsEmpleadoEstablecimiento;
		if (rtpsEmpleadoEstablecimiento.getAuxFlgPreparadoBoolean())
			return rtpsEmpleadoEstablecimiento;
		rtpsEmpleadoEstablecimiento.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEstablecimientoexterno.Eliminar : valores por defecto
		
		return rtpsEmpleadoEstablecimiento;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (rtpsEmpleadoEstablecimiento == null)
			lst.add(this.getMsjUsuarioError("rtpsestablecimiento.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (rtpsEmpleadoEstablecimiento.getPk() != null) {
			Set<ConstraintViolation<BeanRtpsEmpleadoEstablecimientoPk>> reglasPk = validator.validate(rtpsEmpleadoEstablecimiento.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanRtpsEmpleadoEstablecimiento>> reglas = validator.validate(rtpsEmpleadoEstablecimiento);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO RtpsEstablecimientoexterno : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		if (rtpsEmpleadoEstablecimiento.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpleadoEstablecimiento.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpleadoEstablecimiento = prepararInsertar(usuarioActual, rtpsEmpleadoEstablecimiento);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, rtpsEmpleadoEstablecimiento);
		if (!lst.isEmpty())
			return lst;
		
		// TODO RtpsEstablecimientoexterno.Insertar : validaciones
		BeanRtpsEmpleadoEstablecimiento b1 = rtpsEmpleadoEstablecimientoDao.obtenerPorId(rtpsEmpleadoEstablecimiento.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		if (rtpsEmpleadoEstablecimiento.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpleadoEstablecimiento.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpleadoEstablecimiento = prepararActualizar(usuarioActual, rtpsEmpleadoEstablecimiento);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, rtpsEmpleadoEstablecimiento);
		if (!lst.isEmpty())
			return lst;
		
		// TODO RtpsEstablecimientoexterno.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		if (rtpsEmpleadoEstablecimiento.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpleadoEstablecimiento.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpleadoEstablecimiento = prepararEliminar(usuarioActual, rtpsEmpleadoEstablecimiento);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO RtpsEstablecimientoexterno.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoPk pk) throws Exception {
		BeanRtpsEmpleadoEstablecimiento bean = rtpsEmpleadoEstablecimientoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pcodigoautomatico) throws Exception {
		return coreEliminar(usuarioActual,new BeanRtpsEmpleadoEstablecimientoPk( pcodigoautomatico, null, pcodigoautomatico));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		if (rtpsEmpleadoEstablecimiento.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpleadoEstablecimiento.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpleadoEstablecimiento = prepararAnular(usuarioActual, rtpsEmpleadoEstablecimiento);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO RtpsEstablecimientoexterno.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoPk pk) throws Exception {
		BeanRtpsEmpleadoEstablecimiento bean = rtpsEmpleadoEstablecimientoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreAnular(usuarioActual,new BeanRtpsEmpleadoEstablecimientoPk( pcodigoautomatico, null, pcodigoautomatico));
	}

	public DtoComunRtpsEmpleadoEstablecimiento core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunRtpsEmpleadoEstablecimiento dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, rtpsEmpleadoEstablecimiento);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, rtpsEmpleadoEstablecimiento);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, rtpsEmpleadoEstablecimiento);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, rtpsEmpleadoEstablecimiento);
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
