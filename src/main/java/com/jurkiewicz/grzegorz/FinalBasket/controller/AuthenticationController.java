package com.jurkiewicz.grzegorz.FinalBasket.controller;

import com.jurkiewicz.grzegorz.FinalBasket.model.Product;
import com.jurkiewicz.grzegorz.FinalBasket.model.User;
import com.jurkiewicz.grzegorz.FinalBasket.repository.InvoiceRepository;
import com.jurkiewicz.grzegorz.FinalBasket.repository.ProductRepository;
import com.jurkiewicz.grzegorz.FinalBasket.repository.UserRepository;
import com.jurkiewicz.grzegorz.FinalBasket.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AuthenticationController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    InvoiceRepository invoiceRepository;
    @Autowired
    ProductRepository productRepository;


    @RequestMapping(value={"/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView register(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView home(Principal principal) {
        ModelAndView modelAndView = new ModelAndView();
        Product product = new Product();
        String name = principal.getName();
        User us = userService.getActiveUser(name);
        modelAndView.addObject("auth_user", us);
        modelAndView.addObject("product", product);
        modelAndView.addObject("product", productRepository.findAll());
        modelAndView.setViewName("home");
        return modelAndView;
    }

    @RequestMapping(value = "/home", method = RequestMethod.POST)
    public ModelAndView addProduct(@Valid Product product, BindingResult bindingResult, Principal principal, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);

        }
        String name = principal.getName();
        User us = userService.getActiveUser(name);

        us.getProductList().add(product);
        modelAndView.addObject("user", us);
        modelAndView.addObject("auth_user", us);
        modelAndView.addObject("success", "The product has been successfully bought.");
        modelAndView.addObject("products", productRepository.findAll());
        modelAndView.setViewName("home");
        int sum = 0;
        for (Product p : us.getProductList()) {
            System.out.println(p);
            sum += p.getPrice();
            System.out.println(sum);
        }
        return modelAndView;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid User user, BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("successMessage", "Please correct the errors in form!");
            modelMap.addAttribute("bindingResult", bindingResult);

//        } else if (userService.isUserAlreadyPresent(user)){
//            modelAndView.addObject("successMessage", "User already exists!");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "You have been registered successfully!");
        }
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("register");
        return modelAndView;
    }
    @RequestMapping(value="/admin", method = RequestMethod.GET)
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}

