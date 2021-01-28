package com.kotlinquiz.app.ui.splash

import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import com.google.android.material.card.MaterialCardView
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.kotlinquiz.app.NavHostActivity
import com.kotlinquiz.app.R
import com.kotlinquiz.app.data.storage.AvatarManager
import com.kotlinquiz.app.databinding.ActivitySplashBinding
import com.kotlinquiz.app.ui.avatar.AvatarActivity
import com.kotlinquiz.app.ui.common.app
import com.kotlinquiz.app.ui.common.getViewModel
import com.kotlinquiz.app.ui.common.postDelayed
import com.kotlinquiz.app.ui.questions.utils.Utils
import com.squareup.picasso.Picasso


class SplashActivity : AppCompatActivity() {

    private lateinit var component: SplashComponent
    private val viewModel: SplashViewModel by lazy { getViewModel { component.splashViewModel } }

    private lateinit var avatarAdapter: AvatarAdapter

    lateinit var avatarManager: AvatarManager

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        avatarManager = app.avatar


        window?.let { Utils.darkenStatusBar(it, R.color.colorPrimary, false) }

        setExitSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementsUseOverlay = false
        setContentView(binding.root)
        super.onCreate(savedInstanceState)

//        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager



        component = app.component.plus(SplashModule())

        avatarAdapter = AvatarAdapter(this, viewModel::onAvatarClicked)
        binding.recycler.adapter = avatarAdapter


        viewModel.avatar.observe(this, Observer(::dataAvatar))
        viewModel.onGetAllAvatars()


        val a =
            "https://firebasestorage.googleapis.com/v0/b/kotlinquiz-6d189.appspot.com/o/1606993.jpg?alt=media&token=d803ca06-f88e-41e4-be65-f6ae368375ba"

        Picasso.get()
            .load(a)
            .placeholder(R.drawable.ic_loading)
            .error(R.drawable.ic_loading)
            .into(binding.logo)

        postDelayed(5000) {
            if (avatarManager.avatarImage.isEmpty()) {
                binding.splashId.visibility = View.GONE
                binding.selectionAvatar.visibility = View.VISIBLE

            } else {
                val intent = Intent(this, NavHostActivity::class.java)
                startActivity(intent)
            }


        }
    }

    private fun dataAvatar(uiSplash: SplashViewModel.UiSplash?) {
        when (uiSplash) {
            is SplashViewModel.UiSplash.Content -> avatarAdapter.avatars = uiSplash.avatars
            is SplashViewModel.UiSplash.Navigation -> {
                avatarAdapter.avatarClickListener = object : AvatarAdapter.AvatarClickListener {
                    override fun onAvatarClick(cardView: MaterialCardView) {
                        val intent = Intent(this@SplashActivity, AvatarActivity::class.java)
                        intent.putExtra(AvatarActivity.AVATAR, uiSplash.avatar.title)

                        val option = ActivityOptionsCompat.makeSceneTransitionAnimation(
                            this@SplashActivity, cardView, "shared_element"
                        )
                        startActivity(intent, option.toBundle())
                    }
                }
            }
        }
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.isActiveNetworkMetered
        return activeNetworkInfo.not()
    }


}