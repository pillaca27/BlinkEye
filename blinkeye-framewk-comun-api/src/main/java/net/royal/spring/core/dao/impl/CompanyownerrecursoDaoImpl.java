package net.royal.spring.core.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.core.dominio.BeanCompanyownerrecurso;
import net.royal.spring.core.dominio.BeanCompanyownerrecursoPk;
import net.royal.spring.core.dominio.BeanCorrelativosmast;
import net.royal.spring.core.dominio.BeanCorrelativosmastPk;
import net.royal.spring.core.dominio.dto.DtoComunCorrelativosmast;

@Repository
public class CompanyownerrecursoDaoImpl extends GenericoDaoImpl<BeanCompanyownerrecurso, BeanCompanyownerrecursoPk>{

	private static Logger logger = LogManager.getLogger(BeanCompanyownerrecurso.class);

	public CompanyownerrecursoDaoImpl() {
		super("companyownerrecurso");
	}
	
	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public List<BeanCompanyownerrecurso> listarPorRecurso(String tipoRecurso) {	
	    Criteria cri = this.getCriteria();
 		cri.add(Restrictions.eq("pk.Tiporecurso", tipoRecurso));		    		
 		List lista = cri.list();
 		return lista;
	}

	
	
	
	 public String obtenerNombreCompania(String idCompania)
     {
         String resultado = "";
         List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();         
         parametros.add(new DominioParametroPersistencia("p_compania", String.class, idCompania));
         resultado = (String) this.obtenerPorQuery("companyownerrecurso.obtenerNombreCompania", parametros);
                  
         return resultado;
     }
	
	
	
	
}
