package com.android.connal.shapeinvaders;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by Connal on 2015-05-14.
 */
public class GScreen extends SurfaceView {

    private Canvas c;
    private SurfaceHolder sh;
    private Player player;
    LinkedList lasers;
    private LinkedList enemies = new LinkedList();
    private boolean created = false;
    private int level;
    private double screenWidth;
    Enemy enemy;
    Laser laser;
    TextView view;
    public GScreen(Context context) {
        super(context);
        init();
    }

    public GScreen(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GScreen(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public void init(){

        sh = getHolder();
        sh.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                Entity.setScreenDimensions(getWidth(), getHeight());
                Bitmap b = BitmapFactory.decodeResource(getResources(),
                        R.mipmap.blue_arrow);
                player = new Player(b, 12, getWidth()/12);
                lasers = new LinkedList();
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

    public Player getPlayer(){
        return player;
    }
    public void moveRight(){

        if(player.getX() + player.getSpeed() + player.getWidth() < getWidth())
             player.setX(player.getX() + player.getSpeed());
    }

    public void moveLeft(){

        if(player.getX() - player.getSpeed() > 0)
             player.setX(player.getX() - player.getSpeed());
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

        c.drawBitmap(player.getB(), player.getX(), player.getY(), null);
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

        player.shoot(lasers, this);
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
            ((Laser) lasers.get(i)).move(this, player, lasers);
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
            for (int i = 0; i < lasers.size(); i++) {
                Laser l = (Laser) lasers.get(i);

                if (l.isPlayer()) {
                    for (int j = 0; j < enemies.size(); j++) {
                        Enemy e = (Enemy) enemies.get(j);
                        if (isCollision(l, e)) {
                            e.hit(enemies, lasers, l, player);
                        }
                    }
                } else {

                    if (isCollision(l, player))
                        player.hit(l, lasers, player);
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

    public void setUnitLength(double width){
        this.screenWidth = width;
    }

    public void startLevel(int level){

        lasers.clear();
        player.resetShotCount();
        switch(level){

            case 1:
                enemies.add(new Enemy1(200,20, this));
                enemies.add(new Enemy1(400,20, this));
                enemies.add(new Enemy1(600,20, this));
                enemies.add(new Enemy1(800,20, this));
                enemies.add(new Enemy2(333,150,this));
                enemies.add(new Enemy2(667,150,this));
                break;
            case 2:
                enemies.add(new Enemy2(250,150,this));
                enemies.add(new Enemy2(500,150,this));
                enemies.add(new Enemy2(750,150,this));
                enemies.add(new Enemy2(333,300,this));
                enemies.add(new Enemy2(667,300,this));
                break;
            case 3:
                enemies.add(new Enemy3(333, 200, this));
                enemies.add(new Enemy3(667, 200, this));
                enemies.add(new Enemy3(500, 400, this));
                break;
            case 4:
                enemies.add(new Enemy1(200, 500, this));
                enemies.add(new Enemy1(350, 500, this));
                enemies.add(new Enemy1(500, 500, this));
                enemies.add(new Enemy1(650, 500, this));
                enemies.add(new Enemy1(800, 500, this));
                enemies.add(new Enemy2(250, 300, this));
                enemies.add(new Enemy2(500, 300, this));
                enemies.add(new Enemy2(750, 300, this));
                enemies.add(new Enemy3(250, 100, this));
                enemies.add(new Enemy3(500, 100, this));
                enemies.add(new Enemy3(750, 100, this));
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
