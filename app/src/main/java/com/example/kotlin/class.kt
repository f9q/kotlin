package com.example.kotlin

import android.util.Log


val TAG_CLASS = "class"


class A constructor(var id : Int,var text : String){

    private companion object{
        var idx = 0
        @Synchronized
        fun nextId() : Int{
            return ++idx
        }
    }

    init {
        Log.e(TAG_CLASS,"A.init.1 called")
        init()
    }
    init {
        Log.e(TAG_CLASS,"A.init.2 called")
    }
    init {
        Log.e(TAG_CLASS,"A.init.n called")
    }
    private fun init(){
        nextId()
    }
    private constructor(text: String) : this(nextId(),text){
        Log.e(TAG_CLASS,"A.init.1 called")
    }

    constructor() : this(nextId(),"none"){
        Log.e(TAG_CLASS,"A.init.1 called")
    }

    constructor(i : Int = 1,float:Float = 2.02f,d : Double) : this(nextId(),"none"){
        Log.e(TAG_CLASS,"A.init.1 called")
    }

    fun foo(){
        Log.e(TAG_CLASS,"id = $id, text = $text")
    }

}

/*
如果一个非抽象类没有声明构造函数(主构造函数或次构造函数)，它会产生一个没有参数的构造函数。构造函数是 public 。
如果你不想你的类有公共的构造函数，你就得声明一个空的主构造函数：
 */
class B private constructor(){
    var id = 1
}

class Singleton private constructor(){

    companion object{
        private var instance : Singleton? = null
        /*@Synchronized get()  {
            if (field == null){
                field = Singleton()
            }
            return field
        }*/

        @Synchronized fun getInstance() : Singleton? {

            oo@ if (instance == null){
                instance = Singleton()
            }
            return instance
        }
        private var x = 1
    }

    @Synchronized
    fun foo(){
        Log.e(TAG_CLASS,"I'm a Singleton.")
    }
}

class Person constructor( x : Int) {

    var age : Int = x ; get() = field ; set(v){ field = v * 2}

    private var name : String? = null

    constructor(x : Int,name: String) : this(x) { //如果类定义了主构造器，次构造器必须直接或间接调用主构造器；
        this.name = name
    }

    constructor() : this(20) { //如果类定义了主构造器，次构造器必须直接或间接调用主构造器；
        name = "none"
    }

}

class Student /*如果主构造器没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略。*/ {
    private var age = 0
    private var name : String ? = null
    private lateinit var city : String

    fun sayHi(){
        Log.e(TAG_CLASS,"I'm a student.")
    }

    constructor(age: Int,name: String,city:String) {
        this.age = age
        this.name = name
        this.city = city
    }
    constructor(){
        this.age = 0
        this.name = "none"
        this.city = "none"
    }
}

class Teacher constructor(age:Int,name:String) {

    private var age = age
    private var name : String ? = name
    private lateinit var city : String

    fun sayHi(){
        Log.e(TAG_CLASS,"I'm a teacher.")
    }
}


/*
 * 抽象类
 * 抽象是面向对象编程的特征之一，类本身，或类中的部分成员，都可以声明为abstract的。抽象成员在类中不存在具体的实现。
 * 注意：无需对抽象类或抽象成员标注open注解。
*/
private abstract class Base {
    open fun sayHi(){
        Log.e(TAG_CLASS,"base say hi")
    }
    final fun foo2(){

    }
    protected fun foo(text : String){

    }
}

private class  Derived1 : Base() {
    override fun sayHi() {
        super.sayHi()
        Log.e(TAG_CLASS,"Derived1 : hi ")
    }
}

private class Derived2 : Base() {
    override fun sayHi() {
        super.sayHi()
        Log.e(TAG_CLASS,"Derived2 : hi ")
    }
}


//嵌套类、内部类、匿名内部类
class Outer{
    val id = 10
    inner class Inner{
        fun foo(){
            var outer = this@Outer
            Log.e(TAG_CLASS,"id = $id ,outer.id = ${outer.id}")
        }
    }

    class Nested{
        fun foo(){
            Log.e(TAG_CLASS,"""can not get id,because no "inner" keyword before.""")
        }
    }

}

interface IFace{
    fun method1(){
        Log.e(TAG_CLASS,"IFace.m1")
    }
    fun method2(){
        Log.e(TAG_CLASS,"IFace.m2")
    }
    fun method3(){
        Log.e(TAG_CLASS,"IFace.m3")
    }
    fun method4()
}
fun testIFace(iFace: IFace){

    iFace.method1()
    iFace.method2()
    iFace.method3()

}

fun out_inner(){
    var outer = Outer()
    var inner = outer.Inner()
    var nested = Outer.Nested()

    inner.foo()
    nested.foo()

//    var base = Base()
//    var iFace = IFace()

    testIFace(object : IFace{
        override fun method4() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

    })
}

fun class_test(){

    var stu = Student()

    stu.sayHi()

    var stu2 = Student(12,"li","BeiJing")
    stu2.sayHi()


    var person1 = Person(20,"san")
    var person2 = Person()

    person1.age = 3000

    Log.e(TAG_CLASS,"person1.age = ${person1.age}")


    var oa = A(20,"hello")
//    oa.id = 40
    oa.foo()

    Singleton.getInstance()!!.foo()

    var d1 = Derived1()
    d1.sayHi()

    var d2 = Derived2()
    d2.sayHi()

    out_inner()

}