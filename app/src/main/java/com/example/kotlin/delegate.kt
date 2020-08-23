package com.example.kotlin

import android.util.Log
import kotlin.properties.Delegates
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

const val TAG_DELEGATE = "delegate"


fun delegate_test(){

    delegate_test1()
    delegate_test2()
    delegate_test3()
    delegate_test4()
    delegate_test5()
    delegate_test6()
    delegate_test7()
    delegate_test8()
    delegate_test9()
    delegate_test10()
    delegate_test11()
    delegate_test12()
    delegate_test13()
}


//1.委托示例，接口 by 对象

interface __base1__         { fun f2()    }

class base1Impl : __base1__ {
    override fun f2() {
        Log.e(TAG_DELEGATE,"base1Impl.f2()")
    }
}

class delegate1(val impl : __base1__) : __base1__ by impl


fun delegate_test1(){

    Log.e(TAG_DELEGATE,"====================================== delegate_test_ 1 ")

    val impl        =   base1Impl()
    val delegate1   =   delegate1(impl)

    delegate1.f2()

    val d2 = delegate1(object : __base1__{
        override fun f2() {
            Log.e(TAG_DELEGATE,"delegate1.companion.impl.f2()")
        }
    })

    d2.f2()
}

//2.只有接口能被委托，类不可以
open class deleagte_base2
open class delegate_sub1 : deleagte_base2()
//class delegate1(base1 : deleagte_base2) : deleagte_base2 by base1   //error 只有接口能被委托，类不可以

abstract class AB1{
    abstract fun f1()
}
//class D2 (val ab : AB1) : AB1 by ab         //error,抽象类也不可以委托。

fun delegate_test2(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_ 2 ")
    Log.e(TAG_DELEGATE,"只有接口能被委托，类不可以")
}

//3.不要重写委托接口的方法，否则调用的是重写的，不是委托的。

interface __I3__ {  fun f3() }

class I3Impl : __I3__{
    override fun f3() {
        Log.e(TAG_DELEGATE,"I3Impl.f3()")
    }
}

class delegate3(i3 : __I3__) : __I3__ by i3 {
    override fun f3() { //不要重写委托类的方法，否则调用的是重写的，不是委托的。
        Log.e(TAG_DELEGATE,"delegate3.f3()")
    }
}

fun delegate_test3(){

    Log.e(TAG_DELEGATE,"====================================== delegate_test_ 3 ")

    val im3 = I3Impl()

    val delegate3 = delegate3(im3)

    delegate3.f3()

}

//4.委托属性:系统定义的3个委托属性

//成员变量使用委托属性
class delegate4{

    val name : String by lazy(mode = LazyThreadSafetyMode.NONE){
        //mode = LazyThreadSafetyMode.PUBLICATION 表示关闭同步锁，默认开启同步锁的
        Log.e(TAG_DELEGATE,"lazy called ")
        "lazy"
    }

    var value : Int by Delegates.observable(initialValue = 0){
        prop,old,new ->
        Log.e(TAG_DELEGATE,"${prop.name} onChanged : old = $old,new = $new")
    }

    var count : Int by Delegates.vetoable(initialValue = 8){
            prop,old,new ->
        Log.e(TAG_DELEGATE,"${prop.name} onChanged : old = $old,new = $new ")
        false   //return false 表示不接受修改，本次修改被否决
    }

    val str : String by Delegates.notNull()     //在未初始化前使用，IllegalStateException。

}

fun delegate_test4(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_ 4 ")

    val d4 = delegate4()

    d4.value = 99

    Log.e(TAG_DELEGATE,"d4.name = ${d4.name}")

    d4.value = 1024

    d4.count = 2046

    d4.count = 328

    Log.e(TAG_DELEGATE,"d4.count = ${d4.count}")

//    Log.e(TAG_DELEGATE,"d4.str = ${d4.str}")    //error d4.str未初始化。
    300
}

//5.指定初始值时不能使用委托,
fun delegate_test5(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_5 ")

    //val noinit1 : Int = 10 by Delegates.notNull() //error,指定初始值时不能使用委托
    var noinit2 : Int by Delegates.notNull()        //ok
    noinit2 = 20

    //val size  = 64 by lazy {  Log.e(TAG_DELEGATE,"init"); 32 }    //error,size已经初始化。
    class d8{
        //var value = 64 by Delegates.notNull<Int>()                //error,value已经初始化。
    }
}


//6.类型可以省略。
fun delegate_test6(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_6 ")

    val notype1 by lazy { 3 }

    val notype2 by lazy { Log.e(TAG_DELEGATE,"init"); "notype2" }

    Log.e(TAG_DELEGATE,"notype1 = $notype1 , notype2 = $notype2")
}


//7.不能同时指定多个委托
fun delegate_test7(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_7 ")

    //val notype1 by lazy { 3 } ,Delegates.notNull()  //error
    val SIZE by lazy {  Log.e(TAG_DELEGATE,"init"); 32 }
    Log.e(TAG_DELEGATE,"SIZE = $SIZE,不能同时指定多个委托")
}


//8.不能对已经初始化的变量指定委托
fun delegate_test8(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_8 ")

}

//9.map 提供了委托函数
class D9(val map8 : Map<String,Any?>){
    val name    : String    by map8
    val id      : Int       by map8

    val T = 200
    //val age     : Int       by T        //error,Int 并没有提供委托函数。
    var age : Int  = T

    val mmap = mutableMapOf<String,Any?>()
    val desc : String by mmap

    init{
        mmap.put("desc","done")
    }

    override fun toString(): String {
        return "name = $name,id = $id,desc = $desc,age = $age"
    }
    fun reset(){
        mmap.put("desc","null")
        this.age = 0
        this::age
        ::age
    }
}

fun delegate_test9(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_9 ")
    val map = mutableMapOf<String,Any?>(
        "name"  to "sia"
        ,"age"  to 12
        ,"id"   to  3
    )

    val d9 = D9(map)

    Log.e(TAG_DELEGATE,"d9 = $d9")

    //d9本身的name和id不可修改，但是它的值来自外部map，当map改变时，它们也改了。
    map.put("name","nick")
    map.put("id",8)
    Log.e(TAG_DELEGATE,"d9 = $d9")

    d9.reset()
    Log.e(TAG_DELEGATE,"d9 = $d9")

}


//10.局部变量的委托示例
fun getMem() : Int{
    Log.e(TAG_DELEGATE,"getMem  ")
    return 99
}
fun example(compute : () -> Int) : Int{
    val mem by lazy(compute)
    Log.e(TAG_DELEGATE,"example  ")
    return mem
}

fun delegate_test10(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_10 ")

    val v = example(::getMem)

    Log.e(TAG_DELEGATE,"v = $v")
}



//11.委托的本质
//在每个委托属性的实现的背后，Kotlin 编译器都会生成辅助属性并委托给它。
//例如，对于属性 prop，生成隐藏属性 prop$delegate，而访问器的代码只是简单地委托给这个附加属性
/*
class C {
    val prop: Type by MyDelegate()
}

// 这段是由编译器生成的相应代码：
class C {
    private val prop$delegate = MyDelegate()
    val prop: Type
        get() = prop$delegate.getValue(this, this::prop)
    set(value: Type) = prop$delegate.setValue(this, this::prop, value)
}
*/
class lazy11(val default : Int = -1,val lmda : () -> Int) {
    operator fun provideDelegate( thisRef : Any,prop : KProperty<*>) : Delegate13 {
        return Delegate13(lmda)
    }
}

val i11 by lazy { 1 }

class D11{
    val MAX by lazy { 1024 }

    val lazy = lazy{ 9 }
    val value : Int by lazy

    val lazy11 = lazy11{8}
    val count : Int by lazy11
}

fun delegate_test11(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_11 ")

    val d11 = D11()
    Log.e(TAG_DELEGATE,"d11.value = ${d11.value},d11.count = ${d11.count} ,d11.MAX = ${d11.MAX}")
}


//12.自定义变量委托
//重载 operator fun setValue(thisRef: Any, property: KProperty<*>, value : T)
//或者 operator fun getValue(thisRef: Any, property: KProperty<*>): T
//的类就可以被变量委托

class BYY{
    var value : Int = -1

    operator fun setValue(thisRef: Any, property: KProperty<*>, v : Int) {
        Log.e(TAG_DELEGATE,"BYY.setValue")
        value = v
    }
    operator fun getValue(thisRef: Any, property: KProperty<*>): Int {
        Log.e(TAG_DELEGATE,"BYY.getValue")
        return value
    }
}
operator fun BYY.setValue(thisRef: Nothing?, property: KProperty<*>, v : Int){

}
operator fun BYY.get(thisRef: Nothing?, property: KProperty<*>) = value

class D12{
    var size  : Int by BYY()
}


//ReadOnlyProperty 和 ReadWriteProperty 两个接口定义了getValue与setValue 实现它们就可以被委托
class MyDelegate() : ReadOnlyProperty<DC12,Int>,ReadWriteProperty<DC12,Int>{
    var value : Int = 0
    override fun setValue(thisRef: DC12, property: KProperty<*>, v : Int) {
        Log.e(TAG_DELEGATE,"MyDelegate.setValue ")
        value = v
    }

    override fun getValue(thisRef: DC12, property: KProperty<*>) : Int {
        Log.e(TAG_DELEGATE,"MyDelegate.setValue ")
        return value
    }
    constructor(lmda : ()-> Int) : this (){
        value = lmda()
    }
}
fun getNum() : Int{
    Log.e(TAG_DELEGATE,"getNum ")
    return 33
}

class DC12{
    var value   : Int by MyDelegate()
    val value2  : Int by MyDelegate(::getNum)
}

fun delegate_test12(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_12 ")
    val d12 = D12()
    d12.size = 128
    Log.e(TAG_DELEGATE,"d12.size = ${d12.size}")

    val dc12 = DC12()
    dc12.value = 64
    Log.e(TAG_DELEGATE,"dc12.value = ${dc12.value},dc12.value2 = ${dc12.value2}")
}


//13.运算符 provideDelegate 提供委托
//如果 by 右侧所使用的对象将 provideDelegate 定义为成员或扩展函数，那么会调用该函数来创建属性委托实例。

class Delegate13 (lmda: () -> Int) : ReadOnlyProperty<Any,Int>,ReadWriteProperty<Any,Int>{
    var COUNT : Int
    init{
        COUNT = lmda()
    }
    override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) {
        Log.e(TAG_DELEGATE,"Delegate13.setValue")
        COUNT = value
    }
    override fun getValue(thisRef: Any, property: KProperty<*>): Int {
        Log.e(TAG_DELEGATE,"Delegate13.getValue")
        return COUNT
    }
}
class mutableLazy(var default : Int = -1,var lmda : () -> Int) {
    operator fun provideDelegate( thisRef : Any,prop : KProperty<*>) : Delegate13 {
        return Delegate13(lmda)
    }
}

class D13{
    var value : Int by mutableLazy{
        Log.e(TAG_DELEGATE,"mutableLazy.lmda")
        99
    }
}

fun delegate_test13(){
    Log.e(TAG_DELEGATE,"====================================== delegate_test_13 ")

    val d13 = D13()
    Log.e(TAG_DELEGATE,"d13.value = ${d13.value}")

    d13.value = 299
    Log.e(TAG_DELEGATE,"d13.value = ${d13.value}")
}