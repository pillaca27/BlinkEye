package net.royal.spring.framework.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import net.royal.spring.framework.modelo.generico.DominioArchivo;
import net.royal.spring.framework.util.UString;

public class UExportar {

	public static String rutaTemporal = null;

	public static enum FORMATO_EXPORTAR {
		XLS("XLS"), PDF("PDF"), CSV("CSV"), TXT("TXT"), JSON("JSON");

		private String formato;

		private FORMATO_EXPORTAR(String formato) {
			this.formato = formato;
		}

		public String getFormato() {
			return formato;
		}

		public void setFormato(String formato) {
			this.formato = formato;
		}
	}

	public static String generarNombreAleatorio(String extension) {
		String ruta = "";
		UUID uuid = UUID.randomUUID();
		ruta = uuid.toString();
		if (!UString.esNuloVacio(rutaTemporal))
			ruta = rutaTemporal + File.separator + ruta + "." + extension;
		else
			ruta = ruta + "." + extension;
		return ruta;
	}

	public static DominioArchivo listToXls(List lstDatos) throws Exception {
		return listToXls(lstDatos, null);
	}

	public static DominioArchivo listToXls(List lstDatos, String[] arrColumnas) throws Exception {
		DominioArchivo dto = new DominioArchivo();
		String rfile = generarNombreAleatorio("xls");
		// Excel excelReport = new Excel(rfile,arrColumnas);
		// excelReport.write(lstDatos);
		// excelReport.close();

		/*
		 * dto.setRutaCompletaArchivo(rfile); dto.setArchivoFile(new File(rfile));
		 * dto.obtenerMimeType(); dto.setNombre(dto.getArchivoFile().getName());
		 */
		return dto;
	}

	public static DominioArchivo listToXml(List lstDatos) throws IOException {
		DominioArchivo dto = new DominioArchivo();
		String rfile = generarNombreAleatorio("xml");
		XmlMapper xmlMapper = new XmlMapper();
		String xml = xmlMapper.writeValueAsString(lstDatos);

		OutputStream os = new FileOutputStream(rfile);
		os.write(xml.getBytes());
		os.close();

		/*
		 * dto.setArchivo(xml.getBytes()); dto.setRutaCompletaArchivo(rfile);
		 * dto.setArchivoFile(new File(rfile)); dto.obtenerMimeType();
		 * dto.setNombre(dto.getArchivoFile().getName());
		 */
		return dto;
	}

	public static String listToXlsFile(List lstDatos) throws Exception {
		return listToXlsFile(lstDatos, null);
	}

	public static String listToXlsFile(List lstDatos, String[] arrColumnas) throws Exception {
		String rfile = generarNombreAleatorio("xls");
		// Excel excelReport = new Excel(rfile,arrColumnas);
		// excelReport.write(lstDatos);
		// excelReport.close();
		return rfile;
	}

	public static byte[] listToXlsArchivo(List lstDatos) throws Exception {
		return listToXlsArchivo(lstDatos, null);
	}

	public static byte[] listToXlsArchivo(List lstDatos, String[] arrColumnas) throws Exception {
		String rfile = listToXlsFile(lstDatos, arrColumnas);
		File file = new File(rfile);
		byte[] bytesArray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray);
		fis.close();
		return bytesArray;
	}

	public static String listToPdfFile(List lstDatos) throws Exception {
		return listToPdfFile(lstDatos, null);
	}

	public static String listToPdfFile(List lstDatos, String[] arrColumnas) throws Exception {
		String rutaCompletaXls = listToXlsFile(lstDatos, arrColumnas);
		// System.out.println(rutaCompletaXls);
		String rutaCompletaPdf = generarNombreAleatorio("pdf");
		File initialFile = new File(rutaCompletaXls);
		InputStream in = new FileInputStream(initialFile);
		// Excel2Pdf excel2Pdf = new Excel2Pdf(Arrays.asList(new ExcelObject(in)), new
		// FileOutputStream(rutaCompletaPdf));
		// excel2Pdf.convert();
		return rutaCompletaPdf;
	}

	public static byte[] listToPdfByte(List lstDatos) throws Exception {
		return listToPdfByte(lstDatos, null);
	}

	public static byte[] listToPdfByte(List lstDatos, String[] arrColumnas) throws Exception {
		String rfile = listToPdfFile(lstDatos, arrColumnas);
		File file = new File(rfile);
		byte[] bytesArray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray);
		fis.close();
		return bytesArray;
	}

	public static DominioArchivo listToPdf(List lstDatos) throws Exception {
		return listToPdf(lstDatos, null);
	}

	public static DominioArchivo listToPdf(List lstDatos, String[] arrColumnas) throws Exception {
		DominioArchivo dto = new DominioArchivo();
		String rutaCompletaXls = listToXlsFile(lstDatos, arrColumnas);
		// System.out.println(rutaCompletaXls);
		String rutaCompletaPdf = generarNombreAleatorio("pdf");
		File initialFile = new File(rutaCompletaXls);
		InputStream in = new FileInputStream(initialFile);
		// Excel2Pdf excel2Pdf = new Excel2Pdf(Arrays.asList(new ExcelObject(in)), new
		// FileOutputStream(rutaCompletaPdf));
		// excel2Pdf.convert();

		/*
		 * dto.setRutaCompletaArchivo(rutaCompletaPdf); dto.setArchivoFile(new
		 * File(rutaCompletaPdf)); dto.obtenerMimeType();
		 * dto.setNombre(dto.getArchivoFile().getName());
		 */
		return dto;
	}

	public static byte[] listToXmlByte(List lstDatos) throws JsonProcessingException {
		XmlMapper xmlMapper = new XmlMapper();
		String xml = xmlMapper.writeValueAsString(lstDatos);
		// System.out.println(xml);
		return xml.getBytes();
	}

	public static byte[] listToCsvByte(List lstDatos, Class clazz, String[] columnas) throws IOException {
		CsvSchema.Builder builder = CsvSchema.builder();
		for (int i = 0; i < columnas.length; i++) {
			builder.addColumn(columnas[i]);
		}
		CsvMapper mapper = new CsvMapper();
		mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
		CsvSchema schema = builder.build();
		ObjectWriter writer = mapper.writerFor(clazz).with(schema);
		StringWriter sw = new StringWriter();
		writer.writeValues(sw).writeAll(lstDatos);
		return sw.toString().getBytes();
	}

	public static DominioArchivo listToCsv(List lstDatos) throws Exception {
		return listToCsv(lstDatos, null);
	}

	public static DominioArchivo listToCsv(List lstDatos, String[] columnas) throws Exception {
		DominioArchivo dto = new DominioArchivo();
		String rutaCompletaCsv = generarNombreAleatorio("csv");

		if (lstDatos.size() == 0) {
			String str = "";
			File fl = new File(rutaCompletaCsv);
			FileWriter fw = new FileWriter(fl);
			fw.write(str);
			fw.close();

			/*
			 * dto.setArchivo(str.getBytes()); dto.setRutaCompletaArchivo(rutaCompletaCsv);
			 * dto.setArchivoFile(fl); dto.obtenerMimeType();
			 * dto.setNombre(dto.getArchivoFile().getName());
			 */
			return dto;
		}

		Class clazz = null;
		if (columnas == null) {
			/*
			 * ClassMapper classMapper = new ClassMapper(); List<String> lstColumnas =
			 * classMapper.getFieldsForClass(lstDatos.get(0).getClass()); clazz =
			 * lstDatos.get(0).getClass(); int index=0; columnas=new
			 * String[lstColumnas.size()]; for (String col : lstColumnas) { columnas[index]
			 * = col; index++; }
			 */
		} else {
			clazz = lstDatos.get(0).getClass();
		}

		CsvSchema.Builder builder = CsvSchema.builder();
		for (int i = 0; i < columnas.length; i++) {
			builder.addColumn(columnas[i]);
		}
		CsvMapper mapper = new CsvMapper();
		mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
		CsvSchema schema = builder.build();
		ObjectWriter writer = mapper.writerFor(clazz).with(schema);

		File fl = new File(rutaCompletaCsv);
		FileWriter sw = new FileWriter(fl);
		writer.writeValues(sw).writeAll(lstDatos);
		sw.close();

		/*
		 * dto.setArchivo(sw.toString().getBytes());
		 * dto.setRutaCompletaArchivo(rutaCompletaCsv); dto.setArchivoFile(fl);
		 * dto.obtenerMimeType(); dto.setNombre(dto.getArchivoFile().getName());
		 */
		return dto;
	}

	public static DominioArchivo listToTxt(List lstDatos) throws Exception {
		return listToTxt(lstDatos, null);
	}

	public static DominioArchivo listToTxt(List lstDatos, String[] columnas) throws Exception {
		DominioArchivo dto = new DominioArchivo();
		String rutaCompletaCsv = generarNombreAleatorio("txt");

		if (lstDatos.size() == 0) {
			String str = "";
			File fl = new File(rutaCompletaCsv);
			FileWriter fw = new FileWriter(fl);
			fw.write(str);
			fw.close();

			/*
			 * dto.setArchivo(str.getBytes()); dto.setRutaCompletaArchivo(rutaCompletaCsv);
			 * dto.setArchivoFile(fl); dto.obtenerMimeType();
			 * dto.setNombre(dto.getArchivoFile().getName());
			 */
			return dto;
		}

		Class clazz = null;
		if (columnas == null) {
			/*
			 * ClassMapper classMapper = new ClassMapper(); List<String> lstColumnas =
			 * classMapper.getFieldsForClass(lstDatos.get(0).getClass()); clazz =
			 * lstDatos.get(0).getClass(); int index=0; columnas=new
			 * String[lstColumnas.size()]; for (String col : lstColumnas) { columnas[index]
			 * = col; index++; }
			 */
		} else {
			clazz = lstDatos.get(0).getClass();
		}

		CsvSchema.Builder builder = CsvSchema.builder();
		for (int i = 0; i < columnas.length; i++) {
			builder.addColumn(columnas[i]);
		}
		CsvMapper mapper = new CsvMapper();
		mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
		CsvSchema schema = builder.build();
		ObjectWriter writer = mapper.writerFor(clazz).with(schema);

		File fl = new File(rutaCompletaCsv);
		FileWriter sw = new FileWriter(fl);
		writer.writeValues(sw).writeAll(lstDatos);
		sw.close();

		/*
		 * dto.setArchivo(sw.toString().getBytes());
		 * dto.setRutaCompletaArchivo(rutaCompletaCsv); dto.setArchivoFile(fl);
		 * dto.obtenerMimeType(); dto.setNombre(dto.getArchivoFile().getName());
		 */
		return dto;
	}

	public static byte[] listToCsvByte(List lstDatos) throws Exception {
		String rfile = listToCsvFile(lstDatos);
		File file = new File(rfile);
		byte[] bytesArray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray);
		fis.close();
		return bytesArray;
	}

	public static String listToCsvFile(List lstDatos) throws Exception {
		String rutaCompletaCsv = generarNombreAleatorio("csv");
		Writer writer2 = new FileWriter(rutaCompletaCsv);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer2).build();
		beanToCsv.write(lstDatos);
		writer2.close();
		return rutaCompletaCsv;
	}

	public static String listToTxtFile(List lstDatos) throws Exception {
		String rutaCompletaCsv = generarNombreAleatorio("txt");
		Writer writer2 = new FileWriter(rutaCompletaCsv);
		StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer2).build();
		beanToCsv.write(lstDatos);
		writer2.close();
		return rutaCompletaCsv;
	}

	public static byte[] listToTxtByte(List lstDatos) throws Exception {
		String rfile = listToTxtFile(lstDatos);
		File file = new File(rfile);
		byte[] bytesArray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray);
		fis.close();
		return bytesArray;
	}

	public static byte[] listToTxt(List lstDatos, Class clazz, String[] columnas) throws IOException {
		CsvSchema.Builder builder = CsvSchema.builder();
		for (int i = 0; i < columnas.length; i++) {
			builder.addColumn(columnas[i]);
		}
		CsvMapper mapper = new CsvMapper();
		mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
		CsvSchema schema = builder.build();
		ObjectWriter writer = mapper.writerFor(clazz).with(schema);
		StringWriter sw = new StringWriter();
		writer.writeValues(sw).writeAll(lstDatos);
		return sw.toString().getBytes();
	}

	public static byte[] listToJson(List lstDatos) throws Exception {
		String rfile = listToTxtFile(lstDatos);
		File file = new File(rfile);
		byte[] bytesArray = new byte[(int) file.length()];
		FileInputStream fis = new FileInputStream(file);
		fis.read(bytesArray);
		fis.close();
		return bytesArray;
	}
	
	public static DominioArchivo imprimirJson(List lstDatos,String[] columnas) throws IOException, JSONException {
		DominioArchivo dto = new DominioArchivo();
		String rutaCompletaJson = generarNombreAleatorio("json");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValueAsString(lstDatos);
		String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(lstDatos);
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(rutaCompletaJson));
		encode(lstDatos.toString().getBytes());
		writer.write(json);
		writer.close();
		
		

		File file = new File(rutaCompletaJson);
		
		dto.setRutaCompletaArchivo(rutaCompletaJson);
		dto.setArchivoAdjuntoBytes(UFile.obtenerArregloByte(rutaCompletaJson));
		dto.setMimeContentType(UString.obtenerMimeType(file.getName()));
		dto.setNombreArchivo(file.getName());

		
		return dto;
	}

	public static DominioArchivo imprimirExcel(String titulo, String usuario, List<String> cabeceras,
			List<String> atributos, List<?> objetos, String rutaFinal, String rutaImagen) throws Exception {

		DominioArchivo dto = new DominioArchivo();

		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet(titulo);

		org.apache.poi.ss.usermodel.Font font = wb.createFont();
		font.setBold(true);

		CellStyle style = wb.createCellStyle();
		style.setFont(font);

		InputStream inputStream = new FileInputStream(rutaImagen);
		byte[] bytes = IOUtils.toByteArray(inputStream);

		CreationHelper helper = wb.getCreationHelper();
		Drawing drawing = sheet.createDrawingPatriarch();
		ClientAnchor anchor = helper.createClientAnchor();
		anchor.setAnchorType(ClientAnchor.MOVE_AND_RESIZE);
		int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);

		anchor.setCol1(0);
		anchor.setRow1(2);

		Picture pict = drawing.createPicture(anchor, pictureIdx);
		pict.resize(2, 3);

		inputStream.close();

		int rowCount = 9;
		int columnCount = 0;

		int letraTitulo = 4;

		Row rowUsuario = sheet.createRow(3);
		Row rowFecha = sheet.createRow(4);
		Row rowCountt = sheet.createRow(5);

		if (cabeceras.size() == letraTitulo) {
			Cell celUsuario = rowUsuario.createCell(cabeceras.size() + 1);
			celUsuario.setCellValue(usuario);

			Cell celTitulo = rowUsuario.createCell(letraTitulo - 1);
			celTitulo.setCellValue(titulo);
			celTitulo.setCellStyle(style);

			Cell celFecha = rowFecha.createCell(cabeceras.size() + 1);
			celFecha.setCellValue(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));

			Cell celCount = rowCountt.createCell(cabeceras.size() + 1);
			celCount.setCellValue("Registros encontrados : " + objetos.size());

		} else {

			Cell celUsuario = rowUsuario
					.createCell((cabeceras.size() - 1) == letraTitulo ? letraTitulo + 1 : cabeceras.size() - 1);
			celUsuario.setCellValue(usuario);

			Cell celTitulo = rowUsuario.createCell(letraTitulo);
			celTitulo.setCellValue(titulo);
			celTitulo.setCellStyle(style);

			Cell celFecha = rowFecha
					.createCell((cabeceras.size() - 1) == letraTitulo ? letraTitulo + 1 : cabeceras.size() - 1);
			celFecha.setCellValue(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));

			Cell celCount = rowCountt
					.createCell((cabeceras.size() - 1) == letraTitulo ? letraTitulo + 1 : cabeceras.size() - 1);
			celCount.setCellValue("Registros : " + objetos.size());
		}

		Row row = sheet.createRow(rowCount++);

		for (String fieldName : cabeceras) {
			Cell cel = row.createCell(columnCount++);
			cel.setCellValue(fieldName);
			cel.setCellStyle(style);
		}

//		for (int i = 0; i < cabeceras.size(); i++) {
//			sheet.autoSizeColumn(i);
//		}

		for (Object objeto : objetos) {
			row = sheet.createRow(rowCount++);
			for (int i = 0; i < atributos.size(); i++) {
				row.createCell(i).setCellValue(BeanUtils.getProperty(objeto, atributos.get(i)));
			}
		}

		try (OutputStream fileOut = new FileOutputStream(rutaFinal)) {
			wb.write(fileOut);
		}
		wb.close();

		File file = new File(rutaFinal);
		
		dto.setRutaCompletaArchivo(rutaFinal);
		dto.setArchivoAdjuntoBytes(UFile.obtenerArregloByte(rutaFinal));
		dto.setMimeContentType(UString.obtenerMimeType(file.getName()));
		dto.setNombreArchivo(file.getName());

		return dto;
	}

	public static void imprimirPdf(String titulo, String usuario, List<String> cabeceras, List<String> atributos,
			List<?> objetos, boolean vertical, String rutaFinal, String rutaImagen) throws Exception {

		HeaderFooterPageEvent event = new HeaderFooterPageEvent(titulo,
				new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()), usuario, vertical, rutaImagen,
				objetos.size());

		Document document = new Document();

		Rectangle r = PageSize.A4;
		Rectangle r2 = null;

		if (vertical) {
			r2 = new Rectangle(r.getWidth(), r.getHeight());
		} else {
			r2 = new Rectangle(r.getHeight(), r.getWidth());
		}

		document.setPageSize(r2);
		document.setMargins(50, 45, 100, 100);
		document.setMarginMirroring(false);

		FileOutputStream foo = new FileOutputStream(new File(rutaFinal));
		PdfWriter writer = PdfWriter.getInstance(document, foo);
		writer.setPageEvent(event);
		document.open();

		float[] colsWidth = new float[cabeceras.size()];

		for (int i = 0; i < colsWidth.length; i++) {
			colsWidth[i] = 1f;
		}

		Font boldFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD);
		Font fontCells = new Font(Font.FontFamily.TIMES_ROMAN, 9);
		PdfPTable table = new PdfPTable(colsWidth);

		table.setHeaderRows(1);

		table.setWidthPercentage(100);
		table.setHorizontalAlignment(Element.ALIGN_LEFT);

		for (String cabecera : cabeceras) {
			Phrase celda = new Phrase(cabecera, boldFont);
			table.addCell(celda);
		}

		for (Object objeto : objetos) {
			for (String atributo : atributos) {
				Phrase celda = new Phrase(BeanUtils.getProperty(objeto, atributo), fontCells);
				table.addCell(celda);
			}
		}

		document.add(table);
		document.close();
	}

	public static DominioArchivo imprimirXML(String titulo, String usuario, List<String> cabeceras,
			List<String> atributos, List<?> objetos, String rutaCompletaXML) throws Exception {
		DominioArchivo dto = new DominioArchivo();
		StringBuilder contenidoXML = new StringBuilder("<?xml version=\"1.0\" encoding=\"iso-8859-1\"?>");

		contenidoXML.append("<reporte>");
		contenidoXML.append("<nombre>");
		contenidoXML.append(titulo);
		contenidoXML.append("</nombre>");
		contenidoXML.append("<usuario>");
		contenidoXML.append(usuario);
		contenidoXML.append("</usuario>");
		contenidoXML.append("<fecha>");
		contenidoXML.append(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		contenidoXML.append("</fecha>");
		contenidoXML.append("<registrosEncontrados>");
		contenidoXML.append(objetos.size());
		contenidoXML.append("</registrosEncontrados>");
		contenidoXML.append("<datos>");

		for (Object row : objetos) {
			contenidoXML.append("<item>");
			for (int i = 0; i < cabeceras.size(); i++) {
				contenidoXML.append("<" + atributos.get(i) + ">");
				String val = BeanUtils.getProperty(row, atributos.get(i));
				if (!UString.estaVacio(val)) {
					val = val.replace("&", "&amp;");
					contenidoXML.append(val);
				}
				contenidoXML.append("</" + atributos.get(i) + ">");
			}
			contenidoXML.append("</item>");
		}

		contenidoXML.append("</datos>");
		contenidoXML.append("</reporte>");

		BufferedWriter writer = Files.newBufferedWriter(Paths.get(rutaCompletaXML), StandardCharsets.ISO_8859_1);
		encode(contenidoXML.toString().getBytes());
		writer.write(contenidoXML.toString());
		writer.close();

		File file = new File(rutaCompletaXML);
		
		dto.setRutaCompletaArchivo(rutaCompletaXML);
		dto.setArchivoAdjuntoBytes(UFile.obtenerArregloByte(rutaCompletaXML));
		dto.setMimeContentType(UString.obtenerMimeType(file.getName()));
		dto.setNombreArchivo(file.getName());

		return dto;
	}

	public static byte[] encode(byte[] arr) {
		Charset utf8charset = Charset.forName("UTF-8");
		Charset iso88591charset = Charset.forName("ISO-8859-15");

		ByteBuffer inputBuffer = ByteBuffer.wrap(arr);

		// decode UTF-8
		CharBuffer data = utf8charset.decode(inputBuffer);

		// encode ISO-8559-1
		ByteBuffer outputBuffer = iso88591charset.encode(data);
		byte[] outputData = outputBuffer.array();

		return outputData;
	}

}
