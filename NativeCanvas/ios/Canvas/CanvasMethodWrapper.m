//
//  CanvasMethodWrapper.m
//  NativeCanvas
//
//  Created by sam on 2018/12/2.
//  Copyright © 2018年 Facebook. All rights reserved.
//

#import "CanvasMethodWrapper.h"


@implementation CanvasMethodWrapper

- (instancetype)initWithMethod:(NSString *)method moduleClass:(Class)moduleClass
{
    _method = method;
    _moduleClass = moduleClass;
    return self;
}

- (void)buildArgumentExcators
{
    SEL selector = NSSelectorFromString(_method);
    NSMethodSignature *methodSignature = [_moduleClass instanceMethodSignatureForSelector:selector];
    NSInvocation *invocation = [NSInvocation invocationWithMethodSignature:methodSignature];
    _invocation = invocation;

    // process arguments
    NSUInteger numberOfArguments = methodSignature.numberOfArguments;
    NSMutableArray<ArgumentExcator> *argumentExcators = [[NSMutableArray alloc] initWithCapacity:numberOfArguments - 2];

    for (NSUInteger i = 2; i < numberOfArguments; i++) {
        const char *objcType = [methodSignature getArgumentTypeAtIndex:i];
        NSString *typeName = [NSString stringWithUTF8String:objcType];

        if ([typeName isEqualToString:@"NSNumber"]) {
        }
    }
};

- (void)invoke:(id)instance arguments:(NSArray *)arguments
{
    if (_argumentExcators == nil) {
        [self buildArgumentExcators];
    }
};

@end
