package com.example.kotlin
import android.util.Log


const val TAG_INHERIT = "inherit"

private open class Animal constructor(var age : Int,var name :String) : Any(){

    override fun toString(): String {
        return super.toString()
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
//    constructor() : this(1,"none") {
//
//    }

    open fun move(){
        Log.e(TAG_INHERIT,"Animal moving")
    }
}

private class Bird constructor() : Animal(1,"bird"){

    constructor(age: Int,name: String):this(){

    }
    override fun move() {
        super.move()
        Log.e(TAG_INHERIT,"$name moving")
    }
}

/*
 *
 *如果子类没有主构造函数，则必须在每一个二级构造函数中用 super 关键字初始化基类，
 *或者在代理另一个构造函数。初始化基类时，可以调用基类的不同构造方法。
 *
 */
private class Fish : Animal {

    constructor():super(1,"fish"){

    }
    constructor(age: Int,name: String):super(age,name){

    }

    override fun move() {
        super.move()
        Log.e(TAG_INHERIT,"$name moving")
    }
    inline fun add(){

    }
}


interface Ia {
    val COUNT : Int
    fun fo(){
        Log.e(TAG_INHERIT,"Ia.fo()")
    }
}
interface Ib{
    fun fo(){
        Log.e(TAG_INHERIT,"Ib.fo()")
    }
}
open class B1{
    open val byte : Int = 20
    open fun fo(){
        Log.e(TAG_INHERIT,"B1.fo()")
    }
}
open class B2{
    open fun fo2(){
        Log.e(TAG_INHERIT,"B2.fo2()")
    }
}
class D1 : B1(),Ia,Ib{
//    可以用一个var属性重写一个val属性，但是反过来不行。因为val属性本身定义了getter方法，重写为var属性会在衍生类中额外声明一个setter方法
    override var byte: Int = 1
    override val COUNT: Int = 20

    override fun fo() {
        super<B1>.fo()
        super<Ia>.fo()
        super<Ib>.fo()
        Log.e(TAG_INHERIT,"D1.fo(), this.byte = ${this.byte}")
    }
}


inline fun inline_fun(){

    Log.e(TAG_INHERIT,"-----====----====-----")
    D1().fo()

    Log.e(TAG_INHERIT,"-----====----====-----")
    var b1 = B1()
    var d1 = D1()
    var d2 = D1()

//    d2 = b1   //error 子类不能指向父类
    b1 = d1
    b1.fo()

    Log.e(TAG_INHERIT,"-----====----====-----")
    d2.fo()
}



internal fun inherit_test(){

    var fish = Fish(7,"fish7")
    fish.move()

    fish = Fish()
    fish.move()

    inline_fun()
}