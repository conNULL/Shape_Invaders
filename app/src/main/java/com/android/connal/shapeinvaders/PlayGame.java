package com.android.connal.shapeinvaders;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class PlayGame extends ActionBarActivity{

    gScreen g;
    Button left, right, ss;
    TextView hbar, sbar, lbar;
    Player player;
    boolean run = true;
    boolean started = false;
    final static float TRANSP = 0.15f;
    //working
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        g = (gScreen) findViewById(R.id.sv);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
        hbar = (TextView) findViewById(R.id.hbar);
        sbar = (TextView) findViewById(R.id.sbar);
        lbar = (TextView) findViewById(R.id.lbar);
        left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN)
                    left.setPressed(true);
                else if(event.getAction() == MotionEvent.ACTION_UP)
                    left.setPressed(false);

                return true;
            }

        });

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    right.setPressed(true);
                else if (event.getAction() == MotionEvent.ACTION_UP)
                    right.setPressed(false);

                return true;
            }
        });

        //startGame();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_play_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void move() {

        if(left.isPressed())
            g.moveLeft();
        if(right.isPressed())
            g.moveRight();

    }

    public void startGame(){


        new Thread(new Runnable() {
            @Override
            public void run() {

                //TextView habr = (TextView) findViewById(R.id.hbar);
                while(run){

                    move();
                    g.update();
                    g.checkCollisions();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            update_UI();
                        }
                    });
                    g.post(new Runnable() {
                        @Override
                        public void run() {

                            g.draw();
                        }
                    });

                    try {
                        Thread.sleep(20);
                    }catch(InterruptedException e){

                    }
                }
            }
        }).start();

    }

    public void ss(View view){

        if(started) {
            if (run) {
                mark();
                run = false;
            } else {
                unmark();
                run = true;
                startGame();
            }
        }
    }

    public void update_UI(){
        updateHealth();
        updateScore();
        updateLives();
    }
    public void updateHealth(){

        hbar.setText("Health: " + String.valueOf(g.p.getHealth()));
        }
    public void updateScore(){
        sbar.setText("Score: " + String.valueOf(g.p.getScore()) + "  ");
    }

    public void updateLives(){
        lbar.setText("Lives: " + String.valueOf(g.p.getLives()));
    }
    public void shoot(View view){

        if(!started){
            started = true;
            unmark();
            startGame();
        }
        g.pShoot();
    }

    public void onPause(){

        super.onPause();
        run = false;
    }
    public void onResume(){
        super.onResume();
        run = true;
    }
    public void unmark(){

        Button left = (Button)findViewById(R.id.left);
        Button right = (Button)findViewById(R.id.right);
        Button shoot = (Button)findViewById(R.id.shoot);
        Button pause = (Button)findViewById(R.id.ss);
        //pause.getBackground().setAlpha(transp);
        //left.getBackground().setAlpha(transp);
        //right.getBackground().setAlpha(transp);
        //shoot.getBackground().setAlpha(transp);
        pause.setAlpha(TRANSP);
        left.setAlpha(TRANSP);
        right.setAlpha(TRANSP);
        shoot.setAlpha(TRANSP);
    }
    public void mark(){
        Button left = (Button)findViewById(R.id.left);
        Button right = (Button)findViewById(R.id.right);
        Button shoot = (Button)findViewById(R.id.shoot);
        Button pause = (Button)findViewById(R.id.ss);
        pause.setText("PAUSE/RESUME");
        left.setText("LEFT");
        right.setText("RIGHT");
        shoot.setText("SHOOT");/*
        pause.getBackground().setAlpha(255);
        left.getBackground().setAlpha(255);
        right.getBackground().setAlpha(255);
        shoot.getBackground().setAlpha(255);
        */
        pause.setAlpha(1);
        left.setAlpha(1);
        right.setAlpha(1);
        shoot.setAlpha(1);
    }
}
