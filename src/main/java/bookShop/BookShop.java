package bookShop;

import bookShop.BookShopExceptions.GenderException;
import bookShop.bean.*;
import bookShop.service.*;
import bookShop.utils.LocalizationUtil;
import bookShop.enums.Gender;
import bookShop.utils.BookFileHandler;
import bookShop.utils.BookUtils;
import bookShop.utils.Constants;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static bookShop.service.BookService.formatPrice;

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
        System.out.println();
        //set chosen language
        LocalizationUtil.setLocale(new Locale("es", "ES"));
        System.out.println(LocalizationUtil.getMessage("app.welcome") +"\n");
        System.out.println(LocalizationUtil.getMessage("app.greeting") +"\n");

        //call method to print a list of books to the customer
        printBookBasket();

    }

    private static void printBookBasket() {
        String choice = getUserInput("Type => Author <= to see the book list ordered by author's name \n" +
                        "Or Type => Title <= to see the book list ordered by title \n" +
                        "Option: ");

        if(!choice.equalsIgnoreCase("author") && !choice.equalsIgnoreCase("title") ){
            System.out.println("Option not valid! Please try again \n");
            printBookBasket();
        }
        //sort book list by user's choice
        bookService.sortBooksByUserChoice(choice);

        List<Book> bookBasket = bookService.bookBasket();
        System.out.println("Book Number ----- Author  ------------------------------------------ Book Name ------------------------------------------------------------------ Book Price -----Book Type");
        //loop through the list of books and print: BookId, Book Title, Book Price and Book Type
        bookBasket.forEach(book -> {
            System.out.printf("%-17s %-50s %-76s %-15s %-1s %n", book.getBookId(), book.getAuthor(), book.getTitle(), formatPrice(book.getPrice()), getBookType(book));
        } );

        System.out.println();
    }

    //handles the interaction with the customer
    private static void customerAction(String action) {
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
                    if(checkIfObjectIsNull(book)){
                        //if the customers types in a book number that doesn't exit,
                        // the system shows a custom message by throwing this unchecked exception
                        System.out.println("There is no book number: "+ bookId + " Choose of from the list!");
                        customerAction(CHOICE);
                    }

                    OrderItem orderItem = new OrderItem(BookUtils.getNextId(), book, quantity, book.getPrice());

                    orderItemService.createOrderItem(orderItem);
                    orderItemList.add(orderItem);
                    System.out.println("That's an awesome choice: " + book.getTitle() + " by: " + book.getAuthor());

                } catch (NumberFormatException e) {//handles the cast on line 83,84
                    //if the user types different value on lines 83,84, it shows the message below
                    System.out.println("Invalid input: Please enter a valid number for Book ID and Quantity.");
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
            //here the compiler infers  the type of (var order) by the (new Order()) expression
            var order = new Order();
            order.setOrderId(BookUtils.getNextId());
            order.setCustomerId(customer.getCustomerId());
            order.setOrderDate(LocalDate.now());
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
        System.out.println("Total Amount: "+ formatPrice(order.getTotalPrice()) +"\n");

        String userChoice = "To Finish Your Order TYPE: 1 \nTo Cancel and Exit TYPE: 2 \nChoice: ";

        try{//get input from the user
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
            System.out.println("Invalid INPUT!!"+"\n");
            createOrderSummary(order);
        }
    }

    private static void sayGoodBye(String message) {
        if(!checkIfObjectIsNull(message)){
            System.out.println(" It's a pity you didn't find anything you liked");
        }else{
            System.out.println("Thank you for Shopping with US!!");
        }
    }

    private static void finishOrderAndGetFeedBack(Order order) {

        order.setOrderStatus(Constants.FINISHED);
        //fetches customer by customerID
        var customer = customerService.getCustomer(order.getCustomerId());

        System.out.println();
        System.out.println("=================== ORDER PROCESSED ===================");

        System.out.println("Order ID: "+order.getOrderId());
        System.out.println("Customer: "+customer.getFirstName() + ' '+ customer.getLastName());
        System.out.println("Shipping Address: "+ customer.getAddress());
        System.out.println("Order Status: "+order.getOrderStatus());
        System.out.println("Order Date: "+order.getOrderDate());
        System.out.println("Total Amount: "+ formatPrice(order.getTotalPrice())+"\n");

        System.out.println("=================== BOOKS ===================");
        order.getOrderItemList().forEach(book -> System.out.println("Book Title: " + book.getBook().getTitle()));
        System.out.println();

        try {
            BookFileHandler.createOrderReceipt("output_order.csv", order);
        } catch (IOException e) {
            System.out.println("Sorry, We could not create the receipt");
        }

        Thread countdownThread = runCountdown(10);
        try {
            //waits until the 10 seconds is done
            countdownThread.join();
        } catch (InterruptedException e) {
            //stops in case some error occurs
            Thread.currentThread().interrupt();
        }

        getUserRate(order);


    }


    private static void getUserRate(Order order) {
        //get customer feedback
        var reviewDescription = getUserInput(customerInteraction("feedback"));
        //create CustomerExperience experience object
        var customerReview = new CustomerExperience(order.getCustomerId(),reviewDescription, LocalDate.now());
        order.setCustomerExperience(customerReview);
        //"save the order"
        orderService.saveOrder(order);
    }

    private static Customer createCustomerFromCustomerInput() {
        Map<String, String> customerMap = new HashMap<>();
        Customer customer = null;
        System.out.println();
        //this array will be used to get the information from the customer and store in a map
        String[] customerInformation = new String[]{"First Name", "Last Name", "Email", "Address", "Phone Number", "Gender"};

        //get customer details
        for(String customerDetail : customerInformation){
            String userInput = getUserInput("Type your => "+ customerDetail +" ");
            //try to find a user, if it's found, show message to customer
            customer = customerService.getCustomer(userInput);
            if(!checkIfObjectIsNull(customer)){
                System.out.println("Customer Already on the System! ");
                break;
            }
            customerMap.put(customerDetail, userInput);
        }

        if(checkIfObjectIsNull(customer)){
            customer = new Customer();
            //populates the customer object
            customer.setCustomerId(BookUtils.getNextId());
            customer.setFirstName(customerMap.get("First Name"));
            customer.setLastName(customerMap.get("Last Name"));
            customer.setEmail(customerMap.get("Email"));
            customer.setAddress(customerMap.get("Address"));
            customer.setPhoneNumber(customerMap.get("Phone Number"));

            try{
                customer.setGender(Gender.getGender(customerMap.get("Gender")));
            }catch (GenderException g){
                //Show Error Message to customer
                System.out.println(g.getMessage()+ " Gender will be automatically set to "+Gender.NOT_PROVIDED);
                //for simplicity, I'm just setting the Gender to NOT_PROVIDED to avoid ask it again
                customer.setGender(Gender.NOT_PROVIDED);
            }

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

    //this method handles the messages displayed to the customer
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

    private static String getBookType(Book book){
        //book type is return by pattern matching as shown on line 265,266
        String bookType;
        switch (book){
            case PhysicalBook physicalBook -> bookType = "Paper Book";
            case EBook eBook -> bookType = "Ebook";
            default -> bookType = "Not Found";
        }
        return bookType;
    }

    private static boolean checkIfObjectIsNull(Object param){
        //lambda function to check if the object "param" is null
        NullObjectChecker<Object> checkObject  = (obj) -> obj == null;
        return checkObject.isNull(param);
    }

    private static Thread runCountdown(int seconds) {
        Thread thread = new Thread(() -> {
            System.out.println("Creating receipt... Please wait while we generate it.");
            for (int i = seconds; i >= 0; i--) {
                System.out.print("\rGetting there in: " + i + " seconds");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //stops the thread
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("\n \nHow did we do?");
        });

        thread.start();
        return thread;
    }
}
