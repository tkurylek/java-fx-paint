package com.futureprocessing.fxpaint.model.operations;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import com.futureprocessing.fxpaint.model.Point;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import javax.inject.Inject;

public class FlipHorizontallyOperation implements Operation {

    private static final Logger LOGGER = Logger.getLogger(FlipHorizontallyOperation.class);
    private final CanvasController canvasController;

    @Inject
    public FlipHorizontallyOperation(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public String id() {
        return "flipHorizontally";
    }

    @Override
    public void operate(Window ownerWindow) {
        canvasController.flip(Point.from(-1, 1));
        LOGGER.debug("Flipped horizontally");
    }
}
