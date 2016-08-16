package com.android.connal.shapeinvaders;

import android.graphics.Bitmap;
import android.graphics.drawable.shapes.RectShape;

import java.util.LinkedList;

/**
 * Created by Connal on 2015-05-21.
 */
public class Laser extends Entity{

    private boolean player;
    private int power;

    public Laser(boolean player, int power, int speed, Bitmap b, int x, int y) {

        super(b, x, y, speed);
        this.player = player;
        this.power = power;
    }

    public int getPower() {
        return power;
    }

    public boolean isPlayer(){

        return player;
    }

    public void move(GScreen g, Player p, LinkedList l){

        if(player) {
            y -= speed;
            if (y < 0) {
                destroy(l, p);
            }
        } else{
            y += speed;
            if(y + getHeight() > g.getHeight())
                destroy(l, p);
        }
    }


    public void destroy(LinkedList l, Player p){

        if(isPlayer())
            p.addShot();
        l.remove(this);
    }
}
