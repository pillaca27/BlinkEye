package net.royal.spring.sistema.servicio.impl;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Base64;
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
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.util.UValidador;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyReportearchivoDaoImpl;

import net.royal.spring.sistema.dominio.BeanSyReportearchivo;
import net.royal.spring.sistema.dominio.BeanSyReportearchivoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyReportearchivo;
import net.royal.spring.sistema.servicio.validar.SyReportearchivoServicioValidar;

@Service(value = "BeanServicioSyReportearchivo")
public class SyReportearchivoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyReportearchivo";
	private static Logger logger = LogManager.getLogger(SyReportearchivoServicioImpl.class);

	@Autowired
	private SyReportearchivoDaoImpl syReportearchivoDao;

	@Autowired
	private SyReportearchivoServicioValidar validar;

	@Transactional
	public DtoComunSyReportearchivo coreInsertar(SeguridadUsuarioActual usuarioActual, DtoComunSyReportearchivo dto)
			throws UException {
		BeanSyReportearchivo syReportearchivo = dto.obtenerBean();
		syReportearchivo = coreInsertar(usuarioActual, syReportearchivo);
		dto.setTransaccionEstado(syReportearchivo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReportearchivo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReportearchivo coreInsertar(SeguridadUsuarioActual usuarioActual,
			BeanSyReportearchivo syReportearchivo) throws UException {
		// valores por defecto - preparando objeto
		syReportearchivo = validar.prepararInsertar(usuarioActual, syReportearchivo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syReportearchivo);
		if (!lst.isEmpty()) {
			syReportearchivo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syReportearchivo.setTransaccionListaMensajes(lst);
			return syReportearchivo;
		}

		// transaccion
		syReportearchivo = syReportearchivoDao.coreInsertar(syReportearchivo);
		syReportearchivo.setTransaccionEstado(DominioTransaccion.OK);
		syReportearchivo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syReportearchivo;
	}

	@Transactional
	public DtoComunSyReportearchivo coreActualizar(SeguridadUsuarioActual usuarioActual, DtoComunSyReportearchivo dto)
			throws UException {
		BeanSyReportearchivo syReportearchivo = dto.obtenerBean();
		syReportearchivo = coreActualizar(usuarioActual, syReportearchivo);
		dto.setTransaccionEstado(syReportearchivo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReportearchivo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReportearchivo coreActualizar(SeguridadUsuarioActual usuarioActual,
			BeanSyReportearchivo syReportearchivo) throws UException {
		// valores por defecto - preparando objeto
		syReportearchivo = validar.prepararActualizar(usuarioActual, syReportearchivo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syReportearchivo);
		if (!lst.isEmpty()) {
			syReportearchivo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syReportearchivo.setTransaccionListaMensajes(lst);
			return syReportearchivo;
		}

		// transaccion
		syReportearchivo = syReportearchivoDao.coreActualizar(syReportearchivo);
		syReportearchivo.setTransaccionEstado(DominioTransaccion.OK);
		syReportearchivo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syReportearchivo;
	}

	@Transactional
	public DtoComunSyReportearchivo coreAnular(SeguridadUsuarioActual usuarioActual, DtoComunSyReportearchivo dto)
			throws UException {
		BeanSyReportearchivo syReportearchivo = dto.obtenerBean();
		syReportearchivo = coreAnular(usuarioActual, syReportearchivo);
		dto.setTransaccionEstado(syReportearchivo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReportearchivo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReportearchivo coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyReportearchivo syReportearchivo)
			throws UException {
		// valores por defecto - preparando objeto
		syReportearchivo = validar.prepararAnular(usuarioActual, syReportearchivo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, syReportearchivo);
		if (!lst.isEmpty()) {
			syReportearchivo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syReportearchivo.setTransaccionListaMensajes(lst);
			return syReportearchivo;
		}

		// transaccion
		syReportearchivo = syReportearchivoDao.coreActualizar(syReportearchivo);
		syReportearchivo.setTransaccionEstado(DominioTransaccion.OK);
		syReportearchivo.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syReportearchivo;
	}

	public BeanSyReportearchivo coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyReportearchivoPk pk)
			throws UException {
		BeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public BeanSyReportearchivo coreAnular(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,
			String preportecodigo, String pcompaniasocio, String pperiodo, String pversion) throws UException {
		return coreAnular(usuarioActual,
				new BeanSyReportearchivoPk(paplicacioncodigo, preportecodigo, pcompaniasocio, pperiodo, pversion));
	}

	@Transactional
	public DtoComunSyReportearchivo coreEliminar(SeguridadUsuarioActual usuarioActual, DtoComunSyReportearchivo dto)
			throws UException {
		BeanSyReportearchivo syReportearchivo = dto.obtenerBean();
		syReportearchivo = coreEliminar(usuarioActual, syReportearchivo);
		dto.setTransaccionEstado(syReportearchivo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syReportearchivo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyReportearchivo coreEliminar(SeguridadUsuarioActual usuarioActual,
			BeanSyReportearchivo syReportearchivo) throws UException {
		// valores por defecto - preparando objeto
		syReportearchivo = validar.prepararEliminar(usuarioActual, syReportearchivo);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syReportearchivo);
		if (!lst.isEmpty()) {
			syReportearchivo.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syReportearchivo.setTransaccionListaMensajes(lst);
			return syReportearchivo;
		}

		// transaccion
		syReportearchivoDao.eliminar(syReportearchivo);
		syReportearchivo = new BeanSyReportearchivo();
		syReportearchivo.setTransaccionEstado(DominioTransaccion.OK);
		return syReportearchivo;
	}

	public BeanSyReportearchivo coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyReportearchivoPk pk)
			throws UException {
		BeanSyReportearchivo syReportearchivo = syReportearchivoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, syReportearchivo);
	}

	public BeanSyReportearchivo coreEliminar(SeguridadUsuarioActual usuarioActual, String paplicacioncodigo,
			String preportecodigo, String pcompaniasocio, String pperiodo, String pversion) throws UException {
		return coreEliminar(usuarioActual,
				new BeanSyReportearchivoPk(paplicacioncodigo, preportecodigo, pcompaniasocio, pperiodo, pversion));
	}

	public BeanSyReportearchivo archivosolicitudRegistrar(SeguridadUsuarioActual usuarioActual,
			BeanSyReportearchivo bean) throws UException {
		List<DominioMensajeUsuario> lstRetorno = new ArrayList<DominioMensajeUsuario>();

		if (!UString.estaVacio(bean.getAuxString())) {
			bean.setReporte(bean.getAuxString().getBytes(StandardCharsets.UTF_8));
		}

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

	public BeanSyReportearchivo archivosolicitudObtenerPorId(BeanSyReportearchivoPk pk) {
		BeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(pk);
		if (bean.getReporte() != null) {
			if (bean.getReporte().length > 0) {
				bean.setAuxString(new String(bean.getReporte(), StandardCharsets.UTF_8));
				bean.setReporte(null);
			}
		}
		return bean;
	}

	public BeanSyReportearchivo archivosolicitudActualizar(SeguridadUsuarioActual usuarioActual,
			BeanSyReportearchivo bean, String vieneBotonSubir, String tipoarchivo) {

		if (!UString.estaVacio(bean.getAuxString()) && ("S".equals(vieneBotonSubir))) {
			bean.setReporte(null);
			byte imagen[] = Base64.getDecoder().decode(bean.getAuxString());
			if (!UValidador.esNulo(imagen)) {
				
				bean.setReporte(imagen);
			}
		} else if (!UString.estaVacio(bean.getAuxString()) && ("HTML".equals(tipoarchivo))) {
			bean.setReporte(null);
			byte imagen[] = bean.getAuxString().getBytes(StandardCharsets.UTF_8);
			if (!UValidador.esNulo(imagen)) {
				bean.setReporte(imagen);
			}
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

	public BeanSyReportearchivo eliminarReporteArchivo(BeanSyReportearchivoPk pk) {
		syReportearchivoDao.eliminar(new BeanSyReportearchivo(pk));
		return new BeanSyReportearchivo();
	}

}
