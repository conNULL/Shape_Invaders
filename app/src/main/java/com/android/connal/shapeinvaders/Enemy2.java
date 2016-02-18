package com.android.connal.shapeinvaders;

import android.graphics.BitmapFactory;

import java.util.LinkedList;

/**
 * Created by Connal on 2015-12-08.
 */
public class Enemy2 extends Enemy {

    public Enemy2(int x, int y, gScreen g){
        super(x, y, 10 , 5, 250, 125, 10, 3, 20, 150,
                BitmapFactory.decodeResource(g.getResources(), R.mipmap.square_enemy_2),
                BitmapFactory.decodeResource(g.getResources(), R.mipmap.e_laser1), g);
    }

    public void move(int moveNum) {

        if (moveNum > moveBase && moveNum < moveBase + moveF)
            direction = (int) ((Math.random() * 6))+1;

        switch (direction) {

            case 1: case 2:
                x += speed;
                break;
            case 3: case 4:
                x -= speed;
                break;
            case 5:
                y -= speed;
                break;
            case 6:
                y += speed;
                break;
        }

        correctRange();

    }

    public void shoot(LinkedList l, int shotNum){

        if(shotNum > shotBase && shotNum < shotBase + shotF)
            l.add(new Laser(false, power, 20, lb, x + getWidth()/2 - b.getWidth()/2, y + b.getHeight() + 1));


    }
}
