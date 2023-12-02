package net.royal.spring.framework.core;

import java.io.IOException;
import java.util.List;

import net.royal.spring.framework.modelo.generico.DominioArchivo;

public class UFileTestListaArchivos {

	public static void main(String[] args) throws IOException {

		String ruta="D:\\fabrica\\servidordevops\\FAMESA_TEMP";
		List<DominioArchivo> lst = UFile.listarArchivos(ruta);
		for (DominioArchivo dominioArchivo : lst) {
			System.out.println(dominioArchivo.getRutaCompletaArchivo());
		}
	}

}
