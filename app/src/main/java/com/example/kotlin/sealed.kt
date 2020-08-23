package com.example.kotlin

import android.util.Log

val TAG_SEALED = "sealed"


//一个密封类是自身抽象的，它不能直接实例化并可以有抽象（abstract）成员
sealed class S1 /*public*/ constructor(float: Float){ //（其构造函数默认为 private）

//    public constructor(string: String) //error,密封类不允许有非-private 构造函数。
}

data class SF(var f : Float)        : S1(f)
data class SD(var string: String)   : S1(0.0f)
data class SI(var config: BuildConfig): S1(0.8f)


fun sealed_test(){
//    sealed class S1e  //error,密封类不能是本地的
//    var s1 = S1()     //error,一个密封类是自身抽象的，它不能直接实例化并可以有抽象（abstract）成员

    val sf = SF(0.2f)

    when(sf){
        is SF -> Log.e(TAG_SEALED," type is SF")
//        is SD -> Log.e(TAG_SEALED," type is SD")
    }
}