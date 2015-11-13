package com.futureprocessing.fxpaint.controllers;

import com.futureprocessing.fxpaint.model.tools.Tool;
import com.futureprocessing.fxpaint.notifiers.Notifier;
import com.futureprocessing.fxpaint.services.ToolService;
import com.futureprocessing.fxpaint.utils.NodeChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class ToolController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(ToolController.class);
    @FXML
    private ToggleGroup toolsGroup;
    private ToolService toolService;
    private Notifier<Tool> toolSelectionNotifier;

    @Inject
    public ToolController(ToolService toolService, Notifier<Tool> toolSelectionNotifier) {
        this.toolService = toolService;
        this.toolSelectionNotifier = toolSelectionNotifier;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        toolsGroup.selectedToggleProperty().addListener(notifyInterestedThatToolHasChanged());
    }

    private NodeChangeListener notifyInterestedThatToolHasChanged() {
        return (changedTool) -> {
            Tool selectedTool = toolService.getToolById(changedTool.getId());
            LOGGER.debug("Notifying that tool has been selected");
            toolSelectionNotifier.notify(selectedTool);
        };
    }
}
