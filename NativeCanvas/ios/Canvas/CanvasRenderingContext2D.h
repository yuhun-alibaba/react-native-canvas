//
//  CanvasRenderingContext2D.h
//  graphic
//
//  Created by sam on 2018/11/1.
//  Copyright © 2018 sam. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import "CanvasCGFloatArray.h"
#import "CanvasConvert.h"


@interface CanvasRenderingContext2D : NSObject

@property (nonatomic, assign) CGContextRef context;
@property (nonatomic, assign) CGMutablePathRef path;
@property (nonatomic, assign) CGAffineTransform originMatrix;

#pragma 线型
@property (nonatomic, assign) CanvasCGFloatArray lineDash;

#pragma 文本
@property (nonatomic, assign) NSString *fontName;
@property (nonatomic, assign) CGFloat fontSize;
@property (nonatomic, assign) NSTextAlignment textAlign;
@property (nonatomic, assign) NSNumber *textBaseline;
@property (nonatomic, assign) NSString *direction;

#pragma 填充与描边
@property (nonatomic, assign) CGColorRef fillStyle;

#pragma 阴影
@property (nonatomic, assign) CGFloat shadowBlur;
@property (nonatomic, assign) CGColorRef shadowColor;
@property (nonatomic, assign) CGFloat shadowOffsetX;
@property (nonatomic, assign) CGFloat shadowOffsetY;

#pragma 合成
@property (nonatomic, assign) CGFloat globalAlpha;
@property (nonatomic, assign) NSString *globalCompositeOperation;

#pragma 初始化
- (CanvasRenderingContext2D *)init;
- (void)initOrResetProperty;

#pragma 绘制矩形
- (void)clearRect:(CGFloat)x y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height;
- (void)fillRect:(CGFloat)x y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height;
- (void)strokeRect:(CGFloat)x y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height;

#pragma 设置文本样式
- (void)setFont:(NSDictionary *)font;
- (void)setTextAlign:(NSString *)textAlign;
- (void)setTextBaseline:(NSString *)textBaseline;

#pragma 绘制文本
- (void)fillText:(NSString *)text x:(CGFloat)x y:(CGFloat)y;
- (void)strokeText:(NSString *)text x:(CGFloat)x y:(CGFloat)y;
- (CGSize)measureText:(NSString *)text;

#pragma 填充与描边
- (void)setFillStyle:(NSArray *)fillStyle;
- (void)setStrokeStyle:(NSArray *)strokeStyle;

#pragma 线型样式
- (CanvasCGFloatArray)getLineDash;
- (void)setLineDash:(NSArray *)lineDash;
- (void)setLineCap:(NSString *)lineCap;
- (void)setLineJoin:(NSString *)lineJoin;

#pragma 创建渐变和图案
- (void)createLinearGradient:(CGFloat)x0 y0:(CGFloat)y0 x1:(CGFloat)x1 y1:(CGFloat)y1;
- (void)createRadialGradient:(CGFloat)x0 y0:(CGFloat)y0 r0:(CGFloat)r0 x1:(CGFloat)x1 y1:(CGFloat)y1 r1:(CGFloat)r1;
- (void)createPattern;

#pragma 生成路径
- (void)beginPath;
- (void)closePath;
- (void)moveTo:(CGFloat)x y:(CGFloat)y;
- (void)lineTo:(CGFloat)x y:(CGFloat)y;
- (void)bezierCurveTo:(CGFloat)cp1x cp1y:(CGFloat)cp1y cp2x:(CGFloat)cp2x cp2y:(CGFloat)cp2y x:(CGFloat)x y:(CGFloat)y;
- (void)quadraticCurveTo:(CGFloat)cpx cpy:(CGFloat)cpy x:(CGFloat)x y:(CGFloat)y;
- (void)arc:(CGFloat)x y:(CGFloat)y radius:(CGFloat)radius startAangle:(CGFloat)startAngle endAngle:(CGFloat)endAangle;
- (void)arc:(CGFloat)x y:(CGFloat)y radius:(CGFloat)radius startAangle:(CGFloat)startAngle endAngle:(CGFloat)endAangle anticlockwise:(bool)anticlockwise;
- (void)arcTo:(CGFloat)x1 y1:(CGFloat)y1 x2:(CGFloat)x2 y2:(CGFloat)y2 radius:(CGFloat)radius;
- (void)rect:(CGFloat)x y:(CGFloat)y width:(CGFloat)width height:(CGFloat)height;

#pragma 绘制路径
- (void)fill;
- (void)stroke;
- (void)drawFocusIfNeeded;
- (void)scrollPathIntoView;
- (void)clip;
- (void)clip:(NSString *)fillRule;
- (void)isPointInPath;
- (void)isPointInStroke;

#pragma 坐标变换
- (void)rotate:(CGFloat)angle;
- (void)scale:(CGFloat)x y:(CGFloat)y;
- (void)translate:(CGFloat)x y:(CGFloat)y;
- (void)transform:(CGFloat)a b:(CGFloat)b c:(CGFloat)c d:(CGFloat)d e:(CGFloat)e f:(CGFloat)f;
- (void)setTransform:(CGFloat)a b:(CGFloat)b c:(CGFloat)c d:(CGFloat)d e:(CGFloat)e f:(CGFloat)f;
- (void)resetTransform;

#pragma 绘制图像
- (void)drawImage;

#pragma 像素控制
- (void)createImageData;
- (void)getImageData;
- (void)putImageData;

#pragma 状态处理
- (void)save;
- (void)restore;

@end
