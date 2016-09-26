package com.example.joel.painter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


import com.example.joel.painter.ColorPickerView;

/**
 * Created by Joel on 20/05/2016.
 */
public class DrawView extends View implements View.OnTouchListener {

    private float touchX;
    private float touchY;
    private float moveX;
    private float moveY;
    private float distX;
    private float distY;
    private boolean touchHappening;

    public static Bitmap myBitmap;


    public DrawView(Context context) {

        super(context);
        initDraw();

    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDraw();
    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initDraw();
    }

    public void initDraw() {
        setOnTouchListener(this);

        this.setBackgroundColor(0xfff0f0f0);

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        myBitmap = Bitmap.createBitmap(1200, 1000, conf);

        this.clearBitmap();
    }

    public void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        //Copy bitmap to canvas
        canvas.drawBitmap(myBitmap, 0, 0, myPaint);
        Canvas myBitmapCanvas = new Canvas(myBitmap);


        if (touchHappening) {
            myPaint.setStyle(Paint.Style.FILL_AND_STROKE);

            //Tool 1 - Point
            if (MainActivity.currentTool == 0) {
                //Show position
                myPaint.setColor(Color.RED);
                myPaint.setAlpha(255);

                canvas.drawCircle(moveX, moveY, 5, myPaint);

            }
            //Tool 2 - Line
            if (MainActivity.currentTool == 1) {
                //Show position
                myPaint.setColor(Color.RED);
                myPaint.setAlpha(255);

                canvas.drawCircle(touchX, touchY, 5, myPaint);
                canvas.drawCircle(moveX, moveY, 5, myPaint);
                canvas.drawLine(touchX, touchY, moveX, moveY, myPaint);

            }
            //Tool 3 - Circle
            if (MainActivity.currentTool == 2) {
                //Show position
                myPaint.setColor(Color.RED);
                myPaint.setAlpha(255);

                canvas.drawCircle(touchX, touchY, 5, myPaint);
                canvas.drawCircle(moveX, moveY, 5, myPaint);
                canvas.drawLine(touchX, touchY, moveX, moveY, myPaint);

                distX = moveX - touchX;
                if (distX < 0) distX = -distX;
                distY = moveY - touchY;
                if (distY < 0) distY = -distY;

                float rad=(float)Math.sqrt(distX*distX+distY*distY);

                myPaint.setAlpha(50);

                canvas.drawCircle(touchX, touchY, rad, myPaint);
                /*
                if (distY > distX)
                    canvas.drawCircle(touchX, touchY, distY, myPaint);
                else
                    canvas.drawCircle(touchX, touchY, distX, myPaint);
                    */
            }
            //Tool 4 - Rect
            if (MainActivity.currentTool == 3) {
                //Show position
                myPaint.setColor(Color.RED);
                myPaint.setAlpha(255);

                canvas.drawCircle(touchX, touchY, 5, myPaint);
                canvas.drawCircle(moveX, moveY, 5, myPaint);
                canvas.drawLine(touchX, touchY, moveX, moveY, myPaint);

                myPaint.setAlpha(50);

                float fromX=touchX;
                float toX=moveX;
                float fromY=touchY;
                float toY=moveY;
                if (touchX>moveX) {
                    fromX = moveX;
                    toX = touchX;
                }
                if (touchY>moveY) {
                    fromY = moveY;
                    toY = touchY;
                }
                canvas.drawRect(fromX, fromY, toX, toY, myPaint);
            }


        }
        /*
        if (moveY-touchY>moveX-touchX)
            canvas.drawCircle(touchX,touchY,moveY-touchY, myPaint);
        else
            canvas.drawCircle(touchX,touchY,moveX-touchX, myPaint);
            */





/*
        ColorPickerView myColorPickerView = (ColorPickerView) findViewById(R.id.colorpickerview);
  //      int drawColor=myColorPickerView.getColorUsed();

        int drawColor;

        if (myColorPickerView!=null)
        {
            drawColor=0xffff00ff;//myColorPickerView.getColorUsed();
        }
        else
            drawColor=0xff000000;

//        int drawColor=0xff000000;
*/


//        myBitmapCanvas.drawCircle(touchX,touchY,10, myPaint);


    }

    @Override
    public boolean onTouch(View view, MotionEvent mEvent) {
        Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        myPaint.setColor(MainActivity.drawColor);

        myPaint.setAlpha(255);
        Canvas myBitmapCanvas = new Canvas(myBitmap);

        switch (mEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchHappening = true;
                touchX = mEvent.getX();
                touchY = mEvent.getY();
                moveX = mEvent.getX();
                moveY = mEvent.getY();
                distX = 0;
                distY = 0;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touchHappening = true;

                //Actual drawing

                //Tool 1 - Point
                if (MainActivity.currentTool == 0) {
                    //myBitmapCanvas.drawPoint(moveX, moveY, myPaint);
                    myBitmapCanvas.drawCircle(moveX, moveY, 5, myPaint);
                }

                moveX = mEvent.getX();
                moveY = mEvent.getY();
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchHappening = false;

                //Actual drawing

                //Tool 2 - Line
                if (MainActivity.currentTool == 1) {
                    myBitmapCanvas.drawLine(touchX, touchY, moveX, moveY, myPaint);
                }
                //Tool 3 - Circle
                if (MainActivity.currentTool == 2) {
                    float rad=(float)Math.sqrt(distX*distX+distY*distY);
                    myBitmapCanvas.drawCircle(touchX, touchY, rad, myPaint);

                    /*
                    if (distY > distX)
                        myBitmapCanvas.drawCircle(touchX, touchY, distY, myPaint);
                    else
                        myBitmapCanvas.drawCircle(touchX, touchY, distX, myPaint);
                        */
                }
                //Tool 4 - Rect
                if (MainActivity.currentTool==3) {
                    float fromX = touchX;
                    float toX = moveX;
                    float fromY = touchY;
                    float toY = moveY;
                    if (touchX > moveX) {
                        fromX = moveX;
                        toX = touchX;
                    }
                    if (touchY > moveY) {
                        fromY = moveY;
                        toY = touchY;
                    }
                    myBitmapCanvas.drawRect(fromX, fromY, toX, toY, myPaint);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
            default:
                break;
        }
        return true;
    }

    public void clearBitmap() {
        Paint myPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        myPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        myPaint.setColor(Color.WHITE);
        myPaint.setAlpha(255);
        Canvas myBitmapCanvas = new Canvas(myBitmap);

        //myBitmapCanvas.drawRect(0, 0, myBitmapCanvas.getWidth(), myBitmapCanvas.getHeight(), myPaint);
        myPaint.setColor(0xffa3bcd1);
        myBitmapCanvas.drawRect(0,0,myBitmap.getWidth(), myBitmap.getHeight(),myPaint);

    }
}