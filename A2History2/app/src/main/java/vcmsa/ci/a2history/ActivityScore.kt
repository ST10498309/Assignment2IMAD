package vcmsa.ci.a2history

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivityScore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        val txtScore = findViewById<TextView>(R.id.txtScore)
        val btnReview = findViewById<Button>(R.id.btnReview)
        val btnExit = findViewById<Button>(R.id.btnExit)

        val score = intent.getIntExtra("score", 0)
        val questions = intent.getStringArrayListExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")


        val message = when (score) {
            5 -> "Outstanding! You got all the questions right!History Master!"
            4 -> "Great job! Just one mistake."
            3 -> "Good effort! You got more than half."
            2 -> "Not bad, but you can do better! Better hit those books!!"
            1 -> "You got one right. Keep learning!"
            else -> "Oops! Better luck next time."
        }

        txtScore.text = "You scored $score out of 5.\n$message"


        btnReview.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putStringArrayListExtra("questions", questions)
            intent.putExtra("answers", answers)
            startActivity(intent)
        }


        btnExit.setOnClickListener {
            finishAffinity()
        }
    }
}