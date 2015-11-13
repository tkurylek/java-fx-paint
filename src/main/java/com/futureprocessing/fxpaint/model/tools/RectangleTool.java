package com.futureprocessing.fxpaint.model.tools;

import com.futureprocessing.fxpaint.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class RectangleTool implements Tool {

    private Point startPoint = Point.from(0, 0);

    @Override
    public String id() {
        return "rectangle";
    }

    @Override
    public void startDrawing(GraphicsContext gc, Point point) {
        startPoint = point;
    }

    @Override
    public void continueSketching(GraphicsContext gc, Point currentPoint) {
        strokeRectangle(gc, currentPoint);
    }

    @Override
    public void stopDrawing(GraphicsContext gc, Point point) {
        strokeRectangle(gc, point);
    }

    private void strokeRectangle(GraphicsContext gc, Point currentPoint) {
        double width = startPoint.minus(currentPoint).getAbsoluteX();
        double height = startPoint.minus(currentPoint).getAbsoluteY();

        if (currentPoint.relativelyTo(startPoint).isInFirstQuadrant()) {
            gc.strokeRect(startPoint.getX(), startPoint.getY(), width, height);
        }
        else if (currentPoint.relativelyTo(startPoint).isInSecondQuadrant()) {
            gc.strokeRect(currentPoint.getX(), startPoint.getY(), width, height);
        }
        else if (currentPoint.relativelyTo(startPoint).isInThirdQuadrant()) {
            gc.strokeRect(currentPoint.getX(), currentPoint.getY(), width, height);
        }
        else if (currentPoint.relativelyTo(startPoint).isInFourthQuadrant()) {
            gc.strokeRect(startPoint.getX(), currentPoint.getY(), width, height);
        }
    }
}
