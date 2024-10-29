package ie.bookShop;

import ie.bookShop.bean.*;
import ie.bookShop.enums.Gender;
import ie.bookShop.service.*;
import ie.bookShop.utils.BookUtils;
import ie.bookShop.utils.Constants;

import java.time.LocalDate;
import java.util.*;

public class BookShop {

    private static final CustomerService customerService = new CustomerServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();
    private static final OrderItemService orderItemService = new OrderItemServiceImpl();
    private static final Scanner scanner = new Scanner(System.in);
    public static final String CHOICE = "choice";
    public static final String NAME = "name";

    public static void main(String[] args) {
        //createCustomer();
        createBookList();
        customerWelcome();
        customerAction(CHOICE);
        scanner.close();
    }

    private static void createBookList() {
        bookService.createBook();
    }

    private static  List<String> customerInformation(){
       return List.of("First Name", "Last Name", "Email","Address","Phone Number", "Gender");
    }

    private static String getUserInput(String action ){
        System.out.print(action);
        String userInput = scanner.nextLine();
        return userInput;
    }

    private static void customerWelcome(){

        System.out.println("====================== Welcome to TUS Book Shop =========================");
        System.out.println();
        System.out.println( "It's great having you here!! \nExplore our Book Collection Below and Choose Your Favorites");

        System.out.println();//skip line

        List<Book> bookBasket = bookService.bookBasket();
        System.out.println("Book Number ----- Book Name ------------------------------------------------------------------ Book Price ------Book Type");
        bookBasket.forEach(book -> {

            String bookType= book instanceof PhysicalBook ? "Paper Book" : "EBook";
            System.out.printf("%-17s %-76s %-15s %-1s %n", book.getBookId(), book.getTitle(), book.getPrice(), bookType);

        } );
        System.out.println();

    }

    //handles the interaction with the user regards the books
    private static void customerAction(String action){
        String answer = "";
        List<OrderItem> orderItemList = new ArrayList<>();
        while(!answer.equals("exit")){

            answer = getUserInput(customerInteraction(action));

            if (!answer.equals("exit")) {
                try {
                    int quantity = Integer.parseInt(getUserInput("Quantity: "));
                    int bookId = Integer.parseInt(answer);

                    // Fetches the book by its id
                    Book book = bookService.getBook(bookId);
                    if(null == book){
                        throw new IllegalArgumentException("There is no book number: "+ bookId + " Choose of from the list!");
                    }

                    OrderItem orderItem = new OrderItem(BookUtils.getNextId(), book.getBookId(), quantity, book.getPrice());

                    orderItemService.createOrderItem(orderItem);
                    orderItemList.add(orderItem);
                    System.out.println("That's an awesome choice: " + book.getTitle() + " by: " + book.getAuthor());

                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: Please enter a valid number for Book ID and Quantity.");
                 }  catch (IllegalArgumentException e) {
                    System.out.println("Invalid input: "+ e.getMessage());
                }
            }
        }
        String bookOrBooks = orderItemList.size() > 1 ? " books!" : " book!";
        System.out.println("You have chosen "+orderItemList.size() + bookOrBooks);

        handleOrderOrEndInteraction(orderItemList);
    }

    private static void handleOrderOrEndInteraction(List<OrderItem> orderItemList) {
        if(orderItemList.isEmpty()){
            System.out.println("It's a pity you didn't find anything you liked");
        }else{
         //   orderItemService.
            Customer customer =  createCustomerFromCustomerInput();

            var order = new Order();
            order.setOrderId(BookUtils.getNextId());
            order.setCustomerId(customer.getCustomerId());
            order.setOrderDate(LocalDate.now().toString());
            order.setOrderStatus(Constants.PROCESSING);
            order.setOrderItemList(orderItemList);
            order.setTotalPrice(order.getTotalPrice());

            orderService.save(order);

            createOrderSummary(order);

        }
    }

    private static void createOrderSummary(Order order) {
        System.out.println();
        System.out.println("=================== ORDER SUMMARY ===================");
        Customer customer = customerService.getCustomer(order.getCustomerId());
        System.out.println("Customer: "+customer.getFirstName() + ' '+ customer.getLastName());
        System.out.println("Books Quantity: "+ order.getOrderItemList().size());
        System.out.println("Order Status: "+order.getOrderStatus());
        System.out.println("Order Date: "+order.getOrderDate());
        System.out.println("Total Amount: "+order.getTotalPrice());

        System.out.println();
        String userChoice = "To Finish Your Order TYPE: 1 \nTo Add New Item TYPE: 2 \nTo Exit TYPE: 3 \nChoice: ";

        customerChoice( getUserInput(userChoice));


    }

    private static void customerChoice(String choice) {
        int userChoice = Integer.parseInt(choice);
        switch (userChoice){
            case 1:
                return;
            case 2:
                return;
            case 3:
                return;
        }

    }

    private static Customer createCustomerFromCustomerInput() {
        Map<String, String> customerMap = new HashMap<>();
        Customer customer = null;
        System.out.println();
        for(String customerDetail : customerInformation()){
            String userInput = getUserInput("Type your => "+ customerDetail +" ");
            customer = customerService.getCustomer(userInput);
            if(null != customer){
                System.out.println("Customer Already on the System! ");
                break;
            }
            customerMap.put(customerDetail, userInput);
        }
        if(null == customer){
            customer = new Customer();
            //populates the customer object
            customer.setCustomerId(BookUtils.getNextId());
            customer.setFirstName(customerMap.get("First Name"));
            customer.setLastName(customerMap.get("Last Name"));
            customer.setEmail(customerMap.get("Email"));
            customer.setAddress(customerMap.get("Address"));
            customer.setPhoneNumber(customerMap.get("Phone Number"));
            customer.setGender(Gender.getGender(customerMap.get("Gender")));

            customerService.createCustomer(customer);
        }

        return customer;
    }

    //this method asks the customer what they would like to.
    private static String customerInteraction(String action) {
        switch (action.toLowerCase()) {
            case "name":
                return "What is your name? ";
            case "choice":
                return "Choose one of the books by typing the Book Number or type exit to finish your purchase: ";
            default:
                return "Something is not correct!";
        }
    }
}
