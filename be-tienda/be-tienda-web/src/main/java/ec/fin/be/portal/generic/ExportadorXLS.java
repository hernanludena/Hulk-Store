package ec.fin.be.portal.generic;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

/**
 *
 * @author  
 */
public class ExportadorXLS implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final int FILA_TITULO = 1;
    private static final int COLUMNA_TITULO = 0;
    private static final int FILA_USUARIO = 2;
    private static final int COLUMNA_USUARIO = 0;
    private static final int FILA_FECHA_HORA = 3;
    private static final int COLUMNA_FECHA_HORA = 0;
    private static final short TAMANO_FUENTE_TITULO = 12;
    private static final short TAMANO_FUENTE_CABECERAS = 10;
    private static final short FILA_DATOS = 5;
    private HSSFWorkbook documento;
    private String usuario;
    private String titulo;

    public ExportadorXLS(String titulo, String usuario) {
        this.titulo = titulo;
        this.usuario = usuario;
    }

    public void preProcesadorExportarXLS(Object doc) {
        this.documento = (HSSFWorkbook) doc;
    }

    public void postProcesadorExportarXLS(Object doc) {
        HSSFSheet hoja = getHoja();
        if (hoja != null) {
            hoja.shiftRows(0, hoja.getLastRowNum(), FILA_DATOS);
            for (int i = 0; i < 10; i++) {
                hoja.autoSizeColumn(i, false);
            }
            hoja.getRow(FILA_DATOS).setRowStyle(getEstiloCabeceras());
            setCampoTitulo();
            setCampoUsuario();
            setCampoFechaHora();
        }
    }

    private HSSFSheet getHoja() {
        return documento.getSheetAt(0);
    }

    private void setCampoTitulo() {
        HSSFFont fuenteTitulo = getFuenteTitulo();
        HSSFCellStyle estiloTitulo = documento.createCellStyle();
        estiloTitulo.setFont(fuenteTitulo);
        HSSFRow filaTitulo = getHoja().createRow(FILA_TITULO);
        HSSFCell celdaTitulo = filaTitulo.createCell(COLUMNA_TITULO);
        celdaTitulo.setCellStyle(estiloTitulo);
        celdaTitulo.setCellValue(titulo);
    }

    public HSSFFont getFuenteTitulo() {
        HSSFFont fuenteTitulo = documento.createFont();
        fuenteTitulo.setFontHeightInPoints(TAMANO_FUENTE_TITULO);
        fuenteTitulo.setBold(true);
        return fuenteTitulo;
    }

    private HSSFCellStyle getEstiloCabeceras() {
        HSSFCellStyle estilo = documento.createCellStyle();
        estilo.setAlignment(HorizontalAlignment.CENTER);
        estilo.setFont(getFuenteCabeceras());
        return estilo;
    }

    public HSSFFont getFuenteCabeceras() {
        HSSFFont fuenteTitulo = documento.createFont();
        fuenteTitulo.setFontHeightInPoints(TAMANO_FUENTE_CABECERAS);
        fuenteTitulo.setBold(true);
        return fuenteTitulo;
    }

    private void setCampoUsuario() {
        HSSFRow fila = getHoja().createRow(FILA_USUARIO);
        HSSFCell celda = fila.createCell(COLUMNA_USUARIO);
        celda.setCellValue("Usuario:");
        celda = fila.createCell(COLUMNA_USUARIO + 1);
        celda.setCellValue(usuario);
    }

    private void setCampoFechaHora() {
        HSSFRow fila = getHoja().createRow(FILA_FECHA_HORA);
        HSSFCell celda = fila.createCell(COLUMNA_FECHA_HORA);
        celda.setCellValue("Fecha y Hora:");
        celda = fila.createCell(COLUMNA_FECHA_HORA + 1);
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy  HH:mm:ss");
        celda.setCellValue(formatoFecha.format(new Date()));
    }
}
