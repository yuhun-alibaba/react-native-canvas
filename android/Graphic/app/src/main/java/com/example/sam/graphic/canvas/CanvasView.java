package com.example.sam.graphic.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.lang.Object;
import java.lang.reflect.Method;
import java.lang.NoSuchMethodException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by sam on 2018/11/21.
 */

public class CanvasView extends View {
    private final String TAG = "CanvasView";
    private ArrayList<HashMap> actions = new ArrayList<>();
    private JavaModuleWrapper module = new JavaModuleWrapper(this);
    Canvas canvas;
//    private CanvasRenderingContext2D context = new CanvasRenderingContext2D();

    public CanvasView(Context context) {
        super(context);

        canvas = null;
        HashMap d = new HashMap();
        d.put("method", new String("drawColor"));
        int color = Color.BLUE;
        d.put("arguments", new Object[]{ color });
        // d.put("arguments", new Object[]{});
        actions.add(d);
    }

    public void drawColor(){
        drawColor(Color.BLUE);
    }

    public void drawColor(int color){
        canvas.drawColor(color);
    }

    /**
     * { "method": "", "arguments": [] };
     */
    private void runAction(String method, Object[] arguments) {
        module.invoke(method, arguments);
    }

    private void runActions() {
        for (HashMap action : actions) {
            runAction((String) action.get("method"), (Object[]) action.get("arguments"));
        }
    }

    @Override
    protected void onDraw(Canvas c) {
        super.onDraw(c);
        if (canvas == null) {
            canvas = c;
        }
        runActions();
    }
}
