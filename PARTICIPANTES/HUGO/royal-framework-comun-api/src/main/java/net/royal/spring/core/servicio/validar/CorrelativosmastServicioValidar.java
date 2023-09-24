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
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;

import net.royal.spring.core.dominio.BeanCorrelativosmast;
import net.royal.spring.core.dominio.BeanCorrelativosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunCorrelativosmast;

@Service (value = "BeanValidarCorrelativosmast")
public class CorrelativosmastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarCorrelativosmast";
	private static Logger logger = LogManager.getLogger(CorrelativosmastServicioValidar.class);

	@Autowired
	private CorrelativosmastDaoImpl correlativosmastDao;

	private BeanCorrelativosmast prepararBasico(SeguridadUsuarioActual usuarioActual,BeanCorrelativosmast correlativosmast, Boolean flgInsertar) {
     correlativosmast.setUltimousuario(usuarioActual.getUsuario());
     correlativosmast.setUltimafechamodif(new Date());
		
		
		return correlativosmast;
	}

	public BeanCorrelativosmast prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanCorrelativosmast correlativosmast) {
		if (correlativosmast == null)
			return correlativosmast;
		if (correlativosmast.getAuxFlgPreparadoBoolean())
			return correlativosmast;
		correlativosmast = prepararBasico(usuarioActual,correlativosmast, true);
		correlativosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		
		return correlativosmast;
	}

	public BeanCorrelativosmast prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanCorrelativosmast correlativosmast) {
		if (correlativosmast == null)
			return correlativosmast;
		if (correlativosmast.getAuxFlgPreparadoBoolean())
			return correlativosmast;
		correlativosmast = prepararBasico(usuarioActual,correlativosmast, false);
		correlativosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		
		return correlativosmast;
	}

	public BeanCorrelativosmast prepararAnular(SeguridadUsuarioActual usuarioActual,BeanCorrelativosmast correlativosmast) {
		if (correlativosmast == null)
			return correlativosmast;
		if (correlativosmast.getAuxFlgPreparadoBoolean())
			return correlativosmast;
		correlativosmast = prepararBasico(usuarioActual, correlativosmast, false);
		correlativosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		
		return correlativosmast;
	}

	public BeanCorrelativosmast prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanCorrelativosmast correlativosmast) {
		if (correlativosmast == null)
			return correlativosmast;
		if (correlativosmast.getAuxFlgPreparadoBoolean())
			return correlativosmast;
		correlativosmast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		
		return correlativosmast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanCorrelativosmast correlativosmast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (correlativosmast == null)
			lst.add(this.getMsjUsuarioError("correlativosmast.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
			
		
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();	
		if (correlativosmast.getPk() != null) {
			Set<ConstraintViolation<BeanCorrelativosmastPk>> reglasPk = validator.validate(correlativosmast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanCorrelativosmast>> reglas = validator.validate(correlativosmast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanCorrelativosmast correlativosmast) {
		if (correlativosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		correlativosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lst2 = new ArrayList<DominioMensajeUsuario>();


			DtoComunCorrelativosmast dto = correlativosmastDao.obtenerDto(correlativosmast.getPk().getCompaniacodigo(),
											correlativosmast.getPk().getTipocomprobante(),correlativosmast.getPk().getSerie());
			if(dto != null) {
				lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_UNICA_APLICACION_CODIGO));
			}
		
		
		if (!lst2.isEmpty())
			return lst2;
		
		if(UString.esNuloVacio(correlativosmast.getPk().getCompaniacodigo())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_COMPANIA));
		}
		
		if(UString.esNuloVacio(correlativosmast.getPk().getTipocomprobante())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_TIPO));
		}

		if(UString.esNuloVacio(correlativosmast.getPk().getSerie())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_SERIE));
		}
		
		if(UString.esNuloVacio(correlativosmast.getDescripcion())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_DESCRIPCION));
		}
		
		if(UString.esNuloVacio(correlativosmast.getEstado())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_ESTADO));
		}
		
		if(UBigDecimal.esNulo(correlativosmast.getCorrelativonumero())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_NUMERO_ACTUAL));
		}
		
		if(UBigDecimal.esNulo(correlativosmast.getCorrelativodesde())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_RANGO_DESDE));
		}
		
		if(UBigDecimal.esNulo(correlativosmast.getCorrelativohasta())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_HASTA));
		}
		
		if (!lst2.isEmpty())
			return lst2;
		
		if(!UBigDecimal.esNulo(correlativosmast.getCorrelativonumero())) {
			if(correlativosmast.getCorrelativonumero().intValue() < 0) {
				lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_VALID_NUMERO_ACTUAL));
			}else {
				
				if(correlativosmast.getCorrelativonumero().intValue() >= correlativosmast.getCorrelativodesde().intValue()
						&& correlativosmast.getCorrelativodesde().intValue() <= correlativosmast.getCorrelativohasta().intValue()) {	
					System.out.println("VALIDO");
				}else {
					lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_VALID_RANGO));
				}
			}
			
		}
		
		if (!lst2.isEmpty())
			return lst2;
		
		correlativosmast = prepararInsertar(usuarioActual, correlativosmast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correlativosmast);
		if (!lst.isEmpty())
			return lst;
		
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanCorrelativosmast correlativosmast) {
		if (correlativosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		correlativosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lst2 = new ArrayList<DominioMensajeUsuario>();

		/*if(correlativosmast.getAuxAccion().equals("N")) {
			DtoCorrelativosmast bean = new DtoCorrelativosmast();
			bean.obtenerBean(correlativosmast);
			DtoCorrelativosmast dto = correlativosmastDao.obtenerDto(bean);
			if(dto != null) {
				lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_UNICA_APLICACION_CODIGO));
			}
		}
		
		if (!lst2.isEmpty())
			return lst2;*/
		
		if(UString.esNuloVacio(correlativosmast.getPk().getCompaniacodigo())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_COMPANIA));
		}
		
		if(UString.esNuloVacio(correlativosmast.getPk().getTipocomprobante())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_TIPO));
		}

		if(UString.esNuloVacio(correlativosmast.getPk().getSerie())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_SERIE));
		}
		
		if(UString.esNuloVacio(correlativosmast.getDescripcion())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_DESCRIPCION));
		}
		
		if(UString.esNuloVacio(correlativosmast.getEstado())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_ESTADO));
		}
		
		if(UBigDecimal.esNulo(correlativosmast.getCorrelativonumero())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_NUMERO_ACTUAL));
		}
		
		if(UBigDecimal.esNulo(correlativosmast.getCorrelativodesde())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_RANGO_DESDE));
		}
		
		if(UBigDecimal.esNulo(correlativosmast.getCorrelativohasta())) {
			lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_RESTRICCION_HASTA));
		}
		
		if (!lst2.isEmpty())
			return lst2;
		
		if(!UBigDecimal.esNulo(correlativosmast.getCorrelativonumero())) {
			if(correlativosmast.getCorrelativonumero().intValue() < 0) {
				lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_VALID_NUMERO_ACTUAL));
			}else {
				
				if(correlativosmast.getCorrelativonumero().intValue() >= correlativosmast.getCorrelativodesde().intValue()
						&& correlativosmast.getCorrelativodesde().intValue() <= correlativosmast.getCorrelativohasta().intValue()) {	
					System.out.println("VALIDO");
				}else {
					lst2.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_CORRE_VALID_RANGO));
				}
			}
			
		}
		
		if (!lst2.isEmpty())
			return lst2;
		
		correlativosmast = prepararActualizar(usuarioActual, correlativosmast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, correlativosmast);
		
		
		
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanCorrelativosmast correlativosmast) {
		if (correlativosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		correlativosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		correlativosmast = prepararEliminar(usuarioActual, correlativosmast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanCorrelativosmastPk pk) {
		BeanCorrelativosmast bean = correlativosmastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcompaniacodigo,String ptipocomprobante,String pserie) {
		return coreEliminar(usuarioActual,new BeanCorrelativosmastPk( pcompaniacodigo, ptipocomprobante, pserie));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanCorrelativosmast correlativosmast) {
		if (correlativosmast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		correlativosmast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		correlativosmast = prepararAnular(usuarioActual, correlativosmast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanCorrelativosmastPk pk) {
		BeanCorrelativosmast bean = correlativosmastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo,String ptipocomprobante,String pserie) {
		return coreAnular(usuarioActual,new BeanCorrelativosmastPk( pcompaniacodigo, ptipocomprobante, pserie));
	}

	public DtoComunCorrelativosmast core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunCorrelativosmast dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanCorrelativosmast correlativosmast = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, correlativosmast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, correlativosmast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, correlativosmast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, correlativosmast);
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
