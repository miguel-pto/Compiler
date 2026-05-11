(module
  (import "runtime" "print" (func $print (param i32)))
  (import "runtime" "read" (func $read (result i32)))
  (memory 2000)
  (export "memory" (memory 0))
  (global $SP (mut i32) (i32.const 0))
  (global $MP (mut i32) (i32.const 0))
  (global $NP (mut i32) (i32.const 131072000))

  (func $reserveStack (param $size i32)
    global.get $SP
    local.get $size
    i32.add
    global.set $SP
  )

  (func $reserveHeap (param $size i32) (result i32)
    global.get $NP
    local.get $size
    i32.sub
    global.set $NP
    global.get $NP
  )

  (func $main
    ;; Guardar enlace dinamico: Memoria[SP] = MP viejo
    global.get $SP
    global.get $MP
    i32.store
    ;; Establecer nuevo MP (Mark Pointer)
    global.get $SP
    global.set $MP
    ;; Reservar espacio en el Stack: SP = SP + tamanoMarco
    global.get $SP
    i32.const 8
    i32.add
    global.set $SP
    global.get $MP
    i32.const 4
    i32.add
    i32.load
    call $print
    global.get $MP
    i32.const 4
    i32.add
    call $read
    i32.store
    global.get $MP
    i32.const 4
    i32.add
    i32.load
    call $print
    global.get $MP
    i32.const 4
    i32.add
    global.get $MP
    i32.const 4
    i32.add
    i32.store
    global.get $MP
    i32.const 4
    i32.add
    i32.load
    call $print
    ;; Epílogo final
    global.get $MP
    global.set $SP
    global.get $MP
    i32.load
    global.set $MP
  )
  (func $init_global
    i32.const 0
    global.set $SP
    global.get $SP
    global.set $MP

    call $main
  )
  (export "main" (func $init_global))
)
