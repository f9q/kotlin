package com.example.kotlin

import android.util.Log
import java.lang.IllegalArgumentException


val TAG_ENUM = "enum"

//1.每一个枚举都是枚举类的实例，它们可以被初始化
enum class Color{
    RED,BLACK,BLUE,GREEN,WHITE
}

enum class ColorInt(val rgb: Int) {
    RED(0xFF0000),
    GREEN(0x00FF00),
    BLUE(0x0000FF)
}

enum class EOO{
    GPP_0,
    GPP_1,
    GPP_2,
}

enum class WZ(var string : String){
    WORD("word"){
        fun fe() {

        }
    },
    WORK("work"),
    WOLF("wolf");

    fun wzf(){

    }
}

fun enum_test1(){

    var eoo = EOO.GPP_0
    var colo1 = ColorInt.BLUE

    var word = WZ.WORD
    var work = WZ.WORK

    var eoo2 = EOO.valueOf("GPP_0")    // 转换指定 name 为枚举值，若未匹配成功，会抛出IllegalArgumentException
    var values = EOO.values()           // 以数组的形式，返回枚举值


}


//2.一个枚举类可以实现接口,但不能继承其它类

open class Base2
interface InterfaceEm2  {   fun iem()   }

//enum class EM2 : Base2()      //error 能继承其它类
enum class EM2 constructor(string: String): InterfaceEm2 {

    EM2_VALUE1(string = "value1"),
    EM2_VALUE2(string = "value2"),
    EM2_VALUE3(string = "value3"),

    ;

    //枚举类定义任何成员要在“;”后
    override fun iem() {
    }
    inner class Oee(){

    }
    interface Ooe {
    }

    constructor( n : Int):this(""){

    }
}
enum class EM22 constructor(): InterfaceEm2 {

    EM22_VALUE1{
        override fun iem() {    //每个枚举值各自实现接口InterfaceEm2的方法
        }
    },
    EM22_VALUE2{
        override fun iem() {    //每个枚举值各自实现接口InterfaceEm2的方法
        }
    },
    EM22_VALUE3{
        override fun iem() {    //每个枚举值各自实现接口InterfaceEm2的方法
        }
    },

    ;

    //枚举类定义任何成员要在“;”后
    override fun iem() {
    }
    inner class Oee(){

    }
    interface Ooe {
    }

}

fun enum_test2(){

    var v1 = EM2.EM2_VALUE1
    var em2 = EM22.EM22_VALUE3

}

//3.使用枚举常量
enum class EM3(val text : String){
    EM3_1("EM3_1_text"){
        init{
            Log.e(TAG_ENUM,"EM3_1.super.name = ${super.name}")
        }
    },
    EM3_2("EM3_2_text"){
        init{
            Log.e(TAG_ENUM,"EM3_2.super.name = ${super.name}")
        }
    },
}

fun enum_test3(){
    var em3_values = EM3.values()
    for (value in em3_values){
        Log.e(TAG_ENUM,"value = $value")
    }


    for (enum in enumValues<EM3>()){
        Log.e(TAG_ENUM,"value = $enum")
    }

    var valeName = "EM3_1"
    try {
        var em3_1 = EM3.valueOf(valeName)
        Log.e(TAG_ENUM,"value of $valeName found ")
    }catch (e : IllegalArgumentException){
        Log.e(TAG_ENUM,"not found $valeName ")
    }

}


fun enum_test() {
    enum_test1()
    enum_test2()
    enum_test3()
}
