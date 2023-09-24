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
import net.royal.spring.core.dao.impl.ProveedormastDaoImpl;
import net.royal.spring.core.dominio.BeanProveedormast;
import net.royal.spring.core.dominio.BeanProveedormastPk;
import net.royal.spring.core.dominio.dto.DtoComunProveedormast;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarProveedormast")
public class ProveedormastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarProveedormast";
	private static Logger logger = LogManager.getLogger(ProveedormastServicioValidar.class);

	@Autowired
	private ProveedormastDaoImpl proveedormastDao;

	private BeanProveedormast prepararBasico(SeguridadUsuarioActual usuarioActual,BeanProveedormast proveedormast, Boolean flgInsertar) {
		
		// TODO Proveedormast : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		proveedormast.setComentariofecha(new Date());
		return proveedormast;
	}

	public BeanProveedormast prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanProveedormast proveedormast) {
		if (proveedormast == null)
			return proveedormast;
		if (proveedormast.getAuxFlgPreparadoBoolean())
			return proveedormast;
		proveedormast = prepararBasico(usuarioActual,proveedormast, true);
		proveedormast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Proveedormast.Insertar : valores por defecto
		
		return proveedormast;
	}

	public BeanProveedormast prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanProveedormast proveedormast) {
		if (proveedormast == null)
			return proveedormast;
		if (proveedormast.getAuxFlgPreparadoBoolean())
			return proveedormast;
		proveedormast = prepararBasico(usuarioActual,proveedormast, false);
		proveedormast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Proveedormast.Actualizar : valores por defecto
		
		return proveedormast;
	}

	public BeanProveedormast prepararAnular(SeguridadUsuarioActual usuarioActual,BeanProveedormast proveedormast) {
		if (proveedormast == null)
			return proveedormast;
		if (proveedormast.getAuxFlgPreparadoBoolean())
			return proveedormast;
		proveedormast = prepararBasico(usuarioActual, proveedormast, false);
		proveedormast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Proveedormast.Anular : valores por defecto
		
		return proveedormast;
	}

	public BeanProveedormast prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanProveedormast proveedormast) {
		if (proveedormast == null)
			return proveedormast;
		if (proveedormast.getAuxFlgPreparadoBoolean())
			return proveedormast;
		proveedormast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Proveedormast.Eliminar : valores por defecto
		
		return proveedormast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanProveedormast proveedormast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (proveedormast == null)
			lst.add(this.getMsjUsuarioError("proveedormast.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (proveedormast.getPk() != null) {
			Set<ConstraintViolation<BeanProveedormastPk>> reglasPk = validator.validate(proveedormast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanProveedormast>> reglas = validator.validate(proveedormast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		if(UString.esNuloVacio(proveedormast.getTipodocumentodefault())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PROV_RESTRICCION_TIPO_DOCUMENTO));
		}	
		if(UString.esNuloVacio(proveedormast.getTipopago())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PROV_TIPO_PAGO));
		}
		if(UString.esNuloVacio(proveedormast.getTiposervicio())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PROV_TIPO_SERVICIO));
		}
		if(UString.esNuloVacio(proveedormast.getMonedapago())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PROV_MONEDA_PAGO));
		}
		if(UString.esNuloVacio(proveedormast.getFormadepago())) {
			lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PROV_FORMA_PAGO));
		}
		if(!UString.esNuloVacio(proveedormast.getExoneradomediopresentacion())) {
			
			if(proveedormast.getExoneradofechafinal() == null) {
				lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PROV_FECHA_EXONERADO));
			}else {
				System.out.println("VALID");
			}
		}
		
		if(!UString.esNuloVacio(proveedormast.getDetraccioncodigoflag())) {
			
			if(UString.esNuloVacio(proveedormast.getDetraccioncodigo())) {
				lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PROV_DETRACCION_SELECTOR));
			}
			if(UString.esNuloVacio(proveedormast.getDetraccioncuentabancaria())) {
				lst.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_PROV_CUENTA_DETRACCION));
			}
		}
		// TODO Proveedormast : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanProveedormast proveedormast) {
		if (proveedormast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		proveedormast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		proveedormast = prepararInsertar(usuarioActual, proveedormast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, proveedormast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Proveedormast.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanProveedormast proveedormast) {
		if (proveedormast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		proveedormast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		proveedormast = prepararActualizar(usuarioActual, proveedormast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, proveedormast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Proveedormast.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanProveedormast proveedormast) {
		if (proveedormast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		proveedormast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		proveedormast = prepararEliminar(usuarioActual, proveedormast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Proveedormast.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanProveedormastPk pk) {
		BeanProveedormast bean = proveedormastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer pproveedor) {
		return coreEliminar(usuarioActual,new BeanProveedormastPk( pproveedor));
	}


	public DtoComunProveedormast core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunProveedormast dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanProveedormast proveedormast = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, proveedormast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, proveedormast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, proveedormast);
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
