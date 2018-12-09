package com.graphic.canvas;


import java.util.Stack;

/**
 * Created by sam on 2018/12/1.
 * <p>
 * Ref:
 * https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/save
 * https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D/restore
 * <p>
 * 属性查找: 自底向上查找
 */

public class CanvasDrawingStateManager {
  private final Stack<CanvasDrawingState> states = new Stack();

  public CanvasDrawingStateManager() {
  }

  public CanvasDrawingState getCurrentState() {
    return states.peek();
  }

  public void reset() {
    // 每次清除前，栈中最多只有一个 state
    states.clear();
    setUpNewState();
  }

  public void save() {
    setUpNewState(getCurrentState());
  }

  public void restore() {
    states.pop();
  }

  private void setUpNewState() {
    states.push(new CanvasDrawingState());
  }

  private void setUpNewState(CanvasDrawingState currentState) {
    states.push(new CanvasDrawingState(currentState));
  }

}
