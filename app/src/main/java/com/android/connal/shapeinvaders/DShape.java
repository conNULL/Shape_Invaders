package com.android.connal.shapeinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

/**
 * Created by Connal on 2015-05-06.
 */
public class DShape extends View {

    private ShapeDrawable s;
    public DShape(Context context){
        super(context);

        int x = 500;
        int y = 500;
        int width = 50;
        int height = 50;

        s = new ShapeDrawable(new OvalShape());
        s.getPaint().setColor(getResources().getColor(R.color.blue));
        s.setBounds(x, y, x + width, y + height);
    }

    protected void onDraw(Canvas c){

        s.draw(c);
    }
}
