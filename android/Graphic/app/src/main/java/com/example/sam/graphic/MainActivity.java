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

  private HashMap createAction(String method, Object[] arguments) {
    HashMap d = new HashMap();
    d.put("method", method);
    d.put("arguments", arguments);
    return d;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    CanvasView canvas = new CanvasView(this);

    ArrayList actions = new ArrayList<HashMap>();
//    actions.add(createAction((String)"beginPath", new Object[]{}));
//    actions.add(createAction((String)"moveTo:float:float", new Object[]{150,20}));
//    actions.add(createAction((String)"arcTo:float:float:float:float:float", new Object[]{150,100, 50,20, 30}));
//    actions.add(createAction((String)"lineTo:float:float", new Object[]{50, 20}));
//    actions.add(createAction((String)"stroke", new Object[]{}));


    actions.add(createAction((String)"beginPath", new Object[]{}));
//    actions.add(createAction((String)"setFillStyle:other", new Object[]{new int[]{255,255,0,0}}));
    actions.add(createAction((String)"arc:float:float:float:float:float", new Object[]{150,20, 5, 0, 360}));
    actions.add(createAction((String)"stroke", new Object[]{}));

    actions.add(createAction((String)"beginPath", new Object[]{}));
//    actions.add(createAction((String)"setFillStyle:other", new Object[]{new int[]{255,0,255,0}}));
    actions.add(createAction((String)"arc:float:float:float:float:float", new Object[]{150, 100, 5, 0, 360}));
    actions.add(createAction((String)"arc:float:float:float:float:float", new Object[]{50, 20, 5, 0, 360}));
    actions.add(createAction((String)"fill", new Object[]{}));

    canvas.setActions(actions);

    setContentView(canvas);
  }
}
