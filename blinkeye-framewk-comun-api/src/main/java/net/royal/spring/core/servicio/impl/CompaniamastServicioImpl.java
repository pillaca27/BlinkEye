package net.royal.spring.core.servicio.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
//import net.royal.spring.logistica.boot.SpringWhConstanteBoot;
import net.royal.spring.core.dao.impl.CompaniamastDaoImpl;

import net.royal.spring.core.dominio.BeanCompaniamast;
import net.royal.spring.core.dominio.BeanCompaniamastPk;
import net.royal.spring.core.dominio.dto.DtoComunCompaniamast;
import net.royal.spring.core.dominio.dto.DtoComunCompanyowner;
import net.royal.spring.core.dominio.dto.DtoComunReportingcompany;
//import net.royal.spring.core.dominio.dto.DtoServicioximpuesto;
//import net.royal.spring.core.dominio.lista.DtlReportingcompany;
import net.royal.spring.core.servicio.validar.CompaniamastServicioValidar;

@Service (value = "BeanServicioCompaniamast")
public class CompaniamastServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioCompaniamast";
	private static Logger logger = LogManager.getLogger(CompaniamastServicioImpl.class);

	@Autowired
	private CompaniamastDaoImpl companiamastDao;

	@Autowired
	private CompaniamastServicioValidar validar;
	
	@Autowired
	private CompanyownerServicioImpl companyownerServicioImpl;
	
	@Autowired
	private ReportingcompanyServicioImpl reportingcompanyServicioImpl;
	
	@Transactional
	public DtoComunCompaniamast coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunCompaniamast dto) throws Exception {
		BeanCompaniamast acSucursalgrupo = companiamastDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo,dto);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}


	@Transactional
	public DtoComunCompaniamast coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunCompaniamast dto) throws Exception {
		BeanCompaniamast acSucursalgrupo = companiamastDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = dto.obtenerBeanActualizar(acSucursalgrupo);
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo,dto);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	public DtoComunCompaniamast  coreAnularPorUuid(SeguridadUsuarioActual usuarioActual, DtoComunCompaniamast  dto)
			throws Exception {
		BeanCompaniamast bean = companiamastDao.obtenerPorUuid(dto.getUuid());
		bean = validar.prepararAuditoria(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, bean);
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return dto;
		}
		bean.setEstado("N");
		bean = companiamastDao.coreActualizar(bean);
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public String registrarImagen(MultipartFile archivo,String logoanterior) throws IOException {
		
		/* AGREGAR PARA COTIZACIONES  */
		String nombreArchivo=archivo.getOriginalFilename().trim();
		Path rutaArchivo = getPathCotizaciones(nombreArchivo);
		//ELIMINAR LOGO ANTERIOR
		if(!UString.estaVacio(logoanterior)){
			Path rutaArchivoanterior = getPathCotizaciones(logoanterior);
			File archivoFotoAnterior = rutaArchivoanterior.toFile();
			if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
				archivoFotoAnterior.delete();
			}
		}
		Files.copy(archivo.getInputStream(),rutaArchivo);	
		
		/* AGREGAR PARA ORDEN COMPRA  */
		Path rutaArchivo2 = getPathOrdenCompra(nombreArchivo);
		//ELIMINAR LOGO ANTERIOR
		if(!UString.estaVacio(logoanterior)){
			Path rutaArchivoanterior2 = getPathOrdenCompra(logoanterior);
			File archivoFotoAnterior2 = rutaArchivoanterior2.toFile();
			if(archivoFotoAnterior2.exists() && archivoFotoAnterior2.canRead()) {
				archivoFotoAnterior2.delete();
			}
		}
		
		Files.copy(archivo.getInputStream(),rutaArchivo2);	
		return nombreArchivo;
	}
	
	public void eliminarImagen(String logo) {
		
		Path rutaArchivoanterior = getPathCotizaciones(logo);
		File archivoFotoAnterior = rutaArchivoanterior.toFile();
		if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
			archivoFotoAnterior.delete();
		}
		
		Path rutaArchivoanterior2 = getPathOrdenCompra(logo);
		File archivoFotoAnterior2 = rutaArchivoanterior2.toFile();
		if(archivoFotoAnterior2.exists() && archivoFotoAnterior2.canRead()) {
			archivoFotoAnterior2.delete();
		}
	}
	
	public Path getPathCotizaciones(String nombreFoto) {
		String url = UFile.rutaFisicaWebApp() + File.separator + ConstanteBoot.RECURSOS_GLOBAL + File.separator + "cotizaciones"
				+ File.separator +"invitar-cotizarproveedores";
		return Paths.get(url).resolve(nombreFoto).toAbsolutePath();
	}

	public Path getPathOrdenCompra(String nombreFoto) {
		String url = UFile.rutaFisicaWebApp() + File.separator + ConstanteBoot.RECURSOS_GLOBAL + File.separator + "ordencompra";
		return Paths.get(url).resolve(nombreFoto).toAbsolutePath();
	}
	
	@Transactional
	public DtoComunCompaniamast coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunCompaniamast dto) throws UException {
		BeanCompaniamast companiamast = dto.obtenerBean();
		companiamast = coreInsertar(usuarioActual, companiamast,dto);
		dto.setTransaccionEstado(companiamast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(companiamast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCompaniamast coreInsertar(SeguridadUsuarioActual usuarioActual,BeanCompaniamast companiamast,DtoComunCompaniamast dto) throws UException {
		// valores por defecto - preparando objeto
		companiamast = validar.prepararInsertar(usuarioActual, companiamast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, companiamast);
		if (!lst.isEmpty()) {
			companiamast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			companiamast.setTransaccionListaMensajes(lst);
			return companiamast;
		}
		
		// transaccion
		companiamast = companiamastDao.coreInsertar(companiamast);
		
		if(dto.getDetalle1().size() > 0) {
			
			for (DtoComunCompanyowner detalle : dto.getDetalle1()) {
				
				if(detalle.getAccion().equals("N")) {
					detalle.setCompany(companiamast.getPk().getCompaniacodigo());
					detalle.setCompanyowner(companiamast.getPk().getCompaniacodigo() + detalle.getOwner());
					companyownerServicioImpl.coreInsertar(usuarioActual, detalle);
				}		
			}
		}
		
		if(dto.getDetalle2().size() > 0) {
			
			for (DtoComunReportingcompany detalle : dto.getDetalle2()) {
				
				if(detalle.getAccion().equals("N")) {
					detalle.setCompaniacodigo(companiamast.getPk().getCompaniacodigo());
					reportingcompanyServicioImpl.coreInsertar(usuarioActual, detalle);
				}		
			}
		}
		
		companiamast.setTransaccionEstado(DominioTransaccion.OK);
		companiamast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return companiamast;
	}

	@Transactional
	public DtoComunCompaniamast coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunCompaniamast dto) throws UException {
		BeanCompaniamast companiamast = dto.obtenerBean();
		companiamast = coreActualizar(usuarioActual, companiamast,dto);
		dto.setTransaccionEstado(companiamast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(companiamast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCompaniamast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanCompaniamast companiamast,DtoComunCompaniamast dto) throws UException {
		// valores por defecto - preparando objeto
		companiamast = validar.prepararActualizar(usuarioActual, companiamast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, companiamast);
		if (!lst.isEmpty()) {
			companiamast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			companiamast.setTransaccionListaMensajes(lst);
			return companiamast;
		}
		
		// transaccion
		if(dto.getDetalle1().size() > 0) {
			
			for (DtoComunCompanyowner detalle : dto.getDetalle1()) {
				
				if(detalle.getAccion().equals("N")) {
					detalle.setCompany(companiamast.getPk().getCompaniacodigo());
					detalle.setCompanyowner(companiamast.getPk().getCompaniacodigo() + detalle.getOwner());
					companyownerServicioImpl.coreInsertar(usuarioActual, detalle);
				}
				else if(detalle.getAccion().equals("A")) {
					companyownerServicioImpl.coreActualizar(usuarioActual, detalle);
				}
				else if(detalle.getAccion().equals("E")) {				
					companyownerServicioImpl.coreEliminar(usuarioActual, detalle);
				}				
			}
		}
		
		if(dto.getDetalle2().size() > 0) {
			
			for (DtoComunReportingcompany detalle : dto.getDetalle2()) {
				
				if(detalle.getAccion().equals("N")) {
					detalle.setCompaniacodigo(companiamast.getPk().getCompaniacodigo());
					reportingcompanyServicioImpl.coreInsertar(usuarioActual, detalle);
				}	
				else if(detalle.getAccion().equals("A")) {
					reportingcompanyServicioImpl.coreActualizar(usuarioActual, detalle);
				}
				else if(detalle.getAccion().equals("E")) {
					reportingcompanyServicioImpl.coreEliminar(usuarioActual, detalle);
				}	
			}
		}
		
	    companiamast = companiamastDao.coreActualizar(companiamast);
	    
	    
		companiamast.setTransaccionEstado(DominioTransaccion.OK);
		companiamast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return companiamast;
	}


	@Transactional
	public DtoComunCompaniamast coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunCompaniamast dto) throws UException {
		BeanCompaniamast companiamast = dto.obtenerBean();
		companiamast = coreAnular(usuarioActual, companiamast);
		dto.setTransaccionEstado(companiamast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(companiamast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCompaniamast coreAnular(SeguridadUsuarioActual usuarioActual, BeanCompaniamast companiamast) throws UException {
		// valores por defecto - preparando objeto
		companiamast = validar.prepararAnular(usuarioActual, companiamast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, companiamast);
		if (!lst.isEmpty()) {
			companiamast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			companiamast.setTransaccionListaMensajes(lst);
			return companiamast;
		}
		
		// transaccion
	    companiamast.setEstado("I");
	    companiamast = companiamastDao.coreActualizar(companiamast);
		companiamast.setTransaccionEstado(DominioTransaccion.OK);
		companiamast.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return companiamast;
	}

	public BeanCompaniamast coreAnular(SeguridadUsuarioActual usuarioActual, BeanCompaniamastPk pk) throws UException {
		BeanCompaniamast bean = companiamastDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanCompaniamast coreAnular(SeguridadUsuarioActual usuarioActual, String pcompaniacodigo) throws UException {
		return coreAnular(usuarioActual,new BeanCompaniamastPk( pcompaniacodigo));
	}

	@Transactional
	public DtoComunCompaniamast coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunCompaniamast dto) throws UException {
		BeanCompaniamast companiamast = dto.obtenerBean();
		companiamast = coreEliminar(usuarioActual, companiamast,dto);
		dto.setTransaccionEstado(companiamast.getTransaccionEstado());
		dto.setTransaccionListaMensajes(companiamast.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanCompaniamast coreEliminar(SeguridadUsuarioActual usuarioActual, BeanCompaniamast companiamast,DtoComunCompaniamast dto) throws UException {
		// valores por defecto - preparando objeto
		companiamast = validar.prepararEliminar(usuarioActual, companiamast);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, companiamast);
		if (!lst.isEmpty()) {
			companiamast.setTransaccionEstado(DominioTransaccion.VALIDACION);
			companiamast.setTransaccionListaMensajes(lst);
			return companiamast;
		}
		
		if(dto.getDetalle1().size() > 0) {			
			for (DtoComunCompanyowner detalle : dto.getDetalle1()) {
					companyownerServicioImpl.coreEliminar(usuarioActual, detalle);
					
			}
		}
		
		if(dto.getDetalle2().size() > 0) {
			for (DtoComunReportingcompany detalle : dto.getDetalle2()) {
					reportingcompanyServicioImpl.coreEliminar(usuarioActual, detalle);	
			}
		}
		// transaccion
		companiamastDao.eliminar(companiamast);
		companiamast=new BeanCompaniamast();
		companiamast.setTransaccionEstado(DominioTransaccion.OK);
		return companiamast;
	}



}
