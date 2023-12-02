package net.royal.spring.core.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dao.impl.DepartamentoDaoImpl;
import net.royal.spring.core.dominio.BeanDepartamento;
import net.royal.spring.core.dominio.BeanDepartamentoPk;
import net.royal.spring.core.dominio.dto.DtoComunDepartamento;
import net.royal.spring.core.dominio.dto.DtoComunProvincia;
import net.royal.spring.core.dominio.dto.DtoComunZonapostal;
import net.royal.spring.core.servicio.validar.DepartamentoServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioDepartamento")
public class DepartamentoServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioDepartamento";
	private static Logger logger = LogManager.getLogger(DepartamentoServicioImpl.class);

	@Autowired
	private DepartamentoDaoImpl departamentoDao;

	@Autowired
	private DepartamentoServicioValidar validar;

	@Autowired
	private ProvinciaServicioImpl provinciaServicioImpl;
	
	@Autowired
	private ZonapostalServicioImpl zonapostalServicioImpl;
	
	@Transactional
	public DtoComunDepartamento coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunDepartamento dto) throws Exception {
		BeanDepartamento acSucursalgrupo = departamentoDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo,dto);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}


	@Transactional
	public DtoComunDepartamento coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunDepartamento dto) throws Exception {
		BeanDepartamento acSucursalgrupo = departamentoDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = dto.obtenerBeanActualizar(acSucursalgrupo);
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo,dto);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	public DtoComunDepartamento  coreAnularPorUuid(SeguridadUsuarioActual usuarioActual, DtoComunDepartamento  dto)
			throws Exception {
		BeanDepartamento bean = departamentoDao.obtenerPorUuid(dto.getUuid());
		bean = validar.prepararAuditoria(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, bean);
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return dto;
		}
		bean.setEstado("N");
		bean = departamentoDao.coreActualizar(bean);
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}

	@Transactional
	public DtoComunDepartamento coreInsertar(SeguridadUsuarioActual usuarioActual, DtoComunDepartamento dto) throws UException {
		BeanDepartamento departamento = dto.obtenerBean();
		departamento = coreInsertar(usuarioActual, departamento, dto);
		dto.setTransaccionEstado(departamento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(departamento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanDepartamento coreInsertar(SeguridadUsuarioActual usuarioActual, BeanDepartamento departamento,
			DtoComunDepartamento dto) throws UException {
		// valores por defecto - preparando objeto
		departamento = validar.prepararInsertar(usuarioActual, departamento);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, departamento);
		if (!lst.isEmpty()) {
			departamento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			departamento.setTransaccionListaMensajes(lst);
			return departamento;
		}

		// transaccion
		departamento = departamentoDao.coreInsertar(departamento);

		if (dto.getProvincias().size() > 0) {

			for (DtoComunProvincia detalle : dto.getProvincias()) {
				if (detalle.getAccion().equals("N")) {
					detalle.setDepartamento(departamento.getPk().getDepartamento());
					detalle.setPais(departamento.getPais());
					provinciaServicioImpl.coreInsertar(usuarioActual, detalle);
				}
			}
		}

		departamento.setTransaccionEstado(DominioTransaccion.OK);
		departamento.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return departamento;
	}

	@Transactional
	public DtoComunDepartamento coreActualizar(SeguridadUsuarioActual usuarioActual, DtoComunDepartamento dto) throws UException {
		BeanDepartamento departamento = dto.obtenerBean();
		departamento = coreActualizar(usuarioActual, departamento,dto);
		dto.setTransaccionEstado(departamento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(departamento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanDepartamento coreActualizar(SeguridadUsuarioActual usuarioActual, BeanDepartamento departamento, DtoComunDepartamento dto)
			throws UException {
		// valores por defecto - preparando objeto
		departamento = validar.prepararActualizar(usuarioActual, departamento);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, departamento);
		if (!lst.isEmpty()) {
			departamento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			departamento.setTransaccionListaMensajes(lst);
			return departamento;
		}
		
		if(dto.getAccion().equals("PROV")) {
			if (dto.getProvincias().size() > 0) {

				for (DtoComunProvincia detalle : dto.getProvincias()) {
					if (detalle.getAccion().equals("E")) {
						
						provinciaServicioImpl.coreEliminar(usuarioActual, detalle);
					}
				}
			}
			

			if (dto.getProvincias().size() > 0) {

				for (DtoComunProvincia detalle : dto.getProvincias()) {
					if (detalle.getAccion().equals("N")) {
						provinciaServicioImpl.coreInsertar(usuarioActual, detalle);
					}
					else if (detalle.getAccion().equals("A")) {
						provinciaServicioImpl.coreActualizar(usuarioActual, detalle);
					}
				}
			}
		}
		else if(dto.getAccion().equals("DISTR")) {
			if(dto.getDistritos().size() > 0) {
				
				for (DtoComunZonapostal detalle : dto.getDistritos()) {				
					if(detalle.getAccion().equals("E")) {
						zonapostalServicioImpl.coreEliminar(usuarioActual, detalle);
					}	
				}
			}
			
			if(dto.getDistritos().size() > 0) {
				
				for (DtoComunZonapostal detalle : dto.getDistritos()) {				
					if(detalle.getAccion().equals("N")) {
						detalle.setPais(dto.getPais());
						detalle.setUbigeo(detalle.getDepartamento().trim()+detalle.getProvincia().trim()+detalle.getCodigopostal().trim());
						zonapostalServicioImpl.coreInsertar(usuarioActual, detalle);
					}	
					else if(detalle.getAccion().equals("A")) {
						zonapostalServicioImpl.coreActualizar(usuarioActual, detalle);
					}
				}
			}
		}
		

		
		if(dto.getDistritos().size() > 0) {
			
		}

		// transaccion
		departamento = departamentoDao.coreActualizar(departamento);
		departamento.setTransaccionEstado(DominioTransaccion.OK);
		departamento.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return departamento;
	}

	@Transactional
	public DtoComunDepartamento coreAnular(SeguridadUsuarioActual usuarioActual, DtoComunDepartamento dto) throws UException {
		BeanDepartamento departamento = dto.obtenerBean();
		departamento = coreAnular(usuarioActual, departamento);
		dto.setTransaccionEstado(departamento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(departamento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanDepartamento coreAnular(SeguridadUsuarioActual usuarioActual, BeanDepartamento departamento) throws UException {
		// valores por defecto - preparando objeto
		departamento = validar.prepararAnular(usuarioActual, departamento);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, departamento);
		if (!lst.isEmpty()) {
			departamento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			departamento.setTransaccionListaMensajes(lst);
			return departamento;
		}

		// transaccion
		departamento.setEstado("I");
		departamento = departamentoDao.coreActualizar(departamento);
		departamento.setTransaccionEstado(DominioTransaccion.OK);
		departamento.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return departamento;
	}

	public BeanDepartamento coreAnular(SeguridadUsuarioActual usuarioActual, BeanDepartamentoPk pk) throws UException {
		BeanDepartamento bean = departamentoDao.obtenerPorId(pk);
		return coreAnular(usuarioActual, bean);
	}

	public BeanDepartamento coreAnular(SeguridadUsuarioActual usuarioActual, String pdepartamento) throws UException {
		return coreAnular(usuarioActual, new BeanDepartamentoPk(pdepartamento));
	}

	@Transactional
	public DtoComunDepartamento coreEliminar(SeguridadUsuarioActual usuarioActual, DtoComunDepartamento dto) throws UException {
		BeanDepartamento departamento = dto.obtenerBean();
		departamento = coreEliminar(usuarioActual, departamento,dto);
		dto.setTransaccionEstado(departamento.getTransaccionEstado());
		dto.setTransaccionListaMensajes(departamento.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanDepartamento coreEliminar(SeguridadUsuarioActual usuarioActual, BeanDepartamento departamento, DtoComunDepartamento dto)
			throws UException {
		// valores por defecto - preparando objeto
		departamento = validar.prepararEliminar(usuarioActual, departamento);

		if (dto.getProvincias().size() > 0) {

			for (DtoComunProvincia detalle : dto.getProvincias()) {
					provinciaServicioImpl.coreEliminar(usuarioActual, detalle);				
			}
		}
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, departamento);
		if (!lst.isEmpty()) {
			departamento.setTransaccionEstado(DominioTransaccion.VALIDACION);
			departamento.setTransaccionListaMensajes(lst);
			return departamento;
		}

		// transaccion
		departamentoDao.eliminar(departamento);
		departamento = new BeanDepartamento();
		departamento.setTransaccionEstado(DominioTransaccion.OK);
		return departamento;
	}

}
