//
//  CanvasRenderingContext2D.m
//  graphic
//
//  Created by sam on 2018/11/1.
//  Copyright © 2018 sam. All rights reserved.
//
//  API ref: https://developer.mozilla.org/zh-CN/docs/Web/API/CanvasRenderingContext2D
//

#import "CanvasRenderingContext2D.h"

@implementation CanvasRenderingContext2D

-(id)initWithContext:(CGContextRef)context{
    _context = context;
    return self;
}

#pragma 绘制矩形
-(void)clearRect:(CGFloat)x y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height{
    CGRect rect = CGRectMake(x, y, width, height);
    CGContextClearRect(_context, rect);
}
-(void)fillRect:(CGFloat)x y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height{
    CGRect rect = CGRectMake(x, y, width, height);
    CGContextAddRect(_context, rect);
    CGContextDrawPath(_context, kCGPathFill);
}
-(void)strokeRect:(CGFloat)x y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height{
    CGRect rect = CGRectMake(x, y, width, height);
    CGContextAddRect(_context, rect);
    CGContextDrawPath(_context, kCGPathStroke);
}

#pragma 绘制文本
-(void)fillText:(NSString *)text x:(CGFloat)x y:(CGFloat)y{
    
}
-(void)strokeText:(NSString *)text x:(CGFloat)x y:(CGFloat)y{
    
}
-(void)measureText:(NSString *)text{
    
}

#pragma 线型
-(void)setLineWidth:(CGFloat)lineWidth{
    if (lineWidth == _lineWidth) {
        return;
    }
    _lineWidth = lineWidth;
    CGContextSetLineWidth(_context, _lineWidth);
}
-(void)setLineCap:(CGLineCap)lineCap{
    if (lineCap == _lineCap) {
        return;
    }
    _lineCap = lineCap;
    CGContextSetLineCap(_context, _lineCap);
}
-(CanvasCGFloatArray)getLineDash{
    return _lineDash;
}
-(void)setLineDash:(CanvasCGFloatArray)lineDash{
    if (lineDash.array == _lineDash.array) {
        return;
    }
    if (_lineDash.array) {
        free(_lineDash.array);
    }
    _lineDash = lineDash;
    if (lineDash.count) {
        CGContextSetLineDash(_context, 0, lineDash.array, lineDash.count);
    }
}
-(void)setLineJoin:(CGLineJoin)lineJoin{
    if (lineJoin == _lineJoin) {
        return;
    }
    _lineJoin = lineJoin;
    CGContextSetLineJoin(_context, _lineJoin);
}

#pragma 填充与描边
-(void)setFillStyle:(CGColorRef)fillStyle{
    if (fillStyle == _fillStyle) {
        return;
    }
    CGColorRelease(_fillStyle);
    _fillStyle = CGColorRetain(fillStyle);
    CGContextSetFillColorWithColor(_context, _fillStyle);
}
-(void)setStrokeStyle:(CGColorRef)strokeStyle{
    if (strokeStyle == _strokeStyle) {
        return;
    }
    CGColorRelease(_strokeStyle);
    _strokeStyle = CGColorRetain(strokeStyle);
    CGContextSetStrokeColorWithColor(_context, _strokeStyle);
}

#pragma 渐变和图案
-(void)createLinearGradient:(CGFloat)x0 y0:(CGFloat)y0 x1:(CGFloat)x1 y1:(CGFloat)y1{
    
}
-(void)createRadialGradient:(CGFloat)x0 y0:(CGFloat)y0 r0:(CGFloat)r0 x1:(CGFloat)x1 y1:(CGFloat)y1 r1:(CGFloat)r1{
    
}
-(void)createPattern{
    
}

#pragma 路径
-(void)beginPath{
    CGContextBeginPath(_context);
}
-(void)closePath{
    CGContextClosePath(_context);
}
-(void)moveTo:(CGFloat)x y:(CGFloat)y{
    CGContextMoveToPoint(_context, x, y);
}
-(void)lineTo:(CGFloat)x y:(CGFloat)y{
    CGContextAddLineToPoint(_context, x, y);
}
-(void)bezierCurveTo:(CGFloat)cp1x cp1y:(CGFloat)cp1y cp2x:(CGFloat)cp2x cp2y:(CGFloat)cp2y x:(CGFloat)x y:(CGFloat)y{
    CGContextAddCurveToPoint(_context, cp1x, cp1y, cp2x, cp2y, x, y);
}
-(void)quadraticCurveTo:(CGFloat)cpx cpy:(CGFloat)cpy x:(CGFloat)x y:(CGFloat)y{
    CGContextAddQuadCurveToPoint(_context, cpx, cpy, x, y);
}
-(void)arc:(CGFloat)x y:(CGFloat)y radius:(CGFloat)radius startAangle:(CGFloat)startAngle endAngle:(CGFloat)endAangle anticlockwise:(int)anticlockwise{
    CGContextAddArc(_context, x, y, radius, startAngle, endAangle, anticlockwise);
}
-(void)arcTo:(CGFloat)x1 y1:(CGFloat)y1 x2:(CGFloat)x2 y2:(CGFloat)y2 radius:(CGFloat)radius{
    CGContextAddArcToPoint(_context, x1, y1, x2, y2, radius);
}
-(void)rect:(CGFloat)x y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height{
    CGRect rect = CGRectMake(x, y, width, height);
    CGContextAddRect(_context, rect);
}

#pragma 绘制路径
-(void)fill{
    CGContextDrawPath(_context, kCGPathFill);
}
-(void)stroke{
    CGContextDrawPath(_context, kCGPathStroke);
}
-(void)drawFocusIfNeeded{
    
}
-(void)scrollPathIntoView{
    
}
-(void)clip{
    CGContextClip(_context);
}
-(void)clip:(NSString*)fillRule{
    if ([fillRule isEqualToString:@"nonzero"]) {
        [self clip];
    } else if ([fillRule isEqualToString:@"evenodd"]) {
        CGContextEOClip(_context);
    }
}
-(void)isPointInPath{
    
}
-(void)isPointInStroke{
    
}

#pragma 变换
-(void)rotate:(CGFloat)angle{
    CGContextRotateCTM(_context, angle);
}
-(void)scale:(CGFloat)x y:(CGFloat)y{
    CGContextScaleCTM(_context, x, y);
}
-(void)translate:(CGFloat)x y:(CGFloat)y{
    CGContextTranslateCTM(_context, x, y);
}
-(void)transform:(CGFloat)a b:(CGFloat)b c:(CGFloat)c d:(CGFloat)d e:(CGFloat)e f:(CGFloat)f{
    CGContextConcatCTM(_context, CGAffineTransformMake(a, b, c, d, e, f));
}
-(void)setTransform:(CGFloat)a b:(CGFloat)b c:(CGFloat)c d:(CGFloat)d e:(CGFloat)e f:(CGFloat)f{
    [self transform:1 b:0 c:0 d:1 e:0 f:0];
    [self transform:a b:b c:c d:d e:e f:f];
}

#pragma 绘制图像
-(void)drawImage{
    
}

#pragma 像素控制
-(void)createImageData{
    
}
-(void)getImageData{
    
}
-(void)putImageData{
    
}

#pragma canvas 状态
-(void)save{
    CGContextSaveGState(_context);
}
-(void)restore{
    CGContextRestoreGState(_context);
}


@end
