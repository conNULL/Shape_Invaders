package com.android.connal.shapeinvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.LinkedList;

/**
 * Created by Connal on 2016-08-12.
 */
public class Enemy3 extends Enemy {
    public Enemy3(int x, int y, GScreen g){
        super(x, y, screenWidth/24, screenHeight/24, 10, 20, 300, 150, 10, 20,6, 35, 200,
                BitmapFactory.decodeResource(g.getResources(), R.mipmap.green_triangle_1),
                BitmapFactory.decodeResource(g.getResources(), R.mipmap.e_laser1), g);
    }

    public void shoot(LinkedList l, int shotNum){
        if(shotNum > shotBase && shotNum < shotBase + shotF)
            l.add(new Laser(false, power, shotSpeed, lb, x + width/2, y + height + 1, screenWidth/70, screenHeight/20));
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
}
