package Business;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    private List<String> list;
    private FileReader file;
    private BufferedReader reader;

    public Parser(String path) throws FileNotFoundException {
        list = new ArrayList<String>();
        file = new FileReader(path);
        reader = new BufferedReader(file);
    }

    private void readInstructions() throws IOException {
        String temp;
        while((temp = reader.readLine()) != null && temp.length() != 0) {
            list.add(temp);
        }
    }

    public ArrayList<String> createInstructions() {
        ArrayList<String> list = new ArrayList<String>();

        for(String temp : this.list) {
            String[] instr = new String[0];
            if(temp != null) {
                instr = temp.split(" ");
            }
            if(instr[0].equals("Insert")) {

            }

            if(instr[0].equals("Delete")) {

            }

            if(instr[0].equals("Order")) {

            }

            if(instr[0].equals("Report")) {
                if(instr[1].equals("client")) {

                }

                if(instr[1].equals("order")) {

                }

                if(instr[1].equals("product")) {

                }
            }
        }

        return list;
    }
}
