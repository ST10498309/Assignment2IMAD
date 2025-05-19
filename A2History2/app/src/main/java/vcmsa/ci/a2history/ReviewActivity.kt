package vcmsa.ci.a2history

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)

        val txtReview = findViewById<TextView>(R.id.txtReview)

        val questions = intent.getStringArrayListExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        val reviewText = StringBuilder()

        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                val answerText = if (answers[i]) "True" else "False"
                reviewText.append("${i + 1}. ${questions[i]} - Answer: $answerText\n\n")
            }
        } else {
            reviewText.append("Review data not available or mismatched.")
        }

        txtReview.text = reviewText.toString()
    }
}






