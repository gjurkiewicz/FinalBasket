package com.jurkiewicz.grzegorz.FinalBasket.controller;

import com.jurkiewicz.grzegorz.FinalBasket.model.Product;
import com.jurkiewicz.grzegorz.FinalBasket.repository.InvoiceRepository;
import com.jurkiewicz.grzegorz.FinalBasket.repository.ProductRepository;
import com.jurkiewicz.grzegorz.FinalBasket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/admin/users")
    public String postsPage(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "users";
    }

    @GetMapping("/admin/invoices")
    public String invoicesPage(Model model) {
        model.addAttribute("invoices", invoiceRepository.findAll());
        return "invoices";
    }
    @PostMapping("/admin/add")
    public String addProduct(@RequestParam(value = "name") String name,
                             @RequestParam(value = "price") int price, Model model) {
        model.addAttribute("success", "The product has been successfully entered.");
        Product product = new Product(name, price);
        productRepository.save(product);
        return "admin";
    }

}
