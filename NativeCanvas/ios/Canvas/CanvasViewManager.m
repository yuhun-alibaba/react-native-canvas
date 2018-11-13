//
//  CanvasViewManager.m
//  NativeCanvas
//
//  Created by sam on 2018/11/13.
//  Copyright © 2018 Facebook. All rights reserved.
//

#import "CanvasViewManager.h"

@implementation CanvasViewManager

RCT_EXPORT_MODULE()

-(UIView *)view
{
  _canvas = [CanvasView new];
  return _canvas;
}

RCT_CUSTOM_VIEW_PROPERTY(actions, NSMutableArray*, CanvasView){
  NSMutableArray *actions = (NSMutableArray *)[RCTConvert NSArray:json] ?: defaultView.actions;
  [self.canvas invalidate];
  [self.canvas setActions:actions];
}

@end
