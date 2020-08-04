package com.example.kotlin

import android.util.Log


const val TAG_OBJECT = "object"

fun object_test(){
    obj_test1()
    obj_test2()
    obj_test3()
    obj_test4()
}


//1.

interface OBJ_I1{
    var txt : String
    fun test()
}
open class OB1{
    open fun f1(){
        Log.e(TAG_OBJECT,"OB1.f1() called")
    }
}

fun obj_test1(){

    var i1 = object : OBJ_I1{
        override fun test() {
            Log.e(TAG_OBJECT,"object test,txt = $txt")
        }
        override var txt: String = "object_I1"
            get() = field
            set(value) {field = value}

    }

    i1.test()


    var ob1 = object : OB1(){
        override fun f1() {
            Log.e(TAG_OBJECT,"object.f1() called")
        }
    }

    ob1.f1()
}

//2.匿名对象可以 用作 只在 本地和私有作用域 中声明的类型
//如果你使用匿名对象作为公有函数的返回类型或者用作公有属性的类型，那么该函数或属性的实际类型会是匿名对象声明的超类型，如果你没有声明任何超类型，就会是 Any
open class OB2(var value : Int){
    override fun toString(): String {
        return value.toString()
    }
}

class OB2_A{
    var ob2  = object : OB2(3){

    }
    var any = object {
        var count = 22
    }
}
fun obj_test2(){
    var ob2A = OB2_A()

    if (ob2A.ob2 is OB2){
        Log.e(TAG_OBJECT,"ob2A.ob2 is OB2 ,ob2 = ${ob2A.ob2}")
    }else{
        Log.e(TAG_OBJECT,"ob2A.ob2 is not OB2 ,ob2 = ${ob2A.ob2}")
    }

    var ret = ob2A.any is Any
    Log.e(TAG_OBJECT,"ob2A.any is Any = $ret")

}

//3.在公开匿名对象中添加的成员不可以被访问,私有的可以
open class ___OB3__{
    open var name : String = "none"
}
open class OB3 {

    private var pri_obj = object : ___OB3__(){
        var id = 100
    }
    public var pub_obj = object : ___OB3__(){
        var age = 22
    }

    fun showPrivateObj(){
        Log.e(TAG_OBJECT,"ob.name = ${pri_obj.name} , ob.id = ${pri_obj.id}")
    }
    fun showPublicObj(){
//        Log.e(TAG_OBJECT,"ob.name = ${pub_obj.name} , ob.age = ${pub_obj.age}") //error,age can not found
        Log.e(TAG_OBJECT,"ob.name = ${pub_obj.name} , ob.age = can not found")
    }


}

fun obj_test3(){

    var ob3 = OB3()

    ob3.showPrivateObj()
    ob3.showPublicObj()
}


//4.使用object + 类名 可以直接声明 一个 Singleton，且线程安全。

object OB4{
    var name = "OB4"
    fun f4(){
        Log.e(TAG_OBJECT,"OB4 is Singleton , f4() called")
    }
}

//单例对象可以有超类型
open class __OB_4(var name : String){
    open fun foo(){}
}
interface __OB4_I1 {
    fun fi1()
}

object OB4a : __OB_4("father"),__OB4_I1{
    override fun fi1() {
        Log.e(TAG_OBJECT,"OB4a.fi1 called")
    }
    override fun foo() {
        super.foo()
        Log.e(TAG_OBJECT,"OB4a.foo called")
    }
}


fun obj_test4(){
    var ob4 = OB4.name

    OB4.f4()

    var thread = Thread(Runnable { Log.e(TAG_OBJECT,"run in thread") })

    thread.start()

    OB4a.foo()
    OB4a.fi1()
}
