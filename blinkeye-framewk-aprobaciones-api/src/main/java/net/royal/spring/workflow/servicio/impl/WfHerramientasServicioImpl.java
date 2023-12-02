package net.royal.spring.workflow.servicio.impl;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.modelo.ReporteTransaccion;
import net.royal.spring.framework.modelo.WorkFlowEnvioCorreo;
import net.royal.spring.framework.modelo.WorkFlowEnvioCorreoDetalle;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.workflow.dao.impl.WfProcesoVersionesDaoImpl;
import net.royal.spring.workflow.dao.impl.WfTransaccionesDaoImpl;
import net.royal.spring.workflow.dominio.WfProcesoVersiones;
import net.royal.spring.workflow.dominio.WfProcesoVersionesPk;
import net.royal.spring.workflow.dominio.WfTransacciones;

@Service
public class WfHerramientasServicioImpl extends GenericoServicioImpl {

	private static Logger logger = LogManager.getLogger(WfHerramientasServicioImpl.class);
	
	@Autowired
	private WfTransaccionesDaoImpl wfTransaccionesDao;
	
	@Autowired
	private WfTransaccionesServicioImpl wfTransaccionesServicio;
	
	@Autowired
	private WfProcesoVersionesDaoImpl wfProcesoVersionDaoImpl;
	
	public WorkFlowEnvioCorreo enviarCorreo(WorkFlowEnvioCorreo p,String accionId) {
		try {
			int index=0;
			List<EmailTransaccion> lstCorreos=new ArrayList<EmailTransaccion>();
			logger.debug("p.getAplicacionCodigo() 0:"+p.getAplicacionCodigo());
			logger.debug("p.getReporteCodigo()    0:"+p.getReporteCodigo());
			
			ReporteTransaccion reporteParametro = new ReporteTransaccion();		
			reporteParametro.setAplicacionCodigo(p.getAplicacionCodigo());
			reporteParametro.setReporteCodigo(p.getReporteCodigo());			
			reporteParametro.setCompaniaSocio(p.getCompaniaSocio());
			reporteParametro.setPeriodo(p.getPeriodo());
			reporteParametro.setVersion(p.getVersion());
			
			logger.debug("p.getListaCorreoDetalle().size():"+p.getListaCorreoDetalle().size());
			logger.debug("p.getListaCorreoAdjunto().size():"+p.getListaCorreoAdjunto().size());
			logger.debug("p.getListaCorreoDestino().size():"+p.getListaCorreoDestino().size());
			
			for (WorkFlowEnvioCorreoDetalle i : p.getListaCorreoDetalle()) {
				// obtener metadatos por id transaccion
				//Map<String, String> metadatos = new HashMap<String, String>();
				WfTransacciones bTransaccion = wfTransaccionesDao.obtenerPorId(i.getTransaccionId());
				WfProcesoVersiones wfProcesoVersion = wfProcesoVersionDaoImpl.obtenerPorId(new WfProcesoVersionesPk(bTransaccion.getProcesoid(), bTransaccion.getVersionid()));
				
				String api = wfTransaccionesDao.obtenerApiCabecera(bTransaccion.getProcesoid(), bTransaccion.getVersionid()).getApi();
				HashMap<String, Object> metadatos = wfTransaccionesServicio.obtenerMetadatosAPI(api, wfTransaccionesServicio.armarMetadatos(bTransaccion, null));
				
				// traer plantilla y asignar valores					
				reporteParametro.setParametros(metadatos);				
				
				// 
				//reporteParametro.setWorkFlowNivel(bTransaccion.getNivelid());
				//reporteParametro.setWorkFlowAccion(accionId);
				//reporteParametro.setWorkFlowFlgCorreoNiveles(wfProcesoVersion.getFlgCorreoNiveles());
				
				ReporteTransaccion reporte = reporteEjecutarWorkFlow(reporteParametro);
				if (!reporte.getTransaccionEstado().equals(DominioTransaccion.OK)) {
					logger.debug("No encontrado"+reporte.getNombreCompletoReporte());
				}
				
				// armar correos
				EmailTransaccion email = new EmailTransaccion();
				email.setTraceReferencia(i.getTransaccionId().toString());
				email.setCuerpoCorreoBase64(Base64.getEncoder().encodeToString(reporte.getResultadoCuerpoBinario()));
				email.setAsunto(reporte.getResultadoAsunto());	
				email.setListaCorreoDestino(i.getListaCorreoDestino());
				email.setListaCorreoAdjunto(i.getListaCorreoAdjunto());
				
				//adjuntar los generales
				email.getListaCorreoAdjunto().addAll(p.getListaCorreoAdjunto());
				email.getListaCorreoDestino().addAll(p.getListaCorreoDestino());
				
				// log de correos
				email.setProcesoId(bTransaccion.getProcesoid());
				email.setTransaccionId(i.getTransaccionId());
				email.setReferenciaId(i.getTransaccionId().toString());
				email.setAccionId(accionId);
				
				lstCorreos.add(email);				
			}
			
			// envio de correos
			logger.debug("correos a procesos:"+lstCorreos.size());
			index=0;
			for (EmailTransaccion email : lstCorreos) {
				logger.debug("correos index:"+index);
				if (email.getListaCorreoDestino().size()>0) {
					logger.debug(email.getListaCorreoDestino().get(0).getCorreoDestino());
					if (email.getListaCorreoAdjunto().size()>0)
						logger.debug(email.getListaCorreoAdjunto().get(0).getNombreArchivo());
				}else {
					logger.debug("sin destinatarios");
				}
				
				correoEnviar(email);
				index++;
			}
						
			p.setTransaccionEstado(DominioTransaccion.OK);
		} catch (Exception e) {
			p.setTransaccionEstado(DominioTransaccion.ERROR);
			p.setTransaccionMensajesCadena(e.getMessage());
			e.printStackTrace();
		}
		return p;
	}
}
