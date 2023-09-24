package net.royal.spring.core.rest;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Column;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.DatatypeConverter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.royal.spring.core.dao.impl.CompanyownerrecursoDaoImpl;
import net.royal.spring.core.dao.impl.CorrelativosmastDaoImpl;
import net.royal.spring.core.dominio.BeanCompanyownerrecurso;
import net.royal.spring.core.dominio.BeanCompanyownerrecursoPk;
import net.royal.spring.core.dominio.dto.DtoComunCompanyownerrecurso;
import net.royal.spring.core.servicio.validar.CompanyownerrecursosServicioValidar;
import net.royal.spring.core.servicio.validar.CorrelativosmastServicioValidar;
import net.royal.spring.framework.constante.ConstanteDatos;
import net.royal.spring.framework.modelo.CompanyownerrecursoTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.web.rest.GenericoHibernateRest;

@RestController
@RequestMapping("/spring/core/companyownerrecursocomun")
@CrossOrigin(origins = "*")
public class CompanyownerrecursoComunRest extends GenericoHibernateRest{

	private static Logger logger = LogManager.getLogger(CompanyownerComunRest.class);

	
	@Autowired
	private CompanyownerrecursoDaoImpl dao;
	
	@Autowired
	private CompanyownerrecursosServicioValidar validar;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public CompanyownerrecursoComunRest() {
		super("companyownerrecurso");
	}
	
	/**
	 * FALTA
	 * no se implemento en angular aun / es necesario ????
	 * @param dto
	 * @return
	 */
	@Transactional
	@PutMapping(value = "/obtenerImagenComoCadena", produces = MediaType.APPLICATION_JSON_VALUE)
	public String obtenerImagenComoCadena(CompanyownerrecursoTransaccion dto)
    {
		String urlImagen = "";
		String compania = dto.getCompania() ;
		ConstanteDatos.TIPO_IMAGEN tipoImagen = dto.getTipoImagen(); 
		String periodo = dto.getPeriodo();
		String tipoReporte = dto.getTipoReporte();					
		
        if (tipoImagen == ConstanteDatos.TIPO_IMAGEN.LOGO)
        {
        	String base64 = "";
            BeanCompanyownerrecursoPk pk = new BeanCompanyownerrecursoPk();
            pk.setPeriodo( periodo == null ? "999999" : periodo );
            pk.setTiporecurso( ConstanteDatos.CODIGO_IMAGEN_COMPANIA);
            pk.setCompanyowner(compania);
            BeanCompanyownerrecurso recurso = dao.obtenerPorId(pk);                       
			byte[] decoded = Base64Utils.decode(recurso.Recurso);
			if (recurso != null) base64 = "data:image/png;base64," + new String(decoded);
            urlImagen = base64;
        }
        if (tipoImagen == ConstanteDatos.TIPO_IMAGEN.FIRMA)
        {
        	 String base64 = "";
             BeanCompanyownerrecursoPk pk = new BeanCompanyownerrecursoPk();
             pk.setPeriodo( periodo == null ? "999999" : periodo );             
             pk.setTiporecurso( tipoReporte);
             pk.setCompanyowner(compania);

             BeanCompanyownerrecurso recurso = dao.obtenerPorId(pk);

            byte[] decoded = Base64Utils.decode(recurso.Recurso);
 			if (recurso != null) base64 = "data:image/png;base64," + new String(decoded);

             urlImagen = base64;
        }
        if (tipoImagen == ConstanteDatos.TIPO_IMAGEN.IMAGEN)
        {        	
        	String base64 = "";
              BeanCompanyownerrecursoPk pk = new BeanCompanyownerrecursoPk();
              pk.setPeriodo( periodo == null ? "999999" : periodo );              
              pk.setTiporecurso( ConstanteDatos.CODIGO_IMAGEN_COMPANIA);
              pk.setCompanyowner(compania);

              BeanCompanyownerrecurso recurso = dao.obtenerPorId(pk);

            byte[] decoded = Base64Utils.decode(recurso.Recurso);
  			if (recurso != null) base64 = new String(decoded);                           

              urlImagen = base64;
        }
        return urlImagen;
    }


	
	@Transactional
	@PutMapping(value = "/listarporrecurso",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DtoComunCompanyownerrecurso>> listarPorRecurso(@RequestBody DtoTabla dto)
	{
		List lista = dao.listarPorRecurso(dto.getCodigo());				  
		List<BeanCompanyownerrecurso> listacompania = new ArrayList<BeanCompanyownerrecurso>();
		List<DtoComunCompanyownerrecurso> listacompaniaDto = new ArrayList<DtoComunCompanyownerrecurso>();		
		listacompania = lista;		           
		for (BeanCompanyownerrecurso recurso : listacompania) {
			DtoComunCompanyownerrecurso dt = new DtoComunCompanyownerrecurso();
			dt.setCompanyowner(recurso.getPk().getCompanyowner());
			dt.setTiporecurso(recurso.getPk().getTiporecurso());
			dt.setPeriodo(recurso.getPk().getPeriodo());			
			dt.setAuxString(recurso.getAuxString());
			dt.setNombrerecurso(recurso.getNombrerecurso());
			dt.setUltimousuario(recurso.getUltimousuario());
			dt.setUltimafechamodif(recurso.getUltimafechamodif());
			
			dt.setAuxCompanyowner(dao.obtenerNombreCompania(dt.getCompanyowner()));
			if (recurso.getRecurso() != null)
			{
				String decodedString = Base64.getEncoder().encodeToString(recurso.getRecurso());
				dt.setAuxString(decodedString);
			}
			listacompaniaDto.add(dt);
		}
		return new ResponseEntity<List<DtoComunCompanyownerrecurso>>(listacompaniaDto, HttpStatus.OK);
	}

	@Transactional
	@PutMapping(value = "/eliminarportiporecurso",  produces = MediaType.APPLICATION_JSON_VALUE)
	public void eliminarPorTipoRecurso(@RequestBody DtoComunCompanyownerrecurso dto)
	{
		BeanCompanyownerrecurso bean = new BeanCompanyownerrecurso();
		bean.getPk().setCompanyowner(dto.getCompanyowner());
		bean.getPk().setPeriodo(dto.getPeriodo());
		bean.getPk().setTiporecurso(dto.getTiporecurso());
				
		bean = dao.obtenerPorId(bean.getPk());
				
		if(bean != null) {
			dao.eliminar(bean);
		}
	        			  
	}

	@Transactional
	@PutMapping(value="/obtenerporid", produces = MediaType.APPLICATION_JSON_VALUE)
	public DtoComunCompanyownerrecurso obtenerPorId(@RequestBody DtoComunCompanyownerrecurso dto)
	{
		BeanCompanyownerrecursoPk pk = new BeanCompanyownerrecursoPk();
		pk.setTiporecurso ( dto.getTiporecurso());
		pk.setPeriodo ( dto.getPeriodo());
		pk.setCompanyowner ( dto.getCompanyowner());
		
		BeanCompanyownerrecurso bean = dao.obtenerPorId(pk);
		if(bean != null) {
			dto.setTiporecurso(bean.getPk().getTiporecurso());
			dto.setPeriodo(bean.getPk().getPeriodo());
			dto.setCompanyowner(bean.getPk().getCompanyowner());
			return dto;
		}
		return null;
	}

				
	@Transactional
	@PostMapping(value = "/registrar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompanyownerrecurso> registrar(@RequestBody DtoComunCompanyownerrecurso dto) throws Exception {
		BeanCompanyownerrecurso bean = new BeanCompanyownerrecurso();
		bean.getPk().setCompanyowner(dto.getCompanyowner());
		bean.getPk().setPeriodo(dto.getPeriodo());
		bean.getPk().setTiporecurso(dto.getTiporecurso());
		bean.setAuxString(dto.getAuxString());
		bean.setNombrerecurso(dto.getNombrerecurso());
		bean.setUltimafechamodif(dto.getUltimafechamodif());
		bean.setUltimousuario(dto.getUltimousuario());
		if (bean.getAuxString() != null && bean.getAuxString() != "")
		{
			Integer inicio = bean.getAuxString().indexOf(',') + 1;
			Integer fin = bean.getAuxString().length();
			String archivo = bean.getAuxString().substring(inicio, fin - inicio);
			byte[] name = DatatypeConverter.parseBase64Binary(archivo);
			bean.setRecurso(name);
		}
		List<DominioMensajeUsuario> lst = validar.coreInsertar(this.getUsuarioActual(), bean);
		if (!lst.isEmpty())
		{
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return new ResponseEntity<DtoComunCompanyownerrecurso>(dto, HttpStatus.CREATED);
		}
		dao.registrar( bean);		
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunCompanyownerrecurso>(dto, HttpStatus.CREATED);
	}
				
				
	@Transactional
	@PostMapping(value = "/actualizar",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DtoComunCompanyownerrecurso> actualizar(@RequestBody DtoComunCompanyownerrecurso dto) throws Exception {
		BeanCompanyownerrecurso bean = new BeanCompanyownerrecurso();
		bean.getPk().setCompanyowner(dto.getCompanyowner());
		bean.getPk().setPeriodo(dto.getPeriodo());
		bean.getPk().setTiporecurso(dto.getTiporecurso());
		bean.setAuxString(dto.getAuxString());
		bean.setNombrerecurso(dto.getNombrerecurso());
		bean.setUltimafechamodif(dto.getUltimafechamodif());
		bean.setUltimousuario(dto.getUltimousuario());
		if (bean.getAuxString() != null && bean.getAuxString() != "")
		{
			Integer inicio = bean.getAuxString().indexOf(',') + 1;
			Integer fin = bean.getAuxString().length();
			String archivo = bean.getAuxString().substring(inicio, fin - inicio);
			byte[] name = DatatypeConverter.parseBase64Binary(archivo);
			bean.setRecurso(name);
		}
		List<DominioMensajeUsuario> lst = validar.coreActualizar(this.getUsuarioActual(), bean);
		if (!lst.isEmpty())
		{
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return new ResponseEntity<DtoComunCompanyownerrecurso>(dto, HttpStatus.CREATED);
		}
		dao.actualizar( bean);	
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return new ResponseEntity<DtoComunCompanyownerrecurso>(dto, HttpStatus.CREATED);
	}
	
	

}
