package net.royal.spring.sistema.servicio.validar;

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
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.core.dominio.BeanParametrosmast;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyReporteDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyReporte;
import net.royal.spring.sistema.dominio.BeanSyReportePk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyReporte;

@Service (value = "BeanValidarSyReporte")
public class SyReporteServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyReporte";
	private static Logger logger = LogManager.getLogger(SyReporteServicioValidar.class);

	@Autowired
	private SyReporteDaoImpl syReporteDao;

	private BeanSyReporte prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSyReporte syReporte, Boolean flgInsertar) {
     syReporte.setUltimousuario(usuarioActual.getUsuario());
     syReporte.setUltimafechamodif(new Date());
		
		// TODO SyReporte : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syReporte;
	}

	public BeanSyReporte prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSyReporte syReporte) {
		if (syReporte == null)
			return syReporte;
		if (syReporte.getAuxFlgPreparadoBoolean())
			return syReporte;
		syReporte = prepararBasico(usuarioActual,syReporte, true);
		syReporte.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReporte.Insertar : valores por defecto
		
		return syReporte;
	}

	public BeanSyReporte prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSyReporte syReporte) {
		if (syReporte == null)
			return syReporte;
		if (syReporte.getAuxFlgPreparadoBoolean())
			return syReporte;
		syReporte = prepararBasico(usuarioActual,syReporte, false);
		syReporte.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReporte.Actualizar : valores por defecto
		
		return syReporte;
	}

	public BeanSyReporte prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSyReporte syReporte) {
		if (syReporte == null)
			return syReporte;
		if (syReporte.getAuxFlgPreparadoBoolean())
			return syReporte;
		syReporte = prepararBasico(usuarioActual, syReporte, false);
		syReporte.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReporte.Anular : valores por defecto
		
		return syReporte;
	}

	public BeanSyReporte prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSyReporte syReporte) {
		if (syReporte == null)
			return syReporte;
		if (syReporte.getAuxFlgPreparadoBoolean())
			return syReporte;
		syReporte.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyReporte.Eliminar : valores por defecto
		
		return syReporte;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSyReporte syReporte) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syReporte == null)
			lst.add(this.getMsjUsuarioError("syreporte.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syReporte.getPk() != null) {
			Set<ConstraintViolation<BeanSyReportePk>> reglasPk = validator.validate(syReporte.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyReporte>> reglas = validator.validate(syReporte);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyReporte : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyReporte syReporte) {
		if (syReporte.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReporte.setAuxFlgValidadoBoolean(Boolean.TRUE);
		syReporte.setUuid(UUID.randomUUID().toString());
		syReporte.setUltimafechamodif(new Date());
		syReporte.setUltimousuario(usuarioActual.getUsuario());

		//syReporte = prepararInsertar(usuarioActual, syReporte);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>(); // coreBasico(usuarioActual, syReporte);
		
		BeanSyReporte param = syReporteDao.obtenerPorId(syReporte.getPk());
		if(param != null) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_UNICA));			
		}
		
		if(UString.esNuloVacio(syReporte.getPk().getAplicacioncodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_APLICACION_CODIGO));			
		}
		
		if(UString.esNuloVacio(syReporte.getPk().getReportecodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_TABLA_CODIGO));
		}
		
		if(UString.esNuloVacio(syReporte.getDescripcionlocal())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_NOMBRE));
		}
		
		if(UString.esNuloVacio(syReporte.getTiporeporte())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_TIPO_REPORTE));
		}
		
		if(UString.esNuloVacio(syReporte.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_TIPO_REPORTE));
		}
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyReporte.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyReporte syReporte) {
		if (syReporte.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReporte.setAuxFlgValidadoBoolean(Boolean.TRUE);
		syReporte.setUltimafechamodif(new Date());
		syReporte.setUltimousuario(usuarioActual.getUsuario());

		//syReporte = prepararActualizar(usuarioActual, syReporte);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();// coreBasico(usuarioActual, syReporte);
		
		if(UString.esNuloVacio(syReporte.getPk().getAplicacioncodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_APLICACION_CODIGO));			
		}
		
		if(UString.esNuloVacio(syReporte.getPk().getReportecodigo())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_TABLA_CODIGO));
		}
		
		if(UString.esNuloVacio(syReporte.getDescripcionlocal())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_NOMBRE));
		}
		
		if(UString.esNuloVacio(syReporte.getTiporeporte())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_TIPO_REPORTE));
		}
		
		if(UString.esNuloVacio(syReporte.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RE_RESTRICCION_TIPO_REPORTE));
		}
		
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyReporte.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyReporte syReporte) {
		if (syReporte.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReporte.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syReporte = prepararEliminar(usuarioActual, syReporte);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyReporte.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyReportePk pk) {
		BeanSyReporte bean = syReporteDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String preportecodigo) {
		return coreEliminar(usuarioActual,new BeanSyReportePk( paplicacioncodigo, preportecodigo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyReporte syReporte) {
		if (syReporte.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syReporte.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syReporte = prepararAnular(usuarioActual, syReporte);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyReporte.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyReportePk pk) {
		BeanSyReporte bean = syReporteDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String preportecodigo) {
		return coreAnular(usuarioActual,new BeanSyReportePk( paplicacioncodigo, preportecodigo));
	}

	public DtoComunSyReporte core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunSyReporte dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanSyReporte syReporte = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, syReporte);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, syReporte);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, syReporte);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, syReporte);
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
