package com.futureprocessing.fxpaint.model.operations;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import javax.inject.Inject;

public class RotateRight90Operation implements Operation {

    private static final Logger LOGGER = Logger.getLogger(RotateRight90Operation.class);
    private final CanvasController canvasController;

    @Inject
    public RotateRight90Operation(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public String id() {
        return "rotateRight90";
    }

    @Override
    public void operate(Window ownerWindow) {
        canvasController.rotate(90);
        LOGGER.debug("Rotated right 90");
    }
}
