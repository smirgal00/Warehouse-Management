package Business;

import DataAccess.Data;
import Model.Client;
import Model.Order;
import Model.Product;
import com.mysql.cj.log.Log;

public class Logic {
    Data data;

    public Logic() {
        data = new Data();
    }

    public void insertClient(Client client) {
        data.callProcedure("call insertClient('" + client.getName() + "','" + client.getAddress() + "')");
    }

    public void insertProduct(Product product) {
        data.callProcedure("call insertProduct('" + product.getName() + "','" + product.getStock() + "','" + product.getPrice() + "')");
    }

    public void insertOrder(Order order) {
        data.callProcedure("call insertOrder('" + order.getName() + "','" + order.getQuantity() + "','" + order.getProduct() + "')");
    }

    public void clientReport(int count) {

    }

    public void productReport(int count) {

    }

    public void orderReport(int count) {

    }

    public static void main(String[] args) {
        Logic logic = new Logic();

    }
}
