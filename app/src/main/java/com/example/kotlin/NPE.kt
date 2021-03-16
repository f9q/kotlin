package com.example.kotlin

import android.util.Log

const val TAG_NPE = "NPE"



fun npe_test(){

    npe_test1()
    npe_test2()
    npe_test3()
    npe_test4()
    npe_test5()
    npe_test6()
}

fun npe_test1(){
    val s1 : String = "abc"
    val s2 : String? = "abc"

    //s1 = null                 //error,声明时不可为空。

    var len = s1.length         //ok,保证s1不空
    len = s1?.length            //ok,保证s1不空

    //len = s2.length           //error,因为s2可以为空。
    //len = s2?.length

    if (s2 != null) {
        len = s2.length         //ok，有是否为空的判断
    }

    len = s2?.length ?: 1
}
fun npe_test2(){
    //如果调用链中的任何一个接收者为空都会跳过赋值，而右侧的表达式根本不会求值：
    //如果 `person` 或者 `person.department` 其中之一为空，后面函数不会被调用：
    //person?.department?.head = managersPool.getManager()
}

//3.Elvis 操作符.当且仅当左侧为空时，才会对右侧表达式求值。
fun npe_test3(){
    val str1 : String? = null

    val ch0 = str1?.get(0) ?: getCh0()

    Log.e(TAG_NPE,"ch0 = $ch0")

}

class NPE
fun getCh0() : NPE{
    Log.e(TAG_NPE,"Elvis ?: 符号两边的类型可以不一致。。")
    return NPE()
}


//4.非空断言运算符  !!  ,如果空，那么抛异常

fun npe_test4(){
    val str1 : String? = null

    try {

        val len = str1!!.length

        Log.e(TAG_NPE,"len = $len")
    }catch (ex : KotlinNullPointerException){
        Log.e(TAG_NPE,"len = -1, caught a exception = " + ex.message)
    }catch (e : Exception){
        Log.e(TAG_NPE,"caught a exception = " + e.message)
    }
}

//5.安全的类型转换
fun npe_test5(){
    val npe = NPE()
    val value = npe as? Int

    Log.e(TAG_NPE,"value = $value")

}

//6.可空类型的集合 ,过滤非空元素
fun npe_test6(){

    val nullableList : List<Int?> = listOf(1, 2, null, 4)
    for (i in nullableList.indices){
        Log.e(TAG_NPE, "nullableList[$i] = ${nullableList[i]}")
    }
    val intList : List<Int> = nullableList.filterNotNull()

    for (int in intList){
        Log.e(TAG_NPE,"value = " + int)
    }

    val array = arrayOf(1,2,null,"We",2.0f)

    val noNullArray = array.filterNotNull()
    for (i in nullableList.indices){
        Log.e(TAG_NPE, "noNullArray[$i] = ${noNullArray[i]}")
    }
    val intArr = array.filterNot { any : Any? ->  any !is Int }
    for (i in intArr.indices){
        Log.e(TAG_NPE,"intArr[$i] = ${intArr[i]}")
    }
}