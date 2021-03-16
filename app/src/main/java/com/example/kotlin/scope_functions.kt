package com.example.kotlin

import android.os.SystemClock
import android.util.Log
import kotlin.random.Random
import kotlin.time.nanoseconds

val ScopeTAG = "ScopeFunction"

class ScopeFun1{
    var id = 0
    var name = "unknown"

    fun f1(){
        println("ScopeFun1 f1()")
    }

    fun f2(){
        println("ScopeFun1 f2()")
    }
}


fun scope_test1(){
    val cf = ScopeFun1()

//    ScopeFun1().let(::leeeeeeeeeeeet1)     //这种方式不可以在let1中使用it
    cf.let {                    //这种方式可以在{}内可以使用it 访问cf对象
       it.f1()
    }

    cf.run {
        f1()
        f2()
    }

    ScopeFun1().apply {
        id = 10
        name = "apply1"
    }
    cf.also{
        it.f2()
    }
    with(cf){
        this.f1()
    }
    cf.runCatching {

    }
    cf.takeIf {
        it.f1()
        false
    }
    run {

    }
}


//--------------------


fun testLet() {

    class Scope2(var name : String)

    //1.最后一行是返回值
    val num = 12.let {
        3
        Log.e(ScopeTAG, "testLet it = $it")
    }
    Log.e(ScopeTAG, "testLet num = $num")


    //2.函数式调用
    fun <T> leeeeeeeeeeeet1(it : T){
        Log.e(ScopeTAG,"testLet let1<T> it = $it")
    }
    13f.let(::leeeeeeeeeeeet1)

    //3.非空值调用 let
    val flt = 13f.let { 16 }
    Log.e(ScopeTAG, "testLet flt = $flt")

    //4.非空对象调用
    val fullName = Scope2("not-").let {
        val last = "null"
        it.name += last
        Log.e(ScopeTAG, "testLet name append $last ")
        it.name
    }
    Log.e(ScopeTAG, "testLet fullName = $fullName ")


    //5.空对象调用
    val sc2 : Scope2 ? = null
    val name2 = sc2  ?. let {
        it.name += "fff" //虽然sc2 为 null ,但是由于是sc2 ?. 调用，所以在{}内不用以 ?.方式使用it
    }
    Log.e(ScopeTAG, "testLet name2 = $name2 ")


    //6.将表达式作为变量引入为局部作用域中
    1 + (2 * 300) .let {
        Log.e(ScopeTAG, "testLet 1 + (2 * 300) = $it ")
    }
    1.let {
//        it += 2 // error 1.also  1 是常量
        Log.e(ScopeTAG, "testLet it = $it")
    }

    //7.静态类或者伴生对象.let,访问静态成员
    Int.let {
        //对Int的伴生对象调用let
        Log.e(ScopeTAG, "testLet Int.MIN_VALUE = ${it.MIN_VALUE} , SIZE_BYTES = ${it.SIZE_BYTES} , it = $it")
    }
}



class Apply{
    companion object{
        var value = 2046
        override fun toString(): String {
            return "value = $value"
        }
    }
}
object Apply2{
    var value = 99
    override fun toString(): String { return """value = $value""" }
}
class Apply3{
    object Static1{
        var value1 = 99
        override fun toString(): String { return """value1 = $value1""" }
    }
    object Static2{
        var value2 = 99
        override fun toString(): String { return """value2 = $value2""" }
    }
}
fun testApply(){
    class Scope3 {
        var name    = ""
        var age     = 0
        var first   = ""
        var last    = ""
        var address = ""
        var phone   = ""


        override fun toString(): String {
            return "name = $name,age = $age,first = $first,last = $last,address = $address,phone = $phone"
        }
    }
    //1.主要用来配置对象
    val sc3 = Scope3().apply {
        name    = "eot"
        age     = 12
        first   = "li"
        last    = "per"
        address = "ooeoeooe"
        phone   = "13xxxxxxx"
    }

    Log.e(ScopeTAG,"testApply sc3 : $sc3")

    //2.静态类或有内部静态类可以使用类名.apply{}
    val a0 = Apply.apply { value = 11 }
    Log.e(ScopeTAG,"testApply a0 : $a0")

    val a4 = Apply2.apply { value = 55 }
    Log.e(ScopeTAG,"testApply s4 : $a4")

    val a3 = Apply3.Static1.apply { value1 = 66 }
    Log.e(ScopeTAG,"testApply a3 : $a3")

    //kotlin内置常用类都有静态内部类
    val a1 = Int.apply {
        123
        12 * 23
    }
    val a2 = String.apply { """hello"""}
    Log.e(ScopeTAG,"testApply a1 : $a1 ,a2 : $a2")

    //3.没有静态内部类的不支持,编译不过
    class Apply4 { var value = 22}
//    val a6 = Apply4.apply { value = 77}
//    Log.e(ScopeTAG,"testApply a6 : $a6")

}


fun testRun(){
    //1.全局函数版
    run {
        Log.e(ScopeTAG,"testRun 全局函数版: 运行代码")
    }
    val i3 = 122 * 2 + run { Log.e(ScopeTAG,"testRun 全局函数版: 在表达式中运行语句"); 23 } + 34
    Log.e(ScopeTAG,"testRun 全局函数版: i3 = $i3 (122 * 2 + 23 + 34)")

    //2.扩展函数版本
    class A(var name : String){ fun query() : String = "name = $name" }

    val result = A("null").run {
        this.name = "r1"
        query()
    }
    Log.e(ScopeTAG,"testRun result = $result")

}

fun testWith(){
    //1.全局函数版
    with(3) {
        Log.e(ScopeTAG,"testWith this = $this")
    }

    //2.扩展函数版本
    class A(var name : String)

    val w1 = A("null")
    Log.e(ScopeTAG,"testWith r1.name = ${w1.name}")
    with(w1) {
        this.name = "w1"
        Log.e(ScopeTAG,"testWith name = $name")
    }

    //3.同理，对静态类或者有伴生对象的类
    with(Int){
        Log.e(ScopeTAG, "testWith Int.MIN_VALUE = ${this.MIN_VALUE} , SIZE_BYTES = ${this.SIZE_BYTES} , it = $this")
    }
    with(Apply2){
        Log.e(ScopeTAG, "testWith Apply2.value = ${this.value} , this = $this")
    }

    //4.空对象
    val nullInt : Int? = null
    with(nullInt){
        Log.e(ScopeTAG, "testWith nullInt = $this")
    }

    //5.在表达式中
    val ret = 12 + 29 * with(3){//注意优先级 12 + (29 * 6) = 186
        this * 2
    }
    Log.e(ScopeTAG, "testWith ret = $ret")

}

object Also{ var value = 1 ; override fun toString(): String { return "value = $value" } }

fun testAlso(){
    //1,对象并且执行的
    val name = "testAslo-hello"
    val sub = name.substringAfter("-").also {
        it.toUpperCase()
    }
    Log.e(ScopeTAG, "testAlso sub = $sub")

    //2,静态类、伴生对象
    val a1 = Int.also { 23 }
    Log.e(ScopeTAG, "testAlso a1 = $a1")
    val a2 = Also.apply { value = 39 }
    Log.e(ScopeTAG, "testAlso a2 = $a2")

    //3,空对象
    val nil : Also? = null
    nil?.also {
        it.value = 122
    }
    Log.e(ScopeTAG, "testAlso nil = $nil")

}

fun testTake(){

    val now = SystemClock.elapsedRealtime()
    val tif = now.takeIf {
        Log.e(ScopeTAG,"testTake takeIf now = $it")
        it % 2  == 0L
    }
    Log.e(ScopeTAG,"testTake tif = $tif")

    val tus = now.takeUnless {
        Log.e(ScopeTAG,"testTake takeUnless now = $it")
        it % 2 == 0L
    }
    Log.e(ScopeTAG,"testTake tus = $tus")
}
fun scope_test2(){
    val numbers = mutableListOf("one", "two", "three", "four", "five")
    numbers.map { it.length }.filter { it > 3 }.let {
        Log.e(ScopeTAG,"numbers = $it")
    }
}

fun scope_test(){

    Log.e(ScopeTAG,"====================================== scope_test ")

//    scope_test1()
//    scope_test2()
    testLet()
    testApply()
    testRun()
    testWith()
    testAlso()
    testTake()
    class eooe{
        var eoe = lambda@ fun (name : String) : Int{
            return 1
        }
        fun fff(){
            val oe = 3 * eoe.invoke("33")
        }
    }
}