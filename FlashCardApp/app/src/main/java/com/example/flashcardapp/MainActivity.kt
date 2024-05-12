package com.example.flashcardapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.view.View
import com.example.flashcardapp.ui.theme.FlashCardAppTheme
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var mSetRightOut: AnimatorSet
    private lateinit var mSetLeftIn: AnimatorSet
    private var mIsBackVisible = false
    private lateinit var mCardFrontLayout: View
    private lateinit var mCardBackLayout: View

    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViews()
        loadAnimations()
        changeCameraDistance()
    }

    private fun changeCameraDistance() {
        val distance = 8000
        val scale = resources.displayMetrics.density * distance
        mCardFrontLayout.cameraDistance = scale
        mCardBackLayout.cameraDistance = scale
    }

    private fun loadAnimations() {
        mSetRightOut = AnimatorInflater.loadAnimator(this, R.animator.out_animation) as AnimatorSet
        mSetLeftIn = AnimatorInflater.loadAnimator(this, R.animator.in_animation) as AnimatorSet
    }

    private fun findViews() {
        mCardBackLayout = findViewById(R.id.cardback)
        mCardFrontLayout = findViewById(R.id.cardfront)
    }

    fun flipCard(view: View) {
        if (!mIsBackVisible) {
            mSetRightOut.setTarget(mCardFrontLayout)
            mSetLeftIn.setTarget(mCardBackLayout)
            mSetRightOut.start()
            mSetLeftIn.start()
            mIsBackVisible = true
        } else {
            mSetRightOut.setTarget(mCardBackLayout)
            mSetLeftIn.setTarget(mCardFrontLayout)
            mSetRightOut.start()
            mSetLeftIn.start()
            mIsBackVisible = false
        }
    }
}