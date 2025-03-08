package bookShop.bean;

import bookShop.enums.Gender;

public class Customer {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private Gender gender;

    public Customer(){}
    public Customer(Integer customerId, String firstName, String lastName,
                    String email, String address, String phoneNumber, Gender gender) {

        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    // Determines whether two objects are equal or not based on content they hold.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;

        if (getCustomerId() != null ? !getCustomerId().equals(customer.getCustomerId()) : customer.getCustomerId() != null)
            return false;
        if (getFirstName() != null ? !getFirstName().equals(customer.getFirstName()) : customer.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(customer.getLastName()) : customer.getLastName() != null)
            return false;
        if (getEmail() != null ? !getEmail().equals(customer.getEmail()) : customer.getEmail() != null) return false;
        if (getAddress() != null ? !getAddress().equals(customer.getAddress()) : customer.getAddress() != null)
            return false;
        if (getPhoneNumber() != null ? !getPhoneNumber().equals(customer.getPhoneNumber()) : customer.getPhoneNumber() != null)
            return false;
        return getGender() == customer.getGender();
    }

    //Generates a hash code  for an object.

    @Override
    public int hashCode() {
        int result = getCustomerId() != null ? getCustomerId().hashCode() : 0;
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
        result = 31 * result + (getGender() != null ? getGender().hashCode() : 0);
        return result;
    }

    //Provides the result of printing the object in human-readable way, not a memory address
    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", gender=" + gender +
                '}';
    }
}
