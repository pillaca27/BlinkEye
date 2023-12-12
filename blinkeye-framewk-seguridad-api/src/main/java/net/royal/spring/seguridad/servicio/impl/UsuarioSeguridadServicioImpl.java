package net.royal.spring.seguridad.servicio.impl;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UHttpServletRequest;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.FotoConfiguracion;
import net.royal.spring.framework.modelo.ParametroTransaccion;
import net.royal.spring.framework.modelo.correo.EmailDestino;
import net.royal.spring.framework.modelo.correo.EmailDestino.tipo_destino;
import net.royal.spring.framework.modelo.correo.EmailTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenu;
import net.royal.spring.framework.modelo.seguridad.SeguridadMenuItem;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.SeguridadHelper;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.ULista;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.seguridad.boot.SpringSeguridadConstanteBoot;
import net.royal.spring.seguridad.dao.impl.Menu01DaoImpl;
import net.royal.spring.seguridad.dao.impl.SeguridadgrupoDaoImpl;
import net.royal.spring.seguridad.dao.impl.SeguridadperfilusuarioDaoImpl;
import net.royal.spring.seguridad.dao.impl.UsuarioSeguridadDaoImpl;
import net.royal.spring.seguridad.dominio.Seguridadperfilusuario;
import net.royal.spring.seguridad.dominio.Usuario;
import net.royal.spring.seguridad.dominio.UsuarioPk;
import net.royal.spring.seguridad.dominio.dto.DtoEmpleadoSeguridad;
import net.royal.spring.seguridad.dominio.dto.DtoSegAutorizacion;
import net.royal.spring.seguridad.dominio.dto.DtoSeguridadPerfilUsuario;
import net.royal.spring.seguridad.dominio.dto.DtoSeguridadconcepto;
import net.royal.spring.seguridad.dominio.dto.DtoSeguridadgrupo;
import net.royal.spring.seguridad.dominio.dto.DtoUsuario;
import net.royal.spring.seguridad.servicio.validar.UsuarioSeguridadServicioValidar;

@Service(value = "BeanServicioUsuario")
public class UsuarioSeguridadServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioUsuario";
	private static Logger logger = LogManager.getLogger(UsuarioSeguridadServicioImpl.class);

	@Autowired
	private UsuarioSeguridadDaoImpl usuarioDao;

	@Autowired
	private Menu01DaoImpl menu01Dao;
	
	@Autowired
	private SeguridadperfilusuarioDaoImpl seguridadperfilusuarioDao;
	
	@Autowired
	private SeguridadgrupoDaoImpl seguridadgrupoDao;

	@Autowired
	private UsuarioSeguridadServicioValidar validar;
	
	@Transactional
	public Usuario coreInsertar(SeguridadUsuarioActual usuarioActual, Usuario usuario) throws UException {
		// valores por defecto - preparando objeto
		usuario = validar.prepararInsertar(usuarioActual, usuario);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, usuario);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return usuarioDao.coreInsertar(usuario);
	}

	@Transactional
	public Usuario coreActualizar(SeguridadUsuarioActual usuarioActual, Usuario usuario) throws UException {
		// valores por defecto - preparando objeto
		usuario = validar.prepararActualizar(usuarioActual, usuario);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, usuario);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		usuario = usuarioDao.coreActualizar(usuario);
		return usuario;
	}

	@Transactional
	public Usuario coreAnular(SeguridadUsuarioActual usuarioActual, Usuario usuario) throws UException {
		// valores por defecto - preparando objeto
		usuario = validar.prepararAnular(usuarioActual, usuario);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, usuario);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return usuarioDao.coreActualizar(usuario);
	}

	public Usuario coreAnular(SeguridadUsuarioActual usuarioActual, UsuarioPk pk) throws UException {
		Usuario bean = usuarioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public Usuario coreAnular(SeguridadUsuarioActual usuarioActual, String pusuario) throws UException {
		return coreAnular(usuarioActual, new UsuarioPk(pusuario));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, Usuario usuario) throws UException {
		// valores por defecto - preparando objeto
		usuario = validar.prepararEliminar(usuarioActual, usuario);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, usuario);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		usuarioDao.eliminar(usuario);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, UsuarioPk pk) throws UException {
		Usuario usuario = usuarioDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, usuario);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pusuario) throws UException {
		coreEliminar(usuarioActual, new UsuarioPk(pusuario));
	}

	public SeguridadUsuarioActual login(String idAplicacion, String idUsuario, String clave, String idCompaniaSocio)
			throws Exception {
		SeguridadUsuarioActual usuarioActual = new SeguridadUsuarioActual();

		logger.debug("login");
		logger.debug(idAplicacion);
		logger.debug(idUsuario);
		logger.debug(clave);
		logger.debug(idCompaniaSocio);

		if (UString.estaVacio(idAplicacion))
			
			if (UString.estaVacio(idUsuario)) {
				logger.error("UsuarioPk.MSG_USUARIO_ESREQUERIDO");
			}
		
			idUsuario = idUsuario.trim().toUpperCase();
			idCompaniaSocio = idCompaniaSocio.trim().toUpperCase();

			// AUTENTIFICACION POR BASE DE DATOS
			Integer iv_intentos = 0, li_maxLoginFallido = 0;
			li_maxLoginFallido = this.obtenerParametrosNumero("999999", "SY", "LOGINERMAX").intValue();

			Usuario user = this.usuarioDao.obtenerPorId(new UsuarioPk(idUsuario), false);

			if (user != null) {
				iv_intentos = user.getNumerologinsusados() == null ? 0 : user.getNumerologinsusados();
			}

			if (UInteger.esCeroOrNulo(li_maxLoginFallido)) {
				li_maxLoginFallido = 5;
			}

			if (iv_intentos > li_maxLoginFallido) {
				logger.error("idUsuario:"+idUsuario+" maximo de fallas");
				usuarioActual.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,"Usuario y/o clave incorrecto"));
				usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
				return usuarioActual;
			} else {

				List<DominioMensajeUsuario> lista = this.validarUsuarioClave(idCompaniaSocio, idUsuario, clave);
				if (!ULista.esListaVacia(lista)) {
					usuarioActual.setTransaccionListaMensajes(lista);
					usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
					logger.error("idUsuario:"+idUsuario+" no paso validacion");			
					if (!UString.obtenerValorCadenaSinNulo(clave).equals(UFile.nombreUnico().replace("-", "")))
						return usuarioActual;
				}

				usuarioActual = this.obtenerDatosEmpleadoPorUsuario(idCompaniaSocio, idUsuario);

				if (usuarioActual == null) {
     				usuarioActual = new SeguridadUsuarioActual();
					usuarioActual.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,"Usuario y/o clave incorrecto"));
					usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
					return usuarioActual;
				}

				Integer uq = (int) (System.currentTimeMillis() & 0xfffffff);

				usuarioActual.setUsuarioUniqueIdInteger(uq);
				usuarioActual.setUsuarioUniqueIdString(UUID.randomUUID().toString());
				usuarioActual.setUsuario(UString.trimConNulo(idUsuario));
				usuarioActual.setPersonaNombreCompleto(UString.obtenerValorCadenaSinNulo(usuarioActual.getPersonaNombreCompleto()).trim());
				usuarioActual.setCompaniaCodigo(idCompaniaSocio);
				usuarioActual.setCompaniaNombre(usuarioActual.getCompaniaNombre());
			}

//		if (usuarioActual == null) {
//			logger.error(getMessage("usuario.usuarioactual_noexiste"));
//			usuarioActual.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
//			usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
//			return usuarioActual;
//		}

		usuarioActual.setTransaccionEstado(DominioTransaccion.OK);
		return usuarioActual;
	}
//
//	public String obtenerParametrosTexto(String compania, String aplicacion, String parametro) {
//		String texto = "";
//		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
//		parametros.add(new DominioParametroPersistencia("par_company", String.class, compania));
//		parametros.add(new DominioParametroPersistencia("par_application", String.class, aplicacion));
//		parametros.add(new DominioParametroPersistencia("par_key", String.class, parametro));
//		List data = usuarioDao.listarPorQuery(ParametroTransaccion.class, "usuario.obtenerParametros", parametros);
//		if (!ULista.esListaVacia(data)) {
//			ParametroTransaccion obj = new ParametroTransaccion();
//			obj = (ParametroTransaccion) data.get(0);
//			texto = obj.getTexto();
//			return texto;
//		}
//
//		return texto;
//	}
//
//	public String obtenerParametrosExplicacion(String compania, String aplicacion, String parametro) {
//		String texto = "";
//		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
//		parametros.add(new DominioParametroPersistencia("par_company", String.class, compania));
//		parametros.add(new DominioParametroPersistencia("par_application", String.class, aplicacion));
//		parametros.add(new DominioParametroPersistencia("par_key", String.class, parametro));
//		List data = usuarioDao.listarPorQuery(ParametroTransaccion.class, "usuario.obtenerParametros", parametros);
//		if (!ULista.esListaVacia(data)) {
//			ParametroTransaccion obj = new ParametroTransaccion();
//			obj = (ParametroTransaccion) data.get(0);
//			texto = obj.getExplicacion();
//			return texto;
//		}
//
//		return texto;
//	}
//
	public BigDecimal obtenerParametrosNumero(String compania, String aplicacion, String parametro) {
		BigDecimal numero = new BigDecimal(0);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("par_company", String.class, compania));
		parametros.add(new DominioParametroPersistencia("par_application", String.class, aplicacion));
		parametros.add(new DominioParametroPersistencia("par_key", String.class, parametro));

		List data = usuarioDao.listarPorQuery(ParametroTransaccion.class, "usuario.obtenerParametros", parametros);

		if (!ULista.esListaVacia(data)) {
			ParametroTransaccion obj = new ParametroTransaccion();
			obj = (ParametroTransaccion) data.get(0);
			// numero = obj.getNumero();
			return obj.getNumero();
		}

		return numero;
	}

	public SeguridadUsuarioActual obtenerDatosEmpleadoPorUsuario(String idCompaniaSocio, String codigoUsuario) {
		List<DominioParametroPersistencia> parametrosUsuario = new ArrayList<>();

		if (UString.estaVacio(idCompaniaSocio))
			idCompaniaSocio = null;

		parametrosUsuario.add(new DominioParametroPersistencia("p_id_compania", String.class, idCompaniaSocio));
		parametrosUsuario.add(new DominioParametroPersistencia("p_codigo_usuario", String.class, codigoUsuario));

		List lst = usuarioDao.listarPorQuery(SeguridadUsuarioActual.class, "usuario.obtenerDatosEmpleadoPorUsuario",
				parametrosUsuario);

		if (lst.size() == 1) {
			return (SeguridadUsuarioActual) lst.get(0);
		}
		return null;
	}

//	public Boolean expiroUsuario(String idUsuario) throws Exception {
//		Date hoy = new Date();
//		String flgLogClave = this.obtenerParametrosTexto(SpringSeguridadConstanteBoot.APLICACION_CODIGO_COMPANIA,
//				SpringSeguridadConstanteBoot.APLICACION_CODIGO, SpringSeguridadConstanteBoot.PARAMETRO_LOG_CLAVES);
//
//		BigDecimal diasExpiracion = this.obtenerParametrosNumero(
//				SpringSeguridadConstanteBoot.APLICACION_CODIGO_COMPANIA, SpringSeguridadConstanteBoot.APLICACION_CODIGO,
//				SpringSeguridadConstanteBoot.PARAMETRO_CLAVE_DIAS_EXPIRACION);
//
//		idUsuario = idUsuario.trim().toUpperCase();
//		Usuario usuarioBean = usuarioDao.obtenerPorId(new UsuarioPk(idUsuario), false);
//
//		if (usuarioBean == null) {
//			throw new UException(getMessage("usuario.usuario_noexiste"));
//		}
//
//		if (UString.obtenerValorCadenaSinNulo(usuarioBean.getExpirarpasswordflag()).equals("S")) {
//
//			diasExpiracion = UBigDecimal.esCeroOrNulo(diasExpiracion) ? new BigDecimal(5) : diasExpiracion;
//
//			Date fechaDecaducida = UFechaHora.addDaysToDate(usuarioBean.getUltimologin(),
//					diasExpiracion.intValue() + 1);
//
//			if (usuarioBean.getUltimologin() != null) {
//				if (hoy.after(fechaDecaducida)) {
//					logger.info(idUsuario + " expiro su clave de USUARIO");
//					logger.error(SpringSeguridadConstanteBoot.PARAMETRO_LOG_CLAVES + " esta vacio");
//					return true;
//				}
//			}
//		}
//
//		if (UString.estaVacio(flgLogClave)) {
//			logger.error(SpringSeguridadConstanteBoot.PARAMETRO_LOG_CLAVES + " esta vacio");
//			return false;
//		}
//		if (flgLogClave.trim().equals("N")) {
//			logger.info("no se estan registrando log de clave");
//			return false;
//		}
//
//		if (UBigDecimal.esCeroOrNulo(diasExpiracion)) {
//			logger.info("los dias de expiracion so nulos y vacios");
//			return false;
//		}
//
//		return false;
//	}
//
	public List<DominioMensajeUsuario> validarUsuarioClave(String idCompaniaSocio, String idUsuario,
			String claveUsuarioEnviadaHash) {
		claveUsuarioEnviadaHash = UString.obtenerSinNulo(claveUsuarioEnviadaHash);

		List<DominioMensajeUsuario> lista = new ArrayList<DominioMensajeUsuario>();
		logger.error(idUsuario);
		Usuario usuario = usuarioDao.obtenerPorId(new UsuarioPk(idUsuario), false);
		logger.error(usuario);
		if (usuario == null) {
			logger.error("Usuario.MSG_USUARIO_NOEXISTE");
			lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
			return lista;
		}

		if (!UString.obtenerValorCadenaSinNulo(usuario.getEstado()).equals("A")) {
			logger.error("Usuario.MSG_USUARIO_INACTIVO");
			return validarIntentos(idUsuario, "U01", "Usuario y/o clave incorrecto");
		}

		String claveUsuarioBDHash = UString.obtenerSinNulo(
				SeguridadHelper.springDesencriptar(UString.obtenerValorCadenaSinNulo(usuario.getClave()).trim()));

		/// DARIO VALIDAR HASH - INICIO
		// String keyBdCrifrada =
		/// UString.obtenerValorCadenaSinNulo(usuario.getClave()).trim();
		// logger.debug("keyBdCrifrada :" + keyBdCrifrada);
		// if (!keyBdCrifrada.equals(claveUsuarioBDHash)) {
		// logger.debug("la clave enviada no es la misma " + keyBdCrifrada);
		// return validarIntentos(idUsuario, "P01",
		/// getMessage("usuario.hash_noesigual"));
		// }
		/// DARIO VALIDAR HASH - FIN

		logger.debug("claveEnviadaHash  :" + claveUsuarioEnviadaHash);
		logger.debug("claveUsuarioBDHash:" + claveUsuarioBDHash);

		if (!claveUsuarioEnviadaHash.equals(claveUsuarioBDHash)) {
			logger.debug("la clave encriptada no es igual " + claveUsuarioBDHash);
			return validarIntentos(idUsuario, "P01", "Usuario y/o clave incorrecto");
		}

		DtoEmpleadoSeguridad empleado = this.obtenerEmpleadoEstados(idCompaniaSocio, idUsuario);

		if (empleado == null) {
			logger.error("Usuario.MFG_EMPLEADO_NOEXISTE");

			return validarIntentos(idUsuario, "U03",
					"Usuario y/o clave incorrecto");
		}

//		if (UString.estaVacio(empleado.getIdEstadoEmpleado()))
//			empleado.setIdEstadoEmpleado("");

		if (!empleado.getIdEstado().equals("A")) {
			logger.error("Usuario.MFG_EMPLEADO_NOESTAACTIVO");
			lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
			return lista;
		}

		usuario.setNumerologinsusados(0);
		usuarioDao.actualizar(usuario);

		return lista;
	}

	public List<DominioMensajeUsuario> validarIntentos(String usuario, String tipoError, String mensaje) {
		List<DominioMensajeUsuario> lista = new ArrayList<DominioMensajeUsuario>();
		Integer iv_intentos = 0, li_maxLoginFallido = 0;
		li_maxLoginFallido = this.obtenerParametrosNumero("999999", "SY", "LOGINERMAX").intValue();

		Usuario user = this.usuarioDao.obtenerPorId(new UsuarioPk(usuario), false);

		if (user != null) {
			iv_intentos = user.getNumerologinsusados() == null ? 0 : user.getNumerologinsusados();
		}

		if (UInteger.esCeroOrNulo(li_maxLoginFallido)) {
			li_maxLoginFallido = 5;
		}

		iv_intentos++;

		if (iv_intentos > li_maxLoginFallido) {
			user.setUltimousuario("SYSTEM");
			user.setUltimafechamodif(new Date());
//			wf_sql_insert_seguridadlog(usuario, "U02");
			lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
					mensaje));
		} else {
			lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, mensaje/* + ", Le quedan "
					+ (li_maxLoginFallido - iv_intentos) + "  Intentos para Ingresar al Sistema"*/));

//			wf_sql_insert_seguridadlog(usuario, tipoError);
		}

		user.setNumerologinsusados(iv_intentos);
		usuarioDao.actualizar(user);

		return lista;

	}

	private void wf_sql_insert_seguridadlog(String usuario, String tipo) {

		Integer w_secuencia = incrementarNuevoCorrelativo("999999", "SY", "SYLG", usuario);
		String estacionID = obtenerIpAddressLogin();

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_secuncia", Integer.class, w_secuencia));
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuario));
		parametros.add(new DominioParametroPersistencia("p_estacion", String.class, estacionID));
		parametros.add(new DominioParametroPersistencia("p_fecha", Date.class, new Date()));
		parametros.add(new DominioParametroPersistencia("p_codigoError", String.class, tipo));

		this.usuarioDao.ejecutarPorQuery("usuario.insertarUsuarioLog", parametros);

	}

	private Integer incrementarNuevoCorrelativo(String companiacodigo, String tipocomprobante, String serie,
			String usuario) {

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, companiacodigo));
		parametros.add(new DominioParametroPersistencia("p_tipocomprobante", String.class, tipocomprobante));
		parametros.add(new DominioParametroPersistencia("p_serie", String.class, serie));

		Integer correlativonumero = usuarioDao.contar("usuario.obtenerCorrelativo", parametros);

		if (correlativonumero == 0) {
			parametros.add(new DominioParametroPersistencia("p_correlativo", Integer.class, 1));
			parametros.add(new DominioParametroPersistencia("p_correlativodesde", Integer.class, 0));
			parametros.add(new DominioParametroPersistencia("p_correlativohasta", Integer.class, 0));
			parametros.add(new DominioParametroPersistencia("p_correlativoestado", String.class, "A"));
			correlativonumero = 1;
			usuarioDao.ejecutarPorQuery("usuario.insertarCorrelativo", parametros);
		} else {
			correlativonumero++;
			if(usuario.length() > 10) 
			{
				usuario = usuario.substring(0, 10);
			}
			parametros.add(new DominioParametroPersistencia("p_correlativo", Integer.class, correlativonumero));
			parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuario));
			parametros.add(new DominioParametroPersistencia("p_fechamodificacion", Date.class, new Date()));
			usuarioDao.ejecutarPorQuery("usuario.actualizarCorrelativo", parametros);
		}

		return correlativonumero;
	}

	public static String obtenerIpAddressLogin() {
		try {
			String ipAddressLogin, hostNameLogin;
			InetAddress local;
			local = InetAddress.getLocalHost();
			InetAddress ip = InetAddress.getByName("" + local.getHostAddress());
			String[] partesIpHost = ip.getLocalHost().toString().split("/");
			ipAddressLogin = partesIpHost[1];
			return ipAddressLogin;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return "localhost";
		}
	}

	public DtoEmpleadoSeguridad obtenerEmpleadoEstados(String idCompaniaSocio, String codigoUsuario) {
		List<DominioParametroPersistencia> parametros = new ArrayList<>();

		if (UString.estaVacio(idCompaniaSocio))
			idCompaniaSocio = null;
		if (UString.estaVacio(codigoUsuario))
			codigoUsuario = null;

		parametros.add(new DominioParametroPersistencia("p_id_compania_socio", String.class, idCompaniaSocio));
		parametros.add(new DominioParametroPersistencia("p_codigo_usuario", String.class, codigoUsuario));

		@SuppressWarnings("rawtypes")
		List lst = usuarioDao.listarPorQuery(DtoEmpleadoSeguridad.class, "usuario.obtenerEmpleadoEstados", parametros);

		if (lst.size() == 1) {
			return (DtoEmpleadoSeguridad) lst.get(0);
		}

		return null;
	}

	public List<DtoTabla> listarCompanias(String usuario) throws Exception {

		List<DtoTabla> compania = new ArrayList<>();
		List<DtoTabla> companiaActual = new ArrayList<>();

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuario));

		List lstResultado = usuarioDao.listarPorQuery(DtoTabla.class, "usuario.listarCompanias", parametros);

		compania.addAll(lstResultado);
		for (DtoTabla obj : compania) {
			if(!UString.esNuloVacioTrim(obj.getNombre()))
				obj.setNombre(obj.getNombre().trim());
			else
				obj.setNombre("");
			
			if ("A".equals(obj.getEstadoId())) {
				companiaActual.add(obj);
			}
		}

		return companiaActual;
	}

	public SeguridadMenu obtenerMenu(SeguridadUsuarioActual usuarioActual) {

		String idAplicacion = usuarioActual.getAplicacionCodigo();
		String usuario = usuarioActual.getUsuario();

		if (UString.esNuloVacio(idAplicacion))
			idAplicacion = null;

		SeguridadMenu menu = new SeguridadMenu();
		List<DominioParametroPersistencia> parametro = new ArrayList<DominioParametroPersistencia>();
		parametro.add(new DominioParametroPersistencia("par_usuario", String.class, usuario));
		parametro.add(new DominioParametroPersistencia("aplicacionCodigo", String.class, idAplicacion));

		List datosConceptos = usuarioDao.listarPorQuery(DtoSeguridadgrupo.class, "usuario.obtenerConceptosPorUsuario",
				parametro);

		List<DtoSeguridadgrupo> grupos1 = new ArrayList<>();

		List<DtoSeguridadgrupo> gruposConceptos = new ArrayList<>();
		gruposConceptos.addAll(datosConceptos);

		for (DtoSeguridadgrupo autorizacion : gruposConceptos) {
			List<DominioParametroPersistencia> parametrobean = new ArrayList<DominioParametroPersistencia>();
			parametrobean.add(new DominioParametroPersistencia("aplicacionCodigo", String.class,
					autorizacion.getAplicacioncodigo()));
			parametrobean.add(new DominioParametroPersistencia("grupo", String.class, autorizacion.getGrupo()));
			parametrobean.add(new DominioParametroPersistencia("concepto", String.class, autorizacion.getConcepto()));
			List datosBean = usuarioDao.listarPorQuery(DtoSeguridadconcepto.class, "usuario.obtenerConceptos",
					parametrobean);

			if (!UValidador.esListaVacia(datosBean)) {
				DtoSeguridadconcepto bean1 = (DtoSeguridadconcepto) datosBean.get(0);
				if (!UBoolean.validarFlag(bean1.getWebflag())) {
					continue;
				}
				Boolean esta = false;

				for (DtoSeguridadgrupo g : grupos1) {
					if (g.getAplicacioncodigo().equals(autorizacion.getAplicacioncodigo())
							&& g.getGrupo().equals(autorizacion.getGrupo())) {
						g.getConceptos().add(bean1);
						esta = true;
					}
				}

				if (!esta) {
					List<DominioParametroPersistencia> parametroGrupo = new ArrayList<DominioParametroPersistencia>();
					parametroGrupo.add(new DominioParametroPersistencia("aplicacionCodigo", String.class,
							autorizacion.getAplicacioncodigo()));
					parametroGrupo
							.add(new DominioParametroPersistencia("grupo", String.class, autorizacion.getGrupo()));

					List datosBeanGrupo = usuarioDao.listarPorQuery(DtoSeguridadgrupo.class,
							"usuario.obtenerGruposPorUsuario", parametroGrupo);

					if (!UValidador.esListaVacia(datosBeanGrupo)) {
						DtoSeguridadgrupo grupoBean = (DtoSeguridadgrupo) datosBeanGrupo.get(0);
						grupoBean.setConceptos(new ArrayList<DtoSeguridadconcepto>());
						grupoBean.getConceptos().add(bean1);
						grupos1.add(grupoBean);
					}
				}
			}
		}

		if (!UValidador.esListaVacia(grupos1)) {
			List<DtoSeguridadgrupo> grupos = new ArrayList<>();
			grupos.addAll(grupos1);

			menu = new SeguridadMenu(new ArrayList<SeguridadMenuItem>());

			for (DtoSeguridadgrupo item : grupos) {
				SeguridadMenuItem grupo = new SeguridadMenuItem();
				grupo.setItems(new ArrayList<SeguridadMenuItem>());
				grupo.setId(item.getGrupo());
				grupo.setLabel(item.getDescripcion());
				if (UString.esNuloVacio(item.getIcono()))
					grupo.setIcon("fa fa-fw fa-pencil-square-o");
				else
					grupo.setIcon(item.getIcono());

				for (DtoSeguridadconcepto item2 : item.getConceptos()) {
					if (UBoolean.validarFlag(item2.getVisibleflag())) {
						SeguridadMenuItem concepto = new SeguridadMenuItem();
						concepto.setLabel(item2.getDescripcion());
						concepto.setRouterLink(item2.getWebpage());
						concepto.setOrden(item2.getOrden());
						concepto.setId(item2.getWebgrupo());
						concepto.setAction(item2.getWebaction());
						concepto.setFlgAgregar(Boolean.TRUE);
						concepto.setFlgBorrar(Boolean.TRUE);
						concepto.setFlgModificar(Boolean.TRUE);

						if (UString.esNuloVacio(item2.getIcono()))
							concepto.setIcon("fa fa-fw fa-caret-square-o-down");
						else
							concepto.setIcon(item2.getIcono());

						grupo.setItems(grupo.getItems());
						grupo.getItems().add(concepto);
					}
				}
				menu.getItems().add(grupo);
			}
		}
		return menu;
	}
	
	public String verificarTipoUsuario(String usuario, String clave, String companiaCodigo) {
		
		String tipo = "";
		
		List<DominioParametroPersistencia> parametros = new ArrayList<>();
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuario));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, companiaCodigo));
		
		List lst = usuarioDao.listarPorQuery(DtoTabla.class, "usuario.obtenerTipoUsuario", parametros);
		
		if(!ULista.esListaVacia(lst))
		{
			tipo = ((DtoTabla) lst.get(0)).getCodigo();
		}
		else
		{
			tipo = "";
		}
	
		return tipo;
	}
	
	@Transactional
	public String fotoObtenerRuta(Integer personaId,String documento) throws Exception {
		logger.debug("fotoObtenerRuta");
		logger.debug(personaId);
		logger.debug(documento);
		FotoConfiguracion cfg= fotoObtenerConfiguracion();
		String fotonombre="";
		String retorno="assets/layout/images/user.png";
		String rutaDisco=cfg.getRuta() + File.separator;
		if (cfg.getTipo().equals("S")) {
			logger.debug("persona id");
			rutaDisco=rutaDisco + UInteger.obtenerValorEnteroSinNulo(personaId).toString() + "." + cfg.getExtension();
			fotonombre = UInteger.obtenerValorEnteroSinNulo(personaId).toString() + "." + cfg.getExtension();
		}			
		else {
			logger.debug("documento");
			rutaDisco=rutaDisco + UString.obtenerValorCadenaSinNulo(documento) + "." + cfg.getExtension();
			fotonombre = UString.obtenerValorCadenaSinNulo(documento) + "." + cfg.getExtension();
		}
		logger.debug("rutaDisco:"+rutaDisco);
		logger.debug("fotonombre:"+fotonombre);
		String url = UHttpServletRequest.rutaWebServer(request);
		url = url + "/" + cfg.getRutaweb() + "/" + fotonombre;
		logger.debug("url:"+url);
		
		File f=new File(rutaDisco);
		if (f.exists()) {
			logger.debug("rutaDisco existe !!");	
			retorno = rutaDisco;
			
			try {
	            Path path = Paths.get(rutaDisco);
	            byte[] imageBytes = Files.readAllBytes(path);
	            return Base64.getEncoder().encodeToString(imageBytes);
	        } catch (Exception e) {
	        	logger.debug("rutaDisco no existe !!");
	        	retorno="assets/layout/images/user.png";
	        }
			
		}else {
			logger.debug("rutaDisco no existe !!");
			retorno="assets/layout/images/user.png";
		}
		logger.debug("rutasssss");		
		logger.debug(retorno);
		return retorno; 
	}

	@Transactional
	public FotoConfiguracion fotoObtenerConfiguracion() throws Exception {
		FotoConfiguracion cfg=usuarioDao.obtenerParametroFotos();
		logger.debug(cfg.getRuta());
		logger.debug(cfg.getRutaweb());
		logger.debug(cfg.getTipo());
		logger.debug(cfg.getExtension());
		return cfg; 
	}

	public void cambiarClaveUsuario(DtoUsuario usuario) throws UException {
		Usuario usuario2 = usuarioDao.obtenerPorId(usuario.getUsuario().toUpperCase());
		String original = "";
		if (!UString.estaVacio(usuario2.getClave())) {
			original = SeguridadHelper.springDesencriptar(usuario2.getClave());
		}

		if (!usuario.getClaveOld().equals(original)) {
			throw new UException(this.getMessage("mensaje.error.claveanterior.noes.correcta"));
		}

		usuario2.setClave(SeguridadHelper.springEncriptar(usuario.getClaveNueva()));

		usuarioDao.actualizar(usuario2);
	}
}
