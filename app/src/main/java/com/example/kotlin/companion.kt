package com.example.kotlin

import android.util.Log


const val TAG_COMPANION = "companion"

fun companion_test(){
    cp_test1()
    cp_test2()
    cp_test3()
    cp_test5()
}

//1.该伴生对象的成员可通过只使用类名作为限定符来调用,

class CP1{
    companion object Factory{
        fun create() : CP1 = CP1()
    }
    fun f1(){
        Log.e(TAG_COMPANION,"CP1.f1() called")
    }
}
fun CP1.Factory.f2(){
    Log.e(TAG_COMPANION,"CP1.Factory.f2() called")
}

fun cp_test1(){
    var cp1 : CP1 = CP1.create()
    cp1.f1()

    CP1.Factory.f2()
    CP1.f2()
    //cp1.f2()
}

//2.可以省略伴生对象的名称，在这种情况下将使用名称 Companion
class CP2{
    companion object /*NAME*/ {
        fun create() : CP1 = CP1()
    }
    fun f1(){
        Log.e(TAG_COMPANION,"CP2.f1() called")
    }
}

fun CP2.Companion.ext1(){
    Log.e(TAG_COMPANION,"CP2.ext1() called")
}

fun cp_test2(){
    var cp2 = CP2.create()
    cp2.f1()

    CP2.ext1()
}

//3.companion ？
open class CP3{

    @JvmField var number = 10

    companion object static {
        var id = 2
        @JvmField var age = 18
    }
    fun setId(value : Int){
        id = value
    }

    override fun toString(): String {
        return "[id:$id,age:$age]"
    }
}

fun cp_test3(){

    CP3.id = 888
    CP3.age = 24

    var cp1 = CP3()
    var cp2 = CP3()
    var cp3 = CP3()
    var cp4 = CP3()

    Log.e(TAG_COMPANION,"cp1 = $cp1 , cp2 = $cp2 , cp3 = $cp3 , cp4 = $cp4")

    CP3.static.id = 20  //ok
    CP3.id = 10         //ok
    cp1.setId(2046)     //ok

    CP3.age = 99        //ok
    //cp1.id = 20       //error
    //static.id = 99    //error,直接使用伴生对象的名字.成员 也不可以

    Log.e(TAG_COMPANION,"cp1 = $cp1 , cp2 = $cp2 , cp3 = $cp3 , cp4 = $cp4")

}

//4.伴生对象不是静态成员
class CP4{

    //非伴生对象成员 虽然可以使用 @JvmField 声明，但是无意义，不可以用 CP4.value 访问。
    @JvmField var value = 100

    companion object {
        @JvmField var v = 100
        @JvmStatic fun getV() : Int{
            return  v
        }
    }
}
fun cp_test4(){
    var cp4 = CP4()
    //var value = CP4.value   //error,

    Log.e(TAG_COMPANION,"非伴生对象成员 虽然可以使用 @JvmField 声明，但是无意义，不可以用 CP4.value 访问")

    CP4.v = 10

}

//5.伴生对象可以实现其它类和接口
interface __I5__{
    fun f5() = 0
}
open class _Base5_ {

}
class CP5 {

    companion object Action : _Base5_(), __I5__{
        var txt = "hello "
        override fun f5(): Int {
            super.f5()
            Log.e(TAG_COMPANION,"CP5.Action.f5() ")
            return 10
        }
    }
}



fun cp_test5(){
    var cp5 = CP5

    CP5.f5()

}