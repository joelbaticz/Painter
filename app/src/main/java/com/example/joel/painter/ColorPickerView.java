package com.example.joel.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Joel on 20/05/2016.
 */
public class ColorPickerView extends View implements View.OnTouchListener
{
    public ArrayList colors;
    public int colorUsed=0;

    public ColorPickerView(Context context)
    {
        super(context);
        initDraw();
    }

    public ColorPickerView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        initDraw();
    }

    public ColorPickerView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        initDraw();
    }

    public void initDraw()
    {
        setOnTouchListener(this);

        this.setBackgroundColor(Color.WHITE);

        colors=new ArrayList();

        colors.add(0xff000000);
        colors.add(0xff404040);
        colors.add(0xff808080);
        colors.add(0xffB0B0B0);
        colors.add(0xffffffff);


        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);
        colors.add(Color.CYAN);
        colors.add(0xffff9600);
        colors.add(0xff96ff00);
        colors.add(0xffff0096);

        for (int i=0;i<16;i++) {
            int nextGrayColor = (i) * 16;
            int nextColor = 0xff000000 | (nextGrayColor << 16) | (nextGrayColor << 8) | (nextGrayColor);
            colors.add(nextColor);
        }

    }

    public void onDraw(Canvas canvas)
    {

        Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        myPaint.setAlpha(255);

        for (int i=0;i< colors.size();i++)
        {

            myPaint.setColor((int)colors.get(i));
            canvas.drawRect(0+(i*50),0,50+(i*50),50,myPaint);
        }

        myPaint.setColor((int)colors.get(colorUsed) ^ 0xffffff);

        canvas.drawLine((float)colorUsed*50,0,(float)colorUsed*50+50,0,myPaint);
        canvas.drawLine((float)colorUsed*50+50,0,(float)colorUsed*50+50,50,myPaint);
        canvas.drawLine((float)colorUsed*50+50,50,(float)colorUsed*50,50,myPaint);
        canvas.drawLine((float)colorUsed*50,50,(float)colorUsed*50,0,myPaint);

        canvas.drawCircle(25+(colorUsed*50),25,7,myPaint);
    }


    @Override
    public boolean onTouch(View view, MotionEvent mEvent)
    {
        switch(mEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                colorUsed=(int)mEvent.getX() / 50;
                MainActivity.drawColor=(int)colors.get(colorUsed);
                invalidate();
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }

    public int getColorUsed()
    {
        return (int)colors.get(colorUsed);
    }



}
