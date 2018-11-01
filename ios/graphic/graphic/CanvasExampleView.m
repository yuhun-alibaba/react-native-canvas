//
//  CanvasExampleView.m
//  graphic
//
//  Created by sam on 2018/11/1.
//  Copyright © 2018 sam. All rights reserved.
//

#import "CanvasExampleView.h"

@implementation CanvasExampleView

- (void)drawRect:(CGRect)rect {
    CanvasRenderingContext2D *context = [[CanvasRenderingContext2D alloc] initWithContext:UIGraphicsGetCurrentContext()];

    CGColorSpaceRef colorSpaceRef = CGColorSpaceCreateDeviceRGB();
    CGColorRef strokeStyle = CGColorCreate(colorSpaceRef, (CGFloat[]){0.0f,1.0f,1.0f,1.0f});
    CGColorRef fillStyle = CGColorCreate(colorSpaceRef, (CGFloat[]){0.4f, 0.4f, 0.4f, 1.0f});
    
    context.strokeStyle = fillStyle;
    context.fillStyle = strokeStyle;
    
    [context fillRect: 50.0 y:50.0 width:100.0 height:100.0];
    
    context.strokeStyle = strokeStyle;
    context.fillStyle = fillStyle;
    
    [context arc:150.0 y:150.0 radius:50.0 startAangle:0 endAngle:M_PI * 2 anticlockwise:1];
    
}

@end
