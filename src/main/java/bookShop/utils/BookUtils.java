package bookShop.utils;

public final class BookUtils {

    private static int nextId = 1;

    public static int getNextId() {
        return nextId++;
    }

}
