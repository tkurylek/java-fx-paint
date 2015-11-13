package com.futureprocessing.fxpaint.model.tools;

import com.futureprocessing.fxpaint.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class LineTool implements Tool {

    private Point startPoint;

    @Override
    public String id() {
        return "line";
    }

    @Override
    public void startDrawing(GraphicsContext gc, Point point) {
        this.startPoint = point;
        gc.beginPath();
        gc.moveTo(point.getX(), point.getY());
    }

    @Override
    public void continueSketching(GraphicsContext gc, Point point) {
        gc.moveTo(startPoint.getX(), startPoint.getY());
        gc.lineTo(point.getX(), point.getY());
        gc.stroke();
    }

    @Override
    public void stopDrawing(GraphicsContext gc, Point point) {
        gc.lineTo(point.getX(), point.getY());
        gc.stroke();
    }
}
