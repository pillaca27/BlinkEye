package net.royal.spring.sistema.servicio.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.plexus.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;
import net.royal.spring.core.dao.impl.TipocambiomastDaoImpl;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBigDecimal;
import net.royal.spring.framework.util.UPropiedades;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyDocumentoanexosDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyDocumentoanexos;
import net.royal.spring.sistema.dominio.BeanSyDocumentoanexosPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyDocumentoCabecera;
import net.royal.spring.sistema.dominio.dto.DtoComunSyDocumentoanexos;
import net.royal.spring.sistema.servicio.validar.SyDocumentoanexosServicioValidar;

@Service(value = "BeanServicioComunSyDocumentoanexosMotor")
public class SyDocumentoanexosMotorServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioComunSyDocumentoanexosMotor";
	private static Logger logger = LogManager.getLogger(SyDocumentoanexosMotorServicioImpl.class);

	@Autowired
	private SyDocumentoanexosDaoImpl syDocumentoanexosDao;

	@Autowired
	private SyDocumentoanexosServicioValidar validar;

	@Autowired
	private ParametrosmastDaoImpl parametrosmastDao;

	@Autowired
	private TipocambiomastDaoImpl tipocambiomastDaoImpl;

	public String rutaTemporal() throws IOException {
		String recursoRuta = UFile.rutaFisicaWebServer() + File.separator + ConstanteBoot.CARPETA_PROPERTIES;
		Properties recursoPropiedad = UPropiedades.getInstance().abrir(recursoRuta, ConstanteBoot.PROPERTIES_GLOBAL);
		return recursoPropiedad.getProperty("ruta.fisica.temporal");
	}

	@Transactional
	public DtoComunSyDocumentoanexos registrarTemporal(SeguridadUsuarioActual usuarioActual, DtoComunSyDocumentoanexos dto)
			throws Exception {

		// validaciones basicas
		if (dto == null) {
			dto = new DtoComunSyDocumentoanexos();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00edo dto"));
			return dto;
		}
		if (UString.estaVacio(dto.getDescripcion())) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00edo t\u00edtulo de archivo"));
			return dto;
		}
		if (UString.estaVacio(dto.getArchivo())) {
			dto.getTransaccionListaMensajes().add(this.getMsjUsuarioError(
					"No se env\u00eado nombre de archivo") /*
															 * new DominioMensajeUsuario(tipo_mensaje.ERROR,
															 * "No se env\u00eado nombre de archivo")
															 */);
			return dto;
		}
		if (UString.estaVacio(dto.getArchivostring())) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00eado archivo adjunto"));
			return dto;
		}

		// funcionalidad
		// byte[] archivo = dto.getArchivostring().getBytes();
		byte archivo[] = Base64.getDecoder().decode(dto.getArchivostring());

		String rutaTmp = rutaTemporal();
		String idsession = usuarioActual.getUsuarioUniqueIdString();
		String rutaarchivo = idsession + File.separator + dto.getArchivo();
		String rutaCompleta = rutaTmp + File.separator + rutaarchivo;

		File fl = new File(rutaCompleta);
		fl.mkdirs();
		if (fl.exists()) {
			fl.delete();
		}

		UFile.guardarContenidoFileByte(rutaCompleta, archivo);

		dto.setAuxFlgNuevo("S");
		dto.setAuxRutaTemporal(rutaarchivo);
		dto.setArchivostring(null);
		logger.debug(rutaCompleta);
		logger.debug(rutaarchivo);
		return dto;
	}

	@Transactional
	public DtoComunSyDocumentoanexos actualizarTemporal(SeguridadUsuarioActual usuarioActual, DtoComunSyDocumentoanexos dto)
			throws Exception {
		// validaciones basicas
		if (dto == null) {
			dto = new DtoComunSyDocumentoanexos();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00edo dto"));
			return dto;
		}
		if (UString.estaVacio(dto.getDescripcion())) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00eado t\u00edtulo de archivo"));
			return dto;
		}
		if (UString.estaVacio(dto.getArchivo())) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00eado nombre de archivo"));
			return dto;
		}
		if (UString.estaVacio(dto.getArchivostring())) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00eado archivo adjunto"));
			return dto;
		}

		// funcionalidad
		// byte[] archivo = dto.getArchivostring().getBytes();
		byte archivo[] = Base64.getDecoder().decode(dto.getArchivostring());

		String rutaTmp = rutaTemporal();
		String idsession = usuarioActual.getUsuarioUniqueIdString();

		String nombreArchivo = dto.getDescripcion().replace("/", " ");

		dto.setDescripcion(nombreArchivo);

		String rutaarchivo = idsession + File.separator + dto.getDescripcion();
		String rutaCompleta = rutaTmp + File.separator + rutaarchivo;

		File fl = new File(rutaCompleta);
		fl.mkdirs();
		if (fl.exists()) {
			fl.delete();
		}

		UFile.guardarContenidoFileByte(rutaCompleta, archivo);

		dto.setAuxFlgEditar("S");
		dto.setAuxRutaTemporal(rutaarchivo);
		dto.setArchivostring(null);
		logger.debug(rutaarchivo);
		return dto;
	}

	@Transactional
	public DtoComunSyDocumentoanexos eliminarTemporal(SeguridadUsuarioActual usuarioActual, DtoComunSyDocumentoanexos dto)
			throws Exception {
		dto.setAuxFlgEliminar("S");
		dto.setAuxFlgNoConsiderarValidacion("N");
		dto.setArchivostring(null);
		dto.setArchivo(null);
		// dto.setDescripcion(null);
		return dto;
	}

	public String rutaRaiz() throws IOException {
		String r = parametrosmastDao.obtenerParametroExplicacion("DIRFILE ", "SY");
		return r;
	}

	private String rutaEstructura(DtoComunSyDocumentoCabecera dto) throws IOException {
		String r = dto.getCompaniasocio();
		r = r + File.separator + dto.getModulo() + File.separator + dto.getTipodocumento();
		r = r + File.separator + dto.getNumerodocumento() + File.separator + dto.getLinea().toString();
		return r;
	}

	@Transactional
	public List<DominioMensajeUsuario> validar(SeguridadUsuarioActual usuarioActual, DtoComunSyDocumentoanexos dto)
			throws Exception {
		List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
		String s = UString.obtenerValorCadenaSinNulo(dto.getDescripcion());
		if (UString.estaVacio(dto.getDescripcion())) {
			// lst.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00eado
			// t\u00edtulo de archivo ("+s+")"));
			lst.add(this.getMsjUsuarioError("No se envio t\u00edtulo de archivo (" + s + ")"));
		}

		if (dto.getAuxFlgAdjuntoObligatorio().equals("S")) {

			if (UString.estaVacio(dto.getAuxRutaTemporal()) && UString.estaVacio(dto.getRutaarchivo())) {
				lst.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Debe adjuntar " + s + ""));
				return lst;
			}
		}

		if (UString.estaVacio(dto.getArchivo())) {
			// lst.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00eado
			// nombre de archivo ("+s+")"));
			lst.add(this.getMsjUsuarioError("No se envio nombre de archivo  " + s + " "));
		}

		return lst;
	}

	@Transactional
	public DtoComunSyDocumentoCabecera procesarAdjuntos(SeguridadUsuarioActual usuarioActual, DtoComunSyDocumentoCabecera dto)
			throws Exception {

		dto.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());

		if (!UString.esNuloVacio(dto.getNumerocontrato())) {
			return dto;
		}

		// preparacion
		String rutaTmp = rutaTemporal();
		String rutaRaiz = rutaRaiz();
		String rutaEstructura = null;

		if (dto.getLinea() != null && !"0".equals(dto.getNumerodocumento())) {

			rutaEstructura = rutaEstructura(dto);

			logger.debug("rutaTmp:" + rutaTmp);
			logger.debug("rutaRaiz:" + rutaRaiz);
			logger.debug("rutaEstructura:" + rutaEstructura);

			File f2 = new File(rutaRaiz + File.separator + rutaEstructura);

			// if(dto.getModulo().equals("WH") && dto.getTipodocumento().equals("RQ")) {
			// f2 = new File(rutaRaiz + File.separator + "WH");
			// rutaEstructura = rutaRaiz + File.separator + "WH";
			// }

			f2.mkdirs();

		} else {
			dto.setLinea(-1);
		}

		// validar
		if (UString.esNuloVacio(dto.getCompaniasocio()))
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta compa\u00f1ia"));
		if (UString.esNuloVacio(dto.getModulo()))
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta m\u00f3dulo"));
		if (UString.esNuloVacio(dto.getTipodocumento()))
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta tipo documento"));
		if (UString.esNuloVacio(dto.getNumerodocumento()))
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta nro documento"));
		// se coordino con Edgar para comentar esta validacion
//		if (dto.getLinea() < 0)
//			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta linea"));
		if (dto.getTransaccionListaMensajes().size() > 0) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}
		int contador = 0;
		for (DtoComunSyDocumentoanexos bean : dto.getListaDocumentos()) {
			bean.evaluarEstadoRegistro();
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Registrar)) {
				List<DominioMensajeUsuario> c = validar(usuarioActual, bean);
				dto.getTransaccionListaMensajes().addAll(c);
				contador++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Actualizado)) {
				List<DominioMensajeUsuario> c = validar(usuarioActual, bean);
				dto.getTransaccionListaMensajes().addAll(c);
				contador++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Eliminado)) {
				contador++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Ninguno)) {
			}
		}
		if (dto.getTransaccionListaMensajes().size() > 0) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		// procesar
		DtoComunSyDocumentoanexos dto2 = new DtoComunSyDocumentoanexos();
		dto2.setCompaniasocio(dto.getCompaniasocio());
		dto2.setModulo(dto.getModulo());
		dto2.setTipodocumento(dto.getTipodocumento());
		dto2.setNumerodocumento(dto.getNumerodocumento());
		dto2.setLinea(dto.getLinea());

		int secuencia = syDocumentoanexosDao.generarSecuencia(dto2);

		logger.debug(secuencia);
		logger.debug(dto.getListaDocumentos().size());

		for (DtoComunSyDocumentoanexos bean : dto.getListaDocumentos()) {

			logger.debug(bean.getArchivo());

			logger.debug(bean.getAuxFlgNuevo());
			logger.debug(bean.getAuxFlgEditar());
			logger.debug(bean.getAuxFlgEliminar());

			bean.evaluarEstadoRegistro();
			bean.setCompaniasocio(dto.getCompaniasocio());
			bean.setModulo(dto.getModulo());
			bean.setTipodocumento(dto.getTipodocumento());
			bean.setNumerodocumento(dto.getNumerodocumento());
			bean.setLinea(dto.getLinea());

			logger.debug("AuxEstadoRegistro:" + bean.getAuxEstadoRegistro());
			logger.debug("AuxFlgEliminar:" + bean.getAuxFlgEliminar());

			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Registrar)
					&& bean.getAuxFlgEliminar().equals("N")) {
				logger.debug("detalle.getArchivo() insertar:" + bean.getArchivo());
				BeanSyDocumentoanexos detalle = bean.obtenerBean();
				detalle.setRutaarchivo(rutaEstructura + File.separator + detalle.getArchivo());

				// if(dto.getModulo().equals("WH") && dto.getTipodocumento().equals("RQ")) {
				// detalle.setRutaarchivo(rutaEstructura + File.separator +
				// detalle.getArchivo());
				// }

				detalle.getPk().setSecuencia(secuencia);
				detalle.setUltimafechamodif(new Date());
				detalle.setUltimousuario(usuarioActual.getUsuario());
				syDocumentoanexosDao.coreInsertar(detalle);

				String rutaCompletaTemp = rutaTmp + File.separator + bean.getAuxRutaTemporal();
				String rutaCompletaFinal = rutaRaiz + File.separator + rutaEstructura + File.separator
						+ detalle.getArchivo();

				// if(dto.getModulo().equals("WH") && dto.getTipodocumento().equals("RQ")) {
				// rutaCompletaFinal = rutaEstructura + File.separator + detalle.getArchivo();
				// }

				FileUtils.copyFile(new File(rutaCompletaTemp.trim()), new File(rutaCompletaFinal.trim()));
				FileUtils.fileDelete(rutaCompletaTemp.trim());
				secuencia++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Actualizado)
					&& bean.getAuxFlgEliminar().equals("N")) {
				logger.debug("detalle.getArchivo() actualiz:" + bean.getArchivo());

				String rutaArchivo = bean.getRutaarchivo();

				BeanSyDocumentoanexos detalle = bean.obtenerBean();
				detalle.setRutaarchivo(rutaEstructura + File.separator + detalle.getArchivo());

				// if(dto.getModulo().equals("WH") && dto.getTipodocumento().equals("RQ")) {
				// detalle.setRutaarchivo(rutaEstructura + File.separator +
				// detalle.getArchivo());
				// }

				detalle.setUltimafechamodif(new Date());
				detalle.setUltimousuario(usuarioActual.getUsuario());
				syDocumentoanexosDao.coreActualizar(detalle);

				String rutaCompletaTemp = rutaTmp + File.separator + bean.getAuxRutaTemporal();

				String rutaCompletaFinal = rutaRaiz + File.separator + rutaEstructura + File.separator
						+ detalle.getArchivo();

				// if(dto.getModulo().equals("WH") && dto.getTipodocumento().equals("RQ")) {
				// rutaCompletaFinal = rutaEstructura + File.separator + detalle.getArchivo();
				// }

				if (UString.estaVacio(bean.getAuxRutaTemporal()) && !UString.estaVacio(rutaArchivo)) {
					rutaCompletaTemp = rutaArchivo;
					rutaCompletaFinal = rutaArchivo;
				}

				FileUtils.copyFile(new File(rutaCompletaTemp.trim()), new File(rutaCompletaFinal.trim()));

				if (UString.estaVacio(bean.getAuxRutaTemporal()) && !UString.estaVacio(rutaArchivo)) {

				} else {
					FileUtils.fileDelete(rutaCompletaTemp.trim());
				}

			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Eliminado)) {
				logger.debug("detalle.getArchivo() elminar:" + bean.getArchivo());

				BeanSyDocumentoanexos detalle = syDocumentoanexosDao.obtenerPorId(
						new BeanSyDocumentoanexosPk(bean.getCompaniasocio(), bean.getModulo(), bean.getTipodocumento(),
								bean.getNumerodocumento(), bean.getLinea(), bean.getSecuencia()),
						false);

				if (!UValidador.esNulo(detalle)) {
					syDocumentoanexosDao.eliminar(detalle);

					String rutaCompletaTemp = rutaTmp + File.separator + bean.getAuxRutaTemporal();
					String rutaCompletaFinal = rutaRaiz + File.separator + File.separator + detalle.getRutaarchivo();

					// if(dto.getModulo().equals("WH") && dto.getTipodocumento().equals("RQ")) {
					// rutaCompletaFinal = rutaEstructura + File.separator + detalle.getArchivo();
					// }

					try {
						FileUtils.fileDelete(rutaCompletaTemp.trim());
						FileUtils.fileDelete(rutaCompletaFinal.trim());
					} catch (Exception e) {
						logger.debug("Ruta no existe");
						logger.debug(rutaCompletaTemp);
						logger.debug(rutaCompletaFinal);
						logger.debug(e.getMessage());
					}
				}

			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Ninguno)) {
				logger.debug("detalle.getArchivo() ninguno:" + bean.getArchivo());
				// genTablaDetalleDao.coreInsertar(detalle);
			}
		}
		return dto;
	}

	public List<DominioMensajeUsuario> elementosRepetidos(List<DtoComunSyDocumentoanexos> list,
			List<DominioMensajeUsuario> listaErrores) {

		for (int i = 0; i < list.size(); i++) {

			if ("S".equals(list.get(i).getAuxFlgEliminar())) {
				continue;
			}

			for (int j = i + 1; j < list.size(); j++) {

				if ("S".equals(list.get(i).getAuxFlgEliminar())) {
					continue;
				}

				if (!UString.esNuloVacio(list.get(i).getArchivo()) && !UString.esNuloVacio(list.get(j).getArchivo())) {
					if (list.get(i).getArchivo().equals(list.get(j).getArchivo())) {
						listaErrores.add(new DominioMensajeUsuario(tipo_mensaje.ERROR,
								"El Archivo " + list.get(j).getArchivo() + " se encuentra repetido"));
					}
				}
			}
		}
		return listaErrores;
	}

	@Transactional
	public DtoComunSyDocumentoCabecera validarProcesaradjuntos(SeguridadUsuarioActual usuarioActual,
			DtoComunSyDocumentoCabecera dto) throws Exception {

		dto.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());

		if (!UString.esNuloVacio(dto.getNumerocontrato())) {
			return dto;
		}

		if (!UValidador.esListaVacia(elementosRepetidos(dto.getListaDocumentos(), dto.getTransaccionListaMensajes()))) {
			return dto;
		}

		// preparacion
		String rutaTmp = rutaTemporal();
		String rutaRaiz = rutaRaiz();
		String rutaEstructura = null;

		if (dto.getLinea() != null) {

			rutaEstructura = rutaEstructura(dto);

			logger.debug("rutaTmp:" + rutaTmp);
			logger.debug("rutaRaiz:" + rutaRaiz);
			logger.debug("rutaEstructura:" + rutaEstructura);

			File f2 = new File(rutaRaiz + File.separator + rutaEstructura);

			f2.mkdirs();

		} else {
			dto.setLinea(-1);
		}

		// validar

		if (dto.getModulo().equals("WH") && dto.getTipodocumento().equals("RQ")) {

			BigDecimal montoInicial = BigDecimal.ZERO, montoFinal = BigDecimal.ZERO;

			List<DtoComunSyDocumentoanexos> filtroLista = dto.getListaDocumentos().stream()
					.filter(d -> "S".equals(d.getValidarporrango())).collect(Collectors.toList());

			if (!UValidador.esListaVacia(filtroLista)) {

				for (DtoComunSyDocumentoanexos obligatorios : filtroLista) {
					montoInicial = UBigDecimal.esCeroOrNulo(obligatorios.getMontorango1inicio())
							? new BigDecimal(100000)
							: obligatorios.getMontorango1inicio();
					montoFinal = UBigDecimal.esCeroOrNulo(obligatorios.getMontorango1fin())
							? new BigDecimal(999999999.00)
							: obligatorios.getMontorango1fin();

					if (!UString.esNuloVacio(dto.getMonedadocumento())) {
						if ("EX".equals(dto.getMonedadocumento())) {

							DtoComunTipocambiomast tipocambioParam = new DtoComunTipocambiomast();
							tipocambioParam.setFechacambio(new Date());
							tipocambioParam.setAuxRateType("V");

							BigDecimal tipocambio = tipocambiomastDaoImpl.obtenerFactor(tipocambioParam);

							montoInicial = UBigDecimal.dividir(montoInicial, tipocambio, 6);
							montoFinal = UBigDecimal.dividir(montoFinal, tipocambio, 6);

						}
					}

					if (!"EX".equals(dto.getSistemacontratacion())) {

						if (UBigDecimal.esMayorQue(dto.getPreciototal(), montoInicial)
								&& UBigDecimal.esMenorQue(dto.getPreciototal(), montoFinal)) {
							dto.getListaDocumentos().stream().filter((p) -> "S".equals(p.getValidarporrango())
									&& !"N".equals(p.getAuxFlgNoConsiderarValidacion())).map(temp -> {
										temp.setAuxFlgEliminar("N");
										temp.setAuxFlgAdjuntoObligatorio("S");
										return temp;
									}).findAny().orElse(null);
						} else {
							dto.getListaDocumentos().stream().filter((p) -> "S".equals(p.getValidarporrango())
									&& !"N".equals(p.getAuxFlgNoConsiderarValidacion())).map(temp -> {
										if (UString.esNuloVacio(temp.getArchivo())) {
											temp.setAuxFlgEliminar("S");
											temp.setAuxFlgAdjuntoObligatorio("N");
										}

										return temp;
									}).findAny().orElse(null);
						}
					}
				}
			}

//
//			if (!UString.esNuloVacio(dto.getMonedadocumento())) {
//				if ("EX".equals(dto.getMonedadocumento())) {
//
//					DtoComunTipocambiomast tipocambioParam = new DtoComunTipocambiomast();
//					tipocambioParam.setFechacambio(new Date());
//					tipocambioParam.setAuxRateType("V");
//
//					BigDecimal tipocambio = tipocambiomastDaoImpl.obtenerFactor(tipocambioParam);
//
//					montorequerimiento = UBigDecimal.dividir(montorequerimiento, tipocambio, 6);
//
//				}
//			}
//
//			if (!"EX".equals(dto.getSistemacontratacion())) {
//
//				if (UBigDecimal.esMayorQue(dto.getPreciototal(), montorequerimiento)) {
//					dto.getListaDocumentos().stream().filter((p) -> 0 == p.getSecuencia() && !"N".equals( p.getAuxFlgNoConsiderarValidacion())).map(temp -> {
//						temp.setAuxFlgEliminar("N");
//						temp.setAuxFlgAdjuntoObligatorio("S");
//						return temp;
//					}).findAny().orElse(null);
//				} else {
//					dto.getListaDocumentos().stream().filter((p) -> 0 == p.getSecuencia()  && !"N".equals( p.getAuxFlgNoConsiderarValidacion())).map(temp -> {
//						if (UString.esNuloVacio(temp.getArchivo())) {
//							temp.setAuxFlgEliminar("S");
//							temp.setAuxFlgAdjuntoObligatorio("N");
//						}
//
//						return temp;
//					}).findAny().orElse(null);
//				}
//			}

		}

		if (UString.esNuloVacio(dto.getCompaniasocio()))
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta compa\u00f1ia"));
		if (UString.esNuloVacio(dto.getModulo()))
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta m\u00f3dulo"));
		if (UString.esNuloVacio(dto.getTipodocumento()))
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta tipo documento"));
		if (UString.esNuloVacio(dto.getNumerodocumento()))
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta nro documento"));
		if (dto.getLinea() < 0)
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta linea"));

		if (dto.getTransaccionListaMensajes().size() > 0) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		int contador = 0;
		for (DtoComunSyDocumentoanexos bean : dto.getListaDocumentos()) {

			bean.evaluarEstadoRegistro();
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Registrar)) {
				List<DominioMensajeUsuario> c = validar(usuarioActual, bean);
				dto.getTransaccionListaMensajes().addAll(c);
				contador++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Actualizado)) {
				List<DominioMensajeUsuario> c = validar(usuarioActual, bean);
				dto.getTransaccionListaMensajes().addAll(c);
				contador++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Eliminado)) {
				contador++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Ninguno)) {
			}
		}
		if (dto.getTransaccionListaMensajes().size() > 0) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		return dto;
	}

	@Transactional
	public DtoComunSyDocumentoCabecera procesarAdjuntosOrdenCompra(SeguridadUsuarioActual usuarioActual,
			DtoComunSyDocumentoCabecera dto) throws Exception {

		// preparacion
		String rutaTmp = rutaTemporal();
		String rutaPrincipal = rutaRaiz();
		String rutaRaiz = rutaRaiz() + File.separator + dto.getModulo();
		String rutaEstructura = rutaRaiz;
		logger.debug("rutaTmp:" + rutaTmp);
		logger.debug("rutaRaiz:" + rutaRaiz);
		logger.debug("rutaEstructura:" + rutaEstructura);

		File f2 = new File(rutaRaiz + File.separator + rutaEstructura);
		f2.mkdirs();

		if (dto.getLinea() == null)
			dto.setLinea(-1);

		// validar
		if (UString.esNuloVacio(dto.getCompaniasocio()))
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta compa\u00f1ia"));
		if (UString.esNuloVacio(dto.getModulo()))
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta m\u00f3dulo"));
		if (UString.esNuloVacio(dto.getTipodocumento()))
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta tipo documento"));
		if (UString.esNuloVacio(dto.getNumerodocumento()))
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta nro documento"));
		if (dto.getLinea() < 0)
			dto.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "falta linea"));
		if (dto.getTransaccionListaMensajes().size() > 0) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}
		int contador = 0;
		for (DtoComunSyDocumentoanexos bean : dto.getListaDocumentos()) {
			bean.evaluarEstadoRegistro();
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Registrar)) {
				List<DominioMensajeUsuario> c = validar(usuarioActual, bean);
				dto.getTransaccionListaMensajes().addAll(c);
				contador++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Actualizado)) {
				List<DominioMensajeUsuario> c = validar(usuarioActual, bean);
				dto.getTransaccionListaMensajes().addAll(c);
				contador++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Eliminado)) {
				contador++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Ninguno)) {
			}
		}
		if (dto.getTransaccionListaMensajes().size() > 0) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		// procesar
		DtoComunSyDocumentoanexos dto2 = new DtoComunSyDocumentoanexos();
		dto2.setCompaniasocio(dto.getCompaniasocio());
		dto2.setModulo(dto.getModulo());
		dto2.setTipodocumento(dto.getTipodocumento());
		dto2.setNumerodocumento(dto.getNumerodocumento());
		dto2.setLinea(dto.getLinea());

		int secuencia = syDocumentoanexosDao.generarSecuencia(dto2);

		logger.debug(secuencia);
		logger.debug(dto.getListaDocumentos().size());

		for (DtoComunSyDocumentoanexos bean : dto.getListaDocumentos()) {

			logger.debug(bean.getArchivo());

			logger.debug(bean.getAuxFlgNuevo());
			logger.debug(bean.getAuxFlgEditar());
			logger.debug(bean.getAuxFlgEliminar());

			bean.evaluarEstadoRegistro();
			bean.setCompaniasocio(dto.getCompaniasocio());
			bean.setModulo(dto.getModulo());
			bean.setTipodocumento(dto.getTipodocumento());
			bean.setNumerodocumento(dto.getNumerodocumento());
			bean.setLinea(dto.getLinea());
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Registrar)) {
				logger.debug("detalle.getArchivo() insertar:" + bean.getArchivo());
				BeanSyDocumentoanexos detalle = bean.obtenerBean();

				detalle.getPk().setSecuencia(secuencia);
				detalle.setUltimafechamodif(new Date());
				detalle.setUltimousuario(usuarioActual.getUsuario());
				String archivo = detalle.getPk().getCompaniasocio().trim() + File.separator
						+ detalle.getPk().getModulo().trim() + File.separator
						+ detalle.getPk().getTipodocumento().trim() + File.separator
						+ detalle.getPk().getNumerodocumento().trim() + File.separator + detalle.getPk().getLinea()
						+ File.separator + detalle.getArchivo().trim();

				detalle.setRutaarchivo(archivo);
				detalle.setComentario(archivo);

				syDocumentoanexosDao.coreInsertar(detalle);

				String rutaCompletaTemp = rutaTmp + File.separator + bean.getAuxRutaTemporal();
				String rutaCompletaFinal = rutaPrincipal + File.separator + detalle.getRutaarchivo();
				FileUtils.copyFile(new File(rutaCompletaTemp), new File(rutaCompletaFinal));
				logger.debug(rutaCompletaTemp);
				logger.debug(rutaCompletaFinal);
				FileUtils.fileDelete(rutaCompletaTemp);
				secuencia++;
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Actualizado)) {
				logger.debug("detalle.getArchivo() actualiz:" + bean.getArchivo());
				BeanSyDocumentoanexos detalle = bean.obtenerBean();
				String archivoanterior = detalle.getRutaarchivo();
				String archivo = detalle.getPk().getCompaniasocio().trim() + File.separator
						+ detalle.getPk().getModulo().trim() + File.separator
						+ detalle.getPk().getTipodocumento().trim() + File.separator
						+ detalle.getPk().getNumerodocumento().trim() + File.separator + detalle.getPk().getLinea()
						+ File.separator + detalle.getArchivo().trim();

				detalle.setRutaarchivo(archivo);
				detalle.setComentario(archivo);
				detalle.setUltimafechamodif(new Date());
				detalle.setUltimousuario(usuarioActual.getUsuario());

				// obtener archivo anterior
				syDocumentoanexosDao.coreActualizar(detalle);

				String rutaCompletaTemp = rutaTmp + File.separator + bean.getAuxRutaTemporal();
				String rutaCompletaFinal = rutaPrincipal + File.separator + detalle.getRutaarchivo();
				FileUtils.copyFile(new File(rutaCompletaTemp), new File(rutaCompletaFinal));
				FileUtils.fileDelete(rutaCompletaTemp);

				String rutaCompletaAnterior = rutaPrincipal + File.separator + archivoanterior;
				FileUtils.fileDelete(rutaCompletaAnterior);
			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Eliminado)) {
				logger.debug("detalle.getArchivo() elminar:" + bean.getArchivo());
				BeanSyDocumentoanexos detalle = bean.obtenerBean();
				syDocumentoanexosDao.eliminar(detalle);

				String idsession = usuarioActual.getUsuarioUniqueIdString();
				bean.setAuxRutaTemporal(idsession);

				Path rutaArchivo = Paths.get(rutaPrincipal).resolve(detalle.getRutaarchivo()).toAbsolutePath();
				File archivo = rutaArchivo.toFile();
				if (archivo.exists() && archivo.canRead()) {
					archivo.delete();
				}

				Path rutaArchivoAnterior = Paths.get(bean.getAuxRutaTemporal()).resolve(detalle.getArchivo())
						.toAbsolutePath();
				File archivoAnterior = rutaArchivoAnterior.toFile();
				if (archivoAnterior.exists() && archivoAnterior.canRead()) {
					archivoAnterior.delete();
				}

			}
			if (bean.getAuxEstadoRegistro().equals(DominioTransaccion.EstadoRegistro.Ninguno)) {
				logger.debug("detalle.getArchivo() ninguno:" + bean.getArchivo());
				// genTablaDetalleDao.coreInsertar(detalle);
			}
		}
		return dto;
	}

	@Transactional
	public DtoComunSyDocumentoCabecera procesaradjuntoscopy(SeguridadUsuarioActual usuarioActual, DtoComunSyDocumentoCabecera dto)
			throws Exception {

		// preparacion
		String modulo = "WH";
		String tipodocumento = dto.getTipodocumento();
		String rutaAnterior = rutaRaiz();
		String rutaRaiz = rutaRaiz() + File.separator + modulo;
		String rutaEstructura = rutaRaiz;

		logger.debug("rutaRaiz:" + rutaRaiz);
		logger.debug("rutaEstructura:" + rutaEstructura);

		File f2 = new File(rutaRaiz + File.separator + rutaEstructura);
		f2.mkdirs();

		if (dto.getLinea() == null)
			dto.setLinea(-1);

		// procesar
		DtoComunSyDocumentoanexos dto2 = new DtoComunSyDocumentoanexos();
		dto2.setCompaniasocio(dto.getCompaniasocio());
		dto2.setModulo(modulo);
		dto2.setTipodocumento(tipodocumento);
		dto2.setNumerodocumento(dto.getNumerodocumento());
		dto2.setLinea(dto.getLinea());

		int secuencia = syDocumentoanexosDao.generarSecuencia(dto2);

		logger.debug(secuencia);
		logger.debug(dto.getListaDocumentos().size());

		for (DtoComunSyDocumentoanexos bean : dto.getListaDocumentos()) {

			bean.setCompaniasocio(dto.getCompaniasocio());
			bean.setModulo(modulo);
			bean.setTipodocumento(tipodocumento);
			bean.setNumerodocumento(dto.getNumerodocumento());
			bean.setLinea(dto.getLinea());

			logger.debug("detalle.getArchivo() insertar:" + bean.getArchivo());
			BeanSyDocumentoanexos detalle = bean.obtenerBean();

			detalle.setComentario(bean.getComentario());
			detalle.getPk().setSecuencia(secuencia);
			detalle.setUltimafechamodif(new Date());
			detalle.setUltimousuario(usuarioActual.getUsuario());
			detalle.getPk().setCompaniasocio(usuarioActual.getCompaniaCodigo());
			detalle.getPk().setLinea(0);

			String archivo = detalle.getPk().getCompaniasocio().trim() + File.separator + modulo + File.separator
					+ detalle.getPk().getTipodocumento().trim() + File.separator
					+ detalle.getPk().getNumerodocumento().trim() + File.separator + detalle.getPk().getLinea()
					+ File.separator + detalle.getArchivo().trim();
			detalle.setRutaarchivo(archivo.trim());
			syDocumentoanexosDao.coreInsertar(detalle);

			String rutacopiar = rutaAnterior + File.separator + bean.getRutaarchivo().trim();
			Path rutaArchivo = Paths.get(rutacopiar);
			File archivo_anexos = rutaArchivo.toFile();
			if (archivo_anexos.exists() && archivo_anexos.canRead()) {
				String rutaCompletaTemp = rutaAnterior + bean.getRutaarchivo().trim();
				String rutaCompletaFinal = rutaAnterior + File.separator + detalle.getPk().getCompaniasocio().trim()
						+ File.separator + modulo + File.separator + detalle.getPk().getTipodocumento().trim()
						+ File.separator + detalle.getPk().getNumerodocumento().trim() + File.separator
						+ detalle.getPk().getLinea() + File.separator + detalle.getArchivo().trim();

				logger.debug(rutaCompletaTemp);
				logger.debug(rutaCompletaFinal);
				if (!rutaCompletaTemp.equals(rutaCompletaFinal.trim())) {
					FileUtils.copyFile(new File(rutaCompletaTemp), new File(rutaCompletaFinal));
				}
			}

			secuencia++;

		}
		return dto;
	}

	// ARMAS LISTADO
	public List<DtoComunSyDocumentoanexos> listarPorCabecera(DtoComunSyDocumentoCabecera filtro, SeguridadUsuarioActual usuario)
			throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getNumerodocumento())) {
			filtro.setNumerodocumento(usuario.getUsuarioUniqueIdString());
		}

		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_modulo", String.class, filtro.getModulo()));
		parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getTipodocumento()));
		parametros
				.add(new DominioParametroPersistencia("p_numerodocumento", String.class, filtro.getNumerodocumento()));
		parametros.add(new DominioParametroPersistencia("p_linea", Integer.class, filtro.getLinea()));

		List datos = syDocumentoanexosDao.listarPorQuery(DtoComunSyDocumentoanexos.class,
				"sydocumentoanexos.listarPorCabecera", parametros);
		logger.debug(datos.size());
		return datos;
	}
	// FIN LISTADO

	// LUKE LISTADO
	public List<DtoComunSyDocumentoanexos> listarPorCabeceraWh(DtoComunSyDocumentoCabecera filtro, SeguridadUsuarioActual usuario)
			throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getNumerodocumento())) {
			filtro.setNumerodocumento(usuario.getUsuarioUniqueIdString());
		}

		parametros.add(new DominioParametroPersistencia("p_companiasocio", String.class, filtro.getCompaniasocio()));
		parametros.add(new DominioParametroPersistencia("p_modulo", String.class, filtro.getModulo()));
		parametros.add(new DominioParametroPersistencia("p_tipodocumento", String.class, filtro.getTipodocumento()));
		parametros
				.add(new DominioParametroPersistencia("p_numerodocumento", String.class, filtro.getNumerodocumento()));

		List datos = syDocumentoanexosDao.listarPorQuery(DtoComunSyDocumentoanexos.class,
				"sydocumentoanexos.listarPorCabeceraWh", parametros);
		logger.debug(datos.size());
		return datos;
	}
	// FIN LISTADO

	@Transactional
	public DtoComunSyDocumentoanexos validarArchivo(SeguridadUsuarioActual usuarioActual, DtoComunSyDocumentoanexos dto)
			throws Exception {
		// validaciones basicas

		// validaciones basicas
		if (dto == null) {
			dto = new DtoComunSyDocumentoanexos();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00edo dto"));
			return dto;
		}

		if (UString.estaVacio(dto.getDescripcion())) {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se env\u00edo el titulo del adjunto."));
			return dto;
		}

		if (dto.getArchivostring() != null) {
			// funcionalidad
			byte[] archivo = dto.getArchivostring().getBytes();

			String rutaTmp = rutaTemporal();
			String idsession = usuarioActual.getUsuarioUniqueIdString();

			String nombreArchivo = dto.getDescripcion().replace("/", " ");

			dto.setDescripcion(nombreArchivo);

			String rutaarchivo = idsession + File.separator + dto.getDescripcion();
			String rutaCompleta = rutaTmp + File.separator + rutaarchivo;

			String rutaRaiz = rutaRaiz();

//			DtoSyDocumentoCabecera obj= new DtoSyDocumentoCabecera();
//			obj.setModulo(dto.getModulo());
//			obj.setTipodocumento(dto.getTipodocumento());
//			obj.setNumerodocumento(dto.getNumerodocumento());
//			obj.setLinea(dto.getLinea());
//			String rutaEstructura = rutaEstructura(obj);

			String rutaEstructura = "";

			String rutaCompletaFinal = rutaRaiz + File.separator + rutaEstructura + File.separator + dto.getArchivo();

			// if(dto.getModulo().equals("WH") && dto.getTipodocumento().equals("RQ")) {
			// rutaCompletaFinal = rutaEstructura + File.separator + dto.getArchivo();
			// }

			File fl = new File(rutaCompletaFinal);
			if (fl.exists()) {
				dto.setTransaccionEstado(DominioTransaccion.OK);
				dto.setAuxConfirmar("S");
				dto.setAuxConfirmarMensaje(
						"El archivo seleccionado ya existe en el repositorio destino. Desea reemplazar el archivo existente?");
				return dto;
			}
		}

		return dto;
	}

}
