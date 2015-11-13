package com.futureprocessing.fxpaint.model.operations;

import com.futureprocessing.fxpaint.controllers.CanvasController;
import com.futureprocessing.fxpaint.services.WorkHistoryService;
import javafx.stage.Window;

import javax.inject.Inject;

public class UndoOperation implements Operation {

    private final CanvasController canvasController;
    private final WorkHistoryService workHistoryService;

    @Inject
    public UndoOperation(CanvasController canvasController, WorkHistoryService workHistoryService) {
        this.canvasController = canvasController;
        this.workHistoryService = workHistoryService;
    }

    @Override
    public String id() {
        return "undo";
    }

    @Override
    public void operate(Window ownerWindow) {
        canvasController.drawImage(workHistoryService.previous());
    }
}
