package com.example.horoscapp.ui.luck

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.ROTATION
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.activity.OnBackPressedCallback
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.fragment.app.Fragment
import com.example.horoscapp.R
import com.example.horoscapp.R.anim.fade_in
import com.example.horoscapp.R.anim.fade_out
import com.example.horoscapp.R.anim.move_card_down
import com.example.horoscapp.R.anim.move_card_up
import com.example.horoscapp.R.anim.show_roulette
import com.example.horoscapp.R.anim.hide_roulette
import com.example.horoscapp.R.animator.flip_card_back
import com.example.horoscapp.R.animator.flip_card_front
import com.example.horoscapp.databinding.FragmentLuckBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class LuckFragment : Fragment() {

    private var _binding: FragmentLuckBinding? = null
    private val binding get() = _binding!!
    private var itsAnimated = false
    private var isPredictionShowing = false

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
        binding.ivLuckyRoulette.setOnClickListener { spinRoulette() }

        binding.btnLuckTryAgain.setOnClickListener {
            if (isPredictionShowing && !itsAnimated) {
                itsAnimated = true
                flipCard(false)
            }
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!itsAnimated) {
                    if (isPredictionShowing) {
                        itsAnimated = true
                        flipCard(false)

                    } else {
                        isEnabled = false
                        requireActivity().onBackPressedDispatcher.onBackPressed()
                        isEnabled = true
                    }
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun spinRoulette() {
        if (!itsAnimated) {
            itsAnimated = true
            val degrees = (Random.nextInt(360 * 2) + 90).toFloat()
            val animator = ObjectAnimator.ofFloat(binding.ivLuckyRoulette, ROTATION, 0f, degrees)
            animator.apply {
                duration = 1200
                interpolator = DecelerateInterpolator()
            }
            animator.doOnEnd {
                moveCard(true)
            }
            animator.start()
        }
    }

    private fun moveCard(up: Boolean) {
        val animation =
            AnimationUtils.loadAnimation(context, if (up) move_card_up else move_card_down)

        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {
                if (up) {
                    binding.ivLuckyCard.visibility = VISIBLE
                }
            }

            override fun onAnimationEnd(animation: Animation?) {
                if (up) {
                    scaleCard(true)

                } else {
                    binding.ivLuckyCard.visibility = INVISIBLE
                    itsAnimated = false
                    isPredictionShowing = false
                }
            }
        })

        binding.ivLuckyCard.startAnimation(animation)
    }

    private fun scaleCard(grow: Boolean) {
        val card = AnimationUtils.loadAnimation(context, if (grow) R.anim.grow else R.anim.shrink)

        card.setAnimationListener(object : AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {
                showRoulette(!grow)
            }

            override fun onAnimationEnd(animation: Animation?) {
                if (grow) {
                    flipCard(true)

                } else {
                    moveCard(false)
                }
            }
        })
        binding.ivLuckyCard.startAnimation(card)

        val tarot = AnimationUtils.loadAnimation(context, if (grow) R.anim.grow else R.anim.shrink)
        binding.ivLuckPredictionCard.startAnimation(tarot)
    }

    private fun showRoulette(show: Boolean) {
        val animation =
            AnimationUtils.loadAnimation(context, if (show) show_roulette else hide_roulette)

        animation.setAnimationListener(object : AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {
                if (show) {
                    binding.ivLuckyRoulette.visibility = VISIBLE
                }
            }

            override fun onAnimationEnd(animation: Animation?) {
                if (!show) {
                    binding.ivLuckyRoulette.visibility = INVISIBLE
                }
            }
        })

        binding.ivLuckyRoulette.startAnimation(animation)
    }

    private fun flipCard(show: Boolean) {
        val front = AnimatorInflater.loadAnimator(context, flip_card_front) as AnimatorSet
        val back = AnimatorInflater.loadAnimator(context, flip_card_back) as AnimatorSet

        if(show){
            front.setTarget(binding.ivLuckyCard)
            back.setTarget(binding.ivLuckPredictionCard)

        } else {
            front.setTarget(binding.ivLuckPredictionCard)
            back.setTarget(binding.ivLuckyCard)
        }

        front.doOnStart {
            if(show){
                binding.ivLuckPredictionCard.visibility = VISIBLE

            } else {
                binding.ivLuckyCard.visibility = VISIBLE
            }

            back.start()
            binding.tvLuckPrediction.fade(show)
            binding.cardLuckShare.fade(show)
            binding.cardLuckTryAgain.fade(show)
        }

        front.doOnEnd {
            if(show){
                binding.ivLuckyCard.visibility = INVISIBLE
                itsAnimated = false
                isPredictionShowing = true

            } else {
                binding.ivLuckPredictionCard.visibility = INVISIBLE
                scaleCard(false)
            }
        }

        front.start()
    }

    private fun View.fade(show:Boolean){
        val fade = AnimationUtils.loadAnimation(context, if(show) fade_in else fade_out)
        fade.setAnimationListener(object : AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationStart(animation: Animation?) {
                if(show) this@fade.visibility = VISIBLE
            }
            override fun onAnimationEnd(animation: Animation?) {
                if(!show) this@fade.visibility = INVISIBLE
            }
        })
        this.startAnimation(fade)
    }

}