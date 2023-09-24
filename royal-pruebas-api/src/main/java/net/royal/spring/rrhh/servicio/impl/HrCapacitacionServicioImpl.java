package net.royal.spring.rrhh.servicio.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario.tipo_mensaje;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.rrhh.dao.impl.HrCapacitacionDaoImpl;
import net.royal.spring.rrhh.dao.impl.HrEmpleadoCapacitacionDaoImpl;
import net.royal.spring.rrhh.dominio.BeanHrCapacitacion;
import net.royal.spring.rrhh.dominio.BeanHrCapacitacionPk;
import net.royal.spring.rrhh.dominio.BeanHrEmpleadoCapacitacion;
import net.royal.spring.rrhh.dominio.dto.DtoHrCapacitacion;
import net.royal.spring.rrhh.dominio.dto.DtoHrEmpleadoCapacitacion;
import net.royal.spring.rrhh.dominio.filtro.FiltroHrCapacitacion;

@Service
public class HrCapacitacionServicioImpl extends GenericoServicioImpl {

	@Autowired
	private HrCapacitacionDaoImpl hrCapacitacionDaoImpl;

	@Autowired
	private HrEmpleadoCapacitacionDaoImpl hrEmpleadoCapacitacionDaoImpl;

	public DominioPaginacion listarPaginacion(FiltroHrCapacitacion filtro, SeguridadUsuarioActual usuarioActual) {
		return hrCapacitacionDaoImpl.listarPaginacion(filtro, usuarioActual);
	}

	public DtoHrCapacitacion registrar(DtoHrCapacitacion dto, SeguridadUsuarioActual usuarioActual) {

		// Tranformar en Bean
		BeanHrCapacitacion bean = dto.obtenerBean();
		
		
		// GUARDANDO ARCHIVO
		if (!UString.esNuloVacio(dto.getArchivo())) {
			byte imagen[] = Base64.getDecoder().decode(dto.getArchivodatastring());

			if (!UValidador.esNulo(imagen)) {
				bean.setArchivo(imagen);
			}
		}

		// Validaciones Negocio
		List<DominioMensajeUsuario> validaciones = new ArrayList<DominioMensajeUsuario>();
		if (UString.estaVacio(bean.getPk().getCompanyOwner())) {
			validaciones.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "Seleccione la compa\u00F1ia"));
		}
		if (UInteger.esCeroOrNulo(bean.getCurso())) {
			validaciones.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "Seleccione el curso"));
		}
		if (bean.getFechaDesde() == null) {
			validaciones.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "Seleccione la fecha inicio"));
		}
		if (bean.getFechaHasta() == null) {
			validaciones.add(new DominioMensajeUsuario(tipo_mensaje.ADVERTENCIA, "Seleccione la fecha fin"));
		}

		if (validaciones.size() > 0) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(validaciones);
			return dto;
		}

		// Registrar
		Date ahora = new Date();
		bean.setFechaSolicitud(ahora);
		bean.setEmpleadoSolicitante(usuarioActual.getPersonaId());
		bean.setEstado("P");
		bean.setUltimaFechaModif(ahora);
		bean.setUltimoUsuario(usuarioActual.getUsuario());
		bean.getPk().setCapacitacion(
				hrCapacitacionDaoImpl.generarCodigo(bean.getPk().getCompanyOwner(), usuarioActual));
		hrCapacitacionDaoImpl.registrar(bean);

		// Registrar Detalle
		for (DtoHrEmpleadoCapacitacion row : dto.getLstParticipantes()) {
			row.setCompanyOwner(bean.getPk().getCompanyOwner());
			row.setCapacitacion(bean.getPk().getCapacitacion());
			row.setUltimaFechaModif(ahora);
			row.setUltimoUsuario(usuarioActual.getUsuario());
			BeanHrEmpleadoCapacitacion beanDetalle = row.obtenerBean();
			hrEmpleadoCapacitacionDaoImpl.registrar(beanDetalle);
		}

		// Confirmacion de resultado
		DtoHrCapacitacion dtoRespuesta = new DtoHrCapacitacion();
		dtoRespuesta.setCompanyOwner(bean.getPk().getCompanyOwner());
		dtoRespuesta.setCapacitacion(bean.getPk().getCapacitacion());
		dtoRespuesta.setTransaccionEstado(DominioTransaccion.OK);
		return dtoRespuesta;
	}

	public DtoHrCapacitacion obtenerDto(DtoHrCapacitacion dto) {

		// Obtener DtoHrCapacitacion
		dto = hrCapacitacionDaoImpl.obtenerDto(dto.getCompanyOwner(), dto.getCapacitacion());

		if (dto == null) {
			dto = new DtoHrCapacitacion();
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "Registro no encontrado"));
			return dto;
		}

		// Obtener List DtoHrEmpleadoCapacitacion
		dto.setLstParticipantes(
				hrEmpleadoCapacitacionDaoImpl.obtenerListaDto(dto.getCompanyOwner(), dto.getCapacitacion()));

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoHrCapacitacion actualizar(DtoHrCapacitacion dto, SeguridadUsuarioActual usuarioActual) {

		Date ahora = new Date();

		// Obtener bean
		BeanHrCapacitacion bean = dto.obtenerBean();
		bean.setUltimaFechaModif(ahora);
		bean.setUltimoUsuario(usuarioActual.getUsuario());

		// Actualizar
		hrCapacitacionDaoImpl.actualizar(bean);

		// Detalle
		for (DtoHrEmpleadoCapacitacion row : dto.getLstParticipantes()) {

			row.setCompanyOwner(bean.getPk().getCompanyOwner());
			row.setCapacitacion(bean.getPk().getCapacitacion());
			row.setUltimaFechaModif(ahora);
			row.setUltimoUsuario(usuarioActual.getUsuario());

			BeanHrEmpleadoCapacitacion beanRow = row.obtenerBean();

			if (row.getAuxFlgNuevo().equals("S")) {
				hrEmpleadoCapacitacionDaoImpl.registrar(beanRow);
			} else if (row.getAuxFlgEliminar().equals("S")) {
				hrEmpleadoCapacitacionDaoImpl.eliminar(beanRow);
			} else if (row.getAuxFlgEditar().equals("S")) {
				hrEmpleadoCapacitacionDaoImpl.actualizar(beanRow);
			}
		}

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoHrCapacitacion aprobar(DtoHrCapacitacion dto, SeguridadUsuarioActual usuarioActual) {

		Date ahora = new Date();

		// Obtener el dto
		BeanHrCapacitacion bean = hrCapacitacionDaoImpl
				.obtenerPorId(new BeanHrCapacitacionPk(dto.getCompanyOwner(), dto.getCapacitacion()));

		if (!bean.getEstado().equals("P")) {
			dto.getTransaccionListaMensajes().add(
					new DominioMensajeUsuario(tipo_mensaje.ERROR, "El registro no se encuentra en estado preparado"));
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		bean.setEstado("A");
		bean.setUltimaFechaModif(ahora);
		bean.setUltimoUsuario(usuarioActual.getUsuario());
		hrCapacitacionDaoImpl.actualizar(bean);

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoHrCapacitacion anular(DtoHrCapacitacion dto, SeguridadUsuarioActual usuarioActual) {

		Date ahora = new Date();

		// Obtener el dto
		BeanHrCapacitacion bean = hrCapacitacionDaoImpl
				.obtenerPorId(new BeanHrCapacitacionPk(dto.getCompanyOwner(), dto.getCapacitacion()));

		if (bean.getEstado().equals("A")) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "El registro ya se encuentra aprobado"));
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		bean.setEstado("N");
		bean.setUltimaFechaModif(ahora);
		bean.setUltimoUsuario(usuarioActual.getUsuario());
		hrCapacitacionDaoImpl.actualizar(bean);

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public DtoHrCapacitacion eliminar(DtoHrCapacitacion dto, SeguridadUsuarioActual usuarioActual) {

		BeanHrCapacitacion bean = hrCapacitacionDaoImpl
				.obtenerPorId(new BeanHrCapacitacionPk(dto.getCompanyOwner(), dto.getCapacitacion()));

		if (bean.getEstado().equals("A")) {
			dto.getTransaccionListaMensajes()
					.add(new DominioMensajeUsuario(tipo_mensaje.ERROR, "El registro ya se encuentra aprobado"));
			dto.setTransaccionEstado(DominioTransaccion.ERROR);
			return dto;
		}

		// Eliminar Detalle
		for (DtoHrEmpleadoCapacitacion row : hrEmpleadoCapacitacionDaoImpl.obtenerListaDto(dto.getCompanyOwner(),
				dto.getCapacitacion())) {
			BeanHrEmpleadoCapacitacion beanRow = row.obtenerBean();
			hrEmpleadoCapacitacionDaoImpl.eliminar(beanRow);
		}

		// Eliminar Cabecera
		hrCapacitacionDaoImpl.eliminar(bean);

		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	public byte[] ejecutarReporteComoPDF(String rutaArchivoReporteFuente, Map<String, Object> parametros)
			throws Exception {
		return hrCapacitacionDaoImpl.ejecutarReporteComoPDF(rutaArchivoReporteFuente, parametros);
	}

	public List<DtoHrEmpleadoCapacitacion> listarParticipantes(DtoHrCapacitacion dto) {
		return hrEmpleadoCapacitacionDaoImpl.listarParticipantes(dto);
	}

	public List<DtoTabla> listarCompanias(String usuario) throws Exception {


		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuario));

		List lstResultado = hrEmpleadoCapacitacionDaoImpl.listarPorQuery(DtoTabla.class,
				"hrempleadocapacitacion.listarCompanias", parametros);

		return lstResultado;
	}
}
