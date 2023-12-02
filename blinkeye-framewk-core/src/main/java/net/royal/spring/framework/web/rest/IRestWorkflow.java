package net.royal.spring.framework.web.rest;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import net.royal.spring.framework.modelo.WorkFlowResultado;
import net.royal.spring.framework.modelo.generico.DominioMensajeUsuario;

public interface IRestWorkflow {

	public ResponseEntity<Map> wfobtenermetadata(WorkFlowResultado request);

	public ResponseEntity<List<DominioMensajeUsuario>> wfvalidar(WorkFlowResultado request);

	public ResponseEntity<WorkFlowResultado> wfseguimiento(WorkFlowResultado request);

	public ResponseEntity<WorkFlowResultado> wfaprobar(WorkFlowResultado request);

	public ResponseEntity<WorkFlowResultado> wfrechazar(WorkFlowResultado request);

	public ResponseEntity<WorkFlowResultado> wfdevolver(WorkFlowResultado request);
}
