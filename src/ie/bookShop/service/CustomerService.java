package ie.bookShop.service;

import ie.bookShop.bean.Customer;

import java.util.List;

public interface CustomerService {

    void createCustomer();

    void createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomer(Integer customerId);

    Customer getCustomer(String name);

    Customer updateCustomer(Customer customer);

    void delete(Integer customerId);

}
