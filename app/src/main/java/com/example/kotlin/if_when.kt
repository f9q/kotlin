package com.example.kotlin

import android.util.Log


val TAG_IF : String = "if_when"


fun if_when(){

    val bool = true

    if (bool){
        Log.e(TAG_IF,"bool = true")
    }else{
        Log.e(TAG_IF,"bool = fase")
    }

    var result = if (2 > 1){
        3
    }else{
        5
    }
    Log.e(TAG_IF,"result = $result")

    var x = 7
    var y = 10
    if (x in 1..3){
        Log.e(TAG_IF,"x = $x in 1..3")
    }else{
        Log.e(TAG_IF,"x = $x !in 1..3")
    }

    when(x){
        1 -> Log.e(TAG_IF," x = 1")
        3 -> Log.e(TAG_IF," x = 3")
        !in 3..6 -> Log.e(TAG_IF,"x != 3..6")
        in 4..5 -> Log.e(TAG_IF,"x in 4..5")
        in 8..10 -> Log.e(TAG_IF," x in 8...10 ")
        else -> {
            Log.e(TAG_IF,"default ,x = $x")
        }
    }
}