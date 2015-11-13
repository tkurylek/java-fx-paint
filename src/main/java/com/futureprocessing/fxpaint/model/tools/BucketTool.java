package com.futureprocessing.fxpaint.model.tools;

import com.futureprocessing.fxpaint.model.Point;
import com.futureprocessing.fxpaint.utils.ConditionalStack;
import javafx.concurrent.Task;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Stack;

import static com.futureprocessing.fxpaint.model.Point.ONE;
import static com.futureprocessing.fxpaint.model.Point.ONE_X;
import static com.futureprocessing.fxpaint.model.Point.ONE_Y;

public class BucketTool implements Tool {

    private static final double EPSILON = 0.2;

    @Override
    public String id() {
        return "bucket";
    }

    @Override
    public void startDrawing(GraphicsContext gc, Point currentMousePoint) {
        WritableImage canvasSnapshot = getCanvasSnapshot(gc.getCanvas());
        Color replacementColor = (Color) gc.getFill();

        Task<Void> task = new FloodFill(canvasSnapshot, currentMousePoint, replacementColor);
        task.setOnSucceeded(event -> gc.drawImage(canvasSnapshot, 0, 0));
        new Thread(task).start();
    }

    private class FloodFill extends Task<Void> {

        private final WritableImage canvasSnapshot;
        private final Point clickPoint;
        private final Color replacementColor;

        public FloodFill(WritableImage canvasSnapshot, Point clickPoint, Color replacementColor) {
            this.canvasSnapshot = canvasSnapshot;
            this.clickPoint = clickPoint;
            this.replacementColor = replacementColor;
        }

        @Override
        protected Void call() throws Exception {
            Stack<Point> stack = new Stack<>();
            PixelReader pixelReader = canvasSnapshot.getPixelReader();
            PixelWriter pixelWriter = canvasSnapshot.getPixelWriter();
            Color targetColor = pixelReader.getColor(clickPoint.getIntX(), clickPoint.getIntY());
            if (replacementColor.equals(targetColor)) {
                return null;
            }
            stack.push(clickPoint);
            while (!stack.isEmpty()) {
                Point pixel = stack.pop();
                Color pixelColor = pixelReader.getColor(pixel.getIntX(), pixel.getIntY());
                if (isAlreadyFilled(pixelColor, targetColor)) {
                    continue;
                }
                pixelWriter.setColor(pixel.getIntX(), pixel.getIntY(), replacementColor);
                ConditionalStack.of(stack).when(point -> isInBounds(point, canvasSnapshot)).push(pixel,
                        pixel.minus(ONE),
                        pixel.minus(ONE_X),
                        pixel.minus(ONE_X).plus(ONE_Y),
                        pixel.plus(ONE_Y),
                        pixel.plus(ONE),
                        pixel.plus(ONE_X),
                        pixel.plus(ONE_X).minus(ONE_Y),
                        pixel.minus(ONE_Y));
            }
            return null;
        }

        private boolean isInBounds(Point point, Image image) {
            return point.isIn(Point.ZERO, image.getWidth(), image.getHeight());
        }

        private boolean isAlreadyFilled(Color readColor, Color colorToFill) {
            return !withinTolerance(readColor, colorToFill, EPSILON);
        }

        private boolean withinTolerance(Color a, Color b, double epsilon) {
            return withinTolerance(a.getRed(), b.getRed(), epsilon) &&
                    withinTolerance(a.getGreen(), b.getGreen(), epsilon) &&
                    withinTolerance(a.getBlue(), b.getBlue(), epsilon);
        }

        private boolean withinTolerance(double a, double b, double epsilon) {
            return Math.abs(a - b) < epsilon;
        }
    }

    private WritableImage getCanvasSnapshot(Canvas canvas) {
        WritableImage canvasSnapshot = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
        return canvas.snapshot(null, canvasSnapshot);
    }
}
