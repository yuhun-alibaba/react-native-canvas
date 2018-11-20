//
//  CanvasView.h
//  graphic
//
//  Created by sam on 2018/11/1.
//  Copyright © 2018 sam. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <React/RCTLog.h>
#import "CanvasRenderingContext2D.h"


@interface CanvasView : UIView

@property (nonatomic, strong) CanvasRenderingContext2D *context;
@property (nonatomic, strong) NSMutableArray *actions;

- (void)callAction:(NSDictionary *)methodAndArguments;
- (void)callActions:(NSArray<NSDictionary *> *)actions;
- (void)invalidate;

@end
