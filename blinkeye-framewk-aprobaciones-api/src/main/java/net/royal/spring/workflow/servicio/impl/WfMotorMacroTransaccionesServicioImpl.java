package net.royal.spring.workflow.servicio.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.modelo.WorkFlowResultado;
import net.royal.spring.framework.modelo.WorkFlowTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.workflow.dao.impl.WfMacroProcesoDetalleDaoImpl;
import net.royal.spring.workflow.dominio.WfMacroProcesoDetalle;

@Service
public class WfMotorMacroTransaccionesServicioImpl {
	private static Logger logger = LogManager.getLogger(WfMotorMacroTransaccionesServicioImpl.class);
	
	@Autowired
	private WfMacroProcesoDetalleDaoImpl wfMacroProcesoDetalleDao;
	
	@Transactional
	public void incluirEnMacroNuevaTransaccion(SeguridadUsuarioActual usuarioActual,
			WorkFlowTransaccion transaccionSolicitado,
			WorkFlowResultado transaccionResultado) {
		logger.debug("incluirEnMacroNuevaTransaccion");
		
		logger.debug("transaccionSolicitado");
		logger.debug(transaccionSolicitado.getTransaccionOrigenId());
		logger.debug(transaccionSolicitado.getProcesoId());
		logger.debug(transaccionSolicitado.getTransaccionId());
		
		logger.debug("transaccionResultado");
		logger.debug(transaccionResultado.getTransaccionid());
		logger.debug(transaccionResultado.getCodigoproceso());
		
		if (UInteger.esCeroOrNulo(transaccionSolicitado.getTransaccionOrigenId())) {
			// buscar si es una transaccion inicial
			WfMacroProcesoDetalle bean = wfMacroProcesoDetalleDao.obtenerInicial(transaccionSolicitado.getProcesoId());
			logger.debug(bean);
			if (bean!=null) {
				logger.debug("registrando un macro proceso");
			}
		}
		
		if (!UInteger.esCeroOrNulo(transaccionSolicitado.getTransaccionOrigenId())) {
			
		}

		// establecer si es transaccion que origina un nuevo macro proceso
		// buscar en MACRO PROCESO DETALLE : PROCESO_ORIGEN_ID / ORDEN 1
		//		si (registrar en wf_transaccion_macro, wf_transaccion_macro_detalle)
		
	}
}
