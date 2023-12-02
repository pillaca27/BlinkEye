package net.royal.spring.seguridad.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.constante.ConstanteBoot;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.seguridad.dao.impl.UsuarioComunDaoImpl;
import net.royal.spring.seguridad.dominio.BeanUsuario;
import net.royal.spring.seguridad.dominio.BeanUsuarioPk;
import net.royal.spring.seguridad.dominio.filtro.FiltroComunPaginacionUsuario;
import net.royal.spring.seguridad.servicio.validar.UsuarioServicioValidar;

@Service(value = "BeanServicioUsuario")
public class UsuarioServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioUsuario";
	private static Logger logger = LogManager.getLogger(UsuarioServicioImpl.class);

	@Autowired
	private UsuarioComunDaoImpl usuarioDao;

	@Autowired
	private UsuarioServicioValidar validar;

	@Transactional
	public BeanUsuario coreInsertar(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) throws UException {
		// valores por defecto - preparando objeto
		usuario = validar.prepararInsertar(usuarioActual, usuario);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, usuario);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return usuarioDao.coreInsertar(usuario);
	}

	@Transactional
	public BeanUsuario coreActualizar(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) throws UException {
		// valores por defecto - preparando objeto
		usuario = validar.prepararActualizar(usuarioActual, usuario);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, usuario);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		usuario = usuarioDao.coreActualizar(usuario);
		return usuario;
	}

	@Transactional
	public BeanUsuario coreAnular(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) throws UException {
		// valores por defecto - preparando objeto
		usuario = validar.prepararAnular(usuarioActual, usuario);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, usuario);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		return usuarioDao.coreActualizar(usuario);
	}

	public BeanUsuario coreAnular(SeguridadUsuarioActual usuarioActual, BeanUsuarioPk pk) throws UException {
		BeanUsuario bean = usuarioDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public BeanUsuario coreAnular(SeguridadUsuarioActual usuarioActual, String pusuario) throws UException {
		return coreAnular(usuarioActual, new BeanUsuarioPk(pusuario));
	}

	@Transactional
	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanUsuario usuario) throws UException {
		// valores por defecto - preparando objeto
		usuario = validar.prepararEliminar(usuarioActual, usuario);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, usuario);
		if (!lst.isEmpty())
			throw new UException(lst);

		// transaccion
		usuarioDao.eliminar(usuario);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, BeanUsuarioPk pk) throws UException {
		BeanUsuario usuario = usuarioDao.obtenerPorId(pk);
		coreEliminar(usuarioActual, usuario);
	}

	public void coreEliminar(SeguridadUsuarioActual usuarioActual, String pusuario) throws UException {
		coreEliminar(usuarioActual, new BeanUsuarioPk(pusuario));
	}

	 
	//LEONARDO SERVICIO - SEGURIDAD MANTENIMIENTO
	public DominioPaginacion listar(FiltroComunPaginacionUsuario filtro) {
		return usuarioDao.listar(filtro);
	}
	
	@Transactional
	public BeanUsuario obtenerPorId(BeanUsuarioPk pk) 
	{
		return usuarioDao.obtenerPorId(pk);		
	}
	
	
	public List<DtoTabla> obtenerUnidadNegocioSeguridad(String usuario) {
		List<DominioParametroPersistencia> parametrosClave = new ArrayList<DominioParametroPersistencia>();
		parametrosClave.add(new DominioParametroPersistencia("p_user", String.class, usuario));
		parametrosClave.add(new DominioParametroPersistencia("p_grupo", String.class,
				ConstanteBoot.PARAMETRO_SEGURIDAD_UNIDAD_NEGOCIO));
		List listado = usuarioDao.listarPorQuery(DtoTabla.class, "usuario.obtenerUnidadNegocioSeguridad",
				parametrosClave);
		return listado;
	}
	
	//FIN LEONARDO
}
