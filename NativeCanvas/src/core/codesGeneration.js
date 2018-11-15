// @flow
// run in terminal, auto generate codes

const fillStrokeStyles = ["fillStyle", "strokeStyle"];
const lineStyles = ["lineWidth", "lineCap", "lineJoin", "lineDashOffset"];
const textStyles = ["font", "textAlgin", "textBaseline", "direction"];
const shadowStyles = ["shadowColor", "shadowBlur"];

const text = ["fillText", "strokeText"];
const retangles = ["clearRect", "fillRect", "strokeRect"];
const line = ["setLineDash"];
const paths = [
  "beginPath",
  "closePath",
  "moveTo",
  "lineTo",
  "bezierCurveTo",
  "quadraticCurveTo",
  "arc",
  "arcTo",
  "rect",
  "clip"
];
const pathsDrawing = ["fill", "stroke"];
const transformations = [
  "rotate",
  "scale",
  "translate",
  "transform",
  "setTransform"
];
const images = ["drawImage"];
const pixel = ["createImageData", "getImageData", "putImageData"];
const state = ["save", "restore"];

const methods = [].concat(
  line,
  retangles,
  text,
  paths,
  pathsDrawing,
  transformations,
  images,
  pixel,
  state
);

const noArgumentsMethods = [].concat(pathsDrawing, state);

const styles = [].concat(
  fillStrokeStyles,
  lineStyles,
  textStyles,
  shadowStyles
);

function logStyle() {
  styles.forEach(style => {
    console.log(
      `set ${style}(${style}) {this.enqueue(\n\tthis.createAction("${style}", [${style}])\n\t)}\n`
    );
  });
}

function logStyleReset() {
  styles.forEach(style => {
    console.log(`this._${style} = null;`);
  });
}

function logMethod() {
  methods.forEach(method => {
    if (noArgumentsMethods.indexOf(method) > -1) {
      console.log(
        `${method}() {this.enqueue(\n\tthis.createAction("${method}", [])\n\t)}\n`
      );
    } else {
      console.log(
        `${method}(...args) {this.enqueue(\n\tthis.createAction("${method}", args)\n\t)}\n`
      );
    }
  });
}

function logStyleMap() {
  let map = {};

  styles.reduce((acc, style, index) => {
    const firstStyleName = style.slice(0, 1).toUpperCase();
    const restStyleName = style.slice(1);

    acc[style] = `set${firstStyleName}${restStyleName}:`;
    return acc;
  }, map);

  console.log(map);
}

function logMethodMap() {
  let map = {};

  methods.reduce((acc, method, index) => {
    if (methodMap[method]) {
      acc[method] = methodMap[method];
      return acc;
    }

    if (noArgumentsMethods.indexOf(method) > -1) {
      acc[method] = `${method}`;
    } else {
      acc[method] = `${method}:`;
    }

    return acc;
  }, map);

  console.log(map);
}

// logStyle();
logStyleReset();
// logMethod();
// logStyleMap();
// logMethodMap();
