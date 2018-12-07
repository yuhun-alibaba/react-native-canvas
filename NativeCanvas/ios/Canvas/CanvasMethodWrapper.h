//
//  CanvasMethodWrapper.h
//  NativeCanvas
//
//  Created by sam on 2018/12/2.
//  Copyright © 2018年 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <objc/runtime.h>
#import <React/RCTLog.h>


typedef void (^ArgumentExcator)(NSArray *, NSUInteger);


@interface CanvasMethodWrapper : NSObject

@property (nonatomic, assign) NSString *method;
@property (nonatomic, assign) Class moduleClass;
@property (nonatomic, strong) NSInvocation *invocation;
@property (nonatomic, assign) NSUInteger argumentsNumber;
@property (nonatomic, strong) NSMutableArray<ArgumentExcator> *argumentExcators;

- (instancetype)initWithMethod:(NSString *)method moduleClass:(Class)moduleClass;
- (void)invoke:(id)instance arguments:(NSArray *)arguments;

@end
