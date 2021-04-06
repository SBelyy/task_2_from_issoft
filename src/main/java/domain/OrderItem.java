package domain;

import java.util.Objects;

public class OrderItem {
    private final String name;
    private final int quantity;
    private final int cost;

    public OrderItem(String name, int quantity, int cost) {
        this.name = name;
        this.quantity = quantity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && cost == orderItem.cost && Objects.equals(name, orderItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity, cost);
    }
}
