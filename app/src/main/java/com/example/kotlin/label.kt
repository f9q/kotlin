package com.example.kotlin
import android.util.Log

/*
   在 Kotlin 中任何表达式都可以用标签（label）来标记。
   标签的格式为标识符后跟 @ 符号，例如：abc@、fooBar@都是有效的标签。
   要为一个表达式加标签，我们只要在其前加标签即可。
*/

val TAG_LAbEL = "label"

fun label_test() : Unit{

    Log.e(TAG_LAbEL,"-----====----====-----")
    var idx = 0

    loop@ for (idx in 1..100) {
        Log.e(TAG_LAbEL,"i = $idx")
        idx.inc()
        if (idx > 50) break@loop
    }
    Log.e(TAG_LAbEL,"-----====----====-----")
    var ints = arrayOf(1,2,3,6,7,9,4,3,34,6,3,8,9,"word")

    ints.forEach foreach@ {
        if (it == 3) return@foreach
        Log.e(TAG_LAbEL,"it = $it")
    }

    Log.e(TAG_LAbEL,"-----====----====-----")
    ints.forEach {
        if (it == 3) return
        Log.e(TAG_LAbEL,"it = $it")
    }

    Log.e(TAG_LAbEL,"-----====----====-----")
    ints.forEach {
        if (it == 3) return@forEach //通常情况下使用隐式标签更方便,该标签与接受该 lambda 的函数同名。
        Log.e(TAG_LAbEL,"it = $it")
    }

    Log.e(TAG_LAbEL,"-----====----====-----")
    /*ints.forEach (fun(value: Int) {
        if (value == 0) return
    })*/
    foo()
}

fun foo() : Int{
    ff@ var v = 10

    return 1
}