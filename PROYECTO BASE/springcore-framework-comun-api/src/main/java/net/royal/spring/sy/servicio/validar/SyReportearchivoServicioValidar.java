package net.royal.spring.sy.servicio.validar;

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

import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sy.dao.impl.SyReportearchivoDaoImpl;
import net.royal.spring.sy.dominio.BeanSyReporte;
import net.royal.spring.sy.dominio.BeanSyReportearchivo;
import net.royal.spring.sy.dominio.BeanSyReportearchivoPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyReportearchivo;

@Service (value = "BeanValidarSyReportearchivo")
public class SyReportearchivoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyReportearchivo";
	private static Logger logger = LogManager.getLogger(SyReportearchivoServicioValidar.class);

	@Autowired
	private SyReportearchivoDaoImpl syReportearchivoDao;

	private BeanSyReportearchivo prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSyReportearchivo syReportearchivo, Boolean flgInsertar) {
     syReportearchivo.setUltimousuario(usuarioActual.getUsuario());
     syReportearchivo.setUltimafechamodif(new Date());
		
		// TODO SyReportearchivo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syReportearchivo;
	}

	public BeanSyReportearchivo prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo == null)
			return syReportearchivo;
		if (syReportearchivo.getAuxFlgPreparadoBoolean())
			return syReportearchivo;
		syReportearchivo = prepararBasico(usuarioActual,syReportearchivo, true);
		syReportearchivo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReportearchivo.Insertar : valores por defecto
		
		return syReportearchivo;
	}

	public BeanSyReportearchivo prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo == null)
			return syReportearchivo;
		if (syReportearchivo.getAuxFlgPreparadoBoolean())
			return syReportearchivo;
		syReportearchivo = prepararBasico(usuarioActual,syReportearchivo, false);
		syReportearchivo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReportearchivo.Actualizar : valores por defecto
		
		return syReportearchivo;
	}

	public BeanSyReportearchivo prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo == null)
			return syReportearchivo;
		if (syReportearchivo.getAuxFlgPreparadoBoolean())
			return syReportearchivo;
		syReportearchivo = prepararBasico(usuarioActual, syReportearchivo, false);
		syReportearchivo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReportearchivo.Anular : valores por defecto
		
		return syReportearchivo;
	}

	public BeanSyReportearchivo prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo == null)
			return syReportearchivo;
		if (syReportearchivo.getAuxFlgPreparadoBoolean())
			return syReportearchivo;
		syReportearchivo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReportearchivo.Eliminar : valores por defecto
		
		return syReportearchivo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSyReportearchivo syReportearchivo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syReportearchivo == null)
			lst.add(this.getMsjUsuarioError("syreportearchivo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syReportearchivo.getPk() != null) {
			Set<ConstraintViolation<BeanSyReportearchivoPk>> reglasPk = validator.validate(syReportearchivo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyReportearchivo>> reglas = validator.validate(syReportearchivo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyReportearchivo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReportearchivo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		//syReportearchivo = prepararInsertar(usuarioActual, syReportearchivo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>(); //coreBasico(usuarioActual, syReportearchivo);
		
		BeanSyReportearchivo param = syReportearchivoDao.obtenerPorId(syReportearchivo.getPk());
		if(param != null) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_AR_RESTRICCION_UNICA));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getPeriodo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_AR_RESTRICCION_PERIODO));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getCompaniasocio())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_AR_RESTRICCION_COMPANIA_CODIGO));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getVersion())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_AR_RESTRICCION_VERSION));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getAuxString())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_AR_RESTRICCION_ARCHIVO));			
		}
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyReportearchivo.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReportearchivo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		//syReportearchivo = prepararActualizar(usuarioActual, syReportearchivo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();//coreBasico(usuarioActual, syReportearchivo);
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getPeriodo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_AR_RESTRICCION_PERIODO));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getCompaniasocio())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_AR_RESTRICCION_COMPANIA_CODIGO));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getPk().getVersion())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_AR_RESTRICCION_VERSION));			
		}
		
		if(UString.esNuloVacio(syReportearchivo.getAuxString())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_AR_RESTRICCION_ARCHIVO));			
		}
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyReportearchivo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReportearchivo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syReportearchivo = prepararEliminar(usuarioActual, syReportearchivo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyReportearchivo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyReportearchivoPk pk) {
		BeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String preportecodigo,String pcompaniasocio,String pperiodo,String pversion) {
		return coreEliminar(usuarioActual,new BeanSyReportearchivoPk( paplicacioncodigo, preportecodigo, pcompaniasocio, pperiodo, pversion));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyReportearchivo syReportearchivo) {
		if (syReportearchivo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReportearchivo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syReportearchivo = prepararAnular(usuarioActual, syReportearchivo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyReportearchivo.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyReportearchivoPk pk) {
		BeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String preportecodigo,String pcompaniasocio,String pperiodo,String pversion) {
		return coreAnular(usuarioActual,new BeanSyReportearchivoPk( paplicacioncodigo, preportecodigo, pcompaniasocio, pperiodo, pversion));
	}

	public DtoComunSyReportearchivo core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunSyReportearchivo dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanSyReportearchivo syReportearchivo = dto.obtenerBean();
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
