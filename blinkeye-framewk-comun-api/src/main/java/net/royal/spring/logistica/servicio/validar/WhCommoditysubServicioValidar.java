package net.royal.spring.logistica.servicio.validar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.logistica.dao.impl.WhCommodityDaoImpl;
import net.royal.spring.logistica.dao.impl.WhCommoditysubDaoImpl;
import net.royal.spring.logistica.dominio.BeanWhCommodity;
import net.royal.spring.logistica.dominio.BeanWhCommodityPk;
import net.royal.spring.logistica.dominio.BeanWhCommoditysub;
import net.royal.spring.logistica.dominio.BeanWhCommoditysubPk;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommoditysub;
import net.royal.spring.logistica.dominio.dto.DtoComunWhCommodity;

@Service (value = "BeanValidarWhCommoditysub")
public class WhCommoditysubServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarWhCommoditysub";
	private static Logger logger = LogManager.getLogger(WhCommoditysubServicioValidar.class);

	@Autowired
	private WhCommoditysubDaoImpl whCommoditysubDao;
	
	@Autowired
	private WhCommodityDaoImpl whCommodityDao;

	private BeanWhCommoditysub prepararBasico(SeguridadUsuarioActual usuarioActual,BeanWhCommoditysub whCommoditysub, Boolean flgInsertar) {
     whCommoditysub.setUltimousuario(usuarioActual.getUsuario());
     whCommoditysub.setUltimafechamodif(new Date());
		
		// TODO WhCommoditysub : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return whCommoditysub;
	}
	
	public List<DominioMensajeUsuario> coreActualizarCommodity(SeguridadUsuarioActual usuarioActual, DtoComunWhCommodity whCommoditysub) {
		if (whCommoditysub.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommoditysub.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommoditysub = prepararActualizarCommodity(usuarioActual, whCommoditysub);
		List<DominioMensajeUsuario> lst = coreBasicoCommodity(usuarioActual, whCommoditysub);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhCommoditysub.Actualizar : validaciones
		
		return lst;
	}
	
	public List<DominioMensajeUsuario> coreInsertarCommunity(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity whCommoditysub) {
		if (whCommoditysub.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommoditysub.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommoditysub = prepararInsertarCommodity(usuarioActual, whCommoditysub);
		List<DominioMensajeUsuario> lst = coreBasicoCommodity(usuarioActual, whCommoditysub);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhCommoditysub.Insertar : validaciones
		
		return lst;
	}

	private DtoComunWhCommodity prepararBasicoCommunity(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity whCommoditysub, Boolean flgInsertar) {
	     whCommoditysub.obtenerBean().setUltimousuario(usuarioActual.getUsuario());
	     whCommoditysub.obtenerBean().setUltimafechamodif(new Date());
			
			// TODO WhCommoditysub : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
			
			return whCommoditysub;
		}
	
	public DtoComunWhCommodity prepararInsertarCommodity(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity whCommoditysub) {
		if (whCommoditysub == null)
			return whCommoditysub;
		if (whCommoditysub.getAuxFlgPreparadoBoolean())
			return whCommoditysub;
		whCommoditysub = prepararBasicoCommunity(usuarioActual,whCommoditysub, true);
		whCommoditysub.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommoditysub.Insertar : valores por defecto
		
		return whCommoditysub;
	}
	
	public DtoComunWhCommodity prepararActualizarCommodity(SeguridadUsuarioActual usuarioActual,DtoComunWhCommodity whCommoditysub) {
		if (whCommoditysub == null)
			return whCommoditysub;
		if (whCommoditysub.getAuxFlgPreparadoBoolean())
			return whCommoditysub;
		whCommoditysub = prepararBasicoCommunity(usuarioActual,whCommoditysub, false);
		whCommoditysub.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommoditysub.Actualizar : valores por defecto
		
		return whCommoditysub;
	}
	
	private List<DominioMensajeUsuario> coreBasicoCommodity(SeguridadUsuarioActual usuarioActual, DtoComunWhCommodity whCommoditysub) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (whCommoditysub == null)
			lst.add(this.getMsjUsuarioError("whcommoditysub.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		
		BeanWhCommodity commodity= new BeanWhCommodity();
		if (whCommoditysub.obtenerBean().getPk() != null) {
			Set<ConstraintViolation<BeanWhCommodityPk>> reglasPk = validator.validate(whCommoditysub.obtenerBean().getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanWhCommodity>> reglas = validator.validate(whCommoditysub.obtenerBean());
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		if(whCommoditysub.getAuxAccion().equals("NUEVO") || whCommoditysub.getAuxAccion().equals("COPIAR")) {
			BeanWhCommodity commodityBean = whCommodityDao.obtenerPorId(whCommoditysub.obtenerBean().getPk());
			if(commodityBean != null) {
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMU_UNICA_COMMUNITY));			
			}
		}

		
		if (UString.esNuloVacio(whCommoditysub.getCommodity01())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMU_RESTRICCION_CODIGO));	
		}
		if (UString.esNuloVacio(whCommoditysub.getDescripcionlocal())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMU_RESTRICCION_DESCRIPCION));	
		}
		if (UString.esNuloVacio(whCommoditysub.getClasificacion())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMU_RESTRICCION_CLASIFICACION));	
		}
		if (UString.esNuloVacio(whCommoditysub.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMU_RESTRICCION_ESTADO));	
		}
		
		if(whCommoditysub.getLstCommodityDetalle().size() >0) {
			
			if(whCommoditysub.getFlagValida().equals("NO_VALID")){
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMU_UNICA_COMMUNITYSUB));
			}else {
				
				for (DtoComunWhCommoditysub detalle : whCommoditysub.getLstCommodityDetalle()) {
					if (UString.esNuloVacio(detalle.getCommodity02())) {
						lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMUSUB_RESTRICCION_CODIGO));	
					}
					if (UString.esNuloVacio(detalle.getDescripcionlocal())) {
						lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMUSUB_RESTRICCION_DESCRIPCION));	
					}
					if (UString.esNuloVacio(detalle.getUnidadpordefecto())) {
						lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMUSUB_RESTRICCION_UNIDAD));	
					}
					if (UString.esNuloVacio(whCommoditysub.getEstado())) {
						lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMMU_RESTRICCION_ESTADO));	
					}
					
				}
			}
		}
		
		// TODO WhCommoditysub : validaciones comunes Insert/Actualizar
		
		return lst;
	}
	
	public BeanWhCommoditysub prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanWhCommoditysub whCommoditysub) {
		if (whCommoditysub == null)
			return whCommoditysub;
		if (whCommoditysub.getAuxFlgPreparadoBoolean())
			return whCommoditysub;
		whCommoditysub = prepararBasico(usuarioActual,whCommoditysub, true);
		whCommoditysub.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommoditysub.Insertar : valores por defecto
		
		return whCommoditysub;
	}

	public BeanWhCommoditysub prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanWhCommoditysub whCommoditysub) {
		if (whCommoditysub == null)
			return whCommoditysub;
		if (whCommoditysub.getAuxFlgPreparadoBoolean())
			return whCommoditysub;
		whCommoditysub = prepararBasico(usuarioActual,whCommoditysub, false);
		whCommoditysub.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommoditysub.Actualizar : valores por defecto
		
		return whCommoditysub;
	}

	public BeanWhCommoditysub prepararAnular(SeguridadUsuarioActual usuarioActual,BeanWhCommoditysub whCommoditysub) {
		if (whCommoditysub == null)
			return whCommoditysub;
		if (whCommoditysub.getAuxFlgPreparadoBoolean())
			return whCommoditysub;
		whCommoditysub = prepararBasico(usuarioActual, whCommoditysub, false);
		whCommoditysub.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommoditysub.Anular : valores por defecto
		
		return whCommoditysub;
	}

	public BeanWhCommoditysub prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanWhCommoditysub whCommoditysub) {
		if (whCommoditysub == null)
			return whCommoditysub;
		if (whCommoditysub.getAuxFlgPreparadoBoolean())
			return whCommoditysub;
		whCommoditysub.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommoditysub.Eliminar : valores por defecto
		
		return whCommoditysub;
	}
	
	public BeanWhCommodity prepararEliminarCabecera(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommoditysub) {
		if (whCommoditysub == null)
			return whCommoditysub;
		if (whCommoditysub.getAuxFlgPreparadoBoolean())
			return whCommoditysub;
		whCommoditysub.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhCommoditysub.Eliminar : valores por defecto
		
		return whCommoditysub;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanWhCommoditysub whCommoditysub) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (whCommoditysub == null)
			lst.add(this.getMsjUsuarioError("whcommoditysub.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (whCommoditysub.getPk() != null) {
			Set<ConstraintViolation<BeanWhCommoditysubPk>> reglasPk = validator.validate(whCommoditysub.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanWhCommoditysub>> reglas = validator.validate(whCommoditysub);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO WhCommoditysub : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhCommoditysub whCommoditysub) {
		if (whCommoditysub.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommoditysub.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommoditysub = prepararInsertar(usuarioActual, whCommoditysub);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whCommoditysub);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhCommoditysub.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhCommoditysub whCommoditysub) {
		if (whCommoditysub.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommoditysub.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommoditysub = prepararActualizar(usuarioActual, whCommoditysub);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whCommoditysub);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhCommoditysub.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhCommoditysub whCommoditysub) {
		if (whCommoditysub.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommoditysub.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommoditysub = prepararEliminar(usuarioActual, whCommoditysub);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhCommoditysub.Eliminar : validaciones
		
		return lst;
	}
	
	public List<DominioMensajeUsuario> coreEliminarCabecera(SeguridadUsuarioActual usuarioActual,BeanWhCommodity whCommoditysub) {
		if (whCommoditysub.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommoditysub.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommoditysub = prepararEliminarCabecera(usuarioActual, whCommoditysub);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhCommoditysub.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhCommoditysubPk pk) {
		BeanWhCommoditysub bean = whCommoditysubDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcommodity01,String pcommodity02) {
		return coreEliminar(usuarioActual,new BeanWhCommoditysubPk( pcommodity01, pcommodity02));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhCommoditysub whCommoditysub) {
		if (whCommoditysub.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whCommoditysub.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whCommoditysub = prepararAnular(usuarioActual, whCommoditysub);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhCommoditysub.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhCommoditysubPk pk) {
		BeanWhCommoditysub bean = whCommoditysubDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pcommodity01,String pcommodity02) {
		return coreAnular(usuarioActual,new BeanWhCommoditysubPk( pcommodity01, pcommodity02));
	}

	public DtoComunWhCommoditysub core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunWhCommoditysub dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanWhCommoditysub whCommoditysub = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, whCommoditysub);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, whCommoditysub);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, whCommoditysub);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, whCommoditysub);
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
