package com.graphic.canvas;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.view.TextureView;

/**
 * Created by sam on 2018/12/3.
 */

public class CanvasTextureView extends TextureView {
  private class TextureViewListener implements TextureView.SurfaceTextureListener {
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
      return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }
  }

  public CanvasTextureView(Context context) {
    super(context);
    setOpaque(false);
  }

}
