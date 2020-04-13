package Business;

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

/**
 * Class used to manipulate PDF files
 */

public class PDF {
    Document doc;
    String title;

    /**
     *
     * @param option option for giving the file a title
     * @param count count for number of file
     * @throws FileNotFoundException thrown if given file doesn't exist
     * @throws DocumentException thrown when there's an error with PDF handling
     */

    public PDF(Integer option, Integer count) throws FileNotFoundException, DocumentException {
        doc = new Document();
        title = "";

        switch (option) {
            case 1:
                title = "Order report ";
                break;
            case 2:
                title = "Product report";
                break;
            case 3:
                title = "Client report";
                break;
            case 4:
                title = "Bill";
                break;
        }

        PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(title + " " + count + ".pdf"));
        doc.open();

    }

    /**
     * Sets the newly create document's title
     * @param title Title
     */
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

    /**
     * Generates a report in a PDF file
     * @param rs Results that need to be written in the PDF file
     * @throws SQLException Exception thrown by the connection to the database
     * @throws DocumentException Exception thrown by the handling of the PDF document
     */

    public void createReport(ResultSet rs) throws SQLException, DocumentException {
        PdfPTable table = new PdfPTable(rs.getMetaData().getColumnCount());
        setDocumentTitle(title);

        addHeader(table, rs);
        addRows(table, rs);
        doc.add(table);
        doc.close();
    }

    /**
     * Generates a bil in a PDF file
     * @param rs Results that need to be written in the PDF file
     * @throws SQLException Exception thrown by the connection to the database
     * @throws DocumentException Exception thrown by the handling of the PDF document
     */

    public void generateBill(ResultSet rs) throws SQLException, DocumentException {
        setDocumentTitle(title);
        Integer count = 0;

        Paragraph paragraph = new Paragraph();
        while (rs.next()) {
            paragraph.clear();
            paragraph.add("Client " + rs.getString("Name") + " ordered " +
                    rs.getString("Product") +
                    " " +
                    rs.getString("Quantity") +
                    " with a price of " +
                    rs.getString("Price") +
                    "."
            );
        }
        if(!paragraph.toString().equals("")) {
            paragraph.setFont(FontFactory.getFont(FontFactory.TIMES, 14, BaseColor.BLACK));
            doc.add(paragraph);
            closeDocument();
        }
    }

    /**
     * Defines table header
     * @param table table file destination
     * @param rs Result
     * @throws SQLException thrown when database interaction is faulty
     */
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

    /**
     * Fills table with data
     * @param table table file destination
     * @param rs Result
     * @throws SQLException thrown when database interaction is faulty
     */
    private void addRows(PdfPTable table, ResultSet rs) throws SQLException {

        ArrayList<String> list = new ArrayList<String>();

        while (rs.next()) {
            list.clear();
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                list.add(rs.getString(i + 1));
            }

            Stream<String> stream = list.stream();

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

    /**
     * Closes document
     */
    public void closeDocument() {
        doc.close();
    }

}
