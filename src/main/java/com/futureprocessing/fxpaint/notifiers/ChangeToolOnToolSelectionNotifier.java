package com.futureprocessing.fxpaint.notifiers;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import com.futureprocessing.fxpaint.model.tools.Tool;
import org.apache.log4j.Logger;

import javax.inject.Inject;

public class ChangeToolOnToolSelectionNotifier implements Notifier<Tool> {

    private static final Logger LOGGER = Logger.getLogger(ChangeToolOnToolSelectionNotifier.class);
    private final CanvasController canvasController;

    @Inject
    public ChangeToolOnToolSelectionNotifier(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public void notify(Tool tool) {
        LOGGER.debug("Setting tool - " + tool.id());
        canvasController.setSelectedTool(tool);
    }
}
