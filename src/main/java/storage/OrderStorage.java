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

public class OrderStorage {

    private static final Logger logger = LoggerFactory.getLogger(OrderStorage.class);

    public Order findOrderByUserId(String userId) {
        throw new UnsupportedOperationException("The method has no implementation yet");
    }

    public boolean persistOrderInFile(Order order, String fileName) {
        boolean isPersist = false;
        logger.debug("Start uploading an order to a file");

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(order.toString());
            writer.append('\n');
            writer.flush();
            isPersist = true;
            logger.debug("Uploading the order to the file was successful");
        } catch (IOException exception) {
            logger.error("Uploading an order to a file was unsuccessful");
        }

        return isPersist;
    }

    public List<String> getAllOrdersFromFile(String filename) {
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
