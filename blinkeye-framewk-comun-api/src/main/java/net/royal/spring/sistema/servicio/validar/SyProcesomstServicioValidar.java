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
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyProcesomstDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyProcesomst;
import net.royal.spring.sistema.dominio.BeanSyProcesomstPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyProcesomst;

@Service (value = "BeanValidarSyProcesomst")
public class SyProcesomstServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarSyProcesomst";
	private static Logger logger = LogManager.getLogger(SyProcesomstServicioValidar.class);

	@Autowired
	private SyProcesomstDaoImpl syProcesomstDao;

	private BeanSyProcesomst prepararBasico(SeguridadUsuarioActual usuarioActual,BeanSyProcesomst syProcesomst, Boolean flgInsertar) throws Exception {
     syProcesomst.setUltimousuario(usuarioActual.getUsuario());
     syProcesomst.setUltimafechamodif(new Date());
		
		// TODO SyProcesomst : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return syProcesomst;
	}

	public BeanSyProcesomst prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanSyProcesomst syProcesomst) throws Exception {
		if (syProcesomst == null)
			return syProcesomst;
		if (syProcesomst.getAuxFlgPreparadoBoolean())
			return syProcesomst;
		syProcesomst = prepararBasico(usuarioActual,syProcesomst, true);
		syProcesomst.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyProcesomst.Insertar : valores por defecto
		
		return syProcesomst;
	}

	public BeanSyProcesomst prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanSyProcesomst syProcesomst) throws Exception {
		if (syProcesomst == null)
			return syProcesomst;
		if (syProcesomst.getAuxFlgPreparadoBoolean())
			return syProcesomst;
		syProcesomst = prepararBasico(usuarioActual,syProcesomst, false);
		syProcesomst.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyProcesomst.Actualizar : valores por defecto
		
		return syProcesomst;
	}

	public BeanSyProcesomst prepararAnular(SeguridadUsuarioActual usuarioActual,BeanSyProcesomst syProcesomst) throws Exception {
		if (syProcesomst == null)
			return syProcesomst;
		if (syProcesomst.getAuxFlgPreparadoBoolean())
			return syProcesomst;
		syProcesomst = prepararBasico(usuarioActual, syProcesomst, false);
		syProcesomst.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyProcesomst.Anular : valores por defecto
		
		return syProcesomst;
	}

	public BeanSyProcesomst prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanSyProcesomst syProcesomst) throws Exception {
		if (syProcesomst == null)
			return syProcesomst;
		if (syProcesomst.getAuxFlgPreparadoBoolean())
			return syProcesomst;
		syProcesomst.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO SyProcesomst.Eliminar : valores por defecto
		
		return syProcesomst;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanSyProcesomst syProcesomst) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (syProcesomst == null)
			lst.add(this.getMsjUsuarioError("syprocesomst.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (syProcesomst.getPk() != null) {
			Set<ConstraintViolation<BeanSyProcesomstPk>> reglasPk = validator.validate(syProcesomst.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanSyProcesomst>> reglas = validator.validate(syProcesomst);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO SyProcesomst : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyProcesomst syProcesomst) throws Exception {
		if (syProcesomst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syProcesomst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syProcesomst = prepararInsertar(usuarioActual, syProcesomst);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syProcesomst);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyProcesomst.Insertar : validaciones
		BeanSyProcesomst b1 = syProcesomstDao.obtenerPorId(syProcesomst.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyProcesomst syProcesomst) throws Exception {
		if (syProcesomst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syProcesomst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syProcesomst = prepararActualizar(usuarioActual, syProcesomst);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, syProcesomst);
		if (!lst.isEmpty())
			return lst;
		
		// TODO SyProcesomst.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyProcesomst syProcesomst) throws Exception {
		if (syProcesomst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syProcesomst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syProcesomst = prepararEliminar(usuarioActual, syProcesomst);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyProcesomst.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanSyProcesomstPk pk) throws Exception {
		BeanSyProcesomst bean = syProcesomstDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String paplicacioncodigo,String pprocesocodigo) throws Exception {
		return coreEliminar(usuarioActual,new BeanSyProcesomstPk( paplicacioncodigo, pprocesocodigo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyProcesomst syProcesomst) throws Exception {
		if (syProcesomst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		syProcesomst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		syProcesomst = prepararAnular(usuarioActual, syProcesomst);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO SyProcesomst.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyProcesomstPk pk) throws Exception {
		BeanSyProcesomst bean = syProcesomstDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String pprocesocodigo) throws Exception {
		return coreAnular(usuarioActual,new BeanSyProcesomstPk( paplicacioncodigo, pprocesocodigo));
	}

	public DtoComunSyProcesomst core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunSyProcesomst dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanSyProcesomst syProcesomst = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, syProcesomst);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, syProcesomst);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, syProcesomst);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, syProcesomst);
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
