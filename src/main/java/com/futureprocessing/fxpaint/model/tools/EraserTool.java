package com.futureprocessing.fxpaint.model.tools;

import com.futureprocessing.fxpaint.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class EraserTool implements Tool {

    public static final int SIZE = 10;

    @Override
    public String id() {
        return "eraser";
    }

    @Override
    public void startDrawing(GraphicsContext gc, Point point) {
        gc.beginPath();
        gc.clearRect(point.getX(), point.getY(), SIZE, SIZE);
    }

    @Override
    public void continueDrawing(GraphicsContext gc, Point point) {
        gc.clearRect(point.getX(), point.getY(), SIZE, SIZE);
    }
}
