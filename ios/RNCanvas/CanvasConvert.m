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

+ (BOOL)CGColorEqualToUIColor:(CGColorRef)cgColor uiColor:(UIColor *)uiColor
{
    UIColor *color1 = [UIColor colorWithCGColor:cgColor];

    CGFloat r1, g1, b1, a1, r2, g2, b2, a2;
    CGFloat tolerance = 0;

    [color1 getRed:&r1 green:&g1 blue:&b1 alpha:&a1];
    [uiColor getRed:&r2 green:&g2 blue:&b2 alpha:&a2];

    return fabs(r1 - r2) <= tolerance &&
        fabs(g1 - g2) <= tolerance &&
        fabs(b1 - b2) <= tolerance &&
        fabs(a1 - a2) <= tolerance;
}

@end
