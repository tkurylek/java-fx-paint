package com.futureprocessing.fxpaint.model.operations;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import com.futureprocessing.fxpaint.model.Point;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import javax.inject.Inject;

public class FlipVerticallyOperation implements Operation {

    private static final Logger LOGGER = Logger.getLogger(FlipVerticallyOperation.class);
    private final CanvasController canvasController;

    @Inject
    public FlipVerticallyOperation(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public String id() {
        return "flipVertically";
    }

    @Override
    public void operate(Window ownerWindow) {
        canvasController.flip(Point.from(1, -1));
        LOGGER.debug("Flipped vertically");
    }
}
