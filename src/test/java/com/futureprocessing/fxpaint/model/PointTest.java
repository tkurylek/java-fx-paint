package com.futureprocessing.fxpaint.model;

import javafx.scene.input.MouseEvent;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class PointTest {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int FIVE = 5;
    private static final int TEN = 10;
    private static final int ELEVEN = 11;
    public static final int FOUR = 4;

    @Test
    public void shouldCreatePointFromMouseEvent() {
        // given
        MouseEvent mouseEvent = mock(MouseEvent.class);

        // when
        Point result = Point.from(mouseEvent);

        // then
        assertThat(result.getX()).isEqualTo(ZERO);
        assertThat(result.getY()).isEqualTo(ZERO);
    }

    @Test
    public void shouldCreatePoint() {
        // when
        Point result = Point.from(ONE, ONE);

        // then
        assertThat(result.getX()).isEqualTo(ONE);
        assertThat(result.getY()).isEqualTo(ONE);
    }

    @Test
    public void shouldPointBeInFirstQuadrant() {
        // given
        Point pointInFirstQuadrant = Point.from(ONE, ONE);

        // when
        boolean result = pointInFirstQuadrant.isInFirstQuadrant();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldPointBeInSecondQuadrant() {
        // given
        Point pointInSecondQuadrant = Point.from(-ONE, ONE);

        // when
        boolean result = pointInSecondQuadrant.isInSecondQuadrant();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldPointBeInThirdQuadrant() {
        // given
        Point pointInThirdQuadrant = Point.from(-ONE, -ONE);

        // when
        boolean result = pointInThirdQuadrant.isInThirdQuadrant();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldPointBeInFourthQuadrant() {
        // given
        Point pointInFourthQuadrant = Point.from(ONE, -ONE);

        // when
        boolean result = pointInFourthQuadrant.isInFourthQuadrant();

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldAddTwoPoints() {
        // when
        Point result = Point.from(ZERO, ONE).plus(Point.from(ONE, ZERO));

        // then
        assertThat(result).isEqualTo(Point.from(ONE, ONE));
    }

    @Test
    public void shouldSubtractTwoPoints() {
        // when
        Point result = Point.from(ZERO, ONE).minus(Point.from(ONE, ZERO));

        // then
        assertThat(result).isEqualTo(Point.from(-ONE, ONE));
    }

    @Test
    public void shouldGetAbsoluteXAndYValues() {
        // when
        Point point = Point.from(-ONE, -ONE);

        // then
        assertThat(point.getAbsoluteX()).isEqualTo(ONE);
        assertThat(point.getAbsoluteY()).isEqualTo(ONE);
    }

    @Test
    public void shouldGetIntXAndYValues() {
        // when
        Point point = Point.from(1.7d, 1.7d);

        // then
        assertThat(point.getIntX()).isEqualTo(ONE);
        assertThat(point.getIntY()).isEqualTo(ONE);
    }

    @Test
    public void shouldCheckWhetherPointIsInBox() {
        // given
        Point pointInsideBox = Point.from(TEN, TEN);
        Point boxUpperLeft = Point.ZERO;
        double boxWidth = ELEVEN;
        double boxHeight = ELEVEN;

        // when
        boolean result = pointInsideBox.isIn(boxUpperLeft, boxWidth, boxHeight);

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldCheckWhetherPointIsInBox2() {
        // given
        Point pointOutsideBox = Point.from(FIVE, FIVE);
        Point boxUpperLeft = Point.ZERO;
        double boxWidth = TEN;
        double boxHeight = TEN;

        // when
        boolean result = pointOutsideBox.isIn(boxUpperLeft, boxWidth, boxHeight);

        // then
        assertThat(result).isTrue();
    }

    @Test
    public void shouldCreatePointRelativeToAnother() {
        // given
        Point oneOnePoint = Point.from(ONE, ONE);
        Point fiveFivePoint = Point.from(FIVE, FIVE);

        // when
        Point result = fiveFivePoint.relativelyTo(oneOnePoint);

        // then
        assertThat(result).isEqualTo(Point.from(FOUR, FOUR));
    }
}