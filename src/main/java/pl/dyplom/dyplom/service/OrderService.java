package pl.dyplom.dyplom.service;

import pl.dyplom.dyplom.domain.User;
import pl.dyplom.dyplom.domain.order.CancellationReport;
import pl.dyplom.dyplom.domain.order.Order;
import pl.dyplom.dyplom.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();
    Order update(Order orderFromDB, Order order);
    void delete(Order order);
    Order createOrder(OrderDto orderDto, User user);
    void cancelOrder(CancellationReport cr);
    List<CancellationReport> getReportsList();

}
