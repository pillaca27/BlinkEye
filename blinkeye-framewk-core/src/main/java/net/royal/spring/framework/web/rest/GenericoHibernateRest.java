package net.royal.spring.framework.web.rest;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.hibernate.transform.Transformers;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteExportar;
import net.royal.spring.framework.web.dao.impl.GenericoBase;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRXmlExporter;
import net.sf.jasperreports.engine.export.JsonMetadataExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleJsonExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import net.sf.jasperreports.export.SimpleXmlExporterOutput;

public class GenericoHibernateRest extends GenericoRest {
	private static Logger logger = LogManager.getLogger(GenericoHibernateRest.class);
	private String nombreAlias;
	private Session sessionInterna;
	private SessionFactory factory;

	public GenericoHibernateRest(String nombreAlias) {
		this.nombreAlias = nombreAlias;
	}

	public void setSessionFactory(SessionFactory factory) {
		this.factory = factory;
	}

	public Session getSesionActual() {
		if (sessionInterna == null) {
			return factory.getCurrentSession();
		} else {
			return sessionInterna;
		}
	}

	/**********/
	public List<Class> listarPorQuery(Class clazz, String nombreQuery) {

		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return new ArrayList();
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}

	public List<Class> listarPorQuery(Class clazz, String nombreQuery, List<DominioParametroPersistencia> parameters) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return new ArrayList();
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = GenericoBase.getNamedQueryByPatametersSet(query, parameters);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}

	/********/

	public Integer contar(String nombreQuery) {
		/* DIALECTO */
		return GenericoBase.contar(nombreQuery, null, this.getSesionActual(), nombreAlias);
	}

	public Integer contar(String nombreQuery, List<DominioParametroPersistencia> parametros) {
		/* DIALECTO */
		return GenericoBase.contar(nombreQuery, parametros, this.getSesionActual(), nombreAlias);
	}

	/********/
	public List<Class> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery, Class clazz) {
		/* DIALECTO */
		return GenericoBase.listarConPaginacion(parametroPaginacion, parametros, nombreQuery, clazz, null,
				this.getSesionActual(), nombreAlias);
	}

	public List<Class> listarConPaginacion(DominioPaginacion parametroPaginacion,
			List<DominioParametroPersistencia> parametros, String nombreQuery, Class clazz,
			String condicionesConsulta) {
		/* DIALECTO */
		return GenericoBase.listarConPaginacion(parametroPaginacion, parametros, nombreQuery, clazz,
				condicionesConsulta, this.getSesionActual(), nombreAlias);
	}
	/********/

	public String obtenerSentenciaSqlPorQuery(String nombreQuery) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return null;
		StringBuilder queryS = new StringBuilder();
		queryS.append(this.getSesionActual().getNamedQuery(nombreQuery).getQueryString());
		return queryS.toString();
	}
	
	@Transactional(readOnly = true)
	public List<Class> listarPorSentenciaSQL(StringBuilder sentenciaSQL, List<DominioParametroPersistencia> parametros,
			Class clazz) {
		Query query = this.getSesionActual().createSQLQuery(sentenciaSQL.toString());
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		query = query.setResultTransformer(Transformers.aliasToBean(clazz));
		return query.list();
	}
	
	public void ejecutarPorQuery(String nombreQuery, List<DominioParametroPersistencia> parametros) {
		/* DIALECTO */
		nombreQuery = GenericoBase.nombreQueryDialecto(this.getSesionActual(), nombreQuery);

		if (!GenericoBase.validarAlias(nombreAlias, nombreQuery))
			return;
		Query query = this.getSesionActual().getNamedQuery(nombreQuery);
		query = GenericoBase.getNamedQueryByPatametersSet(query, parametros);
		query.executeUpdate();
	}
	
	@Transactional(readOnly = true)
	public byte[] ejecutarReporteComoPDF(String rutaArchivoReporteFuente, Map parametros) throws Exception {
		Connection conexion;
		conexion = ((SessionImpl) getSesionActual()).connection();
		return ejecutarReporteComoPDF(rutaArchivoReporteFuente,parametros,conexion);
	}
	
	@Transactional(readOnly = true)
	public byte[] ejecutarReporte(String formatoSalida, String rutaArchivoReporteFuente, Map parametros)
			throws Exception {
		byte[] salida = null;
		if (UString.esNuloVacio(formatoSalida))
			formatoSalida = ConstanteExportar.FORMATO_SALIDA_PDF;

		Connection conexion = ((SessionImpl) getSesionActual()).connection();

		switch (formatoSalida) {
		case ConstanteExportar.FORMATO_SALIDA_PDF: {
			salida = ejecutarReporteComoPDF(rutaArchivoReporteFuente, parametros, conexion);
			break;
		}
		case ConstanteExportar.FORMATO_SALIDA_XLS: {
			salida = ejecutarReporteComoXLS(rutaArchivoReporteFuente, parametros, conexion);
			break;
		}
		case ConstanteExportar.FORMATO_SALIDA_CSV: {
			salida = ejecutarReporteComoCSV(rutaArchivoReporteFuente, parametros, conexion);
			break;
		}
		case ConstanteExportar.FORMATO_SALIDA_DOC: {
			salida = ejecutarReporteComoDOC(rutaArchivoReporteFuente, parametros, conexion);
			break;
		}
		case ConstanteExportar.FORMATO_SALIDA_HTML: {
			salida = ejecutarReporteComoHTML(rutaArchivoReporteFuente, parametros, conexion);
			break;
		}
		case ConstanteExportar.FORMATO_SALIDA_JSON: {
			salida = ejecutarReporteComoJSON(rutaArchivoReporteFuente, parametros, conexion);
			break;
		}
		case ConstanteExportar.FORMATO_SALIDA_XML: {
			salida = ejecutarReporteComoXML(rutaArchivoReporteFuente, parametros, conexion);
			break;
		}
		default: {
			logger.error("Formato no implementado : " + formatoSalida);

		}
		}

		return salida;
	}
	
	private byte[] ejecutarReporteComoXML(String rutaArchivoReporteFuente, Map parametros, Connection conexion)
			throws Exception {
		byte[] bytReporte = null;
		JasperReport jasperReport = JasperCompileManager
				.compileReport(rutaArchivoReporteFuente.replace(".jasper", ".jrxml"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);
		JRXmlExporter exporter = new JRXmlExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		try (ByteArrayOutputStream report = new ByteArrayOutputStream()) {
			exporter.setExporterOutput(new SimpleXmlExporterOutput(report, "ISO-8859-1"));
			exporter.exportReport();
			bytReporte = report.toByteArray();
		}
		return bytReporte;
	}
	
	private byte[] ejecutarReporteComoXLS(String rutaArchivoReporteFuente, Map parametros, Connection conexion)
			throws Exception {
		byte[] bytReporte = null;
		JasperReport jasperReport = JasperCompileManager
				.compileReport(rutaArchivoReporteFuente.replace(".jasper", ".jrxml"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);
		JRXlsxExporter exporter = new JRXlsxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		try (ByteArrayOutputStream report = new ByteArrayOutputStream()) {
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(report));
			exporter.exportReport();
			bytReporte = report.toByteArray();
		}
		return bytReporte;
	}
	
	private byte[] ejecutarReporteComoCSV(String rutaArchivoReporteFuente, Map parametros, Connection conexion)
			throws Exception {
		byte[] bytReporte = null;
		JasperReport jasperReport = JasperCompileManager
				.compileReport(rutaArchivoReporteFuente.replace(".jasper", ".jrxml"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);
		JRCsvExporter exporter = new JRCsvExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		try (ByteArrayOutputStream report = new ByteArrayOutputStream()) {
			exporter.setExporterOutput(new SimpleWriterExporterOutput(report, "ISO-8859-1"));
			exporter.exportReport();
			bytReporte = report.toByteArray();
		}
		return bytReporte;
	}
	
	private byte[] ejecutarReporteComoDOC(String rutaArchivoReporteFuente, Map parametros, Connection conexion)
			throws Exception {
		byte[] bytReporte = null;
		JasperReport jasperReport = JasperCompileManager
				.compileReport(rutaArchivoReporteFuente.replace(".jasper", ".jrxml"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);
		JRDocxExporter exporter = new JRDocxExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		try (ByteArrayOutputStream report = new ByteArrayOutputStream()) {
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(report));
			exporter.exportReport();
			bytReporte = report.toByteArray();
		}
		return bytReporte;
	}
	
	private byte[] ejecutarReporteComoHTML(String rutaArchivoReporteFuente, Map parametros, Connection conexion)
			throws Exception {
		byte[] bytReporte = null;
		JasperReport jasperReport = JasperCompileManager
				.compileReport(rutaArchivoReporteFuente.replace(".jasper", ".jrxml"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);

		JRExporter exporter = new HtmlExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		try (ByteArrayOutputStream report = new ByteArrayOutputStream()) {
			exporter.setExporterOutput(new SimpleHtmlExporterOutput(report, "ISO-8859-1"));
			exporter.exportReport();
			bytReporte = report.toByteArray();
		}
		return bytReporte;
	}
	
	private byte[] ejecutarReporteComoJSON(String rutaArchivoReporteFuente, Map parametros, Connection conexion)
			throws Exception {
		byte[] bytReporte = null;
		JasperReport jasperReport = JasperCompileManager
				.compileReport(rutaArchivoReporteFuente.replace(".jasper", ".jrxml"));
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);
		JsonMetadataExporter exporter = new JsonMetadataExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		try (ByteArrayOutputStream report = new ByteArrayOutputStream()) {
			exporter.setExporterOutput(new SimpleJsonExporterOutput(report, "ISO-8859-1"));
			exporter.exportReport();
			bytReporte = report.toByteArray();
		}
		return bytReporte;
	}
	
	private byte[] ejecutarReporteComoPDF(String rutaArchivoReporteFuente, Map parametros,Connection conexion) throws Exception {
		byte[] bytReporte = null;
		bytReporte = JasperRunManager.runReportToPdf(rutaArchivoReporteFuente, parametros, conexion);
		return bytReporte;
	}
}
