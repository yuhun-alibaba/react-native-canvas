package com.example.sam.graphic;

import java.util.ArrayList;

import android.util.Log;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by sam on 2018/11/21.
 */

class CanvasExampleView extends View{

    private static final String TAG = "CanvasExampleView";
    private ArrayList<PointF> graphics = new ArrayList<PointF>();

    Paint paint;
    PointF point;

    public CanvasExampleView(Context context) {
        super(context);
        paint = new Paint(); //设置一个笔刷大小是3的黄色的画笔
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        graphics.add(new PointF(event.getX(),event.getY()));
        invalidate(); //重新绘制区域
        return true;
    }

    //在这里我们将测试canvas提供的绘制图形方法
    @Override
    protected void onDraw(Canvas canvas) {
        Log.v(TAG, "onDraw");
        super.onDraw(canvas);
        canvas.drawColor(Color.BLUE);
        for (PointF point : graphics) {
            canvas.drawPoint(point.x, point.y, paint);
        }

    }
}
