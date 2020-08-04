package com.example.kotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data:ArrayList<String> = ArrayList()

    fun initData(){
        for (i in 0 until 32){
            data.add("kotlin " + i)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var lif = LayoutInflater.from(parent.context)
        var view = lif.inflate(R.layout.item,parent,false)

        var holder = ItemHolder(view)

        return holder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder (holder: RecyclerView.ViewHolder, position: Int) {
        var string = data.get(position)
        var ih : ItemHolder = holder as ItemHolder
        ih.text?.setText(string)
    }

    companion object class ItemHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text:TextView = itemView.findViewById(R.id.text)
    }
}