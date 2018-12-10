require 'json'

package = JSON.parse(File.read(File.join(__dir__, 'package.json')))

Pod::Spec.new do |s|

  s.name         = "RNCanvas"
  s.version      = package['version']
  s.summary      = package['description']
  s.license      = package['license']
  s.author       = "henryluki"
  s.summary      = "Canvas bindings for React Native"
  s.homepage     = "https://github.com/henryluki/react-native-canvas"
  s.source       = { :git => "https://github.com/henryluki/react-native-canvas", :tag => s.version }
  s.source_files = "ios/*.{h,m}"
  s.license      = "MIT"
  s.platform     = :ios, "8.0"

end