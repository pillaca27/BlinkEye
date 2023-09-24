package net.royal.spring.rrhh.servicio.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.rrhh.dao.impl.HrEspecialidadDaoImpl;
import net.royal.spring.rrhh.dominio.BeanHrCapacitacion;
import net.royal.spring.rrhh.dominio.BeanHrCapacitacionPk;
import net.royal.spring.rrhh.dominio.BeanHrEmpleadoCapacitacion;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidad;
import net.royal.spring.rrhh.dominio.BeanHrEspecialidadPk;
import net.royal.spring.rrhh.dominio.dto.DtoHrCapacitacion;
import net.royal.spring.rrhh.dominio.dto.DtoHrEmpleadoCapacitacion;
import net.royal.spring.rrhh.dominio.dto.DtoHrEspecialidad;
import net.royal.spring.rrhh.dominio.filtro.FiltroHrEspecialidad;

@Service
public class HrEspecialidadServicioImpl extends GenericoServicioImpl{
	
	@Autowired
	private HrEspecialidadDaoImpl hrEspecialidadDaoImpl;
	
	public List<DtoHrEspecialidad> listarEspecialidades(FiltroHrEspecialidad filtro) throws Exception{
		return hrEspecialidadDaoImpl.listarEspecialidades(filtro);
	}

		
	public DtoHrEspecialidad registrar(DtoHrEspecialidad dto, SeguridadUsuarioActual usuario) {


		BeanHrEspecialidad bean = dto.obtenerBean();

		Date ahora = new Date();
		bean.setDescripcion(dto.getDescripcion());
		bean.setEstado(dto.getEstado());
		bean.setUnidadReplicacion(dto.getUnidadReplicacion());
		bean.setUltimaFechaModif(ahora);
		bean.setUltimoUsuario(usuario.getUsuario());
		bean.getPk().setEspecialidad(Integer.parseInt(hrEspecialidadDaoImpl.generarCodigo()));
		
		hrEspecialidadDaoImpl.registrar(bean);

		// Confirmacion de resultado
		DtoHrEspecialidad dtoRespuesta = new DtoHrEspecialidad();
		dtoRespuesta.setEspecialidad(bean.getPk().getEspecialidad());
		dtoRespuesta.setTransaccionEstado(DominioTransaccion.OK);
		return dtoRespuesta;	
	}


	public DtoHrEspecialidad actualizar(DtoHrEspecialidad dto, SeguridadUsuarioActual usuario) {
		Date ahora = new Date();

		// Obtener bean
		BeanHrEspecialidad bean = dto.obtenerBean();
		bean.setDescripcion(dto.getDescripcion());
		bean.setEstado(dto.getEstado());
		bean.setUnidadReplicacion(dto.getUnidadReplicacion());
		bean.setUltimaFechaModif(ahora);
		bean.setUltimoUsuario(usuario.getUsuario());

		// Actualizar
		hrEspecialidadDaoImpl.actualizar(bean);

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}
	
	public DtoHrEspecialidad aprobar(DtoHrEspecialidad dto, SeguridadUsuarioActual usuarioActual) {

		Date ahora = new Date();

		// Obtener el dto
		BeanHrEspecialidad bean = hrEspecialidadDaoImpl
				.obtenerPorId(new BeanHrEspecialidadPk(dto.getEspecialidad()));

		if (!bean.getEstado().equals("P")) {
			dto.getTransaccionListaMensajes().add(
					new DominioMensajeUsuario(tipo_mensaje.ERROR, "El registro no se encuentra en estado preparado"));
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		bean.setEstado("A");
		bean.setUltimaFechaModif(ahora);
		bean.setUltimoUsuario(usuarioActual.getUsuario());
		hrEspecialidadDaoImpl.actualizar(bean);

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoHrEspecialidad anular(DtoHrEspecialidad dto, SeguridadUsuarioActual usuarioActual) {

		Date ahora = new Date();

		// Obtener el dto
		BeanHrEspecialidad bean = hrEspecialidadDaoImpl
				.obtenerPorId(new BeanHrEspecialidadPk(dto.getEspecialidad()));

		if (bean.getEstado().equals("A")) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "El registro ya se encuentra aprobado"));
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		bean.setEstado("N");
		bean.setUltimaFechaModif(ahora);
		bean.setUltimoUsuario(usuarioActual.getUsuario());
		hrEspecialidadDaoImpl.actualizar(bean);

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}


	public DtoHrEspecialidad eliminar(DtoHrEspecialidad dto, SeguridadUsuarioActual usuario) {
		
		BeanHrEspecialidad bean = hrEspecialidadDaoImpl
				.obtenerPorId(new BeanHrEspecialidadPk(dto.getEspecialidad()));

		if (bean.getEstado().equals("A")) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "El registro ya se encuentra aprobado"));
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		hrEspecialidadDaoImpl.eliminar(bean);

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;

	}


	public DtoHrEspecialidad obtenerDto(DtoHrEspecialidad dto) {

		dto = hrEspecialidadDaoImpl.obtenerDto(dto.getEspecialidad());

		if (dto == null) {
			dto = new DtoHrEspecialidad();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Registro no encontrado"));
			return dto;
		}

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}
		
}
