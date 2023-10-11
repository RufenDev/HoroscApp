package com.example.horoscapp.ui.luck

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.ROTATION
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.horoscapp.R
import com.example.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLuckBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
    }

    private fun initUI() {

        initListeners()

    }

    private fun initListeners() {
        binding.ivLuckyRoulette.setOnClickListener {
            spinRoulette()
        }
    }

    private fun spinRoulette() {
        val oneSpin = 360
        val degrees = (Random.nextInt(oneSpin * 3) + oneSpin).toFloat()

        val animator = ObjectAnimator.ofFloat(binding.ivLuckyRoulette, ROTATION, 0f, degrees)

        animator.duration = 1000
        animator.interpolator = DecelerateInterpolator()

        animator.doOnEnd { slideCard() }
        animator.start()
    }

    private fun slideCard() {
        val slideUp = AnimationUtils.loadAnimation(context, R.anim.slide_up)

        slideUp.setAnimationListener(object : AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationStart(animation: Animation?) {
                binding.ivLuckyCard.visibility = VISIBLE
            }

            override fun onAnimationEnd(animation: Animation?) {
                growCard()
            }
        })

        binding.ivLuckyCard.startAnimation(slideUp)
    }

    private fun growCard() {
        val grow = AnimationUtils.loadAnimation(context, R.anim.grow)

        grow.setAnimationListener(object : AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                showLuckPrediction()
            }
        })

        binding.ivLuckyCard.startAnimation(grow)
    }

    private fun showLuckPrediction() {
        val showAnimation = AlphaAnimation(0f, 1f)
        showAnimation.duration = 1000

        val hideAnimation = AlphaAnimation(1f, 0f)
        hideAnimation.duration = 200
        hideAnimation.setAnimationListener(object : AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationStart(animation: Animation?) {
                binding.layoutLuckPrediction.isVisible = true
                binding.layoutLuckPrediction.startAnimation(showAnimation)
            }
            override fun onAnimationEnd(animation: Animation?) {
                binding.layoutLuckyRoulette.isVisible = false
                binding.ivLuckyCard.visibility = INVISIBLE
            }
        })

        binding.layoutLuckyRoulette.startAnimation(hideAnimation)

    }

}