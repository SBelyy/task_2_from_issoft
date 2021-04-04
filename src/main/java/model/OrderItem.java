package model;

public class OrderItem {
    private String name;
    private int quantity;
    private int cost;

    public OrderItem(String name, int quantity, int cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCost() {
        return cost;
    }
}
