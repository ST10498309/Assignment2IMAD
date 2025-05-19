package vcmsa.ci.a2history

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Questions : AppCompatActivity() {

    private lateinit var txtQuestions: TextView
    private lateinit var btnTrue: Button
    private lateinit var btnFalse: Button
    private lateinit var btnNext: Button
    private lateinit var imgQuestion: ImageView

    private val questions = listOf(
        "World War 2 began in 1939?",
        "Germany was part of the Allied powers?",
        "Anne Frank wrote a diary during WW2?",
        "WW2 ended in 1945?",
        "Hitler was the leader of Germany during WW2?"
    )

    private val answers = listOf(true, false, true, true, true)

    private val imageIds = listOf(
        R.drawable.war1,
        R.drawable.war2,
        R.drawable.anne,
        R.drawable.world,
        R.drawable.hit
    )

    private var currentIndex = 0
    private var score = 0
    private var selectedAnswer: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)


        txtQuestions = findViewById(R.id.txtQuestions)
        btnTrue = findViewById(R.id.btnTrue)
        btnFalse = findViewById(R.id.btnFalse)
        btnNext = findViewById(R.id.btnNext)
        imgQuestion = findViewById(R.id.imgQuestion)

        showQuestion()

        btnTrue.setOnClickListener {
            selectedAnswer = true
        }

        btnFalse.setOnClickListener {
            selectedAnswer = false
        }

        btnNext.setOnClickListener {
            if (selectedAnswer == null) {
                Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (selectedAnswer == answers[currentIndex]) {
                score++
                Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show()
            }

            currentIndex++

            if (currentIndex < questions.size) {
                showQuestion()
                selectedAnswer = null
            } else {
                val intent = Intent(this, ActivityScore::class.java)
                intent.putExtra("score", score)
                intent.putStringArrayListExtra("questions", ArrayList(questions))
                intent.putExtra("answers", answers.toBooleanArray())
                startActivity(intent)
                finish()
            }
        }
    }

    private fun showQuestion() {
        txtQuestions.text = questions[currentIndex]
        imgQuestion.setImageResource(imageIds[currentIndex])
    }
}