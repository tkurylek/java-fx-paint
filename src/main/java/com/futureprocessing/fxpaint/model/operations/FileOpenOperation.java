package com.futureprocessing.fxpaint.model.operations;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import com.futureprocessing.fxpaint.model.DrawableCanvas;
import com.futureprocessing.fxpaint.model.Point;
import com.futureprocessing.fxpaint.services.ImageService;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.io.File;

public class FileOpenOperation implements Operation {

    private static final Logger LOGGER = Logger.getLogger(FileOpenOperation.class);
    private static final String TITLE = "Open Image File";
    private final FileChooser fileChooser = new FileChooser();
    private final CanvasController canvasController;
    private final ImageService imageService;

    @Inject
    public FileOpenOperation(CanvasController canvasController, ImageService imageService) {
        this.canvasController = canvasController;
        this.imageService = imageService;
        fileChooser.setTitle(TITLE);
        fileChooser.getExtensionFilters().addAll(imageService.getSupportedExtensions());
    }

    @Override
    public String id() {
        return "open";
    }

    @Override
    public void operate(Window ownerWindow) {
        File file = fileChooser.showOpenDialog(ownerWindow);
        if (file != null) {
            LOGGER.debug("Opening file " + file.getName());
            Image image = imageService.openImage(file);
            canvasController.drawImage(image);
            canvasController.createHistorySnapshot();
        }
    }
}
