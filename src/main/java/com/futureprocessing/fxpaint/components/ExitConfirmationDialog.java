package com.futureprocessing.fxpaint.components;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;

import javax.inject.Inject;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.ButtonType.OK;

public class ExitConfirmationDialog {

    private static final String CONFIRMATION_TITLE = "confirmation.title";
    private static final String CONFIRMATION_HEADER = "confirmation.header";
    private static final String CONFIRMATION_CONTENT = "confirmation.content";
    private final Alert alert;

    @Inject
    public ExitConfirmationDialog(ResourceBundle communicates) {
        alert = new Alert(CONFIRMATION);
        alert.setTitle(communicates.getString(CONFIRMATION_TITLE));
        alert.setHeaderText(communicates.getString(CONFIRMATION_HEADER));
        alert.setContentText(communicates.getString(CONFIRMATION_CONTENT));

    }

    public void show(WindowEvent event) {
        Optional<ButtonType> decision = alert.showAndWait();
        if (decision.get() != OK) {
            event.consume();
        }
    }
}
