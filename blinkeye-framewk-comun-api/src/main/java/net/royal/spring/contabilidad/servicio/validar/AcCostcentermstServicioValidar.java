package net.royal.spring.contabilidad.servicio.validar;

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

import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.contabilidad.dao.impl.AcCostcentermstDaoImpl;
import net.royal.spring.contabilidad.dominio.BeanAcCostcentermst;
import net.royal.spring.contabilidad.dominio.BeanAcCostcentermstPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcentermst;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;
import net.royal.spring.logistica.dao.impl.WhItemmastDaoImpl;

@Service(value = "BeanValidarAcCostcentermst")
public class AcCostcentermstServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarAcCostcentermst";
	private static Logger logger = LogManager.getLogger(AcCostcentermstServicioValidar.class);

	@Autowired
	private AcCostcentermstDaoImpl acCostcentermstDao;
	
	@Autowired
	private WhItemmastDaoImpl whItemmastDao;

	private BeanAcCostcentermst prepararBasico(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst acCostcentermst,
			Boolean flgInsertar) {

		// TODO AcCostcentermst : valores por defecto comunes
		// Insert/Actualizar/Anular/Eliminar
		acCostcentermst.setLastdate(new Date());
		acCostcentermst.setLastuser(usuarioActual.getUsuario());
		return acCostcentermst;
	}

	public BeanAcCostcentermst prepararInsertar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst acCostcentermst) {
		if (acCostcentermst == null)
			return acCostcentermst;
		if (acCostcentermst.getAuxFlgPreparadoBoolean())
			return acCostcentermst;
		acCostcentermst = prepararBasico(usuarioActual, acCostcentermst, true);
		acCostcentermst.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		acCostcentermst.setMulticompanyflag("S");
		acCostcentermst.setUuid(UUID.randomUUID().toString());
		// TODO AcCostcentermst.Insertar : valores por defecto

		return acCostcentermst;
	}

	public BeanAcCostcentermst prepararActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst acCostcentermst) {
		if (acCostcentermst == null)
			return acCostcentermst;
		if (acCostcentermst.getAuxFlgPreparadoBoolean())
			return acCostcentermst;
		acCostcentermst = prepararBasico(usuarioActual, acCostcentermst, false);
		acCostcentermst.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO AcCostcentermst.Actualizar : valores por defecto

		return acCostcentermst;
	}

	public BeanAcCostcentermst prepararAnular(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst acCostcentermst) {
		if (acCostcentermst == null)
			return acCostcentermst;
		if (acCostcentermst.getAuxFlgPreparadoBoolean())
			return acCostcentermst;
		acCostcentermst = prepararBasico(usuarioActual, acCostcentermst, false);
		acCostcentermst.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO AcCostcentermst.Anular : valores por defecto

		return acCostcentermst;
	}

	public BeanAcCostcentermst prepararEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst acCostcentermst) {
		if (acCostcentermst == null)
			return acCostcentermst;
		if (acCostcentermst.getAuxFlgPreparadoBoolean())
			return acCostcentermst;
		acCostcentermst.setAuxFlgPreparadoBoolean(Boolean.TRUE);

		// TODO AcCostcentermst.Eliminar : valores por defecto

		return acCostcentermst;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual,
			BeanAcCostcentermst acCostcentermst) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (acCostcentermst == null)
			lst.add(this.getMsjUsuarioError("accostcentermst.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (acCostcentermst.getPk() != null) {
			Set<ConstraintViolation<BeanAcCostcentermstPk>> reglasPk = validator.validate(acCostcentermst.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanAcCostcentermst>> reglas = validator.validate(acCostcentermst);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}

		// TODO AcCostcentermst : validaciones comunes Insert/Actualizar

		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,
			BeanAcCostcentermst acCostcentermst) {
		if (acCostcentermst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcentermst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if (UString.esNuloVacio(acCostcentermst.getPk().getCostcenter())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CODIGO));
		} else {
			BeanAcCostcentermst prymeDao = acCostcentermstDao.obtenerPorId(acCostcentermst.getPk());
			if (prymeDao != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CODIGO_VALID));
			}
		}
		if (UString.esNuloVacio(acCostcentermst.getLocalname())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_DESCRIPCION));
		}
		if (UInteger.esCeroOrNulo(acCostcentermst.getVendor())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_EMPLEADO));
		}

		// PARAMETRO 1
		if (acCostcentermst.getAuxParametro1().equals("S")) {
			if (UString.esNuloVacio(acCostcentermst.getCostcenternext())) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_NIVEL));
			}
			
			if(!UString.esNuloVacio(acCostcentermst.getAccount())) {
				List<DtoTabla> lstCuentas = whItemmastDao.validarCuentas(acCostcentermst.getAccount());
				if(lstCuentas.size()==0) {
					lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CUENTA_DEFECTO_VALID));
				}else {
					if(!lstCuentas.get(0).getEstadoNombre().equals("A")) {
						lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CUENTA_DEFECTO_ESTADO));
					}
				}
			}
			if(!UString.esNuloVacio(acCostcentermst.getAccountdestination())) {
				List<DtoTabla> lstCuentas = whItemmastDao.validarCuentas(acCostcentermst.getAccountdestination());
				if(lstCuentas.size()==0) {
					lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CUENTA_ESTADISTICA_VALID));
				}else {
					if(!lstCuentas.get(0).getEstadoNombre().equals("A")) {
						lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CUENTA_ESTADISTICA_ESTADO));
					}
				}
			}
		}

		// PARAMETRO 2
		if (acCostcentermst.getAuxParametro2().equals("S")) {
			if (UString.esNuloVacio(acCostcentermst.getSucursal())) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_SUCURSAL));
			}
			if (UString.esNuloVacio(acCostcentermst.getCostcenterclasification())) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CLASIFICACION));
			}
			if (UString.esNuloVacio(acCostcentermst.getCostcentergroup())) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_GRUPO));
			}
		}

		if (!lstRes.isEmpty())
			return lstRes;

		acCostcentermst = prepararInsertar(usuarioActual, acCostcentermst);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcentermst);
		if (!lst.isEmpty())
			return lst;

		// TODO AcCostcentermst.Insertar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual,
			BeanAcCostcentermst acCostcentermst) {
		if (acCostcentermst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcentermst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if (UString.esNuloVacio(acCostcentermst.getLocalname())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_DESCRIPCION));
		}
		if (UInteger.esCeroOrNulo(acCostcentermst.getVendor())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_EMPLEADO));
		}

		// PARAMETRO 1
		if (acCostcentermst.getAuxParametro1().equals("S")) {
			if (UString.esNuloVacio(acCostcentermst.getCostcenternext())) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_NIVEL));
			} else {

				if (!acCostcentermst.getCostcenternext().equals("9999")) {
					if (acCostcentermst.getCostcenternext().equals(acCostcentermst.getPk().getCostcenter())) {
						lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CUENTA_IGUAL));
					} else {
						System.out.println("VALID");
					}
				}
			}

			if(!UString.esNuloVacio(acCostcentermst.getAccount())) {
				List<DtoTabla> lstCuentas = whItemmastDao.validarCuentas(acCostcentermst.getAccount());
				if(lstCuentas.size()==0) {
					lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CUENTA_DEFECTO_VALID));
				}else {
					if(!lstCuentas.get(0).getEstadoNombre().equals("A")) {
						lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CUENTA_DEFECTO_ESTADO));
					}
				}
			}
			if(!UString.esNuloVacio(acCostcentermst.getAccountdestination())) {
				List<DtoTabla> lstCuentas = whItemmastDao.validarCuentas(acCostcentermst.getAccountdestination());
				if(lstCuentas.size()==0) {
					lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CUENTA_ESTADISTICA_VALID));
				}else {
					if(!lstCuentas.get(0).getEstadoNombre().equals("A")) {
						lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_CUENTA_ESTADISTICA_ESTADO));
					}
				}
			}
		}

		// PARAMETRO 2
		if (acCostcentermst.getAuxParametro2().equals("S")) {
			if (UString.esNuloVacio(acCostcentermst.getSucursal())) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CENTROCOSTO_SUCURSAL));
			}
			if (UString.esNuloVacio(acCostcentermst.getCostcenterclasification())) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_CLASIFICACION));
			}
			if (UString.esNuloVacio(acCostcentermst.getCostcentergroup())) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_GRUPO));
			}
		}

		if (!lstRes.isEmpty())
			return lstRes;

		acCostcentermst = prepararActualizar(usuarioActual, acCostcentermst);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, acCostcentermst);
		if (!lst.isEmpty())
			return lst;

		// TODO AcCostcentermst.Actualizar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,
			BeanAcCostcentermst acCostcentermst) {
		if (acCostcentermst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		acCostcentermst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		acCostcentermst = prepararEliminar(usuarioActual, acCostcentermst);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();

		// TODO AcCostcentermst.Eliminar : validaciones

		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermstPk pk) {
		BeanAcCostcentermst bean = acCostcentermstDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, String pcostcenter) {
		return coreEliminar(usuarioActual, new BeanAcCostcentermstPk(pcostcenter));
	}
	
	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst banco) {
		if (banco.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		banco.setAuxFlgValidadoBoolean(Boolean.TRUE);

		banco = prepararAnular(usuarioActual, banco);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Banco.Anular : validaciones
		
		return lst;
	}
 

	public DtoComunAcCostcentermst core(SeguridadUsuarioActual usuarioActual, String accion, DtoComunAcCostcentermst dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanAcCostcentermst acCostcentermst = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, acCostcentermst);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, acCostcentermst);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, acCostcentermst);
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

	public BeanAcCostcentermst prepararAuditoria(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst asAutorizacion) {
		asAutorizacion.setLastuser(usuarioActual.getUsuario());
		asAutorizacion.setLastdate(new Date());
		return asAutorizacion;
	}
}
