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
    protected static int screenWidth;
    protected static int screenHeight;

    public Entity(Bitmap b, int x, int y, int width, int height, int s) {

        this.width = (width*screenWidth)/1000;
        this.height = (height*screenHeight)/2000;
        this.b = Bitmap.createScaledBitmap(b, width, height, false);
        speed = (s*screenWidth)/1000;
        this.x =  (x*screenWidth)/1000;
        this.y =  (y*screenHeight)/2000;
    }

    public static void setScreenDimensions(int width, int height){
        screenWidth = width;
        screenHeight = height;
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
        speed = (s*screenWidth)/1000;
    }

    public Rect getBounds(){ return (new Rect(getX(), getY(), getWidth(), getHeight()));}
}
