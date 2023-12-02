package net.royal.spring.sistema.servicio.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
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
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.modelo.ReporteTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UByte;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.util.UValidador;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyReporteDaoImpl;
import net.royal.spring.sistema.dao.impl.SyReportearchivoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyReporte;
import net.royal.spring.sistema.dominio.BeanSyReportePk;
import net.royal.spring.sistema.dominio.BeanSyReportearchivo;
import net.royal.spring.sistema.dominio.BeanSyReportearchivoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyReporte;
import net.royal.spring.sistema.dominio.dto.DtoComunReporteConfiguracionResultado;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyReporte;
import net.royal.spring.sistema.servicio.validar.SyReporteServicioValidar;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service(value = "BeanServicioSyReporte")
public class SyReporteServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyReporte";
	private static Logger logger = LogManager.getLogger(SyReporteServicioImpl.class);

	@Autowired
	private SyReporteDaoImpl syReporteDao;

	@Autowired
	private SyReportearchivoDaoImpl syReportearchivoDaoImpl;

	@Autowired
	private SyReporteServicioValidar validar;

	@Transactional
	public DtoComunSyReporte coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunSyReporte dto) throws UException {
		BeanSyReporte syReporte = dto.obtenerBean();
		syReporte = coreInsertar(usuarioActual, syReporte);
		dto.setTransaccionEstado(syReporte.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReporte.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReporte coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyReporte syReporte) throws UException {
		// valores por defecto - preparando objeto
		syReporte = validar.prepararInsertar(usuarioActual, syReporte);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syReporte);
		if (!lst.isEmpty()) {
			syReporte.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syReporte.setTransaccionListaMensajes(lst);
			return syReporte;
		}

		// transaccion
		syReporte = syReporteDao.coreInsertar(syReporte);
		syReporte.setTransaccionEstado(DominioTransaccion.OK);
		syReporte.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syReporte;
	}

	@Transactional
	public DtoComunSyReporte coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunSyReporte dto) throws UException {
		BeanSyReporte syReporte = dto.obtenerBean();
		syReporte = coreActualizar(usuarioActual, syReporte);
		dto.setTransaccionEstado(syReporte.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReporte.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReporte coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyReporte syReporte) throws UException {
		// valores por defecto - preparando objeto
		syReporte = validar.prepararActualizar(usuarioActual, syReporte);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syReporte);
		if (!lst.isEmpty()) {
			syReporte.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syReporte.setTransaccionListaMensajes(lst);
			return syReporte;
		}

		// transaccion
		syReporte = syReporteDao.coreActualizar(syReporte);
		syReporte.setTransaccionEstado(DominioTransaccion.OK);
		syReporte.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syReporte;
	}

	@Transactional
	public DtoComunSyReporte coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunSyReporte dto) throws UException {
		BeanSyReporte syReporte = dto.obtenerBean();
		syReporte = coreAnular(usuarioActual, syReporte);
		dto.setTransaccionEstado(syReporte.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReporte.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReporte coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyReporte syReporte) throws UException {
		// valores por defecto - preparando objeto
		syReporte = validar.prepararAnular(usuarioActual, syReporte);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, syReporte);
		if (!lst.isEmpty()) {
			syReporte.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syReporte.setTransaccionListaMensajes(lst);
			return syReporte;
		}

		// transaccion
		syReporte.setEstado("I");
		syReporte = syReporteDao.coreActualizar(syReporte);
		syReporte.setTransaccionEstado(DominioTransaccion.OK);
		syReporte.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syReporte;
	}

	public BeanSyReporte coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyReportePk pk) throws UException {
		BeanSyReporte bean = syReporteDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSyReporte coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String preportecodigo) throws UException {
		return coreAnular(usuarioActual,new BeanSyReportePk(paplicacioncodigo, preportecodigo));
	}

	@Transactional
	public DtoComunSyReporte coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunSyReporte dto) throws UException {
		BeanSyReporte syReporte = dto.obtenerBean();
		syReporte = coreEliminar(usuarioActual, syReporte);
		dto.setTransaccionEstado(syReporte.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReporte.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReporte coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyReporte syReporte) throws UException {
		// valores por defecto - preparando objeto
		syReporte = validar.prepararEliminar(usuarioActual, syReporte);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syReporte);
		if (!lst.isEmpty()) {
			syReporte.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syReporte.setTransaccionListaMensajes(lst);
			return syReporte;
		}

		// transaccion
		syReporteDao.eliminar(syReporte);
		syReporte = new BeanSyReporte();
		syReporte.setTransaccionEstado(DominioTransaccion.OK);
		return syReporte;
	}

	public BeanSyReporte coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyReportePk pk) throws UException {
		BeanSyReporte syReporte = syReporteDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,syReporte);
	}

	public BeanSyReporte coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,String preportecodigo) throws UException {
		return coreEliminar(usuarioActual,new BeanSyReportePk(paplicacioncodigo, preportecodigo));
	}

	public DominioPaginacion reportelistarConPaginacion(SeguridadUsuarioActual usuarioActual,
			FiltroComunSyReporte paginacion) {
		return syReporteDao.reportelistarConPaginacion(paginacion);
	}

	public BeanSyReporte reportesolicitudRegistrar(SeguridadUsuarioActual usuarioActual, BeanSyReporte bean) throws UException {
		List<DominioMensajeUsuario> lstRetorno = new ArrayList<DominioMensajeUsuario>();

		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, bean);

		if (!lst.isEmpty()) {
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.setTransaccionListaMensajes(lst);
			return bean;
		}

		syReporteDao.registrar(bean);
		bean.setTransaccionEstado(DominioTransaccion.OK);

		return bean;
	}

	public BeanSyReporte reportesolicitudObtenerPorId(BeanSyReportePk pk) {
		BeanSyReporte bean = syReporteDao.obtenerPorId(pk, false);
		return bean;
	}

	public BeanSyReporte reportesolicitudActualizar(SeguridadUsuarioActual usuarioActual, BeanSyReporte bean) {

		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, bean);
		if (!lst.isEmpty()) {
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.setTransaccionListaMensajes(lst);
			return bean;
		}

		syReporteDao.actualizar(bean);
		bean.setTransaccionEstado(DominioTransaccion.OK);

		return bean;
	}

	public DominioPaginacion listarPaginadoReportes(SeguridadUsuarioActual usuarioActual, FiltroComunSyReporte filtro) {
		filtro.setUsuario(usuarioActual.getUsuario());
		return syReporteDao.listarPaginadoReportes(filtro);
	}

	public byte[] ejecutarReporteDesdeArchivo(ReporteTransaccion reporte) throws Exception, JRException, IOException {
	    reporte = crearReporteHijos(reporte);
		DtoComunReporteConfiguracionResultado config = crearReporteEnServidorPrincipal(reporte);
		return ejecutarReporteDesdeArchivo(config, reporte.getParams());
	}

	private byte[] ejecutarReporteDesdeArchivo(DtoComunReporteConfiguracionResultado config,
			List<DominioParametroPersistencia> lstParametros) throws Exception {
//		if (config.getSyReporte().getTiporeporte().equals("HTML")) {
//			String cuerpoCorreo = UByte.convertirString(config.getArchivo());
//			for (DominioParametroPersistencia para : lstParametros) {
//				if (para.getValor() != null)
//					cuerpoCorreo = cuerpoCorreo.replace("[" + para.getCampo() + "]", (String) para.getValor());
//			}
//			return cuerpoCorreo.getBytes();
//		}
		if (config.getSyReporte().getTiporeporte().equals("JASPE")) {
			HashMap hm = new HashMap();
			for (DominioParametroPersistencia para : lstParametros) {
				hm.put(para.getCampo(), para.getValor());
			}
			
			if(!UValidador.esNulo(config.getSyReportearchivo())) {
				if(!UString.esNuloVacio(config.getSyReportearchivo().getPk().getVersion())) {
					hm.put("version", config.getSyReportearchivo().getPk().getVersion());
				}
				
				if(!UValidador.esNulo(config.getSyReportearchivo().getFecharegistro())) {
					hm.put("fecha", new SimpleDateFormat("dd/MM/yyyy").format(config.getSyReportearchivo().getFecharegistro()).toString());
				}
			}

			return syReporteDao.ejecutarReporteComoPDF(config.getRutaCompletaReporte(), hm);
		}
		return null;
	}

	private DtoComunReporteConfiguracionResultado crearReporteEnServidorPrincipal(ReporteTransaccion reporte)
			throws Exception {
		DtoComunReporteConfiguracionResultado config = new DtoComunReporteConfiguracionResultado();
		byte[] archivo = null;
		String rutaReportes = null;
		rutaReportes = this.parametroObtenerExplicacion("SY", "RUTAREPWEB", "999999");
		String nombreReporte = null;

		BeanSyReporte syReporte = syReporteDao
				.obtenerPorId(new BeanSyReportePk(reporte.getAplicacionCodigo(), reporte.getReporteCodigo()));
		if (syReporte == null)
			throw new UException("Reporte no encontrado");
		if (UString.estaVacio(syReporte.getTiporeporte()))
			throw new UException("Reporte no tiene especificado un tipo");
		if (UString.estaVacio(rutaReportes))
			throw new UException("No existe una ruta en donde se encuentren los reportes fisicos");

		nombreReporte = reporte.getNombreCompletoReporte();
		if (syReporte.getTiporeporte().equals("HTML"))
			nombreReporte = nombreReporte + ".html";
		if (syReporte.getTiporeporte().equals("JASPE"))
			nombreReporte = nombreReporte + ".jasper";

		File uploadedFile = new File(rutaReportes + File.separator + nombreReporte);
		if (uploadedFile.exists()) {
			UFile.eliminarArchivo(rutaReportes + File.separator + nombreReporte);
		}
		
		//OBTENER POR LA VERSION ACTUAL
		BeanSyReportearchivo syReportearchivo = syReportearchivoDaoImpl
		.obtenerporVersionactual(new BeanSyReportearchivoPk(reporte.getAplicacionCodigo(), reporte.getReporteCodigo(),
				reporte.getCompaniaSocio(), reporte.getPeriodo().substring(0, 6), reporte.getVersion()));
		
		if (syReportearchivo == null) {
			syReportearchivo = syReportearchivoDaoImpl
					.obtenerPorId(new BeanSyReportearchivoPk(reporte.getAplicacionCodigo(), reporte.getReporteCodigo(),
							reporte.getCompaniaSocio(), "999999", reporte.getVersion()));
		}

		if (syReportearchivo == null) {
			syReportearchivo = syReportearchivoDaoImpl
					.obtenerPorId(new BeanSyReportearchivoPk(reporte.getAplicacionCodigo(), reporte.getReporteCodigo(),
							reporte.getCompaniaSocio(), "999999", "999999"));
		}

		if (syReportearchivo == null) {
			syReportearchivo = syReportearchivoDaoImpl.obtenerPorId(new BeanSyReportearchivoPk(
					reporte.getAplicacionCodigo(), reporte.getReporteCodigo(), "999999", "999999", "999999"));
		}

		if (syReportearchivo == null)
			throw new UException("Reporte no tiene ningun Archivo");

		archivo = syReportearchivo.getReporte();

		if (syReporte.getTiporeporte().equals("JASPE")) {
			if (archivo != null) {
				UFile.guardarContenidoFile(rutaReportes, nombreReporte, archivo);
			}

			config.setRutaCompletaReporte(rutaReportes + File.separator + nombreReporte);
		}
		config.setSyReporte(syReporte);
		config.setFlgOk(Boolean.TRUE);
		config.setArchivo(archivo);
		config.setSyReportearchivo(syReportearchivo);
		return config;
	}

	private ReporteTransaccion crearReporteHijos(ReporteTransaccion reporte) throws Exception {
		List<BeanSyReporte> lstHijas = syReporteDao
				.litarHijasActivas(new BeanSyReportePk(reporte.getAplicacionCodigo(), reporte.getReporteCodigo()));

		String originalIdAplicacion = reporte.getAplicacionCodigo();
		String originalIdReporte = reporte.getReporteCodigo();
		if (reporte.getParams() == null)
			reporte.setParams(new ArrayList<>());

		for (BeanSyReporte syReporte : lstHijas) {
			reporte.setAplicacionCodigo(syReporte.getPk().getAplicacioncodigo());
			reporte.setReporteCodigo(syReporte.getPk().getReportecodigo());
			DtoComunReporteConfiguracionResultado tmp = crearReporteEnServidorPrincipal(reporte);

			String campo = syReporte.getPk().getAplicacioncodigo().trim() + syReporte.getPk().getReportecodigo().trim();

			JasperReport mysubreport = (JasperReport) JRLoader.loadObjectFromFile(tmp.getRutaCompletaReporte());   
			reporte.getParams().add(new DominioParametroPersistencia(campo, null, mysubreport));

		}

		reporte.setAplicacionCodigo(originalIdAplicacion);
		reporte.setReporteCodigo(originalIdReporte);

		return reporte;
	}
	
	@Transactional
	private BeanSyReporte coreActivar(SeguridadUsuarioActual usuarioActual, BeanSyReporte syReporte) {
		// valores por defecto - preparando objeto
		syReporte = validar.prepararAnular(usuarioActual, syReporte);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, syReporte);
		if (!lst.isEmpty()) {
			syReporte.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syReporte.setTransaccionListaMensajes(lst);
			return syReporte;
		}

		// transaccion
		syReporte.setEstado("A");
		syReporte = syReporteDao.coreActualizar(syReporte);
		syReporte.setTransaccionEstado(DominioTransaccion.OK);
		syReporte.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syReporte;
	}

	@Transactional
	public DtoComunSyReporte coreActivarPorUuid(SeguridadUsuarioActual usuarioActual, DtoComunSyReporte dto) {
		BeanSyReporte syReporte = syReporteDao.obtenerPorUuid(dto.getUuid());
		syReporte = coreActivar(usuarioActual, syReporte);
		dto.setTransaccionEstado(syReporte.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReporte.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunSyReporte coreAnularPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunSyReporte dto) throws UException {
		BeanSyReporte syReporte = syReporteDao.obtenerPorUuid(dto.getUuid());
		syReporte = coreAnular(usuarioActual, syReporte);
		dto.setTransaccionEstado(syReporte.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReporte.getTransaccionListaMensajes());
		return dto;
	}
	
}
