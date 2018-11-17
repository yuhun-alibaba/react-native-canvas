// @flow

// extract style values
// copy from "art": react-native/Libraries/ART/ReactNativeART.js

import Color from "art/core/color";

export function extractColor(color) {
  if (color == null) {
    return null;
  }
  const c = new Color(color);
  return [c.red / 255, c.green / 255, c.blue / 255, c.alpha];
}

const cachedFontObjectsFromString = {};

const fontFamilyPrefix = /^[\s"']*/;
const fontFamilySuffix = /[\s"']*$/;

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
  const regexp = /^\s*((?:(?:normal|bold|italic)\s+)*)(?:(\d+(?:\.\d+)?)[ptexm\%]*(?:\s*\/.*?)?\s+)?\s*\"?([^\"]*)/i;
  const match = regexp.exec(font);
  if (!match) {
    return null;
  }
  const fontFamily = extractSingleFontFamily(match[3]);
  const fontSize = +match[2] || 12;
  const isBold = /bold/.exec(match[1]);
  const isItalic = /italic/.exec(match[1]);
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
  const fontFamily = extractSingleFontFamily(font.fontFamily);
  const fontSize = +font.fontSize || 12;
  const fontWeight =
    font.fontWeight != null ? font.fontWeight.toString() : "400";
  return {
    // Normalize
    fontFamily: fontFamily,
    fontSize: fontSize,
    fontWeight: fontWeight,
    fontStyle: font.fontStyle
  };
}

export function extractAlignment(alignment) {
  if (alignment === "start") {
    return "left";
  } else if (alignment === "end") {
    return "right";
  } else {
    return alignment;
  }
}

// measureText sync api
// mock!!

function strLen(str) {
  let len = 0;
  for (let i = 0; i < str.length; i++) {
    if (str.charCodeAt(i) > 0 && str.charCodeAt(i) < 128) {
      len++;
    } else {
      len += 2;
    }
  }
  return len;
}

export function measureText(text, fontSize = 10) {
  return {
    width: (strLen(text) * fontSize) / 2
  };
}
