package net.royal.spring.workflow.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.royal.spring.framework.modelo.generico.DominioPaginacion;
import net.royal.spring.framework.modelo.generico.DominioParametroPersistencia;
import net.royal.spring.framework.modelo.generico.dto.DtoTabla;
import net.royal.spring.framework.modelo.seguridad.SeguridadUsuarioActual;
import net.royal.spring.framework.util.UBoolean;
import net.royal.spring.framework.util.UInteger;
import net.royal.spring.framework.util.UString;
import net.royal.spring.framework.web.dao.impl.GenericoDaoImpl;
import net.royal.spring.workflow.dominio.WfProcesoVersiones;
import net.royal.spring.workflow.dominio.WfProcesoVersionesPk;
import net.royal.spring.workflow.dominio.WfTransacciones;
import net.royal.spring.workflow.dominio.dto.DtoAprobacionAccion;
import net.royal.spring.workflow.dominio.dto.DtoAprobacionAcciones;
import net.royal.spring.workflow.dominio.dto.DtoFlujo;
import net.royal.spring.workflow.dominio.dto.DtoFlujoAdjunto;
import net.royal.spring.workflow.dominio.dto.DtoFlujoConfiguracion;
import net.royal.spring.workflow.dominio.dto.DtoFlujoSolicitud;
import net.royal.spring.workflow.dominio.dto.DtoFlujoTransaccionRequest;
import net.royal.spring.workflow.dominio.dto.DtoOrigenDatos;
import net.royal.spring.workflow.dominio.dto.DtoPlanificacionGenerar;
import net.royal.spring.workflow.dominio.dto.DtoProcesoC2Header;
import net.royal.spring.workflow.dominio.dto.DtoSeguimientoVistaAvanzada;
import net.royal.spring.workflow.dominio.dto.DtoTransaccionValidaciones;
import net.royal.spring.workflow.dominio.dto.DtoTransaccionVistaAvanzada;
import net.royal.spring.workflow.dominio.dto.DtoWfEstado;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujo;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoConfiguracion;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoNivel;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoNivelAccion;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoNivelAprobador;
import net.royal.spring.workflow.dominio.dto.DtoWfFlujoNivelDocumento;
import net.royal.spring.workflow.dominio.dto.DtoWfProceso;
import net.royal.spring.workflow.dominio.dto.DtoWfRol;
import net.royal.spring.workflow.dominio.dto.DtoWfTipoDocumento;
import net.royal.spring.workflow.dominio.dto.DtoWfTransaccionTemp;
import net.royal.spring.workflow.dominio.dto.DtoWfVariable;
import net.royal.spring.workflow.dominio.dto.DtoWfVistaAdminConfiguracionDocumentos;
import net.royal.spring.workflow.dominio.dto.Dtoregistroproveedor;
import net.royal.spring.workflow.dominio.filtro.FiltroSolicitudes;
import net.royal.spring.workflow.dominio.filtro.FiltroSyDocumentos;

@Repository
public class WfTransaccionesDaoImpl extends GenericoDaoImpl<WfTransacciones, Integer> {

	private static final long serialVersionUID = 1L;

	public WfTransaccionesDaoImpl() {
		super("wftransacciones");
	}

	@Autowired
	private WfProcesosDaoImpl wfProcesosDao;

	@Autowired
	private WfProcesoVersionesDaoImpl wfProcesoVersionesDaoImpl;

	@Resource(name = "sessionFactory")
	public void asignarSessionFactory(SessionFactory factory) {
		super.setSessionFactory(factory);
	}

	public DtoWfProceso obtenerProcesoPorId(String uuid) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_procesoUUID", String.class, uuid));
		List lst = listarPorQuery(DtoWfProceso.class, "wftransacciones.obtenerProcesoPorId", parametros);
		if (lst.size() != 1) {
			return null;
		}
		DtoWfProceso d = (DtoWfProceso) lst.get(0);
		d.setFlagPlanificacionGenerar(UBoolean.validarFlag(d.getFlgPlanificacionGenerarString()));
		d.setFlagComunicacionAlerta(UBoolean.validarFlag(d.getFlagComunicacionAlertaString()));
		d.setFlagCorreoNiveles(UBoolean.validarFlag(d.getFlagCorreoNivelesString()));
		return d;
	}

	public List<DtoWfTipoDocumento> obtenerTipoDocumentoProceso(String proceso) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		List lst = listarPorQuery(DtoWfTipoDocumento.class, "wftransacciones.obtenerTipoDocumentoProceso", parametros);
		return lst;
	}

	public List<DtoWfVariable> obtenerVariableProceso(String proceso) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		List lst = listarPorQuery(DtoWfVariable.class, "wftransacciones.obtenerVariableProceso", parametros);
		return lst;
	}

	public List<DtoWfEstado> obtenerEstadoProceso(String proceso) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		List lst = listarPorQuery(DtoWfEstado.class, "wftransacciones.obtenerEstadoProceso", parametros);
		return lst;
	}

	public List<DtoWfFlujo> obtenerFlujoProceso(String proceso, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		List lst = listarPorQuery(DtoWfFlujo.class, "wftransacciones.obtenerFlujoProceso", parametros);
		return lst;
	}

	public List<DtoWfFlujoNivel> obtenerNivelProceso(String proceso, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		List lst = listarPorQuery(DtoWfFlujoNivel.class, "wftransacciones.obtenerNivelProceso", parametros);
		for (DtoWfFlujoNivel nivel : (List<DtoWfFlujoNivel>) lst) {
			nivel.setCorreojefe(UBoolean.validarFlag(nivel.getJefeAux()));
			nivel.setCorreosolicitante(UBoolean.validarFlag(nivel.getSoliAux()));
			nivel.setTipoaprobador(UString.estaVacio(nivel.getTipoaprobador()) ? "PERS" : nivel.getTipoaprobador());
			nivel.setCondicionaprobacion(
					UString.estaVacio(nivel.getCondicionaprobacion()) ? "SOLOUNO" : nivel.getCondicionaprobacion());
			if (UString.estaVacio(nivel.getBtnAprobarAux())) {
				nivel.setBtnAprobarAux("S");
			}
			if (UString.estaVacio(nivel.getBtnRechazarAux())) {
				nivel.setBtnRechazarAux("S");
			}
			if (UString.estaVacio(nivel.getBtnDevolverAux())) {
				nivel.setBtnDevolverAux("S");
			}
			if (UString.estaVacio(nivel.getBtnNotificarAux())) {
				nivel.setBtnNotificarAux("S");
			}
			if (UString.estaVacio(nivel.getDocumentoFlgColumnaGrupo())) {
				nivel.setDocumentoFlgColumnaGrupo("N");
			}
			if (UString.estaVacio(nivel.getDocumentoFlgColumnaNuevo())) {
				nivel.setDocumentoFlgColumnaNuevo("N");
			}
			nivel.setBtnAprobar(UBoolean.validarFlag(nivel.getBtnAprobarAux()));
			nivel.setBtnDevolver(UBoolean.validarFlag(nivel.getBtnDevolverAux()));
			nivel.setBtnRechazar(UBoolean.validarFlag(nivel.getBtnRechazarAux()));
			nivel.setBtnNotificar(UBoolean.validarFlag(nivel.getBtnNotificarAux()));

			nivel.setAuxFlagPlanificacionEditar(UBoolean.validarFlag(nivel.getFlagPlanificacionEditar()));
			nivel.setAuxFlagPlanificacionVer(UBoolean.validarFlag(nivel.getFlagPlanificacionVer()));

			/****/
			nivel.setAuxFlgPlanificacionValidar(UBoolean.validarFlag(nivel.getFlgPlanificacionValidar()));

			nivel.setAuxDocumentoFlgColumnaGrupo(UBoolean.validarFlag(nivel.getDocumentoFlgColumnaGrupo()));
			nivel.setAuxDocumentoFlgColumnaNuevo(UBoolean.validarFlag(nivel.getDocumentoFlgColumnaNuevo()));

			nivel.setAuxFlgComentarioDetalladoAprobar(UBoolean.validarFlag(nivel.getFlgComentarioDetalladoAprobar()));
			nivel.setAuxFlgComentarioDetalladoRechazar(UBoolean.validarFlag(nivel.getFlgComentarioDetalladoRechazar()));
			nivel.setAuxFlgComentarioDetalladoDevolver(UBoolean.validarFlag(nivel.getFlgComentarioDetalladoDevolver()));

			nivel.setAuxFlgCorreoPersonaReferencia(UBoolean.validarFlag(nivel.getFlgCorreoPersonaReferencia()));
			nivel.setAuxFlgCorreoTransaccion(UBoolean.validarFlag(nivel.getFlgCorreoTransaccion()));
			nivel.setAuxFlgCorreoPersona(UBoolean.validarFlag(nivel.getFlgCorreoPersona()));

			nivel.setAuxFlgAprobarComentario(UBoolean.validarFlag(nivel.getFlgAprobarComentario()));

			nivel.setAuxFlgCorreoAdjuntarDocumentos(UBoolean.validarFlag(nivel.getFlgCorreoAdjuntarDocumentos()));
		}
		return lst;
	}

	public List<DtoWfFlujoConfiguracion> obtenerConfiguracionProceso(String proceso, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		List<DtoWfFlujoConfiguracion> lst = new ArrayList<DtoWfFlujoConfiguracion>();
		List lstRaw = listarPorQuery(DtoWfFlujoConfiguracion.class, "wftransacciones.obtenerConfiguracionProceso",
				parametros);

		lst = ((List<DtoWfFlujoConfiguracion>) lstRaw).stream().filter(x -> x.getRelacion() == null)
				.collect(Collectors.toList());

		for (DtoWfFlujoConfiguracion dto : lst) {
			String valorPareja = ((List<DtoWfFlujoConfiguracion>) lstRaw).stream()
					.filter(x -> x.getRelacion() != null
							&& (x.getRelacion().intValue() == dto.getConfiguracion().intValue()
									&& x.getFlujo().intValue() == dto.getFlujo().intValue()))
					.findAny().orElse(new DtoWfFlujoConfiguracion()).getValorDescripcion();
			if (!UString.estaVacio(valorPareja)) {
				dto.setComparacion("BW");
				dto.setValorDescripcion(dto.getValorDescripcion() + ";" + valorPareja);
			}
		}

		for (DtoWfFlujoConfiguracion dto : lst) {
			String valores = dto.getValorDescripcion();
			if (!UString.estaVacio(valores)) {
				String valoresArray[] = valores.split(";");
				for (String valor : valoresArray) {
					dto.getValores().add(valor.trim());
				}
			}
			dto.setValorDescripcion("");
		}

		for (DtoWfFlujoConfiguracion dto : lst) {
			String valorDescripcion = "";
			for (String valor : dto.getValores()) {
				if (dto.getComparacion().equals("IN")) {
					List<DominioParametroPersistencia> parametros2 = new ArrayList<DominioParametroPersistencia>();
					parametros2.add(new DominioParametroPersistencia("p_variable", String.class, dto.getVariable()));
					parametros2.add(new DominioParametroPersistencia("p_valor", String.class, valor));

					List lst2 = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerConfiguracionValorProceso",
							parametros2);

					valorDescripcion = valorDescripcion + ((DtoTabla) lst2.get(0)).getDescripcion() + "; ";
				} else {
					valorDescripcion = valorDescripcion + valor + "; ";
				}
			}
			if (valorDescripcion.length() > 0) {
				valorDescripcion = valorDescripcion.substring(0, valorDescripcion.length() - 2);
			}
			dto.setValorDescripcion(valorDescripcion);

		}

		return lst;
	}

	public List<DtoWfFlujoNivelDocumento> obtenerDocumentoNivelProceso(String proceso, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		List lst = listarPorQuery(DtoWfFlujoNivelDocumento.class, "wftransacciones.obtenerDocumentoNivelProceso",
				parametros);
		for (DtoWfFlujoNivelDocumento documento : (List<DtoWfFlujoNivelDocumento>) lst) {
			documento.setRequerido(UBoolean.validarFlag(documento.getRequeridoA()));
			documento.setFirmadigital(UBoolean.validarFlag(documento.getFirmadigitalA()));
			documento.setFirmaimagen(UBoolean.validarFlag(documento.getFirmaimagenA()));
			documento.setEditable(UBoolean.validarFlag(documento.getEditableA()));
		}
		return lst;
	}

	public List<DtoWfFlujoNivelAprobador> obtenerAprobadorNivelProceso(String proceso, Integer version,
			String codigoSegmento) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		List lst = listarPorQuery(DtoWfFlujoNivelAprobador.class, "wftransacciones.obtenerAprobadorNivelProceso",
				parametros);

		for (DtoWfFlujoNivelAprobador dto : (List<DtoWfFlujoNivelAprobador>) lst) {
			if (UString.estaVacio(codigoSegmento)) {
				dto.setValorDescripcion("");
			}
			String valores = dto.getValorDescripcion();
			if (!UString.estaVacio(valores)) {
				String valoresArray[] = valores.split(";");
				for (String valor : valoresArray) {
					dto.getValores().add(valor.trim());
				}
			}
			dto.setValorDescripcion("");
		}

		for (DtoWfFlujoNivelAprobador dto : (List<DtoWfFlujoNivelAprobador>) lst) {
			String valorDescripcion = "";
			for (String valor : dto.getValores()) {
				List<DominioParametroPersistencia> parametros2 = new ArrayList<DominioParametroPersistencia>();
				parametros2.add(new DominioParametroPersistencia("p_codigotabla", String.class, codigoSegmento));
				parametros2.add(new DominioParametroPersistencia("p_valor", String.class, valor));
				List lst2 = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerNombreSegmento", parametros2);
				valorDescripcion = valorDescripcion + ((DtoTabla) lst2.get(0)).getDescripcion() + "; ";
			}
			if (valorDescripcion.length() > 0) {
				valorDescripcion = valorDescripcion.substring(0, valorDescripcion.length() - 2);
			}
			dto.setValorDescripcion(valorDescripcion);
		}

		return lst;
	}

	public List<DtoWfFlujoNivelAccion> obtenerAccionNivelProceso(String proceso, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		List lst = listarPorQuery(DtoWfFlujoNivelAccion.class, "wftransacciones.obtenerAccionNivelProceso", parametros);
		return lst;
	}

	@Transactional(readOnly = true)
	public List<DtoWfProceso> listarProcesos(DtoTabla filtro) {
		if (UString.estaVacio(filtro.getCodigo())) {
			filtro.setCodigo(null);
		}
		if (UString.estaVacio(filtro.getDescripcion())) {
			filtro.setDescripcion(null);
		}
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getEstado()));
		parametros.add(new DominioParametroPersistencia("p_sigla", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		List lst = listarPorQuery(DtoWfProceso.class, "wftransacciones.listarProcesosWF", parametros);
		return lst;
	}

	public void eliminarProceso(String proceso, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		ejecutarPorQuery("wftransacciones.eliminarProceso", parametros);

	}

	@Transactional(readOnly = true)
	public List<DtoTabla> listarSelector(String id) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_id", String.class, id));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.listarSelector", parametros);
		return lst;
	}

	public DominioPaginacion transaccionListarAprobacion(SeguridadUsuarioActual usuarioActual,
			FiltroSolicitudes filtro) {
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getAplicacion()))
			filtro.setAplicacion(null);

		if (UString.estaVacio(filtro.getProceso()))
			filtro.setProceso(null);

		if (UInteger.esCeroOrNulo(filtro.getIdPersonaSolicitante()))
			filtro.setIdPersonaSolicitante(null);

		if (UInteger.esCeroOrNulo(filtro.getIdPersonaSolicitante2()))
			filtro.setIdPersonaSolicitante2(null);

		if (UString.estaVacio(filtro.getReferencia()))
			filtro.setReferencia(null);

		if (UString.estaVacio(filtro.getCompaniaSocio()))
			filtro.setCompaniaSocio(null);

		if (filtro.getMontodesde() == null)
			filtro.setMontodesde(0.0);

		if (filtro.getMontohasta() == null)
			filtro.setMontohasta(999999999.0);

		if (UString.estaVacio(filtro.getArea()))
			filtro.setArea(null);

		parametros.add(new DominioParametroPersistencia("p_compania", String.class, filtro.getCompaniaSocio()));

		parametros.add(new DominioParametroPersistencia("p_montoDesde", Double.class, filtro.getMontodesde()));
		parametros.add(new DominioParametroPersistencia("p_montoHasta", Double.class, filtro.getMontohasta()));
		parametros.add(new DominioParametroPersistencia("p_area", String.class, filtro.getArea()));

		parametros.add(new DominioParametroPersistencia("p_desde", Date.class, filtro.getFechaDesde()));
		parametros.add(new DominioParametroPersistencia("p_hasta", Date.class, filtro.getFechaHasta()));
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacion()));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, filtro.getProceso()));
		parametros.add(new DominioParametroPersistencia("p_referencia", String.class, filtro.getReferencia()));
		parametros.add(
				new DominioParametroPersistencia("p_solicitante", Integer.class, filtro.getIdPersonaSolicitante()));
		parametros.add(
				new DominioParametroPersistencia("p_2solicitante", Integer.class, filtro.getIdPersonaSolicitante2()));
		parametros.add(new DominioParametroPersistencia("p_aprobador", Integer.class, usuarioActual.getPersonaId()));

		parametros.add(new DominioParametroPersistencia("p_est_proceso", String.class, filtro.getEstadoProceso()));

		cantidadEncontrados = this.contar("wftransacciones.transaccionListarContar", parametros);

		List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"wftransacciones.transaccionListarPaginacion", DtoFlujoSolicitud.class);

		List<Integer> excluir = new ArrayList<Integer>();
		List lista = new ArrayList<DtoFlujoSolicitud>();
		// Logica de segmentos
		for (DtoFlujoSolicitud row : (List<DtoFlujoSolicitud>) lstResultado) {
			if (!UString.estaVacio(row.getSegmento())) {
				int numeroSeg = 0;
				WfTransacciones wfTransacciones = obtenerPorId(row.getTransaccion().intValue());
				String segmentoAprobadorActual = UString.trimSinNulo(row.getSegmento());
				String segmentoNecesarioParaAvanzar = UString.trimSinNulo(wfTransacciones.getSegmentoPendiente());
				String temp = "";
				for (String t : segmentoAprobadorActual.split(";")) {
					if (segmentoNecesarioParaAvanzar.indexOf(t) > -1) {
						temp += t + ";";
						numeroSeg++;
					}
				}
				if (temp.length() > 0) {
					temp = temp.substring(0, temp.length() - 1);
				}
				segmentoAprobadorActual = temp;
				row.setSegmento(segmentoAprobadorActual);

				if (numeroSeg > 0) {
					lista.add(row);
				}
			} else {
				lista.add(row);
			}
		}

		filtro.getPaginacion().setPaginacionListaResultado(lista);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);

		return filtro.getPaginacion();
	}

	public DtoTransaccionValidaciones obtenerAccionesAdicionales(Integer transaccionid, Integer nivel) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccionid));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivel));

		List ls = listarPorQuery(DtoTransaccionValidaciones.class, "wftransacciones.obtenerAccionesAdicionales",
				parametros);
		if (ls.size() == 0) {
			return new DtoTransaccionValidaciones();
		}
		return ((DtoTransaccionValidaciones) ls.get(0));
	}

	public DtoTransaccionValidaciones obtenerApiCabecera(String proceso, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));

		List ls = listarPorQuery(DtoTransaccionValidaciones.class, "wftransacciones.obtenerApiCabecera", parametros);
		if (ls.size() == 0) {
			return new DtoTransaccionValidaciones();
		}
		return ((DtoTransaccionValidaciones) ls.get(0));
	}

	public Dtoregistroproveedor obtenerbeangp_proveedoresid(Integer REGREGISTROID) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_regregistroid", Integer.class, REGREGISTROID));
		List ls = listarPorQuery(Dtoregistroproveedor.class, "wftransacciones.obtenerbeangp_proveedoresid", parametros);
		if (ls.size() == 0) {
			return new Dtoregistroproveedor();
		}
		return ((Dtoregistroproveedor) ls.get(0));
	}

	public List<DtoTabla> obtenerCorreoSolicitante(Integer solicitanteid) {
		logger.debug(solicitanteid);
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_solicitante", Integer.class, solicitanteid));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerCorreoSolicitante", parametros);
		return lst;
	}

	public List<DtoTabla> obtenerDestinatarios(WfTransacciones wfTransaccion, Integer nivelAprobadores, String accion) {
		logger.debug("--->obtenerDestinatarios");
		logger.debug("wfTransaccion.getTransaccionid():"+wfTransaccion.getTransaccionid());
		logger.debug("wfTransaccion.getVersionid():"+wfTransaccion.getVersionid());
		logger.debug("nivelAprobadores:"+nivelAprobadores);
		logger.debug("wfTransaccion.getFlujoid():"+wfTransaccion.getFlujoid());
		logger.debug("wfTransaccion.getProcesoid():"+wfTransaccion.getProcesoid());
		logger.debug("wfTransaccion.getSolicitanteid():"+wfTransaccion.getSolicitanteid());		
		logger.debug("accion:"+accion);
						
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_accion", String.class, accion));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, wfTransaccion.getProcesoid()));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, wfTransaccion.getVersionid()));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, wfTransaccion.getFlujoid()));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivelAprobadores));
		parametros.add(new DominioParametroPersistencia("p_solicitante", Integer.class, wfTransaccion.getSolicitanteid()));
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, wfTransaccion.getTransaccionid()));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerDestinatariosDario", parametros);
		
		//Separar los correos que vengan en el campo adicional
		List<DtoTabla> nuevosCorreos = new ArrayList<DtoTabla>();
		for (DtoTabla d : (List<DtoTabla>)lst) {
			if(!UString.estaVacio(d.getNombre())) {
				
				logger.debug("Correo a desconcatenar :" + d.getNombre());
				
				String correosConcatenados[] = d.getNombre().split(";");
				for (String cc : correosConcatenados) {
					if(!UString.estaVacio(cc.trim())) {
						//Agregar estos correos separados
						DtoTabla nuevo = new DtoTabla();
						nuevo.setCodigo("Persona");
						nuevo.setDescripcion(cc.trim());
						
						logger.debug("Correo desconcatenado agregado :" + cc);
						nuevosCorreos.add(nuevo);
					}					
				}				
			}
		}
		lst.addAll(nuevosCorreos);
		return lst;
	}

	public Integer obtenerVersion(String proceso) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		List ls = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerVersion", parametros);
		if (ls.size() == 0) {
			return 0;
		}
		return ((DtoTabla) ls.get(0)).getIdOracle().intValue();
	}

	public List<DtoFlujo> listarFlujosPorProceso(String proceso, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		List lstResultado = this.listarPorQuery(DtoFlujo.class, "wftransacciones.listarFlujosPorProceso", parametros);
		return (List<DtoFlujo>) lstResultado;
	}

	public List<DtoFlujoConfiguracion> listarCriteriosFlujoPorProceso(String proceso, Integer version, Integer flujo) {
		List<DominioParametroPersistencia> parametros = new ArrayList();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, flujo));
		List lstResultado = this.listarPorQuery(DtoFlujoConfiguracion.class,
				"wftransacciones.listarCriteriosFlujoPorProceso", parametros);
		return (List<DtoFlujoConfiguracion>) lstResultado;
	}

	/*
	 * public Integer generarTransaccionPorFlujoProceso(String proceso, Integer
	 * version, Integer flujo) { List<DominioParametroPersistencia> parametros = new
	 * ArrayList(); parametros.add(new DominioParametroPersistencia("p_proceso",
	 * String.class, proceso)); parametros.add(new
	 * DominioParametroPersistencia("p_proceso", Integer.class, version));
	 * parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class,
	 * flujo)); List lstResultado = this.listarPorQuery(DtoTabla.class,
	 * "wftransacciones.generarTransaccionPorFlujoProceso", parametros);
	 * 
	 * if (lstResultado.size() == 0) { return 1; }
	 * 
	 * return Integer.parseInt(((List<DtoTabla>) lstResultado).get(0).getCodigo());
	 * }
	 */

	public Integer insertarTransaccion(DtoFlujoTransaccionRequest request, Integer version, Integer flujo,
			Integer transaccion, String usuarioLogin, String segmento, String nivelEstadoId, String nivelSiguiente) {

		WfTransacciones bean = new WfTransacciones();
		bean.setProcesoid(request.getProceso());
		bean.setVersionid(version);
		bean.setFlujoid(flujo);
		bean.setTransaccionid(transaccion);
		bean.setCriterios(request.getCriterios());
		bean.setEstado("S");
		bean.setNivelEstadoId(nivelEstadoId);
		bean.setNivelEstadoSiguienteId(nivelSiguiente);
		bean.setCreacionusuario(usuarioLogin);
		bean.setCreacionfecha(new Date());
		bean.setNivelid(0);
		bean.setFecharegistro(new Date());
		bean.setSolicitanteid(request.getSolicitante());
		bean.setReferencia(request.getReferencia());
		bean.setReferenciaPadre(request.getReferenciaPadre());
		bean.setTransaccionOrigenId(request.getOrigenTransaccion());

		// Campos para realizar filtro
		bean.setCompaniaSocioId(request.getCompaniaid());
		bean.setCentroCostosId(request.getCentrocostosid());
		bean.setSucursalId(request.getSucursalid());
		bean.setProyectoId(request.getProyectoid());
		bean.setMonedaId(request.getMonedaid());
		bean.setAreaRevisoraId(request.getAreaid());
		bean.setMonto(request.getMonto());
		bean.setPersonaReferenciaid(request.getPersonaReferencia());

		bean.setSegmentoEnviado(segmento);
		bean.setSegmentoPendiente(segmento);
		bean.setSegmentoAprobado("");

		bean.setPropietarioId(request.getPropietarioId());
		bean.setPropietarioCodigo(request.getPropietarioCodigo());
		bean.setUuid(UUID.randomUUID().toString());
		
		registrar(bean);

		return bean.getTransaccionid();

//		List<DominioParametroPersistencia> parametros = new ArrayList();
//		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, request.getProceso()));
//		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
//		parametros.add(new DominioParametroPersistencia("p_flujo", BigDecimal.class, flujo));
//		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccion));
//		parametros.add(new DominioParametroPersistencia("p_criterios", String.class, request.getCriterios()));
//		parametros.add(new DominioParametroPersistencia("p_usuario", String.class, usuarioLogin));
//		parametros.add(new DominioParametroPersistencia("p_solicitante", BigDecimal.class, request.getSolicitante()));
//		parametros.add(new DominioParametroPersistencia("p_referencia", String.class, request.getReferencia()));
//		parametros.add(new DominioParametroPersistencia("p_origentransaccion", BigDecimal.class, request.getOrigenTransaccion()));
//		this.ejecutarPorQuery("wftransacciones.insertarTransaccion", parametros);

	}

	public DtoAprobacionAcciones obtenerAccionesAprobacion(String transaccionUUID, Integer personaId) {

		WfTransacciones beanWF = obtenerPorUUID(transaccionUUID);
		
		DtoAprobacionAcciones dtoAprobacionAcciones = new DtoAprobacionAcciones();

		dtoAprobacionAcciones.setPuedeAprobar("N");
		dtoAprobacionAcciones.setPuedeDevolver("N");
		dtoAprobacionAcciones.setPuedeRechazar("N");

		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionUUID", String.class, transaccionUUID));
		parametros.add(new DominioParametroPersistencia("p_aprobador", Integer.class, personaId));
		List lst = listarPorQuery(DtoAprobacionAcciones.class, "wftransacciones.obtenerAccionesAprobacion", parametros);

		if (lst.size() > 0) {
			dtoAprobacionAcciones = (DtoAprobacionAcciones) lst.get(0);
		}

		if (dtoAprobacionAcciones.getPuedeAprobar().equals("S")) {
			List nombresAprobar = listarPorQuery(DtoTabla.class, "wftransacciones.accionesaprobar", parametros);
			if (nombresAprobar.size() == 0) {
				dtoAprobacionAcciones.getBotonesaprobar().add(new DtoAprobacionAccion("Aprobar", null));
			}
			for (Object object : nombresAprobar) {
				DtoTabla obj = (DtoTabla) object;
				dtoAprobacionAcciones.getBotonesaprobar()
						.add(new DtoAprobacionAccion(obj.getDescripcion(), obj.getCodigo()));

			}
		}

		if (dtoAprobacionAcciones.getPuedeRechazar().equals("S")) {
			List nombresRechazar = listarPorQuery(DtoTabla.class, "wftransacciones.accionesrechazar", parametros);
			if (nombresRechazar.size() == 0) {
				dtoAprobacionAcciones.getBotonesrechazar().add(new DtoAprobacionAccion("Rechazar", null));
			}
			for (Object object : nombresRechazar) {
				DtoTabla obj = (DtoTabla) object;
				dtoAprobacionAcciones.getBotonesrechazar()
						.add(new DtoAprobacionAccion(obj.getDescripcion(), obj.getCodigo()));

			}
		}

		if (dtoAprobacionAcciones.getPuedeDevolver().equals("S")) {
			List nombresdevolver = listarPorQuery(DtoTabla.class, "wftransacciones.accionesdevolver", parametros);
			if (nombresdevolver.size() == 0) {
				dtoAprobacionAcciones.getBotonesdevolver().add(new DtoAprobacionAccion("Devolver", null));
			}
			for (Object object : nombresdevolver) {
				DtoTabla obj = (DtoTabla) object;
				dtoAprobacionAcciones.getBotonesdevolver()
						.add(new DtoAprobacionAccion(obj.getDescripcion(), obj.getCodigo()));

			}
		}

		dtoAprobacionAcciones.setNivelActual(beanWF.getNivelid());
		return dtoAprobacionAcciones;
	}

	public String obtenerParametroValorExplicacion(String parametroClave, String aplicacionCodigo) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, aplicacionCodigo));
		parametros.add(new DominioParametroPersistencia("p_parametro", String.class, parametroClave));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, "999999"));

		List query = this.listarPorQuery(DtoTabla.class, "wftransacciones.obtenerParametroValorExplicacion",
				parametros);

		if (query.size() != 1) {
			return null;
		}

		return ((List<DtoTabla>) query).get(0).getDescripcion();
	}

	public List<DtoTabla> listarAplicacionPorUsuario(String usuarioLogin) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List query = this.listarPorQuery(DtoTabla.class, "wftransacciones.listarAplicacionPorUsuario", parametros);
		return ((List<DtoTabla>) query);
	}

	public List<DtoTabla> listarMiscelaneosActivos(String aplicacionCodigo, String codigoTabla) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, aplicacionCodigo));
		parametros.add(new DominioParametroPersistencia("p_codigotabla", String.class, codigoTabla));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, "999999"));
		List query = this.listarPorQuery(DtoTabla.class, "wftransacciones.listarMiscelaneosActivos", parametros);
		return ((List<DtoTabla>) query);
	}

	public List<DtoTabla> listarCompaniasActivas() {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List query = this.listarPorQuery(DtoTabla.class, "wftransacciones.listarCompaniasActivas", parametros);
		return ((List<DtoTabla>) query);
	}

	public List<DtoTabla> filtrarEmpleados(String busqueda) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		if (UString.estaVacio(busqueda)) {
			busqueda = "";
		}
		parametros.add(new DominioParametroPersistencia("p_busqueda", String.class, busqueda));
		List query = this.listarPorQuery(DtoTabla.class, "wftransacciones.filtrarEmpleados", parametros);
		return ((List<DtoTabla>) query);

	}

	public DtoProcesoC2Header obtenerC2PorId(Integer idTransaccion, String compania, String numerocontrato) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_idTransaccion", Integer.class, idTransaccion));
		parametros.add(new DominioParametroPersistencia("p_compania", String.class, compania));
		parametros.add(new DominioParametroPersistencia("p_numerocontrato", String.class, numerocontrato));
		List data = listarPorQuery(DtoProcesoC2Header.class, "wftransacciones.wf003approvemstedit_dw_1_retrieve_c2",
				parametros);
		if (data.size() == 0) {
			return null;
		}
		return ((DtoProcesoC2Header) data.get(0));
	}

	public List<DtoWfTransaccionTemp> listarTransaccionesPendientes() {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List lst = listarPorQuery(DtoWfTransaccionTemp.class, "wftransacciones.listarTransaccionesPendientes",
				parametros);
		return lst;
	}

	public void registrarIntentoTransaccionesPendientes(DtoWfTransaccionTemp dtoWfTransaccionTemp) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class,
				dtoWfTransaccionTemp.getTransaccionId()));
		parametros
				.add(new DominioParametroPersistencia("p_intentos", Integer.class, dtoWfTransaccionTemp.getIntento()));
		parametros.add(new DominioParametroPersistencia("p_mensaje", String.class, dtoWfTransaccionTemp.getMensaje()));
		ejecutarPorQuery("wftransacciones.registrarIntentoTransaccionesPendientes", parametros);
	}

	public void transaccionGenerada(Integer transaccionId) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccionId));
		ejecutarPorQuery("wftransacciones.transaccionGenerada", parametros);
	}

	public List<DtoTabla> listarOrigenes() {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.listarOrigenes", parametros);
		return lst;
	}

	public String obtenerPorIdNL(Integer transaccionId) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_id", Integer.class, transaccionId));
		List query = this.listarPorQuery(DtoTabla.class, "wftransacciones.obtenerPorIdNL", parametros);
		if (query.size() != 1) {
			return null;
		}
		return ((List<DtoTabla>) query).get(0).getDescripcion();
	}

	public DtoOrigenDatos obtenerOrigenDatos(Integer origendatosid) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_origendatosid", Integer.class, origendatosid));
		List data = listarPorQuery(DtoOrigenDatos.class, "wftransacciones.obtenerOrigenDatos", parametros);
		if (data.size() == 0) {
			return null;
		}
		return ((DtoOrigenDatos) data.get(0));
	}

	public List<DtoTabla> obtenerSeguimientoAprobacionesXNivel(Integer transaccionid, Integer nivel) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionid", Integer.class, transaccionid));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivel));
		List data = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerSeguimientoAprobacionesXNivel", parametros);
		return data;
	}

	public String verPlantilla(DtoFlujoAdjunto bean) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionUUID", String.class, bean.getTransaccionUUID()));
		List data = listarPorQuery(DtoTabla.class, "wftransacciones.verPlantilla", parametros);
		if (data.size() == 0) {
			return null;
		}
		return ((DtoTabla) data.get(0)).getDescripcion();
	}

	public List<DtoTabla> obtenerIdAprobadores(Integer transaccionid, Integer versionid, int nivel, Integer flujoid,
			String procesoid, Integer solicitanteid) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, procesoid));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, versionid));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, flujoid));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivel));
		parametros.add(new DominioParametroPersistencia("p_solicitante", Integer.class, solicitanteid));
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, transaccionid));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerIdAprobadores", parametros);
		return lst;
	}

	public List<DtoPlanificacionGenerar> generarPlanificacion(String procesoid, Integer versionid, Integer flujoid) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, procesoid));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, versionid));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, flujoid));
		List lst = listarPorQuery(DtoPlanificacionGenerar.class, "wftransacciones.generarPlanificacion", parametros);
		return lst;
	}

	public String obtenerEstadoSiguienteNivel(WfTransacciones wfTransaccion) {
		return obtenerEstadoSiguienteNivel(wfTransaccion.getProcesoid(), wfTransaccion.getVersionid(),
				wfTransaccion.getFlujoid(), wfTransaccion.getNivelid());
	}

	public String obtenerEstadoSiguienteNivel(String procesoId, Integer version, Integer flujo, Integer nivel) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, procesoId));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, flujo));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivel));
		List data = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerEstadoSiguienteNivel", parametros);
		if (data.size() == 0) {
			return null;
		}
		return ((DtoTabla) data.get(0)).getCodigo();
	}

	public String obtenerEstadoActualNivel(WfTransacciones wfTransaccion) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, wfTransaccion.getProcesoid()));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, wfTransaccion.getVersionid()));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, wfTransaccion.getFlujoid()));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, wfTransaccion.getNivelid()));
		List data = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerEstadoActualNivel", parametros);
		if (data.size() == 0) {
			return null;
		}
		return ((DtoTabla) data.get(0)).getCodigo();
	}

	public List<DtoFlujoAdjunto> obtenerAdjuntosSoloLectura(String transaccionUUID, Integer nivel, Integer personaId) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionUUID", String.class, transaccionUUID));
		parametros.add(new DominioParametroPersistencia("p_personaId", Integer.class, personaId));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, nivel));
		List lst = listarPorQuery(DtoFlujoAdjunto.class, "wftransacciones.obtenerAdjuntosSoloLectura", parametros);
		return lst;
	}

	public String obtenerSiguienteEstado(Integer transaccionId) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccion_id", Integer.class, transaccionId));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerSiguienteEstado", parametros);
		if (lst.size() != 1) {
			return null;
		}
		DtoTabla d = (DtoTabla) lst.get(0);
		return d.getCodigo();
	}

	public String obtenerAnteriorEstadoInterno(Integer transaccionId, String proceso, Integer nivelPrevio) {
		WfTransacciones bTransaccion = obtenerPorId(transaccionId);
		String est = null;
		nivelPrevio = UInteger.obtenerValor(nivelPrevio);
		if (nivelPrevio == 0) {
			logger.debug("transaccion en nivel 0 traer el por defecto");
			WfProcesoVersiones pp = wfProcesoVersionesDaoImpl
					.obtenerPorId(new WfProcesoVersionesPk(proceso, bTransaccion.getVersionid()));
			est = pp.getNivelestadoidinicial();
			return est;
		} else if (nivelPrevio == 1) {
			logger.debug("transaccion en nivel 1 traer el por defecto");
			WfProcesoVersiones pp = wfProcesoVersionesDaoImpl
					.obtenerPorId(new WfProcesoVersionesPk(proceso, bTransaccion.getVersionid()));
			est = pp.getNivelestadoidinicial();
			return est;
		}
		/**********************************/
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccion_id", Integer.class, transaccionId));
		parametros.add(new DominioParametroPersistencia("p_nivel_id", Integer.class, nivelPrevio));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerAnteriorEstadoInterno", parametros);
		logger.debug(lst.size());
		if (lst.size() == 1) {
			DtoTabla d = (DtoTabla) lst.get(0);
			logger.debug(d.getCodigo());
			est = d.getCodigo();
			return est;
		}
		return est;
	}

	/*
	 * usar para los que son llamados del rest
	 */
	public String obtenerAnteriorEstadoExterno(Integer transaccionId) {
		String est = null;
		/**********************************/
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccion_id", Integer.class, transaccionId));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerAnteriorEstado", parametros);
		logger.debug(lst.size());
		if (lst.size() == 1) {
			DtoTabla d = (DtoTabla) lst.get(0);
			logger.debug(d.getCodigo());
			est = d.getCodigo();
			return est;
		}
		/*******************************/
		logger.debug("estado anterior no encontrada, buscando transaccion");
		WfTransacciones tra = this.obtenerPorId(transaccionId);
		if (tra == null) {
			logger.debug("transaccion no encontrada");
			return null;
		}
		/*******************************/
		logger.debug("tra.getNivelid():" + tra.getNivelid());
		if (tra.getNivelid() == 0) {
			logger.debug("transaccion en nivel 0 traer el por defecto");
			WfProcesoVersiones pp = wfProcesoVersionesDaoImpl
					.obtenerPorId(new WfProcesoVersionesPk(tra.getProcesoid(), tra.getVersionid()));
			est = pp.getNivelestadoidinicial();
		} else if (tra.getNivelid() == 1) {
			logger.debug("transaccion en nivel 1 traer el por defecto");
			WfProcesoVersiones pp = wfProcesoVersionesDaoImpl
					.obtenerPorId(new WfProcesoVersionesPk(tra.getProcesoid(), tra.getVersionid()));
			est = pp.getNivelestadoidinicial();
		} else {
			logger.debug("transaccion nivel <> 1 traer el actual");
			est = tra.getNivelEstadoId();
		}
		return est;
	}

	public String obtenerRechazadoEstado(Integer transaccionId) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccion_id", Integer.class, transaccionId));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerRechazadoEstado", parametros);
		if (lst.size() != 1) {
			return null;
		}
		DtoTabla d = (DtoTabla) lst.get(0);
		return d.getCodigo();
	}

	public DtoTransaccionVistaAvanzada obtenerVistaAvanzada(String transaccionUUID) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionUUID", String.class, transaccionUUID));
		List data = listarPorQuery(DtoTransaccionVistaAvanzada.class, "wftransacciones.obtenerVistaAvanzada",
				parametros);
		if (data.size() == 0) {
			return null;
		}
		return ((DtoTransaccionVistaAvanzada) data.get(0));
	}

	public List<DtoSeguimientoVistaAvanzada> obtenerSeguimientoVistaAvanzada(String transaccionUUID) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionUUID", String.class, transaccionUUID));
		List data = listarPorQuery(DtoSeguimientoVistaAvanzada.class, "wftransacciones.obtenerSeguimientoVistaAvanzada",
				parametros);
		return data;
	}

	public List<DtoWfFlujoNivel> obtenerNivelAnteriorEstado(Integer transaccionId, String estadoDestino) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionid", Integer.class, transaccionId));
		parametros.add(new DominioParametroPersistencia("p_estado_id", String.class, estadoDestino));
		List data = listarPorQuery(DtoWfFlujoNivel.class, "wftransacciones.obtenerNivelAnteriorEstado", parametros);
		return data;
	}

	public DtoTabla obtenerTitleWF(String transaccionUUID) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionUUID", String.class, transaccionUUID));
		List data = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerTitleWF", parametros);
		if (data.size() == 0) {
			return new DtoTabla();
		}
		return ((DtoTabla) data.get(0));
	}

	public List<DtoWfRol> obtenerRolProceso(String proceso, Integer version) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, proceso));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, version));
		List lst = listarPorQuery(DtoWfRol.class, "wftransacciones.obtenerRolProceso", parametros);
		return lst;
	}

	public DominioPaginacion listarSyTipoDocumentos(FiltroSyDocumentos filtro) {
		Integer cantidadEncontrados = 0;
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();

		if (UString.estaVacio(filtro.getAplicacion()))
			filtro.setAplicacion(null);

		if (UString.estaVacio(filtro.getProceso()))
			filtro.setProceso(null);

		if (UString.estaVacio(filtro.getCodigo()))
			filtro.setCodigo(null);

		if (UString.estaVacio(filtro.getNombre()))
			filtro.setNombre(null);

		parametros.add(new DominioParametroPersistencia("p_aplicacion", String.class, filtro.getAplicacion()));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, filtro.getProceso()));
		parametros.add(new DominioParametroPersistencia("p_codigo", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_nombre", String.class, filtro.getNombre()));

		cantidadEncontrados = this.contar("wftransacciones.contarSyTipoDocumentos", parametros);
		List lstResultado = this.listarConPaginacion(filtro.getPaginacion(), parametros,
				"wftransacciones.listarSyTipoDocumentos", DtoTabla.class);
		filtro.getPaginacion().setPaginacionListaResultado(lstResultado);
		filtro.getPaginacion().setPaginacionRegistrosEncontrados(cantidadEncontrados);
		return filtro.getPaginacion();
	}

	public List<DtoTabla> syprocesomst() {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.syprocesomst", parametros);
		return lst;
	}

	public List<DtoTabla> sytipodocumento() {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.sytipodocumento", parametros);
		return lst;
	}

	public Integer obtenerDestino(WfTransacciones wfTransaccion, String subaccion) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, wfTransaccion.getProcesoid()));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, wfTransaccion.getVersionid()));
		parametros.add(new DominioParametroPersistencia("p_flujo", Integer.class, wfTransaccion.getFlujoid()));
		parametros.add(new DominioParametroPersistencia("p_nivel", Integer.class, wfTransaccion.getNivelid() + 1));
		parametros.add(new DominioParametroPersistencia("p_subaccion", String.class, subaccion));

		List ls = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerDestino", parametros);
		if (ls.size() == 0) {
			return null;
		}
		return ((DtoTabla) ls.get(0)).getId();
	}

	public boolean existeVersion(String procesoId, Integer versionId) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, procesoId));
		parametros.add(new DominioParametroPersistencia("p_version", Integer.class, versionId));
		Integer ls = contar("wftransacciones.existeVersion", parametros);
		if (ls > 0) {
			return true;
		}
		return false;
	}

	public List<DtoTabla> obtenerTransaccionAprobadorVistaAvanzada(String transaccionUUID) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionUUID", String.class, transaccionUUID));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerTransaccionAprobadorVistaAvanzada",
				parametros);
		return lst;
	}

	@Transactional(readOnly = true)
	public List<DtoWfProceso> listarProcesosSinVersion(DtoTabla filtro) {
		if (UString.estaVacio(filtro.getCodigo())) {
			filtro.setCodigo(null);
		}
		if (UString.estaVacio(filtro.getDescripcion())) {
			filtro.setDescripcion(null);
		}
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_sigla", String.class, filtro.getCodigo()));
		parametros.add(new DominioParametroPersistencia("p_descripcion", String.class, filtro.getDescripcion()));
		List lst = listarPorQuery(DtoWfProceso.class, "wftransacciones.listarProcesosSinVersion", parametros);
		return lst;
	}

	public List<DtoWfVistaAdminConfiguracionDocumentos> obtenerConfiguracionDocumentosVistaAvanzada(
			String transaccionUUID) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccionUUID", String.class, transaccionUUID));
		List lst = listarPorQuery(DtoWfVistaAdminConfiguracionDocumentos.class,
				"wftransacciones.obtenerConfiguracionDocumentosVistaAvanzada", parametros);
		return lst;
	}
	
	public int validarPropiedarioIdContar(Integer transaccionId, Integer propietarioId) {
		Criteria cri = this.getCriteria();
			
		cri.add(Restrictions.eq("transaccionid", transaccionId));
		cri.add(Restrictions.eq("propietarioId", propietarioId));
		List<WfTransacciones> lst = cri.list();		
		return lst.size();
	}
	
	public DtoTabla obtenerWfTransaccion(Integer id) {
		DtoTabla bean = new DtoTabla();
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_id", Integer.class, id));
		List lst = listarPorQuery(DtoTabla.class, "wftransacciones.obtenerWfTransaccion", parametros);
		if (lst.size() > 0)
			bean = (DtoTabla)lst.get(0);
		return bean;
	}

	public WfTransacciones obtenerPorUUID(String transaccionUUID) {
		Criteria cri = this.getCriteria();
		cri.add(Restrictions.eq("uuid", transaccionUUID));
		List<WfTransacciones> lst = cri.list();		
		return lst.get(0);
	}

	public List<DtoFlujoAdjunto> listarTipoAdjuntosPorTransaccion(WfTransacciones bean) {
		List<DominioParametroPersistencia> parametros = new ArrayList<DominioParametroPersistencia>();
		parametros.add(new DominioParametroPersistencia("p_transaccion", Integer.class, bean.getTransaccionid()));
		parametros.add(new DominioParametroPersistencia("p_proceso", String.class, bean.getProcesoid()));
		List lst = listarPorQuery(DtoFlujoAdjunto.class, "wftransacciones.listarTipoAdjuntosPorTransaccion", parametros);
		return lst;
	}
	
}
