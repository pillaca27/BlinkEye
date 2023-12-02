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
import com.fasterxml.jackson.databind.ObjectMapper;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.core.dao.impl.RtpsEmpleadoEstablecimientoDaoImpl;
import net.royal.spring.core.dao.impl.RtpsEmpleadoEstablecimientoExternoDaoImpl;
import net.royal.spring.core.dao.impl.RtpsEstablecimientoexternoDaoImpl;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimiento;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoExternoPk;
import net.royal.spring.core.dominio.BeanRtpsEmpleadoEstablecimientoPk;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.BeanRtpsEstablecimientoexternoPk;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpleadoEstablecimiento;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.dominio.dto.DtoComunRtpsEstablecimientoexterno;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEmpleadoEstablecimiento;
import net.royal.spring.core.dominio.filtro.FiltroComunRtpsEmpleadoEstablecimientoExterno;
import net.royal.spring.core.servicio.validar.RtpsEmpleadoEstablecimientoExternoServicioValidar;
import net.royal.spring.core.servicio.validar.RtpsEmpleadoEstablecimientoServicioValidar;
import net.royal.spring.core.servicio.validar.RtpsEstablecimientoexternoServicioValidar;

@Service (value = "BeanServicioRtpsEmpleadoEstablecimiento")
public class RtpsEmpleadoEstablecimientoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioRtpsEmpleadoEstablecimientoexterno";
	private static Logger logger = LogManager.getLogger(RtpsEmpleadoEstablecimientoServicioImpl.class);

	@Autowired
	private RtpsEmpleadoEstablecimientoDaoImpl rtpsEmpleadoEstablecimientoDao;

	@Autowired
	private RtpsEmpleadoEstablecimientoServicioValidar validar;

	@Transactional
	public DtoComunRtpsEmpleadoEstablecimiento coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpleadoEstablecimiento dto) throws Exception {
		BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento = dto.obtenerBean();
		rtpsEmpleadoEstablecimiento = coreInsertar(usuarioActual, rtpsEmpleadoEstablecimiento);
		dto.setTransaccionEstado(rtpsEmpleadoEstablecimiento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpleadoEstablecimiento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpleadoEstablecimiento coreInsertar(SeguridadUsuarioActual usuarioActual,BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpleadoEstablecimiento = validar.prepararInsertar(usuarioActual, rtpsEmpleadoEstablecimiento);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, rtpsEmpleadoEstablecimiento);
		if (!lst.isEmpty()) {
			rtpsEmpleadoEstablecimiento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpleadoEstablecimiento.setTransaccionListaMensajes(lst);
			return rtpsEmpleadoEstablecimiento;
		}
		
		// transaccion
		rtpsEmpleadoEstablecimiento = rtpsEmpleadoEstablecimientoDao.coreInsertar(rtpsEmpleadoEstablecimiento);
		rtpsEmpleadoEstablecimiento.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEmpleadoEstablecimiento.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEmpleadoEstablecimiento;
	}

	@Transactional
	public DtoComunRtpsEmpleadoEstablecimiento coreActualizar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpleadoEstablecimiento dto) throws Exception {
		BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento = dto.obtenerBean();
		rtpsEmpleadoEstablecimiento = coreActualizar(usuarioActual, rtpsEmpleadoEstablecimiento);
		dto.setTransaccionEstado(rtpsEmpleadoEstablecimiento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpleadoEstablecimiento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpleadoEstablecimiento coreActualizar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpleadoEstablecimiento = validar.prepararActualizar(usuarioActual, rtpsEmpleadoEstablecimiento);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, rtpsEmpleadoEstablecimiento);
		if (!lst.isEmpty()) {
			rtpsEmpleadoEstablecimiento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpleadoEstablecimiento.setTransaccionListaMensajes(lst);
			return rtpsEmpleadoEstablecimiento;
		}
		
		// transaccion
		rtpsEmpleadoEstablecimiento = rtpsEmpleadoEstablecimientoDao.coreActualizar(rtpsEmpleadoEstablecimiento);
		rtpsEmpleadoEstablecimiento.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEmpleadoEstablecimiento.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEmpleadoEstablecimiento;
	}

	@Transactional
	public FiltroComunRtpsEmpleadoEstablecimiento coreActualizarLista(SeguridadUsuarioActual usuarioActual, FiltroComunRtpsEmpleadoEstablecimiento filtro) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper(); // or inject it as a dependency
		
		Object pojos = filtro.getPaginacion().getPaginacionListaResultado();
		List<DtoComunRtpsEmpleadoEstablecimiento> listanueva = mapper.convertValue(pojos, new TypeReference<List<DtoComunRtpsEmpleadoEstablecimiento>>() { });
		
//		List<DtoComunRtpsEmpleadoEstablecimientoExterno> listanueva = (List<DtoComunRtpsEmpleadoEstablecimientoExterno>) filtro.getPaginacion().getPaginacionListaResultado();
		DominioPaginacion paginacionant = rtpsEmpleadoEstablecimientoDao.listarPaginado(usuarioActual, filtro);
		List<DtoComunRtpsEmpleadoEstablecimiento> listaant = paginacionant.getPaginacionListaResultado();

		for(int i = 0; i < listanueva.size(); i++) {
			
			DtoComunRtpsEmpleadoEstablecimiento dto = listanueva.get(i);
			
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
			DtoComunRtpsEmpleadoEstablecimiento dto = listaant.get(i);
			
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
	public DtoComunRtpsEmpleadoEstablecimiento coreAnular(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpleadoEstablecimiento dto) throws Exception {
		BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento = dto.obtenerBean();
		rtpsEmpleadoEstablecimiento = coreAnular(usuarioActual, rtpsEmpleadoEstablecimiento);
		dto.setTransaccionEstado(rtpsEmpleadoEstablecimiento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpleadoEstablecimiento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpleadoEstablecimiento coreAnular(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpleadoEstablecimiento = validar.prepararAnular(usuarioActual, rtpsEmpleadoEstablecimiento);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, rtpsEmpleadoEstablecimiento);
		if (!lst.isEmpty()) {
			rtpsEmpleadoEstablecimiento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpleadoEstablecimiento.setTransaccionListaMensajes(lst);
			return rtpsEmpleadoEstablecimiento;
		}
		
		// transaccion
		//rtpsEmpleadoEstablecimientoExterno.setEstado("I");
		rtpsEmpleadoEstablecimiento = rtpsEmpleadoEstablecimientoDao.coreActualizar(rtpsEmpleadoEstablecimiento);
		rtpsEmpleadoEstablecimiento.setTransaccionEstado(DominioTransaccion.OK);
		rtpsEmpleadoEstablecimiento.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return rtpsEmpleadoEstablecimiento;
	}

/*	public RtpsEmpleadoEstablecimiento coreAnular(SeguridadUsuarioActual usuarioActual, RtpsEmpleadoEstablecimientoPk pk) throws Exception {
		RtpsEmpleadoEstablecimiento bean = rtpsEmpleadoEstablecimientoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual,bean);
	}

	public RtpsEmpleadoEstablecimiento coreAnular(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreAnular(usuarioActual,new RtpsEmpleadoEstablecimientoPk( pempleado ,pcodigoautomatico));
	}*/

	@Transactional
	public DtoComunRtpsEmpleadoEstablecimiento coreEliminar(SeguridadUsuarioActual usuarioActual,DtoComunRtpsEmpleadoEstablecimiento dto) throws Exception {
		BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento = dto.obtenerBean();
		rtpsEmpleadoEstablecimiento = coreEliminar(usuarioActual, rtpsEmpleadoEstablecimiento);
		dto.setTransaccionEstado(rtpsEmpleadoEstablecimiento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(rtpsEmpleadoEstablecimiento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanRtpsEmpleadoEstablecimiento coreEliminar(SeguridadUsuarioActual usuarioActual, BeanRtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento) throws Exception {
		// valores por defecto - preparando objeto
		rtpsEmpleadoEstablecimiento = validar.prepararEliminar(usuarioActual, rtpsEmpleadoEstablecimiento);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, rtpsEmpleadoEstablecimiento);
		if (!lst.isEmpty()) {
			rtpsEmpleadoEstablecimiento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			rtpsEmpleadoEstablecimiento.setTransaccionListaMensajes(lst);
			return rtpsEmpleadoEstablecimiento;
		}
		
		// transaccion
		rtpsEmpleadoEstablecimientoDao.eliminar(rtpsEmpleadoEstablecimiento);
		rtpsEmpleadoEstablecimiento=new BeanRtpsEmpleadoEstablecimiento();
		rtpsEmpleadoEstablecimiento.setTransaccionEstado(DominioTransaccion.OK);
		return rtpsEmpleadoEstablecimiento;
	}
	/*
	public RtpsEmpleadoEstablecimiento coreEliminar(SeguridadUsuarioActual usuarioActual, RtpsEmpleadoEstablecimientoPk pk) throws Exception {
		RtpsEmpleadoEstablecimiento rtpsEmpleadoEstablecimiento = rtpsEmpleadoEstablecimientoDao.obtenerPorId(pk);
		return coreEliminar(usuarioActual,rtpsEmpleadoEstablecimiento);
	}

	public RtpsEmpleadoEstablecimiento coreEliminar(SeguridadUsuarioActual usuarioActual, Integer pcodigoautomatico) throws Exception {
		return coreEliminar(usuarioActual,new RtpsEmpleadoEstablecimientoPk( pempleado, null, pcodigoautomatico));
	}*/

}
