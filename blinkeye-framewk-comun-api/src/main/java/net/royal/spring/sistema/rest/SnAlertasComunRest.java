package net.royal.spring.sistema.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import net.royal.spring.core.dao.impl.PersonamastDaoImpl;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;
import net.royal.spring.framework.web.rest.GenericoRest;
import net.royal.spring.sistema.dao.impl.SnAlertaDaoImpl;
import net.royal.spring.sistema.dominio.dto.DtoComunDashboardComunicacion;
import net.royal.spring.sistema.dominio.dto.DtoComunSnAlertas;
import net.royal.spring.sistema.dominio.dto.DtoComunDashboard;
import net.royal.spring.sistema.servicio.impl.SyApiServicioImpl;

@RestController
@RequestMapping("/spring/sistema/snalertascomun")
@CrossOrigin(origins = "*")
public class SnAlertasComunRest extends GenericoRest {
	

	private static Logger logger = LogManager.getLogger(SnAlertasComunRest.class);

	
	@Autowired
	private SnAlertaDaoImpl consulta;
	
	@Autowired
	private PersonamastDaoImpl personamastDao;
	
	@ApiOperation(notes = "Listado de Alertas activas "
			+ "<br><b>Entrada : </b>Persona id <br><b>Salida : </b>Lista de DtoComunSnAlertas(id, nombre, link)", 
			value = "99-SN-ALERACT", tags = {"SN", "Alertas"})
	@Transactional
	@GetMapping(value = "/listaractivos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunDashboard> listaractivos() throws Exception {
		logger.debug("Dashboard");
		SeguridadUsuarioActual usu = getUsuarioActual();
		DtoComunDashboardComunicacion r=null;
		
		DtoComunDashboard dto = this.consulta.listaractivos(usu);
		
		r=consulta.obtenerGestionProveedorCantidad(usu.getPersonaId());
		dto.setCard1Titulo("Proveedores");
		dto.setCard1Icono("pi pi-shopping-cart");
		dto.setCard1IzquierdaTitulo("Pendientes");
		dto.setCard1IzquierdaValor(UInteger.obtenerValorEnteroSinNulo(r.getExternoId1()).toString());
		dto.setCard1DerechaTitulo("En proceso");
		dto.setCard1DerechaValor(UInteger.obtenerValorEnteroSinNulo(r.getExternoId2()).toString());
		
		r=consulta.obtenerCotizacionProveedorCantidad(usu.getPersonaId());
		dto.setCard2Titulo("Estudio de mercado");
		dto.setCard2Icono("pi pi-users");
		dto.setCard2IzquierdaTitulo("Pendientes");
		dto.setCard2IzquierdaValor(UInteger.obtenerValorEnteroSinNulo(r.getExternoId1()).toString());
		dto.setCard2DerechaTitulo("En proceso");
		dto.setCard2DerechaValor(UInteger.obtenerValorEnteroSinNulo(r.getExternoId2()).toString());
		
		r=consulta.obtenerConvocatoriaProveedorCantidad(usu.getPersonaId());
		dto.setCard3Titulo("Convocatorias");
		dto.setCard3Icono("pi pi-dollar");
		dto.setCard3IzquierdaTitulo("Pendientes");
		dto.setCard3IzquierdaValor(UInteger.obtenerValorEnteroSinNulo(r.getExternoId1()).toString());
		dto.setCard3DerechaTitulo("En proceso");
		dto.setCard3DerechaValor(UInteger.obtenerValorEnteroSinNulo(r.getExternoId2()).toString());
		
		r=consulta.obtenerSubastasProveedorCantidad(usu.getPersonaId());
		dto.setCard4Titulo("Subastas");
		dto.setCard4Icono("pi pi-comment");
		dto.setCard4IzquierdaTitulo("Pendientes");
		dto.setCard4IzquierdaValor(UInteger.obtenerValorEnteroSinNulo(r.getExternoId1()).toString());
		dto.setCard4DerechaTitulo("En proceso");
		dto.setCard4DerechaValor(UInteger.obtenerValorEnteroSinNulo(r.getExternoId2()).toString());
		
		/*dto.setLstComunicacion(new ArrayList<>());
		dto.getLstComunicacion().add(new DtoComunDashboardComunicacion("assets/demo/images/avatar/amyelsner.png","Amy Elsner","Accounting","pi pi-comment"));
		dto.getLstComunicacion().add(new DtoComunDashboardComunicacion("assets/demo/images/avatar/annafali.png","Anna Fali","Procurement","pi pi-comment"));
		dto.getLstComunicacion().add(new DtoComunDashboardComunicacion("assets/demo/images/avatar/bernardodominic.png","Bernardo Dominic","Finance","pi pi-comment"));
		dto.getLstComunicacion().add(new DtoComunDashboardComunicacion("assets/demo/images/avatar/ivanmagalhaes.png","Ivan Magalhaes","Sales","pi pi-comment"));
		dto.getLstComunicacion().add(new DtoComunDashboardComunicacion("assets/demo/images/avatar/xuxuefeng.png","Xuxue Feng","Management","pi pi-comment"));*/
		
		List<DtoComunDashboardComunicacion> lst = consulta.listarEstudioActivosPorProveedorConsultas(usu.getPersonaId());
		int index=0;
		for (DtoComunDashboardComunicacion cotizacion : lst) {
			cotizacion = consulta.obtenerGestorCotizacion(cotizacion, cotizacion.getExternoId1());
			if (cotizacion.getPersonaId()!=null) {
				String url = personamastDao.fotoObtenerRuta(cotizacion.getPersonaId());
				cotizacion.setFotoUsuario(url);
			}else {
				cotizacion.setUsuario("CAJA CUSCO");
				cotizacion.setFotoUsuario("assets/layout/images/cajacusco.png");				
			}
			lst.set(index, cotizacion);			
			index++;
		}
		dto.setLstComunicacion(lst);
		
		/******/
		logger.debug("gestionProveedoresEstado");
		String gestionProveedoresEstado = consulta.obtenerGestionProveedorEstado(usu.getPersonaId());
		dto.setGestionProveedoresEstado(gestionProveedoresEstado);
		logger.debug(gestionProveedoresEstado);
		
		return new ResponseEntity<DtoComunDashboard>(dto,HttpStatus.OK);
	}
	
		
	@Transactional
	@PutMapping(value = "/actualizaralerta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSnAlertas> actualizaralerta(@RequestBody DtoComunSnAlertas dto) {
						
		return new ResponseEntity<DtoComunSnAlertas>(this.consulta.actualizarAlerta(dto),HttpStatus.OK);
	}
	
	@Transactional
	@PutMapping(value = "/registraralerta", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunSnAlertas> registraralerta(@RequestBody DtoComunSnAlertas dto) {
						
		return new ResponseEntity<DtoComunSnAlertas>(this.consulta.registrarAlerta(dto, getUsuarioActual()),HttpStatus.OK);
	}

}
