package com.android.connal.shapeinvaders;

import android.graphics.Bitmap;
import android.graphics.Rect;


/**
 * Created by Connal on 2015-05-26.
 */
public abstract class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Bitmap b;
    protected int speed;

    public Entity(Bitmap b, int a, int h, int s) {

        this.b = b;
        width = b.getWidth();
        height = b.getHeight();
        speed = s;
        setX(a);
        setY(h);
    }
    public Entity(){};

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Bitmap getB() {
        return b;
    }

    public int getSpeed() {
        return speed;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpeed(int s){
        speed = s;
    }

    public Rect getBounds(){ return (new Rect(getX(), getY(), getWidth(), getHeight()));}
}
