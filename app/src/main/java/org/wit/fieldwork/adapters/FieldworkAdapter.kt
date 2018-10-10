package org.wit.fieldwork.adapters;
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_fieldwork.view.*

import org.wit.fieldwork.R
import org.wit.fieldwork.models.FieldworkModel

interface FieldworkListener {
  fun onFieldworkClick(fieldwork: FieldworkModel)
}

class FieldworkAdapter constructor(private var fieldworks: List<FieldworkModel>,
    private val listener: FieldworkListener): RecyclerView.Adapter<FieldworkAdapter.MainHolder>(){

    override fun onCreateViewHolder(parent:ViewGroup,viewType:Int):MainHolder{
    return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_fieldwork,parent,false))
    }

    override fun onBindViewHolder(holder:MainHolder,position:Int){
    val fieldwork=fieldworks[holder.adapterPosition]
    holder.bind(fieldwork, listener)
    }

    override fun getItemCount():Int=fieldworks.size

  class MainHolder constructor(itemView:View):RecyclerView.ViewHolder(itemView){

    fun bind(fieldwork:FieldworkModel, listener: FieldworkListener){
    itemView.fieldworkTitle.text=fieldwork.title
    itemView.fieldworkDescription.text=fieldwork.description
      itemView.setOnClickListener { listener.onFieldworkClick(fieldwork) }
    }
    }
}