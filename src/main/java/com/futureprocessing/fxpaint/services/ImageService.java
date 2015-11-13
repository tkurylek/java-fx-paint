package com.futureprocessing.fxpaint.services;

import com.futureprocessing.fxpaint.exceptions.FileSaveNotPossibleException;
import com.futureprocessing.fxpaint.model.ImageType;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.inject.Singleton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static com.futureprocessing.fxpaint.model.ImageType.PNG;
import static java.util.Objects.requireNonNull;

@Singleton
public class ImageService {

    private static final Logger LOGGER = Logger.getLogger(ImageService.class);
    public static final char DOT_CHARACTER = '.';

    public Image openImage(File file) {
        requireNonNull(file);
        return new Image(file.toURI().toString());
    }

    public void save(WritableImage image, File file) {
        requireNonNull(file);
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
        try {
            ImageIO.write(bufferedImage, extension(file), file);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new FileSaveNotPossibleException("Could not save image", e);
        }
    }

    private String extension(File file) {
        String name = file.getName();
        int pos = name.lastIndexOf(DOT_CHARACTER);
        if (pos > 0) {
            return name.substring(pos + 1, name.length());
        }
        LOGGER.error("Unrecognised extension of file " + file.getName());
        throw new FileSaveNotPossibleException("Unrecognised file %s extension.", file.getName());
    }

    public Collection<FileChooser.ExtensionFilter> getSupportedExtensions() {
        ArrayList<FileChooser.ExtensionFilter> supportedExtensions = new ArrayList<>();
        Collections.addAll(supportedExtensions, toExtensionFilter(PNG));
        return supportedExtensions;
    }

    private FileChooser.ExtensionFilter toExtensionFilter(ImageType imageType) {
        return new FileChooser.ExtensionFilter(imageType.getDescription(), imageType.getExtensionPattern());
    }
}
