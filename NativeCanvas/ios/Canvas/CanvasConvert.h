//
//  CanvasConvert.h
//  NativeCanvas
//
//  Created by sam on 2018/11/13.
//  Copyright © 2018 Facebook. All rights reserved.
//

#import <React/RCTConvert.h>
#import <React/RCTFont.h>
#import "CanvasCGFloatArray.h"
#import "CanvasTextBaseline.h"


@interface CanvasConvert : RCTConvert

+ (CanvasCGFloatArray)CanvasCGFloatArray:(NSArray *)arr;
+ (CGColorRef)CGColorConvert:(NSArray *)arr;
+ (NSNumber *)TextBaselineConvert:(NSString *)textBaseline;

@end
