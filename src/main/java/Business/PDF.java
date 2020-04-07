package Business;

import DataAccess.Data;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;


public class PDF {
    Document doc;

    public PDF(Integer option, Integer count) throws FileNotFoundException, DocumentException {
        doc = new Document();
        String title = "";

        switch (option) {
            case 1:
                title = "Order report";
                break;
            case 2:
                title = "Product report";
                break;
            case 3:
                title = "Client report";
                break;
        }

        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(title + " " + count + ".pdf"));
        doc.open();

        setDocumentTitle(title);
    }

    private void setDocumentTitle(String title) {
        try {
            Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 25, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph(title, font);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            doc.add(paragraph);
            doc.add(Chunk.NEWLINE);

        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    public void insertClientTable(ResultSet rs) throws SQLException, DocumentException {
        PdfPTable table = new PdfPTable(rs.getMetaData().getColumnCount());

        addHeader(table, rs);
        addRows(table, rs);
        doc.add(table);
    }

    private void addHeader(PdfPTable table, ResultSet rs) throws SQLException {
        String[] title = new String[rs.getMetaData().getColumnCount()];
        for (int i = 0; i < title.length; i++) {
            title[i] = rs.getMetaData().getColumnName(i + 1);
        }

        Stream<String> stream = Arrays.stream(title);

        stream.forEach(
                columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, FontFactory.getFont(FontFactory.TIMES, 12, BaseColor.BLACK)));
                    table.addCell(header);
                }
        );

    }

    private void addRows(PdfPTable table, ResultSet rs) throws SQLException {

        ArrayList<String> list = new ArrayList<String>();

        while(rs.next()) {
            for(int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                list.add(rs.getString(i + 1));
            }

            Stream<String>  stream = list.stream();

            stream.forEach(
                    columnInfo -> {
                        PdfPCell info = new PdfPCell();
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.WHITE);
                        header.setBorderWidth(1);
                        header.setPhrase(new Phrase(columnInfo, FontFactory.getFont(FontFactory.TIMES, 10, BaseColor.BLACK)));
                        table.addCell(header);
                    }
            );
        }

    }

    public void closeDocument() {
        doc.close();
    }

}
