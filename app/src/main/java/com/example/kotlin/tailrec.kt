package com.example.kotlin

import android.os.SystemClock
import android.util.Log

val TAG_TAILREC = "tailrec"


//1.尾递归条件： 1.最后一条语句是函数调用，且调用的自己

class TreeNode{
    var left    : TreeNode?     =   null
    var right   : TreeNode?     =   null
    var value   : Int           =   0
}
tailrec fun btree(root : TreeNode?) {
    if (root?.left != null) {
        btree(root.left)
    }
    println("value = ${root?.value}")
    if (root?.right != null){
        btree(root.right)
        println()
    }
}
tailrec fun factorial(n : Int) : Int{
    return if (n == 1) 1 else n * factorial1(n - 1)
}

//2.示例
fun factorial1(n : Int) : Int{
    return if (n == 1) 1 else n * factorial1(n - 1)
}

var ret2 = 1
tailrec fun factorial2(n : Int) : Int {
    if (n <= 1) return 1
    ret2 *= n
    return factorial2(n - 1)
}

var ret3 = 1
fun factorial3(n : Int) : Int {
    if (n <= 1) return 1
    ret3 *= n
    return factorial3(n - 1)
}

fun tailrec_test(){
    var start = SystemClock.elapsedRealtimeNanos()
    val ret1 = factorial1(6)
    var end = SystemClock.elapsedRealtimeNanos()
    Log.e(TAG_TAILREC,"ret1 = $ret1 ,takes ${end - start}")


    start = SystemClock.elapsedRealtimeNanos()
    factorial2(6)
    end = SystemClock.elapsedRealtimeNanos()
    Log.e(TAG_TAILREC,"ret2 = $ret2 ,takes ${end - start}")

    start = SystemClock.elapsedRealtimeNanos()
    factorial3(6)
    end = SystemClock.elapsedRealtimeNanos()
    Log.e(TAG_TAILREC,"ret3 = $ret3 ,takes ${end - start}")

}