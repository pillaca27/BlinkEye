package net.royal.spring.comun.rest;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.comun.dao.impl.WfSyReporteDaoImpl;
import net.royal.spring.comun.dao.impl.WfSyReportearchivoDaoImpl;
import net.royal.spring.comun.dominio.WfBeanSyReporte;
import net.royal.spring.comun.dominio.WfBeanSyReportePk;
import net.royal.spring.comun.dominio.WfBeanSyReportearchivo;
import net.royal.spring.comun.dominio.WfBeanSyReportearchivoPk;
import net.royal.spring.comun.dominio.dto.FiltroComunSyReportearchivoWf;
import net.royal.spring.comun.dominio.dto.WfDtoComunSyReportearchivo;
import net.royal.spring.comun.servicio.impl.WfSyReportearchivoServicioImpl;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/comun/wfsyreportearchivocomun")
@CrossOrigin(origins = "*")
public class WfSyReportearchivoComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(WfSyReportearchivoComunRest.class);

	@Autowired
	private WfSyReportearchivoServicioImpl servicio;

	//@Autowired
	//private WfSyReportearchivoServicioValidar validar;

	@Autowired
	private WfSyReportearchivoDaoImpl syReportearchivoDao;

	
	@Autowired
	private WfSyReporteDaoImpl syReporteDao;
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public WfSyReportearchivoComunRest() {
		super("wfsyreportearchivo");
	}

	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | WfDtoComunSyReportearchivo | Parametros de salida: WfDtoComunSyReportearchivo", 
			value = "SY-REPARCH-CREGIST", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional
	@PostMapping(value = "/registrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfBeanSyReportearchivo> registrar(@RequestBody WfDtoComunSyReportearchivo dto)
			throws Exception {		
		WfBeanSyReportearchivo bean = dto.obtenerBean();
		bean = servicio.archivosolicitudRegistrar(this.getUsuarioActual(), bean);		
		
		WfBeanSyReportePk id = new WfBeanSyReportePk();
		id.setAplicacioncodigo(dto.getAplicacioncodigo());
		id.setReportecodigo(dto.getReportecodigo());
		WfBeanSyReporte reporte = syReporteDao.obtenerPorId(id);
		reporte.setAsunto(dto.getAsuntoPrincipal());
		syReporteDao.actualizar(reporte);
		
		return new ResponseEntity<WfBeanSyReportearchivo>(bean, HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | WfDtoComunSyReportearchivo | Parametros de salida: WfDtoComunSyReportearchivo", 
			value = "SY-REPARCH-COBTDTO", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional
	@PutMapping(value = "/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfDtoComunSyReportearchivo> obtenerdtoporid(@RequestBody WfDtoComunSyReportearchivo pk)
			throws Exception {
		pk = syReportearchivoDao.obtenerDtoPorId(pk);		
		
		//Agregar el asunto principal
		WfBeanSyReportePk id = new WfBeanSyReportePk();
		id.setAplicacioncodigo(pk.getAplicacioncodigo());
		id.setReportecodigo(pk.getReportecodigo());
		WfBeanSyReporte reporte = syReporteDao.obtenerPorId(id);
		if(reporte==null){
			reporte = new WfBeanSyReporte();
			reporte.getPk().setAplicacioncodigo(pk.getAplicacioncodigo());
			reporte.getPk().setReportecodigo(pk.getReportecodigo());
			syReporteDao.registrar(reporte);
		}

		pk.setAsuntoPrincipal(reporte.getAsunto());
		
		return new ResponseEntity<WfDtoComunSyReportearchivo>(pk,HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | WfDtoComunSyReportearchivo | Parametros de salida: WfDtoComunSyReportearchivo", 
			value = "SY-REPARCH-CACTUA", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional
	@PutMapping(value = "/actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfBeanSyReportearchivo> actualizar(@RequestBody WfDtoComunSyReportearchivo dto)
			throws Exception {
		
		WfBeanSyReportearchivoPk id = new WfBeanSyReportearchivoPk();
		id.setAplicacioncodigo(dto.getAplicacioncodigo());
		id.setReportecodigo(dto.getReportecodigo());
		id.setCompaniasocio(dto.getCompaniasocio());
		id.setPeriodo(dto.getPeriodo());
		id.setVersion(dto.getVersion());
		WfBeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(id);
		bean.setAuxString(dto.getAuxString());		
		bean.setNombre(dto.getNombre());
		bean.setAsunto(dto.getAsunto());
		bean = servicio.archivosolicitudActualizar(getUsuarioActual(), bean);	
		
		WfBeanSyReportePk idd = new WfBeanSyReportePk();
		idd.setAplicacioncodigo(dto.getAplicacioncodigo());
		idd.setReportecodigo(dto.getReportecodigo());
		WfBeanSyReporte reporte = syReporteDao.obtenerPorId(idd);
		reporte.setAsunto(dto.getAsuntoPrincipal());
		syReporteDao.actualizar(reporte);
		
		return new ResponseEntity<WfBeanSyReportearchivo>(bean,HttpStatus.OK);
	}
	
	/**
	 * ARMAS MIGRADO
	 * @param pk
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | WfDtoComunSyReportearchivo | Parametros de salida: WfDtoComunSyReportearchivo", 
			value = "SY-REPARCH-CELIMI", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional
	@PutMapping(value = "/eliminar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<WfDtoComunSyReportearchivo> eliminar(@RequestBody WfDtoComunSyReportearchivo dto)
			throws Exception {
		WfBeanSyReportearchivoPk id = new WfBeanSyReportearchivoPk();
		id.setAplicacioncodigo(dto.getAplicacioncodigo());
		id.setReportecodigo(dto.getReportecodigo());
		id.setCompaniasocio(dto.getCompaniasocio());
		id.setPeriodo(dto.getPeriodo());
		id.setVersion(dto.getVersion());
		WfBeanSyReportearchivo bean = syReportearchivoDao.obtenerPorId(id);
		syReportearchivoDao.eliminar(bean);
		return new ResponseEntity<WfDtoComunSyReportearchivo>(dto,HttpStatus.OK);
	}	
	
	/**
	 * ARMAS MIGRADO
	 * @param paginacion
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(notes = "-Descripcion: Eliminar | WfDtoComunSyReportearchivo | Parametros de salida: WfDtoComunSyReportearchivo", 
			value = "SY-REPARCH-CLISPAG", tags = {"SISTEMA", "REPORTE ARCHIVO"})
	@Transactional(readOnly = true)
	@PutMapping(value = "/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarPaginado(@RequestBody FiltroComunSyReportearchivoWf paginacion)
			throws Exception {
		return new ResponseEntity<DominioPaginacion>(syReportearchivoDao.archivolistarConPaginacion(paginacion),
				HttpStatus.OK);
	}
	

}
