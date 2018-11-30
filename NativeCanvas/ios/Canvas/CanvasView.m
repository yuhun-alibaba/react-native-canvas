//
//  CanvasView.m
//  graphic
//
//  Created by sam on 2018/11/1.
//  Copyright © 2018 sam. All rights reserved.
//

#import "CanvasView.h"


@implementation CanvasView

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        _actions = [[NSMutableArray alloc] initWithCapacity:0];
    }
    return self;
}

- (void)initWithContext:(CGContextRef)context
{
    if (!_context) {
        _context = [CanvasRenderingContext2D new];
    }
    _context.context = context;
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
 * 未知参数的反射：
 * https://blog.csdn.net/imanapple/article/details/50477148
 * https://github.com/chukong/quick-cocos2d-x/blob/master/lib/cocos2d-x/scripting/lua/cocos2dx_support/platform/ios/CCLuaObjcBridge.mm (156 line)
 */
- (void)callMethodWithArguments:object method:(NSString *)method arguments:(NSArray *)arguments
{
    // @TODO 将 invocation 存起来
    SEL selector = NSSelectorFromString(method);
    NSMethodSignature *signature = [object methodSignatureForSelector:selector];

    if (signature.numberOfArguments == 0) {
        RCTLogWarn(@"callMethodWithArguments:%@ no selector", method);
        return;
    }
    if (signature.numberOfArguments > [arguments count] + 2) {
        RCTLogWarn(@"callMethodWithArguments:%@ not enough aruments", method);
        return;
    }

    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:signature];
    [invocation setTarget:object];
    [invocation setSelector:selector];

    for (int i = 0; i < [arguments count]; i++) {
        NSObject *arg = [arguments objectAtIndex:i];
        // 类型转换
        if ([arg isKindOfClass:[NSNull class]]) {
            arg = nil;
        } else if ([arg isKindOfClass:[NSNumber class]]) {
            NSNumber *number = (NSNumber *)arg;
            const char *numberType = [number objCType];
            if ((strcmp(numberType, @encode(BOOL)) == 0) || [arg isKindOfClass:[@YES class]]) {
                bool value = [(NSNumber *)arg boolValue];
                [invocation setArgument:&value atIndex:2 + i];
            } else if (strcmp(numberType, @encode(int)) == 0) {
                int value = [(NSNumber *)arg intValue];
                [invocation setArgument:&value atIndex:2 + i];
            } else {
                CGFloat value = [(NSNumber *)arg floatValue];
                [invocation setArgument:&value atIndex:2 + i];
            }
            continue;
        }
        [invocation setArgument:&arg atIndex:2 + i];
    }

    [invocation invoke]; // Invoke the selector
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
        NSString *method = [action objectForKey:@"method"];
        [self callMethodWithArguments:_context method:method arguments:[action objectForKey:@"arguments"]];
    }
}

@end
