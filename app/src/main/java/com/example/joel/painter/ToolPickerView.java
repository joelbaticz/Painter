package com.example.joel.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by CiccMicc on 20/05/2016.
 */
public class ToolPickerView extends View implements View.OnTouchListener
{

    public int currentTool=0;

    public ToolPickerView(Context context) {
        super(context);
        initTools();
    }

    public ToolPickerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTools();
    }

    public ToolPickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initTools();
    }

    public void initTools() {
        setOnTouchListener(this);

        this.setBackgroundColor(0xff525f6a);
    }

    public void drawButton(float x, float y, float width, float height, Canvas canvas, Paint myPaint)
    {


        //myPaint.setColor(0xffe0e0e0);
        //canvas.drawRect(x,y,x+width,y+height, myPaint);

        myPaint.setAlpha(50);

        myPaint.setColor(0xffffffff);
        canvas.drawLine(x,y,x+width,y,myPaint);
        //canvas.drawLine(x,y+1,x+width,y+1,myPaint);
        canvas.drawLine(x,y,x,y+height,myPaint);

        myPaint.setColor(0xff000000);
        //canvas.drawLine(x,y+height-1,x+width,y+height-1,myPaint);
        canvas.drawLine(x,y+height,x+width,y+height,myPaint);
        canvas.drawLine(x+width,y,x+width,y+height,myPaint);

    }

    public void drawHighlight(float x, float y, float width, float height, Canvas canvas, Paint myPaint)
    {


        myPaint.setAlpha(255);

        myPaint.setColor(0xffff9600);
        canvas.drawLine(x,y+1,x+width,y+1,myPaint);
        canvas.drawLine(x,y+2,x+width,y+2,myPaint);

        myPaint.setColor(0xffff9600);
        canvas.drawLine(x,y+height-2,x+width,y+height-2,myPaint);
        canvas.drawLine(x,y+height-1,x+width,y+height-1,myPaint);

    }

    public void onDraw(Canvas canvas) {

        Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //currentTool=MainActivity.currentTool;

        myPaint.setAlpha(255);

        for (int i = 0; i < 4; i++)
        {
            drawButton((i*100),0,99,99,canvas, myPaint);
        }

        //Tool 1 - Point
        myPaint.setAlpha(255);
        if (currentTool==0) myPaint.setColor(0xffff9600);
        else myPaint.setColor(0xffbdbfc1);
        canvas.drawRect(48,48,52,52,myPaint);

        //Tool 2 - Line
        myPaint.setAlpha(255);
        if (currentTool==1) myPaint.setColor(0xffff9600);
        else myPaint.setColor(0xffbdbfc1);
        canvas.drawLine(100+20,20,100+80,80,myPaint);
        canvas.drawLine(100+21,20,100+81,80,myPaint);

        //Tool 3 - Cricle
        myPaint.setAlpha(255);
        if (currentTool==2) myPaint.setColor(0xffff9600);
        else myPaint.setColor(0xffbdbfc1);
        canvas.drawCircle(200+50,50,30,myPaint);

        //Tool 4 - Rectangle
        myPaint.setAlpha(255);
        if (currentTool==3) myPaint.setColor(0xffff9600);
        else myPaint.setColor(0xffbdbfc1);
        canvas.drawRect(300+20,20,300+80,80,myPaint);

        //Highlight
        drawHighlight((currentTool*100),0,99,99,canvas,myPaint);



    }

    @Override
    public boolean onTouch(View view, MotionEvent mEvent)
    {
        switch(mEvent.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                int newTool=(int)mEvent.getX() / 100;
                if (newTool<4)
                {
                    currentTool = newTool;
                    MainActivity.currentTool = newTool;
                    invalidate();
                }
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
}