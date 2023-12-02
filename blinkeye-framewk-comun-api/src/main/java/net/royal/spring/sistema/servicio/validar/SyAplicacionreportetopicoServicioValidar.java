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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.sistema.dao.impl.SyAplicacionreportetopicoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyAplicacionreportetopico;
import net.royal.spring.sistema.dominio.BeanSyAplicacionreportetopicoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyAplicacionreportetopico;
 

@Service (value = "BeanValidarSyAplicacionreportetopico")
public class SyAplicacionreportetopicoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyAplicacionreportetopico";
	private static Logger logger = LogManager.getLogger(SyAplicacionreportetopicoServicioValidar.class);

	@Autowired
	private SyAplicacionreportetopicoDaoImpl syAplicacionreportetopicoDao;

	private BeanSyAplicacionreportetopico prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopico syAplicacionreportetopico, Boolean flgInsertar) throws Exception {
     syAplicacionreportetopico.setUltimousuario(usuarioActual.getUsuario());
     syAplicacionreportetopico.setUltimafechamodif(new Date());
		
		// TODO SyAplicacionreportetopico : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syAplicacionreportetopico;
	}

	public BeanSyAplicacionreportetopico prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico == null)
			return syAplicacionreportetopico;
		if (syAplicacionreportetopico.getAuxFlgPreparadoBoolean())
			return syAplicacionreportetopico;
		syAplicacionreportetopico = prepararBasico(usuarioActual,syAplicacionreportetopico, true);
		syAplicacionreportetopico.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		syAplicacionreportetopico.setUuid(UUID.randomUUID().toString());
		
		// TODO SyAplicacionreportetopico.Insertar : valores por defecto
		
		return syAplicacionreportetopico;
	}

	public BeanSyAplicacionreportetopico prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico == null)
			return syAplicacionreportetopico;
		if (syAplicacionreportetopico.getAuxFlgPreparadoBoolean())
			return syAplicacionreportetopico;
		syAplicacionreportetopico = prepararBasico(usuarioActual,syAplicacionreportetopico, false);
		syAplicacionreportetopico.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyAplicacionreportetopico.Actualizar : valores por defecto
		
		return syAplicacionreportetopico;
	}

	public BeanSyAplicacionreportetopico prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico == null)
			return syAplicacionreportetopico;
		if (syAplicacionreportetopico.getAuxFlgPreparadoBoolean())
			return syAplicacionreportetopico;
		syAplicacionreportetopico = prepararBasico(usuarioActual, syAplicacionreportetopico, false);
		syAplicacionreportetopico.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyAplicacionreportetopico.Anular : valores por defecto
		
		return syAplicacionreportetopico;
	}

	public BeanSyAplicacionreportetopico prepararActivar(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico == null)
			return syAplicacionreportetopico;
		if (syAplicacionreportetopico.getAuxFlgPreparadoBoolean())
			return syAplicacionreportetopico;
		syAplicacionreportetopico = prepararBasico(usuarioActual, syAplicacionreportetopico, false);
		syAplicacionreportetopico.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyAplicacionreportetopico.Activar : valores por defecto
		
		return syAplicacionreportetopico;
	}

	public BeanSyAplicacionreportetopico prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico == null)
			return syAplicacionreportetopico;
		if (syAplicacionreportetopico.getAuxFlgPreparadoBoolean())
			return syAplicacionreportetopico;
		syAplicacionreportetopico.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyAplicacionreportetopico.Eliminar : valores por defecto
		
		return syAplicacionreportetopico;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syAplicacionreportetopico == null)
			lst.add(this.getMsjUsuarioError("syaplicacionreportetopico.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syAplicacionreportetopico.getPk() != null) {
			Set<ConstraintViolation<BeanSyAplicacionreportetopicoPk>> reglasPk = validator.validate(syAplicacionreportetopico.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyAplicacionreportetopico>> reglas = validator.validate(syAplicacionreportetopico);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyAplicacionreportetopico : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syAplicacionreportetopico.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syAplicacionreportetopico = prepararInsertar(usuarioActual, syAplicacionreportetopico);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syAplicacionreportetopico);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyAplicacionreportetopico.Insertar : validaciones
		BeanSyAplicacionreportetopico b1 = syAplicacionreportetopicoDao.obtenerPorId(syAplicacionreportetopico.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("syaplicacionreportetopico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syAplicacionreportetopico.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syAplicacionreportetopico = prepararActualizar(usuarioActual, syAplicacionreportetopico);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syAplicacionreportetopico);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyAplicacionreportetopico.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syAplicacionreportetopico.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syAplicacionreportetopico = prepararEliminar(usuarioActual, syAplicacionreportetopico);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyAplicacionreportetopico.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyAplicacionreportetopicoPk pk) throws Exception {
		BeanSyAplicacionreportetopico bean = syAplicacionreportetopicoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String ptopico) throws Exception {
		return coreEliminar(usuarioActual,new BeanSyAplicacionreportetopicoPk( paplicacioncodigo, ptopico));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syAplicacionreportetopico.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syAplicacionreportetopico = prepararAnular(usuarioActual, syAplicacionreportetopico);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyAplicacionreportetopico.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopicoPk pk) throws Exception {
		BeanSyAplicacionreportetopico bean = syAplicacionreportetopicoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String ptopico) throws Exception {
		return coreAnular(usuarioActual,new BeanSyAplicacionreportetopicoPk( paplicacioncodigo, ptopico));
	}

	public List<DominioMensajeUsuario> coreActivar(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopico syAplicacionreportetopico) throws Exception {
		if (syAplicacionreportetopico.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syAplicacionreportetopico.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syAplicacionreportetopico = prepararActivar(usuarioActual, syAplicacionreportetopico);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyAplicacionreportetopico.Activar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActivar(SeguridadUsuarioActual usuarioActual, BeanSyAplicacionreportetopicoPk pk) throws Exception {
		BeanSyAplicacionreportetopico bean = syAplicacionreportetopicoDao.obtenerPorId(pk);
		return coreActivar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreActivar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String ptopico) throws Exception {
		return coreActivar(usuarioActual,new BeanSyAplicacionreportetopicoPk( paplicacioncodigo, ptopico));
	}

	public DtoComunSyAplicacionreportetopico core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunSyAplicacionreportetopico dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanSyAplicacionreportetopico syAplicacionreportetopico = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, syAplicacionreportetopico);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, syAplicacionreportetopico);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, syAplicacionreportetopico);
		if (accion.equals(ConstantePantallaAccion.ACTIVAR))
			lst = coreActivar(usuarioActual, syAplicacionreportetopico);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, syAplicacionreportetopico);
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
