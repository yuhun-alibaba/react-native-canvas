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
  return [CanvasView new];
}

RCT_CUSTOM_VIEW_PROPERTY(actions, NSMutableArray*, CanvasView){
  NSMutableArray *actions = (NSMutableArray *)[RCTConvert NSArray:json] ?: defaultView.actions;
  if (actions.count == 0) {
    return;
  }
  [view invalidate];
  [view setActions:actions];
}

@end
