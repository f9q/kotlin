package com.example.kotlin

import android.util.Log

val TAG_INFIX = "infix"


//1.要求
//必须是成员函数或扩展函数
//函数只能有一个参数
//函数的形参不能是变参
//函数的形参不能有默认值
//在使用infix语法调用时必需指定接收者
infix fun Int.suffix(subfix : String) = "$this-$subfix"

fun infix_test1_1(){
    val str1 = 1.suffix("sss")
    val str2 = 1 suffix "aaa"

    Log.e(TAG_INFIX,"str1 = $str1")
    Log.e(TAG_INFIX,"str2 = $str2")
}

//不可有默认实参
//infix fun Int.Max(max : Int = 64) = max  //error


//指定接收者
fun infix_test1_2(){
    class JJJ {
        infix fun prefix(prefix : String) = "$prefix-$this"
        fun test(){
            prefix("EE")     //ok
            this prefix "HHH"       //ok
            //prefix "jjj"            //error
        }
    }
}


//2.它的优先级在  ..  之后， 在  ?: 之前
fun infix_test2(){

    infix fun Int.suffix(subfix : String) = "$this-$subfix"
    infix fun Int.MAX(max : Int ) = max

    val str1 = 1.suffix("sss")
//    val str2 = "eee" + 2 suffix "aaa"  //error
    val str3 = "eee-" + (3 suffix "aaa")  //ok
    val str4 = null ?: 4 suffix "bbb"
    var str5 = ""
    for (i in 0 until (3 MAX 6)){
        str5 += "$i"
    }

    Log.e(TAG_INFIX,"str1 = $str1")
    Log.e(TAG_INFIX,"str2 = error")
    Log.e(TAG_INFIX,"str3 = $str3")
    Log.e(TAG_INFIX,"str4 = $str4")
    Log.e(TAG_INFIX,"str5 = $str5")
}

fun infix_test(){
    infix_test1_1()
    infix_test1_2()
    infix_test2()
}