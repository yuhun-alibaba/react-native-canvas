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

- (void)buildInvocation
{
    SEL selector = NSSelectorFromString(_method);
    // 不能从 methodSignature 拿到参数是对象的具体类型，否则可以直接存储参数处理方法
    NSMethodSignature *methodSignature = [_moduleClass instanceMethodSignatureForSelector:selector];
    _argumentsNumber = methodSignature.numberOfArguments - 2;
    _invocation = [NSInvocation invocationWithMethodSignature:methodSignature];
    _invocation.selector = selector;
};

- (void)invoke:(id)instance arguments:(NSArray *)arguments
{
    if (_invocation == nil) {
        [self buildInvocation];
    }

    if (_argumentsNumber != arguments.count) {
        RCTLog(@"CanvasMethodWrapper: Could not invoke method %@, arguments number not correct", _method);
        return;
    }

    for (NSUInteger i = 0; i < _argumentsNumber; i++) {
        NSObject *argument = [arguments objectAtIndex:i];
        NSUInteger index = i + 2;

        if ([argument isKindOfClass:[NSNull class]]) {
            argument = nil;
        } else if ([argument isKindOfClass:[NSNumber class]]) {
            NSNumber *number = (NSNumber *)argument;
            const char *numberType = [number objCType];
            if ((strcmp(numberType, @encode(BOOL)) == 0) || [number isKindOfClass:[@YES class]]) {
                bool value = [number boolValue];
                [_invocation setArgument:&value atIndex:index];
            } else if (strcmp(numberType, @encode(int)) == 0) {
                int value = [number intValue];
                [_invocation setArgument:&value atIndex:index];
            } else {
                CGFloat value = [number floatValue];
                [_invocation setArgument:&value atIndex:index];
            }
            continue;
        }

        [_invocation setArgument:&argument atIndex:index];
    };

    [_invocation invokeWithTarget:instance];
}

@end
