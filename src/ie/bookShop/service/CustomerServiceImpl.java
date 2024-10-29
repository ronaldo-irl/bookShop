package ie.bookShop.service;


import ie.bookShop.bean.Customer;
import ie.bookShop.enums.Gender;
import ie.bookShop.utils.BookUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CustomerServiceImpl implements CustomerService {

    private List<Customer> customers = new ArrayList<>();

    @Override
    public void createCustomer() {
        var customer1 = new Customer(BookUtils.getNextId(), "Joe", "Bloggs", "joebloggs@tus.ie", "Athlone Main Road", "0832020909", Gender.MALE);
        var customer2 = new Customer(BookUtils.getNextId(), "Jane", "Doe", "jane@tus.ie", "Dublin city", "089222999", Gender.FEMALE);

        this.customers.add(customer1);
        this.customers.add(customer2);
    }

    @Override
    public void createCustomer(Customer customer) {
        //make sure a duplicated customer is not added to customers list
        List<Customer> allCustomers = new ArrayList<>(this.customers);
        if (allCustomers.stream().noneMatch(c -> c.equals(customer))) {
            allCustomers.add(customer);
            this.customers = allCustomers;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    @Override
    public Customer getCustomer(String name) {
        //search for a customer by name
        if(null != name && !name.isBlank()){
           return this.getAllCustomers()
                                        .stream()
                                        .filter(c -> c.getFirstName().equalsIgnoreCase(name)
                                                || c.getLastName().equalsIgnoreCase(name))
                                        .findFirst().orElse(null);

        }
        return null;
    }

    @Override
    public Customer getCustomer(Integer customerId) {
        //Search customer using  id and if it's not found returns null
        if( null != customerId && !this.getAllCustomers().isEmpty()){
            return this.getAllCustomers().stream().filter(c -> c.getCustomerId().equals(customerId))
                    .findFirst().orElse(null);
        }
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        //Check if the Customer exists and then updates it
        if(null != customer && null != customer.getCustomerId()){
            var updatedCustomer = this.getCustomer(customer.getCustomerId());

            updatedCustomer.setCustomerId(customer.getCustomerId());
            updatedCustomer.setAddress(customer.getAddress());
            updatedCustomer.setEmail(customer.getEmail());
            updatedCustomer.setFirstName(customer.getFirstName());
            updatedCustomer.setLastName(customer.getLastName());
            updatedCustomer.setPhoneNumber(customer.getPhoneNumber());

            return updatedCustomer;
        }
        return null;
    }

    @Override
    public void delete(Integer customerId) {
        //creates a new list removing the given id and assign the new list to the old one
        List<Customer> removeItemFromList = this.customers.stream()
                .filter(c -> !c.getCustomerId().equals(customerId))
                .collect(Collectors.toList());
        this.customers = removeItemFromList;
    }
}