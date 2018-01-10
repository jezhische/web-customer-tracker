package com.jezh.springdemo.daoImpl;

import com.jezh.springdemo.dao.CustomerDAO;
import com.jezh.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
// todo: need to remove @Transactional here, as transaction must take place in proper context (in CustomerService class)
//    @Transactional/*(value = Transactional.TxType.REQUIRES_NEW)*/
    public List<Customer> getCustomers() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
        return query.getResultList();
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.save(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> query = currentSession.createQuery(
                "select cust from Customer cust where cust.id=:theCustomerId",
                Customer.class);
        query.setParameter("theCustomerId", id);
        return query.getSingleResult();
    }
}
