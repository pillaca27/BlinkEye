package net.royal.spring.contabilidad.servicio.validar;

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
import net.royal.spring.contabilidad.dao.impl.AcSucursalDaoImpl;
import net.royal.spring.contabilidad.dominio.BeanAcSucursal;
import net.royal.spring.contabilidad.dominio.BeanAcSucursalPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcSucursal;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
 

@Service (value = "BeanValidarAcSucursal")
public class AcSucursalServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAcSucursal";
	private static Logger logger = LogManager.getLogger(AcSucursalServicioValidar.class);

	@Autowired
	private AcSucursalDaoImpl acSucursalDao;

	private BeanAcSucursal prepararBasico(SeguridadUsuarioActual usuarioActual,BeanAcSucursal acSucursal, Boolean flgInsertar) {
     acSucursal.setUltimousuario(usuarioActual.getUsuario());
     acSucursal.setUltimafechamodif(new Date());
		
		// TODO AcSucursal : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return acSucursal;
	}

	public BeanAcSucursal prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanAcSucursal acSucursal) {
		if (acSucursal == null)
			return acSucursal;
		if (acSucursal.getAuxFlgPreparadoBoolean())
			return acSucursal;
		acSucursal = prepararBasico(usuarioActual,acSucursal, true);
		acSucursal.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		acSucursal.setUuid(UUID.randomUUID().toString());
		// TODO AcSucursal.Insertar : valores por defecto
		
		return acSucursal;
	}

	public BeanAcSucursal prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanAcSucursal acSucursal) {
		if (acSucursal == null)
			return acSucursal;
		if (acSucursal.getAuxFlgPreparadoBoolean())
			return acSucursal;
		acSucursal = prepararBasico(usuarioActual,acSucursal, false);
		acSucursal.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursal.Actualizar : valores por defecto
		
		return acSucursal;
	}

	public BeanAcSucursal prepararAnular(SeguridadUsuarioActual usuarioActual,BeanAcSucursal acSucursal) {
		if (acSucursal == null)
			return acSucursal;
		if (acSucursal.getAuxFlgPreparadoBoolean())
			return acSucursal;
		acSucursal = prepararBasico(usuarioActual, acSucursal, false);
		acSucursal.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursal.Anular : valores por defecto
		
		return acSucursal;
	}

	public BeanAcSucursal prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanAcSucursal acSucursal) {
		if (acSucursal == null)
			return acSucursal;
		if (acSucursal.getAuxFlgPreparadoBoolean())
			return acSucursal;
		acSucursal.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO AcSucursal.Eliminar : valores por defecto
		
		return acSucursal;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanAcSucursal acSucursal) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (acSucursal == null)
			lst.add(this.getMsjUsuarioError("acsucursal.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (acSucursal.getPk() != null) {
			Set<ConstraintViolation<BeanAcSucursalPk>> reglasPk = validator.validate(acSucursal.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanAcSucursal>> reglas = validator.validate(acSucursal);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO AcSucursal : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanAcSucursal acSucursal) {
		if (acSucursal.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursal.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(acSucursal.getPk().getSucursal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_SUCURSAL_CODIGO));
		}else {
			BeanAcSucursal dto= acSucursalDao.obtenerPorId(acSucursal.getPk());
			if(dto != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_SUCURSAL_CODIGO_VALID));
			}
		}
		if(UString.esNuloVacio(acSucursal.getDescripcionlocal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_SUCURSAL_DESCRIPCION));
		}	
		if(UString.esNuloVacio(acSucursal.getSucursalgrupo())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_SUCURSAL_GRUPO));
		}	
		if (!lstRes.isEmpty())
			return lstRes;
		
		acSucursal = prepararInsertar(usuarioActual, acSucursal);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acSucursal);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcSucursal.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcSucursal acSucursal) {
		if (acSucursal.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursal.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursal = prepararActualizar(usuarioActual, acSucursal);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acSucursal);
		if (!lst.isEmpty())
			return lst;
		
		// TODO AcSucursal.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcSucursal acSucursal) {
		if (acSucursal.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursal.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(acSucursal.getDescripcionlocal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_SUCURSAL_DESCRIPCION));
		}	
		if(UString.esNuloVacio(acSucursal.getSucursalgrupo())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_SUCURSAL_GRUPO));
		}	
		if (!lstRes.isEmpty())
			return lstRes;
		
		acSucursal = prepararEliminar(usuarioActual, acSucursal);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcSucursal.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanAcSucursalPk pk) {
		BeanAcSucursal bean = acSucursalDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String psucursal) {
		return coreEliminar(usuarioActual,new BeanAcSucursalPk( psucursal));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursal acSucursal) {
		if (acSucursal.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acSucursal.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acSucursal = prepararAnular(usuarioActual, acSucursal);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO AcSucursal.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcSucursalPk pk) {
		BeanAcSucursal bean = acSucursalDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String psucursal) {
		return coreAnular(usuarioActual,new BeanAcSucursalPk( psucursal));
	}

	public DtoComunAcSucursal core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunAcSucursal dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanAcSucursal acSucursal = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, acSucursal);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, acSucursal);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, acSucursal);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, acSucursal);
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

	public BeanAcSucursal prepararAuditoria(SeguridadUsuarioActual usuarioActual, BeanAcSucursal asAutorizacion) {
		asAutorizacion.setUltimousuario(usuarioActual.getUsuario());
		asAutorizacion.setUltimafechamodif(new Date());
		return asAutorizacion;
	}
}
