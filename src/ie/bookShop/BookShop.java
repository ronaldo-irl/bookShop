package ie.bookShop;

import ie.bookShop.bean.*;
import ie.bookShop.enums.Gender;
import ie.bookShop.service.BookServiceImpl;
import ie.bookShop.service.CustomerServiceImpl;

import java.math.BigDecimal;
import java.util.*;

public class BookShop {

    private static final CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
    private static final BookServiceImpl bookServiceImpl = new BookServiceImpl();
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
        bookServiceImpl.createBook();
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

        List<Book> bookBasket = bookServiceImpl.bookBasket();
        System.out.println("Book Number ----- Book Name ------------------------------------------------------------------ Book Price ------Book Type");
        bookBasket.forEach(book -> {

            String bookType= book instanceof PhysicalBook ? "Paper Book" : "EBook";
            System.out.printf("%-17s %-76s %-15s  %-1s %n", book.getBookId(), book.getTitle(), book.getPrice(), bookType);

        } );
        System.out.println();

    }

    //return the book's price based on type of object book
    private static BigDecimal getBookPrice(Book book) {
        if(book instanceof EBook){
             return ((EBook) book).getPrice();
        }else {
           return ((PhysicalBook) book).getPrice();
        }
    }

    //handles the interaction with the user regards the books
    private static void customerAction(String action){
        String answer = "";
        List<Book> bookBasket = new ArrayList<>();
        List<OrderItem> orderItemList = new ArrayList<>();
        while(!answer.equals("exit")){

            answer = getUserInput(customerInteraction(action));


            if (!answer.equals("exit")) {
                try {
                    int quantity = Integer.parseInt(getUserInput("Quantity: "));
                    int bookId = Integer.parseInt(answer);

                    // Fetches the book by its id
                    Book book = bookServiceImpl.getBook(bookId);
                   // OrderItem orderItem = new OrderItem(OrderItem.getItemId(), book.getBookId(), quantity, book.get)
                    if(null == book){
                        throw new RuntimeException();
                    }

                    // add book to the book basket
                    bookBasket.add(book);
                    System.out.println("That's an awesome choice: " + book.getTitle() + " by: " + book.getAuthor());
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a book ID (number) or type exit to finish.");
                }
            }
        }
        String bookOrBooks = bookBasket.size() > 1 ? " books!" : " book!";
        System.out.println("You have chosen "+bookBasket.size() + bookOrBooks);

        handleOrderOrEndInteraction(bookBasket);
    }

    private static void handleOrderOrEndInteraction(List<Book> bookBasket) {
        if(bookBasket.isEmpty()){
            System.out.println("It's a pity you didn't find anything you liked");
        }else{
            Map<String, String> customerMap = new HashMap<>();
            Customer customer =  new Customer();
            for(String customerDetail : customerInformation()){
                String userInput = getUserInput("Type your => "+ customerDetail +" ");
                customerMap.put(customerDetail, userInput);
            }
            //populates the customer object
            customer.setFirstName(customerMap.get("First Name"));
            customer.setLastName(customerMap.get("Last Name"));
            customer.setEmail(customerMap.get("Email"));
            customer.setAddress(customerMap.get("Address"));
            customer.setPhoneNumber(customerMap.get("Phone Number"));
            customer.setGender(Gender.getGender(customerMap.get("Gender")));

            //createOrder();
        }
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
