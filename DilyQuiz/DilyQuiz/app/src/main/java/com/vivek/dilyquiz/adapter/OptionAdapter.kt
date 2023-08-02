package com.vivek.dilyquiz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.vivek.dilyquiz.R
import com.vivek.dilyquiz.models.Questions

class OptionAdapter(val context: Context,val questions:Questions):
    RecyclerView.Adapter<OptionAdapter.OptionViewHolder>() {

    private var options:List<String> = listOf(questions.option1,questions.option2,questions.option3,questions.option4)


    inner class OptionViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var optionView = itemView.findViewById<TextView>(R.id.quiz_option)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.option_item,parent,false)
        return OptionViewHolder(view)
    }

    override fun getItemCount(): Int {
        return options.size
    }

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        holder.optionView.text = options[position]
        holder.itemView.setOnClickListener{

            questions.useranswer = options[position]
            notifyDataSetChanged()
        }
        if(questions.useranswer == options[position]){
            holder.itemView.setBackgroundResource(R.drawable.option_item_selected_bg)
        }
        else{
            holder.itemView.setBackgroundResource(R.drawable.option_item_bg)
        }
    }
}