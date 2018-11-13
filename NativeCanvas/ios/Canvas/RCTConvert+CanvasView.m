//
//  RCTConvert+CanvasView.m
//  NativeCanvas
//
//  Created by sam on 2018/11/13.
//  Copyright © 2018 Facebook. All rights reserved.
//

#import "RCTConvert+CanvasView.h"

@implementation RCTConvert (CanvasView)

+(NSMutableArray*)NSMutableArray:(id)json{
  return (NSMutableArray*)[self NSArray:json];
}

@end
