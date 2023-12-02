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
import net.royal.spring.core.dao.impl.CompaniamastDaoImpl;
import net.royal.spring.core.dominio.BeanCompaniamast;
import net.royal.spring.core.dominio.BeanCompaniamastPk;
//import net.royal.spring.core.dominio.Departmentmst;
import net.royal.spring.core.dominio.dto.DtoComunCompaniamast;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarCompaniamast")
public class CompaniamastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarCompaniamast";
	private static Logger logger = LogManager.getLogger(CompaniamastServicioValidar.class);

	@Autowired
	private CompaniamastDaoImpl companiamastDao;

	private BeanCompaniamast prepararBasico(SeguridadUsuarioActual usuarioActual,BeanCompaniamast companiamast, Boolean flgInsertar) {
     companiamast.setUltimousuario(usuarioActual.getUsuario());
     companiamast.setUltimafechamodif(new Date());
		
		// TODO Companiamast : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return companiamast;
	}

	public BeanCompaniamast prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanCompaniamast companiamast) {
		if (companiamast == null)
			return companiamast;
		if (companiamast.getAuxFlgPreparadoBoolean())
			return companiamast;
		companiamast = prepararBasico(usuarioActual,companiamast, true);
		companiamast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		companiamast.setUuid(UUID.randomUUID().toString());
		// TODO Companiamast.Insertar : valores por defecto
		
		return companiamast;
	}

	public BeanCompaniamast prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanCompaniamast companiamast) {
		if (companiamast == null)
			return companiamast;
		if (companiamast.getAuxFlgPreparadoBoolean())
			return companiamast;
		companiamast = prepararBasico(usuarioActual,companiamast, false);
		companiamast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companiamast.Actualizar : valores por defecto
		
		return companiamast;
	}

	public BeanCompaniamast prepararAnular(SeguridadUsuarioActual usuarioActual,BeanCompaniamast companiamast) {
		if (companiamast == null)
			return companiamast;
		if (companiamast.getAuxFlgPreparadoBoolean())
			return companiamast;
		companiamast = prepararBasico(usuarioActual, companiamast, false);
		companiamast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companiamast.Anular : valores por defecto
		
		return companiamast;
	}

	public BeanCompaniamast prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanCompaniamast companiamast) {
		if (companiamast == null)
			return companiamast;
		if (companiamast.getAuxFlgPreparadoBoolean())
			return companiamast;
		companiamast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companiamast.Eliminar : valores por defecto
		
		return companiamast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanCompaniamast companiamast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (companiamast == null)
			lst.add(this.getMsjUsuarioError("companiamast.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (companiamast.getPk() != null) {
			Set<ConstraintViolation<BeanCompaniamastPk>> reglasPk = validator.validate(companiamast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanCompaniamast>> reglas = validator.validate(companiamast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Companiamast : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanCompaniamast companiamast) {
		if (companiamast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		companiamast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(companiamast.getPk().getCompaniacodigo())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_CODIGO));
		}else {
			BeanCompaniamast companiaDto= companiamastDao.obtenerPorId(companiamast.getPk());
			if(companiaDto != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_CODIGO_VALID));
			}
		}
		if(UString.esNuloVacio(companiamast.getDescripcioncorta())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_DESCRIPCION));
		}
		if(UString.esNuloVacio(companiamast.getDescripcionlarga())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_DESCRIPCION_COMPLETA));
		}
		if(UString.esNuloVacio(companiamast.getTipocompania())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_TIPO));
		}
		if(UInteger.esCeroOrNulo(companiamast.getPersona())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_PERSONA));
		}
		if(UString.esNuloVacio(companiamast.getDireccioncomun())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_DIRECCION));
		}
		if(UString.esNuloVacio(companiamast.getDocumentofiscal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_DOCUMENTO));
		}
		if(UString.esNuloVacio(companiamast.getPropietariopordefecto())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_SOCIO));
		}
		if(UString.esNuloVacio(companiamast.getMonedapordefecto())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_MONEDA));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		
		companiamast = prepararInsertar(usuarioActual, companiamast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, companiamast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Companiamast.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanCompaniamast companiamast) {
		if (companiamast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		companiamast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();

		if(UString.esNuloVacio(companiamast.getDescripcioncorta())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_DESCRIPCION));
		}
		if(UString.esNuloVacio(companiamast.getDescripcionlarga())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_DESCRIPCION_COMPLETA));
		}
		if(UString.esNuloVacio(companiamast.getTipocompania())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_TIPO));
		}
		if(UInteger.esCeroOrNulo(companiamast.getPersona())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_PERSONA));
		}
		if(UString.esNuloVacio(companiamast.getDireccioncomun())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_DIRECCION));
		}
		if(UString.esNuloVacio(companiamast.getDocumentofiscal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_DOCUMENTO));
		}
		if(UString.esNuloVacio(companiamast.getPropietariopordefecto())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_SOCIO));
		}
		if(UString.esNuloVacio(companiamast.getMonedapordefecto())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_COMPANIA_MONEDA));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		companiamast = prepararActualizar(usuarioActual, companiamast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, companiamast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Companiamast.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanCompaniamast companiamast) {
		if (companiamast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		companiamast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		companiamast = prepararEliminar(usuarioActual, companiamast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Companiamast.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanCompaniamastPk pk) {
		BeanCompaniamast bean = companiamastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcompaniacodigo) {
		return coreEliminar(usuarioActual,new BeanCompaniamastPk( pcompaniacodigo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanCompaniamast companiamast) {
		if (companiamast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		companiamast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		companiamast = prepararAnular(usuarioActual, companiamast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Companiamast.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanCompaniamastPk pk) {
		BeanCompaniamast bean = companiamastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo) {
		return coreAnular(usuarioActual,new BeanCompaniamastPk( pcompaniacodigo));
	}

	public DtoComunCompaniamast core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunCompaniamast dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanCompaniamast companiamast = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, companiamast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, companiamast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, companiamast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, companiamast);
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

	public BeanCompaniamast prepararAuditoria(SeguridadUsuarioActual usuarioActual, BeanCompaniamast asAutorizacion) {
		asAutorizacion.setUltimousuario(usuarioActual.getUsuario());
		asAutorizacion.setUltimafechamodif(new Date());
		return asAutorizacion;
	}
}
