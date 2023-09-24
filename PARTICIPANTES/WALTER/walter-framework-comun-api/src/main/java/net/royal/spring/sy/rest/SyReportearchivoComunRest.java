package net.royal.spring.sy.rest;

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
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.sy.dao.impl.SyReporteDaoImpl;
import net.royal.spring.sy.dao.impl.SyReportearchivoDaoImpl;
import net.royal.spring.sy.dominio.BeanSyReporte;
import net.royal.spring.sy.dominio.BeanSyReportePk;
import net.royal.spring.sy.dominio.BeanSyReportearchivo;
import net.royal.spring.sy.dominio.BeanSyReportearchivoPk;
import net.royal.spring.sy.dominio.dto.DtoComunSyReportearchivo;
import net.royal.spring.sy.dominio.filtro.FiltroComunSyReportearchivo;
import net.royal.spring.sy.servicio.impl.SyReportearchivoServicioImpl;
import net.royal.spring.sy.servicio.validar.SyReportearchivoServicioValidar;

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

	
	@Autowired
	private SyReporteDaoImpl syReporteDao;
	
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
		
		BeanSyReportePk id = new BeanSyReportePk();
		id.setAplicacioncodigo(dto.getAplicacioncodigo());
		id.setReportecodigo(dto.getReportecodigo());
		BeanSyReporte reporte = syReporteDao.obtenerPorId(id);
		reporte.setAsunto(dto.getAsuntoPrincipal());
		syReporteDao.actualizar(reporte);
		
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
	@Transactional
	@PutMapping(value = "/obtenerdto", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSyReportearchivo> obtenerdtoporid(@RequestBody DtoComunSyReportearchivo pk)
			throws Exception {
		pk = syReportearchivoDao.obtenerDtoPorId(pk);		
		
		//Agregar el asunto principal
		BeanSyReportePk id = new BeanSyReportePk();
		id.setAplicacioncodigo(pk.getAplicacioncodigo());
		id.setReportecodigo(pk.getReportecodigo());
		BeanSyReporte reporte = syReporteDao.obtenerPorId(id);
		if(reporte==null){
			reporte = new BeanSyReporte();
			reporte.getPk().setAplicacioncodigo(pk.getAplicacioncodigo());
			reporte.getPk().setReportecodigo(pk.getReportecodigo());
			syReporteDao.registrar(reporte);
		}

		pk.setAsuntoPrincipal(reporte.getAsunto());
		
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
		bean.setAsunto(dto.getAsunto());
		bean = servicio.archivosolicitudActualizar(getUsuarioActual(), bean);	
		
		BeanSyReportePk idd = new BeanSyReportePk();
		idd.setAplicacioncodigo(dto.getAplicacioncodigo());
		idd.setReportecodigo(dto.getReportecodigo());
		BeanSyReporte reporte = syReporteDao.obtenerPorId(idd);
		reporte.setAsunto(dto.getAsuntoPrincipal());
		syReporteDao.actualizar(reporte);
		
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
	
}
