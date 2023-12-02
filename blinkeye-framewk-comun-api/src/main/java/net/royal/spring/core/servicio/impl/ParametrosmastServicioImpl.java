package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;
import net.royal.spring.core.dominio.BeanParametrosmast;
import net.royal.spring.core.dominio.BeanParametrosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunParametrosmast;
import net.royal.spring.core.servicio.validar.ParametrosmastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.ParametroTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioParametrosmast")
public class ParametrosmastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioParametrosmast";
	private static Logger logger = LogManager.getLogger(ParametrosmastServicioImpl.class);

	@Autowired
	private ParametrosmastDaoImpl parametrosmastDao;

	@Autowired
	private ParametrosmastServicioValidar validar;

	@Transactional
	public DtoComunParametrosmast coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual, DtoComunParametrosmast dto)
			throws Exception {
		BeanParametrosmast acSucursalgrupo = parametrosmastDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public DtoComunParametrosmast coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,
			DtoComunParametrosmast dto) throws Exception {
		BeanParametrosmast acSucursalgrupo = parametrosmastDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = dto.obtenerBeanActualizar(acSucursalgrupo);
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	public DtoComunParametrosmast coreAnularPorUuid(SeguridadUsuarioActual usuarioActual, DtoComunParametrosmast dto)
			throws Exception {
		BeanParametrosmast bean = parametrosmastDao.obtenerPorUuid(dto.getUuid());
		bean = validar.prepararAuditoria(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, bean);
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return dto;
		}
		bean.setEstado("N");
		bean = parametrosmastDao.coreActualizar(bean);
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	@Transactional
	public DtoComunParametrosmast coreInsertar(SeguridadUsuarioActual usuarioActual, DtoComunParametrosmast dto)
			throws UException {
		BeanParametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreInsertar(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanParametrosmast coreInsertar(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast)
			throws UException {
		// valores por defecto - preparando objeto

		parametrosmast = validar.prepararInsertar(usuarioActual, parametrosmast);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}

		// transaccion
		parametrosmast = parametrosmastDao.coreInsertar(parametrosmast);
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		parametrosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return parametrosmast;
	}

	@Transactional
	public DtoComunParametrosmast coreActualizar(SeguridadUsuarioActual usuarioActual, DtoComunParametrosmast dto)
			throws UException {
		BeanParametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreActualizar(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanParametrosmast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast)
			throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararActualizar(usuarioActual, parametrosmast);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}

		// transaccion
		parametrosmast = parametrosmastDao.coreActualizar(parametrosmast);
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		parametrosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return parametrosmast;
	}

	@Transactional
	public DtoComunParametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, DtoComunParametrosmast dto)
			throws UException {
		BeanParametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreAnular(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanParametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast)
			throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararAnular(usuarioActual, parametrosmast);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}

		// transaccion
		parametrosmast = parametrosmastDao.coreActualizar(parametrosmast);
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		parametrosmast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return parametrosmast;
	}

	public BeanParametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, BeanParametrosmastPk pk)
			throws UException {
		BeanParametrosmast bean = parametrosmastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public BeanParametrosmast coreAnular(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo,
			String paplicacioncodigo, String pparametroclave) throws UException {
		return coreAnular(usuarioActual, new BeanParametrosmastPk(pcompaniacodigo, paplicacioncodigo, pparametroclave));
	}

	@Transactional
	public DtoComunParametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual, DtoComunParametrosmast dto)
			throws UException {
		BeanParametrosmast parametrosmast = dto.obtenerBean();
		parametrosmast = coreEliminar(usuarioActual, parametrosmast);
		dto.setTransaccionEstado(parametrosmast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(parametrosmast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanParametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanParametrosmast parametrosmast)
			throws UException {
		// valores por defecto - preparando objeto
		parametrosmast = validar.prepararEliminar(usuarioActual, parametrosmast);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, parametrosmast);
		if (!lst.isEmpty()) {
			parametrosmast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			parametrosmast.setTransaccionListaMensajes(lst);
			return parametrosmast;
		}

		// transaccion
		parametrosmastDao.eliminar(parametrosmast);
		parametrosmast = new BeanParametrosmast();
		parametrosmast.setTransaccionEstado(DominioTransaccion.OK);
		return parametrosmast;
	}

	public BeanParametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanParametrosmastPk pk)
			throws UException {
		BeanParametrosmast parametrosmast = parametrosmastDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, parametrosmast);
	}

	public BeanParametrosmast coreEliminar(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo,
			String paplicacioncodigo, String pparametroclave) throws UException {
		return coreEliminar(usuarioActual,
				new BeanParametrosmastPk(pcompaniacodigo, paplicacioncodigo, pparametroclave));
	}

	public List<ParametroTransaccion> parametroList(String aplicacioncodigo, String parametroclave) throws Exception {
		ParametroTransaccion bean = new ParametroTransaccion(null, aplicacioncodigo, parametroclave);
		List<ParametroTransaccion> lst = parametrolist(bean);
		return lst;
	}
	
	public List<ParametroTransaccion> parametrolist(ParametroTransaccion pTran) throws Exception {
		logger.debug("ParametrosmastRest.parametroobtener");
		List<ParametroTransaccion> lst = null;
		if (pTran == null)
			pTran = new ParametroTransaccion();
		if (UString.esNuloVacio(pTran.getAplicacioncodigo()))
			pTran.setAplicacioncodigo("99");
		if (UString.esNuloVacio(pTran.getCompaniacodigo()))
			pTran.setCompaniacodigo("999999");

		try {
			lst = parametrosmastDao.obtenerDtoPorAplicacion(pTran.getCompaniacodigo(), pTran.getAplicacioncodigo(),
					pTran.getParametroclave());

		} catch (Exception e) {
			e.printStackTrace();
			pTran.setTransaccionEstado(DominioTransaccion.ERROR);
			pTran.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, e.getMessage()));
			lst.add(pTran);
		}
		return lst;
	}

}
