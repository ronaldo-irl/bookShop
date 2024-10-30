package ie.bookShop.service;

import ie.bookShop.bean.Customer;

import java.util.List;

public interface CustomerService {

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    default boolean isValidEmailFormat(String email) {
        if (!isValidEmail(email)) {
            return false;
        }
        return true;
    }

    void createCustomer();

    void createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomer(Integer customerId);

    Customer getCustomer(String name);

    Customer updateCustomer(Customer customer);

    void delete(Integer customerId);

    void addCustomerToList(Customer... customers);

}
