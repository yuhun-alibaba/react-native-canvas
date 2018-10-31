//
//  KCView.m
//  graphic
//
//  Created by sam on 2018/10/31.
//  Copyright © 2018年 sam. All rights reserved.
//

#import "KCView.h"

@implementation KCView

-(void)drawRect:(CGRect)rect{
    //1.取得图形上下文对象
    CGContextRef context = UIGraphicsGetCurrentContext();
    self.context = context;
//    [self drawExample:context];
    [self rect:50.0 y:50.0 width:100.0 height:100.0];
    [self arc:150.0 y:150.0 radius:50.0 startAangle:0 endAngle:M_PI * 2 clockwise:1];
//    [self render];
}

-(void)drawExample:(CGContextRef)context{
    
    //2.创建路径对象
    CGMutablePathRef path = CGPathCreateMutable();
    CGPathMoveToPoint(path, nil, 20, 50);//移动到指定位置（设置路径起点）
    CGPathAddLineToPoint(path, nil, 20, 100);//绘制直线（从起始位置开始）
    CGPathAddLineToPoint(path, nil, 300, 100);//绘制另外一条直线（从上一直线终点开始绘制）
    
    
    //3.添加路径到图形上下文
    CGContextAddPath(context, path);
    
    //4.设置图形上下文状态属性
    CGContextSetRGBStrokeColor(context, 1.0, 0, 0, 1);//设置笔触颜色
    CGContextSetRGBFillColor(context, 0, 1.0, 0, 1);//设置填充色
    CGContextSetLineWidth(context, 2.0);//设置线条宽度
    CGContextSetLineCap(context, kCGLineCapRound);//设置顶点样式,（20,50）和（300,100）是顶点
    CGContextSetLineJoin(context, kCGLineJoinRound);//设置连接点样式，(20,100)是连接点
    /*设置线段样式
     phase:虚线开始的位置
     lengths:虚线长度间隔（例如下面的定义说明第一条线段长度8，然后间隔3重新绘制8点的长度线段，当然这个数组可以定义更多元素）
     count:虚线数组元素个数
     */
    CGFloat lengths[2] = { 18, 9 };
    CGContextSetLineDash(context, 0, lengths, 2);
    /*设置阴影
     context:图形上下文
     offset:偏移量
     blur:模糊度
     color:阴影颜色
     */
    CGColorRef color = [UIColor grayColor].CGColor;//颜色转化，由于Quartz 2D跨平台，所以其中不能使用UIKit中的对象，但是UIkit提供了转化方法
    CGContextSetShadowWithColor(context, CGSizeMake(2, 2), 0.8, color);
    
    //5.绘制图像到指定图形上下文
    /*CGPathDrawingMode是填充方式,枚举类型
     kCGPathFill:只有填充（非零缠绕数填充），不绘制边框
     kCGPathEOFill:奇偶规则填充（多条路径交叉时，奇数交叉填充，偶交叉不填充）
     kCGPathStroke:只有边框
     kCGPathFillStroke：既有边框又有填充
     kCGPathEOFillStroke：奇偶填充并绘制边框
     */
    CGContextDrawPath(context, kCGPathFillStroke);//最后一个参数是填充类型
    
    //6.释放对象
    CGPathRelease(path);
}

#pragma 渲染
-(void)render{
//  CGContextDrawPath(self.context, kCGPathFillStroke);
}

#pragma 弧形
-(void)arc:(float)x y:(float)y radius:(float)radius startAangle:(float)startAngle endAngle:(float)endAangle clockwise:(int)clockwise{
    CGContextAddArc(self.context, x, y, radius, startAngle, endAangle, clockwise);
    [[UIColor yellowColor] set];
    CGContextDrawPath(self.context, kCGPathFillStroke);
}

#pragma 矩形
-(void)rect:(float)x y:(float)y width:(float)width height:(float)height{
    CGRect rect = CGRectMake(x, y, width, height);
    CGContextAddRect(self.context, rect);
    [[UIColor blueColor] set];
    CGContextDrawPath(self.context, kCGPathFillStroke);
}
@end
