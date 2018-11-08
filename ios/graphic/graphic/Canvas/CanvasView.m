//
//  CanvasView.m
//  graphic
//
//  Created by sam on 2018/11/1.
//  Copyright © 2018 sam. All rights reserved.
//

#import "CanvasView.h"

@implementation CanvasView


-(id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
//    if (self) {
//        _actions = [[NSMutableArray alloc] initWithCapacity:100];
//    }
    return self;
}

-(void)drawRect:(CGRect)rect {
    CanvasRenderingContext2D *context = [[CanvasRenderingContext2D alloc] initWithContext:UIGraphicsGetCurrentContext()];
    _context = context;
    [self runActions];
}

/**
 * 未知参数的反射：
 * https://blog.csdn.net/imanapple/article/details/50477148
 */
-(void)callMethodWithArguments:(NSString *)method arguments:(NSArray *)arguments {
    SEL selector = NSSelectorFromString(method);
    NSMethodSignature *signature = [_context methodSignatureForSelector:selector];

    if (signature.numberOfArguments == 0) {
        return; //@selector未找到
    }
    if (signature.numberOfArguments > [arguments count]+2) {
        return; //传入arguments参数不足。signature至少有两个参数，self和_cmd
    }
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:signature];
    [invocation setTarget:_context];
    [invocation setSelector:selector];

    for(int i=0; i<[arguments count]; i++)
    {
        id arg = [arguments objectAtIndex:i];
        [invocation setArgument:&arg atIndex:i+2]; // The first two arguments are the hidden arguments self and _cmd
    }

    [invocation invoke]; // Invoke the selector
}

-(void)runActions{
    for (NSDictionary *action in _actions) {
        NSString *method = [action objectForKey:@"method"];
        NSArray *arguments = [action objectForKey:@"arguments"];
        [self callMethodWithArguments:method arguments:arguments];
    }
}

/**
 * @{
 *   @"arguments" : @[],
 *   @"method" : @""
 * };
 */
-(void)callAction:(NSMutableArray*)methodAndArguments{
    [_actions addObject: methodAndArguments];
}

@end
