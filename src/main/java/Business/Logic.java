package Business;

import DataAccess.Data;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class implementing operations for various queries
 * Adding clients, products, etc. to the database
 */

public class Logic {
    private Data data;

    public Logic() {
        data = new Data();
    }

    /**
     *
     * @param query Executes this query
     */
    public void insertClient(String query) {
        data.callProcedure(query);
    }

    /**
     *
     * @param query Executes this query
     */
    public void insertProduct(String query) {
        data.callProcedure(query);
    }

    /**
     *
     * @param option Used for choosing file name and title.
     *               1 - Order
     *               2 - Product
     *               3 - Client
     *               4 - Bill
     * @param count Number of generated file
     * @param query SQL statement to be executed
     * @throws FileNotFoundException Invalid file handling
     * @throws DocumentException Invalid document handling
     * @throws SQLException Invalid Database handling
     */
    public void insertOrder(int option, int count, String query) throws FileNotFoundException, DocumentException, SQLException {
        data.callProcedure(query);
        PDF pdf = new PDF(option, count);
        String[] temp = query.split("'");
        pdf.generateBill(data.createResult("select * from orderr where Name = '" + temp[1] + "'"));
    }

    public void deleteClient(String query) {
        data.callProcedure(query);
    }

    public void deleteOrder(String query) {
        data.callProcedure(query);
    }

    /**
     *
     * @param option Used for choosing file name and title.
     *               1 - Order
     *               2 - Product
     *               3 - Client
     *               4 - Bill
     * @param count Number of generated file
     * @param query SQL statement to be executed
     * @throws FileNotFoundException Invalid file handling
     * @throws DocumentException Invalid document handling
     * @throws SQLException Invalid Database handling
     */

    public void report(int option, int count, String query) throws FileNotFoundException, DocumentException, SQLException {
        ResultSet rs = data.createResult(query);

        PDF pdf = new PDF(option, count);
        pdf.createReport(rs);
    }

}
