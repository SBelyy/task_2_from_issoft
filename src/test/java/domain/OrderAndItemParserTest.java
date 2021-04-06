package domain;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderAndItemParserTest {

    @Test
    void getOrdersFromLinesTest() {
        List<String> lines = Collections.singletonList("157a7604-7357-48db-8022-ac94bfe6b710,SBelyy," +
                "CREATED,1617649819917,Kurchatova 8,[OrderItem{name=Beer quantity=2 cost=4}]");

        Order expected = new Order("SBelyy", new OrderItem[]{new OrderItem("Beer", 2, 4)},
                new Date(1617649819917L), "Kurchatova 8");
        expected.setId("157a7604-7357-48db-8022-ac94bfe6b710");
        expected.setStatus(OrderStatus.CREATED);

        List<Order> expectedList = new ArrayList<>();
        expectedList.add(expected);

        OrderAndItemParser parser = new OrderAndItemParser();
        List<Order> ordersActual = parser.getOrdersFromLines(lines, new OrderItem[]{});

        assertEquals(expectedList, ordersActual);
    }

}