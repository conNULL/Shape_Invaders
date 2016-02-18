package com.android.connal.shapeinvaders;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Connal on 2015-05-14.
 */
public class gScreen extends SurfaceView {

    private Canvas c;
    private SurfaceHolder sh;
    protected Player p;
    LinkedList lasers;
    private LinkedList enemies;
    private boolean created = false;
    private int level;
    Enemy enemy;
    Laser laser;
    TextView view;
    public gScreen(Context context) {
        super(context);
        init();
    }

    public gScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public gScreen(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init(){

        sh = getHolder();



                sh.addCallback(new SurfaceHolder.Callback() {

                    @Override
                    public void surfaceCreated(SurfaceHolder holder) {


                        Bitmap b = BitmapFactory.decodeResource(getResources(),
                                R.mipmap.blue_arrow);
                        p = new Player(b, getWidth() / 2 - b.getWidth() / 2, getHeight() - b.getHeight(), 12);
                        lasers = new LinkedList();
                        enemies = new LinkedList();
                        level = 1;
                        startLevel(level);
                        created = true;

                    }

                    @Override
                    public void surfaceChanged(SurfaceHolder holder,
                                               int format, int width, int height) {


                    }

                    @Override
                    public void surfaceDestroyed(SurfaceHolder holder) {


                    }
                });

    }

    public void moveRight(){

        if(p.getX() + p.getSpeed() + p.getWidth() < getWidth())
             p.setX(p.getX() + p.getSpeed());

    }

    public void moveLeft(){

        if(p.getX() - p.getSpeed() > 0)
             p.setX(p.getX() - p.getSpeed());
    }

    public void draw(){

        if(created){

            c = sh.lockCanvas();
            clear();
            drawPlayer();
            drawLasers();
            drawEnemies();
            sh.unlockCanvasAndPost(c);
        }
    }

    public void clear(){

        c.drawColor(Color.BLACK);

    }

    public void drawPlayer(){

        c.drawBitmap(p.getB(), p.getX(), p.getY(), null);
    }

    public void drawLasers(){

        for(int i = 0; i < lasers.size(); i++){

            Laser l = (Laser) lasers.get(i);
            c.drawBitmap(l.getB(), l.getX(), l.getY(), null);
        }
    }

    public void drawEnemies(){

        for(int i = 0; i < enemies.size(); i++) {
            Enemy e = (Enemy) enemies.get(i);
            c.drawBitmap(e.getB(), e.getX(), e.getY(), null);
        }
    }

    public void pShoot(){

        p.shoot(lasers, this);
    }

    public void update(){

        if(created) {
            moveLasers();
            moveEnemiesAndShoot();
            if(enemies.size() == 0)
                startLevel(++level);
        }
    }

    public void moveLasers(){

        for(int i = 0; i < lasers.size(); i++)
            ((Laser) lasers.get(i)).move(this, p, lasers);
    }

    public void moveEnemiesAndShoot(){

        int e = (int)(Math.random() * 1000) + 1;

        for(int i = 0; i < enemies.size(); i++) {

            enemy = (Enemy) enemies.get(i);
            enemy.move(e);
            correctRange(enemy);
            enemy.shoot(lasers, e);
        }
    }

    public void correctRange(Entity e){

        if(e.getX() < 0)
            e.setX(0);
        else if(e.getX() + e.getWidth() > getWidth())
            e.setX(getWidth() - e.getWidth());

        if(e.getY() < 0)
            e.setY(0);
        else if(e.getY() + e.getHeight() > getHeight())
            e.setY(getHeight() - e.getHeight());
    }

    public void checkCollisions(){

        if(created) {

           // p.setHealth(lasers.size());
            for (int i = 0; i < lasers.size(); i++) {

                Laser l = (Laser) lasers.get(i);
                if (l.isPlayer()) {

                    for (int j = 0; j < enemies.size(); j++) {

                        Enemy e = (Enemy) enemies.get(j);
                        if (isCollision(l, e)) {
                            e.hit(enemies, lasers, l, p);

                        }
                    }
                } else {

                    if (isCollision(l, p))
                        p.hit(l, lasers, p);
                }
            }
        }
    }

    public boolean isCollision(Entity e1, Entity e2) {

        if (e1.getX() >= e2.getX() && e1.getX() <= e2.getX() + e2.getWidth()) {
            if (e1.getY() >= e2.getY() && e1.getY() <= e2.getY() + e2.getHeight())
                return true;
            if (e2.getY() >= e1.getY() && e2.getY() <= e1.getY() + e1.getHeight())
                return true;
        }

        if (e2.getX() >= e1.getX() && e2.getX() <= e1.getX() + e1.getWidth()) {
            if (e1.getY() >= e2.getY() && e1.getY() <= e2.getY() + e2.getHeight())
                return true;
            if (e2.getY() >= e1.getY() && e2.getY() <= e1.getY() + e1.getHeight())
                return true;

        }
        return false;
    }

    public void startLevel(int level){


        switch(level){

            case 1:
                enemies.add(new Enemy1(200,20, this));
                enemies.add(new Enemy1(450,20, this));
                enemies.add(new Enemy1(700,20, this));
                enemies.add(new Enemy1(950,20,this));
                enemies.add(new Enemy2(325,150,this));
                enemies.add(new Enemy2(625,150,this));
                break;
            case 2:
                enemies.add(new Enemy2(250,150,this));
                enemies.add(new Enemy2(500,150,this));
                enemies.add(new Enemy2(750,150,this));
                enemies.add(new Enemy2(325,300,this));
                enemies.add(new Enemy2(625,300,this));
                break;
            default:
                this.level = 0;
                break;
        }
    }

    public void setView(TextView v){

        view = v;
    }
}
