console.log("Inici del programa")

setTimeout(() => {
    console.log("Primer TimeOut")
}, 2000)

setTimeout(fes1, 6000)
setTimeout(fes2, 1000)

function fes1() {
    console.log("fes1")
}

function fes2() {
    console.log("fes2")
}

console.log("Final del programa")