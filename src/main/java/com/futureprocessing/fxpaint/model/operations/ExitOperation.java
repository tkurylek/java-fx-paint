package com.futureprocessing.fxpaint.model.operations;

import javafx.application.Platform;
import javafx.stage.Window;

public class ExitOperation implements Operation {

    @Override
    public String id() {
        return "exit";
    }

    @Override
    public void operate(Window ownerWindow) {
        Platform.exit();
    }
}
