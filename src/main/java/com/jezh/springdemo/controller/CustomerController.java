package com.jezh.springdemo.controller;

import com.jezh.springdemo.entity.Customer;
import com.jezh.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model model) {
        List<Customer> theCustomers = customerService.getCustomers();
        model.addAttribute("customers", theCustomers);

        return "list-customers";
    }

// На эту страницу customer-form передаем атрибут модели "customer", поля которого заполняются в инпутах формы,
// которые связаны с полями этого customer.
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        // create model attribute to bind form data
        Customer theCustomer = new Customer();
        model.addAttribute("customer", theCustomer); //this is pair ("name", value)
        return "customer-form";
    }

// Здесь обращаемся к атрибуту модели customer, поля которого заполнены на странице customer-form, и сохраняем
// его в бд
    @PostMapping("/saveCustomer")
    public String saveC(@Valid @ModelAttribute("customer")Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(">>>> " + bindingResult);
            return "customer-form";
        } else {
            // here we have "saveOrUpdate()" method in CustomerDAOImpl
            customerService.saveCustomer(customer);
            return "redirect:/customer/list";
        }
    }
//    RequestParam - это customerId=5 в запросе http://localhost:8084/hb/customer/showFormForUpdate?customerId=5
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model model) {
        Customer currentCustomer = customerService.getCustomerById(theId);
        // set customer as a model attribute to pre-populate the form:
        model.addAttribute("customer", currentCustomer);
        return "customer-form";
    } // STOPSHIP: 09.01.2018

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId, Model model) {
//        Customer currentCustomer = customerService.getCustomerById(theId);
        customerService.deleteCustomerById(theId);
        return "redirect:/customer/list";
    }

    @PostMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName, Model model) {
        List<Customer> customers = customerService.searchCustomersByPartialMatchInNames(theSearchName);
        model.addAttribute("customers", customers);
        return "list-customers";
    }

//    @GetMapping("/test")
//    public String showTest() {
//        return "customer-form";
//    }
}
