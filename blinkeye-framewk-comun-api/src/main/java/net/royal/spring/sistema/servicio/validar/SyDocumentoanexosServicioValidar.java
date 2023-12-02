package net.royal.spring.sistema.servicio.validar;

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

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.sistema.dominio.BeanSyDocumentoanexos;
import net.royal.spring.sistema.dominio.BeanSyDocumentoanexosPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyDocumentoanexos;
import net.royal.spring.sistema.dao.impl.SyDocumentoanexosDaoImpl;

@Service (value = "BeanValidarSyDocumentoanexos")
public class SyDocumentoanexosServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyDocumentoanexos";
	private static Logger logger = LogManager.getLogger(SyDocumentoanexosServicioValidar.class);

	@Autowired
	private SyDocumentoanexosDaoImpl syDocumentoanexosDao;

	private BeanSyDocumentoanexos prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSyDocumentoanexos syDocumentoanexos, Boolean flgInsertar) throws Exception {
     syDocumentoanexos.setUltimousuario(usuarioActual.getUsuario());
     syDocumentoanexos.setUltimafechamodif(new Date());
		
		// TODO SyDocumentoanexos : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syDocumentoanexos;
	}

	public BeanSyDocumentoanexos prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		if (syDocumentoanexos == null)
			return syDocumentoanexos;
		if (syDocumentoanexos.getAuxFlgPreparadoBoolean())
			return syDocumentoanexos;
		syDocumentoanexos = prepararBasico(usuarioActual,syDocumentoanexos, true);
		syDocumentoanexos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyDocumentoanexos.Insertar : valores por defecto
		
		return syDocumentoanexos;
	}

	public BeanSyDocumentoanexos prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		if (syDocumentoanexos == null)
			return syDocumentoanexos;
		if (syDocumentoanexos.getAuxFlgPreparadoBoolean())
			return syDocumentoanexos;
		syDocumentoanexos = prepararBasico(usuarioActual,syDocumentoanexos, false);
		syDocumentoanexos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyDocumentoanexos.Actualizar : valores por defecto
		
		return syDocumentoanexos;
	}

	public BeanSyDocumentoanexos prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		if (syDocumentoanexos == null)
			return syDocumentoanexos;
		if (syDocumentoanexos.getAuxFlgPreparadoBoolean())
			return syDocumentoanexos;
		syDocumentoanexos = prepararBasico(usuarioActual, syDocumentoanexos, false);
		syDocumentoanexos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyDocumentoanexos.Anular : valores por defecto
		
		return syDocumentoanexos;
	}

	public BeanSyDocumentoanexos prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		if (syDocumentoanexos == null)
			return syDocumentoanexos;
		if (syDocumentoanexos.getAuxFlgPreparadoBoolean())
			return syDocumentoanexos;
		syDocumentoanexos.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyDocumentoanexos.Eliminar : valores por defecto
		
		return syDocumentoanexos;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syDocumentoanexos == null)
			lst.add(this.getMsjUsuarioError("sydocumentoanexos.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syDocumentoanexos.getPk() != null) {
			Set<ConstraintViolation<BeanSyDocumentoanexosPk>> reglasPk = validator.validate(syDocumentoanexos.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyDocumentoanexos>> reglas = validator.validate(syDocumentoanexos);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyDocumentoanexos : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		if (syDocumentoanexos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syDocumentoanexos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syDocumentoanexos = prepararInsertar(usuarioActual, syDocumentoanexos);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syDocumentoanexos);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyDocumentoanexos.Insertar : validaciones
		BeanSyDocumentoanexos b1 = syDocumentoanexosDao.obtenerPorId(syDocumentoanexos.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		if (syDocumentoanexos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syDocumentoanexos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syDocumentoanexos = prepararActualizar(usuarioActual, syDocumentoanexos);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syDocumentoanexos);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyDocumentoanexos.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		if (syDocumentoanexos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syDocumentoanexos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syDocumentoanexos = prepararEliminar(usuarioActual, syDocumentoanexos);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyDocumentoanexos.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyDocumentoanexosPk pk) throws Exception {
		BeanSyDocumentoanexos bean = syDocumentoanexosDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcompaniasocio,String pmodulo,String ptipodocumento,String pnumerodocumento,Integer plinea,Integer psecuencia) throws Exception {
		return coreEliminar(usuarioActual,new BeanSyDocumentoanexosPk( pcompaniasocio, pmodulo, ptipodocumento, pnumerodocumento, plinea, psecuencia));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		if (syDocumentoanexos.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syDocumentoanexos.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syDocumentoanexos = prepararAnular(usuarioActual, syDocumentoanexos);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyDocumentoanexos.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyDocumentoanexosPk pk) throws Exception {
		BeanSyDocumentoanexos bean = syDocumentoanexosDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pcompaniasocio,String pmodulo,String ptipodocumento,String pnumerodocumento,Integer plinea,Integer psecuencia) throws Exception {
		return coreAnular(usuarioActual,new BeanSyDocumentoanexosPk( pcompaniasocio, pmodulo, ptipodocumento, pnumerodocumento, plinea, psecuencia));
	}

	public DtoComunSyDocumentoanexos core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunSyDocumentoanexos dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanSyDocumentoanexos syDocumentoanexos = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, syDocumentoanexos);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, syDocumentoanexos);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, syDocumentoanexos);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, syDocumentoanexos);
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
