package net.royal.spring.sistema.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.sistema.dominio.BeanSyDefinicionpropiedad;
import net.royal.spring.sistema.dominio.BeanSyDefinicionpropiedadPk;

@Repository
public class SyDefinicionpropiedadDaoImpl extends GenericoDaoImpl<BeanSyDefinicionpropiedad, BeanSyDefinicionpropiedadPk> {

	private static final long serialVersionUID = 1L;

	public SyDefinicionpropiedadDaoImpl() {
		super("sydefinicionpropiedad");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<BeanSyDefinicionpropiedad> listarPorDefinicion(Integer idApi, Integer definicionId) {
		Criteria criteria = getCriteria();
		criteria = criteria.add(Restrictions.eq("pk.idapi", idApi));
		criteria = criteria.add(Restrictions.eq("pk.iddefinicion", definicionId));
		return criteria.list();
	}

}
