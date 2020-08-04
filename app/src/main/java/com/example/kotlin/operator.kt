package com.example.kotlin

import android.util.Log

const val TAG_OPERATOR = "operator"


fun operator_test(){

    op_test1()
    op_test2()
    op_test3()
    op_test4()
    op_test5()

    op_test6()

    op_test7()
}



/**
 * 重载操作符的函数需要用 operator 修饰符标记.成员重载和扩展重载
 * 这些操作符具有固定的符号和固定的优先级。,如 unaryMinus 表示 求负号(相反数)
 *
 * */


//1.成员重载
data class OP1(var value : Int){
    operator fun unaryMinus() = OP1(-(value / 2))
}

//扩展重载
operator fun OP1.unaryPlus() = OP1(+ (value * 2))


fun op_test1(){

    var op1 = OP1(66)

    Log.e(TAG_OPERATOR,"op1 = $op1")

    var fop1 = -op1
    Log.e(TAG_OPERATOR,"-op1 = $fop1")

    var zop1 = +op1
    Log.e(TAG_OPERATOR,"+op1 = $zop1")

}


//2.a++,a-- 它们不应该改变调用都原来的值，它们产生副本。

data class OP2(var value : Int){

    override fun toString(): String {
        return value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (other !is OP2 || other == null ) return false
        var op2 = other as OP2
        return op2.value - this.value == 0
    }

    operator fun inc() : OP2 = OP2(value + 10)
}

operator fun OP2.dec() : OP2 = OP2(value - 10)

fun op_test2(){
    var op2 = OP2(33)
    var op3 = ++op2

    Log.e(TAG_OPERATOR,"op2 === op3 is ${op2 === op3} , op2 = $op2  op3 = $op3")
}

//3.常用算术运算符
data class OP3(var value: Int = 0){
    override fun toString() : String {
        return value.toString()
    }
}
operator fun OP3.plus(op3 : OP3)= OP3(value + op3.value)
operator fun OP3.plus(x : Int)  = OP3(value + x)
operator fun OP3.minus(x : Int) = OP3(value - x)
operator fun OP3.times(x : Int) = OP3(value * x)
operator fun OP3.div(x : Int)   = OP3(value / x)
operator fun OP3.rem(x : Int)   = OP3(value % x)

operator fun OP3.divAssign(x : Int){
    value /= x
}

fun op_test3(){

    Log.e(TAG_OPERATOR,"____op_test3___")

    var op3 = OP3()
    Log.e(TAG_OPERATOR,"op3 = $op3")

    var ret = op3 + 5
    Log.e(TAG_OPERATOR,"op3 + 5 = $ret")

    var op4 = OP3(100)
    Log.e(TAG_OPERATOR,"op4 = $op4")

    ret = op3 + op4
    Log.e(TAG_OPERATOR,"op3 + op4 = $ret")

}


//4. in , [i]
data class OP4(var array : Array<Int>){
    override fun toString(): String {
        var sb = StringBuffer()
        for (value in array){
            sb.append(value.toString() + ",")
        }
        return sb.toString()
    }
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
    override fun hashCode(): Int {
        return array.hashCode()
    }
}

operator fun OP4.contains(x : Int?) : Boolean{
    if (null == x) return false
    Log.e(TAG_OPERATOR,"operator in called")
    return array.contains(x)
//    return true
}
operator fun OP4.get(i : Int) : Int?{
    if(i > -1 && i < array.size){
        return array[i]
    }
    return null
}
operator fun OP4.get(i_1 : Int,i_2: Int,i_n: Int) : Int?{
    return null
}

fun op_test4(){
    Log.e(TAG_OPERATOR,"____op_test4___")

    var arr = arrayOf(1,3,6,8,9,5,20)
    var op4 = OP4(array = arr)

    Log.e(TAG_OPERATOR,"op4 = $op4")

    var x = 10000

    if (x in op4){
        Log.e(TAG_OPERATOR,"$x in {$op4}")
    }else{
        Log.e(TAG_OPERATOR,"$x !in {$op4}")
    }

    Log.e(TAG_OPERATOR,"op4[1000] = ${op4[1000]} ,op4[0] = ${op4[0]} ")

}


//5.仿函数
data class OP5(var value : Int = 9){
    override fun toString(): String {
        return value.toString()
    }
}

operator fun OP5.invoke(){
    Log.e(TAG_OPERATOR,"invoke() called")
}
operator fun OP5.invoke(x : Int,y : Int) : Int{
    Log.e(TAG_OPERATOR,"invoke() called")
    return x + y
}

fun op_test5(){

    Log.e(TAG_OPERATOR,"\n\n____op_test5___\n\n")

    var op5 = OP5(1024)

    op5()

    var sum = op5(1 , 2)

    Log.e(TAG_OPERATOR,"op(1,2) = $sum")

}

//6.比较操作符 == !=
// 它们只使用函数 equals(other: Any?): Boolean ，不能重载出其它版本。如 operator fun equals(op6 : OP6) : Boolean
// === 和 !==（同一性检测）不可重载，因此不存在对他们的约定

data class OP6(var value: Int = 9) {
    override fun toString(): String {
        return value.toString()
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}

fun op_test6(){

}


//7.比较操作符 > <  >= <=
//a > b	    a.compareTo(b) > 0
//a < b	    a.compareTo(b) < 0
//a >= b	a.compareTo(b) >= 0
//a <= b	a.compareTo(b) <= 0

data class OP7(var value: Int = 0){
    override fun toString(): String {
        return value.toString()
    }

    operator fun compareTo(x : Int) : Int = value - x
}

operator fun OP7.compareTo(op7 : OP7) : Int = this.value - op7.value
        operator fun OP7.compareTo(op7 : String) : Int {
    var op7 = OP7(op7.toInt())
    return this.value - op7.value
}


fun op_test7(){
    Log.e(TAG_OPERATOR,"\n\n____op_test7___\n\n")

    var op7 = OP7(1000)
    var compare = op7 > 7
    Log.e(TAG_OPERATOR,"op7 > 7 = $compare")

    compare = op7 > "7"
    Log.e(TAG_OPERATOR,"op7 > \"7\" = $compare")

    compare = OP7(23) >= op7
    Log.e(TAG_OPERATOR,"OP7(23) >= op7 = $compare")
}


