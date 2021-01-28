package com.kotlinquiz.app.ui.testFragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.InterstitialAd
import com.kotlinquiz.app.R
import com.kotlinquiz.app.databinding.FragmentTestSolidBinding
import com.kotlinquiz.app.ui.common.*
import com.kotlinquiz.app.ui.questions.utils.Utils


class TestSolidFragment : Fragment(), TestSolidAdapter.Interaction {

    lateinit var navController: NavController

    private lateinit var adapterQuestion: TestSolidAdapter

    private lateinit var binding: FragmentTestSolidBinding

    private lateinit var component: TestFragmentComponent

    private lateinit var mInterstitialAd:InterstitialAd

    private val viewModel:TestSolidViewModel by lazy { getViewModelF { component.testSolidViewModel } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = container!!.bindingInflate(R.layout.fragment_test_solid, false)

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
                    if(shouldInterceptBackPress()){
                       activity?. moveTaskToBack(true)
                    }else{
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

        component= appF.component.plus(TestSolidModule())




        activity?.window?.let { Utils.darkenStatusBar(it, R.color.blue_bar, false) }


        viewModel.model.observe(viewLifecycleOwner, EventObserver {
            viewModel.getQuestionFromDb()
        })

        setHasOptionsMenu(true)
        adapterQuestion = TestSolidAdapter(this@TestSolidFragment)
        binding.recycler.adapter = adapterQuestion

        binding.apply {
            viewmodel=viewModel
            lifecycleOwner = this@TestSolidFragment
        }


        val answers = resources.getStringArray(R.array.Answers)



        binding.bntCheck.setOnClickListener {
            val answerSummary = viewModel.calculateResult()

            val result = compareValues(answers.toList(), answerSummary)

            val action=TestSolidFragmentDirections.actionTestSolidToMainFragment(result)
            navController.navigate(action)

        }
    }


    private fun compareValues(answers: List<String>, userAnswer: List<String>): Int {
        var c = 0
        answers.forEachIndexed { idx, value ->
            c = if (userAnswer[idx] == value) ++c else c

        }
        return 100 * c / answers.size
    }


    override fun onItemSelected(position: Int, selection: String) {

        viewModel.updateAnswer(position, selection)
        adapterQuestion.notifyDataSetChanged()
    }

    fun shouldInterceptBackPress() = true

}
