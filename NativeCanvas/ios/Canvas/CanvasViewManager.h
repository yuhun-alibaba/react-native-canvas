//
//  CanvasViewManager.h
//  NativeCanvas
//
//  Created by sam on 2018/11/13.
//  Copyright © 2018 Facebook. All rights reserved.
//
#import <React/RCTViewManager.h>
#import "CanvasView.h"
#import "CanvasAPI.h"


@interface CanvasViewManager : RCTViewManager

+ (void)setCanvasView:(NSString *)tag canvas:(CanvasView *)canvas;
+ (CanvasView *)getCanvasView:(NSString *)tag;
+ (void)removeCanvasView:(NSString *)tag;

@end
