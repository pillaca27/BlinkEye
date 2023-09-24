package net.royal.spring.sy.servicio.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UMap;
import net.royal.spring.framework.modelo.ErrorTransaccion;
import net.royal.spring.framework.modelo.ParametroTransaccion;
import net.royal.spring.framework.modelo.ReporteTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sy.dao.impl.SyReporteDaoImpl;
import net.royal.spring.sy.dao.impl.SyReportearchivoDaoImpl;
import net.royal.spring.sy.dominio.BeanSyReporte;
import net.royal.spring.sy.dominio.BeanSyReportePk;
import net.royal.spring.sy.dominio.BeanSyReportearchivo;
import net.royal.spring.sy.dominio.BeanSyReportearchivoPk;
import net.royal.spring.sy.servicio.validar.SyReporteServicioValidar;

@Service(value = "BeanServicioSyReporteMotor")
public class SyReporteMotorServicioImpl extends GenericoServicioImpl {

	public static String SPRING_NOMBRE = "BeanServicioSyReporteMotor";
	private static Logger logger = LogManager.getLogger(SyReporteServicioImpl.class);

	@Autowired
	private SyReporteDaoImpl syReporteDao;

	@Autowired
	private SyReporteServicioValidar validar;

	@Autowired
	private SyReportearchivoDaoImpl syReportearchivoDaoImpl;

	public ReporteTransaccion obtenerConfiguracionReporte(ReporteTransaccion reporte) throws Exception {
		String asunto = null;
		String idReporteC = "";
		if (reporte != null) {
			idReporteC = "app=" + UString.obtenerSinNulo(reporte.getAplicacionCodigo()) + "|codigo="
					+ UString.obtenerSinNulo(reporte.getReporteCodigo()) + "|compania="
					+ UString.obtenerSinNulo(reporte.getCompaniaSocio()) + "|periodo="
					+ UString.obtenerSinNulo(reporte.getPeriodo()) + "|version="
					+ UString.obtenerSinNulo(reporte.getVersion());
		}

		byte[] archivo = null;
		String rutaReportes = null;

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, "SY"));
		parametros.add(new DominioParametroPersistencia("p_parametro", String.class, "RUTAREPWEB"));

		List lst = new ArrayList<>();
		lst = syReporteDao.listarPorQuery(ParametroTransaccion.class, "syreporte.syreportesoRuta", parametros);

		ParametroTransaccion param = (ParametroTransaccion) lst.get(0);

		rutaReportes = param.getExplicacion();

		String nombreReporte = null;

		BeanSyReporte syReporte = syReporteDao
				.obtenerPorId(new BeanSyReportePk(reporte.getAplicacionCodigo(), reporte.getReporteCodigo()));
		if (syReporte == null) {
			reporte.setTransaccionEstado(DominioTransaccion.ERROR);
			reporte.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, idReporteC + ":Reporte no encontrado"));
			logger.error(idReporteC + ":Reporte no encontrado");
			this.ErrorRegistrar(new ErrorTransaccion("SY_REPORTE", idReporteC + ":Reporte no encontrado",
					"SyReporteMotorServicioImpl"));
			return reporte;
		}
		if (UString.estaVacio(syReporte.getTiporeporte())) {
			reporte.setTransaccionEstado(DominioTransaccion.ERROR);
			reporte.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
					idReporteC + ":Reporte no tiene especificado un tipo"));
			logger.error(idReporteC + ":Reporte no tiene especificado un tipo");
			this.ErrorRegistrar(new ErrorTransaccion("SY_REPORTE",
					idReporteC + ":Reporte no tiene especificado un tipo", "SyReporteMotorServicioImpl"));
			return reporte;
		}
		if (UString.estaVacio(rutaReportes)) {
			reporte.setTransaccionEstado(DominioTransaccion.ERROR);
			reporte.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
					idReporteC + ":No existe una ruta en donde se encuentren los reportes fisicos"));
			logger.error(idReporteC + ":No existe una ruta en donde se encuentren los reportes fisicos");
			this.ErrorRegistrar(new ErrorTransaccion("SY_REPORTE",
					idReporteC + ":No existe una ruta en donde se encuentren los reportes fisicos",
					"SyReporteMotorServicioImpl"));
			return reporte;
		}

		nombreReporte = reporte.getNombreCompletoReporte();
		asunto = UString.obtenerSinNulo(syReporte.getAsunto());
		asunto = UString.estaVacio(asunto) ? UString.obtenerSinNulo(syReporte.getDescripcionlocal()) : asunto;
		if (syReporte.getTiporeporte().equals("HTML")) {
			nombreReporte = nombreReporte + ".html";
			asunto = UMap.reemplazarValores(reporte.getParametros(), asunto);
		}

		if (syReporte.getTiporeporte().equals("JASPE")) {
			nombreReporte = nombreReporte + ".jasper";
		}

		File tmpFile = new File(rutaReportes);
		if (!tmpFile.exists())
			tmpFile.mkdirs();

		BeanSyReportearchivo syReportearchivo = null;

		if (reporte.getOrigen().equals(ReporteTransaccion.ORIGEN_WORKFLOW)
				&& reporte.getWorkFlowFlgCorreoNiveles().equals("S")) {
			// paplicacioncodigo,preportecodigo,pcompaniasocio,pperiodo, pversion
			/*
			 * syReportearchivo = syReportearchivoDaoImpl .obtenerPorId(new
			 * BeanSyReportearchivoPk(reporte.getAplicacionCodigo(),
			 * reporte.getReporteCodigo(), reporte.getCompaniaSocio(), reporte.getPeriodo(),
			 * reporte.getWorkFlowEstado()));
			 */
			// Version(accionWf.substring(0, 6)

			// como en version ya se manda la ACCION DE WORKFLOW, se tomara el campo de
			// periodo
			// para el envio de niveles
			logger.debug("=================> REPORTE DE WORKFLOW NIVELES");
			logger.debug(reporte.getWorkFlowNivel());
			String periodoWf = null;
			if (reporte.getWorkFlowNivel() == null) {
				periodoWf = "999999";
			} else {
				periodoWf = UString.IntegerToString(reporte.getWorkFlowNivel()).toString();
				logger.debug(periodoWf);
				periodoWf = armarPeriodo(reporte.getWorkFlowFlujo(), reporte.getWorkFlowNivel());
			}
			logger.debug("periodoWf:" + periodoWf);

			// solo deberia existir APROBA,RECHAZ,DEVOLV
			logger.debug("logger.debug(reporte.getWorkFlowAccion());" + reporte.getWorkFlowAccion());
			String versionWf = reporte.getWorkFlowAccion();
			if (UString.esNuloVacio(versionWf))
				versionWf = reporte.getVersion();

			if (!UString.esNuloVacio(versionWf)) {
				if (versionWf.length() > 6) {
					versionWf = versionWf.substring(0, 6);
				}
			}

			logger.debug("versionWf:" + versionWf);
			syReportearchivo = syReportearchivoDaoImpl
					.obtenerPorId(new BeanSyReportearchivoPk(reporte.getAplicacionCodigo(), reporte.getReporteCodigo(),
							reporte.getCompaniaSocio(), periodoWf, versionWf));
			if (syReportearchivo != null) {
				asunto = syReportearchivo.getAsunto();
				asunto = UMap.reemplazarValores(reporte.getParametros(), asunto);
			}
		} else {
			// si viene de workflow pero no tiene correo por niveles
			// se toma como estandar la accion viene en la Version

			if (syReportearchivo == null) {
				syReportearchivo = syReportearchivoDaoImpl.obtenerPorId(
						new BeanSyReportearchivoPk(reporte.getAplicacionCodigo(), reporte.getReporteCodigo(),
								reporte.getCompaniaSocio(), reporte.getPeriodo(), reporte.getVersion()));
			}

			if (syReportearchivo == null) {
				syReportearchivo = syReportearchivoDaoImpl.obtenerPorId(
						new BeanSyReportearchivoPk(reporte.getAplicacionCodigo(), reporte.getReporteCodigo(),
								reporte.getCompaniaSocio(), "999999", reporte.getVersion()));
			}

			if (syReportearchivo == null) {
				syReportearchivo = syReportearchivoDaoImpl
						.obtenerPorId(new BeanSyReportearchivoPk(reporte.getAplicacionCodigo(),
								reporte.getReporteCodigo(), reporte.getCompaniaSocio(), "999999", "999999"));
			}

			if (syReportearchivo == null) {
				syReportearchivo = syReportearchivoDaoImpl.obtenerPorId(new BeanSyReportearchivoPk(
						reporte.getAplicacionCodigo(), reporte.getReporteCodigo(), "999999", "999999", "999999"));
			}
		}

		if (syReportearchivo == null) {
			reporte.setTransaccionEstado(DominioTransaccion.ERROR);
			reporte.getTransaccionListaMensajes().add(
					new DominioMensajeUsuario(tipo_mensaje.ERROR, idReporteC + ":Reporte no tiene ningun Archivo"));
			logger.error(idReporteC + ":Reporte no tiene ningun Archivo");
			this.ErrorRegistrar(new ErrorTransaccion("SY_REPORTE", idReporteC + ":Reporte no tiene ningun Archivo",
					"SyReporteMotorServicioImpl"));
			return reporte;
		}

		logger.debug(rutaReportes);
		logger.debug(nombreReporte);
		archivo = syReportearchivo.getReporte();
		UFile.guardarContenidoFile(rutaReportes, nombreReporte, archivo);

		reporte.setReporteRutaCompleta(rutaReportes + File.separator + nombreReporte);
		reporte.setReporteBinario(archivo);
		reporte.setResultadoAsunto(asunto);
		reporte.setTransaccionEstado(DominioTransaccion.OK);
		return reporte;
	}

	public byte[] ejecutarReporte(ReporteTransaccion config, Map parametros) throws Exception {
		if (parametros == null)
			parametros = new HashMap();

		if (config.getReporteTipo() == null)
			config.setReporteTipo("HTML");

		if (config.getReporteTipo().equals("HTML")) {
			return UMap.obtenerBinarioParseado(config.getReporteBinario(), parametros);
		}
		if (config.getReporteTipo().equals("JASPE")) {
			return syReporteDao.ejecutarReporteComoPDF(config.getReporteRutaCompleta(), parametros);
		}
		return null;
	}

	public String armarPeriodo(Integer flujo, Integer nivel) {
		String p = "F";
		if (flujo > 9) {
			p = p + flujo;
		} else {
			p = p + "0" + flujo;
		}

		p = p + '-';

		if (nivel > 9) {
			p = p + nivel;
		} else {
			p = p + "0" + nivel;
		}
		return p;
	}

}
