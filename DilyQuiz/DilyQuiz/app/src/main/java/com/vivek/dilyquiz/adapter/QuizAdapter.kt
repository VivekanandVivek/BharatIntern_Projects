package com.vivek.dilyquiz.adapter

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.vivek.dilyquiz.R
import com.vivek.dilyquiz.activity.QuestionActivity
import com.vivek.dilyquiz.models.Quiz
import com.vivek.dilyquiz.utils.ColorPicker
import com.vivek.dilyquiz.utils.IconPicker


class QuizAdapter(val context: Context,val quizzes:List<Quiz>):RecyclerView.Adapter<QuizAdapter.QuizViewHolder>() {

    inner class QuizViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var textViewTitle:TextView = itemView.findViewById(R.id.quizTitle)
        var iconView:ImageView = itemView.findViewById(R.id.quizIcon)
        var cardContainer:CardView = itemView.findViewById(R.id.cardContainer)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuizViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.quiz_item,parent,false)
        return QuizViewHolder(view)
    }

    override fun getItemCount(): Int {
        return quizzes.size
    }

    override fun onBindViewHolder(holder: QuizViewHolder, position: Int) {
        holder.textViewTitle.text = quizzes[position].title
        holder.iconView.setImageResource(IconPicker.getIcon())

        // Is there any error present in the below line
        //holder.cardContainer.setCardBackgroundColor(Color.parseColor("#000000"))

        holder.cardContainer.setCardBackgroundColor(Color.parseColor(ColorPicker.getColor()))


        holder.itemView.setOnClickListener{
            Toast.makeText(context,quizzes[position].title,Toast.LENGTH_SHORT).show()

            val intent = Intent(context,QuestionActivity::class.java)
            intent.putExtra("DATE",quizzes[position].title)
            context.startActivity(intent)
        }

    }
}