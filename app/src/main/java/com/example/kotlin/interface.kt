package com.example.kotlin

const val TAG_INTERFACE = "interface"

interface Interface1{

//    接口中的属性只能是抽象的，不允许初始化值，接口不会保存属性值，实现接口时，必须重写属性
    var name:String

    fun f1()
    fun getAge(){

    }
}

interface Interface2{
    fun f1()            //函数可以没有实现体
}
interface Interface3{

    fun f1(){

    }
}

fun interface_test(){

    open class A : Interface1{
        override var name: String = "A"

        override fun getAge() {
            super.getAge()
        }

        override fun f1() {

        }
    }

    class B : A(),Interface1,Interface2,Interface3{
        override fun f1() {
            super<A>.f1()
            super<Interface3>.f1()
        }
    }

}