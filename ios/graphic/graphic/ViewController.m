//
//  ViewController.m
//  graphic
//
//  Created by sam on 2018/10/31.
//  Copyright © 2018年 sam. All rights reserved.
//

#import "ViewController.h"
#import "CanvasExampleView.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    CanvasExampleView *view=[[CanvasExampleView alloc]initWithFrame:[UIScreen mainScreen].bounds];
    view.backgroundColor=[UIColor whiteColor];
    [self.view addSubview:view];
    // Do any additional setup after loading the view, typically from a nib.
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
