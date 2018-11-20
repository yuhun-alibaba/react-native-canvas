//
//  CanvasViewManager.m
//  NativeCanvas
//
//  Created by sam on 2018/11/13.
//  Copyright © 2018 Facebook. All rights reserved.
//

#import "CanvasViewManager.h"

static NSMutableDictionary<NSString *, CanvasView *> *canvasViews;


@implementation CanvasViewManager

RCT_EXPORT_MODULE();

- (UIView *)view
{
    CanvasView *canvas = [CanvasView new];
    static dispatch_once_t onceToken;
    dispatch_once(&onceToken, ^{
        canvasViews = [NSMutableDictionary new];
    });
    return canvas;
}

RCT_CUSTOM_VIEW_PROPERTY(nativeID, NSString, CanvasView)
{
    if ([CanvasViewManager getCanvasView:json]) {
        return;
    }
    [CanvasViewManager setCanvasView:json canvas:view];
}

RCT_CUSTOM_VIEW_PROPERTY(actions, NSMutableArray *, CanvasView)
{
    NSMutableArray *convertedActions = (NSMutableArray *)[RCTConvert NSArray:json] ?: defaultView.actions;
    if (convertedActions.count == 0) {
        return;
    }
    [view setActions:convertedActions];
    [view invalidate];
}

#pragma 静态方法

+ (void)setCanvasView:(NSString *)tag canvas:(CanvasView *)canvas
{
    canvasViews[tag] = canvas;
}

+ (CanvasView *)getCanvasView:(NSString *)tag
{
    return canvasViews[tag] ?: nil;
}

+ (void)removeCanvasView:(NSString *)tag
{
    [canvasViews removeObjectForKey:tag];
}

@end
