package com.futureprocessing.fxpaint.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.ResourceBundle;

public class WindowController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(WindowController.class);
    @FXML
    private CanvasController canvasController;
    @FXML
    private Pane canvasContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LOGGER.debug("Binding canvas width and height with containing pane");
        canvasController.heightProperty().bind(canvasContainer.heightProperty());
        canvasController.widthProperty().bind(canvasContainer.widthProperty());
    }
}
