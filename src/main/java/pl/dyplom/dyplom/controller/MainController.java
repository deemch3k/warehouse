package pl.dyplom.dyplom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dyplom.dyplom.domain.User;
import pl.dyplom.dyplom.service.OrderService;
import pl.dyplom.dyplom.service.ProductService;
import pl.dyplom.dyplom.service.UserService;

import java.util.HashMap;
import java.util.TreeMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Value("${spring.profiles.active}")
    private String profile;

    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public MainController(ProductService productService, OrderService orderService, UserService userService) {
        this.productService = productService;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user) {
        HashMap<Object, Object> data = new HashMap<>();
        if (user != null) {
            data.put("profile", user);
            data.put("products", productService.getList());
            data.put("orders", orderService.getAllOrders());
            data.put("users", userService.getList());
            data.put("reports", orderService.getReportsList());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }
}
