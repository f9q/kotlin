package com.example.kotlin

import android.util.Log

val TAG_VARARG = "vararg"


//1.简单示例：作用与调用方法
fun fun1(vararg numbers : Int ){
    val sz = numbers.size
    for (i in 0 until sz){
        Log.e(TAG_VARARG,"fun1 bumber[$i] = ${numbers[i]}")
    }
    for (num in numbers){
        Log.e(TAG_VARARG,"fun1 $num")
    }
}
fun vararg_test1(){
    //1.简单调用
    fun1(1,2,3,4,5)
    fun1()  //实参可以个数为0
    fun1(1,)  //最后一个是',‘

    //2.使用*加数组名调用
    val array = intArrayOf(6,7,8,9,0)
    fun1(*array)

    val array2 = ArrayList<Int>()
    fun1(*array2.toIntArray())

    //3.无效调用
    val array3 = ArrayList<Int>()
    val f1 = 3.14f;
    val i2 = 100
//    fun1(*f1) //error
//    fun1(*i2) //error
//    fun1(*array3) //error,ArrayList不是数组

}

//2.可变参数的个数只能是一个
//fun fun2(vararg args : Int,vararg args2 : Int){}
fun vararg_test2() = Unit


//3.与数组之间的区别：数组参数只能传数组对象。

//3.1 在顶级定义相同类型的变参与数组生成的函数签名相同
/*
fun fun3(vararg numbers : Int){
}
fun fun3(numbers : IntArray){
}
class JJJJJJ{
    fun fun33(vararg numbers : Int){
    }
    fun fun33(numbers : IntArray){
    }
}
fun vararg_test3_1(){}
*/

//3.2 局部范围内定义相同类型的变参与数组可共存
fun vararg_test3_2(){
    fun fun3(vararg numbers : Int){
        Log.e(TAG_VARARG,"fun3(vararg)  ${numbers[0]}")
    }
    fun fun3(numbers : IntArray){
        Log.e(TAG_VARARG,"fun3(IntArray)  ${numbers[0]}")
    }
    fun fun3(numbers : Array<Int>){
        Log.e(TAG_VARARG,"fun3(Array<Int>)  ${numbers[0]}")
    }
    val array1 = intArrayOf(1,2,3,4)
    val array2 = arrayOf(5,6,7,8)
    fun3(0)   //调用的是fun3(vararg)
    fun3(array1)        //调用的是fun3(IntArray)
    fun3(array2)        //调用的是fun3(Array<Int>)
}
//3.3 数组参数只能传数组对象
fun vararg_test3_3(){
    fun fun3(numbers : IntArray)  {
    }
    fun fun3(vararg numbers : Int)  {
    }
    val array = intArrayOf(1,2,3)
//    fun3(1,1f)            //error，只能传数组参数
    fun3(array)
    fun3(1)     //可以传类型
}

//3.4 数组对象可返回，变参不可返回
fun fun3_4() : IntArray? = null
//fun fun3_4(a:Int) : (vararg arg : Int)? = null  //error

//4.可变参数的位置：前，中、后,当可变参数不是最后一个时，其它参数要显示指定才可以
fun fun4(vararg args : Int,name : String,i : Int = 0) = Unit
fun fun4(i : Int = 1,vararg args : Int,name : String) = Unit
fun fun4(name : String,i : Int = 2,vararg args : Int) = Unit

fun vararg_test4(){
    fun4(1,name = "前",i = 99)       //调用第1个
    fun4(1,name = "前")                  //调用第1个

    fun4(2,3,name = "中")         //调用第2个
    fun4(2,name = "中")                  //调用第2个

    fun4(name = "后",i = 10,2,3,4)   //调用第3个
    fun4("后",2,5,6,7)     //调用第3个
}

//5.lambda的参数不支持可变参数
//fun fun5(vararg lambdas : (vararg lmd2 : Int) -> Int)){} //error
//fun fun5(num : Int, lmd : (vararg lmd2 : Int) -> Int){}//error
fun vararg_test5() = Unit

//6.可变参数的类型是函数藪lamuda表达式
fun fun6(vararg lambdas : (a : Int,b : Int) -> Int) : Int{
    var total = 0
    for (i in 0 until lambdas.size){
        val lambda = lambdas[i]
        total += lambda(i,10)
    }
    return total
}
fun add(a : Int,b : Int) = a + b
fun vararg_test6(){
    val sum1 = fun6(::add,::add,::add,::add)
    Log.e(TAG_VARARG,"sum1 = $sum1")

    val sum2 = fun6({x,b-> 3 }, ::add,::add)
    Log.e(TAG_VARARG,"sum2 = $sum2")
}
//7.模板
fun<T,U> fun7(u : U,vararg args : T) = Unit
fun vararg_test7(){
    fun7(1,1,2,3,4,)
}

//8.变参可以有默认值
fun fun8(vararg args : Float = FloatArray(8)){
    Log.e(TAG_VARARG,"args.size = ${args.size}")
}
fun vararg_test8(){
    fun8()                  //结果 8
    fun8(1f,2f)      //结果 2
    val floatArray = FloatArray(4,)
    fun8(*floatArray,)      //结果 4
}

fun vararg_test(){
    vararg_test1()
    vararg_test2()
//    vararg_test3_1()
    vararg_test3_2()
    vararg_test3_3()

    vararg_test4()
    vararg_test5()
    vararg_test6()
    vararg_test7()
    vararg_test8()
}