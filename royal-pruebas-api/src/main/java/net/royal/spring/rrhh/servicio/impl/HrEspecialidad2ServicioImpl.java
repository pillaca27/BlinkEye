package net.royal.spring.rrhh.servicio.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.rrhh.dao.impl.HrEspecialidad2DaoImpl;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidad2;
import net.royal.spring.rrhh.dominio.dto.DtoHrEspecialidad;
import net.royal.spring.rrhh.dominio.dto.DtoHrEspecialidad2;
import net.royal.spring.rrhh.dominio.filtro.FiltroHrEspecialidad2;

@Service
public class HrEspecialidad2ServicioImpl extends GenericoServicioImpl{
	
	@Autowired
	private  HrEspecialidad2ValidarImpl validar;
	
	@Autowired
	private HrEspecialidad2DaoImpl hrEspecialidad2DaoImpl;
	
	public DominioPaginacion listarPaginacion(FiltroHrEspecialidad2 filtros) throws Exception {
		return hrEspecialidad2DaoImpl.listarPaginacion(filtros);
	}

	@Transactional
	public DtoHrEspecialidad2 coreInsertar(SeguridadUsuarioActual usuarioActual, DtoHrEspecialidad2 dto) {
		dto.setUltimaFechaModif(new Date());
		dto.setUltimoUsuario(usuarioActual.getUsuario());
		
		BeanHrEspecialidad2 bean = dto.obtenerBean();
		bean = coreInsertar(usuarioActual, bean);
		dto.setTransaccionEstado(bean.getTransaccionEstado());
		dto.setTransaccionListaMensajes(bean.getTransaccionListaMensajes());
		return dto;
	}

	private BeanHrEspecialidad2 coreInsertar(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 bean) {
		// valores por defecto - preparando objeto
		bean = validar.prepararInsertar(usuarioActual, bean);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, bean);
		if (!lst.isEmpty()) {
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.setTransaccionListaMensajes(lst);
			return bean;
		}
		
		// transaccion
		bean = hrEspecialidad2DaoImpl.coreInsertar(bean);
		bean.setTransaccionEstado(DominioTransaccion.OK);
		bean.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return bean;
	}

	public DtoHrEspecialidad2 coreActualizar(SeguridadUsuarioActual usuario, DtoHrEspecialidad2 dto) {
		BeanHrEspecialidad2 bean = dto.obtenerBean();
		bean = coreActualizar(usuario, bean);
		dto.setTransaccionEstado(bean.getTransaccionEstado());
		dto.setTransaccionListaMensajes(bean.getTransaccionListaMensajes());
		return dto;
	}

	private BeanHrEspecialidad2 coreActualizar(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 bean) {
		// valores por defecto - preparando objeto
		bean = validar.prepararActualizar(usuarioActual, bean);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, bean);
		if (!lst.isEmpty()) {
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.setTransaccionListaMensajes(lst);
			return bean;
		}
		
		// transaccion
		bean = hrEspecialidad2DaoImpl.coreActualizar(bean);
		bean.setTransaccionEstado(DominioTransaccion.OK);
		bean.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return bean;
	}

	@Transactional
	public DtoHrEspecialidad2 coreAnular(SeguridadUsuarioActual usuarioActual, DtoHrEspecialidad2 dto) {
		BeanHrEspecialidad2 bean = dto.obtenerBean();
		bean = coreAnular(usuarioActual, bean);
		dto.setTransaccionEstado(bean.getTransaccionEstado());
		dto.setTransaccionListaMensajes(bean.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	private BeanHrEspecialidad2 coreAnular(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 bean) {
		// valores por defecto - preparando objeto
		bean = validar.prepararAnular(usuarioActual, bean);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, bean);
		if (!lst.isEmpty()) {
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.setTransaccionListaMensajes(lst);
			return bean;
		}
		
		// transaccion
		bean.setEstado("I");
		bean = hrEspecialidad2DaoImpl.coreActualizar(bean);
		bean.setTransaccionEstado(DominioTransaccion.OK);
		bean.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return bean;
	}

	@Transactional
	public DtoHrEspecialidad2 coreEliminar(SeguridadUsuarioActual usuarioActual, DtoHrEspecialidad2 dto) {
		BeanHrEspecialidad2 bean = dto.obtenerBean();
		bean = coreEliminar(usuarioActual, bean);
		dto.setTransaccionEstado(bean.getTransaccionEstado());
		dto.setTransaccionListaMensajes(bean.getTransaccionListaMensajes());
		return dto;
	}

	private BeanHrEspecialidad2 coreEliminar(SeguridadUsuarioActual usuarioActual, BeanHrEspecialidad2 bean) {
		// valores por defecto - preparando objeto
		bean = validar.prepararEliminar(usuarioActual, bean);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, bean);
		if (!lst.isEmpty()) {
			bean.setTransaccionEstado(DominioTransaccion.VALIDACION);
			bean.setTransaccionListaMensajes(lst);
			return bean;
		}
		
		// transaccion
		hrEspecialidad2DaoImpl.eliminar(bean);
		bean=new BeanHrEspecialidad2();
		bean.setTransaccionEstado(DominioTransaccion.OK);
		return bean;
	}

	public DtoHrEspecialidad2 obtenerDto(DtoHrEspecialidad2 dto) {
		dto = hrEspecialidad2DaoImpl.obtenerDto(dto.getEspecialidad());

		if (dto == null) {
			dto = new DtoHrEspecialidad2();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Registro no encontrado"));
			return dto;
		}

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

}
