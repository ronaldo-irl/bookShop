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



    public static void main(String[] args) {
        createBookList();
        createDefaultCustomer();
        customerWelcome();
        customerAction(CHOICE);
        scanner.close();
    }

    private static void createDefaultCustomer() {
        //create default customers
        customerService.createCustomer();
    }

    private static void createBookList() {
        //creates a list containing the books for the store
        bookService.createBook();
    }

    private static String getUserInput(String action ){
        //shows a message to the customer
        System.out.print(action);
        //get user's input
        String userInput = scanner.nextLine();
        return userInput;
    }

    private static void customerWelcome(){
        System.out.println("====================== Welcome to TUS Book Shop =========================");
        System.out.println();
        System.out.println( "It's great having you here!! \nExplore our Book Collection Below and Choose Your Favorites");

        System.out.println();
        //call method to print a list of books to the customer
        printBookBasket();

    }

    private static void printBookBasket() {
        List<Book> bookBasket = bookService.bookBasket();
        System.out.println("Book Number ----- Book Name ------------------------------------------------------------------ Book Price ------Book Type");
        //loop through the list of books and print: BookId, Book Title, Book Price and Book Type
        bookBasket.forEach(book -> {
            //checks the type of the book object and assigns the result to bookType variable
            String bookType = book instanceof PhysicalBook ? "Paper Book" : "EBook";
            System.out.printf("%-17s %-76s %-15s %-1s %n", book.getBookId(), book.getTitle(), book.getPrice(), bookType);
        } );

        System.out.println();
    }

    //handles the interaction with the customer
    private static void customerAction(String action){
        String answer = "";
        List<OrderItem> orderItemList = new ArrayList<>();
        while(!answer.equals("q")){

            answer = getUserInput(customerInteraction(action));

            if (!answer.equals("q")) {
                try {
                    int quantity = Integer.parseInt(getUserInput("Quantity: "));
                    int bookId = Integer.parseInt(answer);

                    // Fetches the book by its id
                    Book book = bookService.getBook(bookId);
                    if(null == book){
                        //if the customers types in a book number that doesn't exit,
                        // the system shows a custom message to them
                        throw new IllegalArgumentException("There is no book number: "+ bookId + " Choose of from the list!");
                    }

                    OrderItem orderItem = new OrderItem(BookUtils.getNextId(), book, quantity, book.getPrice());

                    orderItemService.createOrderItem(orderItem);
                    orderItemList.add(orderItem);
                    System.out.println("That's an awesome choice: " + book.getTitle() + " by: " + book.getAuthor());

                } catch (NumberFormatException e) {
                    //if the user types different value on lines 82, 83, it shows the message below
                    System.out.println("Invalid input: Please enter a valid number for Book ID and Quantity.");
                 }  catch (IllegalArgumentException e) {
                    //shows the personalized message defined above
                    System.out.println("Invalid input: "+ e.getMessage());
                }
            }
        }
        //call method to handle order details
        handleOrderOrEndInteraction(orderItemList);
    }

    private static void handleOrderOrEndInteraction(List<OrderItem> orderItemList) {
        if(orderItemList.isEmpty()){
            //if the list is empty it means the customer didn't choose any book
            System.out.println("It's a pity you didn't find anything you liked");
        }else{
            //get customer's details
            Customer customer =  createCustomerFromCustomerInput();

            //crate the order and set the values
            var order = new Order();
            order.setOrderId(BookUtils.getNextId());
            order.setCustomerId(customer.getCustomerId());
            order.setOrderDate(LocalDate.now().toString());
            order.setOrderStatus(Constants.PROCESSING);
            order.setOrderItemList(orderItemList);
            order.setTotalPrice(order.getTotalPrice());

            createOrderSummary(order);
        }
    }

    private static void createOrderSummary(Order order) {
        //Display the summary of the order to the customer.
        int totalQuantity = order.getOrderItemList().stream().mapToInt(q -> q.getQuantity()).sum();
        System.out.println();
        System.out.println("=================== ORDER PREVIEW SUMMARY ===================");
        Customer customer = customerService.getCustomer(order.getCustomerId());
        System.out.println("Order ID: "+order.getOrderId());
        System.out.println("Customer: "+customer.getFirstName() + ' '+ customer.getLastName());
        System.out.println("Books Quantity: "+ totalQuantity);
        System.out.println("Order Status: "+order.getOrderStatus());
        System.out.println("Order Date: "+order.getOrderDate());
        System.out.println("Total Amount: "+order.getTotalPrice());

        System.out.println();
        String userChoice = "To Finish Your Order TYPE: 1 \nTo Cancel and Exit TYPE: 2 \nChoice: ";

        try{
            String input = getUserInput(userChoice);
            if(Integer.valueOf(input) == 1){
                finishOrderAndGetFeedBack(order);
                System.out.println();
                sayGoodBye(null);
            }else{
                sayGoodBye("It's a pity you didn't find anything you liked");
                //remove order
                orderService.deleteOrder(order);
            }
        }catch (NumberFormatException nfe){
            System.out.println("Invalid INPUT!!");
            System.out.println();
            createOrderSummary(order);
        }
    }

    private static void sayGoodBye(String message) {
        if(null != message){
            System.out.println(" It's a pity you didn't find anything you liked");
        }else{
            System.out.println("Thank you for Shopping with US!!");
        }
    }

    private static void finishOrderAndGetFeedBack(Order order) {

        order.setOrderStatus(Constants.FINISHED);

        var customer = customerService.getCustomer(order.getCustomerId());

        System.out.println();
        System.out.println("=================== ORDER PROCESSED ===================");

        System.out.println("Order ID: "+order.getOrderId());
        System.out.println("Customer: "+customer.getFirstName() + ' '+ customer.getLastName());
        System.out.println("Shipping Address: "+ customer.getAddress());
        System.out.println("Order Status: "+order.getOrderStatus());
        System.out.println("Order Date: "+order.getOrderDate());
        System.out.println("Total Amount: "+order.getTotalPrice());
        System.out.println();
        System.out.println("=================== BOOKS ===================");
        order.getOrderItemList().forEach(book -> System.out.println("Book Title: " + book.getBook().getTitle()));
        getUserRate(order);
    }

    private static void getUserRate(Order order) {
        var reviewDescription = getUserInput(customerInteraction("feedback"));

        var customerReview = new CustomerExperience(order.getCustomerId(),reviewDescription, LocalDate.now());
        order.setCustomerExperience(customerReview);
        orderService.saveOrder(order);
    }

    private static Customer createCustomerFromCustomerInput() {
        Map<String, String> customerMap = new HashMap<>();
        Customer customer = null;
        System.out.println();
        String[] customerInformation = new String[]{"First Name", "Last Name", "Email", "Address", "Phone Number", "Gender"};

        //get customer details
        for(String customerDetail : customerInformation){
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

            if(!customerService.isValidEmailFormat(customer.getEmail())){
                System.out.println();
                System.out.println("Invalid Email: " + customer.getEmail());
                String email = getUserInput("Type your email again => ");
                customer.setEmail(email);
            }
            customerService.createCustomer(customer);
        }

        return customer;
    }

    //this method asks the customer what they would like to.
    private static String customerInteraction(String action) {
        switch (action.toLowerCase()) {
            case "feedback":
                return "Please, Take a minute and give us your feedback: ";
            case "choice":
                return "Choose one of the books by typing the Book Number or type q to finish your interaction: ";
            default:
                return "Something is wrong!";
        }
    }
}
