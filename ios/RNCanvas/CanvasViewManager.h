#import <React/RCTViewManager.h>
#import "CanvasView.h"
#import "CanvasAPI.h"


@interface CanvasViewManager : RCTViewManager

+ (void)setCanvasView:(NSString *)tag canvas:(CanvasView *)canvas;
+ (CanvasView *)getCanvasView:(NSString *)tag;
+ (void)removeCanvasView:(NSString *)tag;

@end
