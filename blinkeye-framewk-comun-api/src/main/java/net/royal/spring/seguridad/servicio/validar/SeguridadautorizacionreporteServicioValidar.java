package net.royal.spring.seguridad.servicio.validar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.seguridad.dao.impl.SeguridadautorizacionreporteComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionreporte;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizacionreportePk;

@Service (value = "BeanValidarSeguridadautorizacionreporte")
public class SeguridadautorizacionreporteServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSeguridadautorizacionreporte";
	
	@Autowired
	private SeguridadautorizacionreporteComunDaoImpl seguridadautorizacionreporteDao;

	private BeanSeguridadautorizacionreporte prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizacionreporte seguridadautorizacionreporte, Boolean flgInsertar) {
//     seguridadautorizacionreporte.setUltimousuario(usuarioActual.getUsuario());
//     seguridadautorizacionreporte.setUltimafechamodif(new Date());
		return seguridadautorizacionreporte;
	}

	public BeanSeguridadautorizacionreporte prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizacionreporte seguridadautorizacionreporte) {
		if (seguridadautorizacionreporte == null)
			return seguridadautorizacionreporte;
		if (seguridadautorizacionreporte.getAuxFlgPreparado())
			return seguridadautorizacionreporte;
		seguridadautorizacionreporte = prepararBasico(usuarioActual,seguridadautorizacionreporte, true);
		seguridadautorizacionreporte.setAuxFlgPreparado(Boolean.TRUE);
		return seguridadautorizacionreporte;
	}

	public BeanSeguridadautorizacionreporte prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizacionreporte seguridadautorizacionreporte) {
		if (seguridadautorizacionreporte == null)
			return seguridadautorizacionreporte;
		if (seguridadautorizacionreporte.getAuxFlgPreparado())
			return seguridadautorizacionreporte;
		seguridadautorizacionreporte = prepararBasico(usuarioActual,seguridadautorizacionreporte, false);
		seguridadautorizacionreporte.setAuxFlgPreparado(Boolean.TRUE);
		return seguridadautorizacionreporte;
	}

	public BeanSeguridadautorizacionreporte prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizacionreporte seguridadautorizacionreporte) {
		if (seguridadautorizacionreporte == null)
			return seguridadautorizacionreporte;
		if (seguridadautorizacionreporte.getAuxFlgPreparado())
			return seguridadautorizacionreporte;
		seguridadautorizacionreporte = prepararBasico(usuarioActual, seguridadautorizacionreporte, false);
		seguridadautorizacionreporte.setAuxFlgPreparado(Boolean.TRUE);
		return seguridadautorizacionreporte;
	}

	public BeanSeguridadautorizacionreporte prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizacionreporte seguridadautorizacionreporte) {
		if (seguridadautorizacionreporte == null)
			return seguridadautorizacionreporte;
		if (seguridadautorizacionreporte.getAuxFlgPreparado())
			return seguridadautorizacionreporte;
		seguridadautorizacionreporte.setAuxFlgPreparado(Boolean.TRUE);
		return seguridadautorizacionreporte;
	}
	@SuppressWarnings({ "rawtypes" })
	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionreporte seguridadautorizacionreporte) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (seguridadautorizacionreporte == null)
			lst.add(this.getMsjUsuarioError("seguridadautorizacionreporte.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (seguridadautorizacionreporte.getPk() != null) {
			Set<ConstraintViolation<BeanSeguridadautorizacionreportePk>> reglasPk = validator.validate(seguridadautorizacionreporte.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSeguridadautorizacionreporte>> reglas = validator.validate(seguridadautorizacionreporte);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizacionreporte seguridadautorizacionreporte) {
		seguridadautorizacionreporte = prepararInsertar(usuarioActual, seguridadautorizacionreporte);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, seguridadautorizacionreporte);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSeguridadautorizacionreporte seguridadautorizacionreporte) {
		seguridadautorizacionreporte = prepararActualizar(usuarioActual, seguridadautorizacionreporte);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, seguridadautorizacionreporte);
		if (!lst.isEmpty())
			return lst;
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizacionreporte seguridadautorizacionreporte) {
		seguridadautorizacionreporte = prepararEliminar(usuarioActual, seguridadautorizacionreporte);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSeguridadautorizacionreportePk pk) {
		BeanSeguridadautorizacionreporte bean = seguridadautorizacionreporteDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pusuario,String paplicacioncodigo,String preportecodigo) {
		return coreEliminar(usuarioActual,new BeanSeguridadautorizacionreportePk( pusuario, paplicacioncodigo, preportecodigo));
	}


	public List<DominioMensajeUsuario> core(SeguridadUsuarioActual usuarioActual,String accion,BeanSeguridadautorizacionreporte seguridadautorizacionreporte, String pusuario,String paplicacioncodigo,String preportecodigo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			return coreInsertar(usuarioActual, seguridadautorizacionreporte);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			return coreActualizar(usuarioActual, seguridadautorizacionreporte);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR)){
			BeanSeguridadautorizacionreportePk pk = new BeanSeguridadautorizacionreportePk( pusuario, paplicacioncodigo, preportecodigo);
			return coreEliminar(usuarioActual, pk);
		}
		return lst;
	}

}
