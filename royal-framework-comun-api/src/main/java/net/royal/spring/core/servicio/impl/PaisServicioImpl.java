package net.royal.spring.core.servicio.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.PaisDaoImpl;
import net.royal.spring.core.dominio.BeanPais;
import net.royal.spring.core.dominio.BeanPaisPk;
import net.royal.spring.core.dominio.dto.DtoComunPais;
import net.royal.spring.core.servicio.validar.PaisServicioValidar;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.util.dominio.dto.DtoComunArbolFile;
import net.royal.spring.util.dominio.dto.DtoComunFile;
import net.royal.spring.util.dominio.filtro.FiltroComunDirectorio;

@Service(value = "BeanServicioPais")
public class PaisServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioPais";
	private static Logger logger = LogManager.getLogger(PaisServicioImpl.class);

	@Autowired
	private PaisDaoImpl paisDao;

	@Autowired
	private PaisServicioValidar validar;

	@Transactional
	public DtoComunPais coreInsertar(SeguridadUsuarioActual usuarioActual, DtoComunPais dto) throws Exception {
		BeanPais pais = dto.obtenerBean();
		pais = coreInsertar(usuarioActual, pais);
		dto.setTransaccionEstado(pais.getTransaccionEstado());
		dto.setTransaccionListaMensajes(pais.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanPais coreInsertar(SeguridadUsuarioActual usuarioActual, BeanPais pais) throws Exception {
		// valores por defecto - preparando objeto
		pais = validar.prepararInsertar(usuarioActual, pais);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, pais);
		if (!lst.isEmpty()) {
			pais.setTransaccionEstado(DominioTransaccion.VALIDACION);
			pais.setTransaccionListaMensajes(lst);
			return pais;
		}

		// transaccion
		pais = paisDao.coreInsertar(pais);
		pais.setTransaccionEstado(DominioTransaccion.OK);
		pais.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return pais;
	}

	@Transactional
	public DtoComunPais coreActualizar(SeguridadUsuarioActual usuarioActual, DtoComunPais dto) throws Exception {
		BeanPais pais = dto.obtenerBean();
		pais = coreActualizar(usuarioActual, pais);
		dto.setTransaccionEstado(pais.getTransaccionEstado());
		dto.setTransaccionListaMensajes(pais.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanPais coreActualizar(SeguridadUsuarioActual usuarioActual, BeanPais pais) throws Exception {
		// valores por defecto - preparando objeto
		pais = validar.prepararActualizar(usuarioActual, pais);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, pais);
		if (!lst.isEmpty()) {
			pais.setTransaccionEstado(DominioTransaccion.VALIDACION);
			pais.setTransaccionListaMensajes(lst);
			return pais;
		}

		// transaccion
		pais = paisDao.coreActualizar(pais);
		pais.setTransaccionEstado(DominioTransaccion.OK);
		pais.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return pais;
	}

	@Transactional
	public DtoComunPais coreAnular(SeguridadUsuarioActual usuarioActual, DtoComunPais dto) throws Exception {
		BeanPais pais = dto.obtenerBean();
		pais = coreAnular(usuarioActual, pais);
		dto.setTransaccionEstado(pais.getTransaccionEstado());
		dto.setTransaccionListaMensajes(pais.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanPais coreAnular(SeguridadUsuarioActual usuarioActual, BeanPais pais) throws Exception {
		// valores por defecto - preparando objeto
		pais = validar.prepararAnular(usuarioActual, pais);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, pais);
		if (!lst.isEmpty()) {
			pais.setTransaccionEstado(DominioTransaccion.VALIDACION);
			pais.setTransaccionListaMensajes(lst);
			return pais;
		}

		// transaccion
		pais.setEstado("I");
		pais = paisDao.coreActualizar(pais);
		pais.setTransaccionEstado(DominioTransaccion.OK);
		pais.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return pais;
	}

	public BeanPais coreAnular(SeguridadUsuarioActual usuarioActual, BeanPaisPk pk) throws Exception {
		BeanPais bean = paisDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public BeanPais coreAnular(SeguridadUsuarioActual usuarioActual, String ppais) throws Exception {
		return coreAnular(usuarioActual, new BeanPaisPk(ppais));
	}

	@Transactional
	public DtoComunPais coreEliminar(SeguridadUsuarioActual usuarioActual, DtoComunPais dto) throws Exception {
		BeanPais pais = dto.obtenerBean();
		pais = coreEliminar(usuarioActual, pais);
		dto.setTransaccionEstado(pais.getTransaccionEstado());
		dto.setTransaccionListaMensajes(pais.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanPais coreEliminar(SeguridadUsuarioActual usuarioActual, BeanPais pais) throws Exception {
		// valores por defecto - preparando objeto
		pais = validar.prepararEliminar(usuarioActual, pais);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, pais);
		if (!lst.isEmpty()) {
			pais.setTransaccionEstado(DominioTransaccion.VALIDACION);
			pais.setTransaccionListaMensajes(lst);
			return pais;
		}

		// transaccion
		paisDao.eliminar(pais);
		pais = new BeanPais();
		pais.setTransaccionEstado(DominioTransaccion.OK);
		return pais;
	}

	public BeanPais coreEliminar(SeguridadUsuarioActual usuarioActual, BeanPaisPk pk) throws Exception {
		BeanPais pais = paisDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual, pais);
	}

	public BeanPais coreEliminar(SeguridadUsuarioActual usuarioActual, String ppais) throws Exception {
		return coreEliminar(usuarioActual, new BeanPaisPk(ppais));
	}

	public DtoComunArbolFile verDirectorio(FiltroComunDirectorio filtro) {

		DtoComunArbolFile dto = new DtoComunArbolFile();

		File f1 = new File(filtro.getRuta());

		if (!f1.exists()) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "La carpeta no existe"));
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		// Nodo base
		DtoComunFile n1 = new DtoComunFile();
		n1.setLabel(f1.getName());
		n1.setData(n1.getLabel());
		n1.setCollapsedIcon("pi pi-folder");
		n1.setExpandedIcon("pi pi-folder-open");
		n1.setLeaf(false);
		n1.setChildren(buscarHijos(f1));
		dto.getData().add(n1);

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	private List<DtoComunFile> buscarHijos(File folder) {
		List<DtoComunFile> childrenFile = new ArrayList<DtoComunFile>();
		List<DtoComunFile> childrenFolder = new ArrayList<DtoComunFile>();

		File[] fList = folder.listFiles();

		if (fList == null) {
			return childrenFile;
		}

		for (File file : fList) {
			DtoComunFile f = new DtoComunFile();
			f.setLabel(file.getName());
			f.setData(f.getLabel());
			if (file.isFile()) {
				f.setIcon("pi pi-file");
				f.setLeaf(true);
				childrenFile.add(f);
			} else if (file.isDirectory()) {
				f.setCollapsedIcon("pi pi-folder");
				f.setExpandedIcon("pi pi-folder-open");
				f.setChildren(buscarHijos(file));
				f.setLeaf(false);
				childrenFolder.add(f);
			}
		}

		childrenFile.addAll(childrenFolder);

		return childrenFile;
	}

}
