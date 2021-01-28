package com.kotlinquiz.app.ui.questions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.viewpager.widget.ViewPager
import com.architectcoders.domain.question.Question
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd

import com.google.android.gms.ads.MobileAds
import com.kotlinquiz.app.R
import com.kotlinquiz.app.databinding.FragmentMainBinding
import com.kotlinquiz.app.ui.common.appF
import com.kotlinquiz.app.ui.common.getViewModelF

import com.thomy.library.CountDownTime


class MainFragment : Fragment() {


    private lateinit var countDownTimerView: CountDownTime

    private lateinit var adapterQuiz: QuestionAdapter

    private lateinit var binding: FragmentMainBinding

    private lateinit var component: QuestionComponent

    lateinit var navController: NavController

    private var NEXT_ITEM = 0

    private lateinit var mInterstitialAd: InterstitialAd

    private val viewModel: QuestionViewModel by lazy { getViewModelF { component.questionViewModel } }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        //publish

        MobileAds.initialize(
            context
        )
        val adRequest= AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)




        mInterstitialAd = InterstitialAd(context)
        mInterstitialAd.adUnitId="ca-app-pub-9805799677002594~7829592207"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        if (mInterstitialAd.isLoaded){
            mInterstitialAd.show()
        }


        //end publish

        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (shouldInterceptBackPress()) {
                        val alert = AlertDialog.Builder(context!!)
                        alert.setTitle(appF.getString(R.string.app_name))


                        alert.setMessage(appF.getString(R.string.message))
                        alert.setPositiveButton(appF.getString(R.string.yes)) { _, _ ->


                            activity?.moveTaskToBack(true)
                        }

                        alert.setNegativeButton(appF.getString(R.string.no)) { _, _  ->
                            Toast.makeText(context!!.applicationContext, appF.getString(R.string.app_name), Toast.LENGTH_LONG)
                                .show()

                        }
                        alert.show()
                    } else {
                        isEnabled = false
                        activity?.onBackPressed()
                    }

                }
            })

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
        with(binding) {
            countDownTimerView = timeInclude.time


            component = appF.component.plus(QuestionModule())

            viewModel.question.observe(viewLifecycleOwner, Observer(::getData))
            viewModel.onGetAllQuestions()


            clickListeners()

            countDownTimerView.startTimer(
                timerCount = 30,
                onCountDownTimerStarted = {
                    Log.d("Timer: ", "Started")
                },
                onCountDownTimerRunning = {
                    Log.d("Timer: ", "Running $it")
                },
                onCountDownTimerStopped = {

                    buttonEnable(false)

                    Log.d("Timer: ", "Stopped")
                })
        }

        btnResult()
    }


    private fun clickListeners() {
        with(binding) {
            buttons.answerOne.setOnClickListener {
                NEXT_ITEM++
                pager.setCurrentItem(NEXT_ITEM, true)


            }
            buttons.answerTwo.setOnClickListener {
                NEXT_ITEM++
                pager.setCurrentItem(NEXT_ITEM, true)

            }
        }
    }

    private fun getData(uiModel: QuestionViewModel.UiModel?) {

        when (uiModel) {

            is QuestionViewModel.UiModel.Content -> {

                setupPager(uiModel.question)

            }
        }
    }

    private fun setupPager(question: List<Question>) {

        with(binding) {


            root.apply {


                adapterQuiz = QuestionAdapter(appF, question)

                pager.adapter = adapterQuiz

                buttons.answerOne.text = question[0].answers[0].answer
                buttons.answerTwo.text = question[0].answers[1].answer

                pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(p0: Int) {


                    }

                    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

                    }

                    override fun onPageSelected(p0: Int) {

                        if (p0 >= NEXT_ITEM) {
                            buttonEnable(true)
                            NEXT_ITEM = p0
                            countDownTimerView.resetTimer()
                        } else {
                            buttonEnable(false)
                            timeInclude.motionTime.transitionToEnd()
                            countDownTimerView.resetTimer()
                            countDownTimerView.stopTimer()


                        }

                        motionAction()
                        buttons.answerOne.text = question[p0].answers[0].answer
                        buttons.answerTwo.text = question[p0].answers[1].answer

                        if (p0 == adapterQuiz.count - 1) {

                            timeInclude.btnResult.image.visibility = View.VISIBLE

                            timeInclude.btnResult.btnMotion.transitionToEnd()


                        }
                    }
                })
            }
        }
    }

    private fun motionAction() {
        with(binding) {
            timeInclude.motionTime.transitionToStart()
            timeInclude.motionTime.transitionToEnd()
            buttons.motionButton.transitionToEnd()
        }
    }

    private fun buttonEnable(mEnable: Boolean) {
        with(binding) {
            if (!mEnable) {
                buttons.answerOne.isEnabled = false
                buttons.answerTwo.isEnabled = false
            } else {
                buttons.answerOne.isEnabled = true
                buttons.answerTwo.isEnabled = true
            }
        }
    }

    private fun btnResult(){
        with(binding){
            timeInclude.btnResult.image.setOnClickListener {
                val action=MainFragmentDirections.actionMainFragmentToWinnerFragment()
                navController.navigate(action)

            }
        }
    }

    fun shouldInterceptBackPress() = true
}