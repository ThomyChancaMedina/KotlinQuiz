package com.kotlinquiz.app.ui.winner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import com.kotlinquiz.app.databinding.FragmentWinnerBinding


class WinnerFragment : Fragment() {


    private lateinit var mInterstitialAd: InterstitialAd

private lateinit var binding:FragmentWinnerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentWinnerBinding.inflate(inflater,container,false)
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
        return binding.root
    }


}