package com.example.project4

import android.content.Context
import android.content.SharedPreferences

class Game {

    private var currentSequence : String = ""
    private var targetSequence : String = ""
    private var bestLevel : Int = 0

    constructor( context : Context ) {
        val pref : SharedPreferences =
            context.getSharedPreferences( context.packageName + "_preferences", Context.MODE_PRIVATE)
        this.bestLevel = pref.getInt(PREFERENCE_BEST_LEVEL.toString(), this.bestLevel)
    }

    fun generateTargetSequence() : String {
        if (this.bestLevel == 0) {
            this.targetSequence = generateSequence()
        } else {
            this.targetSequence = ""
            this.targetSequence = generateSequence()
            for (i in 0..<this.bestLevel) {
                this.updateTargetSequence()
            }
        }
        return this.targetSequence
    }

    fun updateCurrentSequence(color : String) {
        if (this.currentSequence.isEmpty()) {
            this.currentSequence = color
        } else {
            this.currentSequence = this.currentSequence + " " + color
        }
    }

    fun getTargetSequence() : String {
        return this.targetSequence
    }

    private fun updateTargetSequence() {
        this.targetSequence = this.targetSequence + " " + this.generateSequence()
    }

    private fun updateLevel() {
        this.bestLevel += 1
    }

    fun checkSequence() : Int {
        if (this.currentSequence == targetSequence) {
            this.updateLevel()
            this.updateTargetSequence()
            return 2
        } else if (this.targetSequence.startsWith(this.currentSequence)) {
            return 1
        } else {
            return 0
        }
    }

    fun resetCurrentSequence() {
        this.currentSequence = ""
    }

    private fun generateSequence() : String {
        val colors = arrayOf("red", "green", "yellow", "blue")
        val randomNumber = (0..3).random()
        return colors[randomNumber]
    }

    fun resetGame() {
        this.bestLevel = 0
        this.currentSequence = ""
    }

    fun setPreferences( context : Context ) {
        val pref : SharedPreferences =
            context.getSharedPreferences( context.packageName + "_preferences", Context.MODE_PRIVATE )
        val editor : SharedPreferences.Editor = pref.edit()
        editor.putInt( PREFERENCE_BEST_LEVEL.toString(), this.bestLevel )
        editor.commit()
    }

    companion object {
        private const val PREFERENCE_BEST_LEVEL : Int = 0
    }

}