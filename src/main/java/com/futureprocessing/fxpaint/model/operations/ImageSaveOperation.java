package com.futureprocessing.fxpaint.model.operations;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import com.futureprocessing.fxpaint.services.ImageService;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.io.File;
import java.util.function.Supplier;

public class ImageSaveOperation implements Operation {

    private static final Logger LOGGER = Logger.getLogger(ImageSaveOperation.class);
    private static final String TITLE = "Save Image File";
    private final CanvasController canvasController;
    private final ImageService imageService;
    private final FileChooser fileChooser = new FileChooser();

    @Inject
    public ImageSaveOperation(CanvasController canvasController, ImageService imageService) {
        this.canvasController = canvasController;
        this.imageService = imageService;
        fileChooser.setTitle(TITLE);
        fileChooser.getExtensionFilters().addAll(imageService.getSupportedExtensions());
    }

    @Override
    public String id() {
        return "save";
    }

    @Override
    public void operate(Window ownerWindow) {
        WritableImage writableImage = canvasController.getWorkSnapshot();
        File file = fileChooser.showSaveDialog(ownerWindow);
        if (file != null) {
            imageService.save(writableImage, file);
            LOGGER.debug("File saved " + file.getName());
        }
    }
}
