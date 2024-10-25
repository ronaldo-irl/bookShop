package ie.bookShop;

import ie.bookShop.bean.Book;
import ie.bookShop.bean.Customer;
import ie.bookShop.bean.Order;
import ie.bookShop.bean.OrderItem;
import ie.bookShop.service.BookServiceImpl;
import ie.bookShop.service.CustomerServiceImpl;
import ie.bookShop.utils.BookUtils;
import ie.bookShop.utils.Constants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookShop {

    private static final CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
    private static final BookServiceImpl bookServiceImpl = new BookServiceImpl();

    public static void main(String[] args) {
        createCustomer();
        createBookList();

        List<OrderItem>  orderItemList = new ArrayList<>();

        Customer customer = customerServiceImpl.getCustomer("Ronaldo");

        Order order = new Order();
        order.setOrderId(BookUtils.getNextId());
        order.setCustomerId(customer.getCustomerId());
        order.setOrderStatus(Constants.PROCESSING);
        order.setOrderDate(LocalDate.now());
        order.setOrderItemList(orderItemList);
        order.setTotalPrice(order.getTotalPrice());
        System.out.println(order);

        List<Book> bookBasket = bookServiceImpl.bookBasket();
        bookBasket.forEach(book -> System.out.println(
                bookBasket.indexOf(book) + " "+ book.title()
        ));
    }

    private static void createBookList() {
        bookServiceImpl.createBook();
    }

    private static void createCustomer(){
        customerServiceImpl.createCustomer();
    }


}
