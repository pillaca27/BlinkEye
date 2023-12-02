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
//import net.royal.spring.logistica.boot.SpringWhConstanteBoot;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.MaUnidadnegocioDaoImpl;
//import net.royal.spring.core.dominio.Departmentmst;
import net.royal.spring.core.dominio.BeanMaUnidadnegocio;
import net.royal.spring.core.dominio.BeanMaUnidadnegocioPk;
import net.royal.spring.core.dominio.dto.DtoComunMaUnidadnegocio;

@Service (value = "BeanValidarMaUnidadnegocio")
public class MaUnidadnegocioServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarMaUnidadnegocio";
	private static Logger logger = LogManager.getLogger(MaUnidadnegocioServicioValidar.class);

	@Autowired
	private MaUnidadnegocioDaoImpl maUnidadnegocioDao;

	private BeanMaUnidadnegocio prepararBasico(SeguridadUsuarioActual usuarioActual,BeanMaUnidadnegocio maUnidadnegocio, Boolean flgInsertar) {
     maUnidadnegocio.setUltimousuario(usuarioActual.getUsuario());
     maUnidadnegocio.setUltimafechamodif(new Date());
		
		// TODO MaUnidadnegocio : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return maUnidadnegocio;
	}

	public BeanMaUnidadnegocio prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanMaUnidadnegocio maUnidadnegocio) {
		if (maUnidadnegocio == null)
			return maUnidadnegocio;
		if (maUnidadnegocio.getAuxFlgPreparadoBoolean())
			return maUnidadnegocio;
		maUnidadnegocio = prepararBasico(usuarioActual,maUnidadnegocio, true);
		maUnidadnegocio.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		maUnidadnegocio.setUuid(UUID.randomUUID().toString());
		// TODO MaUnidadnegocio.Insertar : valores por defecto
		
		return maUnidadnegocio;
	}

	public BeanMaUnidadnegocio prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanMaUnidadnegocio maUnidadnegocio) {
		if (maUnidadnegocio == null)
			return maUnidadnegocio;
		if (maUnidadnegocio.getAuxFlgPreparadoBoolean())
			return maUnidadnegocio;
		maUnidadnegocio = prepararBasico(usuarioActual,maUnidadnegocio, false);
		maUnidadnegocio.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaUnidadnegocio.Actualizar : valores por defecto
		
		return maUnidadnegocio;
	}

	public BeanMaUnidadnegocio prepararAnular(SeguridadUsuarioActual usuarioActual,BeanMaUnidadnegocio maUnidadnegocio) {
		if (maUnidadnegocio == null)
			return maUnidadnegocio;
		if (maUnidadnegocio.getAuxFlgPreparadoBoolean())
			return maUnidadnegocio;
		maUnidadnegocio = prepararBasico(usuarioActual, maUnidadnegocio, false);
		maUnidadnegocio.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaUnidadnegocio.Anular : valores por defecto
		
		return maUnidadnegocio;
	}

	public BeanMaUnidadnegocio prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanMaUnidadnegocio maUnidadnegocio) {
		if (maUnidadnegocio == null)
			return maUnidadnegocio;
		if (maUnidadnegocio.getAuxFlgPreparadoBoolean())
			return maUnidadnegocio;
		maUnidadnegocio.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO MaUnidadnegocio.Eliminar : valores por defecto
		
		return maUnidadnegocio;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanMaUnidadnegocio maUnidadnegocio) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (maUnidadnegocio == null)
			lst.add(this.getMsjUsuarioError("maunidadnegocio.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (maUnidadnegocio.getPk() != null) {
			Set<ConstraintViolation<BeanMaUnidadnegocioPk>> reglasPk = validator.validate(maUnidadnegocio.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanMaUnidadnegocio>> reglas = validator.validate(maUnidadnegocio);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO MaUnidadnegocio : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanMaUnidadnegocio maUnidadnegocio) {
		if (maUnidadnegocio.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maUnidadnegocio.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(maUnidadnegocio.getPk().getUnidadnegocio())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_NEGOCIO_CODIGO));
		}else {
			BeanMaUnidadnegocio unidadNegocioDto= maUnidadnegocioDao.obtenerPorId(maUnidadnegocio.getPk());
			if(unidadNegocioDto != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_NEGOCIO_CODIGO_VALID));
			}
		}
		if(UString.esNuloVacio(maUnidadnegocio.getDescripcionlocal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_NEGOCIO_DESCRIPCION));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		maUnidadnegocio = prepararInsertar(usuarioActual, maUnidadnegocio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maUnidadnegocio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaUnidadnegocio.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanMaUnidadnegocio maUnidadnegocio) {
		if (maUnidadnegocio.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maUnidadnegocio.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(maUnidadnegocio.getDescripcionlocal())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_UN_NEGOCIO_DESCRIPCION));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		maUnidadnegocio = prepararActualizar(usuarioActual, maUnidadnegocio);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, maUnidadnegocio);
		if (!lst.isEmpty())
			return lst;
		
		// TODO MaUnidadnegocio.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaUnidadnegocio maUnidadnegocio) {
		if (maUnidadnegocio.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maUnidadnegocio.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maUnidadnegocio = prepararEliminar(usuarioActual, maUnidadnegocio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaUnidadnegocio.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanMaUnidadnegocioPk pk) {
		BeanMaUnidadnegocio bean = maUnidadnegocioDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String punidadnegocio) {
		return coreEliminar(usuarioActual,new BeanMaUnidadnegocioPk( punidadnegocio));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaUnidadnegocio maUnidadnegocio) {
		if (maUnidadnegocio.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		maUnidadnegocio.setAuxFlgValidadoBoolean(Boolean.TRUE);

		maUnidadnegocio = prepararAnular(usuarioActual, maUnidadnegocio);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO MaUnidadnegocio.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanMaUnidadnegocioPk pk) {
		BeanMaUnidadnegocio bean = maUnidadnegocioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String punidadnegocio) {
		return coreAnular(usuarioActual,new BeanMaUnidadnegocioPk( punidadnegocio));
	}

	public DtoComunMaUnidadnegocio core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunMaUnidadnegocio dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanMaUnidadnegocio maUnidadnegocio = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, maUnidadnegocio);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, maUnidadnegocio);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, maUnidadnegocio);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, maUnidadnegocio);
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
