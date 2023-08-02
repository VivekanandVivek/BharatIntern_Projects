package com.vivek.dilyquiz.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import com.google.gson.Gson
import com.vivek.dilyquiz.R
import com.vivek.dilyquiz.models.Questions
import com.vivek.dilyquiz.models.Quiz

class ResultActivity : AppCompatActivity() {
    lateinit var quiz: Quiz
    lateinit var txtScore:TextView
    lateinit var txtAnswer:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        txtScore = findViewById(R.id.txtScore)
        txtAnswer = findViewById(R.id.txtAnswer)

        setUpViews()
        calculateScore()
        setAnswerView()
    }

    private fun setAnswerView() {

        val builder = StringBuilder("")
        for (entry in quiz.questions.entries) {
            val question = entry.value
            builder.append("<font color'#18206F'><b>Question: ${question.description}</b></font><br/><br/>")
            builder.append("<font color='#009688'>Answer: ${question.answer}</font><br/><br/>")
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            txtAnswer.text = Html.fromHtml(builder.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            txtAnswer.text = Html.fromHtml(builder.toString());
        }
    }

    private fun calculateScore() {
        var score = 0
        for (entry in quiz.questions.entries){
            val question:Questions = entry.value
            if(question.answer == question.useranswer){
                score+=10
            }
        }
        txtScore.text = "Your Score : $score"
    }

    fun setUpViews(){
        val quizData = intent.getStringExtra("Quiz")
        quiz = Gson().fromJson<Quiz>(quizData,Quiz::class.java)
    }
}