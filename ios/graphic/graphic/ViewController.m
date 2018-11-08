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
    canvas.backgroundColor=[UIColor whiteColor];
    [self.view addSubview:canvas];

    NSDictionary *fillRect = @{@"method":@"fillRect:y:width:height:",@"arguments":@[@50, @50, @100, @100]};
    [canvas callAction:fillRect];
    // Do any additional setup after loading the view, typically from a nib.
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
