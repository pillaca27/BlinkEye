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
import net.royal.spring.core.dao.impl.PaisDaoImpl;

import net.royal.spring.core.dominio.BeanPais;
import net.royal.spring.core.dominio.BeanPaisPk;
import net.royal.spring.core.dominio.dto.DtoComunPais;

@Service (value = "BeanValidarPais")
public class PaisServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarPais";
	private static Logger logger = LogManager.getLogger(PaisServicioValidar.class);

	@Autowired
	private PaisDaoImpl paisDao;

	private BeanPais prepararBasico(SeguridadUsuarioActual usuarioActual,BeanPais pais, Boolean flgInsertar) throws Exception {
     pais.setUltimousuario(usuarioActual.getUsuario());
     pais.setUltimafechamodif(new Date());
		
		// TODO BeanPais : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return pais;
	}

	public BeanPais prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanPais pais) throws Exception {
		if (pais == null)
			return pais;
		if (pais.getAuxFlgPreparadoBoolean())
			return pais;
		pais = prepararBasico(usuarioActual,pais, true);
		pais.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		pais.setUuid(UUID.randomUUID().toString());
		// TODO BeanPais.Insertar : valores por defecto
		
		return pais;
	}

	public BeanPais prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanPais pais) throws Exception {
		if (pais == null)
			return pais;
		if (pais.getAuxFlgPreparadoBoolean())
			return pais;
		pais = prepararBasico(usuarioActual,pais, false);
		pais.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO BeanPais.Actualizar : valores por defecto
		
		return pais;
	}

	public BeanPais prepararAnular(SeguridadUsuarioActual usuarioActual,BeanPais pais) throws Exception {
		if (pais == null)
			return pais;
		if (pais.getAuxFlgPreparadoBoolean())
			return pais;
		pais = prepararBasico(usuarioActual, pais, false);
		pais.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO BeanPais.Anular : valores por defecto
		
		return pais;
	}

	public BeanPais prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanPais pais) throws Exception {
		if (pais == null)
			return pais;
		if (pais.getAuxFlgPreparadoBoolean())
			return pais;
		pais.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO BeanPais.Eliminar : valores por defecto
		
		return pais;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanPais pais) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (pais == null)
			lst.add(this.getMsjUsuarioError("pais.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (pais.getPk() != null) {
			Set<ConstraintViolation<BeanPaisPk>> reglasPk = validator.validate(pais.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanPais>> reglas = validator.validate(pais);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO BeanPais : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanPais pais) throws Exception {
		if (pais.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		pais.setAuxFlgValidadoBoolean(Boolean.TRUE);

		pais = prepararInsertar(usuarioActual, pais);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, pais);
		if (!lst.isEmpty())
			return lst;
		
		// TODO BeanPais.Insertar : validaciones
		BeanPais b1 = paisDao.obtenerPorId(pais.getPk());
		if (b1!=null)
			lst.add(this.getMsjUsuarioError("generico.clave.duplicado"));
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanPais pais) throws Exception {
		if (pais.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		pais.setAuxFlgValidadoBoolean(Boolean.TRUE);

		pais = prepararActualizar(usuarioActual, pais);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, pais);
		if (!lst.isEmpty())
			return lst;
		
		// TODO BeanPais.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanPais pais) throws Exception {
		if (pais.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		pais.setAuxFlgValidadoBoolean(Boolean.TRUE);

		pais = prepararEliminar(usuarioActual, pais);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO BeanPais.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanPaisPk pk) throws Exception {
		BeanPais bean = paisDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String ppais) throws Exception {
		return coreEliminar(usuarioActual,new BeanPaisPk( ppais));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanPais pais) throws Exception {
		if (pais.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		pais.setAuxFlgValidadoBoolean(Boolean.TRUE);

		pais = prepararAnular(usuarioActual, pais);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO BeanPais.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanPaisPk pk) throws Exception {
		BeanPais bean = paisDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String ppais) throws Exception {
		return coreAnular(usuarioActual,new BeanPaisPk( ppais));
	}

	public DtoComunPais core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunPais dto) throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanPais pais = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, pais);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, pais);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, pais);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, pais);
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

	public BeanPais prepararAuditoria(SeguridadUsuarioActual usuarioActual, BeanPais asAutorizacion) {
		asAutorizacion.setUltimousuario(usuarioActual.getUsuario());
		asAutorizacion.setUltimafechamodif(new Date());
		return asAutorizacion;
	}
}
