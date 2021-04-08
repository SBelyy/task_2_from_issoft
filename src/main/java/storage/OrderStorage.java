package storage;

import domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

public class OrderStorage {

    private static final Logger logger = LoggerFactory.getLogger(OrderStorage.class);

    public Order[] findOrdersByUserId(String userId) {
        throw new UnsupportedOperationException("The method has no implementation yet");
    }

    public String persistOrder(Order order) {
        String id = "";
        logger.debug("Start uploading an order to a file");

        try (FileWriter writer = new FileWriter("OrdersList.txt", true)) {
            writer.write(order.toString());
            writer.append('\n');
            writer.flush();
            id = UUID.randomUUID().toString();
            logger.debug("Uploading the order to the file was successful");
        } catch (IOException exception) {
            logger.error("Uploading an order to a file was unsuccessful");
        }

        return id;
    }

    public List<String> loadAllOrders() {
        List<String> lines = null;
        logger.debug("Start reading from file");
        try {
            lines = Files.readAllLines(
                    new File("OrdersList.txt").toPath(), Charset.forName("UTF-8"));
            logger.debug("File read successfully");
        } catch (IOException exception) {
            logger.error("File read error");
        }
        return lines;
    }

    public List<String> loadOrdersById(String userId) {
        throw new UnsupportedOperationException("The method has no implementation yet");
    }

}
