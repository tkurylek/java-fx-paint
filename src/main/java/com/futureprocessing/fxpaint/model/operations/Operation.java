package com.futureprocessing.fxpaint.model.operations;

import javafx.stage.Window;

public interface Operation {

    String id();

    void operate(Window ownerWindow);
}
