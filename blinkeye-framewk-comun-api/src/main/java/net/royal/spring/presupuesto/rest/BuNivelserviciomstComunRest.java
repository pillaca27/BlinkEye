package net.royal.spring.presupuesto.rest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.dao.impl.ParametrosmastDaoImpl;
import net.royal.spring.core.dominio.BeanCorrelativosmast;
import net.royal.spring.core.dominio.BeanCorrelativosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunCorrelativosmast;
import net.royal.spring.core.servicio.impl.CorrelativosmastServicioImpl;

import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.presupuesto.dominio.dto.DtoComunBuNivelserviciomst;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/presupuesto/bunivelserviciomstcomun")
@CrossOrigin(origins = "*")
public class BuNivelserviciomstComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(BuNivelserviciomstComunRest.class);

	@Autowired
	private CorrelativosmastServicioValidar validar;

	@Autowired
	private ParametrosmastDaoImpl parametrosmastDao;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BuNivelserviciomstComunRest() {
		super("bunivelserviciomst");
	}
	
	/**
	 * QQUECHOD VALIDADO
	 * @param dto
	 * @return
	 */
	@ApiOperation(notes = "-Descripcion: Listado de Niveles de Servicio Activos de la tabla bu_nivelserviciomst donde el campo nivelservicio tenga el codigo del parametro: NIVSERVREQ | Sin parametros de entrada | Parametros de salida: id, nombre", 
			value = "BU-NIVSERV-C0001", tags = {"PRESUPUESTO", "NIVEL DE SERVICIO"})
	@Transactional
	@PutMapping(value = "/listaractivosportiposervicio", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoTabla>> listarActivosPorTipoServicio(
			@ApiParam(value = "Tipo Servicio", required = true) @RequestBody DtoComunBuNivelserviciomst dto			
			) {
		logger.debug("listar Activos Nivel Servicio");
		String idCompania = getUsuarioActual().getCompaniaCodigo();
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, idCompania));
		parametros.add(new DominioParametroPersistencia("p_tiposervicio", String.class, dto.getTiposervicio()));
		String explicacion = parametrosmastDao.obtenerParametroExplicacion("NIVSERVREQ", "HR");
		StringBuilder query = new StringBuilder(this.obtenerSentenciaSqlPorQuery("bunivelserviciomst.listarActivosPorTipoServicio"));
		query = query.append(" AND   nivelservicio in (" + explicacion + ")");
		List data = this.listarPorSentenciaSQL(query, parametros, DtoTabla.class);
		
		return new ResponseEntity<List<DtoTabla>>(data,HttpStatus.OK);
	}	
	
}
