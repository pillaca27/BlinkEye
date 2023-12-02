package net.royal.spring.logistica.dao.impl;



import javax.annotation.Resource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.logistica.dominio.BeanWhCommoditysub;
import net.royal.spring.logistica.dominio.BeanWhCommoditysubPk;



@Repository
public class WhCommoditysubDaoImpl extends GenericoDaoImpl<BeanWhCommoditysub, BeanWhCommoditysubPk> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = LogManager.getLogger(BeanWhCommoditysub.class);

	public WhCommoditysubDaoImpl() {
		super("whcommoditysub");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public BeanWhCommoditysub obtenerPorId(String pcommodity01,String pcommodity02) {
		return obtenerPorId(new BeanWhCommoditysubPk( pcommodity01, pcommodity02));
	}

	public BeanWhCommoditysub coreInsertar(BeanWhCommoditysub bean) {
		this.registrar(bean);
		return bean;
	}


	public BeanWhCommoditysub coreActualizar(BeanWhCommoditysub bean) {
		this.actualizar(bean);
		return bean;
	}







}
