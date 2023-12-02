package net.royal.spring.sistema.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.annotations.ApiOperation;
import net.royal.spring.sistema.dao.impl.SyReporteDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyReporte;
import net.royal.spring.sistema.dominio.BeanSyReportePk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyReporte;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyReporte;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyReportearchivo;
import net.royal.spring.sistema.servicio.impl.SyReporteServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyReporteServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.DtoWhExportar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/sistema/syreportecomun")
@CrossOrigin(origins = "*")
public class SyReporteComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyReporteComunRest.class);

	@Autowired
	private SyReporteServicioImpl servicio;

	@Autowired
	private SyReporteServicioValidar validar;

	@Autowired
	private SyReporteDaoImpl syReporteDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyReporteComunRest() {
		super("syreporte");
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | DtoComunSyReporte | Parametros de salida: DtoComunSyReporte", 
			value = "SY-REPORTE-CREGIST", tags = {"SISTEMA", "REPORTE"})
	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyReporte> registrar(@RequestBody DtoComunSyReporte dto) throws Exception {
		
		if(UString.esNuloVacio(dto.getReportecodigo())) {
			dto.setReportecodigo(syReporteDao.generarCodigo());
		}	
		
		BeanSyReporte bean = dto.obtenerBean();
		bean = servicio.reportesolicitudRegistrar(getUsuarioActual(), bean);
		return new ResponseEntity<BeanSyReporte>(bean,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: obtener por id | DtoComunSyReporte | Parametros de salida: DtoComunSyReporte", 
			value = "SY-REPORTE-COBTDTO", tags = {"SISTEMA", "REPORTE"})
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerdtoPorId", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyReporte> obtenerDtoPorId(@RequestBody DtoComunSyReporte pk) throws Exception {
		BeanSyReporte bean = syReporteDao.obtenerPorId(pk.getAplicacioncodigo(),pk.getReportecodigo());
		pk.asignarBean(bean);
		return new ResponseEntity<DtoComunSyReporte>(pk, HttpStatus.OK);
	}
	
	/**	
	 * QQUECHOD VALIDADO
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: obtener por uuid | DtoComunSyReporte | Parametros de salida: DtoComunSyReporte", 
			value = "SY-REPORTE-COBTDTO", tags = {"SISTEMA", "REPORTE"})
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyReporte> obtenerDtoPorUuid(@RequestBody DtoComunSyReporte pk) throws Exception {
		BeanSyReporte bean = syReporteDao.obtenerPorUuid(pk.getUuid());
		
		if(bean.getDescripcionlocal() != null)
		{
			bean.setDescripcionlocal(bean.getDescripcionlocal().trim());
		}
		if(bean.getDescripcioningles() != null)
		{
			bean.setDescripcioningles(bean.getDescripcioningles().trim());
		}
		pk.asignarBean(bean);
		return new ResponseEntity<DtoComunSyReporte>(pk, HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: obtener por id | DtoComunSyReporte | Parametros de salida: DtoComunSyReporte", 
			value = "SY-REPORTE-CACTUA", tags = {"SISTEMA", "REPORTE"})
	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyReporte> actualizar(@RequestBody DtoComunSyReporte dto) throws Exception {
		BeanSyReporte bean = dto.obtenerBean();
		bean = servicio.reportesolicitudActualizar(getUsuarioActual(), bean);
		return new ResponseEntity<BeanSyReporte>(bean,HttpStatus.OK);
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: obtener por id | FiltroComunSyReporte | Parametros de salida: DominioPaginacion", 
			value = "SY-REPORTE-CLISPAG", tags = {"SISTEMA", "REPORTE"})
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyReporte paginacion)
			throws Exception {
		return new ResponseEntity<DominioPaginacion>(
				servicio.reportelistarConPaginacion(this.getUsuarioActual(), paginacion), HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping(value = "/exportarReportes", produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportarReportes(HttpServletResponse response, @RequestBody FiltroComunSyReporte filtro) throws Exception
	 {
		DominioPaginacion paginacion = syReporteDao.exportarReportes(filtro);
		String[] arrCabecera = new String[] {"Aplicaci\u00f3n","C\u00f3digo","Nombre","Tipo","Estado"};
		String[] arrColumnas = new String[] {"aplicacionDescripcion", "reportecodigo","descripcionlocal","tipo","estadodescripcion"}; 
		
		DtoWhExportar dtoExportar=new DtoWhExportar();
		dtoExportar.setTitulo("Listado de Reportes");
		dtoExportar.setTipoExpotar(filtro.getTipoexportar());
		dtoExportar.setArrCabecera(arrCabecera);
		dtoExportar.setArrColumnas(arrColumnas);
		dtoExportar.setPaginacion(paginacion);
			
		servicio.exportarInformacionWh(response,dtoExportar);
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerTopicos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoTabla> obtenerTopicos(@RequestBody DtoTabla dto) {
		return syReporteDao.obtenerTopicos(dto.getCodigo());
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarPaginadoReportes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginadoReportes(@RequestBody FiltroComunSyReporte filtro)
			throws Exception {
		return new ResponseEntity<DominioPaginacion>(
				servicio.listarPaginadoReportes(this.getUsuarioActual(), filtro), HttpStatus.OK);
	}
	
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerListadoPadre", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<DtoTabla> obtenerListadoPadre(@RequestBody DtoComunSyReporte pk) {
		return syReporteDao.obtenerListadoPadre(pk);
	}
	
}
