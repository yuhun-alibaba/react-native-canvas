#import "CanvasMethodDelegate.h"


@implementation CanvasMethodDelegate

- (instancetype)initWithClass:(Class)moduleClass
{
    _methods = [NSMutableDictionary new];
    [self findMethods:moduleClass];
    return self;
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
        return;
    }
    RCTLog(@"CanvasMethodDelegate: Could not find method %@", method);
}

@end
