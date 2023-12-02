package net.royal.spring.core.servicio.validar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.core.dao.impl.TipocambiomastDaoImpl;
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.BeanTipocambiomastPk;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarTipocambiomast")
public class TipocambiomastServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarTipocambiomast";
	private static Logger logger = LogManager.getLogger(TipocambiomastServicioValidar.class);

	@Autowired
	private TipocambiomastDaoImpl tipocambiomastDao;

	private BeanTipocambiomast prepararBasico(SeguridadUsuarioActual usuarioActual,BeanTipocambiomast tipocambiomast, Boolean flgInsertar) {
     tipocambiomast.setUltimousuario(usuarioActual.getUsuario());
     tipocambiomast.setUltimafechamodif(new Date());
		
		// TODO Tipocambiomast : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return tipocambiomast;
	}

	public BeanTipocambiomast prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast == null)
			return tipocambiomast;
		if (tipocambiomast.getAuxFlgPreparadoBoolean())
			return tipocambiomast;
		
		tipocambiomast.getPk().setFechacambio( removeTime(tipocambiomast.getPk().getFechacambio()  ) );
		tipocambiomast.setFechacambiostring(new SimpleDateFormat("yyyy/MM/dd").format(tipocambiomast.getPk().getFechacambio()).replace("/", ""));		
		
		tipocambiomast = prepararBasico(usuarioActual,tipocambiomast, true);
		tipocambiomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);	
		tipocambiomast.setUuid(UUID.randomUUID().toString());
		// TODO Tipocambiomast.Insertar : valores por defecto
		
		return tipocambiomast;
	}
	
	
	public static Date removeTime(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);  
        cal.set(Calendar.HOUR_OF_DAY, 0);  
        cal.set(Calendar.MINUTE, 0);  
        cal.set(Calendar.SECOND, 0);  
        cal.set(Calendar.MILLISECOND, 0);  
        return cal.getTime(); 
    }
	
	public static String setCadenaFechaString(Date date) {    
        Calendar cal = Calendar.getInstance();  
        cal.setTime(date);
        Integer dia, mes, anio;
        String mes2;
        dia = cal.get(Calendar.DATE);
        mes = cal.get(Calendar.MONTH);  
        anio = cal.get(Calendar.YEAR);
        mes = mes + 1;
        
        if(mes < 10) {
        	mes2 = "0"+mes;
        }else {
        	mes2 = mes.toString();
        }
        
        
        String cadena = anio.toString()+mes2+dia.toString();
        return cadena; 
    }

	public BeanTipocambiomast prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast == null)
			return tipocambiomast;
		if (tipocambiomast.getAuxFlgPreparadoBoolean())
			return tipocambiomast;
		
		tipocambiomast.getPk().setFechacambio( removeTime(tipocambiomast.getPk().getFechacambio()  ) );
		tipocambiomast.setFechacambiostring(setCadenaFechaString(tipocambiomast.getPk().getFechacambio()));
		
		tipocambiomast = prepararBasico(usuarioActual,tipocambiomast, false);
		tipocambiomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tipocambiomast.Actualizar : valores por defecto
		
		return tipocambiomast;
	}

	public BeanTipocambiomast prepararAnular(SeguridadUsuarioActual usuarioActual,BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast == null)
			return tipocambiomast;
		if (tipocambiomast.getAuxFlgPreparadoBoolean())
			return tipocambiomast;
		
		tipocambiomast.getPk().setFechacambio( removeTime(tipocambiomast.getPk().getFechacambio()  ) );
		tipocambiomast.setFechacambiostring(setCadenaFechaString(tipocambiomast.getPk().getFechacambio()));
		
		tipocambiomast = prepararBasico(usuarioActual, tipocambiomast, false);
		tipocambiomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tipocambiomast.Anular : valores por defecto
		
		return tipocambiomast;
	}

	public BeanTipocambiomast prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast == null)
			return tipocambiomast;
		if (tipocambiomast.getAuxFlgPreparadoBoolean())
			return tipocambiomast;
		tipocambiomast.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Tipocambiomast.Eliminar : valores por defecto
		
		return tipocambiomast;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (tipocambiomast == null)
			lst.add(this.getMsjUsuarioError("tipocambiomast.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (tipocambiomast.getPk() != null) {
			Set<ConstraintViolation<BeanTipocambiomastPk>> reglasPk = validator.validate(tipocambiomast.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanTipocambiomast>> reglas = validator.validate(tipocambiomast);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		if(tipocambiomast.getFactorcompra() == null) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_COMPRA_NULO_TIPOCAMBIO));
		}
		
		if(tipocambiomast.getFactorventa() == null) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_VENTA_NULO_TIPOCAMBIO));
		}	
		
		if(tipocambiomast.getPk().getMonedacodigo() == tipocambiomast.getPk().getMonedacambiocodigo()) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MONEDA_IGUAL_TIPOCAMBIO));
		}
		
		
		
		// TODO Tipocambiomast : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipocambiomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipocambiomast = prepararInsertar(usuarioActual, tipocambiomast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipocambiomast);
		
		BeanTipocambiomast tipo = tipocambiomastDao.obtenerPorId(tipocambiomast.getPk());
		if(tipo != null) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_RESTRICCION_UNICA_TIPOCAMBIO));			
		}
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO Tipocambiomast.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipocambiomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipocambiomast = prepararActualizar(usuarioActual, tipocambiomast);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, tipocambiomast);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Tipocambiomast.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipocambiomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipocambiomast = prepararEliminar(usuarioActual, tipocambiomast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Tipocambiomast.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanTipocambiomastPk pk) {
		BeanTipocambiomast bean = tipocambiomastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pmonedacodigo,String pmonedacambiocodigo,java.util.Date pfechacambio) {
		return coreEliminar(usuarioActual,new BeanTipocambiomastPk( pmonedacodigo, pmonedacambiocodigo, pfechacambio));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast tipocambiomast) {
		if (tipocambiomast.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		tipocambiomast.setAuxFlgValidadoBoolean(Boolean.TRUE);

		tipocambiomast = prepararAnular(usuarioActual, tipocambiomast);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Tipocambiomast.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanTipocambiomastPk pk) {
		BeanTipocambiomast bean = tipocambiomastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pmonedacodigo,String pmonedacambiocodigo,java.util.Date pfechacambio) {
		return coreAnular(usuarioActual,new BeanTipocambiomastPk( pmonedacodigo, pmonedacambiocodigo, pfechacambio));
	}

	public DtoComunTipocambiomast core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunTipocambiomast dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanTipocambiomast tipocambiomast = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, tipocambiomast);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, tipocambiomast);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, tipocambiomast);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, tipocambiomast);
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

	public BeanTipocambiomast prepararAuditoria(SeguridadUsuarioActual usuarioActual, BeanTipocambiomast asAutorizacion) {
		asAutorizacion.setUltimousuario(usuarioActual.getUsuario());
		asAutorizacion.setUltimafechamodif(new Date());
		return asAutorizacion;
	}
}
