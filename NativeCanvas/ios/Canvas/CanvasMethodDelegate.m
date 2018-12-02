//
//  CanvasMethodDelegate.m
//  NativeCanvas
//
//  Created by sam on 2018/12/2.
//  Copyright © 2018年 Facebook. All rights reserved.
//

#import "CanvasMethodDelegate.h"


@implementation CanvasMethodDelegate

- (void)initWithClass:(Class)moduleClass
{
    _methods = [NSMutableDictionary new];
    [self findMethods:moduleClass];
}

- (void)findMethods:(Class)moduleClass
{
    unsigned int methodCount;
    Method *methods = class_copyMethodList(moduleClass, &methodCount);
    for (unsigned int i = 0; i < methodCount; i++) {
        Method method = methods[i];
        SEL selector = method_getName(method);
        NSString *name = [NSString stringWithUTF8String:sel_getName(selector)];
        _methods[name] = [[CanvasMethodWrapper alloc] initWithMethod:name moduleClass:moduleClass];
    }
    free(methods);
}

- (void)invoke:object method:(NSString *)method arguments:(NSArray *)arguments
{
    if (_methods[method] != nil) {
        [_methods[method] invoke:object arguments:arguments];
    }
    RCTLog(@"CanvasMethodDelegate: Could not find method %@", method);
}

@end
