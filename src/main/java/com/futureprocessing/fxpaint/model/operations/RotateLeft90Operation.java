package com.futureprocessing.fxpaint.model.operations;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import javax.inject.Inject;

public class RotateLeft90Operation implements Operation {

    private static final Logger LOGGER = Logger.getLogger(RotateLeft90Operation.class);
    private final CanvasController canvasController;

    @Inject
    public RotateLeft90Operation(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public String id() {
        return "rotateLeft90";
    }

    @Override
    public void operate(Window ownerWindow) {
        canvasController.rotate(-90);
        LOGGER.debug("Rotated left 90");
    }
}
