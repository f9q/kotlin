package com.example.kotlin

import android.util.Log
import java.util.HashMap

val TAG_TEMPLATE = "template"

//类模板
fun test_template1(){

    class Student(val n : String)  { override fun toString(): String { return "student.name = $n" } }

    //简单类模板
    open class A <T> (var t : T )  { override fun toString(): String { return "t = $t" } }

    A(1).let                    { Log.e(TAG_TEMPLATE,"A <Int>     : $it")       }

    A("string template").let    { Log.e(TAG_TEMPLATE,"A <String>  : $it")       }

    A<Float>(1314.0f).let       { Log.e(TAG_TEMPLATE,"A <Float>   : $it")       }

    A(Student("li3")).let       { Log.e(TAG_TEMPLATE,"A <Student> : $it")       }

}


interface TIF <T>
interface TIF2 <E> : TIF <Int>  //接口模板继承某个特定类型
interface TIF3 <U> : TIF <U>    //接口模板继承接口模板

fun test_template2(){

    class A     : TIF<Int>  //实现接口模板

    open class  B <T> ()
    class C     : B<Int>()  //普通类继承类模板
    class D<N>  : B<N>()    //类模板继承类模板


    open class  E <T,U>     //多个参数类模板
    class F     : E <Int,Float>()
    class G <T> : E <T,Int>()

}


//函数模板
fun test_template3(){

    //1.简单函数模板
    class Student(val name : String = ""){ override fun toString(): String { return "name = $name" } }

    fun <T> fun1( t : T ) {
        Log.e(TAG_TEMPLATE,"test_template3 t : $t")
    }

    fun1(1)
    fun1(1.0f)
    fun1(Student("li3 "))


    //2.函数模板 不支持默认实参
    //fun <T> fun2( t : T = Int ) { }  //不支持默认实参


    //3.函数参数、返回值 用类模板
    class A <T> (var t : T ) { override fun toString(): String { return "t = $t" } }
    fun <T> fun3(t : T) : A <T>{
        return A(t)
    }
    fun3(1).let {  Log.e(TAG_TEMPLATE,"test_template3 it: $it") }

}



//4.型变
fun test_template4(){
    class A<in T>(){}
    class B<out T>(){}
    val arr : ArrayList<Int>
    var list : List<Int>
    val map : HashMap<Int,Int>

}

fun template_test(){

    test_template1()
    test_template2()
    test_template3()
    test_template4()

}