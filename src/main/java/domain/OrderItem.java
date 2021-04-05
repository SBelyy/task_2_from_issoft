package domain;

public class OrderItem {
    private String name;
    private int quantity;
    private int cost;

    public OrderItem(String name, int quantity, int cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "name=" + name +
                " quantity=" + quantity +
                " cost=" + cost +
                '}';
    }
}
