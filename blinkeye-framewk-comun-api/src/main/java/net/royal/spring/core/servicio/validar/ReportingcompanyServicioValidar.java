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
import net.royal.spring.core.dao.impl.ReportingcompanyDaoImpl;

import net.royal.spring.core.dominio.BeanReportingcompany;
import net.royal.spring.core.dominio.BeanReportingcompanyPk;
import net.royal.spring.core.dominio.dto.DtoComunReportingcompany;

@Service (value = "BeanValidarReportingcompany")
public class ReportingcompanyServicioValidar extends GenericoServicioValidar {
	public static String SPRING_NOMBRE = "BeanValidarReportingcompany";
	private static Logger logger = LogManager.getLogger(ReportingcompanyServicioValidar.class);

	@Autowired
	private ReportingcompanyDaoImpl reportingcompanyDao;

	private BeanReportingcompany prepararBasico(SeguridadUsuarioActual usuarioActual,BeanReportingcompany reportingcompany, Boolean flgInsertar) {
		
		// TODO Reportingcompany : valores por defecto comunes Insert/Actualizar/Anular/Eliminar
		
		return reportingcompany;
	}

	public BeanReportingcompany prepararInsertar(SeguridadUsuarioActual usuarioActual,BeanReportingcompany reportingcompany) {
		if (reportingcompany == null)
			return reportingcompany;
		if (reportingcompany.getAuxFlgPreparadoBoolean())
			return reportingcompany;
		reportingcompany = prepararBasico(usuarioActual,reportingcompany, true);
		reportingcompany.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Reportingcompany.Insertar : valores por defecto
		
		return reportingcompany;
	}

	public BeanReportingcompany prepararActualizar(SeguridadUsuarioActual usuarioActual,BeanReportingcompany reportingcompany) {
		if (reportingcompany == null)
			return reportingcompany;
		if (reportingcompany.getAuxFlgPreparadoBoolean())
			return reportingcompany;
		reportingcompany = prepararBasico(usuarioActual,reportingcompany, false);
		reportingcompany.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Reportingcompany.Actualizar : valores por defecto
		
		return reportingcompany;
	}

	public BeanReportingcompany prepararAnular(SeguridadUsuarioActual usuarioActual,BeanReportingcompany reportingcompany) {
		if (reportingcompany == null)
			return reportingcompany;
		if (reportingcompany.getAuxFlgPreparadoBoolean())
			return reportingcompany;
		reportingcompany = prepararBasico(usuarioActual, reportingcompany, false);
		reportingcompany.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Reportingcompany.Anular : valores por defecto
		
		return reportingcompany;
	}

	public BeanReportingcompany prepararEliminar(SeguridadUsuarioActual usuarioActual,BeanReportingcompany reportingcompany) {
		if (reportingcompany == null)
			return reportingcompany;
		if (reportingcompany.getAuxFlgPreparadoBoolean())
			return reportingcompany;
		reportingcompany.setAuxFlgPreparadoBoolean(Boolean.TRUE);
		
		// TODO Reportingcompany.Eliminar : valores por defecto
		
		return reportingcompany;
	}

	private List<DominioMensajeUsuario> coreBasico(SeguridadUsuarioActual usuarioActual, BeanReportingcompany reportingcompany) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		if (usuarioActual == null)
			lst.add(this.getMsjUsuarioError(SeguridadUsuarioActual.CONSTRAINTS_NOTNULL));
		if (reportingcompany == null)
			lst.add(this.getMsjUsuarioError("reportingcompany.constraints.notnull"));
		if (!lst.isEmpty())
			return lst;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		if (reportingcompany.getPk() != null) {
			Set<ConstraintViolation<BeanReportingcompanyPk>> reglasPk = validator.validate(reportingcompany.getPk());
			for (ConstraintViolation constraint : reglasPk) {
				lst.add(this.getMsjUsuarioError(constraint));
			}
		}
		Set<ConstraintViolation<BeanReportingcompany>> reglas = validator.validate(reportingcompany);
		for (ConstraintViolation constraint : reglas) {
			lst.add(this.getMsjUsuarioError(constraint));
		}
		
		// TODO Reportingcompany : validaciones comunes Insert/Actualizar
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreInsertar(SeguridadUsuarioActual usuarioActual,BeanReportingcompany reportingcompany) {
		if (reportingcompany.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		reportingcompany.setAuxFlgValidadoBoolean(Boolean.TRUE);

		reportingcompany = prepararInsertar(usuarioActual, reportingcompany);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reportingcompany);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Reportingcompany.Insertar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreActualizar(SeguridadUsuarioActual usuarioActual, BeanReportingcompany reportingcompany) {
		if (reportingcompany.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		reportingcompany.setAuxFlgValidadoBoolean(Boolean.TRUE);

		reportingcompany = prepararActualizar(usuarioActual, reportingcompany);
		List<DominioMensajeUsuario> lst = coreBasico(usuarioActual, reportingcompany);
		if (!lst.isEmpty())
			return lst;
		
		// TODO Reportingcompany.Actualizar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanReportingcompany reportingcompany) {
		if (reportingcompany.getAuxFlgValidadoBoolean())
			return new ArrayList<DominioMensajeUsuario>();
		reportingcompany.setAuxFlgValidadoBoolean(Boolean.TRUE);

		reportingcompany = prepararEliminar(usuarioActual, reportingcompany);
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		
		// TODO Reportingcompany.Eliminar : validaciones
		
		return lst;
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,BeanReportingcompanyPk pk) {
		BeanReportingcompany bean = reportingcompanyDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,bean);
	}

	public List<DominioMensajeUsuario> coreEliminar(SeguridadUsuarioActual usuarioActual,String pcompaniacodigo,String pcompanyowner) {
		return coreEliminar(usuarioActual,new BeanReportingcompanyPk( pcompaniacodigo, pcompanyowner));
	}


	public DtoComunReportingcompany core(SeguridadUsuarioActual usuarioActual,String accion,DtoComunReportingcompany dto) {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		BeanReportingcompany reportingcompany = dto.obtenerBean();
		if (accion.equals(ConstantePantallaAccion.INSERTAR))
			lst = coreInsertar(usuarioActual, reportingcompany);
		if (accion.equals(ConstantePantallaAccion.ACTUALIZAR))
			lst = coreActualizar(usuarioActual, reportingcompany);
		if (accion.equals(ConstantePantallaAccion.ELIMINAR))
			lst = coreEliminar(usuarioActual, reportingcompany);
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
