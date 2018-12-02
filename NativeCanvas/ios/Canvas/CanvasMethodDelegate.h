//
//  CanvasMethodDelegate.h
//  NativeCanvas
//
//  Created by sam on 2018/12/2.
//  Copyright © 2018年 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <objc/runtime.h>
#import <React/RCTLog.h>
#import "CanvasMethodWrapper.h"


@interface CanvasMethodDelegate : NSObject

@property (nonatomic, strong) NSMutableDictionary<NSString *, CanvasMethodWrapper *> *methods;

- (void)initWithClass:(Class)moduleClass;
- (void)invoke:object method:(NSString *)method arguments:(NSArray *)arguments;

@end
