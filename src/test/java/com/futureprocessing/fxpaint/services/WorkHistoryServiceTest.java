package com.futureprocessing.fxpaint.services;

import javafx.scene.image.Image;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

public class WorkHistoryServiceTest {

    WorkHistoryService workHistoryService = new WorkHistoryService();

    @Test
    public void shouldGetPreviousImage() {
        // given
        Image image1 = mock(Image.class);
        Image image2 = mock(Image.class);
        workHistoryService.push(image1);
        workHistoryService.push(image2);

        // when
        Image previous = workHistoryService.previous();

        // then
        assertThat(previous).isEqualTo(image1);
    }

    @Test
    public void shouldGetNextImage() {
        // given
        Image image1 = mock(Image.class);
        Image image2 = mock(Image.class);
        workHistoryService.push(image1);
        workHistoryService.push(image2);
        workHistoryService.previous();

        // when
        Image next = workHistoryService.next();

        // then
        assertThat(next).isEqualTo(image2);
    }

    @Test
    public void shouldNotGetOutOfTheLeftBoundary() {
        // given
        Image image = mock(Image.class);
        workHistoryService.push(image);
        workHistoryService.previous();

        // when
        Image first = workHistoryService.previous();

        // then
        assertThat(first).isEqualTo(image);
    }

    @Test
    public void shouldNotGetOutOfTheRightBoundary() {
        // given
        Image image = mock(Image.class);
        workHistoryService.push(image);

        // when
        Image last = workHistoryService.next();

        // then
        assertThat(last).isEqualTo(image);
    }
}