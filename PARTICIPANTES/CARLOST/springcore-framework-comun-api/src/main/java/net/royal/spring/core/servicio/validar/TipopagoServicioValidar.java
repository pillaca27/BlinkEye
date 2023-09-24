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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.TipopagoDaoImpl;
import net.royal.spring.core.dominio.BeanTipopago;
import net.royal.spring.core.dominio.TipopagoPk;
import net.royal.spring.core.dominio.dto.DtoComunTipopago;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarTipopago")
public class TipopagoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarTipopago";
	private static Logger logger = LogManager.getLogger(TipopagoServicioValidar.class);

	@Autowired
	private TipopagoDaoImpl tipopagoDao;

	private BeanTipopago prepararBasico(SeguridadUsuarioActual usuarioActual,BeanTipopago tipopago, Boolean flgInsertar) {
     tipopago.setUltimousuario(usuarioActual.getUsuario());
     tipopago.setUltimafechamodif(new Date());
		
		// TODO Tipopago : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return tipopago;
	}

	public BeanTipopago prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanTipopago tipopago) {
		if (tipopago == null)
			return tipopago;
		if (tipopago.getAuxFlgPreparadoBoolean())
			return tipopago;
		tipopago = prepararBasico(usuarioActual,tipopago, true);
		tipopago.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tipopago.Insertar : valores por defecto
		
		return tipopago;
	}

	public BeanTipopago prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanTipopago tipopago) {
		if (tipopago == null)
			return tipopago;
		if (tipopago.getAuxFlgPreparadoBoolean())
			return tipopago;
		tipopago = prepararBasico(usuarioActual,tipopago, false);
		tipopago.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tipopago.Actualizar : valores por defecto
		
		return tipopago;
	}

	public BeanTipopago prepararAnular(SeguridadUsuarioActual usuarioActual,BeanTipopago tipopago) {
		if (tipopago == null)
			return tipopago;
		if (tipopago.getAuxFlgPreparadoBoolean())
			return tipopago;
		tipopago = prepararBasico(usuarioActual, tipopago, false);
		tipopago.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tipopago.Anular : valores por defecto
		
		return tipopago;
	}

	public BeanTipopago prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanTipopago tipopago) {
		if (tipopago == null)
			return tipopago;
		if (tipopago.getAuxFlgPreparadoBoolean())
			return tipopago;
		tipopago.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tipopago.Eliminar : valores por defecto
		
		return tipopago;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanTipopago tipopago) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (tipopago == null)
			lst.add(this.getMsjUsuarioError("tipopago.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (tipopago.getPk() != null) {
			Set<ConstraintViolation<TipopagoPk>> reglasPk = validator.validate(tipopago.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanTipopago>> reglas = validator.validate(tipopago);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Tipopago : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanTipopago tipopago) {
		if (tipopago.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipopago.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipopago = prepararInsertar(usuarioActual, tipopago);
		
		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(tipopago.getPk().getTipopago())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIPPAGO_CODIGO));
		}else {
			BeanTipopago tipoPagoDto= tipopagoDao.obtenerPorId(tipopago.getPk());
			if(tipoPagoDto != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIPPAGO_CODIGO_VALID));
			}
		}
		if(UString.esNuloVacio(tipopago.getDescripcion())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIPPAGO_DESCRIPCION));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipopago);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Tipopago.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanTipopago tipopago) {
		if (tipopago.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipopago.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipopago = prepararActualizar(usuarioActual, tipopago);
		
		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(tipopago.getDescripcion())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIPPAGO_DESCRIPCION));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipopago);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Tipopago.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanTipopago tipopago) {
		if (tipopago.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipopago.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipopago = prepararEliminar(usuarioActual, tipopago);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Tipopago.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,TipopagoPk pk) {
		BeanTipopago bean = tipopagoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String ptipopago) {
		return coreEliminar(usuarioActual,new TipopagoPk( ptipopago));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanTipopago tipopago) {
		if (tipopago.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipopago.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipopago = prepararAnular(usuarioActual, tipopago);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Tipopago.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, TipopagoPk pk) {
		BeanTipopago bean = tipopagoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String ptipopago) {
		return coreAnular(usuarioActual,new TipopagoPk( ptipopago));
	}

	public DtoComunTipopago core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunTipopago dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanTipopago tipopago = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, tipopago);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, tipopago);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, tipopago);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, tipopago);
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
