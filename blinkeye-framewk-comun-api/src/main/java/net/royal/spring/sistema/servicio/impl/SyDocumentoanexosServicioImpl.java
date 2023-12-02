package net.royal.spring.sistema.servicio.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.core.UFile;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.sistema.dominio.BeanSyDocumentoanexos;
import net.royal.spring.sistema.dominio.BeanSyDocumentoanexosPk;
import net.royal.spring.sistema.dominio.dto.DtoComunSyDocumentoanexos;
import net.royal.spring.sistema.dao.impl.SyDocumentoanexosDaoImpl;
import net.royal.spring.sistema.servicio.validar.SyDocumentoanexosServicioValidar;

@Service (value = "BeanServicioSyDocumentoanexos")
public class SyDocumentoanexosServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioSyDocumentoanexos";
	private static Logger logger = LogManager.getLogger(SyDocumentoanexosServicioImpl.class);

	@Autowired
	private SyDocumentoanexosDaoImpl syDocumentoanexosDao;

	@Autowired
	private SyDocumentoanexosServicioValidar validar;

	@Transactional
	public DtoComunSyDocumentoanexos coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunSyDocumentoanexos dto) throws Exception {
		BeanSyDocumentoanexos syDocumentoanexos = dto.obtenerBean();
		syDocumentoanexos = coreInsertar(usuarioActual, syDocumentoanexos);
		dto.setTransaccionEstado(syDocumentoanexos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syDocumentoanexos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyDocumentoanexos coreInsertar(SeguridadUsuarioActual usuarioActual,BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		// valores por defecto - preparando objeto
		syDocumentoanexos = validar.prepararInsertar(usuarioActual, syDocumentoanexos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, syDocumentoanexos);
		if (!lst.isEmpty()) {
			syDocumentoanexos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syDocumentoanexos.setTransaccionListaMensajes(lst);
			return syDocumentoanexos;
		}
		
		// transaccion
		syDocumentoanexos = syDocumentoanexosDao.coreInsertar(syDocumentoanexos);
		syDocumentoanexos.setTransaccionEstado(DominioTransaccion.OK);
		syDocumentoanexos.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syDocumentoanexos;
	}

	@Transactional
	public DtoComunSyDocumentoanexos coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunSyDocumentoanexos dto) throws Exception {
		if(!UString.estaVacio(dto.getArchivostring())) {
			this.registrarAdjunto(dto, "");
		}
		
		BeanSyDocumentoanexos syDocumentoanexos = dto.obtenerBean();
		syDocumentoanexos = coreActualizar(usuarioActual, syDocumentoanexos);
		dto.setTransaccionEstado(syDocumentoanexos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syDocumentoanexos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyDocumentoanexos coreActualizar(SeguridadUsuarioActual usuarioActual, BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		// valores por defecto - preparando objeto
		syDocumentoanexos = validar.prepararActualizar(usuarioActual, syDocumentoanexos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, syDocumentoanexos);
		if (!lst.isEmpty()) {
			syDocumentoanexos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syDocumentoanexos.setTransaccionListaMensajes(lst);
			return syDocumentoanexos;
		}
		
		// transaccion
	    syDocumentoanexos = syDocumentoanexosDao.coreActualizar(syDocumentoanexos);
		syDocumentoanexos.setTransaccionEstado(DominioTransaccion.OK);
		syDocumentoanexos.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syDocumentoanexos;
	}


	@Transactional
	public DtoComunSyDocumentoanexos coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunSyDocumentoanexos dto) throws Exception {
		BeanSyDocumentoanexos syDocumentoanexos = dto.obtenerBean();
		syDocumentoanexos = coreAnular(usuarioActual, syDocumentoanexos);
		dto.setTransaccionEstado(syDocumentoanexos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syDocumentoanexos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyDocumentoanexos coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		// valores por defecto - preparando objeto
		syDocumentoanexos = validar.prepararAnular(usuarioActual, syDocumentoanexos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, syDocumentoanexos);
		if (!lst.isEmpty()) {
			syDocumentoanexos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syDocumentoanexos.setTransaccionListaMensajes(lst);
			return syDocumentoanexos;
		}
		
		// transaccion
	    syDocumentoanexos.setEstado("I");
	    syDocumentoanexos = syDocumentoanexosDao.coreActualizar(syDocumentoanexos);
		syDocumentoanexos.setTransaccionEstado(DominioTransaccion.OK);
		syDocumentoanexos.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return syDocumentoanexos;
	}

	public BeanSyDocumentoanexos coreAnular(SeguridadUsuarioActual usuarioActual, BeanSyDocumentoanexosPk pk) throws Exception {
		BeanSyDocumentoanexos bean = syDocumentoanexosDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanSyDocumentoanexos coreAnular(SeguridadUsuarioActual usuarioActual, String pcompaniasocio,String pmodulo,String ptipodocumento,String pnumerodocumento,Integer plinea,Integer psecuencia) throws Exception {
		return coreAnular(usuarioActual,new BeanSyDocumentoanexosPk( pcompaniasocio, pmodulo, ptipodocumento, pnumerodocumento, plinea, psecuencia));
	}

	@Transactional
	public DtoComunSyDocumentoanexos coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunSyDocumentoanexos dto) throws Exception {
		String rutaServer = syDocumentoanexosDao.obtenerParametroValorExplicacion("DIRFILE", "SY")+ File.separator;
		String tmp_ = rutaServer +   dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento().trim() + File.separator + dto.getArchivo();
		System.out.println(rutaServer);
		System.out.println(tmp_);
		
		try {
			new File(tmp_).delete();
			//FileUtils.cleanDirectory(new File(ruta));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error eliminando adj: "+ e);
			e.printStackTrace();
		}
		BeanSyDocumentoanexos syDocumentoanexos = dto.obtenerBean();
		syDocumentoanexos = coreEliminar(usuarioActual, syDocumentoanexos);
		dto.setTransaccionEstado(syDocumentoanexos.getTransaccionEstado());
		dto.setTransaccionListaMensajes(syDocumentoanexos.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanSyDocumentoanexos coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyDocumentoanexos syDocumentoanexos) throws Exception {
		// valores por defecto - preparando objeto
		syDocumentoanexos = validar.prepararEliminar(usuarioActual, syDocumentoanexos);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, syDocumentoanexos);
		if (!lst.isEmpty()) {
			syDocumentoanexos.setTransaccionEstado(DominioTransaccion.VALIDACION);
			syDocumentoanexos.setTransaccionListaMensajes(lst);
			return syDocumentoanexos;
		}
		
		// transaccion
		syDocumentoanexosDao.eliminar(syDocumentoanexos);
		syDocumentoanexos=new BeanSyDocumentoanexos();
		syDocumentoanexos.setTransaccionEstado(DominioTransaccion.OK);
		return syDocumentoanexos;
	}

	public BeanSyDocumentoanexos coreEliminar(SeguridadUsuarioActual usuarioActual, BeanSyDocumentoanexosPk pk) throws Exception {
		BeanSyDocumentoanexos syDocumentoanexos = syDocumentoanexosDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,syDocumentoanexos);
	}

	public BeanSyDocumentoanexos coreEliminar(SeguridadUsuarioActual usuarioActual, String pcompaniasocio,String pmodulo,String ptipodocumento,String pnumerodocumento,Integer plinea,Integer psecuencia) throws Exception {
		return coreEliminar(usuarioActual,new BeanSyDocumentoanexosPk( pcompaniasocio, pmodulo, ptipodocumento, pnumerodocumento, plinea, psecuencia));
	}

	
	
	public DtoComunSyDocumentoanexos registrarAdjunto(DtoComunSyDocumentoanexos dto, String rutaServer) {
		rutaServer = syDocumentoanexosDao.obtenerParametroValorExplicacion("DIRFILE", "SY") + File.separator;
//		if (dto.getFlgactualizar().equals("NUEVO")) {

			if (!UString.estaVacio(rutaServer)) {
				if (!new File(rutaServer + dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento().trim()).exists()) {
					new File(rutaServer + dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento().trim()).mkdirs();
				}
				//si existe la ruta con el adjunto eliminamos
				DtoComunSyDocumentoanexos dtotemp = syDocumentoanexosDao.obtenerDto(dto);
				String tmp_t = rutaServer  + dtotemp.getCompaniasocio() + File.separator+ dtotemp.getModulo() + File.separator + dtotemp.getTipodocumento() + File.separator + dtotemp.getNumerodocumento().trim() +File.separator + dto.getNombreanteerior(); 
				if (new File(tmp_t).exists()) {
					new File(tmp_t).delete();
				}
				//
				
				String tmp_ = dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento().trim();
				if (!new File(rutaServer + tmp_).exists()) {
					new File(rutaServer + tmp_).mkdirs();
				}

				rutaServer = rutaServer + tmp_ + File.separator;
				System.out.println("ruta file: " + rutaServer);
				String rutaDocumento;
				String nombreDocumento = dto.getArchivo();
				rutaDocumento = rutaServer + nombreDocumento;

				String bin = dto.getArchivostring().substring(dto.getArchivostring().indexOf(",") + 1);

				dto.setArchivostring(bin);

				try {
					Files.write(Paths.get(rutaDocumento), Base64.getDecoder().decode(dto.getArchivostring()));
					// dto.setRutaadjunto(rutaDocumento);
					dto.setArchivostring(null);
					//dto.setArchivo(null);

				} catch (IOException e) {
					System.out.println("err file: " + e);
					return null;
				}
			}
//		} else {
//			if (!UString.estaVacio(rutaServer)) {
//				String rutaServer_ = syDocumentoanexosDao.obtenerParametroValorExplicacion("RUTAADJUN", "SY") + File.separator;
//				DtoSyDocumentoanexos dtotemp = syDocumentoanexosDao.obtenerDto(dto);
//
//				 
//				String tmp_t = rutaServer  + dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento().trim() +File.separator + dtotemp.getArchivo() ;//dto.getRutatemporal();
//				System.out.println(rutaServer);
//				System.out.println(tmp_t);
//				byte[] contenido = null;
//				if (new File(tmp_t).exists()) {
//					contenido = UFile.obtenerArregloByte(tmp_t);
//				} else {
//					return dto;
//				}
//				 
//				
//				if (!new File(rutaServer + dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento()).exists()) {
//					new File(rutaServer + dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento()).mkdirs();
//				}
//
//				String tmp_ = dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento();
//
//				if (!new File(rutaServer + tmp_).exists()) {
//					new File(rutaServer + tmp_).mkdirs();
//				}
//
//				rutaServer = rutaServer + tmp_ + File.separator;
//				System.out.println("ruta file: " + rutaServer);
//				String rutaDocumento;
//				String nombreDocumento = dto.getArchivo();
//				rutaDocumento = rutaServer + nombreDocumento;
//
//				String bin = dto.getArchivostring().substring(dto.getArchivostring().indexOf(",") + 1);
//
//				dto.setArchivostring(bin);
//
//				try {
//					Files.write(Paths.get(rutaDocumento), Base64.getDecoder().decode(dto.getArchivostring()));
//					Files.delete(Paths.get(rutaServer + dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento()+ File.separator + dto.getNombreanteerior()));
//					dto.setArchivostring(null);
//					 
//				} catch (IOException e) {
//					System.out.println("err file: " + e);
//					return null;
//				}
//			}
//		}
		return dto;
	}
	
	public DtoComunSyDocumentoanexos verAdjunto(DtoComunSyDocumentoanexos dto) throws IOException {
		String rutaServer = syDocumentoanexosDao.obtenerParametroValorExplicacion("DIRFILE", "SY")+ File.separator;
		String tmp_ = rutaServer +   dto.getCompaniasocio() + File.separator+ dto.getModulo() + File.separator + dto.getTipodocumento() + File.separator + dto.getNumerodocumento().trim() + File.separator + dto.getArchivo();
		System.out.println(rutaServer);
		System.out.println(tmp_);
		byte[] contenido = null;
		if (new File(tmp_).exists()) {
			contenido = UFile.obtenerArregloByte(tmp_);
			dto.setArchivostring(org.apache.commons.codec.binary.Base64.encodeBase64String(contenido));
		} else {
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			List<DominioMensajeUsuario> lst = new ArrayList<DominioMensajeUsuario>();
			lst.add(this.getMsjUsuarioError("sydocumentoanexos.archivo.vacio"));
			dto.setTransaccionListaMensajes(lst);
		}
		return dto;
	}
}
