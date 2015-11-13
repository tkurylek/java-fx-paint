package com.futureprocessing.fxpaint.utils;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.Stack;

import static org.assertj.core.api.Assertions.assertThat;

public class ConditionalStackTest {

    public static final String CORRECT_SEQUENCE = "correct sequence";
    public static final String FIRST_CORRECT_SEQUENCE = "First correct sequence";
    public static final String BAD_SEQUENCE = "bad sequence";

    @Test
    public void shouldPushFirstCorrectSequenceToStack() {
        // given
        Stack<String> stackOfStrings = new Stack<>();

        // when
        ConditionalStack.of(stackOfStrings).when(string -> string.contains(CORRECT_SEQUENCE)).push
                (FIRST_CORRECT_SEQUENCE);

        // then
        assertThat(stackOfStrings).containsOnlyOnce(FIRST_CORRECT_SEQUENCE);
    }

    @Test
    public void shouldNotPushIncorrectSequenceToStack() {
        // given
        Stack<String> stackOfStrings = new Stack<>();

        // when
        ConditionalStack.of(stackOfStrings).when(s -> s.contains(CORRECT_SEQUENCE)).push(BAD_SEQUENCE);

        // then
        assertThat(stackOfStrings).doesNotContain(FIRST_CORRECT_SEQUENCE);
    }

    @Test
    public void shouldPushNull() {
        // given
        Stack<Object> stackOfObjects = new Stack<>();

        // when
        ConditionalStack.of(stackOfObjects).always().push(null);

        // then
        assertThat(stackOfObjects).hasSize(1);
    }
}