package service;

import model.Order;
import model.OrderItem;
import model.OrderStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import storage.OrderStorage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

public class OrderService {

    private final OrderStorage orderStorage;
    private final OrderValidator orderValidator;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderStorage orderStorage, OrderValidator orderValidator) {
        this.orderStorage = orderStorage;
        this.orderValidator = orderValidator;
    }

    /**
     * Creates an order and assigns an "almost" unique ID to it
     */
    public String createOrder(Order order) {
        orderValidator.validateOrderForCreate(order);

        final String id = UUID.randomUUID().toString();
        order.setId(id);
        order.setStatus(OrderStatus.CREATED);

        return id;
    }

    /**
     * The method puts Order passed to it into a text file and returns an true if successful
     */
    public boolean placeOrder(Order order) {
        boolean isPlaced = false;

        logger.debug("Start placing order to file");

        orderValidator.validateOrderForPlace(order);

        try (FileWriter writer = new FileWriter("OrdersList.txt", true)) {
            writer.write(order.toString());
            writer.append('\n');
            writer.flush();
            logger.debug("Placing the order to the file was successful");
            isPlaced = true;
        } catch (IOException exception) {
            logger.error("Placing an order to a file was unsuccessful");
        }

        return isPlaced;
    }

    /**
     * This method returns a List<Order> by the userId that was received when reading the file
     */
    public List<Order> loadAllByUserId(String userId) {
        List<String> lines = getLinesFromFile("OrdersList.txt");

        if (lines != null) {
            return getOrderFromList(userId, lines);
        }

        return null;
    }

    private List<Order> getOrderFromList(String userId, List<String> lines) {
        List<Order> orders = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[1].equals(userId)) {
                OrderItem[] items = getItemsFromList(parts);

                Date date = new Date(Long.parseLong(parts[3]));
                Order order = new Order(parts[1], items, date, parts[4]);
                order.setId(parts[0]);
                order.setStatus(OrderStatus.valueOf(parts[2]));

                orders.add(order);
            }
        }

        return orders;
    }

    private OrderItem[] getItemsFromList(String[] parts) {
        OrderItem[] items = new OrderItem[parts.length - 4];

        for (int i = 5; i < parts.length; i++) {
            String name = parts[i].substring(parts[i].indexOf("name=") + 5, parts[i].indexOf(" q"));
            String quantity = parts[i].substring(parts[i].indexOf("quantity=") + 9, parts[i].indexOf(" c"));
            String cost = parts[i].substring(parts[i].indexOf("cost=") + 5, parts[i].indexOf("}"));
            OrderItem item = new OrderItem(name, Integer.parseInt(quantity), Integer.parseInt(cost));
            items[i - 5] = item;
        }

        return items;
    }

    private List<String> getLinesFromFile(String filename) {
        List<String> lines = null;
        logger.debug("Start reading from file");
        try {
            lines = Files.readAllLines(
                    new File(filename).toPath(), Charset.forName("UTF-8"));
            logger.debug("File read successfully");
        } catch (IOException exception) {
            logger.error("File read error");
        }
        return lines;
    }

}
