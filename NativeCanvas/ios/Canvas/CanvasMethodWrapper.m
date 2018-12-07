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

- (void)buildExtractors:(NSArray *)arguments
{
    // 第一次运行将传入的参数做正确的入参，生成参数处理方法
    // Warning: 依赖入参的类型检查！！
    NSMutableArray<ArgumentExcator> *argumentExtractors = [[NSMutableArray alloc] initWithCapacity:_argumentsNumber];
    NSInvocation *invocation = _invocation;

#define SET_ARGUMENT_BLOCK(_logic)                                           \
    [argumentExtractors addObject:^(NSArray * arguments, NSUInteger index) { \
        _logic                                                               \
            [invocation setArgument:&value atIndex:(index) + 2];             \
    }]

#define SET_ARGUMENT_NSNUMBER_BLOCK(_type, _typeValue) SET_ARGUMENT_BLOCK( \
    NSNumber *number = (NSNumber *)arguments[index];                       \
    _type value = [number _typeValue];)

    for (NSUInteger i = 0; i < _argumentsNumber; i++) {
        NSObject *argument = [arguments objectAtIndex:i];

        if ([argument isKindOfClass:[NSNull class]]) {
            argument = nil;
        } else if ([argument isKindOfClass:[NSNumber class]]) {
            NSNumber *number = (NSNumber *)argument;
            const char *numberType = [number objCType];
            if ((strcmp(numberType, @encode(BOOL)) == 0) || [number isKindOfClass:[@YES class]]) {
                SET_ARGUMENT_NSNUMBER_BLOCK(bool, boolValue);
            } else if (strcmp(numberType, @encode(int)) == 0) {
                SET_ARGUMENT_NSNUMBER_BLOCK(int, intValue);
            } else {
                SET_ARGUMENT_NSNUMBER_BLOCK(CGFloat, floatValue);
            }
            continue;
        }
        SET_ARGUMENT_BLOCK(
            NSObject *value = arguments[index];);
    };

    _invocation = invocation;
    _argumentExcators = argumentExtractors;
}

- (void)invoke:(id)instance arguments:(NSArray *)arguments
{
    if (_invocation == nil) {
        [self buildInvocation];
    }

    if (_argumentsNumber != arguments.count) {
        RCTLog(@"CanvasMethodWrapper: Could not invoke method %@, arguments number not correct", _method);
        return;
    }

    if (_argumentExcators == nil) {
        [self buildExtractors:arguments];
    }

    for (NSUInteger i = 0; i < _argumentsNumber; i++) {
        _argumentExcators[i](arguments, i);
    }

    [_invocation invokeWithTarget:instance];
}

@end
