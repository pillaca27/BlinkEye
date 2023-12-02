package net.royal.spring.contabilidad.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCashflow;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCashflowmajor;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCashflowmst;
import net.royal.spring.contabilidad.dominio.filtro.FiltroComunAcCashflow;
import net.royal.spring.core.dominio.dto.DtoComunEmpleado;
import net.royal.spring.core.dominio.filtro.FiltroComunEmpleado;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/contabilidad/accashflowcomun")
@CrossOrigin(origins = "*")
public class AcCashflowComunRest extends GenericoHibernateRest {

	private static Logger logger = LogManager.getLogger(AcCashflowComunRest.class);

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public AcCashflowComunRest() {
		super("accashflow");
	}
	
	@ApiOperation(notes = "Listar paginado. Entrada: FiltroComunAcCashflow. Retorno: List DtoComunAcAfecompany",					
			nickname="AC_CASHFLOW_CLISTARPAG", value = "Listar paginado", tags = {"AC_CASHFLOW", "LISTAR", "FILTRO"})
	@Transactional
	@PutMapping(value = "/listarpaginado", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DominioPaginacion> listarpaginado(@RequestBody FiltroComunAcCashflow filtro) {
		if (UString.estaVacio(filtro.getDescripcion()))
			filtro.setDescripcion(null);
		
		if (UString.estaVacio(filtro.getCodigo()))
			filtro.setCodigo(null);
		
		DominioPaginacion paginacion = filtro.getPaginacion();
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();			
		
		
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		parametros.add(new DominioParametroPersistencia("p_tiporegistro", String.class, filtro.getTiporegistro()));
		parametros.add(new DominioParametroPersistencia("p_tipooperacion", String.class, filtro.getTipooperacion()));
		parametros.add(new DominioParametroPersistencia("p_grupoflujo", String.class, filtro.getGrupoflujo()));		
		
		cantidadEncontrados = contar("accashflow.contar", parametros);
		List lstResultado = listarConPaginacion(paginacion, parametros, "accashflow.listar",
				DtoComunAcCashflow.class);
		
		paginacion.setPaginacionListaResultado(lstResultado);
		paginacion.setPaginacionRegistrosEncontrados(cantidadEncontrados);
		return new ResponseEntity<DominioPaginacion>(paginacion, HttpStatus.OK);
	}
	

		
	
}
