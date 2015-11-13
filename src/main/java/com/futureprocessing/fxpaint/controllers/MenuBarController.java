package com.futureprocessing.fxpaint.controllers;

import com.futureprocessing.fxpaint.services.OperationService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Window;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {

    private static final Logger LOGGER = Logger.getLogger(MenuBarController.class);
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu fileMenu;
    @FXML
    private Menu editMenu;
    @FXML
    private Menu rotateMenu;
    private final OperationService operationService;

    @Inject
    public MenuBarController(OperationService operationService) {
        this.operationService = operationService;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        assignEachMenuItemWithItsOperation();
    }

    private void assignEachMenuItemWithItsOperation() {
        getAllMenuItems().forEach(menuItem -> menuItem.setOnAction(event -> {
            Window ownerWindow = menuBar.getScene().getWindow();
            String operationId = menuItem.getId();
            LOGGER.debug("Executing operation " + operationId);
            operationService.getOperationById(operationId).operate(ownerWindow);
        }));
    }

    private List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.addAll(fileMenu.getItems());
        menuItems.addAll(editMenu.getItems());
        menuItems.addAll(rotateMenu.getItems());
        return menuItems;
    }
}
