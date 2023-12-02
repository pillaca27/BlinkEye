package net.royal.spring.sistema.rest;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
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
import net.royal.spring.sistema.dao.impl.SyReportearchivoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyReportearchivo;
import net.royal.spring.sistema.dominio.BeanSyReportearchivoPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyReportearchivo;
import net.royal.spring.sistema.dominio.dto.DtoComunSyReportearchivoHeader;
import net.royal.spring.sistema.dominio.filtro.FiltroComunSyReportearchivo;
import net.royal.spring.sistema.servicio.impl.SyReportearchivoServicioImpl;
import net.royal.spring.sistema.servicio.validar.SyReportearchivoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.util.UValidador;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/sistema/syreportearchivocomun")
@CrossOrigin(origins = "*")
public class SyReportearchivoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(SyReportearchivoComunRest.class);

	@Autowired
	private SyReportearchivoServicioImpl servicio;

	@Autowired
	private SyReportearchivoServicioValidar validar;

	@Autowired
	private SyReportearchivoDaoImpl syReportearchivoDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public SyReportearchivoComunRest() {
		super("syreportearchivo");
	}

	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | DtoComunSyReportearchivo | Parametros de salida: DtoComunSyReportearchivo", 
			value = "SY-REPARCH-CREGIST", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyReportearchivo> registrar(@RequestBody DtoComunSyReportearchivo dto)
			throws Exception {		
		BeanSyReportearchivo bean = dto.obtenerBean();
		bean = servicio.archivosolicitudRegistrar(this.getUsuarioActual(), bean);		
		return new ResponseEntity<BeanSyReportearchivo>(bean, HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | DtoComunSyReportearchivo | Parametros de salida: DtoComunSyReportearchivo", 
			value = "SY-REPARCH-COBTDTO", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional(readOnly = true)
	@PutMapping(value = "/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyReportearchivo> obtenerdtoporid(@RequestBody DtoComunSyReportearchivo pk)
			throws Exception {
		pk = syReportearchivoDao.obtenerDtoPorId(pk);		
		return new ResponseEntity<DtoComunSyReportearchivo>(pk,HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | DtoComunSyReportearchivo | Parametros de salida: DtoComunSyReportearchivo", 
			value = "SY-REPARCH-CACTUA", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BeanSyReportearchivo> actualizar(@RequestBody DtoComunSyReportearchivo dto)
			throws Exception {
		
		BeanSyReportearchivoPk id = new BeanSyReportearchivoPk();
		id.setAplicacioncodigo(dto.getAplicacioncodigo());
		id.setReportecodigo(dto.getReportecodigo());
		id.setCompaniasocio(dto.getCompaniasocio());
		id.setPeriodo(dto.getPeriodo());
		id.setVersion(dto.getVersion());
		BeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(id);
		bean.setAuxString(dto.getAuxString());		
		bean.setNombre(dto.getNombre());
		bean.setVersionactual(dto.getVersionactual());
		bean.setFecharegistro(dto.getFecharegistro());
		bean = servicio.archivosolicitudActualizar(getUsuarioActual(), bean, dto.getVieneBotonSubir(),dto.getTipoarchivo());		
		return new ResponseEntity<BeanSyReportearchivo>(bean,HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | DtoComunSyReportearchivo | Parametros de salida: DtoComunSyReportearchivo", 
			value = "SY-REPARCH-CELIMI", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyReportearchivo> eliminar(@RequestBody DtoComunSyReportearchivo dto)
			throws Exception {
		BeanSyReportearchivoPk id = new BeanSyReportearchivoPk();
		id.setAplicacioncodigo(dto.getAplicacioncodigo());
		id.setReportecodigo(dto.getReportecodigo());
		id.setCompaniasocio(dto.getCompaniasocio());
		id.setPeriodo(dto.getPeriodo());
		id.setVersion(dto.getVersion());
		BeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(id);
		syReportearchivoDao.eliminar(bean);
		return new ResponseEntity<DtoComunSyReportearchivo>(dto,HttpStatus.OK);
	}	
	
	/**
	 * ARMAS MIGRADO
	 * @param paginacion
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | DtoComunSyReportearchivo | Parametros de salida: DtoComunSyReportearchivo", 
			value = "SY-REPARCH-CLISPAG", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyReportearchivo paginacion)
			throws Exception {
		return new ResponseEntity<DominioPaginacion>(syReportearchivoDao.archivolistarConPaginacion(paginacion),
				HttpStatus.OK);
	}
	
	
	/**
	 * @author Torresj
	 * @param paginacion
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Listar el Detalle de los reportes | DtoComunSyReportearchivo | Parametros de salida: List<DtoComunSyReportearchivo>", 
			value = "SY-REPARCH-CLIS", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional(readOnly = true)
	@PutMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunSyReportearchivo> > listar(@RequestBody FiltroComunSyReportearchivo paginacion) throws Exception {
		List<DtoComunSyReportearchivo>  dto = syReportearchivoDao.listar(paginacion);
		return new ResponseEntity<List<DtoComunSyReportearchivo> >(dto, HttpStatus.OK);
	}
	
	/**
	 * @author Torresj
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Actualiza la version de los reportes | DtoComunSyReportearchivo | Parametros de salida: DtoComunSyReportearchivo", 
			value = "SY-REPARCH-ACT", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional
	@PutMapping(value = "/actualizaVersion", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyReportearchivoHeader> actualizaVersion(@RequestBody DtoComunSyReportearchivoHeader pk)
			throws Exception {
		pk = syReportearchivoDao.actualizaVersion(pk);		
		return new ResponseEntity<DtoComunSyReportearchivoHeader>(pk,HttpStatus.OK);
	}
	
}
