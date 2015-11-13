package com.futureprocessing.fxpaint.controllers;

import com.futureprocessing.fxpaint.model.DrawableCanvas;
import com.futureprocessing.fxpaint.model.HtmlColor;
import com.futureprocessing.fxpaint.model.Point;
import com.futureprocessing.fxpaint.model.tools.Tool;
import com.futureprocessing.fxpaint.services.WorkHistoryService;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.URL;
import java.util.ResourceBundle;

@Singleton
public class CanvasController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(CanvasController.class);
    @FXML
    private Canvas sketchCanvas;
    @FXML
    private Canvas drawCanvas;

    private final WorkHistoryService workHistoryService;
    private DrawableCanvas drawableCanvas;
    private DrawableCanvas sketchableCanvas;

    @Inject
    public CanvasController(WorkHistoryService workHistoryService) {
        this.workHistoryService = workHistoryService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawableCanvas = DrawableCanvas.of(drawCanvas).cleared();
        sketchableCanvas = DrawableCanvas.of(sketchCanvas).cleared();
        resizeSketchCanvasOnDrawCanvasResize();
        createHistorySnapshot();
    }

    private void resizeSketchCanvasOnDrawCanvasResize() {
        drawCanvas.widthProperty().addListener((observable, oldWidth, newWidth)
                -> sketchCanvas.setWidth(newWidth.doubleValue()));
        drawCanvas.heightProperty().addListener((observable, oldHeight, newHeight)
                -> sketchCanvas.setHeight(newHeight.doubleValue()));
    }

    public void onMousePressed(MouseEvent event) {
        drawableCanvas.on(Point.from(event)).startDrawing();
    }

    public void onMouseDragged(MouseEvent event) {
        sketchableCanvas.advanced().cleared().on(Point.from(event)).continueSketching();
        drawableCanvas.on(Point.from(event)).continueDrawing();
    }

    public void onMouseReleased(MouseEvent event) {
        sketchableCanvas.withdrawn().cleared();
        drawableCanvas.on(Point.from(event)).stopDrawing();
        createHistorySnapshot();
    }

    public void setSelectedColor(HtmlColor htmlColor) {
        sketchableCanvas.coloredBy(htmlColor.toFxColor());
        drawableCanvas.coloredBy(htmlColor.toFxColor());
        LOGGER.debug("Color set - " + htmlColor.name());
    }

    public void setSelectedTool(Tool tool) {
        drawableCanvas.equippedWith(tool);
        sketchableCanvas.equippedWith(tool);
        LOGGER.debug("Tool set - " + tool.id());
    }

    public void drawImage(Image image) {
        drawableCanvas.coveredBy(image);
    }

    public void rotate(double degrees) {
        drawableCanvas.rotated(degrees);
        createHistorySnapshot();
    }

    public void flip(Point direction) {
        drawableCanvas.flipped(direction);
        createHistorySnapshot();
    }

    public void createHistorySnapshot() {
        workHistoryService.push(getWorkSnapshot());
        LOGGER.debug("Work snapshot taken");
    }

    public WritableImage getWorkSnapshot() {
        return drawableCanvas.snapshot();
    }

    public DoubleProperty widthProperty() {
        return drawCanvas.widthProperty();
    }

    public DoubleProperty heightProperty() {
        return drawCanvas.heightProperty();
    }
}
