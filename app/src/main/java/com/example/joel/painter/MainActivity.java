package com.example.joel.painter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    DrawView myDrawView;
    //ToolPickerView myToolPickerView;


    public static int drawColor=0xff000000;
    public static int currentTool=0;
    public String filePath="/";
    private String fileName="/pic001.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find DrawView to access props
        myDrawView = (DrawView) findViewById(R.id.drawview);
        //myToolPickerView = (ToolPickerView) findViewById(R.id.drawview);

        currentTool=0;
        drawColor=(int) Color.BLACK;


        filePath= Environment.getExternalStorageDirectory().toString();

        TextView lblPath = (TextView) findViewById(R.id.lblPath);
        lblPath.setText(filePath);

        TextView txtFileName= (TextView) findViewById(R.id.txtFileName);
        txtFileName.setText(fileName);

    }

    public void newClicked(View view)
    {
        currentTool=0;
        ToolPickerView myToolPickerView = (ToolPickerView) findViewById(R.id.toolpickerview);
        myToolPickerView.currentTool=0;
        myToolPickerView.invalidate();

        drawColor=0xff000000;
        ColorPickerView myColorPickerView = (ColorPickerView) findViewById(R.id.colorpickerview);
        myColorPickerView.colorUsed=0;
        myColorPickerView.invalidate();


        myDrawView.clearBitmap();
        myDrawView.invalidate();

    }

    public void saveClicked(View view)
    {
        TextView txtFileName= (TextView) findViewById(R.id.txtFileName);
        fileName=txtFileName.getText().toString();

        OutputStream outStream = null;
        File file = new File(filePath, fileName);
        try
        {
            outStream = new FileOutputStream(file);
            //bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            DrawView.myBitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);

            outStream.flush();
            outStream.close();

            Toast.makeText(getApplicationContext(), "Saved as " + filePath + fileName, Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();

        }



    }

    public void openClicked(View view)
    {
        myDrawView.myBitmap = BitmapFactory.decodeFile(filePath+fileName);
        myDrawView.invalidate();
    }

}
