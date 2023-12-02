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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.DepartamentoDaoImpl;
import net.royal.spring.core.dominio.BeanDepartamento;
import net.royal.spring.core.dominio.BeanDepartamentoPk;
import net.royal.spring.core.dominio.dto.DtoComunDepartamento;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioValidar;

@Service (value = "BeanValidarDepartamento")
public class DepartamentoServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarDepartamento";
	private static Logger logger = LogManager.getLogger(DepartamentoServicioValidar.class);

	@Autowired
	private DepartamentoDaoImpl departamentoDao;

	private BeanDepartamento prepararBasico(SeguridadUsuarioActual usuarioActual,BeanDepartamento departamento, Boolean flgInsertar) {
     departamento.setUltimousuario(usuarioActual.getUsuario());
     departamento.setUltimafechamodif(new Date());
		
		// TODO Departamento : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return departamento;
	}

	public BeanDepartamento prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanDepartamento departamento) {
		if (departamento == null)
			return departamento;
		if (departamento.getAuxFlgPreparadoBoolean())
			return departamento;
		departamento = prepararBasico(usuarioActual,departamento, true);
		departamento.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		departamento.setUuid(UUID.randomUUID().toString());
		// TODO Departamento.Insertar : valores por defecto
		
		return departamento;
	}

	public BeanDepartamento prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanDepartamento departamento) {
		if (departamento == null)
			return departamento;
		if (departamento.getAuxFlgPreparadoBoolean())
			return departamento;
		departamento = prepararBasico(usuarioActual,departamento, false);
		departamento.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Departamento.Actualizar : valores por defecto
		
		return departamento;
	}

	public BeanDepartamento prepararAnular(SeguridadUsuarioActual usuarioActual,BeanDepartamento departamento) {
		if (departamento == null)
			return departamento;
		if (departamento.getAuxFlgPreparadoBoolean())
			return departamento;
		departamento = prepararBasico(usuarioActual, departamento, false);
		departamento.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Departamento.Anular : valores por defecto
		
		return departamento;
	}

	public BeanDepartamento prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanDepartamento departamento) {
		if (departamento == null)
			return departamento;
		if (departamento.getAuxFlgPreparadoBoolean())
			return departamento;
		departamento.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Departamento.Eliminar : valores por defecto
		
		return departamento;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanDepartamento departamento) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (departamento == null)
			lst.add(this.getMsjUsuarioError("departamento.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (departamento.getPk() != null) {
			Set<ConstraintViolation<BeanDepartamentoPk>> reglasPk = validator.validate(departamento.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanDepartamento>> reglas = validator.validate(departamento);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Departamento : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanDepartamento departamento) {
		if (departamento.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		departamento.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(departamento.getPk().getDepartamento())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_DEP_GEO_CODIGO));
		}else {
			BeanDepartamento DptoBean= departamentoDao.obtenerPorId(departamento.getPk());
			if(DptoBean != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_DEP_GEO_CODIGO_VALID));
			}
		}
		if(UString.esNuloVacio(departamento.getDescripcioncorta())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_DEP_GEO_DESCRI));
		}
	
		if (!lstRes.isEmpty())
			return lstRes;
		
		departamento = prepararInsertar(usuarioActual, departamento);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, departamento);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Departamento.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanDepartamento departamento) {
		if (departamento.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		departamento.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(departamento.getDescripcioncorta())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_DEP_GEO_DESCRI));
		}
	
		if (!lstRes.isEmpty())
			return lstRes;
		
		departamento = prepararActualizar(usuarioActual, departamento);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, departamento);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Departamento.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanDepartamento departamento) {
		if (departamento.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		departamento.setAuxFlgValidadoBoolean(Boolean.TRUE);

		departamento = prepararEliminar(usuarioActual, departamento);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Departamento.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanDepartamentoPk pk) {
		BeanDepartamento bean = departamentoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pdepartamento) {
		return coreEliminar(usuarioActual,new BeanDepartamentoPk( pdepartamento));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanDepartamento departamento) {
		if (departamento.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		departamento.setAuxFlgValidadoBoolean(Boolean.TRUE);

		departamento = prepararAnular(usuarioActual, departamento);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Departamento.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanDepartamentoPk pk) {
		BeanDepartamento bean = departamentoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pdepartamento) {
		return coreAnular(usuarioActual,new BeanDepartamentoPk( pdepartamento));
	}

	public DtoComunDepartamento core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunDepartamento dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanDepartamento departamento = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, departamento);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, departamento);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, departamento);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, departamento);
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
	public BeanDepartamento prepararAuditoria(SeguridadUsuarioActual usuarioActual, BeanDepartamento asAutorizacion) {
		asAutorizacion.setUltimousuario(usuarioActual.getUsuario());
		asAutorizacion.setUltimafechamodif(new Date());
		return asAutorizacion;
	}
}
