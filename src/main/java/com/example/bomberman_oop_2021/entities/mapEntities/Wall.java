package com.example.bomberman_oop_2021.entities.mapEntities;

import com.example.bomberman_oop_2021.entities.AbstractEntity;
import javafx.scene.image.Image;

public class Wall extends AbstractEntity {

    public Wall(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }
}
