package bookShop.utils;

import bookShop.bean.Order;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

import static bookShop.service.BookService.formatPrice;

public class BookFileHandler {

    public static void createOrderReceipt(String filePath, Order order) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add(String.valueOf(order.getOrderId()));
        lines.add(order.getOrderStatus());
        lines.add(String.valueOf(order.getOrderDate()));
        lines.add(formatPrice(order.getTotalPrice()));

        //add book details
        order.getOrderItemList().forEach(item -> {
            lines.add(item.getBook().getTitle());
            lines.add(item.getBook().getAuthor());
            lines.add(String.valueOf(item.getBook().getPrice()));
        });
        /// write the content to output file
        Files.write(Paths.get(filePath), lines);
    }
}
