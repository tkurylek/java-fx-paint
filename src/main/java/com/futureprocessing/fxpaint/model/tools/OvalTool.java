package com.futureprocessing.fxpaint.model.tools;

import com.futureprocessing.fxpaint.model.Point;
import javafx.scene.canvas.GraphicsContext;

import static java.lang.Math.abs;

public class OvalTool implements Tool {

    private Point startPoint = Point.from(0, 0);

    @Override
    public String id() {
        return "oval";
    }

    @Override
    public void startDrawing(GraphicsContext gc, Point point) {
        startPoint = point;
    }

    @Override
    public void continueSketching(GraphicsContext gc, Point currentPoint) {
        strokeOval(gc, currentPoint);
    }

    @Override
    public void stopDrawing(GraphicsContext gc, Point point) {
        strokeOval(gc, point);
    }

    private void strokeOval(GraphicsContext gc, Point currentPoint) {
        double width = startPoint.minus(currentPoint).getAbsoluteX();
        double height = startPoint.minus(currentPoint).getAbsoluteY();

        if (currentPoint.relativelyTo(startPoint).isInFirstQuadrant()) {
            gc.strokeOval(startPoint.getX(), startPoint.getY(), width, height);
        }
        else if (currentPoint.relativelyTo(startPoint).isInSecondQuadrant()) {
            gc.strokeOval(currentPoint.getX(), startPoint.getY(), width, height);
        }
        else if (currentPoint.relativelyTo(startPoint).isInThirdQuadrant()) {
            gc.strokeOval(currentPoint.getX(), currentPoint.getY(), width, height);
        }
        else if (currentPoint.relativelyTo(startPoint).isInFourthQuadrant()) {
            gc.strokeOval(startPoint.getX(), currentPoint.getY(), width, height);
        }
    }
}
