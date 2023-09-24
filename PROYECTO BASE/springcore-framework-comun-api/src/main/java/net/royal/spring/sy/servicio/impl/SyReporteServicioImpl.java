package net.royal.spring.sy.servicio.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sy.dao.impl.SyReporteDaoImpl;
import net.royal.spring.sy.dao.impl.SyReportearchivoDaoImpl;
import net.royal.spring.sy.dominio.BeanSyReporte;
import net.royal.spring.sy.dominio.BeanSyReportePk;
import net.royal.spring.sy.dominio.BeanSyReportearchivo;
import net.royal.spring.sy.dominio.dto.DtoComunSyReporte;
import net.royal.spring.sy.dominio.filtro.FiltroComunSyReporte;
import net.royal.spring.sy.servicio.validar.SyReporteServicioValidar;

@Service(value = "BeanServicioSyReporte")
public class SyReporteServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyReporte";
	private static Logger logger = LogManager.getLogger(SyReporteServicioImpl.class);

	@Autowired
	private SyReporteDaoImpl syReporteDao;

	@Autowired
	private SyReporteServicioValidar validar;

	@Autowired
	private SyReportearchivoDaoImpl syReportearchivoDaoImpl;

	@Transactional
	public DtoComunSyReporte coreInsertar(SeguridadUsuarioActual usuarioActual, DtoComunSyReporte dto)
			throws UException {
		BeanSyReporte syReporte = dto.obtenerBean();
		syReporte = coreInsertar(usuarioActual, syReporte);
		dto.setTransaccionEstado(syReporte.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReporte.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReporte coreInsertar(SeguridadUsuarioActual usuarioActual, BeanSyReporte syReporte) throws UException {
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
	public DtoComunSyReporte coreActualizar(SeguridadUsuarioActual usuarioActual, DtoComunSyReporte dto)
			throws UException {
		BeanSyReporte syReporte = dto.obtenerBean();
		syReporte = coreActualizar(usuarioActual, syReporte);
		dto.setTransaccionEstado(syReporte.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReporte.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReporte coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyReporte syReporte)
			throws UException {
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
	public DtoComunSyReporte coreAnular(SeguridadUsuarioActual usuarioActual, DtoComunSyReporte dto) throws UException {
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
		syReporte = syReporteDao.coreActualizar(syReporte);
		syReporte.setTransaccionEstado(DominioTransaccion.OK);
		syReporte.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syReporte;
	}

	public BeanSyReporte coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyReportePk pk) throws UException {
		BeanSyReporte bean = syReporteDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public BeanSyReporte coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,
			String preportecodigo) throws UException {
		return coreAnular(usuarioActual, new BeanSyReportePk(paplicacioncodigo, preportecodigo));
	}

	@Transactional
	public DtoComunSyReporte coreEliminar(SeguridadUsuarioActual usuarioActual, DtoComunSyReporte dto)
			throws UException {
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
		return coreEliminar(usuarioActual, syReporte);
	}

	public BeanSyReporte coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,
			String preportecodigo) throws UException {
		return coreEliminar(usuarioActual, new BeanSyReportePk(paplicacioncodigo, preportecodigo));
	}

	public DominioPaginacion listarpaginadoReportes(SeguridadUsuarioActual usuarioActual,
			FiltroComunSyReporte paginacion) {

		paginacion.setUsuario(usuarioActual.getUsuario());
		return syReporteDao.listarpaginadoReportes(paginacion);
	}

	public DominioPaginacion reportelistarConPaginacion(SeguridadUsuarioActual usuarioActual,
			FiltroComunSyReporte paginacion) {
		return syReporteDao.reportelistarConPaginacion(paginacion);
	}

	public BeanSyReporte reportesolicitudRegistrar(SeguridadUsuarioActual usuarioActual, BeanSyReporte bean)
			throws UException {
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
		/*
		 * if(bean.getSentencia()!=null) { if(bean.getSentencia().length>0) {
		 * bean.setAuxsentencia(new String(bean.getSentencia(),
		 * StandardCharsets.UTF_8)); } }
		 */
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
/*
	public DtoExportarReportes exportarJsonReportes(FiltroComunSyReporte filtro) {
		DtoExportarReportes dto = new DtoExportarReportes();

		BeanSyReporte beanSyReporte = syReporteDao.obtenerPorId(filtro.getAplicacioncodigo(),
				filtro.getReportecodigo());
		List<BeanSyReportearchivo> lstBeanSyReporteArchivo = syReportearchivoDaoImpl.getCriteria()
				.add(Restrictions.eq("pk.aplicacioncodigo", filtro.getAplicacioncodigo()))
				.add(Restrictions.eq("pk.reportecodigo", filtro.getReportecodigo())).list();

		DtoExportarReportesJson json = new DtoExportarReportesJson();
		json.setCabecera(beanSyReporte);
		json.setDetalles(lstBeanSyReporteArchivo);

		try {
			ObjectMapper mapper = new ObjectMapper();
			dto.setArchivoNombre(filtro.getAplicacioncodigo() + "_" + filtro.getReportecodigo()
					+ new SimpleDateFormat("_dd-MM-yyyy_hh-mm-ss").format(new Date()) + ".json");
			dto.setArchivoBase64(mapper.writeValueAsString(json));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Error al exportar"));
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoExportarReportes importarJsonReportes(DtoExportarReportes dto) {

		try {
			ObjectMapper mapper = new ObjectMapper();
			String parsed = new String(Base64.getDecoder().decode(dto.getArchivoBase64()));
			DtoExportarReportesJson dtoImportar = mapper.readValue(parsed, DtoExportarReportesJson.class);

			syReporteDao.eliminar(dtoImportar.getCabecera());
			syReporteDao.registrar(dtoImportar.getCabecera());

			for (BeanSyReportearchivo row : dtoImportar.getDetalles()) {
				syReportearchivoDaoImpl.eliminar(row);
				syReportearchivoDaoImpl.registrar(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Error al importar"));
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;

	}*/
}
