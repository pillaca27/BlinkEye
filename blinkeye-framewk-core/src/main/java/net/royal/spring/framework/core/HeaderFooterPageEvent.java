package net.royal.spring.framework.core;

import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

	private String titulo;
	private String fecha;
	private String usuario;
	private boolean vertical;
	private String rutaImagen;
	private Integer registros;

	public HeaderFooterPageEvent(String titulo, String fecha, String usuario, boolean vertical, String rutaImagen,
			Integer registros) {
		super();
		this.titulo = titulo;
		this.fecha = fecha;
		this.usuario = usuario;
		this.vertical = vertical;
		this.rutaImagen = rutaImagen;
		this.registros = registros;
	}

	public void onStartPage(PdfWriter writer, Document document) {
		PdfPTable header = new PdfPTable(3);
		try {
			header.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			int[] ws = new int[] { 100, 350, 100 };
			int wd = 550;
			if (!vertical) {
				ws = new int[] { 100, 600, 100 };
				wd = 800;
			}
			header.setWidths(ws);
			header.setTotalWidth(wd);
			header.setLockedWidth(true);
			header.setHorizontalAlignment(Element.ALIGN_CENTER);
			Image logo = Image.getInstance(rutaImagen);

			PdfPCell cellLogo = new PdfPCell();
			cellLogo.setVerticalAlignment(Element.ALIGN_CENTER);
			cellLogo.setBorder(Rectangle.NO_BORDER);
			cellLogo.setRowspan(3);
			cellLogo.setImage(logo);
			header.addCell(cellLogo);

			PdfPCell cellTitulo = new PdfPCell(new Phrase(titulo, new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
			cellTitulo.setHorizontalAlignment(Element.ALIGN_CENTER);
			cellTitulo.setVerticalAlignment(Element.ALIGN_CENTER);
			cellTitulo.setBorder(Rectangle.NO_BORDER);
			cellTitulo.setRowspan(3);
			header.addCell(cellTitulo);

			header.addCell(new Phrase(usuario, new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD)));

			header.addCell(new Phrase(fecha, new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD)));

			header.addCell(new Phrase("Registros : " + registros, new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD)));

			int y = !vertical ? 560 : 823;
			header.writeSelectedRows(0, -1, 34, y, writer.getDirectContent());
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		} catch (MalformedURLException e) {
			throw new ExceptionConverter(e);
		} catch (IOException e) {
			throw new ExceptionConverter(e);
		}

	}

	public void onEndPage(PdfWriter writer, Document document) {

		PdfPTable footer = new PdfPTable(1);
		try {
			footer.setTotalWidth(document.getPageSize().getWidth() - 75);
			footer.setLockedWidth(false);
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			footer.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			footer.addCell(new Phrase(String.format("P\u00e1gina " + writer.getPageNumber()),
					new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD)));
			PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			footer.writeSelectedRows(0, -1, 34, 50, canvas);
			canvas.endMarkedContentSequence();
		} catch (Exception de) {
			throw new ExceptionConverter(de);
		}

	}

}