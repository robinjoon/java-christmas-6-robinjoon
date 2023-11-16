package christmas.domain.eventplanner;

import java.util.function.Supplier;

final class RetryHelper<E> {
    E retry(Supplier<E> supplier) {
        E result = null;
        while (result == null) {
            result = useSupplier(supplier);
        }
        return result;
    }

    private E useSupplier(Supplier<E> supplier) {
        try {
            return supplier.get();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}