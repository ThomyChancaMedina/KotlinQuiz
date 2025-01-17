package com.kotlinquiz.app.ui.questions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.architectcoders.domain.question.Question
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.kotlinquiz.app.R
import com.kotlinquiz.app.databinding.ViewHolderQuestionBinding
import com.kotlinquiz.app.ui.common.app


class QuestionAdapter(private val context: Context, var questions: List<Question>) :
    PagerAdapter() {

    private val avatarManager = context.app.avatar

    @SuppressLint("SetTextI18n")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val itemQuiz = questions[position]
        val layout = LayoutInflater.from(context).inflate(R.layout.view_holder_question, container, false)

        val viewBinding = ViewHolderQuestionBinding.bind(layout)

        with(viewBinding) {
            layout.apply {
                quiz.text = itemQuiz.question
                quizNum.text = questions.size.toString() + "/" + (position + 1)


            }

            container.addView(layout)


            Glide.with(context)
                .asBitmap()
                .load(avatarManager.avatarImage)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        avatarCircle.avatar.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {}
                })
            motionIcon.transitionToEnd()
        }

        return layout
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) =
        container.removeView(`object` as View)


    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`


    override fun getCount(): Int = questions.size


}