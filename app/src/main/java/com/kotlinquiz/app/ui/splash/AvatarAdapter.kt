package com.kotlinquiz.app.ui.splash


import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.architectcoders.domain.avatar.Avatar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.card.MaterialCardView
import com.kotlinquiz.app.R
import com.kotlinquiz.app.databinding.ViewHolderAvatarsBinding
import com.kotlinquiz.app.ui.common.basicDiffUtil
import com.squareup.picasso.Picasso

class AvatarAdapter(val context: Context, private val listener: (Avatar) -> Unit) :
    RecyclerView.Adapter<AvatarAdapter.ViewHolder>() {
    lateinit var avatarClickListener: AvatarClickListener
    var avatars: List<Avatar> by basicDiffUtil(
        emptyList(),
        areItemsTheSame = { old, new -> old.title == new.title }
    )


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ViewHolderAvatarsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = avatars.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val avatar = avatars[position]
        holder.bind(avatar)

        holder.itemView.setOnClickListener {

        }
    }


    inner class ViewHolder(private val viewBinding: ViewHolderAvatarsBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(avatar: Avatar) {

            with(viewBinding) {
                avatarCard.transitionName = avatar.title

                Glide.with(context)
                    .asBitmap()
                    .load(avatar.image)
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            val palette = Palette.from(resource).generate()
                            palette.darkVibrantSwatch?.let {
                                avatarCard.setCardBackgroundColor(it.rgb)
                            } ?: palette.lightVibrantSwatch?.let {
                                avatarCard.setCardBackgroundColor(it.rgb)
                            }
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {}
                    })


                Picasso.get()
                    .load(avatar.image)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_loading)
                    .into(avatarImg)

                avatarCard.transitionName = avatar.title

                avatarCard.setOnClickListener {
                    listener(avatar)
                    avatarClickListener.onAvatarClick(avatarCard)
                }

            }
        }
    }

    interface AvatarClickListener {
        fun onAvatarClick(cardView: MaterialCardView)
    }
}


