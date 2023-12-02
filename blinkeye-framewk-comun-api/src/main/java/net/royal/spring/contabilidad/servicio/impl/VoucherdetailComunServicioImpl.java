package net.royal.spring.contabilidad.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.contabilidad.dao.impl.VoucherdetailComunDaoImpl;
import net.royal.spring.contabilidad.dominio.BeanVoucherdetail;
import net.royal.spring.contabilidad.dominio.dto.DtoComunVoucherdetail;
import net.royal.spring.contabilidad.servicio.validar.VoucherdetailComunServicioValidar;
import net.royal.spring.framework.core.UException;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.web.servicio.impl.GenericoServicioImpl;

@Service(value = "BeanServicioVoucherdetail")
public class VoucherdetailComunServicioImpl extends GenericoServicioImpl {
	
	@Autowired
	private VoucherdetailComunDaoImpl voucherdetailDao;

	@Autowired
	private VoucherdetailComunServicioValidar validar;
	
	public static String SPRING_NOMBRE = "BeanServicioVoucherdetail";
	private static Logger logger = LogManager.getLogger(VoucherdetailComunServicioImpl.class);

	@Transactional
	public DtoComunVoucherdetail coreInsertar(SeguridadUsuarioActual usuarioActual,DtoComunVoucherdetail dto) throws UException {
		BeanVoucherdetail voucherdetail = dto.obtenerBean();
		voucherdetail = coreInsertar(usuarioActual, voucherdetail);
		dto.setTransaccionEstado(voucherdetail.getTransaccionEstado());
		dto.setTransaccionListaMensajes(voucherdetail.getTransaccionListaMensajes());
		return dto;
	}
	
	@Transactional
	public BeanVoucherdetail coreInsertar(SeguridadUsuarioActual usuarioActual,BeanVoucherdetail voucherdetail) throws UException {
		// valores por defecto - preparando objeto
		voucherdetail = validar.prepararInsertar(usuarioActual, voucherdetail);
		
		// validaciones de negocio
		List<DominioMensajeUsuario> lst = validar.coreInsertar(usuarioActual, voucherdetail);
		if (!lst.isEmpty()) {
			voucherdetail.setTransaccionEstado(DominioTransaccion.VALIDACION);
			voucherdetail.setTransaccionListaMensajes(lst);
			return voucherdetail;
		}
		
		// transaccion
		voucherdetail = voucherdetailDao.coreInsertar(voucherdetail);
		voucherdetail.setTransaccionEstado(DominioTransaccion.OK);
		voucherdetail.setTransaccionListaMensajes(new ArrayList<DominioMensajeUsuario>());
		return voucherdetail;
	}
}
