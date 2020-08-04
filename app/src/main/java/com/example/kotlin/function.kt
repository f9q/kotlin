package com.example.kotlin

import android.util.Log


val TAG : String = "function"

inline fun sum(a : Int,b : Int):Int{
    return a + b
}

inline fun sum(a :Int,b : Int,c : Int) = a + b + c

inline fun log1(a:Int,b : Int) : Unit{

}

fun fun_in_fun(){

    fun feee() {
        Log.e(TAG,"fun_in_fun")
    }
    feee()

}


fun fun_test1(){

    Log.e(TAG,sum(1,2).toString())

    Log.e(TAG,sum(3,4,5).toString())

    vargs(1,2,3,4,6,7,8)

    var sumLmd : (Int,Int) -> Int = {x,y -> x + y}
    var strLmd : (Int) -> String = {x-> x.toString()}

    Log.e(TAG,sumLmd(1,2).toString())

    Log.e(TAG,strLmd(100))

    var str1 : String = "abcdefg"
    var value = 100

    Log.e(TAG,"value = $value")

    Log.e(TAG,"str1.length = ${str1.length}")

    Log.e(TAG,"str1.length = " + str1.length)

    var str2 : String? = "aaa"

    val age : Int = str2!!.length

    Log.e(TAG,(str1 is String ).toString() + " age !is int = " + (age !is Int))

    testRange()

    fun_test3()
}
fun testRange() : Unit{
    var arr = intArrayOf(0,2,7,5,4,9,3,1,10,6)

    Log.e(TAG,"---======------=====-------=====------------" )
    for (i in arr.indices){
        Log.e(TAG,"arr[i] = ${arr[i]}" )
    }

    Log.e(TAG,"---======------=====-------=====------------" )
    for (i in 0 until arr.size step 1){
        Log.e(TAG,"arr[$i] = ${arr[i]}" )
    }

    Log.e(TAG,"---======------=====-------=====------------" )
    for (i in arr.size - 1 downTo 0 step 1){
        Log.e(TAG,"arr[$i] = ${arr[i]}" )
    }

    Log.e(TAG,"---======------=====-------=====------------" )
    for (i in 5..8 ){
        Log.e(TAG,"arr[$i] = ${arr[i]}" )
    }

    Log.e(TAG,"---======------=====-------=====------------" )
    for (i in 1..5 step 2){
        Log.e(TAG,"arr[$i] = ${arr[i]}" )
    }
}
fun testRange1() : Unit = Unit

fun testVar(obj : Any){

    val a : Int

    var f : Float = 3.0f

    var x = 10

    a = 100

    Log.e(TAG,"a = " + a.toString())

}

//可变长参数函数
fun vargs(vararg args: Int){
    for (i in args){
        Log.e(TAG,"i = $i")
    }
}

fun f1(x : Int,array : IntArray) : Int {

    /*for (i : Int in array){
        if (x == i) return i;
    }*/

    for (i in array.indices){
        if (array[i] == x) return i
    }

    return -1
}


internal fun foo(float: Float) : Int{
    return -1
}
private fun foo(i : Int,j : Any){

}
public fun foo(i : Int){

}

//1.函数的参数必需有类型。
//fun f1( age = 1)  = 0                     //error,虽然有默认值，也必需要指定类型。
fun f1(age : Int = 1,name : String) = 0


//2.覆盖方法总是使用与基类型方法相同的默认参数值。 当覆盖一个带有默认参数值的方法时，必须从签名中省略默认参数值.
open class F2{
    open fun f2( age : Int = 1, name : String){

    }
}
class F2_sub : F2(){
    override fun f2(age: Int /*= 1*/, name: String) {   //这里不能再次指定默认值。父类已经指定。
        super.f2(age, name)
    }
}

//3.如果一个默认参数在一个无默认值的参数之前，那么该默认值只能通过使用命名参数调用该函数来使用

fun f3(age : Int = 22,name : String) = 0
fun f3(age : Int = 22,name : String,id : Int = 0) = 0

fun fun_test3(){
    f3(age = 19,name = "jacky") //ok,正确调用方式。
    f3(name = "lisa",age = 25)  //ok,虽然顺序不是声明时的，但是指定形参后可以调用。
    f3(name = "ashe")           //ok，不指定age，使用默认值。
    //f3("ashe")                //error,
    //f3(age = 24,"hei")        //error,
    //f3("hei",age = 24)        //error,
}

//4.如果在默认参数之后的最后一个参数是 lambda 表达式，那么它既可以作为命名参数在括号内传入，也可以在括号外传入

fun f4(size : Int = 16, lmda : (Int,Int) -> Int) : Int{
    var x = 32
    var y = 16
    var sum = lmda(x,y)
    return if(sum > size) size else sum
}

fun fun_test4(){

    var sz1 = f4(size = 1){
        x : Int ,y : Int -> Int
        if (x > y) x else y
    }

    var sz2 = f4(size = 2,lmda = {
            x : Int ,y : Int -> Int
        if (x < y) x else y
    })

    Log.e(TAG,"sz1 = $sz1, sz2 = $sz2")
}

//5.当一个函数调用混用位置参数与命名参数时，
//所有位置参数都要放在第一个命名参数之前。例如，允许调用 f(1, y = 2) 但不允许 f(x = 1, 2)

fun f5(age : Int = 10,name : String = "none", id : Int){

}
fun fun_test5(){
    f5(9,"name",5)
}

//6.可变参数
fun f6(age : Int = 19,id : Int = 0,name : String = "li",vararg args : String){
    var sz = args.size
    Log.e(TAG,"f6(age : Int = 19,id : Int = 0,name : String = \"li\",vararg args : String)")

    Log.e(TAG,"there are $sz args")

    for (i in args.indices){
        Log.e(TAG,"arg[$i] = ${args[i]}")
    }
}
fun f6(vararg args : String){
    var sz = args.size
    Log.e(TAG,"f6(vararg args : String)")

    Log.e(TAG,"there are $sz args")

    for (i in args.indices){
        Log.e(TAG,"arg[$i] = ${args[i]}")
    }
}

//只有一个参数可以标注为 vararg。如果 vararg 参数不是列表中的最后一个参数，要使用命名参数语法传递其后的参数的值
//fun f6(vararg args : String,vararg int: Int) = Unit
fun f6(vararg args : String, int : Int) = Unit


fun fun_test6(){
    f6()
    f6("one","two","three","four","five","six","seven","eight")
    f6(args = *arrayOf("1","2","3","5"))    //可以通过使用星号操作符将可变数量参数（vararg） 以命名形式传入

    f6("e","e","e",int = 3) //如果 vararg 参数不是列表中的最后一个参数，要使用命名参数语法传递其后的参数的值
}


fun fun_test(){
    fun_test1()
    fun_test3()
    fun_test4()
    fun_test5()
    fun_test6()
}