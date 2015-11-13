package com.futureprocessing.fxpaint.model.operations;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import javax.inject.Inject;

public class Rotate180Operation implements Operation {

    private static final Logger LOGGER = Logger.getLogger(Rotate180Operation.class);
    private final CanvasController canvasController;

    @Inject
    public Rotate180Operation(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public String id() {
        return "rotate180";
    }

    @Override
    public void operate(Window ownerWindow) {
        canvasController.rotate(180);
        LOGGER.debug("Rotated 180");
    }
}
