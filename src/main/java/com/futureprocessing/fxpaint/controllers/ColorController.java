package com.futureprocessing.fxpaint.controllers;

import com.futureprocessing.fxpaint.model.HtmlColor;
import com.futureprocessing.fxpaint.notifiers.Notifier;
import com.futureprocessing.fxpaint.utils.NodeChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class ColorController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(ColorController.class);
    @FXML
    private ToggleGroup colorsGroup;
    @FXML
    private Button selectedColorButton;
    private Notifier<HtmlColor> colorSelectionNotifier;

    @Inject
    public ColorController(Notifier<HtmlColor> colorSelectionNotifier) {
        this.colorSelectionNotifier = colorSelectionNotifier;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colorsGroup.selectedToggleProperty().addListener(notifyInterestedThatColorHasChanged());
    }

    private NodeChangeListener notifyInterestedThatColorHasChanged() {
        return (changedColor) -> {
            selectedColorButton.setStyle(changedColor.getStyle());
            HtmlColor selectedHtmlColor = HtmlColor.of(changedColor.getId());
            LOGGER.debug("Notifying that color has been selected");
            colorSelectionNotifier.notify(selectedHtmlColor);
        };
    }
}
