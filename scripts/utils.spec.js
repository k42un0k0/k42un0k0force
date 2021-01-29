const { cbToPromise, sjisToUtf8, IS_WINDOWS } = require("./utils");

describe("cbToPromise", () => {
    test("callback to promise with resolve", () => {
        function someCallback(arg, cb) {
            cb(undefined, arg);
        }
        const promisedFn = cbToPromise(someCallback)

        const actual = "hello"
        return promisedFn(actual).then((res) => {
            expect(res).toBe(actual)
        })
    })

    test("callback to promise with reject", () => {
        function someCallback(_, cb) {
            cb(Error("faild by some error"));
        }
        const promisedFn = cbToPromise(someCallback)

        return expect(promisedFn("hello")).rejects.toThrow("faild by some error")
    })
})
