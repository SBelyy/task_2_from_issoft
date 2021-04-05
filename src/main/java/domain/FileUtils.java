package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileUtils {

    public List<Order> getOrdersFromLines(List<String> lines) {
        List<Order> orders = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            OrderItem[] items = getItemsFromList(parts);

            Date date = new Date(Long.parseLong(parts[3]));
            Order order = new Order(parts[1], items, date, parts[4]);
            order.setId(parts[0]);
            order.setStatus(OrderStatus.valueOf(parts[2]));

            orders.add(order);
        }

        return orders;
    }

    private OrderItem[] getItemsFromList(String[] parts) {
        OrderItem[] items = new OrderItem[parts.length - 5];

        for (int i = 5; i < parts.length; i++) {
            String name = parts[i].substring(parts[i].indexOf("name=") + 5, parts[i].indexOf(" q"));
            String quantity = parts[i].substring(parts[i].indexOf("quantity=") + 9, parts[i].indexOf(" c"));
            String cost = parts[i].substring(parts[i].indexOf("cost=") + 5, parts[i].indexOf("}"));

            items[i - 5] = new OrderItem(name, Integer.parseInt(quantity), Integer.parseInt(cost));
        }

        return items;
    }
}
