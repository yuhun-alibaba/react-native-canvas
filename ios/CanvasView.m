#import "CanvasView.h"


static CanvasMethodDelegate *delegate;


@implementation CanvasView

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        static dispatch_once_t onceToken;
        dispatch_once(&onceToken, ^{
            Class contextClass = [CanvasRenderingContext2D class];
            delegate = [[CanvasMethodDelegate alloc] initWithClass:contextClass];
        });
        _actions = [[NSMutableArray alloc] initWithCapacity:0];
    }
    return self;
}

- (void)initWithContext:(CGContextRef)context
{
    if (!_context) {
        _context = [CanvasRenderingContext2D new];
    }
    [_context setContext:context];
}

- (void)invalidate
{
    [self setNeedsDisplay];
}

- (void)clear
{
    [self.context initOrResetProperty];
}

- (void)drawRect:(CGRect)rect
{
    [self initWithContext:UIGraphicsGetCurrentContext()];
    [self runActions];
    [self clear];
}

/**
 * @{
 *   @"method" : @"",
 *   @"arguments" : @[]
 * };
 */
- (void)runActions
{
    for (NSDictionary *action in _actions) {
        [delegate invoke:_context method:[action objectForKey:@"method"] arguments:[action objectForKey:@"arguments"]];
    }
}

@end
