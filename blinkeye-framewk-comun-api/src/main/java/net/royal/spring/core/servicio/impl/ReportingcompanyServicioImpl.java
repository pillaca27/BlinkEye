package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.core.dao.impl.ReportingcompanyDaoImpl;

import net.royal.spring.core.dominio.BeanReportingcompany;
import net.royal.spring.core.dominio.BeanReportingcompanyPk;
import net.royal.spring.core.dominio.dto.DtoComunReportingcompany;
import net.royal.spring.core.servicio.validar.ReportingcompanyServicioValidar;

@Service (value = "BeanServicioReportingcompany")
public class ReportingcompanyServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioReportingcompany";
	private static Logger logger = LogManager.getLogger(ReportingcompanyServicioImpl.class);

	@Autowired
	private ReportingcompanyDaoImpl reportingcompanyDao;

	@Autowired
	private ReportingcompanyServicioValidar validar;

	@Transactional
	public DtoComunReportingcompany coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunReportingcompany dto) throws UException {
		BeanReportingcompany reportingcompany = dto.obtenerBean();
		reportingcompany = coreInsertar(usuarioActual, reportingcompany);
		dto.setTransaccionEstado(reportingcompany.getTransaccionEstado());
		dto.setTransaccionListaMensajes(reportingcompany.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanReportingcompany coreInsertar(SeguridadUsuarioActual usuarioActual,BeanReportingcompany reportingcompany) throws UException {
		// valores por defecto - preparando objeto
		reportingcompany = validar.prepararInsertar(usuarioActual, reportingcompany);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, reportingcompany);
		if (!lst.isEmpty()) {
			reportingcompany.setTransaccionEstado(DominioTransaccion.VALIDACION);
			reportingcompany.setTransaccionListaMensajes(lst);
			return reportingcompany;
		}
		
		// transaccion
		reportingcompany = reportingcompanyDao.coreInsertar(reportingcompany);
		reportingcompany.setTransaccionEstado(DominioTransaccion.OK);
		reportingcompany.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return reportingcompany;
	}

	@Transactional
	public DtoComunReportingcompany coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunReportingcompany dto) throws UException {
		BeanReportingcompany reportingcompany = dto.obtenerBean();
		reportingcompany = coreActualizar(usuarioActual, reportingcompany);
		dto.setTransaccionEstado(reportingcompany.getTransaccionEstado());
		dto.setTransaccionListaMensajes(reportingcompany.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanReportingcompany coreActualizar(SeguridadUsuarioActual usuarioActual, BeanReportingcompany reportingcompany) throws UException {
		// valores por defecto - preparando objeto
		reportingcompany = validar.prepararActualizar(usuarioActual, reportingcompany);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, reportingcompany);
		if (!lst.isEmpty()) {
			reportingcompany.setTransaccionEstado(DominioTransaccion.VALIDACION);
			reportingcompany.setTransaccionListaMensajes(lst);
			return reportingcompany;
		}
		
		// transaccion
	    reportingcompany = reportingcompanyDao.coreActualizar(reportingcompany);
		reportingcompany.setTransaccionEstado(DominioTransaccion.OK);
		reportingcompany.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return reportingcompany;
	}

	@Transactional
	public DtoComunReportingcompany coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunReportingcompany dto) throws UException {
		BeanReportingcompany reportingcompany = dto.obtenerBean();
		reportingcompany = coreEliminar(usuarioActual, reportingcompany);
		dto.setTransaccionEstado(reportingcompany.getTransaccionEstado());
		dto.setTransaccionListaMensajes(reportingcompany.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanReportingcompany coreEliminar(SeguridadUsuarioActual usuarioActual, BeanReportingcompany reportingcompany) throws UException {
		// valores por defecto - preparando objeto
		reportingcompany = validar.prepararEliminar(usuarioActual, reportingcompany);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, reportingcompany);
		if (!lst.isEmpty()) {
			reportingcompany.setTransaccionEstado(DominioTransaccion.VALIDACION);
			reportingcompany.setTransaccionListaMensajes(lst);
			return reportingcompany;
		}
		
		// transaccion
		reportingcompanyDao.eliminar(reportingcompany);
		reportingcompany=new BeanReportingcompany();
		reportingcompany.setTransaccionEstado(DominioTransaccion.OK);
		return reportingcompany;
	}

	public BeanReportingcompany coreEliminar(SeguridadUsuarioActual usuarioActual, BeanReportingcompanyPk pk) throws UException {
		BeanReportingcompany reportingcompany = reportingcompanyDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,reportingcompany);
	}

	public BeanReportingcompany coreEliminar(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo,String pcompanyowner) throws UException {
		return coreEliminar(usuarioActual,new BeanReportingcompanyPk( pcompaniacodigo, pcompanyowner));
	}

}
