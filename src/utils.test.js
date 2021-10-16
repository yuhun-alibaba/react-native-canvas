const rewire = require("rewire")
const utils = rewire("./utils")
const extractSingleFontFamily = utils.__get__("extractSingleFontFamily")
const parseFontString = utils.__get__("parseFontString")
const strLen = utils.__get__("strLen")
// @ponicode
describe("utils.extractColor", () => {
    test("0", () => {
        let callFunction = () => {
            utils.extractColor("transparent")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("1", () => {
        let callFunction = () => {
            utils.extractColor("none")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("2", () => {
        let callFunction = () => {
            utils.extractColor("rgb(0,100,200)")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("3", () => {
        let callFunction = () => {
            utils.extractColor("red")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("4", () => {
        let callFunction = () => {
            utils.extractColor("rgb(20%,10%,30%)")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("5", () => {
        let callFunction = () => {
            utils.extractColor(undefined)
        }
    
        expect(callFunction).not.toThrow()
    })
})

// @ponicode
describe("extractSingleFontFamily", () => {
    test("0", () => {
        let callFunction = () => {
            extractSingleFontFamily(",")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("1", () => {
        let callFunction = () => {
            extractSingleFontFamily(",foo bar")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("2", () => {
        let callFunction = () => {
            extractSingleFontFamily("foo bar")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("3", () => {
        let callFunction = () => {
            extractSingleFontFamily("This is a Text,,This is a Text")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("4", () => {
        let callFunction = () => {
            extractSingleFontFamily("foo bar,foo bar")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("5", () => {
        let callFunction = () => {
            extractSingleFontFamily(undefined)
        }
    
        expect(callFunction).not.toThrow()
    })
})

// @ponicode
describe("parseFontString", () => {
    test("0", () => {
        let callFunction = () => {
            parseFontString("George")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("1", () => {
        let callFunction = () => {
            parseFontString("Michael")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("2", () => {
        let callFunction = () => {
            parseFontString("Pierre Edouard")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("3", () => {
        let callFunction = () => {
            parseFontString("Edmond")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("4", () => {
        let callFunction = () => {
            parseFontString("Jean-Philippe")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("5", () => {
        let callFunction = () => {
            parseFontString(undefined)
        }
    
        expect(callFunction).not.toThrow()
    })
})

// @ponicode
describe("utils.extractFont", () => {
    test("0", () => {
        let callFunction = () => {
            utils.extractFont({ fontFamily: "hsl(10%,20%,40%)", fontSize: 5, fontWeight: { toString: () => "2019-06-01" }, fontStyle: "hsl(10%,20%,40%)" })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("1", () => {
        let callFunction = () => {
            utils.extractFont({ fontFamily: "rgb(20%,10%,30%)", fontSize: 5, fontWeight: { toString: () => "2019-06-01" }, fontStyle: "rgb(0,100,200)" })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("2", () => {
        let callFunction = () => {
            utils.extractFont({ fontFamily: "black", fontSize: 1, fontWeight: { toString: () => "2019-10-01-preview" }, fontStyle: "red" })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("3", () => {
        let callFunction = () => {
            utils.extractFont({ fontFamily: "green", fontSize: 1, fontWeight: { toString: () => "2020-03-01" }, fontStyle: "black" })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("4", () => {
        let callFunction = () => {
            utils.extractFont({ fontFamily: "#F00", fontSize: 3, fontWeight: { toString: () => "2019-10-01-preview" }, fontStyle: "rgb(0,100,200)" })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("5", () => {
        let callFunction = () => {
            utils.extractFont(undefined)
        }
    
        expect(callFunction).not.toThrow()
    })
})

// @ponicode
describe("utils.extractAlignment", () => {
    test("0", () => {
        let callFunction = () => {
            utils.extractAlignment("justify")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("1", () => {
        let callFunction = () => {
            utils.extractAlignment("center")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("2", () => {
        let callFunction = () => {
            utils.extractAlignment("left")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("3", () => {
        let callFunction = () => {
            utils.extractAlignment("start")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("4", () => {
        let callFunction = () => {
            utils.extractAlignment("right")
        }
    
        expect(callFunction).not.toThrow()
    })

    test("5", () => {
        let callFunction = () => {
            utils.extractAlignment(undefined)
        }
    
        expect(callFunction).not.toThrow()
    })
})

// @ponicode
describe("strLen", () => {
    test("0", () => {
        let callFunction = () => {
            strLen({ length: 10 })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("1", () => {
        let callFunction = () => {
            strLen({ length: 16 })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("2", () => {
        let callFunction = () => {
            strLen({ length: 64 })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("3", () => {
        let callFunction = () => {
            strLen({ length: 32 })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("4", () => {
        let callFunction = () => {
            strLen({ length: 256 })
        }
    
        expect(callFunction).not.toThrow()
    })

    test("5", () => {
        let callFunction = () => {
            strLen({ length: Infinity })
        }
    
        expect(callFunction).not.toThrow()
    })
})
