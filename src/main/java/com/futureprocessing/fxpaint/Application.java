package com.futureprocessing.fxpaint;

import com.cathive.fx.guice.GuiceApplication;
import com.cathive.fx.guice.GuiceFXMLLoader;
import com.futureprocessing.fxpaint.components.ExitConfirmationDialog;
import com.futureprocessing.fxpaint.inject.NotifiersInjector;
import com.futureprocessing.fxpaint.inject.OperationsInjector;
import com.futureprocessing.fxpaint.inject.ToolsInjector;
import com.google.inject.Module;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.io.IOException;
import java.lang.String;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Application extends GuiceApplication {

    private static final Logger LOGGER = Logger.getLogger(Application.class);
    private static final String COMMUNICATES = "communicates";
    public static final String MAIN_FXML = "/layout/main.fxml";
    public static final String STYLE_CSS = "/css/style.css";
    public static final String TITLE = "FxPaint";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    @Inject
    private GuiceFXMLLoader fxmlLoader;

    @Override
    public void init(List<Module> list) {
        list.add(new OperationsInjector());
        list.add(new ToolsInjector());
        list.add(new NotifiersInjector());
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        LOGGER.debug("Application start");
        LOGGER.debug("Loaded locale " + Locale.getDefault());
        URL mainFxml = getClass().getResource(MAIN_FXML);
        ResourceBundle communicates = ResourceBundle.getBundle(COMMUNICATES);
        Parent root = fxmlLoader.load(mainFxml, communicates).getRoot();
        LOGGER.debug("Loaded layout " + MAIN_FXML);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.getStylesheets().add(STYLE_CSS);
        LOGGER.debug("Loaded css " + STYLE_CSS);

        primaryStage.setTitle(TITLE);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new ExitConfirmationDialog(communicates)::show);
    }

    public static void main(String[] args) {
        launch(args);
    }
}