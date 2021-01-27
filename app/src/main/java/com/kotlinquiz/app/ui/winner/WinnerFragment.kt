package com.kotlinquiz.app.ui.winner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.kotlinquiz.app.R
import com.kotlinquiz.app.databinding.FragmentWinnerBinding
import com.kotlinquiz.app.ui.common.appF


class WinnerFragment : Fragment() {


private lateinit var binding:FragmentWinnerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentWinnerBinding.inflate(inflater,container,false)
        MobileAds.initialize(
            appF
        )
        val adRequest= AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        return binding.root
    }


}