#!/usr/bin/env node

const { readFileSync } = require("fs");
const readline = require('readline');
const { WebAssembly } = global;

const insrc = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

let entrada = [];
let i = 0;

async function readInput(n) {
    var line;
    for await (line of insrc) {
        entrada.push(parseInt(line));
        n--;
        if (n == 0) return;
    }
    insrc.close();
}

var importObjects = {
    runtime: {
        print: function(n) {
            console.log(n);
        },
        read: function() {
            let val = entrada[i];
            i += 1;
            return val;
        }
    }
};

async function start(wasmFile) {
    const code = readFileSync(wasmFile);
    wasmModule = await WebAssembly.compile(code);
    instance = await WebAssembly.instantiate(wasmModule, importObjects);
    await instance.exports.main();
}

async function run() {
    const args = process.argv.slice(2);
    
    if (args.length === 0) {
        console.log("Uso: node runtime.js <archivo.wasm> <num_inputs>");
        console.log("Ejemplo: node runtime.js salida.wasm 2");
        process.exit(0);
    }

    const wasmFile = args[0];
    const numInputs = args.length > 1 ? parseInt(args[1], 10) : 0;

    if (numInputs > 0) {
        console.log(`Introduce ${numInputs} números (uno por línea):`);
        await readInput(numInputs);
    }
    
    await start(wasmFile);

    process.exit(0);
}

run();