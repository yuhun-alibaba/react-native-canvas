//
//  KCView.h
//  graphic
//
//  Created by sam on 2018/10/31.
//  Copyright © 2018年 sam. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface KCView : UIView;

@property CGContextRef context;

-(void)arc:(float)x y:(float)y radius:(float)radius startAangle:(float)startAngle endAngle:(float)endAangle clockwise:(int)clockwise;

@end
