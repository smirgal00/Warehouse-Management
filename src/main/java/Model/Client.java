package Model;

public class Client {
    private String address;
    private String name;

    public Client(String name, String address) {
        this.address = address;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
