//
//  CanvasConvert.m
//  NativeCanvas
//
//  Created by sam on 2018/11/13.
//  Copyright © 2018 Facebook. All rights reserved.
//

#import "CanvasConvert.h"


@implementation CanvasConvert

+ (CanvasCGFloatArray)CanvasCGFloatArray:(NSArray *)arr
{
    NSUInteger count = arr.count;

    CanvasCGFloatArray array;
    array.count = count;
    array.array = NULL;

    if (count) {
        // Ideally, these arrays should already use the same memory layout.
        // In that case we shouldn't need this new malloc.
        array.array = malloc(sizeof(CGFloat) * count);
        for (NSUInteger i = 0; i < count; i++) {
            array.array[i] = [arr[i] doubleValue];
        }
    }

    return array;
}

+ (CGColorRef)CGColorConvert:(NSArray *)arr
{
    if (arr.count < 4) {
        return NULL;
    }
    return [self CGColor:[arr subarrayWithRange:(NSRange){0, 4}]];
}

+ (NSNumber *)TextBaselineConvert:(NSString *)textBaseline
{
    static NSDictionary *mapping;
    static dispatch_once_t onceToken;

    dispatch_once(&onceToken, ^{
        mapping = @{
            @"bottom" : @(CanvasTextBaselineBottom),
            @"middle" : @(CanvasTextBaselineMiddle),
            @"top" : @(CanvasTextBaselineTop)
        };
    });

    return mapping[textBaseline] ?: @(CanvasTextBaselineTop);
}

@end
