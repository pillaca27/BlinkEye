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
import net.royal.spring.comun.boot.SpringComunConstanteBoot;
import net.royal.spring.core.dao.impl.CompanyownerrecursoDaoImpl;
import net.royal.spring.core.dao.impl.MaMiscelaneosdetalleDaoImpl;

import net.royal.spring.core.dominio.BeanCompanyownerrecurso;
import net.royal.spring.core.dominio.BeanCompanyownerrecursoPk;
import net.royal.spring.core.dominio.BeanMaMiscelaneosdetallePk;
import net.royal.spring.core.dominio.dto.DtoComunMaMiscelaneosdetalle;
import net.royal.spring.core.dominio.dto.DtoComunCompanyownerrecurso;

@Service (value = "BeanCompanyownerrecursosServicioValidar")
public class CompanyownerrecursosServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarMaMiscelaneosdetalle";
	private static Logger logger = LogManager.getLogger(CompanyownerrecursosServicioValidar.class);

	@Autowired
	private CompanyownerrecursoDaoImpl dao;

	private BeanCompanyownerrecurso prepararBasico(SeguridadUsuarioActual usuarioActual,BeanCompanyownerrecurso bean, Boolean flgInsertar) {
     bean.setUltimousuario(usuarioActual.getUsuario());
     bean.setUltimafechamodif(new Date());
		
		// TODO Companyownerrecurso : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return bean;
	}
		
	public BeanCompanyownerrecurso prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanCompanyownerrecurso bean) {
		if (bean == null)
			return bean;
		if (bean.getAuxFlgPreparadoBoolean())
			return bean;
		bean = prepararBasico(usuarioActual,bean, true);
		bean.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companyownerrecurso.Insertar : valores por defecto
		
		return bean;
	}

	public BeanCompanyownerrecurso prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanCompanyownerrecurso bean) {
		if (bean == null)
			return bean;
		if (bean.getAuxFlgPreparadoBoolean())
			return bean;
		bean = prepararBasico(usuarioActual,bean, false);
		bean.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companyownerrecurso.Actualizar : valores por defecto
		
		return bean;
	}

	public BeanCompanyownerrecurso prepararAnular(SeguridadUsuarioActual usuarioActual,BeanCompanyownerrecurso bean) {
		if (bean == null)
			return bean;
		if (bean.getAuxFlgPreparadoBoolean())
			return bean;
		bean = prepararBasico(usuarioActual, bean, false);
		bean.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companyownerrecurso.Anular : valores por defecto
		
		return bean;
	}

	public BeanCompanyownerrecurso prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanCompanyownerrecurso bean) {
		if (bean == null)
			return bean;
		if (bean.getAuxFlgPreparadoBoolean())
			return bean;
		bean.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Companyownerrecurso.Eliminar : valores por defecto
		
		return bean;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanCompanyownerrecurso bean) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (bean == null)
			lst.add(this.getMsjUsuarioError("mamiscelaneosdetalle.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (bean.getPk() != null) {
			Set<ConstraintViolation<BeanCompanyownerrecursoPk>> reglasPk = validator.validate(bean.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanCompanyownerrecurso>> reglas = validator.validate(bean);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Companyownerrecurso : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanCompanyownerrecurso bean) {
		if (bean.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		bean.setAuxFlgValidadoBoolean(Boolean.TRUE);

		bean = prepararInsertar(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();//coreBasico(usuarioActual, bean);
		
		BeanCompanyownerrecurso bean2 = dao.obtenerPorId(bean.getPk());

		if (bean2 != null) {
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_UNICA));
		}
		
		if (UString.estaVacio(bean.getPk().getTiporecurso())) 
		{
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_TIPO_RECURSO));
		}
		
		if (UString.estaVacio(bean.getPk().getCompanyowner()))
		{
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_COMPANIA_CODIGO));
		}

		if (UString.estaVacio(bean.getPk().getPeriodo()))
		{
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_CODIGO_PERIODO));
		}

		if (UString.estaVacio(bean.getAuxString()))
		{
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_REPORTE));
		}			
		
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO Companyownerrecurso.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanCompanyownerrecurso bean) {
		if (bean.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		bean.setAuxFlgValidadoBoolean(Boolean.TRUE);

		bean = prepararActualizar(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();//coreBasico(usuarioActual, bean);
		
		if (UString.estaVacio(bean.getPk().getTiporecurso())) 
		{
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_TIPO_RECURSO));
		}
		
		if (UString.estaVacio(bean.getPk().getCompanyowner()))
		{
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_RESTRICCION_COMPANIA_CODIGO));
		}
		
		
		if (UString.estaVacio(bean.getPk().getPeriodo()))
		{
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_CODIGO_PERIODO));
		}

		if (UString.estaVacio(bean.getAuxString()))
		{
			lst.add(this.getMsjUsuarioError(SpringComunConstanteBoot.VAL_MISC_D_RESTRICCION_REPORTE));
		}	
		
		if (!lst.isEmpty())
			return lst;
		
		// TODO Companyownerrecurso.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanCompanyownerrecurso bean) {
		if (bean.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		bean.setAuxFlgValidadoBoolean(Boolean.TRUE);

		bean = prepararEliminar(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Companyownerrecurso.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual, BeanCompanyownerrecursoPk pk) {
		BeanCompanyownerrecurso bean = dao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcompania,String ptipo,String pperiodo) {
		return coreEliminar(usuarioActual,new BeanCompanyownerrecursoPk( pcompania, ptipo, pperiodo));
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanCompanyownerrecurso bean) {
		if (bean.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		bean.setAuxFlgValidadoBoolean(Boolean.TRUE);

		bean = prepararAnular(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Companyownerrecurso.Anular : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, BeanCompanyownerrecursoPk pk) {
		BeanCompanyownerrecurso bean = dao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreAnular(SeguridadUsuarioActual usuarioActual, String pcompania,String ptipo,String pperiodo) {
		return coreAnular(usuarioActual,new BeanCompanyownerrecursoPk( pcompania, ptipo, pperiodo));
	}

	public DtoComunMaMiscelaneosdetalle core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunMaMiscelaneosdetalle dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanCompanyownerrecurso bean = new BeanCompanyownerrecurso();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, bean);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, bean);
		if (accion.equals(ConstantePantallaAccion.ANULAR))
			lst = coreAnular(usuarioActual, bean);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, bean);
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
