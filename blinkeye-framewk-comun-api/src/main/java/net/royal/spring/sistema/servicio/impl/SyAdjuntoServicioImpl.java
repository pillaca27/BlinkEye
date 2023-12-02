package net.royal.spring.sistema.servicio.impl;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dao.impl.SyAdjuntoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyAdjunto;
import net.royal.spring.sistema.dominio.BeanSyAdjuntoPk;
import net.royal.spring.sistema.servicio.validar.SyAdjuntoServicioValidar;

@Service (value = "BeanServicioSyAdjunto")
public class SyAdjuntoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyAdjunto";
	private static Logger logger = LogManager.getLogger(SyAdjuntoServicioImpl.class);

	@Autowired
	private SyAdjuntoDaoImpl syAdjuntoDao;

	@Autowired
	private SyAdjuntoServicioValidar validar;

	@Transactional
	public BeanSyAdjunto coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto) throws Exception {
		// GP - RUTAFILE : DARIO LO CAMBIO
		String ruta = this.parametroObtenerExplicacion("SY", "RUTAADJUN");		
		syAdjunto = syAdjuntoDao.obtenerMaximoId(syAdjunto);		
		String rutaCompleta = ruta+ syAdjunto.getRuta() +"\\"+ syAdjunto.getPk().getClavetabla() +"\\"+ syAdjunto.getPk().getSecuencia();
		logger.debug(rutaCompleta);
		File tmpFile = new File(rutaCompleta);
		if (!tmpFile.exists())
			tmpFile.mkdirs();
		
		// valores por defecto - preparando objeto
		syAdjunto = validar.prepararInsertar(usuarioActual, syAdjunto);
		syAdjunto.getPk().setSecuencia(1);
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		String nombreArchivo = syAdjunto.getArchivo();
		syAdjunto.setArchivo(rutaCompleta+ "\\" + syAdjunto.getArchivo());
		
		
		if (!UString.esNuloVacio(syAdjunto.getArchivoadjuntostring())) {
			byte imagen[] = Base64.getDecoder().decode(syAdjunto.getArchivoadjuntostring());

			if (!UValidador.esNulo(imagen)) {
				syAdjunto.setArchivodata(null);
				syAdjunto.setArchivodata(imagen);
			}
		}
		
		syAdjunto = syAdjuntoDao.coreInsertar(syAdjunto);			
				
		
		if (!UString.estaVacio(syAdjunto.getArchivoadjuntostring())) {
			syAdjunto.setArchivoadjunto(syAdjunto.getArchivoadjuntostring().getBytes(StandardCharsets.UTF_8));
		}
		
		UFile.guardarContenidoFile(rutaCompleta, nombreArchivo, syAdjunto.getArchivoadjunto());
		
		return syAdjunto;
		
	}
	
	@Transactional
	public BeanSyAdjunto coreInsertarLstWH(SeguridadUsuarioActual usuarioActual, List<BeanSyAdjunto>  beanSyAdjunto) throws Exception {
		
		if(beanSyAdjunto.size()>0) {
			List<BeanSyAdjunto>  lstDetalle= syAdjuntoDao.listarPortabla(beanSyAdjunto.get(0).getPk().getNombretabla(), beanSyAdjunto.get(0).getPk().getClavetabla());
			
			
			for (BeanSyAdjunto syAdjunto :lstDetalle ) {
				
				syAdjunto = validar.prepararEliminar(usuarioActual, syAdjunto);
				
				// validaciones de negocio
				List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syAdjunto);
				if (!lst.isEmpty())
					throw new UException(lst);
				
				String ruta = "D:\\temporal";	
				//sbyAdjunto = syAdjuntoDao.obtenerMaximoId(syAdjunto);		
				String rutaCompleta = ruta +"\\"+"WH"+ "\\"+syAdjunto.getArchivo();
				String nombreArchivo = syAdjunto.getArchivo();
				UFile.eliminarContenidoFile(rutaCompleta, nombreArchivo, syAdjunto.getArchivoadjunto());
				// transaccion
				syAdjuntoDao.eliminar(syAdjunto);
				
			}
		}
		

		for (BeanSyAdjunto syAdjunto :beanSyAdjunto ) {
			
			String ruta = "D:\\temporal";			
			syAdjunto = syAdjuntoDao.obtenerMaximoId(syAdjunto);		
			String rutaCompleta = ruta +"\\"+"WH";
			logger.debug(rutaCompleta);
			File tmpFile = new File(rutaCompleta);
			if (!tmpFile.exists())
				tmpFile.mkdirs();
			
			// valores por defecto - preparando objeto
			syAdjunto = validar.prepararInsertar(usuarioActual, syAdjunto);
			syAdjunto.getPk().setSecuencia(1);
			// validaciones de negocio
			List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syAdjunto);
			if (!lst.isEmpty())
				throw new UException(lst);
			
			// transaccion
			String nombreArchivo = syAdjunto.getArchivo();
			syAdjunto.setArchivo(nombreArchivo);
			
			
			if (!UString.esNuloVacio(syAdjunto.getArchivoadjuntostring())) {
				byte imagen[] = Base64.getDecoder().decode(syAdjunto.getArchivoadjuntostring());

				if (!UValidador.esNulo(imagen)) {
					syAdjunto.setArchivodata(null);
					syAdjunto.setArchivodata(imagen);
				}
			}
			
			syAdjunto = syAdjuntoDao.coreInsertar(syAdjunto);			
					
			
			if (!UString.estaVacio(syAdjunto.getArchivoadjuntostring())) {
				syAdjunto.setArchivoadjunto(syAdjunto.getArchivoadjuntostring().getBytes(StandardCharsets.UTF_8));
			}else {
				syAdjunto.setArchivoadjunto(syAdjunto.getArchivodata());
			}
			
			UFile.guardarContenidoFile(rutaCompleta, nombreArchivo, syAdjunto.getArchivoadjunto());
			
		}
		
		return new BeanSyAdjunto();
		
	}
	
	@Transactional
	public BeanSyAdjunto coreInsertarWH(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto) throws Exception {

		String ruta = "D:\\temporal";			
		syAdjunto = syAdjuntoDao.obtenerMaximoId(syAdjunto);		
		String rutaCompleta = ruta +"\\"+"WH";
		logger.debug(rutaCompleta);
		File tmpFile = new File(rutaCompleta);
		if (!tmpFile.exists())
			tmpFile.mkdirs();
		
		// valores por defecto - preparando objeto
		syAdjunto = validar.prepararInsertar(usuarioActual, syAdjunto);
		syAdjunto.getPk().setSecuencia(1);
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		String nombreArchivo = syAdjunto.getArchivo();
		syAdjunto.setArchivo(nombreArchivo);
		
		
		if (!UString.esNuloVacio(syAdjunto.getArchivoadjuntostring())) {
			byte imagen[] = Base64.getDecoder().decode(syAdjunto.getArchivoadjuntostring());

			if (!UValidador.esNulo(imagen)) {
				syAdjunto.setArchivodata(null);
				syAdjunto.setArchivodata(imagen);
			}
		}
		
		syAdjunto = syAdjuntoDao.coreInsertar(syAdjunto);			
				
		
		if (!UString.estaVacio(syAdjunto.getArchivoadjuntostring())) {
			syAdjunto.setArchivoadjunto(syAdjunto.getArchivoadjuntostring().getBytes(StandardCharsets.UTF_8));
		}
		
		UFile.guardarContenidoFile(rutaCompleta, nombreArchivo, syAdjunto.getArchivoadjunto());
		
		return syAdjunto;
		
	}
	
	@Transactional
	public BeanSyAdjunto coreActualizarWH(SeguridadUsuarioActual usuarioActual, BeanSyAdjunto syAdjunto) throws Exception {
		// valores por defecto - preparando objeto
		
		String ruta = "D:\\temporal";			
		//sbyAdjunto = syAdjuntoDao.obtenerMaximoId(syAdjunto);		
		String rutaCompleta = ruta +"\\"+"WH";
		
		File tmpFile = new File(rutaCompleta);
		if (!tmpFile.exists())
			tmpFile.mkdirs();
		
		syAdjunto = validar.prepararActualizar(usuarioActual, syAdjunto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		String nombreArchivo = syAdjunto.getArchivo();
		syAdjunto.setArchivo(nombreArchivo);
				
		syAdjunto.setArchivoadjunto(syAdjunto.getArchivodata());
		UFile.guardarContenidoFile(rutaCompleta, nombreArchivo, syAdjunto.getArchivoadjunto());
		
		if (!UString.esNuloVacio(syAdjunto.getArchivoadjuntostring())) {
			byte imagen[] = Base64.getDecoder().decode(syAdjunto.getArchivoadjuntostring());

			if (!UValidador.esNulo(imagen)) {
				syAdjunto.setArchivodata(null);
				syAdjunto.setArchivodata(imagen);
			}
		}
		
		BeanSyAdjunto syAdjunto2 = syAdjuntoDao.obtenerPorId(syAdjunto.getPk());
		 UFile.eliminarContenidoFile(rutaCompleta, syAdjunto2.getArchivo(), syAdjunto2.getArchivoadjunto());
		 
		syAdjunto2.setArchivo(syAdjunto.getArchivo());
		syAdjunto2.setArchivodata(syAdjunto.getArchivodata());
		syAdjunto2.setArchivoadjunto(syAdjunto.getArchivoadjunto());
		syAdjunto2.setComentario(syAdjunto.getComentario());
		
	    syAdjunto = syAdjuntoDao.coreActualizar(syAdjunto2);
	    
	   
		return syAdjunto;
	}

	@Transactional
	public void coreEliminarWH(SeguridadUsuarioActual usuarioActual, BeanSyAdjunto syAdjunto) throws Exception {
		// valores por defecto - preparando objeto
		syAdjunto = validar.prepararEliminar(usuarioActual, syAdjunto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		String ruta = "D:\\temporal";	
		//sbyAdjunto = syAdjuntoDao.obtenerMaximoId(syAdjunto);		
		String rutaCompleta = ruta +"\\"+"WH";
		String nombreArchivo = syAdjunto.getArchivo();
		UFile.eliminarContenidoFile(rutaCompleta, nombreArchivo, syAdjunto.getArchivoadjunto());
		// transaccion
		syAdjuntoDao.eliminar(syAdjunto);
	}
	
	@Transactional
	public BeanSyAdjunto coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyAdjunto syAdjunto) throws Exception {
		// valores por defecto - preparando objeto
		// GP - RUTAFILE : DARIO LO CAMBIO
		String ruta = this.parametroObtenerExplicacion("SY", "RUTAADJUN");		
		//sbyAdjunto = syAdjuntoDao.obtenerMaximoId(syAdjunto);		
		String rutaCompleta = ruta+ syAdjunto.getRuta() +"\\"+ syAdjunto.getPk().getClavetabla() +"\\"+ syAdjunto.getPk().getSecuencia();
		
		File tmpFile = new File(rutaCompleta);
		if (!tmpFile.exists())
			tmpFile.mkdirs();
		
		syAdjunto = validar.prepararActualizar(usuarioActual, syAdjunto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		String nombreArchivo = syAdjunto.getArchivo();
		syAdjunto.setArchivo(rutaCompleta+ "\\" + syAdjunto.getArchivo());
		
		if (!UString.estaVacio(syAdjunto.getArchivoadjuntostring())) {
			syAdjunto.setArchivoadjunto(syAdjunto.getArchivoadjuntostring().getBytes(StandardCharsets.UTF_8));
		}
		
		UFile.guardarContenidoFile(rutaCompleta, nombreArchivo, syAdjunto.getArchivoadjunto());
		
		if (!UString.esNuloVacio(syAdjunto.getArchivoadjuntostring())) {
			byte imagen[] = Base64.getDecoder().decode(syAdjunto.getArchivoadjuntostring());

			if (!UValidador.esNulo(imagen)) {
				syAdjunto.setArchivodata(null);
				syAdjunto.setArchivodata(imagen);
			}
		}
		
		BeanSyAdjunto syAdjunto2 = syAdjuntoDao.obtenerPorId(syAdjunto.getPk());
		
		syAdjunto2.setArchivo(syAdjunto.getArchivo());
		syAdjunto2.setArchivodata(syAdjunto.getArchivodata());
		syAdjunto2.setArchivoadjunto(syAdjunto.getArchivoadjunto());
		syAdjunto2.setComentario(syAdjunto.getComentario());
		
	    syAdjunto = syAdjuntoDao.coreActualizar(syAdjunto2);
		return syAdjunto;
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyAdjunto syAdjunto) throws UException {
		// valores por defecto - preparando objeto
		syAdjunto = validar.prepararEliminar(usuarioActual, syAdjunto);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syAdjunto);
		if (!lst.isEmpty())
			throw new UException(lst);
		
		// transaccion
		syAdjuntoDao.eliminar(syAdjunto);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyAdjuntoPk pk) throws UException {
		BeanSyAdjunto syAdjunto = syAdjuntoDao.obtenerPorId(pk);
		coreEliminar(usuarioActual,syAdjunto);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pnombretabla,String pclavetabla,Integer psecuencia) throws UException {
		coreEliminar(usuarioActual,new BeanSyAdjuntoPk( pnombretabla, pclavetabla, psecuencia));
	}
	
	
	@Transactional
	public List<BeanSyAdjunto> listar(SeguridadUsuarioActual usuarioActual,BeanSyAdjunto syAdjunto) throws UException {
		// transaccion
		return syAdjuntoDao.listarPortabla(syAdjunto.getPk().getNombretabla(), syAdjunto.getPk().getClavetabla());
	}
	

	

}
