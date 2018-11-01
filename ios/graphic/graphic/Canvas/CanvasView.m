//
//  CanvasView.m
//  graphic
//
//  Created by sam on 2018/11/1.
//  Copyright © 2018 sam. All rights reserved.
//

#import "CanvasView.h"

@implementation CanvasView


- (id)initWithFrame:(CGRect)frame{
    self = [super initWithFrame:frame];
    if (self) {
        _actions = [[NSMutableArray alloc] initWithCapacity:100];
    }
    return self;
}

-(void)drawRect:(CGRect)rect {
    CanvasRenderingContext2D *context = [[CanvasRenderingContext2D alloc] initWithContext:UIGraphicsGetCurrentContext()];
    _context = context;
    [self runActions];
}

-(void)runActions{
    for (NSMutableArray *action in _actions) {
//        NSString *name = action[0];
//        switch (name) {
//            case "fillRect":
//                _context.fillRect()
//                break;
//            default:
//                break;
//        }
    }
}

-(void)callAction:(NSMutableArray*)actionAndArgs{
    [_actions addObject: actionAndArgs];
}

@end
