package com.futureprocessing.fxpaint.model;

public enum ImageType {

    PNG("Portable Network Graphics", "*.png");

    private final String description;
    private final String extensionPattern;

    ImageType(String description, String extensionPattern) {
        this.description = description;
        this.extensionPattern = extensionPattern;
    }

    public String getDescription() {
        return description;
    }

    public String getExtensionPattern() {
        return extensionPattern;
    }
}
