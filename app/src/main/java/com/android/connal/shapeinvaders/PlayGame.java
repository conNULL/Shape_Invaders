package com.android.connal.shapeinvaders;

import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;


public class PlayGame extends ActionBarActivity{

    GScreen g;
    Button left, right, ss;
    TextView hbar, sbar, lbar;
    Player player;
    boolean run = true;
    boolean started = false;
    final static float TRANSP = 0.15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        g = (GScreen) findViewById(R.id.sv);
        left = (Button) findViewById(R.id.left);
        right = (Button) findViewById(R.id.right);
        hbar = (TextView) findViewById(R.id.hbar);
        sbar = (TextView) findViewById(R.id.sbar);
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
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

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
        } else {
            started = true;
            unmark();
            startGame();
        }
    }

    public void update_UI(){
        updateHealth();
        updateScore();
    }
    public void updateHealth(){

        hbar.setText("Health: " + String.valueOf(g.player.getHealth()));
        }

    public void updateScore(){
        sbar.setText("Score: " + String.valueOf(g.player.getScore()) + "  ");
    }


    public void shoot(View view){

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
        shoot.setText("SHOOT");
        pause.setAlpha(1);
        left.setAlpha(1);
        right.setAlpha(1);
        shoot.setAlpha(1);
    }
}
