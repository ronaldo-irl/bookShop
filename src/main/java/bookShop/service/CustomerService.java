package bookShop.service;

import bookShop.bean.Customer;

import java.util.List;

public interface CustomerService {

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    //check if the email is valid or not.
    //this default method calls the private above
    // this is to demonstrate private, default and static interface methods
    default boolean isValidEmailFormat(String email) {
        return this.isValidEmail(email);
    }

    void createCustomer();

    void createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomer(Integer customerId);

    Customer getCustomer(String name);

    Customer updateCustomer(Customer customer);

    void addCustomerToList(Customer... customers);

}
