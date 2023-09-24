package net.royal.spring.seguridad.servicio.impl;

import java.io.File;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import net.royal.spring.seguridad.dao.impl.SeguridadperfilusuarioDaoImpl;
import net.royal.spring.seguridad.dao.impl.UsuarioDaoImpl;
import net.royal.spring.seguridad.dominio.Seguridadperfilusuario;
import net.royal.spring.seguridad.dominio.Usuario;
import net.royal.spring.seguridad.dominio.UsuarioPk;
import net.royal.spring.seguridad.dominio.dto.DtoEmpleadoSeguridad;
import net.royal.spring.seguridad.dominio.dto.DtoSegAutorizacion;
import net.royal.spring.seguridad.dominio.dto.DtoSeguridadconcepto;
import net.royal.spring.seguridad.dominio.dto.DtoSeguridadgrupo;
import net.royal.spring.seguridad.dominio.dto.DtoUsuario;
import net.royal.spring.seguridad.servicio.validar.UsuarioServicioValidar;

@Service(value = "BeanServicioUsuario")
public class UsuarioServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioUsuario";
	private static Logger logger = LogManager.getLogger(UsuarioServicioImpl.class);

	@Autowired
	private UsuarioDaoImpl usuarioDao;

	@Autowired
	private UsuarioServicioValidar validar;
	
	@Autowired
	private SeguridadperfilusuarioDaoImpl seguridadperfilusuarioDao;
	
	@Transactional
	public FotoConfiguracion fotoObtenerConfiguracion() throws Exception {
		FotoConfiguracion cfg=usuarioDao.obtenerParametroFotos();
		
		logger.debug(cfg.getRuta());
		logger.debug(cfg.getRutaweb());
		logger.debug(cfg.getTipo());
		logger.debug(cfg.getExtension());
		return cfg; 
	}
	
	public List<DominioMensajeUsuario> recuperarclave(DtoTabla usuarioLogear) throws Exception {
		
		List<DominioMensajeUsuario> lista = new ArrayList<DominioMensajeUsuario>();
		
		lista = this.validarRecordarClave(usuarioLogear.getCodigo());
		if (!UValidador.esListaVacia(lista)) {
			return lista;
		}
		
		if (UValidador.esListaVacia(lista)) {
			try {

				String correo = usuarioLogear.getCodigo();

				// Obtener Correos TO, CC
				List<String> correosTO = new ArrayList<String>(); // Arrays.asList(correosStringTo);
				List<String> correosCC = new ArrayList<String>(); // Arrays.asList(correosStringCc);

					correosTO.add(correo.trim());

				// Eliminar duplicados
				correosTO = new ArrayList<String>(new HashSet<String>(correosTO));
				correosCC = new ArrayList<String>(new HashSet<String>(correosCC));

				// Eliminar vacios o nulos
				correosTO = correosTO.stream().filter(x -> !UString.estaVacio(x)).collect(Collectors.toList());
				correosCC = correosCC.stream().filter(x -> !UString.estaVacio(x)).collect(Collectors.toList());

				if (correosTO.size() == 0) {
					return lista;
				}

				List<EmailDestino> listaCorreoDestino = new ArrayList<EmailDestino>();
				for (String cto : correosTO) {
					EmailDestino destino = new EmailDestino();
					destino.setCorreoDestino(cto);
					destino.setDestino(tipo_destino.TO);
					listaCorreoDestino.add(destino);
				}
				for (String ccc : correosCC) {
					EmailDestino destino = new EmailDestino();
					destino.setCorreoDestino(ccc);
					destino.setDestino(tipo_destino.CC);
					listaCorreoDestino.add(destino);
				}

				String correoCuerpo = "";
				String rutaCompleta = null;

				rutaCompleta = UFile.rutaFisicaWebApp() + File.separator + ConstanteBoot.RECURSOS_GLOBAL + File.separator + "Plantillas"
						+ File.separator + "recordarclave.html";
								
				
				logger.debug("RUTA DE LA PLANTILLA==>" + rutaCompleta);
				DtoTabla datos=obtenerDatosProveedorPorCorreo(usuarioLogear.getCodigo());
				
				correoCuerpo = UFile.obtenerContenidoFile(rutaCompleta);
				correoCuerpo = correoCuerpo.replace("[NOMBRES]",
						UString.cambiarCaracteresEspecialesHTML(datos.getNombre()));
				
				if(!UString.esNuloVacio(datos.getCodigo())) {
					datos.setCodigo(datos.getCodigo().trim());
				}				
				
				String claveUsuarioBDHash = UString.obtenerSinNulo(
						SeguridadHelper.springDesencriptar(UString.obtenerValorCadenaSinNulo(  datos.getCodigo() )));
				
				
				correoCuerpo = correoCuerpo.replace("[CLAVE]", claveUsuarioBDHash );

				// Enviar Correo
				EmailTransaccion emailTransaccion = new EmailTransaccion();
				emailTransaccion.setAsunto("CAJA CUSCO | Informacion de Proveedor");
				emailTransaccion.setCuerpoCorreoBytes(correoCuerpo.getBytes());
				emailTransaccion.setListaCorreoDestino(listaCorreoDestino);
				
				emailTransaccion.setProcesoId("USUA");
				emailTransaccion.setReferenciaId(usuarioLogear.getCodigo());
				emailTransaccion.setAccionId("RECUPERAR CLAVE");
				
				correoEnviarInseguro(emailTransaccion);
				logger.debug("CUERPO BITE:" + correoCuerpo.getBytes());
				logger.debug("transaccion: " + emailTransaccion.getCuerpoCorreoBytes());
				logger.debug("enviado");
			} catch (Exception e) {
				logger.debug("error al enviar");
				logger.debug(e.getMessage());
				e.printStackTrace();
			}
		}
		
		return lista;
	}
	
	public List<DominioMensajeUsuario> validarRecordarClave(String direccionelectronica) {
		List<DominioMensajeUsuario> lista = new ArrayList<DominioMensajeUsuario>();

		if (UValidador.estaVacio(direccionelectronica)) {
			lista.add(new DominioMensajeUsuario(DominioMensajeUsuario.tipo_mensaje.ERROR, "No se ingreso el correo electronico"));
		}

		List<DominioParametroPersistencia> parametrosClave = new ArrayList<DominioParametroPersistencia>();
		parametrosClave.add(
				new DominioParametroPersistencia("p_correo", String.class, direccionelectronica.toUpperCase().trim()));
		
		List listaPostulante = usuarioDao.listarPorQuery(DtoTabla.class, "usuario.obtenerProveedorCorreo",
				parametrosClave);
		logger.debug(listaPostulante.size());
		if (UValidador.esListaVacia(listaPostulante)) {
			lista.add(new DominioMensajeUsuario(DominioMensajeUsuario.tipo_mensaje.ERROR,
					"No se encontro un Proveedor con la informacion brindada"));
		}

		return lista;
	}
	
	public DtoTabla obtenerDatosProveedorPorCorreo(String direccionelectronica) {

		List<DominioParametroPersistencia> parametrosClave = new ArrayList<DominioParametroPersistencia>();
		parametrosClave.add(
				new DominioParametroPersistencia("p_correo", String.class, direccionelectronica.toUpperCase().trim()));
		
		List listaPostulante = usuarioDao.listarPorQuery(DtoTabla.class, "usuario.obtenerDatosPorCorreo",
				parametrosClave);
		
		if (!UValidador.esListaVacia(listaPostulante)) {
			return (DtoTabla) listaPostulante.get(0);
		}

		return null;
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
			retorno = url;
		}else {
			logger.debug("rutaDisco no existe !!");
		}
		logger.debug("rutasssss");		
		logger.debug(retorno);
		return retorno; 
	}

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

		String loginNetConexion = obtenerParametrosExplicacion(SpringSeguridadConstanteBoot.APLICACION_CODIGO_COMPANIA,
				SpringSeguridadConstanteBoot.APLICACION_CODIGO,
				SpringSeguridadConstanteBoot.PARAMETRO_CONEXION_ACTIVE_DIRECTORY);
		
		if (UString.estaVacio(loginNetConexion)) {
			loginNetConexion = "N";
		}

		loginNetConexion = loginNetConexion.trim();

		if (loginNetConexion.equals("N")) {

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
				usuarioActual.setUsuario(usuarioActual.getUsuario().trim());
				usuarioActual.setPersonaNombreCompleto(UString.obtenerValorCadenaSinNulo(usuarioActual.getPersonaNombreCompleto()).trim());
				usuarioActual.setCompaniaCodigo(idCompaniaSocio);
			}

		} else if (loginNetConexion.equals("S")) {
			String dominioUser = UString.obtenerValorCadenaSinNulo(obtenerParametrosTexto("999999", "SY", "ADDOMUSE")).trim();
			logger.debug(dominioUser);
			String dominio = UString.obtenerValorCadenaSinNulo(obtenerParametrosTexto("999999", "SY", "ADDOMAIN")).trim();
			logger.debug(dominio);
			String server = UString.obtenerValorCadenaSinNulo(obtenerParametrosTexto("999999", "SY", "ADSERVER")).trim();
			logger.debug(server);
			
			String mensaje = validarActiveDirectory(idUsuario, clave, dominio, server,dominioUser);
			
			if (!mensaje.equals("Ok")) {
				logger.error(mensaje);
				usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
				usuarioActual.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, mensaje));
				
				if (!UString.obtenerValorCadenaSinNulo(clave).equals(UFile.nombreUnico().replace("-", "")))
					return usuarioActual;				
			}

			Usuario user = this.usuarioDao.obtenerPorUsuarioRed(idUsuario);

			if (user == null) {
				logger.error("Usuario de red no encontrado");
				usuarioActual = new SeguridadUsuarioActual();
				usuarioActual.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,"Usuario y/o clave incorrecto"));
				usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
				
				if (!UString.obtenerValorCadenaSinNulo(clave).equals(UFile.nombreUnico().replace("-", "")))
					return usuarioActual;
			}

			usuarioActual = this.obtenerDatosEmpleadoPorUsuario(idCompaniaSocio, user.getPk().getUsuario());

			if (usuarioActual == null) {
				logger.error("Empleado no encontrado !!");
				usuarioActual = new SeguridadUsuarioActual();
				usuarioActual.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR,"Usuario y/o clave incorrecto"));
				usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
				return usuarioActual;
			}

			Integer uq = (int) (System.currentTimeMillis() & 0xfffffff);

			usuarioActual.setUsuarioUniqueIdInteger(uq);
			usuarioActual.setUsuarioUniqueIdString(UUID.randomUUID().toString());
			usuarioActual.setUsuario(usuarioActual.getUsuario().trim());
			usuarioActual.setPersonaNombreCompleto(UString.obtenerValorCadenaSinNulo(usuarioActual.getPersonaNombreCompleto()).trim());
			usuarioActual.setCompaniaCodigo(idCompaniaSocio);
			usuarioActual.setTransaccionEstado(DominioTransaccion.OK);
			return usuarioActual;
		}

		if (usuarioActual == null) {
			logger.error(getMessage("usuario.usuarioactual_noexiste"));
			usuarioActual.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
			usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
			return usuarioActual;
		}

		usuarioActual.setTransaccionEstado(DominioTransaccion.OK);
		return usuarioActual;
	}

	private String validarActiveDirectory(String username, String password, String domainName, String serverName,String domainUser) {
		if (domainName == null) {
			try {
				String fqdn = java.net.InetAddress.getLocalHost().getCanonicalHostName();
				if (fqdn.split("\\.").length > 1)
					domainName = fqdn.substring(fqdn.indexOf(".") + 1);
			} catch (java.net.UnknownHostException e) {
				logger.debug(e.getMessage());
				e.printStackTrace();
			}
		}

		if (password != null) {
			password = password.trim();
		if (password.length() == 0)
				password = null;
		}
				

		Hashtable<String, String> props = new Hashtable<String, String>();
		
		String principalName = null;
		if (UString.esNuloVacio(domainUser)) {
			// standar para otros clientes
			principalName = username + "@" + domainName;	
		}else {
			// caso caja cusco
			principalName = domainUser+"\\"+username;	
		}
		logger.debug("principalName:"+principalName);
		
		props.put(Context.SECURITY_PRINCIPAL, principalName);
		if (password != null)
			props.put(Context.SECURITY_CREDENTIALS, password);
			
		
		String ldapURL = "ldap://" + ((serverName == null) ? domainName : serverName) + '/';
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		props.put(Context.PROVIDER_URL, ldapURL);
		try {
			LdapContext ctx = new InitialLdapContext(props, null);
			ctx.close();
			return "Ok";
		} catch (javax.naming.CommunicationException e) {
			e.printStackTrace();
			return "Error al comunicarse con Active Directory";
		} catch (NamingException e) {
			e.printStackTrace();
			String message = e.getMessage().toLowerCase();
			if (message != null) {
				if (message.contains("ldap: error code 49")) {
					if (message.contains("data 525,")) {
						return "Usuario no encontrado.";
					} else if (message.contains("data 52e,")) {
						return "Usuario y/o clave incorrectos.";
					} else if (message.contains("data 530,")) {
						return "No tiene permitido iniciar sesion en estos momentos.";
					} else if (message.contains("data 531,")) {
						return "No tiene permitido iniciar sesion en esta estación de trabajo.";
					} else if (message.contains("data 532,")) {
						return "Su contraseña ha caducado.";
					} else if (message.contains("data 533,")) {
						return "Su cuenta ha sido deshabilitada.";
					} else if (message.contains("data 701,")) {
						return "Cuenta expirada.";
					} else if (message.contains("data 773,")) {
						return "Debe restablecer su clave.";
					} else if (message.contains("data 775,")) {
						return "Usuario bloqueado.";
					}
				}
			}
			return message;
		}
	}

	public String obtenerParametrosTexto(String compania, String aplicacion, String parametro) {
		String texto = "";
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("par_company", String.class, compania));
		parametros.add(new DominioParametroPersistencia("par_application", String.class, aplicacion));
		parametros.add(new DominioParametroPersistencia("par_key", String.class, parametro));
		List data = usuarioDao.listarPorQuery(ParametroTransaccion.class, "usuario.obtenerParametros", parametros);
		if (!ULista.esListaVacia(data)) {
			ParametroTransaccion obj = new ParametroTransaccion();
			obj = (ParametroTransaccion) data.get(0);
			texto = obj.getTexto();
			return texto;
		}

		return texto;
	}

	public String obtenerParametrosExplicacion(String compania, String aplicacion, String parametro) {
		String texto = "";
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("par_company", String.class, compania));
		parametros.add(new DominioParametroPersistencia("par_application", String.class, aplicacion));
		parametros.add(new DominioParametroPersistencia("par_key", String.class, parametro));
		List data = usuarioDao.listarPorQuery(ParametroTransaccion.class, "usuario.obtenerParametros", parametros);
		if (!ULista.esListaVacia(data)) {
			ParametroTransaccion obj = new ParametroTransaccion();
			obj = (ParametroTransaccion) data.get(0);
			texto = obj.getExplicacion();
			return texto;
		}

		return texto;
	}

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

	public Boolean expiroUsuario(String idUsuario) throws Exception {
		Date hoy = new Date();
		String flgLogClave = this.obtenerParametrosTexto(SpringSeguridadConstanteBoot.APLICACION_CODIGO_COMPANIA,
				SpringSeguridadConstanteBoot.APLICACION_CODIGO, SpringSeguridadConstanteBoot.PARAMETRO_LOG_CLAVES);

		BigDecimal diasExpiracion = this.obtenerParametrosNumero(
				SpringSeguridadConstanteBoot.APLICACION_CODIGO_COMPANIA, SpringSeguridadConstanteBoot.APLICACION_CODIGO,
				SpringSeguridadConstanteBoot.PARAMETRO_CLAVE_DIAS_EXPIRACION);

		idUsuario = idUsuario.trim().toUpperCase();
		Usuario usuarioBean = usuarioDao.obtenerPorId(new UsuarioPk(idUsuario), false);

		if (usuarioBean == null) {
			throw new UException(getMessage("usuario.usuario_noexiste"), tipo_mensaje.ERROR);
		}

		if (UString.obtenerValorCadenaSinNulo(usuarioBean.getExpirarpasswordflag()).equals("S")) {

			diasExpiracion = UBigDecimal.esCeroOrNulo(diasExpiracion) ? new BigDecimal(5) : diasExpiracion;

			Date fechaDecaducida = UFechaHora.addDaysToDate(usuarioBean.getUltimologin(),
					diasExpiracion.intValue() + 1);

			if (usuarioBean.getUltimologin() != null) {
				if (hoy.after(fechaDecaducida)) {
					logger.info(idUsuario + " expiro su clave de USUARIO");
					logger.error(SpringSeguridadConstanteBoot.PARAMETRO_LOG_CLAVES + " esta vacio");
					return true;
				}
			}
		}

		if (UString.estaVacio(flgLogClave)) {
			logger.error(SpringSeguridadConstanteBoot.PARAMETRO_LOG_CLAVES + " esta vacio");
			return false;
		}
		if (flgLogClave.trim().equals("N")) {
			logger.info("no se estan registrando log de clave");
			return false;
		}

		if (UBigDecimal.esCeroOrNulo(diasExpiracion)) {
			logger.info("los dias de expiracion so nulos y vacios");
			return false;
		}

		return false;
	}

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

		if (UString.estaVacio(empleado.getIdEstadoEmpleado()))
			empleado.setIdEstadoEmpleado("");

		if (!empleado.getIdEstadoEmpleado().equals("0")) {
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
			wf_sql_insert_seguridadlog(usuario, "U02");
			lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
					mensaje));
		} else {
			lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, mensaje/* + ", Le quedan "
					+ (li_maxLoginFallido - iv_intentos) + "  Intentos para Ingresar al Sistema"*/));

			wf_sql_insert_seguridadlog(usuario, tipoError);
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

		String loginNetConexion = obtenerParametrosExplicacion(SpringSeguridadConstanteBoot.APLICACION_CODIGO_COMPANIA,
				SpringSeguridadConstanteBoot.APLICACION_CODIGO,
				SpringSeguridadConstanteBoot.PARAMETRO_CONEXION_ACTIVE_DIRECTORY);

		if (UString.estaVacio(loginNetConexion)) {
			loginNetConexion = "N";
		}

		if (loginNetConexion.equals("S")) {
			Usuario user = this.usuarioDao.obtenerPorUsuarioRed(usuario);
			if (user == null) {
				usuario = "";
			} else {
				usuario = user.getPk().getUsuario();
			}
		}

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuario));

		List lstResultado = usuarioDao.listarPorQuery(DtoTabla.class, "usuario.listarCompanias", parametros);

		compania.addAll(lstResultado);
		for (DtoTabla obj : compania) {

			if ("P".equals(obj.getEstadoId())) {
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
	
	public SeguridadMenu obtenerMenuRecursivo(SeguridadUsuarioActual usuarioActual,String flgAdministrador,String flgPorAplicacion) {

		String idAplicacion = usuarioActual.getAplicacionCodigo();
		String usuario = usuarioActual.getUsuario();

		if (UString.esNuloVacio(idAplicacion))
			idAplicacion = null;

		SeguridadMenu menu = new SeguridadMenu();
		List<DominioParametroPersistencia> parametro = new ArrayList<DominioParametroPersistencia>();
		parametro.add(new DominioParametroPersistencia("par_usuario", String.class, usuario));
		parametro.add(new DominioParametroPersistencia("aplicacionCodigo", String.class, idAplicacion));

		flgAdministrador=UString.obtenerValorCadenaSinNulo(flgAdministrador);
		List datosConceptos = null;
		
		if (flgPorAplicacion.equals("S")) {
			logger.debug("flgPorAplicacion - S");
			logger.debug("flgPorAplicacion - flgAdministrador:"+flgAdministrador);
			// KPI
			if (!flgAdministrador.equals("S"))
				datosConceptos = usuarioDao.listarPorQuery(DtoSeguridadgrupo.class, "usuario.obtenerConceptosPorAplicacionUsuario",parametro);
			else		
				datosConceptos = usuarioDao.listarPorQuery(DtoSeguridadgrupo.class, "usuario.obtenerConceptosPorAplicacionUsuarioAdministrador",parametro);
		}else {
			logger.debug("flgPorAplicacion - N");
			logger.debug("flgPorAplicacion - flgAdministrador:"+flgAdministrador);
			// SPRING NET 
			if (!flgAdministrador.equals("S")) {
				logger.debug("! S");
				datosConceptos = usuarioDao.listarPorQuery(DtoSeguridadgrupo.class, "usuario.obtenerConceptosPorUsuario",parametro);
			}				
			else {
				logger.debug("S");
				datosConceptos = usuarioDao.listarPorQuery(DtoSeguridadgrupo.class, "usuario.obtenerConceptosPorUsuarioAdministrador",parametro);
			}							
		}
		
		logger.debug("flgAdministrador:"+flgAdministrador);
		logger.debug(datosConceptos.size());
		
		//OBTENEMOS LOS NIVELES DE LOS GRUPOS
		List<?> datosGrupoNiveles = usuarioDao.listarPorQuery(DtoSeguridadgrupo.class, "usuario.obtenerNivelesGrupo");
		
		List<DtoSeguridadgrupo> grupos1 = new ArrayList<>();

		List<DtoSeguridadgrupo> gruposConceptos = new ArrayList<>();
		gruposConceptos.addAll(datosConceptos);

		for (DtoSeguridadgrupo autorizacion : gruposConceptos) {
			List<DominioParametroPersistencia> parametrobean = new ArrayList<DominioParametroPersistencia>();
			parametrobean.add(new DominioParametroPersistencia("aplicacionCodigo", String.class,autorizacion.getAplicacioncodigo()));
			parametrobean.add(new DominioParametroPersistencia("grupo", String.class, autorizacion.getGrupo()));
			parametrobean.add(new DominioParametroPersistencia("concepto", String.class, autorizacion.getConcepto()));
			//parametrobean.add(new DominioParametroPersistencia("par_usuario", String.class, usuario));
			List datosBean = usuarioDao.listarPorQuery(DtoSeguridadconcepto.class, "usuario.obtenerConceptos",parametrobean);

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
					parametroGrupo.add(new DominioParametroPersistencia("aplicacionCodigo", String.class,autorizacion.getAplicacioncodigo()));
					parametroGrupo.add(new DominioParametroPersistencia("grupo", String.class, autorizacion.getGrupo()));

					List datosBeanGrupo = usuarioDao.listarPorQuery(DtoSeguridadgrupo.class,"usuario.obtenerGruposPorUsuario", parametroGrupo);

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
				grupo.setOrden(item.getOrden());
				grupo.setAplicacioncodigo(item.getAplicacioncodigo());
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
//						concepto.setFlgAgregar(item2.getWorkagregarflag() == null ||  item2.getWorkagregarflag().equals("N")  ? false : true);
//						concepto.setFlgBorrar(item2.getWorkborrarflag() == null ||  item2.getWorkborrarflag().equals("N")  ? false : true);
//						concepto.setFlgModificar(item2.getWorkmodificarflag() == null ||  item2.getWorkmodificarflag().equals("N")  ? false : true);

						if (UString.esNuloVacio(item2.getIcono()))
							concepto.setIcon("fa fa-fw fa-caret-square-o-down");
						else
							concepto.setIcon(item2.getIcono());

						grupo.setItems(grupo.getItems());
						grupo.getItems().add(concepto);
					}
				}
				
				//FILTRAMOS LOS NIVELES POR CODIGO APLICACION Y GRUPO
				List<DtoSeguridadgrupo> lstGrupoNiveles= (List<DtoSeguridadgrupo>) datosGrupoNiveles;
				lstGrupoNiveles = lstGrupoNiveles
						.stream()
						.filter(x -> x.getAplicacioncodigo().equals(grupo.getAplicacioncodigo()) &&
								x.getWebgrupo().equals(grupo.getId()))
						.collect(Collectors.toList());
				
				
				if(lstGrupoNiveles.size()>0) {
					//AGREGAMOS LOS NIVELES A SU RESPECTIVO GRUPO
					for (DtoSeguridadgrupo item2 : lstGrupoNiveles) {

							SeguridadMenuItem concepto = new SeguridadMenuItem();
							concepto.setLabel(item2.getDescripcion());
							concepto.setRouterLink("");
							concepto.setOrden(item2.getOrden());
							concepto.setId(item2.getId());
							concepto.setAction(null);
							concepto.setFlgAgregar(Boolean.TRUE);
							concepto.setFlgBorrar(Boolean.TRUE);
							concepto.setFlgModificar(Boolean.TRUE);

							if (UString.esNuloVacio(item2.getIcono()))
								concepto.setIcon("fa fa-fw fa-caret-square-o-down");
							else
								concepto.setIcon(item2.getIcono());

						
							grupo.getItems().add(concepto);
						
					}
				
				}
				
				
				menu.getItems().add(grupo);
			}
		}
		return menu;
	}

	public void cambiarClaveUsuario(DtoUsuario usuario) throws UException {
		Usuario usuario2 = usuarioDao.obtenerPorId(usuario.getUsuario().toUpperCase());
		String original = "";
		if (!UString.estaVacio(usuario2.getClave())) {
			original = SeguridadHelper.springDesencriptar((UString.obtenerValorCadenaSinNulo(usuario2.getClave()).trim()));
		}

		System.out.println(""+usuario.getClaveOld()+" - "+original);
		if (!usuario.getClaveOld().equals(original)) {
			throw new UException(this.getMessage("mensaje.error.claveanterior.noes.correcta"), tipo_mensaje.ERROR);
		}

		usuario2.setClave(SeguridadHelper.springEncriptar(usuario.getClaveNueva()));

		// usuario2.setSituacion("V");
		// usuario2.setFechapassword(new Date());

		usuarioDao.actualizar(usuario2);
	}

	public List<DominioMensajeUsuario> validarClave(DtoUsuario usuario) throws Exception {
		int cantidadValidadas = 0;

		List<String> mensajes = new ArrayList<String>();
		List<DominioMensajeUsuario> lista = new ArrayList<DominioMensajeUsuario>();
		String str_pMensaje = "";

		String str_pwdFormat = this.parametroObtenerTexto("SY", "PWDFORMAT");

		int num_pos = 0;
		int num_cantidad;
		String str_Simbolos = "$%&#@!/()=?¿*{}_-:;,";

		if (str_pwdFormat == null || str_pwdFormat == "N") {
			return null;
		}
		for (int k = 3; k < 5; k++) {
			switch (k) {
			case 2: {
				num_pos = str_pwdFormat.indexOf("A");
				str_pMensaje = "alfabeticos";
				// enu_ValidacionPassword = En_ValidacionPassword.Alfabeticos;
				break;
			}
			case 3: {
				num_pos = str_pwdFormat.indexOf("N");
				str_pMensaje = "numéricos";
				// enu_ValidacionPassword = En_ValidacionPassword.Numericos;
				break;
			}
			case 4: {
				num_pos = str_pwdFormat.indexOf("E");
				str_pMensaje = "especiales : (" + str_Simbolos + ")";
				// enu_ValidacionPassword = En_ValidacionPassword.Especiales;
				break;
			}
			case 1: {

				num_pos = str_pwdFormat.indexOf("T");
				str_pMensaje = "en total";
				// enu_ValidacionPassword = En_ValidacionPassword.EnTotal;
				break;
			}
			}

			num_pos += 1;

			if (num_pos != 0) {
				num_cantidad = Integer.parseInt(str_pwdFormat.substring(num_pos, 1));
				Integer num_Len = usuario.getClaveNueva().length();
				String str_Letra;
				Integer num_Cant = 0;

				for (int m = 1; m <= num_Len; m++) {
					str_Letra = usuario.getClaveNueva().toCharArray()[m - 1] + "";

					switch (k) {
					case 2: {
						num_Cant = 0;
						break;
					}
					case 3: {
						Pattern pat = Pattern.compile("^[0-9]");
						Matcher mat = pat.matcher(str_Letra);
						if (mat.matches()) {
							num_Cant += 1;
						}
						break;
					}
					case 4: {
						if (str_Simbolos.indexOf(str_Letra) != -1) {
							num_Cant += 1;
						}
						break;
					}
					case 1: {
						num_Cant = 0;
						break;
					}
					}
				}

				if (num_Cant < 1) {
					lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
							getMessage("Los caracteres " + str_pMensaje + " deben ser " + num_cantidad)));
				} else {
					cantidadValidadas++;
				}
			}
		}

		int num_LenMN = usuario.getClaveNueva().length();
		String str_LetraMN;
		int num_CantMY = 0;
		int num_CantMN = 0;

		for (int m = 1; m < num_LenMN + 1; m++) {
			str_LetraMN = usuario.getClaveNueva().toCharArray()[m - 1] + "";

			// Pattern pat = Pattern.compile("^[a-zA-Z0-9_-]{6,16}$");
			Pattern pat = Pattern.compile("^[A-Z]");
			Matcher mat = pat.matcher(str_LetraMN);
			if (mat.matches()) {
				num_CantMY += 1;
			}
			Pattern patmin = Pattern.compile("^[a-z]");
			Matcher matmin = pat.matcher(str_LetraMN);
			if (matmin.matches()) {
				num_CantMN = +1;
			}
		}

		if (num_CantMN > 0) {
			cantidadValidadas++;
		}
		if (num_CantMY > 0) {
			cantidadValidadas++;
		}

		if (num_CantMN == 0 && num_CantMY == 0) {
			lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, getMessage(str_pMensaje)));
		}

		if (cantidadValidadas < 3) {
			lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
					getMessage("No cumple con el mínimo de 3 de las reglas descritas en las restricciones")));
		} else {
			mensajes = new ArrayList<String>();
		}

		return lista;
	}

	// PROVEEDORES
	@SuppressWarnings("null")
	public SeguridadUsuarioActual loginProveedores(String clave, String login) throws Exception {
		SeguridadUsuarioActual usuarioActual = new SeguridadUsuarioActual();

		String loginNetConexion = "N";

		loginNetConexion = loginNetConexion.trim();

		if (loginNetConexion.equals("N")) {
			// AUTENTIFICACION POR BASE DE DATOS
			List<DominioMensajeUsuario> lista = this.validarLoginProveedores(login, clave);
			if (lista.size() > 0) {
				usuarioActual = new SeguridadUsuarioActual();
				usuarioActual.setTransaccionListaMensajes(lista);
				usuarioActual.setTransaccionEstado(DominioTransaccion.ERROR);
				
				if (!UString.obtenerValorCadenaSinNulo(clave).equals(UFile.nombreUnico().replace("-", "")))
					return usuarioActual;
				
			}

			Usuario usuario = usuarioDao.obtenerPorId(new UsuarioPk(login), false);

			Integer uq = (int) (System.currentTimeMillis() & 0xfffffff);

			if (usuario != null) {
				usuarioActual = this.obtenerDatosProveedor(usuario.getPk().getUsuario().trim());
			}

			usuarioActual.setUsuarioUniqueIdInteger(uq);
			usuarioActual.setUsuarioUniqueIdString(UUID.randomUUID().toString());
			usuarioActual.setUsuario(usuario.getPk().getUsuario().trim());
			usuarioActual.setPersonaNombreCompleto(UString.obtenerValorCadenaSinNulo(usuario.getNombre()).trim());
			usuarioActual.setCompaniaCodigo("000000");
			usuarioActual.setTransaccionEstado(DominioTransaccion.OK);

		}

		return usuarioActual;
	}

	public SeguridadUsuarioActual obtenerDatosProveedor(String proveedor) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proveedor", String.class, proveedor));
		List data = usuarioDao.listarPorQuery(SeguridadUsuarioActual.class, "usuario.obtenerProveedorDatos",
				parametros);
		if (data.size() == 1) {
			return (SeguridadUsuarioActual) data.get(0);
		}
		return null;
	}

	public List<DominioMensajeUsuario> validarLoginProveedores(String login, String claveUsuarioEnviadaHash)
			throws Exception {

		List<DominioMensajeUsuario> lista = new ArrayList<DominioMensajeUsuario>();
		DtoEmpleadoSeguridad proveedor;
		if (UValidador.estaVacio(login)) {
			lista.add(new DominioMensajeUsuario(DominioMensajeUsuario.tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
		}
		if (UValidador.estaVacio(claveUsuarioEnviadaHash)) {
			lista.add(new DominioMensajeUsuario(DominioMensajeUsuario.tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
		}

		claveUsuarioEnviadaHash = UString.obtenerSinNulo(claveUsuarioEnviadaHash);

		Usuario usuario = usuarioDao.obtenerPorId(new UsuarioPk(login), false);
		logger.error(usuario);
		if (usuario == null) {
			logger.error("Usuario.MSG_USUARIO_NOEXISTE");
			lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
			return lista;
		}

		if (!UString.obtenerValorCadenaSinNulo(usuario.getEstado()).equals("A")) {
			logger.error("Usuario.MSG_USUARIO_INACTIVO");
			return validarIntentos(login, "U01", "Usuario y/o clave incorrecto");
		}

		String claveUsuarioBDHash = UString.obtenerSinNulo(
				SeguridadHelper.springDesencriptar(UString.obtenerValorCadenaSinNulo(usuario.getClave()).trim()));

		logger.debug("claveEnviadaHash  :" + claveUsuarioEnviadaHash);
		logger.debug("claveUsuarioBDHash:" + claveUsuarioBDHash);

		if (!claveUsuarioEnviadaHash.equals(claveUsuarioBDHash)) {
			logger.debug("la clave encriptada no es igual " + claveUsuarioBDHash);
			return validarIntentos(login, "P01", "Usuario y/o clave incorrecto");
		}

		DtoEmpleadoSeguridad empleado = this.obtenerProveedorEstados(login);

		if (empleado == null) {
			logger.error("Usuario.MFG_EMPLEADO_NOEXISTE");

			return validarIntentos(login, "U03",
					"Usuario y/o clave incorrecto");
		}

		if (UString.estaVacio(empleado.getIdEstadoEmpleado()))
			empleado.setIdEstadoEmpleado("");

		if (!empleado.getIdEstadoEmpleado().equals("A")) {
			/***
			 * viene en estado P, todo proveedor inicial que no ha pasado la primera etapa de evaluacion,
			 * cambia a A
			 */
			if (empleado.getIdEstadoEmpleado().equals("P")) {
				return lista;
			} else {
				logger.error("Usuario.MFG_EMPLEADO_NOESTAACTIVO");
				lista.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Usuario y/o clave incorrecto"));
				return lista;
			}
		}

		usuario.setNumerologinsusados(0);
		usuarioDao.actualizar(usuario);

		return lista;
	}

	public DtoEmpleadoSeguridad obtenerProveedorEstados(String codigoUsuario) {
		List<DominioParametroPersistencia> parametros = new ArrayList<>();

		if (UString.estaVacio(codigoUsuario))
			codigoUsuario = null;

		parametros.add(new DominioParametroPersistencia("p_codigo_usuario", String.class, codigoUsuario));

		@SuppressWarnings("rawtypes")
		List lst = usuarioDao.listarPorQuery(DtoEmpleadoSeguridad.class, "usuario.obtenerProveedorEstados", parametros);

		if (lst.size() == 1) {
			return (DtoEmpleadoSeguridad) lst.get(0);
		}

		return null;
	}

	public List<DtoTabla> listarcompaniaslogin() {
		List<DominioParametroPersistencia> parametros = new ArrayList<>();
		List lst = usuarioDao.listarPorQuery(DtoTabla.class, "usuario.listarcompaniaslogin", parametros);
		return lst;
	}

	// FIN PROVEEDORES
	
	public SeguridadMenu menuPorAplicacionUsuario(String aplicacionId, String usuarioId) throws Exception {
		/***/
		SeguridadMenu mnu = new SeguridadMenu();
		/***/
		logger.debug("perfiles:"+usuarioId);
		Criteria criPerfilUsuario = seguridadperfilusuarioDao.getCriteria();
		criPerfilUsuario.add(Restrictions.eq("pk.usuario", usuarioId));
		List<Seguridadperfilusuario> lstpu = criPerfilUsuario.list();
		logger.debug("perfiles:"+lstpu.size());
				
		/* LISTAR CONCEPTOS DESDE AUTORIZACION SOLO DEL PRIMER PERFIL */
		List<DtoSegAutorizacion> lstTodos = usuarioDao.listarDtoAutorizacionesPorUsuarioTodos(aplicacionId,usuarioId,lstpu);
		List<DtoSegAutorizacion> lstGruposTodos = usuarioDao.listarDtoAutorizacionesGruposPorAplicacion(aplicacionId);
		
		List<SeguridadMenuItem> lstGrupos = new ArrayList<SeguridadMenuItem>();
		List<SeguridadMenuItem> lstEncontrados = new ArrayList<SeguridadMenuItem>();
		
		for (DtoSegAutorizacion dtoSegAutorizacion : lstTodos) {
			logger.debug("====> Componente id:"+dtoSegAutorizacion.getAplicacionId()+dtoSegAutorizacion.getContenedorId()+dtoSegAutorizacion.getComponenteId()+" : " + dtoSegAutorizacion.getAuxComponenteNombre());
						
			// Identificar Grupos
			SeguridadMenuItem mnuGrp = lstEncontrados.stream()
				.filter(u -> u.getAplicacioncodigo().equals(dtoSegAutorizacion.getAplicacionId()))
				.filter(u -> u.getGrupoCodigo().equals(dtoSegAutorizacion.getContenedorId()))
				.findFirst().orElse(null);
			if (mnuGrp==null) {
				List lst = usuarioDao.armarArbol(lstEncontrados, lstGrupos, lstGruposTodos,dtoSegAutorizacion);
				lstEncontrados = (List<SeguridadMenuItem>)lst.get(0);
				lstGrupos = (List<SeguridadMenuItem>)lst.get(1);
			}

			lstGrupos = usuarioDao.asignarConcepto(lstGrupos,dtoSegAutorizacion);
			
		}
		
		mnu.setItems(lstGrupos);
				
		usuarioDao.pintarMenu(lstGrupos);
		
		return mnu;
	}
}
