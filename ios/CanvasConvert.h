#import <React/RCTConvert.h>
#import <React/RCTFont.h>
#import "CanvasCGFloatArray.h"
#import "CanvasTextBaseline.h"


@interface CanvasConvert : RCTConvert

+ (CanvasCGFloatArray)CanvasCGFloatArray:(NSArray *)arr;
+ (CGColorRef)CGColorConvert:(NSArray *)arr;
+ (NSNumber *)TextBaselineConvert:(NSString *)textBaseline;
+ (BOOL)CGColorEqualToUIColor:(CGColorRef)cgColor uiColor:(UIColor *)uiColor;

@end
