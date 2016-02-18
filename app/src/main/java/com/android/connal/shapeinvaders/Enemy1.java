package com.android.connal.shapeinvaders;

import android.graphics.BitmapFactory;

import java.util.LinkedList;

/**
 * Created by Connal on 2015-12-07.
 */
public class Enemy1 extends Enemy{

    public Enemy1(int x, int y, gScreen g){
        super(x, y,10, 5, 250, 125, 10, 0, 0, 100,
                BitmapFactory.decodeResource(g.getResources(), R.mipmap.square_enemy_1),
                BitmapFactory.decodeResource(g.getResources(), R.mipmap.e_laser1), g);

    }

    public void move(int moveNum){}

    public void shoot(LinkedList l, int shotNum){

        if(shotNum > shotBase && shotNum < shotBase + shotF)
            l.add(new Laser(false, power, 20, lb, x + getWidth()/2 - b.getWidth()/2, y + b.getHeight() + 1));


    }
}

