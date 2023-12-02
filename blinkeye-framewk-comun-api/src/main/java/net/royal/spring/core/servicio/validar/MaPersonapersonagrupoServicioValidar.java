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
import net.royal.spring.core.dao.impl.MaPersonapersonagrupoDaoImpl;

import net.royal.spring.core.dominio.BeanMaPersonapersonagrupo;
import net.royal.spring.core.dominio.BeanMaPersonapersonagrupoPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonapersonagrupo;

@Service (value = "BeanValidarMaPersonapersonagrupo")
public class MaPersonapersonagrupoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarMaPersonapersonagrupo";
	private static Logger logger = LogManager.getLogger(MaPersonapersonagrupoServicioValidar.class);

	@Autowired
	private MaPersonapersonagrupoDaoImpl maPersonapersonagrupoDao;

	private BeanMaPersonapersonagrupo prepararBasico(SeguridadUsuarioActual usuarioActual,BeanMaPersonapersonagrupo maPersonapersonagrupo, Boolean flgInsertar) {
		
		// TODO MaPersonapersonagrupo : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return maPersonapersonagrupo;
	}

	public BeanMaPersonapersonagrupo prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanMaPersonapersonagrupo maPersonapersonagrupo) {
		if (maPersonapersonagrupo == null)
			return maPersonapersonagrupo;
		if (maPersonapersonagrupo.getAuxFlgPreparadoBoolean())
			return maPersonapersonagrupo;
		maPersonapersonagrupo = prepararBasico(usuarioActual,maPersonapersonagrupo, true);
		maPersonapersonagrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonapersonagrupo.Insertar : valores por defecto
		
		return maPersonapersonagrupo;
	}

	public BeanMaPersonapersonagrupo prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanMaPersonapersonagrupo maPersonapersonagrupo) {
		if (maPersonapersonagrupo == null)
			return maPersonapersonagrupo;
		if (maPersonapersonagrupo.getAuxFlgPreparadoBoolean())
			return maPersonapersonagrupo;
		maPersonapersonagrupo = prepararBasico(usuarioActual,maPersonapersonagrupo, false);
		maPersonapersonagrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonapersonagrupo.Actualizar : valores por defecto
		
		return maPersonapersonagrupo;
	}

	public BeanMaPersonapersonagrupo prepararAnular(SeguridadUsuarioActual usuarioActual,BeanMaPersonapersonagrupo maPersonapersonagrupo) {
		if (maPersonapersonagrupo == null)
			return maPersonapersonagrupo;
		if (maPersonapersonagrupo.getAuxFlgPreparadoBoolean())
			return maPersonapersonagrupo;
		maPersonapersonagrupo = prepararBasico(usuarioActual, maPersonapersonagrupo, false);
		maPersonapersonagrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonapersonagrupo.Anular : valores por defecto
		
		return maPersonapersonagrupo;
	}

	public BeanMaPersonapersonagrupo prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanMaPersonapersonagrupo maPersonapersonagrupo) {
		if (maPersonapersonagrupo == null)
			return maPersonapersonagrupo;
		if (maPersonapersonagrupo.getAuxFlgPreparadoBoolean())
			return maPersonapersonagrupo;
		maPersonapersonagrupo.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonapersonagrupo.Eliminar : valores por defecto
		
		return maPersonapersonagrupo;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanMaPersonapersonagrupo maPersonapersonagrupo) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (maPersonapersonagrupo == null)
			lst.add(this.getMsjUsuarioError("mapersonapersonagrupo.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (maPersonapersonagrupo.getPk() != null) {
			Set<ConstraintViolation<BeanMaPersonapersonagrupoPk>> reglasPk = validator.validate(maPersonapersonagrupo.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanMaPersonapersonagrupo>> reglas = validator.validate(maPersonapersonagrupo);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO MaPersonapersonagrupo : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaPersonapersonagrupo maPersonapersonagrupo) {
		if (maPersonapersonagrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonapersonagrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonapersonagrupo = prepararInsertar(usuarioActual, maPersonapersonagrupo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maPersonapersonagrupo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaPersonapersonagrupo.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaPersonapersonagrupo maPersonapersonagrupo) {
		if (maPersonapersonagrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonapersonagrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonapersonagrupo = prepararActualizar(usuarioActual, maPersonapersonagrupo);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maPersonapersonagrupo);
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaPersonapersonagrupo.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaPersonapersonagrupo maPersonapersonagrupo) {
		if (maPersonapersonagrupo.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonapersonagrupo.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonapersonagrupo = prepararEliminar(usuarioActual, maPersonapersonagrupo);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaPersonapersonagrupo.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaPersonapersonagrupoPk pk) {
		BeanMaPersonapersonagrupo bean = maPersonapersonagrupoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer ppersona,String ppersonagrupo) {
		return coreEliminar(usuarioActual,new BeanMaPersonapersonagrupoPk( ppersona, ppersonagrupo));
	}


	public DtoComunMaPersonapersonagrupo core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunMaPersonapersonagrupo dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanMaPersonapersonagrupo maPersonapersonagrupo = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, maPersonapersonagrupo);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, maPersonapersonagrupo);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, maPersonapersonagrupo);
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
