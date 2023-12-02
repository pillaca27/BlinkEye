package net.royal.spring.core.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.TiposervicioDaoImpl;
import net.royal.spring.core.dominio.BeanTiposervicio;
import net.royal.spring.core.dominio.BeanTiposervicioPk;
import net.royal.spring.core.dominio.dto.DtoComunTiposervicio;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarTiposervicio")
public class TiposervicioServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarTiposervicio";
	private static Logger logger = LogManager.getLogger(TiposervicioServicioValidar.class);

	@Autowired
	private TiposervicioDaoImpl tiposervicioDao;

	private BeanTiposervicio prepararBasico(SeguridadUsuarioActual usuarioActual,BeanTiposervicio tiposervicio, Boolean flgInsertar) {
     tiposervicio.setUltimousuario(usuarioActual.getUsuario());
     tiposervicio.setUltimafechamodif(new Date());
		
		// TODO Tiposervicio : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return tiposervicio;
	}

	public BeanTiposervicio prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanTiposervicio tiposervicio) {
		if (tiposervicio == null)
			return tiposervicio;
		if (tiposervicio.getAuxFlgPreparadoBoolean())
			return tiposervicio;
		tiposervicio = prepararBasico(usuarioActual,tiposervicio, true);
		tiposervicio.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		tiposervicio.setUuid(UUID.randomUUID().toString());
		// TODO Tiposervicio.Insertar : valores por defecto
		
		return tiposervicio;
	}

	public BeanTiposervicio prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanTiposervicio tiposervicio) {
		if (tiposervicio == null)
			return tiposervicio;
		if (tiposervicio.getAuxFlgPreparadoBoolean())
			return tiposervicio;
		tiposervicio = prepararBasico(usuarioActual,tiposervicio, false);
		tiposervicio.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tiposervicio.Actualizar : valores por defecto
		
		return tiposervicio;
	}

	public BeanTiposervicio prepararAnular(SeguridadUsuarioActual usuarioActual,BeanTiposervicio tiposervicio) {
		if (tiposervicio == null)
			return tiposervicio;
		if (tiposervicio.getAuxFlgPreparadoBoolean())
			return tiposervicio;
		tiposervicio = prepararBasico(usuarioActual, tiposervicio, false);
		tiposervicio.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tiposervicio.Anular : valores por defecto
		
		return tiposervicio;
	}

	public BeanTiposervicio prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanTiposervicio tiposervicio) {
		if (tiposervicio == null)
			return tiposervicio;
		if (tiposervicio.getAuxFlgPreparadoBoolean())
			return tiposervicio;
		tiposervicio.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tiposervicio.Eliminar : valores por defecto
		
		return tiposervicio;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanTiposervicio tiposervicio) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (tiposervicio == null)
			lst.add(this.getMsjUsuarioError("tiposervicio.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (tiposervicio.getPk() != null) {
			Set<ConstraintViolation<BeanTiposervicioPk>> reglasPk = validator.validate(tiposervicio.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanTiposervicio>> reglas = validator.validate(tiposervicio);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Tiposervicio : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanTiposervicio tiposervicio) {
		if (tiposervicio.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tiposervicio.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(tiposervicio.getPk().getTiposervicio())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIP_SERVICIO_CODIGO));
		}else {
			BeanTiposervicio servicioDto= tiposervicioDao.obtenerPorId(tiposervicio.getPk());
			if(servicioDto != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIP_SERVICIO_CODIGO_VALID));
			}
		}
		if(UString.esNuloVacio(tiposervicio.getDescripcion())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIP_SERVICIO_DESCRI));
		}
		if(UString.esNuloVacio(tiposervicio.getRegimenfiscal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIP_SERVICIO_REGIMEN));
		}
			

		if (!lstRes.isEmpty())
			return lstRes;
		
		tiposervicio = prepararInsertar(usuarioActual, tiposervicio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tiposervicio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Tiposervicio.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanTiposervicio tiposervicio) {
		if (tiposervicio.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tiposervicio.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(tiposervicio.getDescripcion())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIP_SERVICIO_DESCRI));
		}
		if(UString.esNuloVacio(tiposervicio.getRegimenfiscal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_TIP_SERVICIO_REGIMEN));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		tiposervicio = prepararActualizar(usuarioActual, tiposervicio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tiposervicio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Tiposervicio.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanTiposervicio tiposervicio) {
		if (tiposervicio.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tiposervicio.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tiposervicio = prepararEliminar(usuarioActual, tiposervicio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Tiposervicio.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanTiposervicioPk pk) {
		BeanTiposervicio bean = tiposervicioDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String ptiposervicio) {
		return coreEliminar(usuarioActual,new BeanTiposervicioPk( ptiposervicio));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanTiposervicio tiposervicio) {
		if (tiposervicio.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tiposervicio.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tiposervicio = prepararAnular(usuarioActual, tiposervicio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Tiposervicio.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanTiposervicioPk pk) {
		BeanTiposervicio bean = tiposervicioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String ptiposervicio) {
		return coreAnular(usuarioActual,new BeanTiposervicioPk( ptiposervicio));
	}

	public DtoComunTiposervicio core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunTiposervicio dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanTiposervicio tiposervicio = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, tiposervicio);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, tiposervicio);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, tiposervicio);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, tiposervicio);
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
	
	public BeanTiposervicio prepararAuditoria(SeguridadUsuarioActual usuarioActual, BeanTiposervicio asAutorizacion) {
		asAutorizacion.setUltimousuario(usuarioActual.getUsuario());
		asAutorizacion.setUltimafechamodif(new Date());
		return asAutorizacion;
	}

}
