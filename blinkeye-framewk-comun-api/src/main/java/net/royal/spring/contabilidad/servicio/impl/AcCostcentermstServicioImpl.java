package net.royal.spring.contabilidad.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;
import net.royal.spring.contabilidad.dao.impl.AcCostcentermstDaoImpl;

import net.royal.spring.contabilidad.dominio.BeanAcCostcentermst;
import net.royal.spring.contabilidad.dominio.BeanAcCostcentermstPk;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenteraccount;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterafe;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcenterdestvalid;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcentermst;
import net.royal.spring.contabilidad.dominio.dto.DtoComunAcCostcentervendor;
import net.royal.spring.contabilidad.servicio.validar.AcCostcentermstServicioValidar;

@Service(value = "BeanServicioAcCostcentermst")
public class AcCostcentermstServicioImpl extends GenericoServicioImpl {
	public static String SPRING_NOMBRE = "BeanServicioAcCostcentermst";
	private static Logger logger = LogManager.getLogger(AcCostcentermstServicioImpl.class);

	@Autowired
	private AcCostcentermstDaoImpl acCostcentermstDao;

	@Autowired
	private AcCostcentermstServicioValidar validar;

	@Autowired
	private AcCostcenteraccountServicioImpl acCostcenteraccountServicioImpl;
	
	@Autowired
	private AcCostcentervendorServicioImpl acCostcentervendorServicioImpl;
	
	@Autowired
	private AcCostcenterdestvalidServicioImpl acCostcenterdestvalidServicioImpl;
	
	@Autowired
	private AcCostcenterafeServicioImpl acCostcenterafeServicioImpl;

	@Transactional
	public DtoComunAcCostcentermst coreEliminarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcentermst dto) throws Exception {
		BeanAcCostcentermst acSucursalgrupo = acCostcentermstDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = coreEliminar(usuarioActual, acSucursalgrupo,dto);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}


	@Transactional
	public DtoComunAcCostcentermst coreActualizarPorUuid(SeguridadUsuarioActual usuarioActual,DtoComunAcCostcentermst dto) throws Exception {
		BeanAcCostcentermst acSucursalgrupo = acCostcentermstDao.obtenerPorUuid(dto.getUuid());
		acSucursalgrupo = dto.obtenerBeanActualizar(acSucursalgrupo);
		acSucursalgrupo = coreActualizar(usuarioActual, acSucursalgrupo,dto);
		dto.setTransaccionEstado(acSucursalgrupo.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acSucursalgrupo.getTransaccionListaMensajes());
		return dto;
	}

	public DtoComunAcCostcentermst  coreAnularPorUuid(SeguridadUsuarioActual usuarioActual, DtoComunAcCostcentermst  dto)
			throws Exception {
		BeanAcCostcentermst bean = acCostcentermstDao.obtenerPorUuid(dto.getUuid());
		bean = validar.prepararAuditoria(usuarioActual, bean);
		List<DominioMensajeUsuario> lst = validar.coreAnular(usuarioActual, bean);
		if (!lst.isEmpty()) {
			dto.setTransaccionEstado(DominioTransaccion.VALIDACION);
			dto.setTransaccionListaMensajes(lst);
			return dto;
		}
		bean.setStatus("N");
		bean = acCostcentermstDao.coreActualizar(bean);
		dto.setTransaccionEstado(DominioTransaccion.OK);
		return dto;
	}
	
	@Transactional
	public DtoComunAcCostcentermst coreInsertar(SeguridadUsuarioActual usuarioActual, DtoComunAcCostcentermst dto)
			throws UException {
		BeanAcCostcentermst acCostcentermst = dto.obtenerBean();
		acCostcentermst = coreInsertar(usuarioActual, acCostcentermst, dto);
		dto.setTransaccionEstado(acCostcentermst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcentermst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcentermst coreInsertar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst acCostcentermst,
			DtoComunAcCostcentermst dto) throws UException {
		// valores por defecto - preparando objeto
		acCostcentermst = validar.prepararInsertar(usuarioActual, acCostcentermst);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, acCostcentermst);
		if (!lst.isEmpty()) {
			acCostcentermst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcentermst.setTransaccionListaMensajes(lst);
			return acCostcentermst;
		}

		// transaccion
		acCostcentermst = acCostcentermstDao.coreInsertar(acCostcentermst);

		if (dto.getLstDetalle1().size() > 0) {

			for (DtoComunAcCostcenteraccount detalle : dto.getLstDetalle1()) {

				if (detalle.getAccion().equals("N")) {
					detalle.setCostcenter(acCostcentermst.getPk().getCostcenter());
					acCostcenteraccountServicioImpl.coreInsertar(usuarioActual, detalle);
				}
			}
		}
		
		if (dto.getLstDetalle2().size() > 0) {

			for (DtoComunAcCostcentervendor detalle : dto.getLstDetalle2()) {

				if (detalle.getAccion().equals("N")) {
					detalle.setCostcenter(acCostcentermst.getPk().getCostcenter());
					acCostcentervendorServicioImpl.coreInsertar(usuarioActual, detalle);
				}
			}
		}
		
		if (dto.getLstDetalle3().size() > 0) {

			for (DtoComunAcCostcenterdestvalid detalle : dto.getLstDetalle3()) {

				if (detalle.getAccion().equals("N")) {
					detalle.setCostcenter(acCostcentermst.getPk().getCostcenter());
					acCostcenterdestvalidServicioImpl.coreInsertar(usuarioActual, detalle);
				}
			}
		}
		
		if (dto.getLstDetalle4().size() > 0) {

			for (DtoComunAcCostcenterafe detalle : dto.getLstDetalle4()) {

				if (detalle.getAccion().equals("N")) {
					detalle.setCostcenter(acCostcentermst.getPk().getCostcenter());
					acCostcenterafeServicioImpl.coreInsertar(usuarioActual, detalle);
				}
			}
		}
		
		
		acCostcentermst.setTransaccionEstado(DominioTransaccion.OK);
		acCostcentermst.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcentermst;
	}

	@Transactional
	public DtoComunAcCostcentermst coreActualizar(SeguridadUsuarioActual usuarioActual, DtoComunAcCostcentermst dto)
			throws UException {
		BeanAcCostcentermst acCostcentermst = dto.obtenerBean();
		acCostcentermst = coreActualizar(usuarioActual, acCostcentermst,dto);
		dto.setTransaccionEstado(acCostcentermst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcentermst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcentermst coreActualizar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst acCostcentermst, DtoComunAcCostcentermst dto)
			throws UException {
		// valores por defecto - preparando objeto
		acCostcentermst = validar.prepararActualizar(usuarioActual, acCostcentermst);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreActualizar(usuarioActual, acCostcentermst);
		if (!lst.isEmpty()) {
			acCostcentermst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcentermst.setTransaccionListaMensajes(lst);
			return acCostcentermst;
		}

		// transaccion
		acCostcentermst = acCostcentermstDao.coreActualizar(acCostcentermst);

		if (dto.getLstDetalle1().size() > 0) {

			// ELIMINAR PRIMERO
			for (DtoComunAcCostcenteraccount detalle : dto.getLstDetalle1()) {

				if (detalle.getAccion().equals("E")) {
					acCostcenteraccountServicioImpl.coreEliminar(usuarioActual, detalle);
				}
			}

			for (DtoComunAcCostcenteraccount detalle : dto.getLstDetalle1()) {

				if (detalle.getAccion().equals("N")) {
					detalle.setCostcenter(acCostcentermst.getPk().getCostcenter());
					acCostcenteraccountServicioImpl.coreInsertar(usuarioActual, detalle);
				} else if (detalle.getAccion().equals("A")) {
					acCostcenteraccountServicioImpl.coreActualizar(usuarioActual, detalle);
				}
			}
		}
		
		if (dto.getLstDetalle2().size() > 0) {

			// ELIMINAR PRIMERO
			for (DtoComunAcCostcentervendor detalle : dto.getLstDetalle2()) {

				if (detalle.getAccion().equals("E")) {
					acCostcentervendorServicioImpl.coreEliminar(usuarioActual, detalle);
				}
			}

			for (DtoComunAcCostcentervendor detalle : dto.getLstDetalle2()) {

				if (detalle.getAccion().equals("N")) {
					detalle.setCostcenter(acCostcentermst.getPk().getCostcenter());
					acCostcentervendorServicioImpl.coreInsertar(usuarioActual, detalle);
				} else if (detalle.getAccion().equals("A")) {
					acCostcentervendorServicioImpl.coreActualizar(usuarioActual, detalle);
				}
			}
		}
		
		if (dto.getLstDetalle3().size() > 0) {

			// ELIMINAR PRIMERO
			for (DtoComunAcCostcenterdestvalid detalle : dto.getLstDetalle3()) {

				if (detalle.getAccion().equals("E")) {
					acCostcenterdestvalidServicioImpl.coreEliminar(usuarioActual, detalle);
				}
			}

			for (DtoComunAcCostcenterdestvalid detalle : dto.getLstDetalle3()) {

				if (detalle.getAccion().equals("N")) {
					detalle.setCostcenter(acCostcentermst.getPk().getCostcenter());
					acCostcenterdestvalidServicioImpl.coreInsertar(usuarioActual, detalle);
				} else if (detalle.getAccion().equals("A")) {
					acCostcenterdestvalidServicioImpl.coreActualizar(usuarioActual, detalle);
				}
			}
		}
		
		if (dto.getLstDetalle4().size() > 0) {

			// ELIMINAR PRIMERO
			for (DtoComunAcCostcenterafe detalle : dto.getLstDetalle4()) {

				if (detalle.getAccion().equals("E")) {
					acCostcenterafeServicioImpl.coreEliminar(usuarioActual, detalle);
				}
			}

			for (DtoComunAcCostcenterafe detalle : dto.getLstDetalle4()) {

				if (detalle.getAccion().equals("N")) {
					detalle.setCostcenter(acCostcentermst.getPk().getCostcenter());
					acCostcenterafeServicioImpl.coreInsertar(usuarioActual, detalle);
				} else if (detalle.getAccion().equals("A")) {
					acCostcenterafeServicioImpl.coreActualizar(usuarioActual, detalle);
				}
			}
		}
		
		acCostcentermst.setTransaccionEstado(DominioTransaccion.OK);
		acCostcentermst.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return acCostcentermst;
	}

	@Transactional
	public DtoComunAcCostcentermst coreEliminar(SeguridadUsuarioActual usuarioActual, DtoComunAcCostcentermst dto)
			throws UException {
		BeanAcCostcentermst acCostcentermst = dto.obtenerBean();
		acCostcentermst = coreEliminar(usuarioActual, acCostcentermst,dto);
		dto.setTransaccionEstado(acCostcentermst.getTransaccionEstado());
		dto.setTransaccionListaMensajes(acCostcentermst.getTransaccionListaMensajes());
		return dto;
	}

	@Transactional
	public BeanAcCostcentermst coreEliminar(SeguridadUsuarioActual usuarioActual, BeanAcCostcentermst acCostcentermst, DtoComunAcCostcentermst dto)
			throws UException {
		// valores por defecto - preparando objeto
		acCostcentermst = validar.prepararEliminar(usuarioActual, acCostcentermst);

		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreEliminar(usuarioActual, acCostcentermst);
		if (!lst.isEmpty()) {
			acCostcentermst.setTransaccionEstado(DominioTransaccion.VALIDACION);
			acCostcentermst.setTransaccionListaMensajes(lst);
			return acCostcentermst;
		}


		if (dto.getLstDetalle1().size() > 0) {
			for (DtoComunAcCostcenteraccount detalle : dto.getLstDetalle1()) {
					acCostcenteraccountServicioImpl.coreEliminar(usuarioActual, detalle);		
			}
		}
		
		if (dto.getLstDetalle2().size() > 0) {
			for (DtoComunAcCostcentervendor detalle : dto.getLstDetalle2()) {
					acCostcentervendorServicioImpl.coreEliminar(usuarioActual, detalle);
			}
		}
		
		if (dto.getLstDetalle3().size() > 0) {
			for (DtoComunAcCostcenterdestvalid detalle : dto.getLstDetalle3()) {
					acCostcenterdestvalidServicioImpl.coreEliminar(usuarioActual, detalle);				
			}
		}
		
		if (dto.getLstDetalle4().size() > 0) {
			for (DtoComunAcCostcenterafe detalle : dto.getLstDetalle4()) {
					acCostcenterafeServicioImpl.coreEliminar(usuarioActual, detalle);				
			}
		}
		
		// transaccion
		acCostcentermstDao.eliminar(acCostcentermst);
		acCostcentermst = new BeanAcCostcentermst();
		acCostcentermst.setTransaccionEstado(DominioTransaccion.OK);
		return acCostcentermst;
	}


}
