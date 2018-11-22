package com.example.sam.graphic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.sam.graphic.canvas.CanvasView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    CanvasView canvas = new CanvasView(this);

    ArrayList actions = new ArrayList<HashMap>();
    HashMap d = new HashMap();
    d.put("method", new String("drawColor:int"));
    d.put("arguments", new Object[]{Color.BLUE});
    actions.add(d);

    canvas.setActions(actions);

    setContentView(canvas);
  }
}
