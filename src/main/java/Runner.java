import model.Order;
import model.OrderItem;
import model.OrderStatus;
import service.OrderService;
import service.OrderValidator;
import storage.OrderStorage;

import java.util.Date;
import java.util.List;

public class Runner {

    public static void main(String[] args) {
        OrderService orderService = new OrderService(new OrderStorage(), new OrderValidator());
        List<Order> orders = orderService.loadAllByUserId("sd");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

}
