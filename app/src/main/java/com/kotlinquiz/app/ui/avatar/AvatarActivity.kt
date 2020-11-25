package com.kotlinquiz.app.ui.avatar

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback
import com.kotlinquiz.app.NavHostActivity
import com.kotlinquiz.app.R
import com.kotlinquiz.app.data.storage.AvatarManager
import com.kotlinquiz.app.databinding.ActivityAvatarBinding
import com.kotlinquiz.app.ui.common.app
import com.kotlinquiz.app.ui.common.getViewModel
import com.kotlinquiz.app.ui.questions.utils.Utils

class AvatarActivity : AppCompatActivity() {

    companion object {
        const val AVATAR = "AvatarActivity:avatar"
    }

    private lateinit var binding: ActivityAvatarBinding

    lateinit var avatarManager: AvatarManager

    private lateinit var component: AvatarActivityComponent
    private val viewModel by lazy { getViewModel { component.avatarViewModel } }


    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        binding = ActivityAvatarBinding.inflate(layoutInflater)

        avatarManager = app.avatar

        window?.let { Utils.darkenStatusBar(it, R.color.colorPrimary, false) }

        component = app.component.plus(AvatarActivityModule(intent.getStringExtra(AVATAR)!!))
        viewModel.avatar.observe(this, Observer(::getAvatarUi))


        setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
        window.sharedElementEnterTransition = buildContainerTransform()
        window.sharedElementReturnTransition = buildContainerTransform()
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        binding.btnOk.setOnClickListener {
            val intent = Intent(this, NavHostActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getAvatarUi(uiAvatar: AvatarViewModel.UiAvatar) = with(uiAvatar.avatar) {
        with(binding) {
            avatarManager.saveImage(image)
            Glide.with(app)
                .asBitmap()
                .load(image)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {

                        avatarImg.avatar.setImageBitmap(resource)
                        val palette = Palette.from(resource).generate()
                        palette.darkVibrantSwatch?.let {
                            constraint.setBackgroundColor(it.rgb)
                        } ?: palette.lightVibrantSwatch?.let {
                            constraint.setBackgroundColor(it.rgb)
                        }
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
        }
    }

    private fun buildContainerTransform() =
        MaterialContainerTransform().apply {
            addTarget(binding.constraint)
            duration = 300
            interpolator = FastOutSlowInInterpolator()
            fadeMode = MaterialContainerTransform.FADE_MODE_IN
        }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> {
                true
            }
        }
    }
}