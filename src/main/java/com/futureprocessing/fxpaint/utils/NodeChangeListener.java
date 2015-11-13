package com.futureprocessing.fxpaint.utils;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

@FunctionalInterface
public interface NodeChangeListener extends ChangeListener<Toggle> {

    @Override
    default void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
        if(newValue instanceof ToggleButton) {
            changed((ToggleButton) newValue);
        }
    }

    void changed(Node node);
}
