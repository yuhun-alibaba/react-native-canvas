// @flow
// copy from "art": react-native/Libraries/ART/ReactNativeART.js

var Color = require("art/core/color");

export function extractColor(color) {
  if (color == null) {
    return null;
  }
  var c = new Color(color);
  return [c.red / 255, c.green / 255, c.blue / 255, c.alpha];
}

var cachedFontObjectsFromString = {};

var fontFamilyPrefix = /^[\s"']*/;
var fontFamilySuffix = /[\s"']*$/;

function extractSingleFontFamily(fontFamilyString) {
  // ART on the web allows for multiple font-families to be specified.
  // For compatibility, we extract the first font-family, hoping
  // we'll get a match.
  return fontFamilyString
    .split(",")[0]
    .replace(fontFamilyPrefix, "")
    .replace(fontFamilySuffix, "");
}

function parseFontString(font) {
  if (cachedFontObjectsFromString.hasOwnProperty(font)) {
    return cachedFontObjectsFromString[font];
  }
  var regexp = /^\s*((?:(?:normal|bold|italic)\s+)*)(?:(\d+(?:\.\d+)?)[ptexm\%]*(?:\s*\/.*?)?\s+)?\s*\"?([^\"]*)/i;
  var match = regexp.exec(font);
  if (!match) {
    return null;
  }
  var fontFamily = extractSingleFontFamily(match[3]);
  var fontSize = +match[2] || 12;
  var isBold = /bold/.exec(match[1]);
  var isItalic = /italic/.exec(match[1]);
  cachedFontObjectsFromString[font] = {
    fontFamily: fontFamily,
    fontSize: fontSize,
    fontWeight: isBold ? "bold" : "normal",
    fontStyle: isItalic ? "italic" : "normal"
  };
  return cachedFontObjectsFromString[font];
}

export function extractFont(font) {
  if (font == null) {
    return null;
  }
  if (typeof font === "string") {
    return parseFontString(font);
  }
  var fontFamily = extractSingleFontFamily(font.fontFamily);
  var fontSize = +font.fontSize || 12;
  var fontWeight = font.fontWeight != null ? font.fontWeight.toString() : "400";
  return {
    // Normalize
    fontFamily: fontFamily,
    fontSize: fontSize,
    fontWeight: fontWeight,
    fontStyle: font.fontStyle
  };
}

export function extractAlignment(alignment) {
  switch (alignment) {
    case "right":
      return 1;
    case "center":
      return 2;
    default:
      return 0;
  }
}
