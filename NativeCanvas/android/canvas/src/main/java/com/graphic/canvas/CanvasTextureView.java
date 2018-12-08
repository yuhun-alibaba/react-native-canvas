package com.graphic.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Region;
import android.graphics.SurfaceTexture;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sam on 2018/12/3.
 * <p>
 * TextureView 单独线程渲染
 */

public class CanvasTextureView extends TextureView {
  private final String TAG = "CanvasTextureView";
  private static final CanvasMethodDelegate delegate = new CanvasMethodDelegate(CanvasRenderingContext2D.class);
  private final CanvasRenderingContext2D renderingContext2D = new CanvasRenderingContext2D();
  private ArrayList<HashMap> actions = new ArrayList();
  private Surface mSurface;
  private Integer mBackgroundColor;

  public CanvasTextureView(Context context) {
    super(context);
    setOpaque(false);
    setDevicePixelRatio(context);
    setSurfaceTextureListener(new SurfaceTextureListener() {
      @Override
      public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        mSurface = new Surface(surface);
        drawOutput();
      }

      @Override
      public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        surface.release();
        mSurface = null;
        return true;
      }

      @Override
      public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
      }

      @Override
      public void onSurfaceTextureUpdated(SurfaceTexture surface) {
      }
    });
  }

  /**
   * { "method": "", "arguments": [] };
   */
  private void runActions() {
    for (HashMap action : actions) {
      delegate.invoke(renderingContext2D, (String) action.get("method"), (Object[]) action.get("arguments"));
    }
  }

  private void setDevicePixelRatio(Context context) {
    renderingContext2D.setDevicePixelRatio(context.getResources().getDisplayMetrics().density);
  }

  private void drawCanvas(Canvas canvas) {
    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
    if (mBackgroundColor != null) {
      canvas.drawColor(mBackgroundColor);
    } else {
      canvas.drawColor(Color.rgb(255, 255, 255));
    }
    canvas.clipRect(0, 0, canvas.getWidth(), canvas.getHeight(), Region.Op.REPLACE);
    renderingContext2D.setCanvas(canvas);
    runActions();
  }

  public void setActions(ArrayList<HashMap> drawActions) {
    actions = new ArrayList(drawActions);
  }

  public void setBackgroundColor(Integer color) {
    mBackgroundColor = color;
  }

  public void drawOutput() {
    if (mSurface == null || !mSurface.isValid()) {
      return;
    }

    try {
      Canvas canvas = mSurface.lockCanvas(null);
      // 绘制逻辑
      drawCanvas(canvas);

      if (mSurface == null) {
        return;
      }

      mSurface.unlockCanvasAndPost(canvas);
    } catch (IllegalArgumentException | IllegalStateException e) {
      Log.w(TAG, "in Surface.unlockCanvasAndPost");
    }
  }
}

