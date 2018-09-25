package ru.tisov.denis.utils;

import ru.tisov.denis.service.ConsumerWithException;

import java.util.function.Consumer;

public class Utils {

    public static <T, E extends Exception> Consumer<T> ex(ConsumerWithException<T, E> fe) {
        return arg -> {
            try {
                fe.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
