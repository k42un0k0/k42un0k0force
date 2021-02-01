export function cbToPromise(fn) {
    return (arg) => new Promise((resolve, reject) => fn(arg, (err, res) => {
        if (err == null) {
            resolve(res);
        } else {
            reject(err);
        }
    }))
}

export const IS_WINDOWS = process.platform === 'win32'