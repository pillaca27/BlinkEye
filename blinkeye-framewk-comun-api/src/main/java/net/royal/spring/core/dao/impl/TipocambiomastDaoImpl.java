package net.royal.spring.core.dao.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import net.royal.spring.framework.constante.ConstanteEstadoGenerico;
import net.royal.spring.framework.core.UValidador;
import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UFechaHora;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.core.dominio.BeanTipocambiomast;
import net.royal.spring.core.dominio.BeanTipocambiomastPk;
import net.royal.spring.core.dominio.dto.DtoComunTipocambiomast;
import net.royal.spring.core.dominio.filtro.FiltroComunTipocambiomast;
import net.royal.spring.core.dominio.lista.DtlComunTipocambiomast;

@Repository
public class TipocambiomastDaoImpl extends GenericoDaoImpl<BeanTipocambiomast, BeanTipocambiomastPk> {

	private static Logger logger = LogManager.getLogger(BeanTipocambiomast.class);
	
	@Autowired
	private ParametrosmastDaoImpl parametrosmastDao;

	public TipocambiomastDaoImpl() {
		super("tipocambiomast");
	}

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}
	
	public DtoComunTipocambiomast obtenerDtoPorUuid(String uuid) {
		DtoComunTipocambiomast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_uuid", String.class, uuid));
		List lst = listarPorQuery(DtoComunTipocambiomast.class, "tipocambiomast.obtenerDtoPorUuid", parametros);
		if (lst.size() == 1) {
			dto = (DtoComunTipocambiomast) lst.get(0); 
		}
		return dto;
	}

	public BeanTipocambiomast obtenerPorId(String pmonedacodigo, String pmonedacambiocodigo,
			java.util.Date pfechacambio) {
		return obtenerPorId(new BeanTipocambiomastPk(pmonedacodigo, pmonedacambiocodigo, pfechacambio));
	}

	public BeanTipocambiomast coreInsertar(BeanTipocambiomast bean) {
		this.registrar(bean);
		return bean;
	}

	public BeanTipocambiomast coreActualizar(BeanTipocambiomast bean) {
		this.actualizar(bean);
		return bean;
	}

	public DtoComunTipocambiomast obtenerDtoPorId(DtoComunTipocambiomast pk) {
		DtoComunTipocambiomast dto = null;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_monedacodigo", String.class, pk.getMonedacodigo()));
		parametros.add(
				new DominioParametroPersistencia("p_monedacambiocodigo", String.class, pk.getMonedacambiocodigo()));
		parametros.add(new DominioParametroPersistencia("p_fechacambio", Date.class, pk.getFechacambio()));

		System.out.println(pk.getMonedacodigo());
		System.out.println(pk.getMonedacambiocodigo());
		System.out.println(pk.getFechacambio());

		List lst = listarPorQuery(DtoComunTipocambiomast.class, "tipocambiomast.obtenerDtoPorId", parametros);
		if (lst.size() == 1)
			dto = (DtoComunTipocambiomast) lst.get(0);
		return dto;
	}

	public DominioPaginacion listarPaginado(SeguridadUsuarioActual usuarioActual, FiltroComunTipocambiomast filtro) throws Exception {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.esNuloVacio(filtro.getFechacambiostring()))
			filtro.setFechacambiostring("19000101");
		
		if(!UFechaHora.esNuloVacio(filtro.getFechacambio())) {
			filtro.setFechacambiostring(UFechaHora.convertirFechaCadena(filtro.getFechacambio(), "yyyyMMdd"));
		}
		// TODO Tipocambiomast.Paginacion
		//parametros.add(new DominioParametroPersistencia("p_fechacambio", Date.class, filtro.getFechacambio()));
		parametros.add(new DominioParametroPersistencia("p_fechacambio", String.class, filtro.getFechacambiostring()));
		parametros.add(new DominioParametroPersistencia("p_estado", String.class, filtro.getEstado()));

		Integer registros = contar("tipocambiomast.listarPaginadoContar", parametros);
		List lst = listarConPaginacion(filtro.getPaginacion(), parametros, "tipocambiomast.listarPaginadoSentencia",
				DtlComunTipocambiomast.class);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(registros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}

	public BeanTipocambiomast obtenerTipoCambioMaximo(String monedaCodigo, String monedaCambioCodigo, Date fecha) {
		BeanTipocambiomast tipo = null;
		Criteria cri = this.getCriteria();

		cri.add(Restrictions.eq("pk.monedacodigo", monedaCodigo));
		cri.add(Restrictions.eq("pk.monedacambiocodigo", monedaCambioCodigo));
		cri.add(Restrictions.le("pk.fechacambio", fecha));
		cri.addOrder(Order.desc("pk.fechacambio"));
		cri.setMaxResults(1);

		List<BeanTipocambiomast> list = this.listarPorCriterios(cri);

		if (list == null)
			return null;
		if (list.size() != 1)
			return null;
		return list.get(0);
	}

	public BigDecimal obtenerFactor(DtoComunTipocambiomast pk) throws Exception {
		logger.debug("obtenerFactor");
		String rateType = pk.getAuxRateType();
		Date fecha = null;
		if (UString.esNuloVacio(pk.getFechacambiostring())) {
			fecha = pk.getFechacambio();
		} else {
			fecha = new SimpleDateFormat("yyyyMMdd").parse(pk.getFechacambiostring());
		}

		///// codigo original
		BeanTipocambiomast tipocambiomast = null;
		BigDecimal rate = null;
		BigDecimal wFactorcomprasbs = null;
		BigDecimal wFactorventasbs = null;

		// == 1 =================================
		if (rateType.equals("P") || rateType.equals("C") || rateType.equals("V") || rateType.equals("CS")
				|| rateType.equals("VS")) {

			String rateDaily = parametrosmastDao.obtenerTexto("999999", "SY", "RATEDAILY");
			if (rateDaily == null)
				rateDaily = "";
			if (rateDaily.equals("S")) {
				tipocambiomast = this.obtenerPorId("EX", "LO", fecha);
				logger.debug(tipocambiomast);
				tipocambiomast = UValidador.esNulo(tipocambiomast) ? new BeanTipocambiomast() : tipocambiomast;
				if (rateType.length() == 0) {
					rate = tipocambiomast.getFactor();
				} else {
					if (rateType.equals("P")) {
						rate = tipocambiomast.getFactorpromedio();
					}
					if (rateType.equals("C")) {
						rate = tipocambiomast.getFactorcompra();
					}
					if (rateType.equals("V")) {
						rate = tipocambiomast.getFactorventa();
					}
					if (rateType.equals("CS")) {
						rate = tipocambiomast.getFactorcomprasbs();
					}
					if (rateType.equals("VS")) {
						rate = tipocambiomast.getFactorventasbs();
					}
				}
				return rate;
			}
		}
		// == 2 =================================
		// exchangerate
		if (rateType.equals("S") || rateType.equals("T")) {
//			Exchangerate exchangerate = exchangerateDao
//					.obtenerPorPeriodo(fecha);
//			if (exchangerate != null) {
//				wFactorcomprasbs = exchangerate.getSbsrate();
//				wFactorventasbs = exchangerate.getSbsrate();
//			}
		} else {
			tipocambiomast = this.obtenerTipoCambioMaximo("EX", "LO", fecha);
			wFactorcomprasbs = tipocambiomast.getFactorcomprasbs();
			wFactorventasbs = tipocambiomast.getFactorventasbs();
		}

		// == 3 =================================
		if (rateType.length() == 0) {
			rate = tipocambiomast.getFactor();
		} else {
			if (rateType.equals("P")) {
				rate = tipocambiomast.getFactorpromedio();
			}
			if (rateType.equals("C")) {
				rate = tipocambiomast.getFactorcompra();
			}
			if (rateType.equals("V")) {
				rate = tipocambiomast.getFactorventa();
			}
			if (rateType.equals("VS")) {
				rate = tipocambiomast.getFactorventasbs();
			}
			if (rateType.equals("CS")) {
				rate = tipocambiomast.getFactorcomprasbs();
			}
			if (rateType.equals("S")) {
				rate = wFactorventasbs;
			}
			if (rateType.equals("T")) {
				rate = wFactorcomprasbs;
			}
		}
		return rate;
	}
	
	public DominioPaginacion exportarTipoCambio(FiltroComunTipocambiomast filtro) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		// TODO BeanTipocambiomast.listarPaginado : modificar query / modificar propiedades
		if (UString.esNuloVacio(filtro.getFechacambiostring()))
			filtro.setFechacambiostring("");
		
		parametros.add(new DominioParametroPersistencia("p_fechacambio", String.class, filtro.getFechacambiostring()));

		List lst = listarPorQuery(DtlComunTipocambiomast.class, "tipocambiomast.listarPaginadoSentencia", parametros);
		filtro.getPaginacion().setPaginacionListaResultado(lst);
		return filtro.getPaginacion();
	}
}
