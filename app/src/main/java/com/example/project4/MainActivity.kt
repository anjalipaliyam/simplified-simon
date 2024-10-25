package com.example.project4

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var game : Game

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        game = Game( this )

        val toast : Toast = Toast.makeText( this, game.generateTargetSequence(), Toast.LENGTH_LONG)
        toast.show()

        val resetButton : Button = findViewById(R.id.reset_button)
        resetButton.setOnClickListener{ resetGame() }
        val redButton : Button = findViewById(R.id.red_button)
        redButton.setOnClickListener{ buttonClicked("red") }
        val greenButton : Button = findViewById(R.id.green_button)
        greenButton.setOnClickListener{ buttonClicked("green") }
        val yellowButton : Button = findViewById(R.id.yellow_button)
        yellowButton.setOnClickListener{ buttonClicked("yellow") }
        val blueButton : Button = findViewById(R.id.blue_button)
        blueButton.setOnClickListener{ buttonClicked("blue") }

    }

    private fun buttonClicked(color : String) {
        game.updateCurrentSequence(color)
        if (game.checkSequence() == 2) {
            game.resetCurrentSequence()
            val toast = Toast.makeText(this, game.getTargetSequence(), Toast.LENGTH_LONG)
            toast.show()
        } else if (game.checkSequence() == 0) {
            finish()
        }
        game.setPreferences( this )
    }

    private fun resetGame() {
        game.resetGame()
        game.setPreferences( this )
        val toast = Toast.makeText(this, game.generateTargetSequence(), Toast.LENGTH_LONG)
        toast.show()
    }

}