package net.royal.spring.seguridad.rest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.constante.ConstantePantallaAccion;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioArchivo;
import net.royal.spring.framework.modelo.generico.DominioExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;
 
import net.royal.spring.seguridad.dao.impl.SeguridadautorizacionesComunDaoImpl;
import net.royal.spring.seguridad.dao.impl.SySeguridadautorizacionesComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadautorizaciones;
import net.royal.spring.seguridad.dominio.BeanSySeguridadautorizaciones;
import net.royal.spring.seguridad.dominio.dto.DtoComunAutorizacionConcepto;
import net.royal.spring.seguridad.dominio.dto.DtoComunAutorizacionFuncion;
import net.royal.spring.seguridad.dominio.dto.DtoComunSeguridadTabsMenu;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionAutorizacionConcepto;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionUsuario;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunSeguridadTabsMenu;
import net.royal.spring.seguridad.servicio.impl.SeguridadautorizacionesServicioImpl;
import net.royal.spring.seguridad.servicio.impl.SySeguridadautorizacionesServicioImpl;
import net.royal.spring.seguridad.servicio.validar.SeguridadautorizacionesServicioValidar;

@RestController
@RequestMapping("/spring/seguridad/seguridadautorizaciones")
@CrossOrigin(origins = "*")
public class SeguridadautorizacionesRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(SeguridadautorizacionesRest.class);

	@Autowired
	private SeguridadautorizacionesServicioImpl servicio;

	@Autowired
	private SeguridadautorizacionesServicioValidar validar;

	@Autowired
	private SeguridadautorizacionesComunDaoImpl consulta;
	
	@Autowired
	private SySeguridadautorizacionesComunDaoImpl syconsulta;
	
	@Autowired
	private SySeguridadautorizacionesServicioImpl syservicio;
	

	@Transactional
	@PutMapping(value = "/validar/{accion}/{aplicacioncodigo}/{grupo}/{concepto}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion,
			@Validated @PathVariable String aplicacioncodigo, @Validated @PathVariable String grupo,
			@Validated @PathVariable String concepto, @Validated @PathVariable String usuario,
			@RequestBody BeanSeguridadautorizaciones bean) throws Exception {
		logger.debug("SeguridadautorizacionesRest.validar");
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean, aplicacioncodigo, grupo,
				concepto, usuario);
		if (lst.isEmpty())
			return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.BAD_REQUEST);
	}

	@Transactional
	@GetMapping(value = "/obtenerporid/{aplicacioncodigo}/{grupo}/{concepto}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSeguridadautorizaciones> obtenerPorId(@Validated @PathVariable String aplicacioncodigo,
			@Validated @PathVariable String grupo, @Validated @PathVariable String concepto,
			@Validated @PathVariable String usuario) throws Exception {
		logger.debug("SeguridadautorizacionesRest.obtenerPorId");
		BeanSeguridadautorizaciones bean = consulta.obtenerPorId(aplicacioncodigo, grupo, concepto, usuario);
		if (bean == null)
			return new ResponseEntity<BeanSeguridadautorizaciones>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<BeanSeguridadautorizaciones>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSeguridadautorizaciones> registrar(@RequestBody BeanSeguridadautorizaciones bean)
			throws Exception {
		logger.debug("SeguridadautorizacionesRest.registrar");
		bean = servicio.coreInsertar(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanSeguridadautorizaciones>(bean, HttpStatus.CREATED);
	}

	@PostMapping(value = "/updateList", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoComunAutorizacionFuncion> updateLists(@RequestBody List<DtoComunAutorizacionFuncion> lst) throws UException {

		logger.debug("ConceptoSendDto.ConceptoSendDto");

		BeanSeguridadautorizaciones bean = new BeanSeguridadautorizaciones();

		List<DtoComunAutorizacionFuncion> lstReq = lst;

		// INIT UPDATE SEGURIDADCONCEPTO

		for (DtoComunAutorizacionFuncion items : lstReq) {
		
			if (items.getSeleccionar() == "true" ) {
				items.setSeleccionar("A");
			} else if(items.getSeleccionar() == "false"){
				items.setSeleccionar("I");
			}
			System.out.println("getSeleccionar: "+items.getSeleccionar());
			if (items.getFlgAgregar() == "true") {
				items.setFlgAgregar("S");
			} else if(items.getFlgAgregar() == "false") {
				items.setFlgAgregar("N");
			}

			if (items.getFlgBorrar() == "true") {
				items.setFlgBorrar("S");
			} else if(items.getFlgBorrar() == "false") {
				items.setFlgBorrar("N");
			}

			if (items.getFlgModificar() == "true") {
				items.setFlgModificar("S");
			} else if(items.getFlgModificar() == "false") {
				items.setFlgModificar("N");
			}

			if (items.getFlgAprobar() == "true") {
				items.setFlgAprobar("S");
			} else if(items.getFlgAprobar() == "false"){
				items.setFlgAprobar("N");
			}

			bean.getPk().setAplicacioncodigo(items.getAplicacion());
			bean.getPk().setConcepto(items.getConcepto());
			bean.getPk().setGrupo(items.getGrupo());
			bean.getPk().setUsuario(items.getUsuario());

			bean = consulta.obtenerPorId(bean.getPk());

			if (bean == null) {

				bean = new BeanSeguridadautorizaciones();

				bean.getPk().setAplicacioncodigo(items.getAplicacion());
				bean.getPk().setConcepto(items.getConcepto());
				bean.getPk().setGrupo(items.getGrupo());
				bean.getPk().setUsuario(items.getUsernew());

				bean.setEstado(items.getSeleccionar());
				bean.setOpcionagregarflag(items.getFlgAgregar());
				bean.setOpcionborrarflag(items.getFlgBorrar());
				bean.setOpcionmodificarflag(items.getFlgModificar());
				bean.setOpcionaprobarflag(items.getFlgAprobar());

				bean = servicio.coreInsertar(this.getUsuarioActual(), bean);
			} else {

				bean.setEstado(items.getSeleccionar());
				bean.setOpcionagregarflag(items.getFlgAgregar());
				bean.setOpcionborrarflag(items.getFlgBorrar());
				bean.setOpcionmodificarflag(items.getFlgModificar());
				bean.setOpcionaprobarflag(items.getFlgAprobar());

				bean = servicio.coreActualizar(this.getUsuarioActual(), bean);

			}

 

		}

		return lstReq;

	}
	
	
	
//	//ESTABA COMENTADO ESTE METODO
//	@PostMapping(value = "/updateListConcepto",  produces = MediaType.APPLICATION_JSON_VALUE)
//    public  List<DtoAutorizacionConcepto> updateListConcepto(@RequestBody List<DtoAutorizacionConcepto> lst) throws UException {
//	
//	logger.debug("ConceptoSendDto.ConceptoSendDto");
//	
//	
//	SySeguridadautorizaciones bean = new BeanSySeguridadautorizaciones();
//	 
//    
//    
//    List<DtoAutorizacionConcepto> lstReq = lst;
//   
// 
//    //INIT UPDATE SEGURIDADCONCEPTO
//    
//	for (DtoAutorizacionConcepto items : lstReq) {
//		
//		bean.getPk().setAplicacioncodigo(items.getAplicacioncodigo());
//		bean.getPk().setConcepto(items.getConcepto());
//		bean.getPk().setGrupo(items.getGrupo());
//		bean.getPk().setUsuario(items.getUsuariocodigo());
//		
//		
//		bean = syconsulta.obtenerPorId(bean.getPk());
//		
//		//String estado = ConstanteEstadoGenerico.ACTIVO;
//		
//		if(bean==null) {
//
//			System.out.println("seleccionar : "+items.getSeleccionar());
//			bean = new BeanSySeguridadautorizaciones();
//			
//			bean.getPk().setAplicacioncodigo(items.getAplicacioncodigo());
//			bean.getPk().setConcepto(items.getConcepto());
//			bean.getPk().setGrupo(items.getGrupo());
//			bean.getPk().setUsuario(items.getUsernew());
//			
//			bean.setEstado(items.getSeleccionar() == "false" ? ConstanteEstadoGenerico.INACTIVO : ConstanteEstadoGenerico.ACTIVO );	 
//			bean.setAuxAccion(ConstantePantallaAccion.INSERTAR);
//			System.out.println("estado: "+bean.getEstado());
//			bean = servicio.coreInsertar(this.getUsuarioActual(),bean);
//		} else {
//			System.out.println("seleccionar : "+items.getSeleccionar());
//		
//			System.out.println("pk.usuario: "+items.getUsuariocodigo()); 
//			bean.setEstado(items.getSeleccionar() == "false" ? ConstanteEstadoGenerico.INACTIVO : ConstanteEstadoGenerico.ACTIVO );	 
//			bean.setAuxAccion(ConstantePantallaAccion.ACTUALIZAR);
//			System.out.println("estado: "+bean.getEstado());
//			bean = servicio.coreActualizar(this.getUsuarioActual(),bean);
//		}
//	}
//    
//	return lstReq;
// 
//}
	
	@PostMapping(value = "/updateListConcepto", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoComunAutorizacionConcepto> updateListConcepto(@RequestBody List<DtoComunAutorizacionConcepto> lst)
			throws Exception {

		logger.debug("ConceptoSendDto.ConceptoSendDto");

		BeanSySeguridadautorizaciones bean = new BeanSySeguridadautorizaciones();
		List<DtoComunAutorizacionConcepto> lstReq = lst;

		// INIT UPDATE SEGURIDADCONCEPTO

		for (DtoComunAutorizacionConcepto items : lstReq) {
			if (items.getSeleccionar() == "true") {
				items.setSeleccionar("A");
			} else {
				items.setSeleccionar("I");
			}
			bean.getPk().setAplicacioncodigo(items.getAplicacioncodigo());
			bean.getPk().setConcepto(items.getConcepto());
			bean.getPk().setGrupo(items.getGrupo());
			bean.getPk().setUsuario(items.getUsuariocodigo());
			bean = syconsulta.obtenerPorId(bean.getPk());
			if (bean == null) {
				bean = new BeanSySeguridadautorizaciones();
				bean.getPk().setAplicacioncodigo(items.getAplicacioncodigo());
				bean.getPk().setConcepto(items.getConcepto());
				bean.getPk().setGrupo(items.getGrupo());
				bean.getPk().setUsuario(items.getUsernew());
				bean.setEstado(items.getSeleccionar());
				bean = syservicio.coreInsertar(this.getUsuarioActual(), bean);
			} else {

				bean.setEstado(items.getSeleccionar());
				bean = syservicio.coreActualizar(this.getUsuarioActual(), bean);
			}

		}

		return lstReq;

	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSeguridadautorizaciones> actualizar(@RequestBody BeanSeguridadautorizaciones bean)
			throws Exception {
		logger.debug("SeguridadautorizacionesRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanSeguridadautorizaciones>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular/{aplicacioncodigo}/{grupo}/{concepto}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSeguridadautorizaciones> anular(@Validated @PathVariable String aplicacioncodigo,
			@Validated @PathVariable String grupo, @Validated @PathVariable String concepto,
			@Validated @PathVariable String usuario) throws Exception {
		logger.debug("SeguridadautorizacionesRest.anular");
		BeanSeguridadautorizaciones bean = consulta.obtenerPorId(aplicacioncodigo, grupo, concepto, usuario);
		if (bean == null)
			return new ResponseEntity<BeanSeguridadautorizaciones>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanSeguridadautorizaciones>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar/{aplicacioncodigo}/{grupo}/{concepto}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@Validated @PathVariable String aplicacioncodigo,
			@Validated @PathVariable String grupo, @Validated @PathVariable String concepto,
			@Validated @PathVariable String usuario) throws Exception {
		logger.debug("SeguridadautorizacionesRest.eliminar");
		BeanSeguridadautorizaciones bean = consulta.obtenerPorId(aplicacioncodigo, grupo, concepto, usuario);
		if (bean == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			servicio.coreEliminar(this.getUsuarioActual(), bean);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@Transactional
	@PostMapping(value = "/listarFunciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarFunciones_(@RequestBody FiltroComunPaginacionAutorizacionConcepto filtro)
			throws Exception {
		return servicio.listarFunciones(filtro);
	}

	@Transactional
	@PostMapping(value = "/listarConceptos", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarConceptos(@RequestBody FiltroComunPaginacionAutorizacionConcepto filtro)
			throws Exception {
		return servicio.listarConceptos(filtro);
	}
	@Transactional
	@PostMapping(value = "/listarreportesseguridad", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listarreportesseguridad(@RequestBody FiltroComunPaginacionAutorizacionConcepto filtro)
			throws Exception {
		return servicio.listarreportesseguridad(filtro);
	}
	
	
	
	@Transactional
	@SuppressWarnings({ "rawtypes" })
	@PutMapping(value = "/exportarfunciones", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarfunciones(HttpServletResponse response,
			@RequestBody FiltroComunPaginacionAutorizacionConcepto filtro) throws Exception {
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10000);
		List lista = servicio.listarFunciones(filtro).getPaginacionListaResultado();
		String[] arrColumnas = new String[] { "seleccionar", "grupo", "concepto", "grupoDescripcion", "objetoAutorizar",
				"flgAgregar", "flgModificar", "flgBorrar", "ultimoUsuario", "ultimaFecha" };
		/*
		 * "aplicacion", ,"usernew","flgAgregarboolean","flgModificarboolean"
		 * ,"flgBorrabooleanr"
		 * 
		 */

		DominioExportar dtoExportar = new DominioExportar();
		dtoExportar.setFormato(filtro.getTipoexportar());
		dtoExportar.setLstDatos(lista);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setTitulo("Seguridad Autorizaciones - Funciones");
		dtoExportar.setArrCabeceras(new String[] { "Seleccionar", "Grupo", "Concepto", "Grupo Descripci\u00F3n",
				"Objeto a Autorizar", "Nuevo", "Modificar", "Eliminar", "\u00DAltimo Usuario", "\u00DAltima Fecha" });
		DominioArchivo obj = servicio.exportarInformacion(dtoExportar);

		response.setContentType(obj.getMimeContentType());
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"" + obj.getNombreArchivo()+ "\""));
		response.setContentLength((int) obj.getNombreArchivo().length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(obj.getArchivoAdjuntoBase64()));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

	@Transactional
	@SuppressWarnings({ "rawtypes" })
	@PutMapping(value = "/exportarconcepto", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarconcepto(HttpServletResponse response, @RequestBody FiltroComunPaginacionAutorizacionConcepto filtro)
			throws Exception {
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10000);
		List lista = servicio.listarConceptos(filtro).getPaginacionListaResultado();
		String[] arrColumnas = new String[] { "seleccionar", "grupo", "concepto", "grupoDescripcion", "objetoAutorizar",
				"ultimoUsuario", "ultimaFecha"

		};

		/*
		 * "aplicacioncodigo",
		 * 
		 * "usuariocodigo", "grupoDescripcion" , "objetoAutorizar", "ultimoUsuario",
		 * "ultimaFecha" ,"tipodeacceso", "concepto" ,"descripcion", "visible"
		 * ,"tipodetalle","tipoobjeto","objeto","ultimousuario","ultimafecha" ,"wpage"
		 */

		DominioExportar dtoExportar = new DominioExportar();
		dtoExportar.setFormato(filtro.getTipoexportar());
		dtoExportar.setLstDatos(lista);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setTitulo("Seguridad Autorizaciones - Concepto");
		dtoExportar.setArrCabeceras(new String[] { "Seleccionar", "Grupo", "Concepto", "Grupo Descripci\u00F3n",
				"Objeto a Autorizar", "\u00DAltimo Usuario", "\u00DAltima Fecha" });
		DominioArchivo obj = servicio.exportarInformacion(dtoExportar);

		response.setContentType(obj.getMimeContentType());
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"" + obj.getNombreArchivo() + "\""));
		response.setContentLength((int) obj.getNombreArchivo().length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(obj.getArchivoAdjuntoBase64()));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}
	
	
	@Transactional
	@SuppressWarnings({ "rawtypes" })
	@PutMapping(value = "/exportarreporte", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarreporte(HttpServletResponse response, @RequestBody FiltroComunPaginacionAutorizacionConcepto filtro)
			throws Exception {
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10000);
		List lista = servicio.listarreportesseguridad(filtro).getPaginacionListaResultado();
		String[] arrColumnas = new String[] { "seleccionar", "aplicacion", "grupo", "ultimoUsuario", "ultimaFecha"

		};
		
//		private String seleccionar;
//		private String aplicacion;
//		private String grupo;
//		private String concepto;
//		private String usuario;
//		private String grupoDescripcion;
//		private String objetoAutorizar;
//		private String flgAgregar;
//		private String flgModificar;
//		private String flgAprobar;
//		private String flgBorrar;
//		
//		private String ultimoUsuario;
//		private String ultimaFecha;
//		private String usernew;
//		public BigDecimal ROWNUM_ ;
 
		DominioExportar dtoExportar = new DominioExportar();
		dtoExportar.setFormato(filtro.getTipoexportar());
		dtoExportar.setLstDatos(lista);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setTitulo("Seguridad Autorizaciones - Reporte");
		dtoExportar.setArrCabeceras(new String[] { "Seleccionar", "Aplicaci\u00F3n", "Reporte", "Ult Usuario",
				"Ult.Fecha Modif"});
		DominioArchivo obj = servicio.exportarInformacion(dtoExportar);

		response.setContentType(obj.getMimeContentType());
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"" + obj.getNombreArchivo() + "\""));
		response.setContentLength((int) obj.getNombreArchivo().length());
		InputStream inputStream = new BufferedInputStream(new FileInputStream(obj.getArchivoAdjuntoBase64()));
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

	@Transactional
	@PutMapping(value = "/obtenerSeguridadTabsMenu", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoComunSeguridadTabsMenu obtenerSeguridadTabsMenu(@RequestBody FiltroComunSeguridadTabsMenu filtro) throws Exception {
		filtro.setPerfil(this.getUsuarioActual().getUsuario());
		return servicio.obtenerSeguridadTabsMenu(filtro, false);
	}
	
	@Transactional
	@PutMapping(value = "/obtenerSeguridadTabsMenuConNull", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoComunSeguridadTabsMenu obtenerSeguridadTabsMenuConNull(@RequestBody FiltroComunSeguridadTabsMenu filtro) throws Exception {
		filtro.setPerfil(this.getUsuarioActual().getUsuario());
		return servicio.obtenerSeguridadTabsMenu(filtro, true);
	}

	//LEONARDO OBTENER SEGURIDAD INDEPENDIENTE PARA GTH
	@Transactional
	@PutMapping(value = "/obtenerSeguridadTabsMenuGTH", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoComunSeguridadTabsMenu obtenerSeguridadTabsMenuGTH(@RequestBody FiltroComunSeguridadTabsMenu filtro) throws Exception {
		filtro.setPerfil(this.getUsuarioActual().getUsuario());
		return servicio.obtenerSeguridadTabsMenuGTH(filtro);
	}
	
	//SEGURIDAD PARA KPI - INDICADORES
	@Transactional
	@PutMapping(value="/listarUsuariospaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarUsuariospaginado(@RequestBody  FiltroComunPaginacionUsuario filtro) throws Exception {
		logger.debug("RtConceptoRest.listarPaginado");
		DominioPaginacion paginacion = consulta.listarUsuariospaginado(this.getUsuarioActual(), filtro);
		return new ResponseEntity<DominioPaginacion>(paginacion,HttpStatus.OK);
	}
	
	
}
