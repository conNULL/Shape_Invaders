package com.android.connal.shapeinvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.LinkedList;

/**
 * Created by Connal on 2015-12-07.
 */
public class Enemy1 extends Enemy{

    public Enemy1(int x, int y, GScreen g){
        super(x, y, 70, 70, 10, 5, 250, 125, 10, 20,0, 0, 100,
                BitmapFactory.decodeResource(g.getResources(), R.mipmap.square_enemy_1),
                BitmapFactory.decodeResource(g.getResources(), R.mipmap.e_laser1), g);
    }

    public void move(int moveNum){}

    public void shoot(LinkedList l, int shotNum){
        if(shotNum > shotBase && shotNum < shotBase + shotF)
            l.add(new Laser(false, power, shotSpeed, lb, x+width/2, y + height + 1, 10, 100));
    }
}

