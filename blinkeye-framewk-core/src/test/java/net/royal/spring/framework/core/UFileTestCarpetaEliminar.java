package net.royal.spring.framework.core;

import java.io.IOException;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioArchivo;
import net.royal.spring.framework.modelo.generico.DominioTransaccion;

public class UFileTestCarpetaEliminar {

	public static void main(String[] args) throws IOException {

		String rutaOrigen="D:\\TMP\\celiminar";
		DominioTransaccion res = UFile.carpetaEliminar(rutaOrigen);
		System.out.println(res.getTransaccionEstado());
		System.out.println(res.getTransaccionMensajesCadena());
	}

}
