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
import net.royal.spring.core.dao.impl.RtpsEmpresaexternaDaoImpl;

import net.royal.spring.core.dominio.BeanRtpsEmpresaexterna;
import net.royal.spring.core.dominio.BeanRtpsEmpresaexternaPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpresaexterna;

@Service (value = "BeanValidarRtpsEmpresaexterna")
public class RtpsEmpresaexternaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarRtpsEmpresaexterna";
	private static Logger logger = LogManager.getLogger(RtpsEmpresaexternaServicioValidar.class);

	@Autowired
	private RtpsEmpresaexternaDaoImpl rtpsEmpresaexternaDao;

	private BeanRtpsEmpresaexterna prepararBasico(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpresaexterna rtpsEmpresaexterna, Boolean flgInsertar) throws Exception {
     rtpsEmpresaexterna.setUltimousuario(usuarioActual.getUsuario());
     rtpsEmpresaexterna.setUltimafechamodif(new Date());
		
		// TODO RtpsEmpresaexterna : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return rtpsEmpresaexterna;
	}

	public BeanRtpsEmpresaexterna prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		if (rtpsEmpresaexterna == null)
			return rtpsEmpresaexterna;
		if (rtpsEmpresaexterna.getAuxFlgPreparadoBoolean())
			return rtpsEmpresaexterna;
		rtpsEmpresaexterna = prepararBasico(usuarioActual,rtpsEmpresaexterna, true);
		rtpsEmpresaexterna.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEmpresaexterna.Insertar : valores por defecto
		
		return rtpsEmpresaexterna;
	}

	public BeanRtpsEmpresaexterna prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		if (rtpsEmpresaexterna == null)
			return rtpsEmpresaexterna;
		if (rtpsEmpresaexterna.getAuxFlgPreparadoBoolean())
			return rtpsEmpresaexterna;
		rtpsEmpresaexterna = prepararBasico(usuarioActual,rtpsEmpresaexterna, false);
		rtpsEmpresaexterna.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEmpresaexterna.Actualizar : valores por defecto
		
		return rtpsEmpresaexterna;
	}

	public BeanRtpsEmpresaexterna prepararAnular(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		if (rtpsEmpresaexterna == null)
			return rtpsEmpresaexterna;
		if (rtpsEmpresaexterna.getAuxFlgPreparadoBoolean())
			return rtpsEmpresaexterna;
		rtpsEmpresaexterna = prepararBasico(usuarioActual, rtpsEmpresaexterna, false);
		rtpsEmpresaexterna.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEmpresaexterna.Anular : valores por defecto
		
		return rtpsEmpresaexterna;
	}

	public BeanRtpsEmpresaexterna prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		if (rtpsEmpresaexterna == null)
			return rtpsEmpresaexterna;
		if (rtpsEmpresaexterna.getAuxFlgPreparadoBoolean())
			return rtpsEmpresaexterna;
		rtpsEmpresaexterna.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO RtpsEmpresaexterna.Eliminar : valores por defecto
		
		return rtpsEmpresaexterna;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (rtpsEmpresaexterna == null)
			lst.add(this.getMsjUsuarioError("rtpsempresaexterna.constraints.notnull"));
		
		
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (rtpsEmpresaexterna.getPk() != null) {
			Set<ConstraintViolation<BeanRtpsEmpresaexternaPk>> reglasPk = validator.validate(rtpsEmpresaexterna.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanRtpsEmpresaexterna>> reglas = validator.validate(rtpsEmpresaexterna);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO RtpsEmpresaexterna : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		if (rtpsEmpresaexterna.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpresaexterna.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpresaexterna = prepararInsertar(usuarioActual, rtpsEmpresaexterna);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, rtpsEmpresaexterna);
		
		
		if (UString.esNuloVacio(rtpsEmpresaexterna.getRucempresa())) {
			lst.add(this.getMsjUsuarioError("Debe Ingresar RUC."));
		}
		
		if (UString.esNuloVacio(rtpsEmpresaexterna.getRazonsocial())) {
			lst.add(this.getMsjUsuarioError("Debe Ingresar Razon Social."));
		}
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO RtpsEmpresaexterna.Insertar : validaciones
		BeanRtpsEmpresaexterna b1 = rtpsEmpresaexternaDao.obtenerPorId(rtpsEmpresaexterna.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		if (rtpsEmpresaexterna.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpresaexterna.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpresaexterna = prepararActualizar(usuarioActual, rtpsEmpresaexterna);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, rtpsEmpresaexterna);
		
		
		if (UString.esNuloVacio(rtpsEmpresaexterna.getRucempresa())) {
			lst.add(this.getMsjUsuarioError("Debe Ingresar RUC."));
		}
		
		if (UString.esNuloVacio(rtpsEmpresaexterna.getRazonsocial())) {
			lst.add(this.getMsjUsuarioError("Debe Ingresar Razon Social."));
		}
		
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO RtpsEmpresaexterna.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		if (rtpsEmpresaexterna.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpresaexterna.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpresaexterna = prepararEliminar(usuarioActual, rtpsEmpresaexterna);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO RtpsEmpresaexterna.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpresaexternaPk pk) throws Exception {
		BeanRtpsEmpresaexterna bean = rtpsEmpresaexternaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pcodigoautomatico) throws Exception {
		return coreEliminar(usuarioActual,new BeanRtpsEmpresaexternaPk( pcodigoautomatico));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpresaexterna rtpsEmpresaexterna) throws Exception {
		if (rtpsEmpresaexterna.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		rtpsEmpresaexterna.setAuxFlgValidadoBoolean(Boolean.TRUE);

		rtpsEmpresaexterna = prepararAnular(usuarioActual, rtpsEmpresaexterna);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO RtpsEmpresaexterna.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpresaexternaPk pk) throws Exception {
		BeanRtpsEmpresaexterna bean = rtpsEmpresaexternaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreAnular(usuarioActual,new BeanRtpsEmpresaexternaPk( pcodigoautomatico));
	}

	public DtoComunRtpsEmpresaexterna core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunRtpsEmpresaexterna dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanRtpsEmpresaexterna rtpsEmpresaexterna = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, rtpsEmpresaexterna);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, rtpsEmpresaexterna);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, rtpsEmpresaexterna);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, rtpsEmpresaexterna);
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
