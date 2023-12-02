package net.royal.spring.core.dao.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.core.dominio.BeanPersonamast;
import net.royal.spring.core.dominio.BeanPersonamastPk;
import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UHttpServletRequest;
import net.royal.spring.framework.modelo.FotoConfiguracion;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;

@Repository
public class PersonamastDaoImpl extends GenericoDaoImpl<BeanPersonamast, BeanPersonamastPk> {

	private static Logger logger = LogManager.getLogger(PersonamastDaoImpl.class);

	public PersonamastDaoImpl() {
		super("personamast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanPersonamast obtenerPorId(Integer ppersona) {
		return obtenerPorId(new BeanPersonamastPk(ppersona));
	}

	public BeanPersonamast coreInsertar(BeanPersonamast bean) {
		this.registrar(bean);
		return bean;
	}

	public BeanPersonamast coreInsertar(SeguridadUsuarioActual usuarioActual, BeanPersonamast bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.registrar(bean);
		return bean;
	}

	public BeanPersonamast coreActualizar(SeguridadUsuarioActual usuarioActual, BeanPersonamast bean, String estado) {
		if (UString.estaVacio(estado))
			estado = ConstanteEstadoGenerico.ACTIVO;
		bean.setEstado(estado);
		bean.setUltimafechamodif(new Date());
		bean.setUltimousuario(usuarioActual.getUsuario());
		this.actualizar(bean);
		return bean;
	}

	public BeanPersonamast coreActualizar(BeanPersonamast bean) {
		this.actualizar(bean);
		return bean;
	}

	public BeanPersonamast obtenerBeanPersona(Integer idPersona) {
		Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("pk.persona", idPersona));
		List<BeanPersonamast> lst = cri.list();

		if (lst.size() > 0)
			return lst.get(0);

		return null;
	}
	
	@Transactional
	public FotoConfiguracion obtenerParametroFotosBD()throws Exception {
		FotoConfiguracion cfg=new FotoConfiguracion();
		List lista = listarPorQuery(FotoConfiguracion.class, "personamast.obtenerParametroFotos");
		if (lista.size()==1) {
			FotoConfiguracion r1=(FotoConfiguracion)lista.get(0);
			cfg.setExtension(UString.obtenerValorCadenaSinNulo(r1.getExtension()).trim());
			cfg.setRuta(UString.obtenerValorCadenaSinNulo(r1.getRuta()).trim());
			cfg.setRutaweb(UString.obtenerValorCadenaSinNulo(r1.getRutaweb()).trim());
			cfg.setTipo(UString.obtenerValorCadenaSinNulo(r1.getTipo()).trim());
			return cfg;
		}
		return cfg;
	}
	
	private static FotoConfiguracion cfgGlobal=null;
	@Transactional
	public FotoConfiguracion fotoObtenerConfiguracion() throws Exception {
		if (cfgGlobal==null)
			cfgGlobal=obtenerParametroFotosBD();
		/*FotoConfiguracion cfg=new FotoConfiguracion();
		cfg.setRuta("C:\\cajacusco\\persona");
		cfg.setRutaweb("fotos");
		cfg.setExtension("JPG");
		cfg.setTipo("S");*/
		logger.debug(cfgGlobal.getRuta());
		logger.debug(cfgGlobal.getRutaweb());
		logger.debug(cfgGlobal.getTipo());
		logger.debug(cfgGlobal.getExtension());
		return cfgGlobal; 
	}
	
	@Transactional
	public String fotoObtenerRuta(Integer personaId,String documento) throws Exception {
		logger.debug("fotoObtenerRuta");
		logger.debug(personaId);
		logger.debug(documento);
		FotoConfiguracion cfg= fotoObtenerConfiguracion();
		String fotonombre="";
		String retorno="assets/layout/images/user.png";
		String rutaDisco=cfg.getRuta() + File.separator;
		if (cfg.getTipo().equals("S")) {
			logger.debug("persona id");
			rutaDisco=rutaDisco + UInteger.obtenerValorEnteroSinNulo(personaId).toString() + "." + cfg.getExtension();
			fotonombre = UInteger.obtenerValorEnteroSinNulo(personaId).toString() + "." + cfg.getExtension();
		}			
		else {
			logger.debug("documento");
			rutaDisco=rutaDisco + UString.obtenerValorCadenaSinNulo(documento) + "." + cfg.getExtension();
			fotonombre = UString.obtenerValorCadenaSinNulo(documento) + "." + cfg.getExtension();
		}
		logger.debug("rutaDisco:"+rutaDisco);
		logger.debug("fotonombre:"+fotonombre);
		String url = UHttpServletRequest.rutaWebServer(request);
		url = url + "/" + cfg.getRutaweb() + "/" + fotonombre;
		logger.debug("url:"+url);
		
		File f=new File(rutaDisco);
		if (f.exists()) {
			logger.debug("rutaDisco existe !!");	
			retorno = url;
		}else {
			logger.debug("rutaDisco no existe !!");
		}
		logger.debug("rutasssss");		
		logger.debug(retorno);
		return retorno; 
	}
	
	@Transactional
	public String fotoObtenerRuta(Integer personaId) throws Exception {
		logger.debug("fotoObtenerRuta");
		logger.debug(personaId);
		FotoConfiguracion cfg= fotoObtenerConfiguracion();
		String fotonombre="";
		String retorno="assets/layout/images/user.png";
		String rutaDisco=cfg.getRuta() + File.separator;

		rutaDisco=rutaDisco + UInteger.obtenerValorEnteroSinNulo(personaId).toString() + "." + cfg.getExtension();
		fotonombre = UInteger.obtenerValorEnteroSinNulo(personaId).toString() + "." + cfg.getExtension();
		
		logger.debug("rutaDisco:"+rutaDisco);
		logger.debug("fotonombre:"+fotonombre);
		String url = UHttpServletRequest.rutaWebServer(request);
		url = url + "/" + cfg.getRutaweb() + "/" + fotonombre;
		logger.debug("url:"+url);
		
		File f=new File(rutaDisco);
		if (f.exists()) {
			logger.debug("rutaDisco existe !!");	
			retorno = url;
		}else {
			logger.debug("rutaDisco no existe !!");
		}
		logger.debug("rutasssss");		
		logger.debug(retorno);
		return retorno; 
	}
	
}
