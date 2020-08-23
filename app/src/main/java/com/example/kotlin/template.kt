package com.example.kotlin

import android.util.Log

val TAG_TEMPLATE = "template"


//类模板
fun test_template1(){

    //类模板
    class A <T> (var t : T ) { override fun toString(): String { return "t : $t" } }

    val t1 = A(1)
    Log.e(TAG_TEMPLATE,"test_template1 t1 : $t1")

    val t2 = A("string template")
    Log.e(TAG_TEMPLATE,"test_template1 t2 : $t2")

    val t3 = A(1314.0f)
    Log.e(TAG_TEMPLATE,"test_template1 t3 : $t3")

    class Student(val name : String = "z3") { override fun toString(): String { return "student.name = $name" } }
    val t4 = A<Student>(Student())
    Log.e(TAG_TEMPLATE,"test_template1 t4 : $t4")
    val st = Student()
}

//函数模板
fun test_template2(){

    //1.简单函数模板
    class Student(val name : String = ""){ override fun toString(): String { return "name = $name" } }

    fun <T> fun1( t : T ) {
        Log.e(TAG_TEMPLATE,"test_template2 t : $t")
    }

    fun1(1)
    fun1(1.0f)
    fun1(Student("li3 "))


    //2.函数模板 不支持默认实参
    //fun <T> fun2( t : T = T() ) { }  //不支持默认实参


    //3.函数参数、返回值 用类模板
    class A <T> (var t : T ) { override fun toString(): String { return "t = $t" } }
    fun <T> fun3(t : T) : A <T>{
        return A(t)
    }
    val ret = fun3(1)

}

fun template_test(){

    test_template1()

    test_template2()

}