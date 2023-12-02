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

 
import net.royal.spring.framework.modelo.generico.DominioArchivo;
import net.royal.spring.framework.modelo.generico.DominioExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.seguridad.dao.impl.SeguridadperfilusuarioComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanSeguridadperfilusuario;
import net.royal.spring.seguridad.dominio.dto.DtoComunSeguridadperfilusuarioBean;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionPerfilUsuario;
import net.royal.spring.seguridad.servicio.impl.SeguridadperfilusuarioServicioImpl;
import net.royal.spring.seguridad.servicio.validar.SeguridadperfilusuarioServicioValidar;

@RestController
@RequestMapping("/spring/seguridad/seguridadperfilusuario")
@CrossOrigin(origins = "*")
public class SeguridadperfilusuarioRest extends GenericoRest {

	private static Logger logger = LogManager.getLogger(SeguridadperfilusuarioRest.class);

	@Autowired
	private SeguridadperfilusuarioServicioImpl servicio;

	@Autowired
	private SeguridadperfilusuarioServicioValidar validar;

	@Autowired
	private SeguridadperfilusuarioComunDaoImpl consulta;

	@Transactional
	@PutMapping(value = "/validar/{accion}/{perfil}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DominioMensajeUsuario>> validar(@Validated @PathVariable String accion,
			@Validated @PathVariable String perfil, @Validated @PathVariable String usuario,
			@RequestBody BeanSeguridadperfilusuario bean) throws Exception {
		logger.debug("SeguridadperfilusuarioRest.validar");
		List<DominioMensajeUsuario> lst = validar.core(this.getUsuarioActual(), accion, bean, perfil, usuario);
		if (lst.isEmpty())
			return new ResponseEntity<List<DominioMensajeUsuario>>(HttpStatus.OK);
		return new ResponseEntity<List<DominioMensajeUsuario>>(lst, HttpStatus.BAD_REQUEST);
	}

	@Transactional
	@GetMapping(value = "/obtenerporid/{perfil}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSeguridadperfilusuario> obtenerPorId(@Validated @PathVariable String perfil,
			@Validated @PathVariable String usuario) throws Exception {
		logger.debug("SeguridadperfilusuarioRest.obtenerPorId");
		BeanSeguridadperfilusuario bean = consulta.obtenerPorId(perfil, usuario);
		if (bean == null)
			return new ResponseEntity<BeanSeguridadperfilusuario>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<BeanSeguridadperfilusuario>(bean, HttpStatus.OK);
	}

	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSeguridadperfilusuarioBean> registrar(@RequestBody DtoComunSeguridadperfilusuarioBean beans)
			throws Exception {
		logger.debug("SeguridadperfilusuarioRest.registrar");

		BeanSeguridadperfilusuario bean = new BeanSeguridadperfilusuario();
		if (beans.getLabeelbutton().equals("Usuario")) {

			String Usuario = beans.getPk().getPerfil();
			String Perfil = beans.getPk().getUsuario();

			bean.getPk().setPerfil(Perfil);
			bean.getPk().setUsuario(Usuario);
			bean.setAuxFlgPreparado(beans.getAuxFlgPreparado());
			bean.setEstado(beans.getEstado());
			bean.setUltimafechamodif(beans.getUltimafechamodif());
			bean.setUltimousuario(beans.getUltimousuario());

			bean = servicio.coreInsertar(this.getUsuarioActual(), bean);

		} else if (beans.getLabeelbutton().equals("Perfil")) {

			bean.getPk().setPerfil(beans.getPk().getPerfil());
			bean.getPk().setUsuario(beans.getPk().getUsuario());
			bean.setAuxFlgPreparado(beans.getAuxFlgPreparado());
			bean.setEstado(beans.getEstado());
			bean.setUltimafechamodif(beans.getUltimafechamodif());
			bean.setUltimousuario(beans.getUltimousuario());

			bean = servicio.coreInsertar(this.getUsuarioActual(), bean);
		}

		return new ResponseEntity<DtoComunSeguridadperfilusuarioBean>(beans, HttpStatus.CREATED);
	}

	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSeguridadperfilusuario> actualizar(@RequestBody BeanSeguridadperfilusuario bean)
			throws Exception {
		logger.debug("SeguridadperfilusuarioRest.actualizar");
		bean = servicio.coreActualizar(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanSeguridadperfilusuario>(bean, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/anular/{perfil}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSeguridadperfilusuario> anular(@Validated @PathVariable String perfil,
			@Validated @PathVariable String usuario) throws Exception {
		logger.debug("SeguridadperfilusuarioRest.anular");
		BeanSeguridadperfilusuario bean = consulta.obtenerPorId(perfil, usuario);
		if (bean == null)
			return new ResponseEntity<BeanSeguridadperfilusuario>(HttpStatus.NOT_FOUND);
		bean = servicio.coreAnular(this.getUsuarioActual(), bean);
		return new ResponseEntity<BeanSeguridadperfilusuario>(bean, HttpStatus.OK);
	}

	@Transactional
	@DeleteMapping(value = "/eliminar/{perfil}/{usuario}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> eliminar(@Validated @PathVariable String perfil,
			@Validated @PathVariable String usuario) throws Exception {
		logger.debug("SeguridadperfilusuarioRest.eliminar");
		BeanSeguridadperfilusuario bean = consulta.obtenerPorId(perfil, usuario);
		if (bean == null) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			servicio.coreEliminar(this.getUsuarioActual(), bean);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@Transactional
	@PostMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listar(@RequestBody FiltroComunPaginacionPerfilUsuario filtro) throws Exception {

		logger.debug("listar " + filtro.getUsuario());

		return servicio.listar(filtro);
	}

	@Transactional
	@PostMapping(value = "/listaruserbyperfil", produces = MediaType.APPLICATION_JSON_VALUE)
	public DominioPaginacion listaruserbyperfil(@RequestBody FiltroComunPaginacionPerfilUsuario filtro)
			throws Exception {

		logger.debug("listaruserbyperfil " + filtro.getUsuario());

		return servicio.listaruserbyperfil(filtro);
	}

	@Transactional
	@SuppressWarnings({ "rawtypes" })
	@PutMapping(value = "/exportarusuarioporperfil", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarusuarioporperfil(HttpServletResponse response,
			@RequestBody FiltroComunPaginacionPerfilUsuario filtro) throws Exception {
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10000);
		List lista = servicio.listaruserbyperfil(filtro).getPaginacionListaResultado();
		String[] arrColumnas = new String[] { "perfil", "nombre", "estado" };

		DominioExportar dtoExportar = new DominioExportar();
		dtoExportar.setFormato(filtro.getTipoexportar());
		dtoExportar.setLstDatos(lista);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setTitulo("Listado Usuarios por Perfiles");
		dtoExportar.setArrCabeceras(new String[] { "Usuario", "Nombre", "Estado" });
		DominioArchivo obj = servicio.exportarInformacion(dtoExportar);
		
		response.setContentType(obj.getMimeContentType()); //getMimeType()
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"" + obj.getNombreArchivo() + "\"")); //obj.getArchivoFile().getName()
		response.setContentLength((int) obj.getArchivoAdjuntoBase64().length()); //getArchivoFile()
		InputStream inputStream = new BufferedInputStream(new FileInputStream(obj.getArchivoAdjuntoBase64()));  //getArchivoFile()
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

	@Transactional
	@SuppressWarnings({ "rawtypes" })
	@PutMapping(value = "/exportarperfilgeneral", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarperfilgeneral(HttpServletResponse response, @RequestBody FiltroComunPaginacionPerfilUsuario filtro)
			throws Exception {
		filtro.getPaginacion().setPaginacionRegistrosPorPagina(10000);
		List lista = servicio.listaruserbyperfil(filtro).getPaginacionListaResultado();
		String[] arrColumnas = new String[] { "perfil", "usuario", "nombreperfil" };

		DominioExportar dtoExportar = new DominioExportar();
		dtoExportar.setFormato(filtro.getTipoexportar());
		dtoExportar.setLstDatos(lista);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setTitulo("Listado Usuarios por Perfiles");
		dtoExportar.setArrCabeceras(new String[] { "Usuario", "Perfil", "Nombre" });
		DominioArchivo obj = servicio.exportarInformacion(dtoExportar);

//		response.setContentType(obj.getMimeType());
//		response.setHeader("Content-Disposition",
//				String.format("attachment; filename=\"" + obj.getArchivoFile().getName() + "\""));
//		response.setContentLength((int) obj.getArchivoFile().length());
//		InputStream inputStream = new BufferedInputStream(new FileInputStream(obj.getArchivoFile()));
//		FileCopyUtils.copy(inputStream, response.getOutputStream());
		
		response.setContentType(obj.getMimeContentType()); //getMimeType()
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"" + obj.getNombreArchivo() + "\"")); //obj.getArchivoFile().getName()
		response.setContentLength((int) obj.getArchivoAdjuntoBase64().length()); //getArchivoFile()
		InputStream inputStream = new BufferedInputStream(new FileInputStream(obj.getArchivoAdjuntoBase64()));  //getArchivoFile()
		FileCopyUtils.copy(inputStream, response.getOutputStream());
	}

}
