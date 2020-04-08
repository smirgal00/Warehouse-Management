package Business;

import Model.Client;
import Model.Order;
import Model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class parses the text found in the text file given as an argument
 * It transforms its contents into SQL queries
 */

public class Parser {
    private List<String> list;
    private BufferedReader reader;

    /**
     *
     * @param path Path to the command file
     * @throws FileNotFoundException Thrown if an invalid path has been passed
     */

    public Parser(String path) throws FileNotFoundException {
        list = new ArrayList<String>();
        FileReader file = new FileReader(path);
        reader = new BufferedReader(file);
    }

    private void readInstructions() throws IOException {
        String temp;
        while ((temp = reader.readLine()) != null && temp.length() != 0) {
            list.add(temp);
        }
    }

    /**
     *
     * @return Returns a list with SQL queries
     * @throws IOException thrown if file handling is incorrect
     */

    public ArrayList<String> createInstructions() throws IOException {
        readInstructions();
        ArrayList<String> list = new ArrayList<String>();
        for (String temp : this.list) {
            String[] instr = new String[0];
            if (temp != null) {
                instr = temp.split(" ");
            }
            if (instr[0].equals("Insert")) {
                if (instr[1].equals("client:")) {
                    list.add("call insertClient('" + (instr[2] + " " + instr[3]).replace(",", "") + "','" + instr[4] + "')");
                }
                if (instr[1].equals("product:")) {
                    list.add("call insertProduct('" + instr[2].replace(",", "") + "','" + Integer.valueOf(instr[3].replace(",", "")) + "','" + Double.valueOf(instr[4].replace(",", "")) + "')");
                }
            }
            if (instr[0].equals("Delete")) {
                if (instr[1].equals("client:")) {
                    list.add("call deleteClient('" + (instr[2] + " " + instr[3]).replace(",", "") + "')");
                }
                if (instr[1].equals("Product:")) {
                    list.add("call deleteProduct('" + instr[2] + "')");
                }
            }
            if (instr[0].equals("Order:")) {
                list.add("call insertOrder('" + (instr[1] + " " + instr[2]).replace(",", "") + "','" + instr[3].replace(",", "") + "','" + Integer.valueOf(instr[4]) + "')");
            }
            if (instr[0].equals("Report")) {
                if (instr[1].equals("client")) {
                    list.add("call reportClient()");
                }
                if (instr[1].equals("order")) {
                    list.add("call reportOrder()");
                }
                if (instr[1].equals("product")) {
                    list.add("call reportProduct()");
                }
            }
        }
        return list;
    }

}
