package Call;

import Business.Logic;
import Business.Parser;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Driver class for running the program
 */

public class MainFunc {

    Parser parser;
    Integer clientCount;
    Integer orderCount;
    Integer productCount;
    Integer billCount;
    Logic logic;

    public MainFunc(String path) throws FileNotFoundException {
        parser = new Parser(path);
        clientCount = orderCount = productCount = billCount = 1;
        logic = new Logic();
    }

    public ArrayList<String> getInstructions() throws IOException {
        return parser.createInstructions();
    }

    /**
     * Used for transforming commands in text file to SQL queries
     * @throws IOException Invalid I/O handling
     * @throws SQLException Invalid Database handling
     * @throws DocumentException Invalid Document handling
     */

    public void executeInstructions() throws IOException, SQLException, DocumentException {
        ArrayList<String> instr = getInstructions();

        for(String s : instr) {
            if(s.contains("reportProduct")) {
                logic.report(2, productCount++, s);
            }
            if(s.contains("reportClient")) {
                logic.report(3, clientCount++, s);
            }
            if(s.contains("reportOrder")) {
                logic.report(1, orderCount++, s);
            }
            if(s.contains("insertClient")) {
                logic.insertClient(s);
            }
            if(s.contains("insertOrder")) {
                logic.insertOrder(4, billCount++, s);
            }
            if(s.contains("insertProduct")) {
                logic.insertProduct(s);
            }
        }
    }

    public static void main(String[] args) throws IOException, SQLException, DocumentException {
        MainFunc m = new MainFunc(args[0]);

        ArrayList<String> s = m.getInstructions();

        m.executeInstructions();
    }
}
