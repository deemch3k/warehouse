package pl.dyplom.dyplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.dyplom.dyplom.domain.*;
import pl.dyplom.dyplom.dto.OrderDto;
import pl.dyplom.dyplom.service.OrderService;

import javax.transaction.Transactional;
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
        return orderService.getList();
    }

    @PostMapping
    public Order create(@RequestBody OrderDto orderDto, @AuthenticationPrincipal User user){
        return orderService.createOrder(orderDto, user);
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
