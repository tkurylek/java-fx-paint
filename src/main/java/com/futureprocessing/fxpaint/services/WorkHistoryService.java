package com.futureprocessing.fxpaint.services;

import javafx.scene.image.Image;

import javax.inject.Singleton;
import java.util.LinkedList;
import java.util.List;

@Singleton
public class WorkHistoryService {

    private List<Image> list = new LinkedList<>();
    private int index = -1;

    public Image previous() {
        if (index > 0) {
            index--;
        }
        return list.get(index);
    }

    public Image next() {
        if (index < list.size() - 1) {
            index++;
        }
        return list.get(index);
    }

    public void push(Image image) {
        index++;
        image.cancel();
        list = list.subList(0, index);
        list.add(image);
    }
}
