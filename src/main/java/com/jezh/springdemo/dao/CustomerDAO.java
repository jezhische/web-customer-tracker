package com.jezh.springdemo.dao;

import com.jezh.springdemo.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getCustomers();

    void saveCustomer(Customer customer);


    Customer getCustomerById(int id);

    void deleteCustomerById(int id);

    List<Customer> searchCustomersByPartialMatchInNames(String theSearchName);
}
