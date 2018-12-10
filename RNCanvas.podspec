Pod::Spec.new do |s|

  s.name         = "RNCanvas"
  s.version      = "0.0.1"
  s.author       = "henryluki"
  s.summary      = "Canvas bindings for React Native"
  s.homepage     = "https://github.com/henryluki/react-native-canvas"
  s.source       = { :git => "https://github.com/henryluki/react-native-canvas", :tag => "v0.0.1" }
  s.source_files = "ios/*.{h,m}"
  s.license      = "MIT"
  s.platforms    = { :ios => "8.0", :tvos => "9.2" }

end
