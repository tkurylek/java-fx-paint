package com.futureprocessing.fxpaint.model.tools;

import com.futureprocessing.fxpaint.model.Point;
import javafx.scene.canvas.GraphicsContext;

public interface Tool  {

    String id();

    default void startDrawing(GraphicsContext gc, Point point) {
    }

    default void continueSketching(GraphicsContext gc, Point point) {
    }

    default void continueDrawing(GraphicsContext gc, Point point) {
    }

    default void stopDrawing(GraphicsContext gc, Point point) {
    }
}
