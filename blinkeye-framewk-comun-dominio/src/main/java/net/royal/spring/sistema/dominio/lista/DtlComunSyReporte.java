package net.royal.spring.sistema.dominio.lista;

import java.math.BigDecimal;

import net.royal.spring.sistema.dominio.BeanSyReporte;

public class DtlComunSyReporte {
	// pk
		private String aplicacioncodigo;
		private String reportecodigo;
		
		// columna
		private String descripcionlocal;
		private String descripcioningles;	
		private String topico;	
		private String topicoDescripcion;
		private String ventanaobjeto;
		private String comentarios;
		private String reportestandardflag;
		
		private String estado;
		private String ultimousuario;
		private java.util.Date ultimafechamodif;
		
		private String asunto;
		private String padreIdAplicacion;
		private String padreIdReporte;
		private String tiporeporte;
		private String uuid;

		// AUXILIARES
		private String aplicacionDescripcion;	
		private BigDecimal ROWNUM_;
		
		//private String periodoimplementacion;
		//private String ventanaobjeto;
		//private String parametrosflag;
		//private String formatodefaultflag;
		//private String descripciondata;
		//private String comentarios;
		//private String restricciones;	
		//private String reportestandardflag;	
		

		private String estadodescripcion;
		private String tipo;
		
		public String getEstadodescripcion() {
			return estadodescripcion;
		}
		public void setEstadodescripcion(String estadodescripcion) {
			this.estadodescripcion = estadodescripcion;
		}

		
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public String getAplicacioncodigo() {
			return aplicacioncodigo;
		}

		public void setAplicacioncodigo(String aplicacioncodigo) {
			this.aplicacioncodigo = aplicacioncodigo;
		}
		public String getReportecodigo() {
			return reportecodigo;
		}

		public void setReportecodigo(String reportecodigo) {
			this.reportecodigo = reportecodigo;
		}
		public String getDescripcionlocal() {
			return descripcionlocal;
		}

		public void setDescripcionlocal(String descripcionlocal) {
			this.descripcionlocal = descripcionlocal;
		}
		public String getDescripcioningles() {
			return descripcioningles;
		}

		public void setDescripcioningles(String descripcioningles) {
			this.descripcioningles = descripcioningles;
		}
		public String getTopico() {
			return topico;
		}

		public void setTopico(String topico) {
			this.topico = topico;
		}
		
		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}
		public String getUltimousuario() {
			return ultimousuario;
		}

		public void setUltimousuario(String ultimousuario) {
			this.ultimousuario = ultimousuario;
		}
		public java.util.Date getUltimafechamodif() {
			return ultimafechamodif;
		}

		public void setUltimafechamodif(java.util.Date ultimafechamodif) {
			this.ultimafechamodif = ultimafechamodif;
		}
		
		public String getAsunto() {
			return asunto;
		}

		public void setAsunto(String asunto) {
			this.asunto = asunto;
		}
		public String getPadreIdAplicacion() {
			return padreIdAplicacion;
		}

		public void setPadreIdAplicacion(String padreIdAplicacion) {
			this.padreIdAplicacion = padreIdAplicacion;
		}
		public String getPadreIdReporte() {
			return padreIdReporte;
		}

		public void setPadreIdReporte(String padreIdReporte) {
			this.padreIdReporte = padreIdReporte;
		}
		public String getTiporeporte() {
			return tiporeporte;
		}

		public void setTiporeporte(String tiporeporte) {
			this.tiporeporte = tiporeporte;
		}
		
		
		public String getAplicacionDescripcion() {
			return aplicacionDescripcion;
		}

		public void setAplicacionDescripcion(String aplicacionDescripcion) {
			this.aplicacionDescripcion = aplicacionDescripcion;
		}


		public BigDecimal getROWNUM_() {
			return ROWNUM_;
		}

		public void setROWNUM_(BigDecimal rOWNUM_) {
			ROWNUM_ = rOWNUM_;
		}

		
		public BeanSyReporte obtenerBean() {
			BeanSyReporte bean = new BeanSyReporte();
			return obtenerBean(bean);
		}

		public BeanSyReporte obtenerBean(BeanSyReporte bean) {
			if (bean == null)
				bean = new BeanSyReporte();

			bean.getPk().setAplicacioncodigo(aplicacioncodigo);
			bean.getPk().setReportecodigo(reportecodigo);
			bean.setDescripcionlocal(descripcionlocal);
			bean.setDescripcioningles(descripcioningles);
			bean.setTopico(topico);
			bean.setEstado(estado);
			bean.setUltimousuario(ultimousuario);
			bean.setUltimafechamodif(ultimafechamodif);
			
			bean.setAsunto(asunto);		
			bean.setTiporeporte(tiporeporte);
			//bean.setPadreIdAplicacion(padreIdAplicacion);
			//bean.setPadreIdReporte(padreIdReporte);
			
			return bean;
		}	
		
		public void asignarBean(BeanSyReporte bean) {
			if (bean==null)
				return;
			aplicacioncodigo  = bean.getPk().getAplicacioncodigo();
			reportecodigo = bean.getPk().getReportecodigo();
			descripcionlocal = bean.getDescripcionlocal();
			descripcioningles = bean.getDescripcioningles();
			topico = bean.getTopico();
			estado = bean.getEstado();
			ultimousuario = bean.getUltimousuario();
			ultimafechamodif = bean.getUltimafechamodif();
			
			asunto = bean.getAsunto();		
			tiporeporte = bean.getTiporeporte();
			//padreIdAplicacion = bean.getPadreIdAplicacion();
			//padreIdReporte = bean.getPadreIdReporte();
			
		}
		public String getTopicoDescripcion() {
			return topicoDescripcion;
		}
		public void setTopicoDescripcion(String topicoDescripcion) {
			this.topicoDescripcion = topicoDescripcion;
		}
		public String getVentanaobjeto() {
			return ventanaobjeto;
		}
		public void setVentanaobjeto(String ventanaobjeto) {
			this.ventanaobjeto = ventanaobjeto;
		}
		public String getComentarios() {
			return comentarios;
		}
		public void setComentarios(String comentarios) {
			this.comentarios = comentarios;
		}
		public String getReportestandardflag() {
			return reportestandardflag;
		}
		public void setReportestandardflag(String reportestandardflag) {
			this.reportestandardflag = reportestandardflag;
		}
		public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}	
		
}
