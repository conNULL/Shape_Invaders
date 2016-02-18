package com.android.connal.shapeinvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.LinkedList;

/**
 * Created by Connal on 2015-05-14.
 */
public class Player extends Entity {

    private int health;
    private int lives;
    private int shotsLeft;
    private int power;
    private int score;


    public Player(Bitmap b, int a , int h, int s){

        super(b, a, h, s);
        health = 100;
        lives = 3;
        score = 0;
        power = 10;
        shotsLeft = 2;
    }

    public boolean isDead(){

        if(lives == 0)
            return true;
        else
            return false;

    }

    public void hit(Laser z, LinkedList l, Player p){

        setHealth(this.health -  z.getPower());
        z.destroy(l, p);
        if(health <= 0)
            die();
    }

    public void die(){

        lives--;
        health = 100;
    }

    public void shoot(LinkedList l, gScreen g){

        if(shotsLeft != 0) {
            Bitmap b = BitmapFactory.decodeResource(g.getResources(), R.mipmap.player_laser);
            l.add(new Laser(true, power, 20, b, x + getWidth()/2 - b.getWidth()/2, y - b.getHeight() - 1));
            shotsLeft--;
        }
    }

    public void addShot(){shotsLeft++;}
    public int getHealth(){
        return health;
    }
    public int getScore(){return score;}
    public void setScore(int change){this.score += change;}
    public void setHealth(int h){ this.health = h;}
    public int getLives(){
        return lives;
    }

}
