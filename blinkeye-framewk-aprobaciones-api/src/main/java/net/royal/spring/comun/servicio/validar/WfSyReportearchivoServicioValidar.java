package net.royal.spring.comun.servicio.validar;

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

import net.royal.spring.comun.dao.impl.WfSyReportearchivoDaoImpl;
import net.royal.spring.comun.dominio.WfBeanSyReportearchivo;
import net.royal.spring.comun.dominio.WfBeanSyReportearchivoPk;
import net.royal.spring.comun.dominio.dto.WfDtoComunSyReportearchivo;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.workflow.boot.SpringWorkflowConstanteBoot;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;


@Service (value = "BeanValidarSyReportearchivo")
public class WfSyReportearchivoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyReportearchivo";
	private static Logger logger = LogManager.getLogger(WfSyReportearchivoServicioValidar.class);

	@Autowired
	private WfSyReportearchivoDaoImpl syReportearchivoDao;

	private WfBeanSyReportearchivo prepararBasico(SeguridadUsuarioActual usuarioActual,WfBeanSyReportearchivo syReportearchivo, Boolean flgInsertar) {
     syReportearchivo.setUltimousuario(usuarioActual.getUsuario());
     syReportearchivo.setUltimafechamodif(new Date());
		
		// TODO SyReportearchivo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syReportearchivo;
	}

	public WfBeanSyReportearchivo prepararInsertar(SeguridadUsuarioActual usuarioActual,WfBeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo == null)
			return syReportearchivo;
		if (syReportearchivo.getAuxFlgPreparadoBoolean())
			return syReportearchivo;
		syReportearchivo = prepararBasico(usuarioActual,syReportearchivo, true);
		syReportearchivo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReportearchivo.Insertar : valores por defecto
		
		return syReportearchivo;
	}

	public WfBeanSyReportearchivo prepararActualizar(SeguridadUsuarioActual usuarioActual,WfBeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo == null)
			return syReportearchivo;
		if (syReportearchivo.getAuxFlgPreparadoBoolean())
			return syReportearchivo;
		syReportearchivo = prepararBasico(usuarioActual,syReportearchivo, false);
		syReportearchivo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReportearchivo.Actualizar : valores por defecto
		
		return syReportearchivo;
	}

	public WfBeanSyReportearchivo prepararAnular(SeguridadUsuarioActual usuarioActual,WfBeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo == null)
			return syReportearchivo;
		if (syReportearchivo.getAuxFlgPreparadoBoolean())
			return syReportearchivo;
		syReportearchivo = prepararBasico(usuarioActual, syReportearchivo, false);
		syReportearchivo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReportearchivo.Anular : valores por defecto
		
		return syReportearchivo;
	}

	public WfBeanSyReportearchivo prepararEliminar(SeguridadUsuarioActual usuarioActual,WfBeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo == null)
			return syReportearchivo;
		if (syReportearchivo.getAuxFlgPreparadoBoolean())
			return syReportearchivo;
		syReportearchivo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReportearchivo.Eliminar : valores por defecto
		
		return syReportearchivo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, WfBeanSyReportearchivo syReportearchivo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syReportearchivo == null)
			lst.add(this.getMsjUsuarioError("syreportearchivo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syReportearchivo.getPk() != null) {
			Set<ConstraintViolation<WfBeanSyReportearchivoPk>> reglasPk = validator.validate(syReportearchivo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<WfBeanSyReportearchivo>> reglas = validator.validate(syReportearchivo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyReportearchivo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,WfBeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReportearchivo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		//syReportearchivo = prepararInsertar(usuarioActual, syReportearchivo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>(); //coreBasico(usuarioActual, syReportearchivo);
		
		WfBeanSyReportearchivo param = syReportearchivoDao.obtenerPorId(syReportearchivo.getPk());
		if(param != null) {
			lst.add(this.getMsjUsuarioError(SpringWorkflowConstanteBoot.VAL_RE_AR_RESTRICCION_UNICA));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getPeriodo())) {
			lst.add(this.getMsjUsuarioError(SpringWorkflowConstanteBoot.VAL_RE_AR_RESTRICCION_PERIODO));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getCompaniasocio())) {
			lst.add(this.getMsjUsuarioError(SpringWorkflowConstanteBoot.VAL_RE_AR_RESTRICCION_COMPANIA_CODIGO));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getVersion())) {
			lst.add(this.getMsjUsuarioError(SpringWorkflowConstanteBoot.VAL_RE_AR_RESTRICCION_VERSION));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getAuxString())) {
			lst.add(this.getMsjUsuarioError(SpringWorkflowConstanteBoot.VAL_RE_AR_RESTRICCION_ARCHIVO));			
		}
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyReportearchivo.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, WfBeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReportearchivo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		//syReportearchivo = prepararActualizar(usuarioActual, syReportearchivo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();//coreBasico(usuarioActual, syReportearchivo);
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getPeriodo())) {
			lst.add(this.getMsjUsuarioError(SpringWorkflowConstanteBoot.VAL_RE_AR_RESTRICCION_PERIODO));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getCompaniasocio())) {
			lst.add(this.getMsjUsuarioError(SpringWorkflowConstanteBoot.VAL_RE_AR_RESTRICCION_COMPANIA_CODIGO));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getVersion())) {
			lst.add(this.getMsjUsuarioError(SpringWorkflowConstanteBoot.VAL_RE_AR_RESTRICCION_VERSION));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getAuxString())) {
			lst.add(this.getMsjUsuarioError(SpringWorkflowConstanteBoot.VAL_RE_AR_RESTRICCION_ARCHIVO));			
		}
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyReportearchivo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfBeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReportearchivo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syReportearchivo = prepararEliminar(usuarioActual, syReportearchivo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyReportearchivo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,WfBeanSyReportearchivoPk pk) {
		WfBeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String preportecodigo,String pcompaniasocio,String pperiodo,String pversion) {
		return coreEliminar(usuarioActual,new WfBeanSyReportearchivoPk( paplicacioncodigo, preportecodigo, pcompaniasocio, pperiodo, pversion));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, WfBeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReportearchivo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syReportearchivo = prepararAnular(usuarioActual, syReportearchivo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyReportearchivo.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, WfBeanSyReportearchivoPk pk) {
		WfBeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String preportecodigo,String pcompaniasocio,String pperiodo,String pversion) {
		return coreAnular(usuarioActual,new WfBeanSyReportearchivoPk( paplicacioncodigo, preportecodigo, pcompaniasocio, pperiodo, pversion));
	}

	public WfDtoComunSyReportearchivo core(SeguridadUsuarioActual usuarioActual,String accion,WfDtoComunSyReportearchivo dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		WfBeanSyReportearchivo syReportearchivo = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, syReportearchivo);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, syReportearchivo);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, syReportearchivo);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, syReportearchivo);
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
