//
//  CanvasRenderingContext2D.m
//  graphic
//
//  Created by sam on 2018/11/1.
//  Copyright © 2018 sam. All rights reserved.
//
//  API ref:
//  https://developer.mozilla.org/zh-CN/docs/Web/API/CanvasRenderingContext2D
//

#import "CanvasRenderingContext2D.h"


@implementation CanvasRenderingContext2D

#pragma 初始化
- (CanvasRenderingContext2D *)init
{
    [self initOrResetProperty];
    return self;
}

- (void)initOrResetProperty
{
    _fillStyle = [UIColor blackColor].CGColor;
    _fontName = @"Helvetica";
    _fontSize = 10.0;
    _textBaseline = @(CanvasTextBaselineTop);
}

#pragma 绘制矩形
- (void)clearRect:(CGFloat)x
                y:(CGFloat)y
            width:(CGFloat)width
           height:(CGFloat)height
{
    CGRect rect = CGRectMake(x, y, width, height);
    CGContextClearRect(_context, rect);
}

- (void)fillRect:(CGFloat)x
               y:(CGFloat)y
           width:(CGFloat)width
          height:(CGFloat)height
{
    CGRect rect = CGRectMake(x, y, width, height);
    CGContextAddRect(_context, rect);
    CGContextDrawPath(_context, kCGPathFill);
}

- (void)strokeRect:(CGFloat)x
                 y:(CGFloat)y
             width:(CGFloat)width
            height:(CGFloat)height
{
    CGRect rect = CGRectMake(x, y, width, height);
    CGContextAddRect(_context, rect);
    CGContextDrawPath(_context, kCGPathStroke);
}

#pragma 设置文本样式
- (void)setFont:(NSDictionary *)font
{
    UIFont *f = [CanvasConvert UIFont:font];
    _fontName = f.fontName;
    _fontSize = f.pointSize;

    CGFontRef ff = CGFontCreateWithFontName((__bridge CFStringRef)_fontName);
    CGContextSetFont(_context, ff);
    CGContextSetFontSize(_context, _fontSize);
    CGFontRelease(ff);
}

- (void)setTextAlign:(NSString *)textAlign
{
    NSTextAlignment align = [CanvasConvert NSTextAlignment:textAlign];
    _textAlign = align;
}

- (CGFloat)textHorizontalOffset:(NSString *)text:(NSTextAlignment)textAlign
{
    if (textAlign == NSTextAlignmentLeft || textAlign == NSTextAlignmentNatural) {
        return 0;
    }
    CGSize textSize = [self measureText:text];

    if (textAlign == NSTextAlignmentRight) {
        return -textSize.width;
    } else if (textAlign == NSTextAlignmentCenter) {
        return -(textSize.width / 2);
    } else {
        return 0;
    }
}

- (CGFloat)textVerticalOffset:(UIFont *)font:(NSNumber *)textBaseLine
{
    if (textBaseLine.integerValue == CanvasTextBaselineTop) { // top
        return 0;
    }

    CGFloat lineHeight = font.lineHeight;

    if (textBaseLine.integerValue == CanvasTextBaselineBottom) { // bottom
        return -lineHeight;
    } else if (textBaseLine.integerValue == CanvasTextBaselineMiddle) { // middle
        return -(lineHeight / 2);
    } else {
        return 0;
    }
}

- (void)setTextBaseline:(NSString *)textBaseline
{
    _textBaseline = [CanvasConvert TextBaselineConvert:textBaseline];
}

#pragma 绘制文本
- (void)drawText:(NSString *)text x:(CGFloat)x y:(CGFloat)y
{
    NSMutableParagraphStyle *textStyle = NSMutableParagraphStyle.defaultParagraphStyle.mutableCopy;
    textStyle.alignment = _textAlign;
    UIColor *color = [UIColor colorWithCGColor:_fillStyle];
    UIFont *font = [UIFont fontWithName:_fontName size:_fontSize];
    if (!font) {
        font = [UIFont systemFontOfSize:_fontSize];
    }

    CGFloat horizontalOffset = [self textHorizontalOffset:text:_textAlign];
    CGFloat verticalOffset = [self textVerticalOffset:font:_textBaseline];

    NSDictionary *textFontAttributes = @{
        NSFontAttributeName : font,
        NSForegroundColorAttributeName : color,
        NSParagraphStyleAttributeName : textStyle
    };
    [text drawAtPoint:CGPointMake(x + horizontalOffset, y + verticalOffset)
        withAttributes:textFontAttributes];
}

- (void)fillText:(NSString *)text x:(CGFloat)x y:(CGFloat)y
{
    CGContextSetTextPosition(_context, x, y);
    CGContextSetTextDrawingMode(_context, kCGTextFill);

    [self drawText:text x:x y:y];
}

- (void)strokeText:(NSString *)text x:(CGFloat)x y:(CGFloat)y
{
    CGContextSetTextPosition(_context, x, y);
    CGContextSetTextDrawingMode(_context, kCGTextStroke);

    [self drawText:text x:x y:y];
}

- (CGSize)measureText:(NSString *)text
{
    NSDictionary *userAttributes =
        @{NSFontAttributeName : [UIFont fontWithName:_fontName size:_fontSize]};
    CGSize textSize = [text sizeWithAttributes:userAttributes];
    return textSize;
}

#pragma 填充与描边
- (void)setFillStyle:(NSArray *)fillStyle
{
    _fillStyle = [CanvasConvert CGColorConvert:fillStyle];
    CGContextSetFillColorWithColor(_context, _fillStyle);
}

- (void)setStrokeStyle:(NSArray *)strokeStyle
{
    CGColorRef style = [CanvasConvert CGColorConvert:strokeStyle];
    CGContextSetStrokeColorWithColor(_context, style);
}

#pragma 线型样式
- (void)setLineWidth:(CGFloat)lineWidth
{
    CGContextSetLineWidth(_context, lineWidth);
}

- (void)setLineCap:(NSString *)lineCap
{
    CGLineCap cap = [CanvasConvert CGLineCap:lineCap];
    CGContextSetLineCap(_context, cap);
}

- (CanvasCGFloatArray)getLineDash
{
    return _lineDash;
}

- (void)setLineDash:(NSArray *)lineDash
{
    CanvasCGFloatArray dash = [CanvasConvert CanvasCGFloatArray:lineDash];
    if (dash.array == _lineDash.array) {
        return;
    }
    if (_lineDash.array) {
        free(_lineDash.array);
    }
    _lineDash = dash;
    if (dash.count) {
        CGContextSetLineDash(_context, 0, dash.array, dash.count);
    }
}

- (void)setLineJoin:(NSString *)lineJoin
{
    CGLineJoin join = [CanvasConvert CGLineJoin:lineJoin];
    CGContextSetLineJoin(_context, join);
}

#pragma 创建渐变和图案
- (void)createLinearGradient:(CGFloat)x0
                          y0:(CGFloat)y0
                          x1:(CGFloat)x1
                          y1:(CGFloat)y1
{
}
- (void)createRadialGradient:(CGFloat)x0
                          y0:(CGFloat)y0
                          r0:(CGFloat)r0
                          x1:(CGFloat)x1
                          y1:(CGFloat)y1
                          r1:(CGFloat)r1
{
}

- (void)createPattern
{
}

#pragma 生成路径
- (void)beginPath
{
    CGContextBeginPath(_context);
}

- (void)closePath
{
    CGContextClosePath(_context);
}

- (void)moveTo:(CGFloat)x y:(CGFloat)y
{
    CGContextMoveToPoint(_context, x, y);
}

- (void)lineTo:(CGFloat)x y:(CGFloat)y
{
    CGContextAddLineToPoint(_context, x, y);
}

- (void)bezierCurveTo:(CGFloat)cp1x
                 cp1y:(CGFloat)cp1y
                 cp2x:(CGFloat)cp2x
                 cp2y:(CGFloat)cp2y
                    x:(CGFloat)x
                    y:(CGFloat)y
{
    CGContextAddCurveToPoint(_context, cp1x, cp1y, cp2x, cp2y, x, y);
}

- (void)quadraticCurveTo:(CGFloat)cpx
                     cpy:(CGFloat)cpy
                       x:(CGFloat)x
                       y:(CGFloat)y
{
    CGContextAddQuadCurveToPoint(_context, cpx, cpy, x, y);
}

- (void)arc:(CGFloat)x
                y:(CGFloat)y
           radius:(CGFloat)radius
      startAangle:(CGFloat)startAngle
         endAngle:(CGFloat)endAangle
    anticlockwise:(int)anticlockwise
{
    CGContextAddArc(_context, x, y, radius, startAngle, endAangle, anticlockwise);
}

- (void)arcTo:(CGFloat)x1
           y1:(CGFloat)y1
           x2:(CGFloat)x2
           y2:(CGFloat)y2
       radius:(CGFloat)radius
{
    CGContextAddArcToPoint(_context, x1, y1, x2, y2, radius);
}

- (void)rect:(CGFloat)x
           y:(CGFloat)y
       width:(CGFloat)width
      height:(CGFloat)height
{
    CGRect rect = CGRectMake(x, y, width, height);
    CGContextAddRect(_context, rect);
}

#pragma 绘制路径
- (void)fill
{
    CGContextDrawPath(_context, kCGPathFillStroke);
}

- (void)stroke
{
    CGContextDrawPath(_context, kCGPathStroke);
}

- (void)drawFocusIfNeeded
{
}

- (void)scrollPathIntoView
{
}

- (void)clip
{
    CGContextClip(_context);
}

- (void)clip:(NSString *)fillRule
{
    if ([fillRule isEqualToString:@"nonzero"]) {
        [self clip];
    } else if ([fillRule isEqualToString:@"evenodd"]) {
        CGContextEOClip(_context);
    }
}

- (void)isPointInPath
{
}

- (void)isPointInStroke
{
}

#pragma 坐标变换
- (void)rotate:(CGFloat)angle
{
    CGContextRotateCTM(_context, angle);
}

- (void)scale:(CGFloat)x y:(CGFloat)y
{
    CGContextScaleCTM(_context, x, y);
}

- (void)translate:(CGFloat)x y:(CGFloat)y
{
    CGContextTranslateCTM(_context, x, y);
}

- (void)transform:(CGFloat)a
                b:(CGFloat)b
                c:(CGFloat)c
                d:(CGFloat)d
                e:(CGFloat)e
                f:(CGFloat)f
{
    CGContextConcatCTM(_context, CGAffineTransformMake(a, b, c, d, e, f));
}

- (void)setTransform:(CGFloat)a
                   b:(CGFloat)b
                   c:(CGFloat)c
                   d:(CGFloat)d
                   e:(CGFloat)e
                   f:(CGFloat)f
{
    [self transform:1 b:0 c:0 d:1 e:0 f:0];
    [self transform:a b:b c:c d:d e:e f:f];
}

#pragma 绘制图像
- (void)drawImage
{
}

#pragma 像素控制
- (void)createImageData
{
}

- (void)getImageData
{
}

- (void)putImageData
{
}

#pragma 状态处理
- (void)save
{
    CGContextSaveGState(_context);
}

- (void)restore
{
    CGContextRestoreGState(_context);
}

@end
