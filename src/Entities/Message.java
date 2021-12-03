package Entities;

import java.awt.*;
import Graphics.*;

public class Message extends AbstractEntity {

    protected String message;
    protected int duration;
    protected Color color;
    protected int size;

    public Message(String message, double x, double y, int duration, Color color, int size) {
        
        this.x = x;
        this.y = y;
        message = message;
        duration = duration * 60; //seconds
        color = color;
        size = size;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public Color getColor() {
        return color;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Screen screen) {
    }

    @Override
    public boolean collide(AbstractEntity e) {
        return true;
    }
}
