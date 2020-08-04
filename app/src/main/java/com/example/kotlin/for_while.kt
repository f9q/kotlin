package com.example.kotlin

import android.util.Log

val TAG_WHILE : String = "for_while"

fun for_while(){

    var list = listOf("one","two","three","four","five","six","seven","eight","nine","ten")

    Log.e(TAG_WHILE,"-----====----====-----")
    for (str : String in list){
        Log.e(TAG_WHILE,"str = $str")
    }

    Log.e(TAG_WHILE,"-----====----====-----")

    for (idx in list.indices){
        Log.e(TAG_WHILE,"str[$idx] = ${list[idx]}")
    }

    Log.e(TAG_WHILE,"-----====----====-----")
    for (iv in list.withIndex()){
        Log.e(TAG_WHILE,"index = ${iv.index} , value = ${iv.value} ")
    }

    Log.e(TAG_WHILE,"-----====----====-----")
    for ((index,value) in list.withIndex()){
        Log.e(TAG_WHILE,"index = $index, value = $value ")
    }


    Log.e(TAG_WHILE,"-----====----====-----")
    var i = 0;
    while (++i < 10){
        Log.e(TAG_WHILE,"i = $i")
        if (i == 5 ) continue
    }

    Log.e(TAG_WHILE,"-----====----====-----")
    do {
        Log.e(TAG_WHILE,"i = $i")
        if (i == 15) break
    }while (++i < 20)


}