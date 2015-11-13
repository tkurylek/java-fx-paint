package com.futureprocessing.fxpaint.model;

import com.futureprocessing.fxpaint.model.tools.NullTool;
import com.futureprocessing.fxpaint.model.tools.Tool;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import static java.util.Objects.requireNonNull;

public class DrawableCanvas {

    private final GraphicsContext graphicsContext;
    private final Canvas canvas;
    private Tool tool = new NullTool();

    private DrawableCanvas(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
    }

    public static DrawableCanvas of(Canvas canvas) {
        requireNonNull(canvas);
        return new DrawableCanvas(canvas);
    }

    public DrawableCanvas coloredBy(Color color) {
        graphicsContext.setFill(color);
        graphicsContext.setStroke(color);
        return this;
    }

    public DrawableCanvas equippedWith(Tool tool) {
        this.tool = tool;
        return this;
    }

    public DrawableCanvas cleared() {
        graphicsContext.beginPath();
        graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        return this;
    }

    public DrawableCanvas advanced() {
        canvas.toFront();
        return this;
    }

    public DrawableCanvas withdrawn() {
        canvas.toBack();
        return this;
    }

    public DrawableCanvas coveredBy(Image image) {
        graphicsContext.drawImage(image, 0, 0);
        return this;
    }

    public DrawableCanvas rotated(double degrees) {
        canvas.setRotate(canvas.getRotate() + degrees);
        coveredBy(snapshot());
        canvas.setRotate(canvas.getRotate() - degrees);
        return this;
    }

    public WritableImage snapshot() {
        return canvas.snapshot(null, null);
    }

    public DrawableCanvas flipped(Point direction) {
        canvas.setScaleX(canvas.getScaleX() * direction.getX());
        canvas.setScaleY(canvas.getScaleY() * direction.getY());
        return this;
    }

    public InitializedTool on(Point point) {
        return new InitializedTool() {

            @Override
            public void startDrawing() {
                tool.startDrawing(graphicsContext, point);
            }

            @Override
            public void continueDrawing() {
                tool.continueDrawing(graphicsContext, point);
            }

            @Override
            public void continueSketching() {
                tool.continueSketching(graphicsContext, point);
            }

            @Override
            public void stopDrawing() {
                tool.stopDrawing(graphicsContext, point);
            }
        };
    }

    public interface InitializedTool {

        void startDrawing();

        void continueDrawing();

        void continueSketching();

        void stopDrawing();
    }
}
