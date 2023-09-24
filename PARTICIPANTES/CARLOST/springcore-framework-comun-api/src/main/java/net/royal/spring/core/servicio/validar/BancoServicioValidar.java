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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.BancoDaoImpl;
import net.royal.spring.core.dominio.BeanBanco;
import net.royal.spring.core.dominio.BeanBancoPk;
import net.royal.spring.core.dominio.dto.DtoComunBanco;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarBanco")
public class BancoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarBanco";
	private static Logger logger = LogManager.getLogger(BancoServicioValidar.class);

	@Autowired
	private BancoDaoImpl bancoDao;

	private BeanBanco prepararBasico(SeguridadUsuarioActual usuarioActual,BeanBanco banco, Boolean flgInsertar) {
     banco.setUltimousuario(usuarioActual.getUsuario());
     banco.setUltimafechamodif(new Date());
		
		// TODO Banco : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return banco;
	}

	public BeanBanco prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanBanco banco) {
		if (banco == null)
			return banco;
		if (banco.getAuxFlgPreparadoBoolean())
			return banco;
		banco = prepararBasico(usuarioActual,banco, true);
		banco.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Banco.Insertar : valores por defecto
		
		return banco;
	}

	public BeanBanco prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanBanco banco) {
		if (banco == null)
			return banco;
		if (banco.getAuxFlgPreparadoBoolean())
			return banco;
		banco = prepararBasico(usuarioActual,banco, false);
		banco.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Banco.Actualizar : valores por defecto
		
		return banco;
	}

	public BeanBanco prepararAnular(SeguridadUsuarioActual usuarioActual,BeanBanco banco) {
		if (banco == null)
			return banco;
		if (banco.getAuxFlgPreparadoBoolean())
			return banco;
		banco = prepararBasico(usuarioActual, banco, false);
		banco.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Banco.Anular : valores por defecto
		
		return banco;
	}

	public BeanBanco prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanBanco banco) {
		if (banco == null)
			return banco;
		if (banco.getAuxFlgPreparadoBoolean())
			return banco;
		banco.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Banco.Eliminar : valores por defecto
		
		return banco;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanBanco banco) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (banco == null)
			lst.add(this.getMsjUsuarioError("banco.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (banco.getPk() != null) {
			Set<ConstraintViolation<BeanBancoPk>> reglasPk = validator.validate(banco.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanBanco>> reglas = validator.validate(banco);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Banco : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanBanco banco) {
		if (banco.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		banco.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(banco.getPk().getBanco())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_CODIGO));
		}else {
			BeanBanco bancoDto= bancoDao.obtenerPorId(banco.getPk());
			if(bancoDto != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_CODIGO_VALID));
			}
		}
		if(UString.esNuloVacio(banco.getDescripcioncorta())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_DESCRIPCION_CORTA));
		}
		if(UInteger.esCeroOrNulo(banco.getBanconumero())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_PERSONA));
		}
			
		if(!UString.esNuloVacio(banco.getFormatopropioflag())) {
			if(banco.getFormatopropioflag().equals("S")) {
				if(UString.esNuloVacio(banco.getFormatodatawindow())) {
					lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_CHEQUE));
				}
				else {
					System.out.println("VALID");
				}
			}else {
				System.out.println("VALID");
			}
		}
		
		if(!UString.esNuloVacio(banco.getConciliacionautomaticaflag())) {
			if(banco.getConciliacionautomaticaflag().equals("S")) {
				if(UString.esNuloVacio(banco.getConciliacionformatoflag())) {
					lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_CONCILIACION));
				}else {
					System.out.println("VALID");
				}
			}else {
				System.out.println("VALID");
			}
		}
		
		if (!lstRes.isEmpty())
			return lstRes;
		
		banco = prepararInsertar(usuarioActual, banco);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, banco);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Banco.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanBanco banco) {
		if (banco.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		banco.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(banco.getDescripcioncorta())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_DESCRIPCION_CORTA));
		}
		if(UInteger.esCeroOrNulo(banco.getBanconumero())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_PERSONA));
		}
			
		if(!UString.esNuloVacio(banco.getFormatopropioflag())) {
			if(banco.getFormatopropioflag().equals("S")) {
				if(UString.esNuloVacio(banco.getFormatodatawindow())) {
					lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_CHEQUE));
				}else {
					System.out.println("VALID");
				}
			}else {
				System.out.println("VALID");
			}
		}
		
		if(!UString.esNuloVacio(banco.getConciliacionautomaticaflag())) {
			if(banco.getConciliacionautomaticaflag().equals("S")) {
				if(UString.esNuloVacio(banco.getConciliacionformatoflag())) {
					lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_BANCO_CONCILIACION));
				}else {
					System.out.println("VALID");
				}
			}else {
				System.out.println("VALID");
			}
		}

		
		if (!lstRes.isEmpty())
			return lstRes;
		
		banco = prepararActualizar(usuarioActual, banco);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, banco);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Banco.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanBanco banco) {
		if (banco.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		banco.setAuxFlgValidadoBoolean(Boolean.TRUE);

		banco = prepararEliminar(usuarioActual, banco);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Banco.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanBancoPk pk) {
		BeanBanco bean = bancoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pbanco) {
		return coreEliminar(usuarioActual,new BeanBancoPk( pbanco));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanBanco banco) {
		if (banco.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		banco.setAuxFlgValidadoBoolean(Boolean.TRUE);

		banco = prepararAnular(usuarioActual, banco);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Banco.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanBancoPk pk) {
		BeanBanco bean = bancoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pbanco) {
		return coreAnular(usuarioActual,new BeanBancoPk( pbanco));
	}

	public DtoComunBanco core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunBanco dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanBanco banco = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, banco);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, banco);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, banco);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, banco);
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
