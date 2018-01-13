package com.jezh.springdemo.service;

import com.jezh.springdemo.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomerById(int id);

    void deleteCustomerById(int id);

    List<Customer> searchCustomersByPartialMatchInNames(String theSearchName);
}
