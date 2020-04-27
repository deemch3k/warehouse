package pl.dyplom.dyplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dyplom.dyplom.domain.Order;
import pl.dyplom.dyplom.domain.ProductQuantity;
import pl.dyplom.dyplom.repo.OrderRepo;

import java.util.List;
import java.util.Set;

@Service
public class OrderService {

    private final OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getList(){
        return orderRepo.findAll();
    }


    public Order update(Order orderFromDB, Order order) {
        orderFromDB.setProducts(order.getProducts());
        return orderRepo.save(orderFromDB);
    }

    public void delete(Order order) {
        orderRepo.delete(order);
    }

    public Order add(Order order) {
       return orderRepo.save(order);
    }

    public Order createOrder(Set<ProductQuantity> productQuantities) {

        Order order = new Order();
        order.setProducts(productQuantities);
        return orderRepo.save(order);
    }
}
