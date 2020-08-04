package com.example.kotlin

import android.util.Log


var TAG_DATA = "data"

interface I1



data class Station(var id : Int) : Comparable<Station>{
    override fun compareTo(other: Station): Int {
        return other.id - this.id
    }

}


fun data_test1(){

    //主构造函数至少包含一个参数。
    //所有的主构造函数的参数必须标识为val 或者 var ;
    //数据类不可以声明为 abstract, open, sealed 或者 inner;

    data class U1(var id : Int,var name : String) {

        constructor( int : Int) : this(int,"none"){
        }

    }

    data class U2(var id : Int)

    open class A { val id = 10 }

    data class U3(var type : Int) : A(),I1 {

    }


}

fun data_test2(){
    data class U(var id : Int,var name : String) {

        constructor( int : Int) : this(int,"none"){
        }

    }
    var u1 = U(2,"name")
    var u2 = u1.copy(id = 5)
    var (id1,name1) = u1
    var (id2,name2) = u2

    Log.e(TAG_DATA,"id1 = $id1 ,name1 = $name1 , id2 = $id2 name2 = $name2")

}
fun data_test(){

    data_test1()

    data_test2()

    data_test3()
}

data class PCV(var age : Int,var name : String){
    override fun toString(): String {
        return "name = $name,age = $age"
    }
}

fun data_test3(){
    var pcv = PCV(name = "imna",age = 19)

    Log.e(TAG_DATA,"pcv = $pcv")
}