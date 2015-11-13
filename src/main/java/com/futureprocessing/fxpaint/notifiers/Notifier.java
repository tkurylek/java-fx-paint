package com.futureprocessing.fxpaint.notifiers;

@FunctionalInterface
public interface Notifier<T> {

    void notify(T t);
}
