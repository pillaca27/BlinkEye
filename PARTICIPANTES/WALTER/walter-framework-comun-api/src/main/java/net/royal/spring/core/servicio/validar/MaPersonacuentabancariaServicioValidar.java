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
import net.royal.spring.core.dao.impl.MaPersonacuentabancariaDaoImpl;

import net.royal.spring.core.dominio.BeanMaPersonacuentabancaria;
import net.royal.spring.core.dominio.BeanMaPersonacuentabancariaPk;
import net.royal.spring.core.dominio.dto.DtoComunMaPersonacuentabancaria;

@Service (value = "BeanValidarMaPersonacuentabancaria")
public class MaPersonacuentabancariaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarMaPersonacuentabancaria";
	private static Logger logger = LogManager.getLogger(MaPersonacuentabancariaServicioValidar.class);

	@Autowired
	private MaPersonacuentabancariaDaoImpl maPersonacuentabancariaDao;

	private BeanMaPersonacuentabancaria prepararBasico(SeguridadUsuarioActual usuarioActual,BeanMaPersonacuentabancaria maPersonacuentabancaria, Boolean flgInsertar) {
     maPersonacuentabancaria.setUltimousuario(usuarioActual.getUsuario());
     maPersonacuentabancaria.setUltimafechamodif(new Date());
		
		// TODO MaPersonacuentabancaria : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return maPersonacuentabancaria;
	}

	public BeanMaPersonacuentabancaria prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanMaPersonacuentabancaria maPersonacuentabancaria) {
		if (maPersonacuentabancaria == null)
			return maPersonacuentabancaria;
		if (maPersonacuentabancaria.getAuxFlgPreparadoBoolean())
			return maPersonacuentabancaria;
		maPersonacuentabancaria = prepararBasico(usuarioActual,maPersonacuentabancaria, true);
		maPersonacuentabancaria.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonacuentabancaria.Insertar : valores por defecto
		
		return maPersonacuentabancaria;
	}

	public BeanMaPersonacuentabancaria prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanMaPersonacuentabancaria maPersonacuentabancaria) {
		if (maPersonacuentabancaria == null)
			return maPersonacuentabancaria;
		if (maPersonacuentabancaria.getAuxFlgPreparadoBoolean())
			return maPersonacuentabancaria;
		maPersonacuentabancaria = prepararBasico(usuarioActual,maPersonacuentabancaria, false);
		maPersonacuentabancaria.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonacuentabancaria.Actualizar : valores por defecto
		
		return maPersonacuentabancaria;
	}

	public BeanMaPersonacuentabancaria prepararAnular(SeguridadUsuarioActual usuarioActual,BeanMaPersonacuentabancaria maPersonacuentabancaria) {
		if (maPersonacuentabancaria == null)
			return maPersonacuentabancaria;
		if (maPersonacuentabancaria.getAuxFlgPreparadoBoolean())
			return maPersonacuentabancaria;
		maPersonacuentabancaria = prepararBasico(usuarioActual, maPersonacuentabancaria, false);
		maPersonacuentabancaria.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonacuentabancaria.Anular : valores por defecto
		
		return maPersonacuentabancaria;
	}

	public BeanMaPersonacuentabancaria prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanMaPersonacuentabancaria maPersonacuentabancaria) {
		if (maPersonacuentabancaria == null)
			return maPersonacuentabancaria;
		if (maPersonacuentabancaria.getAuxFlgPreparadoBoolean())
			return maPersonacuentabancaria;
		maPersonacuentabancaria.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaPersonacuentabancaria.Eliminar : valores por defecto
		
		return maPersonacuentabancaria;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanMaPersonacuentabancaria maPersonacuentabancaria) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (maPersonacuentabancaria == null)
			lst.add(this.getMsjUsuarioError("mapersonacuentabancaria.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (maPersonacuentabancaria.getPk() != null) {
			Set<ConstraintViolation<BeanMaPersonacuentabancariaPk>> reglasPk = validator.validate(maPersonacuentabancaria.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanMaPersonacuentabancaria>> reglas = validator.validate(maPersonacuentabancaria);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO MaPersonacuentabancaria : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaPersonacuentabancaria maPersonacuentabancaria) {
		if (maPersonacuentabancaria.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonacuentabancaria.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonacuentabancaria = prepararInsertar(usuarioActual, maPersonacuentabancaria);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maPersonacuentabancaria);
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaPersonacuentabancaria.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaPersonacuentabancaria maPersonacuentabancaria) {
		if (maPersonacuentabancaria.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonacuentabancaria.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonacuentabancaria = prepararActualizar(usuarioActual, maPersonacuentabancaria);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maPersonacuentabancaria);
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaPersonacuentabancaria.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaPersonacuentabancaria maPersonacuentabancaria) {
		if (maPersonacuentabancaria.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonacuentabancaria.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonacuentabancaria = prepararEliminar(usuarioActual, maPersonacuentabancaria);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaPersonacuentabancaria.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaPersonacuentabancariaPk pk) {
		BeanMaPersonacuentabancaria bean = maPersonacuentabancariaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,Integer ppersona,Integer psecuencia) {
		return coreEliminar(usuarioActual,new BeanMaPersonacuentabancariaPk( ppersona, psecuencia));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaPersonacuentabancaria maPersonacuentabancaria) {
		if (maPersonacuentabancaria.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maPersonacuentabancaria.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maPersonacuentabancaria = prepararAnular(usuarioActual, maPersonacuentabancaria);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaPersonacuentabancaria.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaPersonacuentabancariaPk pk) {
		BeanMaPersonacuentabancaria bean = maPersonacuentabancariaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, Integer ppersona,Integer psecuencia) {
		return coreAnular(usuarioActual,new BeanMaPersonacuentabancariaPk( ppersona, psecuencia));
	}

	public DtoComunMaPersonacuentabancaria core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunMaPersonacuentabancaria dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanMaPersonacuentabancaria maPersonacuentabancaria = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, maPersonacuentabancaria);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, maPersonacuentabancaria);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, maPersonacuentabancaria);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, maPersonacuentabancaria);
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
