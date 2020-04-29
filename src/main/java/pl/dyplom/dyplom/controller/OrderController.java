package pl.dyplom.dyplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.dyplom.dyplom.domain.Order;
import pl.dyplom.dyplom.domain.Product;
import pl.dyplom.dyplom.domain.ProductQuantity;
import pl.dyplom.dyplom.service.OrderService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("{id}")
    public Order getOne(@PathVariable("id") Order order){
        return order;
    }

    @GetMapping
    public List<Order> getList(){

        List<Order> list = orderService.getList();

        return list;
    }

    @PostMapping
    public Order create(@RequestBody Set<ProductQuantity> productQuantities){
        return orderService.createOrder(productQuantities);
    }

    @PutMapping("{id}")
    public Order update(@PathVariable("id") Order orderFromDB, @RequestBody Order order){
        return orderService.update(orderFromDB, order);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Order order){
        orderService.delete(order);
    }

}
