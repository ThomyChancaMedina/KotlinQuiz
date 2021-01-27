package com.kotlinquiz.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.kotlinquiz.app.databinding.ActivityNavHostBinding


class NavHostActivity : AppCompatActivity() {

    private lateinit var binding:ActivityNavHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityNavHostBinding.inflate(layoutInflater).apply {
            setContentView(root)


            MobileAds.initialize(
                this@NavHostActivity
            )
            val adRequest=AdRequest.Builder().build()

        }



    }
}