package com.example.kotlin

import android.content.Context
import android.os.Bundle
import androidx.annotation.IntDef
import androidx.annotation.StringDef
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.MainActivity.CMD.Companion.A8
import com.example.kotlin.MainActivity.CMD.Companion.E5
import com.example.kotlin.MainActivity.CMD.Companion.H9
import com.example.kotlin.MainActivity.MODE.Companion.MODE_LIST
import com.example.kotlin.MainActivity.MODE.Companion.MODE_STANDARD
import com.example.kotlin.MainActivity.MODE.Companion.MODE_TABS
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    lateinit var list:RecyclerView

    var adapter:ItemAdapter = ItemAdapter()

    fun initList(){

        list = findViewById(R.id.list);

        adapter = ItemAdapter()

        adapter.initData()

        val llm = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)

        list.adapter = adapter

        list.layoutManager = llm

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initList()

        fun_test()

        type_main()

        if_when()

        for_while()

        label_test()

        class_test()

        inherit_test()

        interface_test()

        extensions_test()

        data_test()

        sealed_test()

        enum_test()

        template_test()

        npe_test()

        operator_test()

        object_test()

        companion_test()

        delegate_test()

        scope_test()
    }


    fun Context.toast(msg : String, duration:Int = Snackbar.LENGTH_SHORT){
        Snackbar.make(window.decorView,msg,duration).show()
    }
    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        baseContext.toast("onTrimMemory")
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onStop() {
        super.onStop()
    }

    //1.指定注解的保留策略，AnnotationRetention.SOURCE表示只保留源码中，编译时删除。还有CLASS和RUNTIME
    @Retention(AnnotationRetention.SOURCE)
    //2.定义int 值 ，
    @IntDef(flag = true, value = [MODE_STANDARD, MODE_LIST, MODE_TABS])
    //3.定义注解类型
    annotation class MODE {
        companion object {
            const val MODE_STANDARD   = 1
            const val MODE_LIST       = 2
            const val MODE_TABS       = 4
        }
    }
    //4.使用注解 示例
    fun resetMode(@MODE m: Int) {
        mode = m
    }
    var mode: Int = 0

    fun testMode() {
        val m1 = MODE_LIST

        resetMode(m1)

        var m2 = m1 or (MODE_LIST and MODE_TABS)

    }


    //1.指定注解的保留策略，AnnotationRetention.SOURCE表示只保留源码中，
    @Retention(AnnotationRetention.SOURCE)
    //2.定义string值 ，它必需在 注解类型 前。
    @StringDef(A8,E5,H9)
    //3.定义注解类型，
    annotation class CMD {
        companion object {
            const val A8   = "A8"
            const val E5   = "E5"
            const val H9   = "H9"
        }
    }

    //4.使用
    fun sendData(@CMD cmd: String, data: String) {
        //... connect socket ,
        // socket.send
    }

    //5.测试
    fun testStringDef() {
        //sendData("cmd","data");//error ,"cmd"不是 定义的那几个值中的一个。
        sendData(A8, "hello")
    }

}
