//
//  ViewController.m
//  graphic
//
//  Created by sam on 2018/10/31.
//  Copyright © 2018年 sam. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    CanvasView *canvas=[[CanvasView alloc]initWithFrame:[UIScreen mainScreen].bounds];
    canvas.backgroundColor = [UIColor whiteColor];
    [self.view addSubview:canvas];
    
    NSArray *actions = @[
        @{
            @"method":@"strokeRect:y:width:height:",
            @"arguments":@[@50.0, @50.0, @100.0, @100.0]
        },
        @{
            @"method":@"arc:y:radius:startAangle:endAngle:anticlockwise:",
            @"arguments":@[@150.0, @150.0, @50.0, @0, @(M_PI * 2), @1]
        },
        @{
            @"method": @"fill",
            @"arguments":@[]
        },
        @{
            @"method": @"setFont:",
            @"arguments": @[@"10px HelveticaNeue"]
        },
        @{
            @"method": @"fillText:x:y:",
            @"arguments": @[@"10px HelveticaNeue", @10.0, @10.0]
        },
        @{
            @"method": @"strokeText:x:y:",
            @"arguments": @[@"10px HelveticaNeue", @10.0, @30.0]
        },
        @{
            @"method": @"measureText:",
            @"arguments": @[@"hello world"]
        }
    ];
    [canvas callActions:actions];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
}


@end
