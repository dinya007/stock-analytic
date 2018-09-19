package ru.tisov.denis.service;

@FunctionalInterface
public interface ConsumerWithException<T, E extends Exception> {

    void apply(T t) throws E;
}