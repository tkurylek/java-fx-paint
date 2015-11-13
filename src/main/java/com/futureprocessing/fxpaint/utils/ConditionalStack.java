package com.futureprocessing.fxpaint.utils;

import java.util.Stack;
import java.util.function.Predicate;

public class ConditionalStack<S> {

    private final Stack<S> stack;

    public ConditionalStack(Stack<S> stack) {
        this.stack = stack;
    }

    public static <S> ConditionalStack<S> of(Stack<S> stack) {
        return new ConditionalStack<>(stack);
    }

    public ConditionedStack<S> when(Predicate<S> predicate) {
        return new ConditionedStack<>(predicate);
    }

    public ConditionedStack<S> always() {
        return new ConditionedStack<>(condition -> true);
    }

    public class ConditionedStack<T extends S> {

        private final Predicate<T> predicate;

        public ConditionedStack(Predicate<T> predicate) {
            this.predicate = predicate;
        }

        @SafeVarargs
        public final ConditionalStack<S> push(T element, T... rest) {
            pushIfPasses(element);
            for (T t : rest) {
                pushIfPasses(t);
            }
            return ConditionalStack.this;
        }

        private void pushIfPasses(T first) {
            if(predicate.test(first)) {
                stack.push(first);
            }
        }
    }
}
