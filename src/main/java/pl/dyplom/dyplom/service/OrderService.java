package pl.dyplom.dyplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dyplom.dyplom.domain.Order;
import pl.dyplom.dyplom.domain.User;
import pl.dyplom.dyplom.dto.OrderDto;
import pl.dyplom.dyplom.repo.OrderRepo;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final ProductService productService;

    @Autowired
    public OrderService(OrderRepo orderRepo, ProductService productService) {
        this.orderRepo = orderRepo;
        this.productService = productService;
    }

    public List<Order> getList() {
        return orderRepo.findAll();
    }


    public Order update(Order orderFromDB, Order order) {
        orderFromDB.setProductQuantities(order.getProductQuantities());
        return orderRepo.save(orderFromDB);
    }

    public void delete(Order order) {
        orderRepo.delete(order);
    }

    public Order createOrder(OrderDto orderDto, User user) {

        Order order = new Order();
        orderDto.getOrderedProducts().stream().forEach(pr -> pr.getOrderedProduct().setId(null));
        order.setProductQuantities(orderDto.getOrderedProducts());
        order.setClientInfo(orderDto.getClientInfo());
        if (user != null) {
            order.setUser(user);
        }
        productService.updateProducts(orderDto.getOrderedProducts());
        return orderRepo.save(order);
    }
}
