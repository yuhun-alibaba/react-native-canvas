#import <Foundation/Foundation.h>
#import <objc/runtime.h>
#import <React/RCTLog.h>
#import "CanvasMethodWrapper.h"


@interface CanvasMethodDelegate : NSObject

@property (nonatomic, strong) NSMutableDictionary<NSString *, CanvasMethodWrapper *> *methods;

- (id)initWithClass:(Class)moduleClass;
- (void)invoke:object method:(NSString *)method arguments:(NSArray *)arguments;

@end
