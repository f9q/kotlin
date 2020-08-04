package com.example.kotlin

import android.util.Log

val TAG_TYPE : String = "base_type"

const val PI = 3.14159265359

fun type_main(vararg string: String) : Unit {

    var byte : Byte = 1

    var short : Short = 32_76

    var long : Long = 0xff_00_32

    var float : Float = 1_2_0.2f

    byte = 0b1010

    long = 91120L

    var double : Double = 9e10

    var int : Int = 1_2_0

    var str : String = "eefefefee"

    Log.e(TAG_TYPE,"byte == short is : " + (byte == short.toByte()))
    Log.e(TAG_TYPE,"byte == byte is : " + (short === short))


    var bit:Int = 0b1
    long = 1L

    Log.e(TAG_TYPE,"bit = " + bit)

    bit = bit.shl(1)
    Log.e(TAG_TYPE,"bit << 1 = " + bit)

    bit = bit.shr(1)
    Log.e(TAG_TYPE,"bit >> 1 = " + bit)

    bit = bit.ushr(2)
    Log.e(TAG_TYPE,"bit >>> 2 = " + bit)

    long = long.and(bit.toLong())
    Log.e(TAG_TYPE,"1L & ${bit.toLong()}L = " + long)

    bit = bit.xor(12)
    Log.e(TAG_TYPE,"bit ^ 12 = " + bit)

    bit = bit.inv()
    Log.e(TAG_TYPE,"~bit = " + bit)

    bit = bit.inc()
    Log.e(TAG_TYPE,"bit ++ = " + bit)

    bit = bit.dec()
    Log.e(TAG_TYPE,"bit -- = " + bit)

    var ch:Char = 'a'

//    int = 'b'

    if (ch.toInt() == 97){
        Log.e(TAG_TYPE,"a.value \t= " + ch.toInt())
    }

    //数组
    var a = arrayOf(1,2,3,4,5)
    var b = Array(1,{j -> (j + 2)})
    var ia = IntArray(5,{1})

    for (i in ia){
        Log.e(TAG_TYPE,"ia[i] = ${ia[i]}")
    }

    var string : String = "hello world"

    Log.e(TAG_TYPE,"string[3] = ${string[3]}")

    var text : String = """ hello kottlin,i'm your father."""

    Log.e(TAG_TYPE,"multi = $text")



}