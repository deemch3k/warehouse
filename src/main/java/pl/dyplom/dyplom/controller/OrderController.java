package pl.dyplom.dyplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pl.dyplom.dyplom.domain.order.CancellationReport;
import pl.dyplom.dyplom.domain.order.Order;
import pl.dyplom.dyplom.domain.User;
import pl.dyplom.dyplom.dto.OrderDto;
import pl.dyplom.dyplom.service.OrderService;
import pl.dyplom.dyplom.service.impl.OrderServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("{id}")
    public Order getOne(@PathVariable("id") Order order) {
        return order;
    }

    @GetMapping
    public List<Order> getList() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order create(@RequestBody OrderDto orderDto, @AuthenticationPrincipal User user) {
        return orderService.createOrder(orderDto, user);
    }

    @PutMapping("{id}")
    public Order update(@PathVariable("id") Order orderFromDB, @RequestBody Order order) {
        return orderService.update(orderFromDB, order);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Order order) {
        orderService.delete(order);
    }

    @PostMapping("/cancel")
    public void cancelOrder(@RequestBody CancellationReport cr){
       orderService.cancelOrder(cr);
    }

    @GetMapping("/cancel")
    public List<CancellationReport> getReportsList(){
       return orderService.getReportsList();
    }

}
