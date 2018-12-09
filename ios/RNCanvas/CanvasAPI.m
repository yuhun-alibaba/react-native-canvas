#import "CanvasAPI.h"


@implementation CanvasAPI

RCT_EXPORT_MODULE();

RCT_EXPORT_SYNCHRONOUS_TYPED_METHOD(NSDictionary *, measureText
                                    : (NSString *)text fontSize
                                    : (CGFloat)fontSize)
{
    NSDictionary *userAttributes = @{NSFontAttributeName : [UIFont systemFontOfSize:fontSize]};
    CGSize textSize = [text sizeWithAttributes:userAttributes];
    NSDictionary *textMetric = @{ @"width" : @(textSize.width) };
    return textMetric;
}

// 同步绘制
RCT_EXPORT_SYNCHRONOUS_TYPED_METHOD(id, drawSync
                                    : (NSString *)tag actions
                                    : (id)actions)
{
    NSMutableArray *convertedActions = (NSMutableArray *)[RCTConvert NSArray:actions];
    dispatch_async(dispatch_get_main_queue(), ^{
        [CanvasAPI draw:tag actions:convertedActions];
    });
    return @1;
}

RCT_EXPORT_METHOD(release
                  : (NSString *)tag)
{
    [CanvasViewManager removeCanvasView:tag];
}

+ (void)draw:(NSString *)tag actions:(NSMutableArray *)actions
{
    CanvasView *canvas = [CanvasViewManager getCanvasView:tag];
    if (actions.count == 0 || !canvas) {
        return;
    }
    [canvas setActions:actions];
    [canvas invalidate];
}

@end
