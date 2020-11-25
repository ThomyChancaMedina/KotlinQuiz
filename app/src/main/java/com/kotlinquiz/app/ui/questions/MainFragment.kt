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

    private val viewModel: QuestionViewModel by lazy { getViewModelF { component.questionViewModel } }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countDownTimerView=binding.timeInclude.time


        component = appF.component.plus(QuestionModule())

        viewModel.question.observe(viewLifecycleOwner, Observer(::getData))
        viewModel.onGetAllQuestions()


//        clickListeners()

        countDownTimerView.startTimer(
            timerCount = 30,
            onCountDownTimerStarted = {
                Log.d("Timer: ", "Started")
            },
            onCountDownTimerRunning = {
                Log.d("Timer: ", "Running $it")
            },
            onCountDownTimerStopped = {
                Log.d("Timer: ", "Stopped")
            })

    }


//    private fun clickListeners() {
//        add_to_cart.setOnClickListener {
//
//
//        }
//    }

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
                pager.addOnPageChangeListener(timeInclude.motionTime as ViewPager.OnPageChangeListener)
                pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                    override fun onPageScrollStateChanged(p0: Int) {


                    }

                    override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {

                    }

                    override fun onPageSelected(p0: Int) {


                        buttons.answerOne.text = question[p0].answers[0].answer
                        buttons.answerTwo.text = question[p0].answers[1].answer
                        countDownTimerView.resetTimer()

                        buttons.motionButton.transitionToEnd()
                    }

                })

            }
        }
    }
}