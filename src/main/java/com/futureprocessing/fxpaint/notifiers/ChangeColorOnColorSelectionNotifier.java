package com.futureprocessing.fxpaint.notifiers;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import com.futureprocessing.fxpaint.model.HtmlColor;
import org.apache.log4j.Logger;

import javax.inject.Inject;

public class ChangeColorOnColorSelectionNotifier implements Notifier<HtmlColor> {

    private static final Logger LOGGER = Logger.getLogger(ChangeColorOnColorSelectionNotifier.class);
    private final CanvasController canvasController;

    @Inject
    public ChangeColorOnColorSelectionNotifier(CanvasController canvasController) {
        this.canvasController = canvasController;
    }

    @Override
    public void notify(HtmlColor htmlColor) {
        LOGGER.debug("Setting color - " + htmlColor.name());
        canvasController.setSelectedColor(htmlColor);
    }
}
