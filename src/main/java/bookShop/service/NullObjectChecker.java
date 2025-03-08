package bookShop.service;

@FunctionalInterface
public interface NullObjectChecker<T> {
    boolean isNull(T obj);
}

