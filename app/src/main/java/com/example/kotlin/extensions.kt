package com.example.kotlin

import android.util.Log

const val TAG_EXTENSIONS = "extensions"


open class Base1{
    var base = 100
}
open class Source : Base1(){
    var COUNT = 1
    fun fooooo() : Int {
        return super.base.inc()
    }
}

//1.自定义的类 扩展函数,里面可以使用this,但是不可以用super
inline fun Source.addBase(x : Int){
//  var ret = this.COUNT + x + super.base //error,不可以用super
    var ret = this.COUNT + x + fooooo()
    Log.e(TAG_EXTENSIONS, "count = ${this.COUNT} ,ret = $ret" )
}
fun test1(){
    var a = Source()
    a.addBase(3)

}
//2.扩展函数的重载版本
inline fun Source.addBase(string : String){
    var x = string.toInt()
    var ret = this.COUNT + x + fooooo()
    Log.e(TAG_EXTENSIONS, "count = ${this.COUNT} ,ret = $ret" )
}
fun test2(){
    var a = Source()
    a.addBase(3)
    a.addBase("1000")

}
//3.预定义类 扩展函数
fun Int.add(source: Source) =  source.base.dec()

fun test3(){
    var a = Source()

    val int = 100
    var ret = int.add(a)
    Log.e(TAG_EXTENSIONS,"ret = $ret")
}
//4.final的类也可以扩展函数
fun String.add(ext : String?) : String?{
    return  toString() + " $ext"
}
fun test4(){
    var str1 = "hello"
    var str2 = "world"
    var str3 = str1.add(str2)
    Log.e(TAG_EXTENSIONS,"str3 = $str3")
}


//5.父类的 扩展函数 子类可以使用
open class User{
    /*protected*/
    var age = 22
    open fun name(){

    }
}
class EUser : User() {
}
fun User.age() : Int{
    return this.age
}
fun EUser.eage() : Int{
    return this.age
}

fun test5(){
    var user = User();
    var name = user.name()
    var age = user.age()
    var eUser = EUser()

    age = eUser.age()
//    user.eage()   //error,子类的扩展函数不能用于父类
}

//6.扩展函数 与多态
open class C6{
    open fun f6() = "c6_f6"
}
open class D6 : C6(){
    override fun f6() = "d6_f6"
}

fun C6.foo() = "c6"
fun D6.foo() = "d6"

fun test6(){
    var c6 = C6()
    var d6 = D6()
    var objC6 : C6 = D6()

    var foo = objC6.foo()   //这里调用的是C6.foo()
    var f6 = objC6.f6()

    Log.e(TAG_EXTENSIONS,"foo = $foo , f6 = $f6")
}

fun test6(int: Int){

    open class A

    open class Base {
        open fun A.foo() {
            Log.e(TAG_EXTENSIONS,"A.foo in Base.")
        }
        fun caller(a : A) {
            a.foo()
        }
    }
    class Derived : Base() {
        override fun A.foo() {
            Log.e(TAG_EXTENSIONS,"A.foo in Derived")
        }
    }
    var oa = A()
    var base = Derived()
    base.caller(oa)

}


//7.接口支持扩展函数,但是不用实现扩展的函数
interface IF1{
    fun foo(){
        Log.e(TAG_EXTENSIONS,"c7.foo")
    }
}
class C7 : IF1{
    override fun foo() {
        super.foo()
        Log.e(TAG_EXTENSIONS,"c7.foo")
    }
}
fun IF1.fext(){
    Log.e(TAG_EXTENSIONS,"IF1.fext")
}
fun test7(){
    var obj = object : IF1{
        override fun foo() {
            super.foo()
        }
    }
    obj.fext()
    var c7 = C7()

    c7.fext()
}


//8.扩展函数与成员函数相同时，优先使用类内的同名函数。
open class C8{
    var value = 0
    inline fun add(v : Int){
        this.value += v
        Log.e(TAG_EXTENSIONS,"member.add(Int) value = $value")
    }
}
fun C8.add(v: Int) {
    this.value += v * 2
    Log.e(TAG_EXTENSIONS, "extension.add(Int) value = $value")
}
fun C8.add(f: Float) {
    this.value += (f * 2).toInt()
    Log.e(TAG_EXTENSIONS, "extension.add(Float) value = $value")
}

internal fun test8(){
    var c8 = C8()

    c8.add(2)

    c8.add(3.0f)

}


//9.扩展静态函数
class C9{
    companion object{
        fun getNum() = 10
    }
}
fun C9.Companion.foo() = 20

fun test9(){
    var num = C9.foo()
    Log.e(TAG_EXTENSIONS,"C9.externion static foo = " + num)
}

//10.扩展属性,扩展属性允许定义在类或者kotlin文件中，不允许定义在函数中。
//扩展可属性因为没有后端字段（backing field），所以不允许被初始化，只能由显式提供的 getter/setter 定义。
class C10{
    fun f10(){
    }
}

//给C10 扩展一个不可修改的属性
val C10.SIZE : Int get()  = 10

fun test10(){
    var c10 = C10();
    Log.e(TAG_EXTENSIONS,"c10.SIZE = ${c10.SIZE} ")
}



//11.给C11 扩展一个可修改的属性

class C11

internal var c11_num = 20

var C11.num : Int
    get() {
        return c11_num
    }
    set(value) {
        c11_num = value
    }
fun test11(){
    var c11 = C11();
    var num = c11.num;

    c11.num = 20 * 2

    Log.e(TAG_EXTENSIONS,"c10.num = ${c11.num} , num = $num ")
}

//12.对类内部扩展
open class C12{
    var num12 = 12
    inner class CIn12{
    }
    fun login(){
        Log.e(TAG_EXTENSIONS,"C12.login() called ")
    }
    open fun CIn12.f12(){
        login()
        Log.e(TAG_EXTENSIONS,"CIn12.f12() called,in12 = $in12")
    }
    val CIn12.in12
        get() = 24

    fun t12(){
        var cIn12 = CIn12()
        cIn12.f12()
    }
}

class C12_sub : C12(){
    override fun CIn12.f12() {
        Log.e(TAG_EXTENSIONS,"C12_sub.CIn12.f12() called,in12 = $in12")
    }
}
fun test12(){
    C12().t12()
}


fun test13(){
    //不用继承,不用生成对象，就可以访问另一个类的成员
    data class A(var name : String,var age : Int){
        fun id() = 1
        fun name() = "nik"
    }
    data class B(var id : Int) {
        fun A.fid(){
            var id = id()
        }
        fun A.fn(){
            var name = name()
        }
    }
}

enum class EN{
    EN_H {
        override fun getValue() = EN_J
    },
    EN_J{
        override fun getValue(): EN = EN_H
    };

    abstract fun getValue() : EN
}

fun EN.eno(){

}

data class KOOE(val int: Int){

}
fun KOOE.f1(){

}

sealed class SS{

}

fun SS.fooe(){

}

fun extensions_test(){

    test1()
    test2()
    test3()
    test4()
    test5()
    test6()
    test6(1)
    test8()
    test7()
    test9()
    test10()
    test11()
    test12()
    test13()
}