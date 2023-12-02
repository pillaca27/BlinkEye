package net.royal.spring.comun.servicio.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.comun.dao.impl.WfSyReportearchivoDaoImpl;
import net.royal.spring.comun.dominio.WfBeanSyReportearchivo;
import net.royal.spring.comun.dominio.WfBeanSyReportearchivoPk;
import net.royal.spring.comun.servicio.validar.WfSyReportearchivoServicioValidar;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service (value = "BeanServicioSyReportearchivo")
public class WfSyReportearchivoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyReportearchivo";
	private static Logger logger = LogManager.getLogger(WfSyReportearchivoServicioImpl.class);

	@Autowired
	private WfSyReportearchivoDaoImpl syReportearchivoDao;

	@Autowired
	private WfSyReportearchivoServicioValidar validar;
	
	
	
	public WfBeanSyReportearchivo archivosolicitudRegistrar(SeguridadUsuarioActual usuarioActual, WfBeanSyReportearchivo bean)
			throws UException {
		List<DominioMensajeUsuario> lstRetorno = new ArrayList<DominioMensajeUsuario>();

		if (!UString.estaVacio(bean.getAuxString())) {
			bean.setReporte(bean.getAuxString().getBytes(StandardCharsets.UTF_8));
		}
		
		//SyReportearchivoPk pk = new SyReportearchivoPk(bean.getPk().getAplicacioncodigo(),
			//	bean.getPk().getReportecodigo(), bean.getPk().getCompaniasocio(), bean.getPk().getPeriodo(),
				//bean.getPk().getVersion());		

		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, bean);
		
		if (!lst.isEmpty()) {
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.setTransaccionListaMensajes(lst);
			return bean;
		}
		
		syReportearchivoDao.registrar(bean);
		bean.setTransaccionEstado(DominioTransaccion.OK);

		return bean;
	}
	
	
	public WfBeanSyReportearchivo archivosolicitudObtenerPorId(WfBeanSyReportearchivoPk pk) {
		WfBeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(pk);
		if (bean.getReporte() != null) {
			if (bean.getReporte().length > 0) {
				bean.setAuxString(new String(bean.getReporte(), StandardCharsets.UTF_8));
				bean.setReporte(null);
			}
		}
		return bean;
	}
	
	
	public WfBeanSyReportearchivo archivosolicitudActualizar(SeguridadUsuarioActual usuarioActual, WfBeanSyReportearchivo bean) {
		if (!UString.estaVacio(bean.getAuxString())) {
			bean.setReporte(bean.getAuxString().getBytes(StandardCharsets.UTF_8));
		}
		
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, bean);
		
		if (!lst.isEmpty()) {
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.setTransaccionListaMensajes(lst);
			return bean;
		}							
		syReportearchivoDao.actualizar(bean);
		bean.setTransaccionEstado(DominioTransaccion.OK);
		return bean;
	}
	
	
	public WfBeanSyReportearchivo eliminarReporteArchivo(WfBeanSyReportearchivoPk pk) {
		syReportearchivoDao.eliminar(new WfBeanSyReportearchivo(pk));
		return new WfBeanSyReportearchivo();
	}

}
