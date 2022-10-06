package com.example.whatsappclone.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.whatsappclone.R
import com.example.whatsappclone.User
import com.example.whatsappclone.databinding.ListItemBinding
import com.squareup.picasso.Picasso

class UsersViewHolder(itemView: ListItemBinding) : RecyclerView.ViewHolder(itemView.root) {
    fun bind(user: User, onClick: (name: String, photo: String, id: String) -> Unit) =
        with(itemView) {
            itemView.timeTv.isVisible = false
            itemView.titleTv.text = user.name
            itemView.subTitleTv.text = user.status
            Picasso.get()
                .load(user.thumbImage)
                .placeholder(R.drawable.defaultavatar)
                .error(R.drawable.defaultavatar)
                .into(itemView.userImgView)
            setOnClickListener {
                onClick.invoke(user.name, user.thumbImage, user.uid)
            }
        }
}

class EmptyViewHolder(view: View) : RecyclerView.ViewHolder(view)