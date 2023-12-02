package net.royal.spring.core.rest;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.BeanTipocambiomastPk;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.core.dominio.dto.DtoComunTipodocumentocxp;
import net.royal.spring.core.dominio.filtro.FiltroComunTipocambiomast;
import net.royal.spring.core.dominio.lista.DtlComunTipocambiomast;
import net.royal.spring.core.servicio.impl.PersonaSunatServicioImpl;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.PersonaSunatTransaccion;
import net.royal.spring.framework.modelo.TipoCambioSunatTransaccion;
import net.royal.spring.framework.modelo.TipoCambioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/personasunat")
@CrossOrigin(origins = "*")
public class PersonaSunatComunRest {

	@Autowired
	private PersonaSunatServicioImpl servicio;
	
	@PutMapping(value = "/buscarporruc", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PersonaSunatTransaccion> buscarPorRuc(@RequestBody PersonaSunatTransaccion filtro) throws Exception {
		
		if(servicio.puedeConsultarRuc().equals("S")) {
			try {
				filtro = obtenerDatosXRuc(filtro.getRuc());	
			} catch (Exception e) {
				filtro.setTransaccionEstado(DominioTransaccion.ERROR);
				filtro.getTransaccionListaMensajes().add(new DominioMensajeUsuario(e));
			}			
		}
		
		
		return new ResponseEntity<PersonaSunatTransaccion>(filtro, HttpStatus.OK); 
	}
	
	public static PersonaSunatTransaccion obtenerDatosXRuc(String ruc) throws Exception {

		String textoAleatorio = "IMPORTANTE LAS PALABRAS CLAVES DEBE SER ALEATORIO EXISTIR LETRAS Y ESTAR EN MAYUSCULA COMO RANDOM UAP UPC LIMA HOLA MUNDO COMO ESTAS TEST"
				.toUpperCase();

		String[] arrNombreAleatorio = textoAleatorio.split(" ");

		int nPalabra = getRandomNumber(0, arrNombreAleatorio.length);

		String url = "https://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/jcrS00Alias?accion=consPorRazonSoc&razSoc="
				+ arrNombreAleatorio[nPalabra];

		Response res = Jsoup.connect(url).method(Method.GET).execute();

		Document doc = res.parse();

		String contenidoHTML = doc.toString();

		url = "https://e-consultaruc.sunat.gob.pe/cl-ti-itmrconsruc/jcrS00Alias?accion=consPorRuc&actReturn=1&nroRuc="
				+ ruc + "&numRnd=" + ExtraerContenidoEntreTagString(contenidoHTML, 0, "name=\"numRnd\" value=\"", "\">")
				+ "&modo=1";

		doc = Jsoup.connect(url).cookies(res.cookies()).get();

		contenidoHTML = doc.toString();

		PersonaSunatTransaccion dto = ObtenerDatos(contenidoHTML);

		dto.setRuc(ruc);

		return dto;
	}
	
	private static PersonaSunatTransaccion ObtenerDatos(String contenidoHTML) {
		PersonaSunatTransaccion oDatosRUC = new PersonaSunatTransaccion();
		String nombreInicio = "<HEAD><TITLE>";
		String nombreFin = "</TITLE></HEAD>";

		String contenidoBusqueda = ExtraerContenidoEntreTagString(contenidoHTML, 0, nombreInicio, nombreFin);

		if (contenidoBusqueda == ".:: Pagina de Mensajes ::.") {
			nombreInicio = "<p class=\"error\">";
			nombreFin = "</p>";
			//oDatosRUC.setTipoRespuesta(2);
			//oDatosRUC.setMensajeRespuesta(ExtraerContenidoEntreTagString(contenidoHTML, 0, nombreInicio, nombreFin));
			String str = ExtraerContenidoEntreTagString(contenidoHTML, 0, nombreInicio, nombreFin);
			oDatosRUC.setTransaccionEstado(DominioTransaccion.ERROR);
			oDatosRUC.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, str));
		} else if (contenidoBusqueda == ".:: Pagina de Error ::.") {
			nombreInicio = "<p class=\"error\">";
			nombreFin = "</p>";
			//oDatosRUC.setTipoRespuesta(2);
			//oDatosRUC.setMensajeRespuesta(ExtraerContenidoEntreTagString(contenidoHTML, 0, nombreInicio, nombreFin));
			String str = ExtraerContenidoEntreTagString(contenidoHTML, 0, nombreInicio, nombreFin);
			oDatosRUC.setTransaccionEstado(DominioTransaccion.ERROR);
			oDatosRUC.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, str));
		} else {
			//oDatosRUC.setTipoRespuesta(2);
			oDatosRUC.setTransaccionEstado(DominioTransaccion.ERROR);
			nombreInicio = "<div class=\"list-group\">";
			nombreFin = "<div class=\"panel-footer text-center\">";
			contenidoBusqueda = ExtraerContenidoEntreTagString(contenidoHTML, 0, nombreInicio, nombreFin);

			if (contenidoBusqueda == "") {
				nombreInicio = "<strong>";
				nombreFin = "</strong>";
				//oDatosRUC.setMensajeRespuesta(ExtraerContenidoEntreTagString(contenidoHTML, 0, nombreInicio, nombreFin));
				String str = ExtraerContenidoEntreTagString(contenidoHTML, 0, nombreInicio, nombreFin);
				oDatosRUC.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, str));
				if (str == "") {
					//oDatosRUC.setMensajeRespuesta("No se encuentra las cabeceras principales del contenido HTML");
					oDatosRUC.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "No se encuentra las cabeceras principales del contenido HTML"));
				}					
			} else {
				contenidoHTML = contenidoBusqueda;
				//oDatosRUC.setMensajeRespuesta("Mensaje del inconveniente no especificado");
				oDatosRUC.getTransaccionListaMensajes().add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Mensaje del inconveniente no especificado"));
				nombreInicio = "<h4 class=\"list-group-item-heading\">";
				nombreFin = "</h4>";

				int resultadoBusqueda = contenidoHTML.indexOf(nombreInicio, 0);

				if (resultadoBusqueda > -1) {
					resultadoBusqueda += nombreInicio.length();
					String[] arrResultado = ExtraerContenidoEntreTag(contenidoHTML, resultadoBusqueda, nombreInicio,
							nombreFin);

					if (arrResultado != null) {
						oDatosRUC.setRuc(arrResultado[1]);
						// Tipo Contribuyente
						nombreInicio = "<p class=\"list-group-item-text\">";
						nombreFin = "</p>";
						arrResultado = ExtraerContenidoEntreTag(contenidoHTML, Integer.parseInt(arrResultado[0]),
								nombreInicio, nombreFin);
						if (arrResultado != null) {
							oDatosRUC.setTipoContribuyente(arrResultado[1]);
							// Nombre Comercial
							arrResultado = ExtraerContenidoEntreTag(contenidoHTML, Integer.parseInt(arrResultado[0]),
									nombreInicio, nombreFin);

							if (arrResultado != null) {
								oDatosRUC.setNombreComercial(
										arrResultado[1].replace("\r\n", "").replace("\t", "").trim());

								// Fecha de Inscripcion
								arrResultado = ExtraerContenidoEntreTag(contenidoHTML,
										Integer.parseInt(arrResultado[0]), nombreInicio, nombreFin);
								if (arrResultado != null) {
									oDatosRUC.setFechaInscripcion(arrResultado[1]);

									// Fecha de Inicio de Actividades:
									arrResultado = ExtraerContenidoEntreTag(contenidoHTML,
											Integer.parseInt(arrResultado[0]), nombreInicio, nombreFin);
									if (arrResultado != null) {
										oDatosRUC.setFechaInicioActividades(arrResultado[1]);

										// Estado del Contribuyente
										arrResultado = ExtraerContenidoEntreTag(contenidoHTML,
												Integer.parseInt(arrResultado[0]), nombreInicio, nombreFin);
										if (arrResultado != null) {
											oDatosRUC.setEstadoContribuyente(arrResultado[1].trim());

											// Condicion del Contribuyente
											arrResultado = ExtraerContenidoEntreTag(contenidoHTML,
													Integer.parseInt(arrResultado[0]), nombreInicio, nombreFin);
											if (arrResultado != null) {
												oDatosRUC.setCondicionContribuyente(arrResultado[1].trim());

												// Domicilio Fiscal
												arrResultado = ExtraerContenidoEntreTag(contenidoHTML,
														Integer.parseInt(arrResultado[0]), nombreInicio, nombreFin);
												if (arrResultado != null) {
													oDatosRUC.setDomicilioFiscal(arrResultado[1].trim());

													// Sistema Emision de Comprobante
													arrResultado = ExtraerContenidoEntreTag(contenidoHTML,
															Integer.parseInt(arrResultado[0]), nombreInicio, nombreFin);
													if (arrResultado != null) {
														oDatosRUC.setSistemaEmisionComprobante(arrResultado[1].trim());

														// Actividad Comercio Exterior
														arrResultado = ExtraerContenidoEntreTag(contenidoHTML,
																Integer.parseInt(arrResultado[0]), nombreInicio,
																nombreFin);

														if (arrResultado != null) {
															oDatosRUC.setActividadComercioExterior(
																	arrResultado[1].trim());

															// Sistema Contabilidad
															arrResultado = ExtraerContenidoEntreTag(contenidoHTML,
																	Integer.parseInt(arrResultado[0]), nombreInicio,
																	nombreFin);
															if (arrResultado != null) {
																oDatosRUC.setSistemaContabilidiad(
																		arrResultado[1].trim());

																// Actividad(es) Economica(s)
																nombreInicio = "<tbody>";
																nombreFin = "</tbody>";
																arrResultado = ExtraerContenidoEntreTag(contenidoHTML,
																		Integer.parseInt(arrResultado[0]), nombreInicio,
																		nombreFin);
																if (arrResultado != null) {
																	oDatosRUC.setActividadesEconomicas(
																			obtenerContenidosLista(
																					arrResultado[1].replace("\r\n", "")
																							.replace("\t", "").trim()));

																	// Comprobantes de Pago c/aut. de impresion (F. 806
																	// u
																	// 816)
																	arrResultado = ExtraerContenidoEntreTag(
																			contenidoHTML,
																			Integer.parseInt(arrResultado[0]),
																			nombreInicio, nombreFin);
																	if (arrResultado != null) {
																		oDatosRUC.setComprobantesPago(
																				obtenerContenidosLista(arrResultado[1]
																						.replace("\r\n", "")
																						.replace("\t", "").trim()));

																		// Sistema de Emision Electronica
																		arrResultado = ExtraerContenidoEntreTag(
																				contenidoHTML,
																				Integer.parseInt(arrResultado[0]),
																				nombreInicio, nombreFin);
																		if (arrResultado != null) {

																			oDatosRUC.setSistemaEmisionElectronica(
																					obtenerContenidosLista(
																							arrResultado[1]
																									.replace("\r\n", "")
																									.replace("\t", "")
																									.trim()));

																			// Emisor Electronico Desde
																			nombreInicio = "<p class=\"list-group-item-text\">";
																			nombreFin = "</p>";

																			arrResultado = ExtraerContenidoEntreTag(
																					contenidoHTML,
																					Integer.parseInt(arrResultado[0]),
																					nombreInicio, nombreFin);
																			if (arrResultado != null) {
																				oDatosRUC.setEmisorElectronicoDesde(
																						arrResultado[1].trim());

																				// Comprobantes Electronicos

																				arrResultado = ExtraerContenidoEntreTag(
																						contenidoHTML,
																						Integer.parseInt(
																								arrResultado[0]),
																						nombreInicio, nombreFin);

																				if (arrResultado != null) {
																					oDatosRUC
																							.setComprobantesElectronicos(
																									arrResultado[1]
																											.trim());

																					// Afiliado al PLE desde
																					nombreInicio = "<p class=\"list-group-item-text\">";
																					nombreFin = "</p>";
																					arrResultado = ExtraerContenidoEntreTag(
																							contenidoHTML,
																							Integer.parseInt(
																									arrResultado[0]),
																							nombreInicio, nombreFin);
																					if (arrResultado != null) {
																						oDatosRUC.setAfiliadoPLEDesde(
																								arrResultado[1]);

																						// Padrones
																						nombreInicio = "<tbody>";
																						nombreFin = "</tbody>";
																						arrResultado = ExtraerContenidoEntreTag(
																								contenidoHTML,
																								Integer.parseInt(
																										arrResultado[0]),
																								nombreInicio,
																								nombreFin);
																						if (arrResultado != null) {
																							oDatosRUC.setPadrones(
																									obtenerContenidosLista(
																											arrResultado[1]
																													.replace(
																															"\r\n",
																															"")
																													.replace(
																															"\t",
																															"")
																													.trim()));

																							//oDatosRUC.setTipoRespuesta(1);
																							//oDatosRUC.setMensajeRespuesta("Ok");
																							oDatosRUC.setTransaccionEstado(DominioTransaccion.OK);
																							oDatosRUC.getTransaccionListaMensajes().clear();
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}

											}

										}
									}

								}
							}
						}
					}

				}

			}

		}

		return oDatosRUC;
	}

	private static String ExtraerContenidoEntreTagString(String cadena, int posicion, String nombreInicio,
			String nombreFin) {
		String respuesta = "";
		int posicionInicio = cadena.indexOf(nombreInicio, posicion);
		if (posicionInicio > -1) {
			posicionInicio += nombreInicio.length();
			int posicionFin = cadena.indexOf(nombreFin, posicionInicio);
			if (posicionFin > -1)
				respuesta = cadena.substring(posicionInicio, posicionFin);
		}
		return respuesta;
	}

	private static String[] ExtraerContenidoEntreTag(String cadena, int posicion, String nombreInicio,
			String nombreFin) {
		String[] arrRespuesta = null;
		int posicionInicio = cadena.indexOf(nombreInicio, posicion);
		if (posicionInicio > -1) {
			posicionInicio += nombreInicio.length();
			int posicionFin = cadena.indexOf(nombreFin, posicionInicio);
			if (posicionFin > -1) {
				posicion = posicionFin + nombreFin.length();
				arrRespuesta = new String[2];
				arrRespuesta[0] = "" + posicion;
				arrRespuesta[1] = cadena.substring(posicionInicio, posicionFin);
			}
		}

		return arrRespuesta;
	}

	private static List<String> obtenerContenidosLista(String tr) {
		List<String> lst = new ArrayList<String>();
		while (tr.indexOf("<td>") > -1) {
			lst.add(tr.substring(tr.indexOf("<td>") + 4, tr.indexOf("</td>")).trim());
			tr = tr.substring(tr.indexOf("</td>") + 5, tr.length());
		}
		return lst;
	}

	private static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
	
}

