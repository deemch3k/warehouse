package pl.dyplom.service;

import pl.dyplom.domain.User;
import pl.dyplom.domain.order.CancellationReport;
import pl.dyplom.domain.order.Order;
import pl.dyplom.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();
    Order update(Order orderFromDB, Order order);
    void delete(Order order);
    Order createOrder(OrderDto orderDto, User user);
    void cancelOrder(CancellationReport cr);
    List<CancellationReport> getReportsList();

}
