package net.royal.spring.logistica.servicio.validar;

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

import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.logistica.dao.impl.WhClaselineaDaoImpl;

import net.royal.spring.logistica.dominio.BeanWhClaselinea;
import net.royal.spring.logistica.dominio.BeanWhClaselineaPk;
import net.royal.spring.logistica.dominio.BeanWhCommodity;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClasefamilia;
import net.royal.spring.logistica.dominio.dto.DtoComunWhClaselinea;

@Service (value = "BeanValidarWhClaselinea")
public class WhClaselineaServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarWhClaselinea";
	private static Logger logger = LogManager.getLogger(WhClaselineaServicioValidar.class);

	@Autowired
	private WhClaselineaDaoImpl whClaselineaDao;

	private BeanWhClaselinea prepararBasico(SeguridadUsuarioActual usuarioActual,BeanWhClaselinea whClaselinea, Boolean flgInsertar) {
     whClaselinea.setUltimousuario(usuarioActual.getUsuario());
     whClaselinea.setUltimafechamodif(new Date());
		
		// TODO WhClaselinea : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return whClaselinea;
	}

	public BeanWhClaselinea prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanWhClaselinea whClaselinea) {
		if (whClaselinea == null)
			return whClaselinea;
		if (whClaselinea.getAuxFlgPreparadoBoolean())
			return whClaselinea;
		whClaselinea = prepararBasico(usuarioActual,whClaselinea, true);
		whClaselinea.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClaselinea.Insertar : valores por defecto
		
		return whClaselinea;
	}

	public BeanWhClaselinea prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanWhClaselinea whClaselinea) {
		if (whClaselinea == null)
			return whClaselinea;
		if (whClaselinea.getAuxFlgPreparadoBoolean())
			return whClaselinea;
		whClaselinea = prepararBasico(usuarioActual,whClaselinea, false);
		whClaselinea.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClaselinea.Actualizar : valores por defecto
		
		return whClaselinea;
	}

	public BeanWhClaselinea prepararAnular(SeguridadUsuarioActual usuarioActual,BeanWhClaselinea whClaselinea) {
		if (whClaselinea == null)
			return whClaselinea;
		if (whClaselinea.getAuxFlgPreparadoBoolean())
			return whClaselinea;
		whClaselinea = prepararBasico(usuarioActual, whClaselinea, false);
		whClaselinea.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClaselinea.Anular : valores por defecto
		
		return whClaselinea;
	}

	public BeanWhClaselinea prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanWhClaselinea whClaselinea) {
		if (whClaselinea == null)
			return whClaselinea;
		if (whClaselinea.getAuxFlgPreparadoBoolean())
			return whClaselinea;
		whClaselinea.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO WhClaselinea.Eliminar : valores por defecto
		
		return whClaselinea;
	}

	private List<DominioMensajeUsuario> coreBasicoCompleto(SeguridadUsuarioActual usuarioActual, BeanWhClaselinea whClaselinea,DtoComunWhClaselinea dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (whClaselinea == null)
			lst.add(this.getMsjUsuarioError("whclaselinea.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (whClaselinea.getPk() != null) {
			Set<ConstraintViolation<BeanWhClaselineaPk>> reglasPk = validator.validate(whClaselinea.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanWhClaselinea>> reglas = validator.validate(whClaselinea);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		if(dto.getAuxAccion().equals("NUEVO")) {
			BeanWhClaselinea commodityBean = whClaselineaDao.obtenerPorId(whClaselinea.getPk());
			if(commodityBean != null) {
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEA_UNICA_CODIGO));			
			}
		}

		
		if (UString.esNuloVacio(dto.getLinea())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEA_VACIO_CODIGO));	
		}
		if (UString.esNuloVacio(dto.getDescripcionlocal())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEA_VACIO_DESCRIPCION));	
		}
		if (UString.esNuloVacio(dto.getEstado())) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEA_VACIO_ESTADO));	
		}
		
		if(dto.getLstDetalle().size() >0) {
			
			if(dto.getFlagValida().equals("NO_VALID")){
				lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_UNICA_FAMILIA));
			}else {
				
				for (DtoComunWhClasefamilia detalle : dto.getLstDetalle()) {
					if (UString.esNuloVacio(detalle.getFamilia()) && !detalle.getAccion().equals("E")) {
						lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_VACIO_CODIGO));	
					}
					if (UString.esNuloVacio(detalle.getDescripcionlocal())  && !detalle.getAccion().equals("E")) {
						lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_VACIO_DESCRIPCION));	
					}
					if (UString.esNuloVacio(detalle.getEstado())  && !detalle.getAccion().equals("E")) {
						lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_VACIO_ESTADO));	
					}
					
					if(!detalle.getAccion().equals("E")) {
						if(!UString.esNuloVacio(detalle.getCuentainventario())) {
							List<DtoTabla> lstCuentas = whClaselineaDao.validarCuentas(detalle.getCuentainventario());
							if(lstCuentas.size()==0) {
								lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_CUENTA1_VALID + " - Cta: "+detalle.getCuentainventario()));
							}else {
								if(!lstCuentas.get(0).getEstadoNombre().equals("A")) {
									lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_CUENTA1_ESTADO+ " - Cta: "+detalle.getCuentainventario()));
								}
							}
						}
						
						if(!UString.esNuloVacio(detalle.getCuentagasto())) {
							List<DtoTabla> lstCuentas = whClaselineaDao.validarCuentas(detalle.getCuentagasto());
							if(lstCuentas.size()==0) {
								lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_CUENTA2_VALID + " - Cta: "+detalle.getCuentagasto()));
							}else {
								if(!lstCuentas.get(0).getEstadoNombre().equals("A")) {
									lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_CUENTA2_ESTADO+ " - Cta: "+detalle.getCuentagasto()));
								}
							}
						}
						
						if(!UString.esNuloVacio(detalle.getCuentaventas())) {
							List<DtoTabla> lstCuentas = whClaselineaDao.validarCuentas(detalle.getCuentaventas());
							if(lstCuentas.size()==0) {
								lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_CUENTA3_VALID + " - Cta: "+detalle.getCuentaventas()));
							}else {
								if(!lstCuentas.get(0).getEstadoNombre().equals("A")) {
									lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_CUENTA3_ESTADO+ " - Cta: "+detalle.getCuentaventas()));
								}
							}
						}
						
						if(!UString.esNuloVacio(detalle.getCuentatransito())) {
							List<DtoTabla> lstCuentas = whClaselineaDao.validarCuentas(detalle.getCuentatransito());
							if(lstCuentas.size()==0) {
								lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_CUENTA4_VALID + " - Cta: "+detalle.getCuentatransito()));
							}else {
								if(!lstCuentas.get(0).getEstadoNombre().equals("A")) {
									lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_LINEAF_CUENTA4_ESTADO+ " - Cta: "+detalle.getCuentatransito()));
								}
							}
						}
					}

				}
			}
		}
		
		// TODO WhClaselinea : validaciones comunes Insert/Actualizar
		
		return lst;
	}
	
	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanWhClaselinea whClaselinea) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (whClaselinea == null)
			lst.add(this.getMsjUsuarioError("whclaselinea.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (whClaselinea.getPk() != null) {
			Set<ConstraintViolation<BeanWhClaselineaPk>> reglasPk = validator.validate(whClaselinea.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanWhClaselinea>> reglas = validator.validate(whClaselinea);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
			
		// TODO WhClaselinea : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertarLineas(SeguridadUsuarioActual usuarioActual,BeanWhClaselinea whClaselinea,DtoComunWhClaselinea dto) {
		if (whClaselinea.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClaselinea.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClaselinea = prepararInsertar(usuarioActual, whClaselinea);
		List<DominioMensajeUsuario> lst = coreBasicoCompleto(usuarioActual, whClaselinea,dto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhClaselinea.Insertar : validaciones
		
		return lst;
	}
	
	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanWhClaselinea whClaselinea) {
		if (whClaselinea.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClaselinea.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClaselinea = prepararInsertar(usuarioActual, whClaselinea);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whClaselinea);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhClaselinea.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizarLineas(SeguridadUsuarioActual usuarioActual, BeanWhClaselinea whClaselinea,DtoComunWhClaselinea dto) {
		if (whClaselinea.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClaselinea.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClaselinea = prepararActualizar(usuarioActual, whClaselinea);
		List<DominioMensajeUsuario> lst = coreBasicoCompleto(usuarioActual, whClaselinea,dto);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhClaselinea.Actualizar : validaciones
		
		return lst;
	}
	
	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanWhClaselinea whClaselinea) {
		if (whClaselinea.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClaselinea.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClaselinea = prepararActualizar(usuarioActual, whClaselinea);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, whClaselinea);
		if (!lst.isEmpty())
			return lst;
		
		// TODO WhClaselinea.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhClaselinea whClaselinea) {
		if (whClaselinea.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClaselinea.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClaselinea = prepararEliminar(usuarioActual, whClaselinea);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhClaselinea.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanWhClaselineaPk pk) {
		BeanWhClaselinea bean = whClaselineaDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String plinea) {
		return coreEliminar(usuarioActual,new BeanWhClaselineaPk( plinea));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClaselinea whClaselinea) {
		if (whClaselinea.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		whClaselinea.setAuxFlgValidadoBoolean(Boolean.TRUE);

		whClaselinea = prepararAnular(usuarioActual, whClaselinea);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO WhClaselinea.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanWhClaselineaPk pk) {
		BeanWhClaselinea bean = whClaselineaDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String plinea) {
		return coreAnular(usuarioActual,new BeanWhClaselineaPk( plinea));
	}

	public DtoComunWhClaselinea core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunWhClaselinea dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanWhClaselinea whClaselinea = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, whClaselinea);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, whClaselinea);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, whClaselinea);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, whClaselinea);
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
