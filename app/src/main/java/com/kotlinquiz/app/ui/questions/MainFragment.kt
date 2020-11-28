package com.kotlinquiz.app.ui.questions

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.architectcoders.domain.question.Question
import com.kotlinquiz.app.databinding.FragmentMainBinding
import com.kotlinquiz.app.ui.common.appF
import com.kotlinquiz.app.ui.common.getViewModelF

import com.thomy.library.CountDownTime


class MainFragment : Fragment() {


    private lateinit var countDownTimerView: CountDownTime

    private lateinit var adapterQuiz: QuestionAdapter

    private lateinit var binding: FragmentMainBinding

    private lateinit var component: QuestionComponent

    private var NEXT_ITEM = 0


    private val viewModel: QuestionViewModel by lazy { getViewModelF { component.questionViewModel } }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

//                pager.addOnPageChangeListener(timeInclude.motionTime as ViewPager.OnPageChangeListener)
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

                        if (p0 == adapterQuiz.count) {
                            Log.d("TAG", "onPageSelected: thomy:: " + adapterQuiz.count)

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
}