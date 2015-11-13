package com.futureprocessing.fxpaint.model;

import javafx.scene.paint.Color;

public enum HtmlColor {
    RED("red"), GREEN("green"), BLUE("blue"), AQUA("aqua"), BLACK("black"), ORANGE("orange");

    private final String htmlColor;

    HtmlColor(String htmlColor) {
        this.htmlColor = htmlColor;
    }

    public Color toFxColor() {
        return Color.valueOf(htmlColor.toUpperCase());
    }

    public static HtmlColor of(String htmlColor) {
        return HtmlColor.valueOf(htmlColor.toUpperCase());
    }
}
