package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.core.dao.impl.RtpsEmpleadoEstablecimientoExternoDaoImpl;
import net.royal.spring.core.dao.impl.RtpsEstablecimientoexternoDaoImpl;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExternoPk;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.servicio.validar.RtpsEmpleadoEstablecimientoExternoServicioValidar;
import net.royal.spring.core.servicio.validar.RtpsEstablecimientoexternoServicioValidar;

@Service (value = "BeanServicioRtpsEmpleadoEstablecimientoExterno")
public class RtpsEmpleadoEstablecimientoExternoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioRtpsEmpleadoEstablecimientoexterno";
	private static Logger logger = LogManager.getLogger(RtpsEmpleadoEstablecimientoExternoServicioImpl.class);

	@Autowired
	private RtpsEmpleadoEstablecimientoExternoDaoImpl rtpsEmpleadoEstablecimientoExternoDao;

	@Autowired
	private RtpsEmpleadoEstablecimientoExternoServicioValidar validar;

	@Transactional
	public DtoComunRtpsEmpleadoEstablecimientoExterno coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno = dto.obtenerBean();
		rtpsEmpleadoEstablecimientoExterno = coreInsertar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		dto.setTransaccionEstado(rtpsEmpleadoEstablecimientoExterno.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpleadoEstablecimientoExterno.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpleadoEstablecimientoExterno coreInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpleadoEstablecimientoExterno = validar.prepararInsertar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		if (!lst.isEmpty()) {
			rtpsEmpleadoEstablecimientoExterno.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpleadoEstablecimientoExterno.setTransaccionListaMensajes(lst);
			return rtpsEmpleadoEstablecimientoExterno;
		}
		
		// transaccion
		rtpsEmpleadoEstablecimientoExterno = rtpsEmpleadoEstablecimientoExternoDao.coreInsertar(rtpsEmpleadoEstablecimientoExterno);
		rtpsEmpleadoEstablecimientoExterno.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEmpleadoEstablecimientoExterno.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEmpleadoEstablecimientoExterno;
	}

	@Transactional
	public DtoComunRtpsEmpleadoEstablecimientoExterno coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno = dto.obtenerBean();
		rtpsEmpleadoEstablecimientoExterno = coreActualizar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		dto.setTransaccionEstado(rtpsEmpleadoEstablecimientoExterno.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpleadoEstablecimientoExterno.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public FiltroComunRtpsEmpleadoEstablecimientoExterno coreActualizarLista(SeguridadUsuarioActual usuarioActual, FiltroComunRtpsEmpleadoEstablecimientoExterno filtro) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper(); // or inject it as a dependency
		
		Object pojos = filtro.getPaginacion().getPaginacionListaResultado();
		List<DtoComunRtpsEmpleadoEstablecimientoExterno> listanueva = mapper.convertValue(pojos, new TypeReference<List<DtoComunRtpsEmpleadoEstablecimientoExterno>>() { });
		
//		List<DtoComunRtpsEmpleadoEstablecimientoExterno> listanueva = (List<DtoComunRtpsEmpleadoEstablecimientoExterno>) filtro.getPaginacion().getPaginacionListaResultado();
		DominioPaginacion paginacionant = rtpsEmpleadoEstablecimientoExternoDao.listarPaginado(usuarioActual, filtro);
		List<DtoComunRtpsEmpleadoEstablecimientoExterno> listaant = paginacionant.getPaginacionListaResultado();

		for(int i = 0; i < listanueva.size(); i++) {
			
			DtoComunRtpsEmpleadoEstablecimientoExterno dto = listanueva.get(i);
			
			if (dto.getAccion().equals("NUEVO")) {
				dto =  coreInsertar(this.getUsuarioActual(),dto);
			} else if (dto.getAccion().equals("ELIMINAR")) {
				dto =  coreEliminar(this.getUsuarioActual(),dto);
			} else {
				final String companiasocio = dto.getCompaniasocio();
				final Integer empleado= dto.getEmpleado();
				final Integer codigoautomatico = dto.getCodigoautomatico();
				
				int existe = listaant
								.stream()
								.filter(reg -> 
//									reg.getCompaniasocio().equals(companiasocio)
//									&& reg.getEmpleado() == empleado
//									&& 
									reg.getCodigoautomatico() == codigoautomatico)
								.collect(Collectors.toList())
								.size();
				if (existe == 0) {
					dto =  coreInsertar(this.getUsuarioActual(),dto);
				}
			}
			
			if (dto.getTransaccionEstado().equals(DominioTransaccion.VALIDACION)) {
				filtro.setTransaccionEstado(dto.getTransaccionEstado());
				filtro.setTransaccionListaMensajes(dto.getTransaccionListaMensajes());
				return filtro;
			}
		}
		
		for(int i = 0; i < listaant.size(); i++) {
			DtoComunRtpsEmpleadoEstablecimientoExterno dto = listaant.get(i);
			
			final String companiasocio = dto.getCompaniasocio();
			final Integer empleado= dto.getEmpleado();
			final Integer codigoautomatico = dto.getCodigoautomatico();
			
			int existe = listanueva
					.stream()
					.filter(reg -> 
//						reg.getCompaniasocio().equals(companiasocio)
//						&& reg.getEmpleado() == empleado
//						&& 
						reg.getCodigoautomatico() == codigoautomatico)
					.collect(Collectors.toList())
					.size();
			if (existe == 0) {
				dto =  coreEliminar(this.getUsuarioActual(),dto);
				if (dto.getTransaccionEstado().equals(DominioTransaccion.VALIDACION)) {
					filtro.setTransaccionEstado(dto.getTransaccionEstado());
					filtro.setTransaccionListaMensajes(dto.getTransaccionListaMensajes());
					return filtro;
				}

			}
		}
		
		return filtro;
	}
	
	@Transactional
	public BeanRtpsEmpleadoEstablecimientoExterno coreActualizar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpleadoEstablecimientoExterno = validar.prepararActualizar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		if (!lst.isEmpty()) {
			rtpsEmpleadoEstablecimientoExterno.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpleadoEstablecimientoExterno.setTransaccionListaMensajes(lst);
			return rtpsEmpleadoEstablecimientoExterno;
		}
		
		// transaccion
		rtpsEmpleadoEstablecimientoExterno = rtpsEmpleadoEstablecimientoExternoDao.coreActualizar(rtpsEmpleadoEstablecimientoExterno);
		rtpsEmpleadoEstablecimientoExterno.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEmpleadoEstablecimientoExterno.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEmpleadoEstablecimientoExterno;
	}


	@Transactional
	public DtoComunRtpsEmpleadoEstablecimientoExterno coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno = dto.obtenerBean();
		rtpsEmpleadoEstablecimientoExterno = coreAnular(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		dto.setTransaccionEstado(rtpsEmpleadoEstablecimientoExterno.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpleadoEstablecimientoExterno.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpleadoEstablecimientoExterno coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpleadoEstablecimientoExterno = validar.prepararAnular(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		if (!lst.isEmpty()) {
			rtpsEmpleadoEstablecimientoExterno.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpleadoEstablecimientoExterno.setTransaccionListaMensajes(lst);
			return rtpsEmpleadoEstablecimientoExterno;
		}
		
		// transaccion
		//rtpsEmpleadoEstablecimientoExterno.setEstado("I");
		rtpsEmpleadoEstablecimientoExterno = rtpsEmpleadoEstablecimientoExternoDao.coreActualizar(rtpsEmpleadoEstablecimientoExterno);
		rtpsEmpleadoEstablecimientoExterno.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEmpleadoEstablecimientoExterno.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEmpleadoEstablecimientoExterno;
	}

	public BeanRtpsEmpleadoEstablecimientoExterno coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoExternoPk pk) throws Exception {
		BeanRtpsEmpleadoEstablecimientoExterno bean = rtpsEmpleadoEstablecimientoExternoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public BeanRtpsEmpleadoEstablecimientoExterno coreAnular(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreAnular(usuarioActual,new BeanRtpsEmpleadoEstablecimientoExternoPk( pcodigoautomatico));
	}

	@Transactional
	public DtoComunRtpsEmpleadoEstablecimientoExterno coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpleadoEstablecimientoExterno dto) throws Exception {
		BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno = dto.obtenerBean();
		rtpsEmpleadoEstablecimientoExterno = coreEliminar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		dto.setTransaccionEstado(rtpsEmpleadoEstablecimientoExterno.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpleadoEstablecimientoExterno.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpleadoEstablecimientoExterno coreEliminar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpleadoEstablecimientoExterno = validar.prepararEliminar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, rtpsEmpleadoEstablecimientoExterno);
		if (!lst.isEmpty()) {
			rtpsEmpleadoEstablecimientoExterno.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpleadoEstablecimientoExterno.setTransaccionListaMensajes(lst);
			return rtpsEmpleadoEstablecimientoExterno;
		}
		
		// transaccion
		rtpsEmpleadoEstablecimientoExternoDao.eliminar(rtpsEmpleadoEstablecimientoExterno);
		rtpsEmpleadoEstablecimientoExterno=new BeanRtpsEmpleadoEstablecimientoExterno();
		rtpsEmpleadoEstablecimientoExterno.setTransaccionEstado(DominioTransaccion.OK);
		return rtpsEmpleadoEstablecimientoExterno;
	}

	public BeanRtpsEmpleadoEstablecimientoExterno coreEliminar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimientoExternoPk pk) throws Exception {
		BeanRtpsEmpleadoEstablecimientoExterno rtpsEmpleadoEstablecimientoExterno = rtpsEmpleadoEstablecimientoExternoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,rtpsEmpleadoEstablecimientoExterno);
	}

	public BeanRtpsEmpleadoEstablecimientoExterno coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreEliminar(usuarioActual,new BeanRtpsEmpleadoEstablecimientoExternoPk( pcodigoautomatico));
	}

}
