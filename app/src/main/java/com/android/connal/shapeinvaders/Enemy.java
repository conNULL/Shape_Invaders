package com.android.connal.shapeinvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.LinkedList;

/**
 * Created by Connal on 2015-06-11.
 */
public abstract class Enemy extends Entity {

    private int health;
    protected int shotF;
    protected int moveF;
    private int xRange;
    private int yRange;
    private int sX;
    private int sY;
    protected int power;
    protected int shotSpeed;
    protected Bitmap lb;
    protected int shotBase;
    protected int moveBase;
    protected int direction;
    protected int value;

    public Enemy(int a , int h, int health, int shotF, int xRange, int yRange, int power,int shotSpeed, int speed,
                 int moveF, int value, Bitmap b, Bitmap lb,  GScreen g) {

        setX(a);
        setY(h);
        this.health = health;
        this.shotF = shotF;
        this.moveF = moveF;
        this.shotSpeed = shotSpeed;
        this.xRange = xRange;
        this.yRange = yRange;
        this.power = power;
        this.speed = speed;
        this.value = value;
        this.b = b;
        this.lb = lb;
        sX = x;
        sY = y;
        direction = 0;
/*

        switch(type){

            case 1: health = 10;
                    shotF = 5;
                    xRange = 250;
                    yRange = 125;
                    power = 10;
                    speed = 0;
                    moveF = 0;
                    b = BitmapFactory.decodeResource(g.getResources(), R.mipmap.square_enemy_1);
                    lb = BitmapFactory.decodeResource(g.getResources(), R.mipmap.e_laser1); break;

            case 2: health = 10;
                    shotF = 5;
                    xRange = 250;
                    yRange = 125;
                    power = 10;
                    speed = 3;
                    moveF = 20;
                    b = BitmapFactory.decodeResource(g.getResources(), R.mipmap.square_enemy_2);
                    lb = BitmapFactory.decodeResource(g.getResources(), R.mipmap.e_laser1); break;
        }
*/
        shotBase = (int)((Math.random() * (1000 - shotF))) + 1;
        moveBase = (int)((Math.random() * (1000 - moveF))) + 1;
        width = b.getWidth();
        height = b.getHeight();

    }

    public void setHealth(int h){

        health = h;
    }

    public int getHealth(){

        return health;
    }

    public void correctRange(){

        if(x < sX - xRange)
            x = sX - xRange;
        else if(x > sX + xRange)
            x = sX + xRange;

        if(y < sY - yRange)
            y = sY - yRange;
        else if(y > sY + yRange)
            y = sY + yRange;
    }

    public abstract void move(int moveNum);

    public abstract void shoot(LinkedList l, int shotNum);

    public void hit(LinkedList e, LinkedList l, Laser z, Player p){

        health -= z.getPower();
        z.destroy(l, p);
        if(health <= 0)
            destroy(e, p);
    }

    public void destroy(LinkedList l, Player p){
        p.setScore(value);
        l.remove(this);
    }
}
