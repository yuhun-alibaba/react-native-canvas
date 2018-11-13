//
//  CanvasViewManager.h
//  NativeCanvas
//
//  Created by sam on 2018/11/13.
//  Copyright © 2018 Facebook. All rights reserved.
//
#import <React/RCTViewManager.h>
#import "CanvasView.h"

@interface CanvasViewManager : RCTViewManager

@property (nonatomic, strong) CanvasView *canvas;

@end
