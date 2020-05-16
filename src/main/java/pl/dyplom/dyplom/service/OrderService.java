package pl.dyplom.dyplom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.dyplom.dyplom.domain.order.CancellationReport;
import pl.dyplom.dyplom.domain.order.Order;
import pl.dyplom.dyplom.domain.User;
import pl.dyplom.dyplom.dto.OrderDto;
import pl.dyplom.dyplom.repo.CancellationReportRepo;
import pl.dyplom.dyplom.repo.OrderRepo;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final ProductService productService;
    private final CancellationReportRepo cancellationReportRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo, ProductService productService, CancellationReportRepo cancellationReportRepo) {
        this.orderRepo = orderRepo;
        this.productService = productService;
        this.cancellationReportRepo = cancellationReportRepo;
    }

    public List<Order> getList() {
        return orderRepo.findAll();
    }

    public Order update(Order orderFromDB, Order order) {
        orderFromDB.getProductQuantities().clear();
        orderFromDB.getProductQuantities().addAll(order.getProductQuantities());
        orderFromDB.setStatus(order.getStatus());
        orderFromDB.setUser(order.getUser());
        return orderRepo.save(orderFromDB);
    }

    public void delete(Order order) {
        productService.updateProducts(order.getProductQuantities(),"DELETE");
        orderRepo.delete(order);

    }

    public Order createOrder(OrderDto orderDto, User user) {

        Order order = new Order();
        orderDto.getOrderedProducts().stream().forEach(pr -> pr.getOrderedProduct().setId(null));
        order.setProductQuantities(orderDto.getOrderedProducts());
        order.setClientInfo(orderDto.getClientInfo());
        order.setStatus("PENDING");
        if (user != null && orderDto.getUser() != null) {
            order.setUser(user);
        }
        productService.updateProducts(orderDto.getOrderedProducts(), "CREATE");
        return orderRepo.save(order);
    }

    public void cancelOrder(CancellationReport cr){
        cancellationReportRepo.save(cr);
    }
    public List<CancellationReport> getReportsList(){
       return cancellationReportRepo.findAll();
    }
}
