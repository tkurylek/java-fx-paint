package com.futureprocessing.fxpaint.model.tools;

import com.futureprocessing.fxpaint.model.Point;
import javafx.scene.canvas.GraphicsContext;

public class PencilTool implements Tool {

    @Override
    public String id() {
        return "pencil";
    }

    @Override
    public void startDrawing(GraphicsContext gc, Point point) {
        gc.beginPath();
        gc.lineTo(point.getX(), point.getY());
        gc.stroke();
    }

    @Override
    public void continueDrawing(GraphicsContext gc, Point point) {
        gc.lineTo(point.getX(), point.getY());
        gc.stroke();
    }
}
