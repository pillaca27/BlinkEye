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
import net.royal.spring.comun.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.DepartmentmstDaoImpl;

import net.royal.spring.core.dominio.BeanDepartmentmst;
import net.royal.spring.core.dominio.BeanDepartmentmstPk;
 
import net.royal.spring.core.dominio.dto.DtoComunDepartmentmst;

@Service (value = "BeanValidarDepartmentmst")
public class DepartmentmstServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarDepartmentmst";
	private static Logger logger = LogManager.getLogger(DepartmentmstServicioValidar.class);

	@Autowired
	private DepartmentmstDaoImpl departmentmstDao;

	private BeanDepartmentmst prepararBasico(SeguridadUsuarioActual usuarioActual,BeanDepartmentmst departmentmst, Boolean flgInsertar) {
		
		// TODO Departmentmst : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		departmentmst.setLastdate(new Date());
		departmentmst.setLastuser(usuarioActual.getUsuario());
		return departmentmst;
	}

	public BeanDepartmentmst prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanDepartmentmst departmentmst) {
		if (departmentmst == null)
			return departmentmst;
		if (departmentmst.getAuxFlgPreparadoBoolean())
			return departmentmst;
		departmentmst = prepararBasico(usuarioActual,departmentmst, true);
		departmentmst.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Departmentmst.Insertar : valores por defecto
		
		return departmentmst;
	}

	public BeanDepartmentmst prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanDepartmentmst departmentmst) {
		if (departmentmst == null)
			return departmentmst;
		if (departmentmst.getAuxFlgPreparadoBoolean())
			return departmentmst;
		departmentmst = prepararBasico(usuarioActual,departmentmst, false);
		departmentmst.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Departmentmst.Actualizar : valores por defecto
		
		return departmentmst;
	}

	public BeanDepartmentmst prepararAnular(SeguridadUsuarioActual usuarioActual,BeanDepartmentmst departmentmst) {
		if (departmentmst == null)
			return departmentmst;
		if (departmentmst.getAuxFlgPreparadoBoolean())
			return departmentmst;
		departmentmst = prepararBasico(usuarioActual, departmentmst, false);
		departmentmst.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Departmentmst.Anular : valores por defecto
		
		return departmentmst;
	}

	public BeanDepartmentmst prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanDepartmentmst departmentmst) {
		if (departmentmst == null)
			return departmentmst;
		if (departmentmst.getAuxFlgPreparadoBoolean())
			return departmentmst;
		departmentmst.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Departmentmst.Eliminar : valores por defecto
		
		return departmentmst;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanDepartmentmst departmentmst) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (departmentmst == null)
			lst.add(this.getMsjUsuarioError("departmentmst.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (departmentmst.getPk() != null) {
			Set<ConstraintViolation<BeanDepartmentmstPk>> reglasPk = validator.validate(departmentmst.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanDepartmentmst>> reglas = validator.validate(departmentmst);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Departmentmst : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanDepartmentmst departmentmst) {
		if (departmentmst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		departmentmst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(departmentmst.getPk().getDepartment())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_DEP_DEPARTAMENTO));
		}else {
			BeanDepartmentmst departamentotDto= departmentmstDao.obtenerPorId(departmentmst.getPk());
			if(departamentotDto != null) {
				lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_DEP_DEPARTAMENTO_VALID));
			}
		}
		if(UString.esNuloVacio(departmentmst.getDescription())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_DEP_DESCRIPCION));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		departmentmst = prepararInsertar(usuarioActual, departmentmst);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, departmentmst);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Departmentmst.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanDepartmentmst departmentmst) {
		if (departmentmst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		departmentmst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		List<DominioMensajeUsuario> lstRes = new ArrayList<DominioMensajeUsuario>();
		if(UString.esNuloVacio(departmentmst.getDescription())) {
			lstRes.add(this.getMsjUsuarioError(SpringWhConstanteBoot.VAL_DEP_DESCRIPCION));
		}
		if (!lstRes.isEmpty())
			return lstRes;
		
		departmentmst = prepararActualizar(usuarioActual, departmentmst);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, departmentmst);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Departmentmst.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanDepartmentmst departmentmst) {
		if (departmentmst.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		departmentmst.setAuxFlgValidadoBoolean(Boolean.TRUE);

		departmentmst = prepararEliminar(usuarioActual, departmentmst);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Departmentmst.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanDepartmentmstPk pk) {
		BeanDepartmentmst bean = departmentmstDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pdepartment) {
		return coreEliminar(usuarioActual,new BeanDepartmentmstPk( pdepartment));
	}


	public DtoComunDepartmentmst core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunDepartmentmst dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanDepartmentmst departmentmst = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, departmentmst);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, departmentmst);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, departmentmst);
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
