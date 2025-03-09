package bookShop.generic;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class GenericBookSorter {
    public static <T, U extends Comparable<? super U>> void sortByKey(List<T> list, Function<? super T, ? extends U> keyToSortBy) {
        list.sort(Comparator.comparing(keyToSortBy));
    }
}

